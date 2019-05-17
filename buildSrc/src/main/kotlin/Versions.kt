import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val com_diffplug_gradle_spotless_gradle_plugin: String = "3.23.0" 

    const val com_github_johnrengelman_shadow_gradle_plugin: String = "5.0.0" 

    const val com_gradle_build_scan_gradle_plugin: String = "2.2.1" // available: "2.3"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2" 

    const val io_pixeloutlaw_gradle_buildconfigkt_gradle_plugin: String = "1.0.1" 

    const val org_jetbrains_kotlin_jvm_gradle_plugin: String = "1.3.31" 

    const val org_jetbrains_kotlin: String = "1.3.31" 

    const val org_spekframework_spek2: String = "2.0.4" 

    const val plugin_annotations: String = "1.2.2-SNAPSHOT" 

    const val spigot_api: String = "1.14.1-R0.1-SNAPSHOT" 

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.4.1"

        const val currentVersion: String = "5.4.1"

        const val nightlyVersion: String = "5.5-20190516000042+0000"

        const val releaseCandidate: String = ""
    }
}
