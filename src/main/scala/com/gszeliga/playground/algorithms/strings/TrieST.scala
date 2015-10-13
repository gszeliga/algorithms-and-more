package com.gszeliga.playground.algorithms.strings

import scala.annotation.tailrec

/**
 * Created by guillermo on 12/10/15.
 */
class TrieST[V] {

  case class Node(value: Option[V] = None, next: Array[Node] = new Array[Node](R))

  private val R = 256 //Alphabet size
  private var root: Option[Node] = None

  def size: Int = {

    def size(count: Int,node:Option[Node]): Int = {

      node map { n =>

        var c = count

        if(n.value.isDefined)
          c = c + 1

        n.next.map(Option(_)).filter(_.isDefined).foldLeft(c)(size)

      } getOrElse count

    }

    size(0,root)
  }


  def get(key: String): Option[V] = {
    get(root, key,0)
  }

  @tailrec
  private def get(node: Option[Node], key: String, d: Int): Option[V] = {

    node match {
      case Some(Node(v,next)) if d == key.length => v
      case Some(Node(v,next)) => get(Option(next(key(d))),key,d+1)
      case _ => None
    }
  }

  def put(key: String, value: V): Unit = {
    root = put(root, key, value,0)
  }

  private def put(node: Option[Node], key: String, value: V, d: Int): Option[Node] = {

    //If node doesn't exist
    if(node.isEmpty && d == key.length)
    {
      Some(Node(Some(value)))
    }
    else
    {
      var current = node

      //If it's the first time we reach this level then create new instance
      if(current.isEmpty)
        current = Some(Node())

      val c = key(d)
      val nextOne = current flatMap  (n => put(Option(n.next(c)),key, value, d+1))

      current flatMap { n =>
        nextOne map {nxt => n.next(c) = nxt; n;}
      }
    }

  }

}
