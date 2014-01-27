package com.gszeliga.playground.algorithms.search

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.Matchers
import org.scalatest.FlatSpec
import com.gzeliga.playground.algorithms.search.SeparateChainingHashST

@RunWith(classOf[JUnitRunner])
class TestSeparateChainingHashST  extends FlatSpec with Matchers{

  behavior of "SeparateChainingHashST"
  
  "If I put five elements then" must "be able to retrieve any of them by key" in {
    
    val st = new SeparateChainingHashST[String, Int](31) 
    
    
    st.put("A", 1);
    st.put("B", 2);
    st.put("C", 3);
    st.put("D", 4);
    st.put("Z", 5);
    
    st.get("D") should be (Some(4))
    
  }
  
}