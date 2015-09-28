package com.gszeliga.playground.algorithms.strings

import com.gszeliga.playground.algorithms.fundamentals.Bag
import com.gszeliga.playground.algorithms.graph.{DirectedDFS, DirectedGraph}

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
  //We are basically defining the 'red-edges' of the graph!!
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

          //Link current ')' to next member of the alphabet
          (g.addEdge(i,i+1), stack2)
        }
        else {

          //Update last seen operator (bubble up hierarchy)
          lp = operator
          //Link current ')' to next member of the alphabet
          (g.addEdge(i,i+1),stack)
        }
      }
      case '*' => (g.addEdge(i,i+1),ops) //Link current '*' to next member of the alphabet
      case _ => (g,ops)
    }

    //If we're still one step before the end and the next character is 'star', then create
    //and evaluation loop (red edges)
    if(i < (M - 1) && re(i+1) == '*')
    {
      new_G.addEdge(lp,i+1)
      new_G.addEdge(i+1,lp)
    }

    (new_G,new_stack)

  }

  }._1

  def recognizes(txt: String):Boolean = {

    //Depth-First search algorithm (we find the set of states reachable via e-transitions from state 0)
    val dfs = DirectedDFS(G,0)

    //Extract all reachable states from 0 using red-edges
    val pc = (0 to G.V).filter(v => dfs.marked(v)).foldRight(new Bag[Int]) { (v, b) =>
      b.add(v)
      b
    }

    //For each character within text
    val (reachable_states, _) = txt.toCharArray.foldLeft((pc, dfs)) { case ((reachable_s, transitions), c) => {

      //For each reachable state
      val next_states = reachable_s.values.foldLeft(new Bag[Int])((m, state) => {

        //Still within limits
        if (state < M) {
          //Does character match 're' or single character wildcard
          if (re(state) == c || re(state) == '.') {
            m.add(state+1) //more forward to the next state
          }
        }

        m
      })

      //Retrieve next reachable states from matching previous characters
      val next_dfs = DirectedDFS(G, next_states.values.toSeq:_*)

      //Extract all reachable states from matching states using red-edges
      val next_pc = (0 to G.V).filter(v => next_dfs.marked(v)).foldRight(new Bag[Int]) { (v, b) =>
        b.add(v)
        b
      }

      (next_pc, next_dfs)
    }
    }

    //Do latest states contains 'accept' state M from 're'?
    return reachable_states.values.contains(M)

  }

}


object NFA {
  def apply(regexp: String) = new NFA(regexp)
}
