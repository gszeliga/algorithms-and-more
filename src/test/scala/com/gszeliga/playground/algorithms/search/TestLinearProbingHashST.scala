package com.gszeliga.playground.algorithms.search

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.gzeliga.playground.algorithms.search.LinearProbingHashST

@RunWith(classOf[JUnitRunner])
class TestLinearProbingHashST extends FlatSpec with Matchers{

  behavior of "LinearProbingHashST"
  
  "If a put several values then" must "be able to retrieve any of them" in {
    
    val sb = new LinearProbingHashST[String, Integer]
    
    sb.put("S", 0)
    sb.put("E", 1)
    sb.put("A", 2)
    sb.put("R", 3)
    sb.put("C", 4)
    sb.put("H", 5)
    sb.put("E", 10)
    sb.put("X", 7)
    sb.put("A", 8)
    sb.put("M", 9)
    sb.put("P", 10)
    sb.put("L", 11)
    sb.put("E", 12)
    
    sb.get("A") should be (Some(8))
    sb.get("P") should be (Some(10))
    sb.get("Q") should be (None)
  }
  
  "If a delete a value then" must "not be available anymore" in {
    
    val sb = new LinearProbingHashST[String, Int]
    
    sb.put("S", 0)
    sb.put("E", 1)
    sb.put("A", 2)
    sb.put("R", 3)
    sb.put("C", 4)
    sb.put("H", 5)
    sb.put("E", 10)
    sb.put("X", 7)
    sb.put("A", 8)
    sb.put("M", 9)
    sb.put("P", 10)
    sb.put("L", 11)
    sb.put("E", 12)
    
    sb.get("A") should be (Some(8))
    
    sb.delete("A")
    
    sb.get("A") should be (None)
    
  }
  
 
}