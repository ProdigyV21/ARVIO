package com.arflix.tv.ui.screens.collections

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.SportsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import java.io.IOException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CollectionDetailsLoadingTest {
    private val dispatcher = StandardTestDispatcher()
    private val store = ViewModelStore()
    private val catalogs = mockk<CatalogRepository>()
    private val media = mockk<MediaRepository>()
    private val sports = mockk<SportsRepository>()
    private lateinit var model: CollectionDetailsViewModel

    @Before fun setUp() {
        Dispatchers.setMain(dispatcher)
        every { sports.sportsCollectionCatalog(any()) } returns null
        coEvery { catalogs.getCatalogs() } returns listOf(catalog("first"), catalog("second"))
        coEvery { media.missingCollectionAddons(any()) } returns emptyList()
        every { media.peekCachedLogoUrl(any(), any()) } returns null
        coEvery { media.getLogoUrl(any<MediaType>(), any()) } returns null
        coEvery { media.loadCollectionCatalogPage(any(), any(), any(), any(), any()) } returns
            page((1..4).map(::item))
        coEvery { media.getMovieDetails(any()) } answers { item(firstArg()).copy(duration = "2h", imdbRating = "8.1") }
        coEvery { media.getTvDetails(any()) } answers { item(firstArg()).copy(mediaType = MediaType.TV, duration = "45m") }
        model = CollectionDetailsViewModel(catalogs, media, sports, mockk(relaxed = true))
        store.put("collection", model)
    }

    @After fun tearDown() {
        store.clear()
        dispatcher.scheduler.runCurrent()
        Dispatchers.resetMain()
    }

    @Test fun previewProvidersLoadOnlyForSettledFocusAndReuseCachedResults() = runTest {
        coEvery { media.getStreamingServices(any(), any(), any()) } returns
            com.arflix.tv.data.repository.StreamingServicesResult("NL", listOf(
                com.arflix.tv.data.repository.StreamingServiceInfo(8, "Netflix", "netflix.svg")))
        model.loadPreviewProvider(item(1))
        runCurrent()
        model.loadPreviewProvider(item(2))
        advanceUntilIdle()
        coVerify(exactly = 0) { media.getStreamingServices(MediaType.MOVIE, 1, any()) }
        coVerify(exactly = 1) { media.getStreamingServices(MediaType.MOVIE, 2, any()) }
        assertEquals("netflix.svg", model.providerLogos.value["MOVIE_2"])
        model.loadPreviewProvider(item(2))
        advanceUntilIdle()
        coVerify(exactly = 1) { media.getStreamingServices(MediaType.MOVIE, 2, any()) }
    }

    @Test fun unavailableProviderDoesNotRepeatOrInventABrand() = runTest {
        coEvery { media.getStreamingServices(any(), any(), any()) } returns null
        model.loadPreviewProvider(item(1))
        advanceUntilIdle()
        model.loadPreviewProvider(item(1))
        advanceUntilIdle()
        coVerify(exactly = 1) { media.getStreamingServices(any(), any(), any()) }
        assertNull(model.providerLogos.value["MOVIE_1"])
    }

    @Test fun changingCollectionCancelsPendingProviderRequests() = runTest {
        model.loadPreviewProvider(item(1))
        runCurrent()
        model.load("second")
        advanceUntilIdle()
        coVerify(exactly = 0) { media.getStreamingServices(any(), any(), any()) }
        assertTrue(model.providerLogos.value.isEmpty())
    }

    @Test fun partialPageKeepsLoadingAndBlocksDuplicateRequests() = runTest {
        val complete = CompletableDeferred<MediaRepository.CategoryPageResult>()
        lateinit var publish: (List<MediaItem>) -> Unit
        coEvery { media.loadCollectionCatalogPage(any(), 0, 8, MediaType.MOVIE, any()) } coAnswers {
            publish = arg<(List<MediaItem>) -> Unit>(4)
            publish(listOf(item(1)))
            complete.await()
        }
        model.load("first")
        runCurrent()
        assertEquals(listOf(1), model.uiState.value.movieItems.map { it.id })
        assertTrue(model.uiState.value.isLoadingMovies)
        assertEquals(0, model.uiState.value.loadedMovieOffset)
        model.load("first")
        model.loadMoreIfNeeded(CollectionTab.MOVIES)
        runCurrent()
        coVerify(exactly = 1) { media.loadCollectionCatalogPage(any(), any(), any(), any(), any()) }
        coVerify(exactly = 0) { media.getMovieDetails(any()) }

        complete.complete(page(listOf(item(1), item(2)), hasMore = true, nextOffset = 8))
        runCurrent()
        assertFalse(model.uiState.value.isLoadingMovies)
        assertEquals(8, model.uiState.value.loadedMovieOffset)
        assertTrue(model.uiState.value.hasMoreMovies)
        publish(listOf(item(99)))
        runCurrent()
        assertEquals(listOf(1, 2), model.uiState.value.movieItems.map { it.id })
    }

    @Test fun enrichmentSurvivesPartialFinalAndAppendPublicationsWithoutReordering() = runTest {
        val complete = CompletableDeferred<MediaRepository.CategoryPageResult>()
        val append = CompletableDeferred<MediaRepository.CategoryPageResult>()
        lateinit var publish: (List<MediaItem>) -> Unit
        val source = item(1).copy(image = "source-poster", badge = "Source", sourceOrder = 7)
        coEvery { media.loadCollectionCatalogPage(any(), 0, 8, MediaType.MOVIE, any()) } coAnswers {
            publish = arg<(List<MediaItem>) -> Unit>(4)
            publish(listOf(source))
            complete.await()
        }
        coEvery { media.loadCollectionCatalogPage(any(), 8, 12, MediaType.MOVIE, any()) } coAnswers { append.await() }
        coEvery { media.getMovieDetails(1) } returns item(1).copy(
            title = "Detail title", image = "detail-poster", duration = "2h", contentRating = "PG-13", imdbRating = "8.1"
        )
        model.load("first")
        runCurrent()
        model.enrichVisibleDetails(listOf(source))
        runCurrent()
        publish(listOf(source, item(2)))
        runCurrent()
        assertEquals("2h", model.uiState.value.movieItems.first().duration)
        complete.complete(page(listOf(source, item(2)), hasMore = true, nextOffset = 8))
        runCurrent()
        model.loadMoreIfNeeded(CollectionTab.MOVIES)
        runCurrent()
        model.enrichVisibleDetails(listOf(item(2)))
        runCurrent()
        append.complete(page(listOf(item(3)), nextOffset = 9))
        runCurrent()
        val items = model.uiState.value.movieItems
        assertEquals(listOf(1, 2, 3), items.map { it.id })
        assertEquals(source.copy(duration = "2h", contentRating = "PG-13", imdbRating = "8.1"), items.first())
        assertEquals("2h", items[1].duration)
        assertEquals("", items[2].duration)
        assertEquals(9, model.uiState.value.loadedMovieOffset)
        coVerify(exactly = 0) { media.getMovieDetails(3) }
    }

    @Test fun enrichmentFillsMissingArtworkAndPreservesSourceArtworkAcrossFinalPublication() = runTest {
        val complete = CompletableDeferred<MediaRepository.CategoryPageResult>()
        val source = listOf(
            item(1),
            item(2).copy(image = " ", backdrop = ""),
            item(3).copy(image = "source-poster", backdrop = "source-backdrop"),
            item(4).copy(image = "source-poster-4", backdrop = " ")
        )
        coEvery { media.loadCollectionCatalogPage(any(), 0, 8, MediaType.MOVIE, any()) } coAnswers {
            arg<(List<MediaItem>) -> Unit>(4).invoke(source)
            complete.await()
        }
        coEvery { media.getMovieDetails(any()) } answers {
            val id = firstArg<Int>()
            item(id).copy(image = "detail-poster-$id", backdrop = "detail-backdrop-$id")
        }
        model.load("first")
        runCurrent()
        model.enrichVisibleDetails(model.uiState.value.movieItems)
        runCurrent()
        val expected = listOf(
            source[0].copy(image = "detail-poster-1", backdrop = "detail-backdrop-1"),
            source[1].copy(image = "detail-poster-2", backdrop = "detail-backdrop-2"),
            source[2],
            source[3].copy(backdrop = "detail-backdrop-4")
        )
        assertEquals(expected, model.uiState.value.movieItems)
        assertTrue(model.uiState.value.isLoadingMovies)
        complete.complete(page(source))
        runCurrent()
        assertEquals(expected, model.uiState.value.movieItems)
        assertFalse(model.uiState.value.isLoadingMovies)
    }

    @Test fun onlyVisibleDetailsRunWithTwoPermitsAndAttemptDeduplication() = runTest {
        val release = CompletableDeferred<Unit>()
        var active = 0
        var peak = 0
        coEvery { media.getMovieDetails(any()) } coAnswers {
            val id = firstArg<Int>()
            active += 1
            peak = maxOf(peak, active)
            try {
                release.await()
                item(id).copy(duration = "2h")
            } finally {
                active -= 1
            }
        }
        model.load("first")
        runCurrent()
        val visible = model.uiState.value.movieItems.take(3)
        model.enrichVisibleDetails(visible)
        model.enrichVisibleDetails(visible)
        runCurrent()
        assertEquals(2, active)
        coVerify(exactly = 0) { media.getMovieDetails(3) }
        coVerify(exactly = 0) { media.getMovieDetails(4) }
        model.enrichVisibleDetails(visible.take(1))
        release.complete(Unit)
        runCurrent()
        coVerify(exactly = 0) { media.getMovieDetails(3) }
        model.enrichVisibleDetails(visible)
        runCurrent()
        model.enrichVisibleDetails(visible)
        runCurrent()
        assertEquals(2, peak)
        (1..3).forEach { id -> coVerify(exactly = 1) { media.getMovieDetails(id) } }
        coVerify(exactly = 0) { media.getMovieDetails(4) }
    }

    @Test fun failedEnrichmentDoesNotRepeatOrRemoveTheCard() = runTest {
        coEvery { media.getMovieDetails(1) } throws IOException("unavailable")
        model.load("first")
        runCurrent()
        val before = model.uiState.value.movieItems
        repeat(3) {
            model.enrichVisibleDetails(before.take(1))
            runCurrent()
        }
        assertEquals(before, model.uiState.value.movieItems)
        coVerify(exactly = 1) { media.getMovieDetails(1) }
    }

    @Test fun movieAndSeriesWithSameIdEnrichIndependently() = runTest {
        coEvery { catalogs.getCatalogs() } returns listOf(catalog("first").copy(
            collectionSources = listOf(CollectionSourceConfig(CollectionSourceKind.CURATED_IDS))
        ))
        coEvery { media.loadCollectionCatalogPage(any(), 0, 8, any(), any()) } answers {
            page(listOf(item(1).copy(mediaType = arg<MediaType>(3))))
        }
        model.load("first")
        advanceUntilIdle()
        model.enrichVisibleDetails(model.uiState.value.movieItems)
        runCurrent()
        model.enrichVisibleDetails(model.uiState.value.seriesItems)
        runCurrent()
        assertEquals("2h", model.uiState.value.movieItems.single().duration)
        assertEquals("45m", model.uiState.value.seriesItems.single().duration)
        coVerify(exactly = 1) { media.getMovieDetails(1) }
        coVerify(exactly = 1) { media.getTvDetails(1) }
    }

    @Test fun changingSeriesGenrePreservesMoviesWhileAnAppendIsInFlight() = runTest {
        coEvery { catalogs.getCatalogs() } returns listOf(catalog("first").copy(
            collectionSources = listOf("movie", "series").map { type ->
                CollectionSourceConfig(CollectionSourceKind.TMDB_WATCH_PROVIDER, mediaType = type, tmdbWatchProviderId = 8)
            }
        ))
        coEvery { media.loadCollectionCatalogPage(any(), 0, 8, MediaType.MOVIE, any()) } returns
            page(listOf(item(1)), hasMore = true, nextOffset = 8)
        coEvery { media.loadCollectionCatalogPage(any(), 0, 8, MediaType.TV, any()) } answers {
            val genreId = firstArg<CatalogConfig>().collectionSources.single().tmdbGenreId
            page(listOf(item(if (genreId == 10764) 3 else 2).copy(mediaType = MediaType.TV)))
        }
        var appendCancelled = false
        coEvery { media.loadCollectionCatalogPage(any(), 8, 12, MediaType.MOVIE, any()) } coAnswers {
            try { awaitCancellation() } finally { appendCancelled = true }
        }

        model.load("first")
        advanceUntilIdle()
        assertTrue(model.uiState.value.isLoadingMoreMovies)
        assertEquals(listOf(2), model.uiState.value.seriesItems.map { it.id })
        model.selectGenre(CollectionTab.SERIES, 10764)
        advanceUntilIdle()

        val state = model.uiState.value
        assertTrue(appendCancelled)
        assertFalse(state.isLoadingMovies)
        assertFalse(state.isLoadingMoreMovies)
        assertTrue(state.hasMoreMovies)
        assertEquals(8, state.loadedMovieOffset)
        assertEquals(listOf(1), state.movieItems.map { it.id })
        assertEquals(listOf(3), state.seriesItems.map { it.id })
        coVerify(exactly = 1) { media.loadCollectionCatalogPage(any(), 0, 8, MediaType.MOVIE, any()) }

        coEvery { media.loadCollectionCatalogPage(any(), 8, 12, MediaType.MOVIE, any()) } returns
            page(listOf(item(4)), nextOffset = 9)
        model.loadMoreIfNeeded(CollectionTab.MOVIES)
        advanceUntilIdle()
        assertEquals(listOf(1, 4), model.uiState.value.movieItems.map { it.id })
    }

    @Test fun cancellationIsNotPublishedAsAnEmptyPageOrLoadError() = runTest {
        coEvery { media.loadCollectionCatalogPage(any(), any(), any(), any(), any()) } throws CancellationException()
        model.load("first")
        advanceUntilIdle()
        assertTrue(model.uiState.value.isLoadingMovies)
        assertNull(model.uiState.value.error)
        coVerify(exactly = 1) { media.loadCollectionCatalogPage(any(), any(), any(), any(), any()) }
        coEvery { media.loadCollectionCatalogPage(any(), any(), any(), any(), any()) } returns page(listOf(item(1)))
        model.load("first")
        advanceUntilIdle()
        assertFalse(model.uiState.value.isLoadingMovies)
        assertEquals(listOf(1), model.uiState.value.movieItems.map { it.id })
        coVerify(exactly = 2) { media.loadCollectionCatalogPage(any(), any(), any(), any(), any()) }
    }

    @Test fun newCollectionRejectsLateInitialCallbacksAndFinalResults() = runTest {
        val release = CompletableDeferred<Unit>()
        lateinit var publish: (List<MediaItem>) -> Unit
        coEvery { media.loadCollectionCatalogPage(any(), 0, 8, any(), any()) } coAnswers {
            if (firstArg<CatalogConfig>().id == "first") {
                publish = arg<(List<MediaItem>) -> Unit>(4)
                publish(listOf(item(1)))
                withContext(NonCancellable) { release.await() }
                page(listOf(item(99)))
            } else page(listOf(item(2)))
        }
        model.load("first")
        runCurrent()
        model.load("second")
        runCurrent()
        publish(listOf(item(98)))
        release.complete(Unit)
        runCurrent()
        assertEquals("second", model.uiState.value.catalog?.id)
        assertEquals(listOf(2), model.uiState.value.movieItems.map { it.id })
        assertFalse(model.uiState.value.isLoadingMovies)
    }

    @Test fun lateDetailsAndAppendCannotChangeANewCollection() = runTest {
        val release = CompletableDeferred<Unit>()
        coEvery { media.loadCollectionCatalogPage(any(), 0, 8, any(), any()) } answers {
            page(listOf(item(1)), hasMore = firstArg<CatalogConfig>().id == "first", nextOffset = 8)
        }
        coEvery { media.loadCollectionCatalogPage(any(), 8, 12, any(), any()) } coAnswers {
            withContext(NonCancellable) { release.await() }
            page(listOf(item(99)))
        }
        coEvery { media.getMovieDetails(1) } coAnswers {
            withContext(NonCancellable) { release.await() }
            item(1).copy(duration = "old duration")
        }
        model.load("first")
        runCurrent()
        model.loadMoreIfNeeded(CollectionTab.MOVIES)
        model.enrichVisibleDetails(model.uiState.value.movieItems)
        runCurrent()
        model.load("second")
        runCurrent()
        release.complete(Unit)
        runCurrent()
        assertEquals("second", model.uiState.value.catalog?.id)
        assertEquals(listOf(item(1)), model.uiState.value.movieItems)
    }

    @Test fun clearingViewModelCancelsPagesLogosAndDetailWork() = runTest {
        var pageCancelled = false
        var logoCancelled = false
        var detailsCancelled = false
        coEvery { media.loadCollectionCatalogPage(any(), any(), any(), any(), any()) } coAnswers {
            arg<(List<MediaItem>) -> Unit>(4).invoke(listOf(item(1), item(2), item(3)))
            try { awaitCancellation() } finally { pageCancelled = true }
        }
        coEvery { media.getMovieDetails(any()) } coAnswers {
            try { awaitCancellation() } finally { detailsCancelled = true }
        }
        coEvery { media.getLogoUrl(any<MediaType>(), any()) } coAnswers {
            try { awaitCancellation() } finally { logoCancelled = true }
        }
        model.load("first")
        runCurrent()
        model.enrichVisibleDetails(model.uiState.value.movieItems)
        model.preloadLogos(model.uiState.value.movieItems)
        runCurrent()
        val state = model.uiState.value
        store.clear()
        runCurrent()
        assertTrue(pageCancelled)
        assertTrue(logoCancelled)
        assertTrue(detailsCancelled)
        assertTrue(model.viewModelScope.coroutineContext[Job]!!.isCancelled)
        assertEquals(state, model.uiState.value)
        coVerify(exactly = 0) { media.getMovieDetails(3) }
    }

    private fun catalog(id: String) = CatalogConfig(
        id = id, title = id, sourceType = CatalogSourceType.PREINSTALLED, kind = CatalogKind.COLLECTION,
        collectionSources = listOf(CollectionSourceConfig(CollectionSourceKind.CURATED_IDS, mediaType = "movie"))
    )

    private fun item(id: Int) = MediaItem(id, "Item $id")

    private fun page(items: List<MediaItem>, hasMore: Boolean = false, nextOffset: Int = items.size) =
        MediaRepository.CategoryPageResult(items, hasMore, nextOffset)
}
