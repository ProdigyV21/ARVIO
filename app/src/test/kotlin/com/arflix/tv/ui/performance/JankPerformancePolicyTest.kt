package com.arflix.tv.ui.performance

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class JankPerformancePolicyTest {
    @Test
    fun `jank stats are enabled for debug benchmark and qa builds`() {
        assertTrue(shouldTrackJankStats(isDebug = true, buildType = "debug"))
        assertTrue(shouldTrackJankStats(isDebug = false, buildType = "benchmark"))
        assertTrue(shouldTrackJankStats(isDebug = false, buildType = "qa"))
        assertFalse(shouldTrackJankStats(isDebug = false, buildType = "release"))
        assertFalse(shouldTrackJankStats(isDebug = false, buildType = "staging"))
    }

    @Test
    fun `navigation routes are normalized to stable performance screen names`() {
        assertEquals("home", performanceScreenName("home"))
        assertEquals("discover", performanceScreenName("discover"))
        assertEquals("collections", performanceScreenName("collections/{catalogId}"))
        assertEquals("collections", performanceScreenName("collections/netflix"))
        assertEquals("details", performanceScreenName("details/{mediaType}/{mediaId}"))
        assertEquals("details", performanceScreenName("details/movie/603"))
        assertEquals("player", performanceScreenName("player/{mediaType}/{mediaId}"))
        assertEquals("player", performanceScreenName("player/tv/1399?seasonNumber=1"))
        assertEquals("unknown", performanceScreenName(null))
    }
}
