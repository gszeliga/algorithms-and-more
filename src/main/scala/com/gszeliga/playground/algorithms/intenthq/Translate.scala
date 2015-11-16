package com.gszeliga.playground.algorithms.intenthq

/**
 * Created by guillermo on 16/11/15.
 */
object Translate {

  def apply(dictionary: Map[Long, Char])(source: List[Int]): String = {

    def bestMatch(tail: List[Int],partial: Long, bestSoFar: Option[(Long,Char)]):Option[(Long,Char)] = {
      tail match {
        case h :: t => {
          val key = partial * 10 + h
          bestMatch(t,key,dictionary.get(key).map((key,_)).orElse(bestSoFar))
        }
        case _ => bestSoFar
      }
    }

    def steps(key: Long): Int = key.toString.length //Ugly but works

    def doTranslate(tail: List[Int], tmp: String): String = {
      tail match{
        case h :: t => {
          bestMatch(t, h, dictionary.get(h).map((h.toLong,_))) match {
            case Some((k,c)) => doTranslate(tail.drop(steps(k)), tmp + c)
            case _ => doTranslate(t,tmp + h.toString)
          }
        }
        case Nil => tmp
      }
    }

    doTranslate(source, "")

  }
}
