package com.gszeliga.playground.algorithms.strings

import scala.io.Source
import java.io.File

class KeyIndexEntry(val name: String, val key: Int) {
  override def toString = s"($name,$key)"
}

object KeyIndexCounting {

  def loadFrom(source: File): List[KeyIndexEntry] = {

    Source.fromFile(source).getLines.map { l =>
      val parts = l.split(",")

      new KeyIndexEntry(parts(0), parts(1).toInt)

    }.toList

  }

  def order(entries: List[KeyIndexEntry], sections: Int): List[KeyIndexEntry] = {

    //R = sections. Also frequency[0] must always be zero
    val frequency = entries.foldLeft(new Array[Int](sections+1)) { (a, v) =>
      a(v.key+1) = a(v.key+1) + 1
      a
    }

    val indices = (0 until sections).foldLeft(frequency) { (f, v) =>
      f(v + 1) = f(v + 1) + f(v)
      f
    }

    entries.foldLeft(new Array[KeyIndexEntry](entries.size)) { (a, v) =>
      //assign element to result
      a(indices(v.key)) = v
      //increment position within the same key section
      indices(v.key) = indices(v.key) + 1
      a
    }.toList

  }

}