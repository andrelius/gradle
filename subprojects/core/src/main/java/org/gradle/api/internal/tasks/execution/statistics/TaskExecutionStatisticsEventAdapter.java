/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.internal.tasks.execution.statistics;

import org.gradle.BuildAdapter;
import org.gradle.BuildListener;
import org.gradle.BuildResult;
import org.gradle.api.Task;
import org.gradle.api.execution.TaskExecutionListener;
import org.gradle.api.internal.tasks.TaskStateInternal;
import org.gradle.api.invocation.Gradle;
import org.gradle.api.tasks.TaskState;

public class TaskExecutionStatisticsEventAdapter extends BuildAdapter implements BuildListener, TaskExecutionListener {
    private final TaskExecutionStatisticsListener listener;
    private int executedTasksCount;
    private int avoidedTasksCount;
    private Gradle gradle;

    public TaskExecutionStatisticsEventAdapter(TaskExecutionStatisticsListener listener) {
        this.listener = listener;
    }

    @Override
    public void buildStarted(Gradle gradle) {
        if (gradle.getParent() == null) {
            this.gradle = gradle;
        }
    }

    @Override
    public void buildFinished(BuildResult result) {
        if (result.getGradle() == gradle) {
            gradle = null;
            listener.buildFinished(new TaskExecutionStatistics(executedTasksCount, avoidedTasksCount));
        }
    }

    @Override
    public void beforeExecute(Task task) {
        // do nothing
    }

    @Override
    public void afterExecute(Task task, TaskState state) {
        if (task.getProject().getGradle() == gradle) {
            TaskStateInternal stateInternal = (TaskStateInternal) state;
            if (stateInternal.isAvoided()) {
                avoidedTasksCount++;
            } else if (stateInternal.isActionsWereExecuted()) {
                executedTasksCount++;
            }
        }
    }
}
