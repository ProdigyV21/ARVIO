package com.arflix.tv.ui.screens.player.preview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.util.LruCache
import androidx.media3.common.MediaItem
import androidx.media3.effect.Presentation
import androidx.media3.exoplayer.SeekParameters
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector
import androidx.media3.inspector.FrameExtractor
import com.arflix.tv.data.model.StreamPreviewMetadata
import com.google.common.util.concurrent.ListenableFuture
import fi.iki.elonen.NanoHTTPD
import java.io.Closeable
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.EOFException
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.security.MessageDigest
import java.util.LinkedHashMap
import java.util.UUID
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.math.roundToInt
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

private const val PREVIEW_INTERVAL_MS = 10_000L
private const val FRAME_TOLERANCE_MS = 5_000L
internal const val SEEK_PREVIEW_CACHE_EPOCH = 5
private const val DISK_CACHE_LIMIT_BYTES = 128L * 1024L * 1024L
private const val MAX_CACHE_ENTRY_BYTES = 2L * 1024L * 1024L
internal const val RANGE_CHUNK_BYTES = 512 * 1024
private const val RANGE_MEMORY_LIMIT_BYTES = 4 * 1024 * 1024
private const val MAX_EXTRACTION_NETWORK_BYTES = 16L * 1024L * 1024L
internal const val SEEK_PREVIEW_REQUEST_TIMEOUT_MS = 10_000L
private const val MEDIA3_FRAME_TIMEOUT_MS = 6_000L

data class SeekPreviewSource(
    val url: String,
    val headers: Map<String, String>,
    val cacheIdentity: String,
    val durationMs: Long,
    val isLive: Boolean,
    val isAdaptive: Boolean,
    val allowExtraction: Boolean = true,
    val maxWidthPx: Int = 480,
    val preview: StreamPreviewMetadata? = null,
)

enum class SeekPreviewCapability { AVAILABLE, UNAVAILABLE }
enum class SeekPreviewState { IDLE, LOADING, READY, UNAVAILABLE }
enum class SeekPreviewOrigin { PROVIDER, DECODER, RENDERED, MEMORY, DISK }
enum class SeekPreviewValidity { TIMESTAMP, CUE, UNVERIFIED }

data class SeekPreviewStatus(
    val sourceGeneration: Long = 0,
    val capability: SeekPreviewCapability = SeekPreviewCapability.UNAVAILABLE,
    val state: SeekPreviewState = SeekPreviewState.UNAVAILABLE,
    val reason: String? = "No source",
    val requestId: Long = 0,
) {
    val isAvailable: Boolean get() = capability == SeekPreviewCapability.AVAILABLE
}

data class SeekPreviewFrame(
    val bitmap: Bitmap,
    /** Actual presentation timestamp, or the start of the image cue. Never a request bucket. */
    val positionMs: Long,
    val requestedPositionMs: Long = positionMs,
    val actualPositionMs: Long? = positionMs,
    val cueStartMs: Long? = null,
    val cueEndMs: Long? = null,
    val sourceGeneration: Long = 0,
    val requestId: Long = 0,
    val sourceIdentity: String = "",
    val origin: SeekPreviewOrigin = SeekPreviewOrigin.DECODER,
    val validity: SeekPreviewValidity = SeekPreviewValidity.TIMESTAMP,
) {
    fun isValidFor(positionMs: Long, generation: Long = sourceGeneration): Boolean =
        generation == sourceGeneration && seekPreviewTimeMatches(
            positionMs, actualPositionMs, cueStartMs, cueEndMs, validity,
        )
}

internal fun seekPreviewTimeMatches(
    requestedMs: Long,
    actualMs: Long?,
    cueStartMs: Long?,
    cueEndMs: Long?,
    validity: SeekPreviewValidity,
): Boolean = when (validity) {
    SeekPreviewValidity.CUE -> cueStartMs != null && cueEndMs != null &&
        cueStartMs >= 0 && cueEndMs > cueStartMs && requestedMs >= cueStartMs && requestedMs < cueEndMs &&
        (actualMs == null || (actualMs >= cueStartMs && actualMs < cueEndMs))
    SeekPreviewValidity.TIMESTAMP -> actualMs != null && actualMs >= 0 && requestedMs >= 0 &&
        maxOf(actualMs, requestedMs) - minOf(actualMs, requestedMs) <= FRAME_TOLERANCE_MS
    SeekPreviewValidity.UNVERIFIED -> false
}

internal fun quantizeSeekPreviewPosition(positionMs: Long, durationMs: Long): Long {
    if (durationMs <= 0L) return 0L
    if (positionMs >= durationMs) return durationMs
    val clamped = positionMs.coerceIn(0L, durationMs)
    val base = (clamped / PREVIEW_INTERVAL_MS) * PREVIEW_INTERVAL_MS
    val rounded = if (clamped - base >= PREVIEW_INTERVAL_MS / 2L && base <= Long.MAX_VALUE - PREVIEW_INTERVAL_MS) {
        base + PREVIEW_INTERVAL_MS
    } else base
    return rounded.coerceAtMost(durationMs)
}

internal fun quantizeSeekPreviewPositionWithHysteresis(
    currentBucket: Long,
    positionMs: Long,
    durationMs: Long,
    hysteresisMs: Long = 2_500L,
): Long {
    if (durationMs <= 0L) return 0L
    if (currentBucket < 0L) return quantizeSeekPreviewPosition(positionMs, durationMs)
    val clamped = positionMs.coerceIn(0L, durationMs)
    if (clamped >= durationMs) return durationMs
    val threshold = (PREVIEW_INTERVAL_MS / 2L) + hysteresisMs
    if (kotlin.math.abs(clamped - currentBucket) <= threshold) {
        return currentBucket.coerceAtMost(durationMs)
    }
    return quantizeSeekPreviewPosition(clamped, durationMs)
}


internal fun acceleratedSeekPreviewStepMs(repeatCount: Int): Long = when {
    repeatCount >= 18 -> 60_000L
    repeatCount >= 8 -> 30_000L
    else -> 10_000L
}

