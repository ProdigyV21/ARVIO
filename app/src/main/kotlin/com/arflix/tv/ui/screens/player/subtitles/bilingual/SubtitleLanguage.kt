package com.arflix.tv.ui.screens.player.subtitles.bilingual

import java.util.Locale

/**
 * A normalised subtitle language: an ISO 639-1 (or 639-2/3 when no 639-1 exists) base code plus,
 * for Chinese only, the writing system (Simplified `Hans` / Traditional `Hant`) — the one script
 * distinction that actually changes which subtitle a viewer can read.
 *
 * [script] null means "not stated". For Chinese that is deliberately *not* treated as either
 * script: `zh`, `chi`, `zho` alone are ambiguous, `zh-CN`/`zh-SG` imply Simplified and
 * `zh-TW`/`zh-HK`/`zh-MO` imply Traditional (BCP 47 region → script defaults).
 */
data class SubtitleLanguage(val base: String, val script: String? = null) {

    /** BCP 47 tag, e.g. `en`, `zh-Hans`, `zh-Hant`, `zh` (script unknown). */
    val tag: String get() = if (script != null) "$base-$script" else base

    val isChinese: Boolean get() = base == "zh"

    /** English display name, also handed to Gemini/Groq as the translation target. */
    val displayName: String
        get() = when {
            base == "zh" && script == HANS -> "Chinese (Simplified)"
            base == "zh" && script == HANT -> "Chinese (Traditional)"
            else -> NAMES[base] ?: Locale.forLanguageTag(base).getDisplayLanguage(Locale.ENGLISH)
                .ifBlank { base }
        }

    /** Name shown in the UI, in the app's interface language. */
    val localName: String
        get() {
            val ui = Locale.getDefault()
            if (ui.language != "zh") return displayName
            val hant = BilingualStrings.isTraditional(ui)
            return when {
                base == "zh" && script == HANS -> if (hant) "簡體中文" else "简体中文"
                base == "zh" && script == HANT -> if (hant) "繁體中文" else "繁体中文"
                base == "zh" -> "中文"
                else -> Locale.forLanguageTag(base).getDisplayLanguage(ui).ifBlank { displayName }
            }
        }

    /** Name used in the AI prompt (unambiguous for Chinese scripts). */
    val translationTargetName: String
        get() = when {
            base == "zh" && script == HANS -> "Simplified Chinese"
            base == "zh" && script == HANT -> "Traditional Chinese"
            else -> displayName
        }

