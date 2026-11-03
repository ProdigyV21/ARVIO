package com.arflix.tv.ui.screens.home

import androidx.media3.common.Player
import com.arflix.tv.data.repository.IptvStreamPolicy

/** What one channel card's hero preview may do under its account's connection limit. */
internal data class HomeIptvPreviewGate(val itemId: Int, val allowed: Boolean, val stopOnLeave: Boolean)

/** Holds only for the card it was read for, so a moved focus can never borrow the last card's answer. */
internal fun HomeIptvPreviewGate?.allows(itemId: Int): Boolean = this?.itemId == itemId && allowed

/** A full account found at prepare time takes back only that card's preview. */
internal fun HomeIptvPreviewGate?.refused(itemId: Int): HomeIptvPreviewGate? = if (this?.itemId == itemId) copy(allowed = false) else this

/** A null policy keeps today's preview and pause; an unreadable one opens nothing on the account. */
internal suspend fun readHomeIptvPreviewGate(itemId: Int, policy: suspend () -> IptvStreamPolicy?): HomeIptvPreviewGate =
    runCatching {
        val read = policy()
        HomeIptvPreviewGate(itemId, allowed = read?.homePreviewAllowed ?: true, stopOnLeave = read != null)
    }.onFailure { if (it is kotlinx.coroutines.CancellationException) throw it }
        .getOrDefault(HomeIptvPreviewGate(itemId, allowed = false, stopOnLeave = true))

/** The URL a channel card's hero may play; nothing until its channel is known and its gate has answered. */
internal fun homeIptvPreviewUrl(
    itemId: Int?,
    gate: HomeIptvPreviewGate?,
    streamUrl: (Int) -> String?,
): String? = itemId?.takeIf { gate.allows(it) }?.let(streamUrl)

/** What a prepared hero must do when focus leaves, fixed when it was prepared, since the gate may answer for another card by then. */
internal fun homeHeroStopsOnLeave(isHeroIptv: Boolean, gate: HomeIptvPreviewGate?): Boolean = isHeroIptv && gate?.stopOnLeave == true

/** Stopping matters on a limited account: a paused loader keeps its socket, and so its connection. */
internal fun Player.leaveHomeHeroPreview(stop: Boolean) {
    playWhenReady = false
    if (stop) {
        stop()
        clearMediaItems()
    }
}
