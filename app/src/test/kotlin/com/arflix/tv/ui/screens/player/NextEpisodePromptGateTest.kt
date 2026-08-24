package com.arflix.tv.ui.screens.player

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class NextEpisodePromptGateTest {

    @Test
    fun dismissedPromptDoesNotReopenWhilePlayerRemainsEnded() {
        val gate = NextEpisodePromptGate()
        val episode = PlaybackEpisodeKey(mediaId = 42, seasonNumber = 1, episodeNumber = 3)

        assertThat(gate.tryOpen(episode, true, NextEpisodeAirDateResolution.Allowed)).isTrue()

        // Closing the overlay does not change ExoPlayer's STATE_ENDED. The next polling tick must
        // therefore reject this same episode instead of starting a fresh countdown.
        assertThat(gate.tryOpen(episode, true, NextEpisodeAirDateResolution.Allowed)).isFalse()
    }

    @Test
    fun nextEpisodeCanOpenItsOwnPrompt() {
        val gate = NextEpisodePromptGate()

        assertThat(
            gate.tryOpen(
                PlaybackEpisodeKey(mediaId = 42, seasonNumber = 1, episodeNumber = 3),
                eligible = true,
                airDateResolution = NextEpisodeAirDateResolution.Allowed,
            )
        ).isTrue()
        assertThat(
            gate.tryOpen(
                PlaybackEpisodeKey(mediaId = 42, seasonNumber = 1, episodeNumber = 4),
                eligible = true,
                airDateResolution = NextEpisodeAirDateResolution.Allowed,
            )
        ).isTrue()
    }

    @Test
    fun ineligibleStateDoesNotConsumeTheEpisode() {
        val gate = NextEpisodePromptGate()
        val episode = PlaybackEpisodeKey(mediaId = 42, seasonNumber = 1, episodeNumber = 3)

        assertThat(gate.tryOpen(episode, false, NextEpisodeAirDateResolution.Allowed)).isFalse()
        assertThat(gate.tryOpen(episode, true, NextEpisodeAirDateResolution.Allowed)).isTrue()
    }

    @Test
    fun pendingAirDateDoesNotOpenOrConsumeTheEpisode() {
        val gate = NextEpisodePromptGate()
        val episode = PlaybackEpisodeKey(mediaId = 42, seasonNumber = 1, episodeNumber = 3)

        assertThat(
            gate.tryOpen(
                episode = episode,
                eligible = true,
                airDateResolution = NextEpisodeAirDateResolution.Pending,
            )
        ).isFalse()
        assertThat(
            gate.tryOpen(
                episode = episode,
                eligible = true,
                airDateResolution = NextEpisodeAirDateResolution.Allowed,
            )
        ).isTrue()
    }

    @Test
    fun blockedAirDateDoesNotOpenTheEpisode() {
        val gate = NextEpisodePromptGate()
        val episode = PlaybackEpisodeKey(mediaId = 42, seasonNumber = 1, episodeNumber = 3)

        assertThat(
            gate.tryOpen(
                episode = episode,
                eligible = true,
                airDateResolution = NextEpisodeAirDateResolution.Blocked(
                    NextEpisodeAirDateBlockReason.FutureAirDate,
                ),
            )
        ).isFalse()
    }

    @Test
    fun differentTmdbIdentityCanOpenItsOwnPrompt() {
        val gate = NextEpisodePromptGate()
        val first = PlaybackEpisodeKey(
            mediaId = 42,
            seasonNumber = 1,
            episodeNumber = 1,
            tmdbSeasonNumber = 1,
            tmdbEpisodeNumber = 12,
        )
        val second = first.copy(tmdbSeasonNumber = 2, tmdbEpisodeNumber = 1)

        assertThat(gate.tryOpen(first, true, NextEpisodeAirDateResolution.Allowed)).isTrue()
        assertThat(gate.tryOpen(second, true, NextEpisodeAirDateResolution.Allowed)).isTrue()
    }
}
