
name := "TutorialSam"
scalaVersion := "3.1.0"
Compile/scalaSource := baseDirectory.value / "src"
scalacOptions ++= Seq("-feature", "-language:implicitConversions", "-noindent", "-deprecation", "-encoding", "UTF-8")
