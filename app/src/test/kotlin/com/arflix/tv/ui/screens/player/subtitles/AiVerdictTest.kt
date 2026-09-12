package com.arflix.tv.ui.screens.player.subtitles

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * The AI check has three outcomes and only one of them is a verification. A failed request, or an
 * answer too thin or inconsistent to measure, must leave the timing verdict standing WITHOUT being
 * remembered as a verified match — otherwise every later playback of the file skips the scan and
 * reuses a subtitle nothing ever confirmed.
 */
class AiVerdictTest {

    @Test
    fun failedRequestIsUnconfirmedAndNotRemembered() {
        // matchSubtitleLines returned null: HTTP error, rate limit, or an unreadable reply.
        val sync: AiLineSync? = null

        assertThat(sync.verdict()).isEqualTo(AiVerdict.UNCONFIRMED)
        assertThat(mayRememberMatch(aiIsArbiter = true, verdict = sync.verdict())).isFalse()
    }

    @Test
    fun tooFewPairsToMeasureIsUnconfirmedAndNotRemembered() {
        // 2 of 8 lines: not a veto (a quarter or more), but too few to measure an offset from.
        val sync = AiLineSync(pairs = 2, offsetMs = null, sent = 8)

        assertThat(sync.verdict()).isEqualTo(AiVerdict.UNCONFIRMED)
        assertThat(mayRememberMatch(aiIsArbiter = true, verdict = sync.verdict())).isFalse()
    }

    @Test
    fun pairsThatDisagreeAreUnconfirmedAndNotRemembered() {
        val sync = AiLineSync(pairs = 6, offsetMs = null)

        assertThat(sync.verdict()).isEqualTo(AiVerdict.UNCONFIRMED)
        assertThat(mayRememberMatch(aiIsArbiter = true, verdict = sync.verdict())).isFalse()
    }

    @Test
    fun agreeingPairsConfirmTheDialogueAndMayBeRemembered() {
        // 3 of 7 paired on a subtitle that was correct (From S01E10).
        val sync = AiLineSync(pairs = 3, offsetMs = -118)

        assertThat(sync.verdict()).isEqualTo(AiVerdict.CONFIRMED)
        assertThat(mayRememberMatch(aiIsArbiter = true, verdict = sync.verdict())).isTrue()
    }

    @Test
    fun zeroOrNearZeroPairsRejectTheSubtitle() {
        assertThat(AiLineSync(pairs = 0, offsetMs = null, sent = 8).verdict()).isEqualTo(AiVerdict.REJECTED)
        // The Office S01E03: 1 of 8 lines paired on a subtitle that scored 0.75 on timing alone.
        assertThat(AiLineSync(pairs = 1, offsetMs = null, sent = 8).verdict()).isEqualTo(AiVerdict.REJECTED)
    }

    @Test
    fun aShiftBeyondAnySyncErrorIsADifferentCutAndRejected() {
        val sync = AiLineSync(pairs = 5, offsetMs = null, unfixableShiftMs = 15_198)

        assertThat(sync.verdict()).isEqualTo(AiVerdict.REJECTED)
    }

    @Test
    fun withoutAiTheTimingVerdictIsTheVerification() {
        // Keyless users have no model to ask; a timing-verified match is remembered as it always was.
        assertThat(mayRememberMatch(aiIsArbiter = false, verdict = null)).isTrue()
    }

    @Test
    fun withAiACandidateTheModelNeverLookedAtIsNotRemembered() {
        assertThat(mayRememberMatch(aiIsArbiter = true, verdict = null)).isFalse()
    }
}
