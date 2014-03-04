package com.gzeliga.playground.algorithms.graph

object test_scc {
  val g = new DirectedGraph(13)                   //> g  : com.gzeliga.playground.algorithms.graph.DirectedGraph = com.gzeliga.play
                                                  //| ground.algorithms.graph.DirectedGraph@462ba11b

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
  
  
  val a = new KosarajuStronglyCC(g)               //> a  : com.gzeliga.playground.algorithms.graph.KosarajuStronglyCC = com.gzelig
                                                  //| a.playground.algorithms.graph.KosarajuStronglyCC@23d256fa
  
  a.count                                         //> res0: Int = 5
  a.components                                    //> res1: scala.collection.immutable.Map[Int,scala.collection.immutable.IndexedS
                                                  //| eq[Int]] = Map(0 -> Vector(9, 10, 11, 12), 1 -> Vector(1), 2 -> Vector(0, 2,
                                                  //|  3, 4, 5), 3 -> Vector(6), 4 -> Vector(7, 8))
}