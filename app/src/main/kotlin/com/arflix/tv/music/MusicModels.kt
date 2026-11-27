package com.arflix.tv.music

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/** The kind of personal music server the Music tab is connected to. */
enum class MusicServerType { SUBSONIC, JELLYFIN }

/**
 * A saved connection to the user's own music server.
 *
 * Subsonic servers (Navidrome, Gonic, Airsonic, Ampache, ...) get the salted token from the
 * Subsonic API instead of the password. Jellyfin keeps the access token it handed out at sign-in.
 */
data class MusicServerConfig(
    val type: MusicServerType,
    val baseUrl: String,
    val username: String,
    val token: String,
    val salt: String = "",
    val userId: String = "",
    val deviceId: String = "",
)

data class MusicArtist(
    val id: String,
    val name: String,
    val coverUrl: String?,
    val albumCount: Int? = null,
)

data class MusicAlbum(
    val id: String,
    val name: String,
    val artist: String,
    val artistId: String?,
    val coverUrl: String?,
    val year: Int? = null,
    val trackCount: Int? = null,
)

data class MusicTrack(
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val albumId: String?,
    val durationSec: Int,
    val coverUrl: String?,
    val streamUrl: String,
    val trackNumber: Int? = null,
)

data class MusicSearchResult(
    val artists: List<MusicArtist> = emptyList(),
    val albums: List<MusicAlbum> = emptyList(),
    val tracks: List<MusicTrack> = emptyList(),
    /** Playlists (an addon's, or the charts'); they open like albums. */
    val playlists: List<MusicAlbum> = emptyList(),
    /** Latin names the query was also searched by, when it was typed in another script ([MusicNameLookup]). */
    val aliases: List<String> = emptyList(),
) {
    val isEmpty: Boolean get() = artists.isEmpty() && albums.isEmpty() && tracks.isEmpty() && playlists.isEmpty()
}

/** The album lists the Music home and Albums tab show. */
enum class AlbumShelf { NEWEST, RECENT, FREQUENT, RANDOM, ALPHABETICAL }

/** One row on the Music home: a standard list ([kind]) or an addon catalog ([title]). */
data class MusicShelf(
    val key: String,
    val albums: List<MusicAlbum>,
    val kind: AlbumShelf? = null,
    val title: String? = null,
)

class MusicServerException(message: String) : Exception(message)

/** One personal music server, whatever API it speaks. */
interface MusicSource {
    /** The home rows; a server gives its standard lists, an addon its catalogs. */
    suspend fun shelves(): List<MusicShelf> = coroutineScope {
        listOf(AlbumShelf.NEWEST, AlbumShelf.RECENT, AlbumShelf.FREQUENT, AlbumShelf.RANDOM).map { kind ->
            async {
                val albums = if (kind == AlbumShelf.NEWEST) albums(kind, 24)
                else runCatching { albums(kind, 24) }.getOrDefault(emptyList())
                MusicShelf(key = kind.name, albums = albums, kind = kind)
            }
        }.map { it.await() }.filter { it.albums.isNotEmpty() }
    }

    suspend fun albums(shelf: AlbumShelf, size: Int = 30): List<MusicAlbum>
    suspend fun artists(): List<MusicArtist>
    suspend fun artistAlbums(artistId: String): List<MusicAlbum>
    suspend fun albumTracks(albumId: String): List<MusicTrack>
    suspend fun search(query: String): MusicSearchResult

    /** Tells the server a track was listened to, so its play counts (and any scrobbling it does) stay right. */
    suspend fun reportPlayed(trackId: String)
}

/** Accepts "music.example.com", "192.168.1.5:4533/" and full URLs alike. */
fun normalizeServerUrl(input: String): String {
    val trimmed = input.trim().trimEnd('/')
    if (trimmed.isEmpty()) return trimmed
    return if (trimmed.startsWith("http://", ignoreCase = true) || trimmed.startsWith("https://", ignoreCase = true)) {
        trimmed
    } else {
        // A LAN address or an explicit port is almost always a plain-HTTP home server;
        // a bare domain name is almost always behind HTTPS.
        val host = trimmed.substringBefore('/')
        val local = host.contains(':') || host.substringBefore(':').let { name ->
            name.all { it.isDigit() || it == '.' } || name.endsWith(".local") || name.endsWith(".lan") || !name.contains('.')
        }
        (if (local) "http://" else "https://") + trimmed
    }
}
