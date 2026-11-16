package com.arflix.tv.ui.components

import kotlin.math.ceil

internal fun resolveDetailsBackdropHeightDp(
    availableWidthDp: Float,
    availableHeightDp: Float,
): Float {
    val width = availableWidthDp.coerceAtLeast(1f)
    val height = availableHeightDp.coerceAtLeast(1f)
    return if (width > height) {
        val overlayFloor = minOf(190f, height * 0.68f)
        (height * 0.55f).coerceIn(overlayFloor, 360f)
    } else {
        // Keep the complete 2:3 poster visible, leaving room for the first action.
        minOf(width * 1.5f, height * 0.84f)
    }
}

internal data class MobileDetailsArtworkSource(val url: String, val isPoster: Boolean)

internal fun mobileDetailsArtworkSource(
    poster: String?,
    backdrop: String?,
    portrait: Boolean,
): MobileDetailsArtworkSource? {
    val posterUrl = poster?.takeIf(String::isNotBlank)
    val backdropUrl = backdrop?.takeIf(String::isNotBlank)
    return when {
        portrait && posterUrl != null && posterUrl.substringAfterLast('/') != backdropUrl?.substringAfterLast('/') ->
            MobileDetailsArtworkSource(posterUrl, true)
        backdropUrl != null -> MobileDetailsArtworkSource(backdropUrl, false)
        posterUrl != null -> MobileDetailsArtworkSource(posterUrl, true)
        else -> null
    }
}

internal data class DetailsTvHeroBounds(
    val rowsHeightPx: Int,
    val heroOffsetYPx: Int,
)

internal fun resolveDetailsTvHeroBounds(
    containerHeightPx: Int,
    heroHeightPx: Int,
    restingRowsHeightPx: Int,
    expandedRowsHeightPx: Int,
    expansionProgress: Float,
    gapPx: Float,
): DetailsTvHeroBounds {
    val reservedHeight = ceil(heroHeightPx + gapPx).toInt()
        .coerceIn(0, containerHeightPx)
    val restingHeight = restingRowsHeightPx.coerceIn(0, containerHeightPx - reservedHeight)
    val expandedHeight = expandedRowsHeightPx.coerceIn(restingHeight, containerHeightPx)
    val rowsHeight = (restingHeight +
        (expandedHeight - restingHeight) * expansionProgress.coerceIn(0f, 1f)).toInt()

    // Expand the rails without sacrificing poster height or changing the gap to the hero.
    return DetailsTvHeroBounds(
        rowsHeightPx = rowsHeight,
        heroOffsetYPx = restingHeight - rowsHeight,
    )
}
