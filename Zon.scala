import com.amazonaws.regions.{Region, Regions}
import com.amazonaws.services.ec2.AmazonEC2AsyncClient

import scala.collection.JavaConverters._

object Zon {
  def main(args: Array[String]): Unit = {
    val ec2AsyncClient = new AmazonEC2AsyncClient()
    val instancesSizes = (Seq(Regions.EU_WEST_1, Regions.US_EAST_1, Regions.US_WEST_2)
      map (_.toRegion())
      map (r ⇒ ec2Count1(ec2AsyncClient, r))
    )
    println(s"Total instances: ${instancesSizes.sum}")
  }

  def ec2Count1(ec2AsyncClient: AmazonEC2AsyncClient, region: Region) = {
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
