package com.arflix.tv.ui.components

import android.content.Context
import android.view.accessibility.AccessibilityManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

/**
 * True while an accessibility service is on but no screen reader is.
 *
 * Many TV launchers and remote helpers keep an accessibility service enabled. Compose then walks
 * and diffs the whole semantics tree every few frames, which on a 4-core TV took about half of the
 * main thread's time while scrolling Home. Nobody listens to the inner nodes of a media card in
 * that state, so cards collapse them into one labelled, clickable node. With a screen reader
 * (touch exploration) on, or with no service at all, the full tree stays as it is.
 */
val LocalLightweightSemantics = staticCompositionLocalOf { false }

@Composable
fun rememberLightweightSemantics(): Boolean {
    val context = LocalContext.current
    val manager = remember(context) {
        context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager
    }
    var lightweight by remember(manager) { mutableStateOf(manager.wantsLightweightSemantics()) }
    DisposableEffect(manager) {
        if (manager == null) return@DisposableEffect onDispose {}
        val stateListener = AccessibilityManager.AccessibilityStateChangeListener {
            lightweight = manager.wantsLightweightSemantics()
        }
        val touchListener = AccessibilityManager.TouchExplorationStateChangeListener {
            lightweight = manager.wantsLightweightSemantics()
        }
        manager.addAccessibilityStateChangeListener(stateListener)
        manager.addTouchExplorationStateChangeListener(touchListener)
        lightweight = manager.wantsLightweightSemantics()
        onDispose {
            manager.removeAccessibilityStateChangeListener(stateListener)
            manager.removeTouchExplorationStateChangeListener(touchListener)
        }
    }
    return lightweight
}

internal fun AccessibilityManager?.wantsLightweightSemantics(): Boolean =
    this != null && isEnabled && !isTouchExplorationEnabled
