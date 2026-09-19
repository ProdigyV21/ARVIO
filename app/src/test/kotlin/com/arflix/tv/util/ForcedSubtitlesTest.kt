package com.arflix.tv.util

import com.arflix.tv.data.model.Subtitle
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * The "Forced Subtitles" rule, case by case.
 *
 * The two that carry the feature are [noForcedTrackSelectsNothing] — the whole point is that no
 * forced track means NO subtitles, never a full one — and [dubbedAudioLeavesTheRuleAlone], which
 * keeps today's behaviour for a dubbed film, where a forced track would show almost nothing.
 */
class ForcedSubtitlesTest {

    /** Stands in for the player's own language normaliser: enough of it for these cases. */
    private val normalize: (String) -> String = { raw ->
        when (val v = raw.lowercase().trim()) {
            "english", "eng", "en" -> "en"
            "german", "deutsch", "ger", "deu", "de" -> "de"
            "french", "fra", "fre", "fr" -> "fr"
            else -> v
        }
    }

    private fun sub(
        id: String,
        lang: String,
        label: String,
        embedded: Boolean = true,
        forcedFlag: Boolean = false,
        trackIndex: Int? = null,
    ) = Subtitle(
        id = id,
        url = "",
        lang = lang,
        label = label,
        isEmbedded = embedded,
        trackIndex = trackIndex,
        // Mirrors PlayerScreen: the broad isForced trusts the flag OR a "forced" name hint.
        isForced = forcedFlag || label.contains("forced", ignoreCase = true),
        hasForcedFlag = forcedFlag,
    )

    private val englishForced = sub("1", "en", "English forced")
    private val englishFull = sub("2", "en", "English")

    // ── §6: the four cases the feature was asked for ──

    @Test
    fun forcedTrackWinsOverTheFullOneInTheSameLanguage() {
        val picked = ForcedSubtitles.pick(listOf(englishFull, englishForced), "en", null, normalize)

        assertThat(picked).isEqualTo(englishForced)
    }

    @Test
    fun noForcedTrackSelectsNothing() {
        val picked = ForcedSubtitles.pick(listOf(englishFull), "en", null, normalize)

        assertThat(picked).isNull()
    }

    @Test
    fun forcedTrackInAnotherLanguageIsNotAStandIn() {
        val germanForced = sub("3", "de", "Deutsch forced")

        val picked = ForcedSubtitles.pick(listOf(germanForced, englishFull), "en", null, normalize)

        assertThat(picked).isNull()
    }

    @Test
    fun fallbackLanguageIsTriedOnlyAfterThePreferredOne() {
        val germanForced = sub("3", "de", "Deutsch forced")

        assertThat(ForcedSubtitles.pick(listOf(germanForced), "en", "de", normalize))
            .isEqualTo(germanForced)
        assertThat(ForcedSubtitles.pick(listOf(germanForced, englishForced), "en", "de", normalize))
            .isEqualTo(englishForced)
    }

    // ── §7: the audio-language condition ──

    @Test
    fun matchingAudioLetsTheRuleRun() {
        assertThat(ForcedSubtitles.ruleApplies("en", "en", normalize)).isTrue()
        assertThat(ForcedSubtitles.ruleApplies("English", "en", normalize)).isTrue()
    }

    @Test
    fun dubbedAudioLeavesTheRuleAlone() {
        // German audio, English subtitles: forced would show almost nothing, so the normal
        // behaviour has to stay. This is the case that keeps the setting from doing harm.
        assertThat(ForcedSubtitles.ruleApplies("de", "en", normalize)).isFalse()
    }

    @Test
    fun unknownAudioCountsAsAMatch() {
        // The load-bearing case: without it the setting silently does nothing for every source
        // that ships audio with no language tag — which is most of the ones complained about.
        assertThat(ForcedSubtitles.ruleApplies(null, "en", normalize)).isTrue()
        assertThat(ForcedSubtitles.ruleApplies("", "en", normalize)).isTrue()
        assertThat(ForcedSubtitles.ruleApplies("und", "en", normalize)).isTrue()
        assertThat(ForcedSubtitles.ruleApplies("  ", "en", normalize)).isTrue()
    }

