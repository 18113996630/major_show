package com.hrong.analysis.ip

import com.hrong.analysis.entity.Log
import com.hrong.analysis.source.NginxLogSource
import org.apache.commons.lang3.time.FastDateFormat
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, _}
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

/**
  * ip检测规则：
  * 1. 同一ip每次请求间隔时间相同
  * 2. 重复请求同一地址次数过多
  * 3. 请求地址依次递增：https://www.subjectshow.com/major/1 https://www.subjectshow.com/major/2
  */
object IllegalIpAnalysis {
  val sdf: FastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss:SSS")

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //设置eventTime为流数据时间类型
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    //自定义的nginx-source，已实现去重功能
    val sourceData = env.addSource(new NginxLogSource)
    import org.apache.flink.api.scala._
    val requestLog = sourceData
      .filter(log => log.getRequestMethod.contains("/major/"))
      .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[Log] {
        // 事件时间
        var currentMaxTimestamp = 0L
        val maxOutOfOrder = 3000L
        var lastEmittedWatermark: Long = Long.MinValue

        // Returns the current watermark
        override def getCurrentWatermark: Watermark = {
          // 允许延迟三秒
          val potentialWM = currentMaxTimestamp - maxOutOfOrder
          // 保证水印能依次递增
          if (potentialWM >= lastEmittedWatermark) {
            lastEmittedWatermark = potentialWM
          }
          new Watermark(lastEmittedWatermark)
        }

        override def extractTimestamp(element: Log, previousElementTimestamp: Long): Long = {
          // 将元素的时间字段值作为该数据的timestamp
          val time = element.getTime
          if (time > currentMaxTimestamp) {
            currentMaxTimestamp = time
          }
          val outData = String.format("key: %s    EventTime: %s    waterMark:  %s",
            element.getIp,
            sdf.format(time),
            sdf.format(getCurrentWatermark.getTimestamp))
          println(outData)
          time
        }
      })
    requestLog.print("中间数据：")
    requestLog.keyBy(_.getIp)
      .window(TumblingEventTimeWindows.of(Time.seconds(5L)))
      .apply(new WindowFunction[Log, Log, String, TimeWindow] {
        override def apply(key: String, window: TimeWindow, input: Iterable[Log], out: Collector[Log]): Unit = {
          val iterator = input.iterator
          while(iterator.hasNext){
            val log = iterator.next()
            println("有数据：", log)
            out.collect(log)
          }
        }
      }).print("结果：：")






    /*requestLog.print("请求日志数据：")
    //使用cep处理：请求地址依次递增：https://www.subjectshow.com/major/1 https://www.subjectshow.com/major/2
    //获取请求地址最末尾的数字，判断是否是连续递增的，如果状态持续次数过多，则判定为异常ip
    val pattern = Pattern.begin[Log]("start")
      .where(log => log.getRequestMethod.contains("/major/"))
      .followedBy("middle").where(new RichIterativeCondition[Log] {
      override def filter(t: Log, context: IterativeCondition.Context[Log]): Boolean = {
        //当前请求参数id
        val majorId = LogParseUtil.getRequestMajorId(t)
        //第一次请求参数id
        val startId = LogParseUtil.getRequestMajorId(context.getEventsForPattern("start").iterator().next())
        majorId > startId
      }
    })
      //      .within(Time.seconds(10L))
//      .times(3, 10)

    val result = CEP.pattern(requestLog, pattern)
    result.select(new PatternSelectFunction[Log, Blacklist] {
      override def select(pattern: util.Map[String, util.List[Log]]): Blacklist = {
        var result: Blacklist = null
        if (pattern != null) {
          println("成功匹配")
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
    }).print()*/
    env.execute("source Job starting")
  }
}
