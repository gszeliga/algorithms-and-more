package com.gszeliga.playground.algorithms.graph

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gszeliga.playground.algorithms.graph.DirectedGraph
import com.gszeliga.playground.algorithms.graph.DirectedDFS

@RunWith(classOf[JUnitRunner])
class TestDirectedDFS extends FlatSpec with Matchers {

  behavior of "DirectedDFS"

  val g = new DirectedGraph(7)

  g.addEdge(0, 1)
  g.addEdge(0, 5)
  g.addEdge(2, 0)
  g.addEdge(2, 3)
  g.addEdge(3, 2)
  g.addEdge(3, 5)
  g.addEdge(4, 2)
  g.addEdge(4, 3)
  g.addEdge(5, 4)
  g.addEdge(6, 0)
  g.addEdge(6, 4)

  "DirectedDFS" must "mark all reachable vertices from a source" in {

    val dfs1 = DirectedDFS(g, 1)
    val dfs0 = DirectedDFS(g, 0)

    dfs1.marked(0) should be(false)

    dfs0.marked(1) should be(true)
    dfs0.marked(5) should be(true)
    dfs0.marked(2) should be(true)
    dfs0.marked(6) should be(false)

  }

  it must "mark all reachable vertices from several sources" in {

    val dfs = DirectedDFS(g, 0, 1, 6)

    dfs.marked(0) should be(true)
    dfs.marked(1) should be(true)
    dfs.marked(5) should be(true)
    dfs.marked(2) should be(true)
    dfs.marked(6) should be(true)    
    
  }

}