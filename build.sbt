val commonSettings = Seq(
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.11.6"
)

val appDeps = Seq(
  "org.skinny-framework" %% "skinny-json" % "1.3.20"
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
