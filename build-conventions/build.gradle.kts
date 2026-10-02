plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.vanniktech.maven.publish)
}

kotlin {
    jvmToolchain(17)
}

gradlePlugin {
    plugins {
        register("sharedPublish") {
            id = libs.plugins.kherkin.publish.get().pluginId
            implementationClass = "SharedPublishPlugin"
        }
    }
}