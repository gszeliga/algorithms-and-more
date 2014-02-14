package com.gzeliga.playground.algorithms.graph

import com.gzeliga.playground.algorithms.fundamentals.Bag
import scala.annotation.tailrec

trait Graph{
  def V: Int
  def E: Int
  def addEdge(v: Int, w: Int)
  def adj(v: Int): Stream[Int]
}

class UndirectedGraph(val V: Int) extends Graph{

  private var edges: Int = 0 //Number of edges between vertices
  private val adjacency = new Array[Bag[Int]](V) map (_ => new Bag[Int]()) //Adjacency lists

  def E = edges

  def addEdge(v: Int, w: Int): Unit = {

    adjacency(v).add(w)
    adjacency(w).add(v)
    edges = edges + 1

  }

  def adj(v: Int): Stream[Int] = adjacency(v).values

  //The degree of a vertex is the number of edges incident to it
  def degree(v: Int): Int = adj(v).size
  def maxDegree = adjacency.indices map(degree) max
  def avgDegree = (2 * edges) / V
  def numberOfSelfLoops = {

    @tailrec
    def loop(v: Int, acc: Int): Int = {

      if (v >= V) acc
      else if (adjacency(v).values.exists(_ == v)) loop(v + 1, acc + 1)
      else loop(v + 1, acc)

    }

    loop(0, 0)
  }

  override def toString = {

    adjacency.zipWithIndex.map { v =>
      s"${v._2}: " + v._1.values.foldLeft("") { (acc, b) => b + " " + acc }
    } mkString ("\n")

  }

}