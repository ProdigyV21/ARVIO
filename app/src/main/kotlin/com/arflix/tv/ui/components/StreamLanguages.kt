package com.arflix.tv.ui.components

import com.arflix.tv.util.normalizeLanguageCode

/**
 * Audio languages named in a source's text, as short codes in the order they appear.
 *
 * Aggregators list every language of a release ("Eng Deu Ita", "🇬🇧 / 🇩🇪", "German DL"), so the
 * source badge must not stop at the first hit. Only whole words and flag emoji count: two-letter
 * codes ("IT", "DE") are ordinary words in titles and are deliberately not matched. Words of
 * [ignoreWordsOf] (the film's own title, "The Italian Job"), subtitle sections, and languages named
 * next to an inline subtitle qualifier ("GERMAN.SUBBED") are not audio languages.
 */
internal fun detectStreamLanguages(text: String, ignoreWordsOf: String = ""): List<String> {
    if (text.isBlank()) return emptyList()
    val titleWords = StreamLanguageRegexes.WORD.findAll(ignoreWordsOf).map { it.value.lowercase() }.toSet()
    val audioText = withoutSubtitleSections(text)
    val words = StreamLanguageRegexes.WORD.findAll(audioText).toList()
    val found = sortedMapOf<Int, String>()
    words.forEachIndexed { index, match ->
        val word = match.value.lowercase()
        val code = LANGUAGE_WORDS[word] ?: return@forEachIndexed
        if (word in titleWords) return@forEachIndexed
        val previous = words.getOrNull(index - 1)?.value?.lowercase()
        val next = words.getOrNull(index + 1)?.value?.lowercase()
        if (previous in SUBTITLE_WORDS || next in SUBTITLE_WORDS) return@forEachIndexed
        found.putIfAbsent(match.range.first, code)
    }
    // A flag is a pair of regional-indicator characters. Back-to-back flags ("🇬🇧🇷🇺") are read two
    // at a time from the start of the run, so no flag is seen across a pair boundary ("🇧🇷").
    var offset = 0
    while (offset < audioText.length) {
        val first = audioText.codePointAt(offset)
        val afterFirst = offset + Character.charCount(first)
        if (isRegionalIndicator(first) && afterFirst < audioText.length) {
            val second = audioText.codePointAt(afterFirst)
            if (isRegionalIndicator(second)) {
                val afterSecond = afterFirst + Character.charCount(second)
                FLAG_CODES[audioText.substring(offset, afterSecond)]?.let { found.putIfAbsent(offset, it) }
                offset = afterSecond
                continue
            }
        }
        offset = afterFirst
    }
    return found.values.distinct()
}

/** Structured audio hints are authoritative; free-text fields have separate section boundaries. */
internal fun detectSourceAudioLanguages(
    hintedLanguage: String?,
    sourceTexts: List<String>,
    mediaTitle: String
): List<String> {
    val hint = hintedLanguage?.trim().orEmpty()
    val normalizedHint = normalizeLanguageCode(hint).substringBefore('-')
    val hintedCodes = detectStreamLanguages(hint).ifEmpty {
        if (StreamLanguageRegexes.LANGUAGE_CODE.matches(hint) && normalizedHint.length == 2) {
            listOf(normalizedHint)
        } else emptyList()
    }
    return (hintedCodes + sourceTexts.flatMap { detectStreamLanguages(it, mediaTitle) }).distinct()
}

private fun withoutSubtitleSections(text: String): String = buildString {
    var start = 0
    var subtitles = false
    // Subtitle lists can contain many words or flags. Resume only at an explicit audio heading,
    // not after skipping just the first language next to "Subtitles".
    StreamLanguageRegexes.SECTION.findAll(text).forEach { marker ->
        if (!subtitles) append(text, start, marker.range.first)
        append(' ')
        val label = marker.value.trimStart()
        subtitles = label.startsWith("sub", ignoreCase = true) ||
            label.startsWith("untertitel", ignoreCase = true) || label == "💬"
        start = marker.range.last + 1
    }
    if (!subtitles) append(text, start, text.length)
}

private fun isRegionalIndicator(codePoint: Int): Boolean = codePoint in 0x1F1E6..0x1F1FF

/**
 * The language badge for a source: the viewer's language first when the release has it, then how
 * many other languages it carries ("🇩🇪 DE +2"). A multi-audio release that names only one language
 * ("MULTi FRENCH") adds the globe for "more, count unknown" ("🇫🇷 FR 🌐"): only named languages are
 * counted. Null when no language is named.
 */
