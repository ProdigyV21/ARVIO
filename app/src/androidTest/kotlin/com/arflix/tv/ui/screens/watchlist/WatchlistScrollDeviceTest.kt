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

    @Test fun longSidebarCanSelectLastServerLibrary() {
        show(DeviceType.TV)
        compose.onNodeWithText("Homeserver").performClick()
        val tag = "library-source-server_EMBY-29"
        compose.onNodeWithTag("library-sources").performScrollToNode(hasTestTag(tag))
        compose.onNodeWithTag(tag).performClick()
        compose.runOnIdle { assertEquals("EMBY-29", state.value.selectedSourceRef) }
    }
    @Test fun landscapeGridNavigatesByRemoteWithoutHorizontalLayoutJump() {
        show(DeviceType.TV)
        compose.onNodeWithText("Homeserver").performClick()
        val card = compose.onAllNodes(hasAnyAncestor(hasTestTag("library-card-0")) and hasClickAction()).onFirst()
        card.performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus)
        keys(listOf(Key.DirectionRight))
        val first = compose.onNodeWithTag("library-card-0").fetchSemanticsNode().boundsInRoot.top
        keys(listOf(Key.DirectionRight))
        assertEquals(first, compose.onNodeWithTag("library-card-0").fetchSemanticsNode().boundsInRoot.top, 1f)
        keys(List(12) { Key.DirectionDown })
        assertTrue(compose.onNodeWithTag("library-grid").fetchSemanticsNode().config[SemanticsProperties.VerticalScrollAxisRange].value() > 0f)
    }
    @Test fun posterGridKeepsPositionWhenMoreItemsArrive() = verifyAppend(true)
    @Test fun serverGridRequestsEveryPageBeyondSixty() {
        show(DeviceType.TV)
        every { viewModel.loadMoreLibrary() } answers {
            val size = (state.value.items.size + 60).coerceAtMost(185)
            state.value = state.value.copy(items = items(size), hasMore = size < 185, isLoadingMore = false)
        }
        compose.onNodeWithText("Homeserver").performClick()
        compose.runOnIdle { state.value = state.value.copy(hasMore = true) }
        val grid = compose.onNodeWithTag("library-grid")
        grid.performScrollToIndex(55)
        compose.waitUntil(5000) { state.value.items.size >= 120 }
        grid.performScrollToIndex(115)
        compose.waitUntil(5000) { state.value.items.size >= 180 }
        grid.performScrollToIndex(175)
        compose.waitUntil(5000) { state.value.items.size == 185 }
        grid.performScrollToIndex(184)
        compose.onNodeWithTag("library-card-184").assertIsDisplayed()
    }
    @Test fun mobilePaginationDoesNotResetScrollToFirstItem() = verifyAppend(false)
    private fun verifyAppend(poster: Boolean) {
        show(DeviceType.PHONE, poster)
        compose.onNodeWithText("Homeserver").performClick()
        val grid = compose.onNodeWithTag("library-grid")
        grid.performScrollToIndex(30)
        val before = grid.fetchSemanticsNode().config[SemanticsProperties.VerticalScrollAxisRange].value()
        compose.runOnIdle { state.value = state.value.copy(items = items(120)) }
        val after = grid.fetchSemanticsNode().config[SemanticsProperties.VerticalScrollAxisRange].value()
        assertEquals(before, after, .01f)
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
        keys.forEach { key -> compose.onNodeWithTag("oled-library").performKeyInput { pressKey(key) }; compose.waitForIdle() }
    }
    private fun capture(name: String) {
        val bitmap = compose.onNodeWithTag("oled-library").captureToImage().asAndroidBitmap()
        File(compose.activity.getExternalFilesDir(null), "$name.png").outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }
}
