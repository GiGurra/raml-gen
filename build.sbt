val commonSettings = Seq(
  organization := "se.gigurra",
  version := "SNAPSHOT",
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation"),
  libraryDependencies ++= Seq(
    "org.scalatest"        %%   "scalatest"             %   "2.2.4"     %   "test",
    "org.mockito"           %   "mockito-core"          %   "1.10.19"   %   "test"
  )
)

val ramlgen_core = Project(
  id = "ramlgen-core",
  base = file("ramlgen-core"),
  settings = commonSettings
)

val ramlgen_cli = Project(
  id = "ramlgen-cli",
  base = file("ramlgen-cli"),
  settings = commonSettings,
  dependencies = Seq(ramlgen_core)
)

val ramlgen_finagle = Project(
  id = "ramlgen-finagle",
  base = file("ramlgen-finagle"),
  settings = commonSettings,
  dependencies = Seq(ramlgen_core)
)

val ramlgen = Project(id = "ramlgen", base = file("."), settings = commonSettings).dependsOn(
  ramlgen_core,
  ramlgen_cli,
  ramlgen_finagle
).aggregate(
  ramlgen_core,
  ramlgen_finagle,
  ramlgen_finagle
)
