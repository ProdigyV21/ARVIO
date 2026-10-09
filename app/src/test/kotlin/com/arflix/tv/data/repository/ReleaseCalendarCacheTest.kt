package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbEpisode
import com.arflix.tv.data.api.TmdbSeasonDetails
import com.arflix.tv.data.model.CalendarRelease
import com.arflix.tv.data.model.CalendarReleaseKind
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import java.time.Instant
import java.time.LocalDate
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class ReleaseCalendarCacheTest {
    @get:Rule val temporary = TemporaryFolder()

    @Test fun `month survives process recreation with exact artwork timestamps and provenance`() = runBlocking {
        val directory = temporary.newFolder()
        val preview = CalendarMonthPreview(listOf(CalendarRelease(
            "episode-1", MediaItem(1, "Show", mediaType = MediaType.TV, backdrop = "https://art.example/backdrop.jpg"),
            LocalDate.of(2026, 10, 4), Instant.parse("2026-10-04T18:00:00Z"), CalendarReleaseKind.EPISODE,
            2, 3, "Next episode", setOf("arvio", "trakt"), "https://art.example/logo.png", "NL"
        )), mapOf("all" to 1, "arvio" to 1, "trakt" to 1))
        ReleaseCalendarCache(directory).writeMonth("profile1|connection1|watchlist1|en-US|2026-10|UTC|NL", preview)
        assertEquals(preview, ReleaseCalendarCache(directory).readMonth("profile1|connection1|watchlist1|en-US|2026-10|UTC|NL"))
        assertNull(ReleaseCalendarCache(directory).readMonth("profile2|connection1|watchlist1|en-US|2026-10|UTC|NL"))
        assertNull(ReleaseCalendarCache(directory).readMonth("profile1|connection2|watchlist1|en-US|2026-10|UTC|NL"))
        assertNull(ReleaseCalendarCache(directory).readMonth("profile1|connection1|watchlist2|en-US|2026-10|UTC|NL"))
        assertNull(ReleaseCalendarCache(directory).readMonth("profile1|connection1|watchlist1|nl-NL|2026-10|UTC|NL"))
    }

    @Test fun `date only cache and empty results round trip without inventing times`() = runBlocking {
        val cache = ReleaseCalendarCache(temporary.newFolder())
        val preview = CalendarMonthPreview(listOf(CalendarRelease("movie", MediaItem(1, "Movie"),
            LocalDate.of(2026, 10, 4), kind = CalendarReleaseKind.MOVIE, sourceIds = setOf("arvio"))), mapOf("all" to 1))
        cache.writeMonth("month", preview)
        assertEquals(preview, cache.readMonth("month"))
        cache.writeMonth("month", CalendarMonthPreview(emptyList(), mapOf("all" to 0)))
        assertTrue(cache.readMonth("month")!!.entries.isEmpty())
    }

    @Test fun `expired corrupt and future dated previews are discarded`() = runBlocking {
        var time = System.currentTimeMillis()
        val directory = temporary.newFolder()
        val cache = ReleaseCalendarCache(directory) { time }
        cache.writeMonth("month", CalendarMonthPreview(emptyList(), emptyMap()))
        time += 24 * 60 * 60_000L
        assertNull(cache.readMonth("month"))
        time -= 24 * 60 * 60_000L + 1
        assertNull(cache.readMonth("month"))
        time += 1
        directory.listFiles()!!.single().writeText("{broken")
        assertNull(cache.readMonth("month"))
    }

    @Test fun `public season metadata expires and explicit invalidation removes disk entries`() = runBlocking {
        var time = System.currentTimeMillis()
        val cache = ReleaseCalendarCache(temporary.newFolder()) { time }
        val season = TmdbSeasonDetails(episodes = listOf(TmdbEpisode(episodeNumber = 4, airDate = "2026-10-04")))
        cache.writeValue("season:1:2:en-US", season)
        assertEquals(season, cache.readValue<TmdbSeasonDetails>("season:1:2:en-US", TmdbSeasonDetails::class.java))
        time += 30 * 60_000L
        assertNull(cache.readValue<TmdbSeasonDetails>("season:1:2:en-US", TmdbSeasonDetails::class.java))
        cache.writeValue("season:1:2:en-US", season)
        cache.clearMetadata()
        assertNull(cache.readValue<TmdbSeasonDetails>("season:1:2:en-US", TmdbSeasonDetails::class.java))
    }

    @Test fun `reconciled removals survive restart and failed provider cannot extend stale expiry`() = runBlocking {
        var time = System.currentTimeMillis()
        val directory = temporary.newFolder()
        val cache = ReleaseCalendarCache(directory) { time }
        val removed = CalendarRelease("removed", MediaItem(1, "Removed"), LocalDate.of(2026, 10, 4),
            kind = CalendarReleaseKind.MOVIE, sourceIds = setOf("trakt"))
        val retained = removed.copy(id = "retained", media = MediaItem(2, "Unavailable"), sourceIds = setOf("mdblist"))
        val preview = CalendarMonthPreview(listOf(removed, retained), emptyMap(), time)
        cache.writeMonth("month", preview)
        val progress = CalendarLoadProgress(CalendarWatchlists("fixture", mapOf(
            com.arflix.tv.data.model.ReleaseCalendarSource.TRAKT to emptyList(),
            com.arflix.tv.data.model.ReleaseCalendarSource.MDBLIST to emptyList()),
            failedSources = setOf(com.arflix.tv.data.model.ReleaseCalendarSource.MDBLIST)),
            CalendarMonthResult(emptyList(), listOf("Unavailable")), true)
        time += 23 * 60 * 60_000L
        cache.writeMonth("month", preview.copy(entries = mergeCalendarPreview(preview.entries, progress)))
        assertEquals(listOf(retained), ReleaseCalendarCache(directory) { time }.readMonth("month")!!.entries)
        time += 60 * 60_000L
        assertNull(ReleaseCalendarCache(directory) { time }.readMonth("month"))
    }

    @Test fun `metadata keeps original write time across process recreation and historical read window`() = runBlocking {
        var time = System.currentTimeMillis()
        val directory = temporary.newFolder()
        val cache = ReleaseCalendarCache(directory) { time }
        val season = TmdbSeasonDetails(episodes = listOf(TmdbEpisode(airDate = "2020-01-01")))
        cache.writeValue("season", season)
        val writtenAt = time
        time += 2 * 60 * 60_000L
        assertEquals(CalendarMetadataRecord(season, writtenAt),
            ReleaseCalendarCache(directory) { time }.readMetadata<TmdbSeasonDetails>("season", TmdbSeasonDetails::class.java))
        time += 22 * 60 * 60_000L
        assertNull(cache.readMetadata<TmdbSeasonDetails>("season", TmdbSeasonDetails::class.java))
    }

    @Test fun `title projections round trip exactly expire and retry clears them but not full previews`() = runBlocking {
        var time = System.currentTimeMillis()
        val directory = temporary.newFolder()
        val cache = ReleaseCalendarCache(directory) { time }
        val entry = CalendarRelease("episode", MediaItem(1, "Show", mediaType = MediaType.TV),
            LocalDate.of(2026, 10, 4), Instant.parse("2026-10-04T18:00:00Z"), CalendarReleaseKind.EPISODE,
            0, 1, "Special", setOf("trakt"), "https://art.example/logo.png")
        val projection = CalendarTitleProjection(listOf(entry), time + 60_000L)
        cache.writeTitle("private-title-key", projection)
        assertEquals(projection, ReleaseCalendarCache(directory) { time }.readTitle("private-title-key"))
        assertNull(cache.readTitle("other-profile-key"))
        time += 60_000L
        assertNull(cache.readTitle("private-title-key"))
        cache.writeTitle("private-title-key", CalendarTitleProjection(emptyList(), time + 60_000L))
        assertTrue(cache.readTitle("private-title-key")!!.entries.isEmpty())
        cache.writeMonth("month", CalendarMonthPreview(listOf(entry), emptyMap(), time))
        cache.clearMetadata()
        assertNull(cache.readTitle("private-title-key"))
        assertNotNull(cache.readMonth("month"))
    }
}
