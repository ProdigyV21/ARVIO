package com.arflix.tv.ui.screens.player.subtitles.bilingual

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BilingualSettingsCodecTest {

    @Test fun `defaults match the spec`() {
        val d = BilingualSubtitleSettings.DEFAULT
        assertEquals(BilingualDisplayMode.BILINGUAL, d.mode)
        assertEquals(BilingualOrder.ORIGINAL_FIRST, d.order)
        assertEquals("#FFFFFF", d.original.colorHex)
        assertEquals("#F4D979", d.translation.colorHex)
        assertEquals(SubtitleOutline.THIN, d.original.outline)
        assertEquals(SubtitleOutline.THIN, d.translation.outline)
        assert(d.original.sizeSp < d.translation.sizeSp)
    }

    @Test fun `round trip keeps every field`() {
        val s = BilingualSubtitleSettings(
            mode = BilingualDisplayMode.TRANSLATION_ONLY,
            order = BilingualOrder.TRANSLATION_FIRST,
            gapDp = 12,
            bottomPercent = 20,
            original = BilingualLineStyle(visible = false, colorHex = "#7FE3FF", sizeSp = 14, bold = true, font = "Lexend",
                outline = SubtitleOutline.THICK, outlineColorHex = "#112233", shadow = false, background = true, offsetDp = -30),
            translation = BilingualLineStyle(colorHex = "#FFE600", sizeSp = 30, offsetDp = 40),
        )
        assertEquals(s, BilingualSettingsCodec.decode(BilingualSettingsCodec.encode(s)))
    }

    @Test fun `colors are independent`() {
        val s = BilingualSubtitleSettings.DEFAULT.let {
            it.copy(original = it.original.copy(colorHex = "#FFFFFF"), translation = it.translation.copy(colorHex = "#FFE600"))
        }
        val back = BilingualSettingsCodec.decode(BilingualSettingsCodec.encode(s))
        assertEquals("#FFFFFF", back.original.colorHex)
        assertEquals("#FFE600", back.translation.colorHex)
    }

    @Test fun `garbage and missing values fall back safely`() {
        assertEquals(BilingualSubtitleSettings.DEFAULT, BilingualSettingsCodec.decode(null))
        assertEquals(BilingualSubtitleSettings.DEFAULT, BilingualSettingsCodec.decode("not json"))
        val partial = BilingualSettingsCodec.decode("""{"mode":"original","unknownKey":1}""")
        assertEquals(BilingualDisplayMode.ORIGINAL_ONLY, partial.mode)
        assertEquals("#F4D979", partial.translation.colorHex)
    }

    @Test fun `out of range values are clamped`() {
        val s = BilingualSettingsCodec.decode("""{"gapDp":999,"bottomPercent":-4,"original":{"sizeSp":2,"colorHex":"zzz","offsetDp":9999}}""")
        assertEquals(BilingualSubtitleSettings.MAX_GAP_DP, s.gapDp)
        assertEquals(0, s.bottomPercent)
        assertEquals(BilingualLineStyle.MIN_SIZE_SP, s.original.sizeSp)
        assertEquals("#FFFFFF", s.original.colorHex)
        assertEquals(BilingualLineStyle.MAX_OFFSET_DP, s.original.offsetDp)
    }

    @Test fun `hex parsing`() {
        assertEquals("#FFAA00", BilingualColors.normalizeHex("fa0"))
        assertEquals("#F4D979", BilingualColors.normalizeHex(" #f4d979 "))
        assertEquals("#80FFFFFF", BilingualColors.normalizeHex("#80ffffff"))
        assertNull(BilingualColors.normalizeHex("#12345"))
        assertNull(BilingualColors.normalizeHex("yellow"))
        assertEquals(0xFFF4D979.toInt(), BilingualColors.parseArgb("#F4D979"))
        assertEquals(0x80FFFFFF.toInt(), BilingualColors.parseArgb("#80FFFFFF"))
        assertEquals(7, BilingualColors.parseArgb("nope", 7))
    }
}
