package com.jacksonyoudi.base

case class WordWithCount(word: String, count: Long) {
  override def toString: String = {
    word + " : " + count
  }
}
