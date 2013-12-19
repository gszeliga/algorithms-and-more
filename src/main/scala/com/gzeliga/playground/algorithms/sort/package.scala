package com.gzeliga.playground.algorithms

import scala.collection.mutable.ListBuffer

package object sort {
  object MergeSort {

    def merge[T](elems: List[T], low: Int, mid: Int, high: Int)(implicit cmp: Ordering[T]) = {

      var i = low
      var j = mid + 1

      val tmp = new ListBuffer[T]()

      //TODO We can skip the whole thing by checking that elems(mid) <= elems(mid + 1)
      
      for (k <- low until (high + 1)) {

        if (i > mid) { //If left hand exhausted
          tmp += elems(j)
          j += 1
        } else if (j > high) { //If right hand exhausted
          tmp += elems(i)
          i += 1
        } else if (Algorutils.less(elems(j), elems(i))) {
          tmp += elems(j)
          j += 1
        } else {
          tmp += elems(i)
          i += 1
        }

      }

      tmp.toList

    }

    // Time= N log N
    // This is a BOTTOM-TO-TOP implementation
    /*The primary drawback of mergesort is that it requires extra space proportional to N, for the auxiliary array for merging*/

    def sort[T](elems: List[T], low: Int, high: Int)(implicit cmp: Ordering[T]) = {

      def doSort(slice: List[T], lw: Int, hg: Int): List[T] = {
        if (hg <= lw) slice
        else {
          val mid = lw + (hg - lw) / 2
          val slices = slice.splitAt(mid + 1)

          merge(doSort(slices._1, 0, mid) ++ doSort(slices._2, 0, hg - (mid + 1)), lw, mid, hg)
        }
      }

      doSort(elems, low, high)

    }

  }
}