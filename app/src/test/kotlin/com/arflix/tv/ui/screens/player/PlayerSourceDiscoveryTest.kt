package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.StreamSource
import org.junit.Assert.*
import org.junit.Test

class PlayerSourceDiscoveryTest {
    private fun stream(id: String, quality: String = "1080p") = StreamSource(
        source = id, addonName = id, addonId = id, quality = quality, size = "2 GB", url = "https://example.com/$id"
    )

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
