package com.arflix.tv.ui.screens.tv.live

import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.media3.exoplayer.ExoPlayer
import androidx.test.core.app.ApplicationProvider
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

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
}
