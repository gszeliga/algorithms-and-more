package com.gszeliga.playground.algorithms.sort

import org.scalatest._
import com.gszeliga.playground.algorithms.sort._
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class TestMergeSort extends FlatSpec with Matchers {

  behavior of "Merge Sort"

  "Two ordered arrays {1,2,4,6} and {3,5,7}" should "return {1,2,3,4,5,6,7} after merging" in {
    MergeSort.merge(List(1, 2, 4, 6, 3, 5, 7), 0, 3, 6) should be (List(1,2,3,4,5,6,7))
  }

  "A single array of ints {8,2,7,3,5,3,8,1,0,1,8}" should " be ordered as {0,1,1,2,3,3,5,7,8,8,8}" in {
    MergeSort.sort(List(8,2,7,3,5,3,8,1,0,1,8), 0, 10) should be (List(0,1,1,2,3,3,5,7,8,8,8))
  }

  "A single array of strings {G,U,I,L,L,E,R,M,O}" should " be ordered as {E, G, I, L, L, M, O, R, U}" in {
    MergeSort.sort(List("G","U","I","L","L","E","R","M","O"), 0, 8) should be (List("E", "G", "I", "L", "L", "M", "O", "R", "U"))
  }  
  
}