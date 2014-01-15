package com.gzeliga.playground.algorithms.search

import scala.Some
import scala.annotation.tailrec

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

  def min(cn: Node[K, V]): Node[K, V] = {

    cn match {
      case Leaf => Leaf
      case b @ Branch(k, v, Leaf, right) => b
      case Branch(k, v, left, right) => min(left)
    }

  }

  def min: Option[K] = {
    min(root) match {
      case Branch(k, _, _, _) => Some(k)
      case _ => None
    }
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

  def floor(key: K)(implicit ord: Ordering[K]): Option[K] = {

    def doFloor(current: Node[K, V]): Option[K] = {
      current match {
        case Leaf => None
        case Branch(k, v, left, right) =>
          if (key == k) Some(k)
          else if (ord.lt(key, k)) doFloor(left)
          else {
            doFloor(right) match {
              case None => Some(k)
              case v @ Some(_) => v
            }
          }
      }
    }

    doFloor(root)

  }

  def ceiling(key: K)(implicit ord: Ordering[K]): Option[K] = {

    def doCeiling(current: Node[K, V]): Option[K] = {
      current match {
        case Leaf => None
        case Branch(k, v, left, right) =>
          if (key == k) Some(k)
          else if (ord.gt(key, k)) doCeiling(right)
          else {
            doCeiling(left) match {
              case None => Some(k)
              case v @ Some(_) => v
            }
          }
      }
    }

    doCeiling(root)

  }

  def select(rank: Int): Option[Node[K, V]] = {
    def doSelect(current: Node[K, V], crank: Int): Option[Node[K, V]] = {

      current match {
        case Leaf => None
        case Branch(k, v, left, right) => {
          if (left.size > crank) doSelect(left, crank)
          else if (left.size < crank) doSelect(right, crank - left.size - 1)
          else Some(current)
        }
      }

    }

    doSelect(root, rank)

  }

  private def deleteMin(current: Node[K, V]): Node[K, V] = {

    current match {
      case Leaf => Leaf
      case Branch(k, v, Leaf, right) => right
      case Branch(k, v, left, right) => new Branch(k, v, deleteMin(left), right)
    }

  }

  def deleteMin: Unit = {
    root = deleteMin(root)
  }

  def delete(key: K)(implicit ord: Ordering[K]) = {

    def doDelete(current: Node[K, V]): Node[K, V] = {
      current match {
        case Leaf => Leaf
        case Branch(k, v, left, right) if ord.gt(key, k) => new Branch(k, v, left, doDelete(right))
        case Branch(k, v, left, right) if ord.lt(key, k) => new Branch(k, v, doDelete(left), right)
        case Branch(k, v, Leaf, right) => right
        case Branch(k, v, left, Leaf) => left
        case Branch(k, v, left, right) => {
          val t = min(right)

          //1- Search for the minimum on the right
          //2- We create a new branch with the left side of the deleted node plus the right side minus the min from the right side
          new Branch(t.key, t.value, left, deleteMin(right))

        }
      }
    }

    root = doDelete(root)

  }

  private def keys(start: Node[K, V], low: K, high: K)(implicit ord: Ordering[K]): List[K] = {

    def step(current: Node[K, V], elems: List[K]): List[K] = {

      current match {
        case Branch(k, v, left, right) if ord.lteq(low, k) && ord.gteq(high, k) => step(right, step(left, elems) :+ k)
        case Branch(k, v, left, right) if ord.gt(low, k) => step(right, elems)
        case Branch(k, v, left, right) if ord.lt(low, k) => step(left, elems)
        case _ => elems
      }
    }

    step(start, Nil)
  }

  def keys(low: K, high: K)(implicit ord: Ordering[K]): List[K] = {
    keys(root, low, high)
  }

  //http://nurkiewicz.blogspot.com/2013/05/lazy-sequences-in-scala-and-clojure.html
  def keys(implicit ord: Ordering[K]): List[K] = {
    {
      for {
        mn <- min
        mx <- max
      } yield keys(root, mn, mx)
    } getOrElse Nil
  }

}