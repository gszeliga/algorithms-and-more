package com.gzeliga.playground.algorithms.graph

import java.io.File

class DegreesOfSeparation(private val graph_source: File, private val delimiter: String, private val source_label: String) {

  private val fsg = new FileSymbolGraph(graph_source, delimiter)
  private val bfs = fsg.index(source_label) map { i => new BreadthFirstSearch(fsg.G, i) }

  def degreesTo(name: String): Option[Stream[String]] = {

    bfs flatMap { s =>
      fsg.index(name) flatMap { i =>
        s.pathTo(i) map { str => str map { i => fsg.name(i).get }
        }
      }
    }

  }

}