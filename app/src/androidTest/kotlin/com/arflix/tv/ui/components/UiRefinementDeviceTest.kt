package com.arflix.tv.ui.components

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.R
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.StreamBehaviorHints
import com.arflix.tv.ui.screens.details.EpisodeCard
import com.arflix.tv.ui.screens.details.SeasonButton
import com.arflix.tv.ui.screens.profile.EditProfileDialog
import com.arflix.tv.ui.screens.player.PlayerMetadataChrome
import com.arflix.tv.ui.screens.player.PlayerUiState
import com.arflix.tv.ui.screens.settings.SettingsScreen
import com.arflix.tv.ui.screens.settings.SettingsUiState
import com.arflix.tv.ui.screens.settings.SettingsViewModel
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

/** The same production controls are exercised on a TV AVD and a portrait phone AVD. */
@RunWith(AndroidJUnit4::class)
class UiRefinementDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val instrumentation get() = InstrumentationRegistry.getInstrumentation()
    private val deviceType = if (
        InstrumentationRegistry.getInstrumentation().targetContext.packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)
    ) DeviceType.TV else DeviceType.PHONE
    private fun text(id: Int) = instrumentation.targetContext.getString(id)

    private fun show(content: @Composable () -> Unit) {
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides deviceType) {
                ArflixTvTheme {
                    Box(Modifier.fillMaxSize().background(Color.Black)) { content() }
                }
            }
        }
        compose.waitForIdle()
    }

    @Test fun pausedPlayerShowsTheCurrentEpisodeSynopsis() {
        val paused = mutableStateOf(true)
        val state = mutableStateOf(PlayerUiState(
            title = "Industry", episodeTitle = "Episode three", seasonNumber = 3, episodeNumber = 3,
            overview = "The show description.",
            seasonEpisodes = listOf(
                Episode(3, 3, 3, "Episode three", overview = "The third episode description."),
                Episode(4, 4, 3, "Episode four", overview = "The fourth episode description.")
            )
        ))
        show {
            PlayerMetadataChrome(state.value, MediaType.TV,
                state.value.seasonNumber, state.value.episodeNumber, paused.value, Color.White,
                Modifier.padding(24.dp))
        }
        compose.onNodeWithText("The third episode description.").assertIsDisplayed()
        compose.onNodeWithText("The show description.").assertDoesNotExist()
        compose.runOnIdle { state.value = state.value.copy(episodeNumber = 4, episodeTitle = "Episode four") }
        compose.onNodeWithText("The fourth episode description.").assertIsDisplayed()
        compose.onNodeWithText("The third episode description.").assertDoesNotExist()
        compose.runOnIdle { paused.value = false }
        compose.onNodeWithText("The fourth episode description.").assertDoesNotExist()
    }

    @Test fun episodeMenuFocusDoesNotPretendAnActionIsSelected() {
        var action = ""
        var dismissed = 0
        show {
            EpisodeContextMenu(true, "Dio the Invader", "Season 1 · Episode 1", false,
                onPlay = { action = "play" }, onSelectSource = { action = "source" },
                onToggleWatched = { action = "watched" }, onDismiss = { dismissed++ })
        }
        compose.onAllNodesWithContentDescription(text(R.string.selected)).assertCountEquals(0)
        screenshot("episode-menu")
        if (deviceType == DeviceType.TV) {
            key(KeyEvent.KEYCODE_DPAD_DOWN)
            key(KeyEvent.KEYCODE_DPAD_CENTER)
        } else {
            compose.onNodeWithText(text(R.string.stream_title_select_source)).performClick()
        }
        compose.runOnIdle { assertEquals("source", action); assertEquals(1, dismissed) }
    }

    @Test fun settingsValuesAndSwitchesRemainActionable() {
        var choiceClicks = 0
        val checked = mutableStateOf(true)
        show {
            Column(Modifier.padding(if (deviceType == DeviceType.TV) 80.dp else 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SettingsRow(Icons.Default.Language, "App Language", "Language used throughout ARVIO", "English", true,
                    onClick = { choiceClicks++ })
                SettingsRow(Icons.Default.Language, "Default Audio", "Preferred audio language", "Auto (Original)", false, onClick = {})
                SettingsRow(Icons.Default.Language, "Default Subtitle", "Preferred subtitle language", "English", false, onClick = {})
                SettingsToggleRow("Disable Subtitles", "Start playback without subtitles", checked.value, false,
                    onToggle = { checked.value = it })
            }
        }
        compose.onNodeWithText("App Language").performClick()
        assertEquals(1, choiceClicks)
        compose.onNode(isToggleable()).assertIsOn().performClick().assertIsOff()
        compose.onNode(isToggleable()).performClick().assertIsOn()
        screenshot("settings-controls")
    }

    @Test fun realSettingsPageKeepsItsNavigationAndValuePicker() {
        val model = mockk<SettingsViewModel>(relaxed = true)
        every { model.uiState } returns MutableStateFlow(SettingsUiState())
        show { SettingsScreen(viewModel = model, initialSection = "language") }
        screenshot("settings-page")
        if (deviceType == DeviceType.TV) {
            key(KeyEvent.KEYCODE_DPAD_CENTER)
            key(KeyEvent.KEYCODE_BACK)
            key(KeyEvent.KEYCODE_DPAD_DOWN)
            key(KeyEvent.KEYCODE_DPAD_CENTER)
            compose.onAllNodesWithText(text(R.string.default_audio)).onLast().assertIsDisplayed()
            key(KeyEvent.KEYCODE_BACK)
            verify(exactly = 0) { model.setDefaultAudioLanguage(any()) }
        } else {
            compose.onNodeWithText(text(R.string.default_audio)).assertIsDisplayed().performClick()
            key(KeyEvent.KEYCODE_BACK)
        }
    }

    @Test fun homeContextMenuKeepsWatchlistAndDetailsActions() {
        var action = ""
        var closed = 0
        show {
            MediaContextMenu(true, "JoJo's Bizarre Adventure", isInWatchlist = true, isWatched = false,
                onPlay = { action = "play" }, onViewDetails = { action = "details" },
                onToggleWatchlist = { action = "watchlist" }, onToggleWatched = { action = "watched" },
                onDismiss = { closed++ })
        }
        compose.onAllNodesWithContentDescription(text(R.string.selected)).assertCountEquals(0)
        compose.onNodeWithText(text(R.string.remove_from_watchlist)).assertIsDisplayed()
        screenshot("home-menu")
        if (deviceType == DeviceType.TV) {
            key(KeyEvent.KEYCODE_DPAD_DOWN)
            key(KeyEvent.KEYCODE_DPAD_CENTER)
        } else compose.onNodeWithText(text(R.string.details)).performClick()
        compose.runOnIdle { assertEquals("details", action); assertEquals(1, closed) }
    }

    @Test fun sourcesPreserveReleaseNamesAndOnlyCheckActualSelection() {
        val streams = listOf(
            source("JoJos.Bizarre.Adventure.S01E01.1080p.BluRay.REMUX.AVC.TrueHD.7.1-Sample", "1080p", "8.4 GB", "one")
                .copy(behaviorHints = StreamBehaviorHints(cached = true)),
            source("JoJos.Bizarre.Adventure.S01E01.2160p.WEB-DL.DV.HDR10.HEVC.DDP5.1-Sample", "4K", "5.2 GB", "two"),
            source("JoJos.Bizarre.Adventure.S01E01.720p.WEB-DL.AAC2.0-Sample", "720p", "1.1 GB", "three")
        )
        var selected: StreamSource? = null
        var closed = 0
        show {
            StreamSelector(true, streams, streams[1], title = "JoJo's Bizarre Adventure", subtitle = "S1 E1 · Dio the Invader",
                onSelect = { selected = it }, onClose = { closed++ })
        }
        compose.onNodeWithText(streams[0].source).assertIsDisplayed()
        compose.onAllNodesWithContentDescription(text(R.string.selected)).assertCountEquals(1)
        compose.onAllNodesWithText(text(R.string.stream_source_cached)).assertCountEquals(1)
        screenshot("source-picker")
        if (deviceType == DeviceType.TV) {
            compose.waitUntil(5_000) { compose.onAllNodes(isFocused()).fetchSemanticsNodes().size == 1 }
            key(KeyEvent.KEYCODE_DPAD_CENTER)
        }
        else compose.onNodeWithText(streams[0].source).performClick()
        compose.runOnIdle { assertNotNull(selected) }
        key(KeyEvent.KEYCODE_BACK)
        compose.runOnIdle { assertEquals(1, closed) }
    }

    @Test fun episodeInfoStaysInsideCardAndMissingImdbIsNotInvented() {
        val focused = mutableStateOf(false)
        val rating = mutableStateOf("")
        var clicks = 0
        val first = Episode(1, 1, 1, "Dio the Invader", "Jonathan Joestar meets Dio, an ambitious young man taken in by his father.",
            imdbRating = "7.8", runtime = 24, airDate = "2012-10-06", isWatched = true)
        show {
            Column(Modifier.padding(16.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                EpisodeCard(first.copy(imdbRating = rating.value), isFocused = focused.value, cardWidth = if (deviceType == DeviceType.TV) 300.dp else 328.dp,
                    onClick = { clicks++ })
                EpisodeCard(first.copy(id = 2, episodeNumber = 2, name = "A Letter from the Past", imdbRating = "", isWatched = false),
                    isFocused = false, cardWidth = if (deviceType == DeviceType.TV) 300.dp else 328.dp)
            }
        }
        val card = compose.onNodeWithTag("episode_1_1")
        val before = card.fetchSemanticsNode().boundsInRoot
        compose.onAllNodesWithText("7.8").assertCountEquals(0)
        compose.runOnIdle { focused.value = true; rating.value = "7.8" }
        assertEquals(before, card.fetchSemanticsNode().boundsInRoot)
        compose.onAllNodesWithText("7.8").assertCountEquals(1)
        compose.onAllNodesWithText("6 Oct 2012 · 24m", useUnmergedTree = true).onFirst().assertIsDisplayed()
        val synopsis = compose.onAllNodesWithText(first.overview, useUnmergedTree = true).onFirst().fetchSemanticsNode().boundsInRoot
        assertTrue(before.contains(synopsis.topLeft) && before.contains(synopsis.bottomRight))
        card.performClick()
        assertEquals(1, clicks)
        screenshot("episode-cards")
    }

    @Test fun seasonsDistinguishSelectedFromFocusedAndKeepLongPress() {
        var selected = 0
        var options = 0
        show {
            Row(Modifier.padding(24.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SeasonButton(1, true, false, 3, 3, onClick = { selected = 1 }, onLongClick = { options++ })
                SeasonButton(2, false, true, onClick = { selected = 2 }, onLongClick = { options++ })
            }
        }
        compose.onNodeWithTag("season_1").assertIsSelected()
        compose.onNodeWithTag("season_2").assertIsNotSelected().performClick()
        assertEquals(2, selected)
        compose.onNodeWithTag("season_1").performTouchInput { longClick() }
        assertEquals(1, options)
        screenshot("season-tabs")
    }

    @Test fun profileDialogRetainsSaveCancelDeleteAndPinActions() {
        var saved = 0
        var cancelled = 0
        var deleted = 0
        var pin = 0
        show {
            EditProfileDialog(Profile(name = "Arvind"), "Arvind", onNameChange = {}, selectedColorIndex = 0,
                onColorSelected = {}, onConfirm = { saved++ }, onDelete = { deleted++ }, onDismiss = { cancelled++ },
                onShowPinSetup = { pin++ })
        }
        screenshot("profile-dialog")
        activate(compose.onNodeWithText(text(R.string.profile_set_pin)))
        activate(compose.onNodeWithText(text(R.string.cancel)))
        activate(compose.onNodeWithText(text(R.string.delete)))
        activate(compose.onNodeWithText(text(R.string.save)))
        compose.runOnIdle {
            assertEquals(1, pin)
            assertEquals(1, cancelled)
            assertEquals(1, deleted)
            assertEquals(1, saved)
        }
    }

    @Test fun emptyProfileNameCannotBeSavedAndAvatarCanStillBeChanged() {
        var saved = 0
        var avatar = -1
        show {
            EditProfileDialog(Profile(name = "Arvind"), "", onNameChange = {}, selectedColorIndex = 0,
                onColorSelected = {}, onAvatarSelected = { avatar = it }, onConfirm = { saved++ },
                onDelete = {}, onDismiss = {})
        }
        compose.onNodeWithText(text(R.string.save)).assertIsNotEnabled().performTouchInput { click() }
        assertEquals(0, saved)
        activate(compose.onNodeWithText("Aa"))
        assertEquals(0, avatar)
    }

    @Test fun profileNameInputAndImeSaveRemainConnected() {
        val name = mutableStateOf("Arvind")
        var savedName = ""
        show {
            EditProfileDialog(Profile(name = "Arvind"), name.value, onNameChange = { name.value = it }, selectedColorIndex = 0,
                onColorSelected = {}, onConfirm = { savedName = name.value }, onDelete = {}, onDismiss = {})
        }
        onView(withHint(text(R.string.profile_name_hint))).perform(replaceText("Test profile"), pressImeActionButton())
        compose.runOnIdle { assertEquals("Test profile", savedName) }
    }

    private fun source(title: String, quality: String, size: String, id: String) =
        StreamSource(title, "Sample addon", "fixture", quality, size, url = "https://example.invalid/$id.mp4")

    private fun key(code: Int) {
        instrumentation.sendKeyDownUpSync(code)
        compose.waitForIdle()
    }

    private fun activate(node: SemanticsNodeInteraction) {
        if (deviceType == DeviceType.TV) {
            node.performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus) { it() }
            key(KeyEvent.KEYCODE_DPAD_CENTER)
        } else node.performClick()
    }

    private fun screenshot(name: String) {
        compose.mainClock.advanceTimeBy(500)
        compose.waitForIdle()
        instrumentation.waitForIdleSync()
        android.os.SystemClock.sleep(500)
        val directory = File(compose.activity.getExternalFilesDir(null), "accepted-ui").apply { mkdirs() }
        val bitmap = captureNonblank()
        File(directory, "${deviceType.name.lowercase()}-$name.png").outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        bitmap.recycle()
    }

    private fun captureNonblank(): Bitmap {
        repeat(3) {
            val bitmap = checkNotNull(instrumentation.uiAutomation.takeScreenshot())
            val hasContent = (0 until bitmap.height step 16).any { y ->
                (0 until bitmap.width step 16).any { x ->
                    val pixel = bitmap.getPixel(x, y)
                    android.graphics.Color.red(pixel) > 40 || android.graphics.Color.green(pixel) > 40 || android.graphics.Color.blue(pixel) > 40
                }
            }
            if (hasContent) return bitmap
            bitmap.recycle()
            android.os.SystemClock.sleep(500)
        }
        error("Emulator screenshot was blank")
    }
}