internal fun fitSeekPreviewDimensions(sourceWidth: Int, sourceHeight: Int, maxWidth: Int, maxHeight: Int): Pair<Int, Int> {
    if (sourceWidth <= 0 || sourceHeight <= 0 || maxWidth <= 0 || maxHeight <= 0) {
        return maxWidth.coerceAtLeast(1) to maxHeight.coerceAtLeast(1)
    }
    return fitSeekPreviewAspectRatio(sourceWidth.toFloat() / sourceHeight, maxWidth, maxHeight)
}

internal fun fitSeekPreviewAspectRatio(aspectRatio: Float, maxWidth: Int, maxHeight: Int): Pair<Int, Int> {
    if (!aspectRatio.isFinite() || aspectRatio <= 0f || maxWidth <= 0 || maxHeight <= 0) {
        return maxWidth.coerceAtLeast(1) to maxHeight.coerceAtLeast(1)
    }
    val widthAtMaxHeight = (maxHeight * aspectRatio).roundToInt().coerceAtLeast(1)
    return if (widthAtMaxHeight <= maxWidth) widthAtMaxHeight to maxHeight
    else maxWidth to (maxWidth / aspectRatio).roundToInt().coerceAtLeast(1)
}

internal fun seekPreviewDisplayAspectRatio(
    videoWidth: Int, videoHeight: Int, pixelWidthHeightRatio: Float, unappliedRotationDegrees: Int,
    fallbackWidth: Int, fallbackHeight: Int,
): Float {
    val fallback = if (fallbackWidth > 0 && fallbackHeight > 0) fallbackWidth.toFloat() / fallbackHeight else 16f / 9f
    if (videoWidth <= 0 || videoHeight <= 0) return fallback
    val pixelRatio = pixelWidthHeightRatio.takeIf { it.isFinite() && it > 0f } ?: 1f
    val encodedRatio = videoWidth.toFloat() * pixelRatio / videoHeight
    val rotation = ((unappliedRotationDegrees % 360) + 360) % 360
    return (if (rotation == 90 || rotation == 270) 1f / encodedRatio else encodedRatio)
        .takeIf { it.isFinite() && it > 0f } ?: fallback
}

/** The production image adapter and tests supply identical timestamp-bearing results. */
internal interface SeekPreviewImageLoader : Closeable {
    suspend fun load(positionMs: Long): SeekPreviewImage?
    override fun close() {}
}

internal data class SeekPreviewImage(
    val bitmap: Bitmap,
    val actualPositionMs: Long? = null,
    val cueStartMs: Long? = null,
    val cueEndMs: Long? = null,
)

