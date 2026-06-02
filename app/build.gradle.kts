import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest
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
        versionCode = 281
        versionName = "1.9.94"
        buildConfigField("String", "GITHUB_OWNER", "\"ProdigyV21\"")
        buildConfigField("String", "GITHUB_REPO", "\"ARVIO\"")


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



    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += setOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                "/META-INF/LICENSE*",
                "/META-INF/NOTICE*", "META-INF/versions/9/OSGI-INF/MANIFEST.MF")
        }
        jniLibs {
            useLegacyPackaging = false  // Required for 16KB page size support
        }
    }

    baselineProfile {
        mergeIntoMain = true
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/tdlib-java")
        }
    }

    // Per-ABI APKs for sideload distribution only.
    // Play builds are distributed via AAB (Google Play handles splitting) so splits are not needed.
    // x86/x86_64 splits are emulator/debug use only.
    val isSideloadBuild = gradle.startParameter.taskNames.any { it.contains("sideload", ignoreCase = true) }
    splits {
        abi {
            isEnable = isSideloadBuild
            reset()
            include("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
            isUniversalApk = true
        }
    }
}

// Kotlin 2.0+ Compose compiler plugin config. The stability config file
// is the same as before — marks domain models as stable to avoid
// unnecessary recompositions — but fed through a first-class plugin
// extension instead of a raw -P freeCompilerArg.
configurations.configureEach {
    exclude(group = "org.json", module = "json")

    resolutionStrategy.eachDependency {
        if (requested.group == "org.jetbrains.kotlinx" && requested.name.startsWith("kotlinx-coroutines")) {
            useVersion("1.7.3")
            because("Ktor 2.3.7 used by Supabase and the Telegram proxy crashes with coroutines 1.10.x on Android TV.")
        }
    }
}

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

