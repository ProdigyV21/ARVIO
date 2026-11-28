package com.arflix.tv.music

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient

/**
 * Some music addons only search in Latin letters: a band typed in Hebrew ("משינה") comes back as
 * an unrelated list. For a query in another script, Deezer's catalogue, which knows both spellings,
 * gives the artist's Latin name ("Mashina"), and the user's sources are searched with it too. A song
 * typed by its title gives its singer, so the song is found among that singer's songs.
 * Queries in Latin letters never get here.
 */
object MusicNameLookup {

    /** True when the query has letters outside the Latin script, e.g. Hebrew, Cyrillic or Arabic. */
    fun needsLookup(query: String): Boolean =
        query.any { it.isLetter() && Character.UnicodeScript.of(it.code) != Character.UnicodeScript.LATIN }

    private fun isLatin(name: String): Boolean = name.any { it.isLetter() } && !needsLookup(name)

    /** True when [artist] (which may list several, "A, B") is [name]. */
    internal fun isArtist(artist: String, name: String): Boolean {
        val n = SpotifyImport.normalize(name)
        return n.isNotEmpty() && " ${SpotifyImport.normalize(artist)} ".contains(" $n ")
    }

    /**
     * Up to [MAX_NAMES] Latin artist names Deezer has for [query]: the artist it finds, and the
     * singers of songs titled exactly [query]. Names Deezer only has in the query's own script are useless here.
     */
    suspend fun latinNames(http: OkHttpClient, query: String, baseUrl: String = ChartsSource.DEEZER_API): List<String> = coroutineScope {
        fun search(kind: String) = async {
            runCatching {
                val url = baseUrl.toHttpUrl().newBuilder().addPathSegment("search").addPathSegment(kind)
                    .addQueryParameter("q", query).addQueryParameter("limit", "5").build()
                withContext(Dispatchers.IO) { MusicAddonSource.fetchJson(http, url) }.list("data")
            }.getOrDefault(emptyList())
        }
        val artists = search("artist")
        val tracks = search("track")
        val q = SpotifyImport.normalize(query)
        val artist = artists.await().take(1).mapNotNull { it.str("name") }
        val singers = tracks.await().filter { SpotifyImport.normalize(it.str("title").orEmpty()) == q }
            .mapNotNull { it.obj("artist").str("name") }
        (artist + singers).filter { isLatin(it) }.distinctBy { SpotifyImport.normalize(it) }.take(MAX_NAMES)
    }

    /**
     * [source]'s results for [query]; for a query in another script, also its results for the
     * Latin [names] of the artist, the ones by that artist first.
     */
    suspend fun search(source: MusicSource, query: String, names: suspend (String) -> List<String>): MusicSearchResult = coroutineScope {
        val direct = async { runCatching { source.search(query) }.getOrDefault(MusicSearchResult()) }
        if (!needsLookup(query)) return@coroutineScope direct.await()
        val aliases = runCatching { names(query) }.getOrDefault(emptyList())
        val byName = aliases.map { name -> async { name to runCatching { source.search(name) }.getOrDefault(MusicSearchResult()) } }
            .map { it.await() }
        merge(direct.await(), byName)
    }

    /** Only what is by the named artist is kept from a by-name search, so its other hits don't crowd the list. */
    internal fun merge(direct: MusicSearchResult, byName: List<Pair<String, MusicSearchResult>>): MusicSearchResult {
        if (byName.isEmpty()) return direct
        val artists = byName.flatMap { (name, r) -> r.artists.filter { SpotifyImport.normalize(it.name) == SpotifyImport.normalize(name) } }
        val albums = byName.flatMap { (name, r) -> r.albums.filter { isArtist(it.artist, name) } }
        val tracks = byName.flatMap { (name, r) -> r.tracks.filter { isArtist(it.artist, name) } }
        return MusicSearchResult(
            artists = (artists + direct.artists).distinctBy { it.id },
            albums = (albums + direct.albums).distinctBy { it.id },
            tracks = (tracks + direct.tracks).distinctBy { it.id },
            playlists = direct.playlists,
            aliases = byName.map { it.first },
        )
    }

    private const val MAX_NAMES = 2
}
