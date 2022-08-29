/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.skydoves.landscapist.Configuration
import com.github.skydoves.landscapist.Dependencies
import com.github.skydoves.landscapist.Versions

plugins {
  id("com.android.library")
  id("kotlin-android")
  id("org.jetbrains.dokka")
  id("binary-compatibility-validator")
}

rootProject.extra.apply {
  set("PUBLISH_GROUP_ID", Configuration.artifactGroup)
  set("PUBLISH_ARTIFACT_ID", "landscapist-glide")
  set("PUBLISH_VERSION", rootProject.extra.get("rootVersionName"))
}

apply(from = "${rootDir}/scripts/publish-module.gradle")

android {
  compileSdk = Configuration.compileSdk
  defaultConfig {
    minSdk = Configuration.minSdk
    targetSdk = Configuration.targetSdk
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
  }

  buildFeatures {
    compose = true
  }

  packagingOptions {
    resources {
      excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions.freeCompilerArgs += listOf("-Xexplicit-api=strict")
}

dependencies {
  api(project(":landscapist"))
  api(Dependencies.glide)

  implementation(Dependencies.coreKtx)
  implementation(Dependencies.composeUI)
  implementation(Dependencies.composeRuntime)
  implementation(Dependencies.composeFoundation)

  androidTestImplementation(Dependencies.testRules)
  androidTestImplementation(Dependencies.androidTestRunner)
  androidTestImplementation(Dependencies.androidTestJunit)
  androidTestImplementation(Dependencies.composeUI)
  androidTestImplementation(Dependencies.composeJunitTest)
}
