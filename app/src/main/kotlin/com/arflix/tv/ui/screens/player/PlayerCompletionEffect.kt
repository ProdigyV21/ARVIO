package com.arflix.tv.ui.screens.player

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue

/** Every completed on-demand video must either offer its next episode or leave the player. */
@Composable
internal fun PlayerCompletionEffect(
    ended: Boolean,
    enabled: Boolean,
    blocked: Boolean,
    waitingForNextEpisode: Boolean,
    offerNextEpisode: Boolean,
    onNextEpisode: () -> Unit,
    onFinish: () -> Unit,
) {
    var handled by remember(ended) { mutableStateOf(false) }
    val latestNext by rememberUpdatedState(onNextEpisode)
    val latestFinish by rememberUpdatedState(onFinish)
    LaunchedEffect(ended, enabled, blocked, waitingForNextEpisode, offerNextEpisode) {
        if (!ended || !enabled || blocked || handled) return@LaunchedEffect
        // Metadata normally resolves during playback. A short clip, failed lookup or offline
        // server must not leave STATE_ENDED (often a black final frame) on screen indefinitely.
        if (waitingForNextEpisode) kotlinx.coroutines.delay(3_000L)
        handled = true
        if (offerNextEpisode && !waitingForNextEpisode) latestNext() else latestFinish()
    }
}
