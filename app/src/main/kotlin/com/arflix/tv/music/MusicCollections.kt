package com.arflix.tv.music

/** A playlist the user made in ARVIO; it keeps whole tracks, so it plays without asking a source. */
data class MusicPlaylist(
    val id: String,
    val name: String,
    val tracks: List<MusicTrack> = emptyList(),
    /** Where an imported playlist came from, such as "spotify:37i9dQZF1DXcBWIGoYBM5M". */
    val origin: String? = null,
)

enum class MusicPinType { ALBUM, PLAYLIST, ARTIST }

/**
 * Something the user pinned to the top of the Music home: an album (or an addon's or the charts'
 * playlist, which open like albums), one of their own playlists, or an artist.
 */
data class MusicPin(
    val type: MusicPinType,
    val id: String,
    val name: String,
    val subtitle: String = "",
    val coverUrl: String? = null,
    val artistId: String? = null,
) {
    fun toAlbum() = MusicAlbum(id = id, name = name, artist = subtitle, artistId = artistId, coverUrl = coverUrl)
    fun toArtist() = MusicArtist(id = id, name = name, coverUrl = coverUrl)

    companion object {
        fun of(album: MusicAlbum) = MusicPin(MusicPinType.ALBUM, album.id, album.name, album.artist, album.coverUrl, album.artistId)
        fun of(artist: MusicArtist) = MusicPin(MusicPinType.ARTIST, artist.id, artist.name, coverUrl = artist.coverUrl)
        fun of(playlist: MusicPlaylist) = MusicPin(
            MusicPinType.PLAYLIST, playlist.id, playlist.name,
            coverUrl = playlist.tracks.firstOrNull { it.coverUrl != null }?.coverUrl,
        )
    }
}

/**
 * What the user keeps in the Music tab: liked songs, favourite albums and playlists (an addon's
 * or server's playlists show as albums, so they are favourited the same way), and their own playlists.
 *
 * Plain data with pure updates, so the rules are tested without a device.
 */
