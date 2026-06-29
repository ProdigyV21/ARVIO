// Top-level build file for Arflix Native Android TV

plugins {
    id("com.android.application") version "8.9.0" apply false
    id("com.android.test") version "8.9.0" apply false
    id("androidx.baselineprofile") version "1.3.1" apply false
    id("org.jetbrains.kotlin.android") version "2.3.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.3.0" apply false
    // Kotlin 2.0+: Compose compiler is a dedicated Gradle plugin; version
    // must track Kotlin.
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.0" apply false
    id("com.google.devtools.ksp") version "2.3.0" apply false
    // Hilt 2.59's Gradle plugin requires AGP 9.0 + Gradle 9.1 and is broken there
    // (dagger#5099: missing ComponentTreeDeps at runtime). Pinned to 2.57 — the last
    // stable release that works with AGP 8.x. Paired with androidx.hilt 1.2.0 (stable)
    // so hilt-work doesn't force dagger back up to 2.59.
    id("com.google.dagger.hilt.android") version "2.57" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    // Firebase - requires google-services.json from Firebase Console
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
    // Static analysis
    id("io.gitlab.arturbosch.detekt") version "1.23.7" apply false
}

