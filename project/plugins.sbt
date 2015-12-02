logLevel := Level.Info

resolvers += Resolver.bintrayRepo("twittercsl", "sbt-plugins")

addSbtPlugin("com.twitter" %% "scrooge-sbt-plugin" % "4.2.0-SNAPSHOT")
