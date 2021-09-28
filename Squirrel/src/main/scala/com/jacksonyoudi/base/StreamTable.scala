package com.jacksonyoudi.base

import org.apache.flink.api.common.RuntimeExecutionMode
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.Table

object StreamTable {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC)
    val tabEnv: StreamTableEnvironment = StreamTableEnvironment.create(env)


    val ds: DataStream[String] = env.socketTextStream("127.0.0.1", 9988)

    val wordStream: DataStream[String] = ds.flatMap(_.split(" "))

    val table: Table = tabEnv.fromDataStream(wordStream)
    table.printSchema()

  }
}
