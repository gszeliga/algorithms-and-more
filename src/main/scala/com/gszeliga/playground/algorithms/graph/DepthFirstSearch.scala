package com.gszeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet
import scala.annotation.tailrec


//Allow to know how many vertices are connected within the graph
class DepthFirstSearch(val graph: Graph, val source: Int) {

  val marked = dfs(graph, source)

  private def dfs(graph: Graph, source: Int): BitSet = {

    def loop(tmp: BitSet, current: Int): BitSet = {
    		
      val updated = tmp + current
      
      graph.adj(current).filterNot(updated).foldLeft(updated)((acc, a) => loop(acc, a))

    }

    loop(BitSet.empty, source)

  }

  val size = marked.size
  
}