    companion object {
        const val HANS = "Hans"
        const val HANT = "Hant"
        const val AUTO = "auto"

        val SIMPLIFIED_CHINESE = SubtitleLanguage("zh", HANS)
        val TRADITIONAL_CHINESE = SubtitleLanguage("zh", HANT)
        val ENGLISH = SubtitleLanguage("en")

        private val NAMES = mapOf(
            "en" to "English", "ja" to "Japanese", "ko" to "Korean", "fr" to "French", "de" to "German",
            "es" to "Spanish", "it" to "Italian", "pt" to "Portuguese", "ru" to "Russian", "ar" to "Arabic",
            "hi" to "Hindi", "th" to "Thai", "vi" to "Vietnamese", "id" to "Indonesian", "ms" to "Malay",
            "nl" to "Dutch", "sv" to "Swedish", "no" to "Norwegian", "da" to "Danish", "fi" to "Finnish",
            "pl" to "Polish", "tr" to "Turkish", "he" to "Hebrew", "uk" to "Ukrainian", "cs" to "Czech",
            "el" to "Greek", "hu" to "Hungarian", "ro" to "Romanian", "fa" to "Persian", "zh" to "Chinese",
            "yue" to "Cantonese", "tl" to "Filipino", "bg" to "Bulgarian", "hr" to "Croatian", "sr" to "Serbian",
            "sk" to "Slovak", "sl" to "Slovenian", "et" to "Estonian", "lv" to "Latvian", "lt" to "Lithuanian",
            "is" to "Icelandic", "ca" to "Catalan", "eu" to "Basque", "gl" to "Galician", "bn" to "Bengali",
            "ta" to "Tamil", "te" to "Telugu", "ur" to "Urdu",
        )

        /** ISO 639-2 (B and T) / 639-3 → 639-1, plus the language names containers write. */
        private val ALIASES: Map<String, String> = buildMap {
            fun a(target: String, vararg keys: String) = keys.forEach { put(it, target) }
            a("en", "eng", "english")
            a("zh", "chi", "zho", "chinese", "cmn", "mandarin")
            a("yue", "yue", "cantonese")
            a("ja", "jpn", "japanese", "jp")
            a("ko", "kor", "korean")
            a("fr", "fre", "fra", "french")
            a("de", "ger", "deu", "german")
            a("es", "spa", "spanish", "castilian")
            a("it", "ita", "italian")
            a("pt", "por", "portuguese")
            a("ru", "rus", "russian")
            a("ar", "ara", "arabic")
            a("hi", "hin", "hindi")
            a("th", "tha", "thai")
            a("vi", "vie", "vietnamese")
            a("id", "ind", "indonesian")
            a("ms", "may", "msa", "malay")
            a("nl", "dut", "nld", "dutch")
            a("sv", "swe", "swedish")
            a("no", "nor", "nob", "nno", "nb", "nn", "norwegian")
            a("da", "dan", "danish")
            a("fi", "fin", "finnish")
            a("pl", "pol", "polish")
            a("tr", "tur", "turkish")
            a("he", "heb", "iw", "hebrew")
            a("uk", "ukr", "ukrainian")
            a("cs", "cze", "ces", "czech")
            a("el", "gre", "ell", "greek")
            a("hu", "hun", "hungarian")
            a("ro", "rum", "ron", "romanian")
            a("fa", "per", "fas", "persian", "farsi")
            a("tl", "tgl", "fil", "filipino", "tagalog")
            a("bg", "bul", "bulgarian")
            a("hr", "hrv", "croatian")
            a("sr", "srp", "serbian")
            a("sk", "slo", "slk", "slovak")
            a("sl", "slv", "slovenian")
            a("et", "est", "estonian")
            a("lv", "lav", "latvian")
            a("lt", "lit", "lithuanian")
            a("is", "ice", "isl", "icelandic")
            a("ca", "cat", "catalan")
            a("bn", "ben", "bengali")
            a("ta", "tam", "tamil")
            a("te", "tel", "telugu")
            a("ur", "urd", "urdu")
        }

        private val SIMPLIFIED_REGIONS = setOf("cn", "sg", "my")
        private val TRADITIONAL_REGIONS = setOf("tw", "hk", "mo")

        // Words in a track *name* that state the Chinese script (containers often only say "chi").
        private val SIMPLIFIED_HINTS = listOf(
            "simplified", "简体", "简中", "簡體", "簡体", "簡中", "简", "chs", "zh-hans", "zh_hans", "hans", "gb", "mainland", "zh-cn", "zh_cn",
        )
        private val TRADITIONAL_HINTS = listOf(
            "traditional", "繁體", "繁体", "繁中", "繁", "cht", "zh-hant", "zh_hant", "hant", "big5", "taiwan",
            "hong kong", "zh-tw", "zh_tw", "zh-hk",
        )

        /**
         * Normalises a BCP 47 / ISO 639 tag or a language name. Returns null for blank, `und`,
         * `mul`, `zxx` and unknown values.
         */
        fun parse(raw: String?): SubtitleLanguage? {
            val value = raw?.trim()?.lowercase(Locale.ROOT)?.replace('_', '-') ?: return null
            if (value.isEmpty() || value in setOf("und", "mul", "zxx", "mis", "unknown", "auto", "none", "off")) return null
            // Common free-text names first ("Chinese (Simplified)", "简体中文", "Traditional Chinese").
            if (value.startsWith("chinese") || value.contains("中文") || value == "chs" || value == "cht" ||
                value.endsWith(" chinese")) {
                return SubtitleLanguage("zh", scriptHint(value))
            }
            val parts = value.split('-').filter { it.isNotEmpty() }
            if (parts.isEmpty()) return null
            val first = parts[0]
            val base = when {
                first.length == 2 && first.all { it in 'a'..'z' } -> if (first == "iw") "he" else if (first in setOf("nb", "nn")) "no" else first
                else -> ALIASES[first] ?: ALIASES[value] ?: return null
            }
            var script: String? = null
            if (base == "zh" || base == "yue") {
                for (p in parts.drop(1)) {
                    when {
                        p == "hans" -> script = HANS
                        p == "hant" -> script = HANT
                        script == null && p in SIMPLIFIED_REGIONS -> script = HANS
                        script == null && p in TRADITIONAL_REGIONS -> script = HANT
                    }
                }
            }
            // Cantonese is folded into Chinese; its subtitles are written in Traditional script.
            return if (base == "yue") SubtitleLanguage("zh", script ?: HANT) else SubtitleLanguage(base, script)
        }

        /** Script stated by free text (a track title such as "简体中文" or "Chinese (Traditional)"). */
        fun scriptHint(text: String?): String? {
            val t = text?.lowercase(Locale.ROOT) ?: return null
            val simplified = SIMPLIFIED_HINTS.any { hintMatches(t, it) }
            val traditional = TRADITIONAL_HINTS.any { hintMatches(t, it) }
            return when {
                simplified && !traditional -> HANS
                traditional && !simplified -> HANT
                else -> null
            }
        }

        private fun hintMatches(text: String, hint: String): Boolean {
            if (hint.any { it.code > 0x7F }) return text.contains(hint)
            // ASCII hints must be whole words ("gb" must not match "rugby").
            return Regex("(^|[^a-z])" + Regex.escape(hint) + "([^a-z]|$)").containsMatchIn(text)
        }

        /**
         * Language of a subtitle track from its container language tag and its title. The tag
         * wins for the base language; the title can add (never contradict) the Chinese script.
         */
        fun ofTrack(languageTag: String?, label: String?): SubtitleLanguage? {
            val fromTag = parse(languageTag)
            val fromLabel = parse(label)
            val lang = fromTag ?: fromLabel ?: return null
            if (!lang.isChinese) return lang
            if (lang.script != null) return lang
            val hint = scriptHint(label) ?: fromLabel?.takeIf { it.isChinese }?.script
            return if (hint != null) lang.copy(script = hint) else lang
        }

        /** User choices offered in the language pickers (Auto is only offered for the primary). */
        val PICKER_OPTIONS: List<SubtitleLanguage> = listOf(
            ENGLISH, SIMPLIFIED_CHINESE, TRADITIONAL_CHINESE,
            SubtitleLanguage("ja"), SubtitleLanguage("ko"), SubtitleLanguage("es"), SubtitleLanguage("fr"),
            SubtitleLanguage("de"), SubtitleLanguage("it"), SubtitleLanguage("pt"), SubtitleLanguage("ru"),
            SubtitleLanguage("ar"), SubtitleLanguage("th"), SubtitleLanguage("vi"), SubtitleLanguage("id"),
            SubtitleLanguage("ms"), SubtitleLanguage("hi"), SubtitleLanguage("tr"), SubtitleLanguage("nl"),
            SubtitleLanguage("pl"), SubtitleLanguage("sv"), SubtitleLanguage("he"),
        )
    }
}

