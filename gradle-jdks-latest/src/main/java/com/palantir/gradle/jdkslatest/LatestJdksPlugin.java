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

package com.palantir.gradle.jdkslatest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.palantir.gradle.jdks.JdksExtension;
import com.palantir.gradle.jdks.JdksPlugin;
import com.palantir.gradle.jdks.PalantirCaPlugin;
import com.palantir.gradle.jdks.json.JdksInfoJson;
import java.io.IOException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public final class LatestJdksPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getPlugins().apply(JdksPlugin.class);
        project.getPlugins().apply(PalantirCaPlugin.class);

        JdksExtension jdksExtension = project.getExtensions().getByType(JdksExtension.class);

        jdksExtension.fromJson(deserializeLatestJdkVersions());
    }

    private JdksInfoJson deserializeLatestJdkVersions() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(
                    LatestJdksPlugin.class.getClassLoader().getResourceAsStream("latestjdks/latest-gradle-jdks.json"),
                    new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Could not deserialize list of versions to use for JDKs", e);
        }
    }
}
