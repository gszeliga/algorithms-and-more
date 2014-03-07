package com.gszeliga.playground.algorithms.graph

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.gzeliga.playground.algorithms.graph.EdgeWeightedGraph
import com.gzeliga.playground.algorithms.graph.KruskalMST
import com.gzeliga.playground.algorithms.graph.Edge
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestKruskalMinimumSpanningTree extends FlatSpec with Matchers {

  behavior of "Kruskal MST"
  
  val g = EdgeWeightedGraph(getClass().getResource("/tinyEWG.txt"))

  "KruskalMST" must "list be able to detect the MST of a graph" in {
    
    val mst = new KruskalMST(g)
    
    mst.edges should have length 7

    mst.edges should contain allOf (Edge(0, 7, 0.16), Edge(1, 7, 0.19), Edge(0, 2, 0.26), Edge(2, 3, 0.17), Edge(5, 7, 0.28), Edge(4, 5, 0.35), Edge(6, 2, 0.4))    
    
  } 
  
}