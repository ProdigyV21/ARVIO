package com.arflix.tv.ui.screens.music

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.focusGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.arflix.tv.music.MusicAlbum
import com.arflix.tv.music.MusicTrack
import com.arflix.tv.util.LocalDeviceType
import kotlinx.coroutines.delay

/** True on a TV, where the Music tab is driven with a remote's D-pad instead of touch. */
@Composable
internal fun isMusicTv(): Boolean = !LocalDeviceType.current.isTouchDevice()

/**
 * On TV, a white ring (and, with [lift], a slight zoom) on whatever the remote is on; touch screens
 * keep their ripple only. It goes before the element's clickable in the chain, so it sees that
 * element's focus, or a child's (an IconButton's own clickable).
 */
internal fun Modifier.tvFocusRing(shape: Shape, lift: Boolean = false): Modifier = composed {
    if (!isMusicTv()) return@composed Modifier
    var focused by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (focused && lift) 1.06f else 1f, label = "music_focus_lift")
    Modifier
        .onFocusChanged { focused = it.hasFocus }
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .then(if (focused) Modifier.border(3.dp, Color.White, shape) else Modifier)
}

/**
 * On TV, puts the remote on [requester] when the page shows (or [key] changes), so a page never
 * opens with nothing focused. Lazy lists compose their items a frame late, hence the retries.
 */
@Composable
internal fun rememberTvInitialFocus(key: Any? = Unit): FocusRequester {
    val requester = remember { FocusRequester() }
    if (isMusicTv()) {
        LaunchedEffect(key) {
            repeat(10) {
                if (runCatching { requester.requestFocus() }.isSuccess) return@LaunchedEffect
                delay(50)
            }
        }
    }
    return requester
}

/** Keeps the remote inside this part of the screen, e.g. the full player over the library behind it. */
internal fun Modifier.trapTvFocus(): Modifier =
    focusProperties { onExit = { cancelFocusChange() } }.focusGroup()

/**
 * On TV, starting songs opens the full player, where the remote's controls are: the mini player
 * sits below lists that can run to hundreds of songs. A phone keeps the mini player a tap away.
 */
internal class OpenPlayerOnPlay(private val actions: MusicActions) : MusicActions by actions {
    override fun play(tracks: List<MusicTrack>, index: Int) {
        actions.play(tracks, index)
        actions.setPlayerOpen(true)
    }

    override fun shuffle(tracks: List<MusicTrack>) {
        actions.shuffle(tracks)
        actions.setPlayerOpen(true)
    }

    override fun playAlbum(album: MusicAlbum) {
        actions.playAlbum(album)
        actions.setPlayerOpen(true)
    }
}