/** Provider images first; bounded local Media3 extraction only when playback allows it. */
class SeekPreviewFrameProvider internal constructor(
    context: Context,
    playbackClient: OkHttpClient,
    memoryClassMb: Int,
    private val imageLoaderFactory: ((SeekPreviewSource) -> SeekPreviewImageLoader?)?,
    private val decoderOverride: (suspend (SeekPreviewSource, Long) -> SeekPreviewImage)?,
    cacheLimitBytes: Long,
) : Closeable {
    constructor(context: Context, playbackClient: OkHttpClient, memoryClassMb: Int) :
        this(context, playbackClient, memoryClassMb, null, null, DISK_CACHE_LIMIT_BYTES)

    private val appContext = context.applicationContext
    private val previewClient = playbackClient.newBuilder()
        .connectTimeout(2, TimeUnit.SECONDS).readTimeout(2, TimeUnit.SECONDS)
        .callTimeout(4, TimeUnit.SECONDS).build()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val scheduler = SeekPreviewExtractionScheduler<SeekPreviewFrame?>(scope)
    private val lock = Any()
    private val requests = AtomicLong()
    private val memoryCache = object : LruCache<String, CachedPreview>(
        if (memoryClassMb <= 256) 6 * 1024 * 1024 else 16 * 1024 * 1024,
    ) {
        override fun sizeOf(key: String, value: CachedPreview): Int = value.bitmap.allocationByteCount
    }
    private val disk = SeekPreviewDiskCache(File(appContext.cacheDir, "seek_previews/v5"), cacheLimitBytes)
    private val diskWriter = SeekPreviewCacheWriter<Pair<String, CachedPreview>>(scope) { (key, entry) ->
        disk.write(key, entry)
    }
    private val mutableStatus = MutableStateFlow(SeekPreviewStatus())
    val status: StateFlow<SeekPreviewStatus> = mutableStatus.asStateFlow()
    val sourceGeneration: Long get() = status.value.sourceGeneration
    @Volatile private var active: ActiveSource? = null
    @Volatile private var closed = false
    private var generation = 0L
    private val decoderHandler = Handler(Looper.getMainLooper())
    private val decoderSession = SeekPreviewDecoderSession<FrameExtractor.Frame>(
        CoroutineScope(scope.coroutineContext + Dispatchers.Main.immediate),
        Executor { command -> decoderHandler.post(command) },
    )

    init {
        scope.launch {
            // Retire bitmap-only epochs without ever treating their requested buckets as PTS.
            File(appContext.cacheDir, "seek_previews").listFiles { file ->
                file.isFile && file.extension in setOf("jpg", "tmp")
            }?.forEach { it.delete() }
        }
    }

    private class ActiveSource(
        val source: SeekPreviewSource,
        val identity: String,
        val generation: Long,
        val images: SeekPreviewImageLoader?,
    ) {
        var failures = 0
        @Volatile var decoderDisabled = false
        @Volatile var unavailable = false
        @Volatile var memoryDisabled = false
        var foregroundRequest = 0L
        var proxy: SeekPreviewRangeProxy? = null
        fun close() {
            images?.close()
            proxy?.close()
            proxy = null
        }
    }

    suspend fun configure(source: SeekPreviewSource?) {
        val usable = source?.takeIf {
            !it.isLive && it.durationMs > 0 && it.url.isNotBlank() &&
                Uri.parse(it.url).scheme?.lowercase() in setOf("http", "https", "file", "content")
        }?.let { it.copy(headers = it.headers.toMap(), maxWidthPx = if (it.maxWidthPx >= 640) 640 else 480) }
        val identity = usable?.let(::sourceIdentity)
        val old: ActiveSource?
        synchronized(lock) {
            if (closed) return
            if (active?.source == usable && active?.identity == identity) return
            old = active
            scheduler.cancel()
            generation++
            active = usable?.let {
                val images = imageLoaderFactory?.invoke(it) ?: it.preview?.let { metadata ->
                    val adapter = NativeSeekPreviewAdapter(metadata, previewClient, it.headers, it.maxWidthPx)
                    object : SeekPreviewImageLoader {
                        override suspend fun load(positionMs: Long): SeekPreviewImage? = adapter.load(positionMs)?.let { frame ->
                            SeekPreviewImage(frame.bitmap, frame.actualPositionMs, frame.cueStartMs, frame.cueEndMs)
                        }
                        override fun close() = adapter.close()
                    }
                }
                ActiveSource(it, checkNotNull(identity), generation, images).apply {
                    unavailable = images == null && (!it.allowExtraction || it.isAdaptive)
                }
            }
            val available = active?.unavailable == false
            mutableStatus.value = SeekPreviewStatus(
                generation,
                if (available) SeekPreviewCapability.AVAILABLE else SeekPreviewCapability.UNAVAILABLE,
                if (available) SeekPreviewState.IDLE else SeekPreviewState.UNAVAILABLE,
                if (available) null else "No supported preview path",
            )
        }
        old?.close()
    }

    fun matchesTarget(frame: SeekPreviewFrame?, positionMs: Long): Boolean {
        val source = active ?: return false
        return !closed && frame != null && frame.sourceIdentity == source.identity &&
            frame.isValidFor(clamp(positionMs, source), source.generation)
    }

    fun memoryFrameAt(positionMs: Long): SeekPreviewFrame? {
        val source = active ?: return null
        if (source.memoryDisabled) return null
        val target = clamp(positionMs, source)
        val cached = memoryCache.get(frameKey(source, target)) ?: return null
        return deliver(source, cached, target, 0, SeekPreviewOrigin.MEMORY)
    }

    suspend fun cachedFrameAt(positionMs: Long): SeekPreviewFrame? {
        val source = active ?: return null
        return cached(source, clamp(positionMs, source), 0)
    }

    suspend fun frameAt(positionMs: Long, cacheOnly: Boolean = false): SeekPreviewFrame? =
        requestFrame(positionMs, cacheOnly, background = false)

    /** Only call when playback is stable. Foreground work preempts warming, never the reverse. */
    suspend fun warmAround(positionMs: Long, direction: Int = 1) {
        val source = active ?: return
        val foreground = synchronized(lock) { source.foregroundRequest }
        val step = if (direction < 0) -PREVIEW_INTERVAL_MS else PREVIEW_INTERVAL_MS
        val target = clamp(positionMs, source)
        val neighbors = if (source.images != null) listOf(step, -step) else listOf(step)
        for (delta in neighbors) {
            if (active !== source || source.unavailable || synchronized(lock) { source.foregroundRequest != foreground }) return
            val neighbor = if (delta > 0 && target > Long.MAX_VALUE - delta) source.source.durationMs else target + delta
            requestFrame(clamp(neighbor, source), cacheOnly = false, background = true)
        }
    }

    /** Cancels outstanding target/network work without disposing the configured source. */
    fun cancelPending() {
        synchronized(lock) {
            scheduler.cancel()
            active?.let { source ->
                source.foregroundRequest = requests.incrementAndGet()
                if (!source.unavailable) updateStatus(source, SeekPreviewState.IDLE)
            }
        }
        scope.launch(Dispatchers.Main.immediate) { decoderSession.release() }
    }

    private suspend fun requestFrame(positionMs: Long, cacheOnly: Boolean, background: Boolean): SeekPreviewFrame? {
        val source = active ?: return null
        val target = clamp(positionMs, source)
        val requestId = requests.incrementAndGet()
        if (!background) synchronized(lock) {
            if (active !== source || closed) return null
            source.foregroundRequest = requestId
            scheduler.cancel()
            if (!source.unavailable) updateStatus(source, SeekPreviewState.IDLE, requestId = requestId)
        }
        cached(source, target, requestId)?.let {
            if (!background && synchronized(lock) { source.foregroundRequest != requestId }) return null
            if (!background) updateStatus(source, SeekPreviewState.READY, requestId = requestId)
            return it
        }
        if (cacheOnly || source.unavailable || active !== source || closed) return null
        return scheduler.submit(background) {
            if (active !== source || (!background && source.foregroundRequest != requestId)) return@submit null
            val startedAt = SystemClock.elapsedRealtime()
            if (!background) updateStatus(source, SeekPreviewState.LOADING, requestId = requestId)
            try {
                val result = withTimeout(SEEK_PREVIEW_REQUEST_TIMEOUT_MS) {
                    var image = source.images?.load(target)
                    var origin = SeekPreviewOrigin.PROVIDER
                    // Metadata alone does not guarantee images. Background image warming must
                    // never escalate into a decoder while constrained/heavy playback is active.
                    if (image == null && background && source.images != null) return@withTimeout null
                    if (image == null && source.source.allowExtraction && !source.source.isAdaptive && !source.decoderDisabled) {
                        origin = SeekPreviewOrigin.DECODER
                        image = decoderOverride?.invoke(source.source, decodePosition(source, target))
                            ?: extractWithMedia3(source, target)
                    }
                    currentCoroutineContext().ensureActive()
                    if (image == null && source.images != null) {
                        if (!background) updateStatus(source, SeekPreviewState.UNAVAILABLE, "No image for target", requestId)
                        return@withTimeout null
                    }
                    if (image == null) throw UnsupportedPreviewException("No preview for target")
                    val validity = if (origin == SeekPreviewOrigin.PROVIDER) SeekPreviewValidity.CUE else SeekPreviewValidity.TIMESTAMP
                    val metadata = PreviewMetadata(
                        source.identity, target, image.actualPositionMs, image.cueStartMs, image.cueEndMs, origin, validity,
                    )
                    if (!metadata.matches(target)) throw IOException("Frame outside target window")
                    val cached = CachedPreview(normalizeFrame(image.bitmap, source.source.maxWidthPx), metadata)
                    currentCoroutineContext().ensureActive()
                    if (active !== source) return@withTimeout null
                    val frame = deliver(source, cached, target, requestId, origin) ?: return@withTimeout null
                    val key = frameKey(source, target)
                    memoryCache.put(key, cached)
                    // Display is not held behind JPEG compression or disk pruning.
                    diskWriter.offer(key to cached)
                    frame
                }
                if (result != null) {
                    source.failures = 0
                    if (!background) updateStatus(source, SeekPreviewState.READY, requestId = requestId)
                    Log.i("SeekPreview", "origin=${result.origin} targetMs=$target actualMs=${result.actualPositionMs} cue=${result.cueStartMs}..${result.cueEndMs} elapsedMs=${SystemClock.elapsedRealtime() - startedAt} status=READY generation=${source.generation}")
                }
                result
            } catch (timeout: TimeoutCancellationException) {
                fail(source, "Preview deadline exceeded", permanent = false, requestId = requestId, background = background)
                logFailure(source, target, startedAt, "deadline")
                null
            } catch (cancelled: CancellationException) {
                throw cancelled
            } catch (_: OutOfMemoryError) {
                disableForMemoryPressure(source)
                logFailure(source, target, startedAt, "memory")
                null
            } catch (failure: Exception) {
                currentCoroutineContext().ensureActive()
                val reason = if (failure is UnsupportedPreviewException) "Unsupported preview path" else "Preview extraction failed"
                fail(source, reason, failure is UnsupportedPreviewException, requestId, background)
                logFailure(source, target, startedAt, if (failure is UnsupportedPreviewException) "unsupported" else "extraction")
                null
            } finally {
                if (!background && status.value.state == SeekPreviewState.LOADING) {
                    updateStatus(source, SeekPreviewState.IDLE, requestId = requestId)
                }
            }
        }
    }

    private fun logFailure(source: ActiveSource, target: Long, startedAt: Long, reason: String) {
        // Never print exception messages: network/decoder exceptions can embed signed URLs.
        if (source.failures <= 3) {
            Log.w("SeekPreview", "targetMs=$target elapsedMs=${SystemClock.elapsedRealtime() - startedAt} status=UNAVAILABLE reason=$reason generation=${source.generation}")
        }
    }

    private fun disableForMemoryPressure(source: ActiveSource) {
        synchronized(lock) {
            memoryCache.evictAll()
            source.proxy?.close()
            source.proxy = null
            source.images?.close()
            if (active !== source || closed) return
            source.failures++
            source.decoderDisabled = true
            source.memoryDisabled = true
            source.unavailable = true
            updateStatus(source, SeekPreviewState.UNAVAILABLE, "Insufficient preview memory")
        }
        scope.launch(Dispatchers.Main.immediate) {
            if (active !== source) return@launch
            decoderSession.release()
        }
    }

    private fun fail(source: ActiveSource, reason: String, permanent: Boolean, requestId: Long, background: Boolean) {
        synchronized(lock) {
            if (active !== source) return
            source.failures++
            source.unavailable = true
            if (!background || source.unavailable) updateStatus(source, SeekPreviewState.UNAVAILABLE, reason, if (background) 0 else requestId)
            if (!permanent) {
                val failureCount = source.failures
                scope.launch {
                    delay(if (failureCount == 1) 5_000L else 30_000L)
                    synchronized(lock) {
                        if (!closed && active === source && source.failures == failureCount) {
                            source.unavailable = false
                            updateStatus(source, SeekPreviewState.IDLE)
                        }
                    }
                }
            }
        }
    }

    private fun updateStatus(source: ActiveSource, state: SeekPreviewState, reason: String? = null, requestId: Long = 0) {
        synchronized(lock) {
            if (closed || active !== source || (requestId != 0L && source.foregroundRequest != requestId)) return
            mutableStatus.value = SeekPreviewStatus(
                source.generation,
                if (source.unavailable) SeekPreviewCapability.UNAVAILABLE else SeekPreviewCapability.AVAILABLE,
                state, reason, requestId,
            )
        }
    }

    private suspend fun cached(source: ActiveSource, target: Long, requestId: Long): SeekPreviewFrame? = withContext(Dispatchers.IO) {
        if (closed || active !== source || source.memoryDisabled) return@withContext null
        val key = frameKey(source, target)
        memoryCache.get(key)?.let { entry -> deliver(source, entry, target, requestId, SeekPreviewOrigin.MEMORY)?.let { return@withContext it } }
        val entry = try {
            disk.read(key, source.identity)
        } catch (_: OutOfMemoryError) {
            disableForMemoryPressure(source)
            Log.i("SeekPreview", "origin=DISK targetMs=$target status=UNAVAILABLE reason=memory generation=${source.generation}")
            return@withContext null
        } ?: return@withContext null
        currentCoroutineContext().ensureActive()
        deliver(source, entry, target, requestId, SeekPreviewOrigin.DISK)?.also { memoryCache.put(key, entry) }
    }

    private fun deliver(source: ActiveSource, cached: CachedPreview, target: Long, requestId: Long, origin: SeekPreviewOrigin): SeekPreviewFrame? {
        synchronized(lock) {
            if (closed || active !== source || !cached.metadata.matches(target) || cached.metadata.identity != source.identity) return null
            val metadata = cached.metadata
            return SeekPreviewFrame(
                cached.bitmap, metadata.actualMs ?: metadata.cueStartMs ?: return null,
                target, metadata.actualMs, metadata.cueStartMs, metadata.cueEndMs,
                source.generation, requestId, source.identity, origin, metadata.validity,
            )
        }
    }

    /** Accept only a renderer-supplied presentation timestamp and its captured source generation. */
    fun rememberRenderedFrame(positionMs: Long, bitmap: Bitmap, capturedSourceGeneration: Long = -1): SeekPreviewFrame? {
        val source = active ?: return null
        if (source.memoryDisabled || capturedSourceGeneration != source.generation || positionMs < 0 || positionMs > source.source.durationMs) return null
        val metadata = PreviewMetadata(source.identity, positionMs, positionMs, null, null, SeekPreviewOrigin.RENDERED, SeekPreviewValidity.TIMESTAMP)
        val entry = try {
            CachedPreview(normalizeFrame(bitmap, source.source.maxWidthPx), metadata)
        } catch (_: OutOfMemoryError) {
            disableForMemoryPressure(source)
            return null
        }
        return deliver(source, entry, positionMs, 0, SeekPreviewOrigin.RENDERED)?.also {
            memoryCache.put(frameKey(source, positionMs), entry)
        }
    }

    private suspend fun extractWithMedia3(source: ActiveSource, target: Long): SeekPreviewImage {
        val uri = localDecoderUri(source)
        return withContext(Dispatchers.Main.immediate) {
            currentCoroutineContext().ensureActive()
            if (closed || active !== source) throw CancellationException("Source replaced")
            try {
                val frame = withTimeout(MEDIA3_FRAME_TIMEOUT_MS) {
                    decoderSession.frameAt(source.identity, decodePosition(source, target), create = {
                        val extractor = FrameExtractor.Builder(appContext, MediaItem.fromUri(uri))
                            .setSeekParameters(SeekParameters(FRAME_TOLERANCE_MS * 1_000, FRAME_TOLERANCE_MS * 1_000))
                            .setMediaCodecSelector(MediaCodecSelector.PREFER_SOFTWARE)
                            .setEffects(listOf(Presentation.createForWidthAndHeight(
                                source.source.maxWidthPx, source.source.maxWidthPx * 9 / 16, Presentation.LAYOUT_SCALE_TO_FIT,
                            )))
                            .build()
                        val proxy = source.proxy
                        object : SeekPreviewDecoder<FrameExtractor.Frame> {
                            override fun frameAt(positionMs: Long): ListenableFuture<FrameExtractor.Frame> {
                                proxy?.beginRequest()
                                return extractor.getFrame(positionMs)
                            }
                            override fun close() {
                                proxy?.endRequest()
                                extractor.close()
                            }
                        }
                    }, onFrame = { decoded -> retainDecodedFrame(source, target, decoded) })
                }
                SeekPreviewImage(frame.bitmap, actualPositionMs = frame.presentationTimeMs)
            } catch (failure: Exception) {
                if (source.proxy?.memoryFailed == true) throw OutOfMemoryError("Preview range allocation failed")
                throw failure
            }
        }
    }

    private fun retainDecodedFrame(source: ActiveSource, target: Long, frame: FrameExtractor.Frame) {
        if (closed || active !== source || source.memoryDisabled) return
        val metadata = PreviewMetadata(source.identity, target, frame.presentationTimeMs, null, null,
            SeekPreviewOrigin.DECODER, SeekPreviewValidity.TIMESTAMP)
        if (!metadata.matches(target)) return
        try {
            memoryCache.put(frameKey(source, target), CachedPreview(normalizeFrame(frame.bitmap, source.source.maxWidthPx), metadata))
            // A correct late result ends the cooldown; the UI can reuse it immediately.
            if (source.unavailable && !source.decoderDisabled) {
                source.failures = 0
                source.unavailable = false
                updateStatus(source, SeekPreviewState.IDLE)
            }
        } catch (_: OutOfMemoryError) {
            disableForMemoryPressure(source)
        }
    }

    private suspend fun localDecoderUri(source: ActiveSource): Uri {
        val uri = Uri.parse(source.source.url)
        if (uri.scheme?.lowercase() !in setOf("http", "https")) return uri
        source.proxy?.let { return Uri.parse(it.url) }
        // A loopback-only byte bridge keeps Media3 maintained and playback HTTP identity intact.
        // Only the ranges actually consumed by the extractor are fetched, never the whole remux.
        val proxy = SeekPreviewRangeProxy(HttpRangeReader(previewClient, source.source.url, source.source.headers))
        try {
            proxy.prepare()
            currentCoroutineContext().ensureActive()
            synchronized(lock) {
                if (active !== source || closed) throw CancellationException("Source replaced")
                source.proxy = proxy
            }
            return Uri.parse(proxy.url)
        } catch (failure: Exception) {
            proxy.close()
            throw failure
        }
    }

    private fun sourceIdentity(source: SeekPreviewSource): String = stableHash(buildString {
        append("v5|").append(source.cacheIdentity).append('|').append(source.url).append('|')
        append(source.durationMs).append('|').append(source.maxWidthPx).append('|')
        source.headers.toSortedMap(String.CASE_INSENSITIVE_ORDER).forEach { (name, value) ->
            append(name.lowercase()).append('=').append(value).append(';')
        }
        source.url.toHttpUrlOrNull()?.let { url ->
            previewClient.cookieJar.loadForRequest(url).sortedBy { it.name }.forEach { append(it.toString()).append(';') }
        }
        source.preview?.let { append('|').append(nativePreviewCacheIdentity(it)) }
    })

    private fun frameKey(source: ActiveSource, positionMs: Long) = "${source.identity}_${quantizeSeekPreviewPosition(positionMs, source.source.durationMs)}"
    private fun decodePosition(source: ActiveSource, target: Long) =
        target.coerceIn(0L, source.source.durationMs - 1)
    private fun clamp(positionMs: Long, source: ActiveSource) = positionMs.coerceIn(0, source.source.durationMs)

    override fun close() {
        synchronized(lock) {
            if (closed) return
            closed = true
            scheduler.close()
            diskWriter.close()
            active?.close()
            active = null
            memoryCache.evictAll()
            mutableStatus.value = SeekPreviewStatus(++generation, reason = "Provider closed")
        }
        scope.cancel()
        // close queues release behind existing Media3 work, without submitting another decode.
        CoroutineScope(Dispatchers.Main.immediate).launch {
            decoderSession.close()
        }
    }
}

