package com.arflix.tv.ui.screens.collections

import android.os.SystemClock
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** How long after a collection opens its rows still rise in; later ones (scrolled to) just show. */
private const val ENTRANCE_WINDOW_MS = 1_600L
private const val ENTRANCE_STAGGER_MS = 70L
private const val ENTRANCE_MAX_DELAY_MS = 350L

/** When the collection page opened, so rows know whether they are part of its opening. */
@Composable
internal fun rememberCollectionOpenedAt(): Long = remember { SystemClock.uptimeMillis() }

/**
 * Rows of a collection that has just opened fade in and rise a little, one after another
 * ([index] sets the order). A row composed again later (scrolled back into view) shows at once.
 */
@Composable
internal fun Modifier.collectionEntrance(openedAt: Long, index: Int): Modifier {
    val animate = remember { SystemClock.uptimeMillis() - openedAt < ENTRANCE_WINDOW_MS }
    if (!animate) return this
    val progress = remember { Animatable(0f) }
    val rise = with(LocalDensity.current) { 22.dp.toPx() }
    LaunchedEffect(Unit) {
        delay((index * ENTRANCE_STAGGER_MS).coerceAtMost(ENTRANCE_MAX_DELAY_MS))
        launch { progress.animateTo(1f, tween(420, easing = FastOutSlowInEasing)) }
    }
    return graphicsLayer {
        val p = progress.value
        alpha = p
        translationY = (1f - p) * rise
    }
}
