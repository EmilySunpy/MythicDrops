import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import io.gitlab.arturbosch.detekt.DetektPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    base
    `build-scan`
    kotlin("jvm") version Versions.org_jetbrains_kotlin_jvm_gradle_plugin apply false
    id("com.diffplug.gradle.spotless") version Versions.com_diffplug_gradle_spotless_gradle_plugin apply false
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
    id("io.gitlab.arturbosch.detekt") version Versions.io_gitlab_arturbosch_detekt_gradle_plugin apply false
}

subprojects {
    pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
        this@subprojects.pluginManager.apply(DetektPlugin::class.java)
        this@subprojects.pluginManager.apply(SpotlessPlugin::class.java)
        this@subprojects.configure<SpotlessExtension> {
            kotlin {
                target("src/**/*.kt")
                ktlint()
            }
        }
        this@subprojects.tasks.withType<KotlinCompile> {
            dependsOn("spotlessKotlinApply")
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform {
            includeEngines("spek2")
        }
    }
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"

    publishAlways()
}