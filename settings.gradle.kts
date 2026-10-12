pluginManagement {
    includeBuild("build-logic")
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