package com.arflix.tv.ui.screens.player.mobile

import android.graphics.Bitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ApplicationProvider
import java.io.File
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.Episode
import com.arflix.tv.ui.screens.player.NextEpisodeAirDateResolution
import com.arflix.tv.ui.screens.player.common.NextEpisodePromptGate
import com.arflix.tv.ui.screens.player.common.PlaybackEpisodeKey
import com.arflix.tv.ui.screens.player.PlayerUiState
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MobilePlayerInteractionTest {
    @get:Rule val compose = createComposeRule()

    @Test
    fun scrubCommitsLatestPositionAfterRecomposition() {
        val preview = mutableStateOf(0f)
        val committed = mutableListOf<Float>()
        var cancellations = 0
        compose.setContent {
            val latestPreview = preview.value
            Scrubber(
                preview = latestPreview,
                onMove = { preview.value = it },
                onEnd = { committed += latestPreview },
                onCancel = { cancellations++ },
            )
        }
        compose.onNodeWithTag(SCRUBBER).performTouchInput {
            down(Offset(width * 0.2f, height / 2f))
        }
        compose.waitForIdle()
        compose.onNodeWithTag(SCRUBBER).performTouchInput {
            moveTo(Offset(width * 0.8f, height / 2f))
        }
        compose.waitForIdle()
        compose.onNodeWithTag(SCRUBBER).performTouchInput { up() }
        compose.runOnIdle {
            assertEquals(1, committed.size)
            assertEquals(0.8f, committed.single(), 0.02f)
            assertEquals(0, cancellations)
        }
    }

    @Test
    fun cancelledScrubDoesNotCommit() {
        var commits = 0
        var cancellations = 0
        compose.setContent { Scrubber(0f, {}, { commits++ }, { cancellations++ }) }
        compose.onNodeWithTag(SCRUBBER).performTouchInput {
            down(center)
            moveTo(Offset(width * 0.8f, height / 2f))
            cancel()
        }
        compose.runOnIdle {
            assertEquals(0, commits)
            assertEquals(1, cancellations)
        }
    }

    @Test
    fun removingScrubberCancelsOutstandingGesture() {
        val visible = mutableStateOf(true)
        var commits = 0
        var cancellations = 0
        compose.setContent {
            if (visible.value) Scrubber(0f, {}, { commits++ }, { cancellations++ })
        }
        compose.onNodeWithTag(SCRUBBER).performTouchInput { down(center) }
        compose.runOnIdle { visible.value = false }
        compose.waitForIdle()
        compose.runOnIdle {
            assertEquals(0, commits)
            assertEquals(1, cancellations)
        }
    }

    @Test
    fun nearingEndWhilePausedDoesNotExitOrAdvance() {
        var exits = 0
        var next = 0
        compose.setContent {
            PlayerHarness(
                position = 99_800L,
                onBack = { exits++ },
                onNext = { next++ },
            )
        }
        compose.waitForIdle()
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        File(context.getExternalFilesDir(null), "pr610-mobile-player.png").outputStream().use {
            compose.onRoot().captureToImage().asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        compose.runOnIdle {
            assertEquals(0, exits)
            assertEquals(0, next)
        }
    }

    @Test
    fun cancellingUpNextSuppressesEndCountdown() {
        val gate = NextEpisodePromptGate()
        val episode = PlaybackEpisodeKey(mediaId = 42, seasonNumber = 1, episodeNumber = 1)
        var next = 0
        compose.setContent {
            PlayerHarness(
                position = 90_000L,
                onNext = { next++ },
                onDismissNext = { gate.dismiss(episode) },
                playerState = PlayerUiState(
                    title = "Test series", selectedStreamUrl = "https://example.invalid/video.mp4",
                    isLoading = false, isLoadingStreams = false, mediaType = MediaType.TV,
                    seasonNumber = 1, episodeNumber = 1, autoPlayNext = true,
                    seasonEpisodes = listOf(Episode(2, 2, 1, "Next episode")),
                ),
            )
        }
        compose.onNodeWithText("Cancel").performClick()
        compose.runOnIdle {
            assertEquals(0, next)
            assertEquals(false, gate.tryOpen(episode, true, NextEpisodeAirDateResolution.Allowed))
        }
    }

    @Test
    fun doubleTapUsesUpdatedPlaybackCallback() {
        val position = mutableStateOf(10_000L)
        val seeks = mutableListOf<Long>()
        compose.setContent {
            val current = position.value
            PlayerHarness(position = current, onForward = { seeks += current + 10_000L })
        }
        compose.runOnIdle { position.value = 45_000L }
        compose.onRoot().performTouchInput {
            val target = Offset(width * 0.85f, height * 0.45f)
            down(target)
            up()
            advanceEventTime(100)
            down(target)
            up()
        }
        compose.waitUntil(3_000L) { seeks.isNotEmpty() }
        compose.runOnIdle { assertEquals(listOf(55_000L), seeks) }
    }

    @Composable
    private fun Scrubber(preview: Float, onMove: (Float) -> Unit, onEnd: () -> Unit, onCancel: () -> Unit) {
        MobilePlayerBottomSection(
            eyebrow = "", mainTitle = "Test playback", currentPositionMs = 10_000L,
            durationMs = 100_000L, bufferedPositionMs = 50_000L,
            isScrubbing = preview > 0f, scrubPreviewMs = (preview * 100_000L).toLong(),
            currentAudioTrack = "English", currentSubtitleTrack = "Off",
            currentPlaybackSpeed = 1f, isEpisodeListAvailable = false, isPromptShowing = false,
            onOpenSources = {}, onOpenEpisodes = {}, onOpenAudio = {}, onOpenSubtitles = {},
            onOpenSpeed = {}, onSeekStart = onMove, onSeekMove = onMove, onSeekEnd = onEnd,
            onSeekCancel = onCancel,
        )
    }

    @Composable
    private fun PlayerHarness(
        position: Long,
        onForward: () -> Unit = {},
        onBack: () -> Unit = {},
        onNext: () -> Unit = {},
        onDismissNext: () -> Unit = {},
        playerState: PlayerUiState = PlayerUiState(
            title = "Test playback", selectedStreamUrl = "https://example.invalid/video.mp4",
            isLoading = false, isLoadingStreams = false, mediaType = MediaType.MOVIE,
        ),
    ) {
        Box(Modifier.fillMaxSize()) {
            ArvioMobilePlayer(
                uiState = playerState,
                isPlaying = false, isBuffering = false, hasPlaybackStarted = true,
                currentPositionMs = position, durationMs = 100_000L, bufferedPositionMs = 80_000L,
                audioTracks = emptyList(), selectedAudioIndex = 0, currentPlaybackSpeed = 1f,
                aspectModeLabel = "Auto", isCasting = false, showCastButton = false,
                showPipButton = false,
                onTogglePlayPause = {}, onSeekTo = {}, onRewind10 = {}, onForward10 = onForward,
                onCycleAspectRatio = {}, onSelectAspectRatio = {}, onSelectEpisode = {},
                onSelectSource = {}, onSelectAudioTrack = {}, onSelectSubtitleTrack = {},
                onSelectPlaybackSpeed = {}, onSkipIntro = {}, onSkipOutro = {}, onPlayNextEpisode = onNext,
                canPlayNextEpisode = playerState.seasonEpisodes.isNotEmpty(),
                onDismissNextEpisode = onDismissNext,
                onEnterPip = {}, onOpenCastChooser = {}, onRetryPlayback = {}, onReloadStreams = {},
                onUpdateAutoplay = {}, onUpdateAutoSkipIntro = {}, onUpdateAutoSkipOutro = {},
                onUpdateAudioDelay = {}, onUpdateVolumeNormalization = {}, onUpdateSubtitleDelay = {},
                onUpdateSubtitleSize = {}, onUpdateSubtitleColor = {}, onUpdateSubtitlePosition = {},
                onBack = onBack,
            )
        }
    }

    companion object {
        private const val SCRUBBER = "mobile_player_scrubber"
    }
}
