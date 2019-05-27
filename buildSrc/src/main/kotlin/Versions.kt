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

    const val com_google_dagger: String = "2.22.1" 

    const val truth: String = "0.44" 

    const val com_gradle_build_scan_gradle_plugin: String = "2.2.1" // available: "2.3"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2" 

    const val io_gitlab_arturbosch_detekt: String = "1.0.0-RC14" 

    const val io_pixeloutlaw_gradle_buildconfigkt_gradle_plugin: String = "1.0.1" 

    const val st4: String = "4.1" 

    const val org_jetbrains_kotlin_jvm_gradle_plugin: String = "1.3.31" 

    const val org_jetbrains_kotlin: String = "1.3.31" 

    const val org_junit_jupiter: String = "5.3.1" // available: "5.4.2"

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

        const val nightlyVersion: String = "5.6-20190519000207+0000"

        const val releaseCandidate: String = ""
    }
}
