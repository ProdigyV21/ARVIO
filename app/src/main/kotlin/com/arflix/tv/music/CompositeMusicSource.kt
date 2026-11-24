package com.arflix.tv.music

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * All the user's music sources (their server and any addons) as one library. Every id is
 * prefixed with its source's key ("server~abc", "tv.arvio.music.audius~xyz"), so a later call
 * goes back to the source the item came from, also for favourites saved before another source
 * was added or removed. [keys] defaults to the sources' positions.
 */
class CompositeMusicSource(
    private val sources: List<MusicSource>,
    keys: List<String> = sources.indices.map { it.toString() },
) : MusicSource {

    private val keys: List<String> = keys.map { it.replace(SEPARATOR, "_") }

    private fun tag(index: Int, id: String) = "${keys[index]}$SEPARATOR$id"
    private fun tagOrNull(index: Int, id: String?) = id?.let { tag(index, it) }

    /** The position of the source an id belongs to, and the id as that source knows it. */
    private fun route(id: String): Pair<Int, String>? {
        val index = keys.indexOf(id.substringBefore(SEPARATOR, "")).takeIf { it >= 0 } ?: return null
        return index to id.substringAfter(SEPARATOR)
    }

    private fun MusicAlbum.tagged(i: Int) = copy(id = tag(i, id), artistId = tagOrNull(i, artistId))
    private fun MusicArtist.tagged(i: Int) = copy(id = tag(i, id))
    private fun MusicTrack.tagged(i: Int) = copy(id = tag(i, id), albumId = tagOrNull(i, albumId))

    /** Runs [block] on every source at once; a source that fails is left out. */
    private suspend fun <T> each(block: suspend (Int, MusicSource) -> List<T>): List<T> = coroutineScope {
        sources.mapIndexed { i, source -> async { runCatching { block(i, source) }.getOrDefault(emptyList()) } }
            .flatMap { it.await() }
    }

    override suspend fun shelves(): List<MusicShelf> {
        if (sources.size == 1) return sources[0].shelves().map { it.copy(albums = it.albums.map { a -> a.tagged(0) }) }
        // With several sources a failing one is skipped; with all failing the error still surfaces.
        val shelves = each { i, source -> source.shelves().map { it.copy(key = "$i/${it.key}", albums = it.albums.map { a -> a.tagged(i) }) } }
        if (shelves.isEmpty()) return sources.first().shelves().map { it.copy(albums = it.albums.map { a -> a.tagged(0) }) }
        return shelves
    }

    override suspend fun albums(shelf: AlbumShelf, size: Int): List<MusicAlbum> =
        each { i, source -> source.albums(shelf, size).map { it.tagged(i) } }
            .let { albums -> if (shelf == AlbumShelf.ALPHABETICAL) albums.sortedBy { it.name.lowercase() } else albums }

    override suspend fun artists(): List<MusicArtist> =
        each { i, source -> source.artists().map { it.tagged(i) } }.sortedBy { it.name.lowercase() }

    override suspend fun artistAlbums(artistId: String): List<MusicAlbum> {
        val (index, id) = route(artistId) ?: return emptyList()
        return sources[index].artistAlbums(id).map { it.tagged(index) }
    }

    override suspend fun albumTracks(albumId: String): List<MusicTrack> {
        val (index, id) = route(albumId) ?: return emptyList()
        return sources[index].albumTracks(id).map { it.tagged(index) }
    }

    override suspend fun search(query: String): MusicSearchResult = coroutineScope {
        val parts = sources.mapIndexed { i, source ->
            async { i to runCatching { source.search(query) }.getOrDefault(MusicSearchResult()) }
        }.map { it.await() }
        MusicSearchResult(
            artists = parts.flatMap { (i, r) -> r.artists.map { it.tagged(i) } },
            albums = parts.flatMap { (i, r) -> r.albums.map { it.tagged(i) } },
            tracks = parts.flatMap { (i, r) -> r.tracks.map { it.tagged(i) } },
        )
    }

    override suspend fun reportPlayed(trackId: String) {
        val (index, id) = route(trackId) ?: return
        sources[index].reportPlayed(id)
    }

    companion object {
        const val SEPARATOR = "~"
    }
}
