@file:androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)

package com.arflix.tv.ui.screens.player

import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.ByteArrayDataSource
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.PlaceholderDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.CacheWriter
import androidx.media3.datasource.cache.SimpleCache
import androidx.media3.ui.PlayerView
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.repository.IdleReturnCoordinator
import com.arflix.tv.player.cache.AdjustableLruCacheEvictor
import com.arflix.tv.player.cache.PlaybackCache
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import fi.iki.elonen.NanoHTTPD
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.io.InputStream
import java.io.RandomAccessFile
import java.util.concurrent.atomic.AtomicInteger

class PlaybackCacheDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun populatedCacheReopensNestedSpansWithoutTrimmingThem() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val dir = File(context.cacheDir, "cache-reopen-${System.nanoTime()}").apply { mkdirs() }
        val database = StandaloneDatabaseProvider(context)
        val bytes = ByteArray(128 * 1024) { (it % 251).toByte() }
        val free = PlaybackCache.RESERVE_BYTES + (300L shl 20)
        fun evictor() = AdjustableLruCacheEvictor(
            maxBytes = 1L shl 30, userLimitGb = 1, freeBytes = { free }, clockMs = { 10_000L },
        )
        var cache: SimpleCache? = null
        try {
            val initial = SimpleCache(dir, evictor(), database).also { cache = it }
            val writerFactory = CacheDataSource.Factory().setCache(initial)
                .setUpstreamDataSourceFactory { ByteArrayDataSource(bytes) }
            for (index in 0..2) {
                CacheWriter(writerFactory.createDataSource(), DataSpec.Builder()
                    .setUri("https://fixture.invalid/$index").build(), null, null).cache()
            }
            assertEquals(3L * bytes.size, initial.cacheSpace)
            assertTrue(initial.keys.flatMap { initial.getCachedSpans(it) }.all { it.file?.parentFile != dir })
            initial.release()
            cache = null
            val reopenedEvictor = evictor()
            val reopened = SimpleCache(dir, reopenedEvictor, database).also { cache = it }
            assertEquals(3L * bytes.size, reopened.cacheSpace)
            assertEquals((300L shl 20) + reopened.cacheSpace, reopenedEvictor.maxBytes)
            val readerFactory = CacheDataSource.Factory().setCache(reopened)
                .setUpstreamDataSourceFactory(PlaceholderDataSource.FACTORY)
            for (index in 0..2) {
                val reader = readerFactory.createDataSource()
                val read = ArrayList<Byte>()
                try {
                    reader.open(DataSpec.Builder().setUri("https://fixture.invalid/$index").build())
                    val buffer = ByteArray(4096)
                    while (true) {
                        val count = reader.read(buffer, 0, buffer.size)
                        if (count == C.RESULT_END_OF_INPUT) break
                        repeat(count) { read.add(buffer[it]) }
                    }
                } finally { reader.close() }
                assertArrayEquals(bytes, read.toByteArray())
            }
        } finally {
            cache?.release()
            database.close()
            dir.deleteRecursively()
        }
    }

    @Test fun highBitratePlaybackAndTenSecondRewindRemainPlayable() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val file = File(context.getExternalFilesDir(null), "pr820-high-bitrate.mp4")
        org.junit.Assume.assumeTrue("Supply the generated 25-second 80 Mbps fixture", file.isFile)
        assertTrue("The stress fixture must contain at least 200 MiB of media", file.length() > (200L shl 20))
        val requests = AtomicInteger()
        val server = object : NanoHTTPD("127.0.0.1", 0) {
            override fun serve(session: IHTTPSession): Response {
                requests.incrementAndGet()
                val size = file.length()
                val start = Regex("bytes=(\\d+)-.*").matchEntire(session.headers["range"].orEmpty())
                    ?.groupValues?.get(1)?.toLong() ?: 0L
                if (start >= size) return newFixedLengthResponse(Response.Status.RANGE_NOT_SATISFIABLE, "text/plain", "")
                val input = RandomAccessFile(file, "r").apply { seek(start) }
                val body = object : InputStream() {
                    override fun read(): Int = input.read()
                    override fun read(buffer: ByteArray, offset: Int, length: Int): Int = input.read(buffer, offset, length)
                    override fun close() = input.close()
                }
                return newFixedLengthResponse(
                    if (start > 0) Response.Status.PARTIAL_CONTENT else Response.Status.OK,
                    "video/mp4", body, size - start,
                ).apply {
                    addHeader("Accept-Ranges", "bytes")
                    if (start > 0) addHeader("Content-Range", "bytes $start-${size - 1}/$size")
                }
            }
        }.apply { start(NanoHTTPD.SOCKET_READ_TIMEOUT, false) }
        val model = mockk<PlayerViewModel>(relaxed = true)
        val idle = mockk<IdleReturnCoordinator>(relaxed = true)
        every { idle.idleReturns } returns MutableStateFlow(0L)
        every { idle.idleThresholdMs } returns 900_000L
        every { idle.shouldDiscardPlayer } returns false
        every { model.idleReturnCoordinator } returns idle
        coEvery { model.iptvVodPolicy(any()) } returns null
        val source = StreamSource("High bitrate fixture", "Fixture", "fixture", "1080p", "",
            url = "http://127.0.0.1:${server.listeningPort}/video.mp4")
        every { model.uiState } returns MutableStateFlow(PlayerUiState(
            isLoading = false, title = source.source, selectedStream = source, selectedStreamUrl = source.url,
            streams = listOf(source), subtitlePreloadEnabled = false, autoPlayNext = false,
        ))
        every { model.isTranslatingLive } returns MutableStateFlow(false)
        try {
            compose.setContent {
                CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                    ArflixTvTheme { PlayerScreen(MediaType.MOVIE, -820, streamUrl = source.url, viewModel = model, onBack = {}) }
                }
            }
            var player: Player? = null
            compose.waitUntil(40_000) {
                compose.runOnUiThread {
                    val current = findPlayer(compose.activity.window.decorView)
                    player = current
                    assertNull(current?.playerError)
                    current?.isPlaying == true && current.currentPosition > 14_000L
                }
            }
            compose.runOnUiThread { checkNotNull(player).seekTo(4_000L) }
            compose.waitUntil(20_000) {
                compose.runOnUiThread {
                    val current = checkNotNull(player)
                    assertNull(current.playerError)
                    current.isPlaying && current.currentPosition in 5_000L..12_000L
                }
            }
            assertTrue(requests.get() > 0)
        } finally {
            compose.runOnUiThread { findPlayer(compose.activity.window.decorView)?.release() }
            server.stop()
        }
    }

    private fun findPlayer(view: View): Player? {
        if (view is PlayerView) return view.player
        if (view is ViewGroup) for (index in 0 until view.childCount) {
            findPlayer(view.getChildAt(index))?.let { return it }
        }
        return null
    }
}
