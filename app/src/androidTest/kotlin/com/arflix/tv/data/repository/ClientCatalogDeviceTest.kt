package com.arflix.tv.data.repository

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.*
import com.arflix.tv.data.repository.simkl.SimklSyncService
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class ClientCatalogDeviceTest {
    @get:Rule val compose = createComposeRule()
    private val streams = mockk<StreamRepository>()
    private val trakt = mockk<TraktRepository>()
    private val simkl = mockk<SimklSyncService>()
    private val home = mockk<HomeServerRepository>()
    private val media = spyk(MediaRepository(mockk(relaxed = true), mockk(), trakt, mockk(), mockk(), streams, home,
        simklSyncService = simkl))
    private val catalog = CatalogConfig("personal", "Personal", CatalogSourceType.ADDON,
        addonId = "fixture", addonCatalogId = "personal", addonCatalogType = "movie")

    init {
        every { streams.installedAddons } returns MutableStateFlow(emptyList())
        coEvery { media.getMovieDetails(any()) } answers {
            MediaItem(firstArg(), "Verified Film ${firstArg<Int>()}", year = if (firstArg<Int>() % 2 == 0) "2024" else "1999")
        }
    }

    private fun assertRemoteSelection(item: MediaItem) {
        assertTrue(item.hasOpenableId)
        var selected: MediaItem? = null
        val focus = FocusRequester()
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                ArflixTvTheme {
                    Box(Modifier.padding(32.dp)) {
                        MediaCard(item, modifier = Modifier.focusRequester(focus), artworkPainter = ColorPainter(Color.DarkGray),
                            onClick = { selected = item })
                    }
                    LaunchedEffect(Unit) { focus.requestFocus() }
                }
            }
        }
        compose.onNodeWithText(item.title).assertIsDisplayed()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        java.io.File(context.cacheDir, "client-catalog-${item.id}.png").outputStream().use { output ->
            compose.onRoot().captureToImage().asAndroidBitmap().compress(android.graphics.Bitmap.CompressFormat.PNG, 100, output)
        }
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        compose.runOnIdle { assertEquals(item.id, selected?.id) }
    }

    @Test fun traktManifestFilterPagesAndRemoteSelection() = runBlocking {
        every { streams.installedAddons } returns MutableStateFlow(listOf(Addon(
            "fixture", "Fixture", "1", "Fixture", true, type = AddonType.COMMUNITY,
            manifest = AddonManifest("fixture", "Fixture", "1", catalogs = listOf(AddonCatalog("movie", "personal",
                clientDelegate = "trakt://watchlist?type=movie", clientFilter = "{\"minYear\":2020}"))))))
        coEvery { trakt.getWatchlist() } returns (1..4).map { MediaItem(it, "Seed") }
        val first = media.loadCustomCatalogPage(catalog, 0, 1)
        assertEquals(2, first.items.single().id)
        assertTrue(first.hasMore)
        assertEquals(4, media.loadCustomCatalogPage(catalog, first.nextOffset!!, 1).items.single().id)
        coVerify(exactly = 0) { streams.getAddonCatalogPage(any(), any(), any(), any()) }
        assertRemoteSelection(first.items.single())
    }

    @Test fun simklHybridUsesItsOwnAccountAndRendersSelectableCards() = runBlocking {
        coEvery { simkl.getLibraryItems("completed", any()) } returns listOf(MediaItem(4, "Simkl"))
        val page = media.loadCustomCatalogPage(catalog.copy(clientDelegate = "hybrid:completed?tracker=simkl"), 0, 10)
        coVerify(exactly = 0) { trakt.getWatchlist() }
        assertEquals(4, page.items.single().id)
        assertRemoteSelection(page.items.single())
    }

    @Test fun homeServerLibraryDelegationUsesPlayableCatalogIdentity() = runBlocking {
        val source = "home_server_catalog|fixture-server|films|movie"
        coEvery { home.loadCatalogItems(source, 0, 1, any(), any(), any(), false) } returns HomeServerCatalogPage(
            listOf(HomeServerCatalogItem("native-2", "Film", MediaType.MOVIE, 2024, mapOf("tmdb" to "2"))), false, 1)
        val uri = "homeserver:library?source=${java.net.URLEncoder.encode(source, "UTF-8")}"
        val page = media.loadCustomCatalogPage(catalog.copy(clientDelegate = uri), 0, 1)
        assertEquals(2, page.items.single().id)
        assertRemoteSelection(page.items.single())
    }
}
