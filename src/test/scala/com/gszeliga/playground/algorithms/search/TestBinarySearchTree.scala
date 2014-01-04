package com.gszeliga.playground.algorithms.search

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gzeliga.playground.algorithms.search.BinarySearchTree

@RunWith(classOf[JUnitRunner])
class TestBinarySearchTree  extends FlatSpec with Matchers {

  behavior of "Binary Search Tree"
  
  "If a put an element" must "size should be 1" in {
    
    val tree = new BinarySearchTree[String, Int]
    
    tree.put("Guillermo", 34)
    
    tree.size should be (1)
    
  }

  "If a put two elements" must "size should be 2" in {
    
    val tree = new BinarySearchTree[String, Int]
    
    tree.put("Guillermo", 34)
    tree.put("Marta", 37)
    
    tree.size should be (2)
    
  }  

  "If a put 4 elements" must "size should be 4" in {
    
    val tree = new BinarySearchTree[String, Int]
    
    tree.put("Guillermo", 34)
    tree.put("Marta", 37)
    tree.put("Fernando", 22)
    tree.put("Eduardo", 29)
    tree.put("Liliana", 59)
    
    tree.size should be (5)
    
  }    
  
}