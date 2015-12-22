val commonSettings = Seq(
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.11.6"
)


val slickVersion = "3.1.1"
val playSlickVersion = "1.1.1"

val appDeps = Seq(
  "org.skinny-framework" %% "skinny-json" % "1.3.20",
  "com.typesafe.play" %% "play-slick" % playSlickVersion,
  "com.typesafe.play" %% "play-slick-evolutions" % playSlickVersion,
//  "mysql" % "mysql-connector-java" % "5.1.36",
  "org.mariadb.jdbc" % "mariadb-java-client" % "1.3.3",
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.slick" %% "slick-codegen" % slickVersion,
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.1.0"
)

val stressTestDeps = Seq (
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.7" % Test,
  "io.gatling" % "gatling-test-framework" % "2.1.7" % Test
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
  .settings( commonSettings ++ Seq(
    name := """play-bookshelf""",
    libraryDependencies ++= appDeps,
    routesGenerator := InjectedRoutesGenerator
  ))

lazy val stressTest = (project in file("stress-test")).enablePlugins(GatlingPlugin)
  .settings( commonSettings ++ Seq(
    name := "stress-test",
    libraryDependencies ++= stressTestDeps
  ))
