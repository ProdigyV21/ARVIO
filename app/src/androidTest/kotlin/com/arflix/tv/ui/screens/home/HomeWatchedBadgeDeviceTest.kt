package com.arflix.tv.ui.screens.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.ui.components.FeaturedMediaCard
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import java.io.File
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class HomeWatchedBadgeDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun normalCardKeepsItsWatchedTick() {
        val watched = mutableStateOf(false)
        fixture {
            MediaCard(
                item = MediaItem(901, "Watched title", isWatched = watched.value),
                modifier = Modifier.testTag("normal_card"), width = 160.dp,
                showTitle = false, enableSystemFocus = false, onClick = {}
            )
        }
        verifyToggle(watched, "media_watched_MOVIE_901", "normal_card", "normal")
    }

    @Test fun classicExpandedCardKeepsItsWatchedTickBesideALongTitle() {
        val watched = mutableStateOf(false)
        fixture {
            Box(Modifier.testTag("classic_card")) {
                FeaturedMediaCard(
                    item = MediaItem(902, "A long watched movie title that must leave room for the tick", isWatched = watched.value),
                    width = 360.dp, height = 202.5.dp, trailerKey = null,
                    trailerDelayMs = 0L, trailerVolume = 0f, onClick = {}
                )
            }
        }
        verifyToggle(watched, "featured_watched_MOVIE_902", "classic_card", "classic")
    }

    @Test fun netflixWatchedTickReplacesResumeStatusAndUpdatesWithoutRefocusing() {
        val watched = mutableStateOf(false)
        val focus = HomeFocusState(0, 0).apply { userHasNavigated = true }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                HomeInputLayer(
                    categories = listOf(Category("continue_watching", "Continue Watching", listOf(
                        MediaItem(903, "Watched title", progress = 40, timeRemainingMinutes = 25, isWatched = watched.value)
                    ))),
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
        val badge = compose.onNodeWithTag("netflix_watched_MOVIE_903", useUnmergedTree = true)
        val status = compose.onNodeWithTag("netflix_continue_status_MOVIE_903", useUnmergedTree = true)
        val progress = compose.onNodeWithTag("netflix_continue_progress_MOVIE_903", useUnmergedTree = true)
        badge.assertDoesNotExist()
        status.assertIsDisplayed()
        progress.assertIsDisplayed()

        compose.runOnIdle { watched.value = true }
        compose.waitForIdle()
        assertBadgeInside("netflix_watched_MOVIE_903", "netflix_card_MOVIE_903")
        status.assertDoesNotExist()
        progress.assertDoesNotExist()
        capture("netflix")

        compose.runOnIdle { watched.value = false }
        compose.waitForIdle()
        badge.assertDoesNotExist()
        status.assertIsDisplayed()
        progress.assertIsDisplayed()
    }

    private fun fixture(content: @Composable () -> Unit) {
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                Box(Modifier.fillMaxSize().background(Color.Black).padding(24.dp)) { content() }
            }
        }
        compose.waitForIdle()
    }

    private fun verifyToggle(watched: MutableState<Boolean>, badge: String, card: String, screenshot: String) {
        compose.onNodeWithTag(badge, useUnmergedTree = true).assertDoesNotExist()
        compose.runOnIdle { watched.value = true }
        compose.waitForIdle()
        assertBadgeInside(badge, card)
        capture(screenshot)
        compose.runOnIdle { watched.value = false }
        compose.waitForIdle()
        compose.onNodeWithTag(badge, useUnmergedTree = true).assertDoesNotExist()
    }

    private fun assertBadgeInside(badge: String, card: String) {
        val tick = compose.onNodeWithTag(badge, useUnmergedTree = true).assertIsDisplayed()
            .fetchSemanticsNode().boundsInRoot
        val bounds = compose.onNodeWithTag(card, useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        assertTrue("Watched tick stays inside the card", bounds.contains(tick.topLeft) && bounds.contains(tick.bottomRight))
        assertTrue("Watched tick stays bottom-right", tick.center.x > bounds.left + bounds.width * 0.75f &&
            tick.center.y > bounds.top + bounds.height * 0.75f)
    }

    private fun capture(name: String) {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(
            File(compose.activity.getExternalFilesDir(null), "pr835-$name.png")
        )
    }
}
