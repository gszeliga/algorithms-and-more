package com.gzeliga.playground.algorithms.graph

object test_bellman {

  type Cycle[T] = Int => (Int, Option[T])

  def findNegativeCycle[T](f: Int => Boolean)(g: Int => Option[T]): Cycle[T] = {

    calls =>
      {
        if (f(calls)) {
          g(calls) match {
            case None => (calls + 1, None)
            case s @ _ => (calls, s)
          }
        } else (calls + 1, None)
      }

  }                                               //> findNegativeCycle: [T](f: Int => Boolean)(g: Int => Option[T])Int => (Int, O
                                                  //| ption[T])

  val c1 = findNegativeCycle[Int] { _ % 2 == 1 }(Some(_))
                                                  //> c1  : Int => (Int, Option[Int]) = <function1>

  c1(0)                                           //> res0: (Int, Option[Int]) = (1,None)
	c1(3)                                     //> res1: (Int, Option[Int]) = (3,Some(3))
	
}