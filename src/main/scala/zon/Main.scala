package zon

import scopt._

object Main {
  case class Config()
  object OptParser extends OptionParser[Config]("zon") {
    head("zon", "0.x") toUnit()

    help("help")       hidden() toUnit()
    version("version") hidden() toUnit()
    cmd("help")    text "displays help information"    action { (_, c) ⇒ showUsage;  c } toUnit()
    cmd("version") text "displays version information" action { (_, c) ⇒ showHeader; c } toUnit()
  }
  def main(args: Array[String]): Unit = {
    OptParser.parse(args, Config()) map { config ⇒
      println("Well done")
    } getOrElse {
      println("You done goofed")
    }
  }

  implicit class AnyWithToUnit[A](private val x: A) extends AnyVal {
    def toUnit() = ()
  }
}
