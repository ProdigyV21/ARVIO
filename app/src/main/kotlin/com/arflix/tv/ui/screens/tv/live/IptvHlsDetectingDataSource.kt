package com.arflix.tv.ui.screens.tv.live

import androidx.media3.common.C
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DataSpec
import java.io.IOException

/** A format correction, not a network retry. Never include credential-bearing URLs in the message. */
internal class IptvHlsFormatDetected(val sourceUrl: String) : IOException("Live source returned an HLS playlist")

internal fun Throwable.iptvHlsFormatDetected(): IptvHlsFormatDetected? =
    generateSequence(this) { it.cause }.take(16).filterIsInstance<IptvHlsFormatDetected>().firstOrNull()

/** Inspect only the initial root response. HLS segments and range resumes must stay untouched. */
internal class IptvHlsDetectingDataSource(
    private val upstream: DataSource,
    private val rootUrl: String,
) : DataSource by upstream {
    private var prefix = ByteArray(0)
    private var prefixPosition = 0

    override fun open(dataSpec: DataSpec): Long {
        prefix = ByteArray(0)
        prefixPosition = 0
        val length = upstream.open(dataSpec)
        if (dataSpec.uri.toString() != rootUrl || dataSpec.position != 0L) return length
        try {
            val contentType = upstream.responseHeaders.entries.firstOrNull { it.key.equals("Content-Type", true) }
                ?.value?.firstOrNull().orEmpty()
            if (contentType.contains("mpegurl", true)) throw IptvHlsFormatDetected(rootUrl)
            val bytes = ByteArray(if (length == C.LENGTH_UNSET.toLong()) 16 else length.coerceIn(0L, 16L).toInt())
            var count = 0
            while (count < bytes.size) {
                val read = upstream.read(bytes, count, bytes.size - count)
                if (read == C.RESULT_END_OF_INPUT || read == 0) break
                count += read
            }
            prefix = bytes.copyOf(count)
            if (prefix.toString(Charsets.UTF_8).trimStart('\uFEFF', ' ', '\t', '\r', '\n').startsWith("#EXTM3U"))
                throw IptvHlsFormatDetected(rootUrl)
            return length
        } catch (error: IOException) {
            runCatching { upstream.close() }
            throw error
        }
    }

    override fun read(buffer: ByteArray, offset: Int, length: Int): Int {
        if (length == 0) return 0
        if (prefixPosition < prefix.size) {
            val count = minOf(length, prefix.size - prefixPosition)
            prefix.copyInto(buffer, offset, prefixPosition, prefixPosition + count)
            prefixPosition += count
            return count
        }
        return upstream.read(buffer, offset, length)
    }

    override fun close() {
        prefix = ByteArray(0)
        prefixPosition = 0
        upstream.close()
    }
}
