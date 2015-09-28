package com.gszeliga.playground.algorithms.search

import scala.collection.immutable.Vector

class SeparateChainingHashST[K, V](private val M: Int = 997) {

  //Create and initialized the new array empty linked list instances
  val st = new Array[SequentialSearchST[K, V]](M) map { _ => new SequentialSearchST[K, V]() }

  //We use modular hashing
  private def hash(key: K) = (key.hashCode() & 0x7fffffff) % M
  
  def get(key: K) = st(hash(key)).get(key)
  def put(key: K, value: V) = st(hash(key)).put(key, value)
  
}