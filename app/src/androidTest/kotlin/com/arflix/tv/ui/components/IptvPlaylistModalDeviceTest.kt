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
import androidx.compose.ui.test.onNodeWithContentDescription
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
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasFocus
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arflix.tv.R
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.LocalHasTouchScreen
import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
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
    private var limitChanges = 0
    private var accountRefreshes = 0

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
    fun tvKeyboardDoneReturnsToDialogAndCanSave() {
        show(DeviceType.TV)
        keys(listOf(Key.DirectionDown, Key.DirectionDown, Key.DirectionDown, Key.DirectionDown, Key.DirectionCenter))
        Espresso.onView(withHint(EPG_HINT)).perform(replaceText(GUIDES), pressImeActionButton())
        compose.waitForIdle()
        keys(listOf(Key.DirectionDown, Key.DirectionRight, Key.DirectionCenter))
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
            assertEquals(listOf("Test", HOST, "00:1A:79:12:34:56", "true", "true", "true"), savedPortal)
        }
    }

    @Test
    fun tvRemoteCanOpenAndClosePhoneInput() {
        show(DeviceType.TV)
        keys(listOf(Key.DirectionLeft) + List(4) { Key.DirectionDown } + Key.DirectionCenter)
        compose.onNodeWithText(compose.activity.getString(R.string.phone_input_button_hide)).assertIsDisplayed()
        val url = phonePageUrl()
        assertEquals(200, phoneRequest(url).first)
        captureDialog("phone-input")
        keys(listOf(Key.DirectionCenter))
        compose.onNodeWithContentDescription(compose.activity.getString(R.string.component_qr_code)).assertDoesNotExist()
        assertThrows(IOException::class.java) { phoneRequest(url) }
    }

    @Test
    fun tvPhoneInputDoesNotReplaceAccountControls() {
        show(DeviceType.TV, accountControls = true)
        keys(listOf(Key.DirectionLeft) + List(5) { Key.DirectionDown })
        compose.onNodeWithText(compose.activity.getString(R.string.iptv_account_refresh)).assertIsDisplayed()
        keys(listOf(Key.DirectionCenter, Key.DirectionDown, Key.DirectionCenter))
        compose.onNodeWithText(compose.activity.getString(R.string.phone_input_button_hide)).assertIsDisplayed()
        keys(listOf(Key.DirectionCenter, Key.DirectionUp, Key.DirectionCenter, Key.DirectionUp, Key.DirectionCenter))
        assertEquals(2, accountRefreshes)
        assertEquals(1, limitChanges)
    }

    @Test
    fun tvPhoneM3uSubmissionPreservesMultipleGuideSources() {
        assertPhoneSubmission(IptvSourceType.M3U)
    }

    @Test
    fun tvPhoneXtreamSubmissionPreservesMultipleGuideSources() {
        assertPhoneSubmission(IptvSourceType.XTREAM)
    }

    @Test
    fun tvSourceSwitchClosesThePreviousPhoneSession() {
        show(DeviceType.TV)
        keys(listOf(Key.DirectionLeft) + List(4) { Key.DirectionDown } + Key.DirectionCenter)
        val url = phonePageUrl()
        keys(List(4) { Key.DirectionUp } + listOf(Key.DirectionLeft, Key.DirectionCenter))
        compose.onNodeWithContentDescription(compose.activity.getString(R.string.component_qr_code)).assertDoesNotExist()
        assertThrows(IOException::class.java) { phoneRequest(url) }
    }

    private fun assertPhoneSubmission(source: IptvSourceType) {
        show(DeviceType.TV)
        keys(listOf(Key.DirectionLeft))
        if (source == IptvSourceType.M3U) keys(listOf(Key.DirectionLeft, Key.DirectionLeft, Key.DirectionCenter))
        keys(List(4) { Key.DirectionDown } + Key.DirectionCenter)
        val url = phonePageUrl()
        val (status, page) = phoneRequest(url)
        assertEquals(200, status)
        val config = JSONObject(page.lineSequence().first { it.startsWith("const C=") }.removePrefix("const C=").removeSuffix(";"))
        val fields = config.getJSONArray("fields")
        val epgIndex = if (source == IptvSourceType.M3U) 2 else 4
        assertTrue(fields.getJSONObject(epgIndex).getBoolean("multiline"))
        assertEquals(GUIDES, fields.getJSONObject(epgIndex).getString("value"))
        if (source == IptvSourceType.XTREAM) assertEquals("", fields.getJSONObject(3).getString("value"))
        val values = JSONArray((0 until fields.length()).map { fields.getJSONObject(it).getString("value") })
        val changedUrl = "https://phone.example/list.m3u"
        values.put(1, changedUrl)
        val submitUrl = URL("http://127.0.0.1:${url.port}/submit?t=${config.getString("token")}")
        assertEquals(200, phoneRequest(submitUrl, JSONObject().put("values", values).toString()).first)
        compose.waitForIdle()
        assertNull(saved)
        keys(listOf(Key.DirectionRight, Key.DirectionRight, Key.DirectionCenter))
        assertEquals(
            SavedPlaylist(changedUrl, if (source == IptvSourceType.XTREAM) "user" else "", if (source == IptvSourceType.XTREAM) "password" else "", GUIDES),
            saved,
        )
    }

    private fun phonePageUrl(): URL {
        val bitmap = compose.onNodeWithContentDescription(compose.activity.getString(R.string.component_qr_code))
            .assertIsDisplayed().captureToImage().asAndroidBitmap()
        val pixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        val source = RGBLuminanceSource(bitmap.width, bitmap.height, pixels)
        val scanned = URL(MultiFormatReader().decode(BinaryBitmap(HybridBinarizer(source))).text)
        // Keep test traffic inside the emulator; the scanned address is otherwise unchanged.
        return URL("http://127.0.0.1:${scanned.port}${scanned.file}")
    }

    private fun phoneRequest(url: URL, body: String? = null): Pair<Int, String> {
        val connection = url.openConnection() as HttpURLConnection
        try {
            connection.connectTimeout = 3000
            connection.readTimeout = 3000
            if (body != null) {
                connection.requestMethod = "POST"
                connection.doOutput = true
                connection.setRequestProperty("Content-Type", "application/json")
                connection.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }
            }
            val status = connection.responseCode
            return status to (if (status < 400) connection.inputStream else connection.errorStream).bufferedReader().use { it.readText() }
        } finally {
            connection.disconnect()
        }
    }

    private fun show(device: DeviceType, source: IptvSourceType = IptvSourceType.XTREAM, accountControls: Boolean = false) {
        if (device == DeviceType.TV) {
            val config = compose.activity.resources.configuration
            assumeTrue("Run remote tests on a landscape TV emulator", config.screenWidthDp >= 600 && config.screenWidthDp > config.screenHeightDp)
        }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides device, LocalHasTouchScreen provides (device != DeviceType.TV)) {
                IptvPlaylistModal(
                    isEditing = true, initialSourceType = source, initialName = "Test", initialUrl = HOST,
                    initialXtreamUser = "user", initialXtreamPass = "password", initialEpg = GUIDES,
                    initialMacAddress = "00:1A:79:12:34:56",
                    onSaveIptv = { _, url, user, password, epg, _, _, _ ->
                        saved = SavedPlaylist(url, user, password, epg)
                    },
                    onSaveStalker = { name, url, mac, importLiveTv, importVod, importSeries ->
                        savedPortal = listOf(
                            name, url, mac,
                            importLiveTv.toString(), importVod.toString(), importSeries.toString()
                        )
                    },
                    onDismiss = {},
                    connectionLimitLabel = if (accountControls) "Auto" else null,
                    onStepConnectionLimit = if (accountControls) ({ limitChanges++; Unit }) else null,
                    onRefreshAccount = if (accountControls) ({ accountRefreshes++; Unit }) else null,
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
