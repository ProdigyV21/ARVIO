package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel

/** Never more than this many live streams at once, whatever the device can decode. */
internal const val MULTIVIEW_MAX_TILES = 4

/**
 * One stream in multiview. [alternates] are the game's other channels, best first: a tile whose
 * channel fails moves on to the next one, as the single player's sports failover does.
 */
internal data class MultiviewTile(
    val key: String,
    val title: String,
    val channel: IptvChannel,
    val alternates: List<IptvChannel> = emptyList(),
)

internal sealed class MultiviewAddResult {
    data class Added(val tiles: List<MultiviewTile>) : MultiviewAddResult()
    data class AlreadyThere(val index: Int) : MultiviewAddResult()
    data object Full : MultiviewAddResult()
}

/** A tile is one game (or one channel without a game): the same game twice would only cost a connection. */
internal fun List<MultiviewTile>.adding(tile: MultiviewTile, capacity: Int): MultiviewAddResult {
    val existing = indexOfFirst { it.key == tile.key || it.channel.id == tile.channel.id }
    if (existing >= 0) return MultiviewAddResult.AlreadyThere(existing)
    if (size >= capacity.coerceIn(1, MULTIVIEW_MAX_TILES)) return MultiviewAddResult.Full
    return MultiviewAddResult.Added(this + tile)
}

/**
 * How many tiles this device can run. Every tile holds its own hardware video decoder and its
 * own buffer, so the count follows the decoder's instance limit and the app's heap: a low-RAM
 * stick that would stutter on four streams gets two.
 */
internal fun multiviewCapacity(decoderInstances: Int?, isLowRamDevice: Boolean, memoryClassMb: Int): Int {
    val byMemory = when {
        isLowRamDevice || memoryClassMb < 192 -> 2
        memoryClassMb < 256 -> 3
        else -> MULTIVIEW_MAX_TILES
    }
    // A reported limit counts every app's decoders; one stays free for whatever else is running.
    val byDecoder = decoderInstances?.takeIf { it > 0 }?.let { (it - 1).coerceAtLeast(1) } ?: MULTIVIEW_MAX_TILES
    return minOf(byMemory, byDecoder, MULTIVIEW_MAX_TILES).coerceAtLeast(1)
}

/** A tile's place on screen as fractions of the multiview area. */
internal data class MultiviewCell(val x: Float, val y: Float, val width: Float, val height: Float)

/**
 * One tile fills the screen, two sit side by side, three put the first one large on the left
 * with two stacked on the right, four make a 2×2 grid.
 */
internal fun multiviewCells(count: Int, portrait: Boolean = false): List<MultiviewCell> {
    val n = count.coerceIn(0, MULTIVIEW_MAX_TILES)
    // A phone held upright: full-width games stacked, which keeps each one wide.
    if (portrait) return List(n) { i -> MultiviewCell(0f, i.toFloat() / n, 1f, 1f / n) }
    return landscapeMultiviewCells(n)
}

private fun landscapeMultiviewCells(count: Int): List<MultiviewCell> = when (count) {
    0 -> emptyList()
    1 -> listOf(MultiviewCell(0f, 0f, 1f, 1f))
    2 -> listOf(MultiviewCell(0f, .25f, .5f, .5f), MultiviewCell(.5f, .25f, .5f, .5f))
    3 -> listOf(
        MultiviewCell(0f, 0f, 2f / 3f, 1f),
        MultiviewCell(2f / 3f, 0f, 1f / 3f, .5f),
        MultiviewCell(2f / 3f, .5f, 1f / 3f, .5f),
    )
    else -> listOf(
        MultiviewCell(0f, 0f, .5f, .5f), MultiviewCell(.5f, 0f, .5f, .5f),
        MultiviewCell(0f, .5f, .5f, .5f), MultiviewCell(.5f, .5f, .5f, .5f),
    )
}

internal enum class MultiviewDirection { Left, Right, Up, Down }

/**
 * The tile the D-pad lands on: the nearest cell whose centre lies in that direction, or the
 * same tile at an edge.
 */
internal fun multiviewNeighbour(index: Int, direction: MultiviewDirection, count: Int, portrait: Boolean = false): Int {
    val cells = multiviewCells(count, portrait)
    val from = cells.getOrNull(index) ?: return index.coerceIn(0, (count - 1).coerceAtLeast(0))
    val fx = from.x + from.width / 2
    val fy = from.y + from.height / 2
    var best = index
    var bestScore = Float.MAX_VALUE
    cells.forEachIndexed { i, cell ->
        if (i == index) return@forEachIndexed
        val cx = cell.x + cell.width / 2
        val cy = cell.y + cell.height / 2
        val dx = cx - fx
        val dy = cy - fy
        val (along, across) = when (direction) {
            MultiviewDirection.Left -> -dx to dy
            MultiviewDirection.Right -> dx to dy
            MultiviewDirection.Up -> -dy to dx
            MultiviewDirection.Down -> dy to dx
        }
        if (along <= 0.01f) return@forEachIndexed
        val score = along + 2 * kotlin.math.abs(across)
        if (score < bestScore) {
            bestScore = score
            best = i
        }
    }
    return best
}

/**
 * The largest picture a tile asks for. The tile with sound gets full HD while it is big enough
 * to show it; the others take a lower rendition, which on adaptive (HLS) channels saves
 * bandwidth and decoder load. Single-rendition channels simply play what they have.
 */
internal fun multiviewMaxVideoHeight(hasAudio: Boolean, count: Int): Int = when {
    count <= 1 -> Int.MAX_VALUE
    hasAudio && count == 2 -> 1080
    hasAudio -> 720
    count == 2 -> 720
    else -> 540
}

/** Removing a tile keeps the sound on the same game, or on the tile that took its place. */
internal fun multiviewAudioAfterRemoval(audioIndex: Int, removed: Int, remaining: Int): Int = when {
    remaining <= 0 -> 0
    removed < audioIndex -> audioIndex - 1
    removed == audioIndex -> removed.coerceAtMost(remaining - 1)
    else -> audioIndex
}.coerceIn(0, (remaining - 1).coerceAtLeast(0))

/** The next channel to try for a failed tile, or null once every alternate has had its turn. */
internal fun MultiviewTile.nextChannelAfter(failedId: String, tried: Set<String>): IptvChannel? =
    (listOf(channel) + alternates).distinctBy { it.id }
        .firstOrNull { it.id != failedId && it.id !in tried }
