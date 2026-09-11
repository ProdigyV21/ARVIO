package com.arflix.tv.ui.screens.player.preview

import fi.iki.elonen.NanoHTTPD
import java.io.ByteArrayInputStream
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.EventListener
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import okio.BufferedSource
import okio.Source
import okio.Timeout
import okio.buffer
import org.junit.Assert.*
import org.junit.Test

class SeekPreviewRangeProviderTest {
    private val upstream = "https://media.test/large.mkv"
    private fun response(request: Request, code: Int, bytes: ByteArray = ByteArray(0), vararg headers: Pair<String, String>): Response =
        Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(code).message("fixture")
            .body(bytes.toResponseBody()).apply { headers.forEach { (name, value) -> header(name, value) } }.build()

    private fun client(handler: (Request) -> Response) = OkHttpClient.Builder()
        .addInterceptor { handler(it.request()) }.build()

    private suspend fun expectFailure(operation: suspend () -> Unit) {
        try { operation(); fail("Expected validated range failure") } catch (_: IOException) {}
    }

    @Test
    fun `failed HEAD length is ignored and valid 206 discovers real length`() = runBlocking {
        val requested = mutableListOf<Request>()
        val client = client { request ->
            requested += request
            if (request.method == "HEAD") response(request, 500, ByteArray(0), "Content-Length" to "9000")
            else response(request, 206, byteArrayOf(1, 2, 3, 4), "Content-Range" to "bytes 0-3/4", "Content-Length" to "4")
        }
        HttpRangeReader(client, upstream, emptyMap()).use { reader ->
            assertEquals(4L, reader.size())
            assertArrayEquals(byteArrayOf(2, 3), reader.read(1, 2))
            assertEquals(2, requested.size)
            assertEquals("bytes=0-524287", requested.last().header("Range"))
        }
    }

    @Test
    fun `wrong offsets lengths and truncated bodies are rejected before cache insertion`() = runBlocking {
        val cases = listOf(
            "bytes 1-4/5" to byteArrayOf(1, 2, 3, 4),
            "bytes 0-3/4" to byteArrayOf(1, 2),
            "bytes 0-8/4" to ByteArray(9),
            "bytes 0-3/9223372036854775808" to ByteArray(4),
        )
        for ((range, body) in cases) {
            val client = client { response(it, 206, body, "Content-Range" to range, "Content-Length" to body.size.toString()) }
            HttpRangeReader(client, upstream, emptyMap()).use { reader -> expectFailure { reader.read(0, 1) } }
        }
    }

    @Test
    fun `416 must supply real EOF and never infers length from requested offset`() = runBlocking {
        val valid = client { response(it, 416, ByteArray(0), "Content-Range" to "bytes */12") }
        HttpRangeReader(valid, upstream, emptyMap()).use { reader ->
            assertArrayEquals(ByteArray(0), reader.read(RANGE_CHUNK_BYTES.toLong(), 1))
            assertEquals(12L, reader.size())
        }
        val malformed = client { response(it, 416) }
        HttpRangeReader(malformed, upstream, emptyMap()).use { reader ->
            expectFailure { reader.read(RANGE_CHUNK_BYTES.toLong(), 1) }
        }
        HttpRangeReader(valid, upstream, emptyMap()).use { reader -> expectFailure { reader.read(0, 1) } }
    }

    @Test
    fun `ignored range responses are closed without reading entire media`() = runBlocking {
        val consumed = AtomicLong()
        val client = client { request -> response(request, 200).newBuilder().body(unboundedBody(consumed)).build() }
        HttpRangeReader(client, upstream, emptyMap()).use { reader -> expectFailure { reader.read(0, 8) } }
        assertEquals(0L, consumed.get())
    }

    @Test
    fun `chunked oversized 206 is bounded and cannot be cached as valid`() = runBlocking {
        val consumed = AtomicLong()
        val client = client { request -> response(request, 206, ByteArray(0), "Content-Range" to "bytes 0-3/4")
            .newBuilder().body(unboundedBody(consumed)).build() }
        HttpRangeReader(client, upstream, emptyMap()).use { reader -> expectFailure { reader.read(0, 1) } }
        // Okio may prefetch one segment, but never drains the response or allocates its declared size.
        assertTrue(consumed.get() in 1..8192)
    }

    private fun unboundedBody(consumed: AtomicLong) = object : ResponseBody() {
        override fun contentType() = null
        override fun contentLength() = -1L
        private val stream = object : Source {
            override fun read(sink: Buffer, byteCount: Long): Long {
                val count = minOf(byteCount, 8192).toInt()
                consumed.addAndGet(count.toLong())
                sink.write(ByteArray(count))
                return count.toLong()
            }
            override fun timeout() = Timeout.NONE
            override fun close() {}
        }.buffer()
        override fun source(): BufferedSource = stream
    }

    @Test
    fun `cancelling range read cancels active HTTP call`() = runBlocking {
        val started = CompletableDeferred<Unit>()
        val release = CountDownLatch(1)
        val cancelled = AtomicBoolean()
        val client = OkHttpClient.Builder().eventListener(object : EventListener() {
            override fun canceled(call: Call) { cancelled.set(true) }
        }).addInterceptor { chain ->
            started.complete(Unit)
            release.await(5, TimeUnit.SECONDS)
            response(chain.request(), 206, byteArrayOf(7), "Content-Range" to "bytes 0-0/1")
        }.build()
        try {
            HttpRangeReader(client, upstream, emptyMap()).use { reader ->
                val pending = async { reader.read(0, 1) }
                started.await()
                pending.cancelAndJoin()
                assertTrue(cancelled.get())
            }
        } finally { release.countDown() }
    }

