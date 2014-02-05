package com.gzeliga.playground.algorithms.graph

import scala.collection.BitSet
import scala.collection.mutable.WrappedArray

object test {
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
	
	g                                         //> res0: com.gzeliga.playground.algorithms.graph.UndirectedGraph = 0: 1 2 5 
                                                  //| 1: 0 2 
                                                  //| 2: 0 1 4 3 
                                                  //| 3: 2 4 5 
                                                  //| 4: 2 3 
                                                  //| 5: 0 3 
   
  val dfs = new DepthFirstSearch(g, 5)            //> dfs  : com.gzeliga.playground.algorithms.graph.DepthFirstSearch = com.gzelig
                                                  //| a.playground.algorithms.graph.DepthFirstSearch@70cb6009

	dfs.marked                                //> res1: scala.collection.immutable.BitSet = BitSet(0, 1, 2, 3, 4, 5)

	val dfp = new DepthFirstPaths(g,1)        //> dfp  : com.gzeliga.playground.algorithms.graph.DepthFirstPaths = com.gzeliga
                                                  //| .playground.algorithms.graph.DepthFirstPaths@2df6df4c
	 
	dfp.hasPathTo(3)                          //> res2: Boolean = true
	
	dfp.pathTo(4)                             //> res3: Option[List[Int]] = Some(List(1, 2, 4))
}