package com.gszeliga.playground.algorithms.compression

import com.gszeliga.playground.algorithms.{BinaryStdIn, BinaryStdOut}
import com.gszeliga.playground.algorithms.BinaryStdOut.write
import com.gszeliga.playground.algorithms.compression.BinaryStdInStreams.bitsStream

import scala.annotation.tailrec

/**
  * Created by guillermo on 27/07/16.
  */
object Huffman {

  // alphabet size of extended ASCII
  private val R = 256

  private case class Node(ch: Char, feq: Int, left: Option[Node] = None, right: Option[Node] = None) {
    def isLeaf: Boolean = left.isEmpty && right.isEmpty
  }

  //Translates the 'trie' into a symbol table or 'codeword' table
  def buildCode(from: Option[Node]) = {

    def doBuildCode(current: Option[Node], path: String, table: Array[String]): Array[String] = {

      if(current.exists(_.isLeaf)){
        current.foreach(n => table(n.ch) = path)
        table
      }
      else{
        current match {
          case Some(Node(_,_, left, right)) => {
            val afterLeft = doBuildCode(left,path+"0",table)
            doBuildCode(right, path+"1",afterLeft)
          }
        }
      }
    }

    doBuildCode(from, "", Array.ofDim(R))

  }

  private def buildTrie(freq: Array[Int]): Option[Node] = {

    @tailrec
    def doBuild(nodes: MinPQ[Node]): Option[Node] = {
      //If still got members to merge
      if(nodes.size() > 1)
      {
        val x = nodes.delMin
        val y = nodes.delMin

        nodes.insert(Node('\0',x.feq + y.feq, Some(x),Some(y)))

        doBuild(nodes)

      }
      else{
        //If only remains one single member, we're done, we've got the root
        Option(nodes.delMin)
      }
    }

    val pq = new MinPQ[Node]()

    //Populate the priority queue with members of frequency greater than 1
    (0 to R).map(char => (char,freq.lift(char))).filter(v => v._2.exists(_ > 0)).foreach({
      case (char, Some(f)) => pq.insert(Node(char.toChar, f, None, None))
    })

    doBuild(pq)

  }

  //Writes out the trie based on 'preorder traversal' method
  def writeTrie(node: Node): Unit = {

    if(node.isLeaf){
      write(true)
      BinaryStdOut.write(node.ch)
    }
    else{
      write(false)

      //Recursively continue
      node.left.foreach(writeTrie)
      node.right.foreach(writeTrie)
    }

  }

  def readTrie(): Option[Node] = {
    //If leaf
    if(BinaryStdIn.readBoolean()){
      Some(Node(BinaryStdIn.readChar(),0, None, None))
    }
    else{
      Some(Node('\0',0,readTrie(), readTrie()))
    }
  }


  def expand() = {

    @tailrec
    def expandChar(current: Option[Node], bits: Stream[Boolean]): Option[(Char, Stream[Boolean])] = {

      if (current.exists(_.isLeaf)) current.map(n => (n.ch, bits))
      else {
        bits match {
          case h #:: t if !h => expandChar(current.flatMap(_.left), t)
          case h #:: t if h => expandChar(current.flatMap(_.right), t)
        }
      }

    }

    val root = readTrie()
    val N = BinaryStdIn.readInt()

    (0 to N).foldLeft(bitsStream) { case ((bits, _)) => {

      expandChar(root, bits) match {
        case Some((c, remaining)) => {
          write(c)
          remaining
        }
        case None => Stream.empty
      }

    }
    }

    BinaryStdOut.close()

  }

  def compress() = {
    val source = BinaryStdIn.readString
    val input = source.toCharArray

    // Tabulate frequency counts
    val freq = input.foldLeft(Array.ofDim[Int](R)){
      (f,char) => {
        f(char) = f(char) + 1
        f
      }
    }

    val root = buildTrie(freq)
    val codetable = buildCode(root)

    // Print trie for decoder
    root.foreach(writeTrie)

    // Print number of chars
    BinaryStdOut.write(input.length)

    //Compress
    input.map(_.toInt).map(codetable).foreach(_.map(_ == '1').foreach(write))

    BinaryStdOut.close()

  }


}