private fun normalizeFrame(bitmap: Bitmap, maxWidth: Int): Bitmap {
    val maxHeight = maxWidth * 9 / 16
    val scale = minOf(maxWidth.toFloat() / bitmap.width, maxHeight.toFloat() / bitmap.height, 1f)
    val width = (bitmap.width * scale).roundToInt().coerceAtLeast(1)
    val height = (bitmap.height * scale).roundToInt().coerceAtLeast(1)
    val scaled = if (width != bitmap.width || height != bitmap.height) Bitmap.createScaledBitmap(bitmap, width, height, true) else bitmap
    // Media3 can return the same deduplicated bitmap again. Its bitmap must not be recycled here.
    return if (Build.VERSION.SDK_INT >= 26 && scaled.config == Bitmap.Config.HARDWARE) {
        scaled.copy(Bitmap.Config.ARGB_8888, false)
    } else scaled
}

internal data class PreviewMetadata(
    val identity: String,
    val extractedForMs: Long,
    val actualMs: Long?,
    val cueStartMs: Long?,
    val cueEndMs: Long?,
    val origin: SeekPreviewOrigin,
    val validity: SeekPreviewValidity,
) {
    fun matches(targetMs: Long) = seekPreviewTimeMatches(targetMs, actualMs, cueStartMs, cueEndMs, validity)

    fun write(output: DataOutputStream) {
        output.writeInt(SEEK_PREVIEW_CACHE_EPOCH)
        output.writeUTF(identity)
        output.writeLong(extractedForMs)
        listOf(actualMs, cueStartMs, cueEndMs).forEach { value ->
            output.writeBoolean(value != null)
            if (value != null) output.writeLong(value)
        }
        output.writeUTF(origin.name)
        output.writeUTF(validity.name)
    }

    companion object {
        fun read(input: DataInputStream): PreviewMetadata {
            if (input.readInt() != SEEK_PREVIEW_CACHE_EPOCH) throw IOException("Unvalidated cache epoch")
            return PreviewMetadata(
                input.readUTF(), input.readLong(),
                if (input.readBoolean()) input.readLong() else null,
                if (input.readBoolean()) input.readLong() else null,
                if (input.readBoolean()) input.readLong() else null,
                SeekPreviewOrigin.valueOf(input.readUTF()), SeekPreviewValidity.valueOf(input.readUTF()),
            ).also { if (!it.matches(it.extractedForMs)) throw IOException("Invalid preview metadata") }
        }
    }
}

