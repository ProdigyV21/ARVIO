package com.arflix.tv.util

import android.util.Log
import com.arflix.tv.BuildConfig
import java.security.MessageDigest

/**
 * Application-wide logging utility with PII protection.
 *
 * Features:
 * - Automatic PII sanitization (emails, tokens, API keys)
 * - Debug logs stripped in release via R8 (see proguard-rules.pro)
 * - Crash context logging for diagnostics
 * - Consistent tag formatting
 *
 * Usage:
 * ```kotlin
 * AppLogger.d("Auth", "User signed in: ${email.sanitizeEmail()}")
 * AppLogger.e("Network", "API call failed", exception)
 * AppLogger.setCrashContext("user_id", userId.hash())
 * ```
 */
object AppLogger {

    private const val TAG_PREFIX = "Arflix"
    private var crashContextProvider: CrashContextProvider? = null

    /**
     * Interface for crash reporting integration.
     * Implement with Firebase Crashlytics or other crash reporting service.
     */
    interface CrashContextProvider {
        fun setCustomKey(key: String, value: String)
        fun setCustomKey(key: String, value: Int)
        fun setCustomKey(key: String, value: Boolean)
        fun log(message: String)
        fun recordException(throwable: Throwable)
        fun setUserId(userId: String?)
    }

    /**
     * Initialize crash reporting integration.
     */
    fun init(provider: CrashContextProvider?) {
        crashContextProvider = provider
    }

    // ============================================
    // Standard logging methods (stripped in release)
    // ============================================

    /**
     * Verbose log - stripped in release builds.
     */
    fun v(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
        }
    }

    /**
     * Debug log - stripped in release builds.
     */
    fun d(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
        }
    }

    /**
     * Info log - stripped in release builds.
     */
    fun i(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
        }
    }

    /**
     * Warning log - kept in release for diagnostics.
     */
    fun w(tag: String, message: String, throwable: Throwable? = null) {
        val sanitized = sanitize(message)
        crashContextProvider?.log("W/$tag: $sanitized")
    }

    /**
     * Error log - kept in release, also sent to crash reporter.
     */
    fun e(tag: String, message: String, throwable: Throwable? = null) {
        val sanitized = sanitize(message)
        crashContextProvider?.log("E/$tag: $sanitized")
        throwable?.let { crashContextProvider?.recordException(it) }
    }

    // ============================================
    // Crash context methods
    // ============================================

    /**
     * Set a crash context key-value pair.
     * These appear in crash reports for debugging.
     */
    fun setCrashContext(key: String, value: String) {
        crashContextProvider?.setCustomKey(key, sanitize(value))
    }

    fun setCrashContext(key: String, value: Int) {
        crashContextProvider?.setCustomKey(key, value)
    }

    fun setCrashContext(key: String, value: Boolean) {
        crashContextProvider?.setCustomKey(key, value)
    }

    /**
     * Set the user ID for crash reports.
     * Should be a hash, not the actual user ID.
     */
    fun setUserId(userId: String?) {
        crashContextProvider?.setUserId(userId?.hash())
    }

    /**
     * Record a non-fatal exception for crash reporting.
     */
    fun recordException(throwable: Throwable, context: Map<String, String> = emptyMap()) {
        context.forEach { (key, value) ->
            crashContextProvider?.setCustomKey(key, sanitize(value))
        }
        crashContextProvider?.recordException(throwable)
    }

    // ============================================
    // PII Sanitization
    // ============================================

    private val EMAIL_PATTERN = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    private val TOKEN_PATTERN = Regex("(token|jwt|bearer|api[_-]?key|secret)[\"':\\s=]+([a-zA-Z0-9._-]{20,})", RegexOption.IGNORE_CASE)
    private val LONG_HEX_PATTERN = Regex("[a-fA-F0-9]{32,}")

    /**
     * Sanitize a message by removing/masking PII.
     */
    private fun sanitize(message: String): String {
        var result = message

        // Mask emails: user@example.com -> u***@***.com
        result = EMAIL_PATTERN.replace(result) { match ->
            val email = match.value
            val atIndex = email.indexOf('@')
            val dotIndex = email.lastIndexOf('.')
            if (atIndex > 0 && dotIndex > atIndex) {
                "${email[0]}***@***.${email.substring(dotIndex + 1)}"
            } else {
                "[EMAIL]"
            }
        }

        // Mask tokens/API keys: show only first 4 chars
        result = TOKEN_PATTERN.replace(result) { match ->
            val prefix = match.groupValues[1]
            val token = match.groupValues[2]
            val masked = if (token.length > 4) "${token.take(4)}***" else "***"
            "$prefix:$masked"
        }

        // Mask long hex strings (likely hashes/tokens): show first 8
        result = LONG_HEX_PATTERN.replace(result) { match ->
            val hex = match.value
            if (hex.length > 8) "${hex.take(8)}..." else hex
        }

        return result
    }

    private fun formatTag(tag: String): String {
        return "$TAG_PREFIX.$tag"
    }
}

// ============================================
// Extension functions for PII-safe logging
// ============================================

/**
 * Hash a string for safe logging (e.g., user IDs).
 * Uses first 12 chars of SHA-256 hash.
 */
fun String.hash(): String {
    return try {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(this.toByteArray())
        hash.take(6).joinToString("") { "%02x".format(it) }
    } catch (e: Exception) {
        "hash_error"
    }
}

/**
 * Sanitize an email for logging: user@example.com -> u***@***.com
 */
fun String.sanitizeEmail(): String {
    val atIndex = indexOf('@')
    val dotIndex = lastIndexOf('.')
    return if (atIndex > 0 && dotIndex > atIndex) {
        "${this[0]}***@***.${substring(dotIndex + 1)}"
    } else {
        "[EMAIL]"
    }
}

/**
 * Mask a token/key for logging: show only first 4 characters.
 */
fun String.maskToken(): String {
    return if (length > 4) "${take(4)}***" else "***"
}
