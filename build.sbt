lazy val moodapi = project
  .in(file("moodapi"))
  .settings(Settings.commonSettings("moodapi"))
  .settings(libraryDependencies += lagomScaladslApi)

lazy val moodimpl = project
  .in(file("moodimpl"))
  .settings(Settings.commonSettings("moodimpl"))
  .settings(libraryDependencies += "com.softwaremill.macwire" %% "macros" % Settings.versions.macwire % "provided")
  .dependsOn(moodapi)
  .enablePlugins(LagomScala)

lazy val frontend = project
  .in(file("frontend"))
  .settings(Settings.commonSettings("frontend"))
  .dependsOn(moodapi)
  .enablePlugins(PlayScala, LagomPlay)

lazy val root = project
  .in(file("."))
  .settings(Settings.commonSettings("root"))

lagomKafkaEnabled in ThisBuild := false
lagomCassandraEnabled in ThisBuild := false
