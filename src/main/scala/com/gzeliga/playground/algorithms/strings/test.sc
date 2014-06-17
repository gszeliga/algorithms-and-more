package com.gzeliga.playground.algorithms.strings

import java.io.File

object test {
 
  val kv = KeyIndexCounting.loadFrom(new File(getClass().getResource("/keyindexcounting.txt").getFile()))
                                                  //> kv  : List[com.gzeliga.playground.algorithms.strings.KeyIndexEntry] = List((
                                                  //| Anderson,2), (Brown,3), (Davis,3), (Garcia,4), (Harris,1), (Jackson,3), (Joh
                                                  //| nson,4), (Jones,3), (Martin,1), (Martinez,2), (Miller,2), (Moore,1), (Robins
                                                  //| on,2), (Smith,4), (Taylor,3), (Thomas,4), (Thompson,4), (White,2), (Williams
                                                  //| ,3), (Wilson,4))
                                                   
             
                                                   
  KeyIndexCounting.order(kv, 5)                   //> res0: List[com.gzeliga.playground.algorithms.strings.KeyIndexEntry] = List((
                                                  //| Harris,1), (Martin,1), (Moore,1), (Anderson,2), (Martinez,2), (Miller,2), (R
                                                  //| obinson,2), (White,2), (Brown,3), (Davis,3), (Jackson,3), (Jones,3), (Taylor
                                                  //| ,3), (Williams,3), (Garcia,4), (Johnson,4), (Smith,4), (Thomas,4), (Thompson
                                                  //| ,4), (Wilson,4))
}