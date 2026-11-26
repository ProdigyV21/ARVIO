package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.screens.player.audiosync.SubtitleSyncModel
import com.arflix.tv.ui.screens.player.audiosync.SubtitleSyncSegment
import com.arflix.tv.util.normalizeLanguageCode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EnglishAiSourceFallbackTest {

    private fun addon(id: String, lang: String = "en", label: String = id, url: String = "https://example.test/$id.srt") =
        Subtitle(id = id, url = url, lang = lang, label = label, provider = "addon")

    private fun candidates(subs: List<Subtitle>, target: String = "el", scores: Map<String, Int> = emptyMap()) =
        EnglishAiSourceFallback.candidates(subs, target, ::normalizeLanguageCode) { scores[it.id] ?: 0 }

    // ---- candidate order ----

    @Test fun bestReleaseMatchComesFirst() {
        val subs = listOf(addon("a"), addon("b"), addon("c"))
        val order = candidates(subs, scores = mapOf("a" to 10, "b" to 80, "c" to 40)).map { it.id }
        assertEquals(listOf("b", "c", "a"), order)
    }

    @Test fun hearingImpairedGoesAfterOthersAtTheSameScore() {
        val subs = listOf(addon("sdh", label = "English SDH"), addon("hi", label = "English [HI]"), addon("plain"))
        assertEquals("plain", candidates(subs).first().id)
    }

    @Test fun aBetterScoreStillBeatsTheHearingImpairedRule() {
        val subs = listOf(addon("plain"), addon("sdh", label = "English SDH"))
        assertEquals("sdh", candidates(subs, scores = mapOf("sdh" to 90, "plain" to 20)).first().id)
    }

    @Test fun onlyEnglishTextAddonSubtitlesWithAUrl() {
        val subs = listOf(
            addon("greek", lang = "el"),
            addon("forced-flag").copy(isForced = true),
            addon("forced-label", label = "English Forced"),
            addon("bitmap").copy(isBitmap = true),
            addon("embedded").copy(isEmbedded = true),
            addon("no-url", url = ""),
            addon("eng", lang = "eng"),
        )
        assertEquals(listOf("eng"), candidates(subs).map { it.id })
    }

    @Test fun atMostSixAreTried() {
        val subs = (1..9).map { addon("s$it") }
        assertEquals(EnglishAiSourceFallback.MAX_CANDIDATES, candidates(subs).size)
    }

    @Test fun nothingWhenTheTargetIsEnglish() {
        assertTrue(candidates(listOf(addon("a")), target = "English").isEmpty())
    }

    // ---- confirm / drift ----

    private fun shift(ms: Long) = SubtitleSyncModel(listOf(SubtitleSyncSegment(0L, 1.0, ms.toDouble())))

    @Test fun waitsWhileTheAudioHasNotConfirmed() {
        val check = SteadyConfirmation()
        assertEquals(SteadyConfirmation.Verdict.Waiting, check.observe(null, 10_000L))
        assertEquals(SteadyConfirmation.Verdict.Waiting, check.observe(null, 60_000L))
    }

    @Test fun holdsOnlyAfterTheVerifyWindow() {
        val check = SteadyConfirmation(verifyMs = 20_000L, steadyMs = 400L)
        assertEquals(SteadyConfirmation.Verdict.Waiting, check.observe(shift(10_600L), 60_000L))
        assertEquals(SteadyConfirmation.Verdict.Waiting, check.observe(shift(10_700L), 70_000L))
        assertEquals(SteadyConfirmation.Verdict.Holds, check.observe(shift(10_650L), 80_000L))
    }

    @Test fun aNewOffsetIsAMove() {
        val check = SteadyConfirmation(verifyMs = 20_000L, steadyMs = 400L)
        check.observe(shift(2_000L), 60_000L)
        assertEquals(SteadyConfirmation.Verdict.Moved, check.observe(shift(5_000L), 70_000L))
    }

    @Test fun aRateCorrectionThatAgreesAtThePlayheadIsNotAMove() {
        val check = SteadyConfirmation(verifyMs = 20_000L, steadyMs = 400L)
        val offset = shift(10_000L)
        check.observe(offset, 600_000L)
        // 23.976/24: same delay at 600s as the plain offset, so not a move at the playhead.
        val rate = 1.001001
        val atPlayhead = offset.delayUsAt(620_000_000L) / 1_000.0
        val shiftMs = 620_000.0 - (620_000.0 - atPlayhead) * rate
        val stretched = SubtitleSyncModel(listOf(SubtitleSyncSegment(0L, rate, shiftMs)))
        assertEquals(SteadyConfirmation.Verdict.Holds, check.observe(stretched, 620_000L))
    }

    @Test fun aSeekBackRestartsTheWait() {
        val check = SteadyConfirmation(verifyMs = 20_000L, steadyMs = 400L)
        check.observe(shift(3_000L), 120_000L)
        assertEquals(SteadyConfirmation.Verdict.Waiting, check.observe(shift(3_000L), 30_000L))
        assertEquals(SteadyConfirmation.Verdict.Waiting, check.observe(shift(3_000L), 45_000L))
        assertEquals(SteadyConfirmation.Verdict.Holds, check.observe(shift(3_000L), 50_000L))
    }
}
