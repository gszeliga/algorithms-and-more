package com.gszeliga.playground.algorithms.graph

object test_ewd {
  val g = EdgeWeightedDigraph(getClass().getResource("/tinyEWD.txt"))
                                                  //> g  : com.gzeliga.playground.algorithms.graph.EdgeWeightedDigraph = com.gzeli
                                                  //| ga.playground.algorithms.graph.EdgeWeightedDigraph@7f2ad19e

	g.E                                       //> res0: Int = 15
	g.edges.toList                            //> res1: List[com.gzeliga.playground.algorithms.graph.DirectedEdge] = List(7->5
                                                  //|  0.28, 7->3 0.39, 6->2 0.4, 6->0 0.58, 6->4 0.93, 5->4 0.35, 5->7 0.28, 5->1
                                                  //|  0.32, 4->5 0.35, 4->7 0.37, 3->6 0.52, 2->7 0.34, 1->3 0.29, 0->4 0.38, 0->
                                                  //| 2 0.26)
 
 val spt = new DijkstraShortestPathTree(g,0)      //> spt  : com.gzeliga.playground.algorithms.graph.DijkstraShortestPathTree = co
                                                  //| m.gzeliga.playground.algorithms.graph.DijkstraShortestPathTree@6fc5f743
 
 spt.pathTo(6)                                    //> res2: Seq[com.gzeliga.playground.algorithms.graph.DirectedEdge] = List(0->2 
                                                  //| 0.26, 2->7 0.34, 7->3 0.39, 3->6 0.52)
 
}