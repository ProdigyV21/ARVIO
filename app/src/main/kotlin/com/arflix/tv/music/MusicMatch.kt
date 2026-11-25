package com.arflix.tv.music

import java.net.URLEncoder
import java.io.IOException

/** No source of the user's has this song; the player skips to the next one. */
class MusicMatchMissingException(title: String) : IOException("Not in your sources: $title")

/**
 * A song known only by name (from the charts), played from whichever of the user's own sources
 * has it. Its stream is a placeholder until it starts; then the sources are searched, the best fit
 * is taken (the same scoring as the Spotify import) and its stream played.
 */
object MusicMatch {
    const val SCHEME = "arvio-music-match"

    fun uri(title: String, artist: String, durationSec: Int): String =
        "$SCHEME://track?t=${encode(title)}&a=${encode(artist)}&d=$durationSec"

    private fun encode(text: String) = URLEncoder.encode(text, "UTF-8").replace("+", "%20")

    private val cache = object : LinkedHashMap<String, MusicTrack>(64, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, MusicTrack>?) = size > 200
    }

    /** The user's own track for a placeholder; blocking, for the player's loading thread. */
    suspend fun find(source: MusicSource, title: String, artist: String, durationSec: Int): MusicTrack {
        val key = "$artist\u0000$title"
        synchronized(cache) { cache[key] }?.let { return it }
        val wanted = ImportedTrack(title, artist, durationMs = durationSec.takeIf { it > 0 }?.times(1000L))
        val query = listOf(artist, title).filter { it.isNotBlank() }.joinToString(" ")
        val found = source.search(query).tracks.filterNot { it.streamUrl.startsWith("$SCHEME:") }
        val best = found.maxByOrNull { SpotifyImport.score(wanted, it) }
            ?.takeIf { SpotifyImport.score(wanted, it) >= SpotifyImport.MATCH_THRESHOLD }
            ?: throw MusicMatchMissingException(title)
        synchronized(cache) { cache[key] = best }
        return best
    }
}
