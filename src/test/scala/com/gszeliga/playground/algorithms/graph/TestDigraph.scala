package com.gszeliga.playground.algorithms.graph

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gzeliga.playground.algorithms.graph.DirectedGraph

@RunWith(classOf[JUnitRunner])
class TestDigraph extends FlatSpec with Matchers {
  behavior of "Digraph"

  val g = new DirectedGraph(6)

  g.addEdge(0, 1)
  g.addEdge(0, 5)
  g.addEdge(2, 0)
  g.addEdge(2, 3)
  g.addEdge(3, 2)
  g.addEdge(3, 5)
  g.addEdge(4, 2)
  g.addEdge(4, 3)
  g.addEdge(5, 4)

  "Digraph" must "reflect v -> w reachability only once" in {

    g.adj(0) should be(Some(Stream(5, 1)))
    g.adj(1) should be(Some(Stream()))

  }

  it must "be able reversable" in {
	  
    val ng = g.reverse
    
    ng.adj(1) should be(Some(Stream(0)))
    ng.adj(0) should be(Some(Stream(2)))
    
  }

}