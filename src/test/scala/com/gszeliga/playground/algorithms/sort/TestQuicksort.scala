package com.gszeliga.playground.algorithms.sort

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.gzeliga.playground.algorithms.Algorutils

@RunWith(classOf[JUnitRunner])
class TestQuicksort extends FlatSpec with Matchers {

  behavior of "Quicksort"

  "Two positions within a list " must "be correctly swaped" in {

    withClue("when indexes are from smaller to bigger") {
      Algorutils.swap(List(1, 2, 3, 4, 5), 2, 4) should be(List(1, 2, 5, 4, 3))
      Algorutils.swap(List(1, 2, 3, 4, 5), 0, 3) should be(List(4, 2, 3, 1, 5))
    }
    
    withClue("also when indexes are from bigger to smaller") {
      Algorutils.swap(List(1, 2, 3, 4, 5, 6, 7, 8, 9), 8, 7) should be(List(1, 2, 3, 4, 5, 6, 7, 9, 8))
      Algorutils.swap(List(1, 2, 3, 4, 5, 6, 7, 8, 9), 4, 2) should be(List(1, 2, 5, 4, 3, 6, 7, 8, 9))
    }
  }

}