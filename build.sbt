name := "scala-edge-detection"

version := "1.0"

scalaVersion := "2.11.6"

mainClass in (Compile, run) := Some("Main")

libraryDependencies += "com.sksamuel.scrimage" %% "scrimage-core" % "2.1.7"

libraryDependencies += "com.sksamuel.scrimage" %% "scrimage-io-extra" % "2.1.7"

libraryDependencies += "com.sksamuel.scrimage" %% "scrimage-filters" % "2.1.7"

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.102-R11"