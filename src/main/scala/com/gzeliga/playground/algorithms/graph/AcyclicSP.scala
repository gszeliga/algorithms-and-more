package com.gzeliga.playground.algorithms.graph

import scala.annotation.tailrec

/*If we want to find the longest-path, all we need to is:
 * 
 * 1- Make all weights in the edge-weighted graph negative
 * 2- Use Double.NegativeInfinity
 * 3- Change condition on relax() function
 * */


class AcyclicSP(val G: EdgeWeightedDigraph, val source: Int) {

  private val _edgeTo = new Array[DirectedEdge](G.V)
  private val _distTo = new Array[Double](G.V) map { _ => Double.PositiveInfinity }

  private val top = new Topological(G)

  {
    def relax(current: Int) {

      G.adjE(current).foreach { e =>

        if (_distTo(e.to) > _distTo(e.from) + e.weight) {
          _distTo(e.to) = _distTo(e.from) + e.weight
          _edgeTo(e.to) = e
        }

      }

    }

    _distTo(source) = 0.0

    top.order map (_.foreach(relax))

  }

  def distTo(v: Int) = _distTo(v)
  def hasPathTo(v: Int) = _distTo(v) < Double.PositiveInfinity
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