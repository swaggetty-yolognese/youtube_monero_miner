import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences._

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)
enablePlugins(AshScriptPlugin)

lazy val akkaHttpVersion = "10.0.10"
lazy val akkaVersion    = "2.5.4"
lazy val json4sVersion    = "3.5.3"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "yt_converter",
      scalaVersion    := "2.12.3",
      version         := "0.0.1",
      mainClass in Compile := Some("example.AppEntryPoint"),
      ScalariformKeys.preferences := scalariformPref.value
    )),
    name := "youtube_converter_api",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"               % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml"           % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"             % akkaVersion,
      "com.typesafe.akka" %% "akka-slf4j"              % akkaVersion,
      "de.heikoseeberger" %% "akka-http-json4s"        % "1.+",

      "org.json4s"        %% "json4s-native"           % json4sVersion,
      "org.json4s"        %% "json4s-ext"              % json4sVersion,

      "org.specs2"        %% "specs2-core"             % "3.8.6" % Test,
      "org.specs2"        %% "specs2-mock"             % "3.8.6" % Test,
      "org.specs2"        %% "specs2-matcher-extra"    % "3.8.6" % Test,
      "com.typesafe.akka" %% "akka-http-testkit"       % akkaHttpVersion % Test,

      "ch.qos.logback"    %  "logback-classic"         % "1.1.2",
      "com.typesafe.scala-logging" %% "scala-logging"  % "3.5.+"

    )
  )

dockerBaseImage := "c79255aa3aed"

lazy val scalariformPref = Def.setting {
  ScalariformKeys.preferences.value
    .setPreference(AlignSingleLineCaseStatements, true)
    .setPreference(DanglingCloseParenthesis, Preserve)
    .setPreference(CompactStringConcatenation, true)
}
