package com.arflix.tv.ui.screens.player.engine.exoplayer

import androidx.annotation.OptIn
import androidx.media3.common.C
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.common.TrackSelectionOverride
import androidx.media3.common.Tracks
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import com.arflix.tv.ui.screens.player.engine.AudioTrackItem
import com.arflix.tv.ui.screens.player.engine.PlayerEngine
import com.arflix.tv.ui.screens.player.engine.PlayerEngineState
import com.arflix.tv.ui.screens.player.engine.PlayerEngineType
import com.arflix.tv.ui.screens.player.engine.ResizeMode
import com.arflix.tv.ui.screens.player.engine.SubtitleTrackItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * ExoPlayer implementation of [PlayerEngine].
 */
@OptIn(UnstableApi::class)
class ExoPlayerEngine(
    val exoPlayer: ExoPlayer,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) : PlayerEngine, Player.Listener {

    override val engineType: PlayerEngineType = PlayerEngineType.EXOPLAYER

    private val _state = MutableStateFlow(PlayerEngineState())
    override val state: StateFlow<PlayerEngineState> = _state.asStateFlow()

    private var pollJob: Job? = null

    init {
        exoPlayer.addListener(this)
        startPositionPolling()
        updateInitialState()
    }

    private fun startPositionPolling() {
        pollJob?.cancel()
        pollJob = scope.launch {
            while (isActive) {
                if (exoPlayer.isPlaying || exoPlayer.playbackState == Player.STATE_BUFFERING) {
                    _state.update { current ->
                        current.copy(
                            currentPositionMs = exoPlayer.currentPosition.coerceAtLeast(0L),
                            durationMs = exoPlayer.duration.coerceAtLeast(0L),
                            bufferedPositionMs = exoPlayer.bufferedPosition.coerceAtLeast(0L),
                            isPlaying = exoPlayer.isPlaying,
                            isBuffering = exoPlayer.playbackState == Player.STATE_BUFFERING && exoPlayer.playWhenReady,
                            hasPlaybackStarted = current.hasPlaybackStarted || (exoPlayer.currentPosition > 0L)
                        )
                    }
                }
                delay(250)
            }
        }
    }

    private fun updateInitialState() {
        _state.update {
            it.copy(
                currentPositionMs = exoPlayer.currentPosition.coerceAtLeast(0L),
                durationMs = exoPlayer.duration.coerceAtLeast(0L),
                bufferedPositionMs = exoPlayer.bufferedPosition.coerceAtLeast(0L),
                isPlaying = exoPlayer.isPlaying,
                isBuffering = exoPlayer.playbackState == Player.STATE_BUFFERING && exoPlayer.playWhenReady,
                playbackSpeed = exoPlayer.playbackParameters.speed,
                volume = exoPlayer.volume
            )
        }
    }

    override fun play() {
        exoPlayer.play()
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun togglePlayPause() {
        if (exoPlayer.playWhenReady) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
    }

    override fun seekTo(positionMs: Long) {
        val duration = exoPlayer.duration
        val target = if (duration > 0L) positionMs.coerceIn(0L, duration) else positionMs.coerceAtLeast(0L)
        exoPlayer.seekTo(target)
        _state.update { it.copy(currentPositionMs = target) }
    }

    override fun setPlaybackSpeed(speed: Float) {
        exoPlayer.setPlaybackSpeed(speed)
        _state.update { it.copy(playbackSpeed = speed) }
    }

    override fun setResizeMode(mode: ResizeMode) {
        _state.update { it.copy(resizeMode = mode) }
    }

    override fun selectAudioTrack(index: Int) {
        val tracks = exoPlayer.currentTracks
        var flatTrackIndex = 0
        for (group in tracks.groups) {
            if (group.type == C.TRACK_TYPE_AUDIO) {
                for (trackIdx in 0 until group.length) {
                    if (flatTrackIndex == index) {
                        exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                            .buildUpon()
                            .setOverrideForType(
                                TrackSelectionOverride(group.mediaTrackGroup, trackIdx)
                            )
                            .build()
                        _state.update { it.copy(selectedAudioIndex = index) }
                        return
                    }
                    flatTrackIndex++
                }
            }
        }
    }

    override fun selectSubtitleTrack(track: SubtitleTrackItem?) {
        if (track == null) {
            exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                .buildUpon()
                .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, true)
                .build()
            _state.update { it.copy(selectedSubtitleTrack = null) }
        } else {
            val tracks = exoPlayer.currentTracks
            var textIndex = 0
            for (group in tracks.groups) {
                if (group.type == C.TRACK_TYPE_TEXT) {
                    for (i in 0 until group.length) {
                        val format = group.getTrackFormat(i)
                        val resolvedId = format.id ?: textIndex.toString()
                        if (format.id == track.id || resolvedId == track.id) {
                            exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                                .buildUpon()
                                .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
                                .setOverrideForType(
                                    TrackSelectionOverride(group.mediaTrackGroup, i)
                                )
                                .build()
                            _state.update { it.copy(selectedSubtitleTrack = track) }
                            return
                        }
                        textIndex++
                    }
                }
            }
        }
    }

    override fun setAudioDelayMs(delayMs: Long) {
        _state.update { it.copy(audioDelayMs = delayMs) }
    }

    override fun setVolume(volume: Float) {
        exoPlayer.volume = volume.coerceIn(0f, 1f)
        _state.update { it.copy(volume = volume, isMuted = volume == 0f) }
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {
        _state.update { it.copy(playbackSpeed = playbackParameters.speed) }
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        _state.update { it.copy(isPlaying = isPlaying) }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        val isBuffering = playbackState == Player.STATE_BUFFERING && exoPlayer.playWhenReady
        _state.update {
            it.copy(
                isBuffering = isBuffering,
                durationMs = exoPlayer.duration.coerceAtLeast(0L),
                bufferedPositionMs = exoPlayer.bufferedPosition.coerceAtLeast(0L)
            )
        }
    }

    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
        val isBuffering = exoPlayer.playbackState == Player.STATE_BUFFERING && playWhenReady
        _state.update { it.copy(isBuffering = isBuffering) }
    }

    override fun onTracksChanged(tracks: Tracks) {
        val audioList = mutableListOf<AudioTrackItem>()
        val subList = mutableListOf<SubtitleTrackItem>()
        var audioIndex = 0

        for (group in tracks.groups) {
            when (group.type) {
                C.TRACK_TYPE_AUDIO -> {
                    for (i in 0 until group.length) {
                        val format = group.getTrackFormat(i)
                        audioList.add(
                            AudioTrackItem(
                                id = format.id ?: audioIndex.toString(),
                                index = audioIndex,
                                label = format.label,
                                language = format.language,
                                isDefault = (format.selectionFlags and C.SELECTION_FLAG_DEFAULT) != 0,
                                mimeType = format.sampleMimeType,
                                channelCount = format.channelCount,
                                sampleRate = format.sampleRate
                            )
                        )
                        audioIndex++
                    }
                }
                C.TRACK_TYPE_TEXT -> {
                    for (i in 0 until group.length) {
                        val format = group.getTrackFormat(i)
                        subList.add(
                            SubtitleTrackItem(
                                id = format.id ?: subList.size.toString(),
                                label = format.label,
                                language = format.language,
                                isDefault = (format.selectionFlags and C.SELECTION_FLAG_DEFAULT) != 0
                            )
                        )
                    }
                }
            }
        }

        _state.update {
            it.copy(
                audioTracks = audioList,
                subtitleTracks = subList
            )
        }
    }

    override fun release() {
        pollJob?.cancel()
        exoPlayer.removeListener(this)
    }
}
