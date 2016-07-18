package com.gszeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet
import scala.annotation.tailrec

//Check reachability from source vertex
class DirectedDFS(private val G: Digraph, sources: Seq[Int]) {

  private val marked_v: BitSet = sources.foldLeft(BitSet.empty) { (bs, source) =>

    //If source it's not already marked
    if (!bs(source))
      dfs(bs, source)
    else bs

  }

  def dfs(marked: BitSet, source: Int): BitSet = {

    val tmp = marked + source
    G.adj(source).filterNot(tmp).foldLeft(tmp)(dfs)

  }

  def marked(v: Int): Boolean = marked_v(v)

}

object DirectedDFS {

  def apply(G: Digraph, s: Int): DirectedDFS = new DirectedDFS(G, List(s))
  def apply(G: Digraph, sources: Int*): DirectedDFS = new DirectedDFS(G, sources)
  /*def apply(G: Digraph, sources: Seq[Int]): DirectedDFS = new DirectedDFS(G, sources)*/

}