package com.arflix.tv.ui.screens.player.subtitles

/**
 * An answer pairing fewer than this share of the reference lines sent is a veto, like zero:
 * 1 of 8 is different dialogue, not a thin measurement. 2 of 8 stays inconclusive.
 */
internal const val MATCH_AI_MIN_PAIR_FRACTION = 0.25

/**
 * The model's line-pairing answer for one candidate subtitle (`PlayerViewModel.measureOffsetWithAi`).
 *
 * [pairs] is how many reference lines the model could confidently match in the candidate. [offsetMs]
 * is the shift those pairs agree on — null when there is nothing to measure (too few pairs, or pairs
 * that disagree). [unfixableShiftMs] is set when they agree on a shift beyond any real sync error:
 * the right episode, cut to a different length. [sent] is how many reference lines were sent; it is
 * set when the answer paired too few to measure.
 *
 * A failed or unreadable request is not an AiLineSync at all — it is `null`.
 */
internal data class AiLineSync(
    val pairs: Int,
    val offsetMs: Long?,
    val unfixableShiftMs: Long? = null,
    val sent: Int = 0,
) {
    /**
     * The model answered and could pair none, or almost none, of the reference lines: these are not
     * the same dialogue. Exactly zero used to be the only veto, so a reply pairing 1 of 8 lines was
     * treated like no reply at all and the coincidental timing score it should have overruled stood —
     * The Office S01E03 (Sept 2026): 0.75 on timing, 1/8 lines paired, the wrong subtitle selected
     * while the right one sat third. Correct subtitles have paired 8/8, 8/8, 4/5 and 3/7, so the bar
     * sits well below them.
     */
    val notThisDialogue: Boolean
        get() = pairs == 0 || pairs < sent * MATCH_AI_MIN_PAIR_FRACTION
}

/**
 * What the AI check established about a candidate. Three states, because two of them are easy to
 * conflate: [UNCONFIRMED] — the request failed, or the answer was too thin or inconsistent to measure —
 * is not [CONFIRMED]. Such a candidate may still be *selected* on its timing score, but nothing has
 * verified it, so it must not be remembered as a verified match for the file.
 */
internal enum class AiVerdict { CONFIRMED, UNCONFIRMED, REJECTED }

internal fun AiLineSync?.verdict(): AiVerdict = when {
    this == null -> AiVerdict.UNCONFIRMED
    notThisDialogue -> AiVerdict.REJECTED
    unfixableShiftMs != null -> AiVerdict.REJECTED
    offsetMs == null -> AiVerdict.UNCONFIRMED
    else -> AiVerdict.CONFIRMED
}

/**
 * Whether a winning match may be written to the per-stream cache (after the viewing dwell).
 *
 * Without AI, timing is the only verification there is — that is how the cache has always worked.
 * With AI available, only a subtitle whose dialogue the model actually confirmed counts: a timing-only
 * fallback is selected but not remembered, so the next playback scans again instead of skipping
 * straight to an unconfirmed subtitle. [verdict] is null for a candidate the model never looked at.
 */
internal fun mayRememberMatch(aiIsArbiter: Boolean, verdict: AiVerdict?): Boolean =
    !aiIsArbiter || verdict == AiVerdict.CONFIRMED
