package com.gszeliga.playground.algorithms.graph

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.gszeliga.playground.algorithms.graph.UndirectedGraph
import com.gszeliga.playground.algorithms.graph.ConnectedComponents

@RunWith(classOf[JUnitRunner])
class TestConnectedComponents extends FlatSpec with Matchers {

  behavior of "Connected Components"

  val g = new UndirectedGraph(13)

  g.addEdge(0, 1)
  g.addEdge(0, 2)
  g.addEdge(0, 6)
  g.addEdge(0, 5)
  g.addEdge(5, 3)
  g.addEdge(5, 4)
  g.addEdge(6, 4)
  g.addEdge(7, 8)
  g.addEdge(9, 10)
  g.addEdge(9, 11)
  g.addEdge(9, 12)
  g.addEdge(11, 12)

  "Connected Components" must "detect different components of a non-connected grapth" in {
    val cc = new ConnectedComponents(g)
    cc.count should be(3)
  }

  it must "be able to detect whether to vertex are connected" in {

    val cc = new ConnectedComponents(g)

    cc.connected(2, 4) should be(true)
    cc.connected(6, 12) should be(false)
  }

  it must "be able to list each vertex from each component" in {

    val cc = new ConnectedComponents(g)

    cc.components should contain allOf (1 -> Vector(7, 8), 2 -> Vector(9, 10, 11, 12), 0 -> Vector(0, 1, 2, 3, 4, 5, 6))
  }

}