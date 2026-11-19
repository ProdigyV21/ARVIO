package com.arflix.tv.ui.screens.player.preview

import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.cache.Cache
import androidx.media3.datasource.cache.CacheDataSource
import java.io.IOException

/** A cache miss must never open the playback network connection or acquire a write hole. */
internal fun interface SeekPreviewPlaybackBytes {
    fun read(url: String, position: Long, length: Int): ByteArray?
    fun size(url: String): Long? = null
}

internal class Media3SeekPreviewPlaybackBytes(private val cache: Cache) : SeekPreviewPlaybackBytes {
    override fun size(url: String): Long? = androidx.media3.datasource.cache.ContentMetadata
        .getContentLength(cache.getContentMetadata(url)).takeIf { it > 0 }

    override fun read(url: String, position: Long, length: Int): ByteArray? {
        if (length <= 0 || !cache.isCached(url, position, length.toLong())) return null
        val source = CacheDataSource.Factory().setCache(cache)
            .setCacheWriteDataSinkFactory(null).createDataSource()
        return try {
            // With no upstream factory, Media3 uses DummyDataSource. Eviction races
            // become misses, never network requests. Cache's default key is this URI.
            source.open(DataSpec.Builder().setUri(url).setKey(url).setPosition(position)
                .setLength(length.toLong()).build())
            val bytes = ByteArray(length)
            var read = 0
            while (read < length) {
                val count = source.read(bytes, read, length - read)
                if (count <= 0) return null
                read += count
            }
            bytes
        } catch (_: IOException) {
            null
        } finally {
            try { source.close() } catch (_: IOException) { }
        }
    }
}
