plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("androidx.baselineprofile")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("io.gitlab.arturbosch.detekt")
    kotlin("kapt")
    kotlin("plugin.serialization")
    // Firebase Crashlytics - uncomment after adding google-services.json
    // id("com.google.gms.google-services")
    // id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.arflix.tv"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.arvio.tv"
        minSdk = 26
        targetSdk = 34
        versionCode = 100
        versionName = "1.0.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    baselineProfile {
        mergeIntoMain = true
    }
}

dependencies {
    // Core library desugaring for Java 8+ APIs
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    // Core Android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.core:core-splashscreen:1.0.1")  // Android 12+ Splash Screen
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")

    // Compose BOM - use compatible version for TV
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.animation:animation")

    // Compose for TV - Core TV components
    implementation("androidx.tv:tv-foundation:1.0.0-alpha10")
    implementation("androidx.tv:tv-material:1.0.0-alpha10")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Hilt for DI
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Leanback (TV compliance, browse fragments if needed)
    implementation("androidx.leanback:leanback:1.1.0-rc02")

    // ExoPlayer / Media3 for video playback - version 1.3.1 for latest codec support
    val media3Version = "1.3.1"
    implementation("androidx.media3:media3-exoplayer:$media3Version")
    implementation("androidx.media3:media3-exoplayer-hls:$media3Version")
    implementation("androidx.media3:media3-exoplayer-dash:$media3Version")
    implementation("androidx.media3:media3-ui:$media3Version")
    implementation("androidx.media3:media3-session:$media3Version")
    implementation("androidx.media3:media3-common:$media3Version")
    // FFmpeg extension for software decoding of DTS/TrueHD/Atmos/HEVC/DV (Jellyfin's prebuilt)
    implementation("org.jellyfin.media3:media3-ffmpeg-decoder:1.3.1+2")

    // Networking - Retrofit + OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Image loading - Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Supabase (optional - for cloud sync)
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.0.4")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.0.4")
    implementation("io.ktor:ktor-client-android:2.3.7")

    // DataStore for preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Google Sign-In / Credential Manager for TV
    implementation("androidx.credentials:credentials:1.3.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    // WorkManager for background sync
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-work:1.1.0")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    // Profile installer for baseline profiles
    implementation("androidx.profileinstaller:profileinstaller:1.3.1")

    // Performance instrumentation
    implementation("androidx.metrics:metrics-performance:1.0.0-alpha04")
    implementation("androidx.tracing:tracing-ktx:1.2.0")

    // Debug tools
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Firebase Crashlytics - optional, works when google-services.json is present
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")

    baselineProfile(project(":baselineprofile"))

    testImplementation("junit:junit:4.13.2")
}

kapt {
    correctErrorTypes = true
}

secrets {
    // Secrets file to read from
    propertiesFileName = "secrets.properties"

    // Default file with placeholder values (for CI/new developers)
    defaultPropertiesFileName = "secrets.defaults.properties"

    // Ignore missing keys to allow builds without secrets file
    ignoreList.add("sdk.*")
}

detekt {
    // Configuration file
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))

    // Baseline file for existing issues (generated with ./gradlew detektBaseline)
    baseline = file("$rootDir/config/detekt/baseline.xml")

    // Build upon default ruleset
    buildUponDefaultConfig = true

    // Run detekt on all source sets
    source.setFrom(
        files(
            "src/main/kotlin",
            "src/main/java"
        )
    )

    // Parallel execution
    parallel = true

    // Don't fail build on issues (use baseline instead)
    ignoreFailures = true
}
