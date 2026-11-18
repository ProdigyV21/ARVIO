package com.arflix.tv.ui.screens.player.subtitles.bilingual

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class SubtitleLanguageTest {

    private fun tag(raw: String?) = SubtitleLanguage.parse(raw)?.tag

    @Test fun `ISO 639-2 and names normalise to 639-1`() {
        assertEquals("en", tag("eng"))
        assertEquals("en", tag("English"))
        assertEquals("ja", tag("jpn"))
        assertEquals("de", tag("ger"))
        assertEquals("de", tag("deu"))
        assertEquals("fr", tag("fre"))
        assertEquals("he", tag("iw"))
        assertEquals("pt", tag("pt-BR"))
        assertNull(tag("und"))
        assertNull(tag(""))
        assertNull(tag(null))
    }

    @Test fun `Chinese scripts are distinguished and bare zh stays ambiguous`() {
        assertEquals("zh-Hans", tag("zh-Hans"))
        assertEquals("zh-Hans", tag("zh-CN"))
        assertEquals("zh-Hans", tag("zh_SG"))
        assertEquals("zh-Hant", tag("zh-Hant"))
        assertEquals("zh-Hant", tag("zh-TW"))
        assertEquals("zh-Hant", tag("zh-HK"))
        assertEquals("zh-Hant", tag("zh-Hant-TW"))
        assertEquals("zh-Hans", tag("zh-Hans-HK")) // explicit script beats region
        assertEquals("zh", tag("zh"))
        assertEquals("zh", tag("zho"))
        assertEquals("zh", tag("chi"))
        assertEquals("zh-Hant", tag("yue"))
        assertEquals("zh-Hans", tag("简体中文"))
        assertEquals("zh-Hant", tag("繁體中文"))
        assertEquals("zh-Hans", tag("Chinese (Simplified)"))
        assertEquals("zh-Hant", tag("Traditional Chinese"))
    }

    @Test fun `track title adds the script to an ambiguous chi tag`() {
        assertEquals("zh-Hans", SubtitleLanguage.ofTrack("chi", "简体")?.tag)
        assertEquals("zh-Hans", SubtitleLanguage.ofTrack("chi", "CHS")?.tag)
        assertEquals("zh-Hant", SubtitleLanguage.ofTrack("chi", "Chinese Traditional")?.tag)
        assertEquals("zh", SubtitleLanguage.ofTrack("chi", "Chinese")?.tag)
        assertEquals("zh", SubtitleLanguage.ofTrack("chi", "简繁双语")?.tag) // both scripts named
        // The tag's explicit script is never overridden by the title.
        assertEquals("zh-Hant", SubtitleLanguage.ofTrack("zh-TW", "简体")?.tag)
        // "gb" only as a word: "Rugby commentary" is not Simplified.
        assertEquals("en", SubtitleLanguage.ofTrack("eng", "Rugby commentary")?.tag)
    }

    @Test fun `matcher never treats Simplified and Traditional as equal`() {
        val hans = SubtitleLanguage.SIMPLIFIED_CHINESE
        val hant = SubtitleLanguage.TRADITIONAL_CHINESE
        assertEquals(LanguageMatch.EXACT, SubtitleLanguageMatcher.match(SubtitleLanguage.parse("zh-CN"), hans))
        assertEquals(LanguageMatch.NONE, SubtitleLanguageMatcher.match(SubtitleLanguage.parse("zh-TW"), hans))
        assertEquals(LanguageMatch.NONE, SubtitleLanguageMatcher.match(SubtitleLanguage.parse("zh-Hans"), hant))
        assertEquals(LanguageMatch.AMBIGUOUS_SCRIPT, SubtitleLanguageMatcher.match(SubtitleLanguage.parse("zho"), hans))
        assertEquals(LanguageMatch.NONE, SubtitleLanguageMatcher.match(SubtitleLanguage.parse("ja"), hans))
        assertEquals(LanguageMatch.EXACT, SubtitleLanguageMatcher.match(SubtitleLanguage.parse("eng"), SubtitleLanguage.ENGLISH))
    }

    @Test fun `same language detection`() {
        assertTrue(SubtitleLanguageMatcher.sameLanguage(SubtitleLanguage.parse("en"), SubtitleLanguage.parse("eng")))
        assertFalse(SubtitleLanguageMatcher.sameLanguage(SubtitleLanguage.parse("zh-Hans"), SubtitleLanguage.parse("zh-Hant")))
        assertTrue(SubtitleLanguageMatcher.sameLanguage(SubtitleLanguage.parse("zh"), SubtitleLanguage.parse("zh-Hans")))
        assertFalse(SubtitleLanguageMatcher.sameLanguage(SubtitleLanguage.parse("en"), SubtitleLanguage.parse("ja")))
    }

    @Test fun `AI target names are unambiguous`() {
        assertEquals("Simplified Chinese", SubtitleLanguage.SIMPLIFIED_CHINESE.translationTargetName)
        assertEquals("Traditional Chinese", SubtitleLanguage.TRADITIONAL_CHINESE.translationTargetName)
        assertEquals("Japanese", SubtitleLanguage.parse("ja")!!.translationTargetName)
    }

    @Test fun `settings sanitise languages and default secondary is Simplified Chinese`() {
        val d = BilingualSubtitleSettings()
        assertFalse(d.smartEnabled)
        assertEquals("auto", d.primaryLanguage)
        assertEquals("zh-Hans", d.secondary.tag)
        val s = BilingualSubtitleSettings(primaryLanguage = "jpn", secondaryLanguage = "zh-TW").sanitized()
        assertEquals("ja", s.primaryLanguage)
        assertEquals("zh-Hant", s.secondaryLanguage)
        val bad = BilingualSubtitleSettings(primaryLanguage = "???", secondaryLanguage = "nope!").sanitized()
        assertEquals("auto", bad.primaryLanguage)
        assertEquals("zh-Hans", bad.secondaryLanguage)
        // Round-trip through the persisted JSON.
        val back = BilingualSettingsCodec.decode(BilingualSettingsCodec.encode(s.copy(smartEnabled = true)))
        assertTrue(back.smartEnabled)
        assertEquals("ja", back.primaryLanguage)
        assertEquals("zh-Hant", back.secondaryLanguage)
    }
}
