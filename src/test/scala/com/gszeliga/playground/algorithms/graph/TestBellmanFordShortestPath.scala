package com.gszeliga.playground.algorithms.graph

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.Matchers
import org.scalatest.FlatSpec
import com.gzeliga.playground.algorithms.graph.EdgeWeightedDigraph
import com.gzeliga.playground.algorithms.graph.BellmanFordSP
import com.gzeliga.playground.algorithms.graph.DirectedEdge

@RunWith(classOf[JUnitRunner])
class TestBellmanFordShortestPath extends FlatSpec with Matchers {

  behavior of "Bellman-Ford Shortest Path"

  val g = EdgeWeightedDigraph(getClass().getResource("/tinyEWD.txt"))
  val ng = EdgeWeightedDigraph(getClass().getResource("/tinyEWDn.txt"))
  val ncg = EdgeWeightedDigraph(getClass().getResource("/tinyEWDnc.txt"))

  "Bellman-Ford implementation" must "retrieve the shortest-path tree from an edge-weighted graph WITHOUT negative edges" in {

    val bfsp = new BellmanFordSP(g, 0)

    bfsp.hasPathTo(6) should be(true)
    bfsp.hasNegativeCycle should be(false)

    bfsp.pathTo(6) should contain only (DirectedEdge(0, 2, 0.26), DirectedEdge(2, 7, 0.34), DirectedEdge(7, 3, 0.39), DirectedEdge(3, 6, 0.52))

  }

  it must "retrieve the shortest-path tree from an edge-weighted graph WITH negative edges" in {
    val bfsp = new BellmanFordSP(ng, 0)

    bfsp.hasPathTo(4) should be(true)
    bfsp.hasNegativeCycle should be(false)

    bfsp.pathTo(4) should contain only (DirectedEdge(0, 2, 0.26), DirectedEdge(2, 7, 0.34), DirectedEdge(7, 3, 0.39), DirectedEdge(3, 6, 0.52), DirectedEdge(6, 4, -1.25))
  }

  it must "detect a negative cycle if present" in {
    val bfsp = new BellmanFordSP(ncg, 0)

    bfsp.hasNegativeCycle should be(true)
  }

}