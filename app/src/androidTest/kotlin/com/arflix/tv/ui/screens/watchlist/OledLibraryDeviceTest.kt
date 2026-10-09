package com.arflix.tv.ui.screens.watchlist

import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
        if(compose.activity.resources.configuration.screenWidthDp >= 600) {
            card(0).performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus)
            compose.onNodeWithTag("oled-library").performKeyInput { pressKey(Key.DirectionRight) }
            val after = compose.onNodeWithTag("library-card-0").fetchSemanticsNode().boundsInRoot
            assertEquals(first.top, after.top, 2f)
        }
        if(compose.activity.resources.configuration.screenWidthDp >= 600) compose.onNodeWithTag("library-source-my_watchlist").performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus)
        capture("watchlists-horizontal")
        compose.onNodeWithText("Filters").performClick()
        compose.onNodeWithText("Title A-Z").assertIsDisplayed().performClick()
        capture("filters-right")
        compose.onNodeWithText("Close ×").performClick()
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
    @Test fun posterAndLongGridScroll() {
        show(true)
        compose.onNodeWithTag("library-grid").performScrollToIndex(55)
        compose.onNodeWithTag("library-card-55").assertIsDisplayed()
        compose.onNodeWithTag("library-grid").performScrollToIndex(0)
        capture("watchlists-poster")
    }
    @Test fun landscapeFocusedCardsRemainFullyVisibleWhileScrolling() = verifyFocusedScroll(false)
    @Test fun posterFocusedCardsRemainFullyVisibleWhileScrolling() = verifyFocusedScroll(true)

    private fun verifyFocusedScroll(poster: Boolean) {
        show(poster)
        card(0).performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus)
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
        }
        capture(if(poster) "poster-scrolled-focus" else "horizontal-scrolled-focus")
        repeat(14) { compose.onNodeWithTag("oled-library").performKeyInput { pressKey(Key.DirectionUp) }; compose.waitForIdle() }
        capture(if(poster) "poster-top-focus" else "horizontal-top-focus")
    }
    private fun show(poster: Boolean) {
        val context = compose.activity
        val assets = InstrumentationRegistry.getInstrumentation().context.assets
        val destination = File(context.cacheDir, "library-fixtures").apply { mkdirs() }
        assets.list("library")!!.forEach { name -> assets.open("library/$name").use { input -> File(destination,name).outputStream().use { input.copyTo(it) } } }
        val rows = JSONArray(File(destination,"titles.json").readText())
        fun uri(name: String) = File(destination,name).toURI().toString()
        val seed = (0 until rows.length()).map { i -> val row = rows.getJSONObject(i)
            MediaItem(id=row.getInt("id"), title=row.getString("title"), year=row.getString("year"), mediaType=MediaType.valueOf(row.getString("mediaType")), image=uri(row.getString("poster")), backdrop=uri(row.getString("backdrop"))) }
        val titles = (0 until 6).flatMap { batch -> seed.map { it.copy(id=it.id + batch*1000000) } }
        val logos = (0 until 6).flatMap { batch -> (0 until rows.length()).map { i ->
            watchlistLogoKey(seed[i].copy(id = seed[i].id + batch * 1000000)) to uri(rows.getJSONObject(i).optString("logo"))
        } }.toMap()
        val lists = listOf("Friday night","Science fiction essentials","Family favourites","Hidden gems","Weekend series","Award winners").mapIndexed { index, title ->
            WatchlistSourceItem.Catalog(CatalogConfig("fixture-$index", title, CatalogSourceType.TRAKT)) }
        val server = HomeServerCatalogCandidate("Movies", "fixture-movies", "Home NAS", "Movies", "movies", HomeServerKind.JELLYFIN, "fixture")
        ui = MutableStateFlow(WatchlistUiState(sources=listOf(WatchlistSourceItem.MyWatchlist,
            WatchlistSourceItem.TrackerList(TrackerLibraryProvider.TRAKT,"watchlist","Watchlist"),
            WatchlistSourceItem.TrackerList(TrackerLibraryProvider.SIMKL,"plantowatch","Plan to watch"), WatchlistSourceItem.HomeServer(server)) + lists,
            isLoading=false, movies=titles.filter { it.mediaType == MediaType.MOVIE }, series=titles.filter { it.mediaType == MediaType.TV }))
        library = MutableStateFlow(HomeLibraryUiState(providers=listOf(HomeServerKind.JELLYFIN),libraries=listOf(server)))
        every { model.uiState } returns ui; every { model.libraryState } returns library; every { model.logoUrls } returns MutableStateFlow(logos)
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
        val device=if(InstrumentationRegistry.getArguments().getString("formFactor") == "tablet") DeviceType.TABLET else if(context.resources.configuration.screenWidthDp < 600) DeviceType.PHONE else DeviceType.TV
        compose.setContent { CompositionLocalProvider(LocalDeviceType provides device, LocalAccentColorOverride provides Color.White) {
            WatchlistScreen(model,currentProfile=Profile(id="fixture", name="P", avatarColor=0xFF242426L),onNavigateToDetails={ _,id -> opened=id })
        } }
        compose.waitForIdle()
    }
    private fun card(index: Int) = compose.onAllNodes(hasAnyAncestor(hasTestTag("library-card-$index")) and hasClickAction()).onFirst()
    private fun capture(name: String) {
        Thread.sleep(1200)
        compose.waitForIdle()
        val bitmap=(if(name == "filters-right") compose.onNodeWithTag("library-drawer-root") else compose.onNodeWithTag("oled-library")).captureToImage().asAndroidBitmap()
        File(compose.activity.getExternalFilesDir(null),"oled-$name.png").outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG,100,it) }
    }
}
