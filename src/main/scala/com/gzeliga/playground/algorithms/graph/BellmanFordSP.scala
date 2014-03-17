package com.gzeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet
import scala.collection.immutable.Queue
import scala.annotation.tailrec

//It supports negative weights!!!
class BellmanFordSP(val G: EdgeWeightedDigraph, val source: Int) {

  private val _distTo = new Array[Double](G.V) map { _ => Double.PositiveInfinity }
  private val _edgeTo = new Array[DirectedEdge](G.V)
  private val (_, _, (_, _cycle)) = {

    type IterState = (BitSet, Queue[Int], (Int, Option[Seq[Int]]))
    //I don't like it, I just wanted to play around a little bit, I know I can make it a lot more abstract that this but maybe another time...
    type Watchdog[T] = Int => (Int, Option[T])

    def dontRunUntil[T](f: Int => Boolean)(g: Int => Option[T]): Watchdog[T] = {

      acc =>
        {
          if (f(acc)) {
            g(acc) match {
              case None => (acc + 1, None)
              case s @ _ => (acc, s)
            }
          } else (acc + 1, None)
        }

    }

    def findNegativeCycle: Option[Seq[Int]] = {

      val ewd = _edgeTo.filter(_ != null).foldLeft(new EdgeWeightedDigraph(G.V)) { (g, e) => g.addEdge(e); g }
      val dc = new DirectedCycle(ewd)

      dc.cycle
    }

    def relax(v: Int, onQ: BitSet, crossingEdge: Queue[Int], relaxN: Int, checkOnCycle: Watchdog[Seq[Int]]): IterState = {

      G.adjE(v).foldLeft((onQ, crossingEdge, (relaxN, Option.empty[Seq[Int]]))) {
        case ((bs, q, (call, cycle)), e) =>
          {
            if (distTo(e.to) > distTo(e.from) + e.weight) {
              _edgeTo(e.to) = e
              _distTo(e.to) = distTo(e.from) + e.weight

              //If it has never been in the queue (we admit each vertex only once)
              if (!bs(e.to)) {
                (bs + e.to, q.enqueue(e.to), checkOnCycle(call))
              } else (bs, q, checkOnCycle(call))
            } else (bs, q, checkOnCycle(call))
          }
      }

    }

    @tailrec
    def loop(state: IterState): (BitSet, Queue[Int], (Int, Option[Seq[Int]])) = {

      val (onQ, crossingEdges, (calls, cycle)) = state

      if (!crossingEdges.isEmpty && cycle.isEmpty) {
        val (v, q) = crossingEdges.dequeue
        loop(relax(v, onQ - v, q, calls, dontRunUntil(_ % G.V == 0)(_ => findNegativeCycle)))
      } else state

    }

    val crossingEdges = Queue(source)
    val onQ = BitSet(source)
    _distTo(source) = 0.0

    loop((onQ, crossingEdges, (0, None)))

  }

  def hasNegativeCycle = _cycle.isDefined
  def negativeCycle: Option[Seq[Int]] = _cycle

  def distTo(v: Int): Double = _distTo(v)
  def hasPathTo(v: Int): Boolean = _distTo(v) < Double.PositiveInfinity
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