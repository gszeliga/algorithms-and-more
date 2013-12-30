package com.gszeliga.playground.algorithms.search

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gzeliga.playground.algorithms.search.BinarySearchDictionary

@RunWith(classOf[JUnitRunner])
class TestBinarySearch extends FlatSpec with Matchers {

  behavior of "Binary Search"

  "If I put a value then " should " have size 1" in {

    val bs = new BinarySearchDictionary[String, Int](2)

    bs.put("Guillermo", 34)

    bs.size should be(1)

  }

  "If I put two values then " should " have size 2" in {

    val bs = new BinarySearchDictionary[String, Int](2)

    bs.put("Guillermo", 34)
    bs.put("Marta", 37)

    bs.size should be(2)

  }

  "If I put three values then " should " resize to 4 and have size 3" in {

    val bs = new BinarySearchDictionary[String, Int](2)

    bs.put("Fernando", 22)
    bs.put("Marta", 37)
    bs.put("Guillermo", 34)

    bs.size should be(3)

  }

  "If I put three pairs and try to get one then " should " retrieve the the right value" in {

    val bs = new BinarySearchDictionary[String, Int](2)

    bs.put("Fernando", 22)
    bs.put("Marta", 37)
    bs.put("Guillermo", 34)

    bs.get("Marta") should be(Some(37))
    bs.get("Fernando") should be(Some(22))
    bs.get("Guillermo") should be(Some(34))

  }

  "If I put three pairs and try to get a non-existing key then " should " retrieve an empty value" in {

    val bs = new BinarySearchDictionary[String, Int](2)

    bs.put("Fernando", 22)
    bs.put("Marta", 37)
    bs.put("Guillermo", 34)

    bs.get("Ricardo") should be (None)
    
  }

  "If I put three pairs and ask for ceiling of 'Gustavo'" should " be 'Marta'" in {

    val bs = new BinarySearchDictionary[String, Int](2)

    bs.put("Fernando", 22)
    bs.put("Marta", 37)
    bs.put("Guillermo", 34)

    bs.ceiling("Gustavo") should be ("Marta")
    
  }

  "If I put three pairs and ask for floor of 'Gustavo'" should " be 'Guillermo'" in {

    val bs = new BinarySearchDictionary[String, Int](2)

    bs.put("Fernando", 22)
    bs.put("Marta", 37)
    bs.put("Guillermo", 34)

    bs.floor("Gustavo") should be ("Guillermo")
    
  }  
  
}