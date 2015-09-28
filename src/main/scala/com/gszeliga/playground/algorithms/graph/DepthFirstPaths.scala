package com.gszeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet

class DepthFirstPaths(val g: Graph, val s: Int) {

  private lazy val dfs: (BitSet, Array[Int]) = {

    var edgeTo = new Array[Int](g.V)

    def loop(current: Int, marked: BitSet): BitSet = {

      val updated = marked + current

      g.adj(current).filterNot(updated).foldLeft(updated) { (acc, x) =>

        //Mark an existing connected edge to current
        edgeTo(x) = current
        loop(x, acc)
      }
    }

    (loop(s, BitSet.empty), edgeTo)

  }

  private val marked: BitSet = dfs._1
  private val edgeTo: Array[Int] = dfs._2

  def hasPathTo(v: Int) = marked(v)

  def pathTo(v: Int): Option[List[Int]] = {

    if (!hasPathTo(v)) None
    else {

      //We use previously path to check all steps, but of course, this will not be the shortest one.
      def loop(current: Int, path: List[Int]): List[Int] = {

        if (current != s) loop(edgeTo(current), current :: path)
        else s :: path

      }

      Some(loop(v, Nil))

    }
  }

}