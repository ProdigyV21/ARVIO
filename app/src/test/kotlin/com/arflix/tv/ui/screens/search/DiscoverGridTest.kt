package com.arflix.tv.ui.screens.search

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.TraktRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Step 2 of the combined search/discover page: as soon as a filter is set the
 * five browse rows are replaced by one endlessly paging grid.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class DiscoverGridTest {
    @Test fun fullyWatchedBatchOffersExplicitContinuationInsteadOfFalseEnd() = runBlocking {
        every { trakt.getWatchedMoviesFromCache() } returns setOf(1)
        var calls = 0
        coEvery {
            repository.discoverMovies(genres = any(), page = any(), sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } coAnswers {
            calls++
            if (arg<Int>(3) <= 5) listOf(movie(1)) else listOf(movie(2))
        }
        model.setHideWatched(true)
        val paused = withTimeout(5_000) { model.uiState.first { it.gridScanPaused } }
        assertTrue(paused.discoverGridItems.isEmpty())
        assertFalse(paused.gridEndReached)
        val before = calls
        model.loadMoreDiscoverGrid()
        assertEquals(before, calls)
        model.retryDiscoverGrid()
        val continued = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.isNotEmpty() } }
        assertEquals(listOf(2), continued.discoverGridItems.map { it.id })
        assertFalse(continued.gridScanPaused)
    }

    @Test fun failedPageWaitsForExplicitRetryAndKeepsExistingTitles() = runBlocking {
        var attempts = 0
        coEvery {
            repository.discoverMovies(genres = "28", page = any(), sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } coAnswers {
            if (arg<Int>(3) == 1) listOf(movie(1))
            else if (++attempts == 1) throw java.io.IOException("offline")
            else listOf(movie(2), movie(2))
        }
        model.toggleGenre(action)
        withTimeout(5_000) { model.uiState.first { it.discoverGridItems.size == 1 } }
        model.loadMoreDiscoverGrid()
        val failed = withTimeout(5_000) { model.uiState.first { it.gridLoadFailed } }
        assertFalse(failed.gridEndReached)
        assertEquals(listOf(1), failed.discoverGridItems.map { it.id })
        model.loadMoreDiscoverGrid()
        assertEquals(1, attempts)
        model.retryDiscoverGrid()
        val retried = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.size == 2 } }
        assertEquals(listOf(1, 2), retried.discoverGridItems.map { it.id })
        assertFalse(retried.gridLoadFailed)
        assertEquals(2, attempts)
    }

    @Test fun changingFiltersCancelsTheInFlightNextPage() = runBlocking {
        val started = kotlinx.coroutines.CompletableDeferred<Unit>()
        val cancelled = kotlinx.coroutines.CompletableDeferred<Unit>()
        coEvery {
            repository.discoverMovies(genres = "28", page = any(), sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } coAnswers {
            if (arg<Int>(3) == 1) listOf(movie(1)) else {
                started.complete(Unit)
                try { kotlinx.coroutines.awaitCancellation() }
                finally { cancelled.complete(Unit) }
            }
        }
        model.toggleGenre(action)
        withTimeout(5_000) { model.uiState.first { it.discoverGridItems.size == 1 } }
        model.loadMoreDiscoverGrid()
        withTimeout(5_000) { started.await() }
        model.clearDiscoverFilters()
        model.toggleGenre(action)
        withTimeout(5_000) { cancelled.await() }
        val refreshed = withTimeout(5_000) { model.uiState.first { !it.isGridLoading && it.discoverGridItems.isNotEmpty() } }
        assertEquals(listOf(1), refreshed.discoverGridItems.map { it.id })
    }

    private val repository = mockk<MediaRepository>(relaxed = true)
    private val trakt = mockk<TraktRepository>(relaxed = true)
    private val store = ViewModelStore()
    private lateinit var model: SearchViewModel

    private fun movie(id: Int) = MediaItem(id = id, title = "Movie $id", mediaType = MediaType.MOVIE)
    private fun show(id: Int) = MediaItem(id = id, title = "Show $id", mediaType = MediaType.TV)
    private val action = MOVIE_GENRES.first { it.id == 28 }

    @Before fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        coEvery { repository.getLogoUrl(any<MediaType>(), any()) } returns null
        coEvery { repository.discoverMovies(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()) } returns emptyList()
        coEvery { repository.discoverTv(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()) } returns emptyList()
        model = SearchViewModel(repository, trakt, mockk(relaxed = true))
        store.put("search", model)
    }

    @After fun tearDown() {
        val job = model.viewModelScope.coroutineContext[kotlinx.coroutines.Job]
        try {
            store.clear()
            // IO children must finish cancellation before Main is removed.
            runBlocking { withTimeout(5_000) { job?.join() } }
        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test fun noFilterKeepsTheBrowseRowsAndNoGrid() = runBlocking {
        assertFalse(model.uiState.value.hasDiscoverFilters)
        assertTrue(model.uiState.value.discoverGridItems.isEmpty())
    }

    @Test fun settingAGenreSwitchesFromRowsToTheGrid() = runBlocking {
        coEvery {
            repository.discoverMovies(genres = "28", page = 1, sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(1), movie(2))

        model.toggleGenre(action)
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.size == 2 } }

        assertTrue(state.hasDiscoverFilters)
        assertTrue(state.discoverCategories.isEmpty())
        assertFalse(state.isGridLoading)
        assertEquals(listOf(1, 2), state.discoverGridItems.map { it.id })
    }

    @Test fun loadMoreAppendsTheNextPageAndDropsDuplicates() = runBlocking {
        coEvery {
            repository.discoverMovies(genres = "28", page = 1, sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(1), movie(2))
        coEvery {
            repository.discoverMovies(genres = "28", page = 2, sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(2), movie(3))

        model.toggleGenre(action)
        withTimeout(5_000) { model.uiState.first { it.discoverGridItems.size == 2 } }
        model.loadMoreDiscoverGrid()
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.size == 3 } }

        assertEquals(listOf(1, 2, 3), state.discoverGridItems.map { it.id })
        assertFalse(state.isGridLoadingMore)
    }

    @Test fun anEmptyPageEndsThePagingSoTheGridStopsAsking() = runBlocking {
        coEvery {
            repository.discoverMovies(genres = "28", page = 1, sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns emptyList()

        model.toggleGenre(action)
        withTimeout(5_000) { model.uiState.first { it.gridEndReached } }
        model.loadMoreDiscoverGrid()
        model.loadMoreDiscoverGrid()

        coVerify(exactly = 0) {
            repository.discoverMovies(genres = "28", page = 2, sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        }
    }

    @Test fun clearingTheGenreBringsTheRowsBack() = runBlocking {
        coEvery {
            repository.discoverMovies(genres = "28", page = 1, sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(1))
        coEvery {
            repository.discoverMovies(genres = null, page = any(), sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(9))

        model.toggleGenre(action)
        withTimeout(5_000) { model.uiState.first { it.discoverGridItems.isNotEmpty() } }
        model.toggleGenre(action)
        val state = withTimeout(5_000) { model.uiState.first { it.discoverCategories.isNotEmpty() } }

        assertFalse(state.hasDiscoverFilters)
        assertTrue(state.discoverGridItems.isEmpty())
    }

    @Test fun theGridFollowsTheMediaTypeAndUsesTheSeriesGenreId() = runBlocking {
        coEvery {
            repository.discoverTv(genres = "10759", page = 1, sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), airDateLte = any(), airDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), firstAirDateLte = any(), firstAirDateGte = any())
        } returns listOf(show(5))

        model.selectType(DiscoverType.TV_SHOWS)
        model.toggleGenre(action)
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.isNotEmpty() } }

        assertEquals(listOf(5), state.discoverGridItems.map { it.id })
    }

    // ── Step 3: the six filters the new row adds ────────────────────────

    @Test fun twoGenresAreSentAsOneAndedValue() = runBlocking {
        val sciFi = MOVIE_GENRES.first { it.id == 878 }
        coEvery {
            repository.discoverMovies(genres = "28,878", page = 1, sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(1))

        model.toggleGenre(action)
        model.toggleGenre(sciFi)
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.isNotEmpty() } }

        assertEquals(listOf(28, 878), state.selectedGenres.map { it.id })
        assertTrue(state.matchAllGenres)
    }

    @Test fun theSortChipReachesTheGridRequest() = runBlocking {
        coEvery {
            repository.discoverMovies(genres = "28", sortBy = "vote_average.desc", page = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(7))

        model.toggleGenre(action)
        model.selectSort(SortOption.TOP_RATED)
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.map { item -> item.id } == listOf(7) } }

        assertEquals(SortOption.TOP_RATED, state.sortOption)
    }

    @Test fun theRatingRangeAndVoteFloorBothReachTheRequest() = runBlocking {
        coEvery {
            repository.discoverMovies(minVoteAverage = 7.0, maxVoteAverage = 9.0, minVoteCount = 500, genres = any(), sortBy = any(), page = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(8))

        model.setRating(RatingFilter(min = 7.0, max = 9.0, minVotes = 500))
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.isNotEmpty() } }

        assertTrue(state.hasDiscoverFilters)
        assertEquals(listOf(8), state.discoverGridItems.map { it.id })
    }

    /**
     * A year is an explicit ask, so the "nothing unreleased" cut-off has to step aside for it.
     *
     * It also travels on its own parameter (`primary_release_year`), which is why the year never
     * showed the wrong numbers and the decade did (B34) — so no date window may go out with it.
     */
    @Test fun askingForAYearDropsTheReleasedUpToTodayLimit() = runBlocking {
        coEvery {
            repository.discoverMovies(year = 1999, releaseDateLte = null, releaseDateGte = null, primaryReleaseDateLte = null, primaryReleaseDateGte = null, genres = any(), sortBy = any(), minVoteCount = any(), page = any(), language = any(), keywords = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any())
        } returns listOf(movie(11))

        model.selectYear(1999)
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.isNotEmpty() } }

        assertEquals(listOf(11), state.discoverGridItems.map { it.id })
    }

    // ── B34: the decade asks for the date the card actually prints ─────

    /**
     * Filtered by any release, a film from 1994 that returned to cinemas in 2021 answered the
     * 2020s and then printed 1994 on its own card. `primary_release_date` is the first release.
     */
    @Test fun aDecadeAsksMoviesForTheirFirstReleaseNotForEveryRerun() = runBlocking {
        coEvery {
            repository.discoverMovies(primaryReleaseDateGte = "1990-01-01", primaryReleaseDateLte = "1999-12-31", releaseDateGte = null, releaseDateLte = null, genres = any(), sortBy = any(), minVoteCount = any(), page = any(), language = any(), year = any(), keywords = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any())
        } returns listOf(movie(90))

        model.selectDecade(Decade(1990, 1999))
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.isNotEmpty() } }

        assertEquals(listOf(90), state.discoverGridItems.map { it.id })
    }

    /** The same bug on the series side: `air_date` is one episode, `first_air_date` the start. */
    @Test fun aDecadeAsksSeriesWhenTheyStartedNotWhenAnEpisodeAired() = runBlocking {
        coEvery {
            repository.discoverTv(firstAirDateGte = "1990-01-01", firstAirDateLte = "1999-12-31", airDateGte = null, airDateLte = null, genres = any(), sortBy = any(), minVoteCount = any(), page = any(), language = any(), year = any(), keywords = any(), minVoteAverage = any(), maxVoteAverage = any())
        } returns listOf(show(91))

        model.selectType(DiscoverType.TV_SHOWS)
        model.selectDecade(Decade(1990, 1999))
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.isNotEmpty() } }

        assertEquals(listOf(91), state.discoverGridItems.map { it.id })
    }

    @Test fun anAgeRatingIsNeverSentForSeriesBecauseTmdbHasNoSuchFilter() = runBlocking {
        model.selectCertification("16")
        assertEquals("16", model.uiState.value.certification)

        model.selectType(DiscoverType.TV_SHOWS)
        assertNull("the age filter cannot survive the switch to series", model.uiState.value.certification)

        model.selectCertification("16")
        assertNull("and it cannot be set again while series are shown", model.uiState.value.certification)
    }

    /**
     * T8 in one test: the wanted language has to leave as the title's ORIGINAL language.
     *
     * `MediaRepository` calls the parameter `language` and hands it on as
     * `with_original_language`, while the language the app is read in travels separately. A
     * filter wired to the wrong one would quietly switch the app's words instead of narrowing
     * the grid, and nothing on the screen would say so.
     */
    @Test fun aChosenLanguageTravelsAsTheTitlesOriginalLanguage() = runBlocking {
        coEvery {
            repository.discoverMovies(language = "ja", genres = any(), sortBy = any(), minVoteCount = any(), page = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(21))

        model.selectLanguage("ja")
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.isNotEmpty() } }

        assertEquals(listOf(21), state.discoverGridItems.map { it.id })
        assertTrue(state.hasDiscoverFilters)
    }

    /** The series path reaches TMDB through its own wrapper, so it needs its own proof. */
    @Test fun theLanguageReachesTheSeriesRequestToo() = runBlocking {
        coEvery {
            repository.discoverTv(language = "ko", genres = any(), sortBy = any(), minVoteCount = any(), page = any(), year = any(), keywords = any(), airDateLte = any(), airDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), firstAirDateLte = any(), firstAirDateGte = any())
        } returns listOf(show(22))

        model.selectType(DiscoverType.TV_SHOWS)
        model.selectLanguage("ko")
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.isNotEmpty() } }

        assertEquals(listOf(22), state.discoverGridItems.map { it.id })
    }

    @Test fun pickingASecondLanguageReplacesTheFirstInsteadOfAddingToIt() = runBlocking {
        model.selectLanguage("ja")
        model.selectLanguage("hi")
        assertEquals("hi", model.uiState.value.language)

        model.selectLanguage(null)
        assertNull("and \"any\" takes the filter off again", model.uiState.value.language)
        assertFalse(model.uiState.value.hasDiscoverFilters)
    }

    @Test fun hideWatchedDropsWatchedTitlesAndKeepsPagingUntilSomethingIsLeft() = runBlocking {
        every { trakt.getWatchedMoviesFromCache() } returns setOf(1, 2)
        coEvery {
            repository.discoverMovies(page = 1, genres = any(), sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(1), movie(2))
        coEvery {
            repository.discoverMovies(page = 2, genres = any(), sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(3))

        model.setHideWatched(true)
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.isNotEmpty() } }

        // Page 1 held nothing but watched films, so a page of only-watched results must not be
        // mistaken for the end of the list.
        assertEquals(listOf(3), state.discoverGridItems.map { it.id })
        assertFalse(state.gridEndReached)
    }

    @Test fun theResetChipComesWithTheGridAndLeavesWithIt() = runBlocking {
        // The whole round trip the chip goes through, on the state the row is built from: it is
        // not in the row while nothing is set, it is there once a filter is, and one press later
        // both the filter and the chip itself are gone again.
        assertFalse(showsClearChip(model.uiState.value))

        model.toggleGenre(action)
        model.setHideWatched(true)
        withTimeout(5_000) { model.uiState.first { showsClearChip(it) } }

        model.clearDiscoverFilters()
        val state = withTimeout(5_000) { model.uiState.first { !showsClearChip(it) } }

        assertFalse(state.hasDiscoverFilters)
        assertTrue(state.selectedGenres.isEmpty())
        assertFalse(state.hideWatched)
    }

    @Test fun clearingEveryFilterBringsTheRowsBackInOneStep() = runBlocking {
        model.toggleGenre(action)
        // The decade belongs in here, and so does the language: a filter the reset forgets
        // leaves the screen on the grid with nothing on it explaining why.
        model.selectDecade(Decade(2000, 2009))
        model.selectYear(2001)
        model.setRating(RatingFilter(min = 7.0))
        model.selectLanguage("ja")
        model.setHideWatched(true)
        withTimeout(5_000) { model.uiState.first { it.hasDiscoverFilters } }

        model.clearDiscoverFilters()
        val state = withTimeout(5_000) { model.uiState.first { !it.hasDiscoverFilters } }

        assertTrue(state.selectedGenres.isEmpty())
        assertNull(state.decade)
        assertNull(state.year)
        assertFalse(state.rating.isSet)
        assertNull(state.language)
        assertFalse(state.hideWatched)
        assertTrue(state.discoverGridItems.isEmpty())
    }

    @Test fun gridCardsCarryTheWatchedTickLikeHome() = runBlocking {
        every { trakt.getWatchedMoviesFromCache() } returns setOf(1)
        coEvery {
            repository.discoverMovies(genres = "28", page = 1, sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(1), movie(2))

        model.toggleGenre(action)
        val state = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.size == 2 } }

        // TMDB never sends the flag; without it every card here looked unwatched.
        assertEquals(listOf(true, false), state.discoverGridItems.map { it.isWatched })
    }

    @Test fun browseRowsCarryTheWatchedTickToo() = runBlocking {
        every { trakt.getWatchedMoviesFromCache() } returns setOf(9)
        coEvery {
            repository.discoverMovies(genres = null, page = any(), sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(9), movie(10))

        // On and off again: the rows reload with the stubs above in place.
        model.toggleGenre(action)
        model.toggleGenre(action)
        val state = withTimeout(5_000) { model.uiState.first { it.discoverCategories.isNotEmpty() } }

        state.discoverCategories.forEach { row ->
            assertEquals(listOf(true, false), row.items.map { it.isWatched })
        }
    }

    @Test fun returningToTheScreenBringsTheTicksUpToDate() = runBlocking {
        coEvery {
            repository.discoverMovies(genres = "28", page = 1, sortBy = any(), minVoteCount = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any())
        } returns listOf(movie(1), movie(2))
        model.toggleGenre(action)
        val before = withTimeout(5_000) { model.uiState.first { it.discoverGridItems.size == 2 } }
        assertEquals(listOf(false, false), before.discoverGridItems.map { it.isWatched })

        // Film 2 opened from the grid and watched: the grid is kept for the session (E5), so
        // only the refresh on the way back can give it its tick.
        every { trakt.getWatchedMoviesFromCache() } returns setOf(2)
        model.refreshWatchedMarks()
        withTimeout(5_000) { model.uiState.first { it.discoverGridItems.map { item -> item.isWatched } == listOf(false, true) } }

        // Marked unwatched again: the tick goes as well.
        every { trakt.getWatchedMoviesFromCache() } returns emptySet()
        model.refreshWatchedMarks()
        withTimeout(5_000) { model.uiState.first { it.discoverGridItems.none { item -> item.isWatched } } }
        coVerify(atLeast = 1) { trakt.initializeWatchedCache() }
    }
}
