package com.gzeliga.playground.algorithms.graph

object test_bfs {
  val g = new UndirectedGraph(6)                  //> g  : com.gzeliga.playground.algorithms.graph.UndirectedGraph = 0: 
                                                  //| 1: 
                                                  //| 2: 
                                                  //| 3: 
                                                  //| 4: 
                                                  //| 5: 

  g.addEdge(0, 1)
  g.addEdge(0, 2)
  g.addEdge(0, 5)
  g.addEdge(1, 2)
  g.addEdge(2, 4)
  g.addEdge(3, 2)
  g.addEdge(3, 4)
  g.addEdge(3, 5)

  val bfs = new BreadthFirstSearch(g, 0)          //> bfs  : com.gzeliga.playground.algorithms.graph.BreadthFirstSearch = com.gzel
                                                  //| iga.playground.algorithms.graph.BreadthFirstSearch@5705b99f

  bfs.pathTo(3) toList                            //> res0: List[Stream[Int]] = List(Stream(0, 5, 3))

  (0 until 3)                                     //> res1: scala.collection.immutable.Range = Range(0, 1, 2)

}