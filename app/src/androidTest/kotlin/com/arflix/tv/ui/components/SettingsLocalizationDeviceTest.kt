package com.arflix.tv.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.arflix.tv.R
import com.arflix.tv.ui.screens.tv.live.FullscreenHud
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.localizedAppContext
import java.util.Locale
import org.junit.After
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class SettingsLocalizationDeviceTest(private val language: String) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val originalLocale = Locale.getDefault()

    @After fun restoreLocale() {
        Locale.setDefault(originalLocale)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun languages() = listOf("en", "de", "pt-BR")
    }

    @Test fun translatedToggleKeepsSwitchAndChangesExactlyOncePerTap() {
        val checked = mutableStateOf(false)
        var clicks = 0
        compose.setContent {
            Localized(language) {
                MobileSettingsRow(
                    title = "Toggle",
                    value = stringResource(if (checked.value) R.string.on else R.string.off),
                    toggleChecked = checked.value,
                    onClick = { clicks++; checked.value = !checked.value }
                )
            }
        }
        val row = compose.onNode(isToggleable())
        row.assertIsOff().performClick().assertIsOn()
        row.assert(SemanticsMatcher.expectValue(SemanticsProperties.StateDescription, text(R.string.on)))
        assertEquals(1, clicks)
        assertTrue("On switch should be green", greenPixels(row) > 20)
        row.performClick().assertIsOff()
        row.assert(SemanticsMatcher.expectValue(SemanticsProperties.StateDescription, text(R.string.off)))
        assertEquals(2, clicks)
        assertEquals(0, greenPixels(row))
    }

    @Test fun disabledLocalizedToggleDoesNotChangeState() {
        var clicks = 0
        compose.setContent {
            Localized(language) {
                MobileSettingsRow(
                    title = "Disabled",
                    value = stringResource(R.string.off),
                    toggleChecked = false,
                    enabled = false,
                    onClick = { clicks++ }
                )
            }
        }
        compose.onNode(isToggleable()).assertIsOff().assertIsNotEnabled()
            .performTouchInput { click() }
        assertEquals(0, clicks)
    }

    @Test fun legacyRowsAndExplicitNonToggleValuesKeepTheirBehavior() {
        compose.setContent {
            Localized(language) {
                Column {
                    MobileSettingsRow(title = "Legacy on", value = "On", onClick = {})
                    MobileSettingsRow(title = "Legacy off", value = "Off", onClick = {})
                    MobileSettingsRow(title = "Language", value = "Off", isToggle = false, onClick = {})
                }
            }
        }
        compose.onAllNodes(isToggleable()).assertCountEquals(2)
        compose.onNodeWithText("Legacy on").assertIsOn()
        compose.onNodeWithText("Legacy off").assertIsOff()
        compose.onNodeWithText("Language")
            .assert(SemanticsMatcher.keyNotDefined(SemanticsProperties.ToggleableState))
        compose.onNodeWithText(text(R.string.off)).assertIsDisplayed()
    }

    @Test fun changingAppLanguagePreservesBooleanToggleState() {
        val locale = mutableStateOf(language)
        var clicks = 0
        compose.setContent {
            Localized(locale.value) {
                MobileSettingsRow(title = "Toggle", value = stringResource(R.string.on),
                    toggleChecked = true, onClick = { clicks++ })
            }
        }
        val next = if (language == "de") "en" else "de"
        compose.runOnIdle { locale.value = next }
        val expected = localizedAppContext(compose.activity, next).getString(R.string.on)
        compose.onNode(isToggleable()).assertIsOn()
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.StateDescription, expected))
        assertEquals(0, clicks)
    }

    @Test fun formattedMessagesAndUntranslatedLocaleFallbackLoadCorrectly() {
        val context = localizedAppContext(compose.activity, language)
        assertEquals(
            if (language == "de") "Katalog-Paket importieren" else "Import Catalog Pack",
            context.getString(R.string.settings_catalog_pack_import_title)
        )
        assertEquals(
            if (language == "de") "Enthaltene Kataloge (7):" else "Catalogs included (7):",
            context.getString(R.string.settings_catalog_pack_included, 7)
        )
        assertEquals(
            if (language == "de") "Autor: Example \u2022 v2.0" else "Author: Example \u2022 v2.0",
            context.getString(R.string.settings_catalog_pack_author, "Example", "2.0")
        )
        assertEquals(
            if (language == "de") "Paket-URL eingeben \u2026" else "Enter Paket-URL...",
            context.getString(R.string.settings_input_hint_enter, "Paket-URL")
        )
        assertTrue(context.getString(R.string.settings_activation_visit_instruction, "https://example.test/pin")
            .contains("https://example.test/pin"))
        assertEquals(if (language == "pt-BR") "T2 E3" else "S2 E3",
            context.getString(R.string.player_season_episode_short, 2, 3))
    }

    @Test fun translatedLiveTvControlsStillDispatchTheCorrectActions() {
        val configuration = compose.activity.resources.configuration
        assumeTrue("The TV playback overlay requires a landscape display",
            configuration.screenWidthDp > configuration.screenHeightDp)
        val actions = mutableListOf<String>()
        compose.setContent {
            Localized(language) {
                FullscreenHud(
                    channel = null, nowNext = null, pokeSignal = 0,
                    onPreviousCatchupClick = { actions += "previous" },
                    onNextCatchupClick = { actions += "next" },
                    onPlayPauseClick = { actions += "play-pause" },
                    onRewindClick = { actions += "rewind" },
                    onFastForwardClick = { actions += "forward" },
                    onReplayClick = { actions += "replay" }
                )
            }
        }
        compose.mainClock.autoAdvance = false
        listOf(R.string.live_cd_previous_channel, R.string.live_cd_rewind, R.string.live_cd_pause,
            R.string.live_cd_fast_forward, R.string.live_cd_next_channel, R.string.live_cd_replay)
            .forEach { resource ->
                compose.onNodeWithContentDescription(text(resource)).performClick()
                compose.mainClock.advanceTimeByFrame()
            }
        compose.onNodeWithContentDescription(text(R.string.play)).performClick()
        compose.mainClock.advanceTimeByFrame()
        compose.onNodeWithContentDescription(text(R.string.live_cd_pause)).assertExists()
        assertEquals(listOf("previous", "rewind", "play-pause", "forward", "next", "replay", "play-pause"), actions)
    }

    @Composable private fun Localized(locale: String, content: @Composable () -> Unit) {
        val context = localizedAppContext(compose.activity, locale)
        CompositionLocalProvider(
            LocalContext provides context,
            LocalConfiguration provides context.resources.configuration,
            LocalDeviceType provides DeviceType.PHONE,
            content = content
        )
    }

    private fun text(resource: Int) = localizedAppContext(compose.activity, language).getString(resource)

    private fun greenPixels(node: SemanticsNodeInteraction): Int {
        val pixels = node.captureToImage().toPixelMap()
        var count = 0
        for (y in 0 until pixels.height) for (x in 0 until pixels.width) {
            val color = pixels[x, y]
            if (color.green > 0.5f && color.green > color.red * 1.5f && color.green > color.blue * 1.3f) count++
        }
        return count
    }
}
