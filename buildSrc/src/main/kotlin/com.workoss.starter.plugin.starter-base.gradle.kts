import java.net.URI


plugins {

}


group = project.findProperty("group") as String
version = project.findProperty("version") as String


repositories {
    maven {
        name = "aliyun-snapshot"
        url = URI.create("https://packages.aliyun.com/maven/repository/2018751-snapshot-UpWoHZ/")
    }
    maven {
        name = "aliyun-release"
        url = URI.create("https://packages.aliyun.com/maven/repository/2018751-release-sb65PP/")
    }
    maven {
        name = "ossrh-snapshot"
        url = URI.create("https://s01.oss.sonatype.org/content/repositories/snapshots")
    }
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/central")
    gradlePluginPortal()
    mavenCentral()
    mavenLocal()
}



tasks.withType<Jar> {
    manifest.attributes.putIfAbsent(
        "Created-By",
        System.getProperty("java.version") + " (" + System.getProperty("java.specification.vendor") + ")"
    )
    manifest.attributes.put("Implementation-Title", project.getName());
    manifest.attributes.put("Implementation-Version", project.getVersion().toString());
    manifest.attributes.put("Automatic-Module-Name", project.getName().replace("-", "."));
    manifest.attributes.putIfAbsent("Gradle-Version", GradleVersion.current())
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
    options.setEncoding("UTF-8")
    options.setDeprecation(true)
    options.setFork(true)
//    options.compilerArgs.add("-parameters")
    options.compilerArgs = listOf("-parameters",
        "-Amapstruct.suppressGeneratorTimestamp=false",
        "-Amapstruct.suppressGeneratorVersionInfoComment=false",
        "-Amapstruct.verbose=false")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Javadoc>{
    var docOption = options as StandardJavadocDocletOptions
    docOption.addBooleanOption("html5", true)
    docOption.addStringOption("Xdoclint:none", "-quiet")
}
