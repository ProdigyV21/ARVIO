package com.arflix.tv.data.repository

import com.arflix.tv.data.api.*
import java.time.Instant
import org.junit.Assert.assertEquals
import org.junit.Test

class ReleaseCalendarCachePolicyTest {
    private val now = Instant.parse("2026-10-08T12:00:00Z").toEpochMilli()

    @Test fun `historical metadata lasts longer but active and unknown schedules stay fresh`() {
        assertEquals(CALENDAR_HISTORY_TTL, calendarMetadataTtl(TmdbMovieDetails(releaseDate = "2020-01-01"), now))
        assertEquals(CALENDAR_ACTIVE_TTL, calendarMetadataTtl(TmdbMovieDetails(releaseDate = "2026-10-08"), now))
        assertEquals(CALENDAR_ACTIVE_TTL, calendarMetadataTtl(TmdbMovieDetails(), now))
        assertEquals(CALENDAR_HISTORY_TTL, calendarMetadataTtl(TmdbTvDetails(status = "Ended",
            lastEpisodeToAir = TmdbEpisode(airDate = "2020-01-01")), now))
        assertEquals(CALENDAR_ACTIVE_TTL, calendarMetadataTtl(TmdbTvDetails(status = "Returning Series",
            lastEpisodeToAir = TmdbEpisode(airDate = "2020-01-01")), now))
        assertEquals(CALENDAR_ACTIVE_TTL, calendarMetadataTtl(TmdbTvDetails(status = "Ended"), now))
    }

    @Test fun `upcoming regional movie releases prevent historical caching`() {
        val movie = TmdbMovieDetails(releaseDate = "2020-01-01", releaseDates = TmdbReleaseDatesResponse(listOf(
            TmdbReleaseDatesResult("NL", listOf(TmdbReleaseDate(releaseDate = "2026-10-20T00:00:00Z")))
        )))
        assertEquals(CALENDAR_ACTIVE_TTL, calendarMetadataTtl(movie, now))
    }

    @Test fun `specials future episodes and unknown air dates prevent historical season caching`() {
        fun season(vararg dates: String?) = TmdbSeasonDetails(seasonNumber = 0,
            episodes = dates.map { TmdbEpisode(airDate = it) })
        assertEquals(CALENDAR_HISTORY_TTL, calendarMetadataTtl(season("2020-01-01", "2021-01-01"), now))
        assertEquals(CALENDAR_ACTIVE_TTL, calendarMetadataTtl(season("2020-01-01", "2026-10-20"), now))
        assertEquals(CALENDAR_ACTIVE_TTL, calendarMetadataTtl(season("2020-01-01", null), now))
        assertEquals(CALENDAR_ACTIVE_TTL, calendarMetadataTtl(season(), now))
    }
}
