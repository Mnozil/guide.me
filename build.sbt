import play.Project._

name := "hello-play-java"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.2.2",
  "org.webjars.bower" % "bootstrap" % "3.3.4",
  "org.webjars.bower" % "angular" % "1.4.0-rc.2",
  "org.webjars.bower" % "angular-ui-router" % "0.2.14",
  "org.webjars.bower" % "js-data-angular" % "2.4.0")

libraryDependencies ++= Seq(
  javaJdbc,
  "org.xerial" % "sqlite-jdbc" % "3.8.6",
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  "org.hibernate" % "hibernate-entitymanager" % "4.3.9.Final" // replace by your jpa implementation
)


playJavaSettings
