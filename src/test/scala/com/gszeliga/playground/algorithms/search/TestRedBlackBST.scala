package com.gszeliga.playground.algorithms.search

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.gszeliga.playground.algorithms.search.RedBlackBST

@RunWith(classOf[JUnitRunner])
class TestRedBlackBST extends FlatSpec with Matchers{

  
  "If a put 5 elements size" must "be 5" in {

    val tree = new RedBlackBST[String, Int]

    tree.put("Guillermo", 34)
    tree.put("Marta", 37)
    tree.put("Fernando", 22)
    tree.put("Eduardo", 29)
    tree.put("Liliana", 59)

    tree.size should be(5)
    
    
    println(tree)

  }  

  "If a put 10 elements size" must "be 10" in {

    val tree = new RedBlackBST[String, Int]

    tree.put("S", 1)
    tree.put("E", 2)
    tree.put("A", 3)
    tree.put("R", 4)
    tree.put("C", 5)
    tree.put("H", 6)
    tree.put("X", 7)
    tree.put("M", 8)
    tree.put("P", 9)
    tree.put("L", 10)

    tree.size should be(10)
    
    
    println(tree)

  }
  
  "If a put 10 elements in ordered sequence size" must "be 10" in {

    val tree = new RedBlackBST[String, Int]

    tree.put("A", 1)
    tree.put("C", 2)
    tree.put("E", 3)
    tree.put("H", 4)
    tree.put("L", 5)
    tree.put("M", 6)
    tree.put("P", 7)
    tree.put("R", 8)
    tree.put("S", 9)
    tree.put("X", 10)

    tree.size should be(10)
    
    
    println(tree)

  }  
  
}