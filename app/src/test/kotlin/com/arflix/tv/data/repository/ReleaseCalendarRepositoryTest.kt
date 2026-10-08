package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbEpisode
import com.arflix.tv.data.api.TmdbMovieDetails
import com.arflix.tv.data.api.TmdbSeasonDetails
import com.arflix.tv.data.api.TmdbTvDetails
import com.arflix.tv.data.api.TmdbTvSeason
import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.data.api.TraktCalendarEpisode
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.ReleaseCalendarSource
import com.arflix.tv.data.repository.simkl.SimklAuthManager
import com.arflix.tv.data.repository.simkl.SimklSyncService
import com.arflix.tv.data.repository.sync.RemoteWatchlistResult
import com.arflix.tv.util.Constants
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.mockk.*
import java.io.IOException
import java.time.YearMonth
import java.time.ZoneId
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import okhttp3.ResponseBody.Companion.toResponseBody

@OptIn(ExperimentalCoroutinesApi::class)
class ReleaseCalendarRepositoryTest {
    private val tmdb = mockk<TmdbApi>()
    private val traktApi = mockk<TraktApi>()
    private val own = mockk<WatchlistRepository>()
    private val trakt = mockk<TraktRepository>()
    private val simklAuth = mockk<SimklAuthManager>()
    private val simkl = mockk<SimklSyncService>()
    private val mdb = mockk<MdbListRepository>()
    private val media = mockk<MediaRepository>()
    private val profiles = mockk<ProfileManager>()
    private val cache = mockk<ReleaseCalendarCache>()
    private lateinit var repository: ReleaseCalendarRepository
    private val month = YearMonth.of(2026, 10)

    @Before fun setup() {
        mockkObject(Constants)
        every { Constants.TMDB_API_KEY } returns "fixture-key"
        every { Constants.TRAKT_CLIENT_ID } returns ""
        every { profiles.getProfileIdSync() } returns "fixture-profile"
        every { trakt.isAuthenticated } returns flowOf(false)
        coEvery { own.getLocalWatchlistItems() } returns listOf(MediaItem(1, "Own movie"))
        coEvery { simklAuth.isConnected() } returns false
        coEvery { mdb.getWatchlist() } returns RemoteWatchlistResult(false, null, 0)
        coEvery { media.getLogoUrl(any<MediaType>(), any()) } returns null
        coEvery { cache.readValue<Any>(any(), any()) } returns null
        coEvery { cache.writeValue(any(), any()) } just Runs
        coEvery { cache.clearMetadata() } just Runs
        repository = ReleaseCalendarRepository(tmdb, traktApi, own, trakt, simklAuth, simkl, mdb, media, profiles, cache)
    }

    @After fun tearDown() = unmockkAll()

