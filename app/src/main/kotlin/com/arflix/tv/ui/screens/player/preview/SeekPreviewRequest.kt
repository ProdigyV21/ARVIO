package com.arflix.tv.ui.screens.player.preview

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withTimeoutOrNull

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
