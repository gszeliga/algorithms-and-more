package com.gzeliga.playground.algorithms.fundamentals

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(96); 
  val bag = new Bag[Int]();System.out.println("""bag  : com.gzeliga.playground.algorithms.fundamentals.Bag[Int] = """ + $show(bag ));$skip(64); 
                                                  
  bag.add(1);$skip(13); 
  bag.add(2);$skip(13); 
  bag.add(3);$skip(13); 
  bag.add(4);$skip(35); 
  
  
  bag.values foreach println;$skip(12); val res$0 = 

  bag.size;System.out.println("""res0: Int = """ + $show(res$0));$skip(14); val res$1 = 
  bag.isEmpty;System.out.println("""res1: Boolean = """ + $show(res$1))}
}
