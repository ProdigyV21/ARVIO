package com.arflix.tv.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Measures the hero first so the second child (the scrolling rails) cannot cover it. */
@Composable
internal fun DetailsTvHeroLayout(
    restingRowsHeight: Dp,
    expandedRowsHeight: Dp,
    expansionProgress: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        val hero = measurables[0].measure(constraints.copy(minWidth = 0, minHeight = 0))
        val bounds = resolveDetailsTvHeroBounds(
            containerHeightPx = constraints.maxHeight,
            heroHeightPx = hero.height,
            restingRowsHeightPx = restingRowsHeight.roundToPx(),
            expandedRowsHeightPx = expandedRowsHeight.roundToPx(),
            expansionProgress = expansionProgress,
            gapPx = 12.dp.toPx(),
        )
        val rows = measurables[1].measure(
            constraints.copy(minHeight = bounds.rowsHeightPx, maxHeight = bounds.rowsHeightPx)
        )

        layout(constraints.maxWidth, constraints.maxHeight) {
            hero.placeRelativeWithLayer(0, 0) {
                translationY = bounds.heroOffsetYPx.toFloat()
            }
            rows.placeRelative(0, constraints.maxHeight - rows.height)
        }
    }
}
