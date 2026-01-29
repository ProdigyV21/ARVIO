package com.arflix.tv.util

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics

/**
 * Firebase Crashlytics implementation of [AppLogger.CrashContextProvider].
 *
 * Provides crash reporting with:
 * - Custom keys for context (user_id, content_id, playback_state)
 * - Non-fatal exception recording
 * - Breadcrumb logging
 *
 * Setup:
 * 1. Add google-services.json from Firebase Console to app/
 * 2. Uncomment Firebase plugins in app/build.gradle.kts
 * 3. Call CrashlyticsProvider.initialize() in Application.onCreate()
 */
object CrashlyticsProvider : AppLogger.CrashContextProvider {

    private const val TAG = "Crashlytics"
    private var isInitialized = false

    /**
     * Initialize Crashlytics integration.
     * Call from Application.onCreate() after Firebase is initialized.
     *
     * @return true if initialization succeeded, false otherwise
     */
    fun initialize(): Boolean {
        return try {
            // Check if Firebase/Crashlytics is available
            val crashlytics = FirebaseCrashlytics.getInstance()
            crashlytics.setCrashlyticsCollectionEnabled(true)
            isInitialized = true
            AppLogger.init(this)
            Log.i(TAG, "Crashlytics initialized successfully")
            true
        } catch (e: Exception) {
            // Firebase not configured - that's OK, just disable crash reporting
            Log.w(TAG, "Crashlytics not available (Firebase not configured): ${e.message}")
            isInitialized = false
            AppLogger.init(null)
            false
        }
    }

    override fun setCustomKey(key: String, value: String) {
        if (!isInitialized) return
        try {
            FirebaseCrashlytics.getInstance().setCustomKey(key, value)
        } catch (e: Exception) {
            // Ignore - Crashlytics not available
        }
    }

    override fun setCustomKey(key: String, value: Int) {
        if (!isInitialized) return
        try {
            FirebaseCrashlytics.getInstance().setCustomKey(key, value)
        } catch (e: Exception) {
            // Ignore - Crashlytics not available
        }
    }

    override fun setCustomKey(key: String, value: Boolean) {
        if (!isInitialized) return
        try {
            FirebaseCrashlytics.getInstance().setCustomKey(key, value)
        } catch (e: Exception) {
            // Ignore - Crashlytics not available
        }
    }

    override fun log(message: String) {
        if (!isInitialized) return
        try {
            FirebaseCrashlytics.getInstance().log(message)
        } catch (e: Exception) {
            // Ignore - Crashlytics not available
        }
    }

    override fun recordException(throwable: Throwable) {
        if (!isInitialized) return
        try {
            FirebaseCrashlytics.getInstance().recordException(throwable)
        } catch (e: Exception) {
            // Ignore - Crashlytics not available
        }
    }

    override fun setUserId(userId: String?) {
        if (!isInitialized) return
        try {
            FirebaseCrashlytics.getInstance().setUserId(userId ?: "")
        } catch (e: Exception) {
            // Ignore - Crashlytics not available
        }
    }

    // ============================================
    // Convenience methods for common crash contexts
    // ============================================

    /**
     * Set playback context for crash reports.
     */
    fun setPlaybackContext(
        contentId: String?,
        contentType: String?,
        streamUrl: String?,
        position: Long?,
        duration: Long?
    ) {
        setCustomKey("content_id", contentId ?: "none")
        setCustomKey("content_type", contentType ?: "unknown")
        setCustomKey("stream_url_host", streamUrl?.let { extractHost(it) } ?: "none")
        position?.let { setCustomKey("playback_position", it.toInt()) }
        duration?.let { setCustomKey("playback_duration", it.toInt()) }
    }

    /**
     * Set navigation context for crash reports.
     */
    fun setScreenContext(screenName: String) {
        setCustomKey("screen", screenName)
    }

    /**
     * Set network context for crash reports.
     */
    fun setNetworkContext(networkType: String, isConnected: Boolean) {
        setCustomKey("network_type", networkType)
        setCustomKey("network_connected", isConnected)
    }

    private fun extractHost(url: String): String {
        return try {
            java.net.URL(url).host
        } catch (e: Exception) {
            "invalid_url"
        }
    }
}
