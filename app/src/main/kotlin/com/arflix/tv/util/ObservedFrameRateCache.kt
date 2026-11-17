package com.arflix.tv.util

import android.content.Context
import android.util.AtomicFile
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.File
import java.security.MessageDigest
import java.util.Locale

internal data class ObservedFrameRate(val key: String?, val fps: Float, val observedAt: Long)

internal interface FrameRateStorage {
    fun read(): List<ObservedFrameRate>
    fun write(entries: List<ObservedFrameRate>)
}

internal class ObservedFrameRateCache(
    private val storage: FrameRateStorage? = null,
    private val clock: () -> Long = System::currentTimeMillis,
    private val capacity: Int = 128,
) : AutoCloseable {
    private val entries = LinkedHashMap<String, ObservedFrameRate>(16, 0.75f, true)
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val io = Mutex()
    private var pendingWrite: Job? = null

    fun start() { scope.launch { load() } }

    suspend fun load() = io.withLock {
        val restored = runCatching { storage?.read().orEmpty() }.getOrDefault(emptyList())
        synchronized(this) {
            restored.filter(::valid).takeLast(capacity).forEach { entry ->
                entry.key?.let { if (!entries.containsKey(it)) entries[it] = entry }
            }
            trim()
        }
    }

    @Synchronized fun get(key: String): Float? {
        val entry = entries[key] ?: return null
        if (!valid(entry)) { entries.remove(key); return null }
        return entry.fps
    }

    @Synchronized fun record(key: String, fps: Float) {
        val entry = ObservedFrameRate(key, fps, clock())
        if (!valid(entry)) return
        entries[key] = entry
        trim()
        if (storage != null) {
            pendingWrite?.cancel()
            pendingWrite = scope.launch { delay(500L); flush() }
        }
    }

    suspend fun flush() = io.withLock {
        val snapshot = synchronized(this) { entries.values.filter(::valid).toList() }
        runCatching { storage?.write(snapshot) }
        Unit
    }

    private fun valid(entry: ObservedFrameRate): Boolean =
        entry.key?.matches(Regex("[a-f0-9]{64}")) == true && entry.fps.isFinite() && entry.fps in 10f..120f &&
            clock() - entry.observedAt in 0L..(7L * 24 * 60 * 60 * 1000)

    private fun trim() { while (entries.size > capacity) entries.remove(entries.keys.first()) }
    override fun close() { scope.cancel() }
}

/** Hash the complete request unless a specific file identity is available. Never persist credentials. */
internal fun observedFrameRateKey(
    url: String, headers: Map<String, String>, filename: String?, fileSize: Long?, contentId: String, addonId: String,
): String? {
    val uri = kotlin.runCatching { java.net.URI(url) }.getOrNull() ?: return null
    if (uri.scheme?.lowercase(Locale.ROOT) !in listOf("http", "https")) return null
    val path = uri.path.orEmpty().lowercase(Locale.ROOT)
    if (path.endsWith(".m3u8") || path.endsWith(".mpd") || path.endsWith(".ts") || path.contains("/live/")) return null
    val file = filename?.trim()?.takeIf { it.isNotEmpty() && it.length <= 4096 }
    val identity = if (file != null && fileSize != null && fileSize > 0 && contentId.isNotBlank()) {
        listOf("file-v1", contentId, addonId, file, fileSize.toString()).joinToString("\u0000")
    } else listOf("request-v1", contentId, addonId, url).joinToString("\u0000")
    val canonicalHeaders = headers.entries.filterNot { it.key.equals("Range", true) }
        .sortedBy { it.key.lowercase(Locale.ROOT) }
        .joinToString("\u0000") { "${it.key.lowercase(Locale.ROOT)}=${it.value}" }
    return MessageDigest.getInstance("SHA-256").digest("$identity\u0000$canonicalHeaders".toByteArray(Charsets.UTF_8))
        .joinToString("") { "%02x".format(it.toInt() and 0xff) }
}

internal object PlaybackFrameRates {
    @Volatile private var cache: ObservedFrameRateCache? = null
    fun get(context: Context): ObservedFrameRateCache = cache ?: synchronized(this) {
        cache ?: ObservedFrameRateCache(object : FrameRateStorage {
            private val file = AtomicFile(File(context.applicationContext.filesDir, "observed-frame-rates-v1.json"))
            private val gson = Gson()
            override fun read(): List<ObservedFrameRate> {
                if (file.baseFile.length() > 128 * 1024) return emptyList()
                return file.openRead().bufferedReader().use { reader ->
                    gson.fromJson(reader, Array<ObservedFrameRate>::class.java)?.filterNotNull().orEmpty()
                }
            }
            override fun write(entries: List<ObservedFrameRate>) {
                val output = file.startWrite()
                try {
                    output.write(gson.toJson(entries).toByteArray(Charsets.UTF_8))
                    file.finishWrite(output)
                } catch (error: Exception) {
                    file.failWrite(output)
                    throw error
                }
            }
        }).also { cache = it; it.start() }
    }
}
