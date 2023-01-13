plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.mastadonclone"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.mastadonclone"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        buildConfig = true
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":presentation"))

    // timber
    implementation("com.jakewharton.timber:timber:5.0.1")
}