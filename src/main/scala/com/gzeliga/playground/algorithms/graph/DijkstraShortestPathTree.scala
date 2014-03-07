package com.gzeliga.playground.algorithms.graph

import com.gzeliga.playground.algorithms.search.IndexMinPQ
import java.lang.Double
import scala.annotation.tailrec

class DijkstraShortestPathTree(G: EdgeWeightedDigraph, source: Int) {

  private val edgeTo = new Array[DirectedEdge](G.V)
  private val distTo = new Array[Double](G.V) map (_ => Double.POSITIVE_INFINITY)

  {
    def relax(v: Int, crossingEdges: IndexMinPQ[Double]): IndexMinPQ[Double] = {

      G.adj(v).foldLeft(crossingEdges) { (pq, e) =>

        if (distTo(e.to) > distTo(e.from) + e.weight) {

          edgeTo(e.to) = e
          distTo(e.to) = distTo(e.from) + e.weight

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
    distTo(source) = 0

    //Add source to the priority queue to start from there
    val pq = new IndexMinPQ[Double](G.V)
    pq.insert(source, 0)

    loop(pq)

  }
}