package com.gszeliga.playground.algorithms.search

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gszeliga.playground.algorithms.search.SequentialSearchST

@RunWith(classOf[JUnitRunner])
class TestSequentialSearchST extends FlatSpec with Matchers{

  behavior of "Sequential Search Symbol Table"
  
  "If a put an element then" must "be able to retrieve it" in {
    
    val st = new SequentialSearchST[String, Int]()
    
    st.put("Guillermo", 22)
    st.put("Fernando", 12)
    st.put("Eduardo", 10)
    
    
    st.get("Fernando") should equal(Some(12))
    
  }
  
}