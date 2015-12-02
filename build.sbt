organization := "demo"

name := "scrooge-multiproject-thrift-deps"

version in ThisBuild := "1.0"

scalaVersion in ThisBuild := "2.11.7"

lazy val `scrooge-thrift-deps` =
  project
    .in(file("."))
    .aggregate(core)

lazy val core = Project(
  id = "core",
  base = file("core"),
  settings =  Defaults.coreDefaultSettings
).settings(
  scroogeThriftSources in Compile :=
    (internalDependencyClasspath in Compile).value.files.flatMap(f=> (f * "*.thrift").get),
  scroogeThriftIncludes in Compile :=
    (internalDependencyClasspath in Compile).value.files,
    libraryDependencies ++= Seq(
      "org.apache.thrift" % "libthrift" % "0.8.0",
      "com.twitter" %% "scrooge-core" % "4.2.0",
      "com.twitter" %% "finagle-thrift" % "6.28.0"
    )
).dependsOn(`hello-idl`)

lazy val idlSettings = Seq(
  unmanagedResourceDirectories in Compile +=
    (baseDirectory in Scope.ThisScope).value / "src" / "main"/ "thrift"
)

lazy val `world-idl` = Project(
  id = "world-idl",
  base = file("world-idl")
).settings(idlSettings)
  .disablePlugins(ScroogeSBT)

lazy val `hello-idl`  = Project(
  id = "hello-idl",
  base = file("hello-idl")
).settings(idlSettings)
  .dependsOn(`world-idl`)
  .disablePlugins(ScroogeSBT)

