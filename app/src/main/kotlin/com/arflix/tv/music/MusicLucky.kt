package com.arflix.tv.music

import kotlin.random.Random

/**
 * The "Surprise me" tile on the Music home: one tap that starts something the user is likely to
 * enjoy and that their sources can play: their own songs (liked, recently played, in their
 * playlists) shuffled, or an album from their server or addons; only without those a mix or chart
 * playlist, whose songs first have to be found in their sources.
 *
 * Pure, so the choice is tested without a device.
 */
object MusicLucky {
    sealed interface Pick {
        data class Tracks(val tracks: List<MusicTrack>) : Pick
        data class Album(val album: MusicAlbum) : Pick
    }

    private const val MIN_OWN = 8
    private const val MAX_TRACKS = 50

    fun pick(
        collections: MusicCollections,
        taste: MusicTaste,
        shelves: List<MusicShelf>,
        random: Random = Random.Default,
    ): Pick? {
        val own = (collections.favoriteTracks + taste.recent + collections.playlists.flatMap { it.tracks })
            .distinctBy { it.id }
        // Albums from the user's own server or addons always play; chart rows are only names that
        // have to be found in those sources first, so they come last.
        val sourceAlbums = shelves.filterNot { ChartsSource.isChartShelf(it.key) }.flatMap { it.albums }
        val personal = shelves.filter { ChartsSource.isPersonalShelf(it.key) }.flatMap { it.albums }
        val charts = shelves.filter { ChartsSource.isChartShelf(it.key) }.flatMap { it.albums }
        val ownPick = own.takeIf { it.size >= MIN_OWN }?.let { Pick.Tracks(it.shuffled(random).take(MAX_TRACKS)) }
        val albumPick = sourceAlbums.takeIf { it.isNotEmpty() }?.let { Pick.Album(it.random(random)) }
        return when {
            // Both are good: alternate, so the tile does not always do the same thing.
            ownPick != null && albumPick != null -> if (random.nextBoolean()) ownPick else albumPick
            ownPick != null -> ownPick
            albumPick != null -> albumPick
            own.isNotEmpty() -> Pick.Tracks(own.shuffled(random))
            personal.isNotEmpty() -> Pick.Album(personal.random(random))
            charts.isNotEmpty() -> Pick.Album(charts.take(40).random(random))
            else -> null
        }
    }
}
