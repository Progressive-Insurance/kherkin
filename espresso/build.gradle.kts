import com.android.build.api.dsl.LibraryExtension

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.kherkin.publish)
}

extensions.extraProperties["ARTIFACT_ID"] = "kherkin-espresso"
extensions.extraProperties["DESCRIPTION"] = "An Android UI testing framework for XML layouts that simplifies writing UI tests"

configure<LibraryExtension> {
    namespace = "com.progressive.kherkin.espresso"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.junit)
    implementation(libs.androidx.junit)
    implementation(libs.androidx.espresso.device)
    api(libs.androidx.uiautomator)
    api(libs.espresso.core)
    api(libs.espresso.contrib)
    api(project(":common"))
}