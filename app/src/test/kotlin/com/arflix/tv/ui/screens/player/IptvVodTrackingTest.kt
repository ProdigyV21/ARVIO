package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.IptvVodSourceIds
import com.arflix.tv.data.model.MediaType
import org.junit.Assert.*
import org.junit.Test

class IptvVodTrackingTest {
    @Test fun sameSecondPauseResumeAndBackwardSeekReportWithoutIncreasingSteadyPlaybackFrequency() {
        assertTrue(shouldReportPlaybackProgress(60, 60, true, false))
        assertTrue(shouldReportPlaybackProgress(60, 60, false, true))
        assertTrue(shouldReportPlaybackProgress(-1, 60, null, true))
        assertTrue(shouldReportPlaybackProgress(60, 30, true, true))
        assertFalse(shouldReportPlaybackProgress(60, 61, true, true))
        assertFalse(shouldReportPlaybackProgress(60, 60, false, false))
        assertTrue(shouldReportPlaybackProgress(60, 63, true, true))
    }

    @Test fun onlyMatchedOnDemandMoviesAndEpisodesGetTheException() {
        for (id in IptvVodSourceIds.ALL) {
            assertTrue(isMatchedIptvVodPlayback(MediaType.MOVIE, 123, null, null, id))
            assertTrue(isMatchedIptvVodPlayback(MediaType.TV, 1416, 22, 15, id))
            assertTrue(isMatchedIptvVodPlayback(MediaType.TV, 1416, 0, 1, id))
            assertFalse(isMatchedIptvVodPlayback(MediaType.TV, -1, 22, 15, id))
            assertFalse(isMatchedIptvVodPlayback(MediaType.TV, 1416, null, null, id))
            assertFalse(isMatchedIptvVodPlayback(MediaType.TV, 1416, 22, 0, id))
            assertFalse(isMatchedIptvVodPlayback(MediaType.TV, 1416, 22, 15, id, explicitlyLive = true))
        }
        assertFalse(isMatchedIptvVodPlayback(MediaType.TV, 1416, 22, 15, "iptv_channels"))
    }

    @Test fun unknownDurationCanReportWithoutInventingARuntimeOrCompletion() {
        assertTrue(canReportPlaybackProgress(60_000, 0, true))
        assertFalse(canReportPlaybackProgress(60_000, 0, false))
        assertFalse(canReportPlaybackProgress(0, 0, true))
        assertTrue(canReportPlaybackProgress(60_000, 600_000, false))
        assertEquals(IptvVodTrackingProgress(0, 0f), unknownDurationVodProgress(60_000, 0, false))
        assertEquals(IptvVodTrackingProgress(0, 100f), unknownDurationVodProgress(60_000, 0, true))
    }

    @Test fun metadataFallbackCannotFalselyScrobbleAnUnfinishedVideo() {
        assertEquals(IptvVodTrackingProgress(600_000, 10f), unknownDurationVodProgress(60_000, 600_000, false))
        assertEquals(79f, unknownDurationVodProgress(590_000, 600_000, false).percent)
        assertEquals(79f, unknownDurationVodProgress(Long.MAX_VALUE, 600_000, false).percent)
        assertEquals(0f, unknownDurationVodProgress(-1, 600_000, false).percent)
        assertEquals(100f, unknownDurationVodProgress(590_000, 600_000, true).percent)
    }

    @Test fun rollingLiveWindowIsNotTheEpisodesRuntime() {
        assertEquals(0L, playbackTrackingDurationMs(120_000, true, true))
        assertEquals(600_000L, playbackTrackingDurationMs(600_000, false, true))
        assertEquals(120_000L, playbackTrackingDurationMs(120_000, true, false))
        assertEquals(0L, playbackTrackingDurationMs(Long.MIN_VALUE + 1, false, true))
    }

    @Test fun trustedVodStillRejectsKnownErrorVideosAndDoesNotChangeOtherSources() {
        assertEquals(PlaybackVideoStatus.REAL, classifyPlaybackVideo(0, 600_000, "https://example.invalid/episode.ts", false, false, true))
        assertEquals(PlaybackVideoStatus.PENDING, classifyPlaybackVideo(0, 600_000, "https://example.invalid/episode.ts", false, false))
        assertEquals(PlaybackVideoStatus.PLACEHOLDER, classifyPlaybackVideo(0, 600_000, "https://example.invalid/static/403.mp4", false, false, true))
        assertEquals(PlaybackVideoStatus.PLACEHOLDER, classifyPlaybackVideo(30_000, 600_000, "https://example.invalid/error.mp4", false, false, true))
    }
}
