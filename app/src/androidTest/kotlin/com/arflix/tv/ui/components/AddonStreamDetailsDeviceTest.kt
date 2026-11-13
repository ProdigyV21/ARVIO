package com.arflix.tv.ui.components

import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.TextLayoutResult
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.addonStreamDetailsKey
import com.arflix.tv.util.profilesDataStore
import com.arflix.tv.util.settingsDataStore
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddonStreamDetailsDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val instrumentation get() = InstrumentationRegistry.getInstrumentation()
    private val context get() = instrumentation.targetContext
    private val activeProfile = stringPreferencesKey("active_profile_id")
    private val profile = "addon-details-device-test"
    private var previousProfile: String? = null
    private val description = "Resolution: 2160p\nRelease: WEB-DL\nHDR: DV HDR10+\nAudio: TrueHD 7.1\nSize: 12.5 GB\nBitrate: 67 Mbps\nGroup: Example\nLanguages: English"
    private val streams = (0..25).map { index -> StreamSource(
        source = "Movie.$index.2160p.WEB-DL.mkv", addonName = "AIOStreams", addonId = "aio-streams",
        quality = "4K", size = "12.5 GB", url = "https://example.invalid/$index.mkv",
        description = "$description\nSource: $index"
    ) }

    @Before fun resetPreference() = runBlocking {
        context.profilesDataStore.edit { previousProfile = it[activeProfile]; it[activeProfile] = profile }
        context.settingsDataStore.edit { it.remove(addonStreamDetailsKey(profile)) }
    }

    @After fun restoreProfile() = runBlocking {
        context.profilesDataStore.edit { prefs ->
            previousProfile?.let { prefs[activeProfile] = it } ?: prefs.remove(activeProfile)
        }
        context.settingsDataStore.edit { it.remove(addonStreamDetailsKey(profile)) }
    }

    private fun enable(enabled: Boolean) = runBlocking {
        context.settingsDataStore.edit { it[addonStreamDetailsKey(profile)] = enabled }
    }

    private fun show(device: DeviceType = DeviceType.TV, sources: List<StreamSource> = streams, onSelect: (StreamSource) -> Unit = {}) {
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides device) {
                ArflixTvTheme {
                    Box(Modifier.fillMaxSize().background(Color.Black)) {
                        StreamSelector(true, sources, null, onSelect = onSelect)
                    }
                }
            }
        }
        if (device == DeviceType.TV) {
            compose.waitUntil(5_000) { compose.onAllNodes(isFocused()).fetchSemanticsNodes().size == 1 }
        }
        compose.waitForIdle()
    }

    private fun key(code: Int) {
        instrumentation.sendKeyDownUpSync(code)
        compose.waitForIdle()
    }

    private fun layout(text: String): TextLayoutResult {
        val results = mutableListOf<TextLayoutResult>()
        compose.onNodeWithText(text, useUnmergedTree = true).performSemanticsAction(SemanticsActions.GetTextLayoutResult) { it(results) }
        return results.single()
    }

    private fun assertDetailsNotClipped(text: String) {
        assertEquals(layout(text).size.height.toFloat(),
            compose.onNodeWithText(text, useUnmergedTree = true).fetchSemanticsNode().boundsInRoot.height, 1f)
    }

    @Test fun defaultsOffAndUpdatesImmediatelyWhenPreferenceChanges() {
        show()
        compose.onNodeWithText(streams[0].description!!, useUnmergedTree = true).assertDoesNotExist()
        enable(true)
        compose.waitUntil(5_000) { compose.onAllNodesWithText(streams[0].description!!, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithText(streams[0].description!!, useUnmergedTree = true).assertIsDisplayed()
        enable(false)
        compose.waitUntil(5_000) { compose.onAllNodesWithText(streams[0].description!!, useUnmergedTree = true).fetchSemanticsNodes().isEmpty() }
    }

    @Test fun focusedDetailsExpandAndRemoteStillSelectsTheCorrectSource() {
        enable(true)
        var selected: StreamSource? = null
        show(onSelect = { selected = it })
        assertEquals(9, layout(streams[0].description!!).lineCount)
        assertEquals(3, layout(streams[1].description!!).lineCount)
        java.io.File(context.getExternalFilesDir(null), "issue894-tv-details.png").outputStream().use {
            instrumentation.uiAutomation.takeScreenshot().compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
        }
        repeat(18) { index ->
            key(KeyEvent.KEYCODE_DPAD_DOWN)
            compose.onNodeWithText(streams[index + 1].source).assertIsDisplayed()
            assertEquals(9, layout(streams[index + 1].description!!).lineCount)
            assertDetailsNotClipped(streams[index + 1].description!!)
        }
        key(KeyEvent.KEYCODE_DPAD_RIGHT)
        key(KeyEvent.KEYCODE_DPAD_LEFT)
        key(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle { assertEquals(streams[18], selected) }
    }

    @Test fun switchingProfilesDoesNotLeakAnOptIn() {
        enable(true)
        show()
        compose.onNodeWithText(streams[0].description!!, useUnmergedTree = true).assertIsDisplayed()
        runBlocking { context.profilesDataStore.edit { it[activeProfile] = "$profile-other" } }
        compose.waitUntil(5_000) { compose.onAllNodesWithText(streams[0].description!!, useUnmergedTree = true).fetchSemanticsNodes().isEmpty() }
        runBlocking { context.profilesDataStore.edit { it[activeProfile] = profile } }
        compose.waitUntil(5_000) { compose.onAllNodesWithText(streams[0].description!!, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty() }
    }

    @Test fun longFilenamesLeaveRoomForDetailsOnSmallTvLayouts() {
        enable(true)
        val long = streams[0].copy(
            source = "Movie.Extended.Directors.Cut.Multi.Audio.TrueHD.7.1.Atmos.REMUX.".repeat(7),
            description = (1..25).joinToString("\n") { "Formatter line $it: 2160p WEB-DL HDR" }
        )
        show(sources = listOf(long))
        assertTrue(layout(long.description!!).lineCount in 1..12)
        assertDetailsNotClipped(long.description!!)
    }

    @Test fun phoneKeepsItsExistingThreeLineDescription() {
        enable(true)
        show(DeviceType.PHONE)
        assertEquals(3, layout(streams[0].description!!).lineCount)
    }
}
