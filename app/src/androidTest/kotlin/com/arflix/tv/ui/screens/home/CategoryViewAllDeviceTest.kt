package com.arflix.tv.ui.screens.home

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.InputMode
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.di.RepositoryAccessEntryPoint
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import dagger.hilt.android.EntryPointAccessors
import java.io.File
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
class CategoryViewAllDeviceTest {
    @get:Rule val compose = createComposeRule()
    private val items = (1..48).map { MediaItem(it, "Movie $it", overview = "Overview $it", year = "2026") }

    private fun card(index: Int) = compose.onNodeWithTag("collection_item_$index")
        .onChildren().filter(hasClickAction()).onFirst()

    @Test fun tvHeroFocusPaginationAndRestoration() {
        val restoration = StateRestorationTester(compose)
        var opened: MediaItem? = null
        var lastVisible = -1
        var nearEnd = false
        restoration.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                LocalInputModeManager.current.requestInputMode(InputMode.Keyboard)
                CategoryViewAllContent("popular", "Popular movies", items, false, emptyMap(), false, true,
                    onPreviewItemChanged = {}, onVisiblePositionChanged = { last, _ -> lastVisible = last },
                    onNearEnd = { nearEnd = true }, onItemClick = { opened = it }, onBack = {})
            }
        }
        compose.waitUntil(5000) { card(0).fetchSemanticsNode().config[androidx.compose.ui.semantics.SemanticsProperties.Focused] }
        val heroBottom = compose.onNodeWithTag("collection_spotlight").getUnclippedBoundsInRoot().bottom
        val cardTop = compose.onNodeWithTag("collection_item_0").getUnclippedBoundsInRoot().top
        compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
        compose.mainClock.advanceTimeBy(200)
        card(1).assertIsFocused()
        compose.onNodeWithTag("collection_spotlight_title").assertTextEquals("Movie 2")
        assertEquals("Horizontal focus must not drag the grid", cardTop,
            compose.onNodeWithTag("collection_item_1").getUnclippedBoundsInRoot().top)
        compose.onRoot().performKeyInput { pressKey(Key.Enter) }
        compose.runOnIdle { assertEquals(2, opened?.id) }
        restoration.emulateSavedInstanceStateRestore()
        compose.mainClock.advanceTimeBy(500)
        card(1).assertIsFocused()
        repeat(15) {
            compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
            compose.mainClock.advanceTimeBy(200)
        }
        compose.runOnIdle {
            assertTrue("Visible-index paging must reach the end of the catalog", lastVisible >= items.size - 4)
            assertTrue("Near-end paging must remain reachable", nearEnd)
        }
        assertEquals("Collection hero must stay above the scrolling grid", heroBottom,
            compose.onNodeWithTag("collection_spotlight").getUnclippedBoundsInRoot().bottom)
    }

    @Test fun lateDataAndDuplicateEntriesRemainFocusable() {
        val data = mutableStateOf(emptyList<MediaItem>())
        var opened: MediaItem? = null
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                LocalInputModeManager.current.requestInputMode(InputMode.Keyboard)
                CategoryViewAllContent("late", "Late catalog", data.value, true, emptyMap(), data.value.isEmpty(), false,
                    onPreviewItemChanged = {}, onVisiblePositionChanged = { _, _ -> }, onNearEnd = {},
                    onItemClick = { opened = it }, onBack = {})
            }
        }
        compose.runOnIdle { data.value = listOf(items[0], items[0], items[2]) }
        compose.mainClock.advanceTimeBy(500)
        card(0).assertIsFocused()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionRight); pressKey(Key.DirectionRight) }
        compose.mainClock.advanceTimeBy(200)
        card(2).assertIsFocused()
        compose.onRoot().performKeyInput { pressKey(Key.Enter) }
        compose.runOnIdle { assertEquals(3, opened?.id) }
    }

    @Test fun mobileCardsFillWidthAndTouchScrollingWorks() {
        val width = mutableStateOf(360.dp)
        val posters = mutableStateOf(false)
        var opened: MediaItem? = null
        var lastVisible = -1
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                Box(Modifier.width(width.value).fillMaxSize()) {
                    CategoryViewAllContent("mobile", "Popular movies", items, posters.value, emptyMap(), false, false,
                        onPreviewItemChanged = {}, onVisiblePositionChanged = { last, _ -> lastVisible = last },
                        onNearEnd = {}, onItemClick = { opened = it }, onBack = {})
                }
            }
        }
        fun checkWidth(columns: Int) {
            compose.waitForIdle()
            val grid = compose.onNodeWithTag("collection_grid").getUnclippedBoundsInRoot()
            val first = compose.onNodeWithTag("collection_item_0").getUnclippedBoundsInRoot()
            val last = compose.onNodeWithTag("collection_item_${columns - 1}").getUnclippedBoundsInRoot()
            assertEquals(first.top.value, last.top.value, 1f)
            assertEquals(grid.right.value - 20, last.right.value, 2f)
        }
        checkWidth(2)
        card(1).performClick()
        compose.runOnIdle { assertEquals(2, opened?.id) }
        compose.runOnIdle { width.value = 280.dp }
        checkWidth(1)
        compose.runOnIdle { posters.value = true }
        checkWidth(2)
        val heroBottom = compose.onNodeWithTag("collection_spotlight").getUnclippedBoundsInRoot().bottom
        repeat(3) { compose.onNodeWithTag("collection_grid").performTouchInput { swipeUp() } }
        compose.runOnIdle { assertTrue(lastVisible > 5) }
        assertEquals(heroBottom, compose.onNodeWithTag("collection_spotlight").getUnclippedBoundsInRoot().bottom)
    }

    @Test fun stalePreviewMetadataCannotReplaceFocusedTitle() {
        val preview = mutableStateOf<MediaItem?>(items[1])
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                CategoryViewAllContent("preview", "Popular movies", items, false, emptyMap(), false, false,
                    previewItem = preview.value, onPreviewItemChanged = {}, onVisiblePositionChanged = { _, _ -> },
                    onNearEnd = {}, onItemClick = {}, onBack = {})
            }
        }
        compose.onNodeWithTag("collection_spotlight_title").assertTextEquals("Movie 1")
        compose.runOnIdle { preview.value = items[0].copy(overview = "Enriched overview", duration = "2h 10m") }
        compose.onNodeWithTag("collection_spotlight_overview").assertTextEquals("Enriched overview")
        compose.onNodeWithTag("collection_spotlight_facts").assertTextContains("2h 10m", substring = true)
    }

    @Test fun liveCatalogArtworkScreenshot(): Unit = runBlocking {
        assumeTrue(InstrumentationRegistry.getArguments().getString("catalogLive") == "true")
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val repository = EntryPointAccessors.fromApplication(context, RepositoryAccessEntryPoint::class.java).mediaRepository()
        val page = repository.loadHomeCategoryPage("trending_movies", 1)
        assertTrue("Real catalog must load", page.items.size >= 12)
        val first = (repository.getMovieDetails(page.items.first().id) ?: page.items.first()).copy(
            primaryNetworkLogo = repository.getStreamingServices(MediaType.MOVIE, page.items.first().id,
                preferredRegion = java.util.Locale.getDefault().country)?.services?.firstOrNull()?.logoUrl
        )
        val logos = page.items.take(4).mapNotNull { item ->
            repository.getLogoUrl(MediaType.MOVIE, item.id)?.let { "MOVIE_${item.id}" to it }
        }.toMap()
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                LocalInputModeManager.current.requestInputMode(InputMode.Keyboard)
                CategoryViewAllContent("trending_movies", "Trending movies", page.items, false, logos, false, true,
                    previewItem = first, onPreviewItemChanged = {}, onVisiblePositionChanged = { _, _ -> },
                    onNearEnd = {}, onItemClick = {}, onBack = {})
            }
        }
        Thread.sleep(5000)
        compose.waitForIdle()
        val image = compose.onRoot().captureToImage().asAndroidBitmap()
        File(context.getExternalFilesDir(null), "catalog-collection-layout-tv.png").outputStream().use {
            image.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }
}
