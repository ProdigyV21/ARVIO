import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("androidx.baselineprofile")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("io.gitlab.arturbosch.detekt")
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
        // Fire TV devices can be as low as Android 7.1 (API 25) or lower depending on model/OS.
        // Lower minSdk to maximize compatibility and avoid "There was a problem parsing the package".
        minSdk = 21
        targetSdk = 34
        versionCode = 150
        versionName = "1.5.0"

        vectorDrawables {
            useSupportLibrary = true
        }

        // Enable R8 full mode for better optimization
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
    }

    // Release signing configuration
    // To set up: create keystore.properties in project root with:
    //   storeFile=path/to/your.keystore
    //   storePassword=your_store_password
    //   keyAlias=your_key_alias
    //   keyPassword=your_key_password
    signingConfigs {
        create("release") {
            val keystorePropertiesFile = rootProject.file("keystore.properties")
            if (keystorePropertiesFile.exists()) {
                val keystoreProperties = Properties()
                keystoreProperties.load(keystorePropertiesFile.inputStream())
                storeFile = file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            // Use release signing if configured, otherwise fall back to debug
            val releaseSigningConfig = signingConfigs.findByName("release")
            signingConfig = if (releaseSigningConfig?.storeFile != null) {
                releaseSigningConfig
            } else {
                signingConfigs.getByName("debug")
            }
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // Optimization flags
            isDebuggable = false
            isJniDebuggable = false
            renderscriptOptimLevel = 3

            // Build config fields for release
            buildConfigField("Boolean", "ENABLE_CRASH_REPORTING", "true")
            buildConfigField("Boolean", "ENABLE_ANALYTICS", "true")
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            // applicationIdSuffix = ".debug" // Disabled to preserve settings between debug/release
            versionNameSuffix = "-debug"

            // Build config fields for debug
            buildConfigField("Boolean", "ENABLE_CRASH_REPORTING", "false")
            buildConfigField("Boolean", "ENABLE_ANALYTICS", "false")
        }

        // Staging build type for testing release builds
        create("staging") {
            initWith(getByName("release"))
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            signingConfig = signingConfigs.getByName("debug")

            // Enable crash reporting but disable analytics
            buildConfigField("Boolean", "ENABLE_CRASH_REPORTING", "true")
            buildConfigField("Boolean", "ENABLE_ANALYTICS", "false")
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

// KSP configuration for Hilt
ksp {
    arg("dagger.fastInit", "enabled")
    arg("dagger.formatGeneratedSource", "disabled")
    arg("dagger.hilt.android.internal.disableAndroidSuperclassValidation", "true")
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
    ksp("com.google.dagger:hilt-compiler:2.48")
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
    implementation("com.google.zxing:core:3.5.3")

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
    ksp("androidx.hilt:hilt-compiler:1.1.0")

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

    // Unit Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("app.cash.turbine:turbine:1.0.0")  // Flow testing
    testImplementation("com.google.truth:truth:1.1.5")    // Better assertions
    testImplementation("org.robolectric:robolectric:4.11.1")  // Android mocking

    // Android Instrumented Testing
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("io.mockk:mockk-android:1.13.8")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
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
