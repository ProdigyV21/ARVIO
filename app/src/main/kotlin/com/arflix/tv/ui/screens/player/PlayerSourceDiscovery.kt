package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.repository.ProgressiveStreamResult
import com.arflix.tv.data.repository.providerScopedStreamIdentity
import com.arflix.tv.ui.screens.details.isAutoPlayableStream
import com.arflix.tv.ui.screens.details.qualityScoreForAutoPlay
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withTimeoutOrNull

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

private data class PluginSources(val streams: List<StreamSource>, val finished: Boolean)

/** Both producers can yield playable sources without waiting for the other's first result. */
internal fun mergePlayerSourceDiscovery(
    addons: Flow<ProgressiveStreamResult>,
    pluginBatches: Flow<List<StreamSource>>,
    onPluginFailure: (Throwable) -> Unit = {}
): Flow<ProgressiveStreamResult> {
    val plugins = channelFlow {
        var sources = emptyList<StreamSource>()
        send(PluginSources(sources, false))
        try {
            withTimeoutOrNull(30_000L) {
                pluginBatches.collect { batch ->
                    sources = (sources + batch).distinctBy(::providerScopedStreamIdentity)
                    send(PluginSources(sources, false))
                }
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            onPluginFailure(e)
        }
        send(PluginSources(sources, true))
    }
    return combine(
        addons.onStart { emit(ProgressiveStreamResult(emptyList(), completedAddons = 0, totalAddons = 0, isFinal = false)) },
        plugins
    ) { addon, plugin ->
        addon.copy(
            streams = (addon.streams + plugin.streams).distinctBy(::providerScopedStreamIdentity),
            isFinal = addon.isFinal && plugin.finished
        )
    }
}
