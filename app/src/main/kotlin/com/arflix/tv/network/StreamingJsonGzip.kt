package com.arflix.tv.network

import java.io.IOException
import okhttp3.ResponseBody
import okio.GzipSource
import okio.buffer

/** Some APIs incorrectly label plain JSON, or gzip it more than once. */
internal fun streamingJsonGzipBody(body: ResponseBody): ResponseBody {
    var source = body.source()
    try {
        repeat(3) {
            if (source.request(2) && source.buffer[0] == 0x1f.toByte() &&
                source.buffer[1] == 0x8b.toByte()
            ) {
                source = GzipSource(source).buffer()
            } else {
                return object : ResponseBody() {
                    override fun contentType() = body.contentType()
                    override fun contentLength() = -1L
                    override fun source() = source
                }
            }
        }
        if (source.request(2) && source.buffer[0] == 0x1f.toByte() && source.buffer[1] == 0x8b.toByte()) {
            throw IOException("Too many gzip layers")
        }
        return object : ResponseBody() {
            override fun contentType() = body.contentType()
            override fun contentLength() = -1L
            override fun source() = source
        }
    } catch (error: Exception) {
        source.close()
        throw error
    }
}
