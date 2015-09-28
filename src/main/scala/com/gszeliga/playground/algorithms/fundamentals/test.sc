package com.gszeliga.playground.algorithms.fundamentals

object test {
  val bag = new Bag[Int]()                        //> bag  : com.gzeliga.playground.algorithms.fundamentals.Bag[Int] = com.gzeliga.
                                                  //| playground.algorithms.fundamentals.Bag@15fd309

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

  val q = Queue.empty[Int]                        //> q  : com.gzeliga.playground.algorithms.fundamentals.Queue[Int] = com.gzeliga
                                                  //| .playground.algorithms.fundamentals.Queue$EmptyQueue$@232c5bfc
  val q2 = q.enqueue(1)                           //> q2  : com.gzeliga.playground.algorithms.fundamentals.Queue[Int] = com.gzelig
                                                  //| a.playground.algorithms.fundamentals.Queue$$anon$1@787e4345

  val q3 = q2.enqueue(3)                          //> q3  : com.gzeliga.playground.algorithms.fundamentals.Queue[Int] = com.gzelig
                                                  //| a.playground.algorithms.fundamentals.Queue$$anon$1@2972a4d0

  q3.values.toList                                //> res2: List[Int] = List(1, 3)

  val (v1, q4) = q3.dequeue                       //> v1  : Option[Int] = Some(1)
                                                  //| q4  : com.gzeliga.playground.algorithms.fundamentals.Queue[Int] = com.gzelig
                                                  //| a.playground.algorithms.fundamentals.Queue$$anon$1@53628ee
  val (v2, q5) = q4.dequeue                       //> v2  : Option[Int] = Some(3)
                                                  //| q5  : com.gzeliga.playground.algorithms.fundamentals.Queue[Int] = com.gzelig
                                                  //| a.playground.algorithms.fundamentals.Queue$$anon$1@26b923ee

	Queue(3,2,1).values.toList                //> res3: List[Int] = List(3, 2, 1)

}