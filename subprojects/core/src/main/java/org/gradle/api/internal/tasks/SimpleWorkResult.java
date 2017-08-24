/*
 * Copyright 2011 the original author or authors.
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
package org.gradle.api.internal.tasks;

import org.gradle.api.tasks.WorkResult;
import org.gradle.util.DeprecationLogger;

public class SimpleWorkResult implements WorkResult {

    private final boolean didWork;

    public static WorkResult didWork(boolean didWork) {
        return didWork ? DID_WORK : DID_NO_WORK;
    }

    /**
     * @deprecated Use {@link WorkResult#DID_WORK} and {@link WorkResult#DID_NO_WORK} instead.
     */
    @Deprecated
    public SimpleWorkResult(boolean didWork) {
        DeprecationLogger.nagUserOfDiscontinuedApi("SimpleWorkResult type", "Please use WorkResult.DID_WORK and DID_NO_WORK instead.");
        this.didWork = didWork;
    }

    @Override
    public boolean getDidWork() {
        return didWork;
    }
}
