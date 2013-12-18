package com.gzeliga.playground.algorithms

import scala.collection.mutable.ListBuffer

package object sort {
  object MergeSort {

    def merge[T](elems: List[T], low: Int, mid: Int, high: Int)(implicit cmp: Ordering[T]) = {

      var i = low
      var j = mid + 1

      val aux = elems map (e => e)

      val tmp = new ListBuffer[T]()

      for (k <- low until high) {

        if (i > mid) { //If left hand exhausted
          tmp += aux(j)
          j += 1
        } else if (j > high) { //If right hand exhausted
          tmp += aux(i)
          i += 1
        } else if (Algorutils.less(aux(j), aux(i))) {
          tmp += aux(j)
          j += 1
        } else {
          tmp += aux(i)
          i += 1
        }

      }

      tmp.toList

    }

  }
}