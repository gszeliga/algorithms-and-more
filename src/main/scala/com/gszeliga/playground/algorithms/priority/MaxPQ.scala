package com.gszeliga.playground.algorithms.priority

import com.gszeliga.playground.algorithms.Algorutils.less

import scala.annotation.tailrec
import scala.reflect.ClassTag

/**
 * Created by guillermo on 28/09/15.
 */
//Time: O(log N) since the max level of a tree with N elements is log N
class MaxPQ[K](maxN: Int)(implicit cmp: Ordering[K], ev1: ClassTag[K]) {

  //pq[0] will be unused
  private val pq = new Array[K](maxN+1)
  private var N = 0

  def isEmpty = N == 0
  def size = N

  //Time: 1 + log N
  def insert(v:K) = {
    //Elements are always inserted last
    N = N + 1
    pq(N)=v
    //We need to verify invariants (re-heapify the structure)
    swim(N)
  }

  //Time: 2 log N
  def delMax: Option[K] = {
    if(isEmpty) None
    else
    {
      //First element always is the maximum value
      val max: K = pq(1)
      //Put last element as root of the tree
      exch(1, N)
      //Release N position
      //Resize maybe?
      N = N - 1
      //Sink first element to restore invariants (if necessary)
      sink(1)

      Some(max)
    }
  }

  @tailrec
  private def swim(k: Int):Unit = {

    if(k > 1)
    {
      val parent = k/2

      //If parent is smaller than current node
      if(less(pq(parent), pq(k))){
        exch(parent,k)
        swim(parent)
      }
    }

  }

  @tailrec
  private def sink(k: Int): Unit = {

    var child = k*2

    if(child <= N)
    {
      //If sibling at same level is greater than current then switch indexes
      if(child < N && less(pq(child),pq(child+1)))
      {
        child=child+1
      }

      //If current node is smaller than child
      if(less(pq(k), pq(child)))
      {
        exch(k,child)
        sink(child)
      }
    }
  }

  private def exch(i: Int, j: Int) = {
    val tmp = pq(i)
    pq(i) = pq(j)
    pq(j) = tmp
  }
}
