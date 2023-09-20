plugins {
    `java-platform`
    id("com.workoss.starter.plugin.starter-deploy-plugin")
}

javaPlatform {
    allowDependencies()
}

var buildFiles = fileTree(rootDir) {
    val excludes = gradle.startParameter.projectProperties["excludeProjects"]?.split(",")
    include("**/*.gradle.kts")
    exclude(
        "build",
        "**/gradle",
        "settings.gradle.kts",
        "buildSrc",
        "/build.gradle.kts",
        ".",
        "out"
    )
    if (!excludes.isNullOrEmpty()) {
        exclude(excludes)
    }
}

dependencies {
    // This platform extends the Spring Boot platform.
//    api(platform("org.springframework.boot:spring-boot-dependencies:2.7.6"))

    constraints {
        // Provide an opinion about which version of the Spring Boot Gradle plugin clients
        // should use since it's not included in the standard spring-boot-dependencies BOM.
        buildFiles.forEach {
            val isDefaultName = it.name == "build.gradle" || it.name == "build.gradle.kts"
            if (isDefaultName) {
                val buildFilePath = it.parentFile.absolutePath
                val projectName = buildFilePath.replace(rootDir.absolutePath, "").replace(File.separator, "")
                if (!projectName.contains("-bom")){
                    api(project(":$projectName"))
                }
            } else {
                val projectName = it.name.replace(".gradle.kts", "").replace(".gradle", "")
                if (!projectName.contains("-bom")){
                    api(project(":$projectName"))
                }
            }
        }
    }

}