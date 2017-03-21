package com.test.spark


import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming._

/**
  * Created by yeshwanth on 14/03/17.
  *
  * Helpers for context
  *
  */

object Settings {

  object SparkContext {

    def getContext(conf: SparkConf): SparkContext = {
      new SparkContext(conf)
    }

    /* def getStreamingContext(conf: SparkConf): StreamingContext = {
    new StreamingContext()

  }
*/
    def stopContext(sc: SparkContext): Unit = {
      sc.stop()
    }
  }





}


