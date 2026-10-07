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

extensions.extraProperties["ARTIFACT_ID"] = "kherkin-compose"
extensions.extraProperties["DESCRIPTION"] = "An Android UI testing framework for Jetpack Compose screens that simplifies writing UI tests"

configure<LibraryExtension> {
    namespace = "com.progressive.kherkin.compose"
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.javax.inject)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    api(libs.compose.test)
    api(libs.compose.testing)
    api(project(":common"))
}