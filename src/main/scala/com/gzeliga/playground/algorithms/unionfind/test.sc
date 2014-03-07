package com.gzeliga.playground.algorithms.unionfind

object test {
  
	val uf = UF.empty(10)                     //> uf  : com.gzeliga.playground.algorithms.unionfind.UF = com.gzeliga.playground
                                                  //| .algorithms.unionfind.UF@7290cb03

	val uf1 = uf.union(0, 1)                  //> uf1  : com.gzeliga.playground.algorithms.unionfind.UF = com.gzeliga.playgrou
                                                  //| nd.algorithms.unionfind.UF@939b78e
  uf1.components                                  //> res0: Array[Int] = Array(1, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  
  val uf2 = uf1.union(1, 2)                       //> uf2  : com.gzeliga.playground.algorithms.unionfind.UF = com.gzeliga.playgrou
                                                  //| nd.algorithms.unionfind.UF@1cb8deef
  uf2.components                                  //> res1: Array[Int] = Array(1, 2, 2, 3, 4, 5, 6, 7, 8, 9)
  
  val uf3 = uf2.union(3,4)                        //> uf3  : com.gzeliga.playground.algorithms.unionfind.UF = com.gzeliga.playgrou
                                                  //| nd.algorithms.unionfind.UF@2d342ba4
	
	uf3.connected(1, 4)                       //> res2: Boolean = false
	uf3.components                            //> res3: Array[Int] = Array(1, 2, 2, 4, 4, 5, 6, 7, 8, 9)
	
	uf3.count                                 //> res4: Int = 7
	  
	val uf4 = uf3.union(2, 3)                 //> uf4  : com.gzeliga.playground.algorithms.unionfind.UF = com.gzeliga.playgrou
                                                  //| nd.algorithms.unionfind.UF@3c1d332b
	uf4.connected(1, 4)                       //> res5: Boolean = true
	uf4.components                            //> res6: Array[Int] = Array(1, 2, 4, 4, 4, 5, 6, 7, 8, 9)
	uf3.connected(1, 4)                       //> res7: Boolean = true
	uf3.components                            //> res8: Array[Int] = Array(1, 2, 4, 4, 4, 5, 6, 7, 8, 9)
	uf1.components                            //> res9: Array[Int] = Array(1, 2, 4, 4, 4, 5, 6, 7, 8, 9)
}