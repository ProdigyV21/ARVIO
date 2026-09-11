package com.arflix.tv.ui.screens.player.preview

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

class SeekPreviewFrameProviderTest {
    @Test
    fun `quantization selects nearest ten second frame`() {
        val duration = 7_200_000L

        assertEquals(0L, quantizeSeekPreviewPosition(0L, duration))
        assertEquals(10_000L, quantizeSeekPreviewPosition(6_000L, duration))
        assertEquals(20_000L, quantizeSeekPreviewPosition(15_000L, duration))
        assertEquals(1_230_000L, quantizeSeekPreviewPosition(1_226_000L, duration))
    }

    @Test
    fun `quantization clamps positions to the media duration`() {
        val duration = 93_456L

        assertEquals(0L, quantizeSeekPreviewPosition(-10_000L, duration))
        assertEquals(duration, quantizeSeekPreviewPosition(100_000L, duration))
        assertEquals(duration, quantizeSeekPreviewPosition(Long.MAX_VALUE, duration))
    }

    @Test
    fun `unknown duration never produces an invalid preview position`() {
        assertEquals(0L, quantizeSeekPreviewPosition(30_000L, 0L))
        assertEquals(0L, quantizeSeekPreviewPosition(30_000L, -1L))
    }

    @Test
    fun `held remote navigation accelerates in controlled steps`() {
        assertEquals(10_000L, acceleratedSeekPreviewStepMs(0))
        assertEquals(10_000L, acceleratedSeekPreviewStepMs(7))
        assertEquals(30_000L, acceleratedSeekPreviewStepMs(8))
        assertEquals(30_000L, acceleratedSeekPreviewStepMs(17))
        assertEquals(60_000L, acceleratedSeekPreviewStepMs(18))
    }

    @Test
    fun `preview dimensions preserve widescreen aspect ratio`() {
        assertEquals(416 to 173, fitSeekPreviewDimensions(1920, 800, 416, 234))
    }

    @Test
    fun `preview dimensions preserve four by three aspect ratio`() {
        assertEquals(312 to 234, fitSeekPreviewDimensions(1440, 1080, 416, 234))
    }

    @Test
    fun `preview dimensions keep sixteen by nine within bounds`() {
        assertEquals(416 to 234, fitSeekPreviewDimensions(1920, 1080, 416, 234))
    }

    @Test
    fun `display aspect ratio respects anamorphic pixels`() {
        val ratio = seekPreviewDisplayAspectRatio(
            videoWidth = 720,
            videoHeight = 576,
            pixelWidthHeightRatio = 64f / 45f,
            unappliedRotationDegrees = 0,
            fallbackWidth = 1920,
            fallbackHeight = 1080,
        )

        assertEquals(16f / 9f, ratio, 0.001f)
        assertEquals(416 to 234, fitSeekPreviewAspectRatio(ratio, 416, 234))
    }

    @Test
    fun `display aspect ratio preserves cinema width without cropping`() {
        val ratio = seekPreviewDisplayAspectRatio(
            videoWidth = 1920,
            videoHeight = 800,
            pixelWidthHeightRatio = 1f,
            unappliedRotationDegrees = 0,
            fallbackWidth = 1920,
            fallbackHeight = 1080,
        )

        assertEquals(2.4f, ratio, 0.001f)
        assertEquals(416 to 173, fitSeekPreviewAspectRatio(ratio, 416, 234))
    }

    @Test
    fun `display aspect ratio accounts for unapplied rotation`() {
        val ratio = seekPreviewDisplayAspectRatio(
            videoWidth = 1920,
            videoHeight = 1080,
            pixelWidthHeightRatio = 1f,
            unappliedRotationDegrees = 90,
            fallbackWidth = 1920,
            fallbackHeight = 1080,
        )

        assertEquals(9f / 16f, ratio, 0.001f)
        assertEquals(132 to 234, fitSeekPreviewAspectRatio(ratio, 416, 234))
    }

