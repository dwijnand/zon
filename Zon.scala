import com.amazonaws.regions.Regions._
import com.amazonaws.regions.{Region, Regions}
import com.amazonaws.services.ec2.AmazonEC2AsyncClient

import scala.collection.JavaConverters._

object Zon {
  def main(args: Array[String]): Unit = {
    val regions = Seq(EU_WEST_1, US_EAST_1, US_WEST_2) map (_.toRegion())
    val numRegInstances = regions.par map ec2Count
    println(s"Total instances: ${numRegInstances.sum}")
  }

  def ec2Count(region: Region) = {
    val ec2AsyncClient = new AmazonEC2AsyncClient()
    ec2AsyncClient setRegion region

    val describeInstancesResult = ec2AsyncClient.describeInstances()
    val reservations = describeInstancesResult.getReservations.asScala
    val instances = reservations flatMap (_.getInstances.asScala)
    instances.size
  }

  implicit class RegionsW(private val r: Regions) extends AnyVal {
    def toRegion(): Region = Region getRegion r
  }
}
