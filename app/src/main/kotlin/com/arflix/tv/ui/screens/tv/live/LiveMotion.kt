package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

/** Shared motion for Live TV page changes: short, decelerating, never on the first frame. */
internal object LiveMotion {
    const val PAGE_ENTER_MS = 260
    val PAGE_ENTER_OFFSET = 40.dp
}

/**
 * Slides a page in when the user moves between Live TV pages (groups -> channels and back).
 * [animate] is false on the very first composition, so opening Live TV still paints the
 * guide fully on its first frame. Only the layer moves, so the page is not re-laid out or
 * recomposed while it slides.
 *
 * Deliberately no fade: the page is opaque from its first frame, so there is never a black
 * gap between the old and new page, and the live video (a SurfaceView, which ignores layer
 * alpha) moves together with the page instead of popping in ahead of it.
 */
internal fun Modifier.livePageEnter(animate: Boolean, forward: Boolean): Modifier = composed {
    val progress = remember { Animatable(if (animate) 0f else 1f) }
    val offsetPx = with(LocalDensity.current) { LiveMotion.PAGE_ENTER_OFFSET.toPx() }
    LaunchedEffect(Unit) {
        if (progress.value < 1f) {
            progress.animateTo(1f, tween(LiveMotion.PAGE_ENTER_MS, easing = FastOutSlowInEasing))
        }
    }
    graphicsLayer {
        val p = progress.value
        translationX = (1f - p) * offsetPx * (if (forward) 1f else -1f)
    }
}
