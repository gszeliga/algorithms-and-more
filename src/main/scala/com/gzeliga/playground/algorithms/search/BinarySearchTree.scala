package com.gzeliga.playground.algorithms.search

import scala.Some

trait Node[+K, +V] {
  def left: Node[K, V]
  def right: Node[K, V]
  def key: K
  def value: V
  def size: Int
}

object Leaf extends Node[Nothing, Nothing] {
  override def left = throw new NoSuchElementException("Empty node does not containt left node")
  override def right = throw new NoSuchElementException("Empty node does not containt right node")
  override def key: Nothing = throw new NoSuchElementException("Empty node does not containt key")
  override def value: Nothing = throw new NoSuchElementException("Empty node does not containt value")
  override val size = 0
}

case class Branch[K, V](val key: K, val value: V, val left: Node[K, V], val right: Node[K, V]) extends Node[K, V] {
  lazy val size = {
    {

      def getSize(current: Node[K, V]): Int = {
        current match {
          case Leaf => Leaf.size
          case Branch(key, value, left, right) => left.size + right.size + 1
        }
      }

      getSize(left) + getSize(right) + 1
    }
  }

}

class BinarySearchTree[K, V] {

  private var root: Node[K, V] = Leaf

  def size() = root.size

  def get(key: K)(implicit ord: Ordering[K]): Option[V] = {

    def doGet(current: Node[K, V]): Option[V] = {

      current match {
        case Leaf => None
        case Branch(k, v, left, right) => {
          if (key == k) Some(v)
          else {
            if (ord.gt(key, k)) doGet(right)
            else doGet(left)
          }
        }
      }

    }

    doGet(root)

  }

  def put(key: K, value: V)(implicit ord: Ordering[K]) = {

    def doPut(current: Node[K, V]): Node[K, V] = {
      current match {
        case Leaf => new Branch(key, value, Leaf, Leaf)
        case Branch(k, v, left, right) => {
          if (ord.gt(key, k)) {
            new Branch(k, v, left, doPut(right))
          } else if (ord.lt(key, k)) {
            new Branch(k, v, doPut(left), right)
          } else {
            new Branch(k, value, left, right)
          }
        }
      }
    }

    root = doPut(root)

  }

  def min: Option[K] = {

    def doMin(cn: Node[K, V], latest: Option[K]): Option[K] = {

      cn match {
        case Leaf => latest
        case Branch(k, v, left, right) => doMin(left, Some(k))
      }

    }

    doMin(root, None)

  }

  def max: Option[K] = {

    def doMax(cn: Node[K, V], latest: Option[K]): Option[K] = {

      cn match {
        case Leaf => latest
        case Branch(k, v, left, right) => doMax(right, Some(k))
      }

    }

    doMax(root, None)

  }  
 
  def floor(key:K): K = ???
  def ceiling(key:K): K = ???
  
}