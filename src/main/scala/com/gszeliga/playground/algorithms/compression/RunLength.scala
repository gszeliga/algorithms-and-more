package com.gszeliga.playground.algorithms.compression

import com.gszeliga.playground.algorithms.BinaryStdIn.{readBoolean, readChar}
import com.gszeliga.playground.algorithms.compression.BinaryStdInStreams.{bitsStream, charsStream}
import com.gszeliga.playground.algorithms.{BinaryStdIn, BinaryStdOut}

/**
  * Created by guillermo on 20/07/16.
  */

/*
* sbt --error "run-main com.gszeliga.playground.algorithms.compression.RunLengthEncoding - " < src/test/scala/com/gszeliga/playground/algorithms/compression/4runs.bin
*
* sbt --error "run-main com.gszeliga.playground.algorithms.compression.RunLengthEncoding - " < src/test/scala/com/gszeliga/playground/algorithms/compression/4runs.bin | sbt --error "run-main com.gszeliga.playground.algorithms.HexDump"
*
* */

object RunLength {

  def main(args: Array[String]) {
    if (args(0).equals("-")) compress()
    if (args(0).equals("+")) expand()
  }

  def expand() = {

    val zero = false

    charsStream.foldLeft(zero) { (bit, char) => {

      //Write-out 'bit' char amount of times
      (0 to char).foreach(_ => BinaryStdOut.write(bit))

      //Invert bit
      !bit
    }
    }

    //flush and close
    BinaryStdOut.close()

  }

  def compress() = {

    val zero = 0

    val (_, remaining) = bitsStream.foldLeft((false, zero)) { case ((bit, count), value) =>

      //Changes bit?
      if (bit != value) {
        //flush
        BinaryStdOut.write(count)
        //reset
        (!bit, 1)
      }
      else {
        //reach maximum? (byte)
        if (count == 255) {
          //flush
          BinaryStdOut.write(count)
          //write-out a zero count
          BinaryStdOut.write(zero)
          //reset
          (bit, 1)
        }
        else {
          //increment and continue
          (bit, count + 1)
        }
      }
    }

    //write remaining count
    BinaryStdOut.write(remaining)

    //flush and close
    BinaryStdOut.close()

  }

}
