package com.arflix.tv.ui.screens.player.preview

import android.graphics.Bitmap
import androidx.concurrent.futures.CallbackToFutureAdapter
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.analytics.AnalyticsListener
import com.arflix.tv.player.previewmedia3.FrameExtractor
import com.arflix.tv.player.previewmedia3.FrameExtractorInternal
import java.util.concurrent.ExecutionException
import java.util.concurrent.atomic.AtomicReference
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4


@RunWith(AndroidJUnit4::class)

class SeekPreviewExtractorCallbackTest {
    @Test fun readyWithoutAFrameFailsTheFutureInsteadOfCrashingTheMainLooper() {
        val internal = FrameExtractorInternal.getInstance()
        val rendering = internal.javaClass.getDeclaredField("extractedFrameNeedsRendering").apply { isAccessible = true }
        (rendering.get(internal) as java.util.concurrent.atomic.AtomicBoolean).set(false)
        val activeField = internal.javaClass.getDeclaredField("activeTaskCompleter").apply { isAccessible = true }
        @Suppress("UNCHECKED_CAST")
        val active = activeField.get(internal) as AtomicReference<CallbackToFutureAdapter.Completer<FrameExtractor.Frame>>
        val frameField = internal.javaClass.getDeclaredField("lastSeekDedupeFrame").apply { isAccessible = true }
        frameField.set(internal, null)
        val future = CallbackToFutureAdapter.getFuture<FrameExtractor.Frame> { active.set(it); "unsupported-video-test" }
        val type = Class.forName("com.arflix.tv.player.previewmedia3.FrameExtractorInternal\$PlayerListener")
        val listener = type.getDeclaredConstructor(FrameExtractorInternal::class.java).apply { isAccessible = true }.newInstance(internal)
        val ready = type.getDeclaredMethod("onPlaybackStateChanged", AnalyticsListener.EventTime::class.java, Int::class.javaPrimitiveType).apply { isAccessible = true }
        ready.invoke(listener, null, Player.STATE_READY)
        try { future.get(2, java.util.concurrent.TimeUnit.SECONDS); fail("Missing video must fail the preview") }
        catch (e: ExecutionException) {
            assertEquals(PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED, (e.cause as PlaybackException).errorCode)
        }
        // Duplicate state and error callbacks are legal after completion/cancellation.
        ready.invoke(listener, null, Player.STATE_READY)
        type.getDeclaredMethod("onPlayerError", AnalyticsListener.EventTime::class.java, PlaybackException::class.java)
            .apply { isAccessible = true }.invoke(listener, null, PlaybackException("late", null, PlaybackException.ERROR_CODE_UNSPECIFIED))
        assertNull(active.get())

        val bitmap = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888)
        val frame = FrameExtractor.Frame::class.java.getDeclaredConstructor(Long::class.javaPrimitiveType, Bitmap::class.java)
            .apply { isAccessible = true }.newInstance(10_000L, bitmap)
        frameField.set(internal, frame)
        val valid = CallbackToFutureAdapter.getFuture<FrameExtractor.Frame> { active.set(it); "deduplicated-video-test" }
        ready.invoke(listener, null, Player.STATE_READY)
        assertSame(frame, valid.get(2, java.util.concurrent.TimeUnit.SECONDS))
        frameField.set(internal, null)
        bitmap.recycle()
    }
}