internal data class CachedPreview(val bitmap: Bitmap, val metadata: PreviewMetadata)

/** A single atomic metadata+JPEG entry prevents pictures from acquiring another entry's time. */
internal class SeekPreviewDiskCache(private val root: File, private val limitBytes: Long) {
    init { root.mkdirs() }

    @Synchronized fun read(key: String, identity: String): CachedPreview? {
        val file = File(root, "$key.preview")
        if (!file.isFile || file.length() !in 1..MAX_CACHE_ENTRY_BYTES) return null
        return try {
            file.inputStream().buffered().use { stream ->
                val metadata = PreviewMetadata.read(DataInputStream(stream))
                if (metadata.identity != identity) return null
                // Bound dimensions before decoding even though the cache is app-private.
                val bytes = stream.readBytes()
                val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
                BitmapFactory.decodeByteArray(bytes, 0, bytes.size, bounds)
                if (bounds.outWidth !in 1..640 || bounds.outHeight !in 1..360) return null
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size) ?: return null
                file.setLastModified(System.currentTimeMillis())
                CachedPreview(bitmap, metadata)
            }
        } catch (failure: OutOfMemoryError) {
            file.delete()
            throw failure
        } catch (_: Exception) {
            file.delete()
            null
        }
    }

    @Synchronized fun write(key: String, entry: CachedPreview) {
        if (!entry.metadata.matches(entry.metadata.extractedForMs)) return
        val target = File(root, "$key.preview")
        var temporary: File? = null
        try {
            val pending = File.createTempFile("preview-", ".tmp", root)
            temporary = pending
            pending.outputStream().buffered().use { stream ->
                entry.metadata.write(DataOutputStream(stream))
                if (!entry.bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)) throw IOException("JPEG failed")
            }
            if (pending.length() > MAX_CACHE_ENTRY_BYTES || pending.length() > limitBytes) return
            // Same-directory rename is atomic. Delete an old same-bucket entry before replacement.
            if (target.exists()) target.delete()
            if (!pending.renameTo(target)) return
            prune()
        } catch (_: IOException) {
            // A cache failure must never turn a valid frame into a failed seek.
        } finally {
            temporary?.delete()
        }
    }

    private fun prune() {
        val files = root.listFiles { file -> file.isFile && file.extension == "preview" }?.sortedBy { it.lastModified() }.orEmpty()
        var bytes = files.sumOf { it.length() }
        for (file in files) {
            if (bytes <= limitBytes) break
            val size = file.length()
            if (file.delete()) bytes -= size
        }
    }
}

