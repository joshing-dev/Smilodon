//https://youtrack.jetbrains.com/issue/KTIJ-19369/False-positive-cant-be-called-in-this-context-by-implicit-receiver-with-plugins-in-Gradle-version-catalogs-as-a-TOML-file
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.android.library)
}

kotlin {
  android()

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "shared"
    }
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        // Coroutines
        implementation(libs.kotlinx.coroutines.core)

        // Http client setup
        implementation(libs.ktor.client.core)
        implementation(libs.ktor.serialization.kotlinx.json)
        implementation(libs.ktor.client.content.negotiation)
        implementation(libs.ktor.client.logging)

        // Local settings
        implementation(libs.multiplatform.settings)

        // Logger
        implementation(libs.logger)

        // DI
        implementation(libs.koin.core)
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
//        implementation(libs.junit4)
        implementation(libs.kotlinx.coroutines.core)
        implementation(libs.kotlinx.coroutines.test)
        implementation(libs.multiplatform.settings.test)
        implementation(libs.koin.test)
        implementation(libs.turbine)
      }
    }
    val androidMain by getting {
      dependencies {
        implementation(libs.ktor.client.okhttp)
      }
    }
//    val androidUnitTest by getting
    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)
      dependencies {
        implementation(libs.ktor.client.darwin)
      }
    }
    val iosX64Test by getting
    val iosArm64Test by getting
    val iosSimulatorArm64Test by getting
    val iosTest by creating {
      dependsOn(commonTest)
      iosX64Test.dependsOn(this)
      iosArm64Test.dependsOn(this)
      iosSimulatorArm64Test.dependsOn(this)
    }
  }
}

android {
  namespace = "com.matrix159.mastadonclone.shared"
  compileSdk = 33
  defaultConfig {
    minSdk = 24
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
    }
  }
}
