package com.gzeliga.playground.algorithms.graph

object test_directed_cycle {

  val g = new DirectedGraph(8)                    //> g  : com.gzeliga.playground.algorithms.graph.DirectedGraph = com.gzeliga.pla
                                                  //| yground.algorithms.graph.DirectedGraph@462ba11b

  g.addEdge(0, 1)
  g.addEdge(0, 5)
  g.addEdge(2, 0)
  g.addEdge(2, 3)
  g.addEdge(3, 2)
  g.addEdge(3, 5)
  g.addEdge(4, 2)
  g.addEdge(4, 3)
  g.addEdge(5, 7)
  g.addEdge(6, 0)
  g.addEdge(6, 4)
  g.addEdge(7, 4)
  
  
    val dc = new DirectedCycle(g)                 //> dc  : com.gzeliga.playground.algorithms.graph.DirectedCycle = com.gzeliga.pl
                                                  //| ayground.algorithms.graph.DirectedCycle@d8d9850

    dc.cycle.get                                  //> res0: scala.collection.immutable.Stack[Int] = Stack(3, 5, 7, 4, 3)

}