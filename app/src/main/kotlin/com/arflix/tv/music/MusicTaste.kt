package com.arflix.tv.music

import kotlin.math.pow
import kotlin.random.Random

data class MusicTasteArtist(val name: String, val plays: Int = 1, val lastPlayed: Long = 0)
data class MusicTasteSearch(val query: String, val at: Long = 0)

/**
 * What the user listens to and looks for, per profile and synced: the artists of the songs they
 * heard (with a play count) and their recent music searches. The Music home turns it into
 * "Because you listened to ..." and "Based on your searches" rows.
 *
 * Plain data with pure updates, so the rules are tested without a device.
 */
data class MusicTaste(
    val artists: List<MusicTasteArtist> = emptyList(),
    val searches: List<MusicTasteSearch> = emptyList(),
) {
    val isEmpty: Boolean get() = artists.isEmpty() && searches.isEmpty()

    fun recordPlay(artist: String, now: Long): MusicTaste {
        val name = primaryArtist(artist)
        if (name.isEmpty()) return this
        val key = SpotifyImport.normalize(name)
        val existing = artists.firstOrNull { SpotifyImport.normalize(it.name) == key }
        val updated = MusicTasteArtist(name, (existing?.plays ?: 0) + 1, now)
        val rest = artists.filter { it !== existing }
        // The list keeps the artists that matter most now; a long-unplayed one makes room.
        val kept = (listOf(updated) + rest).sortedByDescending { score(it, now) }.take(MAX_ARTISTS)
        return copy(artists = kept)
    }

    fun recordSearch(query: String, now: Long): MusicTaste {
        val trimmed = query.trim().replace(Regex("\\s+"), " ")
        if (trimmed.length < MIN_QUERY) return this
        val key = SpotifyImport.normalize(trimmed)
        // "dua", "dua l", "dua lipa": a longer search of the same start replaces the shorter one.
        val rest = searches.filter { SpotifyImport.normalize(it.query).let { old -> old != key && !key.startsWith(old) } }
        return copy(searches = (listOf(MusicTasteSearch(trimmed, now)) + rest).take(MAX_SEARCHES))
    }

    /** The artists to recommend from today: the favourite always, the rest change by day. */
    fun artistsForToday(now: Long, day: Long, count: Int = 2): List<String> {
        val ranked = artists.sortedByDescending { score(it, now) }.map { it.name }
        if (ranked.isEmpty()) return emptyList()
        val others = ranked.drop(1).take(ROTATION_POOL).shuffled(Random(day))
        return (listOf(ranked.first()) + others).take(count)
    }

    fun recentSearches(count: Int = 3): List<String> = searches.take(count).map { it.query }

    /** Gson leaves fields that are missing from older saves null, whatever Kotlin says. */
    @Suppress("SENSELESS_COMPARISON", "USELESS_ELVIS")
    fun sanitized(): MusicTaste = MusicTaste(
        artists = (artists ?: emptyList()).filter { it != null && !it.name.isNullOrBlank() },
        searches = (searches ?: emptyList()).filter { it != null && !it.query.isNullOrBlank() },
    )

    companion object {
        private const val MAX_ARTISTS = 40
        private const val MAX_SEARCHES = 15
        private const val MIN_QUERY = 3
        private const val ROTATION_POOL = 5
        private const val HALF_LIFE_DAYS = 21.0

        /** Plays count less as they age: one play three weeks ago weighs half a play today. */
        fun score(artist: MusicTasteArtist, now: Long): Double {
            val days = ((now - artist.lastPlayed).coerceAtLeast(0L)) / 86_400_000.0
            return artist.plays * 0.5.pow(days / HALF_LIFE_DAYS)
        }

        /** "Artist feat. Guest" and "A, B" count for their first artist; "Simon & Garfunkel" stays whole. */
        fun primaryArtist(artist: String): String =
            artist.split(Regex("(?i)\\s+(feat\\.?|ft\\.?|featuring)\\s+|\\s*[,;/]\\s*")).first().trim()
    }
}