internal class UnsupportedPreviewException(message: String) : IOException(message)

internal data class PreviewContentRange(val start: Long, val end: Long, val total: Long?)

internal fun parsePreviewContentRange(value: String?): PreviewContentRange? {
    val match = Regex("bytes (\\d+)-(\\d+)/(\\d+|\\*)", RegexOption.IGNORE_CASE).matchEntire(value?.trim() ?: return null) ?: return null
    val start = match.groupValues[1].toLongOrNull() ?: return null
    val end = match.groupValues[2].toLongOrNull() ?: return null
    val total = match.groupValues[3].takeUnless { it == "*" }?.toLongOrNull()
    if (match.groupValues[3] != "*" && total == null) return null
    if (end < start || end == Long.MAX_VALUE || (total != null && (total <= 0 || end >= total))) return null
    return PreviewContentRange(start, end, total)
}

/** Strict, cancellable random access through playback's client, cookie jar and interceptors. */
internal class HttpRangeReader(
    private val client: OkHttpClient,
    private val url: String,
    private val headers: Map<String, String>,
) : Closeable {
    private val chunks = LinkedHashMap<Long, ByteArray>(16, 0.75f, true)
    private var cachedBytes = 0
    private var resolvedSize: Long? = null
    private var headAttempted = false
    private val remainingBytes = AtomicLong(MAX_EXTRACTION_NETWORK_BYTES)
    @Volatile private var activeCall: Call? = null
    @Volatile private var closed = false

    suspend fun size(): Long {
        checkOpen()
        resolvedSize?.let { return it }
        if (!headAttempted) {
            headAttempted = true
            val length = try {
                request(builder().head().build()) { response ->
                    if (response.code == 200 && response.header("Content-Encoding").let { it == null || it.equals("identity", true) }) {
                        response.header("Content-Length")?.toLongOrNull()?.takeIf { it >= 0 }
                    } else null
                }
            } catch (_: IOException) {
                currentCoroutineContext().ensureActive()
                null
            }
            length?.let { resolvedSize = it; return it }
        }
        fetch(0)
        return resolvedSize ?: -1
    }

    suspend fun read(position: Long, size: Int): ByteArray {
        require(position >= 0 && size in 0..RANGE_CHUNK_BYTES)
        checkOpen()
        if (size == 0 || resolvedSize?.let { position >= it } == true) return ByteArray(0)
        val output = ByteArray(size)
        var copied = 0
        while (copied < size) {
            currentCoroutineContext().ensureActive()
            val cursor = position + copied
            if (cursor < position) throw IOException("Range offset overflow")
            val chunkStart = (cursor / RANGE_CHUNK_BYTES) * RANGE_CHUNK_BYTES
            val chunk = synchronized(chunks) { chunks[chunkStart] } ?: fetch(chunkStart)
            val offset = (cursor - chunkStart).toInt()
            if (offset >= chunk.size) break
            val count = minOf(size - copied, chunk.size - offset)
            chunk.copyInto(output, copied, offset, offset + count)
            copied += count
            if (chunk.size < RANGE_CHUNK_BYTES) break
        }
        return output.copyOf(copied)
    }

    private suspend fun fetch(start: Long): ByteArray {
        checkOpen()
        if (resolvedSize?.let { start >= it } == true) return ByteArray(0)
        if (start > Long.MAX_VALUE - RANGE_CHUNK_BYTES) throw IOException("Range offset overflow")
        val end = minOf(start + RANGE_CHUNK_BYTES - 1, resolvedSize?.minus(1) ?: Long.MAX_VALUE)
        return request(builder().header("Range", "bytes=$start-$end").build()) { response ->
            if (response.code == 416) {
                val match = Regex("bytes \\*/(\\d+)", RegexOption.IGNORE_CASE).matchEntire(response.header("Content-Range")?.trim().orEmpty())
                val total = match?.groupValues?.get(1)?.toLongOrNull()
                    ?: throw UnsupportedPreviewException("Invalid range EOF")
                if (start < total) throw UnsupportedPreviewException("Premature range EOF")
                acceptSize(total)
                return@request ByteArray(0)
            }
            if (response.code != 206) throw UnsupportedPreviewException("Server does not supply byte ranges")
            if (response.header("Content-Encoding").let { it != null && !it.equals("identity", true) }) {
                throw UnsupportedPreviewException("Encoded byte range")
            }
            val range = parsePreviewContentRange(response.header("Content-Range"))
                ?: throw UnsupportedPreviewException("Invalid Content-Range")
            if (range.start != start || range.end > end) throw UnsupportedPreviewException("Mismatched byte range")
            val expected = (range.end - range.start + 1).toInt()
            if (remainingBytes.addAndGet(-expected.toLong()) < 0) throw UnsupportedPreviewException("Preview network budget exceeded")
            val lengthHeader = response.header("Content-Length")
            if (lengthHeader != null && lengthHeader.toLongOrNull() != expected.toLong()) throw UnsupportedPreviewException("Mismatched range length")
            if (range.end < end && range.total != range.end + 1) throw UnsupportedPreviewException("Incomplete byte range")
            range.total?.let(::acceptSize)
            val body = response.body ?: throw IOException("Empty range body")
            val bytes = ByteArray(expected)
            body.byteStream().use { input ->
                var count = 0
                while (count < bytes.size) {
                    checkOpen()
                    val read = input.read(bytes, count, bytes.size - count)
                    if (read < 0) throw EOFException("Truncated byte range")
                    count += read
                }
                if (input.read() != -1) throw UnsupportedPreviewException("Oversized byte range")
            }
            synchronized(chunks) {
                if (!closed) {
                    chunks.put(start, bytes)?.let { cachedBytes -= it.size }
                    cachedBytes += bytes.size
                    val iterator = chunks.entries.iterator()
                    while (cachedBytes > RANGE_MEMORY_LIMIT_BYTES && iterator.hasNext()) {
                        cachedBytes -= iterator.next().value.size
                        iterator.remove()
                    }
                }
            }
            bytes
        }
    }

    private fun acceptSize(total: Long) {
        if (resolvedSize != null && resolvedSize != total) throw UnsupportedPreviewException("Media size changed")
        resolvedSize = total
    }

    private fun builder(): Request.Builder = Request.Builder().url(url).apply {
        headers.forEach { (name, value) ->
            if (name.isNotBlank() && value.isNotBlank() && name.lowercase() !in setOf("range", "accept-encoding")) header(name, value)
        }
        header("Accept-Encoding", "identity")
    }

    private suspend fun <T> request(request: Request, consume: (Response) -> T): T = suspendCancellableCoroutine { continuation ->
        val call = client.newCall(request)
        activeCall = call
        continuation.invokeOnCancellation { call.cancel() }
        if (closed) call.cancel()
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (activeCall === call) activeCall = null
                if (continuation.isActive) continuation.resumeWithException(e)
            }
            override fun onResponse(call: Call, response: Response) {
                try {
                    val result = response.use(consume)
                    if (continuation.isActive) continuation.resume(result)
                } catch (failure: OutOfMemoryError) {
                    if (continuation.isActive) continuation.resumeWithException(failure)
                } catch (failure: Exception) {
                    if (continuation.isActive) continuation.resumeWithException(failure)
                } finally {
                    if (activeCall === call) activeCall = null
                }
            }
        })
    }

    private fun checkOpen() { if (closed) throw IOException("Range source closed") }
    fun beginBudget() { remainingBytes.set(MAX_EXTRACTION_NETWORK_BYTES) }
    fun cancelPending() { activeCall?.cancel() }
    override fun close() {
        closed = true
        activeCall?.cancel()
        synchronized(chunks) { chunks.clear(); cachedBytes = 0 }
    }
}

