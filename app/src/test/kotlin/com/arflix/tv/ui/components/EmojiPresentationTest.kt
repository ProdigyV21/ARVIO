package com.arflix.tv.ui.components

import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class EmojiPresentationTest {

    @Test
    fun `addon symbols ask for the colour emoji`() {
        assertEquals("[PM⚡️] Comet", withEmojiPresentation("[PM⚡] Comet"))
        assertEquals("[PM⏳️]", withEmojiPresentation("[PM⏳]"))
    }

    @Test
    fun `existing variation selectors are kept`() {
        assertEquals("⚡️", withEmojiPresentation("⚡️"))
        assertEquals("⚡︎", withEmojiPresentation("⚡︎"))
    }

    @Test
    fun `text without such symbols is returned unchanged`() {
        val text = "Torrentio ▶ 1080p 🔌"
        assertSame(text, withEmojiPresentation(text))
    }

    @Test
    fun `normalizing mixed selectors is idempotent`() {
        val text = "\u26A1\u26A0\uFE0F\u2714\uFE0E\u2B50"
        val expected = "\u26A1\uFE0F\u26A0\uFE0F\u2714\uFE0E\u2B50\uFE0F"
        assertEquals(expected, withEmojiPresentation(text))
        assertEquals(expected, withEmojiPresentation(withEmojiPresentation(text)))
    }

    @Test
    fun `flags and supplementary emoji survive formatting`() {
        val suffix = " \uD83C\uDDEB\uD83C\uDDF7 \uD83C\uDF10 \uD83D\uDE00"
        assertEquals("\u26A1\uFE0F$suffix", withEmojiPresentation("\u26A1$suffix"))
    }

    @Test
    fun `joined emoji keep their joiner and existing selector`() {
        val expected = "\u2764\uFE0F\u200D\uD83D\uDD25"
        assertEquals(expected, withEmojiPresentation("\u2764\u200D\uD83D\uDD25"))
        assertEquals(expected, withEmojiPresentation(expected))
    }

    @Test
    fun `empty text stays empty`() {
        assertSame("", withEmojiPresentation(""))
    }
}
