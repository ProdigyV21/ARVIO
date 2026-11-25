package com.arflix.tv.music

import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient

/**
 * A music addon in the Eclipse addon format, so addons written for that format work in ARVIO
 * too. ARVIO only speaks the protocol; which addon the user installs is up to them.
 *
 * - `manifest.json`: `resources` such as "search", "stream", "catalog"; optional `catalogs`
 *   (`id`, `type`, `name`) for home rows.
 * - `search?q=`: `tracks`, `albums`, `artists`, `playlists`.
 * - `stream/{id}`: `{ "url": ... }`, asked for only when a track plays (unless the track
 *   already carries a `streamURL`).
 * - `album/{id}`, `playlist/{id}`: the item with its `tracks`; `artist/{id}`: `albums`, `topTracks`.
 * - `catalog/{id}?skip=0`: `items`, each with a `type`.
 *
 * Albums from this source carry their kind in the id ("album:", "playlist:", "catalog:"), so
 * opening one knows which endpoint to ask; a catalog of tracks shows as one album-like card.
 */
class EclipseAddonSource(
    private val http: OkHttpClient,
    val addon: MusicAddon,
) : MusicSource {

    private fun url(vararg segments: String, query: Map<String, String> = emptyMap()): HttpUrl =
        addon.baseUrl.toHttpUrl().newBuilder().apply {
            segments.forEach { addPathSegment(it) }
            query.forEach { (k, v) -> addQueryParameter(k, v) }
        }.build()

    private suspend fun get(url: HttpUrl): JsonObject = withContext(Dispatchers.IO) { MusicAddonSource.fetchJson(http, url) }

    private suspend fun catalogItems(catalogId: String): List<JsonObject> =
        get(url("catalog", catalogId, query = mapOf("skip" to "0"))).list("items")

    override suspend fun shelves(): List<MusicShelf> = coroutineScope {
        addon.catalogs.filter { !it.searchOnly && it.type != "artist" }.map { catalog ->
            async {
                runCatching {
                    val items = catalogItems(catalog.id)
                    val albums = if (catalog.type == "track") {
                        // A row of songs becomes one card that opens (and plays) the whole row.
                        listOfNotNull(items.firstOrNull()?.let { first ->
                            MusicAlbum(
                                id = CATALOG + catalog.id, name = catalog.name, artist = addon.name, artistId = null,
                                coverUrl = first.str("artworkURL"), trackCount = items.size,
                            )
                        })
                    } else {
                        items.filter { it.str("type") == "album" || it.str("type") == "playlist" }.map { parseAlbum(it, it.str("type")) }
                    }
                    MusicShelf(key = "${addon.id}/${catalog.id}", title = catalog.name, albums = albums)
                }.getOrNull()
            }
        }.mapNotNull { it.await() }.filter { it.albums.isNotEmpty() }
    }

    override suspend fun albums(shelf: AlbumShelf, size: Int): List<MusicAlbum> =
        if (shelf == AlbumShelf.ALPHABETICAL) {
            shelves().flatMap { it.albums }.filterNot { it.id.startsWith(CATALOG) }
                .distinctBy { it.id }.sortedBy { it.name.lowercase() }
        } else emptyList()

    override suspend fun artists(): List<MusicArtist> = coroutineScope {
        addon.catalogs.filter { it.type == "artist" }.map { catalog ->
            async { runCatching { catalogItems(catalog.id).map { parseArtist(it) } }.getOrDefault(emptyList()) }
        }.flatMap { it.await() }.distinctBy { it.id }
    }

    override suspend fun artistAlbums(artistId: String): List<MusicAlbum> =
        get(url("artist", artistId)).list("albums").map { parseAlbum(it, "album") }

    override suspend fun albumTracks(albumId: String): List<MusicTrack> = when {
        albumId.startsWith(CATALOG) -> {
            val id = albumId.removePrefix(CATALOG)
            val name = addon.catalogs.firstOrNull { it.id == id }?.name.orEmpty()
            catalogItems(id).filter { it.str("type") == null || it.str("type") == "track" }
                .mapIndexed { i, o -> parseTrack(o, null, i).let { t -> if (t.album.isEmpty()) t.copy(album = name) else t } }
        }
        else -> {
            val playlist = albumId.startsWith(PLAYLIST)
            val id = albumId.removePrefix(PLAYLIST).removePrefix(ALBUM)
            val body = get(url(if (playlist) "playlist" else "album", id))
            val album = parseAlbum(body, if (playlist) "playlist" else "album")
            // A playlist's songs come from many albums, so they keep their own covers and numbering.
            body.list("tracks").mapIndexed { i, o -> parseTrack(o, if (playlist) null else album, i) }
        }
    }

    override suspend fun search(query: String): MusicSearchResult {
        val body = get(url("search", query = mapOf("q" to query)))
        return MusicSearchResult(
            artists = body.list("artists").map { parseArtist(it) },
            albums = body.list("albums").map { parseAlbum(it, "album") },
            playlists = body.list("playlists").map { parseAlbum(it, "playlist") },
            tracks = body.list("tracks").map { parseTrack(it, null, null) },
        )
    }

    override suspend fun reportPlayed(trackId: String) = Unit

    internal fun parseAlbum(o: JsonObject, type: String?): MusicAlbum {
        val prefix = if (type == "playlist") PLAYLIST else ALBUM
        return MusicAlbum(
            id = prefix + (o.str("id").orEmpty().removePrefix(PLAYLIST).removePrefix(ALBUM)),
            name = o.str("title") ?: o.str("name").orEmpty(),
            artist = o.str("artist") ?: o.str("creator").orEmpty(),
            artistId = o.str("artistId"),
            coverUrl = o.str("artworkURL"),
            year = o.str("year")?.take(4)?.toIntOrNull(),
            trackCount = o.int("trackCount"),
        )
    }

    internal fun parseArtist(o: JsonObject) = MusicArtist(
        id = o.str("id").orEmpty(),
        name = o.str("name") ?: o.str("title").orEmpty(),
        coverUrl = o.str("artworkURL"),
    )

    internal fun parseTrack(o: JsonObject, album: MusicAlbum?, index: Int?): MusicTrack {
        val id = o.str("id").orEmpty()
        val seconds = o.int("duration") ?: o.long("durationMs")?.let { (it / 1000).toInt() } ?: 0
        return MusicTrack(
            id = id,
            title = o.str("title").orEmpty(),
            artist = o.str("artist") ?: album?.artist.orEmpty(),
            album = o.str("album") ?: album?.name.orEmpty(),
            albumId = o.str("albumId")?.let { ALBUM + it } ?: album?.id,
            durationSec = seconds,
            coverUrl = o.str("artworkURL") ?: album?.coverUrl,
            streamUrl = o.str("streamURL") ?: MusicAddonSource.addonStreamUri(addon.baseUrl, id, PROTOCOL),
            trackNumber = o.int("trackNumber") ?: index?.plus(1),
        )
    }

    companion object {
        const val PROTOCOL = "eclipse"
        internal const val ALBUM = "album:"
        internal const val PLAYLIST = "playlist:"
        internal const val CATALOG = "catalog:"

        private val ITEM_TYPES = setOf("track", "album", "artist", "playlist", "file")

        /** An Eclipse manifest answers searches itself instead of listing Stremio catalogs of type "music". */
        fun isEclipseManifest(manifest: JsonObject): Boolean {
            val resources = manifest.strings("resources")
            val types = manifest.strings("types")
            if ("music" in types) return false
            return ("search" in resources || "stream" in resources) && types.all { it in ITEM_TYPES }
        }

        fun parseManifest(base: String, manifest: JsonObject): MusicAddon {
            val catalogs = manifest.list("catalogs").mapNotNull { catalog ->
                val id = catalog.str("id") ?: return@mapNotNull null
                MusicAddonCatalog(
                    id = id,
                    name = catalog.str("name") ?: id,
                    searchOnly = false,
                    searchable = false,
                    type = catalog.str("type") ?: "album",
                )
            }
            return MusicAddon(
                baseUrl = base,
                id = manifest.str("id") ?: base,
                name = manifest.str("name") ?: base.substringAfter("://").substringBefore('/'),
                catalogs = catalogs,
                protocol = PROTOCOL,
            )
        }

        /** Asks the addon for a track's stream; blocking, for the player's loading thread. */
        fun resolveStream(http: OkHttpClient, baseUrl: String, trackId: String): String {
            val url = baseUrl.toHttpUrl().newBuilder().addPathSegment("stream").addPathSegment(trackId).build()
            return MusicAddonSource.fetchJson(http, url).str("url")
                ?: throw MusicServerException("No stream for this track")
        }

        private fun JsonObject.strings(name: String): List<String> =
            get(name)?.takeIf { it.isJsonArray }?.asJsonArray
                ?.mapNotNull { e -> e.takeIf { it.isJsonPrimitive }?.asString }.orEmpty()
    }
}
