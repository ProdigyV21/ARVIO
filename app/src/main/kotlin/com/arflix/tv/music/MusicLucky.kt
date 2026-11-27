package com.arflix.tv.music

import kotlin.random.Random

/**
 * The "Surprise me" tile on the Music home: one tap that starts something the user is likely to
 * enjoy. Their own songs (liked, recently played, in their playlists) shuffled; or a mix made from
 * what they listen to; or, for someone new, a playlist from the home rows.
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
        val personal = shelves.filter { ChartsSource.isPersonalShelf(it.key) }.flatMap { it.albums }
        val anyAlbum = shelves.flatMap { it.albums }
        val ownPick = own.takeIf { it.size >= MIN_OWN }?.let { Pick.Tracks(it.shuffled(random).take(MAX_TRACKS)) }
        val mixPick = personal.takeIf { it.isNotEmpty() }?.let { Pick.Album(it.random(random)) }
        return when {
            // Both are good: alternate, so the tile does not always do the same thing.
            ownPick != null && mixPick != null -> if (random.nextBoolean()) ownPick else mixPick
            ownPick != null -> ownPick
            mixPick != null -> mixPick
            anyAlbum.isNotEmpty() -> Pick.Album(anyAlbum.take(40).random(random))
            own.isNotEmpty() -> Pick.Tracks(own.shuffled(random))
            else -> null
        }
    }
}
