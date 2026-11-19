package com.arflix.tv.ui.screens.player.preview

import android.graphics.Color
import android.os.SystemClock
import androidx.test.platform.app.InstrumentationRegistry
import fi.iki.elonen.NanoHTTPD
import java.io.ByteArrayInputStream
import java.io.File
import java.net.ServerSocket
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.cancelAndJoin
import okhttp3.OkHttpClient
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test

class KeyframeWorkerDeviceTest {
    @Test fun controlledColdRangeBenchmark() = runBlocking<Unit> { coldRangeBenchmark(false) }
    @Test fun prestartedWorkerColdRangeBenchmark() = runBlocking<Unit> { coldRangeBenchmark(true) }

    private suspend fun coldRangeBenchmark(prepareWorker: Boolean) {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val input = File(context.getExternalFilesDir(null), "preview-stress-4k-main10.mkv")
        org.junit.Assume.assumeTrue("Supply the generated 32-second 4K Main10 fixture", input.exists())
        val transferred = java.util.concurrent.atomic.AtomicLong()
        val requests = java.util.concurrent.atomic.AtomicInteger()
        val server = object : NanoHTTPD("127.0.0.1", 0) {
            override fun serve(session: IHTTPSession): Response {
                Thread.sleep(100) // Identical upstream latency for both readers.
                requests.incrementAndGet()
                val size = input.length()
                if (session.method == Method.HEAD) return newFixedLengthResponse(Response.Status.OK, "video/x-matroska", "").apply {
                    addHeader("Content-Length", size.toString())
                    addHeader("Accept-Ranges", "bytes")
                }
                val range = Regex("bytes=(\\d+)-(\\d*)").matchEntire(session.headers["range"].orEmpty())
                val start = range?.groupValues?.get(1)?.toLong() ?: 0
                val end = (range?.groupValues?.get(2)?.toLongOrNull() ?: size - 1).coerceAtMost(size - 1)
                if (start >= size || end < start) return newFixedLengthResponse(Response.Status.RANGE_NOT_SATISFIABLE, "text/plain", "")
                val file = java.io.RandomAccessFile(input, "r").apply { seek(start) }
                val body = object : java.io.InputStream() {
                    var remaining = end - start + 1
                    var sent = 0L
                    val began = System.nanoTime()
                    override fun read(): Int = if (remaining-- > 0) file.read() else -1
                    override fun read(bytes: ByteArray, offset: Int, length: Int): Int {
                        if (remaining <= 0) return -1
                        val count = file.read(bytes, offset, minOf(length.toLong(), remaining).toInt())
                        if (count > 0) {
                            remaining -= count
                            transferred.addAndGet(count.toLong())
                            // Pace cumulative bytes so scheduler rounding on small writes
                            // does not accidentally throttle this to a much lower rate.
                            sent += count
                            val nanos = sent * 1_000_000_000L / (10 * 1024 * 1024) - (System.nanoTime() - began)
                            if (nanos > 0) Thread.sleep(nanos / 1_000_000, (nanos % 1_000_000).toInt())
                        }
                        return count
                    }
                    override fun close() = file.close()
                }
                return newFixedLengthResponse(if (range == null) Response.Status.OK else Response.Status.PARTIAL_CONTENT,
                    "video/x-matroska", body, end - start + 1).apply {
                    addHeader("Accept-Ranges", "bytes")
                    if (range != null) addHeader("Content-Range", "bytes $start-$end/$size")
                }
            }
        }.apply { start(NanoHTTPD.SOCKET_READ_TIMEOUT, false) }
        val results = org.json.JSONArray()
        try {
            for (round in 0..2) {
                // A new provider, source identity and worker for each sample: no preview
                // image, range-byte or native demux cache can leak between cold samples.
                SeekPreviewFrameProvider(context, OkHttpClient(), 192).use { provider ->
                    val target = listOf(8_000L, 23_000L, 15_000L)[round]
                    val bytesBefore = transferred.get()
                    val requestsBefore = requests.get()
                    provider.configure(SeekPreviewSource("http://127.0.0.1:${server.listeningPort}/video.mkv", emptyMap(),
                        "cold-range-${SystemClock.elapsedRealtime()}-$round", 32_000,
                        false, false, useNativeKeyframes = true))
                    if (prepareWorker) {
                        assertTrue("Worker can bind and load before a seek", provider.prepareWorker())
                        assertEquals("Preparing the worker must not contact the source", requestsBefore, requests.get())
                        assertNull(provider.memoryFrameAt(target))
                    }
                    val start = SystemClock.elapsedRealtime()
                    val frame = provider.frameAt(target, playbackPaused = true)
                    val centerMs = SystemClock.elapsedRealtime() - start
                    val centerBytes = transferred.get() - bytesBefore
                    assertNotNull("Uncached 4K target must decode", frame)
                    assertTrue(provider.matchesTarget(frame, target))
                    val center = provider.memoryFrameAt(target)
                    assertNotNull(center)
                    provider.warmWindow(target)
                    val window = provider.cachedWindowAt(target)
                    assertEquals(seekPreviewWindow(target, 32_000).size, window.size)
                    val windowMs = SystemClock.elapsedRealtime() - start
                    val cacheStart = System.nanoTime()
                    assertNotNull(provider.memoryFrameAt(target))
                    results.put(JSONObject().put("targetMs", target).put("centerMs", centerMs)
                        .put("actualMs", frame!!.actualPositionMs).put("windowMs", windowMs)
                        .put("centerBytes", centerBytes).put("windowBytes", transferred.get() - bytesBefore)
                        .put("requests", requests.get() - requestsBefore)
                        .put("frames", window.size).put("cachedMs", (System.nanoTime() - cacheStart) / 1_000_000.0))
                }
                delay(500)
            }
        } finally {
            server.stop()
            File(context.getExternalFilesDir(null), if (prepareWorker) "seek-preview-prestarted-report.json" else "seek-preview-benchmark-report.json")
                .writeText(JSONObject().put("workerPrestarted", prepareWorker).put("samples", results).toString(2))
        }
    }

