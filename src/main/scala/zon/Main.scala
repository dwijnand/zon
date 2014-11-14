package zon

import com.amazonaws.regions.{Region, Regions}
import com.amazonaws.services.ec2.AmazonEC2AsyncClient
import scopt._

import scala.collection.JavaConverters._

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

  def ec2List() = {
//    val awsCredentialsProvider: AWSCredentialsProvider = new DefaultAWSCredentialsProviderChain()
//    val awsCredentials = awsCredentialsProvider.getCredentials

    val ec2AsyncClient = new AmazonEC2AsyncClient()
    val regionToInstances = (Seq(Regions.EU_WEST_1, Regions.US_EAST_1, Regions.US_WEST_2)
      map (_.toRegion())
      map (r ⇒ ec2List1(ec2AsyncClient, r))
    )
    val allInstances = (regionToInstances map (_._2)).toVector.flatten
    println(s"Total instances: ${allInstances.size}")
  }

  def ec2List1(ec2AsyncClient: AmazonEC2AsyncClient, region: Region) = {
    println(s"Listing region: $region")
    ec2AsyncClient setRegion region

    val describeInstancesResult = ec2AsyncClient.describeInstances()

    val reservations = describeInstancesResult.getReservations.asScala.toVector
    println(s"${reservations.size} reservations found")
    val instances = reservations flatMap (_.getInstances.asScala)
    println(s"${instances.size} instances found")

    (region, instances)
  }

  implicit class RegionsW(private val r: Regions) extends AnyVal {
    def toRegion(): Region = Region getRegion r
  }

  implicit class AnyWithToUnit[A](private val x: A) extends AnyVal {
    def toUnit() = ()
  }
}
