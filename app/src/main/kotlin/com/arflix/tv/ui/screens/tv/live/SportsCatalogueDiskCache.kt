package com.arflix.tv.ui.screens.tv.live

import android.util.AtomicFile
import com.google.gson.Gson
import java.io.File
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream
import java.util.zip.Deflater

/** Short-lived, complete results only. Never reused for another profile or playlist snapshot. */
internal class SportsCatalogueDiskCache(file: File) {
    private val atomic = AtomicFile(file)
    private val gson = Gson()

    fun read(expected: SportsScheduleKey, now: Long = System.currentTimeMillis()): List<SportsGuideEvent> = synchronized(lock) {
        val file = atomic.baseFile
        if (!file.exists() || file.length() > MAX_BYTES || now - file.lastModified() !in 0..TTL_MS) {
            System.err.println("[Sports-Cache] miss exists=${file.exists()} bytes=${file.length()} age=${now - file.lastModified()}")
            return@synchronized emptyList()
        }
        runCatching {
            val snapshot = GZIPInputStream(atomic.openRead()).bufferedReader().use { gson.fromJson(it, SportsScheduleSnapshot::class.java) }
            val key = snapshot.key
            if (key.sameCatalogueScope(expected)) snapshot.events else emptyList()
        }.onFailure { System.err.println("[Sports-Cache] read failed: ${it.javaClass.simpleName}") }.getOrDefault(emptyList())
    }

    fun write(snapshot: SportsScheduleSnapshot) = synchronized(lock) {
        val output = atomic.startWrite()
        try {
            val compressed = object : GZIPOutputStream(output) {
                init { def.setLevel(Deflater.BEST_SPEED) }
            }
            val writer = compressed.bufferedWriter()
            gson.toJson(snapshot, writer)
            writer.flush()
            compressed.finish()
            atomic.finishWrite(output)
            System.err.println("[Sports-Cache] saved events=${snapshot.events.size} bytes=${atomic.baseFile.length()}")
        } catch (error: Exception) {
            atomic.failWrite(output)
            throw error
        }
    }

    private companion object {
        val lock = Any()
        const val TTL_MS = 120_000L
        const val MAX_BYTES = 12_000_000L
    }
}
