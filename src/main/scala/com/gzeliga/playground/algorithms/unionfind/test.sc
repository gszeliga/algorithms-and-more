package com.gzeliga.playground.algorithms.unionfind

object test {
  
	val uf = UF.empty(10)                     //> uf  : com.gzeliga.playground.algorithms.unionfind.UF = com.gzeliga.playground
                                                  //| .algorithms.unionfind.UF@52fb2197

	val uf1 = uf.union(0, 1)                  //> uf1  : com.gzeliga.playground.algorithms.unionfind.UF = com.gzeliga.playgrou
                                                  //| nd.algorithms.unionfind.UF@5a5b14a1
  uf1.components                                  //> res0: Array[Int] = Array(1, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  
  val uf2 = uf1.union(1, 2)                       //> uf2  : com.gzeliga.playground.algorithms.unionfind.UF = com.gzeliga.playgrou
                                                  //| nd.algorithms.unionfind.UF@31e3bb9e
  uf2.components                                  //> res1: Array[Int] = Array(1, 2, 2, 3, 4, 5, 6, 7, 8, 9)
  
  val uf3 = uf2.union(3,4)                        //> uf3  : com.gzeliga.playground.algorithms.unionfind.UF = com.gzeliga.playgrou
                                                  //| nd.algorithms.unionfind.UF@5e329ba8
	 
	uf3.connected(1, 4)                       //> res2: Boolean = false
	uf3.components                            //> res3: Array[Int] = Array(1, 2, 2, 4, 4, 5, 6, 7, 8, 9)
	
	uf3.count                                 //> res4: Int = 7
	  
	val uf4 = uf3.union(2, 3)                 //> uf4  : com.gzeliga.playground.algorithms.unionfind.UF = com.gzeliga.playgrou
                                                  //| nd.algorithms.unionfind.UF@4c8962e8
	uf4.connected(1, 4)                       //> res5: Boolean = true
	uf4.components                            //> res6: Array[Int] = Array(1, 2, 4, 4, 4, 5, 6, 7, 8, 9)
	uf3.connected(1, 4)                       //> res7: Boolean = true
	uf3.components                            //> res8: Array[Int] = Array(1, 2, 4, 4, 4, 5, 6, 7, 8, 9)
	uf1.components                            //> res9: Array[Int] = Array(1, 2, 4, 4, 4, 5, 6, 7, 8, 9)
}