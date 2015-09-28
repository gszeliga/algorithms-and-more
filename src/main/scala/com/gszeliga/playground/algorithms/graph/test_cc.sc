package com.gszeliga.playground.algorithms.graph

object test_cc {
  val g = new UndirectedGraph(13)                 //> g  : com.gzeliga.playground.algorithms.graph.UndirectedGraph = 0: 
                                                  //| 1: 
                                                  //| 2: 
                                                  //| 3: 
                                                  //| 4: 
                                                  //| 5: 
                                                  //| 6: 
                                                  //| 7: 
                                                  //| 8: 
                                                  //| 9: 
                                                  //| 10: 
                                                  //| 11: 
                                                  //| 12: 

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
  
  
  val cc = new ConnectedComponents(g)             //> cc  : com.gzeliga.playground.algorithms.graph.ConnectedComponents = com.gzel
                                                  //| iga.playground.algorithms.graph.ConnectedComponents@2393385d
  
  
  cc.components                                   //> res0: scala.collection.immutable.Map[Int,scala.collection.immutable.IndexedS
                                                  //| eq[Int]] = Map(2 -> Vector(9, 10, 11, 12), 1 -> Vector(7, 8), 0 -> Vector(0,
                                                  //|  1, 2, 3, 4, 5, 6))
}