package com.gzeliga.playground.algorithms.graph

object test_ewdag {
  val g = EdgeWeightedDigraph(getClass().getResource("/tinyEWDAG.txt"))
                                                  //> g  : com.gzeliga.playground.algorithms.graph.EdgeWeightedDigraph = com.gzeli
                                                  //| ga.playground.algorithms.graph.EdgeWeightedDigraph@159b5217

  val t = new Topological(g)                      //> t  : com.gzeliga.playground.algorithms.graph.Topological = com.gzeliga.playg
                                                  //| round.algorithms.graph.Topological@49ff0dde

  val c = new DirectedCycle(g)                    //> c  : com.gzeliga.playground.algorithms.graph.DirectedCycle = com.gzeliga.pla
                                                  //| yground.algorithms.graph.DirectedCycle@7e78fc6
 

	c.hasCycle                                //> res0: Boolean = false


	t.order                                   //> res1: Option[Seq[Int]] = Some(Stack(5, 1, 3, 6, 4, 7, 0, 2))


	val asp = new AcyclicSP(g, 5)             //> asp  : com.gzeliga.playground.algorithms.graph.AcyclicSP = com.gzeliga.playg
                                                  //| round.algorithms.graph.AcyclicSP@2dafae45

	asp.pathTo(6)                             //> res2: Seq[com.gzeliga.playground.algorithms.graph.DirectedEdge] = List(5->1 
                                                  //| 0.32, 1->3 0.29, 3->6 0.52)
  asp.distTo(0)                                   //> res3: Double = 0.73

}