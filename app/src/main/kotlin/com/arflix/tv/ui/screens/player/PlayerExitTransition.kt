package com.arflix.tv.ui.screens.player

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.zIndex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class PlayerExitTransition(
    private val scope: CoroutineScope,
    private val animateExit: Boolean,
    private val pause: () -> Unit,
    private val leave: () -> Unit
) {
    var isExiting by mutableStateOf(false)
        private set
    private val fade = Animatable(0f)
    val alpha: Float get() = if (isExiting && !animateExit) 1f else fade.value

    fun requestExit() {
        if (isExiting) return
        isExiting = true
        runCatching(pause)
        if (!animateExit) {
            leave()
            return
        }
        scope.launch {
            fade.animateTo(1f, tween(180, easing = FastOutSlowInEasing))
            // Keep the opaque scrim on screen for a frame before navigation removes the SurfaceView.
            withFrameNanos { }
            leave()
        }
    }
}

@Composable
internal fun rememberPlayerExitTransition(
    animateExit: Boolean,
    pause: () -> Unit,
    leave: () -> Unit
): PlayerExitTransition {
    val scope = rememberCoroutineScope()
    val latestPause by rememberUpdatedState(pause)
    val latestLeave by rememberUpdatedState(leave)
    return remember(scope, animateExit) {
        PlayerExitTransition(scope, animateExit, { latestPause() }, { latestLeave() })
    }
}

@Composable
internal fun PlayerExitScrim(exit: PlayerExitTransition) {
    BackHandler(enabled = exit.isExiting) { }
    if (exit.isExiting) {
        Box(
            Modifier.fillMaxSize()
                .testTag("playerExitScrim")
                .background(Color.Black.copy(alpha = exit.alpha))
                .zIndex(100f)
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            awaitPointerEvent(PointerEventPass.Initial).changes.forEach { it.consume() }
                        }
                    }
                }
        )
    }
}
