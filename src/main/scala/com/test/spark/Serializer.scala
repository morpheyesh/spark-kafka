package com.test.spark

import java.util.concurrent.atomic.AtomicReference

import java.lang.reflect.Method

/**
  * Created by yeshwanth on 14/03/17.
  */


object ProtoSerializer {

  private val parsingMethodBindingRef = new AtomicReference[Map[Class[_], Method]](Map.empty)

  def fromBinary(b: Array[Byte], manifest: String): AnyRef = {
    Class.forName(manifest).getDeclaredMethod("parseFrom" ,(classOf[Array[Byte]])).invoke(null, b)
  }
}