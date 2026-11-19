package com.arflix.tv.ui.screens.settings

import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.repository.CloudSyncRepository.CloudProfileSettings
import com.arflix.tv.ui.components.*
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.*
import com.google.gson.Gson
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection
import java.net.URL

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class NavigationVisibilityDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val context get() = InstrumentationRegistry.getInstrumentation().targetContext
    private val first = "issue887-first"
    private val second = "issue887-second"
    private val profile = mutableStateOf(first)
    private val state = MutableStateFlow(SettingsUiState())
    private val model = mockk<SettingsViewModel>(relaxed = true)
    private val showSettings = mutableStateOf(true)
    private var previousFirst: String? = null
    private var previousSecond: String? = null
    private var selectedRoute: String? = null
    private var wentHome = false

    @Before fun setup(): Unit = runBlocking {
        NavigationVisibility.hiddenSections = emptySet()
        KidsNavigation.hideLiveTv = false
        context.settingsDataStore.edit {
            previousFirst = it[hiddenNavigationSectionsKey(first)]
            previousSecond = it[hiddenNavigationSectionsKey(second)]
            it.remove(hiddenNavigationSectionsKey(first))
            it.remove(hiddenNavigationSectionsKey(second))
        }
        every { model.uiState } returns state
        every { model.setNavigationSectionVisible(any(), any()) } answers {
            val section = firstArg<String>()
            val visible = secondArg<Boolean>()
            val hidden = state.value.hiddenNavigationSections.toMutableSet().apply {
                if (visible) remove(section) else add(section)
            }
            state.value = state.value.copy(hiddenNavigationSections = hidden)
            runBlocking { context.settingsDataStore.edit { it[hiddenNavigationSectionsKey(profile.value)] = encodeHiddenNavigationSections(hidden) } }
        }
    }

    @After fun cleanup(): Unit = runBlocking {
        context.settingsDataStore.edit {
            previousFirst?.let { value -> it[hiddenNavigationSectionsKey(first)] = value } ?: it.remove(hiddenNavigationSectionsKey(first))
            previousSecond?.let { value -> it[hiddenNavigationSectionsKey(second)] = value } ?: it.remove(hiddenNavigationSectionsKey(second))
        }
        NavigationVisibility.hiddenSections = emptySet()
        KidsNavigation.hideLiveTv = false
    }

    private fun show(device: DeviceType = DeviceType.TV, settings: Boolean = true) {
        showSettings.value = settings
        compose.setContent {
            val configuration = Configuration(LocalConfiguration.current).apply {
                if (device == DeviceType.PHONE) { screenWidthDp = 390; screenHeightDp = 844; smallestScreenWidthDp = 390; orientation = Configuration.ORIENTATION_PORTRAIT }
            }
            CompositionLocalProvider(LocalDeviceType provides device, LocalConfiguration provides configuration) {
                ArflixTvTheme {
                    ObserveNavigationVisibility(profile.value)
                    LaunchedEffect(profile.value) {
                        context.settingsDataStore.data.collect { prefs ->
                            state.value = state.value.copy(hiddenNavigationSections = readHiddenNavigationSections(prefs[hiddenNavigationSectionsKey(profile.value)]))
                        }
                    }
                    Box(Modifier.then(if (device == DeviceType.PHONE) Modifier.width(390.dp) else Modifier).fillMaxSize().background(Color.Black)) {
                        if (showSettings.value) SettingsScreen(model,
                            currentProfile = Profile(id = profile.value, name = "Navigation", avatarColor = 0xff367b72),
                            initialSection = if (device == DeviceType.TV) "appearance" else null,
                            onNavigateToHome = { wentHome = true })
                        else if (device.isTouchDevice()) AppBottomBar("home", { selectedRoute = it })
                        else AppTopBar(SidebarItem.HOME, false, 0, Profile(id = profile.value, name = "Navigation", avatarColor = 0xff367b72))
                    }
                }
            }
        }
        compose.waitForIdle()
    }

    private fun press(key: Key, count: Int = 1) {
        repeat(count) { compose.onRoot().performKeyInput { pressKey(key) }; compose.waitForIdle() }
    }

    private fun waitHidden(expected: Set<String>) {
        try {
            compose.waitUntil(10_000) { NavigationVisibility.hiddenSections == expected }
        } catch (failure: androidx.compose.ui.test.ComposeTimeoutException) {
            val stored = runBlocking { context.settingsDataStore.data.first()[hiddenNavigationSectionsKey(profile.value)] }
            throw AssertionError("Expected $expected, navigation=${NavigationVisibility.hiddenSections}, settings=${state.value.hiddenNavigationSections}, stored=$stored", failure)
        }
    }

    @Test fun tvRemoteTogglesEachCorrectRowAndHomeSettingsRemainReachable() {
        show()
        press(Key.DirectionDown, tvGeneralRowsForSection("appearance").indexOf(56))
        for ((index, section) in OPTIONAL_NAVIGATION_SECTIONS.withIndex()) {
            if (index > 0) press(Key.DirectionDown)
            press(Key.DirectionCenter)
            waitHidden(OPTIONAL_NAVIGATION_SECTIONS.take(index + 1).toSet())
            verify(exactly = 1) { model.setNavigationSectionVisible(section, false) }
        }
        compose.onNodeWithTag("topbar-item-HOME").assertIsDisplayed()
        compose.onNodeWithTag("topbar-item-SETTINGS").assertIsDisplayed()
        compose.onNodeWithTag("topbar-item-TV").assertDoesNotExist()
        java.io.File(context.getExternalFilesDir(null), "issue887-tv-settings.png").outputStream().use {
            InstrumentationRegistry.getInstrumentation().uiAutomation.takeScreenshot().compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
        }
        press(Key.Escape, 2)
        press(Key.DirectionLeft)
        press(Key.DirectionCenter)
        compose.runOnIdle { assertTrue(wentHome) }
    }

    @Test fun phoneAppearanceSwitchesPersistAndFilterTheRealBottomNavigation() {
        show(DeviceType.PHONE)
        compose.onNodeWithText(context.getString(com.arflix.tv.R.string.interface_label)).performScrollTo().performClick()
        compose.onNodeWithText("Show TV Player").performScrollTo().performClick()
        compose.onNodeWithText("Show Search").performScrollTo().performClick()
        waitHidden(setOf("search", "tv"))
        compose.runOnIdle { showSettings.value = false }
        compose.onNodeWithContentDescription("TV").assertDoesNotExist()
        compose.onNodeWithContentDescription("Search").assertDoesNotExist()
        compose.onNodeWithContentDescription("Settings").assertIsDisplayed()
        compose.onNodeWithContentDescription("Library").performClick()
        compose.runOnIdle { assertEquals("watchlist", selectedRoute) }
        assertEquals(setOf("search", "tv"), runBlocking { readHiddenNavigationSections(context.settingsDataStore.data.first()[hiddenNavigationSectionsKey(first)]) })
    }

    @Test fun tabletRailStillOffersHomeAndSettingsWhenAllOptionalTabsAreHidden() = runBlocking {
        context.settingsDataStore.edit { it[hiddenNavigationSectionsKey(first)] = encodeHiddenNavigationSections(OPTIONAL_NAVIGATION_SECTIONS) }
        show(DeviceType.TABLET, settings = false)
        waitHidden(OPTIONAL_NAVIGATION_SECTIONS.toSet())
        compose.onNodeWithContentDescription("Search").assertDoesNotExist()
        compose.onNodeWithContentDescription("Library").assertDoesNotExist()
        compose.onNodeWithContentDescription("TV").assertDoesNotExist()
        compose.onNodeWithContentDescription("Home").assertIsDisplayed()
        compose.onNodeWithContentDescription("Settings").performClick()
        compose.runOnIdle { assertEquals("settings", selectedRoute) }
    }

    @Test fun profilesRestoreOnlyTheirOwnChoicesWithoutRestarting() = runBlocking {
        context.settingsDataStore.edit {
            it[hiddenNavigationSectionsKey(first)] = """["tv"]"""
            it[hiddenNavigationSectionsKey(second)] = """["search"]"""
        }
        show(settings = false)
        waitHidden(setOf("tv"))
        compose.onNodeWithTag("topbar-item-TV").assertDoesNotExist()
        compose.runOnIdle { profile.value = second }
        waitHidden(setOf("search"))
        compose.onNodeWithTag("topbar-item-SEARCH").assertDoesNotExist()
        compose.onNodeWithTag("topbar-item-TV").assertIsDisplayed()
        compose.runOnIdle { profile.value = first }
        waitHidden(setOf("tv"))
    }

    @Test fun kidsProfilesCannotShowLiveTvThroughTheNewControls() {
        KidsNavigation.hideLiveTv = true
        state.value = state.value.copy(isKidsProfile = true)
        show()
        compose.onNodeWithText("Show TV Player").assertDoesNotExist()
        compose.onNodeWithTag("topbar-item-TV").assertDoesNotExist()
    }

    @Test fun webCloudFieldRestoresOnAndroidAndAndroidChangeIsWebReadable(): Unit = runBlocking {
        val external = InstrumentationRegistry.getArguments().getString("navigationSyncUrl")
        val server = if (external == null) MockWebServer().apply {
            enqueue(MockResponse().setBody("""{"payload":{"profileSettingsById":{"$first":{"hiddenNavigationSections":["tv"]}}}}"""))
            enqueue(MockResponse().setBody("""{"accepted":true}"""))
            start()
        } else null
        val url = external ?: server!!.url("/fixture/payload").toString()
        try {
            val read = URL(url).openConnection().apply { connectTimeout = 5_000; readTimeout = 5_000 }
            val root = JSONObject(read.getInputStream().bufferedReader().use { it.readText() }).getJSONObject("payload")
            val cloud = Gson().fromJson(root.getJSONObject("profileSettingsById").getJSONObject(first).toString(), CloudProfileSettings::class.java)
            context.settingsDataStore.edit { restoreNavigationSections(it, first, cloud.hiddenNavigationSections) }
            show()
            waitHidden(setOf("tv"))
            compose.onNodeWithTag("topbar-item-TV").assertDoesNotExist()
            press(Key.DirectionDown, tvGeneralRowsForSection("appearance").indexOf(57))
            press(Key.DirectionCenter)
            waitHidden(setOf("watchlist", "tv"))
            val wire = CloudProfileSettings(hiddenNavigationSections = navigationSectionsForCloud(context.settingsDataStore.data.first(), first))
            assertEquals(listOf("watchlist", "tv"), wire.hiddenNavigationSections)
            val body = JSONObject().put("profileId", first).put("settings", JSONObject(Gson().toJson(wire)))
                .put("updatedAt", System.currentTimeMillis()).toString()
            val connection = URL(url).openConnection() as HttpURLConnection
            try {
                connection.connectTimeout = 5_000
                connection.readTimeout = 5_000
                connection.requestMethod = "POST"
                connection.doOutput = true
                connection.setRequestProperty("Content-Type", "application/json")
                connection.outputStream.use { it.write(body.toByteArray()) }
                assertEquals(200, connection.responseCode)
            } finally { connection.disconnect() }
        } finally { server?.shutdown() }
    }
}
