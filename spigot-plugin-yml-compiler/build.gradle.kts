plugins {
    kotlin("jvm")
    kotlin("kapt")
    `maven-publish`
}

dependencies {
    implementation(Libs.spigot_api)
    implementation(Libs.kotlin_stdlib_jdk8)
    implementation(project(":spigot-plugin-yml-annotations"))
    implementation(Libs.auto_service)

    kapt(Libs.auto_service)

    testImplementation(Libs.junit_jupiter_api)
    testImplementation(Libs.truth)
    testRuntimeOnly(Libs.junit_jupiter_engine)
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
