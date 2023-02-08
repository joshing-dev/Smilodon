pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // The below can't be added due to an ivy project repository that gets added by kotlin multiplatform
    //repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Mastadon_Clone"
include(":mastadonApp")
include(":shared")
include(":presentation")
