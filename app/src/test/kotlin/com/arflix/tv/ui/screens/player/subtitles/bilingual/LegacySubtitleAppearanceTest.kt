package com.arflix.tv.ui.screens.player.subtitles.bilingual

import com.arflix.tv.ui.screens.player.SubtitleFontOption
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/** Pure JVM regression tests: no Context, Media3, Compose, emulator, or Android SDK required. */
class LegacySubtitleAppearanceTest {
    private val defaults = BilingualSubtitleSettings.DEFAULT

    @Test fun `a new profile keeps independent bilingual defaults`() {
        val legacy = LegacySubtitleAppearance()
        val initialized = legacy.initialize(defaults, hasBilingualSettings = false)
        assertEquals(defaults, initialized.copy(legacyAppearance = null))
        assertEquals(initialized, legacy.resolve(initialized))
    }

    @Test fun `first TV adjustment after pristine initialization keeps the bilingual size hierarchy`() {
        val empty = LegacySubtitleAppearance()
        val anchor = empty.initialize(defaults, false)
        val savedAnchor = BilingualSettingsCodec.decode(BilingualSettingsCodec.encode(anchor))
        val adjusted = empty.copy(sizePercent = 150).resolve(savedAnchor)
        assertEquals(26, adjusted.original.sizeSp)
        assertEquals(32, adjusted.translation.sizeSp)
        assertEquals(defaults.translation.colorHex, adjusted.translation.colorHex)
    }

    @Test fun `saved named preferences migrate once and numeric values win`() {
        val legacy = LegacySubtitleAppearance(
            size = "Small", sizePercent = 150, color = "Cyan", position = "Low",
            verticalPercent = 37, style = "Normal", font = "Lexend", stylized = false,
        )
        val migrated = legacy.initialize(defaults, hasBilingualSettings = false)
        assertEquals(36, migrated.original.sizeSp)
        assertEquals(36, migrated.translation.sizeSp)
        assertEquals("#00FFFF", migrated.original.colorHex)
        assertEquals("#00FFFF", migrated.translation.colorHex)
        assertEquals(37, migrated.bottomPercent)
        assertEquals("Lexend", migrated.translation.font)
        assertFalse(migrated.translation.bold)
        assertFalse(migrated.translation.shadow)
        assertFalse(migrated.translation.background)
        assertEquals(SubtitleOutline.NONE, migrated.translation.outline)
        // Migration, encode/decode, repeated playback and a subsequent render never yield 54sp.
        val restored = BilingualSettingsCodec.decode(BilingualSettingsCodec.encode(migrated))
        assertEquals(migrated, restored)
        assertEquals(migrated, legacy.initialize(restored, hasBilingualSettings = true))
        assertEquals(migrated, legacy.resolve(restored))
        assertEquals(migrated, legacy.resolve(legacy.resolve(restored)))
    }

    @Test fun `every legacy size and position preset is supported without numeric prefs`() {
        listOf("Small" to 19, "Medium" to 24, "Large" to 29, "Extra Large" to 34).forEach { (name, sp) ->
            val migrated = LegacySubtitleAppearance(size = name).initialize(defaults, false)
            assertEquals(name, sp, migrated.translation.sizeSp)
        }
        listOf("Bottom" to 2, "Low" to 8, "Medium" to 15, "High" to 25, "Top" to 88).forEach { (name, percent) ->
            val migrated = LegacySubtitleAppearance(position = name).initialize(defaults, false)
            assertEquals(name, percent, migrated.bottomPercent)
        }
    }

    @Test fun `only saved legacy fields are imported`() {
        val migrated = LegacySubtitleAppearance(font = "Rubik").initialize(defaults, false)
        assertEquals(defaults.original.copy(font = "Rubik"), migrated.original)
        assertEquals(defaults.translation.copy(font = "Rubik"), migrated.translation)
        assertEquals(defaults.bottomPercent, migrated.bottomPercent)
    }

    @Test fun `existing bilingual configuration wins including explicit default values`() {
        val existing = defaults.copy(
            bottomPercent = 12,
            original = defaults.original.copy(sizeSp = 16, font = "Rubik", offsetDp = 9),
            translation = defaults.translation.copy(sizeSp = 24, colorHex = "#AABBCC", offsetDp = -5),
        )
        val legacy = LegacySubtitleAppearance(sizePercent = 180, color = "Green", style = "Background", font = "Lexend", verticalPercent = 30)
        val initialized = legacy.initialize(existing, hasBilingualSettings = true)
        assertEquals(existing, initialized.copy(legacyAppearance = null))
        assertEquals(initialized, legacy.resolve(initialized))
        // A file whose styles happen to equal DEFAULT is still an intentional bilingual file.
        assertEquals(defaults, legacy.initialize(defaults, true).copy(legacyAppearance = null))
    }

    @Test fun `live TV size and position are relative to baseline and preserve independent fields`() {
        val baseline = LegacySubtitleAppearance(sizePercent = 150, verticalPercent = 10)
        val anchor = baseline.initialize(defaults.copy(
            original = defaults.original.copy(sizeSp = 16, offsetDp = 7),
            translation = defaults.translation.copy(sizeSp = 24, offsetDp = -9),
            bottomPercent = 20,
        ), true)
        val live = baseline.copy(sizePercent = 180, verticalPercent = 25)
        val shown = live.resolve(anchor)
        assertEquals(19, shown.original.sizeSp)
        assertEquals(29, shown.translation.sizeSp)
        assertEquals(35, shown.bottomPercent)
        assertEquals(7, shown.original.offsetDp)
        assertEquals(-9, shown.translation.offsetDp)
        assertEquals(anchor.original.colorHex, shown.original.colorHex)
        assertEquals(anchor.translation.colorHex, shown.translation.colorHex)
        assertEquals(shown, live.resolve(shown))
        // Rendering each change from the original anchor avoids rounding/clamping drift.
        assertEquals(anchor, baseline.resolve(anchor))
        assertEquals(shown, live.resolve(BilingualSettingsCodec.decode(BilingualSettingsCodec.encode(anchor))))
    }

