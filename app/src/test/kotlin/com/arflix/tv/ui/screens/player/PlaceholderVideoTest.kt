package com.arflix.tv.ui.screens.player

import org.junit.Assert.*
import org.junit.Test

class PlaceholderVideoTest {
    private val minute = 60_000L
    private val key = "1|https://addon.example/playback/movie"

    private fun status(
        duration: Long = 2 * minute,
        expected: Long = 90 * minute,
        pending: Boolean = false,
        live: Boolean = false,
        url: String = "https://addon.example/playback/movie"
    ) = classifyPlaybackVideo(duration, expected, url, pending, live)

    private fun canSave(status: PlaybackVideoStatus) = canPersistPlaybackSource(key, key, key, status)

    @Test fun twoMinuteClipInsteadOfAMovieIsAPlaceholderEvenBeforeFileSizeArrives() {
        assertEquals(PlaybackVideoStatus.PLACEHOLDER, status())
        assertFalse(canSave(status()))
    }

    @Test fun regularEpisodesRemainRealContent() {
        assertEquals(PlaybackVideoStatus.REAL, status(duration = 42 * minute, expected = 45 * minute))
        assertTrue(canSave(status(duration = 42 * minute, expected = 45 * minute)))
    }

    @Test fun genuineShortContentIsNotRejectedRegardlessOfFileSize() {
        // The detector no longer uses file size: a 350 MiB four-minute short is still real.
        assertEquals(PlaybackVideoStatus.REAL, status(duration = 4 * minute, expected = 4 * minute))
        assertEquals(PlaybackVideoStatus.REAL, status(duration = 4 * minute, expected = 0))
    }

    @Test fun smallRuntimeDifferencesDoNotRejectShortEpisodes() {
        assertEquals(PlaybackVideoStatus.REAL, status(duration = 4 * minute, expected = 6 * minute))
    }

    @Test fun fiveMinuteBoundaryStillRequiresIndependentRuntimeEvidence() {
        assertEquals(PlaybackVideoStatus.REAL, status(duration = 5 * minute, expected = 5 * minute))
        assertEquals(PlaybackVideoStatus.PLACEHOLDER, status(duration = 5 * minute))
        assertEquals(PlaybackVideoStatus.REAL, status(duration = 5 * minute + 1))
    }

    @Test fun durationMustBeKnownBeforeTheSourceCanSaveProgress() {
        for (unknown in listOf(0L, -1L, Long.MIN_VALUE + 1)) {
            assertEquals(PlaybackVideoStatus.PENDING, status(duration = unknown))
            assertFalse(canSave(status(duration = unknown)))
        }
    }

    @Test fun metadataPendingDoesNotPermanentlyAcceptAPlaceholder() {
        val before = status(expected = 0, pending = true)
        assertEquals(PlaybackVideoStatus.PENDING, before)
        assertFalse(canSave(before))
        val after = status()
        assertEquals(PlaybackVideoStatus.PLACEHOLDER, after)
        assertFalse(canSave(after))
    }

    @Test fun delayedDurationIsRecheckedForTheSameSource() {
        assertFalse(canSave(status(duration = 0)))
        assertFalse(canSave(status(duration = 2 * minute)))
        assertTrue(canSave(status(duration = 90 * minute)))
    }

    @Test fun aRealShortBecomesSaveableAfterItsMetadataArrives() {
        assertFalse(canSave(status(duration = 4 * minute, expected = 0, pending = true)))
        assertTrue(canSave(status(duration = 4 * minute, expected = 4 * minute)))
    }

    @Test fun missingMetadataDoesNotPermanentlyBlockRealShortContent() {
        assertTrue(canSave(status(duration = 4 * minute, expected = 0, pending = false)))
    }

    @Test fun longVideosDoNotWaitForMetadataOrSourceEnrichment() {
        assertTrue(canSave(status(duration = 90 * minute, expected = 0, pending = true)))
    }

    @Test fun liveWindowsAreNeverClassifiedAsPlaceholders() {
        assertEquals(PlaybackVideoStatus.REAL, status(live = true))
        assertEquals(PlaybackVideoStatus.REAL, status(duration = 0, live = true))
        assertEquals(PlaybackVideoStatus.REAL, status(live = true, url = "https://aio.example/static/500.mp4"))
    }

    @Test fun knownAddonErrorEndpointsDoNotNeedRuntimeOrSizeMetadata() {
        for (filename in listOf("downloading", "download_failed", "500", "401", "403", "429", "200",
            "no_matching_file", "content_proxy_limit_reached", "payment_required", "store_limit_exceeded",
            "unavailable_for_legal_reasons")) {
            assertEquals(filename, PlaybackVideoStatus.PLACEHOLDER,
                status(expected = 0, pending = true, url = "https://aio.example/static/$filename.mp4?token=x"))
        }
    }

    @Test fun aFilenameOrQueryParameterAloneIsNotAnErrorEndpoint() {
        for (url in listOf("https://cdn.example/movies/500.mp4", "https://cdn.example/static/film.mp4",
            "https://cdn.example/movie?next=/static/downloading.mp4", "not a URL")) {
            assertEquals(PlaybackVideoStatus.REAL, status(expected = 0, url = url))
        }
    }

    @Test fun changingSourceCannotCreditTheOldPlayersDuration() {
        assertFalse(canPersistPlaybackSource("2|other", key, key, PlaybackVideoStatus.REAL))
        assertFalse(canPersistPlaybackSource(key, "2|other", key, PlaybackVideoStatus.REAL))
        assertFalse(canPersistPlaybackSource(key, key, "2|other", PlaybackVideoStatus.REAL))
    }

    @Test fun prepareAndUnselectedStatesCannotSaveProgressOrComplete() {
        assertFalse(canPersistPlaybackSource(null, null, null, PlaybackVideoStatus.REAL))
        assertFalse(canPersistPlaybackSource(key, key, null, PlaybackVideoStatus.REAL))
    }

    @Test fun switchingAwayFromAPlaceholderRetainsTheMoviesSavedPosition() {
        val savedPosition = 35 * minute
        val clipPosition = 100_000L
        val resumeArgument = clipPosition.takeIf { canSave(status()) }
        assertNull(resumeArgument)
        assertEquals(savedPosition, resumeArgument ?: savedPosition)
    }

    @Test fun switchingBetweenRealSourcesKeepsTheCurrentPlayhead() {
        val position = 35 * minute
        assertEquals(position, position.takeIf { canSave(status(duration = 90 * minute)) })
    }
}
