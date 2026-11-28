package com.arflix.tv.music

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.annotation.OptIn
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.cache.Cache
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.CacheWriter
import androidx.media3.datasource.cache.ContentMetadata
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import java.io.File
import java.util.concurrent.Executors

/**
 * Songs kept on the device: the one playing is downloaded whole in the background, so seeking
 * anywhere in it (or playing it again) needs no network. The songs played longest ago make room
 * once the limit is reached, and Android may also empty it when storage runs low.
 */
@OptIn(UnstableApi::class)
object MusicAudioCache {
    const val PHONE_LIMIT_BYTES = 500_000_000L
    /** TV boxes often have little storage. */
    const val TV_LIMIT_BYTES = 250_000_000L

    @Volatile private var instance: SimpleCache? = null

    fun limitBytes(context: Context): Long =
        if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)) TV_LIMIT_BYTES else PHONE_LIMIT_BYTES

    /** Null when the cache cannot be opened (no storage, say); music then streams as before. */
    fun get(context: Context): SimpleCache? = instance ?: synchronized(this) {
        instance ?: runCatching {
            val app = context.applicationContext
            SimpleCache(
                File(app.cacheDir, "music_audio_cache"),
                LeastRecentlyUsedCacheEvictor(limitBytes(app)),
                StandaloneDatabaseProvider(app),
            )
        }.getOrNull()?.also { instance = it }
    }

    fun usedBytes(context: Context): Long = runCatching { get(context)?.cacheSpace ?: 0L }.getOrDefault(0L)

    fun clear(context: Context) {
        val cache = get(context) ?: return
        runCatching { for (key in cache.keys.toList()) cache.removeResource(key) }
    }

    /**
     * Drops a song that was not downloaded to its end. An addon may answer with a different file
     * the next time, and the start of one file must never be joined to the rest of another.
     */
    internal fun dropIfPartial(cache: Cache, key: String) = runCatching {
        val length = ContentMetadata.getContentLength(cache.getContentMetadata(key))
        val complete = length != C.LENGTH_UNSET.toLong() && cache.getCachedBytes(key, 0, length) >= length
        if (!complete) cache.removeResource(key)
    }

    /** Downloads the song playing to its end, one song at a time, in the background. */
    internal class SongDownloader(private val cache: Cache, private val dataSources: CacheDataSource.Factory) {
        private val executor = Executors.newSingleThreadExecutor { Thread(it, "music-download").apply { isDaemon = true } }
        private var song: Uri? = null
        private var writer: CacheWriter? = null

        init {
            // Leftovers of a song cut off when the app last closed.
            executor.execute { runCatching { cache.keys.toList() }.getOrDefault(emptyList()).forEach { dropIfPartial(cache, it) } }
        }

        /** Called on the main thread with the song now playing, or null when nothing is. */
        fun follow(uri: Uri?) {
            if (uri == song) return
            writer?.cancel()
            val previous = song
            song = uri
            val next = uri?.let { CacheWriter(dataSources.createDataSource(), DataSpec(it), null, null) }
            writer = next
            executor.execute {
                previous?.let { dropIfPartial(cache, it.toString()) }
                // A failed download is fine: the player still streams what it needs.
                next?.let { runCatching { it.cache() } }
            }
        }

        fun release() {
            follow(null)
            executor.shutdown()
        }
    }
}
