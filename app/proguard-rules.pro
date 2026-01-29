# Arflix TV ProGuard Rules

# ============================================
# Log stripping for release builds
# Remove verbose, debug, and info logs
# ============================================
-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int d(...);
    public static int i(...);
}

# Also strip our custom AppLogger debug methods
-assumenosideeffects class com.arflix.tv.util.AppLogger {
    public static void v(...);
    public static void d(...);
    public static void i(...);
}

# ============================================
# Keep Retrofit interfaces
-keep,allowobfuscation,allowoptimization interface * {
    @retrofit2.http.* <methods>;
}

# Keep Gson serialization
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-keep class com.arflix.tv.data.model.** { *; }
-keep class com.arflix.tv.data.api.** { *; }

# Keep ExoPlayer
-keep class androidx.media3.** { *; }

# Keep Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# SLF4J (used by some networking libs)
-dontwarn org.slf4j.**


# Keep enum field names for Gson (used in Trakt outbox persistence)
-keepclassmembers enum com.arflix.tv.data.repository.TraktOutboxAction { *; }
