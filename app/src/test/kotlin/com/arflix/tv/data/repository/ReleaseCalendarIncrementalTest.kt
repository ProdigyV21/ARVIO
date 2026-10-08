package com.arflix.tv.data.repository

import com.arflix.tv.data.api.*
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.ReleaseCalendarSource
import com.arflix.tv.data.repository.simkl.SimklAuthManager
import com.arflix.tv.data.repository.simkl.SimklSyncService
import com.arflix.tv.util.Constants
import io.mockk.*
import java.io.IOException
import java.time.Instant
import java.time.YearMonth
import java.time.ZoneId
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.*
import org.junit.rules.TemporaryFolder

class ReleaseCalendarIncrementalTest {
    @get:Rule val temporary = TemporaryFolder()
    private val tmdb = mockk<TmdbApi>()
    private val traktApi = mockk<TraktApi>()
    private val media = mockk<MediaRepository>()
    private val profiles = mockk<ProfileManager>()
    private var now = Instant.parse("2026-10-08T12:00:00Z").toEpochMilli()
    private val month = YearMonth.of(2026, 10)
    private val utc = ZoneId.of("UTC")
    private lateinit var cache: ReleaseCalendarCache
    private lateinit var repository: ReleaseCalendarRepository

    @Before fun setup() {
        mockkObject(Constants)
        every { Constants.TMDB_API_KEY } returns "fixture-key"
        every { Constants.TRAKT_CLIENT_ID } returns ""
        every { profiles.getProfileIdSync() } returns "fixture"
        coEvery { media.getLogoUrl(any<MediaType>(), any()) } returns "https://art.example/logo.png"
        cache = ReleaseCalendarCache(temporary.newFolder()) { now }
        repository = createRepository()
    }

    private fun createRepository() = ReleaseCalendarRepository(tmdb, traktApi, mockk<WatchlistRepository>(),
        mockk<TraktRepository>(), mockk<SimklAuthManager>(), mockk<SimklSyncService>(), mockk<MdbListRepository>(),
        media, profiles, cache).also { it.clock = { now } }

    private fun lists(vararg ids: Int, source: ReleaseCalendarSource = ReleaseCalendarSource.ARVIO) =
        CalendarWatchlists("fixture", mapOf(source to ids.map { MediaItem(it, "Movie $it") }))

    @After fun cleanup() = unmockkAll()

    @Test fun `adding one title only fetches that title and successful removals are not restored`() = runTest {
        coEvery { tmdb.getMovieDetails(any(), any(), any(), any()) } answers {
            TmdbMovieDetails(firstArg(), "Movie ${firstArg<Int>()}", releaseDate = "2026-10-04")
        }
        val first = repository.loadMonth(lists(1, 2), month, utc, "NL")
        val second = repository.loadMonth(lists(1, 2, 3), month, utc, "NL")
        assertTrue(second.entries.containsAll(first.entries))
        assertEquals(setOf(1, 2, 3), second.entries.map { it.media.id }.toSet())
        val removed = repository.loadMonth(lists(2, 3), month, utc, "NL")
        assertEquals(setOf(2, 3), removed.entries.map { it.media.id }.toSet())
        coVerify(exactly = 3) { tmdb.getMovieDetails(any(), any(), any(), any()) }
        coVerify(exactly = 3) { media.getLogoUrl(any<MediaType>(), any()) }
    }

