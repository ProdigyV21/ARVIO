package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.repository.ProgressiveStreamResult
import com.arflix.tv.data.repository.toStreamSource
import com.arflix.tv.domain.model.LocalScraperResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PlayerSourceDiscoveryTest {
    private fun stream(id: String, quality: String = "1080p") = StreamSource(
        source = id, addonName = id, addonId = id, quality = quality, size = "2 GB", url = "https://example.com/$id"
    )
    private fun result(streams: List<StreamSource> = emptyList(), final: Boolean = true) =
        ProgressiveStreamResult(streams, completedAddons = 1, totalAddons = 1, isFinal = final)

    @Test fun latePluginSourcesAreNotLostAfterAddonsFinish() = runTest {
        val plugin = stream("plugin")
        val emissions = mergePlayerSourceDiscovery(flowOf(result()), flow {
            delay(1_000)
            emit(listOf(plugin))
        }).toList()
        assertFalse(emissions.first().isFinal)
        assertEquals(listOf(plugin), emissions.last().streams)
        assertTrue(emissions.last().isFinal)
    }

    @Test fun pluginCanProduceBeforeTheFirstAddonResponse() = runTest {
        val plugin = stream("plugin")
        val addon = stream("addon")
        val emissions = mergePlayerSourceDiscovery(
            flow { delay(5_000); emit(result(listOf(addon))) },
            flow { delay(100); emit(listOf(plugin)) }
        ).toList()
        assertTrue(emissions.any { it.streams == listOf(plugin) && !it.isFinal })
        assertEquals(setOf(plugin, addon), emissions.last().streams.toSet())
    }

    @Test fun pluginFailurePreservesAlreadyDiscoveredSources() = runTest {
        val addon = stream("addon")
        val plugin = stream("plugin")
        var failure: Throwable? = null
        val emissions = mergePlayerSourceDiscovery(flowOf(result(listOf(addon))), flow {
            emit(listOf(plugin))
            delay(50)
            error("scraper failed")
        }, { failure = it }).toList()
        assertEquals("scraper failed", failure?.message)
        assertEquals(setOf(addon, plugin), emissions.last().streams.toSet())
        assertTrue(emissions.last().isFinal)
    }

    @Test fun stalledPluginHasABoundedLifetime() = runTest {
        val emissions = mergePlayerSourceDiscovery(flowOf(result()), flow { awaitCancellation() }).toList()
        assertEquals(30_000L, testScheduler.currentTime)
        assertTrue(emissions.last().isFinal)
    }

    @Test fun slowDownstreamDoesNotCauseFlowExceptionTransparencyViolation() = runTest {
        val plugin = stream("plugin")
        val job = launch {
            mergePlayerSourceDiscovery(
                flowOf(result(listOf(stream("addon")), final = true)),
                flow {
                    emit(listOf(plugin))
                    emit(listOf(plugin))
                    awaitCancellation()
                }
            ).collect {
                delay(35_000L)
            }
        }
        advanceTimeBy(35_000L)
        job.cancel()
    }

    @Test fun leavingPlayerCancelsDiscoveryWithoutReportingFailure() = runTest {
        var cancelled = false
        var failed = false
        val job = launch {
            mergePlayerSourceDiscovery(flowOf(result()), flow {
                try { awaitCancellation() } finally { cancelled = true }
            }, { failed = true }).collect {}
        }
        runCurrent()
        advanceTimeBy(100)
        job.cancel()
        job.join()
        assertTrue(cancelled)
        assertFalse(failed)
    }

    @Test fun minimumQualityExcludesLowerQualityButKeepsNativeMkvSources() {
        val low = stream("low", "720p")
        val high = stream("Movie 2160p REMUX", "Unknown").copy(
            behaviorHints = com.arflix.tv.data.model.StreamBehaviorHints(notWebReady = true)
        )
        assertEquals(listOf(high), eligiblePlayerAutoplayStreams(listOf(low, high), 3))
        assertTrue(eligiblePlayerAutoplayStreams(listOf(low), 4).isEmpty())
    }

    @Test fun pendingDebridAndMagnetSourcesAreNeverAutoplayed() {
        val ready = stream("ready")
        assertEquals(listOf(ready), eligiblePlayerAutoplayStreams(listOf(
            ready, stream("Torrent being downloaded"), stream("magnet").copy(url = "magnet:?xt=urn:btih:abc")
        ), 0))
    }

    @Test fun pluginHeadersAndIdentitySurviveConversion() {
        val headers = mapOf("Referer" to "https://example.com/player", "User-Agent" to "ExamplePlayer")
        val converted = LocalScraperResult("Movie", url = "https://example.com/stream", provider = "My Plugin", headers = headers)
            .toStreamSource()
        assertEquals(headers, converted.behaviorHints?.proxyHeaders?.request)
        assertEquals("plugin_my_plugin", converted.addonId)
    }

    @Test fun completedEmptySearchCannotStayOnLoadingScreen() {
        assertEquals(PlayerAutoplayAvailability.SEARCHING, playerAutoplayAvailability(emptyList(), 0, true, false))
        assertEquals(PlayerAutoplayAvailability.NO_SOURCES, playerAutoplayAvailability(emptyList(), 0, false, false))
    }

    @Test fun belowMinimumSourcesWaitUntilSearchFinishesThenRequireManualChoice() {
        val sources = listOf(stream("low", "720p"))
        assertEquals(PlayerAutoplayAvailability.SEARCHING, playerAutoplayAvailability(sources, 4, true, false))
        assertEquals(PlayerAutoplayAvailability.NO_MATCH, playerAutoplayAvailability(sources, 4, false, false))
        assertEquals(PlayerAutoplayAvailability.READY, playerAutoplayAvailability(sources + stream("4K", "4K"), 4, true, false))
    }

    @Test fun lateSourcesCannotReplaceAnAlreadySelectedStream() {
        assertEquals(PlayerAutoplayAvailability.SELECTED, playerAutoplayAvailability(listOf(stream("new")), 0, false, true))
    }
}
