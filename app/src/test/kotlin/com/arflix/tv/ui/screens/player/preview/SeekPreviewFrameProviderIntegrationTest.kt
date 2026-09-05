package com.arflix.tv.ui.screens.player.preview

import android.content.Context
import android.graphics.Bitmap
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import okhttp3.OkHttpClient
import java.io.File
import java.util.UUID

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
class SeekPreviewFrameProviderIntegrationTest {
    private val context: Context get() = RuntimeEnvironment.getApplication()
    private fun source(allowExtraction: Boolean = true) = SeekPreviewSource(
        "https://example.test/video.mp4?version=1", emptyMap(), UUID.randomUUID().toString(), 120_000, false, false,
        allowExtraction = allowExtraction,
    )
    private fun bitmap() = Bitmap.createBitmap(640, 360, Bitmap.Config.ARGB_8888)
    private fun provider(
        images: SeekPreviewImageLoader? = null,
        decode: suspend (SeekPreviewSource, Long) -> SeekPreviewImage = { _, _ -> error("Unexpected decoder") },
    ) = SeekPreviewFrameProvider(context, OkHttpClient(), 256, { images }, decode, 1024 * 1024)

    @Test
    fun `disk and memory preserve actual decoded presentation time`() = runBlocking {
        val source = source()
        provider(decode = { _, _ -> SeekPreviewImage(bitmap(), actualPositionMs = 28_160) }).use { provider ->
            provider.configure(source)
            val frame = provider.frameAt(30_000)!!
            assertEquals(30_000L, frame.requestedPositionMs)
            assertEquals(28_160L, frame.actualPositionMs)
            assertEquals(28_160L, frame.positionMs)
            assertEquals(28_160L, provider.memoryFrameAt(30_000)!!.positionMs)
            assertTrue(provider.matchesTarget(frame, 30_000))
            assertFalse(provider.matchesTarget(frame, 40_000))
        }
        provider().use { provider ->
            provider.configure(source)
            val diskFrame = provider.cachedFrameAt(30_000)!!
            assertEquals(SeekPreviewOrigin.DISK, diskFrame.origin)
            assertEquals(28_160L, diskFrame.positionMs)
            assertEquals(30_000L, diskFrame.requestedPositionMs)
        }
    }

    @Test
    fun `provider imagery works with decoder disabled and receives unrounded target`() = runBlocking {
        val requests = mutableListOf<Long>()
        val images = object : SeekPreviewImageLoader {
            override suspend fun load(positionMs: Long): SeekPreviewImage {
                requests += positionMs
                val cue = if (positionMs < 20_000) 10_000L else 20_000L
                return SeekPreviewImage(bitmap(), cueStartMs = cue, cueEndMs = cue + 10_000)
            }
        }
        provider(images).use { provider ->
            provider.configure(source(allowExtraction = false))
            val first = provider.frameAt(19_999)!!
            assertEquals(listOf(19_999L), requests)
            assertNull(first.actualPositionMs)
            assertEquals(10_000L, first.positionMs)
            assertTrue(provider.matchesTarget(first, 19_999))
            assertFalse(provider.matchesTarget(first, 20_000))
            // Both targets quantize into the same cache bucket, but the cue must be re-resolved.
            assertNull(provider.memoryFrameAt(20_000))
            val next = provider.frameAt(20_000)!!
            assertEquals(20_000L, next.cueStartMs)
            assertEquals(SeekPreviewState.READY, provider.status.value.state)
        }
    }

    @Test
    fun `missing provider image during warming never starts secondary decoder`() = runBlocking {
        val images = object : SeekPreviewImageLoader {
            override suspend fun load(positionMs: Long): SeekPreviewImage? = null
        }
        provider(images).use { provider ->
            provider.configure(source(allowExtraction = true))
            provider.warmAround(30_000)
            assertEquals(SeekPreviewCapability.AVAILABLE, provider.status.value.capability)
            assertEquals(SeekPreviewState.IDLE, provider.status.value.state)
        }
    }

    @Test
    fun `a cue gap does not disable valid previews at subsequent targets`() = runBlocking {
        val images = object : SeekPreviewImageLoader {
            override suspend fun load(positionMs: Long): SeekPreviewImage? =
                if (positionMs < 20_000) null else SeekPreviewImage(bitmap(), cueStartMs = 20_000, cueEndMs = 30_000)
        }
        provider(images).use { provider ->
            provider.configure(source(allowExtraction = false))
            assertNull(provider.frameAt(10_000))
            assertEquals(SeekPreviewState.UNAVAILABLE, provider.status.value.state)
            assertEquals(SeekPreviewCapability.AVAILABLE, provider.status.value.capability)
            assertNotNull(provider.frameAt(20_000))
            assertEquals(SeekPreviewState.READY, provider.status.value.state)
        }
    }

