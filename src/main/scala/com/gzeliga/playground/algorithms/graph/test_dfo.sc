package com.gzeliga.playground.algorithms.graph

object test_dfo {
    val g = new DirectedGraph(13)                 //> g  : com.gzeliga.playground.algorithms.graph.DirectedGraph = com.gzeliga.pla
                                                  //| yground.algorithms.graph.DirectedGraph@462ba11b

  g.addEdge(0, 1)
  g.addEdge(0, 5)
  g.addEdge(0, 6)
  g.addEdge(2, 0)
  g.addEdge(2, 3)
  g.addEdge(3, 5)
  g.addEdge(5, 4)
  g.addEdge(6, 4)
  g.addEdge(6, 9)
  g.addEdge(7, 6)
  g.addEdge(8, 7)
  g.addEdge(9, 10)
  g.addEdge(9, 11)
  g.addEdge(9, 12)
  g.addEdge(11, 12)
  
  
  
  val dfo = new DepthFirstOrder(g)                //> dfo  : com.gzeliga.playground.algorithms.graph.DepthFirstOrder = com.gzeliga
                                                  //| .playground.algorithms.graph.DepthFirstOrder@4b0ab323
  
  dfo.reversePostOrd                              //> res0: scala.collection.immutable.Stack[Int] = Stack(8, 7, 2, 3, 0, 1, 5, 6, 
                                                  //| 4, 9, 10, 11, 12)
  
}