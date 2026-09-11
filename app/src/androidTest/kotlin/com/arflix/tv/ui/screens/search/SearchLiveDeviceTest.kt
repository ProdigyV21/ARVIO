package com.arflix.tv.ui.screens.search

import android.graphics.Bitmap
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.di.RepositoryAccessEntryPoint
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import dagger.hilt.android.EntryPointAccessors
import java.io.File
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Opt-in public metadata smoke test; no cloud account or playback required. */
@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class SearchLiveDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun liveTitlesAreFirstAndCanBeOpenedWithOneDownPress() {
        assumeTrue(InstrumentationRegistry.getArguments().getString("searchLive") == "true")
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val repository = EntryPointAccessors.fromApplication(instrumentation.targetContext,
            RepositoryAccessEntryPoint::class.java).mediaRepository()
        lateinit var model: SearchViewModel
        var openedId: Int? = null
        compose.runOnUiThread {
            model = SearchViewModel(repository)
            compose.activity.viewModelStore.put("live-search", model)
        }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                SearchScreen(viewModel = model, onNavigateToDetails = { _, id -> openedId = id })
            }
        }
        for (query in listOf("Loki", "Matlock", "Breaking Bad", "Alien")) {
            val started = SystemClock.elapsedRealtime()
            compose.runOnIdle { model.updateQuery(query) }
            compose.waitUntil(15_000) { !model.uiState.value.isLoading && model.uiState.value.query == query }
            val elapsed = SystemClock.elapsedRealtime() - started
            val result = model.uiState.value.results
            assertTrue("$query: ${model.uiState.value.error}", result.isNotEmpty())
            assertEquals(query.lowercase(), result.first().title.lowercase())
            val first = result.first()
            val report = "$query: ${elapsed}ms, ${result.size} titles, first=${first.title} (${first.year})\n"
            instrumentation.sendStatus(0, Bundle().apply { putString("stream", report) })
            File(compose.activity.getExternalFilesDir(null), "search-live-times.txt").appendText(report)
            compose.onNodeWithTag("search-screen").performKeyInput { pressKey(Key.DirectionDown) }
            compose.onNodeWithTag("search-card-s_all-${first.mediaType}-${first.id}")
                .assertIsSelected().assertIsDisplayed()
            compose.onNodeWithTag("search-screen").performKeyInput { pressKey(Key.DirectionCenter) }
            compose.runOnIdle { assertEquals(first.id, openedId) }
            // Allow actual network artwork to settle for the visual audit, not the timing measurement.
            Thread.sleep(1_500)
            val bitmap = compose.onNodeWithTag("search-screen").captureToImage().asAndroidBitmap()
            File(compose.activity.getExternalFilesDir(null), "search-live-${query.replace(' ', '-')}.png")
                .outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
            compose.onNodeWithTag("search-screen").performKeyInput { pressKey(Key.Escape) }
        }
    }
}
