package com.gszeliga.playground.algorithms.search

import scala.reflect.ClassTag
import scala.annotation.tailrec

class LinearProbingHashST[K, V](private var M: Int = 16)(implicit cond: ClassTag[K], cond1: ClassTag[V]) {

  private var N = 0 //Number of key-value pairs

  private var keys = new Array[K](M)
  private var values = new Array[V](M)

  private final val PROBE_KEY = null.asInstanceOf[K]
  private final val PROBE_VALUE = null.asInstanceOf[V]

  private def hash(key: K) = (key.hashCode & 0x7FFFFFFF) % M

  private def resize(size: Int): Unit = {

    val st = new LinearProbingHashST[K, V](size)

    @tailrec
    def loop(pos: Int): Unit = {
      if (pos < M) {
        if(keys(pos) != PROBE_KEY) st.put(keys(pos), values(pos))
        loop(pos + 1)
      }
    }

    loop(0)

    keys = st.keys
    values = st.values
    M = st.M
    
  }

  def put(key: K, value: V) = {

    //If load factor is M/2 then we need to resize the arrays
    if (N >= (M / 2)) resize(M * 2)

    @tailrec
    def loop(pos: Int): Unit = {
      if (keys(pos) == key) values(pos) = value
      else if (keys(pos) == PROBE_KEY) {
        keys(pos) = key
        values(pos) = value
      } else loop((pos + 1) % M)
    }

    loop(hash(key))
    N = N + 1
  }

  private def locate(key: K): Option[Int] = {

    @tailrec
    def loop(pos: Int): Option[Int] = {
      if (keys(pos) == PROBE_KEY) None
      else if (keys(pos) == key) Some(pos)
      else loop((pos + 1) % M)
    }

    loop(hash(key))
  }

  def get(key: K): Option[V] = {
    locate(key) map { pos => values(pos) }
  }
  
  def contains(key: K): Boolean = !locate(key).isEmpty
  

  def delete(key: K): Unit = {

    locate(key) map { pos =>

      keys(pos) = PROBE_KEY
      values(pos) = PROBE_VALUE

      @tailrec
      def loop(pos: Int): Unit = {

        if (keys(pos) != PROBE_KEY) {
          val k = keys(pos)
          val v = values(pos)

          keys(pos) = PROBE_KEY
          values(pos) = PROBE_VALUE

          N = N - 1

          put(k, v)

          loop((pos + 1) % M)

        }

      }

      loop((pos + 1) % M)

      N = N - 1

      if(N > 0 && N == M/8) resize(M/2)

    }

  }

}