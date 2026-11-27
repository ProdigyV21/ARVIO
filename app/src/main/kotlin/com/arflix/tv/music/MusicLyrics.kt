package com.arflix.tv.music

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.math.abs

/** One line of synced lyrics: shown from [timeMs] until the next line starts. */
data class LyricLine(val timeMs: Long, val text: String)

/** A song's lyrics: [synced] lines that follow the song, or only [plain] text. */
data class MusicLyrics(
    val synced: List<LyricLine> = emptyList(),
    val plain: String? = null,
    val instrumental: Boolean = false,
) {
    val isEmpty: Boolean get() = synced.isEmpty() && plain.isNullOrBlank() && !instrumental

    /** The line being sung at [positionMs]; -1 before the first. */
    fun activeIndex(positionMs: Long): Int {
        var low = 0
        var high = synced.lastIndex
        var found = -1
        while (low <= high) {
            val mid = (low + high) ushr 1
            if (synced[mid].timeMs <= positionMs) { found = mid; low = mid + 1 } else high = mid - 1
        }
        return found
    }
}

/** Reads LRC text: "[01:02.34] line", several time tags on one line, and an [offset:+/-ms] tag. */
object Lrc {
    private val TIME = Regex("\\[(\\d{1,3}):(\\d{1,2})(?:[.:](\\d{1,3}))?]")
    private val OFFSET = Regex("\\[offset:\\s*([+-]?\\d+)\\s*]", RegexOption.IGNORE_CASE)

    fun parse(text: String): List<LyricLine> {
        // A positive offset shows the lines earlier.
        val offset = OFFSET.find(text)?.groupValues?.get(1)?.toLongOrNull() ?: 0L
        val lines = mutableListOf<LyricLine>()
        text.lineSequence().forEach { raw ->
            var rest = raw.trim()
            val times = mutableListOf<Long>()
            while (true) {
                val match = TIME.matchAt(rest, 0) ?: break
                val (min, sec, frac) = match.destructured
                val ms = if (frac.isEmpty()) 0L else frac.padEnd(3, '0').take(3).toLong()
                times += min.toLong() * 60_000L + sec.toLong() * 1_000L + ms
                rest = rest.substring(match.value.length).trimStart()
            }
            times.forEach { lines += LyricLine((it - offset).coerceAtLeast(0L), rest.trim()) }
        }
        return lines.sortedBy { it.timeMs }
    }
}

/**
 * Lyrics from LRCLIB (lrclib.net), a free and open lyrics database that asks for no key. A song is
 * looked up by its name, artist, album and length, then by a search when that finds nothing.
 */
class LyricsSource(
    private val http: OkHttpClient,
    private val baseUrl: String = LRCLIB,
) {
    private val cache = object : LinkedHashMap<String, MusicLyrics>(32, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, MusicLyrics>?) = size > 50
    }

    /** The song's lyrics; empty when LRCLIB has none. Throws only when LRCLIB cannot be reached. */
    suspend fun find(track: MusicTrack): MusicLyrics {
        val key = "${track.artist}\u0000${track.title}\u0000${track.durationSec}"
        synchronized(cache) { cache[key] }?.let { return it }
        val title = cleanTitle(track.title)
        val artist = MusicTaste.primaryArtist(track.artist)
        val exact = if (track.durationSec > 0) {
            fetch(
                url("get", "track_name" to title, "artist_name" to artist, "album_name" to track.album, "duration" to track.durationSec.toString())
            )?.takeIf { it.isJsonObject }?.asJsonObject?.let(::parse)
        } else null
        val result = exact?.takeUnless { it.isEmpty }
            ?: (fetch(url("search", "track_name" to title, "artist_name" to artist)) as? JsonArray)
                ?.let { pickBest(it.mapNotNull { e -> e.takeIf { it.isJsonObject }?.asJsonObject }, track.durationSec) }
            ?: MusicLyrics()
        synchronized(cache) { cache[key] = result }
        return result
    }

    private fun url(path: String, vararg params: Pair<String, String>): HttpUrl =
        baseUrl.toHttpUrl().newBuilder().addPathSegment("api").addPathSegment(path).apply {
            params.filter { it.second.isNotBlank() }.forEach { (k, v) -> addQueryParameter(k, v) }
        }.build()

    /** The JSON at [url], or null for "not found". */
    private suspend fun fetch(url: HttpUrl): JsonElement? = withContext(Dispatchers.IO) {
        val request = Request.Builder().url(url)
            .header("User-Agent", USER_AGENT)
            .header("Accept", "application/json")
            .get().build()
        http.newCall(request).execute().use { response ->
            if (response.code == 404) return@use null
            if (!response.isSuccessful) throw MusicServerException("Lyrics: HTTP ${response.code}")
            runCatching { JsonParser.parseString(response.body?.string().orEmpty()) }.getOrNull()
        }
    }

    companion object {
        const val LRCLIB = "https://lrclib.net"
        private const val USER_AGENT = "ARVIO (https://github.com/ProdigyV21/ARVIO)"

        internal fun parse(o: JsonObject): MusicLyrics = MusicLyrics(
            synced = o.str("syncedLyrics")?.let(Lrc::parse).orEmpty(),
            plain = o.str("plainLyrics")?.takeIf { it.isNotBlank() },
            instrumental = o.get("instrumental")?.takeIf { it.isJsonPrimitive }?.asBoolean == true,
        )

        /** Synced lyrics of about the right length first, then plain ones. */
        internal fun pickBest(results: List<JsonObject>, durationSec: Int): MusicLyrics? {
            val fits = results.filter { o ->
                val d = o.get("duration")?.takeIf { it.isJsonPrimitive }?.asDouble ?: return@filter durationSec <= 0
                durationSec <= 0 || abs(d - durationSec) <= 4
            }
            val ranked = fits.sortedByDescending { if (!it.str("syncedLyrics").isNullOrBlank()) 2 else if (!it.str("plainLyrics").isNullOrBlank()) 1 else 0 }
            return ranked.firstOrNull()?.let(::parse)?.takeUnless { it.isEmpty }
        }

        /** "Song - Remastered 2011" and "Song (feat. X)" are filed under "Song". */
        internal fun cleanTitle(title: String): String =
            title.replace(Regex("(?i)\\s*[(\\[](feat\\.?|ft\\.?|featuring|with)\\s[^)\\]]*[)\\]]"), "")
                .replace(Regex("(?i)\\s+-\\s+.*(remaster|version|edit|mix|live|mono|stereo).*$"), "")
                .trim()
                .ifEmpty { title }
    }
}
