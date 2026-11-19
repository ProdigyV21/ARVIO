package com.arflix.tv.ui.screens.player.preview

import android.content.Context
import android.graphics.Bitmap
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import okhttp3.OkHttpClient
import java.io.File
import java.util.UUID

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
// Sideload's Android Conscrypt JNI is unavailable on the host JVM.
@ConscryptMode(ConscryptMode.Mode.OFF)
class SeekPreviewFrameProviderIntegrationTest {
    @Test fun `healthy buffering prepares two sequential frames and follows rewind intent`() = runBlocking {
        val targets = mutableListOf<Long>()
        var running = 0
        provider(decode = { _, target ->
            assertEquals(0, running++)
            targets += target
            delay(5)
            running--
            SeekPreviewImage(bitmap(), actualPositionMs = target)
        }).use { provider ->
            provider.configure(source())
            provider.noteSeekTarget(90_000)
            provider.noteSeekTarget(60_000)
            provider.warmPlayback(60_000, 30_000)
            assertEquals(listOf(50_000L, 40_000L), targets)
            provider.warmPlayback(60_000, 9_000)
            assertEquals(3, targets.size)
        }
    }

    @Test fun `provider images prepare a complete cached window without a video decoder`() = runBlocking {
        val images = object : SeekPreviewImageLoader {
            override suspend fun load(positionMs: Long) = SeekPreviewImage(bitmap(),
                cueStartMs = positionMs, cueEndMs = positionMs + 10_000)
        }
        provider(images).use { provider ->
            provider.configure(source(false))
            provider.warmPlayback(60_000, 9_000)
            val frames = provider.cachedWindowAt(60_000)
            assertEquals(5, frames.size)
            assertTrue(frames.all { it.validity == SeekPreviewValidity.CUE })
        }
    }

    @Test fun `cancelling a preparation batch leaves foreground seeking responsive`() = runBlocking {
        val began = CompletableDeferred<Unit>()
        val targets = mutableListOf<Long>()
        provider(decode = { _, target ->
            targets += target
            if (target == 70_000L) { began.complete(Unit); awaitCancellation() }
            SeekPreviewImage(bitmap(), actualPositionMs = target)
        }).use { provider ->
            provider.configure(source())
            val preparing = async { provider.warmPlayback(60_000, 30_000) }
            began.await()
            preparing.cancel()
            preparing.join()
            provider.cancelPending()
            assertEquals(listOf(70_000L), targets)
            assertNotNull(withTimeout(3_000) { provider.frameAt(90_000) })
        }
    }

    @Test fun `rendered rewind frames remain available without secondary extraction`() = runBlocking {
        provider().use { provider ->
            provider.configure(source(allowExtraction = false))
            assertEquals(SeekPreviewCapability.UNAVAILABLE, provider.status.value.capability)
            assertNull(provider.rememberRenderedFrame(10_000, bitmap(), provider.sourceGeneration - 1))
            assertNotNull(provider.rememberRenderedFrame(10_000, bitmap(), provider.sourceGeneration))
            assertEquals(SeekPreviewCapability.AVAILABLE, provider.status.value.capability)
            assertNotNull(provider.memoryFrameAt(12_000))
            assertNull(provider.memoryFrameAt(30_000))
            assertTrue(provider.cacheRevision.value > 0)
        }
    }

    @Test fun `bounded warming caches one new frame per turn and notifies the strip`() = runBlocking {
        val decoded = mutableListOf<Long>()
        provider(decode = { _, target ->
            decoded += target
            SeekPreviewImage(bitmap(), actualPositionMs = target)
        }).use { provider ->
            provider.configure(source())
            provider.warmNext(1_000)
            assertEquals(listOf(10_000L), decoded)
            assertNotNull(provider.memoryFrameAt(10_000))
            val revision = provider.cacheRevision.value
            // The scheduler marks itself idle just after the caller has the first frame, and a
            // background warm skips while it's still busy. Retry until it takes the next target,
            // so a slow CI runner doesn't turn that hand-off into a failure.
            withTimeout(10_000) {
                while (decoded.size < 2) {
                    provider.warmNext(1_000)
                    if (decoded.size < 2) delay(10)
                }
            }
            assertEquals(listOf(10_000L, 60_000L), decoded)
            assertTrue(provider.cacheRevision.value > revision)
        }
    }

