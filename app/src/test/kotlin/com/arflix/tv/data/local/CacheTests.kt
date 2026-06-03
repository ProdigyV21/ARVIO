package com.arflix.tv.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.arflix.tv.data.api.*
import com.arflix.tv.data.model.*
import com.arflix.tv.data.repository.*
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import io.mockk.*
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
class CacheTests {

    private lateinit var db: AppDatabase
    private lateinit var cacheDao: CacheDao
    private val gson = Gson()

    // MediaRepository dependencies
    private lateinit var tmdbApi: TmdbApi
    private lateinit var traktRepository: TraktRepository
    private lateinit var traktApi: TraktApi
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var streamRepository: StreamRepository
    private lateinit var homeServerRepository: HomeServerRepository
    private lateinit var mediaRepository: MediaRepository
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        cacheDao = db.cacheDao()

        tmdbApi = mockk(relaxed = true)
        traktRepository = mockk(relaxed = true)
        traktApi = mockk(relaxed = true)
        okHttpClient = OkHttpClient()
        streamRepository = mockk(relaxed = true)
        homeServerRepository = mockk(relaxed = true)

        mediaRepository = MediaRepository(
            tmdbApi = tmdbApi,
            traktRepository = traktRepository,
            traktApi = traktApi,
            okHttpClient = okHttpClient,
            streamRepository = streamRepository,
            homeServerRepository = homeServerRepository,
            cacheDao = cacheDao,
            context = context
        )
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    // ==========================================
    // 1. CachePolicyManager Tests
    // ==========================================

    @Test
    fun cachePolicyManager_isMediaItemFresh_worksCorrectly() {
        val freshMovie = CachedMediaItem(
            id = 1,
            mediaType = MediaType.MOVIE.name,
            title = "Movie 1",
            updatedAt = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(6)
        )
        val staleMovie = CachedMediaItem(
            id = 2,
            mediaType = MediaType.MOVIE.name,
            title = "Movie 2",
            updatedAt = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(8)
        )
        val freshTv = CachedMediaItem(
            id = 3,
            mediaType = MediaType.TV.name,
            title = "TV Show 1",
            updatedAt = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2)
        )
        val staleTv = CachedMediaItem(
            id = 4,
            mediaType = MediaType.TV.name,
            title = "TV Show 2",
            updatedAt = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(4)
        )

        assertThat(CachePolicyManager.isMediaItemFresh(freshMovie)).isTrue()
        assertThat(CachePolicyManager.isMediaItemFresh(staleMovie)).isFalse()
        assertThat(CachePolicyManager.isMediaItemFresh(freshTv)).isTrue()
        assertThat(CachePolicyManager.isMediaItemFresh(staleTv)).isFalse()