    @Test fun `own cloud mirrored watchlist works with no external account`() = runTest {
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } returns TmdbMovieDetails(1, "Own movie", releaseDate = "2026-10-04")
        val lists = repository.loadWatchlists("fixture-profile")
        val result = repository.loadMonth(lists, month, ZoneId.of("UTC"), "NL")
        assertEquals(setOf(ReleaseCalendarSource.ARVIO), lists.items.keys)
        assertEquals("Own movie", result.entries.single().media.title)
        assertNull(result.entries.single().releaseInstant)
        coVerify(exactly = 0) { trakt.getWatchlistSyncResultWithAuthState() }
        coVerify(exactly = 0) { simkl.getWatchlistItems() }
        confirmVerified(traktApi)
    }

    @Test fun `one failed provider does not discard own releases`() = runTest {
        every { trakt.isAuthenticated } returns flowOf(true)
        coEvery { trakt.getWatchlistSyncResultWithAuthState() } throws IOException("Fixture outage")
        val lists = repository.loadWatchlists("fixture-profile")
        assertEquals(1, lists.items[ReleaseCalendarSource.ARVIO]?.size)
        assertEquals(listOf("Trakt could not be refreshed."), lists.warnings)
    }

    @Test fun `logo failure cannot remove confirmed releases`() = runTest {
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } returns TmdbMovieDetails(1, "Own movie", releaseDate = "2026-10-04")
        coEvery { media.getLogoUrl(any<MediaType>(), any()) } throws IOException("Fixture logo 404")
        val result = repository.loadMonth(repository.loadWatchlists("fixture-profile"), month, ZoneId.of("UTC"), "NL")
        assertEquals(1, result.entries.size)
        assertNull(result.entries.single().logoUrl)
        assertTrue(result.warnings.isEmpty())
    }

    @Test fun `incomplete simkl playback sync preserves usable library titles`() = runTest {
        coEvery { simklAuth.isConnected() } returns true
        coEvery { simkl.syncIfNeeded(any()) } returns false
        coEvery { simkl.getWatchlistItems() } returns listOf(MediaItem(8, "Planned show", mediaType = MediaType.TV))
        coEvery { simkl.getLibraryItems("watching", any()) } returns listOf(MediaItem(9, "Current show", mediaType = MediaType.TV))
        val lists = repository.loadWatchlists("fixture-profile")
        assertEquals(setOf(8, 9), lists.items[ReleaseCalendarSource.SIMKL].orEmpty().map { it.id }.toSet())
        assertTrue(lists.warnings.any { it.contains("SIMKL") })
    }

    @Test fun `profile change rejects private watchlist result`() = runTest {
        every { profiles.getProfileIdSync() } returns "other-profile"
        try {
            repository.loadWatchlists("fixture-profile")
            fail("Expected cancellation")
        } catch (_: CancellationException) {
            coVerify(exactly = 0) { own.getLocalWatchlistItems() }
        }
    }

    @Test fun `overlapping seasons and specials are not silently excluded`() = runTest {
        val show = MediaItem(5, "Show", mediaType = MediaType.TV)
        coEvery { tmdb.getTvDetails(5, any(), any(), any()) } returns TmdbTvDetails(5, "Show", seasons = listOf(
            TmdbTvSeason(seasonNumber = 0, airDate = "2020-01-01"),
            TmdbTvSeason(seasonNumber = 1, airDate = "2026-01-01"),
            TmdbTvSeason(seasonNumber = 2, airDate = "2026-09-01")
        ))
        coEvery { tmdb.getTvSeason(5, any(), any(), any()) } answers {
            val season = secondArg<Int>()
            TmdbSeasonDetails(seasonNumber = season, episodes = listOf(TmdbEpisode(seasonNumber = season, episodeNumber = 1, airDate = "2026-10-04")))
        }
        val result = repository.loadMonth(CalendarWatchlists("fixture-profile", mapOf(ReleaseCalendarSource.ARVIO to listOf(show))), month, ZoneId.of("UTC"), "US")
        assertEquals(setOf(0, 1, 2), result.entries.map { it.seasonNumber }.toSet())
    }

    @Test fun `twenty five seasons including specials need only two batch requests with identical releases`() = runTest {
        val show = MediaItem(5, "Long running show", mediaType = MediaType.TV)
        coEvery { tmdb.getTvDetails(5, any(), any(), any()) } returns TmdbTvDetails(5, "Long running show",
            seasons = (0..24).map { TmdbTvSeason(seasonNumber = it, airDate = "2020-01-01") })
        val batches = mutableListOf<List<Int>>()
        coEvery { tmdb.getTvSeasons(5, any(), any(), "en-US") } answers {
            val seasons = thirdArg<String>().split(',').map { it.substringAfter('/').toInt() }
            batches += seasons
            JsonObject().apply {
                seasons.forEach { season -> add("season/$season", Gson().toJsonTree(TmdbSeasonDetails(seasonNumber = season,
                    episodes = listOf(TmdbEpisode(seasonNumber = season, episodeNumber = 1, airDate = "2026-10-04"))))) }
            }
        }
        val lists = CalendarWatchlists("fixture-profile", mapOf(ReleaseCalendarSource.ARVIO to listOf(show)))
        val result = repository.loadMonth(lists, month, ZoneId.of("UTC"), "US")
        assertEquals((0..24).toSet(), result.entries.map { it.seasonNumber }.toSet())
        assertEquals(listOf(20, 5), batches.map { it.size })
        assertTrue(result.warnings.isEmpty())
        repository.loadMonth(lists, month.plusMonths(1), ZoneId.of("UTC"), "US")
        coVerify(exactly = 2) { tmdb.getTvSeasons(any(), any(), any(), any()) }
        coVerify(exactly = 0) { tmdb.getTvSeason(any(), any(), any(), any()) }
        coVerify(exactly = 25) { cache.writeValue(match { it.startsWith("season:") }, any()) }
    }

    @Test fun `partial season batch retries only missing blocks and preserves all dates`() = runTest {
        val show = MediaItem(5, "Show", mediaType = MediaType.TV)
        coEvery { tmdb.getTvDetails(5, any(), any(), any()) } returns TmdbTvDetails(5, "Show",
            seasons = (0..2).map { TmdbTvSeason(seasonNumber = it) })
        fun season(number: Int) = TmdbSeasonDetails(seasonNumber = number,
            episodes = listOf(TmdbEpisode(seasonNumber = number, airDate = "2026-10-04")))
        coEvery { tmdb.getTvSeasons(5, any(), any(), any()) } returns JsonObject().apply {
            add("season/2", Gson().toJsonTree(season(2)))
            addProperty("season/1", "invalid")
        }
        coEvery { tmdb.getTvSeason(5, any(), any(), any()) } answers { season(secondArg()) }
        val result = repository.loadMonth(CalendarWatchlists("fixture-profile", mapOf(ReleaseCalendarSource.ARVIO to listOf(show))),
            month, ZoneId.of("UTC"), "US")
        assertEquals(setOf(0, 1, 2), result.entries.map { it.seasonNumber }.toSet())
        coVerify(exactly = 1) { tmdb.getTvSeason(5, 0, any(), any()) }
        coVerify(exactly = 1) { tmdb.getTvSeason(5, 1, any(), any()) }
        coVerify(exactly = 0) { tmdb.getTvSeason(5, 2, any(), any()) }
    }

    @Test fun `batched seasons reuse individual disk cache and respect the five request limit`() = runTest {
        var inFlight = 0
        var peak = 0
        coEvery { tmdb.getTvDetails(any(), any(), any(), any()) } answers {
            TmdbTvDetails(firstArg(), "Show", seasons = (0..2).map { TmdbTvSeason(seasonNumber = it) })
        }
        coEvery { cache.readValue<TmdbSeasonDetails>(match { it.startsWith("season:") && it.contains(":0:") }, any()) } returns
            TmdbSeasonDetails(seasonNumber = 0, episodes = listOf(TmdbEpisode(seasonNumber = 0, airDate = "2026-10-04")))
        coEvery { tmdb.getTvSeasons(any(), any(), any(), any()) } coAnswers {
            assertEquals("season/2,season/1", thirdArg<String>())
            inFlight++
            peak = maxOf(peak, inFlight)
            delay(10)
            inFlight--
            JsonObject().apply { (1..2).forEach { add("season/$it", Gson().toJsonTree(TmdbSeasonDetails(seasonNumber = it))) } }
        }
        val lists = CalendarWatchlists("fixture-profile", mapOf(ReleaseCalendarSource.ARVIO to
            (1..12).map { MediaItem(it, "Show $it", mediaType = MediaType.TV) }))
        val result = repository.loadMonth(lists, month, ZoneId.of("UTC"), "US")
        assertEquals(12, result.entries.size)
        assertTrue(peak <= 2)
        coVerify(exactly = 12) { tmdb.getTvSeasons(any(), any(), any(), any()) }
        coVerify(exactly = 0) { tmdb.getTvSeason(any(), any(), any(), any()) }
    }

    @Test fun `large watchlist uses at most five parallel metadata requests and reuses cache`() = runTest {
        var inFlight = 0
        var peak = 0
        coEvery { tmdb.getMovieDetails(any(), any(), any(), any()) } coAnswers {
            inFlight++
            peak = maxOf(peak, inFlight)
            delay(10)
            inFlight--
            TmdbMovieDetails(firstArg(), "Movie", releaseDate = "2026-10-04")
        }
        val lists = CalendarWatchlists("fixture-profile", mapOf(ReleaseCalendarSource.ARVIO to (1..18).map { MediaItem(it, "Movie $it") }))
        val partialSizes = mutableListOf<Int>()
        assertEquals(18, repository.loadMonth(lists, month, ZoneId.of("UTC"), "US", onProgress = { partialSizes += it.entries.size }).entries.size)
        assertTrue(partialSizes.any { it in 1..17 })
        assertEquals(5, peak)
        repository.loadMonth(lists, month.plusMonths(1), ZoneId.of("UTC"), "US")
        coVerify(exactly = 18) { tmdb.getMovieDetails(any(), any(), any(), any()) }
    }

    @Test fun `current show releases are prioritized ahead of a large old movie backlog`() = runTest {
        val show = MediaItem(500, "Current show", mediaType = MediaType.TV)
        coEvery { tmdb.getTvDetails(500, any(), any(), any()) } returns TmdbTvDetails(500, "Current show",
            nextEpisodeToAir = TmdbEpisode(airDate = "2026-10-04"))
        coEvery { tmdb.getMovieDetails(any(), any(), any(), any()) } coAnswers {
            delay(1_000)
            TmdbMovieDetails(firstArg(), "Old movie", releaseDate = "2000-01-01")
        }
        val updates = mutableListOf<Pair<Long, CalendarMonthResult>>()
        val lists = CalendarWatchlists("fixture-profile", mapOf(ReleaseCalendarSource.ARVIO to
            (1..100).map { MediaItem(it, "Old movie") } + show))
        val request = async { repository.loadMonth(lists, month, ZoneId.of("UTC"), "US") { updates += currentTime to it } }
        runCurrent()
        assertTrue(updates.any { (time, result) -> time == 0L && result.entries.any { it.media.id == 500 } })
        assertFalse(request.isCompleted)
        request.cancelAndJoin()
    }

    @Test fun `confirmed missing metadata does not refetch until explicit refresh`() = runTest {
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } throws
            HttpException(Response.error<TmdbMovieDetails>(404, "{}".toResponseBody()))
        val lists = repository.loadWatchlists("fixture-profile")
        repeat(2) {
            assertEquals(1, repository.loadMonth(lists, month, ZoneId.of("UTC"), "US").warnings.size)
        }
        coVerify(exactly = 1) { tmdb.getMovieDetails(1, any(), any(), any()) }
        repository.invalidateMetadata()
        repository.loadMonth(lists, month, ZoneId.of("UTC"), "US")
        coVerify(exactly = 2) { tmdb.getMovieDetails(1, any(), any(), any()) }
    }

    @Test fun `later provider show is not queued behind hundreds of old movies`() = runTest {
        coEvery { own.getLocalWatchlistItems() } returns (1..100).map { MediaItem(it, "Old movie") }
        every { trakt.isAuthenticated } returns flowOf(true)
        coEvery { trakt.getWatchlistSyncResultWithAuthState() } coAnswers {
            delay(1)
            true to TraktRepository.WatchlistSyncResult(listOf(MediaItem(500, "Current show", mediaType = MediaType.TV)), 1)
        }
        coEvery { tmdb.getMovieDetails(any(), any(), any(), any()) } coAnswers {
            delay(1_000)
            TmdbMovieDetails(firstArg(), "Old movie", releaseDate = "2000-01-01")
        }
        coEvery { tmdb.getTvDetails(500, any(), any(), any()) } returns TmdbTvDetails(500, "Current show",
            nextEpisodeToAir = TmdbEpisode(airDate = "2026-10-04"))
        val updates = mutableListOf<Pair<Long, CalendarLoadProgress>>()
        val request = async { repository.loadCalendar("fixture-profile", month, ZoneId.of("UTC"), "US") { updates += currentTime to it } }
        advanceTimeBy(1_001)
        runCurrent()
        assertTrue(updates.any { (time, progress) -> time <= 1_000L && progress.month.entries.any { it.media.id == 500 } })
        request.cancelAndJoin()
    }

    @Test fun `localized metadata cache cannot leak the previous language`() = runTest {
        coEvery { tmdb.getMovieDetails(1, any(), any(), "en-US") } returns TmdbMovieDetails(1, "English title", releaseDate = "2026-10-04")
        coEvery { tmdb.getMovieDetails(1, any(), any(), "nl-NL") } returns TmdbMovieDetails(1, "Nederlandse titel", releaseDate = "2026-10-04")
        val lists = repository.loadWatchlists("fixture-profile")
        assertEquals("English title", repository.loadMonth(lists, month, ZoneId.of("UTC"), "US", "en-US").entries.single().media.title)
        assertEquals("Nederlandse titel", repository.loadMonth(lists, month, ZoneId.of("UTC"), "NL", "nl-NL").entries.single().media.title)
        coVerify(exactly = 2) { tmdb.getMovieDetails(any(), any(), any(), any()) }
    }

    @Test fun `own releases appear before a stalled provider times out`() = runTest {
        every { trakt.isAuthenticated } returns flowOf(true)
        coEvery { trakt.getWatchlistSyncResultWithAuthState() } coAnswers {
            delay(60_000)
            true to TraktRepository.WatchlistSyncResult(emptyList(), 0)
        }
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } coAnswers {
            delay(10)
            TmdbMovieDetails(1, "Own movie", releaseDate = "2026-10-04")
        }
        val progress = mutableListOf<Pair<Long, CalendarLoadProgress>>()
        val request = async {
            repository.loadCalendar("fixture-profile", month, ZoneId.of("UTC"), "US") { progress += currentTime to it }
        }
        advanceTimeBy(11)
        runCurrent()
        assertTrue(progress.any { (time, update) -> time == 10L && update.month.entries.any { it.media.id == 1 } })
        assertFalse(request.isCompleted)
        assertFalse(progress.last().second.watchlistsComplete)
        advanceUntilIdle()
        assertEquals(1, request.await().entries.size)
        assertEquals(45_000L, currentTime)
        assertTrue(progress.last().second.watchlistsComplete)
        assertTrue(request.await().warnings.any { it.contains("Trakt") })
    }

    @Test fun `later sources merge membership without repeating title requests`() = runTest {
        every { trakt.isAuthenticated } returns flowOf(true)
        coEvery { trakt.getWatchlistSyncResultWithAuthState() } coAnswers {
            delay(1_000)
            true to TraktRepository.WatchlistSyncResult(listOf(MediaItem(1, "Shared movie"), MediaItem(2, "Trakt movie")), 2)
        }
        coEvery { tmdb.getMovieDetails(any(), any(), any(), any()) } answers {
            TmdbMovieDetails(firstArg(), "Movie", releaseDate = "2026-10-04")
        }
        val progress = mutableListOf<CalendarLoadProgress>()
        val result = repository.loadCalendar("fixture-profile", month, ZoneId.of("UTC"), "US") { progress += it }
        assertEquals(2, result.entries.size)
        assertTrue(progress.any { it.month.entries.singleOrNull()?.sourceIds == setOf("arvio") })
        assertEquals(setOf("arvio", "trakt"), result.entries.single { it.media.id == 1 }.sourceIds)
        assertEquals(setOf("trakt"), result.entries.single { it.media.id == 2 }.sourceIds)
        assertEquals(2, mergeCalendarWatchlists(progress.last().watchlists.items).size)
        assertTrue(progress.last().watchlistsComplete)
        coVerify(exactly = 1) { tmdb.getMovieDetails(1, any(), any(), any()) }
        coVerify(exactly = 1) { tmdb.getMovieDetails(2, any(), any(), any()) }
    }

    @Test fun `confirmed episode dates render immediately while exact time request is bounded`() = runTest {
        every { Constants.TRAKT_CLIENT_ID } returns "fixture-client"
        val show = MediaItem(5, "Show", mediaType = MediaType.TV, traktId = 50)
        val episode = TmdbEpisode(seasonNumber = 1, episodeNumber = 1, airDate = "2026-10-04")
        coEvery { tmdb.getTvDetails(5, any(), any(), any()) } returns TmdbTvDetails(5, "Show", seasons = listOf(TmdbTvSeason(seasonNumber = 1)))
        coEvery { tmdb.getTvSeason(5, 1, any(), any()) } returns TmdbSeasonDetails(episodes = listOf(episode))
        coEvery { traktApi.getCalendarSeasonEpisodes(any(), 50, 1, any(), any()) } coAnswers {
            delay(20_000)
            listOf(TraktCalendarEpisode(1, 1, firstAired = "2026-10-04T18:00:00Z"))
        }
        val updates = mutableListOf<Pair<Long, CalendarMonthResult>>()
        val request = async {
            repository.loadMonth(CalendarWatchlists("fixture-profile", mapOf(ReleaseCalendarSource.ARVIO to listOf(show))),
                month, ZoneId.of("UTC"), "US") { updates += currentTime to it }
        }
        runCurrent()
        assertFalse(request.isCompleted)
        assertTrue(updates.any { (time, result) -> time == 0L && result.entries.singleOrNull()?.releaseInstant == null && result.entries.size == 1 })
        advanceUntilIdle()
        assertEquals(4_000L, currentTime)
        assertNull(request.await().entries.single().releaseInstant)
    }

    @Test fun `next episode from detail appears while historical seasons are still loading`() = runTest {
        val show = MediaItem(5, "Show", mediaType = MediaType.TV)
        val episode = TmdbEpisode(seasonNumber = 2, episodeNumber = 1, airDate = "2026-10-04")
        coEvery { tmdb.getTvDetails(5, any(), any(), any()) } returns TmdbTvDetails(5, "Show",
            seasons = listOf(TmdbTvSeason(seasonNumber = 1)), nextEpisodeToAir = episode)
        coEvery { tmdb.getTvSeason(5, 1, any(), any()) } coAnswers {
            delay(10_000)
            TmdbSeasonDetails(episodes = listOf(TmdbEpisode(airDate = "2025-01-01")))
        }
        val updates = mutableListOf<CalendarMonthResult>()
        val request = async {
            repository.loadMonth(CalendarWatchlists("fixture-profile", mapOf(ReleaseCalendarSource.ARVIO to listOf(show))),
                month, ZoneId.of("UTC"), "US") { updates += it }
        }
        runCurrent()
        assertFalse(request.isCompleted)
        assertEquals(2, updates.last().entries.single().seasonNumber)
        advanceUntilIdle()
        assertEquals(1, request.await().entries.size)
    }

    @Test fun `stalled time enrichment cannot starve healthy clearlogos`() = runTest {
        every { Constants.TRAKT_CLIENT_ID } returns "fixture-client"
        val shows = listOf(MediaItem(5, "Show A", mediaType = MediaType.TV, traktId = 50),
            MediaItem(6, "Show B", mediaType = MediaType.TV, traktId = 60))
        coEvery { tmdb.getTvDetails(any(), any(), any(), any()) } answers {
            TmdbTvDetails(firstArg(), "Show", seasons = listOf(TmdbTvSeason(seasonNumber = 1)))
        }
        coEvery { tmdb.getTvSeason(any(), 1, any(), any()) } returns TmdbSeasonDetails(
            episodes = listOf(TmdbEpisode(airDate = "2026-10-04")))
        coEvery { traktApi.getCalendarSeasonEpisodes(any(), any(), 1, any(), any()) } coAnswers {
            delay(20_000)
            emptyList()
        }
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } coAnswers {
            delay(1)
            TmdbMovieDetails(1, "Movie", releaseDate = "2026-10-04")
        }
        coEvery { media.getLogoUrl(any<MediaType>(), any()) } returns "https://images.example/logo.png"
        val updates = mutableListOf<Pair<Long, CalendarMonthResult>>()
        repository.loadMonth(CalendarWatchlists("fixture-profile",
            mapOf(ReleaseCalendarSource.ARVIO to shows + MediaItem(1, "Movie"))),
            month, ZoneId.of("UTC"), "US") { updates += currentTime to it }
        assertTrue(updates.any { (time, result) -> time == 1L && result.entries.any { it.media.id == 1 && it.logoUrl != null } })
        assertEquals(4_000L, currentTime)
    }

    @Test fun `profile switch prevents later provider or metadata publications`() = runTest {
        var profile = "fixture-profile"
        every { profiles.getProfileIdSync() } answers { profile }
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } coAnswers {
            delay(1_000)
            TmdbMovieDetails(1, "Own movie", releaseDate = "2026-10-04")
        }
        val updates = mutableListOf<CalendarLoadProgress>()
        val request = async {
            repository.loadCalendar("fixture-profile", month, ZoneId.of("UTC"), "US") { updates += it }
        }
        runCurrent()
        val beforeSwitch = updates.size
        profile = "other-profile"
        advanceUntilIdle()
        try {
            request.await()
            fail("Expected profile cancellation")
        } catch (_: CancellationException) {
            assertEquals(beforeSwitch, updates.size)
        }
    }

    @Test fun `canceling streamed month stops remaining title publications`() = runTest {
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } coAnswers {
            delay(10_000)
            TmdbMovieDetails(1, "Own movie", releaseDate = "2026-10-04")
        }
        val updates = mutableListOf<CalendarLoadProgress>()
        val request = launch {
            repository.loadCalendar("fixture-profile", month, ZoneId.of("UTC"), "US") { updates += it }
        }
        runCurrent()
        val beforeCancel = updates.size
        request.cancelAndJoin()
        advanceUntilIdle()
        assertEquals(beforeCancel, updates.size)
        assertTrue(updates.all { it.month.entries.isEmpty() })
    }

    @Test fun `public metadata restored from disk needs no network after repository recreation`() = runTest {
        coEvery { cache.readValue<TmdbMovieDetails>("movie:1:en-US", any()) } returns
            TmdbMovieDetails(1, "Saved movie", releaseDate = "2026-10-04")
        val result = repository.loadMonth(repository.loadWatchlists("fixture-profile"), month, ZoneId.of("UTC"), "US")
        assertEquals("Saved movie", result.entries.single().media.title)
        coVerify(exactly = 0) { tmdb.getMovieDetails(any(), any(), any(), any()) }
    }

    @Test fun `concurrent month loads share a single metadata request`() = runTest {
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } coAnswers {
            delay(1_000)
            TmdbMovieDetails(1, "Shared movie", releaseDate = "2026-10-04")
        }
        val lists = repository.loadWatchlists("fixture-profile")
        val first = async { repository.loadMonth(lists, month, ZoneId.of("UTC"), "US") }
        val second = async { repository.loadMonth(lists, month.plusMonths(1), ZoneId.of("UTC"), "US") }
        assertEquals(1, first.await().entries.size)
        assertTrue(second.await().entries.isEmpty())
        coVerify(exactly = 1) { tmdb.getMovieDetails(1, any(), any(), any()) }
    }

    @Test fun `preview remains visible during pending providers and drops removed titles on refresh`() = runTest {
        val saved = com.arflix.tv.data.model.CalendarRelease("saved", MediaItem(99, "Removed movie"),
            month.atDay(4), kind = com.arflix.tv.data.model.CalendarReleaseKind.MOVIE, sourceIds = setOf("trakt"))
        val pending = CalendarLoadProgress(CalendarWatchlists("fixture-profile", mapOf(ReleaseCalendarSource.ARVIO to emptyList())),
            CalendarMonthResult(emptyList(), emptyList()), false)
        assertEquals(listOf(saved), mergeCalendarPreview(listOf(saved), pending))
        val refreshed = pending.copy(watchlists = CalendarWatchlists("fixture-profile",
            mapOf(ReleaseCalendarSource.ARVIO to emptyList(), ReleaseCalendarSource.TRAKT to emptyList())))
        assertTrue(mergeCalendarPreview(listOf(saved), refreshed).isEmpty())
        assertTrue(mergeCalendarPreview(listOf(saved), pending.copy(watchlistsComplete = true)).isEmpty())
    }

    @Test fun `completed title metadata replaces obsolete cached release dates`() = runTest {
        val movie = MediaItem(99, "Rescheduled movie")
        val saved = com.arflix.tv.data.model.CalendarRelease("old-date", movie, month.atDay(4),
            kind = com.arflix.tv.data.model.CalendarReleaseKind.MOVIE, sourceIds = setOf("arvio"))
        val corrected = saved.copy(id = "new-date", date = month.atDay(12))
        val update = CalendarLoadProgress(CalendarWatchlists("fixture-profile", mapOf(ReleaseCalendarSource.ARVIO to listOf(movie))),
            CalendarMonthResult(listOf(corrected), emptyList()), true, setOf(MediaType.MOVIE to 99))
        assertEquals(listOf(corrected), mergeCalendarPreview(listOf(saved), update))
    }

    @Test fun `slow historical seasons cannot starve a later provider's first release`() = runTest {
        coEvery { own.getLocalWatchlistItems() } returns listOf(MediaItem(5, "Long show", mediaType = MediaType.TV))
        every { trakt.isAuthenticated } returns flowOf(true)
        coEvery { trakt.getWatchlistSyncResultWithAuthState() } coAnswers {
            delay(1)
            true to TraktRepository.WatchlistSyncResult(listOf(MediaItem(1, "New movie")), 1)
        }
        coEvery { tmdb.getTvDetails(5, any(), any(), any()) } returns TmdbTvDetails(5, "Long show",
            seasons = (1..12).map { TmdbTvSeason(seasonNumber = it, airDate = "2020-01-01") })
        val requestedSeasons = mutableListOf<Int>()
        coEvery { tmdb.getTvSeason(5, any(), any(), any()) } coAnswers {
            requestedSeasons += secondArg<Int>()
            delay(10_000)
            TmdbSeasonDetails(episodes = emptyList())
        }
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } returns TmdbMovieDetails(1, "New movie", releaseDate = "2026-10-04")
        val updates = mutableListOf<Pair<Long, CalendarLoadProgress>>()
        val request = async { repository.loadCalendar("fixture-profile", month, ZoneId.of("UTC"), "US") { updates += currentTime to it } }
        advanceTimeBy(2)
        runCurrent()
        assertEquals(listOf(12, 11), requestedSeasons)
        assertTrue(updates.any { (time, update) -> time == 1L && update.month.entries.any { it.media.id == 1 } })
        request.cancelAndJoin()
    }

    @Test fun `healthy removal wins alongside failed provider while its fallback survives`() = runTest {
        val removed = com.arflix.tv.data.model.CalendarRelease("removed", MediaItem(98, "Removed movie"),
            month.atDay(4), kind = com.arflix.tv.data.model.CalendarReleaseKind.MOVIE, sourceIds = setOf("trakt"))
        val unavailable = removed.copy(id = "unavailable", media = MediaItem(99, "MDB movie"), sourceIds = setOf("mdblist"))
        val progress = CalendarLoadProgress(CalendarWatchlists("fixture-profile",
            mapOf(ReleaseCalendarSource.TRAKT to emptyList(), ReleaseCalendarSource.MDBLIST to emptyList()),
            listOf("MDBList could not be refreshed."), setOf(ReleaseCalendarSource.MDBLIST)),
            CalendarMonthResult(emptyList(), listOf("MDBList could not be refreshed.")), true)
        assertEquals(listOf(unavailable), mergeCalendarPreview(listOf(removed, unavailable), progress))
    }

    @Test fun `refreshed duplicate title keeps failed provider membership`() = runTest {
        val movie = MediaItem(1, "Shared movie")
        val saved = com.arflix.tv.data.model.CalendarRelease("movie", movie, month.atDay(4),
            kind = com.arflix.tv.data.model.CalendarReleaseKind.MOVIE, sourceIds = setOf("trakt", "mdblist"))
        val refreshed = saved.copy(sourceIds = setOf("trakt"))
        val snapshot = CalendarWatchlists("fixture-profile", mapOf(ReleaseCalendarSource.TRAKT to listOf(movie),
            ReleaseCalendarSource.MDBLIST to emptyList()), listOf("MDBList unavailable"), setOf(ReleaseCalendarSource.MDBLIST))
        val progress = CalendarLoadProgress(snapshot, CalendarMonthResult(listOf(refreshed), snapshot.warnings), true,
            setOf(MediaType.MOVIE to 1))
        assertEquals(setOf("trakt", "mdblist"), mergeCalendarPreview(listOf(saved), progress).single().sourceIds)
    }

    @Test fun `reused source snapshot preserves failed provider previews when switching month`() = runTest {
        val saved = com.arflix.tv.data.model.CalendarRelease("mdb", MediaItem(99, "Unavailable movie"), month.atDay(4),
            kind = com.arflix.tv.data.model.CalendarReleaseKind.MOVIE, sourceIds = setOf("mdblist"))
        val snapshot = CalendarWatchlists("fixture-profile", mapOf(ReleaseCalendarSource.ARVIO to listOf(MediaItem(1, "Own movie")),
            ReleaseCalendarSource.MDBLIST to emptyList()), listOf("MDBList unavailable"), setOf(ReleaseCalendarSource.MDBLIST))
        coEvery { tmdb.getMovieDetails(1, any(), any(), any()) } returns TmdbMovieDetails(1, "Own movie", releaseDate = "2026-10-04")
        val result = repository.loadMonth(snapshot, month, ZoneId.of("UTC"), "US")
        val progress = CalendarLoadProgress(snapshot, result, true, result.completedTitles)
        assertEquals(setOf(MediaType.MOVIE to 1), result.completedTitles)
        assertEquals(setOf(1, 99), mergeCalendarPreview(listOf(saved), progress).map { it.media.id }.toSet())
        val emptySnapshot = snapshot.copy(items = mapOf(ReleaseCalendarSource.MDBLIST to emptyList()))
        val empty = repository.loadMonth(emptySnapshot, month, ZoneId.of("UTC"), "US")
        assertEquals(listOf(saved), mergeCalendarPreview(listOf(saved), CalendarLoadProgress(emptySnapshot, empty, true, empty.completedTitles)))
    }
}
