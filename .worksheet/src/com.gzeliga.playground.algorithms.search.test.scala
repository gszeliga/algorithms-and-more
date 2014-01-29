package com.gzeliga.playground.algorithms.search

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(164); 
  val stream1: Stream[Int] = {
    def loop(v: Int): Stream[Int] = v #:: loop(v + 1)
    loop(0)
  };System.out.println("""stream1  : Stream[Int] = """ + $show(stream1 ));$skip(26); val res$0 = 

  stream1 take (3) force;System.out.println("""res0: scala.collection.immutable.Stream[Int] = """ + $show(res$0));$skip(48); 

  val tree = new BinarySearchTree[String, Int];System.out.println("""tree  : com.gzeliga.playground.algorithms.search.BinarySearchTree[String,Int] = """ + $show(tree ));$skip(21); 

  tree.put("J", 34);$skip(20); 
  tree.put("U", 37);$skip(20); 
  tree.put("X", 22);$skip(20); 
  tree.put("A", 29);$skip(20); 
  tree.put("Z", 59);$skip(20); 
  tree.put("C", 99);$skip(21); 

  val s = tree.keys;System.out.println("""s  : List[String] = """ + $show(s ));$skip(31); 
  val s1 = tree.keys("J", "Z");System.out.println("""s1  : List[String] = """ + $show(s1 ));$skip(49); 

  val other = new BinarySearchTree[String, Int];System.out.println("""other  : com.gzeliga.playground.algorithms.search.BinarySearchTree[String,Int] = """ + $show(other ));$skip(14); val res$1 = 

  other.keys;System.out.println("""res1: List[String] = """ + $show(res$1));$skip(40); 

  def sum(k: Int, v: Int): Int = k + v;System.out.println("""sum: (k: Int, v: Int)Int""");$skip(29); 
  def addTwo = sum(2, _:Int);System.out.println("""addTwo: => Int => Int""");$skip(40); 


	def addFour = addTwo.andThen(addTwo);System.out.println("""addFour: => Int => Int""");$skip(14); val res$2 = 

  addFour(3);System.out.println("""res2: Int = """ + $show(res$2));$skip(32); 

	val a = new Array[Integer](2);System.out.println("""a  : Array[Integer] = """ + $show(a ));$skip(14); 

	a(1) = null}


}