    @Test fun `reused title schedules acquire only current source membership`() = runTest {
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } returns TmdbMovieDetails(1, "Shared", releaseDate = "2026-10-04")
        val original = lists(1).copy(items = lists(1).items + lists(1, source = ReleaseCalendarSource.TRAKT).items)
        assertEquals(setOf("arvio", "trakt"), repository.loadMonth(original, month, utc, "NL").entries.single().sourceIds)
        val current = repository.loadMonth(lists(1, source = ReleaseCalendarSource.TRAKT), month, utc, "NL")
        assertEquals(setOf("trakt"), current.entries.single().sourceIds)
        coVerify(exactly = 1) { tmdb.getMovieDetails(any(), any(), any(), any()) }
    }

    @Test fun `process recreation reuses both nonempty and verified empty projections`() = runTest {
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } returns TmdbMovieDetails(1, "Active", releaseDate = "2026-10-04")
        coEvery { tmdb.getMovieDetails(2, any(), any(), any()) } returns TmdbMovieDetails(2, "Old", releaseDate = "2000-01-01")
        val first = repository.loadMonth(lists(1, 2), month, utc, "NL")
        val restarted = createRepository().loadMonth(lists(1, 2), month, utc, "NL")
        assertEquals(first.entries, restarted.entries)
        assertEquals(setOf(MediaType.MOVIE to 1, MediaType.MOVIE to 2), restarted.completedTitles)
        coVerify(exactly = 2) { tmdb.getMovieDetails(any(), any(), any(), any()) }
        coVerify(exactly = 1) { media.getLogoUrl(any<MediaType>(), any()) }
    }

    @Test fun `active projections expire and changed dates replace old dates`() = runTest {
        var day = "2026-10-04"
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } answers { TmdbMovieDetails(1, "Active", releaseDate = day) }
        repository.loadMonth(lists(1), month, utc, "NL")
        now += CALENDAR_ACTIVE_TTL
        day = "2026-10-12"
        val refreshed = createRepository().loadMonth(lists(1), month, utc, "NL")
        assertEquals(month.atDay(12), refreshed.entries.single().date)
        coVerify(exactly = 2) { tmdb.getMovieDetails(any(), any(), any(), any()) }
    }

    @Test fun `historical projections survive an hour but expire after one day`() = runTest {
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } returns TmdbMovieDetails(1, "Historical", releaseDate = "2000-01-01")
        repository.loadMonth(lists(1), month, utc, "NL")
        now += 60 * 60_000L
        assertTrue(createRepository().loadMonth(lists(1), month, utc, "NL").entries.isEmpty())
        coVerify(exactly = 1) { tmdb.getMovieDetails(any(), any(), any(), any()) }
        now += 23 * 60 * 60_000L
        createRepository().loadMonth(lists(1), month, utc, "NL")
        coVerify(exactly = 2) { tmdb.getMovieDetails(any(), any(), any(), any()) }
    }

    @Test fun `disk restoration cannot extend a projection past its underlying metadata freshness`() = runTest {
        cache.writeValue("movie:1:en-US", TmdbMovieDetails(1, "Active", releaseDate = "2026-10-04"))
        now += CALENDAR_ACTIVE_TTL - 60_000L
        repository.loadMonth(lists(1), month, utc, "NL")
        coVerify(exactly = 0) { tmdb.getMovieDetails(any(), any(), any(), any()) }
        now += 60_000L
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } returns TmdbMovieDetails(1, "Active", releaseDate = "2026-10-12")
        val refreshed = createRepository().loadMonth(lists(1), month, utc, "NL")
        assertEquals(month.atDay(12), refreshed.entries.single().date)
        coVerify(exactly = 1) { tmdb.getMovieDetails(any(), any(), any(), any()) }
    }

    @Test fun `projection keys isolate profile language timezone region and optional Trakt identity`() = runTest {
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } returns TmdbMovieDetails(1, "Active", releaseDate = "2026-10-04")
        repository.loadMonth(lists(1), month, utc, "NL")
        repository.loadMonth(lists(1), month, ZoneId.of("America/New_York"), "NL")
        repository.loadMonth(lists(1), month, utc, "US")
        repository.loadMonth(lists(1), month, utc, "NL", "nl-NL")
        every { profiles.getProfileIdSync() } returns "other"
        repository.loadMonth(lists(1).copy(profileId = "other"), month, utc, "NL")
        coVerify(exactly = 5) { media.getLogoUrl(any<MediaType>(), any()) }
        coVerify(exactly = 2) { tmdb.getMovieDetails(any(), any(), any(), any()) }
    }

    @Test fun `adjacent month preloading never fetches uncached titles and normal entry fills every missing title`() = runTest {
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } returns TmdbMovieDetails(1, "Active", releaseDate = "2026-11-04")
        repository.loadMonth(lists(1), month, utc, "NL")
        val preloaded = repository.loadMonth(lists(1, 2), month.plusMonths(1), utc, "NL", cacheOnly = true)
        assertEquals(listOf(1), preloaded.entries.map { it.media.id })
        assertEquals(1, preloaded.warnings.size)
        coVerify(exactly = 1) { tmdb.getMovieDetails(any(), any(), any(), any()) }
        coVerify(exactly = 0) { media.getLogoUrl(any<MediaType>(), any()) }
        coEvery { tmdb.getMovieDetails(2, any(), any(), any()) } returns TmdbMovieDetails(2, "New", releaseDate = "2026-11-06")
        val opened = repository.loadMonth(lists(1, 2), month.plusMonths(1), utc, "NL")
        assertEquals(setOf(1, 2), opened.entries.map { it.media.id }.toSet())
        assertTrue(opened.entries.all { it.logoUrl != null })
        coVerify(exactly = 2) { tmdb.getMovieDetails(any(), any(), any(), any()) }
    }

    @Test fun `cache only adjacent seasons do not send batch individual time or artwork requests`() = runTest {
        coEvery { tmdb.getTvDetails(5, any(), any(), any()) } returns TmdbTvDetails(5, "Show", seasons = listOf(
            TmdbTvSeason(seasonNumber = 1, airDate = "2026-10-01"),
            TmdbTvSeason(seasonNumber = 2, airDate = "2026-11-02")))
        coEvery { tmdb.getTvSeason(5, any(), any(), any()) } answers {
            val n = secondArg<Int>()
            TmdbSeasonDetails(seasonNumber = n, episodes = listOf(TmdbEpisode(seasonNumber = n,
                airDate = if (n == 1) "2026-10-04" else "2026-11-04")))
        }
        val lists = CalendarWatchlists("fixture", mapOf(ReleaseCalendarSource.ARVIO to listOf(MediaItem(5, "Show", mediaType = MediaType.TV))))
        repository.loadMonth(lists, month, utc, "NL")
        val preload = repository.loadMonth(lists, month.plusMonths(1), utc, "NL", cacheOnly = true)
        assertTrue(preload.warnings.isNotEmpty())
        coVerify(exactly = 1) { tmdb.getTvSeason(any(), any(), any(), any()) }
        coVerify(exactly = 0) { tmdb.getTvSeasons(any(), any(), any(), any()) }
        confirmVerified(traktApi)
        assertEquals(2, repository.loadMonth(lists, month.plusMonths(1), utc, "NL").entries.single().seasonNumber)
        coVerify(exactly = 2) { tmdb.getTvSeason(any(), any(), any(), any()) }
    }

    @Test fun `incomplete season reads are not saved as complete empty months`() = runTest {
        coEvery { tmdb.getTvDetails(5, any(), any(), any()) } returns TmdbTvDetails(5, "Show", seasons = listOf(TmdbTvSeason(seasonNumber = 1)))
        coEvery { tmdb.getTvSeason(5, any(), any(), any()) } throws IOException("Temporary outage")
        val lists = CalendarWatchlists("fixture", mapOf(ReleaseCalendarSource.ARVIO to listOf(MediaItem(5, "Show", mediaType = MediaType.TV))))
        assertTrue(repository.loadMonth(lists, month, utc, "NL").warnings.isNotEmpty())
        coEvery { tmdb.getTvSeason(5, any(), any(), any()) } returns TmdbSeasonDetails(episodes = listOf(TmdbEpisode(airDate = "2026-10-04")))
        assertEquals(1, createRepository().loadMonth(lists, month, utc, "NL").entries.size)
        coVerify(exactly = 2) { tmdb.getTvSeason(any(), any(), any(), any()) }
    }

    @Test fun `explicit retry clears title projections and fetches corrected schedules`() = runTest {
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } returns TmdbMovieDetails(1, "Active", releaseDate = "2026-10-04")
        repository.loadMonth(lists(1), month, utc, "NL")
        repository.invalidateMetadata()
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } returns TmdbMovieDetails(1, "Active", releaseDate = "2026-10-12")
        assertEquals(month.atDay(12), repository.loadMonth(lists(1), month, utc, "NL").entries.single().date)
        coVerify(exactly = 2) { tmdb.getMovieDetails(any(), any(), any(), any()) }
    }
}
