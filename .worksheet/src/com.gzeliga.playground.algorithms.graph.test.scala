package com.gzeliga.playground.algorithms.graph

import scala.collection.BitSet

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(127); 
  val g = new UndirectedGraph(6);System.out.println("""g  : com.gzeliga.playground.algorithms.graph.UndirectedGraph = """ + $show(g ));$skip(19); 

  g.addEdge(0, 1);$skip(18); 
  g.addEdge(0, 2);$skip(18); 
  g.addEdge(0, 5);$skip(18); 
  g.addEdge(1, 2);$skip(18); 
  g.addEdge(2, 4);$skip(18); 
  g.addEdge(3, 2);$skip(18); 
  g.addEdge(3, 4);$skip(18); 
  g.addEdge(3, 5);$skip(5); val res$0 = 
	
	g;System.out.println("""res0: com.gzeliga.playground.algorithms.graph.UndirectedGraph = """ + $show(res$0));$skip(250); 
  
  def search(graph: Graph, source: Int): BitSet = {
	
    def loop(tmp: BitSet, current: Int): BitSet = {

      graph.adj(current).filterNot(tmp + current).foldLeft(tmp + current)((acc, a) => loop(acc, a))

    }

    loop(BitSet(), source)

  };System.out.println("""search: (graph: com.gzeliga.playground.algorithms.graph.Graph, source: Int)scala.collection.BitSet""");$skip(23); 

	val s = search(g, 0);System.out.println("""s  : scala.collection.BitSet = """ + $show(s ));$skip(8); val res$1 = 
	s.size;System.out.println("""res1: Int = """ + $show(res$1))}
	
}
