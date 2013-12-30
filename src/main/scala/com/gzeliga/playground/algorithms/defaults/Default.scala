package com.gzeliga.playground.algorithms.defaults

trait Default[A] {
  def value: A
}

object Default{

  def withValue[A](a: A) = new Default[A] {
    def value = a
  }
  
}