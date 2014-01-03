package com.gzeliga.playground.algorithms.search

trait Node[+K, +V] {
  val left: Node[K, V]
  val right: Node[K, V]
  val key: K
  val value: V
  val size: Int
}

object Empty extends Node[Nothing, Nothing] {
  val left = throw new NoSuchElementException("Nil node does not containt left node")
  val right = throw new NoSuchElementException("Nil node does not containt right node")
  val key: Nothing = throw new NoSuchElementException("Nil node does not containt key")
  val value: Nothing = throw new NoSuchElementException("Nil node does not containt value")
  val size = throw new NoSuchElementException("Nil node does not containt size")
}

class Leaf[K, V](val key: K, val value: V) extends Node[K, V] {
  val left = Empty
  val right = Empty
  val size = 0
}

class SingleNode[K, V](val key: K, val value: V, val size: Int, val left: Node[K, V] = Empty, val right: Node[K, V] = Empty) extends Node[K, V]

class BinarySearchTree[K, V] {

  private val root: Node[K, V] = Empty

  def size() = root.size
  
  def put(n: Node[K,V])(implicit ord: Ordering[K]) = ???
  
}