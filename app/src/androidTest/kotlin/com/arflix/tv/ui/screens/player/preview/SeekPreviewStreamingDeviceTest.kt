package com.arflix.tv.ui.screens.player.preview

import android.graphics.Bitmap
import android.os.SystemClock
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.PlaybackException
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.PlayerView
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.MainActivity
import java.io.File
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Call
import okhttp3.EventListener
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Test

/** Opt-in live test. Signed URLs/headers are supplied on-device, never in source or test output. */
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class SeekPreviewStreamingDeviceTest {
    @Test fun livePreviewDecodesAtRemotePosition() = runBlocking<Unit> {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val input = File(context.getExternalFilesDir(null), "seek-stream-private.json")
        assumeTrue(input.exists())
        val config = JSONObject(input.readText())
        val observations = java.util.Collections.synchronizedList(mutableListOf<JSONObject>())
        val client = OkHttpClient.Builder().eventListenerFactory {
            object : EventListener() {
                var start = 0L
                val item = JSONObject()
                override fun callStart(call: Call) {
                    start = SystemClock.elapsedRealtime()
                    item.put("method", call.request().method).put("range", call.request().header("Range"))
                }
                override fun responseHeadersEnd(call: Call, response: Response) { item.put("status", response.code) }
                override fun responseBodyEnd(call: Call, byteCount: Long) { item.put("bytes", byteCount) }
                override fun callEnd(call: Call) { item.put("ms", SystemClock.elapsedRealtime() - start); observations.add(item) }
                override fun callFailed(call: Call, ioe: java.io.IOException) {
                    item.put("failure", ioe.javaClass.simpleName).put("ms", SystemClock.elapsedRealtime() - start)
                    observations.add(item)
                }
            }
        }.build()
        val report = JSONObject()
        File(context.getExternalFilesDir(null), "seek-cold-report.json").delete()
        SeekPreviewFrameProvider(context, client, 256).use { provider ->
            val headers = config.optJSONObject("headers")?.let { o -> o.keys().asSequence().associateWith { o.getString(it) } }.orEmpty()
            provider.configure(SeekPreviewSource(config.getString("url"), headers, "cold-live-${SystemClock.elapsedRealtime()}", 3_000_000, false, false,
                useNativeKeyframes = config.optBoolean("nativeKeyframes", false)))
            try {
                val start = SystemClock.elapsedRealtime()
                val frame = provider.frameAt(120_000)
                report.put("coldMs", SystemClock.elapsedRealtime() - start).put("available", frame != null)
                if (config.optBoolean("requirePreview", true)) {
                    assertNotNull("Live source must decode a timestamp-validated frame", frame)
                }
                if (frame != null) assertTrue(provider.matchesTarget(frame, 120_000))
                report.put("actualMs", frame?.actualPositionMs).put("completedWithoutCrash", true)
                if (config.optBoolean("nativeKeyframes", false)) {
                    val samples = JSONArray()
                    for (target in listOf(600_000L, 1_200_000L, 1_800_000L)) {
                        val began = SystemClock.elapsedRealtime()
                        val next = provider.frameAt(target)
                        samples.put(JSONObject().put("targetMs", target).put("ms", SystemClock.elapsedRealtime() - began)
                            .put("available", next != null).put("actualMs", next?.actualPositionMs))
                        report.put("unwatchedPositions", samples)
                        assertNotNull("Unwatched remote position $target must decode", next)
                        assertTrue(provider.matchesTarget(next, target))
                    }
                }
            } finally {
                report.put("requests", JSONArray(observations.toList()))
                File(context.getExternalFilesDir(null), "seek-cold-report.json").writeText(report.toString(2))
            }
        }
    }

    @Test fun liveStreamPlaybackWithAndWithoutPreviewWork() = runBlocking {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val input = File(context.getExternalFilesDir(null), "seek-stream-private.json")
        assumeTrue("Supply a private live-stream fixture on the emulator", input.exists())
        val config = JSONObject(input.readText())
        playbackComparison(config)
    }

    /** Reproducible CPU/UI regression check; this is not a debrid throughput benchmark. */
    @Test fun controlledPlaybackWhileDecoding4kPreviews() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val directory = context.getExternalFilesDir(null)!!
        val playback = File(directory, "preview-playback-1080.mp4")
        val preview = File(directory, "preview-ui-4k-main10.mkv")
        assumeTrue("Supply generated playback and 4K preview fixtures", playback.exists() && preview.exists())
        val server = object : fi.iki.elonen.NanoHTTPD("127.0.0.1", 0) {
            override fun serve(session: IHTTPSession): Response {
                val file = if (session.uri == "/preview.mkv") preview else playback
                val size = file.length()
                if (session.method == Method.HEAD) return newFixedLengthResponse(Response.Status.OK, "application/octet-stream", "").apply {
                    addHeader("Content-Length", size.toString())
                    addHeader("Accept-Ranges", "bytes")
                }
                val range = Regex("bytes=(\\d+)-(\\d*)").matchEntire(session.headers["range"].orEmpty())
                val start = range?.groupValues?.get(1)?.toLong() ?: 0
                val end = (range?.groupValues?.get(2)?.toLongOrNull() ?: size - 1).coerceAtMost(size - 1)
                if (start >= size || end < start) return newFixedLengthResponse(Response.Status.RANGE_NOT_SATISFIABLE, "text/plain", "")
                val input = file.inputStream().apply { channel.position(start) }
                return newFixedLengthResponse(if (range == null) Response.Status.OK else Response.Status.PARTIAL_CONTENT,
                    if (file == preview) "video/x-matroska" else "video/mp4", input, end - start + 1).apply {
                    addHeader("Accept-Ranges", "bytes")
                    if (range != null) addHeader("Content-Range", "bytes $start-$end/$size")
                }
            }
        }.apply { start(fi.iki.elonen.NanoHTTPD.SOCKET_READ_TIMEOUT, false) }
        try {
            playbackComparison(JSONObject().put("url", "http://127.0.0.1:${server.listeningPort}/playback.mp4")
                .put("previewUrl", "http://127.0.0.1:${server.listeningPort}/preview.mkv")
                .put("nativeKeyframes", true).put("controlledFixture", true))
        } finally { server.stop() }
    }

    private suspend fun playbackComparison(config: JSONObject) = coroutineScope {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val controlled = config.optBoolean("controlledFixture", false)
        val url = config.getString("url")
        val previewUrl = config.optString("previewUrl", url)
        val separatePreviewSource = previewUrl != url
        val nativeKeyframes = config.optBoolean("nativeKeyframes", false)
        val headers = config.optJSONObject("headers")?.let { o -> o.keys().asSequence().associateWith { o.getString(it) } }.orEmpty()
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        var player: ExoPlayer? = null
        var videoSurface: android.view.SurfaceView? = null
        val renderedPts = java.util.concurrent.atomic.AtomicLong(-1L)
        var errors = 0
        var rebuffers = 0
        var dropped = 0
        var measuring = false
        val selected = mutableStateOf<SeekPreviewFrame?>(null)
        val neighbors = mutableStateOf<List<SeekPreviewFrame>>(emptyList())
        val selectedPosition = mutableStateOf(120_000L)
        val provider = SeekPreviewFrameProvider(context, OkHttpClient(), 256)
        val report = JSONObject()
        report.put("controlledFixture", controlled)
        report.put("previewStreamSeparateFromPlayback", separatePreviewSource)
        File(context.getExternalFilesDir(null), "seek-stream-report.json").delete()
        suspend fun <T> main(block: () -> T): T = withContext(Dispatchers.Main) { block() }
        try {
            scenario.onActivity { activity ->
                val http = DefaultHttpDataSource.Factory().setDefaultRequestProperties(headers)
                val renderers = DefaultRenderersFactory(activity).setEnableDecoderFallback(true)
                    .setExtensionRendererMode(DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON)
                    .forceDisableMediaCodecAsynchronousQueueing()
                if (config.optBoolean("preferSoftwareVideo", false)) renderers.setMediaCodecSelector(MediaCodecSelector.PREFER_SOFTWARE)
                player = ExoPlayer.Builder(activity).setRenderersFactory(renderers)
                    .setMediaSourceFactory(DefaultMediaSourceFactory(http)).build().also { p ->
                    p.setVideoFrameMetadataListener { pts, _, _, _ -> renderedPts.set(pts / 1_000L) }
                    p.addListener(object : Player.Listener {
                        override fun onPlayerError(error: PlaybackException) { errors++ }
                        override fun onPlaybackStateChanged(state: Int) {
                            if (measuring && state == Player.STATE_BUFFERING) rebuffers++
                        }
                    })
                    p.addAnalyticsListener(object : AnalyticsListener {
                        override fun onDroppedVideoFrames(eventTime: AnalyticsListener.EventTime, droppedFrames: Int, elapsedMs: Long) {
                            dropped += droppedFrames
                        }
                    })
                    p.setMediaItem(MediaItem.fromUri(url))
                    p.prepare()
                    p.play()
                }
                activity.setContent {
                    Box(Modifier.fillMaxSize().background(Color.Black)) {
                        AndroidView(factory = { PlayerView(it).also { view ->
                            view.player = player; view.useController = false
                            videoSurface = view.videoSurfaceView as? android.view.SurfaceView
                        } }, modifier = Modifier.fillMaxSize())
                        if (selected.value != null) SeekPreviewFilmstrip(selected.value, neighbors.value,
                            selectedPosition.value, provider.sourceGeneration,
                            Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(horizontal = 48.dp, vertical = 40.dp))
                    }
                }
            }
            withTimeout(60_000) { while (!main { player!!.isPlaying && player!!.currentPosition > 2_000 }) delay(250) }
            report.put("renderedPtsMs", renderedPts.get())
            report.put("videoMime", main { player!!.videoFormat?.sampleMimeType })
            assertTrue("Playback comparison requires actual rendered video, not an audio-only clock", renderedPts.get() >= 0)
            val duration = main { player!!.duration }
            assertTrue("The file must have a seekable duration", duration > if (controlled) 30_000 else 180_000)
            val previewDuration = if (controlled) 64_000L else duration
            provider.configure(SeekPreviewSource(previewUrl, headers, "live-test-${SystemClock.elapsedRealtime()}", previewDuration, false, false,
                useNativeKeyframes = nativeKeyframes))
            main { measuring = true }
            val baselineStart = main { player!!.currentPosition }
            val dropsStart = main { dropped }
            delay(if (controlled) 10_000 else 30_000)
            report.put("baseline", JSONObject().put("positionAdvancedMs", main { player!!.currentPosition } - baselineStart)
                .put("rebuffers", main { rebuffers }).put("droppedFrames", main { dropped } - dropsStart))
            main { rebuffers = 0 }
            val enabledStart = main { player!!.currentPosition }
            val enabledDrops = main { dropped }
            if (nativeKeyframes) {
                val prepared = provider.prepareWorker()
                report.put("workerPreparedDuringPlayback", prepared)
                if (controlled) assertTrue("Worker prestart must succeed during healthy playback", prepared)
            }
            val guard = SeekPreviewPlaybackGuard()
            var job: Job? = null
            var turns = 0
            var warmed = 0
            var captured = 0
            var lastBucket = -1L
            val activityManager = context.getSystemService(android.content.Context.ACTIVITY_SERVICE) as android.app.ActivityManager
            val isTv = context.packageManager.hasSystemFeature("android.software.leanback")
            val resourceSafe = !activityManager.isLowRamDevice && (nativeKeyframes ||
                (activityManager.memoryClass >= 256 && !(isTv && activityManager.memoryClass <= 384)))
            report.put("memoryClassMb", activityManager.memoryClass).put("backgroundDecodeAllowed", resourceSafe)
            var nextAt = 0L
            val end = SystemClock.elapsedRealtime() + if (controlled) 15_000 else 45_000
            try {
                while (SystemClock.elapsedRealtime() < end) {
                    val now = SystemClock.elapsedRealtime()
                    val safe = main { guard.mayWarm(now, player!!.isPlaying,
                        player!!.playbackState == Player.STATE_READY, player!!.totalBufferedDuration,
                        false, true, resourceSafe) }
                    // Exercise the same no-extra-decoder path used on constrained devices.
                    if (!separatePreviewSource) main {
                        val p = player!!
                        val pts = renderedPts.get()
                        val bucket = quantizeSeekPreviewPosition(p.currentPosition, duration)
                        val surface = videoSurface?.holder?.surface
                        if (bucket != lastBucket && kotlin.math.abs(p.currentPosition - bucket) <= 750 &&
                            kotlin.math.abs(pts - p.currentPosition) <= 500 && p.isPlaying && surface?.isValid == true) {
                            lastBucket = bucket
                            val before = pts
                            val generation = provider.sourceGeneration
                            launch(Dispatchers.Main) {
                                val bitmap = withTimeoutOrNull(RENDERED_PREVIEW_COPY_TIMEOUT_MS) { captureRenderedSeekPreview(surface, 320, 180) }
                                if (bitmap != null) {
                                    if (renderedPreviewCopyIsCurrent(before, renderedPts.get(), p.currentPosition) && p.isPlaying) {
                                        if (provider.rememberRenderedFrame(before, bitmap, generation) != null) captured++
                                        else bitmap.recycle()
                                    } else bitmap.recycle()
                                }
                            }
                        }
                    }
                    if (!safe && job?.isActive == true) { job?.cancel(); provider.cancelPending() }
                    if (safe && job?.isActive != true && now >= nextAt) {
                        val position = main { player!!.currentPosition }
                        val buffered = main { player!!.totalBufferedDuration }
                        nextAt = now + if (buffered >= 30_000) 2_000 else 3_000
                        turns++
                        job = launch {
                            val targets = seekPreviewWarmTargets(position, duration)
                            val missing = targets.filter { provider.memoryFrameAt(it) == null }
                            provider.warmPlayback(position, buffered)
                            if (missing.any { provider.memoryFrameAt(it) != null }) warmed++
                        }
                    }
                    delay(250)
                }
            } finally { job?.cancelAndJoin(); provider.cancelPending() }
            report.put("previewsEnabled", JSONObject().put("positionAdvancedMs", main { player!!.currentPosition } - enabledStart)
                .put("rebuffers", main { rebuffers }).put("droppedFrames", main { dropped } - enabledDrops)
                .put("warmTurns", turns).put("warmFramesProduced", warmed).put("renderedFramesCaptured", captured))
            if (nativeKeyframes) assertTrue("Comparison must actually decode previews during playback", warmed > 0)
            main { measuring = false; player!!.pause() }
            val target = if (controlled) 30_000L else if (nativeKeyframes) 600_000L else
                (main { player!!.currentPosition } / 10_000L * 10_000L - 20_000L).coerceAtLeast(0)
            if (controlled) {
                val lookupStarted = SystemClock.elapsedRealtime()
                val prepared = provider.cachedWindowAt(target)
                report.put("preparedWindowFramesBeforeBrowsing", prepared.size)
                    .put("preparedWindowLookupMs", SystemClock.elapsedRealtime() - lookupStarted)
                assertEquals("Healthy playback must prepare the full window before browsing starts", 5, prepared.size)
            }
            selectedPosition.value = target
            val coldStart = SystemClock.elapsedRealtime()
            val frame = if (nativeKeyframes) provider.frameAt(target) else provider.memoryFrameAt(target)
            report.put("renderedPreviewMs", SystemClock.elapsedRealtime() - coldStart).put("previewAvailable", frame != null)
            assertNotNull("Video must yield a validated preview", frame)
            assertTrue(provider.matchesTarget(frame, target))
            selected.value = frame
            if (nativeKeyframes) provider.warmWindow(target)
            neighbors.value = provider.cachedWindowAt(target)
            report.put("readyWindowFrames", neighbors.value.size)
            if (controlled) assertEquals("Center plus two previews on each side", 5, neighbors.value.size)
            if (nativeKeyframes) assertTrue("Paused browsing should fill neighboring thumbnails", neighbors.value.size >= 3)
            val times = (0 until 100).map {
                val start = System.nanoTime()
                assertNotNull(provider.memoryFrameAt(target))
                (System.nanoTime() - start) / 1_000_000.0
            }.sorted()
            report.put("cachedP95Ms", times[94])
            assertTrue(times[94] < 100)
            delay(500)
            val evidence = File(context.getExternalFilesDir(null), "preview-evidence").apply { mkdirs() }
            instrumentation.uiAutomation.takeScreenshot()?.let { bitmap ->
                File(evidence, if (controlled) "controlled-4k-window.png" else "live-debrid-window.png").outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
                bitmap.recycle()
            }
            provider.cancelPending()
            selected.value = null
            val resumeAt = if (controlled) 5_000L else 120_000L
            main { player!!.seekTo(resumeAt); player!!.play() }
            withTimeout(15_000) { while (!main { player!!.isPlaying && player!!.currentPosition >= resumeAt + 1_000 }) delay(250) }
            report.put("resumeAfterSeek", true).put("playerErrors", main { errors })
            assertEquals(0, main { errors })
            assertEquals("No new rebuffer during preview window", 0, report.getJSONObject("previewsEnabled").getInt("rebuffers"))
            assertTrue("Preview work must not materially increase frame drops",
                report.getJSONObject("previewsEnabled").getInt("droppedFrames") <=
                    report.getJSONObject("baseline").getInt("droppedFrames") * 1.5 + 10)
        } finally {
            File(context.getExternalFilesDir(null), "seek-stream-report.json").writeText(report.toString(2))
            provider.close()
            main { player?.release() }
            scenario.close()
        }
    }
}
