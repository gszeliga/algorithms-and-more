package com.gszeliga.playground.algorithms.search

import scala.reflect.ClassTag
import com.gszeliga.playground.algorithms.defaults._

class BinarySearchDictionary[K, V](val capacity: Int)(implicit ev1: ClassTag[K], ev2: ClassTag[V], ord: Ordering[K], dk: Default[K]) {

  val defaultKeyValue = defaultOf[K]

  private var keys = new Array[K](capacity) map { v => defaultKeyValue }
  private var values = new Array[V](capacity)
  private var N: Int = 0

  def size = N

  private[this] def resize(newCapacity: Int) = {

    println("Resized to " + newCapacity)

    val newKeys = new Array[K](newCapacity) map { v => defaultKeyValue }
    val newValues = new Array[V](newCapacity)

    keys.copyToArray(newKeys)
    values.copyToArray(newValues)

    keys = newKeys
    values = newValues

  }

  def ceiling(k: K) = keys(rank(k))
  
  def floor(k: K) = {
    val r = rank(k)
    
    if(r < N &&  k == keys(r)) keys(r)
    else keys(r-1)
  }

  def get(key: K): Option[V] = {
    if (keys.length == 0) None
    else {
      val r = rank(key)
      if (r < N && keys(r) == key) Some(values(r))
      else None
    }
  }

  def put(key: K, value: V) = {
    val r = rank(key)

    if (r < N && keys(r) == key) {
      values(r) = value
    } else {

      //We double space by default
      if (N == keys.size) resize(2 * N)

      //displace positions until rank
      for (p <- N until r by -1) {
        keys(p) = keys(p - 1)
        values(p) = values(p - 1)
      }

      //set the new pair
      keys(r) = key
      values(r) = value
      N = N + 1
    }

  }

  /*
   * Returns the number of keys that are smaller than K, whether K exists or not 
   * */
  def rank(key: K): Int = {

    def doRank(low: Int, high: Int): Int = {

      if (low > high) low
      else {

        val mid = low + (high - low) / 2

        //Move to left half
        if (ord.lt(key, keys(mid))) doRank(low, mid - 1)
        //Move to right half
        else if (ord.gt(key, keys(mid))) doRank(mid + 1, high)
        else mid
      }
    }

    doRank(0, N - 1)

  }

  def delete(key: K) = ???
}