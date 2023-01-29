plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization") version "1.8.0"
  id("com.android.library")
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
    val ktorVersion = "2.2.2"
    val kotlinxCoroutinesTest = "1.6.4"
    val commonMain by getting {
      dependencies {
        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

        // Http client setup
        implementation("io.ktor:ktor-client-core:$ktorVersion")
        implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
        implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
        implementation("io.ktor:ktor-client-logging:${ktorVersion}")

        // Local settings
        implementation("com.russhwolf:multiplatform-settings-no-arg:1.0.0")

        // Logger
        implementation("co.touchlab:kermit:1.2.2")
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
        implementation("junit:junit:4.13.2")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        //For runBlockingTest, CoroutineDispatcher etc.
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
        implementation("com.russhwolf:multiplatform-settings-test:1.0.0")
      }
    }
    val androidMain by getting {
      dependencies {
        implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
      }
    }
    val androidUnitTest by getting
    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)
      dependencies {
        implementation("io.ktor:ktor-client-darwin:$ktorVersion")
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
    targetSdk = 33
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
    }
  }
}