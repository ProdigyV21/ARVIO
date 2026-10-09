package com.arflix.tv.ui.screens.tv.live

import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.MediaItem
import androidx.test.core.app.ApplicationProvider
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.ui.theme.ArvioTvTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import android.graphics.Bitmap
import androidx.test.platform.app.InstrumentationRegistry
import java.io.File

class MiniPlayerFocusDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun programmeFocusUpdatesHeaderWithoutRecomposingParent() {
        val channel = IptvChannel("header:1", "Channel one", "https://example.invalid/not-played", "News")
            .enrichForFastStartup(1)
        val selected = mutableStateOf<Pair<EnrichedChannel, IptvProgram>?>(null)
        val parentCompositions = AtomicInteger()
        lateinit var player: ExoPlayer
        compose.runOnUiThread { player = ExoPlayer.Builder(ApplicationProvider.getApplicationContext()).build() }
        try {
            compose.setContent {
                SideEffect { parentCompositions.incrementAndGet() }
                MiniPlayerRow(
                    exoPlayer = player, channel = channel, clockTickMillis = 60000L,
                    nowNext = null, favoriteSet = emptySet(), onFavoriteToggle = {},
                    playerActive = false, focusedProgrammeProvider = { selected.value },
                )
            }
            compose.waitForIdle()
            val initialCompositions = parentCompositions.get()
            compose.runOnIdle {
                selected.value = channel to IptvProgram("Focused programme", startUtcMillis = 0, endUtcMillis = 3600000)
            }
            compose.onNodeWithText("Focused programme").assertIsDisplayed()
            compose.runOnIdle { assertEquals(initialCompositions, parentCompositions.get()) }
        } finally {
            compose.runOnUiThread { player.release() }
        }
    }

    @Test fun noGuidePreviewShowsFullFocusedChannelNameWithoutChangingPlayback() {
        val playing = IptvChannel("playing", "Playing channel", "https://example.invalid/not-played", "News")
            .enrichForFastStartup(1)
        val fullName = "13:45 Netherlands vs Germany | UEFA Nations League [ESPN] | Dutch commentary | Full event coverage"
        val focused = IptvChannel("preview", fullName, "https://example.invalid/not-played", "Sports")
            .enrichForFastStartup(2)
        val preview = mutableStateOf<Pair<EnrichedChannel, IptvNowNext?>?>(focused to null)
        val focusedProgramme = mutableStateOf<Pair<EnrichedChannel, IptvProgram>?>(null)
        val playingGuide = IptvNowNext(now = IptvProgram("Playing programme", startUtcMillis = 0, endUtcMillis = 3600000))
        val parentCompositions = AtomicInteger()
        lateinit var player: ExoPlayer
        compose.runOnUiThread {
            player = ExoPlayer.Builder(ApplicationProvider.getApplicationContext()).build()
            player.setMediaItem(MediaItem.fromUri(playing.source.streamUrl))
        }
        try {
            compose.setContent {
                SideEffect { parentCompositions.incrementAndGet() }
                ArvioTvTheme {
                    Column(Modifier.width(700.dp).fillMaxSize().background(LiveColors.Bg).padding(16.dp)) {
                        MiniPlayerRow(
                            exoPlayer = player, channel = playing, clockTickMillis = 60000L,
                            nowNext = playingGuide, favoriteSet = emptySet(), onFavoriteToggle = {},
                            playerActive = false, focusedProgrammeProvider = { focusedProgramme.value },
                            focusedChannelProvider = { preview.value },
                        )
                        EpgGrid(
                            channels = listOf(playing, focused), clockTickMillis = 60000L,
                            nowNext = mapOf(playing.id to playingGuide),
                            selectedChannelId = focused.id, focusSelectedChannelSignal = 0,
                            favorites = emptySet(), onChannelSelect = {},
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
            compose.waitForIdle()
            val initialCompositions = parentCompositions.get()
            assertFullTitle(fullName)
            assertGuideVisible(focused.id)
            screenshot("no-epg-channel-preview")
            compose.runOnIdle {
                preview.value = focused to IptvNowNext(now = IptvProgram("Focused channel programme", startUtcMillis = 0, endUtcMillis = 3600000))
            }
            compose.onNodeWithText("Focused channel programme").assertIsDisplayed()
            compose.runOnIdle {
                focusedProgramme.value = focused to IptvProgram("Pre-match coverage", startUtcMillis = 0, endUtcMillis = 3600000)
            }
            compose.onNodeWithText("Pre-match coverage").assertIsDisplayed()
            compose.runOnIdle {
                focusedProgramme.value = null
                preview.value = focused to IptvNowNext(now = IptvProgram(" \t ", startUtcMillis = 0, endUtcMillis = 3600000))
            }
            assertFullTitle(fullName)
            assertGuideVisible(focused.id)
            compose.runOnIdle { preview.value = null }
            assertFullTitle("Playing programme")
            compose.runOnIdle {
                assertEquals("Preview changes must stay local to the mini-player", initialCompositions, parentCompositions.get())
                assertEquals("Preview changes must preserve the playing source", 1, player.mediaItemCount)
                assertEquals(playing.source.streamUrl, player.currentMediaItem?.localConfiguration?.uri.toString())
            }
        } finally {
            compose.runOnUiThread { player.release() }
        }
    }

    @Test fun noGuideTitleWrapsInPhonePreviewLayouts() {
        val name = "13:45 Netherlands vs Germany | UEFA Nations League [ESPN] | Dutch commentary | Full event coverage"
        val channel = IptvChannel("phone", name, "https://example.invalid/not-played", "Sports").enrichForFastStartup(1)
        val landscape = mutableStateOf(false)
        lateinit var player: ExoPlayer
        compose.runOnUiThread { player = ExoPlayer.Builder(ApplicationProvider.getApplicationContext()).build() }
        try {
            compose.setContent {
                ArvioTvTheme {
                    Box(Modifier.size(if (landscape.value) 760.dp else 390.dp, if (landscape.value) 300.dp else 500.dp).background(LiveColors.Bg)) {
                        MiniPlayerRow(
                            exoPlayer = player, channel = channel, clockTickMillis = 60000L,
                            nowNext = null, favoriteSet = emptySet(), onFavoriteToggle = {},
                            playerActive = false, compact = true, landscapeCompact = landscape.value,
                        )
                    }
                }
            }
            assertFullTitle(name)
            screenshot("no-epg-phone-portrait-preview")
            compose.runOnIdle { landscape.value = true }
            assertFullTitle(name)
            screenshot("no-epg-phone-landscape-preview")
        } finally {
            compose.runOnUiThread { player.release() }
        }
    }

    private fun assertGuideVisible(channelId: String) {
        val title = compose.onNodeWithTag("live-programme-title", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        val row = compose.onNodeWithTag("iptv-channel:$channelId").assertIsDisplayed().fetchSemanticsNode().boundsInRoot
        assertTrue("The channel guide must remain below the complete fallback title", row.top > title.bottom)
        assertTrue("The guide row must retain usable height", row.height > 30f)
    }

    private fun assertFullTitle(title: String) {
        val node = compose.onNodeWithTag("live-programme-title", useUnmergedTree = true)
        node.assertIsDisplayed()
        val layouts = mutableListOf<TextLayoutResult>()
        node.performSemanticsAction(SemanticsActions.GetTextLayoutResult) { it(layouts) }
        assertTrue(layouts.isNotEmpty())
        assertEquals(title, layouts.single().layoutInput.text.text)
        assertFalse("The fallback must wrap without clipping or ellipsis", layouts.single().hasVisualOverflow)
    }

    private fun screenshot(name: String) {
        compose.waitForIdle()
        // Let the emulator's native video surface present its first frame.
        // Compose layout can settle before the GPU buffer is ready to capture.
        Thread.sleep(500)
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val folder = File(instrumentation.targetContext.getExternalFilesDir(null), "issue-768").apply { mkdirs() }
        val bitmap = compose.onRoot().captureToImage().asAndroidBitmap()
        try {
            File(folder, "$name.png").outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        } finally {
            bitmap.recycle()
        }
    }
}
