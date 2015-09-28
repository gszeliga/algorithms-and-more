package com.gszeliga.playground.algorithms.search

object RedBlackBST {

  private sealed trait Node[+K, +V] {
    def left: Node[K, V]
    def right: Node[K, V]
    def key: K
    def value: V
    def size: Int
    def color: Boolean
    final def isRed = color == true
    final def isBlack = color == false
  }

  private object Leaf extends Node[Nothing, Nothing] {
    override def left = throw new NoSuchElementException("Empty node does not containt left branch")
    override def right = throw new NoSuchElementException("Empty node does not containt right branch")
    override def key: Nothing = throw new NoSuchElementException("Empty node does not containt key")
    override def value: Nothing = throw new NoSuchElementException("Empty node does not containt value")
    override val size = 0
    override def color = false

    override def toString() = "Leaf"

  }

  private case class Branch[K, V](val key: K, val value: V, val left: Node[K, V], val right: Node[K, V], val color: Boolean) extends Node[K, V] {
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

    override def toString() = s"[$left <--($key,$value,$color)--> $right]"

  }

  private def rotateLeft[K, V](source: Node[K, V]): Node[K, V] = {

    println(s"Rotate ${source.key} to left..")

    //New RED node
    val redNode = new Branch(source.key, source.value, source.left, source.right.left, true);
    val toRightNode = new Branch(source.right.key, source.right.value, redNode, source.right.right, source.color);

    toRightNode
  }

  private def rotateRight[K, V](source: Node[K, V]): Node[K, V] = {

    println(s"Rotate ${source.key} to right..")

    //New RED node
    val redNode = new Branch(source.key, source.value, source.left.right, source.right, true)
    val toLeftNode = new Branch(source.left.key, source.left.value, source.left.left, redNode, source.color)

    toLeftNode

  }

  private def flipColors[K, V](source: Node[K, V]): Node[K, V] = {

    println(s"Flip colors on ${source.key}..")

    //Make both child nodes black
    val leftNode = new Branch(source.left.key, source.left.value, source.left.left, source.left.right, false)
    val rightNode = new Branch(source.right.key, source.right.value, source.right.left, source.right.right, false)

    //Make parent red
    new Branch(source.key, source.value, leftNode, rightNode, true)

  }

  private def balanceColors[K, V] = {

/*    implicit class ConditionalPartialFunction[T, U](val f: T => U) extends AnyRef {

      def onlyIf(precond: T => Boolean): PartialFunction[T, U] = {
        case n if precond(n) => f(n)
        case n => n.asInstanceOf[U]
      }
    }*/

    def onlyIf[T](precond: T => Boolean)(step: Function1[T, T]): PartialFunction[T, T] = {
      case n if precond(n) => step(n)
      case n => n
    }

    //Make all steps Partial Functions
    //val rl = rotateLeft(_: Node[K, V])
    //val rr = rotateRight(_: Node[K, V])
    //val fc = flipColors(_: Node[K, V])

    //rl onlyIf (n => n.right.isRed && !n.left.isRed) andThen rr onlyIf (n => n.left.isRed && n.left.left.isRed) andThen fc onlyIf (n => n.left.isRed && n.right.isRed)

    val rotateToLeft = onlyIf[Node[K, V]](n => n.right.isRed && !n.left.isRed)(rotateLeft)
    val rotateToRight = onlyIf[Node[K, V]](n => n.left.isRed && n.left.left.isRed)(rotateRight)
    val flipNodeColors = onlyIf[Node[K, V]](n => n.left.isRed && n.right.isRed)(flipColors)

    rotateToLeft.andThen(rotateToRight).andThen(flipNodeColors)

  }

  private def put[K, V](node: Node[K, V], key: K, value: V)(implicit ord: Ordering[K]) = {

    def doPut(current: Node[K, V]): Node[K, V] = {
      current match {
        case Leaf => new Branch(key, value, Leaf, Leaf, true)
        case Branch(k, v, left, right, color) if ord.lt(key, k) => balanceColors(new Branch(k, v, doPut(left), right, color))
        case Branch(k, v, left, right, color) if ord.gt(key, k) => balanceColors(new Branch(k, v, left, doPut(right), color))
        case Branch(k, v, left, right, color) => new Branch(k, value, left, right, color)
      }
    }

    balanceColors(doPut(node))
  }

}

class RedBlackBST[K, V] {

  private[this] var root: RedBlackBST.Node[K, V] = RedBlackBST.Leaf

  def size() = root.size

  def put(key: K, value: V)(implicit ord: Ordering[K]) = {

    //Root node must always be black
    root = RedBlackBST.put(root, key, value) match {
      case RedBlackBST.Branch(k, v, left, right, _) => new RedBlackBST.Branch(k, v, left, right, false)
      case _ => RedBlackBST.Leaf
    }

  }

  override def toString() = root.toString()

}