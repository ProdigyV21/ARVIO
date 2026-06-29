# ============================================
# ARVIO ProGuard/R8 Rules
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
# -assumenosideeffects class android.util.Log {
#     public static int v(...);
#     public static int d(...);
#     public static int i(...);
#     public static int w(...);
#     public static int e(...);
#     public static int wtf(...);
# }

# Also strip ALL our custom AppLogger methods
# -assumenosideeffects class com.arflix.tv.util.AppLogger {
#     public static void v(...);
#     public static void d(...);
#     public static void i(...);
#     public static void w(...);
#     public static void e(...);
# }

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
# Sideload plugin runtime / extractor stack
# ============================================
# CloudStream/NewPipe/Jackson pull a few optional JVM helper integrations that
# are not available or not needed on Android release builds. Keep R8 strict for
# app code, but suppress those optional adapter references so sideload release
# minification can complete.
-dontwarn com.google.re2j.**
-dontwarn com.sun.jna.**
-dontwarn edu.umd.cs.findbugs.annotations.**
-dontwarn javax.script.**
-dontwarn org.mozilla.javascript.**

# CloudStream API & Dependencies (External plugins are compiled against these)
-keep class com.lagradost.** { *; }
-keep interface com.lagradost.** { *; }
-dontwarn com.lagradost.**

-keep class org.jsoup.** { *; }
-keep interface org.jsoup.** { *; }
-dontwarn org.jsoup.**

-keep class com.fasterxml.jackson.** { *; }
-keep interface com.fasterxml.jackson.** { *; }
-dontwarn com.fasterxml.jackson.**

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
# Google Cast SDK
# ============================================
-keep class com.google.android.gms.cast.** { *; }
-keep class com.google.android.gms.cast.framework.** { *; }
-keep class com.arflix.tv.cast.CastOptionsProvider { *; }
-dontwarn com.google.android.gms.cast.**

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

# Retrofit needs generic signatures on service methods such as
# Response<List<TraktWatchlistItem>>. Keep these after all other attribute
# rules so release minification cannot strip them and break live Trakt sync.
-keepattributes Signature,*Annotation*,InnerClasses,EnclosingMethod,Exceptions,SourceFile,LineNumberTable

# ============================================
# TDLib — preserve all JNI callback classes
# The native layer calls back into Java by exact name; obfuscation breaks it.
# ============================================
-keep class org.drinkless.tdlib.** { *; }
-dontwarn org.drinkless.tdlib.**

# ============================================
# org.json — Android provides these at runtime; any bundled copy must not be
# renamed or R8 will produce org.json.b/c names that collide with the system
# classes and fail ART bytecode verification (VerifyError on app launch).
# ============================================
-keep class org.json.** { *; }

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
