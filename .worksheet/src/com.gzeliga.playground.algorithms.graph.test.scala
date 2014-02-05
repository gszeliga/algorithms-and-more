package com.gzeliga.playground.algorithms.graph

import scala.collection.BitSet
import scala.collection.mutable.WrappedArray

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(172); 
  val g = new UndirectedGraph(6);System.out.println("""g  : com.gzeliga.playground.algorithms.graph.UndirectedGraph = """ + $show(g ));$skip(19); 

  g.addEdge(0, 1);$skip(18); 
  g.addEdge(0, 2);$skip(18); 
  g.addEdge(0, 5);$skip(18); 
  g.addEdge(1, 2);$skip(18); 
  g.addEdge(2, 4);$skip(18); 
  g.addEdge(3, 2);$skip(18); 
  g.addEdge(3, 4);$skip(18); 
  g.addEdge(3, 5);$skip(5); val res$0 = 
	
	g;System.out.println("""res0: com.gzeliga.playground.algorithms.graph.UndirectedGraph = """ + $show(res$0));$skip(43); 
   
  val dfs = new DepthFirstSearch(g, 5);System.out.println("""dfs  : com.gzeliga.playground.algorithms.graph.DepthFirstSearch = """ + $show(dfs ));$skip(13); val res$1 = 

	dfs.marked;System.out.println("""res1: scala.collection.immutable.BitSet = """ + $show(res$1));$skip(37); 

	val dfp = new DepthFirstPaths(g,1);System.out.println("""dfp  : com.gzeliga.playground.algorithms.graph.DepthFirstPaths = """ + $show(dfp ));$skip(21); val res$2 = 
	 
	dfp.hasPathTo(3);System.out.println("""res2: Boolean = """ + $show(res$2));$skip(17); val res$3 = 
	
	dfp.pathTo(4);System.out.println("""res3: Option[List[Int]] = """ + $show(res$3))}
}
