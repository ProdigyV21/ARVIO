package com.arflix.tv.music

/** A playlist the user made in ARVIO; it keeps whole tracks, so it plays without asking a source. */
data class MusicPlaylist(
    val id: String,
    val name: String,
    val tracks: List<MusicTrack> = emptyList(),
    /** Where an imported playlist came from, such as "spotify:37i9dQZF1DXcBWIGoYBM5M". */
    val origin: String? = null,
)

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
) {
    val isEmpty: Boolean get() = favoriteTracks.isEmpty() && favoriteAlbums.isEmpty() && playlists.isEmpty()

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

    fun deletePlaylist(id: String): MusicCollections = copy(playlists = playlists.filter { it.id != id })

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
    )

    companion object {
        const val DEFAULT_NAME = "Playlist"
    }
}
