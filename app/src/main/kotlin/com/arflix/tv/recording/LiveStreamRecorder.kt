package com.arflix.tv.recording

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

internal sealed class RecordOutcome {
    abstract val bytes: Long
    /** The window ended (or the stream announced its end) with data on disk. */
    data class Completed(override val bytes: Long) : RecordOutcome()
    /** The channel stopped answering; [bytes] may still hold the part that was recorded. */
    data class StreamFailed(val reason: String, override val bytes: Long) : RecordOutcome()
    /** DRM or SAMPLE-AES: nothing an app may or can save. */
    data class Unsupported(val reason: String, override val bytes: Long = 0L) : RecordOutcome()
    data class DiskFull(override val bytes: Long) : RecordOutcome()
}

/**
 * Saves a live channel to a file until a given time. A plain stream (MPEG-TS, as most panels
 * send) is copied as it arrives and reopened after a drop, which TS tolerates because every
 * packet stands alone. An HLS channel is followed playlist by playlist: each new segment is
 * downloaded once (AES-128 ones decrypted) and appended, so the file plays like one long
 * stream. fMP4 HLS gets its init section first and is saved as .mp4.
 */
internal class LiveStreamRecorder(
    private val calls: Call.Factory,
    private val headers: Map<String, String>,
    private val clock: () -> Long = System::currentTimeMillis,
    private val freeBytes: (File) -> Long = { it.parentFile?.usableSpace ?: Long.MAX_VALUE },
    private val pause: suspend (Long) -> Unit = { delay(it) },
) {
    /** [output] is asked once, with "ts" or "mp4", when the stream's format is known. */
    suspend fun record(url: String, until: Long, output: (String) -> File, onProgress: (Long) -> Unit = {}): RecordOutcome =
        withContext(Dispatchers.IO) {
            var written = 0L
            var file: File? = null
            fun fileFor(extension: String): File = file ?: output(extension).also { file = it }
            var failures = 0
            while (clock() < until) {
                currentCoroutineContext().ensureActive()
                val response = try {
                    open(url)
                } catch (e: IOException) {
                    failures++
                    if (failures > MAX_CONSECUTIVE_FAILURES) return@withContext failed(e.message ?: "no answer", written)
                    pause(backoffMs(failures))
                    continue
                }
                if (!response.isSuccessful) {
                    val code = response.code
                    response.close()
                    failures++
                    if (failures > MAX_CONSECUTIVE_FAILURES) return@withContext failed("HTTP $code", written)
                    pause(backoffMs(failures))
                    continue
                }
                val body = response.body ?: run { response.close(); return@withContext failed("empty answer", written) }
                val peek = try {
                    body.source().peek().let { source ->
                        source.request(PEEK_BYTES)
                        val available = minOf(source.buffer.size, PEEK_BYTES)
                        source.readUtf8(available)
                    }
                } catch (e: IOException) {
                    ""
                }
                val contentType = body.contentType()?.toString().orEmpty().lowercase()
                if (looksLikeHlsPlaylist(peek) || "mpegurl" in contentType) {
                    val text = response.use { it.body?.string().orEmpty() }
                    return@withContext recordHls(text, response.request.url.toString(), until, ::fileFor, written, onProgress)
                }
                // A plain stream: copy until the window closes or the connection drops.
                val target = fileFor("ts")
                val startedAt = clock()
                val result = response.use { copyBody(it, target, until, written, onProgress) }
                written = result.first
                if (result.second == CopyEnd.DiskFull) return@withContext RecordOutcome.DiskFull(written)
                if (clock() >= until) break
                // A connection that carried the game for a while gets a fresh set of retries.
                failures = if (clock() - startedAt > HEALTHY_STRETCH_MS) 1 else failures + 1
                if (failures > MAX_CONSECUTIVE_FAILURES) return@withContext failed("stream ended", written)
                pause(backoffMs(failures))
            }
            if (written > 0) RecordOutcome.Completed(written) else RecordOutcome.StreamFailed("nothing received", 0)
        }

    private enum class CopyEnd { Window, Dropped, DiskFull }

    private suspend fun copyBody(response: Response, target: File, until: Long, startBytes: Long, onProgress: (Long) -> Unit): Pair<Long, CopyEnd> {
        var written = startBytes
        val source = response.body?.byteStream() ?: return written to CopyEnd.Dropped
        // A stop is seen between reads; a stalled read ends by the client's read timeout.
        run {
            FileOutputStream(target, true).use { out ->
                val buffer = ByteArray(COPY_BUFFER)
                var sinceCheck = 0L
                while (clock() < until) {
                    currentCoroutineContext().ensureActive()
                    val read = try { source.read(buffer) } catch (e: IOException) { return written to CopyEnd.Dropped }
                    if (read < 0) return written to CopyEnd.Dropped
                    out.write(buffer, 0, read)
                    written += read
                    sinceCheck += read
                    if (sinceCheck >= PROGRESS_EVERY_BYTES) {
                        sinceCheck = 0
                        onProgress(written)
                        if (freeBytes(target) < RECORDING_MIN_FREE_BYTES) return written to CopyEnd.DiskFull
                    }
                }
            }
        }
        onProgress(written)
        return written to CopyEnd.Window
    }

    private suspend fun recordHls(
        firstText: String,
        firstUrl: String,
        until: Long,
        fileFor: (String) -> File,
        startBytes: Long,
        onProgress: (Long) -> Unit,
    ): RecordOutcome {
        var written = startBytes
        var mediaUrl = firstUrl
        var text: String? = firstText
        (parseHlsPlaylist(firstText, firstUrl) as? HlsPlaylist.Master)?.let { master ->
            val variant = chooseRecordingVariant(master.variants) ?: return RecordOutcome.StreamFailed("no renditions", written)
            mediaUrl = variant.uri
            text = null
        }
        var lastWritten: Long? = null
        var writtenMap: String? = null
        val keys = HashMap<String, ByteArray>()
        var failures = 0
        var file: File? = null
        while (clock() < until) {
            currentCoroutineContext().ensureActive()
            val playlistText = text ?: try {
                fetchText(mediaUrl)
            } catch (e: IOException) {
                failures++
                if (failures > MAX_CONSECUTIVE_FAILURES) return failed(e.message ?: "playlist failed", written)
                pause(backoffMs(failures))
                continue
            }
            text = null
            val playlist = parseHlsPlaylist(playlistText, mediaUrl) as? HlsPlaylist.Media
                ?: return failed("unexpected playlist", written)
            val fresh = if (lastWritten == null) startingHlsSegments(playlist.segments) else newHlsSegments(playlist.segments, lastWritten)
            for (segment in fresh) {
                if (clock() >= until) break
                currentCoroutineContext().ensureActive()
                val key = segment.key
                if (key != null && !key.isNone && !key.isAes128) return RecordOutcome.Unsupported("encrypted (${key.method})", written)
                val out = file ?: fileFor(if (segment.mapUri != null) "mp4" else "ts").also { file = it }
                val bytes = try {
                    val init = segment.mapUri?.takeIf { it != writtenMap }?.let { fetchBytes(it) }
                    val data = fetchBytes(segment.uri).let { raw ->
                        if (key != null && key.isAes128) decryptAes128(raw, key, segment.sequence, keys) else raw
                    }
                    if (init != null) {
                        writtenMap = segment.mapUri
                        init + data
                    } else data
                } catch (e: IOException) {
                    // One missing segment is a short skip in the recording, not the end of it.
                    lastWritten = segment.sequence
                    continue
                }
                appendTo(out, bytes)
                written += bytes.size
                lastWritten = segment.sequence
                onProgress(written)
                if (freeBytes(out) < RECORDING_MIN_FREE_BYTES) return RecordOutcome.DiskFull(written)
            }
            failures = 0
            if (playlist.ended) break
            pause((playlist.targetDurationSec * 500).toLong().coerceIn(1_000L, 6_000L))
        }
        return if (written > 0) RecordOutcome.Completed(written) else RecordOutcome.StreamFailed("nothing received", 0)
    }

    private fun failed(reason: String, written: Long): RecordOutcome =
        RecordOutcome.StreamFailed(reason, written)

    private fun decryptAes128(data: ByteArray, key: HlsKey, sequence: Long, cache: MutableMap<String, ByteArray>): ByteArray {
        val keyUri = key.uri ?: throw IOException("key without URI")
        val keyBytes = cache[keyUri] ?: fetchBytes(keyUri).also {
            if (it.size != 16) throw IOException("bad key")
            cache[keyUri] = it
        }
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(keyBytes, "AES"), IvParameterSpec(key.iv ?: hlsSequenceIv(sequence)))
        return try {
            cipher.doFinal(data)
        } catch (e: java.security.GeneralSecurityException) {
            throw IOException("decrypt failed", e)
        }
    }

    private fun appendTo(file: File, bytes: ByteArray) {
        FileOutputStream(file, true).use { out: OutputStream -> out.write(bytes) }
    }

    private fun request(url: String): Request = Request.Builder().url(url).apply {
        headers.forEach { (name, value) -> runCatching { header(name, value) } }
    }.build()

    private fun open(url: String): Response = calls.newCall(request(url)).execute()

    private fun fetchText(url: String): String = calls.newCall(request(url)).execute().use { response ->
        if (!response.isSuccessful) throw IOException("HTTP ${response.code}")
        val body = response.body ?: throw IOException("empty playlist")
        if (body.contentLength() > MAX_PLAYLIST_BYTES) throw IOException("playlist too large")
        body.string()
    }

    private fun fetchBytes(url: String): ByteArray = calls.newCall(request(url)).execute().use { response ->
        if (!response.isSuccessful) throw IOException("HTTP ${response.code}")
        val body = response.body ?: throw IOException("empty segment")
        if (body.contentLength() > MAX_SEGMENT_BYTES) throw IOException("segment too large")
        body.bytes()
    }

    companion object {
        const val MAX_CONSECUTIVE_FAILURES = 8
        const val HEALTHY_STRETCH_MS = 60_000L
        private const val PEEK_BYTES = 64L
        private const val COPY_BUFFER = 64 * 1024
        private const val PROGRESS_EVERY_BYTES = 8L * 1024 * 1024
        private const val MAX_PLAYLIST_BYTES = 4L * 1024 * 1024
        private const val MAX_SEGMENT_BYTES = 200L * 1024 * 1024

        /** 2 s, 4 s, 8 s … capped at 30 s: a panel that drops a connection usually takes it back within seconds. */
        fun backoffMs(failures: Int): Long = (1_000L shl failures.coerceIn(1, 5)).coerceAtMost(30_000L)
    }
}

/** Kept apart so a cancelled recording is never reported as a failed one. */
internal fun Throwable.isRecordingCancellation(): Boolean = this is CancellationException
