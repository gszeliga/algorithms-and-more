package com.gszeliga.playground.algorithms.string

import com.gszeliga.playground.algorithms.strings.NFA
import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by guillermo on 20/04/15.
 */
class TestNFA extends FlatSpec with Matchers {

  behavior of "Non-deterministic Finite-state automata (or Regular Expressions)"

  "Expression '(A*B|AC)D'" should "match a valid alphabetic expression" in {
    val nfa = NFA("(A*B|AC)D")

    nfa.recognizes("AAAABD") should be(true)
  }

  "Expression '(A*B|AC)D'" should "not match an invalid alphabetic expression" in {
    val nfa = NFA("(A*B|AC)D")

    nfa.recognizes("AAAABE") should be(false)
  }

  "Expression '(a|(bc)*d)*'" should "match a valid alphabetic expression" in {
    val nfa = NFA("(a|(bc)*d)*")

    nfa.recognizes("abcbcd") should be(true)
  }

  "Expression '(a|(bc)*d)*'" should "not match an invalid alphabetic expression" in {
    val nfa = NFA("(a|(bc)*d)*")

    nfa.recognizes("abcbcbcdaaaabcbcdaaaddd") should be(false)
  }

}
