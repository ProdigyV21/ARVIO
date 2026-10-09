package com.arflix.tv.ui.components

import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.ui.screens.home.HomeFocusState
import com.arflix.tv.ui.screens.home.HomeInputLayer
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.io.File
import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.atomic.AtomicInteger
import org.junit.Assert.*
import org.junit.Test

class HomeTrailerPreviewDeviceTest {
    @Test fun playerPreparesBeforeTheFocusDeadlineWithoutStartingEarly() {
        val cuedAt = java.util.concurrent.atomic.AtomicLong(0L)
        val playedAt = java.util.concurrent.atomic.AtomicLong(0L)
        val video = InstrumentationRegistry.getArguments().getString("trailerVideo") ?: "M7lc1UVf-VE"
        ActivityScenario.launch(ComponentActivity::class.java).use { scenario ->
            val deadline = android.os.SystemClock.elapsedRealtime() + 15_000L
            scenario.onActivity { activity -> activity.setContent {
                HomeTrailerPreview(video, Modifier.size(360.dp, 202.5.dp), autoplayNotBeforeMs = deadline,
                    onStateChange = {
                        if (it == PlayerState.VIDEO_CUED) cuedAt.compareAndSet(0L, android.os.SystemClock.elapsedRealtime())
                        if (it == PlayerState.PLAYING) playedAt.compareAndSet(0L, android.os.SystemClock.elapsedRealtime())
                    }) { Box(Modifier.size(360.dp, 202.5.dp).background(Color.DarkGray)) }
            } }
            await("Player did not prepare during the focus delay", 12_000) { cuedAt.get() > 0L }
            assertTrue("Preparation waited for the autoplay deadline", cuedAt.get() < deadline)
            assertEquals("Prepared trailer played too early", 0L, playedAt.get())
            var preparedView: YouTubePlayerView? = null
            scenario.onActivity {
                preparedView = findPlayer(it.window.decorView)
                assertEquals(View.INVISIBLE, preparedView!!.visibility)
            }
            await("Prepared trailer did not autoplay", 40_000) { playedAt.get() > 0L }
            assertTrue("Autoplay ignored the focus deadline", playedAt.get() >= deadline)
            scenario.onActivity {
                assertSame("Autoplay recreated the prepared player", preparedView, findPlayer(it.window.decorView))
                assertEquals(View.VISIBLE, preparedView!!.visibility)
                assertEquals(1, webViews(it.window.decorView))
            }
        }
    }

