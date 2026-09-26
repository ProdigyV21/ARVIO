package com.arflix.tv.ui.screens.player.subtitles

import com.arflix.tv.network.OkHttpProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * HTTP transport for [MatroskaSubtitleIndex]: bounded range reads against the stream the player is
 * already playing.
 *
 * Kept apart from the parser so the parser stays pure and JVM-testable. The budget is the point —
 * this runs *while the user is watching*, against the same origin the video is streaming from, so
 * it must be incapable of turning into a second download: a whole-file read is refused by the byte
 * cap, and a server that ignores `Range` is dropped after the first reply.
 */
internal object MatroskaIndexSource {

    /** Whole-operation ceiling. A miss must cost seconds, not a scan's worth of time. */
    private const val TOTAL_TIMEOUT_MS = 7_000L
    // 24MB budget: an MP4's moov (read whole for its subtitle sample tables) runs
    // to several MB on a long 4K file, beyond what a Matroska index ever needed.
    private const val MAX_TOTAL_BYTES = 24L * 1024 * 1024
    private const val MAX_REQUESTS = 16

    /**
     * Reads the embedded subtitle timelines for [url], or null when this source cannot supply one
     * cheaply (not Matroska, no range support, no Cues index for a subtitle track, budget spent).
     *
     * [headers] are the stream's own proxy headers — debrid links commonly 401/redirect without
     * them. Hop-by-hop and range-control names are dropped: the range is ours to set.
     */
    suspend fun load(
        url: String,
        headers: Map<String, String>,
        onDiagnostic: (String) -> Unit = {},
    ): MatroskaSubtitleIndex.IndexedTimeline? {
        val (entry, joined) = sharedLoad(url, headers)
        if (joined) {
            onDiagnostic("matroska index: joined the load started at stream open (${if (entry.result.isCompleted) "ready" else "in flight"})")
        }
        return entry.result.await()
    }

    /**
     * Starts reading [url]'s index now, so the scan finds it ready — starts it when
     * the stream opens rather than when a subtitle scan reaches the reference step (~2s on a 4K
     * MP4). Fire-and-forget: a later [load] of the same URL joins it.
     */
    fun prefetch(url: String, headers: Map<String, String>) {
        sharedLoad(url, headers)
    }

    /** Whether [url]'s index has already been read successfully — a later [load] returns at once. */
    fun isReady(url: String): Boolean = synchronized(this) { shared?.let { it.url == url && it.produced } == true }

    private class SharedLoad(val url: String) {
        lateinit var result: Deferred<MatroskaSubtitleIndex.IndexedTimeline?>
        @Volatile var produced = false
    }

    /** Only the stream being played is worth holding — a new URL replaces the entry. */
    private var shared: SharedLoad? = null
    private val sharedScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    /**
     * The one load for [url]: joined while in flight, reused once it produced a timeline (a rescan
     * of the same file then costs no requests), and restarted when it produced nothing — that can
     * be a transient refusal, and a fresh attempt costs no more than every scan paid before.
     * It runs outside the caller's scope so a cancelled scan leaves it for the next one.
     */
    private fun sharedLoad(url: String, headers: Map<String, String>): Pair<SharedLoad, Boolean> = synchronized(this) {
        shared?.takeIf { held ->
            held.url == url && (!held.result.isCompleted || held.produced)
        }?.let { return it to true }
        val diagnostic: (String) -> Unit = { android.util.Log.i("SubMatch", it) }
        val entry = SharedLoad(url)
        entry.result = sharedScope.async {
            runCatching { loadNow(url, headers, diagnostic) }
                .onFailure { diagnostic("matroska index failed: ${it.message}") }
                .getOrNull()
                .also { entry.produced = it != null }
        }
        shared = entry
        entry to false
    }

    private suspend fun loadNow(
        url: String,
        headers: Map<String, String>,
        onDiagnostic: (String) -> Unit,
    ): MatroskaSubtitleIndex.IndexedTimeline? {
        if (!url.startsWith("http://", ignoreCase = true) && !url.startsWith("https://", ignoreCase = true)) {
            onDiagnostic("matroska index: skipped, not an http(s) stream")
            return null
        }
        val source = HttpRangeSource(url, sanitizeHeaders(headers), onDiagnostic)
        // The summary is emitted OUTSIDE the timeout on purpose: a 7s expiry cancels the block, so
        // a log written inside it never runs — and a timeout is precisely the case worth seeing.
        var completed = false
        val timeline = withTimeoutOrNull(TOTAL_TIMEOUT_MS) {
            withContext(Dispatchers.IO) {
                MatroskaSubtitleIndex.load(source, onDiagnostic = onDiagnostic)
                    .also { completed = true }
            }
        }
        onDiagnostic(
            "matroska index: requests=${source.requestCount} read=${source.bytesRead / 1024}KB " +
                "rangeIgnored=${source.rangeIgnored} throttled=${source.wasThrottled} " +
                (if (!completed) "TIMED OUT after ${TOTAL_TIMEOUT_MS}ms " else "") +
                "result=${if (timeline == null) "none" else "ok"}"
        )
        return timeline
    }

    /**
     * Playback headers only. `Range`/`If-Range` would fight the reads this class issues, and the
     * content-negotiation names below make a server answer with something other than the bytes at
     * the offset asked for — which the parser would then read as corrupt EBML.
     */
    private val DROPPED_HEADERS = setOf(
        "range", "if-range", "accept-encoding", "content-length", "host", "connection", "te", "trailer",
    )

