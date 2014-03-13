package com.gzeliga.playground.algorithms.graph

object test_cp {
  val cpm = new CriticalPathMethod(getClass().getResource("/jobsPC.txt"))
                                                  //> cpm  : com.gzeliga.playground.algorithms.graph.CriticalPathMethod = com.gzel
                                                  //| iga.playground.algorithms.graph.CriticalPathMethod@58d9660d
 
  
	cpm.G.adj(20).toList                      //> res0: List[Int] = List(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
	cpm.lp.pathTo(21)                         //> res1: Seq[com.gzeliga.playground.algorithms.graph.DirectedEdge] = List(20->0
                                                  //|  0.0, 0->10 41.0, 10->9 0.0, 9->19 29.0, 19->6 0.0, 6->16 21.0, 16->8 0.0, 8
                                                  //| ->18 32.0, 18->2 0.0, 2->12 50.0, 12->21 0.0)
	cpm.lp.distTo(21)                         //> res2: Double = 173.0
	cpm.lp.top.order                          //> res3: Option[Seq[Int]] = Some(Stack(20, 5, 15, 0, 10, 9, 19, 6, 16, 4, 14, 7
                                                  //| , 17, 8, 18, 3, 13, 1, 11, 2, 12, 21))
	
	Double.NegativeInfinity < (0.0 + 0.25)    //> res4: Boolean = true
}