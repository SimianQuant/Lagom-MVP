Build Structure
===

A raw sbt build has six files:

1. A `.gitignore` file in the root folder 
1. A `build.properties` file in the project subfolder, with the sbt version
1. A [scalafmt](https://scalameta.org/scalafmt/docs/installation.html#cli) bootstrap script and configuration file in the root folder
1. A `plugins.sbt` file in the project subfolder, adding the Lagom plugin
1. A `Settings.scala` file in the project folder, holding dependency versions 
1. A `build.sbt` file in the root folder

The `.gitignore` and `build.properties` files are pretty standard for a Scala/sbt project, so they won't be covered here. Adding plugins to sbt increases the startup time, that's why it is recommended to use the command line for formatting the code. The end state of the project at this stage can be seen in the [`1.1_buildfiles`](https://github.com/SimianQuant/Lagom-MVP/tree/1.1_buildfiles) branch. 

### Adding Lagom to the build

The simplest way to add the Lagom framework to your build is to add the sbt plugin. By convention, this should be in the `plugins.sbt` file.

```scala
addSbtPlugin("com.lightbend.lagom" % "lagom-sbt-plugin" % "1.4.10")
```

### Configuring the build

It is good practice to isolate the common build settings and dependency versions in a separate file. By convention, these should be in the `Settings.scala` file. 

```scala
object Settings {
  object versions {
    val project = "0.1.0-SNAPSHOT"
    val scala: String = "2.12.8"
  }

  def commonSettings(projectName: String) = {
    import sbt.Keys._
    import sbt.librarymanagement.Configurations._
    Seq(
      name := projectName,
      organization := "com.simianquant.lagommvp",
      version := versions.project,
      scalaVersion := versions.scala,
      autoAPIMappings := true,
      scalacOptions ++= List(
        ("-Xlint:adapted-args,nullary-unit,inaccessible,nullary-override,infer-any,doc-detached,private-shadow," +
          "type-parameter-shadow,poly-implicit-overload,option-implicit,delayedinit-select,by-name-right-associative," +
          "package-object-classes,unsound-match,stars-align,constant"),
        "-Ywarn-unused:imports,patvars,privates,locals",
        "-opt:l:method",
        "-Ywarn-unused-import",
        "-deprecation",
        "-unchecked",
        "-explaintypes",
        "-encoding",
        "UTF-8",
        "-feature",
        "-Xlog-reflective-calls",
        "-Ywarn-inaccessible",
        "-Ywarn-infer-any",
        "-Ywarn-nullary-override",
        "-Ywarn-nullary-unit",
        "-Xfuture",
        "-Ywarn-dead-code",
        "-Ywarn-value-discard",
        "-Ywarn-unused-import"
      )
    )
  }

}
```

### Configuring the subprojects

The main subprojects and their dependencies are, by convention, in the `build.sbt` file. Seperating the dependency versions and common settings makes 
this file much cleaner. Since the microservice will record the user's mood, the API and implementation subproject names are prefixed with **mood**. Since this project is going to use neither Cassandra nor Kafka, they are disabled in the build to save some time at application startup. 

```scala
lazy val moodapi = project
  .in(file("moodapi"))
  .settings(Settings.commonSettings("moodapi"))
  .settings(libraryDependencies += lagomScaladslApi)

lazy val moodimpl = project
  .in(file("moodimpl"))
  .settings(Settings.commonSettings("moodimpl"))
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
```