package com.gzeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet
import scala.collection.immutable.Stack
import scala.annotation.tailrec

class DirectedCycle(val g: Digraph) {

  private val (_, _, foundCycle) = {

    val edgeTo = new Array[Int](g.V)

    def dfs(state: (BitSet, BitSet, Option[Stack[Int]]), v: Int): (BitSet, BitSet, Option[Stack[Int]]) = {

      //Current v vertex is being marked and stacked and start looping on the adjacent ones
      g.adj(v).foldLeft((state._1 + v, state._2 + v, state._3)) { (acc, w) =>

        val marked = acc._1
        val stacked = acc._2
        val cycle = acc._3

        if (!cycle.isDefined) {
          if (!marked(w)) {
            edgeTo(w) = v
            val (mrk, sck, cl) = dfs(acc, w)
            (mrk, sck - w, cl) //Remove the current element from the stack since we're back
          } else if (stacked(w)) {
            (marked, stacked, Some(buildCycle(v, w, v, Stack.empty))) //we found a cycle!
          } else acc
        } else acc
      }

    }

    @tailrec
    def buildCycle(v: Int, w: Int, current: Int, stack: Stack[Int]): Stack[Int] = {

      if (current != w) {
        buildCycle(v, w, edgeTo(current), stack.push(current))
      } else {
        stack.push(w).push(v)
      }

    }

    (0 until g.V).foldLeft((BitSet.empty, BitSet.empty, Option.empty[Stack[Int]])) { (state, v) =>

      val mrk = state._1

      //TODO I think it's also good idea to check whether a cycle was found or not
      if (!mrk(v)) dfs(state, v) else state
    }

  }

  def hasCycle: Boolean = foundCycle.isDefined
  def cycle: Option[Seq[Int]] = foundCycle

}