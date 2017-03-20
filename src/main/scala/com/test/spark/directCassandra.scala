package com.test.spark

import java.lang.reflect.Method
import java.util.concurrent.atomic.AtomicReference

import com.datastax.spark.connector._
//import com.test.spark.Directcassandra.SingleEvent
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import com.trueaccord.scalapb.{GeneratedMessage, GeneratedMessageCompanion}
import entityactors.Messages.EntityEvent
import protobuf.messages.{UserCreated, UserDeleted, UserUpdated}



/**
  * Created by yeshwanth on 07/03/17
  */



/*
object Directcassandra extends App {

  case class SingleEvent(event: Array[Byte], ser_manifest: String)

  val accepts = List(
    UserUpdated
  ).map(_.defaultInstance.getClass.getName)


  val conf = new SparkConf(true)
    .set("spark.cassandra.connection.host", "127.0.0.1")
    .setAppName("cassandra")
    .setMaster("local[*]")
    .set("spark.cassandra.connection.port", "9042")

  val sc = new SparkContext(conf)

  val rdd1 =
    sc.cassandraTable[SingleEvent]("akka", "messages")
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

  println(rdd1)

  sc.stop()
}

object ProtoSerializer {

  private val parsingMethodBindingRef = new AtomicReference[Map[Class[_], Method]](Map.empty)

  def fromBinary(b: Array[Byte], manifest: String): AnyRef = {
    Class.forName(manifest).getDeclaredMethod("parseFrom" ,(classOf[Array[Byte]])).invoke(null, b)
  }
}
*/
