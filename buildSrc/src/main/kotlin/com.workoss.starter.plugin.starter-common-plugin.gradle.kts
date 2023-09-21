

plugins {
    id("com.workoss.starter.plugin.starter-base")
    id("com.workoss.starter.plugin.starter-deploy-plugin")
    `java-library`
}

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
}
