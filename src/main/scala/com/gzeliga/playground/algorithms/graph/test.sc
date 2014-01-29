package com.gzeliga.playground.algorithms.graph

object test {
  val graph = new UndirectedGraph(6)              //> graph  : com.gzeliga.playground.algorithms.graph.UndirectedGraph = 0: 
                                                  //| 1: 
                                                  //| 2: 
                                                  //| 3: 
                                                  //| 4: 
                                                  //| 5: 

  graph.addEdge(1, 2)
  graph.addEdge(1, 3)
  graph.addEdge(1, 1)
  graph.addEdge(0, 3)
  graph.addEdge(3, 3)
  
 	graph.degree(1)                           //> res0: Int = 4
 	graph.degree(3)                           //> res1: Int = 4
 
	graph.avgDegree                           //> res2: Int = 1
	graph.maxDegree                           //> res3: Int = 4
  graph.numberOfSelfLoops                         //> res4: Int = 2
  
  graph                                           //> res5: com.gzeliga.playground.algorithms.graph.UndirectedGraph = 0: 3 
                                                  //| 1: 1 1 3 2 
                                                  //| 2: 1 
                                                  //| 3: 3 3 0 1 
                                                  //| 4: 
                                                  //| 5: 

}