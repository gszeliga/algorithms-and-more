package com.gzeliga.playground.algorithms.search

object test {
  val stream1: Stream[Int] = {
    def loop(v: Int): Stream[Int] = v #:: loop(v + 1)
    loop(0)
  }                                               //> stream1  : Stream[Int] = Stream(0, ?)

  stream1 take (3) force                          //> res0: scala.collection.immutable.Stream[Int] = Stream(0, 1, 2)

  val tree = new BinarySearchTree[String, Int]    //> tree  : com.gzeliga.playground.algorithms.search.BinarySearchTree[String,Int
                                                  //| ] = com.gzeliga.playground.algorithms.search.BinarySearchTree@33010058

  tree.put("J", 34)
  tree.put("U", 37)
  tree.put("X", 22)
  tree.put("A", 29)
  tree.put("Z", 59)
  tree.put("C", 99)

  val s = tree.keys                               //> s  : List[String] = List(A, C, J, U, X, Z)
  val s1 = tree.keys("J", "Z")                    //> s1  : List[String] = List(J, U, X, Z)

  val other = new BinarySearchTree[String, Int]   //> other  : com.gzeliga.playground.algorithms.search.BinarySearchTree[String,In
                                                  //| t] = com.gzeliga.playground.algorithms.search.BinarySearchTree@630045eb

  other.keys                                      //> res1: List[String] = List()

  def sum(k: Int, v: Int): Int = k + v            //> sum: (k: Int, v: Int)Int
  def addTwo = sum(2, _:Int)                      //> addTwo: => Int => Int


	def addFour = addTwo.andThen(addTwo)      //> addFour: => Int => Int

  addFour(3)                                      //> res2: Int = 7

	val a = new Array[Integer](2)             //> a  : Array[Integer] = Array(null, null)

	a(1) = null


}