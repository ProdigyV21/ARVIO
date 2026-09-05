package com.arflix.tv.util

import androidx.datastore.preferences.core.mutablePreferencesOf
import com.arflix.tv.ui.screens.home.readHomeProfilePreferences
import java.util.Locale
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28], qualifiers = "pt-rBR")
class AppLanguageSystemTest {
    @Test fun appLocaleOverrideDoesNotChangeDeviceDefault() {
        val original = Locale.getDefault()
        try {
            Locale.setDefault(Locale.US)
            assertEquals("pt-BR", defaultAppLanguage())
            assertEquals("pt-BR", readHomeProfilePreferences(mutablePreferencesOf(), "primary").contentLanguage)
        } finally {
            Locale.setDefault(original)
        }
    }

    @Test fun inheritedLanguageIsStableAcrossProfileSwitches() {
        val preferences = mutablePreferencesOf(LAST_APP_LANGUAGE_KEY to "en-US")
        assertEquals("en-US", readHomeProfilePreferences(preferences, "primary").contentLanguage)
        assertEquals("en-US", readHomeProfilePreferences(preferences, "secondary").contentLanguage)
    }
}
