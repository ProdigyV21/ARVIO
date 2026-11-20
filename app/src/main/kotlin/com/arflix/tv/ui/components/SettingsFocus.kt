package com.arflix.tv.ui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.Pink

/**
 * Uniform focus indicator for settings rows: a border in the app's theme accent
 * colour, never a fill. Every row in the settings screen should use this (or an
 * equivalent 2dp accent border) so focus looks the same everywhere.
 */
internal fun Modifier.settingsFocusOutline(
    isFocused: Boolean,
    cornerRadius: Dp = 8.dp
): Modifier = if (!isFocused) this else composed {
    val accent = resolveAccentColor(fallback = Pink)
    drawWithCache {
        val strokeWidth = 2.dp.toPx()
        val inset = strokeWidth / 2f
        val origin = Offset(inset, inset)
        val bounds = Size((size.width - strokeWidth).coerceAtLeast(0f), (size.height - strokeWidth).coerceAtLeast(0f))
        val radius = CornerRadius((cornerRadius.toPx() - inset).coerceAtLeast(0f))
        val outline = Stroke(strokeWidth)
        onDrawWithContent {
            drawContent()
            drawRoundRect(accent, origin, bounds, radius, style = outline)
        }
    }
}
