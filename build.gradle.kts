// Top-level build file for Arflix Native Android TV

plugins {
    id("com.android.application") version "8.3.2" apply false
    id("com.android.test") version "8.3.2" apply false
    id("androidx.baselineprofile") version "1.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    // Firebase - requires google-services.json from Firebase Console
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
    // Static analysis
    id("io.gitlab.arturbosch.detekt") version "1.23.4" apply false
}


