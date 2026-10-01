import com.android.build.api.dsl.LibraryExtension

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.com.android.library)
    id("Kherkin.sharedPublish")
}

extensions.extraProperties["ARTIFACT_ID"] = "kherkin-common"
extensions.extraProperties["DESCRIPTION"] = "A dependency for kherkin-espresso and kherkin-compose"

configure<LibraryExtension> {
    namespace = "com.progressive.kherkin.common"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.junit)
    implementation(libs.androidx.junit)
    implementation(libs.androidx.uiautomator)
    implementation(libs.androidx.runner)
    implementation(libs.findbugs)
    implementation(libs.javax.inject)
    api(libs.kotlin.test)
    api(libs.androidx.rules)
    api(libs.kotlin.reflect)
}