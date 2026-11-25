package com.arflix.tv.data.repository

/** How long to wait after a refresh failed for a network or server reason. */
internal const val REFRESH_RETRY_MS = 30_000L

/**
 * Decides whether a session refresh may be attempted.
 *
 * A refresh token the server rejected (400/401/403) can never succeed, so it is
 * not sent again; logging in stores a new token, which is allowed straight away.
 * Network and server failures wait [REFRESH_RETRY_MS] before the next attempt.
 */
internal class RefreshGate {
    @Volatile private var rejectedToken: String? = null
    @Volatile private var retryAfterMillis = 0L

    fun mayRefresh(refreshToken: String?, nowMillis: Long): Boolean {
        if (refreshToken.isNullOrBlank()) return false
        if (refreshToken == rejectedToken) return false
        return nowMillis >= retryAfterMillis
    }

    fun isRejected(refreshToken: String): Boolean = refreshToken == rejectedToken

    /** [httpCode] is null for network errors and exceptions. */
    fun onFailure(refreshToken: String, httpCode: Int?, nowMillis: Long) {
        if (httpCode == 400 || httpCode == 401 || httpCode == 403) {
            rejectedToken = refreshToken
        } else {
            retryAfterMillis = nowMillis + REFRESH_RETRY_MS
        }
    }
}
