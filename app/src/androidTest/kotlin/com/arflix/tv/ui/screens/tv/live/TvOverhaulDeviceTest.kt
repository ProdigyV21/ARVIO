@file:Suppress("UnsafeOptInUsageError")
package com.arflix.tv.ui.screens.tv.live

import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.*
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.ui.components.SidebarItem
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.io.File

@OptIn(ExperimentalTestApi::class)
class TvOverhaulDeviceTest {
    @get:Rule val compose = createComposeRule()
    private val now = System.currentTimeMillis()
    private val names = listOf("Sky Sports Main Event UHD", "National Geographic Wild HD", "NPO 3", "RTL 4", "SBS 6", "ESPN", "Ziggo Sport", "Discovery")
    private val channels = (0 until 144).map { i -> IptvChannel("fixture:$i", names[i % names.size],
        "https://example.invalid/fixture", "Football", qualityLabel = "HD").enrichForFastStartup(i + 1) }
    private val metadata = com.google.gson.Gson().fromJson(
        InstrumentationRegistry.getInstrumentation().context.assets.open("sports-artwork.json").bufferedReader().use { it.readText() },
        com.arflix.tv.data.api.StremioCatalogResponse::class.java).metas.orEmpty()
    private val programs = metadata.map { it.name!! }
    private val guide = channels.associate { channel ->
        val index = channel.number - 1
        val entries = (0..10).map { slot -> IptvProgram(
            title = programs[(index + slot) % programs.size],
            description = metadata[(index + slot) % programs.size].genres.orEmpty().joinToString(" ") + ". Controlled emulator fixture.",
            startUtcMillis = now - 15 * 60_000 + slot * 90 * 60_000,
            endUtcMillis = now - 15 * 60_000 + (slot + 1) * 90 * 60_000)
        }
        channel.id to IptvNowNext(now = entries[0], next = entries[1], upcoming = entries.drop(1))
    }
    private fun screenshot(name: String) {
        compose.waitForIdle()
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        // Compose's test clock does not wait for SurfaceFlinger/window animations.
        instrumentation.waitForIdleSync()
        android.os.SystemClock.sleep(600)
        var bitmap: Bitmap? = null
        val deadline = android.os.SystemClock.uptimeMillis() + 5_000
        while (bitmap == null && android.os.SystemClock.uptimeMillis() < deadline) {
            val candidate = instrumentation.uiAutomation.takeScreenshot()
            val samples = (0 until candidate.width step 16).flatMap { x ->
                (0 until candidate.height step 16).map { y -> candidate.getPixel(x, y) }
            }
            val visible = samples.count { android.graphics.Color.red(it) + android.graphics.Color.green(it) + android.graphics.Color.blue(it) > 100 }
            if (visible > samples.size / 12) bitmap = candidate else {
                candidate.recycle()
                android.os.SystemClock.sleep(300)
            }
        }
        assertNotNull("Screenshot $name must contain rendered content, not a black window", bitmap)
        val rendered = requireNotNull(bitmap)
        val folder = File(instrumentation.targetContext.getExternalFilesDir(null), "tv-overhaul").apply { mkdirs() }
        File(folder, "$name.png").outputStream().use { rendered.compress(Bitmap.CompressFormat.PNG, 100, it) }
        rendered.recycle()
    }

