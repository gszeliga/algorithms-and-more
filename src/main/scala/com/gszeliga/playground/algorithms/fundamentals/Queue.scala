package com.gszeliga.playground.algorithms.fundamentals

//This is a crappy queue, it's just for fun
trait Queue[E] {

  protected[fundamentals] def last: Node[E]
  protected[fundamentals] def first: Node[E]

  def isEmpty = first == Empty

  def enqueue(v: E): Queue[E] = {

    val l = new Elem[E](v, Empty)
    val f = {

      //Actually, this is a foldRight
      def loop(current: Node[E]): Node[E] = {

        current match {
          case Elem(v, r) => new Elem(v, loop(r))
          case Empty => l
        }

      }

      loop(first)
    }

    Queue(f, l)
  }

  def dequeue: (Option[E], Queue[E]) = {

    first match {
      case Empty => (None, Queue.empty[E])
      case Elem(v, next) => (Some(v), Queue(next, last))
    }

  }

  def values: Stream[E] = {

    def loop(current: Node[E]): Stream[E] = {
      current match {
        case Empty => Stream.Empty
        case Elem(item, next) => item #:: loop(next)
      }
    }

    loop(first)

  }

}

protected[fundamentals] sealed trait Node[+E] {
  def item: E
  def next: Node[E]
}

private[fundamentals] case class Elem[E](val item: E, val next: Node[E]) extends Node[E]

private[fundamentals] case object Empty extends Node[Nothing] {
  def item = throw new NoSuchElementException("Empty elements does not have a related item")
  def next = throw new NoSuchElementException("Empty elements does not have a related next node")
}

object Queue {

  private object EmptyQueue extends Queue[Nothing] {
    override val last = Empty
    override val first = Empty
  }

  def empty[E] = EmptyQueue.asInstanceOf[Queue[E]]

  private def apply[T](f: Node[T], l: Node[T]) = new Queue[T] {
    val first = f
    val last = l
  }

  def apply[T](vals: T*): Queue[T] = {

    vals.foldLeft(Queue.empty[T])((q, v) => q.enqueue(v))

  }
}