    @Test
    fun `playback cookies and auth headers reach origin with identity encoding`() = runBlocking {
        val auth = AtomicBoolean()
        val cookie = AtomicBoolean()
        val encoding = AtomicBoolean()
        val server = object : NanoHTTPD("127.0.0.1", 0) {
            override fun serve(session: IHTTPSession): Response {
                auth.set(session.headers["authorization"] == "Bearer fixture")
                cookie.set(session.headers["cookie"]?.contains("session=fixture") == true)
                encoding.set(session.headers["accept-encoding"] == "identity")
                return newFixedLengthResponse(Response.Status.PARTIAL_CONTENT, "video/mp4", ByteArrayInputStream(byteArrayOf(7)), 1).apply {
                    addHeader("Content-Range", "bytes 0-0/1")
                }
            }
        }
        server.start(2_000, true)
        try {
            val playbackClient = OkHttpClient.Builder().cookieJar(object : CookieJar {
                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {}
                override fun loadForRequest(url: HttpUrl) = listOf(Cookie.Builder().domain("127.0.0.1").name("session").value("fixture").build())
            }).build()
            HttpRangeReader(playbackClient.newBuilder().build(), "http://127.0.0.1:${server.listeningPort}/file", mapOf(
                "Authorization" to "Bearer fixture", "Accept-Encoding" to "gzip", "Range" to "bytes=8-9",
            )).use { assertArrayEquals(byteArrayOf(7), it.read(0, 1)) }
            assertTrue(auth.get())
            assertTrue(cookie.get())
            assertTrue(encoding.get())
        } finally { server.stop() }
    }

    @Test
    fun `nearby decode keeps existing byte connection valid and explicit release ends it`() = runBlocking {
        val bytes = ByteArray(32) { it.toByte() }
        val upstreamClient = client { request ->
            if (request.method == "HEAD") response(request, 200, ByteArray(0), "Content-Length" to "32")
            else response(request, 206, bytes, "Content-Range" to "bytes 0-31/32", "Content-Length" to "32")
        }
        SeekPreviewRangeProxy(HttpRangeReader(upstreamClient, upstream, emptyMap())).use { proxy ->
            proxy.prepare()
            proxy.beginRequest()
            val session = java.lang.reflect.Proxy.newProxyInstance(
                NanoHTTPD.IHTTPSession::class.java.classLoader, arrayOf(NanoHTTPD.IHTTPSession::class.java),
            ) { _, method, _ ->
                when (method.name) {
                    "getUri" -> java.net.URI(proxy.url).path
                    "getMethod" -> NanoHTTPD.Method.GET
                    "getHeaders" -> mapOf("range" to "bytes=0-31")
                    else -> null
                }
            } as NanoHTTPD.IHTTPSession
            proxy.serve(session).data.use { body ->
                assertEquals(0, body.read())
                proxy.beginRequest()
                assertEquals(1, body.read())
                proxy.endRequest()
                expectFailure { body.read() }
            }
        }
    }

    @Test
    fun `loopback proxy reads large offsets without staging entire remux`() = runBlocking {
        val mediaSize = 50L * 1024 * 1024 * 1024
        val transferred = AtomicLong()
        val upstreamClient = client { request ->
            if (request.method == "HEAD") response(request, 200, ByteArray(0), "Content-Length" to mediaSize.toString())
            else {
                val pair = request.header("Range")!!.removePrefix("bytes=").split('-')
                val start = pair[0].toLong()
                val end = minOf(pair[1].toLong(), mediaSize - 1)
                val count = (end - start + 1).toInt()
                transferred.addAndGet(count.toLong())
                response(request, 206, ByteArray(count) { ((start + it) % 251).toByte() }, "Content-Range" to "bytes $start-$end/$mediaSize", "Content-Length" to count.toString())
            }
        }
        SeekPreviewRangeProxy(HttpRangeReader(upstreamClient, upstream, emptyMap())).use { proxy ->
            proxy.prepare()
            proxy.beginRequest()
            val consumer = OkHttpClient()
            val start = 32L * 1024 * 1024 * 1024 + 123
            consumer.newCall(Request.Builder().url(proxy.url).header("Range", "bytes=$start-${start + 31}").build()).execute().use { response ->
                assertEquals(206, response.code)
                assertEquals("bytes $start-${start + 31}/$mediaSize", response.header("Content-Range"))
                assertArrayEquals(ByteArray(32) { ((start + it) % 251).toByte() }, response.body!!.bytes())
            }
            assertTrue(transferred.get() <= 2L * RANGE_CHUNK_BYTES)
            consumer.newCall(Request.Builder().url(proxy.url.replaceAfterLast('/', "wrong")).build()).execute().use { assertEquals(404, it.code) }
            proxy.endRequest()
            consumer.newCall(Request.Builder().url(proxy.url).build()).execute().use { assertEquals(503, it.code) }
        }
    }
}