    @Test
    fun withoutASubtitleLanguageTheRuleNeverRuns() {
        assertThat(ForcedSubtitles.ruleApplies("en", "", normalize)).isFalse()
    }

    // ── §7a: R1, the safeguard against a track that only says "forced" ──

    @Test
    fun containerFlagBeatsANameThatMerelySaysForced() {
        val nameOnly = sub("name", "en", "English forced", trackIndex = 0)
        val reallyFlagged = sub("flag", "en", "English", forcedFlag = true, trackIndex = 1)

        val picked = ForcedSubtitles.pick(listOf(nameOnly, reallyFlagged), "en", null, normalize)

        assertThat(picked).isEqualTo(reallyFlagged)
    }

    @Test
    fun anEmbeddedForcedTrackBeatsAnAddonOneOfTheSameLanguage() {
        val addon = sub("addon", "en", "English forced", embedded = false)
        val embedded = sub("embedded", "en", "English forced", embedded = true)

        val picked = ForcedSubtitles.pick(listOf(addon, embedded), "en", null, normalize)

        assertThat(picked).isEqualTo(embedded)
    }

    // ── Track recognition ──

    @Test
    fun forcedIsRecognisedFromFlagLabelAndFileName() {
        assertThat(ForcedSubtitles.isForcedTrack(englishForced)).isTrue()
        assertThat(ForcedSubtitles.isForcedTrack(sub("f", "en", "English", forcedFlag = true))).isTrue()
        assertThat(
            ForcedSubtitles.isForcedTrack(
                Subtitle(id = "Movie.2024.1080p.FORCED.srt", url = "", lang = "en", label = "English")
            )
        ).isTrue()
        assertThat(ForcedSubtitles.isForcedTrack(englishFull)).isFalse()
    }

    @Test
    fun aWordThatMerelyContainsForcedDoesNotCount() {
        // "reinforced" would otherwise turn a full track into a forced one.
        val misleading = Subtitle(id = "x", url = "", lang = "en", label = "English (reinforced audio)")

        assertThat(ForcedSubtitles.isForcedTrack(misleading)).isFalse()
    }

    @Test
    fun theLanguageMayBeNamedByTheLabelInsteadOfTheCode() {
        // Addon subtitles routinely arrive with lang="" and the language only in the release name.
        val byLabel = sub("x", "", "English forced", embedded = false)

        assertThat(ForcedSubtitles.pick(listOf(byLabel), "en", null, normalize)).isEqualTo(byLabel)
    }

    // ── The gate that made the whole setting look dead (king cOllier, 19.09.) ──

    @Test
    fun aPlainTrackAlreadyOnScreenIsNeverGoodEnough() {
        // The reported failure: a file with ~10 English tracks. Once any plain English one was
        // selected, every language-based gate in the player said "already English, nothing to do"
        // and the rule never ran again. Language cannot decide this — only the track itself can.
        assertThat(ForcedSubtitles.needsAnotherLook(englishFull, ruleActive = true)).isTrue()
    }

    @Test
    fun aForcedTrackAlreadyOnScreenIsLeftAlone() {
        assertThat(ForcedSubtitles.needsAnotherLook(englishForced, ruleActive = true)).isFalse()
    }

    @Test
    fun nothingSelectedAlwaysNeedsADecision() {
        assertThat(ForcedSubtitles.needsAnotherLook(null, ruleActive = true)).isTrue()
    }

    @Test
    fun withTheRuleInactiveNothingIsReconsidered() {
        // Switch off, or a dubbed film: the existing behaviour must not be disturbed at all.
        assertThat(ForcedSubtitles.needsAnotherLook(englishFull, ruleActive = false)).isFalse()
        assertThat(ForcedSubtitles.needsAnotherLook(null, ruleActive = false)).isFalse()
    }

    @Test
    fun anEmptyTrackListSelectsNothing() {
        assertThat(ForcedSubtitles.pick(emptyList(), "en", "de", normalize)).isNull()
    }
}
