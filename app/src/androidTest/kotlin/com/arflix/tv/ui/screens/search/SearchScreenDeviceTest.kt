package com.arflix.tv.ui.screens.search

import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arflix.tv.R
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.io.File
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class SearchScreenDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val restoration = StateRestorationTester(compose)

    private val state = MutableStateFlow(SearchUiState(discoverCategories = rows("initial")))
    private val viewModel = mockk<SearchViewModel>(relaxed = true)
    private var openedId: Int? = null

    @Test fun tvExactTitleIsFirstEvenWhenPeopleAndMovieMatchesExist() {
        searchResults()
        show(DeviceType.TV)
        keys(listOf(Key.DirectionDown))
        card("s_all", MediaType.TV, 100).assertIsSelected().assertIsDisplayed()
        keys(listOf(Key.DirectionCenter))
        compose.runOnIdle { assertEquals(100, openedId) }
        capture("search-title-first")
    }

    @Test fun tvMovingBetweenVisibleCardsDoesNotScrollAndRowsRememberPosition() {
        searchResults()
        show(DeviceType.TV)
        keys(listOf(Key.DirectionDown))
        val before = card("s_all", MediaType.TV, 100).fetchSemanticsNode().boundsInRoot
        keys(listOf(Key.DirectionRight))
        val after = card("s_all", MediaType.TV, 100).fetchSemanticsNode().boundsInRoot
        assertEquals("Visible cards should not slide with each key press", before.left, after.left, 1f)
        keys(List(5) { Key.DirectionRight })
        card("s_all", MediaType.TV, 106).assertIsSelected().assertIsDisplayed()
        keys(listOf(Key.DirectionDown, Key.DirectionUp))
        card("s_all", MediaType.TV, 106).assertIsSelected().assertIsDisplayed()
        keys(listOf(Key.Escape, Key.DirectionDown))
        card("s_all", MediaType.TV, 106).assertIsSelected().assertIsDisplayed()
        compose.runOnIdle {
            state.value = state.value.copy(cardLogoUrls = mapOf("TV_106" to "https://example.invalid/logo.png"))
        }
        card("s_all", MediaType.TV, 106).assertIsSelected().assertIsDisplayed()
        capture("search-row-memory")
    }

    @Test fun tvNewQueryStartsAtFirstResultAndDoneEntersResults() {
        searchResults()
        show(DeviceType.TV)
        keys(listOf(Key.DirectionDown) + List(7) { Key.DirectionRight } + listOf(Key.Escape, Key.DirectionCenter))
        compose.onNodeWithTag("search-input").performTextReplacement("new query")
        compose.onNodeWithTag("search-input").performImeAction()
        card("s_all", MediaType.TV, 100).assertIsSelected().assertIsDisplayed()
        verify(exactly = 1) { viewModel.search() }
    }

    @Test fun tvDownWhileLoadingEntersFirstResultWhenReady() {
        state.value = SearchUiState(query = "Loki", isLoading = true)
        show(DeviceType.TV)
        keys(listOf(Key.DirectionDown))
        compose.runOnIdle { searchResults() }
        card("s_all", MediaType.TV, 100).assertIsSelected().assertIsDisplayed()
    }

    @Test fun tvSelectionSurvivesScreenStateRestoration() {
        searchResults()
        show(DeviceType.TV)
        keys(listOf(Key.DirectionDown) + List(5) { Key.DirectionRight })
        restoration.emulateSavedInstanceStateRestore()
        card("s_all", MediaType.TV, 105).assertIsSelected().assertIsDisplayed()
        keys(listOf(Key.DirectionCenter))
        compose.runOnIdle { assertEquals(105, openedId) }
    }

    @Test fun tvPeopleOnlyQueryStillOpensKnownForTitles() {
        state.value = SearchUiState(query = "actor", personResults = listOf(
            Category("person_1", "Actor", listOf(MediaItem(id = 300, title = "Known For", mediaType = MediaType.MOVIE)))
        ))
        show(DeviceType.TV)
        keys(listOf(Key.DirectionDown, Key.DirectionCenter))
        compose.runOnIdle { assertEquals(300, openedId) }
    }

    private fun searchResults() {
        val shows = (0..14).map { MediaItem(id = 100 + it, title = if (it == 0) "Loki" else "Loki: Related Show $it", mediaType = MediaType.TV) }
        val films = listOf(MediaItem(id = 200, title = "Loki: A Behind the Scenes Documentary", mediaType = MediaType.MOVIE))
        state.value = SearchUiState(query = "Loki", results = shows + films, movieResults = films, tvResults = shows,
            personResults = listOf(Category("person_1", "Actor", films)))
    }

    private fun card(row: String, type: MediaType, id: Int) = compose.onNodeWithTag("search-card-$row-$type-$id")

    @Test fun tvSelectionSurvivesKeyUpAndCategoryReload() {
        show(DeviceType.TV)
        keys(listOf(Key.DirectionDown, Key.DirectionRight, Key.DirectionCenter))
        filter("movies").assertIsSelected()
        filter("all").assertIsNotSelected()
        verify(exactly = 1) { viewModel.setDiscoverFilters(DiscoverType.MOVIES, null, null) }
        finishReload()
        keys(listOf(Key.DirectionRight, Key.DirectionCenter))
        filter("shows").assertIsSelected()
        verify(exactly = 1) { viewModel.setDiscoverFilters(DiscoverType.TV_SHOWS, null, null) }
        finishReload()
        capture("tv-filters")
    }

    @Test fun tvSearchReturnKeepsSelectedFilterAndReopensEditor() {
        show(DeviceType.TV)
        keys(listOf(Key.DirectionDown, Key.DirectionRight, Key.DirectionCenter))
        finishReload()
        keys(listOf(Key.DirectionUp, Key.DirectionDown, Key.DirectionCenter))
        filter("movies").assertIsSelected()
        verify(exactly = 2) { viewModel.setDiscoverFilters(DiscoverType.MOVIES, null, null) }
        keys(listOf(Key.DirectionUp, Key.DirectionCenter))
        assertKeyboardVisible()
        compose.onNodeWithTag("search-input").assertIsFocused().performTextInput("test")
        compose.runOnIdle { assertEquals("test", state.value.query) }
        keys(listOf(Key.Escape, Key.DirectionCenter))
        assertKeyboardVisible()
        compose.onNodeWithTag("search-input").assertIsFocused().performTextReplacement("updated")
        compose.runOnIdle { assertEquals("updated", state.value.query) }
    }

    @Test fun tvCanEnterResultsAndReturnWithoutSelectingAll() {
        show(DeviceType.TV)
        keys(listOf(Key.DirectionDown, Key.DirectionRight, Key.DirectionCenter))
        finishReload()
        keys(listOf(Key.DirectionDown, Key.DirectionCenter))
        compose.runOnIdle { assertEquals(1, openedId) }
        keys(listOf(Key.DirectionUp, Key.DirectionCenter))
        verify(exactly = 2) { viewModel.setDiscoverFilters(DiscoverType.MOVIES, null, null) }
        filter("movies").assertIsSelected()
    }

    @Test fun tvRtlFiltersUseMirroredHorizontalNavigation() {
        show(DeviceType.TV, LayoutDirection.Rtl)
        keys(listOf(Key.DirectionDown, Key.DirectionLeft, Key.DirectionCenter))
        filter("movies").assertIsSelected()
    }

    @Test fun tvFilterAccessibilityActionRemainsAvailable() {
        show(DeviceType.TV)
        filter("movies").assertTextEquals(compose.activity.getString(R.string.movies))
        filter("movies").assertHasClickAction().performSemanticsAction(SemanticsActions.OnClick) { it() }
        filter("movies").assertIsSelected()
        verify(exactly = 1) { viewModel.setDiscoverFilters(DiscoverType.MOVIES, null, null) }
    }

    @Test fun mobileTypeAndGenreFiltersRemainClickable() {
        show(DeviceType.PHONE)
        filter("movies").performClick()
        finishReload()
        filter("genre_28").performScrollTo().performClick()
        compose.runOnIdle {
            assertEquals(DiscoverType.MOVIES, state.value.selectedType)
            assertEquals(28, state.value.selectedGenre?.id)
        }
        filter("genre_28").assertIsSelected()
    }

    @Test fun mobileRowsDoNotOverlapAndCardsOpenDetails() {
        show(DeviceType.PHONE)
        val first = compose.onNodeWithTag("search-row-initial-1").fetchSemanticsNode().boundsInRoot
        val second = compose.onNodeWithTag("search-row-initial-2").fetchSemanticsNode().boundsInRoot
        assertTrue("Rows overlap: $first / $second", second.top >= first.bottom)
        capture("mobile-rows")
        compose.onAllNodesWithText("First Movie", useUnmergedTree = true).onFirst().performClick()
        compose.runOnIdle { assertEquals(1, openedId) }
    }

    private fun show(device: DeviceType, direction: LayoutDirection = LayoutDirection.Ltr) {
        if (device == DeviceType.TV) {
            val config = compose.activity.resources.configuration
            assumeTrue("Run TV checks on a landscape TV emulator", config.screenWidthDp >= 600 && config.screenWidthDp > config.screenHeightDp)
        }
        every { viewModel.uiState } returns state
        every { viewModel.setDiscoverFilters(any(), any(), any()) } answers {
            state.value = state.value.copy(
                selectedType = firstArg(), selectedGenre = secondArg(), selectedCountry = thirdArg(),
                discoverCategories = emptyList(), isDiscoverLoading = true
            )
        }
        every { viewModel.updateQuery(any()) } answers { state.value = state.value.copy(query = firstArg()) }
        restoration.setContent {
            CompositionLocalProvider(LocalDeviceType provides device, LocalLayoutDirection provides direction) {
                SearchScreen(viewModel = viewModel, onNavigateToDetails = { _, id -> openedId = id })
            }
        }
        compose.waitForIdle()
    }

    private fun finishReload() {
        compose.runOnIdle {
            state.value = state.value.copy(discoverCategories = rows("reloaded"), isDiscoverLoading = false)
        }
    }

    private fun filter(key: String) = compose.onNodeWithTag("search-filter-$key")

    private fun assertKeyboardVisible() {
        compose.waitUntil(timeoutMillis = 5_000) {
            compose.runOnUiThread {
                ViewCompat.getRootWindowInsets(compose.activity.window.decorView)
                    ?.isVisible(WindowInsetsCompat.Type.ime()) == true
            }
        }
    }

    private fun keys(keys: List<Key>) {
        keys.forEach { key ->
            compose.onNodeWithTag("search-screen").performKeyInput { pressKey(key) }
            compose.waitForIdle()
        }
    }

    private fun capture(name: String) {
        val bitmap = compose.onNodeWithTag("search-screen").captureToImage().asAndroidBitmap()
        File(compose.activity.getExternalFilesDir(null), "pr649-$name.png").outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }

    companion object {
        private fun rows(prefix: String) = listOf(
            Category("$prefix-1", "Trending", listOf(MediaItem(id = 1, title = "First Movie", mediaType = MediaType.MOVIE))),
            Category("$prefix-2", "Top Rated", listOf(MediaItem(id = 2, title = "Second Movie", mediaType = MediaType.MOVIE)))
        )
    }
}
