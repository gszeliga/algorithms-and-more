package com.gszeliga.playground.algorithms.graph

import com.gszeliga.playground.algorithms.fundamentals.Bag
import scala.io.Source
import java.net.URL
 
sealed case class Edge(private val v: Int, private val w: Int, val weight: Double) extends Ordered[Edge] {

  def either = v
  def other(vx: Int) = {
    if (vx == v) w
    else if (vx == w) v
    else throw new RuntimeException("Incosistent edge")
  }

  def compare(that: Edge) = {
    if (this.weight < that.weight) -1
    else if (this.weight > that.weight) 1
    else 0
  }

  override def toString = s"$v-$w $weight"

}

object EdgeOrdering extends Ordering[Edge] {
  def compare(thiz: Edge, that: Edge) = {
    //We need to invert natural order since we want from minimum weight from maximum weight.
    -(thiz compare that)
  }
}

class EdgeWeightedGraph(val V: Int) {

  private val adjacency = new Array[Bag[Edge]](V) map (_ => new Bag[Edge])

  private var edgen: Int = 0

  def E: Int = edgen

  def addEdge(e: Edge) = {

    val v = e.either

    adjacency(v).add(e)
    adjacency(e.other(v)).add(e)
    edgen = edgen + 1
  }

  def adj(v: Int): Seq[Edge] = adjacency(v).values

  //It's suppose to remove self-loops
  def edges: Seq[Edge] = {
    (0 until V).foldLeft(new Bag[Edge]) { (b, v) =>
      adj(v).filter(_.other(v) > v).foreach(b.add(_))
      b
    }.values
  }
}

object EdgeWeightedGraph {

  def apply(source: URL) = {

    val lines = Source.fromURL(source).getLines

    //We could perform something more fancy with Option and such
    val g = lines.take(1).map(s => new EdgeWeightedGraph(s.toInt)).next

    lines.drop(1).foldLeft(g) { (acc, l) =>
      val parts = l.split(" ")
      acc.addEdge(new Edge(parts(0).toInt, parts(1).toInt, parts(2).toDouble))
      acc
    }
    
  }

}