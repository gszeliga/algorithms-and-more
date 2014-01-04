package com.gzeliga.playground.algorithms.search

trait Node[+K, +V] {
  def left: Node[K, V]
  def right: Node[K, V]
  def key: K
  def value: V
  def size: Int
}

object Empty extends Node[Nothing, Nothing] {
  override def left = throw new NoSuchElementException("Empty node does not containt left node")
  override def right = throw new NoSuchElementException("Empty node does not containt right node")
  override def key: Nothing = throw new NoSuchElementException("Empty node does not containt key")
  override def value: Nothing = throw new NoSuchElementException("Empty node does not containt value")
  override val size = 0
}

case class Leaf[K, V](val key: K, val value: V, val left: Node[K, V], val right: Node[K, V]) extends Node[K, V] {
  lazy val size = {

    def getSize(current: Node[K, V]): Int = {
      current match {
        case Empty => Empty.size
        case Leaf(key, value, left, right) => left.size + right.size + 1
      }
    }

    getSize(left) + getSize(right) + 1
  }

}

class BinarySearchTree[K, V] {

  private var root: Node[K, V] = Empty

  def size() = root.size

  def put(key: K, value: V)(implicit ord: Ordering[K]) = {

    def doPut(current: Node[K, V]): Node[K, V] = {
      current match {
        case Empty => new Leaf(key, value, Empty, Empty)
        case Leaf(k, v, left, right) => {
          if (ord.gt(key, k)) {
            new Leaf(k, v, left, doPut(right))
          } else if (ord.lt(key, k)) {
            new Leaf(k, v, doPut(left), right)
          } else {
            new Leaf(k, value, left, right)
          }
        }
      }
    }

    root = doPut(root)

  }
}