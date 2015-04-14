package com.gzeliga.playground.algorithms.strings

import com.gzeliga.playground.algorithms.graph.DirectedGraph

import scala.collection.immutable.Stack

/**
 * Created by guillermo on 14/04/15.
 */
//Non-deterministic Finite-state automata
class NFA(val regexp: String){

  val re = regexp.toCharArray
  val M = re.length

  //for each member of the alphabet (or state if you prefer)
  //We pass as z a tuple of <Directed Graph, Operation Stack> in order to build up the NFA related to the RE
  val G = re.zipWithIndex.foldLeft((new DirectedGraph(M+1),Stack.empty[Int])){case ((g,ops),(c,i)) => {

    var lp = i //last operator

    val (new_G,new_stack) = c match {
      //Track the node where we saw the left parenthesis and add edge to next state
      case '(' => (g.addEdge(i,i+1),ops.push(i))
      //Track the node where we saw the pipe (or) but no edge is created
      case '|' => (g,ops.push(i))
      //Some operator might've ended
      case ')' => {

        //Extract previous operator
        val (operator,stack) = ops.pop2

        //Is it 'or'?
        if(re(operator) == '|')
        {
          //Extract previous operator again
          val (operator_pre_or,stack2) = stack.pop2

          //Connect states
          g.addEdge(operator_pre_or, operator+1) // {OP_PRE_OR} ==> ('|' + 1)
          g.addEdge(operator, i) // '|' ==> ')'

          //Update last seen operator (bubble up hierarchy)
          lp = operator_pre_or

          (g, stack2)
        }
        else {

          //Update last seen operator (bubble up hierarchy)
          lp = operator
          (g,stack)
        }
      }
    }

    //TODO Finish!

    (new_G,new_stack)

  }

  }._1

  def recognizes(nfa:NFA, txt: String):Boolean = ???

}


object NFA {
  def apply(regexp: String) = new NFA(regexp)
}
