package com.arflix.tv.ui.screens.player.subtitles

import android.util.Log
import org.json.JSONArray
import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * Translations survive the session: re-opening an episode, seeking back, switching tracks or
 * re-watching never pays for the same line twice. One append-only file of
 * `["<target language>\u0001<source>", "<translation>"]` JSON lines in the app cache dir; when
 * it outgrows [MAX_BYTES] the newest half is kept.
 */
class TranslationDiskCache(dir: File) {
    private val file = File(dir, "ai_translation_cache.jsonl")
    private val map = ConcurrentHashMap<String, String>()
    @Volatile private var loaded = false

    @Synchronized
    fun load() {
        if (loaded) return
        loaded = true
        runCatching {
            if (!file.isFile) return
            if (file.length() > MAX_BYTES) compact()
            file.forEachLine(Charsets.UTF_8) { line ->
                runCatching {
                    val a = JSONArray(line)
                    map[a.getString(0)] = a.getString(1)
                }
            }
        }.onFailure { Log.w(TAG, "load failed: ${it.message}") }
    }

    fun get(key: String): String? = map[key]

    fun putAll(entries: Map<String, String>) {
        if (entries.isEmpty()) return
        val fresh = entries.filter { (k, v) -> map[k] != v }
        if (fresh.isEmpty()) return
        map.putAll(fresh)
        synchronized(this) {
            runCatching {
                file.parentFile?.mkdirs()
                file.appendText(
                    fresh.entries.joinToString("") { (k, v) -> JSONArray().put(k).put(v).toString() + "\n" },
                    Charsets.UTF_8
                )
            }.onFailure { Log.w(TAG, "write failed: ${it.message}") }
        }
    }

    private fun compact() {
        val lines = file.readLines(Charsets.UTF_8)
        val keep = lines.takeLast(lines.size / 2)
        file.writeText(keep.joinToString("\n", postfix = "\n"), Charsets.UTF_8)
    }

    companion object {
        private const val TAG = "TranslationDiskCache"
        private const val MAX_BYTES = 8L * 1024 * 1024
    }
}
