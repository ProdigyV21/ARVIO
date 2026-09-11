package com.arflix.tv.ui.screens.player.preview

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.ByteArrayOutputStream
import java.io.Closeable
import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal data class PreviewByteRange(val offset: Long, val length: Int, val totalBytes: Long? = null, val validator: String? = null)
internal data class PreviewHttpBytes(val url: String, val bytes: ByteArray, val totalBytes: Long, val partial: Boolean, val validator: String?)

/** Shares playback cookies/TLS/interceptors, but never automatically forwards custom auth headers. */
internal class PreviewHttpClient(
    playbackClient: OkHttpClient,
    private val origin: HttpUrl,
    private val headers: Map<String, String>
) : Closeable {
    private val closed = AtomicBoolean(false)
    private val active = AtomicReference<Call?>(null)
    private val client = playbackClient.newBuilder()
        .connectTimeout(2, TimeUnit.SECONDS)
        .readTimeout(2, TimeUnit.SECONDS)
        .callTimeout(3, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .followRedirects(false)
        .followSslRedirects(false)
        .build()

    suspend fun get(url: String, maxBytes: Int, range: PreviewByteRange? = null, accept: String? = null): PreviewHttpBytes {
        require(maxBytes in 1..PREVIEW_MAX_IMAGE_BYTES)
        if (range != null) require(range.offset >= 0 && range.length in 1..maxBytes &&
            range.offset <= Long.MAX_VALUE - range.length)
        var target = url.toHttpUrlOrNull() ?: throw IOException("Invalid preview URL")
        repeat(3) { redirect ->
            if (closed.get()) throw IOException("Preview closed")
            if (!origin.samePreviewOrigin(target) || target.username.isNotEmpty() || target.password.isNotEmpty()) {
                throw IOException("Cross-origin preview rejected")
            }
            val request = Request.Builder().url(target).get().apply {
                headers.forEach { (key, value) ->
                    if (key.lowercase() !in setOf("host", "range", "content-length", "accept-encoding", "if-range", "connection")) header(key, value)
                }
                header("Accept-Encoding", "identity")
                accept?.let { header("Accept", it) }
                if (range != null) {
                    header("Range", "bytes=${range.offset}-${range.offset + range.length - 1}")
                    range.validator?.let { header("If-Range", it) }
                }
            }.build()
            val reply = execute(request, maxBytes, range)
            if (reply.redirect == null) return reply.data ?: throw IOException("Empty preview response")
            if (redirect == 2) throw IOException("Too many preview redirects")
            target = target.resolve(reply.redirect) ?: throw IOException("Invalid preview redirect")
        }
        throw IOException("Missing preview response")
    }

    private data class Reply(val data: PreviewHttpBytes? = null, val redirect: String? = null)

    private suspend fun execute(request: Request, maxBytes: Int, range: PreviewByteRange?): Reply =
        suspendCancellableCoroutine { continuation ->
            val call = client.newCall(request)
            if (!active.compareAndSet(null, call)) {
                continuation.resumeWithException(IOException("Preview request already active"))
                return@suspendCancellableCoroutine
            }
            continuation.invokeOnCancellation {
                call.cancel()
                active.compareAndSet(call, null)
            }
            if (closed.get()) call.cancel()
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    active.compareAndSet(call, null)
                    if (continuation.isActive) continuation.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val reply = try { response.use { readReply(it, maxBytes, range) } }
                    catch (error: OutOfMemoryError) {
                        // Deliver to the provider's memory circuit breaker, not OkHttp's thread.
                        active.compareAndSet(call, null)
                        if (continuation.isActive) continuation.resumeWithException(error)
                        return
                    }
                    catch (error: Exception) {
                        active.compareAndSet(call, null)
                        if (continuation.isActive) continuation.resumeWithException(error)
                        return
                    }
                    active.compareAndSet(call, null)
                    if (continuation.isActive) continuation.resume(reply)
                }
            })
        }

    private fun readReply(response: Response, maxBytes: Int, range: PreviewByteRange?): Reply {
        if (response.code in setOf(301, 302, 303, 307, 308)) {
            return Reply(redirect = response.header("Location") ?: throw IOException("Missing preview redirect"))
        }
        val partial = response.code == 206
        if (response.code != 200 && !partial) throw IOException("Preview unavailable (${response.code})")
        if (range == null && partial) throw IOException("Unexpected partial preview")
        if (range != null && !partial && (range.offset != 0L || range.totalBytes != null)) {
            throw IOException("Preview range ignored")
        }
        if (response.header("Content-Encoding")?.let { !it.equals("identity", true) } == true) {
            throw IOException("Encoded preview range unsupported")
        }
        val body = response.body ?: throw IOException("Empty preview body")
        val contentRange = if (partial) parseContentRange(response.header("Content-Range")) else null
        if (partial && (range == null || contentRange == null || contentRange.first != range.offset ||
                contentRange.second != range.offset + range.length - 1 ||
                (range.totalBytes != null && contentRange.third != range.totalBytes))) {
            throw IOException("Invalid preview content range")
        }
        val limit = if (partial) range!!.length else maxBytes
        if (body.contentLength() > limit) throw IOException("Preview exceeds byte budget")
        val output = ByteArrayOutputStream(minOf(limit, 8192))
        val buffer = ByteArray(8192)
        body.byteStream().use { stream ->
            while (true) {
                val count = stream.read(buffer, 0, minOf(buffer.size, limit - output.size() + 1))
                if (count < 0) break
                if (output.size() + count > limit) throw IOException("Preview exceeds byte budget")
                output.write(buffer, 0, count)
            }
        }
        val bytes = output.toByteArray()
        if ((partial && bytes.size != limit) || (body.contentLength() >= 0 && bytes.size.toLong() != body.contentLength())) {
            throw IOException("Truncated preview response")
        }
        val validator = response.header("ETag")?.takeUnless { it.startsWith("W/") }
            ?: response.header("Last-Modified")
        if (range?.validator != null && validator != null && validator != range.validator) throw IOException("Preview index changed")
        return Reply(data = PreviewHttpBytes(response.request.url.toString(), bytes,
            contentRange?.third ?: bytes.size.toLong(), partial, validator))
    }

    override fun close() {
        closed.set(true)
        active.get()?.cancel()
    }

    companion object {
        internal fun parseContentRange(value: String?): Triple<Long, Long, Long>? {
            val match = value?.let { Regex("bytes (\\d+)-(\\d+)/(\\d+)").matchEntire(it) } ?: return null
            val start = match.groupValues[1].toLongOrNull() ?: return null
            val end = match.groupValues[2].toLongOrNull() ?: return null
            val total = match.groupValues[3].toLongOrNull() ?: return null
            return Triple(start, end, total).takeIf { start >= 0 && end >= start && total > end }
        }
    }
}
