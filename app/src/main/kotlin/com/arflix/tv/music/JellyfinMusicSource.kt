package com.arflix.tv.music

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * Music from a Jellyfin server, through its own REST API (api.jellyfin.org).
 */
class JellyfinMusicSource(
    private val http: OkHttpClient,
    private val config: MusicServerConfig,
) : MusicSource {

    private fun base(): HttpUrl.Builder = config.baseUrl.toHttpUrl().newBuilder()

    private fun userItems(): HttpUrl.Builder =
        base().addPathSegment("Users").addPathSegment(config.userId).addPathSegment("Items")

    private suspend fun get(url: HttpUrl): JsonObject = withContext(Dispatchers.IO) {
        val request = Request.Builder().url(url)
            .header("Authorization", authorization(config.deviceId, config.token))
            .get().build()
        http.newCall(request).execute().use { response ->
            if (response.code == 401) throw MusicServerException("Signed out of Jellyfin")
            if (!response.isSuccessful) throw MusicServerException("HTTP ${response.code}")
            runCatching { JsonParser.parseString(response.body?.string().orEmpty()).asJsonObject }
                .getOrElse { throw MusicServerException("Unexpected Jellyfin response") }
        }
    }

    override suspend fun albums(shelf: AlbumShelf, size: Int): List<MusicAlbum> {
        val (sortBy, order) = when (shelf) {
            AlbumShelf.NEWEST -> "DateCreated,SortName" to "Descending"
            AlbumShelf.RECENT -> "DatePlayed,SortName" to "Descending"
            AlbumShelf.FREQUENT -> "PlayCount,SortName" to "Descending"
            AlbumShelf.RANDOM -> "Random" to "Ascending"
            AlbumShelf.ALPHABETICAL -> "SortName" to "Ascending"
        }
        val url = userItems()
            .addQueryParameter("IncludeItemTypes", "MusicAlbum")
            .addQueryParameter("Recursive", "true")
            .addQueryParameter("SortBy", sortBy)
            .addQueryParameter("SortOrder", order)
            .addQueryParameter("Limit", size.coerceIn(1, 500).toString())
            .addQueryParameter("Fields", "ChildCount")
            .apply {
                if (shelf == AlbumShelf.RECENT || shelf == AlbumShelf.FREQUENT) addQueryParameter("Filters", "IsPlayed")
            }
            .build()
        return get(url).list("Items").map { parseAlbum(it) }
    }

    override suspend fun artists(): List<MusicArtist> {
        val url = base().addPathSegment("Artists").addPathSegment("AlbumArtists")
            .addQueryParameter("UserId", config.userId)
            .addQueryParameter("SortBy", "SortName")
            .addQueryParameter("SortOrder", "Ascending")
            .addQueryParameter("Limit", "1000")
            .build()
        return get(url).list("Items").map { parseArtist(it) }
    }

    override suspend fun artistAlbums(artistId: String): List<MusicAlbum> {
        val url = userItems()
            .addQueryParameter("IncludeItemTypes", "MusicAlbum")
            .addQueryParameter("Recursive", "true")
            .addQueryParameter("AlbumArtistIds", artistId)
            .addQueryParameter("SortBy", "ProductionYear,SortName")
            .addQueryParameter("SortOrder", "Descending")
            .addQueryParameter("Fields", "ChildCount")
            .build()
        return get(url).list("Items").map { parseAlbum(it) }
    }

    override suspend fun albumTracks(albumId: String): List<MusicTrack> {
        val url = userItems()
            .addQueryParameter("ParentId", albumId)
            .addQueryParameter("IncludeItemTypes", "Audio")
            .addQueryParameter("Recursive", "true")
            .addQueryParameter("SortBy", "ParentIndexNumber,IndexNumber,SortName")
            .addQueryParameter("SortOrder", "Ascending")
            .build()
        return get(url).list("Items").map { parseTrack(it) }
    }

    override suspend fun search(query: String): MusicSearchResult {
        val url = userItems()
            .addQueryParameter("SearchTerm", query)
            .addQueryParameter("IncludeItemTypes", "MusicArtist,MusicAlbum,Audio")
            .addQueryParameter("Recursive", "true")
            .addQueryParameter("Limit", "80")
            .addQueryParameter("Fields", "ChildCount")
            .build()
        val items = get(url).list("Items")
        return MusicSearchResult(
            artists = items.filter { it.str("Type") == "MusicArtist" }.map { parseArtist(it) },
            albums = items.filter { it.str("Type") == "MusicAlbum" }.map { parseAlbum(it) },
            tracks = items.filter { it.str("Type") == "Audio" }.map { parseTrack(it) },
        )
    }

    /** Jellyfin's Instant Mix: songs from the same genres and artists as the seed. */
    override suspend fun radio(seed: MusicTrack): List<MusicTrack> {
        val url = base().addPathSegment("Items").addPathSegment(seed.id).addPathSegment("InstantMix")
            .addQueryParameter("UserId", config.userId)
            .addQueryParameter("Limit", "50")
            .build()
        return get(url).list("Items").map { parseTrack(it) }
    }

    override suspend fun reportPlayed(trackId: String) {
        withContext(Dispatchers.IO) {
            val url = base().addPathSegment("Users").addPathSegment(config.userId)
                .addPathSegment("PlayedItems").addPathSegment(trackId).build()
            val request = Request.Builder().url(url)
                .header("Authorization", authorization(config.deviceId, config.token))
                .post(ByteArray(0).toRequestBody(null)).build()
            http.newCall(request).execute().close()
        }
    }

    private fun imageUrl(itemId: String?): String? = itemId?.takeIf { it.isNotBlank() }?.let {
        base().addPathSegment("Items").addPathSegment(it).addPathSegment("Images").addPathSegment("Primary")
            .addQueryParameter("fillHeight", "600")
            .addQueryParameter("quality", "90")
            .build().toString()
    }

    private fun hasPrimaryImage(o: JsonObject): Boolean = o.obj("ImageTags").has("Primary")

    internal fun parseAlbum(o: JsonObject): MusicAlbum {
        val id = o.str("Id").orEmpty()
        val albumArtist = o.list("AlbumArtists").firstOrNull()
        return MusicAlbum(
            id = id,
            name = o.str("Name").orEmpty(),
            artist = o.str("AlbumArtist") ?: albumArtist?.str("Name").orEmpty(),
            artistId = albumArtist?.str("Id"),
            coverUrl = if (hasPrimaryImage(o)) imageUrl(id) else null,
            year = o.int("ProductionYear"),
            trackCount = o.int("ChildCount"),
        )
    }

    internal fun parseArtist(o: JsonObject): MusicArtist {
        val id = o.str("Id").orEmpty()
        return MusicArtist(
            id = id,
            name = o.str("Name").orEmpty(),
            coverUrl = if (hasPrimaryImage(o)) imageUrl(id) else null,
            albumCount = o.int("AlbumCount"),
        )
    }

    internal fun parseTrack(o: JsonObject): MusicTrack {
        val id = o.str("Id").orEmpty()
        val albumId = o.str("AlbumId")
        val artists = o.list("ArtistItems").mapNotNull { it.str("Name") }
        val cover = when {
            hasPrimaryImage(o) -> imageUrl(id)
            o.str("AlbumPrimaryImageTag") != null -> imageUrl(albumId)
            else -> null
        }
        return MusicTrack(
            id = id,
            title = o.str("Name").orEmpty(),
            artist = artists.joinToString(", ").ifEmpty { o.str("AlbumArtist").orEmpty() },
            album = o.str("Album").orEmpty(),
            albumId = albumId,
            durationSec = ((o.long("RunTimeTicks") ?: 0L) / 10_000_000L).toInt(),
            coverUrl = cover,
            streamUrl = base().addPathSegment("Audio").addPathSegment(id).addPathSegment("stream")
                .addQueryParameter("static", "true")
                .addQueryParameter("api_key", config.token)
                .build().toString(),
            trackNumber = o.int("IndexNumber"),
        )
    }

    companion object {
        fun authorization(deviceId: String, token: String? = null): String = buildString {
            append("MediaBrowser Client=\"ARVIO\", Device=\"Android\", DeviceId=\"")
            append(deviceId)
            append("\", Version=\"1.0\"")
            if (!token.isNullOrBlank()) append(", Token=\"").append(token).append('"')
        }

        /** Signs in with username and password; returns the access token and user id. */
        suspend fun signIn(
            http: OkHttpClient,
            baseUrl: String,
            username: String,
            password: String,
            deviceId: String,
        ): Pair<String, String> = withContext(Dispatchers.IO) {
            val url = baseUrl.toHttpUrl().newBuilder()
                .addPathSegment("Users").addPathSegment("AuthenticateByName").build()
            val body = JsonObject().apply {
                addProperty("Username", username)
                addProperty("Pw", password)
            }.toString().toRequestBody("application/json".toMediaType())
            val request = Request.Builder().url(url)
                .header("Authorization", authorization(deviceId))
                .post(body).build()
            http.newCall(request).execute().use { response ->
                if (response.code == 401) throw MusicServerException("Wrong username or password")
                if (!response.isSuccessful) throw MusicServerException("HTTP ${response.code}")
                val json = runCatching { JsonParser.parseString(response.body?.string().orEmpty()).asJsonObject }
                    .getOrElse { throw MusicServerException("Not a Jellyfin server") }
                val token = json.str("AccessToken") ?: throw MusicServerException("Not a Jellyfin server")
                val userId = json.obj("User").str("Id") ?: throw MusicServerException("Not a Jellyfin server")
                token to userId
            }
        }
    }
}
