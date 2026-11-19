pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

// Newer R8 than the one AGP 8.13 bundles. The bundled R8 miscompiled PlayerScreen in release
// builds (a dropped register move: "register v3 has type Integer but expected Reference"), so
// ART rejected the whole class with a VerifyError and every player open crashed. Debug builds
// don't run R8, so only a release APK shows it. 8.13 (bundled) and 9.0.55 both produced it;
// 9.5.28 verifies clean on an API 30 device. Check before dropping this pin: install a release
// APK on an API 30 device and run `adb shell cmd package compile -m verify -f <package>`, then
// look for "Verification error" in logcat.
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("com.android.tools:r8:9.5.28")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Jellyfin's FFmpeg extension for Media3
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/releases/") }
    }
}

rootProject.name = "ARVIO"
include(":app")
include(":benchmark")
