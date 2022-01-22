package com.jacksonyoudi.concurrent

import scala.concurrent._
import ExecutionContext.Implicits.global


object D01 {
  def printUserInfo(name: String): Unit = {
    println(s"${name} hello")
  }

  def main(args: Array[String]): Unit = {
    val f: Future[Unit] = Future {
      printUserInfo("jack")
    }
  }
}
