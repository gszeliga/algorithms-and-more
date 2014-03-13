package com.gzeliga.playground.algorithms.graph

import java.net.URL
import scala.io.Source

class CriticalPathMethod(from: URL) {

  val lines = Source.fromURL(from).getLines
  val N = lines.take(1).map(_.toInt).next

  val source = N * 2
  val sink = N * 2 + 1

  //Every edge has to have v_start -> v_end plus the two main edges: start and sink
  val G = lines.map(_.split("\\s+")).zipWithIndex.foldLeft(EdgeWeightedDigraph((N * 2) + 2)) {
    case (g, (line, i)) => {

      val v_start = i
      val v_end = i + N

      val duration = line(0).toDouble
      val succesors = line(1).toInt

      g.addEdge(new DirectedEdge(v_start, v_end, duration)) //s_v -> e_v
      g.addEdge(new DirectedEdge(source, v_start, 0.0)) // start -> s_v
      g.addEdge(new DirectedEdge(v_end, sink, 0.0)) // e_v -> t

      if (succesors > 0) {
        line.drop(2).map(_.toInt).foreach { successor =>
          g.addEdge(new DirectedEdge(v_end, successor, 0.0))
        }
      }

      g
    }

  }

  val lp = new AcyclicLP(G, source)

}