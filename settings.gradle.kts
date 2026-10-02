pluginManagement {
    includeBuild("build-conventions")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name = "Kherkin"

include(":sampleapp")
include(":compose")
include(":espresso")
include(":common")