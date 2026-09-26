package com.arflix.tv.data.repository

import kotlinx.coroutines.delay
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
 */
internal fun isTransientTraktPollFailure(error: Throwable): Boolean = error is java.io.IOException

/** Serialize activation requests and retain the server cooldown even if the dialog is cancelled. */
internal class TraktDeviceActivation(
    private val nowMs: () -> Long = { System.nanoTime() / 1_000_000 },
    private val wait: suspend (Long) -> Unit = { delay(it) }
) {
    private val mutex = Mutex()
    private var retryAtMs = 0L

    suspend fun <T> request(block: suspend () -> T): T = mutex.withLock {
        var retries = 0
        while (true) {
            val remaining = retryAtMs - nowMs()
            if (remaining > 0) wait(remaining)
            try {
                return@withLock block()
            } catch (error: HttpException) {
                if (error.code() != 429) throw error
                val waitMs = traktRetryDelayMs(error.response()?.headers()?.get("Retry-After"), 60_000L)
                retryAtMs = nowMs() + waitMs.coerceAtMost(Long.MAX_VALUE - nowMs())
                if (++retries >= 3) throw error
            }
        }
        @Suppress("UNREACHABLE_CODE")
        error("Unreachable")
    }
}
