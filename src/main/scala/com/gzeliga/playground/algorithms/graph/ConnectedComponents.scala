package com.gzeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet
import scala.annotation.tailrec
import com.gzeliga.playground.algorithms.fundamentals.Bag

//It uses  First-Depth search to detect the number of connected components within a graph
class ConnectedComponents(g: Graph) {

  private val (marked, ids, component_count) = {

    def dfs(v: Int, component: Int, marked: BitSet, ids: Array[Int]): (BitSet, Array[Int]) = {

      val updated = marked + v
      ids(v) = component

      g.adj(v).filterNot(updated).foldLeft((updated, ids))((acc, av) => dfs(av, component, acc._1, acc._2))

    }

    @tailrec
    def loop(latest: Int, state: (BitSet, Array[Int], Int)): (BitSet, Array[Int], Int) = {

      state match {
        case (marked, ids, component) =>

          //Try to find the first vertex not being marked yet
          (latest until g.V) find (!marked(_)) match {

            case Some(v) => {

              val (nm, nids) = dfs(v, component, marked, ids)

              loop(latest + 1, (nm, nids, component + 1))

            }
            case None => state //We're done

          }

      }

    }

    loop(0, (BitSet.empty, new Array[Int](g.V), 0))

  }

  def count = component_count
  def connected(v: Int, w: Int) = ids(v) == ids(w)
  def id(v: Int) = ids(v)
  def components = ids.indices.groupBy(ids(_))

}