plugins {
    `java-library`
    id("com.workoss.starter.plugin.starter-deploy-plugin")
}


dependencies {
    api(project(":domain-starter"))
}

