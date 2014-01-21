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
    override def left = throw new NoSuchElementException("Empty node does not containt left branch")
    override def right = throw new NoSuchElementException("Empty node does not containt right branch")
    override def key: Nothing = throw new NoSuchElementException("Empty node does not containt key")
    override def value: Nothing = throw new NoSuchElementException("Empty node does not containt value")
    override val size = 0
    override def color = false
  }

  sealed case class Branch[K, V](val key: K, val value: V, val left: Node[K, V], val right: Node[K, V], val color: Boolean) extends Node[K, V] {
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

  private def balanceColors = {

    def rotateLeft(source: Node[K, V]): Node[K, V] = {

      //New RED node
      val redNode = new Branch(source.key, source.value, source.left, source.right.left, true);
      val toRightNode = new Branch(source.right.key, source.right.value, redNode, source.right.right, source.color);

      toRightNode
    }

    def rotateRight(source: Node[K, V]): Node[K, V] = {

      //New RED node
      val redNode = new Branch(source.key, source.value, source.left.right, source.right, true)
      val toLeftNode = new Branch(source.left.key, source.left.value, source.left.left, redNode, source.color)

      toLeftNode

    }

    def flipColors(source: Node[K, V]): Node[K, V] = {

      //Make both child nodes black
      val leftNode = new Branch(source.left.key, source.left.value, source.left.left, source.left.right, false)
      val rightNode = new Branch(source.right.key, source.right.value, source.right.left, source.right.right, false)

      //Make parent red
      new Branch(source.key, source.value, leftNode, rightNode, true)

    }

    def onlyIf[T](precond: T => Boolean)(step: Function1[T, T]): PartialFunction[T, T] = {
      case n if precond(n) => step(n)
      case n => n
    }

    val rotateToLeft = onlyIf[Node[K, V]](n => n.right.isRed && !n.left.isRed)(rotateLeft)
    val rotateToRight = onlyIf[Node[K, V]](n => n.left.isRed && n.left.left.isRed)(rotateRight)
    val flipNodeColors = onlyIf[Node[K, V]](n => n.left.isRed && n.right.isRed)(flipColors)

    rotateToLeft.andThen(rotateToRight).andThen(flipNodeColors)

  }

  private[this] var root: Node[K, V] = Leaf

  def size() = root.size

  def put(key: K, value: V)(implicit ord: Ordering[K]) = {

    def doPut(current: Node[K, V]): Node[K, V] = {
      current match {
        case Leaf => new Branch(key, value, Leaf, Leaf, true)
        case Branch(k, v, left, right, color) if ord.lt(key, k) => balanceColors(new Branch(k, v, doPut(left), right, color))
        case Branch(k, v, left, right, color) if ord.gt(key, k) => balanceColors(new Branch(k, v, left, doPut(right), color))
        case Branch(k, v, left, right, color) => new Branch(k, value, left, right, color)
      }
    }

    //Root node must always be black
    root = doPut(root) match {
      case Branch(k, v, left, right, _) => new Branch(k, v, left, right, false)
      case _ => Leaf
    }
   
  }

}