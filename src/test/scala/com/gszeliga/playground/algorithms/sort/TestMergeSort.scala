package com.gszeliga.playground.algorithms.sort

import org.scalatest._
import com.gzeliga.playground.algorithms.sort._

class TestMergeSort extends FlatSpec with Matchers {

  behavior of "Merge Sort"

  "Two ordered arrays {1,2,4,6} and {3,5,7}" should "return {1,2,3,4,5,6,7} after merging" in {
    MergeSort.merge(List(1, 2, 4, 6, 3, 5, 7), 0, 3, 7) should be (List(1,2,3,4,5,6,7))
  }

}