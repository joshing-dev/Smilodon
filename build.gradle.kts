// TODO: Remove once KTIJ-19369 is fixed
@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.multiplatform) apply false
  alias(libs.plugins.kotlin.serialization) apply false
  alias(libs.plugins.detekt) apply true
}

subprojects {
  apply(plugin = rootProject.libs.plugins.detekt.get().pluginId)

  configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
    // configure
    detekt {
      toolVersion = rootProject.libs.plugins.detekt.get().version.toString()
      config = files("${rootProject.projectDir}/detekt-config.yml")
      baseline = file("${rootProject.projectDir}/detekt-baseline.xml")
      source = files(
        "src/main/java",
        "src/test/java",
        "src/main/kotlin",
        "src/test/kotlin",
        "src/androidMain/kotlin",
        "src/androidUnitTest/kotlin",
        "src/commonMain/kotlin",
        "src/commonTest/kotlin",
        "src/iosMain/kotlin",
        "src/iosTest/kotlin"
      )
    }

    tasks.named("detekt").configure {
      reports {
        xml.required.set(false)
        txt.required.set(false)
        html.required.set(true)
        html.outputLocation.set(file("${rootProject.projectDir}/reports/detekt.html"))
      }
    }
  }

  dependencies {
    detektPlugins(rootProject.libs.detekt.formatting)
  }
}
