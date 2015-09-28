package com.gszeliga.playground.algorithms.search

object SequentialSearchST {

  sealed private trait Node[+K, +V] {
    def key: K
    def value: V
    def next: Node[K, V]
  }

  private case object Empty extends Node[Nothing, Nothing] {
    def key = throw new NoSuchElementException("Empty element does not have key")
    def value = throw new NoSuchElementException("Empty elements does not have value")
    def next = throw new NoSuchElementException("Empty element does not have next node")
  }

  private case class Element[K, V](val key: K, val value: V, val next: Node[K, V]) extends Node[K, V]

  private def put[K, V](current: Node[K, V], key: K, value: V): Node[K, V] = {
    current match {
      case Empty => new Element(key, value, Empty)
      case Element(k, v, next) if k != key => new Element(k, v, put(next, key, value))
      case Element(k, v, next) => new Element(k, value, next)
    }
  }

  private def get[K, V](current: Node[K, V], key: K): Option[V] = {
    current match {
      case Empty => None
      case Element(k, v, next) if k != key => get(next, key)
      case Element(k, v, next) => Some(v)
    }
  }

}

class SequentialSearchST[K, V] {

  private var first: SequentialSearchST.Node[K, V] = SequentialSearchST.Empty

  def put(key: K, value: V) = {
    first = SequentialSearchST.put(first, key, value)
  }

  def get(key: K): Option[V] = SequentialSearchST.get(first, key)

}
