package com.arflix.tv.network

import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
import java.net.InetAddress
import java.net.ServerSocket
import kotlin.concurrent.thread
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

class IptvProviderRequestGuardTest {
    private class Fixture(var status: Int = 200, var retryAfter: String? = null) : AutoCloseable {
        val clock = AtomicLong(1_000L)
        val hits = AtomicInteger()
        val connections = AtomicInteger()
        val server = ServerSocket(0, 10, InetAddress.getByName("127.0.0.1"))
        private val serving = thread(isDaemon = true, name = "iptv-provider-fixture") {
            while (!server.isClosed) {
                val socket = try { server.accept() } catch (_: IOException) { break }
                connections.incrementAndGet()
                socket.use {
                    it.soTimeout = 5_000
                    val input = it.getInputStream().bufferedReader()
                    if (input.readLine() == null) return@use
                    while (!input.readLine().isNullOrEmpty()) { }
                    hits.incrementAndGet()
                    val retry = retryAfter?.let { value -> "Retry-After: $value\r\n" }.orEmpty()
                    it.getOutputStream().write(
                        "HTTP/1.1 $status Test\r\nContent-Length: 2\r\nConnection: close\r\n${retry}\r\n{}".toByteArray()
                    )
                }
            }
        }
        val guard = IptvProviderRequestGuard(clock::get) { clock.addAndGet(it) }
        val client = OkHttpClient.Builder().retryOnConnectionFailure(false)
            .addInterceptor(guard.preflightInterceptor()).addNetworkInterceptor(guard).build()
        fun call(path: String = "guide") = client.newCall(Request.Builder()
            .url("http://127.0.0.1:${server.localPort}/$path").build()).execute()
        override fun close() {
            server.close()
            serving.join(5_000)
            client.connectionPool.evictAll()
            client.dispatcher.executorService.shutdownNow()
        }
    }

    @Test fun fiveThousandQueuedGuideRequestsCannotTurnIntoAProviderScan() {
        Fixture().use { f ->
            var admitted = 0
            repeat(5_000) { i ->
                try { f.call("player_api.php?action=get_short_epg&stream_id=$i").use { admitted++ } }
                catch (_: IptvProviderRequestDeferredException) { }
            }
            assertEquals(30, admitted)
            assertEquals(30, f.hits.get())
            assertEquals(30, f.connections.get())
            assertEquals(15_500L, f.clock.get())
            f.clock.addAndGet(60_001L)
            f.call().close()
            assertEquals(31, f.hits.get())
        }
    }

    @Test fun bodyLifetimeLimitsSimultaneousXmlAndCatalogDownloads() {
        Fixture().use { f ->
            val first = f.call("xmltv.php")
            val second = f.call("get.php")
            assertThrows(IptvProviderRequestDeferredException::class.java) { f.call("player_api.php") }
            assertEquals(2, f.hits.get())
            first.close()
            first.close()
            f.call("player_api.php").close()
            assertEquals(3, f.hits.get())
            second.close()
        }
    }

    @Test fun paginatedCatalogWaitsForBudgetWithoutDroppingItsRemainingPages() {
        Fixture().use { f ->
            repeat(65) { page -> f.call("server/load.php?action=get_all_channels&p=$page").close() }
            assertEquals(65, f.hits.get())
            assertTrue(f.clock.get() >= 121_000L)
        }
    }

    @Test fun blocksAndServerOverloadStopEveryMetadataFallback() {
        listOf(401, 403, 429, 451, 500, 503, 513).forEach { status ->
            Fixture(status).use { f ->
                f.call("player_api.php").close()
                listOf("get.php", "xmltv.php", "timeshift.php", "server/load.php").forEach { path ->
                    assertThrows(IptvProviderRequestDeferredException::class.java) { f.call(path) }
                }
                assertEquals("HTTP $status", 1, f.hits.get())
            }
        }
    }

    @Test fun okHttpCannotImmediatelyRepeat503WithRetryAfterZero() {
        Fixture(503, "0").use { f ->
            runCatching { f.call().close() }
            assertEquals(1, f.hits.get())
        }
    }

    @Test fun repeatedErrorDoesNotShortenRetryAfter() {
        Fixture(429, "1200").use { f ->
            f.call().close()
            f.status = 200
            f.clock.addAndGet(600_000L)
            assertThrows(IOException::class.java) { f.call() }
            f.clock.addAndGet(600_001L)
            f.call().close()
            assertEquals(2, f.hits.get())
        }
    }

    @Test fun parsesHttpDateRetryAfterAndRejectsInvalidOrNegativeDelays() {
        assertEquals(1_800_000L, iptvProviderCooldownMs(429, "Thu, 1 Jan 1970 00:30:00 GMT", 0))
        assertEquals(300_000L, iptvProviderCooldownMs(429, "invalid", 0))
        assertEquals(900_000L, iptvProviderCooldownMs(403, "-2", 0))
        assertEquals(0L, iptvProviderCooldownMs(200, "3600", 0))
    }

    @Test fun healthyPlaybackIsUnthrottledButPlaybackBlocksAlsoStopMetadata() {
        Fixture().use { f ->
            val playback = f.client.newBuilder().apply { interceptors().clear(); networkInterceptors().clear() }
                .addInterceptor(f.guard.preflightInterceptor(playback = true))
                .addNetworkInterceptor(f.guard.playbackInterceptor()).build()
            fun segment() = playback.newCall(Request.Builder()
                .url("http://127.0.0.1:${f.server.localPort}/hls/segment.ts").build()).execute()
            repeat(100) { segment().close() }
            assertEquals(100, f.hits.get())
            assertEquals(1_000L, f.clock.get())
            f.status = 429
            segment().close()
            assertThrows(IptvProviderRequestDeferredException::class.java) { segment() }
            assertThrows(IptvProviderRequestDeferredException::class.java) { f.call("xmltv.php") }
            assertEquals(101, f.hits.get())
        }
    }

    @Test fun failedGuideDoesNotInterruptHealthyVideo() {
        Fixture(503).use { f ->
            f.call("xmltv.php").close()
            f.status = 200
            val playback = f.client.newBuilder().apply { interceptors().clear(); networkInterceptors().clear() }
                .addInterceptor(f.guard.preflightInterceptor(playback = true))
                .addNetworkInterceptor(f.guard.playbackInterceptor()).build()
            playback.newCall(Request.Builder().url("http://127.0.0.1:${f.server.localPort}/segment.ts").build())
                .execute().close()
            assertEquals(2, f.hits.get())
            assertThrows(IptvProviderRequestDeferredException::class.java) { f.call("xmltv.php") }
        }
    }

    @Test fun oneForbiddenChannelDoesNotPreventSelectingAnotherChannel() {
        Fixture(403).use { f ->
            val playback = f.client.newBuilder().apply { interceptors().clear(); networkInterceptors().clear() }
                .addInterceptor(f.guard.preflightInterceptor(playback = true))
                .addNetworkInterceptor(f.guard.playbackInterceptor()).build()
            fun channel(id: Int) = playback.newCall(Request.Builder()
                .url("http://127.0.0.1:${f.server.localPort}/live/$id.ts").build()).execute()
            channel(1).close()
            assertThrows(IptvProviderRequestDeferredException::class.java) { channel(1) }
            f.status = 200
            channel(2).close()
            assertEquals(2, f.hits.get())
        }
    }
}
