package com.arflix.tv.ui.screens.watchlist

import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.*
import com.arflix.tv.data.repository.*
import com.arflix.tv.ui.skin.LocalAccentColorOverride
import com.arflix.tv.util.*
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class OledLibraryDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val model = mockk<WatchlistViewModel>(relaxed = true)
    private lateinit var ui: MutableStateFlow<WatchlistUiState>
    private lateinit var library: MutableStateFlow<HomeLibraryUiState>
    private var previousLayout: String? = null
    private var profile = "default"
    private var opened: Int? = null

    @After fun restore(): Unit = runBlocking {
        compose.activity.settingsDataStore.edit { prefs ->
            val key = stringPreferencesKey("profile_${profile}_card_layout_mode")
            previousLayout?.let { prefs[key] = it } ?: prefs.remove(key)
        }
    }
    @Test fun landscapeArtworkFocusAndFilters() {
        show(false)
        compose.onNodeWithTag("library-card-0").assertIsDisplayed()
        val first = compose.onNodeWithTag("library-card-0").fetchSemanticsNode().boundsInRoot
        compose.onNodeWithTag("collection_spotlight").assertIsDisplayed()
        if(compose.activity.resources.configuration.screenWidthDp >= 600) {
            card(0).performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus)
            compose.mainClock.advanceTimeBy(400)
            compose.waitForIdle()
            val firstDescription = compose.onNodeWithTag("collection_spotlight_overview").fetchSemanticsNode()
                .config[androidx.compose.ui.semantics.SemanticsProperties.Text].single().text
            compose.onNodeWithTag("oled-library").performKeyInput { pressKey(Key.DirectionRight) }
            compose.mainClock.advanceTimeBy(400)
            compose.waitForIdle()
            val nextDescription = compose.onNodeWithTag("collection_spotlight_overview").fetchSemanticsNode()
                .config[androidx.compose.ui.semantics.SemanticsProperties.Text].single().text
            assertNotEquals("Description must follow the focused title", firstDescription, nextDescription)
            val after = compose.onNodeWithTag("library-card-0").fetchSemanticsNode().boundsInRoot
            assertEquals(first.top, after.top, 2f)
        }
        if(compose.activity.resources.configuration.screenWidthDp >= 600) compose.onNodeWithTag("library-source-my_watchlist").performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus)
        capture("watchlists-horizontal")
        assertBackdropFadesToBlack()
        compose.onNodeWithText("Filters").performClick()
        compose.onNodeWithText("Title A-Z").assertIsDisplayed().performClick()
        capture("filters-right")
        compose.onNode(hasTestTag("library-drawer-close") or hasText("Close") or hasText("Close ×")).performClick()
        card(0).performClick()
        assertNotNull(opened)
    }
    @Test fun collectionAndServerScreens() {
        show(false)
        compose.onNodeWithText("My lists").performClick()
        compose.onNodeWithText("Friday night").assertIsDisplayed()
        if(compose.activity.resources.configuration.screenWidthDp >= 600) compose.onNodeWithText("Friday night").performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus)
        capture("personal-lists")
        compose.onNodeWithText("Friday night").performClick()
        compose.onNodeWithTag("library-card-0").assertIsDisplayed()
        compose.onNodeWithText("Homeserver").performClick()
        compose.onNodeWithTag("library-card-0").assertIsDisplayed()
        capture("server-libraries")
    }

    @Test fun tabletSidebarScrollsToTheLastSource() {
        show(false, deviceOverride = DeviceType.TABLET, serverCount = 32)
        compose.onNodeWithText("Homeserver").performClick()
        compose.onNodeWithTag("library-sources").performScrollToKey("server_fixture-library-31")
        compose.onNodeWithTag("library-source-server_fixture-library-31").assertIsDisplayed().performClick()
        verify { model.selectLibrary("fixture-library-31") }
    }

    @Test fun phoneSourceDrawerStillScrollsToTheLastSource() {
        show(false, deviceOverride = DeviceType.PHONE, serverCount = 32)
        compose.onNodeWithText("Homeserver").performScrollTo().performClick()
        compose.onNodeWithTag("library-source-picker").performClick()
        compose.onNodeWithTag("library-source-server_fixture-library-31").performScrollTo().assertIsDisplayed().performClick()
        verify { model.selectLibrary("fixture-library-31") }
    }

    @Test fun phoneSearchOpensTheKeyboardOnTheFirstTap() {
        show(false, deviceOverride = DeviceType.PHONE)
        compose.onNodeWithContentDescription("Search library").performTouchInput { click() }
        val field = compose.onNodeWithTag("library-search-field").assertIsFocused()
        compose.waitUntil(timeoutMillis = 5_000) {
            compose.runOnUiThread {
                ViewCompat.getRootWindowInsets(compose.activity.window.decorView)
                    ?.isVisible(WindowInsetsCompat.Type.ime()) == true
            }
        }
        field.performTextInput("fixture")
        field.assertTextContains("fixture")
    }
    @Test fun posterAndLongGridScroll() {
        show(true)
        compose.onNodeWithTag("library-grid").performScrollToIndex(55)
        compose.onNodeWithTag("library-card-55").assertIsDisplayed()
        compose.onNodeWithTag("collection_spotlight").assertIsDisplayed()
        compose.onNodeWithTag("library-grid").performScrollToIndex(0)
        capture("watchlists-poster")
    }
    @Test fun landscapeFocusedCardsRemainFullyVisibleWhileScrolling() = verifyFocusedScroll(false)
    @Test fun posterFocusedCardsRemainFullyVisibleWhileScrolling() = verifyFocusedScroll(true)
    @Test fun homeServerLandscapeRowsDoNotStopBehindThePreview() = verifyFocusedScroll(false, server = true)
    @Test fun homeServerPosterRowsDoNotStopBehindThePreview() = verifyFocusedScroll(true, server = true)
    @Test fun landscapeScrollNeverPaintsOverPreview() = verifyPreviewClip(false)
    @Test fun posterScrollNeverPaintsOverPreview() = verifyPreviewClip(true)

    private fun verifyPreviewClip(poster: Boolean) {
        show(poster, fixedPreview = true)
        card(0).performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus)
        compose.mainClock.advanceTimeBy(500)
        Thread.sleep(1200)
        compose.waitForIdle()
        val previewBounds = compose.onNodeWithTag("collection_spotlight").fetchSemanticsNode().boundsInRoot
        val gridBounds = compose.onNodeWithTag("library-grid").fetchSemanticsNode().boundsInRoot
        val reference = compose.onNodeWithTag("oled-library").captureToImage().asAndroidBitmap()
        compose.mainClock.autoAdvance = false
        try {
            repeat(6) {
                compose.onNodeWithTag("oled-library").performKeyInput { pressKey(Key.DirectionDown) }
                repeat(4) {
                    compose.mainClock.advanceTimeBy(40)
                    compose.waitForIdle()
                    val current = compose.onNodeWithTag("oled-library").captureToImage().asAndroidBitmap()
                    var changed = 0
                    var samples = 0
                    for (y in previewBounds.top.toInt() until gridBounds.top.toInt() step 4) {
                        for (x in gridBounds.left.toInt() until gridBounds.right.toInt() step 4) {
                            val before = reference.getPixel(x, y)
                            val after = current.getPixel(x, y)
                            val delta = maxOf(
                                kotlin.math.abs(android.graphics.Color.red(before) - android.graphics.Color.red(after)),
                                kotlin.math.abs(android.graphics.Color.green(before) - android.graphics.Color.green(after)),
                                kotlin.math.abs(android.graphics.Color.blue(before) - android.graphics.Color.blue(after))
                            )
                            if (delta > 16) changed++
                            samples++
                        }
                    }
                    assertTrue("Scrolling cards painted over the fixed preview: $changed/$samples pixels",
                        changed <= samples / 1000)
                    assertEquals(previewBounds, compose.onNodeWithTag("collection_spotlight").fetchSemanticsNode().boundsInRoot)
                }
                compose.mainClock.advanceTimeBy(200)
            }
        } finally {
            compose.mainClock.autoAdvance = true
        }
        capture(if (poster) "poster-preview-protected" else "horizontal-preview-protected")
    }

    @Test fun touchScrollUpdatesPersistentDescription() {
        show(false)
        if(compose.activity.resources.configuration.screenWidthDp >= 600) return
        compose.mainClock.advanceTimeBy(400)
        val before = compose.onNodeWithTag("collection_spotlight_overview").fetchSemanticsNode()
            .config[androidx.compose.ui.semantics.SemanticsProperties.Text].single().text
        compose.onNodeWithTag("library-grid").performScrollToIndex(12)
        compose.mainClock.advanceTimeBy(400)
        val after = compose.onNodeWithTag("collection_spotlight_overview").fetchSemanticsNode()
            .config[androidx.compose.ui.semantics.SemanticsProperties.Text].single().text
        assertNotEquals(before, after)
        compose.onNodeWithTag("collection_spotlight").assertIsDisplayed()
        capture("phone-description-scrolled")
    }

    private fun verifyFocusedScroll(poster: Boolean, server: Boolean = false) {
        show(poster)
        if (server) compose.onNodeWithText("Homeserver").performClick()
        card(0).performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus)
        compose.waitForIdle()
        val anchor = compose.onNodeWithTag("library-card-frame-0").getUnclippedBoundsInRoot().top
        repeat(14) {
            compose.onNodeWithTag("oled-library").performKeyInput { pressKey(Key.DirectionDown) }
            compose.waitForIdle()
            val focused = compose.onAllNodes(isFocused()).fetchSemanticsNodes().single()
            val grid = compose.onNodeWithTag("library-grid").getUnclippedBoundsInRoot()
            val frame = generateSequence(focused.parent) { it.parent }.firstOrNull {
                it.config.contains(androidx.compose.ui.semantics.SemanticsProperties.TestTag) &&
                    it.config[androidx.compose.ui.semantics.SemanticsProperties.TestTag].startsWith("library-card-frame-")
            } ?: error("Focused card has no padded frame")
            val tag = frame.config[androidx.compose.ui.semantics.SemanticsProperties.TestTag]
            val bounds = compose.onNodeWithTag(tag).getUnclippedBoundsInRoot()
            assertTrue("Focused card clipped above: $bounds in $grid", bounds.top >= grid.top)
            assertTrue("Focused card title clipped below: $bounds in $grid", bounds.bottom <= grid.bottom)
            assertEquals("Each focused row must reach the same anchor", anchor.value, bounds.top.value, 1f)
            assertNoCroppedPreviousRow()
            if (it == 0) {
                for (key in listOf(Key.DirectionRight, Key.DirectionLeft)) {
                    compose.onNodeWithTag("oled-library").performKeyInput { pressKey(key) }
                    compose.waitForIdle()
                    assertEquals(anchor.value, compose.onNodeWithTag(tag).getUnclippedBoundsInRoot().top.value, 1f)
                    assertNoCroppedPreviousRow()
                }
            }
        }
        capture(if(poster) "poster-scrolled-focus" else "horizontal-scrolled-focus")
        repeat(14) {
            compose.onNodeWithTag("oled-library").performKeyInput { pressKey(Key.DirectionUp) }
            compose.waitForIdle()
            assertNoCroppedPreviousRow()
        }
        capture(if(poster) "poster-top-focus" else "horizontal-top-focus")
        val last = if (server) library.value.items.lastIndex else ui.value.movies.size + ui.value.series.size - 1
        compose.onNodeWithTag("library-grid").performScrollToIndex(last)
        card(last).performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus)
        compose.waitForIdle()
        assertEquals("Last row must not be clamped beneath a cropped previous row", anchor.value,
            compose.onNodeWithTag("library-card-frame-$last").getUnclippedBoundsInRoot().top.value, 1f)
        assertNoCroppedPreviousRow()
        capture("${if (server) "server" else "watchlist"}-${if (poster) "poster" else "horizontal"}-last-row")
    }
    private fun assertNoCroppedPreviousRow() {
        val grid = compose.onNodeWithTag("library-grid").getUnclippedBoundsInRoot()
        val frames = compose.onAllNodes(SemanticsMatcher("Library card frame") {
            it.config.contains(androidx.compose.ui.semantics.SemanticsProperties.TestTag) &&
                it.config[androidx.compose.ui.semantics.SemanticsProperties.TestTag].startsWith("library-card-frame-")
        }).fetchSemanticsNodes()
        frames.forEach { frame ->
            val tag = frame.config[androidx.compose.ui.semantics.SemanticsProperties.TestTag]
            val bounds = compose.onNodeWithTag(tag).getUnclippedBoundsInRoot()
            if (bounds.bottom > grid.top + 1.dp && bounds.top < grid.bottom) {
                assertTrue("Previous row is cropped beneath the hero: $tag $bounds in $grid", bounds.top >= grid.top)
            }
        }
    }
    private fun show(poster: Boolean, fixedPreview: Boolean = false, deviceOverride: DeviceType? = null, serverCount: Int = 1) {
        val context = compose.activity
        val assets = InstrumentationRegistry.getInstrumentation().context.assets
        val destination = File(context.cacheDir, "library-fixtures").apply { mkdirs() }
        assets.list("library")!!.forEach { name -> assets.open("library/$name").use { input -> File(destination,name).outputStream().use { input.copyTo(it) } } }
        val rows = JSONArray(File(destination,"titles.json").readText())
        fun uri(name: String) = File(destination,name).toURI().toString()
        val seed = (0 until rows.length()).map { i -> val row = rows.getJSONObject(i)
            MediaItem(id=row.getInt("id"), title=row.getString("title"), year=row.getString("year"), mediaType=MediaType.valueOf(row.getString("mediaType")), image=uri(row.getString("poster")), backdrop=uri(row.getString("backdrop"))) }
        val titles = (0 until 6).flatMap { batch -> seed.map { it.copy(id=it.id + batch*1000000) } }
            .map { if (fixedPreview) seed.first().copy(id = it.id) else it }
        val logos = if (fixedPreview) titles.associate { watchlistLogoKey(it) to uri(rows.getJSONObject(0).optString("logo")) }
        else (0 until 6).flatMap { batch -> (0 until rows.length()).map { i ->
            watchlistLogoKey(seed[i].copy(id = seed[i].id + batch * 1000000)) to uri(rows.getJSONObject(i).optString("logo"))
        } }.toMap()
        val lists = listOf("Friday night","Science fiction essentials","Family favourites","Hidden gems","Weekend series","Award winners").mapIndexed { index, title ->
            WatchlistSourceItem.Catalog(CatalogConfig("fixture-$index", title, CatalogSourceType.TRAKT)) }
        val server = HomeServerCatalogCandidate("Movies", "fixture-movies", "Home NAS", "Movies", "movies", HomeServerKind.JELLYFIN, "fixture")
        val serverLibraries = listOf(server) + (1 until serverCount).map { index ->
            server.copy(sourceRef = "fixture-library-$index", collectionName = "Library $index")
        }
        ui = MutableStateFlow(WatchlistUiState(sources=listOf(WatchlistSourceItem.MyWatchlist,
            WatchlistSourceItem.TrackerList(TrackerLibraryProvider.TRAKT,"watchlist","Watchlist"),
            WatchlistSourceItem.TrackerList(TrackerLibraryProvider.SIMKL,"plantowatch","Plan to watch"), WatchlistSourceItem.HomeServer(server)) + lists,
            isLoading=false, movies=titles.filter { it.mediaType == MediaType.MOVIE }, series=titles.filter { it.mediaType == MediaType.TV }))
        library = MutableStateFlow(HomeLibraryUiState(providers=listOf(HomeServerKind.JELLYFIN),libraries=serverLibraries))
        every { model.uiState } returns ui; every { model.libraryState } returns library; every { model.logoUrls } returns MutableStateFlow(logos)
        every { model.kidsFilterState } returns MutableStateFlow(KidsContentFilter.State(null, "US", emptyMap()))
        every { model.applyKidsFilter(any<WatchlistUiState>(), any()) } answers { firstArg() }
        every { model.applyKidsFilter(any<HomeLibraryUiState>(), any()) } answers { firstArg() }
        coEvery { model.loadLibraryPreview(any()) } answers { LibraryPreview(firstArg<MediaItem>().copy(overview = "A detailed description of ${firstArg<MediaItem>().title}.")) }
        every { model.selectSource(any()) } answers { ui.value=ui.value.copy(selectedSourceId=firstArg()) }
        every { model.selectLibraryProvider(any()) } answers { library.value=library.value.copy(selectedProvider=firstArg(),selectedSourceRef=server.sourceRef,items=titles) }
        every { model.selectLibrary(any()) } answers { library.value=library.value.copy(selectedSourceRef=firstArg(),items=titles) }
        coEvery { model.collectionCover(any()) } answers { seed[lists.indexOf(firstArg()).coerceAtLeast(0)].backdrop }
        runBlocking {
            profile=context.profilesDataStore.data.first()[stringPreferencesKey("active_profile_id")].orEmpty().ifBlank { "default" }
            val key=stringPreferencesKey("profile_${profile}_card_layout_mode")
            previousLayout=context.settingsDataStore.data.first()[key]
            context.settingsDataStore.edit { it[key]=if(poster) "Poster" else "Landscape" }
        }
        val device = deviceOverride ?: if(InstrumentationRegistry.getArguments().getString("formFactor") == "tablet") DeviceType.TABLET else if(context.resources.configuration.screenWidthDp < 600) DeviceType.PHONE else DeviceType.TV
        compose.setContent { CompositionLocalProvider(LocalDeviceType provides device, LocalAccentColorOverride provides Color.White) {
            WatchlistScreen(model,currentProfile=Profile(id="fixture", name="P", avatarColor=0xFF242426L),onNavigateToDetails={ _,id -> opened=id })
        } }
        compose.waitForIdle()
    }
    private fun card(index: Int) = compose.onAllNodes(hasAnyAncestor(hasTestTag("library-card-$index")) and hasClickAction()).onFirst()
    private fun assertBackdropFadesToBlack() {
        val bounds = compose.onNodeWithTag("collection_backdrop").fetchSemanticsNode().boundsInRoot
        val root = compose.onNodeWithTag("oled-library").fetchSemanticsNode().boundsInRoot
        val bitmap = compose.onNodeWithTag("oled-library").captureToImage().asAndroidBitmap()
        val left = (bounds.left - root.left).toInt()
        val top = (bounds.top - root.top).toInt()
        val right = (bounds.right - root.left).toInt() - 1
        val bottom = (bounds.bottom - root.top).toInt().coerceAtMost(bitmap.height) - 1
        val points = listOf(left to (top + (bottom - top) / 2),
            right to (top + (bottom - top) / 10),
            (left + (right - left) * 4 / 5) to top, left to bottom)
        points.forEach { (x, y) ->
            val pixel = bitmap.getPixel(x, y)
            assertTrue("Backdrop edge must blend into pure black at $x,$y: $pixel",
                android.graphics.Color.red(pixel) <= 6 && android.graphics.Color.green(pixel) <= 6 &&
                    android.graphics.Color.blue(pixel) <= 6)
        }
    }
    private fun capture(name: String) {
        Thread.sleep(1200)
        compose.waitForIdle()
        val bitmap=(if(name == "filters-right") compose.onNodeWithTag("library-drawer-root") else compose.onNodeWithTag("oled-library")).captureToImage().asAndroidBitmap()
        File(compose.activity.getExternalFilesDir(null),"oled-$name.png").outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG,100,it) }
    }
}
