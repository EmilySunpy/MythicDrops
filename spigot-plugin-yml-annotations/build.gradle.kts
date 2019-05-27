plugins {
    java
    `maven-publish`
}

dependencies {
    implementation(Libs.spigot_api)

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