data class MusicCollections(
    val favoriteTracks: List<MusicTrack> = emptyList(),
    val favoriteAlbums: List<MusicAlbum> = emptyList(),
    val playlists: List<MusicPlaylist> = emptyList(),
    /** The Speed dial at the top of the Music home, newest first. */
    val pins: List<MusicPin> = emptyList(),
) {
    val isEmpty: Boolean get() = favoriteTracks.isEmpty() && favoriteAlbums.isEmpty() && playlists.isEmpty()

    fun isPinned(type: MusicPinType, id: String): Boolean = pins.any { it.type == type && it.id == id }

    /** Pins or unpins; a full Speed dial lets go of its oldest pin. */
    fun togglePin(pin: MusicPin): MusicCollections =
        if (isPinned(pin.type, pin.id)) copy(pins = pins.filterNot { it.type == pin.type && it.id == pin.id })
        else copy(pins = (listOf(pin) + pins).take(MAX_PINS))

    /** Pins of the user's own playlists show their current name and cover; a deleted one is left out. */
    fun visiblePins(): List<MusicPin> = pins.mapNotNull { pin ->
        if (pin.type != MusicPinType.PLAYLIST) pin
        else if (pin.id == LIKED_ID) pin.copy(coverUrl = favoriteTracks.firstOrNull { it.coverUrl != null }?.coverUrl ?: pin.coverUrl)
        else playlist(pin.id)?.let { MusicPin.of(it).copy(coverUrl = MusicPin.of(it).coverUrl ?: pin.coverUrl) }
    }

    fun isFavorite(track: MusicTrack): Boolean = favoriteTracks.any { it.id == track.id }
    fun isFavorite(album: MusicAlbum): Boolean = favoriteAlbums.any { it.id == album.id }

    /** The newest like comes first, as in other music apps. */
    fun toggleFavorite(track: MusicTrack): MusicCollections =
        if (isFavorite(track)) copy(favoriteTracks = favoriteTracks.filter { it.id != track.id })
        else copy(favoriteTracks = listOf(track) + favoriteTracks)

    fun toggleFavorite(album: MusicAlbum): MusicCollections =
        if (isFavorite(album)) copy(favoriteAlbums = favoriteAlbums.filter { it.id != album.id })
        else copy(favoriteAlbums = listOf(album) + favoriteAlbums)

    fun playlist(id: String): MusicPlaylist? = playlists.firstOrNull { it.id == id }

    fun createPlaylist(id: String, name: String, tracks: List<MusicTrack> = emptyList(), origin: String? = null): MusicCollections =
        copy(playlists = playlists + MusicPlaylist(id, name.trim().ifEmpty { DEFAULT_NAME }, tracks.distinctBy { it.id }, origin))

    fun renamePlaylist(id: String, name: String): MusicCollections {
        val trimmed = name.trim()
        if (trimmed.isEmpty()) return this
        return updatePlaylist(id) { it.copy(name = trimmed) }
    }

    fun deletePlaylist(id: String): MusicCollections = copy(
        playlists = playlists.filter { it.id != id },
        pins = pins.filterNot { it.type == MusicPinType.PLAYLIST && it.id == id },
    )

    /** Adds the tracks at the end, leaving out the ones already in the playlist. */
    fun addToPlaylist(id: String, tracks: List<MusicTrack>): MusicCollections = updatePlaylist(id) { playlist ->
        val present = playlist.tracks.mapTo(HashSet()) { it.id }
        playlist.copy(tracks = playlist.tracks + tracks.distinctBy { it.id }.filter { it.id !in present })
    }

    fun removeFromPlaylist(id: String, index: Int): MusicCollections = updatePlaylist(id) { playlist ->
        if (index !in playlist.tracks.indices) playlist
        else playlist.copy(tracks = playlist.tracks.filterIndexed { i, _ -> i != index })
    }

    fun moveInPlaylist(id: String, from: Int, to: Int): MusicCollections = updatePlaylist(id) { playlist ->
        val tracks = playlist.tracks
        if (from !in tracks.indices || to !in tracks.indices || from == to) playlist
        else playlist.copy(tracks = tracks.toMutableList().apply { add(to, removeAt(from)) })
    }

    /** Replaces an imported playlist with a fresh copy of the same origin, keeping its place and name. */
    fun replaceImported(id: String, name: String, tracks: List<MusicTrack>, origin: String): MusicCollections {
        val existing = playlists.firstOrNull { it.origin == origin }
            ?: return createPlaylist(id, name, tracks, origin)
        return updatePlaylist(existing.id) { it.copy(tracks = tracks.distinctBy { t -> t.id }) }
    }

    private fun updatePlaylist(id: String, transform: (MusicPlaylist) -> MusicPlaylist): MusicCollections =
        copy(playlists = playlists.map { if (it.id == id) transform(it) else it })

    /** Gson leaves fields that are missing from older saves null, whatever Kotlin says. */
    @Suppress("SENSELESS_COMPARISON", "USELESS_ELVIS")
    fun sanitized(): MusicCollections = MusicCollections(
        favoriteTracks = (favoriteTracks ?: emptyList()).filter { it != null && it.id != null },
        favoriteAlbums = (favoriteAlbums ?: emptyList()).filter { it != null && it.id != null },
        playlists = (playlists ?: emptyList()).filter { it != null && it.id != null }.map {
            it.copy(name = it.name ?: DEFAULT_NAME, tracks = (it.tracks ?: emptyList()).filter { t -> t != null && t.id != null })
        },
        pins = (pins ?: emptyList()).filter { it != null && it.id != null && it.type != null }
            .map { it.copy(name = it.name ?: "", subtitle = it.subtitle ?: "") },
    )

    companion object {
        const val DEFAULT_NAME = "Playlist"
        /** The id the liked songs go by, as a page and as a pin. */
        const val LIKED_ID = "liked"
        const val MAX_PINS = 8
    }
}
