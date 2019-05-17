import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("com.github.johnrengelman.shadow") version Versions.com_github_johnrengelman_shadow_gradle_plugin
    id("io.pixeloutlaw.gradle.buildconfigkt") version Versions.io_pixeloutlaw_gradle_buildconfigkt_gradle_plugin
    `maven-publish`
}

dependencies {
    implementation(Libs.spigot_api)
    implementation(Libs.plugin_annotations)
    implementation(Libs.kotlin_stdlib_jdk8)

    implementation(project(":hilt"))
    implementation(project(":api"))

    kapt(Libs.plugin_annotations)

    testImplementation(Libs.spek_dsl_jvm)
    testRuntimeOnly(Libs.spek_runner_junit5)
    testRuntimeOnly(Libs.kotlin_reflect)
}

tasks.findByName("build")?.dependsOn("shadowJar")

tasks.withType<ShadowJar> {
    mergeServiceFiles()
    dependencyFilter.apply {
        include(project(":hilt"))
        include(project(":commons"))
        include(project(":api"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk7"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib-common"))
        include(dependency("org.jetbrains:annotations"))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
        create<MavenPublication>("shadow") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            project.shadow.component(this@create)
        }
    }
}