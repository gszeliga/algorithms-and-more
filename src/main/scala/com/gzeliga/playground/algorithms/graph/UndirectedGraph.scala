package com.gzeliga.playground.algorithms.graph

import com.gzeliga.playground.algorithms.fundamentals.Bag
import scala.annotation.tailrec

object UndirectedGraph {

}

class UndirectedGraph(val V: Int) {

  private var edges: Int = 0 //Number of edges between vertices
  private val adj = new Array[Bag[Int]](V) map (_ => new Bag[Int]()) //Adjacency lists

  def E = edges

  def addEdge(v: Int, w: Int): Unit = {

    adj(v).add(w)
    adj(w).add(v)
    edges = edges + 1

  }

  def valuesOf(v: Int): Stream[Int] = adj(v).values

  //The degree of a vertex is the number of edges incident to it
  def degree(v: Int): Int = adj(v).size
  def maxDegree = adj.indices map(degree) max
  def avgDegree = (2 * edges) / V
  def numberOfSelfLoops = {

    @tailrec
    def loop(v: Int, acc: Int): Int = {

      if (v >= V) acc
      else if (adj(v).values.exists(_ == v)) loop(v + 1, acc + 1)
      else loop(v + 1, acc)

    }

    loop(0, 0)
  }

  override def toString = {

    adj.zipWithIndex.map { v =>
      s"${v._2}: " + v._1.values.foldLeft("") { (acc, b) => b + " " + acc }
    } mkString ("\n")

  }

}

