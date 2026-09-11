package com.arflix.tv.ui.screens.watchlist

import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.repository.HomeServerCatalogCandidate
import com.arflix.tv.data.repository.HomeServerKind
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.profilesDataStore
import com.arflix.tv.util.settingsDataStore
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class WatchlistScrollDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val providers = listOf(HomeServerKind.JELLYFIN, HomeServerKind.EMBY)
    private val libraries = providers.flatMap { kind -> (0 until 30).map { index ->
        HomeServerCatalogCandidate("Library $index", "$kind-$index", "$kind server ${index / 15 + 1}",
            "Library $index", "movies", kind, "$kind-${index / 15}")
    } }
    private val state = MutableStateFlow(HomeLibraryUiState(providers = providers, libraries = libraries))
    private val viewModel = mockk<WatchlistViewModel>(relaxed = true)
    private var layoutProfile: String? = null
    private var previousLayout: String? = null
    private fun items(count: Int) = (0 until count).map { MediaItem(id = it + 1, title = "Movie $it") }

    @After fun restoreLayout() {
        val profile = layoutProfile ?: return
        runBlocking {
            compose.activity.settingsDataStore.edit { prefs ->
                val key = stringPreferencesKey("profile_${profile}_card_layout_mode")
                previousLayout?.let { prefs[key] = it } ?: prefs.remove(key)
            }
        }
    }

    @Test fun jellyfinSidebarFollowsRemoteFocusPastFirstScreen() = verifySidebar(HomeServerKind.JELLYFIN)
    @Test fun embySidebarFollowsRemoteFocusPastFirstScreen() = verifySidebar(HomeServerKind.EMBY)

    @Test fun landscapeGridSupportsDirectEntryFullScrollAndReturnToSidebar() = verifyGrid(poster = false)
    @Test fun posterGridSupportsDirectEntryFullScrollAndReturnToSidebar() = verifyGrid(poster = true)

    private fun verifyGrid(poster: Boolean) {
        show(DeviceType.TV, poster)
        openProvider(HomeServerKind.JELLYFIN)
        compose.runOnIdle { state.value = state.value.copy(items = items(63)) }
        keys(listOf(Key.DirectionDown, Key.DirectionRight))
        compose.onNodeWithTag("library-card-0").assertIsSelected().assertIsDisplayed()
        val before = compose.onNodeWithTag("library-card-0").fetchSemanticsNode().boundsInRoot
        keys(listOf(Key.DirectionRight))
        compose.onNodeWithTag("library-card-1").assertIsSelected()
        val after = compose.onNodeWithTag("library-card-0").fetchSemanticsNode().boundsInRoot
        assertEquals("Moving horizontally must not shift the grid", before.top, after.top, 1f)
        keys(List(40) { Key.DirectionDown })
        compose.onNodeWithTag("library-card-62").assertIsSelected().assertIsDisplayed()
        capture("library-grid-${if (poster) "poster" else "landscape"}")
        keys(List(3) { Key.DirectionLeft })
        sidebar(HomeServerKind.JELLYFIN, 0).assertIsFocused().assertIsDisplayed()
        keys(listOf(Key.DirectionRight))
        compose.onNodeWithTag("library-card-60").assertIsSelected().assertIsDisplayed()
    }

    @Test fun rapidRemotePressesKeepLastLibraryVisible() {
        show(DeviceType.TV)
        openProvider(HomeServerKind.EMBY)
        keys(listOf(Key.DirectionDown))
        compose.onNodeWithTag("library-screen").performKeyInput {
            repeat(55) { pressKey(Key.DirectionDown) }
        }
        compose.waitForIdle()
        sidebar(HomeServerKind.EMBY, 29).assertIsFocused().assertIsDisplayed()
    }

    private fun verifySidebar(kind: HomeServerKind) {
        show(DeviceType.TV)
        openProvider(kind)
        keys(listOf(Key.DirectionDown))
        sidebar(kind, 0).assertIsFocused().assertIsDisplayed()
        val before = sidebar(kind, 0).fetchSemanticsNode().boundsInRoot
        keys(listOf(Key.DirectionDown))
        val after = sidebar(kind, 0).fetchSemanticsNode().boundsInRoot
        assertEquals("Visible library rows should stay still", before.top, after.top, 1f)
        keys(List(14) { Key.DirectionDown })
        sidebar(kind, 15).assertIsFocused().assertIsDisplayed()
        keys(List(14) { Key.DirectionDown })
        sidebar(kind, 29).assertIsFocused().assertIsDisplayed()
        keys(listOf(Key.DirectionCenter))
        compose.runOnIdle { assertEquals("$kind-29", state.value.selectedSourceRef) }
        capture("library-scroll-${kind.name.lowercase()}")
        keys(List(29) { Key.DirectionUp })
        sidebar(kind, 0).assertIsFocused().assertIsDisplayed()
        keys(listOf(Key.DirectionCenter))
        compose.runOnIdle { assertEquals("$kind-0", state.value.selectedSourceRef) }
    }

    @Test fun mobilePaginationDoesNotResetScrollToFirstItem() {
        show(DeviceType.PHONE)
        compose.onNodeWithText("Jellyfin", useUnmergedTree = true).performClick()
        val grid = compose.onNodeWithTag("library-grid")
        repeat(3) { grid.performTouchInput { swipeUp() }; compose.waitForIdle() }
        val before = grid.fetchSemanticsNode().config[SemanticsProperties.VerticalScrollAxisRange].value()
        assertTrue("Swipe must move down the grid", before > 0f)
        compose.runOnIdle { state.value = state.value.copy(items = items(120)) }
        compose.waitForIdle()
        val after = grid.fetchSemanticsNode().config[SemanticsProperties.VerticalScrollAxisRange].value()
        assertEquals("Appending results must not scroll back to the focused default item", before, after, 0.01f)
    }

    private fun show(device: DeviceType, poster: Boolean = false) {
        runBlocking {
            val profile = compose.activity.profilesDataStore.data.first()[stringPreferencesKey("active_profile_id")]
                .orEmpty().ifBlank { "default" }
            val key = stringPreferencesKey("profile_${profile}_card_layout_mode")
            layoutProfile = profile
            previousLayout = compose.activity.settingsDataStore.data.first()[key]
            compose.activity.settingsDataStore.edit { it[key] = if (poster) "Poster" else "Landscape" }
        }
        every { viewModel.uiState } returns MutableStateFlow(WatchlistUiState(isLoading = false))
        every { viewModel.libraryState } returns state
        every { viewModel.logoUrls } returns MutableStateFlow(emptyMap())
        every { viewModel.selectLibraryProvider(any()) } answers {
            val kind = firstArg<HomeServerKind?>()
            state.value = state.value.copy(selectedProvider = kind,
                selectedSourceRef = libraries.firstOrNull { it.serverKind == kind }?.sourceRef,
                items = if (kind == null) emptyList() else items(60), isLoading = false)
        }
        every { viewModel.selectLibrary(any()) } answers {
            state.value = state.value.copy(selectedSourceRef = firstArg(), items = items(60), isLoading = false)
        }
        compose.setContent { CompositionLocalProvider(LocalDeviceType provides device) { WatchlistScreen(viewModel) } }
        compose.waitForIdle()
    }

    private fun openProvider(kind: HomeServerKind) {
        keys(listOf(Key.Escape, Key.DirectionDown) + List(providers.indexOf(kind) + 1) { Key.DirectionRight } + Key.DirectionCenter)
    }
    private fun sidebar(kind: HomeServerKind, index: Int) = compose.onNodeWithTag("library-sidebar-$kind-$index")
    private fun keys(keys: List<Key>) {
        keys.forEach { key -> compose.onNodeWithTag("library-screen").performKeyInput { pressKey(key) }; compose.waitForIdle() }
    }
    private fun capture(name: String) {
        val bitmap = compose.onNodeWithTag("library-screen").captureToImage().asAndroidBitmap()
        File(compose.activity.getExternalFilesDir(null), "$name.png").outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }
}
