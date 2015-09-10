name := "InfoCenter"

version := "1.0"

scalaVersion := "2.11.7"

resolvers +=
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.4"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.4"
libraryDependencies += "io.reactivex" %% "rxscala" % "0.25.0"