import com.android.build.api.dsl.LibraryExtension
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.tasks.AbstractPublishToMaven
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.withType

class SharedPublishPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("maven-publish")
                apply("com.vanniktech.maven.publish")
            }

            fun getVersionName(): String {
                return try {
                    val describe = providers.exec {
                        commandLine("git", "describe", "--tags", "HEAD")
                    }.standardOutput.asText.get().trim()

                    if ("-" in describe) {
                        "$describe-SNAPSHOT"
                    } else {
                        describe
                    }
                } catch (_: Exception) {
                    "1.0.0"
                }
            }

            extensions.configure<LibraryExtension> {
                compileSdk = 37

                defaultConfig {
                    minSdk = 24
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildTypes {
                    release {
                        isMinifyEnabled = false
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }

            afterEvaluate {
                val artifactId = extra.get("ARTIFACT_ID") as String
                val descriptionExtra = extra.get("DESCRIPTION") as String

                val printVersion = tasks.register("printVersion") {
                    doLast {
                        val groupId = "com.progressive.kherkin"
                        logger.lifecycle("Publishing artifact: $groupId:$artifactId:${getVersionName()}")
                    }
                }

                tasks.withType<AbstractPublishToMaven>().configureEach {
                    dependsOn(printVersion)
                }

                extensions.configure<MavenPublishBaseExtension> {
                    publishToMavenCentral(true)
                    signAllPublications()

                    coordinates("com.progressive.kherkin", artifactId, getVersionName())

                    pom {
                        name.set(artifactId)
                        description.set(descriptionExtra)
                        inceptionYear.set("2024")
                        url.set("https://github.com/Progressive-Insurance/kherkin")
                        licenses {
                            license {
                                name.set("MIT License")
                                url.set("http://www.opensource.org/licenses/mit-license.php")
                            }
                        }
                        developers {
                            developer {
                                id.set("smugleafdev")
                                name.set("Matthew Rockwell")
                                url.set("https://github.com/smugleafdev")
                            }
                        }
                        scm {
                            url.set("http://github.com/Progressive-Insurance/kherkin")
                            connection.set("scm:git:https://github.com/Progressive-Insurance/kherkin.git")
                            developerConnection.set("scm:git:ssh://github.com/Progressive-Insurance/kherkin.git")
                        }
                    }
                }
            }
        }
    }
}