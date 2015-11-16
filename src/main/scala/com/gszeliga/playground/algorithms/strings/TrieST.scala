package com.gszeliga.playground.algorithms.strings

import scala.annotation.tailrec
import scala.collection.immutable.Queue

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
    get(root, key,0) flatMap (_.value)
  }

  @tailrec
  private def get(node: Option[Node], key: String, d: Int): Option[Node] = {

    node match {
      case n @ Some(_) if d == key.length => n
      case Some(Node(v,next)) => get(Option(next(key(d))),key,d+1)
      case _ => None
    }
  }

  def put(key: String, value: V): Unit = {
    root = put(root, key, value,0)
  }

  private def put(node: Option[Node], key: String, value: V, d: Int): Option[Node] = {

    node match {
      case None if d == key.length => Some(Node(Some(value)))
      case Some(Node(_, next)) if d == key.length => Some(Node(Some(value),next))
      case _ => {

        var current = node

        //If it's the first time we reach this level then create new instance
        if(current.isEmpty)
          current = Some(Node())

        current flatMap {n =>
          val currentChar = key(d)

          //Move to next character among key
          put(Option(n.next(currentChar)),key, value, d+1) map {nextOne =>

            //Assign new node returned by recursion downwards recursion
            n.next(currentChar) = nextOne
            n
          }
        }
      }
    }
  }

  def keysWithPrefix(prefix: String): Seq[String] = {
    collect(get(root,prefix,0),prefix,Queue.empty[String])
  }

  def keys = keysWithPrefix("")

  def collect(node: Option[Node], pre: String, queue: Queue[String]): Queue[String] = {

    if(node.isEmpty) queue
    else
    {
      //Add current 'pre' string only when we reached a complete word or term
      val nq = node.flatMap(_.value.map(_ => queue :+ pre)).getOrElse(queue)

      //for each member of the alphabet from current level, we keep looking for other words downwards
      (0 until R).foldLeft(nq){(q,chr) => {
        collect(node flatMap(n => Option(n.next(chr))),pre + chr.toChar,q)
      }}
    }
  }

  def longestPrefixOf(prefix: String): String =
  {
    @tailrec
    def search(node: Option[Node],s: String, d: Int, length: Int): Int = {

      node match {
        case None => length
        case Some(Node(Some(_),_)) if d == s.length => d
        case Some(Node(value,next)) => {
          var new_length=length

          if(value.isDefined)
            new_length = d //Assign current level depth

          search(Option(next(s(d))),s,d+1,new_length)

        }
      }
    }

    prefix.substring(0, search(root, prefix,0,0))

  }

  def delete(key: String): Unit ={
    root = delete(root, key,0)
  }

  private def delete(node: Option[Node], key: String, d: Int): Option[Node] = {
    node match {
      case Some(Node(_,next)) if d == key.length => {
        //If still have any nodes below me then just remove value from current node
        if(next.map(Option(_)).exists(_.isDefined))
          Some(Node(None,next))
        else
        //Otherwise just get rid of current node
          None
      }
      case Some(Node(value,next)) => {

        val character = key(d)
        next(character) = delete(Option(next(character)), key, d + 1).orNull

        //If still have an associated value or any child nodes
        if(value.isDefined || next.map(Option(_)).exists(_.isDefined)) Some(Node(value,next))
        else None
      }
      case _ => None
    }

  }

}
