package com.gzeliga.playground.algorithms.graph

object test_ewg {
  val g = EdgeWeightedGraph(getClass().getResource("/tinyEWG.txt"))
                                                  //> g  : com.gzeliga.playground.algorithms.graph.EdgeWeightedGraph = com.gzeliga
                                                  //| .playground.algorithms.graph.EdgeWeightedGraph@7f2ad19e
                             
                               
                               
  val mst = new LazyPrimMinSpanningTree(g)        //> mst  : com.gzeliga.playground.algorithms.graph.LazyPrimMinSpanningTree = com
                                                  //| .gzeliga.playground.algorithms.graph.LazyPrimMinSpanningTree@58f39b3a


	mst.edges                                 //> res0: Seq[com.gzeliga.playground.algorithms.graph.Edge] = Queue(0-7 0.16, 1-
                                                  //| 7 0.19, 0-2 0.26, 2-3 0.17, 5-7 0.28, 4-5 0.35, 6-2 0.4)
}