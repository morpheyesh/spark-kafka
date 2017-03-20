addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.2")

addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.6" exclude ("com.trueaccord.scalapb", "protoc-bridge_2.10"))

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")

libraryDependencies += "com.trueaccord.scalapb" %% "compilerplugin-shaded" % "0.6.0-pre1"
