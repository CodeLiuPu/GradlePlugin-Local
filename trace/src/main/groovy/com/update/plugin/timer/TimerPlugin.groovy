package com.update.plugin.timer

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 打印 每个模块花费的时间
 */
class TimerPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.gradle.addListener(new TimerListener())
    }

}