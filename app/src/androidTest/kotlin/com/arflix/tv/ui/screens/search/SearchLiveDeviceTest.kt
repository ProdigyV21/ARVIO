package com.arflix.tv.ui.screens.search

import android.graphics.Bitmap
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.repository.RecentSearchRepository
import com.arflix.tv.di.RepositoryAccessEntryPoint
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import dagger.hilt.android.EntryPointAccessors
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Opt-in public metadata smoke test; no cloud account or playback required. */
@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class SearchLiveDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun liveSortOnlyLoadsOrderedMoviesSeriesAndAnime() {
        assumeTrue(InstrumentationRegistry.getArguments().getString("searchLive") == "true")
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val access = EntryPointAccessors.fromApplication(instrumentation.targetContext,
            RepositoryAccessEntryPoint::class.java)
        lateinit var model: SearchViewModel
        compose.runOnUiThread {
            model = SearchViewModel(access.mediaRepository(), access.traktRepository(),
                RecentSearchRepository(instrumentation.targetContext, access.profileManager()), access.kidsContentFilter())
            compose.activity.viewModelStore.put("live-sort", model)
        }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) { SearchScreen(viewModel = model) }
        }
        for (type in listOf(DiscoverType.MOVIES, DiscoverType.TV_SHOWS, DiscoverType.ANIME)) {
            for (sort in listOf(SortOption.TOP_RATED, SortOption.NEWEST)) {
                compose.runOnIdle { model.selectType(type); model.selectSort(sort) }
                compose.waitUntil(30_000) {
                    val state = model.uiState.value
                    !state.isGridLoading && state.discoverGridItems.isNotEmpty()
                }
                val state = model.uiState.value
                assertEquals(type, state.selectedType)
                assertEquals(sort, state.sortOption)
                assertTrue(state.selectedGenres.isEmpty())
                assertTrue(state.query.isEmpty())
                assertTrue(state.discoverCategories.isEmpty())
                if (sort == SortOption.TOP_RATED) {
                    val ratings = state.discoverGridItems.mapNotNull { it.tmdbRating.ifBlank { it.rating }.toDoubleOrNull() }
                    assertTrue("Live ratings must be present", ratings.isNotEmpty())
                    assertTrue("$type: ratings must descend: $ratings", ratings.zipWithNext().all { (a, b) -> a >= b })
                } else {
                    val format = DateTimeFormatter.ofPattern("d MMM uuuu", Locale.US)
                    val dates = state.discoverGridItems.mapNotNull { it.releaseDate }.map { LocalDate.parse(it, format) }
                    assertTrue("Live release dates must be present", dates.isNotEmpty())
                    assertTrue("$type: dates must descend: $dates", dates.zipWithNext().all { (a, b) -> a >= b })
                }
                instrumentation.sendStatus(0, Bundle().apply {
                    putString("stream", "Live $type $sort: ${state.discoverGridItems.size} ordered titles without other filters\n")
                })
                val bitmap = compose.onNodeWithTag("search-screen").captureToImage().asAndroidBitmap()
                File(compose.activity.getExternalFilesDir(null), "issue874-live-$type-$sort.png").outputStream().use {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                }
            }
        }
        compose.runOnIdle { model.clearDiscoverFilters() }
        compose.waitUntil(30_000) { !model.uiState.value.isDiscoverLoading && model.uiState.value.discoverCategories.isNotEmpty() }
        assertEquals(SortOption.POPULAR, model.uiState.value.sortOption)
        assertFalse(model.uiState.value.hasDiscoverFilters)
    }

    @Test fun liveTitlesAreFirstAndCanBeOpenedWithOneDownPress() {
        assumeTrue(InstrumentationRegistry.getArguments().getString("searchLive") == "true")
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val access = EntryPointAccessors.fromApplication(instrumentation.targetContext,
            RepositoryAccessEntryPoint::class.java)
        val repository = access.mediaRepository()
        lateinit var model: SearchViewModel
        var openedId: Int? = null
        compose.runOnUiThread {
            model = SearchViewModel(repository, access.traktRepository(),
                RecentSearchRepository(instrumentation.targetContext, access.profileManager()),
                access.kidsContentFilter())
            compose.activity.viewModelStore.put("live-search", model)
        }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                SearchScreen(viewModel = model, onNavigateToDetails = { _, id -> openedId = id })
            }
        }
        for (query in listOf("Loki", "Matlock", "Breaking Bad", "Alien")) {
            val started = SystemClock.elapsedRealtime()
            compose.runOnIdle { model.updateQuery(query) }
            compose.waitUntil(15_000) { !model.uiState.value.isLoading && model.uiState.value.query == query }
            val elapsed = SystemClock.elapsedRealtime() - started
            val result = model.uiState.value.results
            assertTrue("$query: ${model.uiState.value.error}", result.isNotEmpty())
            assertEquals(query.lowercase(), result.first().title.lowercase())
            val first = result.first()
            val report = "$query: ${elapsed}ms, ${result.size} titles, first=${first.title} (${first.year})\n"
            instrumentation.sendStatus(0, Bundle().apply { putString("stream", report) })
            File(compose.activity.getExternalFilesDir(null), "search-live-times.txt").appendText(report)
            compose.onNodeWithTag("search-screen").performKeyInput { pressKey(Key.DirectionDown) }
            compose.onNodeWithTag("search-card-s_all-${first.mediaType}-${first.id}")
                .assertIsSelected().assertIsDisplayed()
            compose.onNodeWithTag("search-screen").performKeyInput { pressKey(Key.DirectionCenter) }
            compose.runOnIdle { assertEquals(first.id, openedId) }
            // Allow actual network artwork to settle for the visual audit, not the timing measurement.
            Thread.sleep(1_500)
            val bitmap = compose.onNodeWithTag("search-screen").captureToImage().asAndroidBitmap()
            File(compose.activity.getExternalFilesDir(null), "search-live-${query.replace(' ', '-')}.png")
                .outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
            compose.onNodeWithTag("search-screen").performKeyInput { pressKey(Key.Escape) }
        }
    }
}
