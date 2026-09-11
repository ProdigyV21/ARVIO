package com.arflix.tv.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import java.net.URI

/** Shared across all EPG batches; a new viewport must not create a new request budget. */
internal class IptvGuideRequestBudget(private val clock: () -> Long = System::currentTimeMillis) {
    private class Provider {
        val permits = Semaphore(2)
        val lock = Mutex()
        var nextStart = 0L
        @Volatile var blockedUntil = 0L
        val attempts = LinkedHashMap<String, Long>()
    }

    private val providers = HashMap<String, Provider>()
    private fun provider(url: String): Provider = synchronized(providers) {
        val uri = URI(url)
        providers.getOrPut("${uri.scheme}://${uri.authority}") { Provider() }
    }

    suspend fun <T> request(url: String, block: suspend () -> T?): T? {
        val provider = provider(url)
        return provider.permits.withPermit {
            val allowed = provider.lock.withLock {
                if (clock() < provider.blockedUntil) return@withLock false
                delay((provider.nextStart - clock()).coerceAtLeast(0))
                if (clock() < provider.blockedUntil) return@withLock false
                val now = clock()
                if (provider.attempts[url]?.let { now - it < 120_000L } == true) return@withLock false
                provider.nextStart = clock() + 250L
                provider.attempts[url] = now
                while (provider.attempts.size > 2_000) provider.attempts.remove(provider.attempts.keys.first())
                true
            }
            if (allowed) block() else null
        }
    }

    fun onResponse(url: String, status: Int, retryAfterSeconds: Long? = null) {
        val cooldown = when (status) {
            401, 403 -> 5 * 60_000L
            429, 503, 513 -> (retryAfterSeconds?.coerceIn(30, 900) ?: 60) * 1000L
            else -> return
        }
        val provider = provider(url)
        provider.blockedUntil = maxOf(provider.blockedUntil, clock() + cooldown)
    }
}
