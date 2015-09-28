package com.gszeliga.playground.algorithms.graph

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.gszeliga.playground.algorithms.graph.EdgeWeightedGraph
import com.gszeliga.playground.algorithms.graph.EdgeWeightedDigraph
import com.gszeliga.playground.algorithms.graph.DijkstraShortestPathTree
import com.gszeliga.playground.algorithms.graph.DirectedEdge

@RunWith(classOf[JUnitRunner])
class TestDijkstraSPT extends FlatSpec with Matchers {

  behavior of "Dijkstra SPT"

  val g = EdgeWeightedDigraph(getClass().getResource("/tinyEWD.txt"))

  "Having an edge-weighted digraph" must "be able to retrieve the Shortest-Path Tree" in {

    val spt = new DijkstraShortestPathTree(g, 0)

    spt.hasPathTo(6) should be(true)

    spt.pathTo(6) should contain only (DirectedEdge(0, 2, 0.26), DirectedEdge(2, 7, 0.34), DirectedEdge(7, 3, 0.39), DirectedEdge(3, 6, 0.52))

  }

}