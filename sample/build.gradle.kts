import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

/*
 * Copyright 2018 Manuel Wrage
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

plugins {
    id("com.android.application")
    kotlin("android")
}

apply(from = "https://raw.githubusercontent.com/IVIanuu/gradle-scripts/master/android-build-app.gradle")
apply(from = "https://raw.githubusercontent.com/IVIanuu/gradle-scripts/master/kt-compiler-args.gradle")
apply(from = "https://raw.githubusercontent.com/IVIanuu/gradle-scripts/master/kt-source-sets-android.gradle")
// todo remove once fixed
apply(from = "https://raw.githubusercontent.com/IVIanuu/gradle-scripts/master/coroutines-fix.gradle")

dependencies {
    implementation(Deps.androidxAppCompat)
    implementation(project(":ksettings"))
    implementation(project(":ksettings-coroutines"))
    implementation(project(":ksettings-livedata"))
    implementation(project(":ksettings-rx"))
}