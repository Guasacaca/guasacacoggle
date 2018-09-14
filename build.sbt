name := "poogle"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test",
  "org.scala-js" %%% "scalajs-dom" % "0.9.2"
)

enablePlugins(ScalaJSPlugin)


scalaJSUseMainModuleInitializer := true
mainClass := Some("scoggle.Scoggle")