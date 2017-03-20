import sbt.Keys.resolvers

organization  := ""

version       := "0.1"

scalaVersion  := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")




resolvers ++= Seq(
Resolver.bintrayRepo("scalaz", "releases")

)


scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-target:jvm-1.8",
  "-unchecked",
  "-Ywarn-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Xlint"
)


resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases"
resolvers += "krasserm at bintray" at "http://dl.bintray.com/krasserm/maven"
resolvers += "logger" at "https://mvnrepository.com/artifact"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.1" % "provided",
  "org.apache.spark" %% "spark-sql" % "1.6.1" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.6.1" % "provided",
  "com.typesafe.akka" %% "akka-actor" % "2.4.16" % "provided",
  //"org.apache.spark"  %% "spark-streaming-akka" % "2.0.0-SNAPSHOT",
  "org.apache.bahir" % "spark-streaming-akka_2.11" % "2.1.0",
  "org.apache.spark"  %% "spark-streaming" % "1.6.1",
  "org.apache.kafka" % "kafka-streams" % "0.10.0.0",

  "com.datastax.spark" %% "spark-cassandra-connector" % "1.5.0",

  "com.github.krasserm" %% "akka-analytics-cassandra" % "0.3.1",
  "com.github.krasserm" %% "akka-analytics-kafka" % "0.3.1",

  /* "com.github.acrisci" %% "commander" % "0.1.0" excludeAll (
     ExclusionRule(organization = "org.scalatest")
     ),*/
  //"org.apache.spark" % "spark-streaming-kafka-0-8_2.11" % "2.0.0-preview",
  "com.jsuereth" %% "scala-arm" % "2.0",
  "com.trueaccord.scalapb" %% "scalapb-runtime" % com.trueaccord.scalapb.compiler.Version.scalapbVersion,
  "com.trueaccord.scalapb" %% "scalapb-runtime" % com.trueaccord.scalapb.compiler.Version.scalapbVersion % "protobuf",

"org.scalatest" %% "scalatest" % "3.0.1" % "test"
)




PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)


Revolver.settings

assemblyJarName in assembly := s"""${artifact.value.name}_${scalaBinaryVersion.value}-${version.value}-assembly.jar"""


assemblyMergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case "log4j.properties"                                  => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf"                                    => MergeStrategy.concat
  case _                                                   => MergeStrategy.first
}