/** Not a media host: binds only loopback and requires an unguessable, per-source path. */
internal class SeekPreviewRangeProxy(private val reader: HttpRangeReader) : NanoHTTPD("127.0.0.1", 0), Closeable {
    private class Lease {
        val remaining = AtomicLong(MAX_EXTRACTION_NETWORK_BYTES)
        @Volatile var deadlineNanos = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(SEEK_PREVIEW_REQUEST_TIMEOUT_MS)
    }
    private val path = "/${UUID.randomUUID()}/media"
    private val readLock = Mutex()
    private var mediaSize = -1L
    @Volatile private var lease: Lease? = null
    @Volatile private var closed = false
    @Volatile var memoryFailed = false
        private set
    val url: String get() = "http://127.0.0.1:$listeningPort$path"

    suspend fun prepare() {
        mediaSize = reader.size()
        if (mediaSize <= 0) throw UnsupportedPreviewException("No seekable media length")
        // Probe even after a successful HEAD so a 200-only server is rejected before decode.
        reader.read(0, 1)
        start(2_000, true)
    }

    fun beginRequest() {
        if (!closed) {
            reader.beginBudget()
            // Keep an existing decoder connection valid between nearby seeks.
            // Reset limits only for an actual new target, never for background reads.
            val current = lease ?: Lease().also { lease = it }
            current.remaining.set(MAX_EXTRACTION_NETWORK_BYTES)
            current.deadlineNanos = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(SEEK_PREVIEW_REQUEST_TIMEOUT_MS)
        }
    }

