package com.arflix.tv.ui.screens.player.preview

import android.graphics.Bitmap
import android.net.Uri
import android.os.SystemClock
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.MainActivity
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.screens.player.PlayerScreen
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import java.io.File
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Runs the production PlayerScreen; only its media input is a deterministic three-scene clip. */
@androidx.annotation.OptIn(UnstableApi::class)
@RunWith(AndroidJUnit4::class)
class PlayerSeekPreviewVisualDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()

    @Test fun actualPlayerPreviewsOnTouchAndAutomaticallyCommitsRemoteSeeking() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val isTv = context.packageManager.hasSystemFeature("android.software.leanback")
        val file = File(context.cacheDir, "visual-preview-three-scenes.mp4")
        instrumentation.context.assets.open("seek_preview_device_test.mp4").use { input ->
            file.outputStream().use(input::copyTo)
        }
        compose.activityRule.scenario.onActivity { activity ->
            activity.setContent {
                CompositionLocalProvider(LocalDeviceType provides if (isTv) DeviceType.TV else DeviceType.PHONE) {
                    ArflixTvTheme {
                        PlayerScreen(
                            mediaType = MediaType.MOVIE,
                            mediaId = 0,
                            streamUrl = Uri.fromFile(file).toString(),
                            preferredSourceName = "Preview verification clip",
                            startPositionMs = 2_000L,
                        )
                    }
                }
            }
        }
        var player: Player? = null
        compose.waitUntil(45_000) {
            compose.activityRule.scenario.onActivity { activity ->
                player = findPlayer(activity.window.decorView)
            }
            var ready = false
            instrumentation.runOnMainSync { ready = player?.playbackState == Player.STATE_READY }
            ready
        }
        assertNotNull(player)
        compose.waitUntil(15_000) {
            var playing = false
            instrumentation.runOnMainSync { playing = player!!.isPlaying && player!!.currentPosition >= 3_000L }
            playing
        }
        compose.waitUntil(30_000) {
            compose.onAllNodes(androidx.compose.ui.test.hasTestTag("playerLoadingOverlay"))
                .fetchSemanticsNodes().isEmpty()
        }
        instrumentation.runOnMainSync {
            player!!.pause()
            player!!.seekTo(2_000L)
        }
        SystemClock.sleep(1_500)
        saveScreenshot("${if (isTv) "tv" else "phone"}-playback-before-seek")
        if (isTv) {
            // Back hides controls. The first quick step commits once; subsequent steps browse.
            instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_MEDIA_PAUSE)
            advanceUi()
            instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK)
            advanceUi()
            instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_RIGHT)
            advanceUi()
            instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_RIGHT)
            waitForCommittedPosition(player!!, 22_000L, resume = false)
            saveScreenshot("tv-auto-commit-forward-paused")
            instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_LEFT)
            advanceUi()
            instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_LEFT)
            waitForCommittedPosition(player!!, 2_000L, resume = false)
            saveScreenshot("tv-auto-commit-backward-paused")
            instrumentation.runOnMainSync {
                player!!.play()
            }
            compose.waitUntil(10_000) {
                var playing = false
                instrumentation.runOnMainSync { playing = player!!.isPlaying }
                playing
            }
            instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_RIGHT)
            advanceUi()
            instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_RIGHT)
            waitForCommittedPosition(player!!, 22_000L, resume = true)
            instrumentation.runOnMainSync { player!!.pause() }
        } else {
            val scrubber = compose.onNodeWithTag("mobile_player_scrubber")
            scrubber.performTouchInput { down(Offset(width * 0.75f, height / 2f)) }
            try {
                waitForSceneAndCapture("phone-preview-forward-blue", scene = "blue")
                scrubber.performTouchInput { moveTo(Offset(width * 0.45f, height / 2f)) }
                waitForSceneAndCapture("phone-preview-backward-green", scene = "green")
                instrumentation.runOnMainSync {
                    assertTrue("Touch browsing must leave paused playback near 2s", player!!.currentPosition < 3_000L)
                }
            } finally {
                scrubber.performTouchInput { cancel() }
            }
        }
    }

    private fun waitForCommittedPosition(player: Player, targetMs: Long, resume: Boolean) {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val started = SystemClock.elapsedRealtime()
        var committed = false
        compose.waitUntil(6_000) {
            instrumentation.runOnMainSync {
                committed = player.currentPosition in (targetMs - 500)..(targetMs + 2_500) &&
                    player.playWhenReady == resume
            }
            committed
        }
        val elapsed = SystemClock.elapsedRealtime() - started
        assertTrue("Remote seek must wait for the two-second idle period, took ${elapsed}ms", elapsed >= 1_400L)
        assertTrue("Remote seek must not wait for thumbnail extraction, took ${elapsed}ms", elapsed < 5_000L)
    }

    private fun waitForSceneAndCapture(name: String, scene: String) {
        val started = SystemClock.elapsedRealtime()
        var found = false
        while (SystemClock.elapsedRealtime() - started < 20_000L) {
            advanceUi()
            val bitmap = InstrumentationRegistry.getInstrumentation().uiAutomation.takeScreenshot()
            if (bitmap != null) {
                var scenePixels = 0
                // Expected colours deliberately differ from the paused background video.
                for (y in bitmap.height / 2 until bitmap.height step 3) {
                    for (x in 0 until bitmap.width step 3) {
                        val pixel = bitmap.getPixel(x, y)
                        val r = android.graphics.Color.red(pixel)
                        val g = android.graphics.Color.green(pixel)
                        val b = android.graphics.Color.blue(pixel)
                        val matches = when (scene) {
                            "blue" -> b > 140 && b > r + 80 && b > g + 80
                            "red" -> r > 140 && r > b + 80 && r > g + 80
                            else -> g > 80 && g > r + 50 && g > b + 50
                        }
                        if (matches) {
                            scenePixels++
                        }
                    }
                }
                found = scenePixels > 200
                bitmap.recycle()
                if (found) break
            }
            SystemClock.sleep(250)
        }
        SystemClock.sleep(200)
        saveScreenshot(name)
        assertTrue("$name must contain a decoded scene, not just a grey preview", found)
    }

    private fun saveScreenshot(name: String) {
        advanceUi()
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val bitmap = requireNotNull(instrumentation.uiAutomation.takeScreenshot())
        val output = File(instrumentation.targetContext.getExternalFilesDir(null), "preview-evidence/$name.png")
        output.parentFile!!.mkdirs()
        output.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()
    }

    private fun advanceUi() {
        compose.mainClock.advanceTimeBy(160)
        compose.waitForIdle()
    }

    private fun findPlayer(view: View): Player? {
        if (view is PlayerView) return view.player
        if (view is ViewGroup) {
            for (index in 0 until view.childCount) findPlayer(view.getChildAt(index))?.let { return it }
        }
        return null
    }
}