    @Test
    fun `hysteresis prevents bucket flipping from small jitter across boundaries`() {
        val duration = 7_200_000L

        // Initial scrub with uninitialized bucket (-1L)
        val initialBucket = quantizeSeekPreviewPositionWithHysteresis(-1L, 20_000L, duration)
        assertEquals(20_000L, initialBucket)

        // Micro-tremor moving slightly forward past the 5s rounding midpoint (25_500ms):
        // Normal quantize would jump to 30_000L, but hysteresis holds 20_000L
        assertEquals(20_000L, quantizeSeekPreviewPositionWithHysteresis(20_000L, 25_500L, duration))
        assertEquals(20_000L, quantizeSeekPreviewPositionWithHysteresis(20_000L, 27_000L, duration))

        // Micro-tremor moving backwards (14_500ms):
        // Normal quantize would jump to 10_000L, but hysteresis holds 20_000L
        assertEquals(20_000L, quantizeSeekPreviewPositionWithHysteresis(20_000L, 14_500L, duration))
        assertEquals(20_000L, quantizeSeekPreviewPositionWithHysteresis(20_000L, 13_000L, duration))

        // Deliberate movement beyond the ±7,500ms hysteresis threshold switches buckets
        assertEquals(30_000L, quantizeSeekPreviewPositionWithHysteresis(20_000L, 28_000L, duration))
        assertEquals(10_000L, quantizeSeekPreviewPositionWithHysteresis(20_000L, 12_000L, duration))
    }

    @Test
    fun `metadata round trip preserves actual timestamp instead of requested bucket`() {
        val metadata = PreviewMetadata("media-account-version", 30_000, 28_160, null, null, SeekPreviewOrigin.DECODER, SeekPreviewValidity.TIMESTAMP)
        val bytes = ByteArrayOutputStream().also { metadata.write(DataOutputStream(it)) }.toByteArray()
        val restored = PreviewMetadata.read(DataInputStream(ByteArrayInputStream(bytes)))
        assertEquals(30_000L, restored.extractedForMs)
        assertEquals(28_160L, restored.actualMs)
        assertTrue(restored.matches(30_000))
        assertFalse(restored.matches(40_000))
    }

    @Test
    fun `cue metadata keeps exclusive interval and does not manufacture a timestamp`() {
        val metadata = PreviewMetadata("sprite-v2", 19_999, null, 10_000, 20_000, SeekPreviewOrigin.PROVIDER, SeekPreviewValidity.CUE)
        val bytes = ByteArrayOutputStream().also { metadata.write(DataOutputStream(it)) }.toByteArray()
        val restored = PreviewMetadata.read(DataInputStream(ByteArrayInputStream(bytes)))
        assertEquals(null, restored.actualMs)
        assertTrue(restored.matches(10_000))
        assertTrue(restored.matches(19_999))
        assertFalse(restored.matches(20_000))
    }

    @Test(expected = java.io.IOException::class)
    fun `v4 cache entries cannot be promoted into verified v5 frames`() {
        val bytes = ByteArrayOutputStream().also { DataOutputStream(it).writeInt(4) }.toByteArray()
        PreviewMetadata.read(DataInputStream(ByteArrayInputStream(bytes)))
    }

    @Test
    fun `unverified and distant sync frames are not valid even at requested bucket`() {
        assertFalse(seekPreviewTimeMatches(30_000, null, null, null, SeekPreviewValidity.UNVERIFIED))
        assertFalse(seekPreviewTimeMatches(30_000, 10_000, null, null, SeekPreviewValidity.TIMESTAMP))
        assertFalse(seekPreviewTimeMatches(Long.MAX_VALUE, 0, null, null, SeekPreviewValidity.TIMESTAMP))
    }

    @Test
    fun `scheduler retains only latest target until cancelled operation really exits`() = runTest {
        val scheduler = SeekPreviewExtractionScheduler<Int>(backgroundScope)
        val cleanup = CompletableDeferred<Unit>()
        val started = mutableListOf<Int>()
        val first = async {
            scheduler.submit {
                started += 1
                try { awaitCancellation() } finally { withContext(NonCancellable) { cleanup.await() } }
            }
        }
        runCurrent()
        val obsolete = (2..99).map { target ->
            async { scheduler.submit { started += target; target } }.also { runCurrent() }
        }
        val latest = async { scheduler.submit { started += 100; 100 } }
        runCurrent()
        assertTrue(first.isCancelled)
        assertTrue(obsolete.all { it.isCancelled })
        assertEquals(listOf(1), started)
        cleanup.complete(Unit)
        runCurrent()
        assertEquals(100, latest.await())
        assertEquals(listOf(1, 100), started)
        scheduler.close()
    }

    @Test
    fun `idle warming never cancels foreground extraction`() = runTest {
        val scheduler = SeekPreviewExtractionScheduler<Int>(backgroundScope)
        val release = CompletableDeferred<Unit>()
        val foreground = async { scheduler.submit { release.await(); 7 } }
        runCurrent()
        assertEquals(null, scheduler.submit(background = true) { error("Background must not run") })
        assertFalse(foreground.isCancelled)
        release.complete(Unit)
        runCurrent()
        assertEquals(7, foreground.await())
        scheduler.close()
    }
}
