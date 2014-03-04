package com.gzeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet
import scala.collection.immutable.Queue
import scala.collection.mutable.PriorityQueue
import scala.annotation.tailrec

object EdgeOrdering extends Ordering[Edge] {
  def compare(thiz: Edge, that: Edge) = {
    //We need to invert natural order since we want from minimum weight from maximum weight.
    -(thiz compare that)
  }
}

class LazyPrimMinSpanningTree(G: EdgeWeightedGraph) {

  //MST edges
  private val queue: Queue[Edge] = mst(Queue.empty, visit(0, (BitSet.empty, PriorityQueue.empty(EdgeOrdering))))

  @tailrec
  private def mst(cmst: Queue[Edge], state: (BitSet, PriorityQueue[Edge])): Queue[Edge] = {

    val (marked, crossingEdges) = state

    if (crossingEdges.isEmpty) cmst
    else {
      //Get the minimum weighted edge
      val edge = crossingEdges.dequeue
      val v = edge.either
      val w = edge.other(v)

      //If current edge is not ineligible (something like we've been there)
      if (!(marked(v) && marked(w))) {

        val newmst = cmst.enqueue(edge)

        val nextstate = List(v, w).foldRight((marked, crossingEdges)) { (v, acc) =>
          //If vertex is not marked then we visit their adjacent edges
          if (!acc._1(v)) visit(v, acc) else acc
        }

        mst(newmst, nextstate)

      } else mst(cmst, (marked, crossingEdges))
    }

  }

  private def visit(v: Int, state: (BitSet, PriorityQueue[Edge])): (BitSet, PriorityQueue[Edge]) = {

    val marked = state._1 + v

    //We get all edges whose vertex w has not been marked and enqueue the related edge for later evaluation
    G.adj(v).filterNot(e => marked(e.other(v))).foldLeft((marked, state._2)) {
      case ((mrk, pq), e) => {
        pq.enqueue(e)
        (mrk, pq)
      }
    }

  }

  def edges: Seq[Edge] = queue

}