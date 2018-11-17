import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.apache.tools.ant.filters.ReplaceTokens
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("com.github.johnrengelman.shadow") version "4.0.1"
}

dependencies {
    implementation(Libs.spigot_api)
    implementation(Libs.plugin_annotations)
    implementation(Libs.org_jetbrains_kotlin_kotlin_stdlib_jdk8)
    implementation(project(":hilt"))

    kapt(Libs.plugin_annotations)
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

tasks.withType(ShadowJar::class) {
    classifier = ""
    dependencyFilter.apply {
        include(project(":hilt"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk7"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib-common"))
        include(dependency("org.jetbrains:annotations"))
    }
}

tasks.findByName("build")?.dependsOn("shadowJar")