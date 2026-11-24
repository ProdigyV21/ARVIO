package com.arflix.tv.music

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder

/** A music addon the user added by its manifest URL, like a film addon. */
data class MusicAddon(
    /** The addon's base URL, without "/manifest.json". */
    val baseUrl: String,
    val id: String,
    val name: String,
    val catalogs: List<MusicAddonCatalog> = emptyList(),
)

data class MusicAddonCatalog(
    val id: String,
    val name: String,
    /** Only answers a search, so it is not a home shelf. */
    val searchOnly: Boolean,
    val searchable: Boolean,
)

/**
 * A music addon: the Stremio addon protocol with music types.
 *
 * - `manifest.json`: `types` contains "music", `catalogs` of type "music" (a catalog with a
 *   required `search` extra only answers searches).
 * - `catalog/music/{catalog}.json` and `catalog/music/{catalog}/search={query}.json`:
 *   `metas` of type "album", "artist" or "track".
 * - `meta/album/{id}.json`: the album with its tracks in `videos`.
 * - `meta/artist/{id}.json`: the artist with its albums in `albums`.
 * - `stream/track/{id}.json`: `streams` with a playable `url`, asked for only when a track plays.
 */
class MusicAddonSource(
    private val http: OkHttpClient,
    val addon: MusicAddon,
) : MusicSource {

    private fun url(vararg segments: String): HttpUrl =
        addon.baseUrl.toHttpUrl().newBuilder().apply { segments.forEach { addPathSegment(it) } }.build()

    private suspend fun get(url: HttpUrl): JsonObject = withContext(Dispatchers.IO) { fetchJson(http, url) }

    override suspend fun shelves(): List<MusicShelf> = coroutineScope {
        addon.catalogs.filter { !it.searchOnly }.map { catalog ->
            async {
                runCatching {
                    val albums = get(url("catalog", "music", "${catalog.id}.json")).list("metas")
                        .filter { it.str("type") == "album" }.map { parseAlbum(it) }
                    MusicShelf(key = "${addon.id}/${catalog.id}", title = catalog.name, albums = albums)
                }.getOrNull()
            }
        }.mapNotNull { it.await() }.filter { it.albums.isNotEmpty() }
    }

    override suspend fun albums(shelf: AlbumShelf, size: Int): List<MusicAlbum> =
        if (shelf == AlbumShelf.ALPHABETICAL) shelves().flatMap { it.albums }.distinctBy { it.id }.sortedBy { it.name.lowercase() }
        else emptyList()

    override suspend fun artists(): List<MusicArtist> = coroutineScope {
        addon.catalogs.filter { !it.searchOnly }.map { catalog ->
            async {
                runCatching {
                    get(url("catalog", "music", "${catalog.id}.json")).list("metas")
                        .filter { it.str("type") == "artist" }.map { parseArtist(it) }
                }.getOrDefault(emptyList())
            }
        }.flatMap { it.await() }.distinctBy { it.id }
    }

    override suspend fun artistAlbums(artistId: String): List<MusicAlbum> =
        get(url("meta", "artist", "$artistId.json")).obj("meta").list("albums").map { parseAlbum(it) }

    override suspend fun albumTracks(albumId: String): List<MusicTrack> {
        val meta = get(url("meta", "album", "$albumId.json")).obj("meta")
        val album = parseAlbum(meta)
        return meta.list("videos").mapIndexed { index, video -> parseTrack(video, album, index) }
    }

    override suspend fun search(query: String): MusicSearchResult = coroutineScope {
        val parts = addon.catalogs.filter { it.searchable }.map { catalog ->
            async {
                runCatching { get(url("catalog", "music", catalog.id, "search=$query.json")).list("metas") }
                    .getOrDefault(emptyList())
            }
        }.flatMap { it.await() }
        MusicSearchResult(
            artists = parts.filter { it.str("type") == "artist" }.map { parseArtist(it) }.distinctBy { it.id },
            albums = parts.filter { it.str("type") == "album" }.map { parseAlbum(it) }.distinctBy { it.id },
            tracks = parts.filter { it.str("type") == "track" }.map { parseTrack(it, null, null) }.distinctBy { it.id },
        )
    }

    override suspend fun reportPlayed(trackId: String) = Unit

    internal fun parseAlbum(o: JsonObject) = MusicAlbum(
        id = o.str("id").orEmpty(),
        name = o.str("name").orEmpty(),
        artist = o.str("artist").orEmpty(),
        artistId = o.str("artistId"),
        coverUrl = o.str("poster"),
        year = o.str("releaseInfo")?.take(4)?.toIntOrNull() ?: o.int("year"),
        trackCount = o.int("trackCount"),
    )

    internal fun parseArtist(o: JsonObject) = MusicArtist(
        id = o.str("id").orEmpty(),
        name = o.str("name").orEmpty(),
        coverUrl = o.str("poster"),
        albumCount = o.int("albumCount"),
    )

    internal fun parseTrack(o: JsonObject, album: MusicAlbum?, index: Int?): MusicTrack {
        val id = o.str("id").orEmpty()
        // A track may carry its stream already (Stremio's "streams" on a video); otherwise the
        // player asks the addon's stream endpoint when the track starts.
        val direct = o.list("streams").firstNotNullOfOrNull { it.str("url") }
        return MusicTrack(
            id = id,
            title = o.str("title") ?: o.str("name").orEmpty(),
            artist = o.str("artist") ?: album?.artist.orEmpty(),
            album = o.str("album") ?: album?.name.orEmpty(),
            albumId = o.str("albumId") ?: album?.id,
            durationSec = o.int("duration") ?: 0,
            coverUrl = o.str("thumbnail") ?: o.str("poster") ?: album?.coverUrl,
            streamUrl = direct ?: addonStreamUri(addon.baseUrl, id),
            trackNumber = o.int("number") ?: index?.plus(1),
        )
    }

    companion object {
        const val STREAM_SCHEME = "arvio-music-addon"

        /** A placeholder URI the playback service resolves to the addon's stream when the track plays. */
        fun addonStreamUri(baseUrl: String, trackId: String): String =
            "$STREAM_SCHEME://stream?base=" + URLEncoder.encode(baseUrl, "UTF-8") +
                "&id=" + URLEncoder.encode(trackId, "UTF-8")

        /** Asks the addon for a track's stream; blocking, for the player's loading thread. */
        fun resolveStream(http: OkHttpClient, baseUrl: String, trackId: String): String {
            val url = baseUrl.toHttpUrl().newBuilder()
                .addPathSegment("stream").addPathSegment("track").addPathSegment("$trackId.json").build()
            return fetchJson(http, url).list("streams").firstNotNullOfOrNull { it.str("url") }
                ?: throw MusicServerException("No stream for this track")
        }

        /** Accepts a manifest URL, a base URL, or a stremio:// link. */
        fun normalizeAddonUrl(input: String): String {
            var value = input.trim()
            if (value.startsWith("stremio://", ignoreCase = true)) value = "https://" + value.substring("stremio://".length)
            value = value.removeSuffix("/").removeSuffix("/manifest.json").removeSuffix("/")
            return normalizeServerUrl(value)
        }

        suspend fun install(http: OkHttpClient, input: String): MusicAddon = withContext(Dispatchers.IO) {
            val base = normalizeAddonUrl(input)
            if (base.isEmpty()) throw MusicServerException("Enter the addon address")
            val manifest = fetchJson(http, "$base/manifest.json".toHttpUrl())
            parseManifest(base, manifest)
        }

        fun parseManifest(base: String, manifest: JsonObject): MusicAddon {
            val catalogs = manifest.list("catalogs").filter { it.str("type") == "music" }.mapNotNull { catalog ->
                val id = catalog.str("id") ?: return@mapNotNull null
                val search = catalog.list("extra").firstOrNull { it.str("name") == "search" }
                val required = search?.get("isRequired")?.takeIf { it.isJsonPrimitive }?.asBoolean == true
                MusicAddonCatalog(
                    id = id,
                    name = catalog.str("name") ?: id,
                    searchOnly = required,
                    searchable = search != null,
                )
            }
            val types = manifest.get("types")?.takeIf { it.isJsonArray }?.asJsonArray?.mapNotNull {
                it.takeIf { e -> e.isJsonPrimitive }?.asString
            }.orEmpty()
            if ("music" !in types && catalogs.isEmpty()) throw MusicServerException("This is not a music addon")
            return MusicAddon(
                baseUrl = base,
                id = manifest.str("id") ?: base,
                name = manifest.str("name") ?: base.substringAfter("://"),
                catalogs = catalogs,
            )
        }

        internal fun fetchJson(http: OkHttpClient, url: HttpUrl): JsonObject {
            val request = Request.Builder().url(url).header("Accept", "application/json").get().build()
            return http.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw MusicServerException("HTTP ${response.code}")
                runCatching { JsonParser.parseString(response.body?.string().orEmpty()).asJsonObject }
                    .getOrElse { throw MusicServerException("Unexpected addon response") }
            }
        }
    }
}
