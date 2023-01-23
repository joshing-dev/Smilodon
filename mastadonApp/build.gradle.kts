plugins {
  id("com.android.application")
  kotlin("android")
}

android {
  namespace = "com.matrix159.mastadonclone"
  compileSdk = 33
  defaultConfig {
    applicationId = "com.matrix159.mastadonclone"
    minSdk = 24
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"
  }

  buildFeatures {
    buildConfig = true
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = "1.4.0"
  }

  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
    }
  }
}

dependencies {
  implementation(project(":shared"))
  implementation(project(":presentation"))

  implementation("androidx.core:core-ktx:1.9.0")
  implementation("androidx.activity:activity-compose:1.6.1")

  // timber
  implementation("com.jakewharton.timber:timber:5.0.1")
}