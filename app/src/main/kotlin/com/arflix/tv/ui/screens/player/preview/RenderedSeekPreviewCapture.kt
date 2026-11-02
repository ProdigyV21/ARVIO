package com.arflix.tv.ui.screens.player.preview

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.Surface
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine

// Some TV GPUs take over half a second to copy/tone-map a 4K hardware surface.
internal const val RENDERED_PREVIEW_COPY_TIMEOUT_MS = 1_500L

/** The asynchronous copy may finish on a later frame, but must remain in the same short
 * continuous playback interval. Source/seek generation is checked separately by the caller. */
internal fun renderedPreviewCopyIsCurrent(beforePtsMs: Long, afterPtsMs: Long, playerPositionMs: Long): Boolean =
    beforePtsMs >= 0 && afterPtsMs >= beforePtsMs &&
        afterPtsMs - beforePtsMs <= RENDERED_PREVIEW_COPY_TIMEOUT_MS &&
        kotlin.math.abs(playerPositionMs - afterPtsMs) <= 500L

/** One small copy of an already-rendered video frame; no decoder, network or playback seek. */
internal suspend fun captureRenderedSeekPreview(surface: Surface, width: Int, height: Int): Bitmap? =
    suspendCancellableCoroutine { continuation ->
        val bitmap = try { Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888) }
        catch (_: OutOfMemoryError) { continuation.resume(null); return@suspendCancellableCoroutine }
        try {
            PixelCopy.request(surface, bitmap, { result ->
                if (com.arflix.tv.BuildConfig.DEBUG) android.util.Log.d("SeekPreviewCapture", "pixelCopyResult=$result active=${continuation.isActive}")
                if (result == PixelCopy.SUCCESS && continuation.isActive) {
                    continuation.resume(bitmap, onCancellation = { _, value, _ -> value?.recycle() })
                } else {
                    bitmap.recycle()
                    if (continuation.isActive) continuation.resume(null)
                }
            }, Handler(Looper.getMainLooper()))
        } catch (_: IllegalArgumentException) {
            bitmap.recycle()
            if (continuation.isActive) continuation.resume(null)
        }
    }