    @Test fun `nearby display sample is labelled and cannot satisfy precise request`() = runBlocking {
        val decoded = mutableListOf<Long>()
        provider(decode = { _, target -> decoded += target; SeekPreviewImage(bitmap(), actualPositionMs = target) }).use { provider ->
            provider.configure(source())
            provider.frameAt(60_000)
            val nearby = provider.cachedDisplayFrameAt(75_000)!!
            assertTrue(nearby.approximate)
            assertTrue(provider.matchesDisplayTarget(nearby, 75_000))
            assertFalse(provider.matchesTarget(nearby, 75_000))
            assertFalse(provider.matchesDisplayTarget(nearby, 85_000))
            assertNull(provider.cachedDisplayFrameAt(90_000))
            assertEquals(60_000L, nearby.actualPositionMs)
            assertNotNull(provider.frameAt(75_000))
            assertEquals(listOf(60_000L, 75_000L), decoded)
            provider.configure(source())
            assertFalse(provider.matchesDisplayTarget(nearby, 75_000))
            assertNull(provider.cachedDisplayFrameAt(75_000))
        }
    }

    @Test fun `disk index restores nearby preview without decoder or network`() = runBlocking {
        val source = source()
        provider(decode = { _, at -> SeekPreviewImage(bitmap(), actualPositionMs = at) }).use { writer ->
            writer.configure(source)
            writer.frameAt(60_000)
            withTimeout(3_000) {
                while (true) {
                    val found = provider().use { reader ->
                        reader.configure(source)
                        reader.cachedDisplayFrameAt(75_000)
                    }
                    if (found != null) {
                        assertTrue(found.approximate)
                        assertEquals(SeekPreviewOrigin.DISK, found.origin)
                        break
                    }
                    delay(20)
                }
            }
        }
    }

    @Test fun `saved strip restores all five frames without extraction`() = runBlocking {
        val source = source()
        provider(decode = { _, at -> SeekPreviewImage(bitmap(), actualPositionMs = at) }).use { writer ->
            writer.configure(source)
            for (at in listOf(40_000L, 50_000L, 60_000L, 70_000L, 80_000L)) writer.frameAt(at)
            withTimeout(3_000) {
                while (true) {
                    val frames = provider(decode = { _, _ -> error("Saved frames must not decode") }).use { reader ->
                        reader.configure(source)
                        reader.cachedWindowAt(60_000)
                    }
                    if (frames.size == 5) {
                        assertEquals(setOf(40_000L, 50_000L, 60_000L, 70_000L, 80_000L), frames.map { it.positionMs }.toSet())
                        assertTrue(frames.all { it.origin == SeekPreviewOrigin.DISK })
                        break
                    }
                    delay(20)
                }
            }
        }
    }

    @Test fun `cached display never waits behind an active decoder`() = runBlocking {
        val started = CompletableDeferred<Unit>()
        provider(decode = { _, target ->
            if (target == 90_000L) { started.complete(Unit); awaitCancellation() }
            SeekPreviewImage(bitmap(), actualPositionMs = target)
        }).use { provider ->
            provider.configure(source())
            provider.frameAt(60_000)
            val pending = async { provider.frameAt(90_000) }
            started.await()
            try {
                val cached = withTimeout(1_000) { provider.cachedDisplayFrameAt(75_000) }
                assertNotNull(cached)
                assertTrue(cached!!.approximate)
                assertFalse(pending.isCompleted)
            } finally { pending.cancel(); pending.join() }
        }
    }

    @Test fun `speculative failure does not block a subsequent foreground preview`() = runBlocking {
        var first = true
        provider(decode = { _, target ->
            if (first) { first = false; throw UnsupportedPreviewException("Preview network budget exceeded") }
            SeekPreviewImage(bitmap(), actualPositionMs = target)
        }).use { provider ->
            provider.configure(source())
            provider.warmNext(0)
            assertEquals(SeekPreviewCapability.AVAILABLE, provider.status.value.capability)
            assertNotNull(provider.frameAt(30_000))
        }
    }

    @Test fun `verified frames remain reusable across neighboring cache buckets`() = runBlocking {
        provider(decode = { _, _ -> SeekPreviewImage(bitmap(), actualPositionMs = 36_000) }).use { provider ->
            provider.configure(source())
            assertNotNull(provider.frameAt(40_000))
            // 34 seconds rounds to bucket 30, but the real frame at 36 is valid.
            assertNotNull(provider.memoryFrameAt(34_000))
            assertNotNull(provider.cachedFrameAt(34_000))
            assertNull(provider.memoryFrameAt(30_000))
        }
    }

