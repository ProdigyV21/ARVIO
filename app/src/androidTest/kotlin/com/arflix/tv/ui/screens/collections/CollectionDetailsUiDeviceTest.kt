package com.arflix.tv.ui.screens.collections

import android.graphics.Bitmap
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.input.InputMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.*
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

/** Real TMDB cards in the production browser; no profile changes or media playback. */
@OptIn(ExperimentalTestApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
class CollectionDetailsUiDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test fun liveArtworkFocusScrollAndOpen() = runBlocking {
        assumeTrue(InstrumentationRegistry.getArguments().getString("collectionLive") == "true")
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val device = DeviceType.valueOf(InstrumentationRegistry.getArguments().getString("collectionDevice") ?: "TV")
        val posters = InstrumentationRegistry.getArguments().getString("collectionPosters") != "false"
        val repository = EntryPointAccessors.fromApplication(context, RepositoryAccessEntryPoint::class.java).mediaRepository()
        val catalogId = InstrumentationRegistry.getArguments().getString("collectionCatalogId")
        val catalog = if (catalogId != null) {
            com.arflix.tv.data.repository.MediaRepository.buildPreinstalledDefaults().first { it.id == catalogId }
        } else CatalogConfig("collection-ui-test", "Science fiction", CatalogSourceType.PREINSTALLED,
            kind = CatalogKind.COLLECTION,
            collectionDescription = "New worlds, speculative futures and journeys beyond our own.",
            collectionSources = listOf(CollectionSourceConfig(kind = CollectionSourceKind.TMDB_GENRE,
                mediaType = "movie", tmdbGenreId = 878, sortBy = "popularity.desc")))
        val page = repository.loadCollectionCatalogPage(catalog, 0, 30, MediaType.MOVIE)
        assertTrue("Real collection should contain enough cards to scroll", page.items.size >= 20)
        val provider = repository.getStreamingServices(MediaType.MOVIE, page.items.first().id,
            preferredRegion = java.util.Locale.getDefault().country)?.services?.firstOrNull()
        if (catalogId != null) assertFalse("Provider fixture must resolve actual artwork", provider?.logoUrl.isNullOrBlank())
        val details = repository.getMovieDetails(page.items.first().id)?.copy(primaryNetworkLogo = provider?.logoUrl)
        val items = page.items.map { if (it.id == details?.id) details else it }.filterNotNull()
        val logos = items.take(2).mapNotNull { item ->
            repository.getLogoUrl(item.mediaType, item.id)?.let { "${item.mediaType}_${item.id}" to it }
        }.toMap()
        var opened: MediaItem? = null
        var preview: MediaItem? = null
        var nearEnd = false
        var renderedGridState: LazyGridState? = null
        val selected = mutableStateOf(CollectionTab.MOVIES)
        compose.runOnUiThread {
            compose.activity.actionBar?.hide()
            androidx.core.view.WindowCompat.getInsetsController(compose.activity.window,
                compose.activity.window.decorView).hide(androidx.core.view.WindowInsetsCompat.Type.systemBars())
        }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides device) {
                val firstTab = remember { FocusRequester() }
                val secondTab = remember { FocusRequester() }
                val mobile = device.isTouchDevice()
                val inputMode = LocalInputModeManager.current
                val gridState = rememberLazyGridState()
                renderedGridState = gridState
                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(300)
                    if (!mobile) {
                        inputMode.requestInputMode(InputMode.Keyboard)
                        firstTab.requestFocus()
                    }
                }
                Box(Modifier.fillMaxSize().background(Color.Black)) {
                    CollectionItemsGrid(catalog, {}, items, posters,
                        gridState, -1, {}, true, true, logos, selected.value,
                        firstTab, secondTab, false, { selected.value = it }, { opened = it },
                        { _, _ -> }, {}, {}, { nearEnd = true }, false, false, "", 8.dp,
                        onPreviewItemChanged = { preview = it })
                }
            }
        }
        compose.mainClock.advanceTimeBy(400)
        compose.waitForIdle()
        if (logos.containsKey("${items.first().mediaType}_${items.first().id}")) {
            compose.waitUntil(10000) {
                compose.onAllNodesWithTag("collection_spotlight_title").fetchSemanticsNodes().isEmpty()
            }
            compose.onNodeWithTag("collection_clearlogo").assertContentDescriptionEquals(items.first().title)
        } else {
            compose.onNodeWithTag("collection_spotlight_title").assertTextEquals(items.first().title)
        }
        if (collectionRating(items.first())?.startsWith("IMDb ") == true) {
            compose.onNodeWithContentDescription("IMDb").assertIsDisplayed()
        }
        if (!provider?.logoUrl.isNullOrBlank()) {
            compose.onNodeWithTag("collection_provider_logo").assertIsDisplayed()
        }
        compose.onNodeWithTag("collection_grid").assertIsDisplayed()
        val gridTop = compose.onNodeWithTag("collection_grid").fetchSemanticsNode().boundsInRoot.top
        val heroBottom = compose.onNodeWithTag("collection_spotlight").fetchSemanticsNode().boundsInRoot.bottom
        assertEquals("Hero details stay above the scrolling cards", heroBottom, gridTop, 1f)
        val firstCardTop = compose.onNodeWithTag("collection_item_0").fetchSemanticsNode().boundsInRoot.top
        assertTrue("Initial cards must not overlap hero details", firstCardTop >= heroBottom)
        val backdropBottom = compose.onNodeWithTag("collection_backdrop").fetchSemanticsNode().boundsInRoot.bottom
        assertTrue("Artwork must continue behind the cards", backdropBottom > firstCardTop)
        val density = context.resources.displayMetrics.density
        val gridWidth = compose.onNodeWithTag("collection_grid").fetchSemanticsNode().boundsInRoot.width
        val gridLayout = collectionGridLayout((gridWidth / density).toInt(), device.isTouchDevice(), posters)
        val expectedWidth = if (device.isTouchDevice()) {
            (gridWidth - (40 + (gridLayout.columns - 1) * 12) * density) / gridLayout.columns
        } else gridLayout.cardWidthDp * density
        assertEquals("Touch cards fill their grid cell; TV cards retain Home width", expectedWidth,
            compose.onNodeWithTag("collection_item_0").fetchSemanticsNode().boundsInRoot.width, 2f)
        if (device.isTouchDevice()) {
            val first = compose.onNodeWithTag("collection_item_0").fetchSemanticsNode().boundsInRoot
            val second = compose.onNodeWithTag("collection_item_1").fetchSemanticsNode().boundsInRoot
            val columns = gridLayout.columns
            if (columns > 1) {
                assertEquals("Second card belongs beside the first, not below it", first.top, second.top, 1f)
                assertTrue("Columns must not overlap", second.left >= first.right)
            }
        }
        if (device == DeviceType.TV) {
            compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
            compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
            compose.mainClock.advanceTimeBy(200)
            compose.waitUntil(3000) {
                preview?.id == items[1].id
            }
            assertTrue("First-row focus must not clip the clearlogo header",
                compose.onNodeWithTag("collection_spotlight").getUnclippedBoundsInRoot().top.value >= 0)
            assertEquals(gridTop, compose.onNodeWithTag("collection_grid").fetchSemanticsNode().boundsInRoot.top)
            fun scrollPosition() = renderedGridState!!.let {
                it.firstVisibleItemIndex to it.firstVisibleItemScrollOffset
            }
            val rowPosition = compose.runOnIdle { scrollPosition() }
            repeat(6) {
                compose.onRoot().performKeyInput { pressKey(Key.DirectionLeft) }
                compose.mainClock.advanceTimeBy(200)
                compose.runOnIdle { assertEquals("Sideways focus must not scroll the row", rowPosition, scrollPosition()) }
                compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
                compose.mainClock.advanceTimeBy(200)
                compose.runOnIdle { assertEquals("Sideways focus must not scroll the row", rowPosition, scrollPosition()) }
            }
            compose.onRoot().performKeyInput { pressKey(Key.Enter) }
            compose.runOnIdle { assertEquals(items[1].id, opened?.id) }
            repeat(2) { compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) } }
            val scrolledPosition = compose.runOnIdle { scrollPosition() }
            assertNotEquals("Down must reveal later rows", rowPosition, scrolledPosition)
            repeat(6) {
                compose.onRoot().performKeyInput { pressKey(Key.DirectionLeft) }
                compose.mainClock.advanceTimeBy(200)
                compose.runOnIdle { assertEquals("Scrolled row must stay still moving left", scrolledPosition, scrollPosition()) }
                compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
                compose.mainClock.advanceTimeBy(200)
                compose.runOnIdle { assertEquals("Scrolled row must stay still moving right", scrolledPosition, scrollPosition()) }
            }
            repeat(14) { compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) } }
            compose.runOnIdle { assertTrue("Pagination must remain reachable", nearEnd) }
            compose.onNodeWithTag("collection_spotlight").assertIsDisplayed()
            assertEquals("Hero must remain stationary while scrolling", heroBottom,
                compose.onNodeWithTag("collection_spotlight").fetchSemanticsNode().boundsInRoot.bottom, 1f)
            val visibleCards = compose.onAllNodes(SemanticsMatcher("collection card") {
                it.config.getOrNull(androidx.compose.ui.semantics.SemanticsProperties.TestTag)
                    ?.startsWith("collection_item_") == true
            }).fetchSemanticsNodes().map { it.boundsInRoot }.filter { it.height > 0 }
            assertTrue("Cards must never cover the hero details", visibleCards.all { it.top >= heroBottom })
            Thread.sleep(1500)
            val scrolled = File(context.getExternalFilesDir(null), "collection-blended-scrolled-${if (posters) "poster" else "landscape"}.png")
            scrolled.outputStream().use {
                compose.onRoot().captureToImage().asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, 100, it)
            }
            repeat(14) { compose.onRoot().performKeyInput { pressKey(Key.DirectionUp) } }
            compose.onAllNodes(hasClickAction() and hasAnyAncestor(hasTestTag("collection_item_0"))).onFirst()
                .performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus) { it() }
            compose.mainClock.advanceTimeBy(200)
        } else {
            compose.onAllNodes(hasClickAction() and hasAnyAncestor(hasTestTag("collection_item_0"))).onFirst().performClick()
            compose.runOnIdle { assertEquals(items.first().id, opened?.id) }
            compose.onNodeWithTag("collection_grid").performTouchInput { swipeUp() }
            assertEquals("Hero must also remain visible on touch devices", heroBottom,
                compose.onNodeWithTag("collection_spotlight").fetchSemanticsNode().boundsInRoot.bottom, 1f)
            compose.onNodeWithTag("collection_grid").performTouchInput { swipeDown() }
        }
        // Let real images decode before recording the visual result (not a loading benchmark).
        Thread.sleep(2500)
        compose.waitForIdle()
        val screenshot = compose.onRoot().captureToImage().asAndroidBitmap()
        // The old header's rectangular edge caused a visible colour step across this seam.
        val seamY = heroBottom.toInt().coerceIn(1, screenshot.height - 1)
        var seamDifference = 0L
        val startX = screenshot.width * 3 / 4
        for (x in startX until screenshot.width) {
            val above = screenshot.getPixel(x, seamY - 1)
            val below = screenshot.getPixel(x, seamY)
            seamDifference += kotlin.math.abs(android.graphics.Color.red(above) - android.graphics.Color.red(below)) +
                kotlin.math.abs(android.graphics.Color.green(above) - android.graphics.Color.green(below)) +
                kotlin.math.abs(android.graphics.Color.blue(above) - android.graphics.Color.blue(below))
        }
        assertTrue("Backdrop must blend across the hero/grid boundary",
            seamDifference.toDouble() / ((screenshot.width - startX) * 3) < 12)
        val suffix = (if (posters) "" else "-landscape") + (if (catalogId != null) "-service" else "")
        val file = File(context.getExternalFilesDir(null), "collection-branded-${device.name}$suffix.png")
        file.outputStream().use {
            screenshot.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        instrumentation.sendStatus(0, Bundle().apply { putString("stream", "Collection UI passed: $device, ${items.size} real cards; screenshot ${file.name}\n") })
    }
}
