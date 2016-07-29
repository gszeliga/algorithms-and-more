package com.gszeliga.playground.algorithms.compression

import com.gszeliga.playground.algorithms.BinaryStdIn
import com.gszeliga.playground.algorithms.BinaryStdIn._

/**
  * Created by guillermo on 27/07/16.
  */
object BinaryStdInStreams {

  private def binaryStdInStream[T](next: () => T) = {

    def doRead(): Stream[T] = {
      if (BinaryStdIn.isEmpty) {
        BinaryStdIn.close()
        Stream.empty
      }
      else {
        next() #:: doRead()
      }
    }

    doRead()

  }

  def charsStream = binaryStdInStream(readChar)
  def bitsStream = binaryStdInStream(readBoolean)

}
