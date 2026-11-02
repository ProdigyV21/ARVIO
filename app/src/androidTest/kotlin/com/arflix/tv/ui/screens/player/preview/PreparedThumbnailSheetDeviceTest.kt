package com.arflix.tv.ui.screens.player.preview

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.SystemClock
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.StreamPreviewKind
import com.arflix.tv.data.model.StreamPreviewMetadata
import fi.iki.elonen.NanoHTTPD
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test

class PreparedThumbnailSheetDeviceTest {
    @Test fun prepareWholeWindowAndRevisitPreviousSheetWithoutDownloadingOrDecodingVideo(): Unit = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val colors = listOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN)
        val sheet = Bitmap.createBitmap(160, 18, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(sheet)
        colors.forEachIndexed { i, color -> canvas.drawRect(i * 32f, 0f, (i + 1) * 32f, 18f, Paint().apply { this.color = color }) }
        val bytes = ByteArrayOutputStream().use { out -> sheet.compress(Bitmap.CompressFormat.PNG, 100, out); out.toByteArray() }
        sheet.recycle()
        val a = AtomicInteger()
        val b = AtomicInteger()
        val manifests = AtomicInteger()
        val unexpected = AtomicInteger()
        fun time(seconds: Int) = "%02d:%02d:%02d.000".format(java.util.Locale.ROOT, seconds / 3600, seconds / 60 % 60, seconds % 60)
        val manifest = "WEBVTT\n\n" + (0..11).joinToString("\n\n") { index ->
            val name = if (index < 5 || index >= 10) "a" else "b"
            "${time(index * 10)} --> ${time((index + 1) * 10)}\n$name.png#xywh=${index % 5 * 32},0,32,18"
        }
        val server = object : NanoHTTPD("127.0.0.1", 0) {
            override fun serve(session: IHTTPSession): Response {
                Thread.sleep(100)
                return when (session.uri) {
                    "/story.vtt" -> { manifests.incrementAndGet(); newFixedLengthResponse(Response.Status.OK, "text/vtt", manifest) }
                    "/a.png", "/b.png" -> {
                        if (session.uri == "/a.png") a.incrementAndGet() else b.incrementAndGet()
                        newFixedLengthResponse(Response.Status.OK, "image/png", ByteArrayInputStream(bytes), bytes.size.toLong())
                    }
                    else -> { unexpected.incrementAndGet(); newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "") }
                }
            }
        }.apply { start(NanoHTTPD.SOCKET_READ_TIMEOUT, false) }
        val report = JSONObject()
        try {
            val base = "http://127.0.0.1:${server.listeningPort}"
            val source = SeekPreviewSource("$base/no-video", emptyMap(), "sheet-${System.nanoTime()}", 120_000,
                false, false, allowExtraction = false, preview = StreamPreviewMetadata(
                    kind = StreamPreviewKind.WEBVTT, manifestUrl = "$base/story.vtt", mediaVersion = "fixture-v1", durationMs = 120_000))
            SeekPreviewFrameProvider(context, OkHttpClient(), 192).use { provider ->
                provider.configure(source)
                val prepareStart = SystemClock.elapsedRealtime()
                provider.warmPlayback(20_000, 10_000)
                report.put("prepareFiveMs", SystemClock.elapsedRealtime() - prepareStart)
                val lookupStart = System.nanoTime()
                val frames = provider.cachedWindowAt(20_000)
                report.put("preparedWindowLookupMs", (System.nanoTime() - lookupStart) / 1_000_000.0)
                assertEquals(5, frames.size)
                assertTrue(frames.all { it.validity == SeekPreviewValidity.CUE })
                assertEquals(1, a.get())
                assertEquals(0, b.get())
                assertNotNull(provider.frameAt(60_000))
                val revisitStart = SystemClock.elapsedRealtime()
                val revisit = provider.frameAt(100_000)
                report.put("previousSheetNewCueMs", SystemClock.elapsedRealtime() - revisitStart)
                assertNotNull(revisit)
                assertTrue(provider.matchesTarget(revisit, 100_000))
                assertEquals(Color.RED, revisit!!.bitmap.getPixel(0, 0))
                assertEquals("Crossing sheets must not redownload the first sheet", 1, a.get())
                assertEquals(1, b.get())
                assertEquals(1, manifests.get())
                assertEquals("No video requests or decoder fallback", 0, unexpected.get())
                report.put("frames", frames.size).put("manifestRequests", manifests.get())
                    .put("sheetRequests", a.get() + b.get()).put("videoRequests", unexpected.get())
                Unit
            }
        } finally {
            server.stop()
            File(context.getExternalFilesDir(null), "thumbnail-sheet-report.json").writeText(report.toString(2))
        }
    }
}
