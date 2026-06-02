package com.arflix.tv.util

import com.arflix.tv.data.model.Subtitle

/**
 * Shared subtitle-language matching helpers.
 *
 * Consolidates logic previously duplicated between PlayerViewModel (preferred-language
 * filtering during playback) and DownloadSheet (preferred-language filtering / auto-select
 * for offline downloads). Keep this pure (no Android, no DataStore) so it stays testable
 * and reusable from both UI and worker code.
 */

/** True when a saved language preference means "no subtitles". */
fun isSubtitleLangDisabled(value: String?): Boolean {
    val normalized = value?.trim()?.lowercase().orEmpty()
    return normalized.isBlank() ||
        normalized == "off" ||
        normalized == "none" ||
        normalized == "no subtitles" ||
        normalized == "disabled" ||
        normalized == "disable"
}

/**
 * Normalize a language name / ISO 639-1 / ISO 639-2 code to a canonical short tag.
 * Examples: "English" -> "en", "eng" -> "en", "pt-br" -> "pt-br".
 * Unknown values are returned lowercased and trimmed.
 */
fun normalizeSubtitleLang(lang: String): String {
    val lower = lang.lowercase().trim()
    return when {
        // Full names
        lower == "english" || lower.startsWith("english") -> "en"
        lower == "spanish" || lower.startsWith("spanish") || lower == "espanol" -> "es"
        lower == "french" || lower.startsWith("french") || lower == "francais" -> "fr"
        lower == "german" || lower.startsWith("german") || lower == "deutsch" -> "de"
        lower == "italian" || lower.startsWith("italian") -> "it"
        lower == "portuguese" -> "pt"
        lower == "portuguese (brazil)" ||
            lower == "portuguese-brazil" ||
            lower == "brazilian portuguese" ||
            lower == "brazil portuguese" ||
            lower == "pt-br" ||
            lower == "ptbr" -> "pt-br"
        lower.startsWith("portuguese") -> "pt"
        lower == "dutch" || lower.startsWith("dutch") -> "nl"
        lower == "russian" || lower.startsWith("russian") -> "ru"
        lower == "chinese" || lower.startsWith("chinese") -> "zh"
        lower == "japanese" || lower.startsWith("japanese") || lower == "jp" || lower == "jap" -> "ja"
        lower == "korean" || lower.startsWith("korean") -> "ko"
        lower == "arabic" || lower.startsWith("arabic") -> "ar"
        lower == "hindi" || lower.startsWith("hindi") -> "hi"
        lower == "turkish" || lower.startsWith("turkish") -> "tr"
        lower == "polish" || lower.startsWith("polish") -> "pl"
        lower == "swedish" || lower.startsWith("swedish") -> "sv"
        lower == "norwegian" || lower.startsWith("norwegian") -> "no"
        lower == "danish" || lower.startsWith("danish") -> "da"
        lower == "finnish" || lower.startsWith("finnish") -> "fi"
        lower == "greek" || lower.startsWith("greek") -> "el"
        lower == "czech" || lower.startsWith("czech") -> "cs"
        lower == "hungarian" || lower.startsWith("hungarian") -> "hu"
        lower == "romanian" || lower.startsWith("romanian") -> "ro"
        lower == "thai" || lower.startsWith("thai") -> "th"
        lower == "vietnamese" || lower.startsWith("vietnamese") -> "vi"
        lower == "indonesian" || lower.startsWith("indonesian") -> "id"
        lower == "hebrew" || lower.startsWith("hebrew") -> "he"
        lower == "persian" || lower.startsWith("persian") || lower == "farsi" -> "fa"
        lower == "ukrainian" || lower.startsWith("ukrainian") -> "uk"
        lower == "bengali" || lower.startsWith("bengali") -> "bn"
        lower == "bulgarian" || lower.startsWith("bulgarian") -> "bg"
        lower == "croatian" || lower.startsWith("croatian") -> "hr"
        lower == "serbian" || lower.startsWith("serbian") -> "sr"
        lower == "slovak" || lower.startsWith("slovak") -> "sk"
        lower == "slovenian" || lower.startsWith("slovenian") -> "sl"
        lower == "lithuanian" || lower.startsWith("lithuanian") -> "lt"
        lower == "estonian" || lower.startsWith("estonian") -> "et"
        // ISO 639-1 codes (2 letter)
        lower.length == 2 -> lower
        // ISO 639-2 codes (3 letter)
        lower == "eng" -> "en"
        lower == "spa" -> "es"
        lower == "fra" || lower == "fre" -> "fr"
        lower == "deu" || lower == "ger" -> "de"
        lower == "ita" -> "it"
        lower == "por" -> "pt"
        lower == "pob" || lower == "pobr" -> "pt-br"
        lower == "nld" || lower == "dut" -> "nl"
        lower == "rus" -> "ru"
        lower == "zho" || lower == "chi" -> "zh"
        lower == "jpn" -> "ja"
        lower == "kor" -> "ko"
        lower == "ara" -> "ar"
        lower == "hin" -> "hi"
        lower == "tur" -> "tr"
        lower == "pol" -> "pl"
        lower == "swe" -> "sv"
        lower == "nor" -> "no"
        lower == "dan" -> "da"
        lower == "fin" -> "fi"
        lower == "ell" || lower == "gre" -> "el"
        lower == "ces" || lower == "cze" -> "cs"
        lower == "hun" -> "hu"
        lower == "ron" || lower == "rum" -> "ro"
        lower == "tha" -> "th"
        lower == "vie" -> "vi"
        lower == "ind" -> "id"
        lower == "heb" || lower == "iw" -> "he"
        lower == "fas" || lower == "per" -> "fa"
        lower == "ukr" -> "uk"
        lower == "ben" -> "bn"
        lower == "bul" -> "bg"
        lower == "hrv" -> "hr"
        lower == "srp" -> "sr"
        lower == "slk" || lower == "slo" -> "sk"
        lower == "slv" -> "sl"
        lower == "lit" -> "lt"
        lower == "est" -> "et"
        else -> lower
    }
}

/**
 * True if [sub]'s lang or label normalizes to [normalizedLang]. For longer language tags
 * (>2 chars) falls back to substring containment — avoids the classic "en" matches
 * "Indonesian" false positive that simple `.contains()` would produce.
 */
fun subtitleMatchesLanguage(sub: Subtitle, normalizedLang: String): Boolean {
    val tokens = setOf(
        normalizeSubtitleLang(sub.lang),
        normalizeSubtitleLang(sub.label)
    )
    if (normalizedLang in tokens) return true
    if (normalizedLang.length > 2) {
        return sub.lang.lowercase().contains(normalizedLang) ||
            sub.label.lowercase().contains(normalizedLang)
    }
    return false
}

/**
 * Filter [subs] to those matching [preferred] or [secondary] language preferences. Either
 * preference may be a saved disabled-marker ("Off", blank, etc.) — those are ignored.
 * Falls back to the unfiltered list when nothing matches so the user is never left with
 * an empty subtitle picker.
 */
fun filterSubtitlesByLanguage(
    subs: List<Subtitle>,
    preferred: String,
    secondary: String
): List<Subtitle> {
    val targets = listOf(preferred, secondary)
        .filterNot { isSubtitleLangDisabled(it) }
        .map { normalizeSubtitleLang(it) }
        .filter { it.isNotBlank() }
        .distinct()
    if (targets.isEmpty()) return subs
    val filtered = subs.filter { sub -> targets.any { subtitleMatchesLanguage(sub, it) } }
    return filtered.ifEmpty { subs }
}
