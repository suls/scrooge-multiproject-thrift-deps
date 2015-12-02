package main

import hello.idl._
import world.idl._

object Main extends App {
  val h = Hello(Some(World()))
  println(h)
}
