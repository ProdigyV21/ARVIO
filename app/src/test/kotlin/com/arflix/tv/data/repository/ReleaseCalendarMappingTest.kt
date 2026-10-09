package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbEpisode
import com.arflix.tv.data.api.TmdbMovieDetails
import com.arflix.tv.data.api.TmdbReleaseDate
import com.arflix.tv.data.api.TmdbReleaseDatesResponse
import com.arflix.tv.data.api.TmdbReleaseDatesResult
import com.arflix.tv.data.api.TraktCalendarEpisode
import com.arflix.tv.data.model.CalendarReleaseKind
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.ReleaseCalendarSource
import com.arflix.tv.ui.screens.watchlist.calendar.ReleaseCalendarUiState
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import org.junit.Assert.*
import org.junit.Test

class ReleaseCalendarMappingTest {
    private val month = YearMonth.of(2026, 10)
    private val title = CalendarWatchlistTitle(MediaItem(10, "Example", mediaType = MediaType.TV), setOf("arvio", "trakt"))

    @Test fun `date only never becomes midnight or shifts day in another timezone`() {
        val releases = calendarEpisodeReleases(title, listOf(TmdbEpisode(airDate = "2026-10-04")), emptyList(), month, ZoneId.of("Pacific/Honolulu"))
        assertEquals(LocalDate.of(2026, 10, 4), releases.single().date)
        assertNull(releases.single().releaseInstant)
    }

    @Test fun `confirmed time determines local day and month across daylight saving boundary`() {
        val episode = TmdbEpisode(seasonNumber = 2, episodeNumber = 3, airDate = "2026-10-31")
        val timed = TraktCalendarEpisode(2, 3, firstAired = "2026-10-31T23:30:00Z")
        assertTrue(calendarEpisodeReleases(title, listOf(episode), listOf(timed), month, ZoneId.of("Europe/Amsterdam")).isEmpty())
        val result = calendarEpisodeReleases(title, listOf(episode), listOf(timed), month.plusMonths(1), ZoneId.of("Europe/Amsterdam")).single()
        assertEquals(LocalDate.of(2026, 11, 1), result.date)
        assertEquals(0, result.releaseInstant!!.atZone(ZoneId.of("Europe/Amsterdam")).hour)
    }

    @Test fun `missing or malformed episode dates are omitted rather than guessed`() {
        assertTrue(calendarEpisodeReleases(title, listOf(TmdbEpisode(), TmdbEpisode(airDate = "2026-02-31")), emptyList(), month, ZoneId.of("UTC")).isEmpty())
    }

    @Test fun `episode still is retained separately from series artwork`() {
        val result = calendarEpisodeReleases(title.copy(media = title.media.copy(backdrop = "series.jpg")),
            listOf(TmdbEpisode(airDate = "2026-10-04", stillPath = "https://images.example/episode.jpg")), emptyList(), month, ZoneId.of("UTC")).single()
        assertEquals("https://images.example/episode.jpg", result.media.episodeStill)
        assertEquals("series.jpg", result.media.backdrop)
    }

    @Test fun `regional movie releases preserve types and date only semantics`() {
        val details = TmdbMovieDetails(releaseDate = "2026-09-01", releaseDates = TmdbReleaseDatesResponse(listOf(
            TmdbReleaseDatesResult("US", listOf(TmdbReleaseDate(type = 3, releaseDate = "2026-10-01T00:00:00Z"))),
            TmdbReleaseDatesResult("NL", listOf(
                TmdbReleaseDate(type = 2, releaseDate = "2026-10-04T00:00:00Z"),
                TmdbReleaseDate(type = 3, releaseDate = "2026-10-04T00:00:00Z"),
                TmdbReleaseDate(type = 4, releaseDate = "2026-10-16T00:00:00Z")
            ))
        )))
        val releases = calendarMovieReleases(title, details, month, "NL")
        assertEquals(listOf(CalendarReleaseKind.CINEMA, CalendarReleaseKind.DIGITAL), releases.map { it.kind })
        assertTrue(releases.all { it.region == "NL" && it.releaseInstant == null })
    }

    @Test fun `fallback country is labeled and primary date is used only without regional dates`() {
        val details = TmdbMovieDetails(releaseDate = "2026-10-01", releaseDates = TmdbReleaseDatesResponse(listOf(
            TmdbReleaseDatesResult("US", listOf(TmdbReleaseDate(type = 4, releaseDate = "2026-11-01T00:00:00Z")))
        )))
        assertTrue(calendarMovieReleases(title, details, month, "NL").isEmpty())
        assertEquals("US", calendarMovieReleases(title, details, month.plusMonths(1), "NL").single().region)
        assertEquals(CalendarReleaseKind.MOVIE, calendarMovieReleases(title, details.copy(releaseDates = null), month, "NL").single().kind)
    }

    @Test fun `merged sources retain membership and distinguish movie from television ids`() {
        val titles = mergeCalendarWatchlists(mapOf(
            ReleaseCalendarSource.ARVIO to listOf(title.media, title.media.copy(mediaType = MediaType.MOVIE)),
            ReleaseCalendarSource.TRAKT to listOf(title.media.copy(image = "poster", traktId = 91)),
            ReleaseCalendarSource.MDBLIST to listOf(title.media.copy(id = -4))
        ))
        assertEquals(2, titles.size)
        val television = titles.single { it.media.mediaType == MediaType.TV }
        assertEquals(setOf("arvio", "trakt"), television.sourceIds)
        assertEquals("poster", television.media.image)
        assertEquals(91, television.media.traktId)
    }

    @Test fun `day strip and grid filter the same source membership`() {
        val release = calendarEpisodeReleases(title, listOf(TmdbEpisode(airDate = "2026-10-04")), emptyList(), month, ZoneId.of("UTC")).single()
        val state = ReleaseCalendarUiState(month = month, selectedDate = release.date, entries = listOf(release), selectedSourceId = "simkl")
        assertTrue(state.visibleEntries.isEmpty())
        assertTrue(state.selectedDayReleases.isEmpty())
        assertTrue(state.releasesByDate.isEmpty())
        assertEquals(listOf(release), state.copy(selectedSourceId = "arvio").selectedDayReleases)
    }
}
