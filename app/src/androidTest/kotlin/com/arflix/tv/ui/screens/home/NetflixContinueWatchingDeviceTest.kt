package com.arflix.tv.ui.screens.home

import android.content.Context
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.arflix.tv.R
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.ContinueWatchingItem
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import java.io.File
import java.util.Locale
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class NetflixContinueWatchingDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun focusedCardsShowCurrentEpisodeMovieTimeAndNextEpisode() {
        verifyFocusedStatuses(Locale.ENGLISH, "en")
    }

    @Test fun focusedStatusesFollowTheAppLanguage() {
        verifyFocusedStatuses(Locale.GERMAN, "de")
    }

    @Test fun movieWithoutDurationDoesNotInventRemainingTime() {
        fixture(listOf(MediaItem(123, "Unknown duration", progress = 30)))
        compose.onNodeWithTag("netflix_continue_status_MOVIE_123", useUnmergedTree = true).assertDoesNotExist()
        compose.onNodeWithTag("netflix_continue_progress_MOVIE_123", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test fun otherRowsDoNotShowContinueWatchingStatus() {
        fixture(listOf(movie()), categoryId = "popular")
        compose.onNodeWithTag("netflix_continue_status_MOVIE_456", useUnmergedTree = true).assertDoesNotExist()
        compose.onNodeWithTag("netflix_continue_progress_MOVIE_456", useUnmergedTree = true).assertDoesNotExist()
    }

    private fun verifyFocusedStatuses(locale: Locale, screenshotPrefix: String) {
        val current = ContinueWatchingItem(
            id = 123, title = "Current episode", mediaType = MediaType.TV,
            progress = 25, season = 1, episode = 21, displaySeason = 2, displayEpisode = 9,
            durationSeconds = 1800L, resumePositionSeconds = 450L
        ).toMediaItem()
        // Up-next entries may still carry show-level progress; that is not episode progress.
        val next = ContinueWatchingItem(
            id = 789, title = "Next episode", mediaType = MediaType.TV,
            progress = 55, season = 4, episode = 29, isUpNext = true
        ).toMediaItem()
        val context = fixture(listOf(current, movie(), next), locale)
        assertStatus("TV_123", context.getString(R.string.continue_season_episode, 2, 9), progress = true)
        capture("$screenshotPrefix-current-episode.png")

        compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
        compose.waitForIdle()
        assertStatus("MOVIE_456", context.getString(R.string.component_time_left_hours_minutes, 1, 15), progress = true)
        compose.onNodeWithTag("netflix_continue_status_TV_123", useUnmergedTree = true).assertDoesNotExist()
        capture("$screenshotPrefix-movie-time-left.png")

        compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
        compose.waitForIdle()
        assertStatus("TV_789", context.getString(R.string.component_next_season_episode, 4, 29), progress = false)
        capture("$screenshotPrefix-next-episode.png")
    }

    private fun assertStatus(identity: String, expected: String, progress: Boolean) {
        val status = compose.onNodeWithTag("netflix_continue_status_$identity", useUnmergedTree = true)
        status.assertIsDisplayed().assertTextEquals(expected)
        val cardBounds = compose.onNodeWithTag("netflix_card_$identity", useUnmergedTree = true)
            .fetchSemanticsNode().boundsInRoot
        val statusBounds = status.fetchSemanticsNode().boundsInRoot
        assertTrue("Status stays inside the focused artwork", cardBounds.contains(statusBounds.topLeft) &&
            cardBounds.contains(statusBounds.bottomRight))
        val bar = compose.onNodeWithTag("netflix_continue_progress_$identity", useUnmergedTree = true)
        if (progress) bar.assertExists() else bar.assertDoesNotExist()
    }

    private fun movie() = ContinueWatchingItem(
        id = 456, title = "Movie in progress", mediaType = MediaType.MOVIE,
        progress = 37, durationSeconds = 7200L, resumePositionSeconds = 2700L
    ).toMediaItem()

    private fun fixture(
        items: List<MediaItem>,
        locale: Locale = Locale.ENGLISH,
        categoryId: String = "continue_watching"
    ): Context {
        val base = InstrumentationRegistry.getInstrumentation().targetContext
        val configuration = Configuration(base.resources.configuration).apply { setLocale(locale) }
        val context = base.createConfigurationContext(configuration)
        val focus = HomeFocusState(0, 0).apply { userHasNavigated = true }
        compose.setContent {
            CompositionLocalProvider(
                LocalContext provides context,
                LocalConfiguration provides configuration,
                LocalDeviceType provides DeviceType.TV
            ) {
                HomeInputLayer(
                    categories = listOf(Category(categoryId, "Continue Watching", items)),
                    cardLogoUrls = emptyMap(), focusState = focus,
                    limitRowsDuringStartup = false, suppressSelectUntilMs = 0L,
                    contentStartPadding = 24.dp, fastScrollThresholdMs = 80L,
                    usePosterCards = true, isContextMenuOpen = false, currentProfile = null,
                    onNavigateToDetails = { _, _, _, _ -> }, onNavigateToCollection = {},
                    onNavigateToSearch = {}, onNavigateToWatchlist = {}, onNavigateToTv = { _, _ -> },
                    onNavigateToSettings = {}, onSwitchProfile = {}, onExitApp = {},
                    onOpenContextMenu = { _, _ -> },
                    netflix = NetflixHomeContent(null, null, null, null, null, 0, 0f)
                )
            }
        }
        compose.waitForIdle()
        return context
    }

    private fun capture(name: String) {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(
            File(compose.activity.getExternalFilesDir(null), "pr834-$name")
        )
    }
}