    @Test
    fun `late completion after source replacement cannot poison cache or status`() = runBlocking {
        val started = CompletableDeferred<Unit>()
        val finish = CompletableDeferred<Unit>()
        val images = object : SeekPreviewImageLoader {
            override suspend fun load(positionMs: Long): SeekPreviewImage {
                started.complete(Unit)
                withContext(NonCancellable) { finish.await() }
                return SeekPreviewImage(bitmap(), cueStartMs = 0, cueEndMs = 120_000)
            }
        }
        provider(images).use { provider ->
            val old = source(false)
            provider.configure(old)
            val firstGeneration = provider.sourceGeneration
            val pending = async { provider.frameAt(20_000) }
            started.await()
            provider.configure(old.copy(url = "https://example.test/video.mp4?version=2"))
            assertTrue(provider.sourceGeneration > firstGeneration)
            finish.complete(Unit)
            pending.join()
            assertTrue(pending.isCancelled)
            assertNull(provider.cachedFrameAt(20_000))
            assertEquals(SeekPreviewState.IDLE, provider.status.value.state)
        }
    }

    @Test
    fun `unsupported previews terminate loading and no decoder starts when gated`() = runBlocking {
        provider().use { provider ->
            provider.configure(source(false))
            assertNull(provider.frameAt(20_000))
            assertEquals(SeekPreviewCapability.UNAVAILABLE, provider.status.value.capability)
            assertEquals(SeekPreviewState.UNAVAILABLE, provider.status.value.state)
        }
        provider(decode = { _, _ -> throw UnsupportedPreviewException("https://secret.test/?token=secret") }).use { provider ->
            provider.configure(source())
            assertNull(provider.frameAt(20_000))
            assertEquals(SeekPreviewState.UNAVAILABLE, provider.status.value.state)
            assertFalse(provider.status.value.reason!!.contains("secret"))
            assertNull(provider.frameAt(30_000))
        }
    }

    @Test
    fun `decoder memory exhaustion disables previews without escaping into playback`() = runBlocking {
        var decodeCalls = 0
        provider(decode = { _, _ ->
            decodeCalls++
            if (decodeCalls == 1) SeekPreviewImage(bitmap(), actualPositionMs = 10_000)
            else throw OutOfMemoryError("fixture decoder allocation")
        }).use { provider ->
            provider.configure(source())
            assertNotNull(provider.frameAt(10_000))
            assertNull(provider.frameAt(30_000))
            assertNull(provider.memoryFrameAt(10_000))
            assertEquals(SeekPreviewCapability.UNAVAILABLE, provider.status.value.capability)
            assertEquals(SeekPreviewState.UNAVAILABLE, provider.status.value.state)
            assertNull(provider.frameAt(50_000))
            assertNull("Even a persisted frame must not be re-decoded after memory pressure", provider.frameAt(10_000))
            assertEquals(2, decodeCalls)
        }
    }

    @Test
    fun `cancellation propagates and does not trip unsupported circuit`() = runBlocking {
        val started = CompletableDeferred<Unit>()
        provider(decode = { _, _ -> started.complete(Unit); awaitCancellation() }).use { provider ->
            provider.configure(source())
            val pending = async { provider.frameAt(20_000) }
            started.await()
            pending.cancel()
            pending.join()
            provider.cancelPending()
            assertEquals(SeekPreviewCapability.AVAILABLE, provider.status.value.capability)
            assertEquals(SeekPreviewState.IDLE, provider.status.value.state)
        }
    }

    @Test
    fun `end position requests a real last frame rather than undecodable EOS`() = runBlocking {
        var decodeTarget = -1L
        provider(decode = { _, target ->
            decodeTarget = target
            SeekPreviewImage(bitmap(), actualPositionMs = 29_000)
        }).use { provider ->
            provider.configure(source().copy(durationMs = 30_000))
            val frame = provider.frameAt(30_000)!!
            assertEquals(29_999L, decodeTarget)
            assertEquals(30_000L, frame.requestedPositionMs)
            assertEquals(29_000L, frame.actualPositionMs)
        }
    }

    @Test
    fun `disk budget is enforced while writing not only when constructing provider`() {
        val directory = File(context.cacheDir, "provider-eviction-${UUID.randomUUID()}")
        val cache = SeekPreviewDiskCache(directory, 16_000)
        try {
            repeat(20) { index ->
                val time = index * 10_000L
                cache.write("item$index", CachedPreview(bitmap(), PreviewMetadata("source", time, time, null, null, SeekPreviewOrigin.DECODER, SeekPreviewValidity.TIMESTAMP)))
                assertTrue(directory.listFiles().orEmpty().sumOf { it.length() } <= 16_000)
            }
            assertTrue("Newest entry must actually be persisted", File(directory, "item19.preview").isFile)
            assertTrue("Old entries must have been evicted", directory.listFiles().orEmpty().size < 20)
        } finally {
            directory.deleteRecursively()
        }
    }
}
