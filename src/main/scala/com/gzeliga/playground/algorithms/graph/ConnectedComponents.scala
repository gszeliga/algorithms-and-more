package com.gzeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet
import scala.annotation.tailrec

//It uses  First-Depth search to detect the number of connected components within a graph
class ConnectedComponents(g: Graph) {

  val (marked, ids, component_count) = {

    def dfs(marked: BitSet, ids: Array[Int]): (BitSet, Array[Int]) = ???

    @tailrec
    def loop(latest: Int, state: (BitSet, Array[Int], Int)): (BitSet, Array[Int], Int) = {

      state match {
        case (marked, ids, component) =>

          //Try to find the first vertex not being marked yet
          (latest until g.V) find (!marked.contains(_)) match {

            case Some(v) => {

              val (nm, nids) = dfs(marked, ids)

              loop(latest + 1, (nm, nids, component + 1))

            }
            case None => state //We're done

          }

      }

    }

    loop(0, (BitSet.empty, new Array[Int](g.V), 0))

  }

}