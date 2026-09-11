package com.arflix.tv.ui.screens.player.preview

import fi.iki.elonen.NanoHTTPD
import fi.iki.elonen.NanoHTTPD.IHTTPSession
import fi.iki.elonen.NanoHTTPD.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference

class PreviewHttpClientTest {
    private lateinit var server: NanoHTTPD
    private lateinit var base: String
    private val clients = mutableListOf<PreviewHttpClient>()
    private val handlers = ConcurrentHashMap<String, (IHTTPSession) -> Response>()

    @Before fun start() {
        server = object : NanoHTTPD("127.0.0.1", 0) {
            override fun serve(session: IHTTPSession): Response = handlers[session.uri]?.invoke(session)
                ?: newFixedLengthResponse(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "")
        }
        server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, true)
        base = "http://127.0.0.1:${server.listeningPort}"
    }

    @After fun stop() {
        clients.forEach { it.close() }
        server.stop()
    }

    private fun client(headers: Map<String, String> = emptyMap(), playback: OkHttpClient = OkHttpClient()): PreviewHttpClient =
        PreviewHttpClient(playback, base.toHttpUrl(), headers).also { clients += it }

    private fun fixture(path: String = "/data", code: Int = 200, bytes: ByteArray = byteArrayOf(1, 2, 3, 4),
        headers: Map<String, String> = emptyMap(), chunked: Boolean = false) {
        handlers[path] = {
            val status = when (code) {
                200 -> Response.Status.OK
                206 -> Response.Status.PARTIAL_CONTENT
                302 -> Response.Status.FOUND
                416 -> Response.Status.RANGE_NOT_SATISFIABLE
                else -> error("Unsupported fixture status: $code")
            }
            val body = ByteArrayInputStream(bytes)
            val response = if (chunked) NanoHTTPD.newChunkedResponse(status, "application/octet-stream", body)
                else NanoHTTPD.newFixedLengthResponse(status, "application/octet-stream", body, bytes.size.toLong())
            response.apply { headers.forEach { (key, value) -> addHeader(key, value) } }
        }
    }

    private suspend fun mustFail(block: suspend () -> Unit) {
        try { block(); fail("Expected bounded retrieval failure") } catch (_: IOException) { }
    }

    @Test fun `explicit JSON Accept wins over playback headers while preserving auth and cookies`() = runBlocking {
        val observed = AtomicReference<List<String>>()
        handlers["/metadata"] = { session ->
            observed.set(listOf(session.headers["accept"].orEmpty(), session.headers["x-emby-token"].orEmpty(),
                session.headers["cookie"].orEmpty(), session.headers["accept-encoding"].orEmpty()))
            NanoHTTPD.newFixedLengthResponse(Response.Status.OK, "application/json", "{}")
        }
        val playback = OkHttpClient.Builder().cookieJar(object : CookieJar {
            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) = Unit
            override fun loadForRequest(url: HttpUrl): List<Cookie> = listOf(Cookie.Builder().name("session")
                .value("fixture").hostOnlyDomain("127.0.0.1").build())
        }).build()
        client(mapOf("X-Emby-Token" to "fixture", "Accept" to "application/xml"), playback)
            .get("$base/metadata", 100, accept = "application/json")
        assertEquals(listOf("application/json", "fixture", "session=fixture", "identity"), observed.get())
    }

    @Test fun `bounded response supports small full file when initial range ignored`() = runBlocking {
        fixture()
        val response = client().get("$base/data", 64, PreviewByteRange(0, 4))
        assertFalse(response.partial)
        assertEquals(4L, response.totalBytes)
        assertArrayEquals(byteArrayOf(1, 2, 3, 4), response.bytes)
    }

    @Test fun `range reads require exact range total and stable validator`() = runBlocking {
        fixture(code = 206, headers = mapOf("Content-Range" to "bytes 4-7/20", "ETag" to "\"v1\""))
        val response = client().get("$base/data", 64, PreviewByteRange(4, 4, 20, "\"v1\""))
        assertTrue(response.partial)
        assertEquals(20L, response.totalBytes)
        mustFail { client().get("$base/data", 64, PreviewByteRange(5, 4, 20)) }
        mustFail { client().get("$base/data", 64, PreviewByteRange(4, 4, 21)) }
        mustFail { client().get("$base/data", 64, PreviewByteRange(4, 4, 20, "\"v2\"")) }
    }

    @Test fun `nonzero ranges never fall back to reading entire file`() = runBlocking {
        fixture()
        mustFail { client().get("$base/data", 64, PreviewByteRange(4, 4)) }
        mustFail { client().get("$base/data", 64, PreviewByteRange(0, 4, 20)) }
    }

    @Test fun `missing malformed unsolicited and rejected ranges fail closed`() = runBlocking {
        fixture(path = "/missing", code = 206)
        fixture(path = "/malformed", code = 206, headers = mapOf("Content-Range" to "bytes 0-3/*"))
        fixture(path = "/rejected", code = 416)
        for (path in listOf("missing", "malformed", "rejected")) {
            mustFail { client().get("$base/$path", 64, PreviewByteRange(0, 4)) }
        }
        mustFail { client().get("$base/missing", 64) }
    }

    @Test fun `known and chunked bodies cannot exceed byte budget`() = runBlocking {
        fixture(path = "/known", bytes = ByteArray(100))
        fixture(path = "/chunked", bytes = ByteArray(100), chunked = true)
        mustFail { client().get("$base/known", 32) }
        mustFail { client().get("$base/chunked", 32) }
    }

    @Test fun `same origin redirects work but cross origin never receives auth`() = runBlocking {
        fixture(path = "/redirect", code = 302, headers = mapOf("Location" to "/data"))
        fixture(path = "/external", code = 302, headers = mapOf("Location" to "http://localhost:${server.listeningPort}/data"))
        fixture()
        assertEquals(4, client().get("$base/redirect", 32).bytes.size)
        mustFail { client(mapOf("X-Plex-Token" to "fixture")).get("$base/external", 32) }
        mustFail { client().get("http://localhost:${server.listeningPort}/data", 32) }
    }

    @Test fun `cancellation cancels active request promptly`() = runBlocking {
        val entered = CountDownLatch(1)
        val release = CountDownLatch(1)
        handlers["/slow"] = {
            entered.countDown()
            release.await(5, TimeUnit.SECONDS)
            NanoHTTPD.newFixedLengthResponse(Response.Status.OK, "application/octet-stream", "")
        }
        val http = client()
        val request = async(Dispatchers.IO) { http.get("$base/slow", 32) }
        try {
            assertTrue(withContext(Dispatchers.IO) { entered.await(3, TimeUnit.SECONDS) })
            val start = System.nanoTime()
            request.cancelAndJoin()
            assertTrue(request.isCancelled)
            assertTrue(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start) < 1000)
        } finally { release.countDown() }
    }

    @Test fun `close prevents new network requests`() = runBlocking {
        fixture()
        val http = client()
        http.close()
        mustFail { http.get("$base/data", 32) }
    }

    @Test fun `Content Range parser rejects overflow and invalid lengths`() {
        assertEquals(Triple(4L, 7L, 20L), PreviewHttpClient.parseContentRange("bytes 4-7/20"))
        for (header in listOf("bytes 4-3/20", "bytes 4-20/20", "bytes 0-1/*", "bytes 0-99999999999999999999/100", "bytes -1-2/20")) {
            assertNull(PreviewHttpClient.parseContentRange(header))
        }
    }
}
