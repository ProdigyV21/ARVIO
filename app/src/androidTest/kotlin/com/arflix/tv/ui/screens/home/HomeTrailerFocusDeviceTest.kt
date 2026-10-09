package com.arflix.tv.ui.screens.home

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.profilesDataStore
import java.io.File
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.After

@OptIn(ExperimentalTestApi::class)
class HomeTrailerFocusDeviceTest {
    @get:Rule val compose = createComposeRule()
    private var restoreLayout: () -> Unit = {}
    @After fun restorePreferences() { restoreLayout() }

    @Test fun expansionFitsTheRowAndRemoteStillOpensTheSelectedTitle() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val poster = InstrumentationRegistry.getArguments().getString("trailerPoster") == "true"
        val ranked = InstrumentationRegistry.getArguments().getString("trailerRanked") == "true"
        val profileId = runBlocking { context.profilesDataStore.data.first()[stringPreferencesKey("active_profile_id")] }.orEmpty().ifBlank { "default" }
        val rowLayoutKey = stringPreferencesKey("profile_${profileId}_catalogue_row_layout_home:trending_movies")
        val previousLayout = runBlocking { context.settingsDataStore.data.first()[rowLayoutKey] }
        restoreLayout = { runBlocking { context.settingsDataStore.edit { if (previousLayout == null) it.remove(rowLayoutKey) else it[rowLayoutKey] = previousLayout } }; Unit }
        runBlocking { context.settingsDataStore.edit { it[rowLayoutKey] = if (poster) "Poster" else "Landscape" } }
        val focus = HomeFocusState().apply { userHasNavigated = true }
        var opened = -1
        val items = (1..10).map { MediaItem(it, "Title $it", mediaType = MediaType.MOVIE) }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                HomeInputLayer(
                    categories = listOf(Category("trending_movies", if (ranked) "Top 10 Movies" else "Movies", items)),
                    cardLogoUrls = emptyMap(), focusState = focus,
                    limitRowsDuringStartup = false, suppressSelectUntilMs = 0L,
                    contentStartPadding = 24.dp, fastScrollThresholdMs = 80L,
                    usePosterCards = false, isContextMenuOpen = false, currentProfile = null,
                    onNavigateToDetails = { _, id, _, _ -> opened = id }, onNavigateToCollection = {},
                    onNavigateToSearch = {}, onNavigateToWatchlist = {}, onNavigateToTv = { _, _ -> },
                    onNavigateToSettings = {}, onSwitchProfile = {}, onExitApp = {},
                    onOpenContextMenu = { _, _ -> },
                    featuredTrailerKey = "M7lc1UVf-VE", featuredTrailerDelayMs = 1500L
                )
            }
        }
        compose.mainClock.autoAdvance = false
        compose.mainClock.advanceTimeBy(2300)
        val card = compose.onNodeWithTag("home_trailer_card_MOVIE_1", useUnmergedTree = true)
        card.assertWidthIsEqualTo(360.dp)
        card.assertHeightIsEqualTo(202.5.dp)
        val rowBounds = compose.onNodeWithTag("home_row_trending_movies", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        assertTrue("Expanded preview clipped by the row", card.fetchSemanticsNode().boundsInRoot.bottom <= rowBounds.bottom)
        val output = File(context.getExternalFilesDir(null), "trailer-test").apply { mkdirs() }
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(File(output, if (poster) "home-poster-expanded.png" else "home-landscape-expanded.png"))
        compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
        compose.mainClock.advanceTimeBy(32)
        card.assertDoesNotExist()
        // Expansion now uses a shorter focus delay and its deadline follows
        // elapsed real time. Waiting for test idleness can already pass it;
        // assert the selected identity instead of assuming virtual time alone.
        compose.runOnIdle { assertEquals(1, focus.currentItemIndex) }
        compose.mainClock.advanceTimeBy(2300)
        compose.onNodeWithTag("home_trailer_card_MOVIE_2", useUnmergedTree = true).assertExists()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        compose.runOnIdle { assertEquals(2, opened) }
        // Rapid navigation must collapse the old preview and leave only the
        // settled final title expanded, even at the end of the rail.
        repeat(8) {
            compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
            compose.mainClock.advanceTimeBy(160)
        }
        compose.mainClock.advanceTimeBy(2300)
        compose.runOnIdle { assertEquals(9, focus.currentItemIndex) }
        val last = compose.onNodeWithTag("home_trailer_card_MOVIE_10", useUnmergedTree = true)
        last.assertWidthIsEqualTo(360.dp).assertHeightIsEqualTo(202.5.dp)
        val lastBounds = last.fetchSemanticsNode().boundsInRoot
        assertTrue("Last card clipped horizontally", lastBounds.left >= rowBounds.left && lastBounds.right <= rowBounds.right)
        assertTrue("Last card clipped vertically", lastBounds.bottom <= rowBounds.bottom)
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(File(output, "home-last-expanded-${if (poster) "poster" else "landscape"}${if (ranked) "-ranked" else ""}.png"))
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        compose.runOnIdle { assertEquals(10, opened) }
        compose.mainClock.autoAdvance = true
    }
}
