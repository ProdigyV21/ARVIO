package com.arflix.tv.data.repository

import android.app.Application
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.arflix.tv.util.settingsDataStore
import dagger.Lazy
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class PartnerPreferencesTest {
    @Test fun `defaults restrictions overrides and removal preserve personal storage`() = runBlocking {
        val context = RuntimeEnvironment.getApplication()
        val profiles = mockk<ProfileManager>()
        every { profiles.activeProfileId } returns MutableStateFlow("partner-qa")
        every { profiles.getProfileIdSync() } returns "partner-qa"
        val partner = mockk<PartnerDeliveryRepository>()
        val delivery = PartnerIptvDelivery(1, Long.MAX_VALUE, emptyList(), "org",
            settings = mapOf("watchlist_on_home" to true, "trailer_auto_play" to false))
        val state = MutableStateFlow<PartnerIptvDelivery?>(delivery)
        var account = "customer-a"
        every { partner.deliveries } returns state
        every { partner.activeDelivery() } answers { state.value }
        every { partner.accountId() } answers { account }
        val preferences = PartnerPreferences(context, profiles, Lazy { partner })
        val watchlist = booleanPreferencesKey("profile_partner-qa_watchlist_on_home")
        val trailer = booleanPreferencesKey("profile_partner-qa_trailer_auto_play")
        context.settingsDataStore.edit { it.clear(); it[watchlist] = false; it[trailer] = true }

        assertEquals(true, preferences.data.first()[watchlist])
        preferences.edit { it[watchlist] = false; it[trailer] = true }
        assertEquals(true, preferences.data.first()[watchlist])
        assertEquals(false, preferences.data.first()[trailer])
        assertEquals(false, context.settingsDataStore.data.first()[watchlist])
        assertEquals(true, context.settingsDataStore.data.first()[trailer])

        state.value = delivery.copy(permissions = PartnerPermissions(editAppearance = true))
        // The explicit user choice equals the old raw value, but differs from the effective default.
        preferences.edit { it[watchlist] = false }
        assertEquals(false, preferences.data.first()[watchlist])
        assertEquals(true, context.settingsDataStore.data.first()[trailer])

        account = "customer-b"
        state.value = state.value!!.copy(revision = 2)
        assertEquals(true, preferences.data.first()[watchlist])
        state.value = null
        assertEquals(false, preferences.data.first()[watchlist])
        assertEquals(true, preferences.data.first()[trailer])
    }
}
