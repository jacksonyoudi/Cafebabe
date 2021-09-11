package com.jacksonyoudi.base

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._


object WC {
  def main(args: Array[String]): Unit = {
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    // 隐式转换
    val ds: DataSet[String] = env.fromElements(
      "flink spark storm",
      "flink druid clickhouse",
      "spark hbase elasticsearch"
    )

    val result: AggregateDataSet[(String, Int)] = ds.flatMap(new LineSplitter)
      .groupBy(0)
      .sum(1)

    result.printToErr()

  }
}



