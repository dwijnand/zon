package zon

import scopt._

object Main {
  sealed trait Cmd
  case object Noop extends Cmd
  case object Ec2 {
    case object List extends Cmd
  }
  case class Config(cmd: Cmd)
  object Config {
    val StartConfig = Config(cmd = Noop)
  }
  object OptParser extends OptionParser[Config]("zon") {
    head("zon", "0.x") toUnit()

    help("help")       hidden() toUnit()
    version("version") hidden() toUnit()
    cmd("help")    text "displays help information"    action { (_, c) ⇒ showUsage;  c } toUnit()
    cmd("version") text "displays version information" action { (_, c) ⇒ showHeader; c } toUnit()

    cmd("ec2") action ((_, c) ⇒ c) children(
      cmd("list") action ((_, c) ⇒ c copy (cmd = Ec2.List))
    ) toUnit()
  }

  def main(args: Array[String]): Unit = {
    OptParser.parse(args, Config.StartConfig) map { config ⇒
      config.cmd match {
        case Ec2.List ⇒ ec2List()
        case Noop     ⇒ ()
      }
    } getOrElse (())
  }

  def ec2List(): Unit = {
    println("ec2 instances")
  }

  implicit class AnyWithToUnit[A](private val x: A) extends AnyVal {
    def toUnit() = ()
  }
}
