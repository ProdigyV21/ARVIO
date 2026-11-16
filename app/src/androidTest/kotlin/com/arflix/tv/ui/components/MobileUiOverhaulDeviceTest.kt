package com.arflix.tv.ui.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.core.app.ApplicationProvider
import com.arflix.tv.R
import com.arflix.tv.data.repository.effectiveSettingsDataStore as settingsDataStore
import com.arflix.tv.ui.screens.settings.InputField
import com.arflix.tv.ui.screens.settings.MobileConnectionForm
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.profilesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MobileUiOverhaulDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun ordinaryAudioChoiceDoesNotWasteAnotherRow() {
        compose.setContent {
            Box(Modifier.width(328.dp)) {
                MobileSettingsRow(title = "Default Audio", value = "Auto (Original)", isToggle = false, onClick = {})
            }
        }
        val title = compose.onNodeWithText("Default Audio").getUnclippedBoundsInRoot()
        val value = compose.onNodeWithText("Auto (Original)").getUnclippedBoundsInRoot()
        val titleCenter = (title.top.value + title.bottom.value) / 2f
        val valueCenter = (value.top.value + value.bottom.value) / 2f
        assertTrue(kotlin.math.abs(titleCenter - valueCenter) < 2f)
    }

    @Test fun heroChoicePersistsAndIsIndependentOfCatalogLayout() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val profile = runBlocking { context.profilesDataStore.data.first()[stringPreferencesKey("active_profile_id")] ?: "default" }
        val key = mobileHeroStyleKey(profile)
        val original = runBlocking { context.settingsDataStore.data.first()[key] }
        val cardKey = stringPreferencesKey("profile_${profile}_card_layout_mode")
        val originalCards = runBlocking { context.settingsDataStore.data.first()[cardKey] }
        val shown = mutableStateOf(true)
        try {
            runBlocking { context.settingsDataStore.edit { it[key] = MobileHomeHeroStyle.IMMERSIVE.name } }
            compose.setContent {
                CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                    if (shown.value) Column {
                        Text(rememberMobileHomeHeroStyle().name)
                        MobileHomeHeroStyleSetting()
                    }
                }
            }
            fun choose(label: Int, expected: MobileHomeHeroStyle) {
                compose.onNodeWithText(context.getString(R.string.mobile_home_hero_style)).performClick()
                compose.onAllNodesWithText(context.getString(label)).onLast().performClick()
                compose.waitUntil(5000) { compose.onAllNodesWithText(expected.name).fetchSemanticsNodes().isNotEmpty() }
                assertEquals(expected.name, runBlocking { context.settingsDataStore.data.first()[key] })
                assertEquals(originalCards, runBlocking { context.settingsDataStore.data.first()[cardKey] })
            }
            choose(R.string.mobile_hero_poster, MobileHomeHeroStyle.POSTER)
            compose.runOnIdle { shown.value = false }
            compose.waitForIdle()
            compose.runOnIdle { shown.value = true }
            compose.waitUntil(5000) { compose.onAllNodesWithText("POSTER").fetchSemanticsNodes().isNotEmpty() }
            choose(R.string.mobile_hero_immersive, MobileHomeHeroStyle.IMMERSIVE)
        } finally {
            runBlocking { context.settingsDataStore.edit { if (original == null) it.remove(key) else it[key] = original } }
        }
    }

    @Test fun longSettingsAndChoiceRemainReadableAndClickable() {
        var opened = 0
        var enabled = false
        compose.setContent {
            Box(Modifier.width(300.dp)) {
                Column {
                    MobileSettingsRow(title = "Anime Episode Ordering", subtitle = "Switch between Broadcast Order and Standard seasons",
                        value = "Broadcast Order", isToggle = false, onClick = { opened++ })
                    MobileSettingsRow(title = "Secondary Subtitle Language", value = "Off", toggleChecked = null,
                        isToggle = false, onClick = { opened++ })
                    MobileSettingsRow(title = "Show watchlist on home", subtitle = "Place your watchlist below Continue Watching.",
                        value = "Off", toggleChecked = enabled, onClick = { enabled = !enabled })
                }
            }
        }
        compose.onNodeWithText("Anime Episode Ordering").assertIsDisplayed().performClick()
        compose.onNodeWithText("Broadcast Order").assertIsDisplayed().performClick()
        compose.onNodeWithText("Secondary Subtitle Language").assertIsDisplayed()
        compose.onNodeWithText("Show watchlist on home").performClick()
        compose.runOnIdle { assertEquals(2, opened); assertTrue(enabled) }
    }

    @Test fun mobileFormKeepsConfirmationReachableWithKeyboard() {
        var confirmed = false
        val url = mutableStateOf("")
        compose.setContent {
            MobileConnectionForm("Home Server", "Use HTTPS where possible. HTTP is not encrypted.",
                listOf(InputField("Server URL", url.value, onValueChange = { url.value = it }),
                    InputField("Username", "", onValueChange = {}),
                    InputField("Password / token", "", isSecret = true, onValueChange = {})), emptyList(),
                onConfirm = { confirmed = true }, onDismiss = {})
        }
        compose.onNodeWithTag("connection-field-0").performTextInput("https://server.example")
        compose.onNodeWithText("Confirm").assertIsDisplayed().performClick()
        compose.runOnIdle { assertEquals("https://server.example", url.value); assertTrue(confirmed) }
    }

    @Test fun cloudSignInActionsRemainReachableWithKeyboard() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val email = mutableStateOf("")
        var created = false
        var privacyOpened = false
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                CloudEmailPasswordModal(email.value, "", onEmailChange = { email.value = it },
                    onPasswordChange = {}, onDismiss = {}, onSignIn = {},
                    onCreateAccount = { created = true }, onOpenPrivacy = { privacyOpened = true })
            }
        }
        compose.onAllNodes(hasSetTextAction()).onFirst().performTextInput("review@example.invalid")
        compose.onNodeWithText(context.getString(R.string.settings_btn_create)).performScrollTo().performClick()
        compose.onNodeWithText(context.getString(R.string.login_read_privacy_policy)).performScrollTo().performClick()
        compose.runOnIdle { assertTrue(created); assertTrue(privacyOpened) }
    }
}
