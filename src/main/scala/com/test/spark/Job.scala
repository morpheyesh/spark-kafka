package com.test.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by yeshwanth on 21/03/17.
  */
class Job {

//Run batch first and then start KStream job(s)
  def startApp() = {

    val conf = new SparkConf().setAppName("PrimaryBatchJob")

    val sc = new SparkContext(conf)

    /**
      * Represents batch job from cassandra
      */
    startBatchJob(sc)

    /**
      * This would ideally start bunch of stream jobs
      * depending on entities.
      */
    startKafkaStream()

  }

  def startBatchJob(sc: SparkContext): Unit = {
    BatchProcessing.runBatch(sc)
  }

  def startKafkaStream(): Unit = {
    KafkaStreams.runStreamJob()
  }


}
