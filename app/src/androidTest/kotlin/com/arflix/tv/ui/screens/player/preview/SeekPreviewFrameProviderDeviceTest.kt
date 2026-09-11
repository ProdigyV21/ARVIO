package com.arflix.tv.ui.screens.player.preview

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.SystemClock
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import fi.iki.elonen.NanoHTTPD
import java.io.ByteArrayInputStream
import java.io.File
import java.net.ServerSocket
import java.util.concurrent.atomic.AtomicBoolean
import java.util.UUID
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import org.junit.Assert.assertFalse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SeekPreviewFrameProviderDeviceTest {
    @Test
    fun changingTargetDuringColdDecodeRetainsLateFrameAndLoadsNewTarget() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val file = File(context.cacheDir, "seek_preview_cancel_test.mp4")
        InstrumentationRegistry.getInstrumentation().context.assets.open("seek_preview_device_test.mp4").use { input ->
            file.outputStream().use(input::copyTo)
        }
        val provider = SeekPreviewFrameProvider(context, OkHttpClient(), 256)
        try {
            provider.configure(SeekPreviewSource(
                Uri.fromFile(file).toString(), emptyMap(), UUID.randomUUID().toString(),
                30_000L, false, false,
            ))
            val opening = async { provider.frameAt(2_000L) }
            delay(350)
            opening.cancelAndJoin()
            val ending = provider.frameAt(22_000L)
            assertNotNull("Changing target must not destroy the decoder or poison its next request", ending)
            assertTimestampAndScene(ending!!, 22_000L, 22_000L, red = false)
            val earlier = provider.frameAt(2_000L)
            assertNotNull(earlier)
            assertTimestampAndScene(earlier!!, 2_000L, 2_000L, red = true, origin = SeekPreviewOrigin.MEMORY)
        } finally {
            provider.close()
            file.delete()
        }
    }

    @Test
    fun localVideoReturnsDifferentFramesAcrossTimeline() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val sourceFile = File(context.cacheDir, "seek_preview_device_test.mp4")
        InstrumentationRegistry.getInstrumentation().context.assets
            .open("seek_preview_device_test.mp4").use { input ->
            sourceFile.outputStream().use(input::copyTo)
        }
        val provider = SeekPreviewFrameProvider(
            context = context,
            playbackClient = OkHttpClient(),
            memoryClassMb = 256,
        )

        try {
            provider.configure(
                SeekPreviewSource(
                    url = Uri.fromFile(sourceFile).toString(),
                    headers = emptyMap(),
                    cacheIdentity = "device-test-three-scenes-${UUID.randomUUID()}",
                    durationMs = 30_000L,
                    isLive = false,
                    isAdaptive = false,
                )
            )

            val openingFrame = provider.frameAt(2_000L)
            val endingFrame = provider.frameAt(22_000L)

            assertNotNull("The opening preview frame should be decoded", openingFrame)
            assertNotNull("The ending preview frame should be decoded", endingFrame)
            assertFalse(
                "Timeline previews must change as the scrub position changes",
                openingFrame!!.bitmap.sameAs(endingFrame!!.bitmap),
            )
            assertTimestampAndScene(openingFrame, 2_000L, 2_000L, red = true)
            assertTimestampAndScene(endingFrame, 22_000L, 22_000L, red = false)
            assertWarmMemoryLatency(provider, 22_000L, 22_000L)
            assertTrue(
                "A decoded frame must be immediately available from memory",
                provider.memoryFrameAt(2_000L)?.bitmap === openingFrame.bitmap,
            )
        } finally {
            provider.close()
            sourceFile.delete()
        }
    }

    @Test
    fun rangedHttpVideoReturnsDifferentFramesAcrossTimeline() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val videoBytes = InstrumentationRegistry.getInstrumentation().context.assets
            .open("seek_preview_device_test.mp4").use { it.readBytes() }
        val port = ServerSocket(0).use { it.localPort }
        val server = RangeVideoServer(videoBytes, port).apply {
            start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
        }
        val provider = SeekPreviewFrameProvider(
            context = context,
            playbackClient = OkHttpClient(),
            memoryClassMb = 256,
        )

        try {
            provider.configure(
                SeekPreviewSource(
                    url = "http://127.0.0.1:$port/video.mp4",
                    headers = mapOf("X-Preview-Test" to "range"),
                    cacheIdentity = "device-test-range-http-${UUID.randomUUID()}",
                    durationMs = 30_000L,
                    isLive = false,
                    isAdaptive = false,
                )
            )

            val openingFrame = provider.frameAt(2_000L)
            val endingFrame = provider.frameAt(22_000L)

            assertNotNull("The ranged opening frame should be decoded", openingFrame)
            assertNotNull("The ranged ending frame should be decoded", endingFrame)
            assertFalse(
                "Ranged HTTP previews must change with the scrub position",
                openingFrame!!.bitmap.sameAs(endingFrame!!.bitmap),
            )
            assertTimestampAndScene(openingFrame, 2_000L, 2_000L, red = true)
            assertTimestampAndScene(endingFrame, 22_000L, 22_000L, red = false)
            assertWarmMemoryLatency(provider, 22_000L, 22_000L)
            assertTrue("Playback authentication headers must reach the media server", server.sawAuthHeader.get())
            assertTrue("Progressive previews must use byte-range requests", server.sawRangeRequest.get())
        } finally {
            provider.close()
            server.stop()
        }
    }

    private fun assertTimestampAndScene(frame: SeekPreviewFrame, requestedMs: Long, actualMs: Long, red: Boolean, origin: SeekPreviewOrigin = SeekPreviewOrigin.DECODER) {
        // Fixture is red at 0..10s, green at 10..20s, blue at 20..30s, with 1s sync frames.
        assertEquals(requestedMs, frame.requestedPositionMs)
        assertEquals(actualMs, frame.actualPositionMs)
        assertEquals(actualMs, frame.positionMs)
        assertEquals(SeekPreviewValidity.TIMESTAMP, frame.validity)
        assertEquals(origin, frame.origin)
        assertTrue(frame.bitmap.width <= 480 && frame.bitmap.height <= 270)
        assertEquals(16f / 9f, frame.bitmap.width.toFloat() / frame.bitmap.height, 0.02f)
        val points = listOf(
            0 to 0, frame.bitmap.width - 1 to 0,
            0 to frame.bitmap.height - 1, frame.bitmap.width - 1 to frame.bitmap.height - 1,
            frame.bitmap.width / 2 to frame.bitmap.height / 2,
        )
        for ((x, y) in points) {
            val color = frame.bitmap.getPixel(x, y)
            val expectedChannel = if (red) Color.red(color) else Color.blue(color)
            val otherChannel = if (red) Color.blue(color) else Color.red(color)
            assertTrue("Visible scene including corners must agree with its timestamp", expectedChannel > otherChannel + 100)
            assertTrue("Solid scene should not be a blank decoder surface", expectedChannel > 150)
        }
    }

    private fun assertWarmMemoryLatency(provider: SeekPreviewFrameProvider, targetMs: Long, actualMs: Long) {
        val elapsedNanos = LongArray(100) {
            val started = SystemClock.elapsedRealtimeNanos()
            val frame = provider.memoryFrameAt(targetMs)
            val elapsed = SystemClock.elapsedRealtimeNanos() - started
            assertNotNull(frame)
            assertEquals(actualMs, frame!!.actualPositionMs)
            assertTrue(provider.matchesTarget(frame, targetMs))
            elapsed
        }.sorted()
        val p95Ms = elapsedNanos[94] / 1_000_000.0
        assertTrue("100 warm reads p95=${p95Ms}ms must be below 100ms", p95Ms < 100.0)
    }

    private class RangeVideoServer(
        private val videoBytes: ByteArray,
        port: Int,
    ) : NanoHTTPD(port) {
        val sawAuthHeader = AtomicBoolean(false)
        val sawRangeRequest = AtomicBoolean(false)

        override fun serve(session: IHTTPSession): Response {
            if (session.headers["x-preview-test"] == "range") {
                sawAuthHeader.set(true)
            }
            if (session.method == Method.HEAD) {
                return newFixedLengthResponse(Response.Status.OK, "video/mp4", "").apply {
                    addHeader("Content-Length", videoBytes.size.toString())
                    addHeader("Accept-Ranges", "bytes")
                }
            }
            val requestedRange = session.headers["range"]
                ?.removePrefix("bytes=")
                ?.substringBefore(',')
            if (requestedRange != null) {
                sawRangeRequest.set(true)
                val start = requestedRange.substringBefore('-').toLongOrNull()?.coerceAtLeast(0L) ?: 0L
                if (start >= videoBytes.size) {
                    return newFixedLengthResponse(Response.Status.RANGE_NOT_SATISFIABLE, "text/plain", "")
                }
                val requestedEnd = requestedRange.substringAfter('-', "").toLongOrNull()
                val end = (requestedEnd ?: videoBytes.lastIndex.toLong())
                    .coerceIn(start, videoBytes.lastIndex.toLong())
                val length = (end - start + 1L).toInt()
                return newFixedLengthResponse(
                    Response.Status.PARTIAL_CONTENT,
                    "video/mp4",
                    ByteArrayInputStream(videoBytes, start.toInt(), length),
                    length.toLong(),
                ).apply {
                    addHeader("Accept-Ranges", "bytes")
                    addHeader("Content-Range", "bytes $start-$end/${videoBytes.size}")
                }
            }
            return newFixedLengthResponse(
                Response.Status.OK,
                "video/mp4",
                ByteArrayInputStream(videoBytes),
                videoBytes.size.toLong(),
            ).apply { addHeader("Accept-Ranges", "bytes") }
        }
    }
}
