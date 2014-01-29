package com.gzeliga.playground.algorithms.graph

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(99); 
  val graph = new UndirectedGraph(6);System.out.println("""graph  : com.gzeliga.playground.algorithms.graph.UndirectedGraph = """ + $show(graph ));$skip(23); 

  graph.addEdge(1, 2);$skip(22); 
  graph.addEdge(1, 3);$skip(22); 
  graph.addEdge(1, 1);$skip(22); 
  graph.addEdge(0, 3);$skip(22); 
  graph.addEdge(3, 3);$skip(21); val res$0 = 
  
 	graph.degree(1);System.out.println("""res0: Int = """ + $show(res$0));$skip(18); val res$1 = 
 	graph.degree(3);System.out.println("""res1: Int = """ + $show(res$1));$skip(19); val res$2 = 
 
	graph.avgDegree;System.out.println("""res2: Int = """ + $show(res$2));$skip(17); val res$3 = 
	graph.maxDegree;System.out.println("""res3: Int = """ + $show(res$3));$skip(26); val res$4 = 
  graph.numberOfSelfLoops;System.out.println("""res4: Int = """ + $show(res$4));$skip(11); val res$5 = 
  
  graph;System.out.println("""res5: com.gzeliga.playground.algorithms.graph.UndirectedGraph = """ + $show(res$5))}

}
