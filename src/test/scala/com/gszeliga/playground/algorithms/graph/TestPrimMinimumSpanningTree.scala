package com.gszeliga.playground.algorithms.graph

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gzeliga.playground.algorithms.graph.EdgeWeightedGraph
import com.gzeliga.playground.algorithms.graph.LazyPrimMinSpanningTree
import com.gzeliga.playground.algorithms.graph.Edge
import com.gzeliga.playground.algorithms.graph.EagerPrimMST

@RunWith(classOf[JUnitRunner])
class TestPrimMinimumSpanningTree extends FlatSpec with Matchers {

  behavior of "Prism MST"

  val g = EdgeWeightedGraph(getClass().getResource("/tinyEWG.txt"))

  "Lazy-Prism MST" must "contain all MST edges" in {

    val mst = new LazyPrimMinSpanningTree(g)

    mst.edges should have length 7

    mst.edges should contain allOf (Edge(0, 7, 0.16), Edge(1, 7, 0.19), Edge(0, 2, 0.26), Edge(2, 3, 0.17), Edge(5, 7, 0.28), Edge(4, 5, 0.35), Edge(6, 2, 0.4))

  }

  "Eager-Prism MST" must "contain all MST edges" in {

    val mst = new EagerPrimMST(g)

    mst.edges should have length 7

    mst.edges should contain allOf (Edge(0, 7, 0.16), Edge(1, 7, 0.19), Edge(0, 2, 0.26), Edge(2, 3, 0.17), Edge(5, 7, 0.28), Edge(4, 5, 0.35), Edge(6, 2, 0.4))

  }  
  
}