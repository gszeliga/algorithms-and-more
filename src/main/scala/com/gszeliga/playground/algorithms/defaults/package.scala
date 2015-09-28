package com.gszeliga.playground.algorithms

package object defaults {

  implicit val forBoolean = Default withValue false
  implicit val forChar = Default withValue ' '
  implicit def forNumeric[A](implicit n: Numeric[A]) = Default withValue n.zero
  implicit val forString = Default withValue ""
  implicit def forOption[A] = Default withValue (None: Option[A])  
  //implicit def forAnyRef[A](implicit evidence: Null <:< A) = Default withValue (null: A)
  
  def defaultOf[T](implicit d: Default[T]): T = d.value
  
}