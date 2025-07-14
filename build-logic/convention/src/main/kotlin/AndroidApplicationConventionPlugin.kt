/*
 * Copyright 2022 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import com.android.build.api.dsl.ApplicationExtension
import com.chrispassold.gradle.convention.configureKotlinAndroid
import com.chrispassold.gradle.convention.extensions.libs
import com.chrispassold.gradle.convention.extensions.pluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.pluginId("android-application"))

            apply(plugin = libs.pluginId("ksp"))

            dependencies {
                "implementation"(libs.findLibrary("kotlinx.datetime").get())
                "implementation"(libs.findLibrary("timber").get())
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = ProjectConfig.appId
                    targetSdk = ProjectConfig.targetSdk
                    versionCode = ProjectConfig.versionCode
                    versionName = ProjectConfig.versionName
                }

                buildTypes {
                    debug {
                        isMinifyEnabled = false
                        applicationIdSuffix = ".debug"
                        isDebuggable = true
                    }
                    release {
                        isMinifyEnabled = false
                        isDebuggable = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro",
                        )
                    }
                }

                configureKotlinAndroid(this)
                testOptions.animationsDisabled = true
            }
        }
    }
}
