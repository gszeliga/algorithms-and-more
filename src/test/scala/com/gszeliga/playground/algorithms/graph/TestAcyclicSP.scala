package com.gszeliga.playground.algorithms.graph

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.gzeliga.playground.algorithms.graph.EdgeWeightedDigraph
import com.gzeliga.playground.algorithms.graph.AcyclicSP
import com.gzeliga.playground.algorithms.graph.DirectedEdge

@RunWith(classOf[JUnitRunner])
class TestAcyclicSP extends FlatSpec with Matchers {

  behavior of "Acyclic Shortest-Path"

  val g = EdgeWeightedDigraph(getClass().getResource("/tinyEWDAG.txt"))

  "Acyclic SP" must "be able to find the SPT from DAG by using topological sort" in {

    val asp = new AcyclicSP(g, 5)

    asp.hasPathTo(6) should be(true)

    asp.pathTo(6) should contain only (DirectedEdge(5, 1, 0.32), DirectedEdge(1, 3, 0.29), DirectedEdge(3, 6, 0.52))
    
  }

}