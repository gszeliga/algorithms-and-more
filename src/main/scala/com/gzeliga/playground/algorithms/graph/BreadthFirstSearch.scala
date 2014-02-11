package com.gzeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet
import com.gzeliga.playground.algorithms.fundamentals.Queue

class BreadthFirstSearch(val g: Graph, val s: Int) {

  val (marked, edgeTo) = {

    def loop(pending: Queue[Int], marked: BitSet, edgeTo: Array[Int]): Option[(BitSet, Array[Int])] = {

      if (pending.isEmpty) Some(marked, edgeTo)
      else {
        val (nextv, newqueue) = pending.dequeue

        nextv map { current =>
          g.adj(current).foldLeft((newqueue, marked)) {
            case ((queue, marked), edge) =>
              //First time we can reach the adjacent vertex from current? (This helps retrieving the shortest path)
              if (!marked.contains(edge)) {
                edgeTo(edge) = current
                (queue.enqueue(edge), marked + edge) //Enqueue the adjacent vertex and mark it as visited
              } else (queue, marked)
          }
        } flatMap {
          case (eq, me) => loop(eq, me, edgeTo)
        }

      }

    }

    loop(Queue(s), BitSet(s), new Array[Int](g.V)) match {
      case Some(r) => r
      case None => (BitSet.empty, new Array[Int](g.V))
    }

  }

}