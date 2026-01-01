/*
 * (c) Copyright 2022 Palantir Technologies Inc. All rights reserved.
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

package com.palantir.gradle.jdkslastest;

import com.palantir.gradle.testing.execution.GradleInvoker;
import com.palantir.gradle.testing.junit.DisabledConfigurationCache;
import com.palantir.gradle.testing.junit.GradlePluginTests;
import com.palantir.gradle.testing.project.RootProject;
import org.junit.jupiter.api.Test;

@GradlePluginTests
@DisabledConfigurationCache
final class LatestJdksPluginIntegrationTest {
    @Test
    void can_apply_plugin(GradleInvoker gradle, RootProject rootProject) {
        rootProject.buildGradle().plugins().add("com.palantir.jdks.latest");
        rootProject.buildGradle().plugins().add("java-library");

        rootProject.buildGradle().append("""
            javaVersions {
                libraryTarget = 17
            }
            """);

        rootProject.mainSourceSet().java().writeClass("""
            public class HelloWorld {
                public static void main(String[] args) {
                    System.out.println("Hello World");
                }
            }
            """);

        gradle.withArgs("compileJava").buildsSuccessfully();

        // The test passes if the build succeeds
    }
}
