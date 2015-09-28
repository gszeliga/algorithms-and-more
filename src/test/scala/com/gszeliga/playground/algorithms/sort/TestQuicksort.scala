package com.gszeliga.playground.algorithms.sort

import com.gszeliga.playground.algorithms.Algorutils
import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

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

  "Two arrays {2,1,6,4} and {7,5,3}" should "return {1,2,3,4,5,6,7} after execution" in {
    QuickSort.sort(List(2,1,6,4,7,5,3)) should be (List(1,2,3,4,5,6,7))
  }  

  "A single list of ints {8,2,7,3,5,3,8,1,0,1,8}" should " be ordered as {0,1,1,2,3,3,5,7,8,8,8}" in {
    QuickSort.sort(List(8,2,7,3,5,3,8,1,0,1,8)) should be (List(0,1,1,2,3,3,5,7,8,8,8))
  }

  "A single array of strings {G,U,I,L,L,E,R,M,O}" should " be ordered as {E, G, I, L, L, M, O, R, U}" in {
    QuickSort.sort(List("G","U","I","L","L","E","R","M","O")) should be (List("E", "G", "I", "L", "L", "M", "O", "R", "U"))
  }
}