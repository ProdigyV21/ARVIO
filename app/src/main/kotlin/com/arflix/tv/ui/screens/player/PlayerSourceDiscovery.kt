package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.ui.screens.details.isAutoPlayableStream
import com.arflix.tv.ui.screens.details.qualityScoreForAutoPlay

internal fun eligiblePlayerAutoplayStreams(streams: List<StreamSource>, minimumQuality: Int): List<StreamSource> =
    streams.filter { isAutoPlayableStream(it) && qualityScoreForAutoPlay(it) >= minimumQuality }

internal enum class PlayerAutoplayAvailability { SEARCHING, READY, NO_MATCH, NO_SOURCES, SELECTED }

internal fun playerAutoplayAvailability(
    streams: List<StreamSource>, minimumQuality: Int, searchActive: Boolean, hasSelection: Boolean
): PlayerAutoplayAvailability = when {
    hasSelection -> PlayerAutoplayAvailability.SELECTED
    eligiblePlayerAutoplayStreams(streams, minimumQuality).isNotEmpty() -> PlayerAutoplayAvailability.READY
    searchActive -> PlayerAutoplayAvailability.SEARCHING
    streams.isEmpty() -> PlayerAutoplayAvailability.NO_SOURCES
    else -> PlayerAutoplayAvailability.NO_MATCH
}
