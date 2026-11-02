package com.arflix.tv.ui.screens.player.preview

import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.cache.NoOpCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.io.File
import java.util.UUID

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class SeekPreviewPlaybackBytesTest {
    @Test fun `reads committed playback spans and misses never create spans`() {
        val context = RuntimeEnvironment.getApplication()
        val cache = SimpleCache(File(context.cacheDir, "playback-${UUID.randomUUID()}"),
            NoOpCacheEvictor(), StandaloneDatabaseProvider(context))
        try {
            val url = "https://example.test/video.mkv"
            val hole = cache.startReadWrite(url, 0, 4)
            val file = cache.startFile(url, 0, 4)
            file.writeBytes(byteArrayOf(1, 2, 3, 4))
            cache.commitFile(file, 4)
            cache.releaseHoleSpan(hole)
            val reader = Media3SeekPreviewPlaybackBytes(cache)
            assertArrayEquals(byteArrayOf(2, 3), reader.read(url, 1, 2))
            assertNull(reader.read(url, 3, 2))
            assertNull(reader.read("https://example.test/other.mkv", 0, 4))
            assertEquals(4L, cache.cacheSpace)
        } finally { cache.release() }
    }
}
