package com.arflix.tv.util

import com.arflix.tv.data.model.Subtitle

/**
 * The picking rule behind the "Forced Subtitles" setting.
 *
 * A forced track subtitles only the lines that are NOT in the film's own language — the alien
 * dialogue in a sci-fi film, the Spanish scene in an English one. ARVIO knows such tracks but
 * deliberately ranks them LAST, so they were effectively unreachable; this is the opposite rule,
 * used only while the setting is on.
 *
 * Kept free of Android and of PlayerViewModel state on purpose: every decision here is a pure
 * function of the track list, so the cases that matter can be tested directly.
 */
object ForcedSubtitles {

    /** "forced"/"force" as a whole word — "reinforced" and "enforcement" must not count. */
    private val FORCED_NAME = Regex("""(?i)\bforce[ds]?\b""")

    /** Language codes that mean "no usable language here". */
    private val UNKNOWN_AUDIO = setOf("", "und", "mul", "zxx")

    private val WORDS = Regex("[A-Za-z-]+")

    /**
     * Whether [sub] only subtitles foreign-language dialogue.
     *
     * Two signals, and the second is not a shortcut: subtitles coming from an addon never carry the
     * container's forced flag (nothing in the stream layer ever sets it), so for those the name is
     * all there is. That is also precisely what was asked for — "only select a subtitle if it says
     * forced or force in the name". A full track mislabelled "forced" is the known price of that.
     */
    fun isForcedTrack(sub: Subtitle): Boolean =
        sub.isForced || FORCED_NAME.containsMatchIn("${sub.label} ${sub.lang} ${sub.id}")

    /**
     * Whether the forced rule may run at all, judged by what is being SPOKEN.
     *
     * A forced track in language X exists *because* the main audio is X — it translates the parts
     * that are not X. So it only makes sense when audio and subtitle language agree. On a dubbed
     * film (German audio, English subtitles) a forced track would show almost nothing, so there the
     * normal behaviour has to stay.
     *
     * An UNKNOWN audio language counts as a match, and that is the load-bearing case rather than a
     * loose end: plenty of sources ship audio with no language tag, and refusing to act on those
     * would make the setting quietly do nothing exactly where it was asked for. Unknown audio is
     * nearly always the original version, so "matches" is the better reading of the evidence.
     */
    fun ruleApplies(audioLanguage: String?, preferredLang: String, normalize: (String) -> String): Boolean {
        if (preferredLang.isBlank()) return false
        val audio = normalize(audioLanguage.orEmpty().trim())
        return audio in UNKNOWN_AUDIO || audio == preferredLang
    }

    /**
     * The forced track to show, or null for "show nothing".
     *
     * Null is a real answer, not a failure: showing the whole dialogue is the very thing the
     * setting exists to prevent, so where no forced track exists the correct result is none. The
     * preferred language is tried first, then the fallback one; the language itself is never
     * traded away.
     */
    fun pick(
        subtitles: List<Subtitle>,
        preferredLang: String,
        fallbackLang: String?,
        normalize: (String) -> String
    ): Subtitle? =
        bestFor(subtitles, preferredLang, normalize)
            ?: fallbackLang?.let { bestFor(subtitles, it, normalize) }

    /**
     * Whether the selection has to be decided again, given what is currently on screen.
     *
     * This exists because every other gate in the player asks "is the selected track already in
     * the right LANGUAGE?" — and in forced mode that question separates nothing. A plain English
     * track and the English forced track are both English, so those gates conclude "nothing to
     * do" and [pick] never gets to run. A file carrying ten English tracks is exactly where that
     * goes wrong, which is how it was found.
     *
     * So while the rule is active, anything that is not itself a forced track is never good
     * enough, whatever language it happens to be in.
     */
    fun needsAnotherLook(current: Subtitle?, ruleActive: Boolean): Boolean {
        if (!ruleActive) return false
        return current == null || !isForcedTrack(current)
    }

    private fun bestFor(
        subtitles: List<Subtitle>,
        target: String,
        normalize: (String) -> String
    ): Subtitle? {
        if (target.isBlank()) return null
        return subtitles
            .filter { isForcedTrack(it) && matchesLanguage(it, target, normalize) }
            .minWithOrNull(
                // A track the container itself flagged forced beats one that merely SAYS "forced"
                // in its name. Costs nothing — the flag is read when the track list is built — and
                // it is the only defence against a release that names a full track "forced".
                compareByDescending<Subtitle> { if (it.hasForcedFlag) 1 else 0 }
                    .thenByDescending { if (it.isEmbedded) 1 else 0 }
                    .thenBy { it.groupIndex ?: Int.MAX_VALUE }
                    .thenBy { it.trackIndex ?: Int.MAX_VALUE }
            )
    }

    /** The track's code or its label may name the language; release names carry both. */
    private fun matchesLanguage(sub: Subtitle, target: String, normalize: (String) -> String): Boolean {
        if (normalize(sub.lang) == target) return true
        if (normalize(sub.label) == target) return true
        return WORDS.findAll("${sub.lang} ${sub.label}").any { normalize(it.value) == target }
    }
}
