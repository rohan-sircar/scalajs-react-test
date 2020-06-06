// Comment to get more information during initialization
logLevel := Level.Warn

// Resolvers
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += Resolver.bintrayRepo("oyvindberg", "converter")

// for Scala.js 1.x.x
addSbtPlugin("org.scalablytyped.converter" % "sbt-converter" % "1.0.0-beta13")

// Sbt plugins

// Use Scala.js v1.x
addSbtPlugin("com.vmunier"               % "sbt-web-scalajs"           % "1.0.11")
addSbtPlugin("org.scala-js"              % "sbt-scalajs"               % "1.0.1")
// addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.18.0")
addSbtPlugin("ch.epfl.scala" % "sbt-web-scalajs-bundler" % "0.18.0")

// If you prefer using Scala.js v0.6.x, uncomment the following plugins instead:
// addSbtPlugin("com.vmunier"                  % "sbt-web-scalajs"           % "1.1.0-0.6")
// addSbtPlugin("org.scala-js"                 % "sbt-scalajs"               % "0.6.33")

addSbtPlugin("com.typesafe.play"         % "sbt-plugin"                % "2.8.2")
addSbtPlugin("org.portable-scala"        % "sbt-scalajs-crossproject"  % "1.0.0")
addSbtPlugin("com.typesafe.sbt"          % "sbt-gzip"                  % "1.0.2")
addSbtPlugin("com.typesafe.sbt"          % "sbt-digest"                % "1.1.4")
addSbtPlugin("com.typesafe.sbteclipse"   % "sbteclipse-plugin"         % "5.2.4")