/** How well a track's language satisfies a wanted language. Higher is better; NONE never matches. */
enum class LanguageMatch { NONE, AMBIGUOUS_SCRIPT, EXACT }

object SubtitleLanguageMatcher {
    /**
     * - Different base language → NONE.
     * - Chinese with both scripts known and different (zh-Hans vs zh-Hant) → NONE.
     * - Chinese where the track's script is unknown (`zh`, `chi`) → AMBIGUOUS_SCRIPT: usable only
     *   when nothing matches exactly, and reported as such.
     * - Otherwise EXACT.
     */
    fun match(track: SubtitleLanguage?, wanted: SubtitleLanguage?): LanguageMatch {
        if (track == null || wanted == null) return LanguageMatch.NONE
        if (track.base != wanted.base) return LanguageMatch.NONE
        if (!wanted.isChinese) return LanguageMatch.EXACT
        val ws = wanted.script
        val ts = track.script
        return when {
            ws == null -> LanguageMatch.EXACT
            ts == null -> LanguageMatch.AMBIGUOUS_SCRIPT
            ts == ws -> LanguageMatch.EXACT
            else -> LanguageMatch.NONE
        }
    }

    /** True when two resolved languages would show the same text twice. */
    fun sameLanguage(a: SubtitleLanguage?, b: SubtitleLanguage?): Boolean {
        if (a == null || b == null) return false
        if (a.base != b.base) return false
        if (!a.isChinese) return true
        // zh-Hans + zh-Hant is a legitimate (if unusual) pair; unknown script counts as the same.
        return a.script == null || b.script == null || a.script == b.script
    }
}
