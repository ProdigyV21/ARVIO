package com.arflix.tv.ui.components

import androidx.compose.ui.unit.IntSize
import com.arflix.tv.data.model.MediaItem
import kotlin.math.roundToInt

internal data class LandscapeDeckArtworkSizes(val backdrop: IntSize, val logo: IntSize)

internal fun landscapeDeckArtworkUrl(item: MediaItem): String? =
    item.backdrop?.trim()?.takeIf { it.isNotEmpty() }
        ?: item.image.trim().takeIf { it.isNotEmpty() }

internal fun landscapeDeckArtworkSizes(layout: LandscapeDeckLayout, density: Float): LandscapeDeckArtworkSizes {
    fun pixels(width: Float, height: Float) = IntSize(
        (width * density).roundToInt().coerceAtLeast(1),
        (height * density).roundToInt().coerceAtLeast(1),
    )
    val large = layout.cardWidthDp >= 600f
    val logoWidth = if (layout.compact) layout.cardWidthDp * .6f else if (large) 440f else 280f
    val logoHeight = if (layout.compact) layout.cardHeightDp * .3f else if (large) 96f else 64f
    return LandscapeDeckArtworkSizes(
        backdrop = pixels(layout.cardWidthDp, layout.cardHeightDp),
        logo = pixels(logoWidth, logoHeight),
    )
}

internal fun landscapeDeckPrefetchIndices(currentIndex: Int, itemCount: Int): List<Int> {
    if (itemCount <= 0) return emptyList()
    return listOf(-2, -1, 0, 1, 2, 3).map { offset ->
        val index = (currentIndex.toLong() + offset) % itemCount
        ((index + itemCount) % itemCount).toInt()
    }.distinct()
}
