package com.gszeliga.playground.algorithms

import scala.collection.mutable.ListBuffer

package object sort {

  object QuickSort {
	  
    /*
     * This function finally returns an list where left-half is all smaller than right-half is all
     * greater than the pivot
     * */
    def partition[T](values: List[T], low: Int, high: Int)(implicit cmp: Ordering[T]): (List[T], Int) = {

      val pivot = values(low)

      //Seeks for something bigger than pivot from the left
      val left = values.indexWhere(!Algorutils.less(_, pivot), low + 1)
      //Seek for something smaller than pivot from the right
      val right = high - (values.reverse indexWhere { !Algorutils.less(pivot, _) })

      if (left >= right) {
        (Algorutils.swap(values, low, right), right)
      } else {
        partition(Algorutils.swap(values, left, right), left, right)
      }
    }

    //requires time proportional to N log N on the average to sort an array of length N
    def sort[T](values: List[T])(implicit cmp: Ordering[T]): List[T] = {

      if (values.size <= 1) values
      else {
        val pivot = values(values.length / 2)

        //It uses the 3-Way partitioning: less, equal and greater
        sort(values.filter(Algorutils.less(_, pivot))) ++ 
        values.filter(pivot ==) ++ 
        sort(values.filter(Algorutils.greater(_, pivot)))

      }
    }
  }

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