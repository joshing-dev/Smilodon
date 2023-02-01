@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "com.matrix159.mastadonclone.presentation"
  compileSdk = 33

  defaultConfig {
    minSdk = 24
    targetSdk = 33

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildFeatures {
    compose = true
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.toString()
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {

  implementation(project(":shared"))
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.compose.ui)
  debugImplementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.compose.ui.tooling.preview)

  // TODO remove this????
  //implementation("androidx.compose.material:material:$composeVersion")

  implementation(libs.androidx.compose.material.iconsExtended)
  // Material3 in Compose

  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.lifecycle.runtimeCompose)

  // Image loading
  implementation(libs.coil.kt)
  implementation(libs.coil.kt.compose)
  implementation(libs.timber)

  testImplementation(libs.junit4)
  androidTestImplementation(libs.androidx.test.ext)
  androidTestImplementation(libs.androidx.test.espresso)
}