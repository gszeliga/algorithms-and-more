package com.gzeliga.playground.algorithms.graph

import java.io.File
import scala.io.Source
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.concurrent.ConcurrentHashMap
import sun.misc.AtomicLong
import java.util.concurrent.atomic.AtomicInteger
import scala.concurrent.Promise
import scala.util.{ Success, Failure }
import scala.concurrent.Await
import scala.concurrent.duration._
import com.gzeliga.playground.algorithms.search.LinearProbingHashST

trait SymbolGraph {

  def contains(key: String): Boolean
  def index(key: String): Int
  def name(v: Int): String
}

class FileSymbolGraph(source: File, delimiter: String) extends SymbolGraph {

  val (st, size) = Source.fromFile(source).getLines.map { _.split(delimiter) }.foldLeft((new LinearProbingHashST[String, Int](97), 0)) {
   
    (acc, a) =>

      a.foldLeft(acc) { (acc, s) =>

        val m = acc._1
        val i = acc._2

        if (!m.contains(s)) {
          //Should've done the ST immutable...damn
          m.put(s, i)
          (m, i + 1)
        } else (m, i)

      }

  }
  
  val inverted = new Array[String](size)
  
  
  
  def contains(key: String) = ???
  def index(key: String) = ???
  def name(v: Int) = ???

}