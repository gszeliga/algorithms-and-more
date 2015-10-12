package com.gszeliga.playground.algorithms.priority

import com.gszeliga.playground.algorithms.Algorutils.less

import scala.reflect.ClassTag

/**
 * Created by guillermo on 30/09/15.
 */
class IndexMinPQ[K](val maxN: Int)(implicit cmp: Ordering[K], ev1: ClassTag[K]) {

  private var N = 0
  private val pq = new Array[Int](maxN + 1) //from ordinal to index
  private val qp = new Array[Int](maxN + 1) map (_ => -1) // from index to ordinal
  private val keys = new Array[K](maxN + 1) // from index to key

  def isEmpty = N == 0

  //Check whether the provided index actually exists
  def contains(k: Int) = {
    assert(k > 0 && k < maxN, s"Index '$k' is out of bound [1 - $maxN]")
    qp(k) == -1
  }

  def size = N

  def insert(index: Int, key: K) = {

    assert(index > 0 && index < maxN, s"Index '$index' is out of bound [1 - $maxN]")

    if(contains(index)) throw new IllegalArgumentException("Index already present within priority queue")

    N = N + 1
    qp(index) = N //Relate provided index to current element position
    pq(N) = index // Relate current element position to its index
    keys(index) = key // Relate provided index to its key

    swim(N)
  }

  def minIndex = {
    assert(N == 0, "Priority queue underflow")
    pq(1)
  }

  def minKey = {
    assert(N == 0, "Priority queue underflow")
    keys(minIndex)
  }

  def delMin: Int = {
    val min = minIndex

    //Move last element first
    exch(1, N)

    //Decrease number of available elements
    N = N - 1

    //Sink new value to restore invariants
    sink(1)

    //Release min index for further use
    qp(min) = -1

    //keys(pq(N+1)) = null -> release key to help garbage collector

    //Clean up the index reference from last element
    pq(N+1) = -1

    min
  }

  def keyOf(index: Int): K = {
    assert(index > 0 && index < maxN, s"Index '$index' is out of bound [1 - $maxN]")
    if(contains(index)) throw new IllegalArgumentException("Index already present within priority queue")

    keys(index)
  }

  def changeKey(index: Int, key: K) = {

    assert(index > 0 && index < maxN, s"Index '$index' is out of bound [1 - $maxN]")
    if(!contains(index)) throw new IllegalArgumentException("Index not present within priority queue")

    keys(index) = key

    swim(qp(index))
    sink(qp(index))
  }

  def decreaseKey(index: Int, key: K) = {
    if(less(keys(index),key))
    {
      throw new IllegalArgumentException(s"Key $key is not actually decreasing current key with index $index")
    }

    keys(index)=key

    //Need to 'swim' to key
    swim(qp(index))

  }

  def delete(index: Int) = {

    val ordinal = qp(index)

    //Exchange removed index with latest element
    exch(ordinal,N)
    swim(ordinal)
    sink(ordinal)

    //Reduce number of elements
    N = N - 1

    //TODO release key
    qp(index) = -1

  }

  private def exch(i: Int, j: Int) = {
    val tmp = pq(i)
    pq(i) = pq(j)
    pq(j) = tmp

    qp(pq(i))=i
    qp(pq(j))=j

  }

  def swim(i: Int): Unit = {

    val parent = i / 2

    if(parent > 1 && less(keys(pq(i)),keys(pq(parent))))
    {
      exch(parent, i)
      swim(parent)
    }

  }


  def sink(i: Int): Unit = {

    var child = i * 2

    if(child <= N) {
      if (child < N && less(keys(pq(child + 1)), keys(pq(child)))) {
        child = child + 1
      }

      if (less(keys(pq(child)), keys(pq(i)))) {
        exch(i, child)
        sink(child)
      }
    }

  }

}
