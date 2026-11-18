package com.arflix.tv.ui.screens.home

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.unit.dp
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class NetflixRowMetadataDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun metadataUpdatesFollowOnlyTheMatchingTitleAcrossRows() {
        val first = MediaItem(10, "First title", overview = "First catalogue synopsis")
        val second = MediaItem(20, "Second title", overview = "Second catalogue synopsis")
        val featured = mutableStateOf(second)
        val focus = HomeFocusState(0, 0).apply { userHasNavigated = true }
        val rows = listOf(Category("first", "First movies", listOf(first)),
            Category("second", "Second movies", listOf(second)))
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                HomeInputLayer(
                    categories = rows, cardLogoUrls = emptyMap(), focusState = focus,
                    limitRowsDuringStartup = false, suppressSelectUntilMs = 0L,
                    contentStartPadding = 24.dp, fastScrollThresholdMs = 80L,
                    usePosterCards = true, isContextMenuOpen = false, currentProfile = null,
                    onNavigateToDetails = { _, _, _, _ -> }, onNavigateToCollection = {},
                    onNavigateToSearch = {}, onNavigateToWatchlist = {}, onNavigateToTv = { _, _ -> },
                    onNavigateToSettings = {}, onSwitchProfile = {}, onExitApp = {},
                    onOpenContextMenu = { _, _ -> },
                    netflix = NetflixHomeContent(
                        billboardItem = MediaItem(9001, "Billboard"), billboardLogoUrl = null,
                        billboardTrailerKey = null, featuredItem = featured.value, cardTrailerKey = null,
                        cardTrailerDelayMs = 0, trailerVolume = 0f,
                        featuredOverview = "Selected title enriched synopsis"
                    )
                )
            }
        }
        compose.onNodeWithText("First catalogue synopsis").assertIsDisplayed()
        compose.runOnUiThread { featured.value = first }
        compose.onNodeWithText("Selected title enriched synopsis").assertIsDisplayed()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
        compose.waitForIdle()
        compose.onNodeWithText("Second catalogue synopsis").assertIsDisplayed()
        compose.runOnUiThread { featured.value = second }
        compose.onNodeWithText("Selected title enriched synopsis").assertIsDisplayed()
    }
}
