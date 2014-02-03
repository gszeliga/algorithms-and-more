package com.gzeliga.playground.algorithms.graph

import scala.collection.BitSet

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
  
  def search(graph: Graph, source: Int): BitSet = {
	
    def loop(tmp: BitSet, current: Int): BitSet = {

      graph.adj(current).filterNot(tmp + current).foldLeft(tmp + current)((acc, a) => loop(acc, a))

    }

    loop(BitSet(), source)

  }                                               //> search: (graph: com.gzeliga.playground.algorithms.graph.Graph, source: Int)s
                                                  //| cala.collection.BitSet

	val s = search(g, 0)                      //> s  : scala.collection.BitSet = BitSet(0, 1, 2, 3, 4, 5)
	s.size                                    //> res1: Int = 6
	
}