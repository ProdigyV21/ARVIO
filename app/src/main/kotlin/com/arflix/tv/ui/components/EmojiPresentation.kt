package com.arflix.tv.ui.components

/**
 * Symbols that addons use as emoji ("[PM⚡]", "⏳", "✅") but that Unicode also defines as plain
 * text characters. Without a variation selector, Android may take the glyph from a monochrome
 * symbol font, so "⚡" shows black and white next to a coloured "⏳". Appending U+FE0F asks for the
 * colour emoji glyph. Arrows and ▶ are left alone: those are often meant as text.
 */
internal fun withEmojiPresentation(text: String): String {
    if (text.none { it in EMOJI_STYLE_SYMBOLS }) return text
    return buildString(text.length + 4) {
        text.forEachIndexed { index, char ->
            append(char)
            val next = text.getOrNull(index + 1)
            if (char in EMOJI_STYLE_SYMBOLS && next != EMOJI_VARIATION && next != TEXT_VARIATION) {
                append(EMOJI_VARIATION)
            }
        }
    }
}

private const val EMOJI_VARIATION = '️'
private const val TEXT_VARIATION = '︎'

private val EMOJI_STYLE_SYMBOLS: Set<Char> = setOf(
    '⌚', // ⌚
    '⌛', // ⌛
    '⏰', // ⏰
    '⏱', // ⏱
    '⏳', // ⏳
    '☀', // ☀
    '☁', // ☁
    '☑', // ☑
    '⚙', // ⚙
    '⚠', // ⚠
    '⚡', // ⚡
    '⛔', // ⛔
    '✅', // ✅
    '✔', // ✔
    '✖', // ✖
    '✨', // ✨
    '❌', // ❌
    '❓', // ❓
    '❗', // ❗
    '❤', // ❤
    '⭐', // ⭐
)
