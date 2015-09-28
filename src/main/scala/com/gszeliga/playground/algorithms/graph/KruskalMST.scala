package com.gszeliga.playground.algorithms.graph

import scala.collection.mutable.PriorityQueue
import com.gszeliga.playground.algorithms.unionfind.UF
import scala.collection.immutable.Queue
import scala.annotation.tailrec

class KruskalMST(G: EdgeWeightedGraph) {

  private val pq = G.edges.foldLeft(PriorityQueue.empty(EdgeOrdering)) { (q, e) => q.enqueue(e); q }
  private val mst = kmst(pq, Queue.empty[Edge], UF.empty(G.V))

  @tailrec
  private def kmst(edges: PriorityQueue[Edge], mst: Queue[Edge], con: UF): Queue[Edge] = {

    if (!edges.isEmpty && mst.size < G.V - 1) {

      val edge = edges.dequeue

      val v = edge.either
      val w = edge.other(v)

      //Is it ineligible?
      if (!con.connected(v, w)) {
        kmst(edges, mst.enqueue(edge), con.union(v, w))
      } else kmst(edges, mst, con)

    } else mst

  }

  def edges: Seq[Edge] = mst

}