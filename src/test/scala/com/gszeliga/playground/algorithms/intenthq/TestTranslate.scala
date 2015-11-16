package com.gszeliga.playground.algorithms.intenthq

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by guillermo on 16/11/15.
 */
class TestTranslate extends FlatSpec with Matchers {

  "Translate"

  it should "match single digit key" in {

    val dict = Map(3l -> 'a')

    Translate(dict)(List(3)) shouldBe "a"

  }


  it should "return no-matching digit when no match found" in {

    val dict = Map(3l -> 'a')

    Translate(dict)(List(4)) shouldBe "4"

  }

  it should "return non-matching key after a matching one" in {

    val dict = Map(3l -> 'a',94l ->'v')

    Translate(dict)(List(9,4,7)) shouldBe "v7"

  }

  it should "match two-digit key" in {

    val dict = Map(33l -> 'a')

    Translate(dict)(List(3,3)) shouldBe "a"

  }

  it should "match consecutive two-digit key" in {

    val dict = Map(33l -> 'a')

    Translate(dict)(List(3,3,3,3)) shouldBe "aa"

  }

  it should "return no-matching digits when no match found" in {

    val dict = Map(33l -> 'a')

    Translate(dict)(List(5,9)) shouldBe "59"

  }

  it should "match keys with different size keys" in {

    val dict = Map(3l -> 'a',479l -> 'b',54l -> 'c')

    Translate(dict)(List(4,7,9,3,5,4)) shouldBe "bac"

  }

  it should "always use the longest possible key" in {

    val dict = Map(3l -> 'a', 34l -> 'b', 347l -> 'c')

    Translate(dict)(List(3,4,7)) shouldBe "c"

  }

  it should "properly map my name" in {
    val dict = Map(93l -> 'g', 4l -> 'u', 496l -> 'l',9361l->'r',49690l -> 'm')

    Translate(dict)(List(9,3,4,1,4,9,6,4,9,6,3,9,3,6,1,4,9,6,9,0,0)) shouldBe "gu1ll3rm0"

  }

}
