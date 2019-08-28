name := "Sbtmkdirs"
version := "0.1"
scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

// see https://tpolecat.github.io/2017/04/25/scalac-flags.html for scalacOptions descriptions
scalacOptions ++= Seq(
    "-deprecation",     //emit warning and location for usages of deprecated APIs
    "-unchecked",       //enable additional warnings where generated code depends on assumptions
    "-explaintypes",    //explain type errors in more detail
    "-Ywarn-dead-code", //warn when dead code is identified
    "-Xfatal-warnings"  //fail the compilation if there are any warnings
)


