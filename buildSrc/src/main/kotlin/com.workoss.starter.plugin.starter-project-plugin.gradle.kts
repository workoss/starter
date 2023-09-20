

plugins {
    id("com.workoss.starter.plugin.starter-base")
    id("com.workoss.starter.plugin.starter-deploy-plugin")
    `java-library`
}

dependencies {

    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    // If you are using mapstruct in test code
    testAnnotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

}
