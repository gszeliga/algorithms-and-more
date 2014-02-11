package com.gszeliga.playground.algorithms.fundamentals

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gzeliga.playground.algorithms.fundamentals.Queue

@RunWith(classOf[JUnitRunner])
class TestQueue extends FlatSpec with Matchers {

  behavior of "Queue"

  "If I enqueue some elements then" must "be retrieved in FIFO flavor" in {

    val q1 = Queue.empty[Int]

    val q2 = q1.enqueue(1)
    val q3 = q2.enqueue(3)

    val (v1, q4) = q3.dequeue
    
    v1 should be(Some(1))
    
    val (v2, q5) = q4.dequeue
    
    v2 should be(Some(3))
    
    val (v3, q6) = q5.dequeue
    
    v3 should be(None)
    
  }

}