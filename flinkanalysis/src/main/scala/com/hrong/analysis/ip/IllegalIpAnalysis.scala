package com.hrong.analysis.ip

import java.util
import java.util.Date

import com.hrong.analysis.entity.{Blacklist, Log}
import com.hrong.analysis.source.NginxLogSource
import com.hrong.analysis.util.{ArrayUtil, LogParseUtil}
import org.apache.commons.lang3.time.FastDateFormat
import org.apache.flink.api.java.functions.KeySelector
import org.apache.flink.cep.PatternSelectFunction
import org.apache.flink.cep.pattern.conditions.{IterativeCondition, RichIterativeCondition}
import org.apache.flink.cep.scala.CEP
import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.{AssignerWithPeriodicWatermarks, AssignerWithPunctuatedWatermarks}
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor
import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, _}
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.time.Time

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

/**
  * ip检测规则：
  * 1. 同一ip每次请求间隔时间相同
  * 2. 重复请求同一地址次数过多
  * 3. 请求地址依次递增：https://www.subjectshow.com/major/info/1 https://www.subjectshow.com/major/info/2
  */
object IllegalIpAnalysis {
  val sdf: FastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss:SSS")

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //设置eventTime为流数据时间类型
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    //自定义的nginx-source，已实现去重功能
    val sourceData = env.addSource(new NginxLogSource)
    val requestLog = sourceData
      .filter(log => {
        //过滤静态资源请求
        val method = log.getRequestMethod
        if (method != null && (method.contains("cover") || method.contains("css")
          | method.contains("js") || method.contains("layer") || method.contains("fonts")
          | method.contains("img") || method.contains("face")) || method.contains("favicon")) {
          false
        } else {
          true
        }
      })
      //模拟数据
      /*val requestLog = env.fromElements(ArrayUtil.getLog(1), ArrayUtil.getLog(2),
        ArrayUtil.getLog(3), ArrayUtil.getLog(4),
        ArrayUtil.getLog(5), ArrayUtil.getLog(7),
        ArrayUtil.getLog(7), ArrayUtil.getLog(8),
        ArrayUtil.getLog(20), ArrayUtil.getLog(10),
        ArrayUtil.getLog(11), ArrayUtil.getLog(12),
        ArrayUtil.getLog(12), ArrayUtil.getLog(14))*/
      .assignTimestampsAndWatermarks(new AscendingTimestampExtractor[Log] {
      override def extractAscendingTimestamp(element: Log): Long = {
        element.getTime
      }
    }).keyBy(log => log.getIp)


    requestLog.print("请求日志数据：")
    //使用cep处理：请求地址依次递增：https://www.subjectshow.com/major/info/1 https://www.subjectshow.com/major/info/2
    //获取请求地址最末尾的数字，判断是否是连续递增的，如果状态持续次数过多，则判定为异常ip
    val pattern = Pattern.begin[Log]("start")
      .where(log => log.getRequestMethod.contains("/major/info/"))
      .followedBy("middle").where(new RichIterativeCondition[Log] {
      override def filter(t: Log, context: IterativeCondition.Context[Log]): Boolean = {
        //当前请求参数id
        val majorId = LogParseUtil.getRequestMajorId(t)
        //第一次请求参数id
        val startId = LogParseUtil.getRequestMajorId(context.getEventsForPattern("start").iterator().next())
        majorId > startId
      }
    }).times(4)
      //      .within(Time.seconds(10L))
//      .times(3, 10)

    val result = CEP.pattern(requestLog, pattern)
    result.select(new PatternSelectFunction[Log, Blacklist] {
      override def select(pattern: util.Map[String, util.List[Log]]): Blacklist = {
        var result: Blacklist = null
        if (pattern != null) {
          //请求路径中的数字
          val requestParams = ArrayBuffer[Integer]()
          //请求详情，方便后台查看
          val detail = ArrayBuffer[String]()
          //第一个匹配上的日志对象
          val startLog = pattern.get("start").get(0)
          //将能匹配上的数据的参数存入list，进行连续性判断
          requestParams.append(LogParseUtil.getRequestMajorId(startLog))
          detail.append(LogParseUtil.getDetailOfLog(startLog))

          for (num <- 0 until pattern.get("middle").size()) {
            requestParams.append(LogParseUtil.getRequestMajorId(pattern.get("middle").get(num)))
            detail.append(LogParseUtil.getDetailOfLog(pattern.get("middle").get(num)))
          }
          //若满足参数连续则将该ip返回进行后续操作
          if (ArrayUtil.isContinuousArray(requestParams.asJava)) {
            result = new Blacklist()

            result.setIp(startLog.getIp)
            result.setCheckTime(sdf.format(new Date()))
            result.setDetail(detail.toString())
            System.out.println(result)
          }
        }
        result
      }
    }).print()
    env.execute("source Job starting")
  }
}
