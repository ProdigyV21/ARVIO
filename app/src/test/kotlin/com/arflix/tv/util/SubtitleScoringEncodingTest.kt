package com.arflix.tv.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * A stream name can arrive URL-encoded ('+' for spaces) before something later in the pipeline
 * decodes it. Scoring the encoded form used to tokenise to nothing, so every subtitle tied at 0 and
 * the ranking silently degraded into arrival order — which is how auto-match started from the worst
 * candidate instead of the best (From S02E04, Sept 2026).
 */
class SubtitleScoringEncodingTest {

    private val decoded = "FROM 2022 S02E04 This Way Gone 2160p MGMP WEB-DL DDP5 1 H 265-DUSKLiGHT.mkv"
    private val encoded = "FROM+2022+S02E04+This+Way+Gone+2160p+MGMP+WEB-DL+DDP5+1+H+265-DUSKLiGHT.mkv"
    private val subtitleId = "From.S02E04.This.Way.Gone.2160p.MGMP.WEB-DL.DDP5.1.H.265-DUSKLiGHT"

    @Test
    fun encodedStreamNameScoresTheSameAsTheDecodedOne() {
        val fromDecoded = weightedSubtitleScore(decoded, subtitleId)
        val fromEncoded = weightedSubtitleScore(encoded, subtitleId)

        assertThat(fromDecoded).isGreaterThan(0)
        assertThat(fromEncoded).isEqualTo(fromDecoded)
    }

    @Test
    fun rankingOrderSurvivesEncoding() {
        val matching = "From.S02E04.This.Way.Gone.2160p.MGMP.WEB-DL.DDP5.1.H.265-DUSKLiGHT"
        val unrelated = "From.S02E04.720p.WEB.h264-GOSSIP"

        assertThat(weightedSubtitleScore(encoded, matching))
            .isGreaterThan(weightedSubtitleScore(encoded, unrelated))
    }

    @Test
    fun blankSourceStillScoresZero() {
        assertThat(weightedSubtitleScore("", subtitleId)).isEqualTo(0)
        assertThat(weightedSubtitleScore(encoded, "")).isEqualTo(0)
    }

    @Test
    fun matchingSourceOutranksSharedEpisodeTitle() {
        // Reacher S01E02 (Sept 2026): the AMZN WEB-DL subtitle names the episode ("First Dance"),
        // the BluRay one does not. On a BluRay source the BluRay cut is the right one — the AMZN one
        // was 3.3s out — but episode-title words used to count as full title words and ranked it
        // first (48 vs 28).
        val source = "Reacher S01E02 First Dance 2160p UHD BluRay HDR10 10bit Dts-HDMa5 1 HEVC-d3g.mkv"
        val bluRay = weightedSubtitleScore(source, "[WIZDOM]Reacher.S01E02.1080p.BluRay.x264-BROADCAST")
        val amzn = weightedSubtitleScore(source, "[WIZDOM]Reacher.S01E02.First.Dance.720p.AMZN.WEB-DL.DDP5.1.1.H.264-NTb")

        assertThat(bluRay).isGreaterThan(amzn)
    }

    @Test
    fun episodeTitleStillBreaksATieBetweenOtherwiseEqualReleases() {
        val source = "Reacher S01E02 First Dance 1080p AMZN WEB-DL DDP5 1 H 264-NTb.mkv"
        val named = weightedSubtitleScore(source, "Reacher.S01E02.First.Dance.1080p.AMZN.WEB-DL.DDP5.1.H.264-NTb")
        val unnamed = weightedSubtitleScore(source, "Reacher.S01E02.1080p.AMZN.WEB-DL.DDP5.1.H.264-NTb")

        assertThat(named).isGreaterThan(unnamed)
    }
}
