package com.gszeliga.playground.algorithms.graph

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gszeliga.playground.algorithms.graph.DirectedGraph
import com.gszeliga.playground.algorithms.graph.DirectedCycle
import scala.collection.immutable.Stack

@RunWith(classOf[JUnitRunner])
class TestDigraphCycle extends FlatSpec with Matchers {

  behavior of "Digraph Cycle"

  val g = new DirectedGraph(8)

  g.addEdge(0, 1)
  g.addEdge(0, 5)
  g.addEdge(2, 0)
  g.addEdge(2, 3)
  g.addEdge(3, 2)
  g.addEdge(3, 5)
  g.addEdge(4, 2)
  g.addEdge(4, 3)
  g.addEdge(5, 7)
  g.addEdge(6, 0)
  g.addEdge(6, 4)
  g.addEdge(7, 4)

  "Cycle on a digraph" must "be detected" in {

    val dc = new DirectedCycle(g)

    dc.hasCycle should be(true)

  }

  it must "also be described" in {

    val dc = new DirectedCycle(g)

    dc.cycle should be(Some(Seq(3, 5, 7, 4, 3)))

  }

}