    @Test fun captureFiveStatesAndVerifyPickerAndDrawer() {
        val expanded = mutableStateOf(true)
        val sports = mutableStateOf(false)
        val signal = mutableIntStateOf(0)
        var played: String? = null
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        lateinit var player: ExoPlayer
        compose.runOnUiThread { player = ExoPlayer.Builder(context).build() }
        val tree = buildCategoryTree(channels, favoritesCount = 8, recentCount = 21)
            .withSportsDestination()
        val events = attachSportsArtwork(buildSportsGuideEvents(channels.map { it.source }, guide, now), metadata.mapNotNull { it.toSportsEventArtwork() })
        val firstEvent = sportsGuideRows(events, now).first().events.first()
        try {
            compose.setContent {
                Box(Modifier.fillMaxSize()) {
                    Box(Modifier.fillMaxSize().padding(top = AppTopBarContentTopInset)) {
                        LiveDrawerWorkspace(expanded.value, contentKey = if (sports.value) "sports" else "guide",
                            sidebarWidth = LiveDims.SidebarExpanded, sidebar = {
                            CategorySidebar(tree = tree, selectedId = if (sports.value) SPORTS_GUIDE_CATEGORY else "all",
                                expanded = expanded.value, fixedViewport = true, listState = rememberLazyListState(),
                                providers = listOf(TvProviderFilter("all", "All playlists", 55000)),
                                sidebarWidth = LiveDims.SidebarExpanded,
                                onSelect = { sports.value = it == SPORTS_GUIDE_CATEGORY }, onOpenSearch = {},
                                onMoveRight = { expanded.value = false; signal.intValue++ })
                        }, content = {
                            if (sports.value) SportsGuidePane(events, now, false, signal.intValue,
                                onContentFocused = { expanded.value = false }, onOpenCategories = { expanded.value = true },
                                onPlay = { played = it.id }, sidebarOpen = expanded.value)
                            else Column {
                                MiniPlayerRow(player, channels[0], now, guide[channels[0].id], emptySet(), {},
                                    onFullscreenClick = {}, onProgrammeGuideClick = {}, playerActive = false)
                                EpgGrid(channels, clockTickMillis = now, nowNext = guide, selectedChannelId = channels[0].id,
                                    focusSelectedChannelSignal = 0,
                                    totalChannelCount = 55_000, favorites = emptySet(), onChannelSelect = {},
                                    channelColumnWidthOverride = LiveDims.EpgChannelWideColWidth,
                                    onMoveLeftFromChannels = { expanded.value = true }, modifier = Modifier.weight(1f))
                            }
                        })
                    }
                    AppTopBar(SidebarItem.TV, false, 0, profile = Profile(id = "fixture", name = "Test profile", avatarColor = 0xFF367B72))
                }
            }
            screenshot("01-guide-open")
            compose.onNodeWithText("Recently Watched").assertIsDisplayed()
            val layouts = mutableListOf<androidx.compose.ui.text.TextLayoutResult>()
            compose.onNodeWithText("Recently Watched", useUnmergedTree = true)
                .performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.GetTextLayoutResult) { it(layouts) }
            assertTrue("Recently Watched must fit without ellipsis", layouts.isNotEmpty() && layouts.none { it.hasVisualOverflow })
            compose.onNodeWithText("Watch live").assertDoesNotExist()
            compose.onNodeWithText("ON AIR").assertDoesNotExist()
            compose.runOnIdle { expanded.value = false }
            screenshot("02-guide-closed")
            compose.runOnIdle { sports.value = true; expanded.value = true }
            compose.waitUntil(30_000) { compose.onAllNodesWithTag("sports-artwork-loaded", useUnmergedTree = true).fetchSemanticsNodes().size >= 6 }
            screenshot("03-sports-open")
            compose.runOnIdle { expanded.value = false; signal.intValue++ }
            screenshot("04-sports-closed")
            val cards = compose.onAllNodesWithTag("sports-event-card", useUnmergedTree = true).fetchSemanticsNodes().take(4)
            assertEquals(4, cards.size)
            val art = compose.onAllNodesWithTag("sports-event-art", useUnmergedTree = true).fetchSemanticsNodes().first().boundsInRoot
            assertTrue("Match artwork must use 16:9 without a panoramic frame", kotlin.math.abs(art.width / art.height - 16f / 9f) < .02f)
            assertTrue("Four complete cards must fit without clipping the last one: ${cards.map { it.boundsInRoot }}",
                cards.all { kotlin.math.abs(it.boundsInRoot.width - cards.first().boundsInRoot.width) < 2f })
            compose.onAllNodesWithText(firstEvent.title).onFirst().assertIsDisplayed().assertIsFocused()
            compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
            compose.onNodeWithText("Channels", substring = false).assertIsDisplayed()
            compose.onAllNodesWithText(firstEvent.channels.first().name).onFirst().assertIsFocused()
            screenshot("05-event-picker")
            compose.onAllNodesWithText(firstEvent.channels.first().name).onFirst().performClick()
            compose.runOnIdle { assertNotNull("First source click must invoke playback", played) }
            compose.onNodeWithText("Channels", substring = false).assertDoesNotExist()
            compose.onRoot().performKeyInput { pressKey(Key.DirectionLeft) }
            compose.runOnIdle { assertTrue("Left at first card must reopen categories", expanded.value) }
            compose.onNodeWithContentDescription("Filter upcoming events").assertDoesNotExist()
            compose.onNodeWithText("Today & tomorrow").assertDoesNotExist()
        } finally {
            compose.runOnUiThread { player.release() }
        }
    }

    @Test fun sportsWithoutEventArtworkNeverUseGenericPhotography() {
        val events = GuideSport.entries.mapIndexed { index, sport ->
            SportsGuideEvent("fallback:$index", "${sport.title} event", sport,
                IptvProgram("${sport.title} event", startUtcMillis = now - 60_000, endUtcMillis = now + 60_000),
                listOf(channels[index].source), artwork = if (index == 0) "https://example.invalid/missing.webp" else null)
        }
        compose.setContent {
            SportsGuidePane(events, now, false, 0, {}, {}, {}, sidebarOpen = false)
        }
        compose.waitUntil(15_000) {
            compose.onAllNodesWithTag("sports-event-card", useUnmergedTree = true).fetchSemanticsNodes().isEmpty()
        }
        compose.onNodeWithText("No sports events matched to your channels").assertIsDisplayed()
        compose.onAllNodesWithTag("sports-artwork-fallback-loaded", useUnmergedTree = true).assertCountEquals(0)
        compose.onAllNodesWithTag("sports-artwork-loaded", useUnmergedTree = true).assertCountEquals(0)
    }

    @Test fun upcomingOnlyRowReceivesFocusAfterSidebarCloses() {
        val open = mutableStateOf(true)
        val signal = mutableIntStateOf(0)
        val art = metadata.firstNotNullOf { it.toSportsEventArtwork() }
        val event = SportsGuideEvent("upcoming", art.title, GuideSport.FOOTBALL,
            IptvProgram(art.title, startUtcMillis = now + 3_600_000, endUtcMillis = now + 7_200_000),
            listOf(channels.first().source), artwork = art.background)
        compose.setContent { SportsGuidePane(listOf(event), now, false, signal.intValue, {}, {}, {}, sidebarOpen = open.value) }
        compose.waitForIdle()
        compose.runOnIdle { open.value = false; signal.intValue++ }
        compose.waitUntil(5_000) {
            compose.onAllNodes(hasTestTag("sports-event-card") and isFocused(), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
        }
        compose.onAllNodesWithTag("sports-event-card", useUnmergedTree = true)[0].performKeyInput { pressKey(Key.DirectionCenter) }
        compose.onNodeWithText("Channels").assertIsDisplayed()
    }
}
