package com.gszeliga.playground.algorithms.graph

import com.gzeliga.playground.algorithms.graph.DirectedGraph
import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gzeliga.playground.algorithms.graph.KosarajuStronglyCC

@RunWith(classOf[JUnitRunner])
class TestKosarajuSCC extends FlatSpec with Matchers {

  behavior of "Kosaraju solution for Strongly Connected Components"
  
  val g = new DirectedGraph(13)
  
  g.addEdge(0, 1)
  g.addEdge(0, 5)
  g.addEdge(2, 0)
  g.addEdge(2, 3)
  g.addEdge(3, 2)
  g.addEdge(3, 5)
  g.addEdge(5, 4)
  g.addEdge(4, 3)
  g.addEdge(4, 2)
  g.addEdge(6, 0)
  g.addEdge(6, 4)
  g.addEdge(6, 9)
  g.addEdge(7, 6)
  g.addEdge(7, 8)
  g.addEdge(8, 7)
  g.addEdge(8, 9)
  g.addEdge(9, 10)
  g.addEdge(9, 11)
  g.addEdge(10, 12)
  g.addEdge(11, 12)
  g.addEdge(12, 9)
  
  val scc = new KosarajuStronglyCC(g)     

  "Kosaraju algorithm" must "find all connected components" in {
    
	scc.count should be(5)
    
  }
  
  it must "be able to tell whether two vertices are strongly connected" in {
    
    scc.stronglyConnected(1, 0) should be(false)
    scc.stronglyConnected(0, 3) should be(true)
    scc.stronglyConnected(7, 6) should be(false)
    scc.stronglyConnected(7, 8) should be(true)
    
  }
  
}