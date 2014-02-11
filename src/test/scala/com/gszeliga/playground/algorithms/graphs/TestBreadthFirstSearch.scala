package com.gszeliga.playground.algorithms.graphs

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gzeliga.playground.algorithms.graph.UndirectedGraph
import com.gzeliga.playground.algorithms.graph.BreadthFirstSearch

@RunWith(classOf[JUnitRunner])
class TestBreadthFirstSearch extends FlatSpec with Matchers {

  behavior of "Breadth-First Search"

  "If I look for the shortest path from 0 then" must "be XXX" in {
    val g = new UndirectedGraph(6)

    g.addEdge(0, 1)
    g.addEdge(0, 2)
    g.addEdge(0, 5)
    g.addEdge(1, 2)
    g.addEdge(2, 4)
    g.addEdge(3, 2)
    g.addEdge(3, 4)
    g.addEdge(3, 5)

    val bfs = new BreadthFirstSearch(g, 0)
    
    
    
    bfs.marked
    
  }

}