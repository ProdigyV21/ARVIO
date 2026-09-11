package com.arflix.tv.ui.screens.player.common

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.arflix.tv.util.DeviceType

/**
 * Manages transient edge-to-edge system bars for the standard mobile video player.
 *
 * Sets [WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE] once while active.
 * Shows or hides system bars based on [showBars] (the player controls visibility state):
 *   - showBars == true  -> shows Android status & navigation bars
 *   - showBars == false -> hides Android status & navigation bars
 *
 * On disposal (e.g. exiting the player), restores [WindowInsetsControllerCompat.BEHAVIOR_DEFAULT]
 * and shows system bars so the rest of the app operates normally.
 */
@Composable
fun PlayerSystemBarsEffect(
    activity: Activity?,
    showBars: Boolean,
    enabled: Boolean = true,
    deviceType: DeviceType = DeviceType.PHONE
) {
    if (deviceType == DeviceType.TV || !enabled) return

    val window = activity?.window ?: return
    val decorView = window.decorView ?: return

    DisposableEffect(activity, enabled) {
        val controller = WindowCompat.getInsetsController(window, decorView)
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        onDispose {
            val ctrl = WindowCompat.getInsetsController(window, decorView)
            ctrl.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            ctrl.show(WindowInsetsCompat.Type.systemBars())
        }
    }

    LaunchedEffect(showBars) {
        val controller = WindowCompat.getInsetsController(window, decorView)
        if (showBars) {
            controller.show(WindowInsetsCompat.Type.systemBars())
        } else {
            controller.hide(WindowInsetsCompat.Type.systemBars())
        }
    }
}
