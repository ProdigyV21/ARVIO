pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Jellyfin FFmpeg decoder for DTS/TrueHD/Atmos
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "ArflixTV"
include(":app")
include(":baselineprofile")