dependencies {
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
    implementation("androidx.activity:activity-compose:1.8.2")

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

    // Hilt for DI — 2.54 is the first release with Kotlin 2.1 metadata
    // support on the Java compile side. 2.52 fails on `hiltJavaCompile*`
    // with "Unable to read Kotlin metadata due to unsupported metadata
    // version" because Hilt parses generated `@Module` classes that carry
    // Kotlin 2.1's newer metadata format.
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

    // HTML parsing for catalog discovery.
    implementation("org.jsoup:jsoup:1.17.2")
    // Runtime helpers used by the sideload plugin extractor stack.
    // NewPipe/cloudstream evaluate some packed JavaScript with Rhino, while
    // newer jsoup exposes an optional RE2J regex adapter referenced during R8.
    add("sideloadImplementation", "org.mozilla:rhino:1.8.1")
    add("sideloadImplementation", "com.google.re2j:re2j:1.8")

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

    // Telegram native integration — local HTTP streaming proxy + TDLib Java API
    // libtdjni.so and TdApi.java are downloaded automatically by the downloadTdlibNatives task
    implementation("io.ktor:ktor-server-cio:2.3.7")
    implementation("io.ktor:ktor-server-core:2.3.7")

    // DataStore for preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Google Cast SDK — mobile-only at runtime (guarded by DeviceType check), harmless on TV
    implementation("com.google.android.gms:play-services-cast-framework:21.4.0")
    implementation("androidx.mediarouter:mediarouter:1.7.0")

    // Google Sign-In / Credential Manager for TV
    implementation("androidx.credentials:credentials:1.3.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    // WorkManager for background sync
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-work:1.1.0")
    ksp("androidx.hilt:hilt-compiler:1.1.0")

    // Room for download persistence
    implementation("androidx.room:room-runtime:2.7.1")
    implementation("androidx.room:room-ktx:2.7.1")
    ksp("androidx.room:room-compiler:2.7.1")

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

val validateReleaseSupabaseSecrets = tasks.register("validateReleaseSupabaseSecrets") {
    doLast {
        val supabaseUrl = localSecretValue("SUPABASE_URL")
        val supabaseAnonKey = localSecretValue("SUPABASE_ANON_KEY")
        require(
            supabaseUrl.startsWith("https://") &&
                supabaseUrl.endsWith(".supabase.co") &&
                !supabaseUrl.contains("your-project", ignoreCase = true)
        ) {
            "Release builds require a real SUPABASE_URL in secrets.properties, Gradle properties, or the environment."
        }
        require(
            supabaseAnonKey.length > 40 &&
                !supabaseAnonKey.equals("your-supabase-anon-key", ignoreCase = true)
        ) {
            "Release builds require a real SUPABASE_ANON_KEY in secrets.properties, Gradle properties, or the environment."
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
        dependsOn(validateReleaseSupabaseSecrets)
    }
}

// Downloads prebuilt TDLib Android natives + Java API sources on first build.
// Files are cached — re-download only if arm64-v8a libtdjni.so is missing.
//
// Provenance:
//   Source repo : https://github.com/FaiBah/tdlib-android-prebuilt
//   Release tag : v1.8.64-e0943d0-Java
//   TDLib version: 1.8.64, commit e0943d0 (https://github.com/tdlib/td)
//   License      : Boost Software License 1.0 (https://www.boost.org/LICENSE_1_0.txt)
//
// SHA-256 of extracted libtdjni.so per ABI (verified after download):
//   arm64-v8a  : fd0509edd49107af27e4799caac226f3cc365b273f56df79bf4d7708c6dfa879
//   armeabi-v7a: 971d7abafa0e6a6c1cae31397d6b2c4255064938b7472874b88633b5bde58b5e
//   x86        : 626c06f2de659b925e63e139bc0e3d4e7bc28d3718d7aa7ac3b67f671238d513
//   x86_64     : a652be5eed71dfce7791f80658cbd78742fb6f5341dbbb9e62b31be3a8cb8984
tasks.register("downloadTdlibNatives") {
    val marker = project.file("src/main/jniLibs/arm64-v8a/libtdjni.so")
    onlyIf { !marker.exists() }

    doLast {
        val base = "https://github.com/FaiBah/tdlib-android-prebuilt/releases/download/v1.8.64-e0943d0-Java"

        val expectedChecksums = mapOf(
            "arm64-v8a"   to "fd0509edd49107af27e4799caac226f3cc365b273f56df79bf4d7708c6dfa879",
            "armeabi-v7a" to "971d7abafa0e6a6c1cae31397d6b2c4255064938b7472874b88633b5bde58b5e",
            "x86"         to "626c06f2de659b925e63e139bc0e3d4e7bc28d3718d7aa7ac3b67f671238d513",
            "x86_64"      to "a652be5eed71dfce7791f80658cbd78742fb6f5341dbbb9e62b31be3a8cb8984"
        )

        fun verifySha256(file: File, expected: String) {
            val digest = MessageDigest.getInstance("SHA-256")
            val actual = file.inputStream().use { stream ->
                val buf = ByteArray(8192)
                var n: Int
                while (stream.read(buf).also { n = it } != -1) digest.update(buf, 0, n)
                digest.digest().joinToString("") { "%02x".format(it) }
            }
            require(actual == expected) {
                "Checksum mismatch for ${file.name}: expected $expected but got $actual"
            }
        }

        fun fetch(urlStr: String, dest: File) {
            dest.parentFile.mkdirs()
            var url = URL(urlStr)
            var conn = url.openConnection() as HttpURLConnection
            conn.instanceFollowRedirects = true
            conn.connect()
            while (conn.responseCode in 300..399) {
                url = URL(conn.getHeaderField("Location"))
                conn = url.openConnection() as HttpURLConnection
                conn.instanceFollowRedirects = true
                conn.connect()
            }
            conn.inputStream.use { inp -> dest.outputStream().use { inp.copyTo(it) } }
        }

        // arm64-v8a: .so + Java source files
        val arm64Zip = File(temporaryDir, "tdlib-arm64-v8a.zip")
        logger.lifecycle("Downloading TDLib arm64-v8a (~22 MB)...")
        fetch("$base/tdlib-arm64-v8a.zip", arm64Zip)
        project.copy {
            from(project.zipTree(arm64Zip)) {
                include("libs/arm64-v8a/libtdjni.so")
                eachFile { relativePath = RelativePath(true, *relativePath.segments.drop(1).toTypedArray()) }
                includeEmptyDirs = false
            }
            into("src/main/jniLibs")
        }
        verifySha256(project.file("src/main/jniLibs/arm64-v8a/libtdjni.so"), expectedChecksums["arm64-v8a"]!!)
        project.copy {
            from(project.zipTree(arm64Zip)) {
                include("java/org/drinkless/tdlib/*.java")
                eachFile { relativePath = RelativePath(true, *relativePath.segments.drop(1).toTypedArray()) }
                includeEmptyDirs = false
            }
            into("src/main/tdlib-java")
        }
        arm64Zip.delete()

        // remaining ABIs: .so only
        listOf("armeabi-v7a", "x86", "x86_64").forEach { abi ->
            val zipFile = File(temporaryDir, "tdlib-$abi.zip")
            logger.lifecycle("Downloading TDLib $abi...")
            fetch("$base/tdlib-$abi.zip", zipFile)
            project.copy {
                from(project.zipTree(zipFile)) {
                    include("libs/$abi/libtdjni.so")
                    eachFile { relativePath = RelativePath(true, *relativePath.segments.drop(1).toTypedArray()) }
                    includeEmptyDirs = false
                }
                into("src/main/jniLibs")
            }
            verifySha256(project.file("src/main/jniLibs/$abi/libtdjni.so"), expectedChecksums[abi]!!)
            zipFile.delete()
        }

        logger.lifecycle("TDLib natives ready — all checksums verified.")
    }
}

tasks.named("preBuild") {
    dependsOn("downloadTdlibNatives")
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
        freeCompilerArgs.add("-Xskip-metadata-version-check")
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
    add("sideloadImplementation", "com.github.recloudstream.cloudstream:library:v4.7.0") {
        exclude(group = "org.mozilla", module = "rhino")
    }
    add("sideloadImplementation", "org.webjars.npm:crypto-js:4.2.0")

    // Moshi - used in both sideload plugins and main data store
    implementation("com.squareup.moshi:moshi:1.15.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.1")
}
