package com.gszeliga.playground.algorithms.search

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gszeliga.playground.algorithms.search.BinarySearchTree

@RunWith(classOf[JUnitRunner])
class TestBinarySearchTree extends FlatSpec with Matchers {

  behavior of "Binary Search Tree"

  "If a put an element size" must "be 1" in {

    val tree = new BinarySearchTree[String, Int]

    tree.put("Guillermo", 34)

    tree.size should be(1)

  }

  "If a put two elements size" must "be 2" in {

    val tree = new BinarySearchTree[String, Int]

    tree.put("Guillermo", 34)
    tree.put("Marta", 37)

    tree.size should be(2)

  }

  "If a put 5 elements size" must "be 5" in {

    val tree = new BinarySearchTree[String, Int]

    tree.put("Guillermo", 34)
    tree.put("Marta", 37)
    tree.put("Fernando", 22)
    tree.put("Eduardo", 29)
    tree.put("Liliana", 59)

    tree.size should be(5)

  }

  "If a put an element related to a key I " must "be able to get it back" in {

    val tree = new BinarySearchTree[String, Int]

    tree.put("Guillermo", 34)
    tree.put("Marta", 37)
    tree.put("Fernando", 22)
    tree.put("Eduardo", 29)
    tree.put("Liliana", 59)

    tree.get("Fernando") should equal(Some(22))

  }

  "If a put several elements within a BST I " must "be able to retrieve the minimum entry" in {

    val tree = new BinarySearchTree[String, Int]

    tree.put("Guillermo", 34)
    tree.put("Marta", 37)
    tree.put("Fernando", 22)
    tree.put("Eduardo", 29)
    tree.put("Liliana", 59)

    tree.min should equal(Some("Eduardo"))

  }

  it must "be able to retrieve the maximum entry" in {

    val tree = new BinarySearchTree[String, Int]

    tree.put("Guillermo", 34)
    tree.put("Marta", 37)
    tree.put("Fernando", 22)
    tree.put("Eduardo", 29)
    tree.put("Liliana", 59)

    tree.max should equal(Some("Marta"))

  }

  it must "be able to retrieve the floor of a key" in {

    val tree = new BinarySearchTree[String, Int]

    tree.put("S", 1)
    tree.put("E", 2)
    tree.put("A", 3)
    tree.put("C", 4)
    tree.put("R", 5)
    tree.put("H", 6)
    tree.put("M", 7)
    tree.put("X", 8)

    tree.floor("G") should equal(Some("E"))
    tree.floor("C") should equal(Some("C"))

  }

  it must "be able to retrieve the ceiling of a key" in {

    val tree = new BinarySearchTree[String, Int]

    tree.put("S", 1)
    tree.put("E", 2)
    tree.put("A", 3)
    tree.put("C", 4)
    tree.put("R", 5)
    tree.put("H", 6)
    tree.put("M", 7)
    tree.put("X", 8)

    tree.ceiling("Q") should equal(Some("R"))
    tree.ceiling("B") should equal(Some("C"))

  }

  it must "be able to delete minimum" in {

    val tree = new BinarySearchTree[String, Int]

    tree.put("S", 1)
    tree.put("E", 2)
    tree.put("A", 3)
    tree.put("C", 4)
    tree.put("R", 5)
    tree.put("H", 6)
    tree.put("M", 7)
    tree.put("X", 8)

    tree.get("A") should equal(Some(3))
    tree.size should be(8)
    
    tree.deleteMin

    tree.get("A") should equal(None)
    tree.size should be(7)

  }

  it must "be able to delete any node" in {

    val tree = new BinarySearchTree[String, Int]

    tree.put("S", 1)
    tree.put("E", 2)
    tree.put("A", 3)
    tree.put("C", 4)
    tree.put("R", 5)
    tree.put("H", 6)
    tree.put("M", 7)
    tree.put("X", 8)

    tree.get("E") should equal(Some(2))
    tree.size should be(8)
    
    tree.delete("E")

    tree.get("E") should equal(None)
    tree.size should be(7)

  }  
  
  
  it must "be able to retrieve all keys in order" in {

    val tree = new BinarySearchTree[String, Int]

    tree.put("S", 1)
    tree.put("E", 2)
    tree.put("A", 3)
    tree.put("C", 4)
    tree.put("R", 5)
    tree.put("H", 6)
    tree.put("M", 7)
    tree.put("X", 8)

    tree.keys should equal(List("A","C","E","H","M","R","S","X"))
  }    
  
}