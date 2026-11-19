package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.arflix.tv.data.api.*
import com.arflix.tv.data.repository.sync.SyncProvider
import com.arflix.tv.data.repository.sync.SyncProviderStore
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.traktDataStore
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Provider

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class TraktContinueWatchingRefreshTest {
    private class MemoryStore(initial: Preferences) : DataStore<Preferences> {
        override val data = MutableStateFlow(initial)
        private val mutex = Mutex()
        override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences = mutex.withLock {
            transform(data.value).also { data.value = it }
        }
    }

    private val api = mockk<TraktApi>(relaxed = true)
    private val tmdb = mockk<TmdbApi>()
    private lateinit var repository: TraktRepository
    private val stamp = "2026-09-23T10:00:00Z"
    private val show = TraktShowInfo("Show", 2026, TraktIds(trakt = 10, tmdb = 10))

    @Before fun setUp() {
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        val store = MemoryStore(preferencesOf(stringPreferencesKey("trakt_access_token") to "test-token"))
        val settings = MemoryStore(emptyPreferences())
        every { any<Context>().traktDataStore } returns store
        every { any<Context>().settingsDataStore } returns settings
        val profiles = mockk<ProfileManager>(relaxed = true) {
            every { getProfileIdSync() } returns "p"
            every { activeProfileId } returns MutableStateFlow("p")
            every { profileStringKey(any()) } answers { stringPreferencesKey(firstArg()) }
            every { profileLongKey(any()) } answers { longPreferencesKey(firstArg()) }
            every { profileBooleanKey(any()) } answers { booleanPreferencesKey(firstArg()) }
            every { profileStringKeyFor(any(), any()) } answers { stringPreferencesKey(secondArg()) }
            every { profileLongKeyFor(any(), any()) } answers { longPreferencesKey(secondArg()) }
            every { profileBooleanKeyFor(any(), any()) } answers { booleanPreferencesKey(secondArg()) }
        }
        val providers = mockk<SyncProviderStore>(relaxed = true) {
            coEvery { readProviders(any()) } returns emptySet()
            coEvery { getProvider() } returns SyncProvider.TRAKT
        }
        coEvery { tmdb.getTvDetails(any(), any(), any(), any()) } throws IllegalStateException("No artwork in fixture")
        repository = TraktRepository(RuntimeEnvironment.getApplication(), api, tmdb, OkHttpClient(),
            Provider { mockk(relaxed = true) }, profiles, mockk(relaxed = true), providers,
            mockk(relaxed = true), mockk(relaxed = true), ContinueWatchingUpdates())
        coEvery { api.getLastActivities(any(), any(), any()) } returns mockk<TraktLastActivities>(relaxed = true) {
            every { episodes } returns TraktActivityTimestamps(watchedAt = stamp)
            every { shows } returns null
        }
        coEvery { api.getWatchingNow(any(), any(), any(), any()) } returns Response.success(null)
        coEvery { api.getWatchedShows(any(), any(), any(), any(), any(), any()) } answers {
            if (arg<Int?>(3) == 1) listOf(TraktWatchedShow(1, stamp, stamp, show,
                listOf(TraktWatchedSeason(1, listOf(TraktWatchedEpisode(1, 1, stamp)))))) else emptyList()
        }
        coEvery { api.getShowProgress(any(), any(), any(), any(), any(), any(), any()) } returns
            TraktShowProgress(3, 1, stamp, null, TraktNextEpisode(1, 2, "Next", TraktIds()), emptyList())
    }

    @After fun tearDown() {
        unmockkStatic("com.arflix.tv.util.DataStoresKt")
    }

    @Test fun calendarResolvesAnimeWithOnlyTvdbIdentityWithoutLoadingProgress() = runBlocking {
        val anime = TraktShowInfo("Anime fixture", 2026, TraktIds(trakt = 50, tvdb = 900))
        coEvery { api.getWatchedShows(any(), any(), any(), any(), any(), any()) } answers {
            if (arg<Int?>(3) == 1) listOf(TraktWatchedShow(1, stamp, stamp, anime, emptyList())) else emptyList()
        }
        coEvery { tmdb.findByExternalId("900", any(), "tvdb_id") } returns
            TmdbFindResponse(tvResults = listOf(TmdbFindItem(id = 700)))
        coEvery { tmdb.getTvDetails(700, any(), any(), any()) } returns
            TmdbTvDetails(id = 700, name = "Anime fixture", firstAirDate = "2026-01-01")
        val item = repository.getCalendarWatchingShows().single()
        assertEquals(700, item.id)
        assertEquals(50, item.traktId)
        coVerify(exactly = 0) { api.getShowProgress(any(), any(), any(), any(), any(), any(), any()) }
    }

    @Test fun refreshReadsActivePlaybackEvenWhenActivityDidNotChange() = runBlocking {
        assertEquals(2, repository.getContinueWatching(true).single().episode)
        coEvery { api.getWatchingNow(any(), any(), any(), any()) } returns Response.success(
            TraktWatchingItem("2026-09-23T11:00:00Z", stamp, "scrobble", "episode", null,
                TraktEpisodeInfo(1, 3, "Playing", TraktIds(), 60), show))
        assertEquals(3, repository.getContinueWatching(true).single().episode)
        coVerify(exactly = 2) { api.getWatchingNow(any(), any(), any(), any()) }
        coVerify(exactly = 1) { api.getShowProgress(any(), any(), any(), any(), any(), any(), any()) }
    }

    @Test fun explicitFreshSyncDoesNotAcceptAPartiallyCachedSnapshot() = runBlocking {
        val saved = repository.getContinueWatching(true)
        coEvery { api.getPlaybackProgress(any(), any(), any(), any(), any(), any(), any()) } throws java.io.IOException("offline")
        try {
            repository.getContinueWatching(forceRefresh = true, requireFreshTrakt = true)
            fail("A partial cached response is not a successful manual refresh")
        } catch (_: IllegalStateException) { }
        assertEquals(saved, repository.getCachedContinueWatching())
        assertTrue(repository.getContinueWatching(forceRefresh = true).isNotEmpty())
    }

    @Test fun explicitFreshSyncAcceptsACompleteResponse() = runBlocking {
        assertEquals(2, repository.getContinueWatching(forceRefresh = true, requireFreshTrakt = true).single().episode)
    }

    @Test fun pausedShowReturnsToUpNextWithoutAnotherShowProgressRead() = runBlocking {
        val paused = TraktPlaybackItem(1L, 20f, stamp, "episode", null,
            TraktEpisodeInfo(1, 2, "Paused", TraktIds(), 45), show)
        coEvery { api.getPlaybackProgress(any(), any(), any(), any(), any(), any(), any()) } answers {
            if (arg<Int?>(4) == 1) listOf(paused) else emptyList()
        }
        assertFalse(repository.getContinueWatching(true).single().isUpNext)
        coEvery { api.getPlaybackProgress(any(), any(), any(), any(), any(), any(), any()) } returns emptyList()
        val next = repository.getContinueWatching(true).single()
        assertTrue(next.isUpNext)
        assertEquals(2, next.episode)
        coVerify(exactly = 1) { api.getShowProgress(any(), any(), any(), any(), any(), any(), any()) }
    }

    @Test fun continueWatchingUsesDroppedShowsInsteadOfTheRemovedResetEndpoint() = runBlocking {
        val method = TraktApi::class.java.methods.single { it.name == "getDroppedShows" }
        assertEquals("users/hidden/dropped", method.getAnnotation(retrofit2.http.GET::class.java)!!.value)
        coEvery { api.getDroppedShows(any(), any(), any(), any(), any(), any()) } returns
            listOf(TraktHiddenItem(null, "show", show))
        assertTrue(repository.getContinueWatching(true).isEmpty())
    }

    @Test fun freshNextEpisodesSurviveAnUnrelatedProgressFailureAndRetryWithoutWaitingForCache() = runBlocking {
        val other = show.copy(title = "Other", ids = TraktIds(trakt = 20, tmdb = 20))
        val vision = show.copy(title = "VisionQuest", ids = TraktIds(trakt = 199379, tmdb = 213375))
        var watched = listOf(show, other)
        coEvery { api.getWatchedShows(any(), any(), any(), any(), any(), any()) } answers {
            if (arg<Int?>(3) == 1) watched.map {
                TraktWatchedShow(1, stamp, stamp, it,
                    listOf(TraktWatchedSeason(1, listOf(TraktWatchedEpisode(1, 1, stamp)))))
            } else emptyList()
        }
        assertEquals(2, repository.getContinueWatching(true).size)
        watched = watched + vision
        coEvery { api.getLastActivities(any(), any(), any()) } returns mockk<TraktLastActivities>(relaxed = true) {
            every { episodes } returns TraktActivityTimestamps(watchedAt = "2026-10-20T22:10:00Z")
            every { shows } returns null
        }
        coEvery { api.getShowProgress(any(), any(), any(), "10", any(), any(), any()) } returns
            TraktShowProgress(4, 2, stamp, null, TraktNextEpisode(1, 3, "Third", TraktIds()), emptyList())
        coEvery { api.getShowProgress(any(), any(), any(), "20", any(), any(), any()) } throws java.io.IOException("offline")
        val partial = repository.getContinueWatching(true).associateBy { it.id }
        assertEquals(3, partial.getValue(10).episode)
        assertEquals(2, partial.getValue(20).episode)
        assertEquals(2, partial.getValue(213375).episode)
        coEvery { api.getShowProgress(any(), any(), any(), "20", any(), any(), any()) } returns
            TraktShowProgress(4, 2, stamp, null, TraktNextEpisode(1, 3, "Recovered", TraktIds()), emptyList())
        assertEquals(3, repository.getContinueWatching().single { it.id == 20 }.episode)
    }

    @Test fun aCompletedShowIsNotResurrectedWhenAnotherShowFails() = runBlocking {
        repository.getContinueWatching(true)
        coEvery { api.getLastActivities(any(), any(), any()) } throws java.io.IOException("offline")
        coEvery { api.getShowProgress(any(), any(), any(), "10", any(), any(), any()) } returns
            TraktShowProgress(3, 3, stamp, null, null, emptyList())
        coEvery { api.getDroppedShows(any(), any(), any(), any(), any(), any()) } throws java.io.IOException("offline")
        assertTrue(repository.getContinueWatching(true).isEmpty())
        assertTrue(repository.getCachedContinueWatching().isEmpty())
    }

    @Test fun successfulNextEpisodeReplacesAWatchedPauseEvenWhenPlaybackFails() = runBlocking {
        val paused = TraktPlaybackItem(1L, 20f, stamp, "episode", null,
            TraktEpisodeInfo(1, 2, "Paused", TraktIds(), 45), show)
        coEvery { api.getPlaybackProgress(any(), any(), any(), any(), any(), any(), any()) } answers {
            if (arg<Int?>(4) == 1) listOf(paused) else emptyList()
        }
        assertEquals(2, repository.getContinueWatching(true).single().episode)
        coEvery { api.getPlaybackProgress(any(), any(), any(), any(), any(), any(), any()) } throws java.io.IOException("offline")
        coEvery { api.getLastActivities(any(), any(), any()) } throws java.io.IOException("offline")
        coEvery { api.getWatchedShows(any(), any(), any(), any(), any(), any()) } answers {
            if (arg<Int?>(3) == 1) listOf(TraktWatchedShow(2, stamp, stamp, show,
                listOf(TraktWatchedSeason(1, listOf(TraktWatchedEpisode(1, 1, stamp), TraktWatchedEpisode(2, 1, stamp))))))
            else emptyList()
        }
        coEvery { api.getShowProgress(any(), any(), any(), any(), any(), any(), any()) } returns
            TraktShowProgress(3, 2, stamp, null, TraktNextEpisode(1, 3, "Third", TraktIds()), emptyList())
        val next = repository.getContinueWatching(true).single()
        assertEquals(3, next.episode)
        assertTrue(next.isUpNext)
    }

    /** Opt-in, read-only smoke test for a reported account; credentials stay in the environment. */
    @Test fun liveNextEpisodeMatchesProviderWhenConfigured(): Unit = runBlocking {
        val token = System.getenv("ARVIO_CW_TRAKT_TOKEN")
        val clientId = System.getenv("ARVIO_CW_TRAKT_CLIENT_ID")
        val target = System.getenv("ARVIO_CW_TRAKT_SHOW_ID")?.toIntOrNull()
        org.junit.Assume.assumeTrue(!token.isNullOrBlank() && !clientId.isNullOrBlank() && target != null)
        val live = retrofit2.Retrofit.Builder().baseUrl("https://api.trakt.tv/")
            .client(OkHttpClient()).addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build().create(TraktApi::class.java)
        val auth = "Bearer $token"
        val watched = live.getWatchedShows(auth, clientId!!, page = 1, limit = 100, extended = "progress")
            .single { it.show.ids.trakt == target }
        val expected = live.getShowProgress(auth, clientId, showId = target.toString()).nextEpisode!!
        coEvery { api.getWatchedShows(any(), any(), any(), any(), any(), any()) } answers {
            if (arg<Int?>(3) == 1) listOf(watched) else emptyList()
        }
        coEvery { api.getHiddenProgressShows(any(), any(), any(), any(), any(), any()) } coAnswers {
            live.getHiddenProgressShows(auth, clientId, page = arg(5), limit = arg(4))
        }
        coEvery { api.getDroppedShows(any(), any(), any(), any(), any(), any()) } coAnswers {
            live.getDroppedShows(auth, clientId, page = arg(5), limit = arg(4))
        }
        coEvery { api.getShowProgress(any(), any(), any(), any(), any(), any(), any()) } coAnswers {
            live.getShowProgress(auth, clientId, showId = arg(3))
        }
        val actual = repository.getContinueWatching(true).single { it.id == watched.show.ids.tmdb }
        assertTrue(actual.isUpNext)
        assertEquals(expected.season, actual.season)
        assertEquals(expected.number, actual.episode)
        assertEquals(System.getenv("ARVIO_CW_EXPECT_EPISODE")?.toIntOrNull() ?: expected.number, actual.episode)
    }

    @Test fun firstLoginRateLimitStopsQueuedReadsAndSubsequentRefreshWithoutCache() = runBlocking {
        coEvery { api.getLastActivities(any(), any(), any()) } throws
            HttpException(Response.error<Any>(429, "rate limited".toResponseBody()))
        repeat(2) {
            try { repository.getContinueWatching(true); fail("An outage must not clear Home's saved row") }
            catch (_: IllegalStateException) { }
        }
        coVerify(exactly = 1) { api.getLastActivities(any(), any(), any()) }
        coVerify(exactly = 0) { api.getPlaybackProgress(any(), any(), any(), any(), any(), any(), any()) }
        coVerify(exactly = 0) { api.getWatchingNow(any(), any(), any(), any()) }
        coVerify(exactly = 0) { api.getShowProgress(any(), any(), any(), any(), any(), any(), any()) }
    }

    @Test fun watchingEndpointRateLimitAlsoStopsTheNextRefresh() = runBlocking {
        coEvery { api.getWatchingNow(any(), any(), any(), any()) } returns
            Response.error(429, "rate limited".toResponseBody())
        repeat(2) { runCatching { repository.getContinueWatching(true) } }
        coVerify(exactly = 1) { api.getLastActivities(any(), any(), any()) }
        coVerify(exactly = 1) { api.getWatchingNow(any(), any(), any(), any()) }
    }
}
