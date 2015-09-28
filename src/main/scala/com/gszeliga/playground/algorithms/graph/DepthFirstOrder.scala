package com.gszeliga.playground.algorithms.graph

import scala.collection.immutable.BitSet
import scala.collection.immutable.Queue
import scala.collection.immutable.Stack
import scala.annotation.tailrec

//Vertex ordering in a Digraph (Precedence-Constrained scheduling)
class DepthFirstOrder(G: Digraph) {

  private val (marked, preOrd, posOrd, reversePostOrd) = {

    def dfs(v: Int, state: (BitSet, Queue[Int], Queue[Int], Stack[Int])): (BitSet, Queue[Int], Queue[Int], Stack[Int]) = {

      //We mark vertex and add it to the pre list 
      val (mrk, pre, post, reverse) = (state._1 + v, state._2.enqueue(v), state._3, state._4)

      val ns = G.adj(v).filterNot(mrk).foldRight((mrk, pre, post, reverse))(dfs)

      //Add vertex to the post and reversePost 
      (ns._1, ns._2, ns._3.enqueue(v), ns._4.push(v))

    }

    @tailrec
    def loop(v: Int, state: (BitSet, Queue[Int], Queue[Int], Stack[Int])): (BitSet, Queue[Int], Queue[Int], Stack[Int]) = {

      if (v < G.V) {
        if (!state._1(v)) loop(v + 1, dfs(v, state))
        else loop(v + 1, state)
      } else state

    }

    loop(0, (BitSet.empty, Queue.empty[Int], Queue.empty[Int], Stack.empty[Int]))

  }

  def pre: Seq[Int] = preOrd //Queue before a recursive call
  def post: Seq[Int] = posOrd //Queue after a recursive call
  def reversePost: Seq[Int] = reversePostOrd //Stack after a recursive call

}