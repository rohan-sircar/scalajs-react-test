lazy val server = (project in file("server"))
  .settings(commonSettings)
  .settings(
    scalaJSProjects := Seq(client),
    pipelineStages in Assets := Seq(scalaJSPipeline),
    pipelineStages := Seq(digest, gzip),
    // triggers scalaJSPipeline when using compile or continuous compilation
    compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
    libraryDependencies ++= Seq(
      "com.vmunier" %% "scalajs-scripts" % "1.1.4",
      guice,
      specs2 % Test
    ),
    // Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
    EclipseKeys.preTasks := Seq(compile in Compile)
  )
  .enablePlugins(PlayScala)
  // .enablePlugins(SbtWeb)
  .enablePlugins(WebScalaJSBundlerPlugin)
  .dependsOn(sharedJvm)

lazy val client = (project in file("client"))
  .settings(commonSettings)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "1.0.0",
      "com.github.japgolly.scalajs-react" %%% "core" % "1.7.0",
      "com.github.japgolly.scalajs-react" %%% "extra" % "1.7.0",
      // "com.github.japgolly.scalajs-react" %%% "test" % "1.7.0",
    ),
    scalacOptions ++= Seq("-Ymacro-annotations","-deprecation"),
    useYarn := true,
    stFlavour := Flavour.Japgolly,
    Compile / npmDependencies ++= Seq(
      "react" -> "16.13.1",
      "react-dom" -> "16.13.1",
      "@types/react" -> "16.9.34",
      "@types/react-dom" -> "16.9.6"
    )
  )
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalaJSBundlerPlugin)
  .enablePlugins(ScalablyTypedConverterPlugin)
  .dependsOn(sharedJs)

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("shared"))
  .settings(commonSettings)
  .jsConfigure(_.enablePlugins(ScalaJSWeb))
lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

lazy val commonSettings = Seq(
  scalaVersion := "2.13.2",
  organization := "com.example"
)
