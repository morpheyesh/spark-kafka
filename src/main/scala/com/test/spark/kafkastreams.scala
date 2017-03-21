
package com.test.spark

import java.util
import java.util.Properties

import com.test.spark.Serde.ProtoDeserializer
import entity.Messages.EntityEvent
import org.apache.kafka.common.serialization._
import org.apache.kafka.streams._
import org.apache.kafka.streams.kstream.{KStream, KStreamBuilder}
import org.apache.spark.SparkConf

//Note:this will contain the key for log compaction(ex: account ID )
case class MetaKey(manifest: String, metadata: String) //metadata is just for example

object KafkaStreams {
import Serde._

  def runStreamJob() = {
    val builder: KStreamBuilder = new KStreamBuilder


    val streamingConfig = {
      val settings = new Properties
      settings.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-spark-example")
      settings.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
      // Specify default (de)serializers for record keys and for record values.
      settings.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.ByteArray.getClass.getName)
      settings.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
      settings
    }

    val out = builder.stream(new ProtoSerde[MetaKey], new ProtoSerde[EntityEvent], "accounts")
    out.print()
    //perform all operations on it.

    out.to("account/001")

    val stream: KafkaStreams = new KafkaStreams(builder, streamingConfig)
    stream.start()
  }
}


