package com.gszeliga.playground.algorithms.graph

import java.io.File

object test_degrees {
  val dos = new DegreesOfSeparation(new File(getClass().getResource("/movies.txt").getFile()), "/", "Bacon, Kevin")

}