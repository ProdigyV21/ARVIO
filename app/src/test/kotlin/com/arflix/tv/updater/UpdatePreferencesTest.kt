package com.arflix.tv.updater

import android.app.Application
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.arflix.tv.util.settingsDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class UpdatePreferencesTest {
    private val context: Application get() = RuntimeEnvironment.getApplication()
    private val quietKey = booleanPreferencesKey("app_update_quiet_notifications")

    @Before @After fun clearUpdatePreferences() = runBlocking {
        context.settingsDataStore.edit { it.remove(quietKey) }
        UpdatePreferences(context).setIgnoredTag(null)
        UpdatePreferences(context).setLastCheckAtMs(0L)
    }

    @Test fun quietNotificationsAreOffByDefault() = runBlocking {
        assertFalse(UpdatePreferences(context).quietNotifications.first())
    }

    @Test fun quietModePersistsWithoutChangingOtherUpdatePreferences() = runBlocking {
        val preferences = UpdatePreferences(context)
        preferences.setIgnoredTag("v2.4")
        preferences.setLastCheckAtMs(1234L)
        preferences.setQuietNotifications(true)

        val reopened = UpdatePreferences(context)
        assertTrue(reopened.quietNotifications.first())
        assertEquals(true, context.settingsDataStore.data.first()[quietKey])
        reopened.setQuietNotifications(false)
        assertFalse(preferences.quietNotifications.first())
        assertEquals("v2.4", reopened.ignoredTag.first())
        assertEquals(1234L, reopened.lastCheckAtMs.first())
    }
}
