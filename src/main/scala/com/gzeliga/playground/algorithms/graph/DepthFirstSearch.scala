package com.gzeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet
import scala.annotation.tailrec

class DepthFirstSearch(val graph: Graph, val source: Int) {

  val marked = search(graph, source)

  private def search(graph: Graph, source: Int): BitSet = {

    def loop(tmp: BitSet, current: Int): BitSet = {
    		
      val updated = tmp + current
      
      graph.adj(current).filterNot(updated).foldLeft(updated)((acc, a) => loop(acc, a))

    }

    loop(BitSet(), source)

  }

  def size = marked.size
  
}