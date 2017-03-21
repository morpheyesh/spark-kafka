package com.test.spark

import org.apache.spark.{SparkConf, SparkContext}
import protobuf.messages.{UserCreated, UserDeleted, UserUpdated}
import com.datastax.spark.connector._
import com.test.spark.Settings.SparkContext



/**
  * Created by yeshwanth on 14/03/17.
  */

//represents a row in cassandra from journal and its manifest
case class SingleEvent(event: Array[Byte], ser_manifest: String)


object BatchProcessing  {

  val accepts = List(
    UserUpdated
  ).map(_.defaultInstance.getClass.getName)


  def runBatch(sc: SparkContext) = {


    val rdd =
      sc.cassandraTable[SingleEvent]("akka", "messages") //cassandra akka journal
        .select("event", "ser_manifest")
        .keyBy("persistence_id", "partition_nr").flatMap(r => accepts.contains(r._2.ser_manifest) match {
        case true => Some(r)
        case false => None
      }
      ).map(e => {
        ProtoSerializer.fromBinary(e._2.event, e._2.ser_manifest) match {
          case c: UserUpdated => 1
          case c: UserDeleted => 2
          case c: UserCreated => 3
        }
      }).reduce(_ + _)

    sc.stop()


  }


}
