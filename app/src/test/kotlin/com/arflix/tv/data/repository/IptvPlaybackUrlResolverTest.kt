package com.arflix.tv.data.repository

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class IptvPlaybackUrlResolverTest {
    @Test fun `cancelled tune closes the probe without issuing GET fallback`() = runBlocking {
        val server = java.net.ServerSocket(0)
        val requestStarted = java.util.concurrent.CountDownLatch(1)
        val release = java.util.concurrent.CountDownLatch(1)
        val callRef = java.util.concurrent.atomic.AtomicReference<okhttp3.Call>()
        val worker = Thread {
            server.accept().use { socket ->
                val reader = socket.getInputStream().bufferedReader()
                while (!reader.readLine().isNullOrEmpty()) { }
                requestStarted.countDown()
                release.await(10, java.util.concurrent.TimeUnit.SECONDS)
            }
        }.apply { isDaemon = true; start() }
        val calls = AtomicInteger()
        val client = OkHttpClient.Builder().eventListenerFactory { call ->
            calls.incrementAndGet(); callRef.set(call); okhttp3.EventListener.NONE
        }.build()
        try {
            val resolver = IptvPlaybackUrlResolver(client)
            val job = launch(kotlinx.coroutines.Dispatchers.Default) {
                resolver.resolve("http://127.0.0.1:${server.localPort}/opaque-channel", emptyMap())
            }
            assertThat(requestStarted.await(3, java.util.concurrent.TimeUnit.SECONDS)).isTrue()
            kotlinx.coroutines.withTimeout(1500) { job.cancel(); job.join() }
            assertThat(callRef.get().isCanceled()).isTrue()
            assertThat(calls.get()).isEqualTo(1)
        } finally {
            release.countDown(); server.close(); worker.join(2000)
            client.dispatcher.executorService.shutdownNow(); client.connectionPool.evictAll()
        }
    }
    @Test fun `observed format skips future failures without leaking across authorization headers`() = runBlocking {
        val resolver = IptvPlaybackUrlResolver(OkHttpClient.Builder().addInterceptor { error("No probe should be sent") }.build())
        val url = "https://provider.test/live/123.ts"
        resolver.rememberHls(url, mapOf("Authorization" to "first"), url)
        assertThat(resolver.resolve(url, mapOf("authorization" to "first")).isHls).isTrue()
        assertThat(resolver.resolve(url, mapOf("Authorization" to "second")).isHls).isFalse()
    }
    @Test fun `expired redirect can refresh once for both not found responses`() = runBlocking {
        for (status in listOf(404, 410)) {
            val calls = AtomicInteger()
            val resolver = IptvPlaybackUrlResolver(OkHttpClient.Builder().addInterceptor { chain ->
                val suffix = if (calls.incrementAndGet() == 1) "expired" else "fresh"
                Response.Builder()
                    .request(chain.request().newBuilder().url("https://cdn.test/$suffix.m3u8").build())
                    .protocol(Protocol.HTTP_1_1).code(200).message("OK")
                    .header("Content-Type", "application/vnd.apple.mpegurl")
                    .body("".toResponseBody()).build()
            }.build())
            val url = "https://provider.test/live/channel-slug"
            assertThat(resolver.resolve(url, emptyMap()).url).isEqualTo("https://cdn.test/expired.m3u8")
            assertThat(resolver.resolve(url, emptyMap()).url).isEqualTo("https://cdn.test/expired.m3u8")
            assertThat(com.arflix.tv.ui.screens.tv.live.shouldRetryLiveTvPlayback(status, 1, 3, false)).isTrue()
            val refreshed = resolver.resolve(url, emptyMap(), forceRefresh = true,
                probeKnownUrl = com.arflix.tv.ui.screens.tv.live.isMissingPlaybackResource(status))
            assertThat(refreshed.url).isEqualTo("https://cdn.test/fresh.m3u8")
            assertThat(calls.get()).isEqualTo(2)
        }
    }

    @Test fun `failed numeric stream can explicitly recover an HLS content type`() = runBlocking {
        val calls = AtomicInteger()
        val resolver = IptvPlaybackUrlResolver(OkHttpClient.Builder().addInterceptor { chain ->
            calls.incrementAndGet()
            Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1)
                .code(200).message("OK").header("Content-Type", "application/vnd.apple.mpegurl")
                .body("".toResponseBody()).build()
        }.build())
        val url = "https://provider.test/live/user/pass/123.ts"
        assertThat(resolver.resolve(url, emptyMap()).isHls).isFalse()
        assertThat(calls.get()).isEqualTo(0)
        assertThat(resolver.resolve(url, emptyMap(), forceRefresh = true, probeKnownUrl = true).isHls).isTrue()
        assertThat(calls.get()).isEqualTo(1)
        repeat(3) {
            assertThat(resolver.resolve(url, emptyMap()).isHls).isTrue()
        }
        assertThat(calls.get()).isEqualTo(1)
        assertThat(resolver.resolve(url, emptyMap(), forceRefresh = true, probeKnownUrl = true).isHls).isTrue()
        assertThat(calls.get()).isEqualTo(2)
    }

    @Test fun `HTML error redirect is not cached as a media target`() = runBlocking {
        val calls = AtomicInteger()
        val resolver = IptvPlaybackUrlResolver(OkHttpClient.Builder().addInterceptor { chain ->
            calls.incrementAndGet()
            Response.Builder().request(chain.request().newBuilder().url("https://provider.test/error").build())
                .protocol(Protocol.HTTP_1_1).code(403).message("Forbidden")
                .header("Content-Type", "text/html").body("Denied".toResponseBody()).build()
        }.build())
        val url = "https://provider.test/live/user/pass/channel-slug"
        repeat(2) { assertThat(resolver.resolve(url, emptyMap()).url).isEqualTo(url) }
        assertThat(calls.get()).isEqualTo(1)
    }

    @Test
    fun `extensionless slug live URL resolves redirect and HLS type`() = runBlocking {
        val calls = AtomicInteger()
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                calls.incrementAndGet()
                val finalRequest = chain.request().newBuilder()
                    .url("http://provider.test/hls/animal-planet/index.m3u8")
                    .build()
                Response.Builder()
                    .request(finalRequest)
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .message("OK")
                    .header("Content-Type", "application/x-mpegURL")
                    .body("".toResponseBody("application/x-mpegURL".toMediaType()))
                    .build()
            }
            .build()
        val resolver = IptvPlaybackUrlResolver(client)

        val first = resolver.resolve(
            rawUrl = "http://provider.test/live/user/pass/animal-planet",
            headers = emptyMap(),
        )
        val cached = resolver.resolve(
            rawUrl = "http://provider.test/live/user/pass/animal-planet",
            headers = emptyMap(),
        )

        assertThat(first.url).isEqualTo("http://provider.test/hls/animal-planet/index.m3u8")
        assertThat(first.isHls).isTrue()
        assertThat(cached).isEqualTo(first)
        assertThat(calls.get()).isEqualTo(1)
    }

    @Test
    fun `HLS content type is retained when redirect target has no extension`() = runBlocking {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                Response.Builder()
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .message("OK")
                    .header("Content-Type", "application/vnd.apple.mpegurl")
                    .body("".toResponseBody("application/vnd.apple.mpegurl".toMediaType()))
                    .build()
            }
            .build()
        val resolver = IptvPlaybackUrlResolver(client)

        val target = resolver.resolve(
            rawUrl = "http://provider.test/live/user/pass/channel-slug",
            headers = emptyMap(),
        )

        assertThat(target.url).isEqualTo("http://provider.test/live/user/pass/channel-slug")
        assertThat(target.isHls).isTrue()
    }

    @Test
    fun `GET probe detects HLS body when provider rejects HEAD`() = runBlocking {
        val calls = AtomicInteger()
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val call = calls.incrementAndGet()
                if (chain.request().method == "HEAD") {
                    Response.Builder()
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_1)
                        .code(405)
                        .message("Method Not Allowed")
                        .header("Content-Type", "text/html")
                        .body("".toResponseBody("text/html".toMediaType()))
                        .build()
                } else {
                    Response.Builder()
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_1)
                        .code(200)
                        .message("OK")
                        .header("Content-Type", "text/plain")
                        .body("#EXTM3U\n#EXT-X-VERSION:3".toResponseBody("text/plain".toMediaType()))
                        .build()
                }.also {
                    assertThat(call).isAtMost(2)
                }
            }
            .build()
        val resolver = IptvPlaybackUrlResolver(client)

        val target = resolver.resolve(
            rawUrl = "http://provider.test/live/user/pass/channel-slug",
            headers = emptyMap(),
        )

        assertThat(target.isHls).isTrue()
        assertThat(calls.get()).isEqualTo(2)
    }

    @Test
    fun `single segment token URL is probed and keeps the stated transport stream type`() = runBlocking {
        val calls = AtomicInteger()
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                calls.incrementAndGet()
                Response.Builder()
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .message("OK")
                    .header("Content-Type", "video/mp2t")
                    .body("".toResponseBody("video/mp2t".toMediaType()))
                    .build()
            }
            .build()
        val resolver = IptvPlaybackUrlResolver(client)

        val target = resolver.resolve(
            rawUrl = "http://provider.test/N2Q4ZjhiMGEyYzFlNGY2ZA",
            headers = emptyMap(),
        )

        assertThat(target.isHls).isFalse()
        assertThat(target.mimeType).isEqualTo("video/mp2t")
        assertThat(calls.get()).isEqualTo(1)
    }

    @Test
    fun `probed HLS target carries no transport stream mime type`() = runBlocking {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                Response.Builder()
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .message("OK")
                    .header("Content-Type", "application/vnd.apple.mpegurl")
                    .body("".toResponseBody("application/vnd.apple.mpegurl".toMediaType()))
                    .build()
            }
            .build()
        val resolver = IptvPlaybackUrlResolver(client)

        val target = resolver.resolve(
            rawUrl = "http://provider.test/live/user/pass/channel-slug",
            headers = emptyMap(),
        )

        assertThat(target.isHls).isTrue()
        assertThat(target.mimeType).isNull()
    }

    @Test
    fun `known direct and adaptive URLs skip redirect probe`() = runBlocking {
        val client = OkHttpClient.Builder()
            .addInterceptor { error("Redirect probe should not run") }
            .build()
        val resolver = IptvPlaybackUrlResolver(client)

        val numericXtream = resolver.resolve(
            rawUrl = "http://provider.test/live/user/pass/12345",
            headers = emptyMap(),
        )
        val transportStream = resolver.resolve(
            rawUrl = "http://provider.test/live/user/pass/channel.ts",
            headers = emptyMap(),
        )
        val hls = resolver.resolve(
            rawUrl = "http://provider.test/hls/channel/index.m3u8",
            headers = emptyMap(),
        )
        val hlsParam = resolver.resolve(
            rawUrl = "http://provider.test/live/user/pass/stream?output=m3u8",
            headers = emptyMap(),
        )
        val formatHls = resolver.resolve(
            rawUrl = "http://provider.test/live/user/pass/stream?format=hls",
            headers = emptyMap(),
        )

        assertThat(numericXtream.isHls).isFalse()
        assertThat(transportStream.isHls).isFalse()
        assertThat(hls.isHls).isTrue()
        assertThat(hlsParam.isHls).isTrue()
        assertThat(formatHls.isHls).isTrue()
    }
}
