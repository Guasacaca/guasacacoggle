// shadow sbt-scalajs' crossProject and CrossType from Scala.js 0.6.x
import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

val sharedSettings = Seq(
  version := "0.1",
  scalaVersion := "2.12.6",
  libraryDependencies ++= Seq(
    "org.scalatest" %%% "scalatest" % "3.0.5" % "test",
  )
)

lazy val poggle =
// select supported platforms
  crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Full) // [Pure, Full, Dummy], default: CrossType.Full
    .settings(sharedSettings)
    .jsSettings(
      Seq(
        mainClass := Some("scoggle.Scoggle"),
        scalaJSUseMainModuleInitializer := true,
        libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.2"
      )
    ) // defined in sbt-scalajs-crossproject
    .jvmSettings(/* ... */)

lazy val poggleJS = poggle.js
lazy val poggleJVM = poggle.jvm