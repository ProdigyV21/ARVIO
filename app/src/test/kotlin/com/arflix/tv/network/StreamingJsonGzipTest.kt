package com.arflix.tv.network

import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import okio.ForwardingSource
import okio.buffer
import org.junit.Assert.*
import org.junit.Test

class StreamingJsonGzipTest {
    private fun gzip(bytes: ByteArray) = ByteArrayOutputStream().also { output ->
        GZIPOutputStream(output).use { it.write(bytes) }
    }.toByteArray()

    @Test fun plainAndOneToThreeGzipLayersAreDecoded() {
        var bytes = "{\"channels\":[1,2,3]}".toByteArray()
        repeat(4) {
            assertEquals("{\"channels\":[1,2,3]}", streamingJsonGzipBody(bytes.toResponseBody()).string())
            bytes = gzip(bytes)
        }
    }

    @Test fun largeCatalogIsNotEagerlyExpandedAndClosingCancelsConsumption() {
        val content = ByteArray(4 * 1024 * 1024).also { java.util.Random(1).nextBytes(it) }
        val compressed = gzip(content)
        var read = 0L
        var closed = false
        val source = object : ForwardingSource(Buffer().write(compressed)) {
            override fun read(sink: Buffer, byteCount: Long): Long = super.read(sink, byteCount).also { if (it > 0) read += it }
            override fun close() { closed = true; super.close() }
        }.buffer()
        val body = object : ResponseBody() {
            override fun contentType() = null
            override fun contentLength() = compressed.size.toLong()
            override fun source() = source
        }
        val decoded = streamingJsonGzipBody(body)
        assertTrue("Must only inspect a small prefix: $read", read < 32 * 1024)
        assertArrayEquals(content.copyOf(64), decoded.source().readByteArray(64))
        decoded.close()
        assertTrue(closed)
        assertTrue(read < compressed.size)
    }

    @Test(expected = java.io.IOException::class)
    fun excessiveNestedGzipIsRejected() {
        var bytes = "{}".toByteArray()
        repeat(4) { bytes = gzip(bytes) }
        streamingJsonGzipBody(bytes.toResponseBody()).close()
    }
}
