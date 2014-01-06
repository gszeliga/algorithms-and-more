package com.gszeliga.playground.algorithms.search

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gzeliga.playground.algorithms.search.BinarySearchTree

@RunWith(classOf[JUnitRunner])
class TestBinarySearchTree  extends FlatSpec with Matchers {

  behavior of "Binary Search Tree"
  
  "If a put an element size" must "be 1" in {
    
    val tree = new BinarySearchTree[String, Int]
    
    tree.put("Guillermo", 34)
    
    tree.size should be (1)
    
  }

  "If a put two elements size" must "be 2" in {
    
    val tree = new BinarySearchTree[String, Int]
    
    tree.put("Guillermo", 34)
    tree.put("Marta", 37)
    
    tree.size should be (2)
    
  }  

  "If a put 5 elements size" must "be 5" in {
    
    val tree = new BinarySearchTree[String, Int]
    
    tree.put("Guillermo", 34)
    tree.put("Marta", 37)
    tree.put("Fernando", 22)
    tree.put("Eduardo", 29)
    tree.put("Liliana", 59)
    
    tree.size should be (5)
    
  }    
  
  "If a put an element related to a key I " must "be able to get it back" in {

    val tree = new BinarySearchTree[String, Int]
    
    tree.put("Guillermo", 34)
    tree.put("Marta", 37)
    tree.put("Fernando", 22)
    tree.put("Eduardo", 29)
    tree.put("Liliana", 59)    
    
    tree.get("Fernando") should equal (Some(22))
    
  }

  "If a put several elements within a BST I " must "be able to retrieve the minimum entry" in {

    val tree = new BinarySearchTree[String, Int]
    
    tree.put("Guillermo", 34)
    tree.put("Marta", 37)
    tree.put("Fernando", 22)
    tree.put("Eduardo", 29)
    tree.put("Liliana", 59)    
    
    tree.min should equal (Some("Eduardo"))
    
  }  

  "If a put several elements within a BST I " must "be able to retrieve the maximum entry" in {

    val tree = new BinarySearchTree[String, Int]
    
    tree.put("Guillermo", 34)
    tree.put("Marta", 37)
    tree.put("Fernando", 22)
    tree.put("Eduardo", 29)
    tree.put("Liliana", 59)    
    
    tree.max should equal (Some("Marta"))
    
  }    
  
}