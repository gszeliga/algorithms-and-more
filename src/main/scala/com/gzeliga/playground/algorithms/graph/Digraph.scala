package com.gzeliga.playground.algorithms.graph

import com.gzeliga.playground.algorithms.fundamentals.Bag

trait Digraph {
  def V: Int
  def E: Int
  def adj(v: Int): Stream[Int]
  def reverse: Digraph
}

class DirectedGraph( final val V: Int) extends Digraph {

  private val adjacency = new Array[Int](V) map (_ => new Bag[Int])
  private var edges: Int = 0

  def E = edges

  def addEdge(v: Int, w: Int) = {
    adjacency(v).add(w)
    edges = edges + 1
  }

  def adj(v: Int) = adjacency(v).values
  def reverse = {

    adjacency.zipWithIndex.foldLeft(new DirectedGraph(V)) {
      (graph, adj) =>
        {
          val bag = adj._1
          val v = adj._2

          bag.values.foldLeft(graph) { (acc, w) =>
            {
              acc.addEdge(w, v)
              acc
            }
          }
        }
    }
  }

}