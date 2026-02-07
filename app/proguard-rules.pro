# ============================================
# Arflix TV ProGuard/R8 Rules
# Production-ready optimization rules
# ============================================

# ============================================
# General Android optimizations
# ============================================
-optimizationpasses 5
-dontusemixedcaseclassnames
-verbose

# Keep source file names and line numbers for crash reports
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# ============================================
# Log stripping for release builds
# Remove ALL logs for maximum performance
# ============================================
-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int d(...);
    public static int i(...);
    public static int w(...);
    public static int e(...);
    public static int wtf(...);
}

# Also strip ALL our custom AppLogger methods
-assumenosideeffects class com.arflix.tv.util.AppLogger {
    public static void v(...);
    public static void d(...);
    public static void i(...);
    public static void w(...);
    public static void e(...);
}

# Strip Kotlin debug assertions in release
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    public static void checkNotNull(...);
    public static void checkNotNullParameter(...);
    public static void checkNotNullExpressionValue(...);
    public static void checkParameterIsNotNull(...);
    public static void checkExpressionValueIsNotNull(...);
}

# ============================================
# Kotlin specific rules
# ============================================
-keep class kotlin.Metadata { *; }
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

# Kotlin coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# ============================================
# Retrofit / OkHttp
# ============================================
-keep,allowobfuscation,allowoptimization interface * {
    @retrofit2.http.* <methods>;
}

-keepattributes Signature
-keepattributes Exceptions

# OkHttp platform used only on JVM and when Conscrypt dependency is available
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**

# ============================================
# Gson serialization
# ============================================
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-keep class com.arflix.tv.data.model.** { *; }
-keep class com.arflix.tv.data.api.** { *; }

# Keep generic type information for Gson
-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken

# Prevent R8 from removing fields used by Gson reflection
-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep enum field names for Gson (used in Trakt outbox persistence)
-keepclassmembers enum com.arflix.tv.data.repository.TraktOutboxAction { *; }

# ============================================
# ExoPlayer / Media3
# ============================================
-keep class androidx.media3.** { *; }
-keep interface androidx.media3.** { *; }
-dontwarn androidx.media3.**

# FFmpeg decoder extension
-keep class org.jellyfin.media3.** { *; }
-dontwarn org.jellyfin.media3.**

# ============================================
# Hilt / Dagger - KEEP EVERYTHING
# ============================================
# Keep ALL app classes to prevent Hilt/Dagger issues
-keep class com.arflix.tv.** { *; }
-keep interface com.arflix.tv.** { *; }

# Keep ALL Dagger/Hilt classes and generated code
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }
-keep class dagger.hilt.** { *; }
-keep class dagger.hilt.internal.** { *; }
-keep class dagger.hilt.android.** { *; }
-keep class dagger.hilt.android.internal.** { *; }

# Keep Hilt aggregated deps (generated classes)
-keep class hilt_aggregated_deps.** { *; }
-keep interface hilt_aggregated_deps.** { *; }

# Suppress warnings for Hilt generated classes
-dontwarn com.arflix.tv.**_GeneratedInjector
-dontwarn dagger.hilt.internal.aggregatedroot.codegen.**
-dontwarn hilt_aggregated_deps.**

# ============================================
# Jetpack Compose
# ============================================
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Keep composable functions for proper rendering
-keepclasseswithmembers class * {
    @androidx.compose.runtime.Composable <methods>;
}

# ============================================
# AndroidX / Lifecycle
# ============================================
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keep class * extends androidx.lifecycle.AndroidViewModel { *; }

# ============================================
# Supabase
# ============================================
-keep class io.github.jan.supabase.** { *; }
-dontwarn io.github.jan.supabase.**

# Ktor (used by Supabase)
-keep class io.ktor.** { *; }
-dontwarn io.ktor.**

# ============================================
# Google Sign-In / Credentials
# ============================================
-keep class com.google.android.gms.auth.** { *; }
-keep class androidx.credentials.** { *; }
-dontwarn com.google.android.gms.**

# ============================================
# Firebase Crashlytics
# ============================================
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception
-keep class com.google.firebase.crashlytics.** { *; }

# ============================================
# Coil image loading
# ============================================
-keep class coil.** { *; }
-dontwarn coil.**

# ============================================
# Warnings to suppress
# ============================================
-dontwarn org.slf4j.**
-dontwarn java.lang.invoke.StringConcatFactory
-dontwarn javax.annotation.**
-dontwarn kotlin.reflect.jvm.internal.**

# ============================================
# App-specific keeps
# ============================================
# Keep app exception classes for crash reporting
-keep class com.arflix.tv.util.AppException { *; }
-keep class com.arflix.tv.util.AppException$* { *; }

# Keep sealed classes for proper when() handling
-keep class com.arflix.tv.util.Result { *; }
-keep class com.arflix.tv.util.Result$* { *; }
-keep class com.arflix.tv.util.UiState { *; }
-keep class com.arflix.tv.util.UiState$* { *; }
