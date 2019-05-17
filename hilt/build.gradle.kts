plugins {
    kotlin("jvm")
    `maven-publish`
}

dependencies {
    implementation(Libs.spigot_api)
    implementation(Libs.kotlin_stdlib_jdk8)

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

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}