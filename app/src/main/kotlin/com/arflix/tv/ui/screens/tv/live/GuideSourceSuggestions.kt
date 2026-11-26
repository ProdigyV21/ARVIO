package com.arflix.tv.ui.screens.tv.live

import java.text.Normalizer

/**
 * Picks the channels whose guide is most likely the right one for a channel that has
 * none (or the wrong one): the same channel under another name, quality or playlist.
 * Pure, so it is unit tested and cheap to run on every keystroke of the search box.
 */
internal object GuideSourceSuggestions {
    private val qualityWords = setOf(
        "hd", "fhd", "uhd", "sd", "4k", "8k", "hq", "lq", "hevc", "h265", "h264", "x265", "1080p", "720p",
        "2160p", "1080", "720", "50fps", "60fps", "fps", "raw", "backup", "alt", "vip", "plus", "tv", "channel",
    )
    // Leading/trailing country or provider tags: "NL:", "|NL|", "[NL]", "NL -".
    private val tagPattern = Regex("""^\s*(\|[^|]{1,12}\||\[[^\]]{1,12}]|\([^)]{1,12}\)|[a-z]{2,4}\s*[:|]|[a-z]{2,3}\s+-)\s*""")
    private val bracketPattern = Regex("""[\[(][^\])]{0,24}[\])]""")
    private val nonWord = Regex("""[^a-z0-9]+""")

    /** Lowercase words without accents, tags, quality labels or punctuation. */
    fun words(name: String): List<String> {
        var text = Normalizer.normalize(name, Normalizer.Form.NFD)
            .replace(Regex("\\p{Mn}+"), "")
            .lowercase()
        repeat(2) { text = text.replace(tagPattern, "") }
        text = text.replace(bracketPattern, " ")
        return text.split(nonWord).filter { it.isNotBlank() && it !in qualityWords }
    }

    fun key(name: String): String = words(name).joinToString("")

    /** 0 (unrelated) to 100 (the same channel name once tags and quality are removed). */
    fun score(target: String, candidate: String): Int = scoreWords(words(target), words(candidate))

    fun scoreWords(a: List<String>, b: List<String>): Int {
        if (a.isEmpty() || b.isEmpty()) return 0
        val ka = a.joinToString("")
        val kb = b.joinToString("")
        if (ka == kb) return 100
        // Numbers decide the channel ("NPO 1" is not "NPO 2"): a different number rules it out.
        val numbersA = a.filter { it.all(Char::isDigit) }.toSet()
        val numbersB = b.filter { it.all(Char::isDigit) }.toSet()
        if (numbersA.isNotEmpty() && numbersB.isNotEmpty() && numbersA.intersect(numbersB).isEmpty()) return 0
        // "rtl4" vs "rtl 4 hd", "npo1" vs "npo 1 extra": one name inside the other.
        if (kb.startsWith(ka) || ka.startsWith(kb)) return 80
        val shared = a.toSet().intersect(b.toSet())
        if (shared.isEmpty()) return 0
        val union = a.toSet().union(b.toSet())
        return (60 * shared.size / union.size).coerceAtLeast(1)
    }

    /** A candidate with its name already split, so typing in the search box stays cheap. */
    class Prepared<T>(val item: T, val name: String, val words: List<String>) {
        val key: String = words.joinToString("")
        val lowerName: String = name.lowercase()
    }

    fun <T> prepare(candidates: List<T>, nameOf: (T) -> String): List<Prepared<T>> =
        candidates.map { val name = nameOf(it); Prepared(it, name, words(name)) }

    /**
     * Channels for the picker. Without a [query]: the best name matches for [targetName].
     * With one: every channel whose name contains all of its words, best matches first.
     * Channels that already have a guide come first among equals.
     */
    fun <T> rank(
        targetName: String,
        query: String,
        candidates: List<T>,
        nameOf: (T) -> String,
        hasGuide: (T) -> Boolean,
        limit: Int = 40,
    ): List<T> = rankPrepared(targetName, query, prepare(candidates, nameOf), hasGuide, limit)

    fun <T> rankPrepared(
        targetName: String,
        query: String,
        candidates: List<Prepared<T>>,
        hasGuide: (T) -> Boolean,
        limit: Int = 40,
    ): List<T> {
        val targetWords = words(targetName)
        val queryWords = words(query).ifEmpty {
            query.trim().lowercase().split(nonWord).filter { it.isNotBlank() }
        }
        val scored = ArrayList<Pair<Prepared<T>, Int>>()
        for (candidate in candidates) {
            val similarity = scoreWords(targetWords, candidate.words)
            val matches = if (queryWords.isNotEmpty()) {
                queryWords.all { it in candidate.lowerName || it in candidate.key }
            } else similarity >= 30
            if (!matches) continue
            scored += candidate to (similarity * 2 + if (hasGuide(candidate.item)) 1 else 0)
        }
        return scored.sortedWith(compareByDescending<Pair<Prepared<T>, Int>> { it.second }.thenBy { it.first.name.length })
            .take(limit)
            .map { it.first.item }
    }
}

/** "+1:30" / "−0:30" for a guide offset in minutes; "0:00" for none. */
internal fun formatGuideOffset(minutes: Int): String {
    val sign = when {
        minutes > 0 -> "+"
        minutes < 0 -> "−"
        else -> ""
    }
    val abs = kotlin.math.abs(minutes)
    return "$sign${abs / 60}:${(abs % 60).toString().padStart(2, '0')}"
}
