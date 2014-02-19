package com.gzeliga.playground.algorithms.graph

import java.io.File

object test_symbolgraph {

  val g = new FileSymbolGraph(new File(getClass().getResource("/movies.txt").getFile()), "/")
                                                  //> g  : com.gzeliga.playground.algorithms.graph.FileSymbolGraph = com.gzeliga.p
                                                  //| layground.algorithms.graph.FileSymbolGraph@5e7808b9

	g.size                                    //> res0: Int = 119429
	g.st.get("Wilke, Robert J.")              //> res1: Option[Int] = Some(1110)

}