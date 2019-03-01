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
