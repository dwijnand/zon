name := "zon"
version := "0.1-SNAPSHOT"

description := "Zon: A scala tool for interacting with AmaZON's AWS"
homepage := Some(url("https://github.com/dwijnand/zon"))
startYear := Some(2014)
licenses := Seq("Apache License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

scalaVersion := "2.11.4"
crossScalaVersions := Seq(scalaVersion.value)
scalacOptions ++= Seq("-encoding", "utf8")
scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")
scalacOptions  += "-Xfatal-warnings"
scalacOptions  += "-Xfuture"
scalacOptions  += "-Yinline-warnings"
scalacOptions  += "-Yno-adapted-args"
scalacOptions  += "-Ywarn-dead-code" // WARN: Too many ???s cause false positives!
scalacOptions  += "-Ywarn-numeric-widen"
scalacOptions  += "-Ywarn-unused-import" // 2.11 only
scalacOptions  += "-Ywarn-value-discard"

wartremoverErrors ++= Warts.all

libraryDependencies += "com.amazonaws"        % "aws-java-sdk"   % "1.9.4"
libraryDependencies += "com.martiansoftware"  % "nailgun-server" % "0.9.1"
libraryDependencies += "org.scalaz"          %% "scalaz-core"    % "7.1.0"
libraryDependencies += "com.github.scopt"    %% "scopt"          % "3.2.0"
libraryDependencies += "org.specs2"          %% "specs2-core"    % "2.4.11" % "test"

watchSources ++= (baseDirectory.value * "*.sbt").get
watchSources ++= (baseDirectory.value / "project" * "*.scala").get
