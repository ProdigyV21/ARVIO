package com.arflix.tv.util

import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import java.util.Locale
import org.junit.Assert.assertEquals
import org.junit.Test

class AppLanguageTest {
    @Test fun portugueseDefaultsDistinguishBrazilAndPortugal() {
        mapOf("pt-BR" to "pt-BR", "pt" to "pt-BR", "pt-AO" to "pt-BR", "pt-PT" to "pt-PT")
            .forEach { (device, expected) ->
                assertEquals(device, expected, defaultAppLanguage(Locale.forLanguageTag(device)))
            }
    }

    @Test fun detectedLanguagesUseSettingsLanguageTags() {
        listOf("nl-NL", "fr-FR", "es-ES", "de-DE", "it-IT", "ru-RU", "tr-TR", "ar-SA",
            "hi-IN", "ja-JP", "ko-KR", "pl-PL", "en-US").forEach {
            assertEquals(it, it, defaultAppLanguage(Locale.forLanguageTag(it)))
        }
        assertEquals("en-US", defaultAppLanguage(Locale.forLanguageTag("zz-ZZ")))
    }

    @Test fun chineseScriptTakesPriorityOverRegion() {
        mapOf("zh" to "zh-CN", "zh-HK" to "zh-TW", "zh-Hant" to "zh-TW",
            "zh-Hans-TW" to "zh-CN").forEach { (device, expected) ->
            assertEquals(device, expected, defaultAppLanguage(Locale.forLanguageTag(device)))
        }
    }

    @Test fun savedProfileChoiceWinsWithoutConsultingSystemLocale() {
        val preferences = mutablePreferencesOf(
            stringPreferencesKey("profile_primary_content_language") to "en-US",
            LAST_APP_LANGUAGE_KEY to "pt-BR"
        )
        assertEquals("en-US", resolveAppLanguage(preferences, "primary") { error("Unexpected fallback") })
    }

    @Test fun missingProfileUsesLastChoiceForEveryConsumer() {
        val preferences = mutablePreferencesOf(LAST_APP_LANGUAGE_KEY to "pt-BR")
        listOf(null, "", "secondary").forEach {
            assertEquals("pt-BR", resolveAppLanguage(preferences, it) { error("Unexpected fallback") })
        }
    }

    @Test fun missingOrBlankPreferencesUseDeviceDefault() {
        assertEquals("pt-BR", resolveAppLanguage(mutablePreferencesOf(), "primary") { "pt-BR" })
        val blank = mutablePreferencesOf(
            stringPreferencesKey("profile_primary_content_language") to " ", LAST_APP_LANGUAGE_KEY to ""
        )
        assertEquals("pt-PT", resolveAppLanguage(blank, "primary") { "pt-PT" })
    }
}
