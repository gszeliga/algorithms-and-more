package com.gszeliga.playground.algorithms.graph

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.gszeliga.playground.algorithms.graph.UndirectedGraph
import com.gszeliga.playground.algorithms.graph.BreadthFirstSearch

@RunWith(classOf[JUnitRunner])
class TestBreadFirstSearch extends FlatSpec with Matchers {

  behavior of "Breadth-First Search"

  "Shortest path to 1 from 4" must "be [4,2,3]" in {

    val g = new UndirectedGraph(6)

    g.addEdge(0, 1)
    g.addEdge(0, 2)
    g.addEdge(0, 5)
    g.addEdge(1, 2)
    g.addEdge(2, 4)
    g.addEdge(3, 2)
    g.addEdge(3, 4)
    g.addEdge(3, 5)

    val bfs = new BreadthFirstSearch(g, 4)

    bfs.pathTo(1).get.toList should be(List(4, 2, 1))

  }

}