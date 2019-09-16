package com.hrong.analysis.ip

import java.util

import com.hrong.analysis.entity.Log
import com.hrong.analysis.source.NginxLogSource
import com.hrong.analysis.util.{ArrayUtils, LogParseUtil}
import org.apache.commons.lang3.time.FastDateFormat
import org.apache.flink.cep.PatternSelectFunction
import org.apache.flink.cep.pattern.conditions.{IterativeCondition, RichIterativeCondition}
import org.apache.flink.cep.scala.CEP
import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks
import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, _}
import org.apache.flink.streaming.api.watermark.Watermark

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
    val sourceData: DataStream[Log] = env.addSource(new NginxLogSource)
    /*val requestLog: DataStream[Log] = sourceData
      .filter(log => {
        //过滤静态资源请求
        val method = log.getRequestMethod
        if (method != null && (method.contains("cover") || method.contains("css")
          | method.contains("js") || method.contains("layer") || method.contains("fonts")
          | method.contains("img") || method.contains("face"))) {
          false
        } else {
          true
        }
      })*/

    val requestLog = env.fromElements(ArrayUtils.getLog(1), ArrayUtils.getLog(2),
      ArrayUtils.getLog(3), ArrayUtils.getLog(4),
      ArrayUtils.getLog(5), ArrayUtils.getLog(46)).assignTimestampsAndWatermarks(new AssignerWithPunctuatedWatermarks[Log] {
      // 事件时间
      var currentMaxTimestamp = 0L
      val maxOutOfOrder = 2L
      var lastEmittedWatermark: Long = Long.MinValue

      override def checkAndGetNextWatermark(lastElement: Log, extractedTimestamp: Long): Watermark = {
        val potentialWM = currentMaxTimestamp - maxOutOfOrder
        // 保证水印能依次递增
        if (potentialWM >= lastEmittedWatermark) {
          lastEmittedWatermark = potentialWM
        }
        new Watermark(lastEmittedWatermark)
      }

      override def extractTimestamp(element: Log, previousElementTimestamp: Long): Long = {
        val timestamp = element.getTime
        if (timestamp > currentMaxTimestamp) {
          currentMaxTimestamp = timestamp
        }
        timestamp
      }
    })

    //使用cep处理：请求地址依次递增：https://www.subjectshow.com/major/info/1 https://www.subjectshow.com/major/info/2
    //获取请求地址最末尾的数字，判断是否是连续的，如果连续状态持续十次，则判定为异常ip
    val pattern = Pattern.begin[Log]("start").where(log => {
      if (log.getRequestMethod.contains("/major/info/")) {
        true
      } else {
        false
      }
    }).followedBy("middle").where(new RichIterativeCondition[Log] {
      override def filter(t: Log, context: IterativeCondition.Context[Log]): Boolean = {
        //GET /major/info/25 HTTP/1.1
        //当前请求参数id
        val majorId = LogParseUtil.getRequestMajorId(t)
        //第一次请求参数id
        val startId = LogParseUtil.getRequestMajorId(context.getEventsForPattern("start").iterator().next())
        val iteratorOfMiddle = context.getEventsForPattern("middle").iterator()
        val params = new util.ArrayList[Integer]()
        var count = 0
        while (iteratorOfMiddle.hasNext) {
          val item = iteratorOfMiddle.next()
          params.add(LogParseUtil.getRequestMajorId(item))
          count += 1
        }
        val isContinuous = ArrayUtils.isContinuousArray(params)
        if (majorId - startId == 1) {
          println("第二个 startId:", startId, "当前id:", majorId, "context获取到的数据:", params," 有"+count+"个")
          true
        } else if (isContinuous) {
          println("连续的 startId:", startId, "当前id:", majorId, "context获取到的数据:", params," 有"+count+"个")
          true
        } else {
          println("未匹配上 startId:", startId, "当前id:", majorId, "context获取到的数据:", params," 有"+count+"个")
          false
        }
      }
    }).times(4)

    val result = CEP.pattern(requestLog, pattern)
    result.select(new PatternSelectFunction[Log, String] {
      override def select(pattern: util.Map[String, util.List[Log]]): String = {
        var res: String = ""
        if (pattern != null) {
          val size = pattern.get("middle").size()
          var middle = ""
          for (num <- 0 until size) {
            middle += pattern.get("middle").get(num).getUser + "  "
          }
          res = "start:【" + pattern.get("start").get(0).getUser + "】 ->" +
            "middle: 【" + middle + "】 ->"
        }
        res
      }
    }).print()

    env.execute("source Job starting")
  }
}
