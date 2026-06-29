import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // Kotlin 2.0+: Compose compiler is its own plugin, not a
    // `composeOptions.kotlinCompilerExtensionVersion` pin. Version tracks
    // Kotlin in the root build.gradle.kts.
    id("org.jetbrains.kotlin.plugin.compose")
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
    compileSdk = 36

    flavorDimensions += "distribution"

    defaultConfig {
        applicationId = "com.arvio.tv"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // Fire TV devices can be as low as Android 7.1 (API 25) or lower depending on model/OS.
        minSdk = 23
        targetSdk = 35
        versionCode = 300
        versionName = "1.9.97"
        buildConfigField("String", "GITHUB_OWNER", "\"ProdigyV21\"")
        buildConfigField("String", "GITHUB_REPO", "\"ARVIO\"")
        buildConfigField("Boolean", "FEATURE_PLUGINS_ENABLED", "false")
        // Emergency Supabase cost guard. Keep high-volume public metadata and
        // idle realtime polling off Supabase unless explicitly re-enabled.
        buildConfigField("Boolean", "ENABLE_TMDB_EDGE_PROXY", "false")
        buildConfigField("Boolean", "ENABLE_TRAKT_EDGE_PROXY", "false")
        buildConfigField("Boolean", "ENABLE_REALTIME_CLOUD_SYNC", "true")
        buildConfigField("Boolean", "ENABLE_REALTIME_WATCH_SYNC", "false")
        buildConfigField("Boolean", "ENABLE_PERIODIC_CLOUD_PULL", "false")
        buildConfigField("Boolean", "ENABLE_NETLIFY_CLOUD_SYNC", "true")
        buildConfigField("Boolean", "ENABLE_SUPABASE_SYNC_MIRROR", "false")
        buildConfigField("String", "NETLIFY_BACKEND_URL", "\"https://auth.arvio.tv/.netlify/functions\"")
        buildConfigField(
            "String",
            "APP_ANON_KEY",
            "\"${escapeBuildConfigString(localSecretValue("APP_ANON_KEY").ifBlank { localSecretValue("SUPABASE_ANON_KEY") })}\""
        )


        // Support both 32-bit and 64-bit devices (required for Google Play since 2019)
        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
        }

        vectorDrawables {
            useSupportLibrary = true
        }

        // Enable R8 full mode for better optimization
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
    }

    productFlavors {
        create("play") {
            dimension = "distribution"
            buildConfigField("Boolean", "SELF_UPDATE_ENABLED", "false")
            buildConfigField("Boolean", "FEATURE_PLUGINS_ENABLED", "false")
        }
        create("sideload") {
            dimension = "distribution"
            buildConfigField("Boolean", "SELF_UPDATE_ENABLED", "true")
            buildConfigField("Boolean", "FEATURE_PLUGINS_ENABLED", "true")
        }
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
            // Full release optimization for TV smoothness.
            isMinifyEnabled = true
            isShrinkResources = false
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
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            // applicationIdSuffix = ".debug" // Disabled to preserve settings between debug/release
            versionNameSuffix = "-debug"

            // Build config fields for debug
            buildConfigField("Boolean", "ENABLE_CRASH_REPORTING", "false")
        }

        // Staging build type: release-grade optimizations but signed with the
        // debug keystore so the APK installs as an update over an existing
        // debug build (preserves profile/IPTV/DataStore). NO applicationId
        // suffix — it MUST resolve to the same package as debug/release.
        create("staging") {
            initWith(getByName("release"))
            versionNameSuffix = "-rc"
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = false
            isJniDebuggable = false

            buildConfigField("Boolean", "ENABLE_CRASH_REPORTING", "true")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/tdlib-java")
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/versions/9/OSGI-INF/MANIFEST.MF"
            excludes += setOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                "/META-INF/LICENSE*",
                "/META-INF/NOTICE*",
                "META-INF/versions/9/OSGI-INF/MANIFEST.MF",
            )
        }
        jniLibs {
            useLegacyPackaging = false  // Required for 16KB page size support
        }
    }

    baselineProfile {
        mergeIntoMain = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

// Kotlin 2.0+ Compose compiler plugin config. The stability config file
// is the same as before — marks domain models as stable to avoid
// unnecessary recompositions — but fed through a first-class plugin
// extension instead of a raw -P freeCompilerArg.
composeCompiler {
    stabilityConfigurationFile = rootProject.layout.projectDirectory
        .file("app/compose_stability_config.conf")
}

// KSP configuration for Hilt
ksp {
    arg("dagger.fastInit", "enabled")
    arg("dagger.formatGeneratedSource", "disabled")
    arg("dagger.hilt.android.internal.disableAndroidSuperclassValidation", "true")
}

    // Kotlin 2.3.0 emits class metadata v2.3.0, which Hilt 2.57's bundled
    // kotlin-metadata-jvm (max v2.2.0) cannot read — hiltJavaCompile fails with
    // "Provided Metadata instance has version 2.3.0". Force the reader library to
    // 2.3.0 on every configuration (incl. Hilt's annotation-processor classpath) so
    // Hilt 2.57 can process Kotlin 2.3.0 metadata without moving to Hilt 2.59 (AGP 9).
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains.kotlin:kotlin-metadata-jvm:2.3.0")
            force("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            force("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
        }
    }

    dependencies {
    // Gson explicit pin to keep `JsonParser`/AST extension API stable with current sources.
    implementation("com.google.code.gson:gson:2.10.1")

    // Cast / Chromecast support
    implementation("androidx.mediarouter:mediarouter:1.7.0")
    implementation("com.google.android.gms:play-services-cast-framework:22.2.0")

    // Core library desugaring for Java 8+ APIs
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    // Core Android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.core:core-splashscreen:1.0.1")  // Android 12+ Splash Screen
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    // Provides collectAsStateWithLifecycle — pauses Flow collection while the
    // screen is off so we don't drive recompositions on invisible UI.
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.activity:activity:1.10.0")
    implementation("androidx.activity:activity-ktx:1.10.0")
    implementation("androidx.activity:activity-compose:1.10.0")

    // Compose BOM — bumped alongside Kotlin 2.1. Staying on the 2024.06
    // line keeps tv-foundation 1.0.0-alpha11 happy; newer BOMs drift the
    // runtime off alpha11 and cause invalid-slot-table crashes on D-pad.
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.animation:animation")

    // Compose for TV - Core TV components
    // tv-foundation stays alpha (no beta/stable releases exist); tv-material bumped to stable
    implementation("androidx.tv:tv-foundation:1.0.0-alpha11")
    implementation("androidx.tv:tv-material:1.0.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Hilt for DI — 2.57 (last stable for AGP 8.x). 2.59's Gradle plugin demands
    // AGP 9 + Gradle 9.1 and is broken there (dagger#5099). androidx.hilt is held at
    // stable 1.2.0 below so hilt-work doesn't drag dagger back up to 2.59.
    implementation("com.google.dagger:hilt-android:2.57")
    ksp("com.google.dagger:hilt-compiler:2.57")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Leanback (TV compliance, browse fragments if needed)
    implementation("androidx.leanback:leanback:1.1.0-rc02")
    implementation("androidx.tvprovider:tvprovider:1.1.0")

    // ExoPlayer / Media3 for video playback
    val media3Version = "1.9.0"
    implementation("androidx.media3:media3-exoplayer:$media3Version")
    implementation("androidx.media3:media3-exoplayer-hls:$media3Version")
    implementation("androidx.media3:media3-exoplayer-dash:$media3Version")
    implementation("androidx.media3:media3-datasource-okhttp:$media3Version")
    implementation("androidx.media3:media3-ui:$media3Version")
    implementation("androidx.media3:media3-session:$media3Version")
    implementation("androidx.media3:media3-common:$media3Version")
    // FFmpeg extension for software decoding of DTS/TrueHD/Atmos/HEVC/DV.
    // Keep this only in the sideload build. The Play Store build must comply
    // with 16 KB memory page support, and the current prebuilt native library
    // (libffmpegJNI.so) is the likely source of the Play Console warning.
    add("sideloadImplementation", "org.jellyfin.media3:media3-ffmpeg-decoder:1.9.0+1")

    // Networking - Retrofit + OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:okhttp-dnsoverhttps:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.moshi:moshi:1.15.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    // HTML parsing for catalog discovery.
    implementation("org.jsoup:jsoup:1.17.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Image loading - Coil
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("io.coil-kt:coil-gif:2.5.0")
    implementation("io.coil-kt:coil-svg:2.5.0")
    implementation("com.google.zxing:core:3.5.3")

    // Supabase (optional - for cloud sync)
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.0.4")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.0.4")
    implementation("io.ktor:ktor-client-android:2.3.7")
    // Ktor server modules used by Telegram streaming proxy
    implementation("io.ktor:ktor-server-core:2.3.7")
    implementation("io.ktor:ktor-server-cio:2.3.7")
    implementation("io.ktor:ktor-server-call-logging:2.3.7")
    implementation("io.ktor:ktor-server-host-common:2.3.7")

    // DataStore for preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Google Sign-In / Credential Manager for TV
    implementation("androidx.credentials:credentials:1.3.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    // WorkManager for background sync
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    // Stable 1.2.0: the 1.4.0-rc01 RC depends on dagger Hilt 2.59 and forces the whole
    // graph onto the AGP-9-only (and currently broken) 2.59. 1.2.0 works with Hilt 2.57.
    implementation("androidx.hilt:hilt-work:1.2.0")
    ksp("androidx.hilt:hilt-compiler:1.2.0")

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

    // Sentry crash reporting. Runtime initialization is gated by BuildConfig.ENABLE_CRASH_REPORTING
    // and SENTRY_DSN from secrets.properties/secrets.defaults.properties.
    implementation("io.sentry:sentry-android:8.40.0")

    baselineProfile(project(":benchmark"))

    // NanoHTTPD – lightweight HTTP server for QR-based AI key setup
    implementation("org.nanohttpd:nanohttpd:2.3.1")

    // Plugin system dependencies (Sideload flavor only)
    add("sideloadImplementation", files("libs/quickjs-kt-android-1.0.5-nuvio.aar"))
    add("sideloadImplementation", "com.fasterxml.jackson.core:jackson-databind:2.17.0")
    add("sideloadImplementation", "com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
    add("sideloadImplementation", "com.github.Blatzar:NiceHttp:0.4.11")
    add("sideloadImplementation", "org.conscrypt:conscrypt-android:2.5.3")
    add("sideloadImplementation", "com.github.recloudstream.cloudstream:library-android:v4.7.0") {
        exclude(group = "org.mozilla", module = "rhino")
    }
    add("sideloadImplementation", "org.mozilla:rhino:1.8.1")
    add("sideloadImplementation", "com.google.re2j:re2j:1.8")
    add("sideloadImplementation", "org.webjars.npm:crypto-js:4.2.0")

    // Unit Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("app.cash.turbine:turbine:1.0.0")  // Flow testing
    testImplementation("com.google.truth:truth:1.1.5")    // Better assertions
    testImplementation("org.robolectric:robolectric:4.11.1")  // Android mocking

    // Android Instrumented Testing
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:core-ktx:1.5.0")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.2.0")
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
    ignoreList.add("APP_ANON_KEY")
}

fun localSecretValue(name: String): String {
    val secretsFile = rootProject.file("secrets.properties")
    if (secretsFile.exists()) {
        val properties = Properties()
        secretsFile.inputStream().use { properties.load(it) }
        properties.getProperty(name)?.trim()?.takeIf { it.isNotBlank() }?.let { return it }
    }
    providers.gradleProperty(name).orNull?.trim()?.takeIf { it.isNotBlank() }?.let { return it }
    providers.environmentVariable(name).orNull?.trim()?.takeIf { it.isNotBlank() }?.let { return it }
    return ""
}

fun escapeBuildConfigString(value: String): String =
    value
        .replace("\\", "\\\\")
        .replace("\"", "\\\"")

val validateReleaseCloudSecrets = tasks.register("validateReleaseCloudSecrets") {
    doLast {
        val appAnonKey = localSecretValue("APP_ANON_KEY").ifBlank { localSecretValue("SUPABASE_ANON_KEY") }
        require(
            appAnonKey.length > 40 &&
                !appAnonKey.equals("your-supabase-anon-key", ignoreCase = true) &&
                !appAnonKey.startsWith("your-", ignoreCase = true)
        ) {
            "Release builds require a real APP_ANON_KEY in secrets.properties, Gradle properties, or the environment."
        }
    }
}

tasks.configureEach {
    if (name in setOf(
            "prePlayReleaseBuild",
            "preSideloadReleaseBuild",
            "prePlayStagingBuild",
            "preSideloadStagingBuild"
        )
    ) {
        dependsOn(validateReleaseCloudSecrets)
    }
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


kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    ksp("org.jetbrains.kotlin:kotlin-metadata-jvm:2.3.0")
    annotationProcessor("org.jetbrains.kotlin:kotlin-metadata-jvm:2.3.0")

    // Plugin system dependencies (Sideload flavor only)
    add("sideloadImplementation", files("libs/quickjs-kt-android-1.0.5-nuvio.aar"))
    add("sideloadImplementation", "com.fasterxml.jackson.core:jackson-databind:2.17.0")
    add("sideloadImplementation", "com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
    add("sideloadImplementation", "com.github.Blatzar:NiceHttp:0.4.11")
    add("sideloadImplementation", "org.conscrypt:conscrypt-android:2.5.3")
    add("sideloadImplementation", "com.github.recloudstream.cloudstream:library-android:v4.7.0") {
        exclude(group = "org.mozilla", module = "rhino")
    }
    add("sideloadImplementation", "org.webjars.npm:crypto-js:4.2.0")
    
    // Runtime helpers used by the sideload plugin extractor stack.
    add("sideloadImplementation", "org.mozilla:rhino:1.8.1")
    add("sideloadImplementation", "com.google.re2j:re2j:1.8")
}


