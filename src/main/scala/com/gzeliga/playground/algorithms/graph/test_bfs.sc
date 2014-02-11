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
                                                  //| iga.playground.algorithms.graph.BreadthFirstSearch@25a6cc45
  
  bfs.marked                                      //> res0: scala.collection.immutable.BitSet = BitSet(0, 1, 2, 3, 4, 5)
  bfs.edgeTo                                      //> res1: Array[Int] = Array(0, 0, 0, 5, 2, 0)
   
}