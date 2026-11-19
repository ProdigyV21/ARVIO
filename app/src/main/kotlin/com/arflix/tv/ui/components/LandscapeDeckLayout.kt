package com.arflix.tv.ui.components

import kotlin.math.min

/** Shared geometry for the hero container and its cards, measured inside the navigation shell. */
data class LandscapeDeckLayout(
    val cardWidthDp: Float,
    val cardHeightDp: Float,
    val wingStepDp: Float,
    val aspectRatio: Float,
    val compact: Boolean,
    val visibleCards: Int,
)

fun landscapeDeckLayout(availableWidthDp: Float, availableHeightDp: Float): LandscapeDeckLayout {
    val width = availableWidthDp.coerceAtLeast(1f)
    val height = availableHeightDp.coerceAtLeast(1f)
    val compact = height < 480f || width < 840f
    val aspectRatio = 16f / 9f
    val heightBudget = (height * if (compact) 0.66f else 0.55f) - 24f
    val cardWidth = min(width * if (compact) 0.72f else 0.64f,
        heightBudget.coerceAtLeast(1f) * aspectRatio).coerceAtMost(840f)
    val wingSpace = ((width - cardWidth) / 2f).coerceAtLeast(0f)
    return LandscapeDeckLayout(
        cardWidthDp = cardWidth,
        cardHeightDp = cardWidth / aspectRatio,
        aspectRatio = aspectRatio,
        wingStepDp = min((wingSpace * 0.42f).coerceIn(48f, 84f), wingSpace / 2f),
        compact = compact,
        visibleCards = 5,
    )
}
