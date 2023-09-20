package com.workoss.starter.plugin

import org.gradle.api.Project

/**
 *
 *
 * @author workoss
 */

object ProjectUtils {
    fun getProjectName(project: Project): String {
        var projectName: String = project.getRootProject().getName()
        if (projectName.endsWith("-build")) {
            projectName = projectName.substring(0, projectName.length - "-build".length)
        }
        return projectName
    }

    fun isSnapshot(project: Project): Boolean {
        val projectVersion = projectVersion(project)
        return projectVersion.matches("^.*([.-]BUILD)?-SNAPSHOT$".toRegex())
    }

    fun isMilestone(project: Project): Boolean {
        val projectVersion = projectVersion(project)
        return projectVersion.matches("^.*[.-]M\\d+$".toRegex()) || projectVersion.matches("^.*[.-]RC\\d+$".toRegex())
    }

    fun isRelease(project: Project): Boolean {
        return !(isSnapshot(project) || isMilestone(project))
    }

    private fun projectVersion(project: Project): String {
        return java.lang.String.valueOf(project.getVersion())
    }
}