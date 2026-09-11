package com.arflix.tv.ui.screens.tv.live

import androidx.compose.runtime.Immutable
import kotlin.math.ceil

/** Quantized overscan avoids changing the composed cell set on each scroll pixel. */
@Immutable
internal data class GuideRenderWindow(val startMinute: Int, val endMinute: Int) {
    fun intersects(start: Int, end: Int): Boolean = end > startMinute && start < endMinute
}

internal fun guideRenderWindow(scrollPx: Int, viewportPx: Float, pixelsPerMinute: Float): GuideRenderWindow {
    if (pixelsPerMinute <= 0f) return GuideRenderWindow(0, Int.MAX_VALUE)
    val start = scrollPx.coerceAtLeast(0) / pixelsPerMinute
    val end = start + viewportPx.coerceAtLeast(0f) / pixelsPerMinute
    return GuideRenderWindow(
        startMinute = ((start.toInt() / 30) * 30 - 30).coerceAtLeast(0),
        endMinute = ceil(end / 30f).toInt() * 30 + 30,
    )
}
