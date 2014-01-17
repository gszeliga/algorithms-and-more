package com.gzeliga.playground.algorithms.search

class RedBlackBST[K, V] {

  trait Node[+K, +V] {
    def left: Node[K, V]
    def right: Node[K, V]
    def key: K
    def value: V
    def size: Int
    def color: Boolean
    final def isRed = color == true
    final def isBlack = color == false
  }

  object Leaf extends Node[Nothing, Nothing] {
    override def left = throw new NoSuchElementException("Empty node does not containt left node")
    override def right = throw new NoSuchElementException("Empty node does not containt right node")
    override def key: Nothing = throw new NoSuchElementException("Empty node does not containt key")
    override def value: Nothing = throw new NoSuchElementException("Empty node does not containt value")
    override val size = 0
    override def color = false
  }

  case class Branch[K, V](val key: K, val value: V, val left: Node[K, V], val right: Node[K, V], val color: Boolean) extends Node[K, V] {
    lazy val size = {
      {

        def getSize(current: Node[K, V]): Int = {
          current match {
            case Leaf => Leaf.size
            case Branch(key, value, left, right, color) => left.size + right.size + 1
          }
        }

        getSize(left) + getSize(right) + 1
      }
    }
  }

  private def rotateLeft(source: Node[K, V]): Node[K, V] = {

    //New RED node
    val redNode = new Branch(source.key, source.value, source.left, source.right.left, true);
    val toRightNode = new Branch(source.right.key, source.right.value, redNode, source.right.right, source.color);

    toRightNode
  }

  private def rotateRight(source: Node[K, V]): Node[K, V] = ???

}