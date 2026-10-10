package com.arflix.tv.ui.screens.details

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.di.RepositoryAccessEntryPoint
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import dagger.hilt.android.EntryPointAccessors
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

/** Opt-in live artwork capture; normal regression tests do not depend on metadata services. */
@RunWith(AndroidJUnit4::class)
class DetailsUiScreenshotDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun captureProductionDetailsWithRealEpisodeMetadata() {
        assumeTrue(InstrumentationRegistry.getArguments().getString("captureLive") == "true")
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val repository = EntryPointAccessors.fromApplication(
            instrumentation.targetContext.applicationContext, RepositoryAccessEntryPoint::class.java
        ).mediaRepository()
        val (item, initialEpisodes) = runBlocking {
            withTimeout(60_000) { repository.getTvDetails(45790) to repository.getSeasonEpisodes(45790, 1) }
        }
        val model = mockk<DetailsViewModel>(relaxed = true)
        val logo = runBlocking { withTimeout(20_000) { repository.getLogoUrl(MediaType.TV, 45790) } }
        val episodes = runBlocking {
            withTimeout(25_000) {
                val cached = repository.getSeasonEpisodes(45790, 1)
                if (cached.none { it.imdbRating.isNotBlank() }) {
                    withTimeoutOrNull(10_000) { repository.episodeRatingsUpdated.first { it == (45790 to 1) } }
                    repository.getSeasonEpisodes(45790, 1)
                } else cached
            }
        }
            .ifEmpty { initialEpisodes }
        android.util.Log.i("AcceptedUi", "Real episode IMDb ratings: ${episodes.count { it.imdbRating.isNotBlank() }}/${episodes.size}")
        every { model.uiState } returns MutableStateFlow(DetailsUiState(
            isLoading = false, item = item, logoUrl = logo, episodes = episodes, totalSeasons = 6,
            currentSeason = 1, autoPlaySingleSource = false
        ))
        val tv = instrumentation.targetContext.packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides if (tv) DeviceType.TV else DeviceType.PHONE) {
                ArflixTvTheme {
                    DetailsScreen(MediaType.TV, 45790, viewModel = model,
                        onNavigateToPlayer = { _, _, _, _, _, _, _, _ -> }, onNavigateToDetails = { _, _ -> }, onBack = {})
                }
            }
        }
        compose.waitForIdle()
        if (tv) {
            instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN)
            instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN)
        }
        compose.waitForIdle()
        android.os.SystemClock.sleep(2500)
        if (tv) {
            compose.onNodeWithContentDescription(item.title).assertIsDisplayed()
            val episodeBounds = compose.onNodeWithTag("episode_1_1").fetchSemanticsNode().boundsInRoot
            val rootBounds = compose.onRoot().fetchSemanticsNode().boundsInRoot
            org.junit.Assert.assertTrue("Episode card must fit below the hero", episodeBounds.bottom <= rootBounds.bottom)
        }
        val directory = File(compose.activity.getExternalFilesDir(null), "accepted-ui").apply { mkdirs() }
        val bitmap = checkNotNull(instrumentation.uiAutomation.takeScreenshot())
        File(directory, "${if (tv) "tv" else "phone"}-details.png").outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        bitmap.recycle()
        if (!tv) {
            compose.onRoot().performTouchInput { swipeUp(startY = height * 0.8f, endY = height * 0.3f) }
            compose.waitForIdle()
            compose.onNodeWithTag("episode_1_1").assertIsDisplayed()
            android.os.SystemClock.sleep(400)
            val episodeBitmap = checkNotNull(instrumentation.uiAutomation.takeScreenshot())
            File(directory, "phone-details-episodes.png").outputStream().use {
                episodeBitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }
            episodeBitmap.recycle()
        }
    }
}
