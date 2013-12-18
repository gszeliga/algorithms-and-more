package com.gzeliga.playground.algorithms

object Algorutils {
	def less[T](a: T, b: T)(implicit cmp: Ordering[T]) = cmp.lt(a, b)
}