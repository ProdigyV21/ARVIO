package com.arflix.tv.ui.screens.search

import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.KidsContentFilter
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.RecentSearchRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

/** Real SearchScreen and ViewModel, with deterministic metadata instead of a cloud account. */
@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class DiscoverSortDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val repository = mockk<MediaRepository>(relaxed = true)
    private lateinit var model: SearchViewModel
    private var openedId: Int? = null

    @Before fun setup() {
        every { repository.contentLanguage } returns "en-US"
        coEvery { repository.getLogoUrl(any<MediaType>(), any()) } returns null
        coEvery {
            repository.discoverMovies(genres = any(), sortBy = any(), minVoteCount = any(), page = any(), language = any(), year = any(), keywords = any(), releaseDateLte = any(), releaseDateGte = any(), primaryReleaseDateLte = any(), primaryReleaseDateGte = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(), certificationLte = any())
        } coAnswers {
            if (arg<String?>(9) == null) listOf(MediaItem(id = 900, title = "Browse Movie", mediaType = MediaType.MOVIE))
            else if (arg<Int>(3) > 1) emptyList()
            else {
                val newest = arg<String>(1) == SortOption.NEWEST.apiValue
                val id = if (newest) 201 else 101
                val title = if (newest) "Newest Movie" else "Top Movie"
                listOf(MediaItem(id = id, title = "$title 1", mediaType = MediaType.MOVIE),
                    MediaItem(id = id + 1, title = "$title 2", mediaType = MediaType.MOVIE))
            }
        }
        val recent = mockk<RecentSearchRepository>(relaxed = true)
        every { recent.recentSearches } returns MutableStateFlow(emptyList())
        val kids = mockk<KidsContentFilter>(relaxed = true)
        every { kids.state } returns MutableStateFlow(KidsContentFilter.State(null, "US", emptyMap()))
        compose.runOnUiThread {
            model = SearchViewModel(repository, mockk<TraktRepository>(relaxed = true), recent, kids)
            compose.activity.viewModelStore.put("issue874-search", model)
        }
    }

    private fun show(device: DeviceType = DeviceType.TV) {
        compose.setContent {
            val configuration = Configuration(LocalConfiguration.current).apply {
                if (device == DeviceType.PHONE) {
                    screenWidthDp = 390; screenHeightDp = 844; smallestScreenWidthDp = 390
                    orientation = Configuration.ORIENTATION_PORTRAIT
                }
            }
            CompositionLocalProvider(LocalDeviceType provides device, LocalConfiguration provides configuration) {
                ArflixTvTheme {
                    Box(Modifier.then(if (device == DeviceType.PHONE) Modifier.width(390.dp) else Modifier).fillMaxSize()) {
                        SearchScreen(viewModel = model, onNavigateToDetails = { _, id -> openedId = id })
                    }
                }
            }
        }
        waitForBrowse()
    }

    private fun keys(keys: List<Key>) {
        keys.forEach { key ->
            compose.onNodeWithTag("search-screen").performKeyInput { pressKey(key) }
            compose.waitForIdle()
        }
    }

    private fun remoteSort(sort: SortOption, fromSearch: Boolean = false) {
        if (fromSearch) keys(listOf(Key.DirectionDown, Key.DirectionRight, Key.DirectionRight))
        keys(listOf(Key.DirectionCenter))
        compose.onNodeWithTag("filter-panel-SORT").assertIsDisplayed()
        repeat(sort.ordinal) { keys(listOf(Key.DirectionRight)) }
        keys(listOf(Key.DirectionCenter, Key.Escape))
        compose.onNodeWithTag("filter-panel-SORT").assertDoesNotExist()
    }

    private fun waitForGrid(sort: SortOption) {
        val id = if (sort == SortOption.TOP_RATED) 101 else 201
        compose.waitUntil(15_000) { !model.uiState.value.isGridLoading && model.uiState.value.discoverGridItems.firstOrNull()?.id == id }
        compose.runOnIdle {
            assertTrue(model.uiState.value.selectedGenres.isEmpty())
            assertTrue(model.uiState.value.query.isEmpty())
            assertTrue(model.uiState.value.discoverCategories.isEmpty())
        }
        compose.onAllNodesWithText(if (id == 101) "Top Movie 1" else "Newest Movie 1", useUnmergedTree = true).onFirst().assertIsDisplayed()
    }

    private fun waitForBrowse() {
        compose.waitUntil(15_000) { !model.uiState.value.isDiscoverLoading && model.uiState.value.discoverCategories.isNotEmpty() }
        compose.runOnIdle {
            assertEquals(SortOption.POPULAR, model.uiState.value.sortOption)
            assertFalse(model.uiState.value.hasDiscoverFilters)
            assertTrue(model.uiState.value.discoverGridItems.isEmpty())
        }
        compose.onNodeWithTag("search-filter-clear").assertDoesNotExist()
    }

    @Test fun remoteSortAloneUpdatesResultsAndPopularRestoresBrowse() {
        show()
        remoteSort(SortOption.TOP_RATED, fromSearch = true)
        waitForGrid(SortOption.TOP_RATED)
        keys(listOf(Key.DirectionDown, Key.DirectionCenter))
        compose.runOnIdle { assertEquals(101, openedId) }
        capture("top-rated-remote")
        keys(listOf(Key.Escape))
        remoteSort(SortOption.NEWEST)
        waitForGrid(SortOption.NEWEST)
        remoteSort(SortOption.POPULAR)
        waitForBrowse()
    }

    @Test fun remoteResetClearsASortOnlySelection() {
        show()
        remoteSort(SortOption.TOP_RATED, fromSearch = true)
        waitForGrid(SortOption.TOP_RATED)
        repeat(6) { keys(listOf(Key.DirectionRight)) }
        compose.onNodeWithTag("search-filter-clear").assertIsDisplayed()
        keys(listOf(Key.DirectionCenter))
        waitForBrowse()
    }

    @Test fun phoneSortAndResetWorkWithoutAGenre() {
        show(DeviceType.PHONE)
        compose.onNodeWithTag("search-filter-sort").performScrollTo().performClick()
        compose.onNodeWithTag("filter-option-sort_NEWEST").performClick()
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("filter-panel-SORT").assertDoesNotExist()
        waitForGrid(SortOption.NEWEST)
        capture("newest-phone")
        compose.onNode(hasScrollToIndexAction() and SemanticsMatcher.keyIsDefined(SemanticsProperties.HorizontalScrollAxisRange))
            .performScrollToNode(hasTestTag("search-filter-clear"))
        compose.onNodeWithTag("search-filter-clear").performClick()
        waitForBrowse()
    }

    private fun capture(name: String) {
        val bitmap = compose.onNodeWithTag("search-screen").captureToImage().asAndroidBitmap()
        File(compose.activity.getExternalFilesDir(null), "issue874-$name.png").outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }
}