    @Test fun nativeReaderUsesBoundedPullsAndReusesSession() = runBlocking<Unit> {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val bytes = instrumentation.context.assets.open("seek_preview_4k_main10.mp4").use { it.readBytes() }
        val ranges = java.util.Collections.synchronizedList(mutableListOf<Pair<Int, Int>>())
        val invalid = java.util.concurrent.atomic.AtomicInteger()
        val server = object : NanoHTTPD("127.0.0.1", 0) {
            override fun serve(session: IHTTPSession): Response {
                val match = Regex("bytes=(\\d+)-(\\d+)").matchEntire(session.headers["range"].orEmpty())
                if (match == null) {
                    invalid.incrementAndGet()
                    return newFixedLengthResponse(Response.Status.BAD_REQUEST, "text/plain", "")
                }
                val start = match.groupValues[1].toInt()
                val end = match.groupValues[2].toInt().coerceAtMost(bytes.lastIndex)
                if (start !in bytes.indices || end < start)
                    return newFixedLengthResponse(Response.Status.RANGE_NOT_SATISFIABLE, "text/plain", "")
                ranges.add(start to end)
                return newFixedLengthResponse(Response.Status.PARTIAL_CONTENT, "video/mp4",
                    ByteArrayInputStream(bytes, start, end - start + 1), (end - start + 1).toLong()).apply {
                    addHeader("Content-Range", "bytes $start-$end/${bytes.size}")
                    addHeader("Accept-Ranges", "bytes")
                }
            }
        }.apply { start(NanoHTTPD.SOCKET_READ_TIMEOUT, false) }
        try {
            KeyframeWorkerClient(instrumentation.targetContext).use { worker ->
                val url = "http://127.0.0.1:${server.listeningPort}/bounded.mp4"
                assertTrue(worker.prepare())
                assertTrue("Repeated preparation is idempotent", worker.prepare())
                assertTrue("Worker preparation must not open the media", ranges.isEmpty())
                val first = worker.frameAt(url, 12_000, bytes.size.toLong())
                assertNotNull(first)
                val headersBefore = ranges.count { it.first == 0 }
                val second = worker.frameAt(url, 20_000, bytes.size.toLong(), playbackPaused = true)
                assertNotNull(second)
                assertEquals("A second seek must retain the demuxer", headersBefore, ranges.count { it.first == 0 })
                assertTrue(kotlin.math.abs(second!!.positionMs - 20_000) <= 5_000)
                first!!.bitmap.recycle()
                second.bitmap.recycle()
            }
            assertEquals("No open-ended reads", 0, invalid.get())
            assertTrue(ranges.isNotEmpty())
            assertTrue(ranges.all { it.second - it.first + 1 <= 256 * 1024 })
        } finally { server.stop() }
    }