    @Test fun `later legacy color style and font controls affect both language blocks`() {
        val baseline = LegacySubtitleAppearance(color = "Yellow", style = "Bold", font = "System")
        val anchor = baseline.initialize(defaults, true)
        val live = baseline.copy(color = "Red", style = "Background", font = "Noto Sans")
        val shown = live.resolve(anchor)
        listOf(shown.original, shown.translation).forEach { line ->
            assertEquals("#FF0000", line.colorHex)
            assertEquals("Noto Sans", line.font)
            assertTrue(line.bold)
            assertTrue(line.background)
            assertFalse(line.shadow)
            assertEquals(SubtitleOutline.NONE, line.outline)
        }
        // Returning controls to the captured baseline restores the independent bilingual styles.
        assertEquals(anchor, baseline.resolve(anchor))
    }

    @Test fun `a bilingual edit or reset reanchors to the current legacy values`() {
        val baseline = LegacySubtitleAppearance(sizePercent = 150, color = "White", verticalPercent = 8)
        val anchor = baseline.initialize(defaults, true)
        val live = baseline.copy(sizePercent = 200, color = "Green", verticalPercent = 20)
        val shown = live.resolve(anchor)
        // This is the settings store's edit contract: transform resolved settings and save the
        // current snapshot. The explicit source size/color must not be multiplied/overridden.
        val edited = shown.copy(original = shown.original.copy(sizeSp = 25, colorHex = "#112233"))
        val restored = BilingualSettingsCodec.decode(BilingualSettingsCodec.encode(edited))
        assertEquals(25, live.resolve(restored).original.sizeSp)
        assertEquals("#112233", live.resolve(restored).original.colorHex)
        assertEquals(shown.translation, live.resolve(restored).translation)
        val reset = defaults.copy(legacyAppearance = live)
        assertEquals(defaults, live.resolve(reset).copy(legacyAppearance = null))
    }

    @Test fun `all legacy colors styles and bundled fonts have mappings`() {
        listOf("White" to "#FFFFFF", "Yellow" to "#FFFF00", "Green" to "#00FF00", "Cyan" to "#00FFFF", "Red" to "#FF0000", "#aabbcc" to "#AABBCC").forEach { (name, hex) ->
            assertEquals(hex, LegacySubtitleAppearance(color = name).initialize(defaults, false).translation.colorHex)
        }
        listOf("Bold", "Outline", "Normal", "Background").forEach { name ->
            val line = LegacySubtitleAppearance(style = name).initialize(defaults, false).translation
            assertEquals(name != "Normal", line.bold)
            assertEquals(name == "Background", line.background)
            assertEquals(if (name == "Normal" || name == "Background") SubtitleOutline.NONE else SubtitleOutline.THIN, line.outline)
            assertFalse(line.shadow)
        }
        SubtitleFontOption.entries.forEach { font ->
            assertEquals(font.preferenceValue, LegacySubtitleAppearance(font = font.preferenceValue).initialize(defaults, false).translation.font)
        }
    }

    @Test fun `appearance changes preserve display generation language visibility and ordering`() {
        val baseline = LegacySubtitleAppearance(sizePercent = 100, verticalPercent = 2)
        val configured = defaults.copy(
            smartEnabled = true,
            mode = BilingualDisplayMode.TRANSLATION_ONLY,
            generationMode = BilingualGenerationMode.MERGE,
            order = BilingualOrder.TRANSLATION_FIRST,
            primaryLanguage = "ja",
            secondaryLanguage = "zh-Hant",
            gapDp = 9,
            original = defaults.original.copy(visible = false),
        )
        val anchor = baseline.initialize(configured, true)
        val shown = baseline.copy(sizePercent = 120, verticalPercent = 15, style = "Background").resolve(anchor)
        assertEquals(configured.mode, shown.mode)
        assertEquals(configured.generationMode, shown.generationMode)
        assertEquals(configured.order, shown.order)
        assertEquals(configured.smartEnabled, shown.smartEnabled)
        assertEquals(configured.primaryLanguage, shown.primaryLanguage)
        assertEquals(configured.secondaryLanguage, shown.secondaryLanguage)
        assertEquals(configured.gapDp, shown.gapDp)
        assertFalse(shown.original.visible)
    }

    @Test fun `legacy bounds invalid values and authored styling are safe`() {
        val large = LegacySubtitleAppearance(sizePercent = 999, verticalPercent = 999, color = "garbage", font = "missing")
            .initialize(defaults, false)
        assertEquals(60, large.translation.sizeSp)
        assertEquals(88, large.bottomPercent)
        assertEquals("#FFFFFF", large.translation.colorHex)
        assertEquals("System", large.translation.font)
        val small = LegacySubtitleAppearance(sizePercent = -10, verticalPercent = -10).initialize(defaults, false)
        assertEquals(12, small.translation.sizeSp)
        assertEquals(1, small.bottomPercent)
        val baseline = LegacySubtitleAppearance(stylized = true)
        val anchor = baseline.initialize(defaults, true)
        // Authored Cue styles are not available in the plain-text AI bus. This flag must not
        // erase independent styles, alter size or synthesize an unrelated appearance.
        assertEquals(anchor.copy(legacyAppearance = baseline.copy(stylized = false)), baseline.copy(stylized = false).resolve(anchor))
    }
}
