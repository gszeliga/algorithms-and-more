package com.gszeliga.playground.algorithms.search

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.gzeliga.playground.algorithms.search.RedBlackBST

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

  }  
  
}