    @Test fun unwatched4kMain10FramesUseIndependentWorkerAndActualTimestamps() = runBlocking<Unit> {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val bytes = instrumentation.context.assets.open("seek_preview_4k_main10.mp4").use { it.readBytes() }
        val port = ServerSocket(0).use { it.localPort }
        var authenticatedRanges = 0
        val server = object : NanoHTTPD("127.0.0.1", port) {
            override fun serve(session: IHTTPSession): Response {
                if (session.headers["x-preview-test"] != "native")
                    return newFixedLengthResponse(Response.Status.UNAUTHORIZED, "text/plain", "")
                if (session.method == Method.HEAD) return newFixedLengthResponse(Response.Status.OK, "video/mp4", "").apply {
                    addHeader("Content-Length", bytes.size.toString())
                    addHeader("Accept-Ranges", "bytes")
                }
                val range = session.headers["range"]?.removePrefix("bytes=")
                val start = range?.substringBefore('-')?.toIntOrNull() ?: 0
                val end = (range?.substringAfter('-')?.toIntOrNull() ?: bytes.lastIndex).coerceAtMost(bytes.lastIndex)
                if (start !in bytes.indices || end < start)
                    return newFixedLengthResponse(Response.Status.RANGE_NOT_SATISFIABLE, "text/plain", "")
                if (range != null) authenticatedRanges++
                return newFixedLengthResponse(if (range == null) Response.Status.OK else Response.Status.PARTIAL_CONTENT,
                    "video/mp4", ByteArrayInputStream(bytes, start, end - start + 1), (end - start + 1).toLong()).apply {
                    addHeader("Accept-Ranges", "bytes")
                    if (range != null) addHeader("Content-Range", "bytes $start-$end/${bytes.size}")
                }
            }
        }.apply { start(NanoHTTPD.SOCKET_READ_TIMEOUT, false) }
        val report = JSONObject()
        try {
            SeekPreviewFrameProvider(context, OkHttpClient(), 192).use { provider ->
                provider.configure(SeekPreviewSource("http://127.0.0.1:$port/video.mp4",
                    mapOf("X-Preview-Test" to "native"), "native-4k-${SystemClock.elapsedRealtime()}",
                    24_000, false, false, useNativeKeyframes = true))
                val start = SystemClock.elapsedRealtime()
                val green = provider.frameAt(12_000)
                report.put("coldMs", SystemClock.elapsedRealtime() - start)
                assertNotNull("Unwatched HEVC Main10 4K frame must decode", green)
                assertTrue(provider.matchesTarget(green, 12_000))
                val greenPixel = green!!.bitmap.getPixel(green.bitmap.width / 2, green.bitmap.height / 2)
                assertTrue(Color.green(greenPixel) > Color.red(greenPixel) + 30)
                val next = SystemClock.elapsedRealtime()
                val blue = provider.frameAt(20_000)
                report.put("secondMs", SystemClock.elapsedRealtime() - next)
                assertNotNull(blue)
                assertTrue(provider.matchesTarget(blue, 20_000))
                val bluePixel = blue!!.bitmap.getPixel(blue.bitmap.width / 2, blue.bitmap.height / 2)
                assertTrue(Color.blue(bluePixel) > Color.green(bluePixel) + 30)
                report.put("firstActualMs", green.actualPositionMs).put("secondActualMs", blue.actualPositionMs)
                val cached = SystemClock.elapsedRealtime()
                assertNotNull(provider.memoryFrameAt(20_000))
                report.put("cachedMs", SystemClock.elapsedRealtime() - cached)
                assertTrue("Remote headers must be preserved by the bridge", authenticatedRanges > 0)
                val trimOutput = android.os.ParcelFileDescriptor.AutoCloseInputStream(
                    instrumentation.uiAutomation.executeShellCommand("am send-trim-memory ${context.packageName}:seek_preview RUNNING_LOW")
                ).bufferedReader().use { it.readText() }
                assertTrue("Memory pressure must reach the independent worker", trimOutput.isBlank())
                val pending = launch { provider.frameAt(4_000) }
                delay(1)
                pending.cancelAndJoin()
                provider.cancelPending()
                provider.configure(SeekPreviewSource("http://127.0.0.1:$port/video.mp4",
                    mapOf("X-Preview-Test" to "native"), "replacement-${SystemClock.elapsedRealtime()}",
                    24_000, false, false, useNativeKeyframes = true))
                assertNull("A previous source's cached frames must not leak", provider.memoryFrameAt(20_000))
                val red = provider.frameAt(4_000)
                assertNotNull("Cancellation/source replacement must not poison the worker", red)
                assertTrue(provider.matchesTarget(red, 4_000))
                val redPixel = red!!.bitmap.getPixel(red.bitmap.width / 2, red.bitmap.height / 2)
                assertTrue(Color.red(redPixel) > Color.blue(redPixel) + 30)
                report.put("cancellationAndSourceReplacement", true)
                report.put("memoryPressureRecovery", true)
            }
        } finally {
            server.stop()
            File(context.getExternalFilesDir(null), "native-keyframe-report.json").writeText(report.toString(2))
        }
    }
}
