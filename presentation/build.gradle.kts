@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "com.matrix159.mastadonclone.presentation"
  compileSdk = 33

  defaultConfig {
    minSdk = 26
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
    kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)
  androidTestImplementation(composeBom)
  implementation(project(":shared"))
  implementation(libs.kotlinx.datetime)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.compose.ui)
  debugImplementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.material.iconsExtended)
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.lifecycle.runtimeCompose)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.coil.kt)
  implementation(libs.coil.kt.compose)
  implementation(libs.timber)
  // DI
  implementation(libs.koin.androidx.compose)
  testImplementation(libs.junit4)
  androidTestImplementation(libs.androidx.test.ext)
  androidTestImplementation(libs.androidx.test.espresso)
}
