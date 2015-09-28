package com.gszeliga.playground.algorithms.graph

import java.io.File
import scala.io.Source
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import scala.concurrent.Promise
import scala.util.{ Success, Failure }
import scala.concurrent.Await
import scala.concurrent.duration._
import com.gszeliga.playground.algorithms.search.LinearProbingHashST

trait SymbolGraph {

  def contains(key: String): Boolean
  def index(key: String): Option[Int]
  def name(v: Int): Option[String]
}

class FileSymbolGraph(source: File, delimiter: String) extends SymbolGraph {

  private val (st, size, inverted) = Source.fromFile(source).getLines.map { _.split(delimiter) }.foldLeft((new LinearProbingHashST[String, Int](97), 0, Vector[String]())) {

    (acc, a) =>

      a.foldLeft(acc) { (acc, s) =>

        val st = acc._1
        val i = acc._2
        val inverted = acc._3

        if (!st.contains(s)) {
          //Should've done the ST immutable...damn
          st.put(s, i)
          (st, i + 1, inverted :+ s)
        } else acc

      }

  }

  val G = new UndirectedGraph(size)

  Source.fromFile(source).getLines.map { _.split(delimiter) } foreach { a =>

    /*
     * 1- First, we convert the first label into a vertex index
     * 2- Then, we map each label -> (firt_vertex, vertex) so that we have each edge ready to be added
     * 3- Drop the first entry since it's equal to (firt_vertex,firt_vertex)
     * 4- Load each edge into the graph
     * */

    val label_index = st.get(a(0))

    a.view.map(l => (label_index, st.get(l))).drop(1).foreach { edge =>

      edge._1 map (v => edge._2 map (w => G.addEdge(v, w)))

    }

  }

  def contains(key: String) = st.contains(key)
  def index(key: String) = st.get(key)
  def name(v: Int) = inverted.lift(v)
}