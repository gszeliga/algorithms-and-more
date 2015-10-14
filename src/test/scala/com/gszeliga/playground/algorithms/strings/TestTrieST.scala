package com.gszeliga.playground.algorithms.strings

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by guillermo on 13/10/15.
 */
class TestTrieST extends FlatSpec with Matchers {

  behavior of "Tries"

  it should "support single element insertion" in {
    val trie = new TrieST[Int]
    trie.put("entry-1",250)
  }

  it should "support retrieve single key" in {
    val trie = new TrieST[Int]
    trie.put("entry-1",250)

    trie.keys should contain("entry-1")
  }

  it should "support fetching single element among one element trie" in {
    val trie = new TrieST[Int]

    trie.put("entry-1",250)
    trie.get("entry-1") shouldBe Some(250)
  }

  it should "support fetching single element among multiple element trie" in {
    val trie = new TrieST[Int]

    trie.put("entry-1",250)
    trie.put("some-2",11)
    trie.put("some-3",33)

    trie.get("some-3") shouldBe Some(33)
  }

  it should "support retrieving multiple keys" in {
    val trie = new TrieST[Int]

    trie.put("entry-1",250)
    trie.put("some-2",11)
    trie.put("some-3",33)

    trie.keys should contain allOf("entry-1","some-2","some-3")
  }

  it should "support retrieving multiple keys using prefix" in {
    val trie = new TrieST[Int]

    trie.put("entry-1",250)
    trie.put("some-2",11)
    trie.put("some-3",33)

    trie.keysWithPrefix("some-") should contain only("some-2","some-3")
  }


  it should "retrieve accurate size with a 3-element trie" in {
    val trie = new TrieST[Int]

    trie.put("entry-1",250)
    trie.put("some-2",11)
    trie.put("some-3",33)

    trie.size shouldBe 3
  }

  it should "retrieve accurate size with a 2-element trie" in {
    val trie = new TrieST[Int]

    trie.put("entry-1",250)
    trie.put("some-2",11)

    trie.size shouldBe 2
  }

  it should "retrieve accurate size with a 1-element trie" in {
    val trie = new TrieST[Int]

    trie.put("entry-1",250)

    trie.size shouldBe 1
  }

  it should "retrieve accurate size with an epmty trie" in {
    val trie = new TrieST[Int]

    trie.size shouldBe 0
  }

  it should "support return nothing when key does not exist among multiple element trie" in {
    val trie = new TrieST[Int]

    trie.put("entry-1",250)
    trie.put("some-2",11)
    trie.put("some-3",33)

    trie.get("some-7") shouldBe None
  }

}
