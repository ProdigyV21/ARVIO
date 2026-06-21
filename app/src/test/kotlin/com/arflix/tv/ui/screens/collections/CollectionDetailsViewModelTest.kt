package com.arflix.tv.ui.screens.collections

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.NextEpisode
import com.arflix.tv.data.model.StreamBehaviorHints
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.StreamAvailabilityRepository
import com.arflix.tv.data.repository.StreamSummaryResolver
import com.arflix.tv.util.RuntimeApiKeys
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.cancel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class CollectionDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @After
    fun tearDown() {
        RuntimeApiKeys.clearTmdbApiKey()
        RuntimeApiKeys.clearWatchmodeApiKey()
    }

    @Test
    fun `service catalog requires TMDB key even when Watchmode key exists`() {
        RuntimeApiKeys.clearTmdbApiKey()
        RuntimeApiKeys.setWatchmodeApiKey("watchmode-key")
        val catalog = serviceCatalog()
        val catalogRepository = mockk<CatalogRepository>()
        coEvery { catalogRepository.getCatalogs() } returns listOf(catalog)
        val mediaRepository = mockk<MediaRepository>(relaxed = true)
        coEvery {
            mediaRepository.loadCollectionCatalogPage(any(), any(), any())
        } returns MediaRepository.CategoryPageResult(emptyList(), hasMore = false)
        val viewModel = collectionViewModel(catalogRepository, mediaRepository)

        viewModel.load(catalog.id)
        mainDispatcherRule.dispatcher.scheduler.runCurrent()

        val state = viewModel.uiState.value
        assertEquals(catalog.id, state.catalog?.id)
        assertTrue(state.error.orEmpty().contains("TMDB API key"))
        coVerify(exactly = 0) {
            mediaRepository.loadCollectionCatalogPage(any(), any(), any())
        }
        viewModel.clearForTest()
        mainDispatcherRule.dispatcher.scheduler.runCurrent()
    }

    @Test
    fun `service catalog loads with TMDB key when Watchmode key is missing`() = runTest {
        RuntimeApiKeys.setTmdbApiKey("tmdb-key")
        RuntimeApiKeys.clearWatchmodeApiKey()
        val catalog = serviceCatalog()
        val catalogRepository = mockk<CatalogRepository>()
        coEvery { catalogRepository.getCatalogs() } returns listOf(catalog)
        val mediaRepository = mockk<MediaRepository>(relaxed = true)
        coEvery {
            mediaRepository.loadCollectionCatalogPage(any(), any(), any())
        } returns MediaRepository.CategoryPageResult(emptyList(), hasMore = false)
        val viewModel = collectionViewModel(catalogRepository, mediaRepository)

        viewModel.load(catalog.id)
        advanceUntilIdle()

        assertEquals(null, viewModel.uiState.value.error)
        coVerify(exactly = 1) {
            mediaRepository.loadCollectionCatalogPage(any(), offset = 0, limit = any())
        }
        viewModel.clearForTest()
        runCurrent()
    }

    @Test
    fun `focused preview ignores stale enrichment when focus changes quickly`() = runTest {
        val catalogRepository = mockk<CatalogRepository>(relaxed = true)
        val mediaRepository = mockk<MediaRepository>(relaxed = true)
        val streamResolver = FakeStreamSummaryResolver()
        val first = MediaItem(
            id = 1,
            title = "Old focus",
            mediaType = MediaType.MOVIE,
            image = "old-poster",
            backdrop = "old-backdrop"
        )
        val second = MediaItem(
            id = 2,
            title = "Current focus",
            mediaType = MediaType.MOVIE,
            image = "current-poster",
            backdrop = "current-backdrop"
        )
        coEvery { mediaRepository.getMovieDetails(1) } coAnswers {
            delay(700)
            first.copy(title = "Wrong stale detail", imdbRating = "9.9")
        }
        coEvery { mediaRepository.getMovieDetails(2) } returns second.copy(
            title = "Current enriched",
            imdbRating = "8.4",
            duration = "2h 05m"
        )
        coEvery { mediaRepository.getOrResolveImdbId(MediaType.MOVIE, 1) } returns "tt0000001"
        coEvery { mediaRepository.getOrResolveImdbId(MediaType.MOVIE, 2) } returns "tt0000002"
        val viewModel = collectionViewModel(
            catalogRepository = catalogRepository,
            mediaRepository = mediaRepository,
            streamAvailabilityRepository = StreamAvailabilityRepository(streamResolver)
        )

        viewModel.focusPreview(first)
        advanceTimeBy(100)
        viewModel.focusPreview(second)
        advanceTimeBy(CollectionDetailsViewModel.PREVIEW_ENRICH_DEBOUNCE_MS)
        advanceUntilIdle()

        val preview = viewModel.uiState.value.preview
        assertEquals(2, preview.item?.id)
        assertEquals("Current enriched", preview.item?.title)
        assertEquals("8.4", preview.imdbRating)
        viewModel.clearForTest()
        runCurrent()
    }

    @Test
    fun `focused preview applies movie stream summary using resolved imdb id`() = runTest {
        val catalogRepository = mockk<CatalogRepository>(relaxed = true)
        val mediaRepository = mockk<MediaRepository>(relaxed = true)
        val streamResolver = FakeStreamSummaryResolver(
            movieStreams = listOf(stream("Movie 2160p Atmos 45 GB", "4K", "45 GB"))
        )
        val item = MediaItem(
            id = 11,
            title = "Stream Movie",
            year = "2026",
            mediaType = MediaType.MOVIE,
            image = "poster"
        )
        coEvery { mediaRepository.getMovieDetails(11) } returns item.copy(duration = "1h 55m")
        coEvery { mediaRepository.getImdbRating(MediaType.MOVIE, 11) } returns "8.1"
        coEvery { mediaRepository.getOrResolveImdbId(MediaType.MOVIE, 11) } returns "tt1111111"
        val viewModel = collectionViewModel(
            catalogRepository = catalogRepository,
            mediaRepository = mediaRepository,
            streamAvailabilityRepository = StreamAvailabilityRepository(streamResolver)
        )

        viewModel.focusPreview(item)
        assertEquals(true, viewModel.uiState.value.preview.isLoadingStreamSummary)
        advanceTimeBy(CollectionDetailsViewModel.PREVIEW_ENRICH_DEBOUNCE_MS)
        advanceUntilIdle()

        val preview = viewModel.uiState.value.preview
        assertEquals(11, preview.item?.id)
        assertEquals("tt1111111", streamResolver.lastMovieImdbId)
        assertEquals("Stream Movie", streamResolver.lastMovieTitle)
        assertEquals(2026, streamResolver.lastMovieYear)
        assertEquals("4K", preview.streamSummary?.bestResolution)
        assertEquals("Atmos", preview.streamSummary?.bestAudioTag)
        assertEquals(false, preview.isLoadingStreamSummary)
        viewModel.clearForTest()
        runCurrent()
    }

    @Test
    fun `focused preview uses cached preview without refetching same movie`() = runTest {
        val catalogRepository = mockk<CatalogRepository>(relaxed = true)
        val mediaRepository = mockk<MediaRepository>(relaxed = true)
        val streamResolver = FakeStreamSummaryResolver()
        val item = MediaItem(
            id = 12,
            title = "Cached Movie",
            year = "2026",
            mediaType = MediaType.MOVIE,
            image = "poster"
        )
        coEvery { mediaRepository.getMovieDetails(12) } returns item.copy(
            duration = "2h 01m",
            imdbRating = "7.9"
        )
        coEvery { mediaRepository.getOrResolveImdbId(MediaType.MOVIE, 12) } returns "tt1212121"
        val viewModel = collectionViewModel(
            catalogRepository = catalogRepository,
            mediaRepository = mediaRepository,
            streamAvailabilityRepository = StreamAvailabilityRepository(streamResolver)
        )

        viewModel.focusPreview(item)
        advanceTimeBy(CollectionDetailsViewModel.PREVIEW_ENRICH_DEBOUNCE_MS)
        advanceUntilIdle()
        assertEquals(false, viewModel.uiState.value.preview.isLoading)
        assertEquals(false, viewModel.uiState.value.preview.isLoadingStreamSummary)

        viewModel.focusPreview(item)
        advanceTimeBy(CollectionDetailsViewModel.PREVIEW_ENRICH_DEBOUNCE_MS)
        advanceUntilIdle()

        coVerify(exactly = 1) { mediaRepository.getMovieDetails(12) }
        assertEquals(1, streamResolver.movieCallCount)
        viewModel.clearForTest()
        runCurrent()
    }

    @Test
    fun `focused preview ignores stale stream summary when focus changes quickly`() = runTest {
        val catalogRepository = mockk<CatalogRepository>(relaxed = true)
        val mediaRepository = mockk<MediaRepository>(relaxed = true)
        val streamResolver = FakeStreamSummaryResolver(
            movieStreamsByImdbId = mapOf(
                "tt2222221" to listOf(stream("First 2160p 50 GB", "4K", "50 GB")),
                "tt2222222" to listOf(stream("Second 1080p 8 GB", "1080p", "8 GB"))
            ),
            movieDelayByImdbId = mapOf("tt2222221" to 700L)
        )
        val first = MediaItem(id = 21, title = "First", year = "2024", mediaType = MediaType.MOVIE)
        val second = MediaItem(id = 22, title = "Second", year = "2025", mediaType = MediaType.MOVIE)
        coEvery { mediaRepository.getMovieDetails(21) } returns first
        coEvery { mediaRepository.getMovieDetails(22) } returns second
        coEvery { mediaRepository.getOrResolveImdbId(MediaType.MOVIE, 21) } returns "tt2222221"
        coEvery { mediaRepository.getOrResolveImdbId(MediaType.MOVIE, 22) } returns "tt2222222"
        val viewModel = collectionViewModel(
            catalogRepository = catalogRepository,
            mediaRepository = mediaRepository,
            streamAvailabilityRepository = StreamAvailabilityRepository(streamResolver)
        )

        viewModel.focusPreview(first)
        advanceTimeBy(CollectionDetailsViewModel.PREVIEW_ENRICH_DEBOUNCE_MS)
        advanceTimeBy(50)
        viewModel.focusPreview(second)
        advanceTimeBy(CollectionDetailsViewModel.PREVIEW_ENRICH_DEBOUNCE_MS)
        advanceUntilIdle()

        val preview = viewModel.uiState.value.preview
        assertEquals(22, preview.item?.id)
        assertEquals("1080p", preview.streamSummary?.bestResolution)
        assertEquals(false, preview.isLoadingStreamSummary)
        viewModel.clearForTest()
        runCurrent()
    }

    @Test
    fun `focused tv preview calls episode stream summary with next episode metadata`() = runTest {
        val catalogRepository = mockk<CatalogRepository>(relaxed = true)
        val mediaRepository = mockk<MediaRepository>(relaxed = true)
        val streamResolver = FakeStreamSummaryResolver(
            episodeStreams = listOf(stream("Show S02E03 1080p 7 GB", "1080p", "7 GB"))
        )
        val item = MediaItem(
            id = 31,
            title = "Stream Show",
            year = "2023",
            mediaType = MediaType.TV,
            genreIds = listOf(18, 9648),
            originalLanguage = "sv",
            nextEpisode = NextEpisode(id = 301, seasonNumber = 2, episodeNumber = 3, name = "Next")
        )
        coEvery { mediaRepository.getTvDetails(31) } returns item
        coEvery { mediaRepository.getOrResolveImdbId(MediaType.TV, 31) } returns "tt3131313"
        val viewModel = collectionViewModel(
            catalogRepository = catalogRepository,
            mediaRepository = mediaRepository,
            streamAvailabilityRepository = StreamAvailabilityRepository(streamResolver)
        )

        viewModel.focusPreview(item)
        advanceTimeBy(CollectionDetailsViewModel.PREVIEW_ENRICH_DEBOUNCE_MS)
        advanceUntilIdle()

        assertEquals("tt3131313", streamResolver.lastEpisodeImdbId)
        assertEquals("Stream Show", streamResolver.lastEpisodeTitle)
        assertEquals(2023, streamResolver.lastEpisodeYear)
        assertEquals(2, streamResolver.lastSeason)
        assertEquals(3, streamResolver.lastEpisode)
        assertEquals(31, streamResolver.lastTmdbId)
        assertEquals(listOf(18, 9648), streamResolver.lastGenreIds)
        assertEquals("sv", streamResolver.lastOriginalLanguage)
        assertEquals("1080p", viewModel.uiState.value.preview.streamSummary?.bestResolution)
        assertEquals(false, viewModel.uiState.value.preview.isLoadingStreamSummary)
        viewModel.clearForTest()
        runCurrent()
    }

    @Test
    fun `focused tv preview falls back to first episode for stream summary`() = runTest {
        val catalogRepository = mockk<CatalogRepository>(relaxed = true)
        val mediaRepository = mockk<MediaRepository>(relaxed = true)
        val streamResolver = FakeStreamSummaryResolver(
            episodeStreams = listOf(stream("Show S01E01 720p 2 GB", "720p", "2 GB"))
        )
        val item = MediaItem(
            id = 32,
            title = "Fallback Show",
            year = "2022",
            mediaType = MediaType.TV,
            genreIds = listOf(35),
            originalLanguage = "en"
        )
        coEvery { mediaRepository.getTvDetails(32) } returns item
        coEvery { mediaRepository.getOrResolveImdbId(MediaType.TV, 32) } returns "tt3232323"
        val viewModel = collectionViewModel(
            catalogRepository = catalogRepository,
            mediaRepository = mediaRepository,
            streamAvailabilityRepository = StreamAvailabilityRepository(streamResolver)
        )

        viewModel.focusPreview(item)
        advanceTimeBy(CollectionDetailsViewModel.PREVIEW_ENRICH_DEBOUNCE_MS)
        advanceUntilIdle()

        assertEquals(1, streamResolver.lastSeason)
        assertEquals(1, streamResolver.lastEpisode)
        assertEquals("720p", viewModel.uiState.value.preview.streamSummary?.bestResolution)
        viewModel.clearForTest()
        runCurrent()
    }

    private fun collectionViewModel(
        catalogRepository: CatalogRepository,
        mediaRepository: MediaRepository,
        streamAvailabilityRepository: StreamAvailabilityRepository = StreamAvailabilityRepository(FakeStreamSummaryResolver())
    ): CollectionDetailsViewModel {
        return CollectionDetailsViewModel(
            catalogRepository = catalogRepository,
            mediaRepository = mediaRepository,
            streamAvailabilityRepository = streamAvailabilityRepository
        )
    }

    private fun serviceCatalog(): CatalogConfig {
        return CatalogConfig(
            id = "service_netflix",
            title = "Netflix",
            sourceType = CatalogSourceType.PREINSTALLED,
            kind = CatalogKind.COLLECTION,
            collectionGroup = CollectionGroupKind.SERVICE,
            collectionSources = listOf(
                CollectionSourceConfig(
                    kind = CollectionSourceKind.WATCHMODE_SOURCE,
                    mediaType = "movie",
                    watchRegion = "SE",
                    watchmodeSourceId = 203
                ),
                CollectionSourceConfig(
                    kind = CollectionSourceKind.TMDB_WATCH_PROVIDER,
                    mediaType = "movie",
                    watchRegion = "SE",
                    tmdbWatchProviderId = 8
                )
            )
        )
    }

    private class FakeStreamSummaryResolver(
        var movieStreams: List<StreamSource> = emptyList(),
        var episodeStreams: List<StreamSource> = emptyList(),
        var movieStreamsByImdbId: Map<String, List<StreamSource>> = emptyMap(),
        var movieDelayByImdbId: Map<String, Long> = emptyMap()
    ) : StreamSummaryResolver {
        var lastMovieImdbId: String? = null
        var lastMovieTitle: String? = null
        var lastMovieYear: Int? = null
        var lastEpisodeImdbId: String? = null
        var lastEpisodeTitle: String? = null
        var lastEpisodeYear: Int? = null
        var lastSeason: Int? = null
        var lastEpisode: Int? = null
        var lastTmdbId: Int? = null
        var lastGenreIds: List<Int>? = null
        var lastOriginalLanguage: String? = null
        var movieCallCount = 0
        var episodeCallCount = 0

        override suspend fun movieStreams(imdbId: String, title: String, year: Int?): List<StreamSource> {
            movieCallCount += 1
            lastMovieImdbId = imdbId
            lastMovieTitle = title
            lastMovieYear = year
            movieDelayByImdbId[imdbId]?.let { delay(it) }
            return movieStreamsByImdbId[imdbId] ?: movieStreams
        }

        override suspend fun episodeStreams(
            imdbId: String,
            title: String,
            year: Int?,
            season: Int,
            episode: Int,
            tmdbId: Int?,
            genreIds: List<Int>,
            originalLanguage: String?
        ): List<StreamSource> {
            episodeCallCount += 1
            lastEpisodeImdbId = imdbId
            lastEpisodeTitle = title
            lastEpisodeYear = year
            lastSeason = season
            lastEpisode = episode
            lastTmdbId = tmdbId
            lastGenreIds = genreIds
            lastOriginalLanguage = originalLanguage
            return episodeStreams
        }
    }

    private fun stream(source: String, quality: String, size: String) = StreamSource(
        source = source,
        addonName = "AIOStreams",
        addonId = "aiostreams",
        quality = quality,
        size = size,
        url = "https://example.com/${source.hashCode()}",
        behaviorHints = StreamBehaviorHints(cached = true, filename = source)
    )
}

private fun CollectionDetailsViewModel.clearForTest() {
    viewModelScope.cancel()
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    val dispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
