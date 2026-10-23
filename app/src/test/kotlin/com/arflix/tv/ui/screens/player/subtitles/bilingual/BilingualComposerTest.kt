package com.arflix.tv.ui.screens.player.subtitles.bilingual

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BilingualComposerTest {

    private fun frame(original: String = "Hello there.", translation: String? = "你好。", failed: Boolean = false) =
        BilingualFrame(generation = 1, sourceKey = original, original = original, translation = translation, translationFailed = failed)

    private val defaults = BilingualSubtitleSettings.DEFAULT

    @Test fun `bilingual shows English on top and Chinese below by default`() {
        val blocks = BilingualComposer.compose(frame(), defaults)
        assertEquals(listOf(BilingualRole.ORIGINAL, BilingualRole.TRANSLATION), blocks.map { it.role })
        assertEquals(listOf("Hello there.", "你好。"), blocks.map { it.text })
    }

    @Test fun `swapped order puts Chinese on top`() {
        val blocks = BilingualComposer.compose(frame(), defaults.copy(order = BilingualOrder.TRANSLATION_FIRST))
        assertEquals(listOf(BilingualRole.TRANSLATION, BilingualRole.ORIGINAL), blocks.map { it.role })
    }

    @Test fun `pending translation shows English only, then both`() {
        assertEquals(listOf(BilingualRole.ORIGINAL), BilingualComposer.compose(frame(translation = null), defaults).map { it.role })
        assertEquals(2, BilingualComposer.compose(frame(), defaults).size)
    }

    @Test fun `failed translation keeps English on screen in every mode except off`() {
        val failed = frame(translation = null, failed = true)
        for (mode in listOf(BilingualDisplayMode.BILINGUAL, BilingualDisplayMode.TRANSLATION_ONLY, BilingualDisplayMode.ORIGINAL_ONLY)) {
            val blocks = BilingualComposer.compose(failed, defaults.copy(mode = mode))
            assertEquals("mode $mode", listOf("Hello there."), blocks.map { it.text })
        }
        assertTrue(BilingualComposer.compose(failed, defaults.copy(mode = BilingualDisplayMode.OFF)).isEmpty())
    }

    @Test fun `original only and translation only`() {
        assertEquals(listOf("Hello there."), BilingualComposer.compose(frame(), defaults.copy(mode = BilingualDisplayMode.ORIGINAL_ONLY)).map { it.text })
        assertEquals(listOf("你好。"), BilingualComposer.compose(frame(), defaults.copy(mode = BilingualDisplayMode.TRANSLATION_ONLY)).map { it.text })
    }

    @Test fun `off and null frame render nothing`() {
        assertTrue(BilingualComposer.compose(frame(), defaults.copy(mode = BilingualDisplayMode.OFF)).isEmpty())
        assertTrue(BilingualComposer.compose(null, defaults).isEmpty())
    }

    @Test fun `hidden language is skipped but never leaves the screen blank`() {
        val hideEn = defaults.copy(original = defaults.original.copy(visible = false))
        assertEquals(listOf(BilingualRole.TRANSLATION), BilingualComposer.compose(frame(), hideEn).map { it.role })
        // Translation not there yet: the hidden original still shows rather than nothing.
        assertEquals(listOf(BilingualRole.ORIGINAL), BilingualComposer.compose(frame(translation = null), hideEn).map { it.role })
        val hideZh = defaults.copy(translation = defaults.translation.copy(visible = false))
        assertEquals(listOf(BilingualRole.ORIGINAL), BilingualComposer.compose(frame(), hideZh).map { it.role })
    }

    @Test fun `multi-line and multi-cue text is kept intact`() {
        val f = frame(original = "- Where are you going?\n- Home.", translation = "- 你要去哪？\n- 回家。")
        val blocks = BilingualComposer.compose(f, defaults)
        assertEquals("- Where are you going?\n- Home.", blocks[0].text)
        assertEquals("- 你要去哪？\n- 回家。", blocks[1].text)
    }

    @Test fun `identical translation is not shown twice`() {
        val blocks = BilingualComposer.compose(frame(original = "OK", translation = "OK"), defaults)
        assertEquals(1, blocks.size)
    }
}
