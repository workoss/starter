import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin


plugins {
    id("com.workoss.starter.plugin.starter-common-plugin")
}



dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.21.0")
}


project.plugins.apply(SpotlessPlugin::class.java)

project.plugins.getPlugin(SpotlessPlugin::class.java).apply {
    val rootPath = project.rootDir.absolutePath
    val extension = project.extensions.getByType(SpotlessExtension::class.java)
    extension.ratchetFrom("origin/develop")
    extension.java {
        importOrder()
        googleJavaFormat().aosp().reflowLongStrings().reorderImports(false).groupArtifact("com.google.googlejavaformat:google-java-format")
        trimTrailingWhitespace()
        formatAnnotations()
        removeUnusedImports()
        cleanthat()
//        prettier()
//        licenseHeader("/* (C) 2023 */")
        licenseHeaderFile("${rootPath}/gradle/template/license.lic")
    }

    extension.kotlin {
        ktfmt()
        ktlint()
        diktat()
//        prettier()
//        licenseHeader("/* (C) 2023 */")
        licenseHeaderFile("${rootPath}/gradle/template/license.lic","")
    }
    extension.sql {
        target("src/main/resources/**/*.sql")
        dbeaver()
//        prettier()
        licenseHeaderFile("${rootPath}/gradle/template/license.lic","")
    }

}