    @Test fun endedTrailerRestoresArtworkWithoutLooping() {
        val state = AtomicReference<PlayerState>()
        val ended = java.util.concurrent.atomic.AtomicBoolean(false)
        val videoDuration = AtomicReference(0f)
        val player = AtomicReference<YouTubePlayer>()
        val video = InstrumentationRegistry.getArguments().getString("trailerVideo") ?: "M7lc1UVf-VE"
        ActivityScenario.launch(ComponentActivity::class.java).use { scenario ->
            scenario.onActivity { activity -> activity.setContent {
                HomeTrailerPreview(video, Modifier.size(360.dp, 202.5.dp), onStateChange = {
                    state.set(it)
                    if (it == PlayerState.ENDED) ended.set(true)
                }) { Box(Modifier.size(360.dp, 202.5.dp).background(Color.DarkGray)) }
            } }
            await("Preview did not mount") { count(scenario) == 1 }
            scenario.onActivity { findPlayer(it.window.decorView)!!.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
                    player.set(youTubePlayer)
                    videoDuration.set(duration)
                }
            }) }
            await("Trailer did not start with its duration", 60_000) { state.get() == PlayerState.PLAYING && videoDuration.get() > 0f }
            scenario.onActivity { player.get().seekTo(videoDuration.get() - 0.1f) }
            await("Ended trailer did not return to artwork", 20_000) { ended.get() && count(scenario) == 0 }
            Thread.sleep(1500)
            assertEquals("Ended preview restarted itself", 0, count(scenario))
        }
    }

    @Test fun playingCardKeepsRemoteNavigationAndSinglePressSelection() {
        val focus = HomeFocusState().apply { userHasNavigated = true }
        val opened = AtomicInteger(-1)
        val state = AtomicReference<PlayerState>()
        val video = InstrumentationRegistry.getArguments().getString("trailerVideo") ?: "M7lc1UVf-VE"
        ActivityScenario.launch(ComponentActivity::class.java).use { scenario ->
            scenario.onActivity { activity -> activity.setContent {
                CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                    HomeInputLayer(categories = listOf(Category("trending_movies", "Movies", (1..10).map { MediaItem(it, "Title $it") })),
                        cardLogoUrls = emptyMap(), focusState = focus, limitRowsDuringStartup = false,
                        suppressSelectUntilMs = 0L, contentStartPadding = 24.dp, fastScrollThresholdMs = 80L,
                        usePosterCards = false, isContextMenuOpen = false, currentProfile = null,
                        onNavigateToDetails = { _, id, _, _ -> opened.set(id) }, onNavigateToCollection = {},
                        onNavigateToSearch = {}, onNavigateToWatchlist = {}, onNavigateToTv = { _, _ -> },
                        onNavigateToSettings = {}, onSwitchProfile = {}, onExitApp = {}, onOpenContextMenu = { _, _ -> },
                        featuredTrailerKey = video, featuredTrailerDelayMs = 500L)
                }
            } }
            val first = observePlayer(scenario, state)
            await("Focused card did not play", 60_000) { state.get() == PlayerState.PLAYING }
            scenario.onActivity { assertEquals(View.VISIBLE, first.visibility); assertEquals(1, webViews(it.window.decorView)) }
            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            device.pressDPadRight()
            await("Playing trailer stole D-pad navigation") { focus.currentItemIndex == 1 }
            state.set(null)
            val next = observePlayer(scenario, state, first)
            await("Next focused card did not play", 60_000) { state.get() == PlayerState.PLAYING }
            scenario.onActivity { assertEquals(View.VISIBLE, next.visibility); assertEquals(1, webViews(it.window.decorView)) }
            device.pressDPadCenter()
            await("Select needed more than one press") { opened.get() == 2 }
        }
    }

    @Test fun rendererFailureFallsBackWithoutClosingTheHost() {
        val video = InstrumentationRegistry.getArguments().getString("trailerVideo") ?: "M7lc1UVf-VE"
        val state = AtomicReference<PlayerState>()
        ActivityScenario.launch(ComponentActivity::class.java).use { scenario ->
            scenario.onActivity { activity -> activity.setContent {
                HomeTrailerPreview(video, Modifier.size(360.dp, 202.5.dp), onStateChange = state::set) {
                    Box(Modifier.size(360.dp, 202.5.dp).background(Color.DarkGray))
                }
            } }
            // Start playback first: a startup timeout must not satisfy recovery.
            await("Preview did not start before the crash test", 60_000) { state.get() == PlayerState.PLAYING }
            scenario.onActivity { findWebView(it.window.decorView)!!.loadUrl("chrome://crash") }
            // Android may finish its crash dump before delivering the callback.
            await("Dead renderer was retained", 60_000) { count(scenario) == 0 }
            scenario.onActivity { assertFalse(it.isFinishing); assertFalse(it.isDestroyed) }
        }
    }

    @Test fun inlineAutoplayReleasesOnFocusLossAndBackground() {
        val state = AtomicReference<PlayerState>()
        val enabled = mutableStateOf(true)
        val sawCue = java.util.concurrent.atomic.AtomicBoolean(false)
        val video = InstrumentationRegistry.getArguments().getString("trailerVideo") ?: "M7lc1UVf-VE"
        ActivityScenario.launch(ComponentActivity::class.java).use { scenario ->
            lateinit var host: ComponentActivity
            scenario.onActivity { activity ->
                host = activity
                activity.setContent {
                    HomeTrailerPreview(video, Modifier.size(360.dp, 202.5.dp), delayMs = 500,
                        enabled = enabled.value, onStateChange = { android.util.Log.i("HomeTrailerDevice", "Inline state: $it"); if (it == PlayerState.VIDEO_CUED) sawCue.set(true); state.set(it) }) {
                        Box(Modifier.size(360.dp, 202.5.dp).background(Color.DarkGray))
                    }
                }
            }
            await("Inline trailer did not play", 60_000) { state.get() == PlayerState.PLAYING }
            assertTrue("Playback started without first preparing its thumbnail", sawCue.get())
            scenario.onActivity { assertEquals(1, webViews(it.window.decorView)); assertEquals(View.VISIBLE, findPlayer(it.window.decorView)!!.visibility) }
            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            val output = File(host.getExternalFilesDir(null), "trailer-test").apply { mkdirs() }
            device.takeScreenshot(File(output, "home-inline-playing.png"))
            scenario.moveToState(Lifecycle.State.CREATED)
            await("Background preview retained its WebView") { count(scenario) == 0 }
            state.set(null)
            scenario.moveToState(Lifecycle.State.RESUMED)
            await("Returning did not restore the visible preview", 60_000) { state.get() == PlayerState.PLAYING }
            scenario.onActivity { enabled.value = false }
            await("Focus loss retained a playing WebView") { count(scenario) == 0 }
            scenario.onActivity { assertFalse(it.isFinishing) }
            device.takeScreenshot(File(output, "home-inline-released.png"))
        }
    }

    private fun count(scenario: ActivityScenario<ComponentActivity>): Int {
        var result = -1
        scenario.onActivity { result = webViews(it.window.decorView) }
        return result
    }
    private fun webViews(view: View): Int = if (view is WebView) 1 else
        if (view is ViewGroup) (0 until view.childCount).sumOf { webViews(view.getChildAt(it)) } else 0
    private fun findWebView(view: View): WebView? = if (view is WebView) view else
        if (view is ViewGroup) (0 until view.childCount).firstNotNullOfOrNull { findWebView(view.getChildAt(it)) } else null
    private fun findPlayer(view: View): YouTubePlayerView? = if (view is YouTubePlayerView) view else
        if (view is ViewGroup) (0 until view.childCount).firstNotNullOfOrNull { findPlayer(view.getChildAt(it)) } else null
    private fun observePlayer(scenario: ActivityScenario<ComponentActivity>, playbackState: AtomicReference<PlayerState>, previous: YouTubePlayerView? = null): YouTubePlayerView {
        var result: YouTubePlayerView? = null
        await("Focused preview was not mounted") {
            scenario.onActivity { result = findPlayer(it.window.decorView) }
            result != null && result !== previous
        }
        scenario.onActivity {
            result!!.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerState) { playbackState.set(state) }
                override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) { if (second > 0f) playbackState.set(PlayerState.PLAYING) }
            })
        }
        return result!!
    }
    private fun await(message: String, timeoutMs: Long = 10_000, predicate: () -> Boolean) {
        val deadline = System.currentTimeMillis() + timeoutMs
        while (!predicate() && System.currentTimeMillis() < deadline) Thread.sleep(100)
        assertTrue(message, predicate())
    }
}
