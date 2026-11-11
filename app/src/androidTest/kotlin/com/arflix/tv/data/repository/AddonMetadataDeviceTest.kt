package com.arflix.tv.data.repository

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.api.StreamApi
import com.arflix.tv.data.api.StremioCatalog
import com.arflix.tv.data.api.StremioManifestResponse
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonCatalog
import com.arflix.tv.data.model.AddonManifest
import com.arflix.tv.data.model.AddonType
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class AddonMetadataDeviceTest {
    @get:Rule val compose = createComposeRule()
    private val context get() = InstrumentationRegistry.getInstrumentation().targetContext

    private fun addon(id: String, setup: Long = 200, metadata: Long = 300) = Addon(
        id, "Family $id", "1", "", true, true, AddonType.CUSTOM,
        url = "https://$id.example/p/family/manifest.json", configureUrl = "https://$id.example/configure",
        setupUpdatedAt = setup, metadataUpdatedAt = metadata,
        manifest = AddonManifest(id, "Family $id", "1", catalogs = listOf(
            AddonCatalog("movie", "vod", "Recent VOD", clientDelegate = "vod:recent?type=movie"))))

    @Test fun legacyEditsAndNewerEmptyMetadataRoundTripWithoutResurrectingRows() {
        val current = addon("family")
        val legacy = current.copy(isEnabled = false, setupUpdatedAt = 0, metadataUpdatedAt = 0,
            url = "https://family.example/manifest.json", manifest = null)
        val state = reconcileExplicitAddonState(AddonCloudState(listOf(current), 200, emptyMap()),
            AddonCloudState(listOf(legacy), 500, emptyMap()))
        val merged = state.addons.single()
        assertFalse(merged.isEnabled)
        assertEquals(current.url, merged.url)
        assertEquals(current.manifest, merged.manifest)
        assertEquals(500L, merged.setupUpdatedAt)
        val cleared = current.copy(metadataUpdatedAt = 600,
            manifest = current.manifest!!.copy(version = "2", catalogs = emptyList()))
        val afterClear = reconcileExplicitAddonState(state, AddonCloudState(listOf(cleared), 600, emptyMap()))
        val afterReplay = reconcileExplicitAddonState(afterClear, AddonCloudState(listOf(current), 700, emptyMap()))
        assertFalse(afterReplay.addons.single().isEnabled)
        assertTrue(afterReplay.addons.single().manifest!!.catalogs.isEmpty())
        assertEquals(600L, afterReplay.addons.single().metadataUpdatedAt)
    }

    @Test fun refreshContinuesPastSlowAddonAndPersistsHealthyRowsWithoutSetupChurn() = runBlocking {
        context.streamDataStore.edit { it.clear() }
        val profiles = mockk<ProfileManager> {
            every { getProfileIdSync() } returns "default"
            every { currentProfileId } returns MutableStateFlow("default")
            every { profileStringKey(any()) } answers { stringPreferencesKey("profile_default_${firstArg<String>()}") }
            every { profileStringKeyFor(any(), any()) } answers {
                stringPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}")
            }
        }
        val api = mockk<StreamApi>(relaxed = true)
        val httpRuntime = mockk<HttpLocalScraperRuntime>(relaxed = true)
        coEvery { httpRuntime.fetchInstallCandidate(any(), any()) } returns null
        val repository = StreamRepository(context, api, mockk(relaxed = true), profiles,
            mockk(relaxed = true), mockk(relaxed = true), httpRuntime, mockk(relaxed = true),
            CloudSyncInvalidationBus(), mockk(relaxed = true), mockk(relaxed = true))
        try {
            val slow = addon("slow", metadata = 1)
            val good = addon("good", metadata = 1)
            repository.applyAddonCloudState(listOf(slow, good), 100, emptyMap())
            coEvery { api.getAddonManifest(slow.url!!) } coAnswers { delay(60_000); error("Must time out") }
            coEvery { api.getAddonManifest(good.url!!) } returns StremioManifestResponse(
                id = "good", name = "Family good", version = "2", catalogs = listOf(
                    StremioCatalog("movie", "vod", "Recent VOD", clientDelegate = "vod:recent?type=movie")))
            val report = repository.refreshInstalledAddonsIfNeeded()
            assertEquals(1, report?.refreshed)
            assertEquals(1, report?.failed)
            val saved = repository.exportAddonCloudState()
            assertEquals(100L, saved.updatedAt)
            assertTrue(saved.changes.isEmpty())
            assertEquals("2", saved.addons.first { it.id == "good" }.version)
            assertEquals(1L, repository.getAddonMetadataCache()["https://slow.example/p/family"]?.updatedAt)
            assertEquals("vod:recent?type=movie", repository.getAddonMetadataCache()["https://good.example/p/family"]
                ?.manifest?.catalogs?.single()?.clientDelegate)
            assertNull(repository.refreshInstalledAddonsIfNeeded())
        } finally {
            val field = StreamRepository::class.java.getDeclaredField("repositoryScope").apply { isAccessible = true }
            (field.get(repository) as CoroutineScope).cancel()
        }
    }

    @Test fun delegatedVodRowRendersAndRespondsToRemoteOk() = runBlocking {
        val streams = mockk<StreamRepository>(relaxed = true)
        every { streams.installedAddons } returns MutableStateFlow(emptyList())
        val iptv = mockk<IptvRepository>(relaxed = true)
        coEvery { iptv.getDelegatedVodCatalogSeeds(MediaType.MOVIE, null, any()) } returns listOf(
            IptvRepository.VodCatalogSeed(MediaType.MOVIE, 888001, null, "Family VOD Movie", 2025, "1", 100))
        val media = spyk(MediaRepository(context, mockk(), mockk(), mockk(), mockk(), streams,
            mockk(), iptvRepository = iptv))
        coEvery { media.getMovieDetails(888001) } returns MediaItem(888001, "Family VOD Movie", year = "2025")
        val page = media.loadCustomCatalogPage(CatalogConfig("test-vod", "Recent VOD", CatalogSourceType.ADDON,
            addonId = "family", addonCatalogId = "vod", addonCatalogType = "movie",
            clientDelegate = "vod:recent?type=movie"), 0, 20)
        assertEquals(listOf(888001), page.items.map { it.id })
        val selected = AtomicInteger()
        val focus = FocusRequester()
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                ArflixTvTheme {
                    Column(Modifier.padding(40.dp)) {
                        MediaCard(page.items.single(), modifier = Modifier.testTag("vod-card").focusRequester(focus),
                            artworkPainter = ColorPainter(Color.DarkGray),
                            onClick = { selected.set(page.items.single().id) })
                    }
                    LaunchedEffect(Unit) { withFrameNanos { }; focus.requestFocus() }
                }
            }
        }
        compose.onNodeWithText("Family VOD Movie").assertIsDisplayed()
        val focusedArtwork = isFocused() and hasAnyAncestor(hasTestTag("vod-card"))
        compose.waitUntil(10_000) {
            compose.onAllNodes(focusedArtwork, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
        }
        InstrumentationRegistry.getInstrumentation().uiAutomation.takeScreenshot()?.let { screenshot ->
            java.io.File(context.cacheDir, "addon-vod-card.png").outputStream().use {
                screenshot.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
            }
            screenshot.recycle()
        }
        compose.onNode(focusedArtwork, useUnmergedTree = true).assertIsFocused()
            .performKeyInput { pressKey(Key.DirectionCenter) }
        compose.runOnIdle { assertEquals(888001, selected.get()) }
    }
}
