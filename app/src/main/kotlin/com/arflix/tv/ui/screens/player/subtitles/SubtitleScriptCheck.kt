package com.arflix.tv.ui.screens.player.subtitles

/**
 * Spots translated lines the model garbled by slipping into another writing system, so the caller
 * can ask for just those lines again.
 *
 * The fast translation models occasionally emit a few letters of an unrelated script in the middle
 * of a word ("Σول" for "Σολ", "συνεργάate" for "συνεργάτε" when translating to Greek). The reply is
 * valid JSON with the right line count, so nothing upstream notices and the garbled word is cached
 * and shown. Measured on gemini-3.5-flash-lite, English to Greek: 5 of 538 and 9 of 720 lines in two
 * episodes. A second request for the same line almost always comes back clean.
 *
 * Everything here is pure (no Android, no network) so it can be unit tested.
 */
internal enum class ScriptFamily {
    LATIN, GREEK, CYRILLIC, ARMENIAN, HEBREW, ARABIC, INDIC, THAI_LAO, GEORGIAN, EAST_ASIAN, OTHER
}

/** Scripts written with spaces between words, where a word mixing two alphabets is a real anomaly. */
private val SPACED_SCRIPTS = setOf(
    ScriptFamily.GREEK, ScriptFamily.CYRILLIC, ScriptFamily.ARMENIAN, ScriptFamily.GEORGIAN,
    ScriptFamily.HEBREW, ScriptFamily.ARABIC
)

// Below this the batch is too small to tell which script the translation is in (a two-word reply
// that is mostly a brand name would be misread), so the check stays out of the way.
private const val MIN_LETTERS_FOR_SCRIPT_CHECK = 20
private const val DOMINANT_SCRIPT_PERCENT = 60
// One stray Latin letter inside a Greek or Cyrillic word is usually an invisible look-alike
// ("o" for "ο"); two or more is the visible garbling worth a second request.
private const val MIN_LATIN_LETTERS_IN_MIXED_WORD = 2

/** The script family of a letter, or null for anything that is not a letter. */
internal fun scriptFamilyOf(codePoint: Int): ScriptFamily? {
    if (!Character.isLetter(codePoint)) return null
    return when (codePoint) {
        in 0x0041..0x024F, in 0x1E00..0x1EFF, in 0xFF21..0xFF5A -> ScriptFamily.LATIN
        in 0x0370..0x03FF, in 0x1F00..0x1FFF -> ScriptFamily.GREEK
        in 0x0400..0x052F, in 0x2DE0..0x2DFF, in 0xA640..0xA69F -> ScriptFamily.CYRILLIC
        in 0x0530..0x058F -> ScriptFamily.ARMENIAN
        in 0x0590..0x05FF, in 0xFB1D..0xFB4F -> ScriptFamily.HEBREW
        in 0x0600..0x06FF, in 0x0750..0x077F, in 0x08A0..0x08FF,
        in 0xFB50..0xFDFF, in 0xFE70..0xFEFF -> ScriptFamily.ARABIC
        in 0x0900..0x0DFF -> ScriptFamily.INDIC
        in 0x0E00..0x0EFF -> ScriptFamily.THAI_LAO
        in 0x10A0..0x10FF -> ScriptFamily.GEORGIAN
        // Han, kana and Hangul are one family: Japanese and Korean legitimately mix them.
        in 0x1100..0x11FF, in 0x3040..0x318F, in 0x31F0..0x31FF, in 0x3400..0x4DBF,
        in 0x4E00..0x9FFF, in 0xAC00..0xD7AF, in 0xF900..0xFAFF, in 0xFF66..0xFFDC,
        in 0x20000..0x3FFFF -> ScriptFamily.EAST_ASIAN
        else -> ScriptFamily.OTHER
    }
}

private inline fun forEachCodePoint(text: String, action: (Int) -> Unit) {
    var i = 0
    while (i < text.length) {
        val cp = text.codePointAt(i)
        action(cp)
        i += Character.charCount(cp)
    }
}

private fun isCombiningMark(codePoint: Int): Boolean = when (Character.getType(codePoint)) {
    Character.NON_SPACING_MARK.toInt(),
    Character.COMBINING_SPACING_MARK.toInt(),
    Character.ENCLOSING_MARK.toInt() -> true
    else -> false
}

