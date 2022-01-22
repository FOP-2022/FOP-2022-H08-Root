dependencies {
    compileOnly("org.sourcegrade:jagr-grader-api:0.3")
    compileOnly("org.sourcegrade:jagr-launcher:0.3.0")
    compileOnly("org.ow2.asm:asm-util:9.2")
    implementation("org.mockito:mockito-core:4.2.0")
    implementation("org.powermock:powermock-api-mockito2:2.0.9")
    implementation("org.powermock:powermock-module-junit4:2.0.9")
    implementation("com.google.guava:guava:31.0.1-jre")
    implementation("fr.inria.gforge.spoon:spoon-core:10.0.0")
    implementation(project(":solution"))
}
tasks {
    create<Jar>("buildLibs") {
        group = "build"
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        val runtimeDeps = sourceSets.main.get().runtimeClasspath.mapNotNull {
            if (it.path.toLowerCase().contains("h08")) {
                null
            } else if (it.isDirectory) {
                it
            } else {
                zipTree(it)
            }
        }
        from(runtimeDeps)
        archiveFileName.set("h08-libs.jar")
    }
}
