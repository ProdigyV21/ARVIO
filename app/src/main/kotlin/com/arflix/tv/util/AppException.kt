package com.arflix.tv.util

/**
 * Sealed class hierarchy for application exceptions.
 *
 * This provides type-safe error handling with distinct categories:
 * - [Network]: Connectivity issues (no internet, timeout, SSL)
 * - [Auth]: Authentication/authorization failures (expired token, denied)
 * - [Server]: HTTP errors from the server (4xx, 5xx)
 * - [Unknown]: Unexpected errors
 *
 * Each exception includes:
 * - [message]: User-friendly message to display
 * - [cause]: Original exception for logging/debugging
 * - [errorCode]: Optional code for support troubleshooting
 */
sealed class AppException(
    override val message: String,
    override val cause: Throwable? = null,
    open val errorCode: String? = null
) : Exception(message, cause) {

    /**
     * Network-related errors (no connection, timeout, SSL issues).
     *
     * These are typically recoverable by retrying or when connectivity is restored.
     */
    data class Network(
        override val message: String,
        override val cause: Throwable? = null,
        override val errorCode: String? = "ERR_NETWORK"
    ) : AppException(message, cause, errorCode) {
        companion object {
            val NO_CONNECTION = Network("No internet connection", errorCode = "ERR_NO_CONNECTION")
            val TIMEOUT = Network("Connection timed out", errorCode = "ERR_TIMEOUT")
            val SSL_ERROR = Network("Secure connection failed", errorCode = "ERR_SSL")
        }
    }

    /**
     * Authentication/authorization errors.
     *
     * These typically require user action (re-login, different credentials).
     */
    data class Auth(
        override val message: String,
        override val cause: Throwable? = null,
        override val errorCode: String? = "ERR_AUTH"
    ) : AppException(message, cause, errorCode) {
        companion object {
            val SESSION_EXPIRED = Auth("Session expired. Please sign in again.", errorCode = "ERR_SESSION_EXPIRED")
            val INVALID_CREDENTIALS = Auth("Invalid email or password", errorCode = "ERR_INVALID_CREDENTIALS")
            val ACCESS_DENIED = Auth("Access denied", errorCode = "ERR_ACCESS_DENIED")
            val TOKEN_INVALID = Auth("Invalid authentication token", errorCode = "ERR_TOKEN_INVALID")
        }
    }

    /**
     * Server-side errors (HTTP 4xx, 5xx responses).
     *
     * Includes the HTTP status code for debugging.
     */
    data class Server(
        override val message: String,
        val httpCode: Int,
        override val cause: Throwable? = null,
        override val errorCode: String? = "ERR_SERVER"
    ) : AppException(message, cause, errorCode) {
        companion object {
            fun notFound(resource: String = "Resource") = Server("$resource not found", 404, errorCode = "ERR_NOT_FOUND")
            fun internalError() = Server("Server error. Please try again later.", 500, errorCode = "ERR_SERVER_INTERNAL")
            fun serviceUnavailable() = Server("Service temporarily unavailable", 503, errorCode = "ERR_SERVICE_UNAVAILABLE")
        }
    }

    /**
     * Unexpected/unknown errors.
     *
     * Used as a fallback when the error doesn't fit other categories.
     */
    data class Unknown(
        override val message: String,
        override val cause: Throwable? = null,
        override val errorCode: String? = "ERR_UNKNOWN"
    ) : AppException(message, cause, errorCode)

    /**
     * Returns a formatted error string for support troubleshooting.
     *
     * Example: "No internet connection [ERR_NO_CONNECTION]"
     */
    fun toSupportString(): String {
        return if (errorCode != null) {
            "$message [$errorCode]"
        } else {
            message
        }
    }

    /**
     * Returns true if this error is potentially recoverable by retrying.
     */
    fun isRetryable(): Boolean = when (this) {
        is Network -> true
        is Server -> httpCode in 500..599
        is Auth -> false
        is Unknown -> false
    }
}
