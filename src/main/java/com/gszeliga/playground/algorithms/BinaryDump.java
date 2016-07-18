package com.gszeliga.playground.algorithms;

/**
 * Created by guillermo on 18/07/16.
 */

/*
* How to run this shit:
*
*   sbt "run-main com.gszeliga.playground.algorithms.BinaryDump 64" < src/test/scala/com/gszeliga/playground/algorithms/compression/genomeTiny.txt
*
* */
public class BinaryDump {

    // Do not instantiate.
    private BinaryDump() { }

    /**
     * Reads in a sequence of bytes from standard input and writes
     * them to standard output in binary, k bits per line,
     * where k is given as a command-line integer (defaults
     * to 16 if no integer is specified); also writes the number
     * of bits.
     */
    public static void main(String[] args) {
        int bitsPerLine = 16;
        if (args.length == 1) {
            bitsPerLine = Integer.parseInt(args[0]);
        }

        int count;
        for (count = 0; !BinaryStdIn.isEmpty(); count++) {
            if (bitsPerLine == 0) {
                BinaryStdIn.readBoolean();
                continue;
            }
            else if (count != 0 && count % bitsPerLine == 0) StdOut.println();
            if (BinaryStdIn.readBoolean()) StdOut.print(1);
            else                           StdOut.print(0);
        }
        if (bitsPerLine != 0) StdOut.println();
        StdOut.println(count + " bits");
    }
}

