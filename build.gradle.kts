plugins {
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    id("com.workoss.starter.plugin.starter-base")
    alias(libs.plugins.changelog)
}


description = "workoss starter"
