package com.jacksonyoudi.base


import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.util.Collector

class LineSplitter extends FlatMapFunction[String, Tuple2[String, Int]] {
  override def flatMap(t: String, collector: Collector[(String, Int)]): Unit = {
    val words: Array[String] = t.toLowerCase.split("\\W+")

    for (word <- words) {
      if (word.length > 0) {
        collector.collect(new Tuple2[String, Int](word, 1))
      }
    }

  }
}
