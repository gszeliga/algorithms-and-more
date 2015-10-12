package com.gszeliga.playground.algorithms.strings

/**
 * Created by guillermo on 12/10/15.
 */
class TrieST[V] {

  class Node(var value: Option[V] = None, val next: Array[Node] = new Array[Node](R))

  private val R = 256 //Alphabet size
  private var root: Option[Node] = None;

  def get(key: String): Option[V] = ???

  private def get(node: Node, key: Int, d: Int): Option[V] = ???

  def put(key: String, value: V) = {
    root = put(root, key, value,0)
  }

  private def put(node: Option[Node], key: String, value: V, d: Int): Option[Node] = {

    //If node doesn't exist
    if(node.isEmpty && d == key.length)
    {
      Some(new Node(Some(value)))
    }
    else
    {
      var current = node

      //If it's the first time we reach this level then create new instance
      if(current.isEmpty)
        current = Some(new Node)

      val c = key(d)
      val nextOne = current flatMap  (n => put(Option(n.next(c)),key, value, d+1))

      current flatMap { n =>
         nextOne map {nxt => n.next(c) = nxt; n;}
      }
    }

  }

}
