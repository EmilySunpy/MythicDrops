plugins {
    kotlin("jvm")
    id("io.pixeloutlaw.gradle.buildconfigkt") version Versions.io_pixeloutlaw_gradle_buildconfigkt_gradle_plugin
    `maven-publish`
}

dependencies {
    implementation(Libs.spigot_api)
    implementation(Libs.kotlin_stdlib_jdk8)
    api(project(":commons"))

    testImplementation(Libs.spek_dsl_jvm)
    testRuntimeOnly(Libs.spek_runner_junit5)
    testRuntimeOnly(Libs.kotlin_reflect)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
    }
}