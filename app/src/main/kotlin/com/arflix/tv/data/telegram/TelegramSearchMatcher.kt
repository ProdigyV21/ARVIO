package com.arflix.tv.data.telegram

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TelegramSearchMatcher @Inject constructor() {

    companion object {
        private const val SEP = """[\s._\-x+,&:]{0,2}"""
        private const val SEP_MID = """[\s._\-x+,&:]{0,4}"""
        private val EPISODE_PATTERN = Regex(
            """[Ss][e]?(?:ason)?$SEP(\d{1,2})${SEP_MID}[Ee][p]?(?:isode)?$SEP(\d{1,4})""" +
            """|ע(?:ונה)?$SEP(\d{1,2})${SEP_MID}פ(?:רק)?$SEP(\d{1,4})""",
            RegexOption.IGNORE_CASE
        )
        // Season-1 fallback: episode-only Hebrew marker (פרק 5 / פ5) with no season prefix
        private val EPISODE_ONLY_PATTERN = Regex("""פ(?:רק)?[\s._\-x+,&:]{0,2}(\d{1,4})""")
        private val YEAR_PATTERN = Regex("""\b(19|20)\d{2}\b""")
        private val NOISE = Regex("""[._\-\[\]()'",!?:]""")
        private val MULTI_SPACE = Regex("""\s+""")
        private val SIZE_SUFFIX = Regex("""\.(mkv|mp4|avi|mov|wmv|m4v|ts|m2ts)$""", RegexOption.IGNORE_CASE)
        private val HEBREW_RANGE = 0x0590..0x05FF
    }

    fun score(
        fileName: String,
        caption: String,
        title: String,
        localizedTitle: String? = null,
        englishTitle: String? = null,
        originalTitle: String? = null,
        year: Int?,
        season: Int?,
        episode: Int?
    ): Int {
        val combined = "$fileName $caption"
        val normalizedCombined = normalize(combined)
        val normalizedTitle = normalize(title)
        val normalizedLocalized = localizedTitle?.let { normalize(it) }
        val normalizedEnglish = englishTitle?.let { normalize(it) }
        val normalizedOriginal = originalTitle?.let { normalize(it) }

        // Primary match: TMDB English title, TMDB localized title, TMDB original title, or app
        // title. The original title matters for foreign content whose native name is the one that
        // actually appears in file names — TMDB often has no translation, in which case the
        // "localized" title is just the English one again.
        val engMatch = normalizedEnglish != null && normalizedEnglish.isNotBlank() && normalizedCombined.contains(normalizedEnglish)
        val locMatch = normalizedLocalized != null && normalizedLocalized.isNotBlank() && normalizedCombined.contains(normalizedLocalized)
        val origMatch = normalizedOriginal != null && normalizedOriginal.isNotBlank() && normalizedCombined.contains(normalizedOriginal)
        val appMatch = normalizedCombined.contains(normalizedTitle)

        if (!engMatch && !locMatch && !origMatch && !appMatch) return 0

        var score = 60

        if (year != null) {
            val fileYears = YEAR_PATTERN.findAll(combined).map { it.value.toInt() }.toList()
            score += when {
                fileYears.contains(year) -> 20
                fileYears.any { kotlin.math.abs(it - year) == 1 } -> 5
                fileYears.isEmpty() -> 5
                else -> -10
            }
        }

        if (season != null && episode != null) {
            // Check filename and caption independently (mirrors Stremiogram: accept if either has
            // the right S/E, even if the other text has a different marker).
            val seFile    = extractSeasonEpisode(fileName)
            val seCaption = extractSeasonEpisode(caption)
            val rightSE   = (seFile?.first == season && seFile.second == episode) ||
                            (seCaption?.first == season && seCaption.second == episode)
            when {
                rightSE -> score += 20
                seFile != null || seCaption != null -> return 0  // pattern found but wrong S/E
                season == 1 -> {
                    // Season-1 files often omit the season marker — try episode-only Hebrew marker
                    val epFile    = extractEpisodeOnly(fileName)
                    val epCaption = extractEpisodeOnly(caption)
                    when {
                        epFile == episode || epCaption == episode -> score += 20
                        epFile != null || epCaption != null -> return 0
                        else -> score -= 10
                    }
                }
                else -> score -= 10
            }
        } else if (season == null) {
            if (EPISODE_PATTERN.containsMatchIn(combined) || EPISODE_PATTERN.containsMatchIn(normalizedCombined)) {
                score -= 20
            }
        }

        return score.coerceIn(0, 100)
    }

    private fun extractSeasonEpisode(text: String): Pair<Int, Int>? {
        val m = EPISODE_PATTERN.find(text) ?: EPISODE_PATTERN.find(normalize(text)) ?: return null
        val s = m.groupValues[1].toIntOrNull() ?: m.groupValues[3].toIntOrNull() ?: return null
        val e = m.groupValues[2].toIntOrNull() ?: m.groupValues[4].toIntOrNull() ?: return null
        return s to e
    }

    private fun extractEpisodeOnly(text: String): Int? {
        val m = EPISODE_ONLY_PATTERN.find(text) ?: EPISODE_ONLY_PATTERN.find(normalize(text)) ?: return null
        return m.groupValues[1].toIntOrNull()
    }

    fun buildMovieQueries(
        title: String,
        year: Int?,
        localizedTitle: String? = null,
        englishTitle: String? = null,
        originalTitle: String? = null
    ): List<String> {
        // Prefer TMDB English title as primary; fall back to app title
        val primary = englishTitle?.let { cleanTitle(it) } ?: cleanTitle(title)
        val queries = mutableListOf<String>()
        if (year != null) queries.add("$primary $year")
        queries.add(primary)
        // Localized and original are both searched: for foreign titles TMDB frequently has no
        // translation (localized == English), and only the original name matches real files.
        listOfNotNull(localizedTitle, originalTitle)
            .map { cleanTitle(it) }
            .filter { it.isNotBlank() && !it.equals(primary, ignoreCase = true) }
            .distinct()
            .forEach { alt ->
                if (year != null) queries.add("$alt $year")
                queries.add(alt)
            }
        return queries.distinct()
    }

    fun buildSeriesQueries(
        title: String,
        season: Int,
        episode: Int,
        localizedTitle: String? = null,
        englishTitle: String? = null,
        languageCode: String = "en",
        originalTitle: String? = null
    ): List<String> {
        val engBase = englishTitle?.let { cleanTitle(it) } ?: cleanTitle(title)
        val s = season.toString()
        val e = episode.toString()
        val s2 = season.toString().padStart(2, '0')
        val e2 = episode.toString().padStart(2, '0')

        // Every distinct name this show is known by. The original title is included because TMDB
        // frequently has no translation for foreign content — it then returns the English name for
        // a localized request, so `localizedTitle` alone can silently be English and the native
        // name (the one actually used in file names and captions) is never searched.
        val altBases = listOfNotNull(localizedTitle, originalTitle, title)
            .map { cleanTitle(it) }
            .filter { it.isNotBlank() && !it.equals(engBase, ignoreCase = true) }
            .distinct()

        val queries = mutableListOf<String>()

        // Hebrew-specific episode markers — only for Hebrew users, and only paired with a title
        // that is actually written in Hebrew. Pairing Hebrew markers with a Latin title produces
        // strings like "on standby עונה 1 פרק 1" that cannot exist in any group.
        if (languageCode == "he") {
            val hebrewBases = altBases.filter { isHebrew(it) }
                .ifEmpty { listOfNotNull(engBase.takeIf { isHebrew(it) }) }
            for (hebTitle in hebrewBases) {
                queries += listOf(
                    "$hebTitle ע$s פ$e",
                    "$hebTitle ע${s}פ${e}",
                    "$hebTitle עונה $s פרק $e",
                )
                if (season == 1) queries += listOf("$hebTitle פ$e", "$hebTitle פרק $e")
            }
        }

        // Alternate titles with English S/E patterns (any non-English language)
        for (alt in altBases) {
            queries += listOf(
                "$alt s${s}e${e}",
                "$alt s${s2}e${e2}",
                "$alt s$s e$e",
                "$alt s$s2 e$e2",
            )
        }

        // English patterns
        queries += listOf(
            "$engBase s${s}e${e}",
            "$engBase s${s2}e${e2}",
            "$engBase s$s e$e",
            "$engBase s$s2 e$e2",
        )

        return queries.map { it.lowercase() }.distinct()
    }

    fun isHebrew(s: String) = s.any { it.code in HEBREW_RANGE }

    private fun cleanTitle(title: String): String {
        val stripped = title.replace(":", "").replace("  ", " ").trim()
        return java.text.Normalizer.normalize(stripped, java.text.Normalizer.Form.NFKD)
            .replace(TelegramSearchMatcherRegexes.DIACRITICS_REGEX, "")
    }

    private fun normalize(text: String): String =
        text.replace(SIZE_SUFFIX, "")
            .replace(NOISE, " ")
            .replace(MULTI_SPACE, " ")
            .trim()
            .lowercase()
}

private object TelegramSearchMatcherRegexes {
    val DIACRITICS_REGEX = Regex("\\p{Mn}+")
}
