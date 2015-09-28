package com.gszeliga.playground.algorithms.graph

import com.gszeliga.playground.algorithms.search.IndexMinPQ
import java.lang.Double
import scala.annotation.tailrec

class DijkstraShortestPathTree(G: EdgeWeightedDigraph, val source: Int) {

  private val _edgeTo = new Array[DirectedEdge](G.V)
  private val _distTo = new Array[Double](G.V) map (_ => Double.POSITIVE_INFINITY)

  {
    def relax(v: Int, crossingEdges: IndexMinPQ[Double]): IndexMinPQ[Double] = {

      G.adjE(v).foldLeft(crossingEdges) { (pq, e) =>

        if (_distTo(e.to) > _distTo(e.from) + e.weight) {

          _edgeTo(e.to) = e
          _distTo(e.to) = _distTo(e.from) + e.weight

          if (pq.contains(e.to)) pq.change(e.to, e.weight)
          else pq.insert(e.to, e.weight)

        }

        pq

      }

    }

    @tailrec
    def loop(pq: IndexMinPQ[Double]): Unit = {

      if (!pq.isEmpty) {
        loop(relax(pq.delMin(), pq))
      }
    }

    //Set distance to source as zero
    _distTo(source) = 0

    //Add source to the priority queue to start from there
    val pq = new IndexMinPQ[Double](G.V)
    pq.insert(source, 0)

    loop(pq)

  }

  def distTo(v: Int): Double = _distTo(v)
  def hasPathTo(v: Int): Boolean = _distTo(v) < Double.POSITIVE_INFINITY
  def pathTo(v: Int): Seq[DirectedEdge] = {

    if (!hasPathTo(v)) Nil
    else {

      @tailrec
      def loop(tmp: List[DirectedEdge], current: Option[DirectedEdge]): List[DirectedEdge] = {

        if (current.isDefined) {

          val c = current.get

          if (c.from != source) {
            loop(c :: tmp, _edgeTo.lift(c.from))
          } else c :: tmp
        } else tmp
      }

      loop(Nil, _edgeTo.lift(v))
    }
  }

}