    private fun sanitizeHeaders(headers: Map<String, String>): Map<String, String> =
        headers.asSequence()
            .map { (name, value) -> name.trim() to value.trim() }
            .filter { (name, value) -> name.isNotEmpty() && value.isNotEmpty() }
            .filter { (name, _) -> name.lowercase() !in DROPPED_HEADERS }
            // Header injection guard, same rule the player applies to proxy headers.
            .filter { (name, value) ->
                name.all { it.code in 33..126 && it !in FORBIDDEN_NAME_CHARS } &&
                    value.all { it == '\t' || it.code in 32..126 }
            }
            .toMap()

    private val FORBIDDEN_NAME_CHARS =
        setOf('(', ')', '<', '>', '@', ',', ';', ':', '\\', '"', '/', '[', ']', '?', '=', '{', '}')

    /**
     * Shares the playback client's DNS/TLS/connection pool — the stream host has already been
     * resolved and connected for video, so these reads reuse that rather than opening a new path
     * to the same origin. Timeouts are short by design: this is an optimisation, and a slow
     * answer is worth less than the fallback that is already available.
     */
    private class HttpRangeSource(
        private val url: String,
        private val headers: Map<String, String>,
        private val onDiagnostic: (String) -> Unit,
    ) : MatroskaSubtitleIndex.ByteRangeSource {

        private val client: OkHttpClient = OkHttpProvider.playbackClient.newBuilder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(4, TimeUnit.SECONDS)
            .callTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()

        private val requests = AtomicInteger(0)
        private val bytes = AtomicLong(0L)
        private var reservedBytes = 0L
        @Volatile private var rangeUnsupported = false
        @Volatile private var throttled = false

        val requestCount: Int get() = requests.get()
        val bytesRead: Long get() = bytes.get()
        val rangeIgnored: Boolean get() = rangeUnsupported
        val wasThrottled: Boolean get() = throttled

        override suspend fun read(offset: Long, length: Int): ByteArray? {
            if (rangeUnsupported || throttled || length <= 0 || offset < 0) return null
            if (requests.get() >= MAX_REQUESTS) return null
            if (reservedBytes + length > MAX_TOTAL_BYTES) return null
            reservedBytes += length
            requests.incrementAndGet()

            val request = Request.Builder()
                .url(url)
                .get()
                .apply {
                    headers.forEach { (name, value) -> header(name, value) }
                    header("Accept-Encoding", "identity")
                    header("Range", "bytes=$offset-${offset + length - 1}")
                }
                .build()

            return runCatching {
                client.newCall(request).awaitBytes { response ->
                    when {
                        response.code == 206 -> readRangeBody(response, offset, length)
                        // 200 means the server ignored the range and is about to stream the whole
                        // file. Reading it would download the movie to find a subtitle index, so
                        // the source disables itself and the scan falls back.
                        response.isSuccessful -> {
                            onDiagnostic("matroska index: server ignored Range (HTTP ${response.code}) — stopping")
                            rangeUnsupported = true
                            null
                        }
                        // Throttling or an overloaded origin: stop the whole attempt rather than
                        // spend the remaining budget making it worse. There is no retry here by
                        // design — the index is an optimisation, and the scan's fallback path
                        // costs the user nothing but time.
                        response.code == 429 || response.code == 503 -> {
                            onDiagnostic("matroska index: host throttled us (HTTP ${response.code}) — stopping")
                            throttled = true
                            null
                        }
                        else -> {
                            onDiagnostic("matroska index: range request failed HTTP ${response.code} at offset $offset")
                            null
                        }
                    }
                }
            }.onFailure { if (it is CancellationException) throw it }
                .getOrNull()?.also { bytes.addAndGet(it.size.toLong()) }
        }

        /** Cancellable [Call.execute]: a torn-down playback must not leave reads running. */
        private suspend fun Call.awaitBytes(read: (Response) -> ByteArray?): ByteArray? = suspendCancellableCoroutine { continuation ->
            continuation.invokeOnCancellation { runCatching { cancel() } }
            enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    try {
                        val result = response.use { if (continuation.isActive) read(it) else null }
                        if (continuation.isActive) continuation.resume(result)
                    } catch (error: Exception) {
                        if (continuation.isActive) continuation.resumeWithException(error)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    if (continuation.isActive) continuation.resumeWithException(e)
                }
            })
        }
    }

    /** Validate the returned offset and cap allocation even for dishonest/chunked responses. */
    internal fun readRangeBody(response: Response, offset: Long, length: Int): ByteArray? {
        val range = Regex("bytes (\\d+)-(\\d+)/(?:\\d+|\\*)")
            .matchEntire(response.header("Content-Range").orEmpty()) ?: return null
        val start = range.groupValues[1].toLongOrNull() ?: return null
        val end = range.groupValues[2].toLongOrNull() ?: return null
        if (start != offset || end < start || end - start >= length) return null
        val expected = end - start + 1
        val body = response.body ?: return null
        if (body.contentLength() >= 0 && body.contentLength() != expected) return null
        val source = body.source()
        if (!source.request(expected)) return null
        val result = source.readByteArray(expected)
        return result.takeIf { source.exhausted() }
    }
}
