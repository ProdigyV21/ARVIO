package com.arflix.tv.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.click
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.pressKey
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasFocus
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arflix.tv.R
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import java.io.File
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class IptvPlaylistModalDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private data class SavedPlaylist(val url: String, val user: String, val password: String, val epg: String)
    private var saved: SavedPlaylist? = null
    private var savedPortal: List<String>? = null

    @Test
    fun mobileXtreamSavePreservesCustomGuideSources() {
        show(DeviceType.PHONE)
        captureDialog("mobile")
        saveByTouch()
        assertEquals(SavedPlaylist(HOST, "user", "password", GUIDES), saved)
    }

    @Test
    fun mobileXtreamGuideCanBeEdited() {
        show(DeviceType.PHONE)
        compose.onNodeWithTag("iptv_input_5").performScrollTo()
        Espresso.onView(withHint(EPG_HINT)).perform(replaceText("https://edited.example/guide.xml"))
        Espresso.closeSoftKeyboard()
        saveByTouch()
        assertEquals("https://edited.example/guide.xml", saved?.epg)
    }

    @Test
    fun mobilePasteTargetsXtreamGuideField() {
        show(DeviceType.PHONE)
        // Native EditText bounds do not account for Compose scroll clipping in performScrollTo.
        compose.onNode(hasScrollAction()).performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, 500f) }
        compose.onNodeWithTag("iptv_input_5").performTouchInput { click() }
        Espresso.onView(withHint(EPG_HINT)).check(matches(hasFocus()))
        Espresso.closeSoftKeyboard()
        val pastedGuide = "https://pasted.example/guide.xml.gz"
        compose.runOnIdle {
            val clipboard = compose.activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("EPG", pastedGuide))
        }
        val label = compose.activity.getString(R.string.settings_label_epg_sources)
        compose.onNodeWithText(compose.activity.getString(R.string.settings_paste_into, label)).performClick()
        captureDialog("mobile-epg")
        saveByTouch()
        assertEquals(SavedPlaylist(HOST, "user", "password", pastedGuide), saved)
    }

    @Test
    fun switchingToM3uDoesNotSubmitHiddenXtreamCredentials() {
        show(DeviceType.PHONE)
        compose.onNodeWithText("M3U").performClick()
        compose.onNodeWithTag("iptv_input_2").performScrollTo()
        Espresso.onView(withHint("https://example.com/playlist.m3u"))
            .perform(replaceText("https://m3u.example/list.m3u"))
        Espresso.closeSoftKeyboard()
        saveByTouch()
        assertEquals(SavedPlaylist("https://m3u.example/list.m3u", "", "", GUIDES), saved)
    }

    @Test
    fun switchingBackToXtreamRetainsDraftCredentials() {
        show(DeviceType.PHONE)
        compose.onNodeWithText("M3U").performClick()
        compose.onNodeWithText("Xtream").performClick()
        saveByTouch()
        assertEquals(SavedPlaylist(HOST, "user", "password", GUIDES), saved)
    }

    @Test
    fun tvRemoteCanReachGuideAndSaveXtream() {
        show(DeviceType.TV)
        keys(listOf(Key.DirectionDown, Key.DirectionDown, Key.DirectionDown, Key.DirectionDown))
        compose.onNodeWithTag("iptv_input_5").assertIsDisplayed()
        keys(listOf(Key.DirectionDown))
        compose.onNodeWithText(compose.activity.getString(R.string.settings_save_playlist)).assertIsDisplayed()
        captureDialog("tv")
        keys(listOf(Key.DirectionRight, Key.DirectionCenter))
        assertEquals(SavedPlaylist(HOST, "user", "password", GUIDES), saved)
    }

    @Test
    fun tvRemoteM3uSaveIgnoresHiddenXtreamCredentials() {
        show(DeviceType.TV)
        keys(listOf(Key.DirectionLeft, Key.DirectionLeft, Key.DirectionLeft, Key.DirectionCenter))
        keys(listOf(Key.DirectionRight, Key.DirectionRight, Key.DirectionRight))
        keys(listOf(Key.DirectionDown, Key.DirectionDown, Key.DirectionDown, Key.DirectionRight, Key.DirectionCenter))
        assertEquals(SavedPlaylist(HOST, "", "", GUIDES), saved)
    }

    @Test
    fun stalkerSaveStillUsesPortalCallback() {
        show(DeviceType.PHONE, IptvSourceType.STALKER)
        compose.onNodeWithText(compose.activity.getString(R.string.settings_save_portal)).performClick()
        compose.runOnIdle {
            assertNull(saved)
            assertEquals(listOf("Test", HOST, "00:1A:79:12:34:56"), savedPortal)
        }
    }

    private fun show(device: DeviceType, source: IptvSourceType = IptvSourceType.XTREAM) {
        if (device == DeviceType.TV) {
            val config = compose.activity.resources.configuration
            assumeTrue("Run remote tests on a landscape TV emulator", config.screenWidthDp >= 600 && config.screenWidthDp > config.screenHeightDp)
        }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides device) {
                IptvPlaylistModal(
                    isEditing = true, initialSourceType = source, initialName = "Test", initialUrl = HOST,
                    initialXtreamUser = "user", initialXtreamPass = "password", initialEpg = GUIDES,
                    initialMacAddress = "00:1A:79:12:34:56",
                    onSaveIptv = { _, url, user, password, epg, _, _, _ ->
                        saved = SavedPlaylist(url, user, password, epg)
                    },
                    onSaveStalker = { name, url, mac -> savedPortal = listOf(name, url, mac) },
                    onDismiss = {},
                )
            }
        }
        compose.waitForIdle()
    }

    private fun saveByTouch() {
        compose.onNodeWithText(compose.activity.getString(R.string.settings_save_playlist)).performClick()
        compose.waitForIdle()
    }

    private fun captureDialog(layout: String) {
        val bitmap = compose.onNodeWithTag("iptv_playlist_modal", useUnmergedTree = true).captureToImage().asAndroidBitmap()
        File(compose.activity.getExternalFilesDir(null), "pr647-$layout.png").outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }

    private fun keys(keys: List<Key>) {
        keys.forEach { key ->
            compose.onNodeWithTag("iptv_playlist_modal", useUnmergedTree = true).performKeyInput { pressKey(key) }
            compose.waitForIdle()
        }
    }

    companion object {
        private const val HOST = "https://provider.example"
        private const val EPG_HINT = "https://provider.com/epg.xml.gz"
        private const val GUIDES = "https://guide.example/xmltv.php?custom=1\nhttps://backup.example/guide.xml.gz"
    }
}
