package com.arflix.tv.network

import okhttp3.Interceptor
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okio.BufferedSource
import okio.ForwardingSource
import okio.buffer
import java.io.IOException
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.ArrayDeque
import java.util.concurrent.atomic.AtomicBoolean

/** For IPTV metadata and preflight clients only. Never throttle video segments. */
internal class IptvProviderRequestGuard(
    private val clock: () -> Long = System::currentTimeMillis,
    private val sleep: (Long) -> Unit = Thread::sleep,
) : Interceptor {
    private class Provider {
        val starts = ArrayDeque<Long>()
        var active = 0
        var nextStart = 0L
        var blockedUntil = 0L
        var playbackBlockedUntil = 0L
        val playbackFailures = LinkedHashMap<String, Long>()
    }

    private val providers = HashMap<String, Provider>()

    private fun provider(url: HttpUrl): Provider = synchronized(providers) {
        val key = url.host + if (url.port in setOf(80, 443)) "" else ":${url.port}"
        providers.getOrPut(key) { Provider() }
    }

    private fun recordResponse(provider: Provider, response: Response, playback: Boolean = false) {
        val cooldown = iptvProviderCooldownMs(response.code, response.header("Retry-After"), clock())
        if (cooldown > 0L) synchronized(provider) {
            provider.blockedUntil = maxOf(provider.blockedUntil, clock() + cooldown)
            if (playback) {
                provider.playbackFailures[response.request.url.toString()] = clock() + cooldown
                while (provider.playbackFailures.size > 256) {
                    provider.playbackFailures.remove(provider.playbackFailures.keys.first())
                }
                if (response.code in setOf(429, 503, 513)) {
                    provider.playbackBlockedUntil = maxOf(provider.playbackBlockedUntil, clock() + cooldown)
                }
            }
        }
    }

    private fun playbackBlockedUntil(provider: Provider, url: HttpUrl): Long = maxOf(
        provider.playbackBlockedUntil, provider.playbackFailures[url.toString()] ?: 0L
    )

    /** Reject known deferrals before OkHttp opens a TCP/TLS connection. */
    fun preflightInterceptor(playback: Boolean = false) = Interceptor { chain ->
        val provider = provider(chain.request().url)
        val paginatedCatalog = chain.request().url.queryParameter("action") == "get_all_channels"
        val queuedAt = clock()
        while (true) {
            if (chain.call().isCanceled()) throw IOException("IPTV request cancelled")
            val waitForCatalog = synchronized(provider) {
                val now = clock()
                val blockedUntil = if (playback) playbackBlockedUntil(provider, chain.request().url) else provider.blockedUntil
                if (now < blockedUntil) throw IptvProviderRequestDeferredException()
                val atQuota = !playback && provider.starts.count { now - it < 60_000L } >= 30
                if (atQuota && (!paginatedCatalog || now - queuedAt >= 75_000L)) {
                    throw IptvProviderRequestDeferredException()
                }
                atQuota
            }
            if (!waitForCatalog) break
            // A portal catalog may legitimately span hundreds of pages. Wait
            // before connecting, rather than truncating it at the rate limit.
            pause(100L)
        }
        chain.proceed(chain.request())
    }

    private fun pause(durationMs: Long) {
        try { sleep(durationMs) } catch (error: InterruptedException) {
            Thread.currentThread().interrupt()
            throw IOException("IPTV request interrupted", error)
        }
    }

    /** Observe provider blocks without pacing or limiting healthy media traffic. */
    fun playbackInterceptor() = Interceptor { chain ->
        val provider = provider(chain.request().url)
        synchronized(provider) {
            if (clock() < playbackBlockedUntil(provider, chain.request().url)) throw IptvProviderRequestDeferredException()
        }
        chain.proceed(chain.request()).also { recordResponse(provider, it, playback = true) }
    }

    @Suppress("SENSELESS_COMPARISON") // Play and sideload resolve different OkHttp body nullability.
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
        // Share the budget across accounts, profiles and default HTTP/HTTPS ports.
        val provider = provider(url)
        val queuedAt = clock()
        while (true) {
            if (chain.call().isCanceled()) throw IOException("IPTV request cancelled")
            val waitMs = synchronized(provider) {
                val now = clock()
                while (provider.starts.isNotEmpty() && now - provider.starts.first >= 60_000L) {
                    provider.starts.removeFirst()
                }
                if (now < provider.blockedUntil || provider.starts.size >= 30) {
                    throw IptvProviderRequestDeferredException()
                }
                if (provider.active < 2 && now >= provider.nextStart) {
                    provider.active++
                    provider.starts.addLast(now)
                    provider.nextStart = now + 500L
                    0L
                } else {
                    if (now - queuedAt >= 5_000L) throw IptvProviderRequestDeferredException()
                    if (provider.active >= 2) 50L else (provider.nextStart - now).coerceIn(1L, 100L)
                }
            }
            if (waitMs == 0L) break
            pause(waitMs)
        }
        val released = AtomicBoolean(false)
        fun release() {
            if (released.compareAndSet(false, true)) synchronized(provider) { provider.active-- }
        }
        try {
            val response = chain.proceed(chain.request())
            recordResponse(provider, response)
            val body = response.body
            if (body == null) {
                release()
                return response
            }
            // A returned response can still be downloading 100 MB of XMLTV.
            val source = object : ForwardingSource(body.source()) {
                override fun close() { try { super.close() } finally { release() } }
            }.buffer()
            return response.newBuilder().body(object : ResponseBody() {
                override fun contentType() = body.contentType()
                override fun contentLength() = body.contentLength()
                override fun source(): BufferedSource = source
            }).build()
        } catch (error: Throwable) {
            release()
            throw error
        }
    }

    companion object { val shared = IptvProviderRequestGuard() }
}

internal class IptvProviderRequestDeferredException : IOException(
    "IPTV provider requests paused temporarily. Please wait before refreshing again."
)

internal fun isIptvProviderRequestPaused(error: Throwable): Boolean =
    generateSequence(error) { it.cause }.take(16).any { it is IptvProviderRequestDeferredException }

internal fun iptvProviderCooldownMs(status: Int, retryAfter: String?, nowMs: Long): Long {
    val baseline = when (status) {
        401, 403, 451 -> 15 * 60_000L
        429, 503, 513 -> 5 * 60_000L
        in 500..599 -> 60_000L
        else -> return 0L
    }
    val requested = retryAfter?.trim()?.let { value ->
        value.toLongOrNull()?.coerceIn(0, 86_400)?.times(1000L)
            ?: runCatching {
                (ZonedDateTime.parse(value, DateTimeFormatter.RFC_1123_DATE_TIME)
                    .toInstant().toEpochMilli() - nowMs).coerceIn(0L, 86_400_000L)
            }.getOrNull()
    } ?: 0L
    return maxOf(baseline, requested)
}

internal fun OkHttpClient.Builder.withIptvProviderRequestGuard(): OkHttpClient.Builder = apply {
    retryOnConnectionFailure(false)
    addInterceptor(IptvProviderRequestGuard.shared.preflightInterceptor())
    // Network interception also counts redirects and OkHttp's HTTP follow-ups.
    addNetworkInterceptor(IptvProviderRequestGuard.shared)
}
