package com.jacksonyoudi.base

import org.apache.flink.api.common.RuntimeExecutionMode
import org.apache.flink.api.common.functions.{FlatMapFunction, ReduceFunction}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.util.Collector
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.TimeCharacteristic

object WCStreaming {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC)

    val ds: DataStream[String] = env.socketTextStream("127.0.0.1", 9988)

    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)

    val windowCounts: DataStream[WordWithCount] = ds.flatMap(
      new FlatMapFunction[String, WordWithCount]() {
        override def flatMap(t: String, collector: Collector[WordWithCount]) = {
          for (word <- t.split("\\s")) {
            collector.collect(WordWithCount(word, 1))
          }
        }
      }
    ).keyBy("word")
      .timeWindow(Time.seconds(5), Time.seconds(1))
      .reduce(
        new ReduceFunction[WordWithCount]() {
          override def reduce(t: WordWithCount, t1: WordWithCount): WordWithCount = {
            WordWithCount(t.word, t.count + t1.count)
          }
        }
      )

    windowCounts.print().setParallelism(1);
    env.execute("wc streaming")
  }

}
