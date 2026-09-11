package com.arflix.tv.ui.screens.player.preview

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapRegionDecoder
import android.graphics.Rect
import com.arflix.tv.data.model.StreamPreviewKind
import com.arflix.tv.data.model.StreamPreviewMetadata
import com.arflix.tv.data.model.StreamSource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import java.io.Closeable
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.roundToInt

data class NativePreviewFrame(
    val bitmap: Bitmap,
    val requestedPositionMs: Long,
    val cueStartMs: Long,
    val cueEndMs: Long,
    val cacheIdentity: String,
    val actualPositionMs: Long? = null
)

/**
 * Lazy source-image adapter. No I/O in the constructor; never touches playback or generation APIs.
 * The caller owns successful bitmaps, cancellation, result generation checks, and the frame cache.
 * Cue ends are exclusive and all returned timestamps are on the player's timeline.
 */
class NativeSeekPreviewAdapter(
    private val metadata: StreamPreviewMetadata,
    playbackClient: OkHttpClient,
    headers: Map<String, String> = emptyMap(),
    maxWidthPx: Int = 480
) : Closeable {
    constructor(source: StreamSource, playbackClient: OkHttpClient, maxWidthPx: Int = 480) :
        this(requireNotNull(source.preview), playbackClient, source.behaviorHints?.proxyHeaders?.request.orEmpty(), maxWidthPx)

    val cacheIdentity: String = nativePreviewCacheIdentity(metadata)
    private val maxWidth = maxWidthPx.coerceIn(64, 640)
    private val mutex = Mutex()
    private val closed = AtomicBoolean(false)
    private val origin = (metadata.serverUrl ?: metadata.manifestUrl)?.toHttpUrlOrNull()
    private val http = origin?.let {
        // Generic cross-origin tracks must carry their own explicit headers. Playback headers may
        // only be inherited when the descriptor declares their server/authentication origin.
        PreviewHttpClient(playbackClient, it,
            (if (metadata.serverUrl != null) headers else emptyMap()) + metadata.headers)
    }
    private var discovered = false
    private var storyboard: PreviewStoryboard? = null
    private var failures = 0
    private var bif: PreviewHttpBytes? = null
    private var imageUrl: String? = null
    private var imageBytes: ByteArray? = null

    suspend fun load(positionMs: Long): NativePreviewFrame? = withContext(Dispatchers.IO) {
        if (closed.get() || http == null || positionMs < 0 || !mutex.tryLock()) return@withContext null
        var ownedBitmap: Bitmap? = null
        try {
            if (failures >= 2 || metadata.mediaVersion.isBlank()) return@withContext null
            val originalPosition = try { Math.addExact(positionMs, metadata.timelineOffsetMs) }
                catch (_: ArithmeticException) { return@withContext null }
            if (originalPosition !in 0 until PREVIEW_MAX_TIME_MS) return@withContext null
            val frame = withTimeout(6_000L) {
                if (!discovered) {
                    storyboard = discover()
                    discovered = true
                }
                val cue = storyboard?.cueAt(originalPosition) ?: return@withTimeout null
                val bytes = image(cue) ?: return@withTimeout null
                currentCoroutineContext().ensureActive()
                val bitmap = decode(bytes, cue.crop) ?: return@withTimeout null
                ownedBitmap = bitmap
                currentCoroutineContext().ensureActive()
                if (closed.get()) return@withTimeout null
                val start = Math.subtractExact(cue.startMs, metadata.timelineOffsetMs)
                val end = Math.subtractExact(cue.endMs, metadata.timelineOffsetMs)
                NativePreviewFrame(bitmap, positionMs, start.coerceAtLeast(0), end, cacheIdentity)
            }
            if (frame != null) {
                failures = 0
                ownedBitmap = null
            }
            frame
        } catch (_: TimeoutCancellationException) {
            currentCoroutineContext().ensureActive()
            failures++
            null
        } catch (cancelled: CancellationException) {
            throw cancelled
        } catch (_: Exception) {
            failures++
            null
        } finally {
            ownedBitmap?.recycle()
            if (closed.get()) clear()
            mutex.unlock()
        }
    }

    private suspend fun discover(): PreviewStoryboard? {
        val client = http ?: return null
        return when (metadata.kind) {
            StreamPreviewKind.JELLYFIN -> {
                val url = itemUrl() ?: return null
                val response = client.get(url, PREVIEW_MAX_MANIFEST_BYTES, accept = "application/json")
                HomeServerPreviewParser.jellyfin(response.bytes.toString(Charsets.UTF_8), metadata, maxWidth)
            }
            StreamPreviewKind.PLEX -> {
                val url = serverPreviewUrl(metadata, "library", "metadata", metadata.itemId)
                    ?.newBuilder()?.addQueryParameter("includeMedia", "1")?.build()?.toString() ?: return null
                val response = client.get(url, PREVIEW_MAX_MANIFEST_BYTES, accept = "application/json")
                val indexUrl = HomeServerPreviewParser.plexBifUrl(response.bytes.toString(Charsets.UTF_8), metadata) ?: return null
                discoverBif(indexUrl)
            }
            StreamPreviewKind.EMBY -> {
                val response = client.get(itemUrl() ?: return null, PREVIEW_MAX_MANIFEST_BYTES, accept = "application/json")
                if (!HomeServerPreviewParser.embySingleVersion(response.bytes.toString(Charsets.UTF_8), metadata)) return null
                val url = serverPreviewUrl(metadata, "Videos", metadata.itemId, "index.bif")
                    ?.newBuilder()?.addQueryParameter("Width", maxWidth.toString())?.build()?.toString() ?: return null
                discoverBif(url)
            }
            StreamPreviewKind.BIF -> discoverBif(metadata.manifestUrl ?: return null)
            StreamPreviewKind.WEBVTT -> {
                val response = client.get(metadata.manifestUrl ?: return null, PREVIEW_MAX_MANIFEST_BYTES)
                WebVttPreviewParser.parse(response.bytes.toString(Charsets.UTF_8), response.url)
            }
            StreamPreviewKind.IMAGE_HLS -> {
                var response = client.get(metadata.manifestUrl ?: return null, PREVIEW_MAX_MANIFEST_BYTES)
                ImageHlsPreviewParser.childPlaylist(response.bytes.toString(Charsets.UTF_8), response.url)?.let { child ->
                    response = client.get(child, PREVIEW_MAX_MANIFEST_BYTES)
                }
                ImageHlsPreviewParser.parse(response.bytes.toString(Charsets.UTF_8), response.url)
            }
        }
    }

    private fun itemUrl(): String? {
        if (metadata.userId.isBlank()) return null
        return serverPreviewUrl(metadata, "Users", metadata.userId, "Items", metadata.itemId)?.toString()
    }

    private suspend fun discoverBif(url: String): PreviewStoryboard? {
        val client = http ?: return null
        val header = client.get(url, PREVIEW_MAX_IMAGE_BYTES, PreviewByteRange(0, 64))
        val size = BifPreviewParser.indexSize(header.bytes) ?: return null
        val index = if (!header.partial) header else client.get(header.url, PREVIEW_MAX_MANIFEST_BYTES,
            PreviewByteRange(0, size, header.totalBytes, header.validator))
        val parsed = BifPreviewParser.parse(index.bytes, index.url, index.totalBytes, metadata.durationMs) ?: return null
        bif = index
        return parsed
    }

    private suspend fun image(cue: PreviewCue): ByteArray? {
        val client = http ?: return null
        val offset = cue.byteOffset
        if (offset != null) {
            val index = bif ?: return null
            val length = cue.byteLength ?: return null
            if (!index.partial) {
                if (offset + length > index.bytes.size) return null
                return index.bytes.copyOfRange(offset.toInt(), offset.toInt() + length)
            }
            return client.get(cue.url, PREVIEW_MAX_IMAGE_BYTES,
                PreviewByteRange(offset, length, index.totalBytes, index.validator)).bytes
        }
        if (cue.url == imageUrl) return imageBytes
        val fetched = client.get(cue.url, PREVIEW_MAX_IMAGE_BYTES).bytes
        imageUrl = cue.url
        imageBytes = fetched
        return fetched
    }

    @Suppress("DEPRECATION")
    private fun decode(bytes: ByteArray, crop: PreviewCrop?): Bitmap? {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        BitmapFactory.decodeByteArray(bytes, 0, bytes.size, bounds)
        if (bounds.outWidth !in 1..32_768 || bounds.outHeight !in 1..32_768 ||
            bounds.outWidth.toLong() * bounds.outHeight > 64_000_000 ||
            bounds.outMimeType !in setOf("image/jpeg", "image/png", "image/webp")) return null
        if (crop != null && !crop.fits(bounds.outWidth, bounds.outHeight)) return null
        val width = crop?.width ?: bounds.outWidth
        val height = crop?.height ?: bounds.outHeight
        val options = BitmapFactory.Options().apply {
            inPreferredConfig = Bitmap.Config.ARGB_8888
            inSampleSize = 1
            while (width / inSampleSize > maxWidth * 2 || height / inSampleSize > maxWidth * 2) inSampleSize *= 2
        }
        val bitmap = if (crop == null) BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options) else {
            val decoder = BitmapRegionDecoder.newInstance(bytes, 0, bytes.size, false) ?: return null
            try { decoder.decodeRegion(Rect(crop.x, crop.y, crop.x + crop.width, crop.y + crop.height), options) }
            finally { decoder.recycle() }
        } ?: return null
        val scale = minOf(1.0, maxWidth.toDouble() / bitmap.width, maxWidth.toDouble() / bitmap.height)
        if (scale >= 1) return bitmap
        return try {
            Bitmap.createScaledBitmap(bitmap, (bitmap.width * scale).roundToInt().coerceAtLeast(1),
                (bitmap.height * scale).roundToInt().coerceAtLeast(1), true)
        } finally { bitmap.recycle() }
    }

    override fun close() {
        closed.set(true)
        http?.close()
        if (mutex.tryLock()) {
            try { clear() } finally { mutex.unlock() }
        }
    }

    private fun clear() {
        storyboard = null
        imageBytes = null
        imageUrl = null
        bif = null
    }
}
