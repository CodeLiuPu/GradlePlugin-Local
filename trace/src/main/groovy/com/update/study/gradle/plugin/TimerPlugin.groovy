package com.update.study.gradle.plugin

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState

/**
 * 打印 每个模块花费的时间
 */
class TimerPlugin implements Plugin<Project>, TaskExecutionListener, BuildListener {

    private times = []
    long startTime

    @Override
    void apply(Project project) {
        project.gradle.addListener(this)
    }

    @Override
    void beforeExecute(Task task) {
        startTime = System.currentTimeMillis()
    }

    @Override
    void afterExecute(Task task, TaskState taskState) {
        long ms = System.currentTimeMillis() - startTime
        times.add([ms, task.path])
        task.project.logger.warn "${task.path} spend ${ms}ms"
    }

    @Override
    void buildFinished(BuildResult buildResult) {
        println "Task spend time:"
        for (time in times) {
            if (time[0] >= 0) {
                printf "%7sms  %s\n", time
            }
        }
    }

    @Override
    void buildStarted(Gradle gradle) {

    }

    @Override
    void settingsEvaluated(Settings settings) {

    }

    @Override
    void projectsLoaded(Gradle gradle) {

    }

    @Override
    void projectsEvaluated(Gradle gradle) {

    }


}