package com.arflix.tv.ui.screens.tv.live

import android.net.Uri
import androidx.media3.common.C
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.TransferListener
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class IptvHlsDetectingDataSourceTest {
    private val url = "https://provider.test/live/user/pass/123.ts"

    @Test fun redirectedHttpPlaybackDetectsHlsWithoutASeparateProbeAndKeepsHeaders() {
        val server = java.net.ServerSocket(0, 2, java.net.InetAddress.getByName("127.0.0.1"))
        server.soTimeout = 5000
        val requests = java.util.concurrent.CopyOnWriteArrayList<String>()
        val authorization = java.util.concurrent.CopyOnWriteArrayList<String>()
        val worker = Thread {
            repeat(2) { index ->
                server.accept().use { socket ->
                    socket.soTimeout = 5000
                    val reader = socket.getInputStream().bufferedReader()
                    requests.add(reader.readLine().substringBeforeLast(' '))
                    while (true) {
                        val line = reader.readLine() ?: break
                        if (line.isEmpty()) break
                        if (line.startsWith("Authorization:", true)) authorization.add(line.substringAfter(':').trim())
                    }
                    val body = "#EXTM3U\n#EXT-X-VERSION:3\n"
                    val response = if (index == 0) {
                        "HTTP/1.1 302 Found\r\nLocation: /playlist.ts\r\nContent-Length: 0\r\nConnection: close\r\n\r\n"
                    } else {
                        "HTTP/1.1 200 OK\r\nContent-Type: application/vnd.apple.mpegurl\r\nContent-Length: ${body.length}\r\nConnection: close\r\n\r\n$body"
                    }
                    socket.getOutputStream().write(response.toByteArray(Charsets.US_ASCII))
                }
            }
        }.apply { isDaemon = true; start() }
        val client = okhttp3.OkHttpClient.Builder().build()
        val root = "http://127.0.0.1:${server.localPort}/channel.ts"
        val upstream = androidx.media3.datasource.okhttp.OkHttpDataSource.Factory(client)
            .setDefaultRequestProperties(mapOf("Authorization" to "Bearer test-only"))
            .createDataSource()
        val source = IptvHlsDetectingDataSource(upstream, root)
        try {
            try { source.open(DataSpec(Uri.parse(root))); fail("Expected format correction") }
            catch (error: IptvHlsFormatDetected) { assertEquals(root, error.sourceUrl) }
            assertEquals(listOf("GET /channel.ts", "GET /playlist.ts"), requests)
            assertEquals(listOf("Bearer test-only", "Bearer test-only"), authorization)
        } finally {
            source.close()
            server.close()
            worker.join(2000)
            client.connectionPool.evictAll()
            client.dispatcher.executorService.shutdownNow()
        }
    }

    private class MemorySource(val bytes: ByteArray, val type: String = "video/mp2t", val chunk: Int = 16) : DataSource {
        var position = 0
        var reads = 0
        var opens = 0
        var closes = 0
        private var opened: Uri? = null
        override fun addTransferListener(listener: TransferListener) {}
        override fun getUri() = opened
        override fun getResponseHeaders() = mapOf("Content-Type" to listOf(type))
        override fun open(spec: DataSpec): Long { opens++; opened = spec.uri; position = spec.position.toInt(); return (bytes.size - position).toLong() }
        override fun close() { closes++; opened = null }
        override fun read(buffer: ByteArray, offset: Int, length: Int): Int {
            reads++
            if (position == bytes.size) return C.RESULT_END_OF_INPUT
            val count = minOf(chunk, length, bytes.size - position)
            bytes.copyInto(buffer, offset, position, position + count); position += count
            return count
        }
    }

    @Test fun hlsHeadersCorrectFormatWithoutReadingOrProbing() {
        val upstream = MemorySource("#EXTM3U".toByteArray(), "application/vnd.apple.mpegurl")
        val source = IptvHlsDetectingDataSource(upstream, url)
        try { source.open(DataSpec(Uri.parse(url))); fail("Expected format correction") }
        catch (error: IptvHlsFormatDetected) {
            assertEquals(url, error.sourceUrl)
            assertFalse(error.message!!.contains("user/pass"))
        }
        assertEquals(1, upstream.opens)
        assertEquals(0, upstream.reads)
        assertEquals(1, upstream.closes)
    }

    @Test fun mislabeledPlaylistWithSplitReadsIsDetectedFromTheSameResponse() {
        val upstream = MemorySource("\uFEFF \n#EXTM3U\n#EXT-X-VERSION:3".toByteArray(), chunk = 2)
        try { IptvHlsDetectingDataSource(upstream, url).open(DataSpec(Uri.parse(url))); fail("Expected format correction") }
        catch (_: IptvHlsFormatDetected) { }
        assertEquals(1, upstream.opens)
        assertTrue(upstream.position <= 16)
        assertEquals(1, upstream.closes)
    }

    @Test fun actualTransportStreamKeepsEveryByteAndSupportsReopen() {
        val bytes = ByteArray(188 * 3) { (it % 251).toByte() }
        val upstream = MemorySource(bytes, chunk = 3)
        val source = IptvHlsDetectingDataSource(upstream, url)
        repeat(2) {
            assertEquals(bytes.size.toLong(), source.open(DataSpec(Uri.parse(url))))
            assertEquals(0, source.read(ByteArray(1), 0, 0))
            val result = java.io.ByteArrayOutputStream()
            val buffer = ByteArray(11)
            while (true) { val n = source.read(buffer, 1, 10); if (n == C.RESULT_END_OF_INPUT) break; result.write(buffer, 1, n) }
            assertArrayEquals(bytes, result.toByteArray())
            source.close()
        }
    }

    @Test fun rangeResumesAndNonRootResourcesAreNotInspected() {
        for (spec in listOf(DataSpec.Builder().setUri(url).setPosition(4).build(), DataSpec(Uri.parse("https://provider.test/segment.ts")))) {
            val upstream = MemorySource("#EXTM3U playlist".toByteArray(), "application/x-mpegurl")
            val source = IptvHlsDetectingDataSource(upstream, url)
            source.open(spec)
            assertEquals(0, upstream.reads)
            source.close()
        }
    }
}
