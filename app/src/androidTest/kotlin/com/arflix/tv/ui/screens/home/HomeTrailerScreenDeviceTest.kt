package com.arflix.tv.ui.screens.home

import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.ViewModelProvider
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.arflix.tv.MainActivity
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.profilesDataStore
import java.io.File
import java.util.concurrent.atomic.AtomicReference
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.After
import org.junit.Test

/** Real HomeScreen + real trailer metadata, without signing in to a customer account. */
class HomeTrailerScreenDeviceTest {
    private var restoreLayout: () -> Unit = {}
    @After fun restorePreferences() { restoreLayout() }

    @Test fun homeMetadataLoadsAnInlinePlayerForTheFocusedTitle() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val poster = InstrumentationRegistry.getArguments().getString("trailerPoster") == "true"
        val suffix = if (poster) "-poster" else ""
        if (poster) {
            val profileId = runBlocking { context.profilesDataStore.data.first()[stringPreferencesKey("active_profile_id")] }.orEmpty().ifBlank { "default" }
            val layoutKey = stringPreferencesKey("profile_${profileId}_catalogue_row_layout_home:trending_movies")
            val previous = runBlocking { context.settingsDataStore.data.first()[layoutKey] }
            restoreLayout = { runBlocking { context.settingsDataStore.edit { if (previous == null) it.remove(layoutKey) else it[layoutKey] = previous } }; Unit }
            runBlocking { context.settingsDataStore.edit { it[layoutKey] = "Poster" } }
        }
        val movies = listOf(
            MediaItem(872585, "Oppenheimer", mediaType = MediaType.MOVIE,
                backdrop = "https://image.tmdb.org/t/p/w1280/fm6KqXpk3M2HVveHwCrBSSBaO0V.jpg",
                image = "https://image.tmdb.org/t/p/w500/ptpr0kGAckfQkJeJIt8st5dglvd.jpg"),
            MediaItem(157336, "Interstellar", mediaType = MediaType.MOVIE),
            MediaItem(693134, "Dune: Part Two", mediaType = MediaType.MOVIE)
        )
        val categories = listOf(Category("trending_movies", "Trending Movies", movies))
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            lateinit var vm: HomeViewModel
            lateinit var host: MainActivity
            scenario.onActivity { activity ->
                host = activity
                vm = ViewModelProvider(activity)[HomeViewModel::class.java]
                activity.setContent {
                    CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                        HomeScreen(viewModel = vm, preloadedCategories = categories, preloadedHeroItem = movies.first())
                    }
                }
            }
            await("Home did not resolve a trailer", 45_000) { !vm.uiState.value.heroTrailerKey.isNullOrBlank() }
            await("Home did not mount its inline player", 20_000) {
                var count = 0
                scenario.onActivity { count = webViews(it.window.decorView) }
                count == 1
            }
            val output = File(host.getExternalFilesDir(null), "trailer-test").apply { mkdirs() }
            capturePreparedPreview(scenario, output, "home-real-screen$suffix.png")
            val previousHeroId = vm.uiState.value.heroItem?.id
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressDPadRight()
            await("Home focus did not move to the next title", 10_000) { vm.uiState.value.heroItem?.id != previousHeroId }
            await("Next focused title did not resolve its trailer", 30_000) { !vm.uiState.value.heroTrailerKey.isNullOrBlank() }
            await("Next focused preview was not mounted", 20_000) { count(scenario) == 1 }
            capturePreparedPreview(scenario, output, "home-real-next-card$suffix.png")
            val profileId = runBlocking { host.profilesDataStore.data.first()[stringPreferencesKey("active_profile_id")] }.orEmpty().ifBlank { "default" }
            val cardsKey = booleanPreferencesKey("profile_${profileId}_trailer_in_cards")
            val previous = runBlocking { host.settingsDataStore.data.first()[cardsKey] }
            try {
                runBlocking { host.settingsDataStore.edit { it[cardsKey] = false } }
                await("Hero mode preference did not apply", 10_000) { !vm.uiState.value.trailerInCards }
                await("Hero mode did not mount one player", 20_000) {
                    var count = 0
                    scenario.onActivity { count = webViews(it.window.decorView) }
                    count == 1
                }
                scenario.onActivity { host ->
                    val player = findPlayer(host.window.decorView)!!
                    val density = host.resources.displayMetrics.density
                    assertTrue("Hero viewport below YouTube minimum after pixel rounding", player.width / density >= 200f && player.height / density >= 200f)
                }
                capturePreparedPreview(scenario, output, "home-real-hero$suffix.png")
            } finally {
                runBlocking { host.settingsDataStore.edit { if (previous == null) it.remove(cardsKey) else it[cardsKey] = previous } }
            }
        }
    }
    private fun webViews(view: View): Int = if (view is WebView) 1 else
        if (view is ViewGroup) (0 until view.childCount).sumOf { webViews(view.getChildAt(it)) } else 0
    private fun findPlayer(view: View): YouTubePlayerView? = if (view is YouTubePlayerView) view else
        if (view is ViewGroup) (0 until view.childCount).firstNotNullOfOrNull { findPlayer(view.getChildAt(it)) } else null
    private fun count(scenario: ActivityScenario<MainActivity>): Int {
        var count = 0
        scenario.onActivity { count = webViews(it.window.decorView) }
        return count
    }
    private fun capturePreparedPreview(scenario: ActivityScenario<MainActivity>, output: File, filename: String) {
        val playbackState = AtomicReference<PlayerState>()
        val currentSecond = AtomicReference(0f)
        scenario.onActivity { host ->
            findPlayer(host.window.decorView)?.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerState) { playbackState.set(state) }
                override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) { currentSecond.set(second) }
            })
        }
        // A loaded WebView alone is not proof of playback. Capture a decoded
        // frame, or the restored artwork if YouTube declines this public title.
        await("Preview remained stuck instead of playing or restoring artwork", 30_000) {
            currentSecond.get() > 5f || count(scenario) == 0
        }
        val played = currentSecond.get() > 1f
        scenario.onActivity { host ->
            assertFalse(host.isFinishing)
            if (played) {
                assertEquals(1, webViews(host.window.decorView))
                assertEquals(View.VISIBLE, findPlayer(host.window.decorView)!!.visibility)
            }
        }
        android.util.Log.i("HomeTrailerScreen", "$filename: ${if (played) "live playback" else "artwork fallback"}, state=${playbackState.get()}")
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(File(output, filename))
    }
    private fun await(message: String, timeoutMs: Long, predicate: () -> Boolean) {
        val deadline = System.currentTimeMillis() + timeoutMs
        while (!predicate() && System.currentTimeMillis() < deadline) Thread.sleep(100)
        assertTrue(message, predicate())
    }
}
