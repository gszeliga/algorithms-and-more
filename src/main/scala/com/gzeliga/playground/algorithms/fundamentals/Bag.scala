package com.gzeliga.playground.algorithms.fundamentals

object Bag {

  private sealed trait Node[+E] {
    def item: E
    def next: Node[E]
  }

  private case class Elem[E](val item: E, val next: Node[E]) extends Node[E]

  private case object Empty extends Node[Nothing] {
    def item = throw new NoSuchElementException("Empty elements does not have a related item")
    def next = throw new NoSuchElementException("Empty elements does not have a related next node")
  }

  private def add[E](item: E, node: Node[E]): Node[E] = new Elem(item, node)

  private def values[E](node: Node[E]): Stream[E] = {

    def loop(current: Bag.Node[E]): Stream[E] = {
      current match {
        case Bag.Empty => Stream.Empty
        case Bag.Elem(item, next) => item #:: loop(next)
      }
    }

    loop(node)
 
  }  
  
}

class Bag[E] {

  private var first: Bag.Node[E] = Bag.Empty
  private var N = 0

  def add(item: E) = {
    first = Bag.add(item, first)
    N = N + 1
  }

  def isEmpty = first == Bag.Empty
  def size = N

  def values: Stream[E] = Bag.values(first)

}