        // forceRefresh should always result in stale
        assertThat(CachePolicyManager.isMediaItemFresh(freshMovie, forceRefresh = true)).isFalse()
        assertThat(CachePolicyManager.isMediaItemFresh(freshTv, forceRefresh = true)).isFalse()
    }

    @Test
    fun cachePolicyManager_otherEntitiesFreshness_worksCorrectly() {
        val freshCastTime = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(13)
        val staleCastTime = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(15)

        val freshEpisodeTime = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2)
        val staleEpisodeTime = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(4)

        val freshReviewTime = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(6)
        val staleReviewTime = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(8)

        val freshCollectionTime = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(23)
        val staleCollectionTime = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(25)

        assertThat(CachePolicyManager.isCastFresh(freshCastTime)).isTrue()
        assertThat(CachePolicyManager.isCastFresh(staleCastTime)).isFalse()

        assertThat(CachePolicyManager.isEpisodesFresh(freshEpisodeTime)).isTrue()
        assertThat(CachePolicyManager.isEpisodesFresh(staleEpisodeTime)).isFalse()

        assertThat(CachePolicyManager.isReviewsFresh(freshReviewTime)).isTrue()
        assertThat(CachePolicyManager.isReviewsFresh(staleReviewTime)).isFalse()

        assertThat(CachePolicyManager.isCollectionFresh(freshCollectionTime)).isTrue()
        assertThat(CachePolicyManager.isCollectionFresh(staleCollectionTime)).isFalse()

        // forceRefresh
        assertThat(CachePolicyManager.isCastFresh(freshCastTime, forceRefresh = true)).isFalse()
        assertThat(CachePolicyManager.isEpisodesFresh(freshEpisodeTime, forceRefresh = true)).isFalse()
        assertThat(CachePolicyManager.isReviewsFresh(freshReviewTime, forceRefresh = true)).isFalse()
        assertThat(CachePolicyManager.isCollectionFresh(freshCollectionTime, forceRefresh = true)).isFalse()
    }

    // ==========================================
    // 2. CacheDao Tests
    // ==========================================

    @Test
    fun cacheDao_insertAndGetMediaItem_savesCorrectly() = runTest {
        val cachedItem = CachedMediaItem(
            id = 100,
            mediaType = MediaType.MOVIE.name,
            title = "Test Movie"
        )
        cacheDao.insertMediaItem(cachedItem)

        val retrieved = cacheDao.getMediaItem(100, MediaType.MOVIE.name)
        assertThat(retrieved).isNotNull()
        assertThat(retrieved?.title).isEqualTo("Test Movie")
    }

    @Test
    fun cacheDao_clearAllMetadata_clearsAllTables() = runTest {
        val cachedItem = CachedMediaItem(
            id = 100,
            mediaType = MediaType.MOVIE.name,
            title = "Test Movie"
        )
        cacheDao.insertMediaItem(cachedItem)

        val cachedEpisode = CachedEpisode(
            id = 101,
            tvId = 200,
            seasonNumber = 1,
            episodeNumber = 1,
            name = "Pilot"
        )
        cacheDao.insertEpisodes(listOf(cachedEpisode))

        // Verify inserted
        assertThat(cacheDao.getMediaItem(100, MediaType.MOVIE.name)).isNotNull()
        assertThat(cacheDao.getEpisodes(200, 1)).isNotEmpty()

        // Clear all
        cacheDao.clearAllMetadata()

        // Verify empty
        assertThat(cacheDao.getMediaItem(100, MediaType.MOVIE.name)).isNull()
        assertThat(cacheDao.getEpisodes(200, 1)).isEmpty()
    }

    @Test
    fun cacheDao_saveTvSeason_overwritesCorrectly() = runTest {
        val tvId = 123
        val seasonNum = 1

        val ep1 = CachedEpisode(id = 1, tvId = tvId, seasonNumber = seasonNum, episodeNumber = 1, name = "First")
        val ep2 = CachedEpisode(id = 2, tvId = tvId, seasonNumber = seasonNum, episodeNumber = 2, name = "Second")

        cacheDao.saveTvSeason(tvId, seasonNum, listOf(ep1, ep2))

        var episodes = cacheDao.getEpisodes(tvId, seasonNum)
        assertThat(episodes.size).isEqualTo(2)

        // Save new list, should overwrite old ones
        val ep3 = CachedEpisode(id = 3, tvId = tvId, seasonNumber = seasonNum, episodeNumber = 3, name = "Third")
        cacheDao.saveTvSeason(tvId, seasonNum, listOf(ep3))

        episodes = cacheDao.getEpisodes(tvId, seasonNum)
        assertThat(episodes.size).isEqualTo(1)
        assertThat(episodes.first().name).isEqualTo("Third")
    }

    // ==========================================
    // 3. MediaRepository Cache Flow Tests
    // ==========================================

    @Test
    fun mediaRepository_getMovieDetails_cacheMiss_networkSuccess() = runTest {
        val movieId = 404
        val tmdbMovieDetails = TmdbMovieDetails(
            id = movieId,
            title = "Network Movie",
            overview = "Overview of Network Movie",
            releaseDate = "2024-01-01"
        )

        coEvery { tmdbApi.getMovieDetails(movieId, any(), any()) } returns tmdbMovieDetails
        coEvery { tmdbApi.getMovieExternalIds(movieId, any()) } returns TmdbExternalIds("tt123", 456)

        // Make call
        val result = mediaRepository.getMovieDetails(movieId)

        // Verify it returned network data
        assertThat(result.id).isEqualTo(movieId)
        assertThat(result.title).isEqualTo("Network Movie")

        // Verify it cached the entity in the database
        val dbCached = cacheDao.getMediaItem(movieId, MediaType.MOVIE.name)
        assertThat(dbCached).isNotNull()
        assertThat(dbCached?.title).isEqualTo("Network Movie")
    }

    @Test
    fun mediaRepository_getMovieDetails_cacheHit_fresh_skipsNetwork() = runTest {
        val movieId = 500
        val cachedItem = CachedMediaItem(
            id = movieId,
            mediaType = MediaType.MOVIE.name,
            title = "Cached Movie",
            updatedAt = System.currentTimeMillis() // Fresh
        )
        cacheDao.insertMediaItem(cachedItem)

        // Call repository
        val result = mediaRepository.getMovieDetails(movieId)

        // Verify it returned cache without network call
        assertThat(result.title).isEqualTo("Cached Movie")
        coVerify(exactly = 0) { tmdbApi.getMovieDetails(any(), any(), any()) }
    }

    @Test
    fun mediaRepository_getMovieDetails_cacheHit_stale_networkSuccess() = runTest {
        val movieId = 500
        val cachedItem = CachedMediaItem(
            id = movieId,
            mediaType = MediaType.MOVIE.name,
            title = "Cached Movie",
            updatedAt = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(10) // Stale
        )
        cacheDao.insertMediaItem(cachedItem)

        val tmdbMovieDetails = TmdbMovieDetails(
            id = movieId,
            title = "Network Movie"
        )
        coEvery { tmdbApi.getMovieDetails(movieId, any(), any()) } returns tmdbMovieDetails

        // Call repository
        val result = mediaRepository.getMovieDetails(movieId)

        // Verify network details returned and updated in DB
        assertThat(result.title).isEqualTo("Network Movie")
        coVerify(exactly = 1) { tmdbApi.getMovieDetails(movieId, any(), any()) }

        val dbCached = cacheDao.getMediaItem(movieId, MediaType.MOVIE.name)
        assertThat(dbCached?.title).isEqualTo("Network Movie")
        assertThat(dbCached?.updatedAt).isGreaterThan(System.currentTimeMillis() - 5000)
    }

    @Test
    fun mediaRepository_getMovieDetails_cacheHit_stale_networkFailure_fallsBackToCache() = runTest {
        val movieId = 600
        val cachedItem = CachedMediaItem(
            id = movieId,
            mediaType = MediaType.MOVIE.name,
            title = "Stale Movie Fallback",
            updatedAt = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(10) // Stale
        )
        cacheDao.insertMediaItem(cachedItem)

        // Mock network error
        coEvery { tmdbApi.getMovieDetails(movieId, any(), any()) } throws RuntimeException("Network Offline")

        // Call repository
        val result = mediaRepository.getMovieDetails(movieId)

        // Verify it returned the stale cache data instead of crashing
        assertThat(result.title).isEqualTo("Stale Movie Fallback")
        coVerify(exactly = 1) { tmdbApi.getMovieDetails(movieId, any(), any()) }
    }

    @Test
    fun mediaRepository_getTvDetails_cacheMiss_networkSuccess() = runTest {
        val tvId = 700
        val tmdbTvDetails = TmdbTvDetails(
            id = tvId,
            name = "Network Show",
            overview = "Show overview"
        )

        coEvery { tmdbApi.getTvDetails(tvId, any(), any()) } returns tmdbTvDetails

        val result = mediaRepository.getTvDetails(tvId)

        assertThat(result.id).isEqualTo(tvId)
        assertThat(result.title).isEqualTo("Network Show")

        val dbCached = cacheDao.getMediaItem(tvId, MediaType.TV.name)
        assertThat(dbCached).isNotNull()
        assertThat(dbCached?.title).isEqualTo("Network Show")
    }

    @Test
    fun mediaRepository_getSeasonEpisodes_cacheMiss_networkSuccess() = runTest {
        val tvId = 800
        val seasonNum = 1
        val tmdbSeason = TmdbSeasonDetails(
            id = 999,
            seasonNumber = seasonNum,
            name = "Season 1",
            episodes = listOf(
                TmdbEpisode(id = 1, episodeNumber = 1, seasonNumber = seasonNum, name = "Ep 1"),
                TmdbEpisode(id = 2, episodeNumber = 2, seasonNumber = seasonNum, name = "Ep 2")
            )
        )

        coEvery { tmdbApi.getTvSeason(tvId, seasonNum, any(), any()) } returns tmdbSeason

        val result = mediaRepository.getSeasonEpisodes(tvId, seasonNum)

        assertThat(result.size).isEqualTo(2)
        assertThat(result[0].name).isEqualTo("Ep 1")

        val dbEpisodes = cacheDao.getEpisodes(tvId, seasonNum)
        assertThat(dbEpisodes.size).isEqualTo(2)
        assertThat(dbEpisodes[0].name).isEqualTo("Ep 1")
    }

    @Test
    fun mediaRepository_getCast_cacheHit_fresh_skipsNetwork() = runTest {
        val mediaId = 900
        val mediaType = MediaType.MOVIE
        val cachedCast = CachedCastMember(
            id = 1,
            mediaId = mediaId,
            mediaType = mediaType.name,
            name = "Actor 1",
            character = "Hero",
            profilePath = null,
            updatedAt = System.currentTimeMillis()
        )
        cacheDao.saveCredits(mediaId, mediaType.name, listOf(cachedCast))

        val result = mediaRepository.getCast(mediaType, mediaId)

        assertThat(result.size).isEqualTo(1)
        assertThat(result.first().name).isEqualTo("Actor 1")
        coVerify(exactly = 0) { tmdbApi.getCredits(any(), any(), any(), any()) }
    }

    @Test
    fun mediaRepository_getSimilar_cacheMiss_networkSuccess() = runTest {
        val mediaId = 1000
        val mediaType = MediaType.MOVIE
        val similarResponse = TmdbListResponse(
            results = listOf(
                TmdbMediaItem(id = 1001, title = "Similar 1", mediaType = "movie"),
                TmdbMediaItem(id = 1002, title = "Similar 2", mediaType = "movie")
            )
        )

        coEvery { tmdbApi.getRecommendations("movie", mediaId, any(), any()) } returns similarResponse

        val result = mediaRepository.getSimilar(mediaType, mediaId)

        assertThat(result.size).isEqualTo(2)
        assertThat(result[0].title).isEqualTo("Similar 1")

        val dbSimilar = cacheDao.getSimilarItems(mediaId, mediaType.name)
        assertThat(dbSimilar.size).isEqualTo(2)
    }

    @Test
    fun mediaRepository_getReviews_cacheMiss_networkSuccess() = runTest {
        val mediaId = 1100
        val mediaType = MediaType.MOVIE
        val reviewsResponse = TmdbReviewsResponse(
            results = listOf(
                TmdbReview(
                    id = "rev1",
                    author = "Critic 1",
                    content = "Great show!",
                    authorDetails = TmdbAuthorDetails(username = "critic1", rating = 9.0f)
                )
            )
        )

        coEvery { tmdbApi.getReviews("movie", mediaId, any(), any()) } returns reviewsResponse

        val result = mediaRepository.getReviews(mediaType, mediaId)

        assertThat(result.size).isEqualTo(1)
        assertThat(result.first().content).isEqualTo("Great show!")

        val dbReviews = cacheDao.getReviews(mediaId, mediaType.name)
        assertThat(dbReviews.size).isEqualTo(1)
        assertThat(dbReviews.first().content).isEqualTo("Great show!")
    }
}
