package com.arflix.tv.music

import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import java.io.File
import java.time.LocalDate

/**
 * Charts and playlists for the Music home, from Deezer's public catalogue API: the top songs of
 * the country the user is in first, then rows made from what they listen to and search for, then
 * local genres and hundreds of mood, genre and decade playlists. Deezer updates its charts daily;
 * the less pinned rows also rotate by day.
 *
 * Only names, covers and track lists come from here. A song plays from the user's own sources:
 * its stream is a [MusicMatch] placeholder that the player resolves by searching those sources
 * when the song starts, so nothing is streamed from Deezer.
 */
class ChartsSource(
    private val http: OkHttpClient,
    private val language: String,
    private val cacheDir: File? = null,
    private val baseUrl: String = DEEZER_API,
    /** The device's country (see [DeviceCountry]); null falls back to the app language. */
    deviceCountry: String? = null,
    /** What the profile listened to and searched, read each time the home loads. */
    private val taste: suspend () -> MusicTaste = { MusicTaste() },
    private val now: () -> Long = System::currentTimeMillis,
    private val today: () -> LocalDate = { LocalDate.now() },
) : MusicSource {

    private val country = ChartCatalog.country(language, deviceCountry)
    private val personalCache = HashMap<String, List<MusicShelf>>()

    private val gate = Semaphore(4)
    private val trackCache = HashMap<String, List<MusicTrack>>()

    private fun url(vararg segments: String, query: Map<String, String> = emptyMap()): HttpUrl =
        baseUrl.toHttpUrl().newBuilder().apply {
            segments.forEach { addPathSegment(it) }
            query.forEach { (k, v) -> addQueryParameter(k, v) }
        }.build()

    /** Deezer answers errors with HTTP 200 and an "error" object; a quota error is worth one retry. */
    private suspend fun get(url: HttpUrl): JsonObject = gate.withPermit {
        repeat(2) { attempt ->
            val body = withContext(Dispatchers.IO) { MusicAddonSource.fetchJson(http, url) }
            val error = body.get("error")?.takeIf { it.isJsonObject }?.asJsonObject
            if (error == null) return@withPermit body
            if (attempt == 0 && error.int("code") == QUOTA_ERROR) delay(1_500) else throw MusicServerException(error.str("message") ?: "Charts unavailable")
        }
        throw MusicServerException("Charts unavailable")
    }

    override suspend fun shelves(): List<MusicShelf> = coroutineScope {
        val personal = async { runCatching { personalShelves() }.getOrDefault(emptyList()) }
        val charts = chartShelves()
        val mine = personal.await()
        val shown = mine.flatMapTo(HashSet()) { shelf -> shelf.albums.map { it.id } }
        val rest = charts.mapNotNull { shelf ->
            shelf.copy(albums = shelf.albums.filter { it.id !in shown }).takeIf { it.albums.size >= 3 }
        }
        // The country's chart and the charts row lead, then the user's own rows.
        rest.take(PERSONAL_AFTER) + mine + rest.drop(PERSONAL_AFTER)
    }

    private suspend fun chartShelves(): List<MusicShelf> {
        val date = today()
        val cacheFile = cacheDir?.let { File(it, "music-charts-$language-$country-$date.json") }
        cacheFile?.takeIf { it.exists() }?.let { file ->
            runCatching { decodeShelves(file.readText()) }.getOrNull()?.takeIf { it.isNotEmpty() }?.let { return it }
        }
        val rows = ChartCatalog.rows(language, date, country)
        val shelves = coroutineScope {
            rows.map { row ->
                async {
                    runCatching {
                        val playlists = if (row.query == null) get(url("chart", "0", "playlists", query = mapOf("limit" to "50"))).list("data")
                        else get(url("search", "playlist", query = mapOf("q" to row.query, "limit" to "25"))).list("data")
                        row to playlists.filter { (it.int("nb_tracks") ?: 0) >= MIN_TRACKS }.map { parsePlaylist(it) }
                    }.getOrNull()
                }
            }.mapNotNull { it.await() }
        }
        // A playlist shows once, in the first row that has it.
        val seen = HashSet<String>()
        val result = shelves.mapNotNull { (row, playlists) ->
            val fresh = playlists.filter { seen.add(it.id) }.take(MAX_PER_ROW)
            if (fresh.size < 3) null else MusicShelf(key = SHELF_PREFIX + row.key, title = row.title, albums = fresh)
        }
        if (result.isEmpty()) throw MusicServerException("Charts unavailable")
        cacheFile?.let { file ->
            runCatching {
                file.parentFile?.listFiles { f -> f.name.startsWith("music-charts-") && f.name != file.name }?.forEach { it.delete() }
                file.writeText(encodeShelves(result))
            }
        }
        return result
    }

    /**
     * Rows from the profile's taste: for a favourite artist, a mix of that artist and of the
     * artists Deezer lists as related (each mix plays a radio of similar songs); and playlists
     * for the user's last searches. Kept for the day per taste, so a reload does not ask again.
     */
    private suspend fun personalShelves(): List<MusicShelf> {
        val taste = taste()
        if (taste.isEmpty) return emptyList()
        val date = today()
        val artists = taste.artistsForToday(now(), date.toEpochDay())
        val searches = taste.recentSearches()
        val cacheKey = "$date|$artists|$searches"
        synchronized(personalCache) { personalCache[cacheKey] }?.let { return it }
        val titles = ChartCatalog.personalTitles(language)
        val result = coroutineScope {
            val artistRows = artists.map { name -> async { runCatching { artistRow(name, titles) }.getOrNull() } }
            val searchRow = async { runCatching { searchRow(searches, titles) }.getOrNull() }
            artistRows.mapNotNull { it.await() } + listOfNotNull(searchRow.await())
        }
        synchronized(personalCache) {
            personalCache.clear()
            personalCache[cacheKey] = result
        }
        return result
    }

    private suspend fun artistRow(name: String, titles: ChartCatalog.PersonalTitles): MusicShelf? {
        val wanted = SpotifyImport.normalize(name)
        val artist = get(url("search", "artist", query = mapOf("q" to name, "limit" to "5"))).list("data")
            .firstOrNull { SpotifyImport.normalize(it.str("name").orEmpty()) == wanted } ?: return null
        val id = artist.str("id") ?: return null
        val related = get(url("artist", id, "related", query = mapOf("limit" to "20"))).list("data")
        val mixes = (listOf(artist) + related).map { parseMix(it, titles.mix) }.distinctBy { it.id }
        if (mixes.size < 3) return null
        return MusicShelf(key = SHELF_PREFIX + PERSONAL + "artist:" + id, title = titles.because.format(artist.str("name") ?: name), albums = mixes)
    }

    private suspend fun searchRow(queries: List<String>, titles: ChartCatalog.PersonalTitles): MusicShelf? {
        if (queries.isEmpty()) return null
        val perQuery = coroutineScope {
            queries.map { q ->
                async {
                    runCatching {
                        get(url("search", "playlist", query = mapOf("q" to q, "limit" to "10"))).list("data")
                            .filter { (it.int("nb_tracks") ?: 0) >= MIN_TRACKS }.map { parsePlaylist(it) }
                    }.getOrDefault(emptyList())
                }
            }.map { it.await() }
        }
        // Taking turns, so every search shows near the front.
        val mixed = (0 until (perQuery.maxOfOrNull { it.size } ?: 0)).flatMap { i -> perQuery.mapNotNull { it.getOrNull(i) } }
            .distinctBy { it.id }.take(MAX_PER_ROW)
        if (mixed.size < 3) return null
        return MusicShelf(key = SHELF_PREFIX + PERSONAL + "searches", title = titles.searches, albums = mixed)
    }

    internal fun parseMix(o: JsonObject, subtitle: String) = MusicAlbum(
        id = MIX + o.str("id").orEmpty(),
        name = o.str("name").orEmpty(),
        artist = subtitle,
        artistId = null,
        coverUrl = o.str("picture_xl") ?: o.str("picture_big") ?: o.str("picture_medium"),
    )

    override suspend fun albums(shelf: AlbumShelf, size: Int): List<MusicAlbum> = emptyList()
    override suspend fun artists(): List<MusicArtist> = emptyList()
    override suspend fun artistAlbums(artistId: String): List<MusicAlbum> = emptyList()

    override suspend fun albumTracks(albumId: String): List<MusicTrack> {
        if (albumId.startsWith(MIX)) return mixTracks(albumId.removePrefix(MIX))
        val id = albumId.removePrefix(PLAYLIST)
        synchronized(trackCache) { trackCache[id] }?.let { return it }
        val tracks = mutableListOf<MusicTrack>()
        var index = 0
        while (tracks.size < MAX_TRACKS) {
            val body = get(url("playlist", id, "tracks", query = mapOf("limit" to "100", "index" to index.toString())))
            val page = body.list("data")
            tracks += page.map { parseTrack(it) }
            index += page.size
            if (page.isEmpty() || body.str("next") == null) break
        }
        val unique = tracks.distinctBy { it.id }
        synchronized(trackCache) { trackCache[id] = unique }
        return unique
    }

    /** An artist's mix: Deezer's radio of that artist and similar ones. */
    private suspend fun mixTracks(artistId: String): List<MusicTrack> {
        val key = MIX + artistId
        synchronized(trackCache) { trackCache[key] }?.let { return it }
        val tracks = get(url("artist", artistId, "radio", query = mapOf("limit" to "50"))).list("data")
            .map { parseTrack(it) }.distinctBy { it.id }
        synchronized(trackCache) { trackCache[key] = tracks }
        return tracks
    }

    /** Only playlists: songs from here would duplicate the ones the user's sources find themselves. */
    override suspend fun search(query: String): MusicSearchResult {
        val playlists = get(url("search", "playlist", query = mapOf("q" to query, "limit" to "25"))).list("data")
            .filter { (it.int("nb_tracks") ?: 0) >= MIN_TRACKS }
            .map { parsePlaylist(it) }
        return MusicSearchResult(playlists = playlists)
    }

    /** Deezer's radio of the seed's artist, without the seed itself; played from the user's sources. */
    override suspend fun radio(seed: MusicTrack): List<MusicTrack> {
        val name = MusicTaste.primaryArtist(seed.artist).ifBlank { return emptyList() }
        val wanted = SpotifyImport.normalize(name)
        val found = get(url("search", "artist", query = mapOf("q" to name, "limit" to "5"))).list("data")
        val artist = found.firstOrNull { SpotifyImport.normalize(it.str("name").orEmpty()) == wanted } ?: return emptyList()
        val title = SpotifyImport.normalize(seed.title)
        return mixTracks(artist.str("id") ?: return emptyList())
            .filter { SpotifyImport.normalize(it.title) != title }
            .take(RADIO_SIZE)
    }

    override suspend fun reportPlayed(trackId: String) = Unit

    internal fun parsePlaylist(o: JsonObject) = MusicAlbum(
        id = PLAYLIST + o.str("id").orEmpty(),
        name = o.str("title").orEmpty(),
        artist = o.obj("user").str("name") ?: o.obj("creator").str("name").orEmpty(),
        artistId = null,
        coverUrl = o.str("picture_xl") ?: o.str("picture_big") ?: o.str("picture_medium"),
        trackCount = o.int("nb_tracks"),
    )

    internal fun parseTrack(o: JsonObject): MusicTrack {
        val album = o.obj("album")
        val title = o.str("title_short") ?: o.str("title").orEmpty()
        val artist = o.obj("artist").str("name").orEmpty()
        val seconds = o.int("duration") ?: 0
        return MusicTrack(
            id = TRACK + o.str("id").orEmpty(),
            title = title,
            artist = artist,
            album = album.str("title").orEmpty(),
            albumId = null,
            durationSec = seconds,
            coverUrl = album.str("cover_xl") ?: album.str("cover_big") ?: album.str("cover_medium"),
            streamUrl = MusicMatch.uri(title, artist, seconds),
        )
    }

    private fun encodeShelves(shelves: List<MusicShelf>): String = com.google.gson.Gson().toJson(shelves)

    @Suppress("SENSELESS_COMPARISON")
    private fun decodeShelves(json: String): List<MusicShelf> =
        com.google.gson.Gson().fromJson(json, Array<MusicShelf>::class.java).orEmpty()
            .filter { it.key != null && it.albums != null }

    companion object {
        const val DEEZER_API = "https://api.deezer.com"
        const val KEY = "charts"
        const val SHELF_PREFIX = "charts:"
        internal const val PLAYLIST = "playlist:"
        internal const val MIX = "mix:"
        /** Shelf keys of the rows made from the profile's taste ("charts:you:searches"). */
        internal const val PERSONAL = "you:"
        private const val PERSONAL_AFTER = 2
        internal const val TRACK = "deezer:"
        private const val QUOTA_ERROR = 4
        private const val MIN_TRACKS = 8
        private const val MAX_PER_ROW = 20
        private const val MAX_TRACKS = 300
        private const val RADIO_SIZE = 25

        /** Whether a composite shelf key ("2/charts:top") is one of these rows. */
        fun isChartShelf(key: String) = key.substringAfter('/').startsWith(SHELF_PREFIX)

        /** Whether a composite shelf key is one of the rows made from the profile's taste. */
        fun isPersonalShelf(key: String) = key.substringAfter('/').startsWith(SHELF_PREFIX + PERSONAL)
    }
}
