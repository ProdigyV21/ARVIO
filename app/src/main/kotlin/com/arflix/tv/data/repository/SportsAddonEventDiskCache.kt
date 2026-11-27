package com.arflix.tv.data.repository

import android.util.AtomicFile
import com.arflix.tv.data.model.SportsAddonEvent
import com.arflix.tv.data.model.SportsAddonEventSnapshot
import com.google.gson.Gson
import java.io.File
import java.util.zip.Deflater
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

/**
 * The last sports add-on schedule, so Sports shows games straight away after the app
 * starts instead of waiting for every catalog page again. Live flags expire by
 * themselves (SportsAddonEvent.isLive checks observedAt), so an older copy never claims
 * a game is live; the fresh load replaces it within seconds.
 */
internal class SportsAddonEventDiskCache(file: File) {
    private val atomic = AtomicFile(file)
    private val gson = Gson()

    fun read(installations: Set<String>, now: Long = System.currentTimeMillis()): List<SportsAddonEvent> = synchronized(lock) {
        val file = atomic.baseFile
        if (!file.exists() || file.length() > MAX_BYTES) return@synchronized emptyList()
        runCatching {
            val snapshot = GZIPInputStream(atomic.openRead()).bufferedReader().use { gson.fromJson(it, SportsAddonEventSnapshot::class.java) }
            if (snapshot == null || now - snapshot.savedAt !in 0..TTL_MS) return@runCatching emptyList()
            // Only events of add-ons that are still installed, and nothing long over.
            val events = snapshot.events.orEmpty().filter { event ->
                event.installation in installations && (event.startsAt == null || now - event.startsAt < STALE_EVENT_MS)
            }
            System.err.println("[Sports-AddonCache] restored ${events.size} of ${snapshot.events.orEmpty().size}")
            events
        }.onFailure { System.err.println("[Sports-AddonCache] read failed: ${it.javaClass.simpleName}") }.getOrDefault(emptyList())
    }

    fun write(installations: Set<String>, events: List<SportsAddonEvent>) = synchronized(lock) {
        if (events.isEmpty()) return@synchronized
        val output = atomic.startWrite()
        try {
            val compressed = object : GZIPOutputStream(output) {
                init { def.setLevel(Deflater.BEST_SPEED) }
            }
            val writer = compressed.bufferedWriter()
            gson.toJson(SportsAddonEventSnapshot(installations.sorted(), System.currentTimeMillis(), events), writer)
            writer.flush()
            compressed.finish()
            atomic.finishWrite(output)
            System.err.println("[Sports-AddonCache] saved ${events.size}")
        } catch (error: Exception) {
            atomic.failWrite(output)
            throw error
        }
    }

    private companion object {
        val lock = Any()
        const val TTL_MS = 12 * 60 * 60_000L
        const val STALE_EVENT_MS = 8 * 60 * 60_000L
        const val MAX_BYTES = 8_000_000L
    }
}
