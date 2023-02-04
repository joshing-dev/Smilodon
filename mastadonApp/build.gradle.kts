
@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  //alias(libs.plugins.kotlin.kapt)
  //alias(libs.plugins.ksp)
}
android {
  namespace = "com.matrix159.mastadonclone"
  compileSdk = 33
  defaultConfig {
    applicationId = "com.matrix159.mastadonclone"
    minSdk = 24
    targetSdk = 33
    versionCode = 1
    versionName = "0.0.1" // X.Y.Z; X= Major, Y = minor, Z = Patch level
  }

  buildFeatures {
    buildConfig = true
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
  }

  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  buildTypes {
    getByName("release") {

//      TODO enable minify and update signing configs
      signingConfig = signingConfigs.getByName("debug")
    }
  }

}

dependencies {
  implementation(project(":shared"))
  implementation(project(":presentation"))
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.lifecycle.process)
  implementation(libs.loginoauth)
  implementation(libs.timber)
}