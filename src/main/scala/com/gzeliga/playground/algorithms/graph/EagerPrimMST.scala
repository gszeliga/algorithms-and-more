package com.gzeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet
import com.gzeliga.playground.algorithms.search.IndexMinPQ
import java.lang.Double
import scala.annotation.tailrec
import com.gzeliga.playground.algorithms.fundamentals.Bag

class EagerPrimMST(G: EdgeWeightedGraph) {

  private val edgeTo = new Array[Edge](G.V)
  private val distTo = new Array[Double](G.V) map (_ => Double.POSITIVE_INFINITY)

  {
    def visit(v: Int, state: (BitSet, IndexMinPQ[Double])): (BitSet, IndexMinPQ[Double]) = {

      val marked = state._1 + v
      val crossingEdges = state._2

      (marked, G.adj(v).filterNot(e => marked(e.other(v))).foldLeft(crossingEdges) {
        (ce, e) =>
          {
            val w = e.other(v)

            if (e.weight < distTo(w)) {
              edgeTo(w) = e
              distTo(w) = e.weight

              if (ce.contains(w)) ce.change(w, e.weight)
              else ce.insert(w, e.weight)
            }
            ce
          }
      })
    }

    val crossingEdges = new IndexMinPQ[Double](G.V)

    crossingEdges.insert(0, 0.0)
    distTo(0) = 0.0

    @tailrec
    def loop(state: (BitSet, IndexMinPQ[Double])): Unit = {

      val (mrk, ce) = state

      if (!ce.isEmpty()) {
        loop(visit(ce.delMin, (mrk, ce)))
      }

    }

    loop((BitSet.empty, crossingEdges))

  }

  def edges: Seq[Edge] = {

    (1 until G.V).map(edgeTo(_)).foldLeft(new Bag[Edge]) { (b, e) => b.add(e); b }.values

  }

  def weights: Double = distTo.foldLeft(0.0)(_ + _)

}