    @Test fun `paused strip loads both forward and backward neighbors after foreground completes`() = runBlocking {
        val decoded = mutableListOf<Long>()
        provider(decode = { _, target -> decoded += target; SeekPreviewImage(bitmap(), actualPositionMs = target) }).use { provider ->
            provider.configure(source())
            assertNotNull(provider.frameAt(60_000))
            provider.warmWindow(60_000)
            assertEquals(listOf(60_000L, 70_000L, 80_000L, 50_000L, 40_000L), decoded)
            for (target in listOf(40_000L, 50_000L, 60_000L, 70_000L, 80_000L)) assertNotNull(provider.memoryFrameAt(target))
        }
    }

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
    fun `cold frame may finish after the old UI deadline without being discarded`() = runBlocking {
        provider(decode = { _, target ->
            delay(4_700)
            SeekPreviewImage(bitmap(), actualPositionMs = target)
        }).use { provider ->
            provider.configure(source())
            assertNotNull(loadSeekPreviewFrame(provider, 30_000))
            assertEquals(SeekPreviewState.READY, provider.status.value.state)
        }
    }

    @Test
    fun `decoder cancellation completes UI request instead of leaving loading forever`() = runBlocking {
        provider(decode = { _, _ -> throw CancellationException("Decoder request cancelled") }).use { provider ->
            provider.configure(source())
            assertNull(loadSeekPreviewFrame(provider, 30_000))
            assertEquals(SeekPreviewState.IDLE, provider.status.value.state)
        }
    }

    @Test
    fun `UI request still propagates cancellation when the user changes target`() = runBlocking {
        val started = CompletableDeferred<Unit>()
        provider(decode = { _, _ -> started.complete(Unit); awaitCancellation() }).use { provider ->
            provider.configure(source())
            val pending = async { loadSeekPreviewFrame(provider, 30_000) }
            started.await()
            pending.cancel()
            pending.join()
            assertTrue(pending.isCancelled)
        }
    }

    @Test
    fun `decoder seeks the target not a rounded bucket that rejects valid keyframes`() = runBlocking {
        provider(decode = { _, target ->
            SeekPreviewImage(bitmap(), actualPositionMs = target - 1_000)
        }).use { provider ->
            provider.configure(source())
            val frame = provider.frameAt(14_999)
            assertNotNull("Rounding down before seeking used to reject this valid frame", frame)
            assertEquals(13_999L, frame!!.actualPositionMs)
            assertTrue(provider.matchesTarget(frame, 14_999))
        }
    }

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
            // Display returns before optional persistence. Keep the writer alive and verify
            // a separate provider can read the completed entry without decoding it again.
            provider().use { reader ->
                reader.configure(source)
                val diskFrame = withTimeout(3_000) {
                    var cached = reader.cachedFrameAt(30_000)
                    while (cached == null) {
                        delay(10)
                        cached = reader.cachedFrameAt(30_000)
                    }
                    cached
                }
                assertEquals(SeekPreviewOrigin.DISK, diskFrame.origin)
                assertEquals(28_160L, diskFrame.positionMs)
                assertEquals(30_000L, diskFrame.requestedPositionMs)
            }
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
    fun `cache reads do not queue behind writer compression or pruning`() {
        val directory = File(context.cacheDir, "provider-read-lock-${UUID.randomUUID()}")
        val cache = SeekPreviewDiskCache(directory, 1_000_000)
        val pool = java.util.concurrent.Executors.newFixedThreadPool(2)
        val locked = java.util.concurrent.CountDownLatch(1)
        val release = java.util.concurrent.CountDownLatch(1)
        try {
            cache.write("ready", CachedPreview(bitmap(), PreviewMetadata("source", 10_000, 10_000, null, null,
                SeekPreviewOrigin.DECODER, SeekPreviewValidity.TIMESTAMP)))
            val writer = pool.submit {
                synchronized(cache) { locked.countDown(); release.await() }
            }
            assertTrue(locked.await(2, java.util.concurrent.TimeUnit.SECONDS))
            // The writer holds its monitor until we release it. Neither a hit nor
            // a miss may wait for that unrelated compression/pruning operation.
            pool.submit {
                assertNull(cache.read("missing", "source"))
                assertEquals(10_000L, cache.read("ready", "source")!!.metadata.actualMs)
            }.get(2, java.util.concurrent.TimeUnit.SECONDS)
            release.countDown()
            writer.get(2, java.util.concurrent.TimeUnit.SECONDS)
        } finally {
            release.countDown()
            pool.shutdownNow()
            directory.deleteRecursively()
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
