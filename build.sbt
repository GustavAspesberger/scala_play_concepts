name := "scala_play_concepts"

version := "1.0"

lazy val `scala_play_concepts` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  jdbc,
  ehcache,
  ws,
  specs2 % Test,
  guice,
  "org.postgresql" % "postgresql" % "42.2.1",
  "org.playframework.anorm" %% "anorm" % "2.6.2"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
