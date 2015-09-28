package com.gszeliga.playground.algorithms

/**
 * Created by guillermo on 28/09/15.
 */
object Algorutils {

  def less[T](a: T, b: T)(implicit cmp: Ordering[T]) = cmp.lt(a, b)
  def greater[T](a: T, b: T)(implicit cmp: Ordering[T]) = cmp.gt(a, b)

  def swap[T](source: List[T], p1: Int, p2: Int): List[T] = {

    if (p1 == p2) source
    else {
      source.zipWithIndex map { a =>
        if (a._2 == p1) source(p2)
        else if (a._2 == p2) source(p1)
        else a._1
      }
    }
  }
}
