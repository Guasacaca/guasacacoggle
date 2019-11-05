// shadow sbt-scalajs' crossProject and CrossType from Scala.js 0.6.x
import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

val sharedSettings = Seq(
  version := "0.1",
  scalaVersion := "2.12.6",
  libraryDependencies ++= Seq(
    "org.scalatest" %%% "scalatest" % "3.0.5" % "test"
  ),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
)

lazy val poggle =
// select supported platforms
  crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Full) // [Pure, Full, Dummy], default: CrossType.Full
    .settings(sharedSettings)
    .jsSettings(
      Seq(
        mainClass := Some("scoggle.Scoggle"),
        libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.2",
        libraryDependencies += "com.thoughtworks.binding" %%% "dom" % "latest.release"
      )
    ) // defined in sbt-scalajs-crossproject
    .jvmSettings(/* ... */)

lazy val poggleJS = poggle.js
lazy val poggleJVM = poggle.jvm