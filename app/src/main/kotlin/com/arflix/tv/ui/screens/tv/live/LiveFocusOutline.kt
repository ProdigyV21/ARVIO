package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp

/** Animate only drawing: focus must not resize or remeasure guide content. */
@Composable
internal fun Modifier.liveFocusOutline(focused: Boolean, radius: Dp): Modifier {
    val alpha = animateFloatAsState(
        if (focused) 1f else 0f,
        tween(if (focused) 70 else 120),
        label = "live-focus-outline",
    )
    return drawWithContent {
        drawContent()
        if (alpha.value > 0f) {
            val stroke = LiveDims.FocusBorder.toPx()
            drawRoundRect(
                color = LiveColors.FocusRing.copy(alpha = alpha.value),
                topLeft = Offset(stroke / 2f, stroke / 2f),
                size = Size((size.width - stroke).coerceAtLeast(0f), (size.height - stroke).coerceAtLeast(0f)),
                cornerRadius = CornerRadius((radius.toPx() - stroke / 2f).coerceAtLeast(0f)),
                style = Stroke(stroke),
            )
        }
    }
}
