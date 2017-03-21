package com.test.spark

import java.util.concurrent.atomic.AtomicReference
import java.lang.reflect.Method
import java.util

import org.apache.kafka.common.serialization.{Deserializer, Serde, Serializer}

/**
  * Created by yeshwanth on 14/03/17.
  */

//uses java (de)serialization library
object Serde {

  class ProtoDeserializer[T >: Null <: AnyRef : Manifest] extends Deserializer[T] {


    override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = ()

    override def close(): Unit = ()

    override def deserialize(manifest: String, data: Array[Byte]): T = {
      Class.forName(manifest).getDeclaredMethod("parseFrom" ,(classOf[Array[Byte]])).invoke(null, data).asInstanceOf[T]
    }
  }

  class ProtoSerializer[T >: Null <: AnyRef : Manifest] extends Serializer[T] {


    override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = ()

    override def close(): Unit = ()

    override def serialize(manifest: String, data: T): Array[Byte] = {
      Class.forName(manifest).getDeclaredMethod("toByteArray" ,(classOf[Array[Byte]])).invoke(data).asInstanceOf[Array[Byte]]
    }
  }


  class ProtoSerde[T >: Null <: AnyRef : Manifest] extends Serde[T] {

    override def serializer(): Serializer[T] = { new ProtoSerializer[T] }
    override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = ()

    override def close(): Unit = ()

    override def deserializer(): Deserializer[T] = new ProtoDeserializer[T]

  }
}


object ProtoSerializer {

  //private val parsingMethodBindingRef = new AtomicReference[Map[Class[_], Method]](Map.empty)

  def fromBinary(b: Array[Byte], manifest: String): AnyRef = {
    Class.forName(manifest).getDeclaredMethod("parseFrom" ,(classOf[Array[Byte]])).invoke(null, b)
  }
}