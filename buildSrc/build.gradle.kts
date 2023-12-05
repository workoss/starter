import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    `kotlin-dsl`
    alias(libs.plugins.changelog)
}

repositories {
    // Use the plugin portal to apply community plugins in convention plugins.
    gradlePluginPortal()
    maven { setUrl("https://maven.aliyun.com/repository/public") }
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.23.3")
}



tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}








