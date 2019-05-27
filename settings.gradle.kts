rootProject.name = "mythicdropskt"

gradle.allprojects {
    group = "io.pixeloutlaw.mythicdrops"
    version = "420.0.0-SNAPSHOT"

    repositories {
        mavenCentral()
        jcenter()
        maven {
            url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
        }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }
    }
}

include(
    "hilt",
    "commons",
    "api",
    "spigot-plugin-yml-annotations",
    "spigot-plugin-yml-compiler",
    "plugin"
)
