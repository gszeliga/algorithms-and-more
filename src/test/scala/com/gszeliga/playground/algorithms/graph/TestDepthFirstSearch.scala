package com.gszeliga.playground.algorithms.graph

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.gzeliga.playground.algorithms.graph.UndirectedGraph
import com.gzeliga.playground.algorithms.graph.DepthFirstSearch

@RunWith(classOf[JUnitRunner])
class TestDepthFirstSearch extends FlatSpec with Matchers {

  behavior of "DepthFirstSearch"

  "Depth-First Search (or Tremaux maze exploration)" should "mark all vertex of a connected graph" in {

    val g = new UndirectedGraph(6)

    g.addEdge(0, 1)
    g.addEdge(0, 2)
    g.addEdge(0, 5)
    g.addEdge(1, 2)
    g.addEdge(2, 4)
    g.addEdge(3, 2)
    g.addEdge(3, 4)
    g.addEdge(3, 5)

    val dfs = new DepthFirstSearch(g, 0)

    (0 to 5) forall (dfs.marked) should be(true)

  }

}