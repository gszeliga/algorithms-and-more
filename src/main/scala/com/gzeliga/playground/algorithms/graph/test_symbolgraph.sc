package com.gzeliga.playground.algorithms.graph

import java.io.File

object test_symbolgraph {

  val g = new FileSymbolGraph(new File(getClass().getResource("/movies.txt").getFile()), "/")
                                                  //> g  : com.gzeliga.playground.algorithms.graph.FileSymbolGraph = com.gzeliga.p
                                                  //| layground.algorithms.graph.FileSymbolGraph@19632847
  
	g.G.V                                     //> res0: Int = 119429
	g.index("Wilke, Robert J.")               //> res1: Option[Int] = Some(1110)
	g.name(1110)                              //> res2: Option[String] = Some(Wilke, Robert J.)
	
	
	
}