/**
 * The script the translation is written in, judged from the batch itself rather than from the
 * language name (Serbian can be Cyrillic or Latin; the user's language label says nothing reliable).
 * Lines that came back identical to their source are skipped: they vote for the source script.
 * Null when the batch is too short or has no clear majority, which turns the check off.
 */
internal fun dominantScript(translated: List<String>, source: List<String>): ScriptFamily? {
    val counts = IntArray(ScriptFamily.values().size)
    var total = 0
    translated.forEachIndexed { i, line ->
        if (line == source.getOrNull(i)) return@forEachIndexed
        forEachCodePoint(line) { cp ->
            scriptFamilyOf(cp)?.let { counts[it.ordinal]++; total++ }
        }
    }
    if (total < MIN_LETTERS_FOR_SCRIPT_CHECK) return null
    val best = counts.indices.maxByOrNull { counts[it] } ?: return null
    if (counts[best] * 100 < total * DOMINANT_SCRIPT_PERCENT) return null
    return ScriptFamily.values()[best].takeIf { it != ScriptFamily.OTHER }
}

/**
 * True when [translated] contains letters that belong neither to the [expected] script nor to
 * anything the [source] line could explain.
 *
 * Two signals:
 *  - a letter from a third script: not [expected], not Latin (names and brands stay Latin in every
 *    language) and not a script the source line itself uses;
 *  - for scripts written with spaces, a single word that mixes [expected] letters with two or more
 *    Latin letters, unless that exact word is in the source.
 */
internal fun hasScriptCorruption(translated: String, source: String, expected: ScriptFamily): Boolean {
    val sourceFamilies = HashSet<ScriptFamily>()
    forEachCodePoint(source) { cp -> scriptFamilyOf(cp)?.let { sourceFamilies.add(it) } }

    val checkMixedWords = expected in SPACED_SCRIPTS
    val word = StringBuilder()
    var expectedLetters = 0
    var latinLetters = 0
    var corrupted = false

    fun endWord() {
        if (checkMixedWords && expectedLetters > 0 &&
            latinLetters >= MIN_LATIN_LETTERS_IN_MIXED_WORD && !source.contains(word)
        ) {
            corrupted = true
        }
        word.setLength(0)
        expectedLetters = 0
        latinLetters = 0
    }

    forEachCodePoint(translated) { cp ->
        val family = scriptFamilyOf(cp)
        when {
            family != null -> {
                if (family != expected && family != ScriptFamily.LATIN && family !in sourceFamilies) {
                    return true
                }
                word.appendCodePoint(cp)
                if (family == expected) expectedLetters++
                if (family == ScriptFamily.LATIN) latinLetters++
            }
            isCombiningMark(cp) -> word.appendCodePoint(cp)
            else -> endWord()
        }
    }
    endWord()
    return corrupted
}

/**
 * Lines worth a second request: the ones the model dropped from its reply ([untranslated], still
 * in the source language) and the ones with script corruption. Dropped lines without any letters
 * (music notes, "...") are left alone, there is nothing to translate.
 */
internal fun suspectLineIndices(
    source: List<String>,
    translated: List<String>,
    untranslated: Set<Int>,
    expected: ScriptFamily?
): List<Int> = source.indices.filter { i ->
    val line = translated.getOrNull(i) ?: return@filter false
    if (i in untranslated) {
        source[i].any { Character.isLetter(it) }
    } else {
        expected != null && hasScriptCorruption(line, source[i], expected)
    }
}

/**
 * Folds the second attempt into the first. A retried line replaces the original only when it is
 * an improvement: it was actually translated this time and is free of script corruption. Otherwise
 * the first attempt stands, so a retry can never make a batch worse.
 *
 * [retryLines] and [retryUntranslated] are indexed by position in [suspects].
 */
internal fun mergeRepairedLines(
    source: List<String>,
    first: List<String>,
    suspects: List<Int>,
    retryLines: List<String>,
    retryUntranslated: Set<Int>,
    expected: ScriptFamily?
): List<String> {
    val merged = first.toMutableList()
    suspects.forEachIndexed { k, index ->
        val candidate = retryLines.getOrNull(k) ?: return@forEachIndexed
        if (k in retryUntranslated) return@forEachIndexed
        if (expected != null && hasScriptCorruption(candidate, source[index], expected)) return@forEachIndexed
        merged[index] = candidate
    }
    return merged
}
