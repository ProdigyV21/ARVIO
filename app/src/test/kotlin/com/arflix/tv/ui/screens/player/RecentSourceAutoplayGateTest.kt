package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.RecentPlayedSource
import com.arflix.tv.data.model.StreamSource
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecentSourceAutoplayGateTest {

    private val recentStream = stream(addonId = "torrentio", url = "https://torrentio/play/recent")
    private val iptvStream = stream(addonId = "iptv_vod", url = "https://iptv/movie.mkv")
    private val recent = RecentPlayedSource.of(MediaType.MOVIE, 7, null, null, recentStream, playedAtMs = 1L)

    @Test
    fun withoutARecentSourceItNeverHolds() {
        val gate = RecentSourceAutoplayGate()
        gate.arm(null, nowMs = 0L)

        assertThat(gate.isHolding(0L)).isFalse()
    }

    @Test
    fun holdsFromTheMomentItIsArmedBeforeAnyResults() {
        val gate = RecentSourceAutoplayGate()
        gate.arm(recent, nowMs = 0L)

        assertThat(gate.isHolding(0L)).isTrue()
    }

    @Test
    fun anEarlyBackgroundResultDoesNotReleaseIt() {
        val gate = RecentSourceAutoplayGate()
        gate.arm(recent, nowMs = 0L)

        // IPTV / Home Server answer before the addons: nothing for autoplay to start yet.
        gate.onStreams(listOf(iptvStream))

        assertThat(gate.isHolding(300L)).isTrue()
        assertThat(gate.recentCandidate(listOf(iptvStream))).isNull()
    }

    @Test
    fun releasesWhenTheRecentSourceArrives() {
        val gate = RecentSourceAutoplayGate()
        gate.arm(recent, nowMs = 0L)

        gate.onStreams(listOf(iptvStream, recentStream))

        assertThat(gate.isHolding(1_000L)).isFalse()
        assertThat(gate.recentCandidate(listOf(iptvStream, recentStream))).isSameInstanceAs(recentStream)
    }

    @Test
    fun releasesWhenTheRecentSourceArrivesButAutoplaySettingsFilterItOut() {
        val gate = RecentSourceAutoplayGate()
        gate.arm(recent, nowMs = 0L)

        gate.onStreams(listOf(iptvStream, recentStream))

        assertThat(gate.isHolding(1_000L)).isFalse()
        assertThat(gate.recentCandidate(listOf(iptvStream))).isNull()
    }

    @Test
    fun runsOutAfterTheWait() {
        val gate = RecentSourceAutoplayGate(waitMs = 6_000L)
        gate.arm(recent, nowMs = 1_000L)

        assertThat(gate.isHolding(6_999L)).isTrue()
        assertThat(gate.remainingMs(6_999L)).isEqualTo(1L)
        assertThat(gate.isHolding(7_000L)).isFalse()
        assertThat(gate.remainingMs(9_000L)).isEqualTo(0L)
    }

    @Test
    fun releaseAndDisarmEndTheHold() {
        val gate = RecentSourceAutoplayGate()
        gate.arm(recent, nowMs = 0L)
        gate.release()
        assertThat(gate.isHolding(0L)).isFalse()

        gate.arm(recent, nowMs = 0L)
        gate.disarm()
        assertThat(gate.isHolding(0L)).isFalse()
        assertThat(gate.recent).isNull()
    }

    private fun stream(addonId: String, url: String) = StreamSource(
        source = "Movie 1080p",
        addonName = addonId,
        addonId = addonId,
        quality = "1080p",
        size = "4 GB",
        url = url,
    )
}
