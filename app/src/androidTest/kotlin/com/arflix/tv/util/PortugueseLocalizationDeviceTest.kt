package com.arflix.tv.util

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.R
import java.util.Locale
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PortugueseLocalizationDeviceTest {
    @Test fun brazilianResourcesAndFormattedStringsLoad() = preservingLocale {
        val context = localizedAppContext(InstrumentationRegistry.getInstrumentation().targetContext, "pt-BR")
        assertEquals("Configura\u00e7\u00f5es", context.getString(R.string.settings))
        assertEquals("Epis\u00f3dio 3", context.getString(R.string.episode, 3))
        assertEquals("12 canais ao vivo carregados da API do provedor", context.getString(R.string.iptv_loaded_api, 12))
        assertEquals("Salvar lista", context.getString(R.string.settings_save_playlist))
    }

    @Test fun europeanPortugueseKeepsRegionalWordingAndUsesFallback() = preservingLocale {
        val context = localizedAppContext(InstrumentationRegistry.getInstrumentation().targetContext, "pt-PT")
        assertEquals("Defini\u00e7\u00f5es", context.getString(R.string.settings))
        assertEquals("Salvar portal", context.getString(R.string.settings_save_portal))
    }

    @Test fun appOverrideCannotReplaceTheSystemDefault() = preservingLocale {
        val expected = defaultAppLanguage()
        localizedAppContext(InstrumentationRegistry.getInstrumentation().targetContext, "pt-BR")
        assertEquals(expected, defaultAppLanguage())
        localizedAppContext(InstrumentationRegistry.getInstrumentation().targetContext, "fr-FR")
        assertEquals(expected, defaultAppLanguage())
    }

    private fun preservingLocale(test: () -> Unit) {
        val original = Locale.getDefault()
        try { test() } finally { Locale.setDefault(original) }
    }
}
