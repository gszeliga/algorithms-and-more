package com.gzeliga.playground.algorithms.search

import com.gzeliga.playground.algorithms.defaults._

object default {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(167); 
  def default[T] = implicitly[Default[T]].value;System.out.println("""default: [T]=> <error>""")}
}
