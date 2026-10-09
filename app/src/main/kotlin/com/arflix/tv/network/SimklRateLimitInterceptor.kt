package com.arflix.tv.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.InterruptedIOException

// Tracking and custom lists share the same per-app/account request budget.
internal val sharedSimklRateLimiter = SimklRateLimitInterceptor()

internal class SimklRateLimitInterceptor(
    private val nowMs: () -> Long = { System.nanoTime() / 1_000_000L },
    private val sleepMs: (Long) -> Unit = { Thread.sleep(it) },
) : Interceptor {
    private val lock = Any()
    private var nextRequestAt = Long.MIN_VALUE
    private var backoffUntil = Long.MIN_VALUE

    private fun awaitPermit(chain: Interceptor.Chain) {
        while (true) {
            if (chain.call().isCanceled()) throw InterruptedIOException("Canceled")
            val wait = synchronized(lock) {
                val now = nowMs()
                val deadline = maxOf(nextRequestAt, backoffUntil)
                if (now >= deadline) {
                    nextRequestAt = now + 1_000L
                    0L
                } else deadline - now
            }
            if (wait == 0L) return
            try {
                // Recheck cancellation and new backoff deadlines while waiting.
                sleepMs(wait.coerceAtMost(100L))
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                throw InterruptedIOException("Interrupted waiting for Simkl").apply { initCause(e) }
            }
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var retried = false
        while (true) {
            awaitPermit(chain)
            val response = chain.proceed(chain.request())
            if (response.code != 429) return response
            val seconds = (response.header("Retry-After")?.toLongOrNull() ?: 5L).coerceAtLeast(1L)
            synchronized(lock) {
                val now = nowMs()
                val delay = seconds.coerceAtMost((Long.MAX_VALUE - now.coerceAtLeast(0L)) / 1_000L) * 1_000L
                backoffUntil = maxOf(backoffUntil, now + delay)
            }
            // Record the final response's backoff too, but leave it open for the caller.
            if (retried || chain.request().body?.isOneShot() == true) return response
            response.close()
            retried = true
        }
    }
}
