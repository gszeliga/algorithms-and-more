package com.gzeliga.playground.algorithms.unionfind

import java.net.URL
import scala.annotation.tailrec

class UF(val N: Int, val components: Array[Int], val count: Int) {

  def connected(p: Int, q: Int): Boolean = find(p) == find(q)
  def find(p: Int): Int = {

    @tailrec
    def loop(c: Int): Int = {
      val tmp = components(c)
      if (c == tmp) c
      else loop(tmp)
    }

    //We loop until we find the 'root', meaning, the one where index and value are the same
    loop(p)

  }

  def union(p: Int, q: Int): UF = {

    val fp = find(p)
    val fq = find(q)

    if (fp != fq) {
      components(fp) = fq
      //WARN!! To do it completely immutable we need to clone the array, but it's not going to be space efficient
      new UF(N, components, count - 1)
    }
    else this
    
  }

}

object UF {
  
  def empty(N: Int) = {
    new UF(N, new Array[Int](N).zipWithIndex map (i => i._2), N)
  }
  
  def apply(source: URL) = {
	  //TODO
  }
}