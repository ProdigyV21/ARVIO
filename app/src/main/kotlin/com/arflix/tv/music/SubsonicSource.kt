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
import java.security.MessageDigest
import java.security.SecureRandom

/**
 * Client for the Subsonic / OpenSubsonic REST API, spoken by Navidrome, Gonic, Airsonic,
 * Ampache, LMS and others. Written from the public API description at subsonic.org and
 * opensubsonic.netlify.app.
 */
class SubsonicSource(
    private val http: OkHttpClient,
    private val config: MusicServerConfig,
) : MusicSource {

    internal fun url(method: String, vararg params: Pair<String, String>): HttpUrl =
        buildUrl(config, method, *params)

    private suspend fun call(method: String, vararg params: Pair<String, String>): JsonObject =
        withContext(Dispatchers.IO) {
            val request = Request.Builder().url(url(method, *params)).get().build()
            http.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw MusicServerException("HTTP ${response.code}")
                parseResponse(response.body?.string().orEmpty())
            }
        }

    override suspend fun albums(shelf: AlbumShelf, size: Int): List<MusicAlbum> {
        val type = when (shelf) {
            AlbumShelf.NEWEST -> "newest"
            AlbumShelf.RECENT -> "recent"
            AlbumShelf.FREQUENT -> "frequent"
            AlbumShelf.RANDOM -> "random"
            AlbumShelf.ALPHABETICAL -> "alphabeticalByName"
        }
        val body = call("getAlbumList2", "type" to type, "size" to size.coerceIn(1, 500).toString())
        return body.obj("albumList2").list("album").map { parseAlbum(it) }
    }

    override suspend fun artists(): List<MusicArtist> {
        val body = call("getArtists")
        return body.obj("artists").list("index").flatMap { it.list("artist") }.map { parseArtist(it) }
    }

    override suspend fun artistAlbums(artistId: String): List<MusicAlbum> =
        call("getArtist", "id" to artistId).obj("artist").list("album").map { parseAlbum(it) }
            .sortedByDescending { it.year ?: 0 }

    override suspend fun albumTracks(albumId: String): List<MusicTrack> =
        call("getAlbum", "id" to albumId).obj("album").list("song").map { parseTrack(it) }

    override suspend fun search(query: String): MusicSearchResult {
        val result = call(
            "search3",
            "query" to query,
            "artistCount" to "12",
            "albumCount" to "24",
            "songCount" to "40",
        ).obj("searchResult3")
        return MusicSearchResult(
            artists = result.list("artist").map { parseArtist(it) },
            albums = result.list("album").map { parseAlbum(it) },
            tracks = result.list("song").map { parseTrack(it) },
        )
    }

    override suspend fun reportPlayed(trackId: String) {
        call("scrobble", "id" to trackId, "submission" to "true")
    }

    suspend fun ping() {
        call("ping")
    }

    private fun coverUrl(coverArt: String?): String? =
        coverArt?.takeIf { it.isNotBlank() }?.let { url("getCoverArt", "id" to it, "size" to "600").toString() }

    internal fun parseAlbum(o: JsonObject) = MusicAlbum(
        id = o.str("id").orEmpty(),
        name = o.str("name") ?: o.str("title") ?: o.str("album").orEmpty(),
        artist = o.str("artist").orEmpty(),
        artistId = o.str("artistId"),
        coverUrl = coverUrl(o.str("coverArt")),
        year = o.int("year"),
        trackCount = o.int("songCount"),
    )

    internal fun parseArtist(o: JsonObject) = MusicArtist(
        id = o.str("id").orEmpty(),
        name = o.str("name").orEmpty(),
        coverUrl = o.str("artistImageUrl")?.takeIf { it.startsWith("http") } ?: coverUrl(o.str("coverArt")),
        albumCount = o.int("albumCount"),
    )

    internal fun parseTrack(o: JsonObject): MusicTrack {
        val id = o.str("id").orEmpty()
        return MusicTrack(
            id = id,
            title = o.str("title").orEmpty(),
            artist = o.str("artist").orEmpty(),
            album = o.str("album").orEmpty(),
            albumId = o.str("albumId"),
            durationSec = o.int("duration") ?: 0,
            coverUrl = coverUrl(o.str("coverArt") ?: o.str("albumId")),
            streamUrl = url("stream", "id" to id).toString(),
            trackNumber = o.int("track"),
        )
    }

    companion object {
        const val API_VERSION = "1.16.1"
        const val CLIENT_NAME = "ARVIO"

        fun buildUrl(config: MusicServerConfig, method: String, vararg params: Pair<String, String>): HttpUrl {
            val builder = config.baseUrl.toHttpUrl().newBuilder()
                .addPathSegment("rest")
                .addPathSegment(method)
                .addQueryParameter("u", config.username)
                .addQueryParameter("t", config.token)
                .addQueryParameter("s", config.salt)
                .addQueryParameter("v", API_VERSION)
                .addQueryParameter("c", CLIENT_NAME)
                .addQueryParameter("f", "json")
            params.forEach { (key, value) -> builder.addQueryParameter(key, value) }
            return builder.build()
        }

        /** Subsonic token auth: md5(password + salt), sent with the salt instead of the password. */
        fun credentials(password: String): Pair<String, String> {
            val bytes = ByteArray(8).also { SecureRandom().nextBytes(it) }
            val salt = bytes.joinToString("") { "%02x".format(it) }
            return md5Hex(password + salt) to salt
        }

        fun md5Hex(value: String): String =
            MessageDigest.getInstance("MD5").digest(value.toByteArray(Charsets.UTF_8))
                .joinToString("") { "%02x".format(it) }

        /** Returns the "subsonic-response" object, or throws with the server's own error message. */
        fun parseResponse(body: String): JsonObject {
            val root = runCatching { JsonParser.parseString(body).asJsonObject }.getOrNull()
                ?: throw MusicServerException("Not a Subsonic server")
            val response = root.obj("subsonic-response")
            if (!response.has("status")) throw MusicServerException("Not a Subsonic server")
            if (response.str("status") != "ok") {
                val error = response.obj("error")
                throw MusicServerException(error.str("message") ?: "Subsonic error ${error.int("code") ?: ""}".trim())
            }
            return response
        }
    }
}

internal fun JsonObject.obj(name: String): JsonObject =
    get(name)?.takeIf { it.isJsonObject }?.asJsonObject ?: JsonObject()

/** Some servers send a lone object where a one-item list is meant; both read as a list here. */
internal fun JsonObject.list(name: String): List<JsonObject> {
    val element: JsonElement = get(name) ?: return emptyList()
    return when {
        element.isJsonArray -> (element as JsonArray).filter { it.isJsonObject }.map { it.asJsonObject }
        element.isJsonObject -> listOf(element.asJsonObject)
        else -> emptyList()
    }
}

internal fun JsonObject.str(name: String): String? =
    get(name)?.takeIf { it.isJsonPrimitive }?.asString?.takeIf { it.isNotEmpty() }

internal fun JsonObject.int(name: String): Int? =
    get(name)?.takeIf { it.isJsonPrimitive }?.let { runCatching { it.asNumber.toInt() }.getOrNull() }

internal fun JsonObject.long(name: String): Long? =
    get(name)?.takeIf { it.isJsonPrimitive }?.let { runCatching { it.asNumber.toLong() }.getOrNull() }
