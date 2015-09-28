package com.gszeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet
import scala.annotation.tailrec
import scala.collection.immutable.Queue

class BreadthFirstSearch(val g: Graph, val s: Int) {

  private val (marked, edgeTo) = {

    @tailrec
    def loop(pending: Queue[Int], marked: BitSet, edgeTo: Array[Int]): Option[(BitSet, Array[Int])] = {

      if (pending.isEmpty) Some(marked, edgeTo)
      else {
        val (nextv, newqueue) = pending.dequeue

        g.adj(nextv).foldLeft((newqueue, marked)) {
          case ((queue, marked), edge) =>
            //First time we can reach the adjacent vertex from current? (This helps retrieving the shortest path)
            if (!marked.contains(edge)) {
              edgeTo(edge) = nextv
              (queue.enqueue(edge), marked + edge) //Enqueue the adjacent vertex and mark it as visited
            } else (queue, marked)
        } match {
          case (a, b) => loop(a, b, edgeTo)
        }

      }

    }

    loop(Queue(s), BitSet(s), new Array[Int](g.V)) match {
      case Some(r) => r
      case None => (BitSet.empty, new Array[Int](g.V))
    }

  }

  def hasPathTo(v: Int) = marked(v)

  def pathTo(v: Int): Option[Stream[Int]] = {

    def loop(current: Int): Stream[Int] = {

      if (current == s) s #:: Stream.empty
      else {
        current #:: loop(edgeTo(current))
      }

    }

    if (!hasPathTo(v)) None
    else Some(loop(v) reverse)

  }

}