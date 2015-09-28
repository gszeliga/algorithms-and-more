package com.gszeliga.playground.algorithms.graph

class Topological(private val G: Digraph) {

  val (cycle, dfo) = {
    val dc = new DirectedCycle(G)

    if (!dc.hasCycle) {
      (dc, Some(new DepthFirstOrder(G)))
    } else (dc, None)

  }

  def isDAG(): Boolean = !cycle.hasCycle
  def order: Option[Seq[Int]] = dfo map (_.reversePost)

}