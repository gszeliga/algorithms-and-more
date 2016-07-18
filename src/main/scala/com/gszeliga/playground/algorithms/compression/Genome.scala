package com.gszeliga.playground.algorithms.compression

import com.gszeliga.playground.algorithms.{Alphabet, BinaryStdIn, BinaryStdOut}

/**
  * Created by guillermo on 18/07/16.
  */

/*
*  How to run this stuff
*
*   sbt "run-main com.gszeliga.playground.algorithms.compression.Genome - " < src/test/scala/com/gszeliga/playground/algorithms/compression/genomeTiny.txt
*
*  How to visualize output in binary:
*
*   sbt --error "run-main com.gszeliga.playground.algorithms.compression.Genome - " < src/test/scala/com/gszeliga/playground/algorithms/compression/genomeTiny.txt | sbt "run-main com.gszeliga.playground.algorithms.BinaryDump 64"

*
* */

object Genome {

  private val DNA = new Alphabet("ACTG")
  private val relevantBits = DNA.lgR()

  def main(args: Array[String]) {
    if (args(0).equals("-")) compress()
    if (args(0).equals("+")) expand()

  }

  def compress() = {
    val string = BinaryStdIn.readString()
    val N = string.length

    //Writes out the length of the string first
    BinaryStdOut.write(N)

    //Then proceed with a 2-bit encoding (here DNA.lgR() will be 2,
    string.map(DNA.toIndex).foreach(char => BinaryStdOut.write(char, relevantBits))

    BinaryStdOut.close()

  }

  def expand() = {
    //Retrieve string length
     val N = BinaryStdIn.readInt

    (0 to N).map(_ => BinaryStdIn.readChar(relevantBits)).foreach(index => BinaryStdOut.write(DNA.toChar(index)))

    BinaryStdOut.close()

  }

}
