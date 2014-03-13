package com.gszeliga.playground.algorithms.graph

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.gzeliga.playground.algorithms.graph.CriticalPathMethod

@RunWith(classOf[JUnitRunner])
class TestCriticalPathMethod extends FlatSpec with Matchers {

  val cpm = new CriticalPathMethod(getClass().getResource("/jobsPC.txt"))

  "CPM" must "be able to provide de longest-path (critical path) in a precedence-constrained scheduling problem" in {

    cpm.lp.distTo(cpm.sink) should be(173)

  }

}