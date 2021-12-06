repositories {
  maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
  compileOnly("org.sourcegrade:jagr-grader-api:0.3-SNAPSHOT")
  compileOnly("org.sourcegrade:jagr-launcher:0.3.0-SNAPSHOT")
  compileOnly("org.ow2.asm:asm-util:9.2")
  implementation(project(":solution"))
}
