package com.arflix.tv.data.repository

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import retrofit2.HttpException
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

internal fun traktRetryDelayMs(header: String?, fallbackMs: Long, nowMs: Long = System.currentTimeMillis()): Long {
    val seconds = header?.trim()?.toLongOrNull()?.takeIf { it >= 0 }
    val milliseconds = seconds?.let { if (it > Long.MAX_VALUE / 1000) Long.MAX_VALUE else it * 1000 }
        ?: runCatching {
            ZonedDateTime.parse(header, DateTimeFormatter.RFC_1123_DATE_TIME).toInstant().toEpochMilli() - nowMs
        }.getOrNull()
    return maxOf(fallbackMs, milliseconds ?: 0L)
}

/**
 * A poll that never reached Trakt (DNS, timeout, dropped connection) says nothing about the
 * activation. Phones often lose the network for a moment while the user approves the code in a
 * browser, so polling keeps going until the code expires instead of ending the sign-in.
 * A rejected certificate (wrong device clock, intercepting network) will not heal by waiting.
 */
internal fun isTransientTraktPollFailure(error: Throwable): Boolean =
    (error is HttpException && error.code() in 500..599) ||
    error is java.io.IOException &&
        generateSequence<Throwable>(error) { it.cause }.take(8).none { it is java.security.cert.CertificateException }

/** Serialize activation requests and retain the server cooldown even if the dialog is cancelled. */
internal class TraktDeviceActivation(
    private val nowMs: () -> Long = { System.nanoTime() / 1_000_000 },
    private val timeoutMs: Long = 20_000L
) {
    private val mutex = Mutex()
    private var retryAtMs = 0L
    private var rateLimitError: HttpException? = null

    suspend fun <T> request(block: suspend () -> T): T {
        try {
            return withTimeout(timeoutMs) {
                mutex.withLock {
                    // Keep the cooldown, but report it immediately instead of hiding minutes
                    // of delay behind the Connect spinner. Retry never bypasses Trakt's limit.
                    rateLimitError?.let { if (nowMs() < retryAtMs) throw it }
                    try {
                        block().also { rateLimitError = null }
                    } catch (error: HttpException) {
                        if (error.code() == 429) {
                            val now = nowMs()
                            val waitMs = traktRetryDelayMs(error.response()?.headers()?.get("Retry-After"), 60_000L)
                            retryAtMs = now + waitMs.coerceAtMost(Long.MAX_VALUE - now)
                            rateLimitError = error
                        }
                        throw error
                    }
                }
            }
        } catch (error: TimeoutCancellationException) {
            // Ordinary user cancellation still propagates; only our own deadline becomes an error.
            kotlinx.coroutines.currentCoroutineContext().ensureActive()
            throw java.net.SocketTimeoutException("Trakt activation timed out. Please check your connection and try again.")
        }
    }
}
