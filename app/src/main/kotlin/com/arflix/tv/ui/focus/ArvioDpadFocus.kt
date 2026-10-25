package com.arflix.tv.ui.focus

import android.view.KeyEvent as AndroidKeyEvent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.gestures.BringIntoViewSpec
import androidx.compose.foundation.gestures.LocalBringIntoViewSpec
import androidx.compose.foundation.relocation.BringIntoViewResponder
import androidx.compose.foundation.relocation.bringIntoViewResponder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.key.Key
import kotlin.Unit

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
fun Modifier.arvioDpadFocusGroup(
    enableFocusRestorer: Boolean = true
): Modifier {
    // Compose's focusRestorer(onRestoreFailed = { restoreFocusRequester }) captures the
    // requester at modifier-application time and, on a focus enter whose automatic restore
    // fails, calls requestFocus() ITSELF — deep inside the modifier, without a try/catch.
    // When the restore target lives in a LazyColumn row that is not yet composed/attached
    // (the case right after categories finish loading on a fresh TV entry), that internal
    // requestFocus() throws IllegalStateException: FocusRequester is not initialized and
    // crashes the app, unreachable by the runCatching guards wrapping direct calls.
    //
    // Keep focus restoration limited to Compose's own saved focus history. Callers that
    // need an initial focus target should request it after the target is attached instead
    // of supplying a fallback that Compose can invoke before a lazy item is composed.
    val restorer = if (enableFocusRestorer) Modifier.focusRestorer() else Modifier
    return this.then(restorer).focusGroup()
}

@OptIn(ExperimentalFoundationApi::class)
private object ArvioNoOpBringIntoViewResponder : BringIntoViewResponder {
    override fun calculateRectForParent(localRect: Rect): Rect = localRect

    override suspend fun bringChildIntoView(localRect: () -> Rect?) = Unit
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.arvioManualBringIntoViewBoundary(): Modifier {
    return bringIntoViewResponder(ArvioNoOpBringIntoViewResponder)
}

/**
 * The focus scrolling the tv-foundation lazy lists had built in: a focused item's leading edge
 * moves to [parentFraction] of the viewport, and nothing scrolls while user scrolling is off.
 */
@OptIn(ExperimentalFoundationApi::class)
private data class ArvioTvListBringIntoViewSpec(
    val parentFraction: Float,
    val userScrollEnabled: Boolean
) : BringIntoViewSpec {
    override fun calculateScrollDistance(offset: Float, size: Float, containerSize: Float): Float {
        if (!userScrollEnabled) return 0f
        val initialTarget = parentFraction * containerSize
        val target = if (size <= containerSize && containerSize - initialTarget < size) {
            containerSize - size
        } else {
            initialTarget
        }
        return offset - target
    }
}

/**
 * Gives the foundation lazy lists in [content] the focus scrolling of the tv-foundation lists they
 * replaced. Without it they follow the platform default, which only pivots on FEATURE_LEANBACK
 * devices (not no-touch boxes or the "tv" device mode) and still scrolls to focus when
 * userScrollEnabled is false.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArvioTvListFocusScrolling(
    parentFraction: Float = 0.3f,
    userScrollEnabled: Boolean = true,
    content: @Composable () -> Unit
) {
    val spec = remember(parentFraction, userScrollEnabled) {
        ArvioTvListBringIntoViewSpec(parentFraction, userScrollEnabled)
    }
    CompositionLocalProvider(LocalBringIntoViewSpec provides spec, content = content)
}

fun isArvioDpadNavigationKey(key: Key): Boolean {
    return key == Key.DirectionLeft ||
        key == Key.DirectionRight ||
        key == Key.DirectionUp ||
        key == Key.DirectionDown
}

/**
 * Mirrors a horizontal D-pad key when the layout direction is RTL.
 *
 * In an RTL locale the IPTV screen's visuals are mirrored by Compose, but its
 * navigation code is written against an LTR mental model (Left = toward the
 * sidebar / earlier, Right = deeper / later). Feeding the mirrored key into that
 * code keeps focus movement pointing the same way the eye expects. Vertical and
 * non-directional keys pass through untouched.
 */
fun Key.mirrorHorizontalForRtl(isRtl: Boolean): Key {
    if (!isRtl) return this
    return when (this) {
        Key.DirectionLeft -> Key.DirectionRight
        Key.DirectionRight -> Key.DirectionLeft
        else -> this
    }
}

@Stable
class ArvioDpadRepeatGate(
    private val horizontalMinRepeatIntervalMs: Long,
    private val verticalMinRepeatIntervalMs: Long = horizontalMinRepeatIntervalMs
) {
    private var lastKeyCode: Int = Int.MIN_VALUE
    private var lastHandledAtMs: Long = 0L

    fun shouldSkip(keyCode: Int, repeatCount: Int, nowMs: Long): Boolean {
        if (repeatCount <= 0) {
            lastKeyCode = keyCode
            lastHandledAtMs = nowMs
            return false
        }

        val minRepeatIntervalMs = when (keyCode) {
            AndroidKeyEvent.KEYCODE_DPAD_UP,
            AndroidKeyEvent.KEYCODE_DPAD_DOWN -> verticalMinRepeatIntervalMs
            else -> horizontalMinRepeatIntervalMs
        }
        val skip = keyCode == lastKeyCode && nowMs - lastHandledAtMs < minRepeatIntervalMs
        if (!skip) {
            lastKeyCode = keyCode
            lastHandledAtMs = nowMs
        }
        return skip
    }

    fun reset() {
        lastKeyCode = Int.MIN_VALUE
        lastHandledAtMs = 0L
    }
}

@Composable
fun rememberArvioDpadRepeatGate(
    minRepeatIntervalMs: Long = 82L,
    horizontalMinRepeatIntervalMs: Long = minRepeatIntervalMs,
    verticalMinRepeatIntervalMs: Long = minRepeatIntervalMs
): ArvioDpadRepeatGate {
    return remember(horizontalMinRepeatIntervalMs, verticalMinRepeatIntervalMs) {
        ArvioDpadRepeatGate(
            horizontalMinRepeatIntervalMs = horizontalMinRepeatIntervalMs,
            verticalMinRepeatIntervalMs = verticalMinRepeatIntervalMs
        )
    }
}
