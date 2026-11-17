package com.arflix.tv.data.api

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
import java.io.ByteArrayOutputStream

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class YoutubeChunkAccountingTest {
    private class Upstream(val content: ByteArray) : DataSource {
        var opens = 0
        var cursor = 0
        var limit = 0
        private var uri: Uri? = null
        override fun open(spec: DataSpec): Long {
            opens++
            uri = spec.uri
            val range = uri?.getQueryParameter("range")?.split('-')
            cursor = range?.first()?.toInt() ?: spec.position.toInt()
            limit = minOf(content.size, range?.last()?.toInt()?.plus(1) ?: content.size)
            return (limit - cursor).toLong()
        }
        override fun read(buffer: ByteArray, offset: Int, length: Int): Int {
            if (length == 0) return 0
            if (cursor >= limit) return C.RESULT_END_OF_INPUT
            val count = minOf(3, length, limit - cursor)
            content.copyInto(buffer, offset, cursor, cursor + count)
            cursor += count
            return count
        }
        override fun getUri(): Uri? = uri
        override fun addTransferListener(transferListener: TransferListener) = Unit
        override fun close() = Unit
    }

    private fun checkRead(size: Int, knownLength: Boolean = false, google: Boolean = true) {
        val expected = ByteArray(size) { it.toByte() }
        val upstream = Upstream(expected)
        val source = YoutubeChunkedDataSourceFactory.YoutubeChunkedDataSource(upstream, 8)
        val request = DataSpec.Builder().setUri("https://${if (google) "r1.googlevideo.com" else "example.test"}/video")
            .apply { if (knownLength) setLength(size.toLong()) }.build()
        source.open(request)
        assertEquals(0, source.read(ByteArray(1), 0, 0))
        val output = ByteArrayOutputStream()
        val buffer = ByteArray(3)
        while (true) {
            val count = source.read(buffer, 0, buffer.size)
            if (count == C.RESULT_END_OF_INPUT) break
            assertTrue(count > 0)
            output.write(buffer, 0, count)
        }
        source.close()
        assertArrayEquals(expected, output.toByteArray())
        if (!google) assertEquals(1, upstream.opens)
    }

    @Test fun multipleChunksAndShortTail() = checkRead(27)
    @Test fun exactFullChunks() = checkRead(24)
    @Test fun knownLengthEndsWithoutTruncation() = checkRead(27, true)
    @Test fun singleShortChunk() = checkRead(5)
    @Test fun otherHostsPassThrough() = checkRead(27, google = false)
}
