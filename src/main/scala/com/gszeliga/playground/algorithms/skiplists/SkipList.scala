package com.gszeliga.playground.algorithms.skiplists

import com.gszeliga.playground.algorithms.skiplists.SkipList.{NEG_INF, POS_INF}

import scala.annotation.tailrec
import scala.util.Random

/**
 * Created by guillermo on 25/11/15.
 */

//http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html
sealed case class SkipListEntry[V](key: String, value: Option[V]){
  var up: Option[SkipListEntry[V]] = None
  var down: Option[SkipListEntry[V]] = None
  var left: Option[SkipListEntry[V]] = None
  var right: Option[SkipListEntry[V]] = None
}

class SkipList[V] {

  //Initialize HEAD and TAIL
  private var head: SkipListEntry[V] = new SkipListEntry(POS_INF,None)
  private var tail: SkipListEntry[V] = new SkipListEntry(NEG_INF,None)

  head.right = Some(tail)
  tail.left = Some(head)

  private var n = 0 //Number of entries in the skip list
  private var height = 0

  //Used to determine the height of a newly added entry, simulating a coin toss experiment
  private var r: () => Double  = Random.nextDouble

  private def findEntry(key: String): SkipListEntry[V] = {

    /* ------------------------------------------------
       Search RIGHT until you find a LARGER entry

       E.g.: k = 34

                 10 ---> 20 ---> 30 ---> 40
                                  ^
                                  |
                                  p must stop here
        p.right.key = 40
    ------------------------------------------------ */

    @tailrec
    def searchRight(current: SkipListEntry[V]): SkipListEntry[V] =
    {
      current.right match {
        case Some(right) if right.key == POS_INF => current
        case Some(right) if right.key.compareTo(key) <= 0 => searchRight(right)
        case None => current
      }

    }

    @tailrec
    def oneLevelDown(current: SkipListEntry[V]):SkipListEntry[V] = {

      current.down match {
        case Some(down) => oneLevelDown(searchRight(down)) // Go downwards
        case _ => current  // We reached the LOWEST level... Exit...
      }

    }

    oneLevelDown(searchRight(head))

  }

  def get(key: String): Option[V] = {

    findEntry(key) match {
      case SkipListEntry(k,v) if key == k => v
      case _ => None
    }

  }

  def put(key: String, value: V): Option[V] = {

    @tailrec
    def fistWithLinkUp(entry: SkipListEntry[V]): SkipListEntry[V] ={
      if(entry.up.isDefined) entry
      else
      {
        entry.left match {
          case Some(e) => fistWithLinkUp(e)
        }
      }
    }

    @tailrec
    def buildUpTower(coinTossValue: Double, currentLevel: Int, p: SkipListEntry[V], q: SkipListEntry[V]): Unit = {
      if(coinTossValue < 0.5){

        if(currentLevel >= height)
        {
          //TODO Create new TOP layer
        }

        //Find first element with an UP-link and make 'p' point to it
        val newp = fistWithLinkUp(p).up.get

        //New column value (see that related value is not needed)
        val e = new SkipListEntry[V](key,None)

        //Initialize links of new column value
        e.left = Some(newp)
        e.right = newp.right
        e.down = Some(q) //'q' represent one floor below the current column level

        //Initialize neighbours
        newp.right.foreach(re => re.left = Some(e))
        newp.right = Some(e)
        q.up = Some(e) //'q' represent one floor below the current column level

        buildUpTower(r(),currentLevel+1,newp,e)

      }
    }

    findEntry(key) match {

      //If we've got a match, we just replace current value with new one
      case entry @ SkipListEntry(k,v) if key == k => {

        //Replace current value
        val copy = entry.copy(value = Option(value))

        //Replace with new entry where necessary
        entry.down.foreach(e => e.up = Some(copy))
        entry.up.foreach(e => e.down = Some(copy))
        entry.left.foreach(e => e.right = Some(copy))
        entry.right.foreach(e => e.left = Some(copy))

        //Return old value
        v
      }

      //If we don't have a match, then we've got floorEntry(key)
      case p @ SkipListEntry(k,v) =>  {

        val q = new SkipListEntry[V](key,Option(value))

        /* --------------------------------------------------------------
          Insert q into the lowest level after SkipListEntry p:

                           p   put q here           p        q
                           |     |                  |        |
                           V     V                  V        V        V
          Lower level:    [ ] <------> [ ]    ==>  [ ] <--> [ ] <--> [ ]
        --------------------------------------------------------------- */

        //Connect all edges
        q.left = Some(p)
        q.right = p.right
        p.right.foreach(e => e.left = Some(q))
        p.right = Some(q)

        buildUpTower(r(),0,p,q)

        //One more element added
        n = n +1

        //No result available
        None
      }
    }
  }

}

object SkipList {

  val NEG_INF = "-oo"
  val POS_INF = "+oo"
}
