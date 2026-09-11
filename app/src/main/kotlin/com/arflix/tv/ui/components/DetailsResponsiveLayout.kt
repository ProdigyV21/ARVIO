package com.arflix.tv.ui.components

import kotlin.math.ceil

internal fun resolveDetailsBackdropHeightDp(
    screenWidthDp: Int,
    screenHeightDp: Int,
    isPhone: Boolean,
): Float {
    val isPhoneLandscape = isPhone && screenWidthDp > screenHeightDp
    return if (isPhoneLandscape) {
        (screenHeightDp * 0.55f).coerceIn(190f, 220f)
    } else {
        (screenHeightDp * 0.53f).coerceAtLeast(400f)
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
