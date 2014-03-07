package com.gzeliga.playground.algorithms.graph

import com.gzeliga.playground.algorithms.fundamentals.Bag
import java.net.URL
import scala.io.Source
 
sealed case class DirectedEdge(private val v: Int, private val w: Int, val weight: Double) {

  def from = v
  def to = w

  override def toString = s"$v->$w $weight"

}

class EdgeWeightedDigraph(val V: Int) {

  private val adjacency = new Array[Bag[DirectedEdge]](V) map (_ => new Bag[DirectedEdge])

  private var edgen: Int = 0

  def E: Int = edgen

  def addEdge(e: DirectedEdge) = {
    adjacency(e.from).add(e)
    edgen = edgen + 1
  }

  def adj(v: Int): Seq[DirectedEdge] = adjacency(v).values

  def edges: Seq[DirectedEdge] = {
    adjacency.foldLeft(new Bag[DirectedEdge]) { (acc, b) =>
      b.values.foreach(acc.add(_))
      acc
    }.values
  }

}

object EdgeWeightedDigraph {

  def apply(source: URL) = {

    val lines = Source.fromURL(source).getLines

    val g = lines.take(1).map(s => new EdgeWeightedDigraph(s.toInt)).next

    lines.drop(1).foldLeft(g) { (acc, l) =>
      val parts = l.split(" ")
      acc.addEdge(new DirectedEdge(parts(0).toInt, parts(1).toInt, parts(2).toDouble))
      acc
    }
    
  }

}