    fun endRequest() {
        lease = null
        reader.cancelPending()
        asyncRunner.closeAll()
    }

    override fun serve(session: IHTTPSession): Response {
        if (session.uri != path || session.method !in setOf(Method.GET, Method.HEAD)) {
            return newFixedLengthResponse(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "")
        }
        val current = lease
            ?: return newFixedLengthResponse(Response.Status.SERVICE_UNAVAILABLE, MIME_PLAINTEXT, "")
        val rangeHeader = session.headers["range"]
        val range = rangeHeader?.let { Regex("bytes=(\\d+)-(\\d*)").matchEntire(it) }
        val start = if (rangeHeader == null) 0L else range?.groupValues?.get(1)?.toLongOrNull() ?: -1
        val requestedEnd = range?.groupValues?.get(2)?.takeIf { it.isNotEmpty() }
        val end = if (requestedEnd == null) mediaSize - 1 else requestedEnd.toLongOrNull()?.coerceAtMost(mediaSize - 1) ?: -1
        if (start < 0 || start >= mediaSize || end < start) {
            return newFixedLengthResponse(Response.Status.RANGE_NOT_SATISFIABLE, MIME_PLAINTEXT, "").apply {
                addHeader("Content-Range", "bytes */$mediaSize")
            }
        }
        val body = object : InputStream() {
            private var position = start
            private var streamClosed = false
            override fun read(): Int {
                val byte = ByteArray(1)
                return if (read(byte, 0, 1) < 0) -1 else byte[0].toInt() and 0xff
            }
            override fun read(buffer: ByteArray, offset: Int, length: Int): Int {
                if (length == 0) return 0
                if (position > end) return -1
                if (streamClosed || closed || lease !== current || System.nanoTime() >= current.deadlineNanos) {
                    throw IOException("Preview request expired")
                }
                val count = minOf(length.toLong(), RANGE_CHUNK_BYTES.toLong(), end - position + 1).toInt()
                if (current.remaining.addAndGet(-count.toLong()) < 0) throw IOException("Preview byte budget exceeded")
                val bytes = try {
                    runBlocking {
                        readLock.withLock {
                            if (lease !== current) throw IOException("Preview replaced")
                            reader.read(position, count)
                        }
                    }
                } catch (_: OutOfMemoryError) {
                    memoryFailed = true
                    throw IOException("Preview range allocation failed")
                }
                if (bytes.isEmpty()) throw EOFException("Incomplete preview range")
                bytes.copyInto(buffer, offset)
                position += bytes.size
                return bytes.size
            }
            override fun close() { streamClosed = true }
        }
        return newFixedLengthResponse(
            if (rangeHeader == null) Response.Status.OK else Response.Status.PARTIAL_CONTENT,
            "application/octet-stream", body, end - start + 1,
        ).apply {
            addHeader("Accept-Ranges", "bytes")
            addHeader("Cache-Control", "no-store")
            if (rangeHeader != null) addHeader("Content-Range", "bytes $start-$end/$mediaSize")
        }
    }

    override fun close() {
        closed = true
        endRequest()
        reader.close()
        stop()
    }
}

private fun stableHash(value: String): String = MessageDigest.getInstance("SHA-256")
    .digest(value.toByteArray(Charsets.UTF_8)).joinToString("") { byte -> "%02x".format(byte) }.take(32)
