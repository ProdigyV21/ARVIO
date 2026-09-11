package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.roundToInt

/** Read in a graphicsLayer block to keep the video anchored at the right edge. */
internal val LocalLiveDrawerTranslation = staticCompositionLocalOf<() -> Float> { { 0f } }
internal val LocalLiveDrawerVisible = staticCompositionLocalOf { true }

/** Measure once per toggle; only render layers move during the animation. */
@Composable
internal fun LiveDrawerWorkspace(
    expanded: Boolean,
    sidebar: @Composable () -> Unit,
    content: @Composable () -> Unit,
    contentKey: String = "guide",
    sidebarWidth: androidx.compose.ui.unit.Dp = LiveDims.SidebarExpanded,
) {
    val destinations = rememberSaveableStateHolder()
    val progress = animateFloatAsState(
        if (expanded) 1f else 0f,
        tween(260, easing = CubicBezierEasing(0.2f, 0f, 0f, 1f)),
        label = "guide-drawer",
    )
    val visible by remember(expanded) { derivedStateOf { expanded || progress.value > 0f } }
    val measuredSidebarWidth = remember { mutableIntStateOf(0) }
    val target = if (expanded) 1f else 0f
    val density = androidx.compose.ui.platform.LocalDensity.current
    val sidebarPixels = with(density) { sidebarWidth.toPx() }
    Layout(modifier = Modifier.fillMaxSize().clipToBounds(), content = {
        CompositionLocalProvider(LocalLiveDrawerVisible provides visible) {
            Box(Modifier.fillMaxSize()) { sidebar() }
        }
        CompositionLocalProvider(LocalLiveDrawerTranslation provides { measuredSidebarWidth.intValue * (progress.value - target) }) {
            Box(Modifier.fillMaxSize()) {
                destinations.SaveableStateProvider(contentKey) { content() }
            }
        }
    }) { children, constraints ->
        val sideWidth = sidebarPixels.roundToInt().coerceAtMost(constraints.maxWidth / 2)
        measuredSidebarWidth.intValue = sideWidth
        val reserved = if (expanded) sideWidth else 0
        val side = children[0].measure(Constraints.fixed(sideWidth, constraints.maxHeight))
        val body = children[1].measure(Constraints.fixed(constraints.maxWidth - reserved, constraints.maxHeight))
        layout(constraints.maxWidth, constraints.maxHeight) {
            val direction = if (layoutDirection == LayoutDirection.Rtl) -1f else 1f
            side.placeRelativeWithLayer(0, 0) {
                translationX = direction * sideWidth * (progress.value - 1f)
            }
            body.placeRelativeWithLayer(reserved, 0) {
                translationX = direction * (sideWidth * progress.value - reserved)
            }
        }
    }
}
