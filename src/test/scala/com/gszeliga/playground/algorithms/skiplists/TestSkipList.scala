package com.gszeliga.playground.algorithms.skiplists

import org.scalatest.{Matchers, FlatSpec, FunSuite}

/**
 * Created by guillermo on 16/12/15.
 */
class TestSkipList extends FlatSpec with Matchers {

  behavior of "SkipList"

  it should "allow to put a single element" in {
    val list: SkipList[Int] = SkipList()

    list.put("V1",1)

    list.size shouldBe 1
  }

  it should "allow to retrieve a single element after putting it" in {

    val list: SkipList[Int] = SkipList()

    list.put("V",25)

    list.get("V") shouldBe Some(25)

  }

  it should "allow to replace an already existing entry with a new value" in {

    val list: SkipList[Int] = SkipList()

    list.put("V",25)
    list.put("V",3)

    list.get("V") shouldBe Some(3)
  }

  it should "return oldest value when an existing entry value gets replaced" in {

    val list: SkipList[Int] = SkipList()

    list.put("V",25)
    list.put("V",3) shouldBe Some(25)
  }

  it should "allow to put two elements" in {

    val list: SkipList[Int] = SkipList()

    list.put("v1",2)
    list.put("v3",37)

    list.size shouldBe 2
    list.get("v1") shouldBe Some(2)
    list.get("v3") shouldBe Some(37)

  }

}
