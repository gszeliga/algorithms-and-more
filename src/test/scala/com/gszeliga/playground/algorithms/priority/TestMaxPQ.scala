package com.gszeliga.playground.algorithms.priority

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by guillermo on 28/09/15.
 */
class TestMaxPQ extends FlatSpec with Matchers {

  "MaxPQ" should "persist single element" in {
    val pq = new MaxPQ[Int](1)

    pq.insert(250)

    pq.size shouldBe 1
    pq.delMax shouldBe Some(250)
  }

  it should "return elements in order" in {
    val pq = new MaxPQ[Int](5)

    pq.insert(250)
    pq.insert(15)
    pq.insert(27)
    pq.insert(890)
    pq.insert(3)

    pq.size shouldBe 5

    pq.delMax shouldBe Some(890)
    pq.delMax shouldBe Some(250)
    pq.delMax shouldBe Some(27)
    pq.delMax shouldBe Some(15)
    pq.delMax shouldBe Some(3)
    pq.delMax shouldBe None
  }

}