internal fun streamLanguageBadge(
    languages: List<String>,
    isMultiAudio: Boolean,
    preferredLanguage: String?
): String? {
    if (languages.isEmpty()) return null
    // "pt-br" ranks as "pt": the badge works in base languages.
    val preferred = preferredLanguage?.let(::normalizeLanguageCode)?.take(2)
    val primary = languages.firstOrNull { it == preferred } ?: languages.first()
    val others = languages.size - 1
    val code = primary.uppercase()
    return when {
        others > 0 -> "${languageFlag(primary)}$code +$others"
        isMultiAudio -> "${languageFlag(primary)}$code 🌐"
        else -> "${languageFlag(primary)}$code"
    }
}

private fun languageFlag(code: String): String =
    FLAG_LANGUAGES.firstOrNull { it.second == code }?.first?.let { "$it " }.orEmpty()

private object StreamLanguageRegexes {
    // Letters only, so "Eng.Deu" and "ENG|GER" split into words the same way spaces do.
    val WORD = Regex("""\p{L}+""")
    val LANGUAGE_CODE = Regex("""[a-zA-Z]{2,3}(?:[-_][a-zA-Z]{2,3})?""")
    val SECTION = Regex(
        """\b(?:audio|subs?|subtitles?|untertitel)(?:[ \t]+languages?)?[ \t]*[:=]|(?:^|[\r\n])[ \t]*(?:audio|subs?|subtitles?|untertitel)\b[ \t]*[:=]?|🎧|🔊|💬""",
        RegexOption.IGNORE_CASE
    )
}

private val SUBTITLE_WORDS = setOf("sub", "subs", "subbed", "subtitle", "subtitles", "subtitled", "untertitel")

private val LANGUAGE_WORDS: Map<String, String> = buildMap {
    fun add(code: String, vararg words: String) = words.forEach { put(it, code) }
    add("en", "eng", "english")
    add("de", "ger", "deu", "german", "deutsch")
    add("fr", "fre", "fra", "french", "francais", "français", "vff", "vfq", "truefrench")
    add("es", "spa", "esp", "spanish", "espanol", "español", "castellano", "latino")
    add("it", "ita", "italian", "italiano")
    add("pt", "por", "portuguese", "portugues", "português")
    add("nl", "nld", "dut", "dutch", "nederlands")
    add("ru", "rus", "russian")
    add("pl", "pol", "polish", "polski")
    add("tr", "turkish")
    add("ja", "jpn", "japanese")
    add("ko", "kor", "korean")
    add("zh", "chinese", "mandarin", "cantonese")
    add("hi", "hin", "hindi")
    add("ta", "tam", "tamil")
    add("te", "tel", "telugu")
    add("ar", "arabic")
    add("sv", "swe", "swedish")
    add("no", "norwegian")
    add("da", "danish")
    add("fi", "finnish")
    add("cs", "cze", "czech")
    add("hu", "hun", "hungarian")
    add("uk", "ukr", "ukrainian")
    add("el", "greek")
    add("he", "heb", "hebrew")
    add("th", "thai")
    add("vi", "vietnamese")
}

// Flags as aggregators print them (AIOStreams' language emojis). One code per flag; the first
// flag listed for a code is the one the badge shows.
private val FLAG_LANGUAGES: List<Pair<String, String>> = listOf(
    "🇬🇧" to "en", "🇺🇸" to "en",
    "🇩🇪" to "de", "🇦🇹" to "de",
    "🇫🇷" to "fr",
    "🇪🇸" to "es", "🇲🇽" to "es",
    "🇮🇹" to "it",
    "🇵🇹" to "pt", "🇧🇷" to "pt",
    "🇳🇱" to "nl",
    "🇷🇺" to "ru",
    "🇵🇱" to "pl",
    "🇹🇷" to "tr",
    "🇯🇵" to "ja",
    "🇰🇷" to "ko",
    "🇨🇳" to "zh",
    "🇮🇳" to "hi",
    "🇸🇦" to "ar",
    "🇸🇪" to "sv",
    "🇳🇴" to "no",
    "🇩🇰" to "da",
    "🇫🇮" to "fi",
    "🇨🇿" to "cs",
    "🇭🇺" to "hu",
    "🇺🇦" to "uk",
    "🇬🇷" to "el",
    "🇮🇱" to "he",
    "🇹🇭" to "th",
    "🇻🇳" to "vi",
)

// AIOStreams shares India's flag between Hindi, Tamil, Telugu and others. Keep it for displaying
// an explicitly named Hindi track, but never infer an audio language from this flag alone.
private val FLAG_CODES: Map<String, String> = FLAG_LANGUAGES.filterNot { it.first == "🇮🇳" }.toMap()
