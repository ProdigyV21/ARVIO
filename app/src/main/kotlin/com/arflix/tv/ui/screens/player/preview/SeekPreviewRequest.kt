package com.arflix.tv.ui.screens.player.preview

import com.arflix.tv.data.model.IptvVodSourceIds
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withTimeoutOrNull

// Provider image previews and already captured frames remain available. A second
// video connection may violate the IPTV subscription's single-connection limit.
internal fun allowSecondarySeekPreviewExtraction(memoryClassMb: Int, addonId: String?): Boolean =
    memoryClassMb >= 192 && !IptvVodSourceIds.isIptvVodAddonId(addonId)

/** A cancelled decoder request is a missing frame, not a cancelled UI loading effect. */
internal suspend fun loadSeekPreviewFrame(provider: SeekPreviewFrameProvider, targetMs: Long): SeekPreviewFrame? =
    try {
        withTimeoutOrNull(SEEK_PREVIEW_REQUEST_TIMEOUT_MS + 1_000L) {
            provider.cachedFrameAt(targetMs) ?: provider.frameAt(targetMs)
        }
    } catch (cancelled: CancellationException) {
        currentCoroutineContext().ensureActive()
        null
    }
