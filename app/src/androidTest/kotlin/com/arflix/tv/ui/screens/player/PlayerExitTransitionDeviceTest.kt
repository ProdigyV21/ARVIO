package com.arflix.tv.ui.screens.player

import android.content.Context
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlayerExitTransitionDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun mobilePausesImmediatelyButLeavesOnlyAfterOpaqueFade() {
        lateinit var exit: PlayerExitTransition
        val events = mutableListOf<String>()
        var underlyingClicks = 0
        var underlyingBacks = 0
        compose.setContent {
            exit = rememberPlayerExitTransition(true, { events += "pause" }, {
                assertEquals(1f, exit.alpha, 0f)
                events += "restore orientation"
                events += "back"
            })
            BackHandler { underlyingBacks++ }
            Box(Modifier.fillMaxSize().background(Color.Red)) {
                Button(onClick = { underlyingClicks++ }) { Text("Underlying control") }
                PlayerExitScrim(exit)
            }
        }
        compose.mainClock.autoAdvance = false
        compose.runOnIdle {
            exit.requestExit()
            exit.requestExit()
            assertEquals(listOf("pause"), events)
        }
        compose.mainClock.advanceTimeBy(96)
        compose.runOnIdle {
            assertTrue(exit.alpha > 0f && exit.alpha < 1f)
            assertEquals(listOf("pause"), events)
            compose.activity.onBackPressedDispatcher.onBackPressed()
        }
        compose.onNodeWithText("Underlying control").performTouchInput { click() }
        compose.mainClock.advanceTimeBy(300)
        compose.runOnIdle {
            assertEquals(listOf("pause", "restore orientation", "back"), events)
            assertEquals(0, underlyingClicks)
            assertEquals(0, underlyingBacks)
        }
        assertOpaqueBlack()
    }

    @Test fun exitUsesLatestNavigationCallback() {
        lateinit var exit: PlayerExitTransition
        val version = mutableStateOf(0)
        val calledVersions = mutableListOf<Int>()
        compose.setContent {
            val currentVersion = version.value
            exit = rememberPlayerExitTransition(true, {}, { calledVersions += currentVersion })
            PlayerExitScrim(exit)
        }
        compose.runOnIdle { version.value = 1 }
        compose.runOnIdle { exit.requestExit() }
        compose.waitUntil(3_000) { calledVersions.isNotEmpty() }
        compose.runOnIdle { assertEquals(listOf(1), calledVersions) }
    }

    @Test fun removingPlayerDuringFadeCannotPopAnotherScreen() {
        lateinit var exit: PlayerExitTransition
        val visible = mutableStateOf(true)
        var exits = 0
        compose.setContent {
            if (visible.value) {
                exit = rememberPlayerExitTransition(true, {}, { exits++ })
                PlayerExitScrim(exit)
            }
        }
        compose.mainClock.autoAdvance = false
        compose.runOnIdle { exit.requestExit() }
        compose.mainClock.advanceTimeBy(32)
        compose.runOnIdle { visible.value = false }
        compose.mainClock.advanceTimeBy(400)
        compose.runOnIdle { assertEquals(0, exits) }
    }

    @Test fun tvExitHasNoExtraAnimationDelay() {
        lateinit var exit: PlayerExitTransition
        val events = mutableListOf<String>()
        compose.setContent {
            exit = rememberPlayerExitTransition(false, { events += "pause" }, { events += "back" })
            PlayerExitScrim(exit)
        }
        compose.runOnIdle {
            exit.requestExit()
            assertEquals(listOf("pause", "back"), events)
            assertEquals(1f, exit.alpha, 0f)
        }
    }

    @androidx.annotation.OptIn(UnstableApi::class)
    @Test fun actualVideoPausesBeforeExitAndIsCoveredByScrim() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val file = File(context.cacheDir, "pr652-exit-test.mp4")
        InstrumentationRegistry.getInstrumentation().context.assets.open("seek_preview_device_test.mp4").use { input ->
            file.outputStream().use(input::copyTo)
        }
        lateinit var player: ExoPlayer
        lateinit var exit: PlayerExitTransition
        val firstFrame = AtomicBoolean(false)
        var exits = 0
        compose.runOnIdle {
            player = ExoPlayer.Builder(context).build()
            player.addListener(object : Player.Listener {
                override fun onRenderedFirstFrame() { firstFrame.set(true) }
            })
        }
        try {
            compose.setContent {
                exit = rememberPlayerExitTransition(true, { player.pause() }, { exits++ })
                Box(Modifier.fillMaxSize()) {
                    AndroidView(
                        factory = { PlayerView(it).apply { this.player = player; useController = false } },
                        modifier = Modifier.fillMaxSize()
                    )
                    PlayerExitScrim(exit)
                }
            }
            compose.runOnIdle {
                player.setMediaItem(MediaItem.fromUri(Uri.fromFile(file)))
                player.prepare()
                player.play()
            }
            compose.waitUntil(15_000) { firstFrame.get() }
            compose.mainClock.autoAdvance = false
            compose.runOnIdle {
                exit.requestExit()
                assertFalse(player.playWhenReady)
                assertEquals(0, exits)
            }
            compose.mainClock.advanceTimeBy(400)
            compose.runOnIdle {
                assertFalse(player.playWhenReady)
                assertEquals(1, exits)
            }
            assertOpaqueBlack()
        } finally {
            compose.runOnIdle { player.release() }
            file.delete()
        }
    }

    private fun assertOpaqueBlack() {
        val bitmap = compose.onRoot().captureToImage().asAndroidBitmap()
        assertEquals(android.graphics.Color.BLACK, bitmap.getPixel(bitmap.width / 2, bitmap.height / 2))
    }
}
