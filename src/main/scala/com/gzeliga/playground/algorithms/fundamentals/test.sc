package com.gzeliga.playground.algorithms.fundamentals

object test {
  val bag = new Bag[Int]()                        //> bag  : com.gzeliga.playground.algorithms.fundamentals.Bag[Int] = com.gzeliga.
                                                  //| playground.algorithms.fundamentals.Bag@35ab28fe
                                                  
  bag.add(1)
  bag.add(2)
  bag.add(3)
  bag.add(4)
  
  
  bag.values foreach println                      //> 4
                                                  //| 3
                                                  //| 2
                                                  //| 1

  bag.size                                        //> res0: Int = 4
  bag.isEmpty                                     //> res1: Boolean = false
}