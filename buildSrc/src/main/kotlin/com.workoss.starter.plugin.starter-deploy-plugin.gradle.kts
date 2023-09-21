import com.workoss.starter.plugin.ProjectUtils
import java.net.URI

plugins {
    id("com.workoss.starter.plugin.starter-base")
    `maven-publish`
    signing
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            project.plugins.withType(JavaPlugin::class.java){
                from(components["java"])
            }
            project.plugins.withType(JavaPlatformPlugin::class.java){
                from(components["javaPlatform"])
            }

            project.plugins.withType(VersionCatalogPlugin::class.java){
                from(components["versionCatalog"])
            }

            pom {
                url.set("https://github.com/workoss/starter")
                name.set(project.provider(project::getName))
                description.set(project.provider(project::getDescription))
                organization {
                    name.set("Workoss, Inc.")
                    url.set("https://www.workoss.com")
                }
                licenses {
                    license {
                        name.set("Apache License Version 2.0")
                        url.set("https://www.apache.com/license/LICENSE-2.0")
                    }
                }
                developers {
                    developer {
                        name.set("workoss")
                        email.set("workoss@icloud.com")
                        roles.set(listOf("develop"))
                    }
                }

                scm {
                    url.set("https://github.com/workoss/starter.git")
                    developerConnection.set("scm:git:https://github.com/workoss/starter.git")
                    connection.set("scm:git:https://github.com/workoss/starter.git")
                }

                issueManagement {
                    system.set("GitHub")
                    url.set("https://github.com/workoss/starter/issues")
                }
            }
        }

        repositories {
            maven {
                name = "ossrh"
                url = URI.create(if (ProjectUtils.isRelease(project)) "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/" else "https://s01.oss.sonatype.org/content/repositories/snapshots/")
                credentials {
                    username = project.findProperty("OSSRH_USERNAME") as String
                    password = project.findProperty("OSSRH_PASSWORD") as String
                }
            }

            maven {
                name = "aliyun"
                url =
                    URI.create(if (ProjectUtils.isRelease(project)) "https://packages.aliyun.com/maven/repository/2018751-release-sb65PP/" else "https://packages.aliyun.com/maven/repository/2018751-snapshot-UpWoHZ/")
                credentials {
                    username = project.findProperty("ALIYUNMVN_USERNAME") as String
                    password = project.findProperty("ALIYUNMVN_PASSWORD") as String
                }

            }
        }

    }
}

signing {
    val signingKeyId = project.findProperty("signingKeyId") as String?
    val signingKey = project.findProperty("signingKey") as String?
    val signingPassword = project.findProperty("signingPassword") as String?
    if (signingKeyId != null && signingKey!=null){
        signing.useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword);
    }else if (signingKeyId ==null && signingKey !=null){
        signing.useInMemoryPgpKeys(signingKey, signingPassword);
    }else {
        useGpgCmd()
    }
    sign(publishing.publications["mavenJava"])
}


task("finalizeDeployArtifacts") {
    if (ProjectUtils.isRelease(project) && project.hasProperty("ossrh_username")) {
        val closeAndReleaseOssrhStagingRepository = project.tasks.findByName("closeAndReleaseOssrhStagingRepository")
        dependsOn(closeAndReleaseOssrhStagingRepository)
    }
}

