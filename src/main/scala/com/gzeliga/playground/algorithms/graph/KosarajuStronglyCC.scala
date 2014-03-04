package com.gzeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet

class KosarajuStronglyCC(G: Digraph) {

  val order = new DepthFirstOrder(G.reverse)
  val ids = new Array[Int](G.V)

  private val result = {

    def dfs(v: Int, marked: BitSet, component: Int): BitSet = {

      val m = marked + v
      ids(v) = component

      G.adj(v).filterNot(m).foldLeft(m) { (acc, w) =>
        dfs(w, acc, component)
      }

    }

    order.reversePost.foldLeft((BitSet.empty, 0)) { (state, v) =>
      if (!state._1(v)) {
        (dfs(v, state._1, state._2), state._2 + 1)
      } else state
    }

  }

  def stronglyConnected(v: Int, w: Int): Boolean = ids(v) == ids(w)
  def id(v: Int): Int = ids(v)
  def count: Int = result._2
  def components = ids.indices.groupBy(ids(_))
  
}