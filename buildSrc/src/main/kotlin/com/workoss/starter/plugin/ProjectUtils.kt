/*
 * Copyright 2023 Workoss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.workoss.starter.plugin

import org.gradle.api.Project

/**
 * 项目工具类
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