package com.arflix.tv.ui.screens.player.tv

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import com.arflix.tv.R
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.components.NextEpisodeOverlay
import com.arflix.tv.ui.components.StreamSelector
import com.arflix.tv.ui.screens.player.AudioTrackInfo
import com.arflix.tv.ui.screens.player.PlayerUiState
import com.arflix.tv.ui.screens.player.localizedText
import com.arflix.tv.ui.screens.player.preview.SeekPreviewFrame
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ArvioTvPlayer(
    uiState: PlayerUiState,
    isPlaying: Boolean,
    isBuffering: Boolean,
    hasPlaybackStarted: Boolean,
    currentPositionMs: Long,
    durationMs: Long,
    progress: Float,
    audioTracks: List<AudioTrackInfo>,
    selectedAudioIndex: Int,
    aspectModeLabel: String,
    clockFormat: String,
    mediaType: MediaType,
    seasonNumber: Int?,
    episodeNumber: Int?,
    playerAccent: Color,
    seekPreviewFrame: SeekPreviewFrame? = null,
    // Indicators & Overlays state
    showVolumeIndicator: Boolean,
    currentVolume: Int,
    maxVolume: Int,
    isMuted: Boolean,
    showAspectIndicator: Boolean,
    showSkipOverlay: Boolean,
    skipAmount: Int,
    skipPreviewPositionMs: Long,
    showNextEpisodePrompt: Boolean,
    pendingNextSeason: Int,
    pendingNextEpisode: Int,
    // Time formatters
    formatTime: (Long) -> String,
    formatClockTime: (Long, String) -> String,
    // Subtitle grouping
    subtitleGroups: List<Pair<String, List<Pair<Int, Subtitle>>>>,
    selectedSubtitle: Subtitle?,
    // Actions
    onTogglePlayPause: () -> Unit,
    onSeekTo: (Long) -> Unit,
    onRewind10: () -> Unit,
    onForward10: () -> Unit,
    onCycleAspectRatio: () -> Unit,
    onSelectStream: (StreamSource) -> Unit,
    onPrewarmStreams: (StreamSource) -> Unit,
    onSelectSubtitle: (Subtitle?) -> Unit,
    onSelectAudioTrack: (AudioTrackInfo) -> Unit,
    onPlayNextEpisode: () -> Unit,
    onDismissSkipIntro: () -> Unit,
    onPlayPendingNextEpisode: () -> Unit,
    onCancelNextEpisodePrompt: () -> Unit,
    onUpdateSubtitleDelay: (Long) -> Unit,
    onUpdateSubtitleSize: (Int) -> Unit,
    onUpdateSubtitleVerticalPosition: (Int) -> Unit,
    onRetryPlayback: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    // TV Focus Requesters
    val containerFocusRequester = remember { FocusRequester() }
    val playButtonFocusRequester = remember { FocusRequester() }
    val trackbarFocusRequester = remember { FocusRequester() }
    val subtitleButtonFocusRequester = remember { FocusRequester() }
    val subtitleSettingsBtnFocusRequester = remember { FocusRequester() }
    val sourceButtonFocusRequester = remember { FocusRequester() }
    val rewindButtonFocusRequester = remember { FocusRequester() }
    val forwardButtonFocusRequester = remember { FocusRequester() }
    val aspectButtonFocusRequester = remember { FocusRequester() }
    val nextEpisodeButtonFocusRequester = remember { FocusRequester() }
    val skipIntroFocusRequester = remember { FocusRequester() }

    // TV UI visibility state
    var showControls by remember { mutableStateOf(false) }
    var showSubtitleMenu by remember { mutableStateOf(false) }
    var showSubtitleSettings by remember { mutableStateOf(false) }
    var showSourceMenu by remember { mutableStateOf(false) }

    // Subtitle & Settings navigation indices
    var subtitleMenuTab by remember { mutableIntStateOf(0) }
    var subtitleLangIndex by remember { mutableIntStateOf(0) }
    var subtitleTrackIndex by remember { mutableIntStateOf(0) }
    var subtitlePanelFocus by remember { mutableIntStateOf(0) }
    var audioMenuIndex by remember { mutableIntStateOf(0) }
    var subtitleSettingsRow by remember { mutableIntStateOf(0) }
    var subtitleSyncOffsetMs by remember { mutableLongStateOf(0L) }
    var subtitleSizePct by remember { mutableIntStateOf(100) }
    var subtitleVerticalPct by remember { mutableIntStateOf(0) }

    // Scrubber state
    var isControlScrubbing by remember { mutableStateOf(false) }
    var scrubPreviewPosition by remember { mutableLongStateOf(0L) }

    // Up Next prompt button focus
    var nextEpisodePromptButton by remember { mutableIntStateOf(0) }

    // Error modal focus
    var errorModalFocusIndex by remember { mutableIntStateOf(0) }

    // Auto-hide controls timer
    var interactionCount by remember { mutableIntStateOf(0) }
    LaunchedEffect(showControls, isPlaying, showSubtitleMenu, showSourceMenu, showSubtitleSettings, interactionCount) {
        if (showControls && isPlaying && !showSubtitleMenu && !showSourceMenu && !showSubtitleSettings) {
            delay(5000)
            showControls = false
        }
    }

    // Default container focus
    LaunchedEffect(Unit) {
        delay(100)
        runCatching { containerFocusRequester.requestFocus() }
    }

    // Back button handling
    BackHandler(enabled = showSubtitleMenu) {
        showSubtitleMenu = false
        showControls = true
        coroutineScope.launch {
            delay(150)
            runCatching { subtitleButtonFocusRequester.requestFocus() }
        }
    }

    BackHandler(enabled = showSubtitleSettings) {
        showSubtitleSettings = false
        showControls = true
        coroutineScope.launch {
            delay(150)
            runCatching { subtitleSettingsBtnFocusRequester.requestFocus() }
        }
    }

    BackHandler(enabled = showSourceMenu) {
        showSourceMenu = false
        showControls = true
        coroutineScope.launch {
            delay(150)
            runCatching { sourceButtonFocusRequester.requestFocus() }
        }
    }

    BackHandler(enabled = showNextEpisodePrompt) {
        onCancelNextEpisodePrompt()
    }

    BackHandler(enabled = showControls && !showSubtitleMenu && !showSourceMenu && !showSubtitleSettings) {
        showControls = false
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .focusRequester(containerFocusRequester)
            .focusable()
            .onKeyEvent { event ->
                if (event.type != KeyEventType.KeyDown) return@onKeyEvent false

                val handled = when {
                    // Error modal D-pad handling
                    uiState.error != null -> {
                        val maxButtons = if (uiState.isSetupError) 0 else 1
                        when (event.key) {
                            Key.DirectionLeft -> { if (errorModalFocusIndex > 0) errorModalFocusIndex--; true }
                            Key.DirectionRight -> { if (errorModalFocusIndex < maxButtons) errorModalFocusIndex++; true }
                            Key.Enter, Key.DirectionCenter -> {
                                if (uiState.isSetupError) onBack()
                                else if (errorModalFocusIndex == 0) onRetryPlayback()
                                else onBack()
                                true
                            }
                            Key.Back, Key.Escape -> { onBack(); true }
                            else -> false
                        }
                    }

                    // Subtitle settings panel D-pad handling
                    showSubtitleSettings -> {
                        when (event.key) {
                            Key.DirectionUp -> { subtitleSettingsRow = (subtitleSettingsRow - 1).coerceAtLeast(0); true }
                            Key.DirectionDown -> { subtitleSettingsRow = (subtitleSettingsRow + 1).coerceAtMost(2); true }
                            Key.DirectionLeft -> {
                                when (subtitleSettingsRow) {
                                    0 -> { subtitleSyncOffsetMs = (subtitleSyncOffsetMs - 100L).coerceAtLeast(-10000L); onUpdateSubtitleDelay(subtitleSyncOffsetMs) }
                                    1 -> { subtitleSizePct = (subtitleSizePct - 10).coerceAtLeast(50); onUpdateSubtitleSize(subtitleSizePct) }
                                    2 -> { subtitleVerticalPct = (subtitleVerticalPct - 1).coerceAtLeast(0); onUpdateSubtitleVerticalPosition(subtitleVerticalPct) }
                                }
                                true
                            }
                            Key.DirectionRight -> {
                                when (subtitleSettingsRow) {
                                    0 -> { subtitleSyncOffsetMs = (subtitleSyncOffsetMs + 100L).coerceAtMost(10000L); onUpdateSubtitleDelay(subtitleSyncOffsetMs) }
                                    1 -> { subtitleSizePct = (subtitleSizePct + 10).coerceAtMost(250); onUpdateSubtitleSize(subtitleSizePct) }
                                    2 -> { subtitleVerticalPct = (subtitleVerticalPct + 1).coerceAtMost(50); onUpdateSubtitleVerticalPosition(subtitleVerticalPct) }
                                }
                                true
                            }
                            Key.Back, Key.Escape -> {
                                showSubtitleSettings = false
                                showControls = true
                                coroutineScope.launch {
                                    delay(120)
                                    runCatching { subtitleSettingsBtnFocusRequester.requestFocus() }
                                }
                                true
                            }
                            else -> false
                        }
                    }

                    // Subtitle / Audio Menu D-pad handling
                    showSubtitleMenu -> {
                        when (event.key) {
                            Key.DirectionUp -> {
                                when {
                                    subtitleMenuTab == 1 -> { if (audioMenuIndex > 0) audioMenuIndex-- }
                                    subtitlePanelFocus == 0 -> { if (subtitleLangIndex > 0) subtitleLangIndex-- }
                                    else -> { if (subtitleTrackIndex > 0) subtitleTrackIndex-- }
                                }
                                true
                            }
                            Key.DirectionDown -> {
                                when {
                                    subtitleMenuTab == 1 -> { if (audioMenuIndex < audioTracks.size - 1) audioMenuIndex++ }
                                    subtitlePanelFocus == 0 -> { if (subtitleLangIndex < subtitleGroups.size) subtitleLangIndex++ }
                                    else -> {
                                        val group = subtitleGroups.getOrNull(subtitleLangIndex - 1)
                                        if (group != null && subtitleTrackIndex < group.second.size - 1) subtitleTrackIndex++
                                    }
                                }
                                true
                            }
                            Key.DirectionLeft -> {
                                if (subtitlePanelFocus == 1) subtitlePanelFocus = 0
                                else if (subtitleMenuTab == 1) subtitleMenuTab = 0
                                true
                            }
                            Key.DirectionRight -> {
                                if (subtitleMenuTab == 0 && subtitlePanelFocus == 0 && subtitleLangIndex > 0) {
                                    subtitlePanelFocus = 1
                                    subtitleTrackIndex = 0
                                } else if (subtitleMenuTab == 0 && subtitlePanelFocus == 0 && subtitleLangIndex == 0) {
                                    subtitleMenuTab = 1
                                }
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                if (subtitleMenuTab == 1) {
                                    audioTracks.getOrNull(audioMenuIndex)?.let { onSelectAudioTrack(it) }
                                    showSubtitleMenu = false
                                    showControls = true
                                } else if (subtitlePanelFocus == 0) {
                                    if (subtitleLangIndex == 0) {
                                        onSelectSubtitle(null)
                                        showSubtitleMenu = false
                                        showControls = true
                                    } else {
                                        subtitlePanelFocus = 1
                                        subtitleTrackIndex = 0
                                    }
                                } else {
                                    val group = subtitleGroups.getOrNull(subtitleLangIndex - 1)
                                    val trackPair = group?.second?.getOrNull(subtitleTrackIndex)
                                    if (trackPair != null) {
                                        onSelectSubtitle(trackPair.second)
                                    }
                                    showSubtitleMenu = false
                                    showControls = true
                                }
                                true
                            }
                            Key.Back, Key.Escape -> {
                                showSubtitleMenu = false
                                showControls = true
                                coroutineScope.launch {
                                    delay(150)
                                    runCatching { subtitleButtonFocusRequester.requestFocus() }
                                }
                                true
                            }
                            else -> false
                        }
                    }

                    // TV Sources Menu D-pad / Back handling
                    showSourceMenu -> {
                        when (event.key) {
                            Key.Back, Key.Escape -> {
                                showSourceMenu = false
                                showControls = true
                                coroutineScope.launch {
                                    delay(150)
                                    runCatching { sourceButtonFocusRequester.requestFocus() }
                                }
                                true
                            }
                            else -> false
                        }
                    }

                    // Global Controls Key Events
                    else -> when (event.key) {
                        Key.MediaPlayPause -> { onTogglePlayPause(); showControls = true; true }
                        Key.MediaPlay -> { onTogglePlayPause(); showControls = true; true }
                        Key.MediaPause -> { onTogglePlayPause(); showControls = true; true }
                        Key.MediaFastForward -> { onForward10(); true }
                        Key.MediaRewind -> { onRewind10(); true }
                        Key.DirectionCenter, Key.Enter -> {
                            if (!showControls) {
                                showControls = true
                                coroutineScope.launch {
                                    delay(50)
                                    runCatching { playButtonFocusRequester.requestFocus() }
                                }
                                true
                            } else false
                        }
                        Key.DirectionUp, Key.DirectionDown, Key.DirectionLeft, Key.DirectionRight -> {
                            if (!showControls) {
                                showControls = true
                                coroutineScope.launch {
                                    delay(50)
                                    runCatching { playButtonFocusRequester.requestFocus() }
                                }
                                true
                            } else false
                        }
                        Key.Back, Key.Escape -> {
                            if (showControls) {
                                showControls = false
                                true
                            } else {
                                onBack()
                                true
                            }
                        }
                        else -> false
                    }
                }
                if (handled) {
                    interactionCount++
                }
                handled
            }
    ) {
        // TV Skip Intro Button
        if (hasPlaybackStarted) {
            val activeSkip = uiState.activeSkipInterval
            TvSkipIntroButton(
                interval = activeSkip,
                dismissed = uiState.skipIntervalDismissed,
                controlsVisible = showControls,
                onSkip = {
                    val end = activeSkip?.endMs ?: return@TvSkipIntroButton
                    onSeekTo((end + 500L).coerceAtLeast(0L))
                    onDismissSkipIntro()
                },
                focusRequester = skipIntroFocusRequester,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .zIndex(5f)
            )
        }

        // TV Netflix-style Controls Overlay
        TvPlayerControls(
            isVisible = hasPlaybackStarted && showControls && !showSubtitleMenu && !showSourceMenu && !showSubtitleSettings,
            uiState = uiState,
            isPlaying = isPlaying,
            isBuffering = isBuffering,
            currentPositionMs = currentPositionMs,
            durationMs = durationMs,
            progress = progress,
            isScrubbing = isControlScrubbing,
            scrubPreviewPositionMs = scrubPreviewPosition,
            clockFormat = clockFormat,
            aspectModeLabel = aspectModeLabel,
            mediaType = mediaType,
            seasonNumber = seasonNumber,
            episodeNumber = episodeNumber,
            playerAccent = playerAccent,
            seekPreviewFrame = seekPreviewFrame,
            formatTime = formatTime,
            formatClockTime = formatClockTime,
            playButtonFocusRequester = playButtonFocusRequester,
            rewindButtonFocusRequester = rewindButtonFocusRequester,
            forwardButtonFocusRequester = forwardButtonFocusRequester,
            subtitleButtonFocusRequester = subtitleButtonFocusRequester,
            subtitleSettingsBtnFocusRequester = subtitleSettingsBtnFocusRequester,
            sourceButtonFocusRequester = sourceButtonFocusRequester,
            aspectButtonFocusRequester = aspectButtonFocusRequester,
            nextEpisodeButtonFocusRequester = nextEpisodeButtonFocusRequester,
            trackbarFocusRequester = trackbarFocusRequester,
            skipIntroFocusRequester = skipIntroFocusRequester,
            onTogglePlayPause = {
                interactionCount++
                onTogglePlayPause()
            },
            onRewind10 = {
                interactionCount++
                onRewind10()
            },
            onForward10 = {
                interactionCount++
                onForward10()
            },
            onCycleAspectRatio = {
                interactionCount++
                onCycleAspectRatio()
            },
            onOpenSubtitlesMenu = {
                interactionCount++
                showSubtitleMenu = true
                coroutineScope.launch {
                    delay(50)
                    runCatching { containerFocusRequester.requestFocus() }
                }
            },
            onToggleSubtitleSettings = {
                interactionCount++
                showSubtitleSettings = !showSubtitleSettings
                if (showSubtitleSettings) {
                    subtitleSettingsRow = 0
                    coroutineScope.launch {
                        delay(50)
                        runCatching { containerFocusRequester.requestFocus() }
                    }
                }
            },
            onOpenSourceMenu = {
                interactionCount++
                showSourceMenu = true
                showControls = true
            },
            onPlayNextEpisode = {
                interactionCount++
                onPlayNextEpisode()
            },
            onScrubSeekDelta = { deltaMs ->
                interactionCount++
                if (durationMs > 0L) {
                    val base = if (isControlScrubbing) scrubPreviewPosition else currentPositionMs
                    scrubPreviewPosition = (base + deltaMs).coerceIn(0L, durationMs)
                    isControlScrubbing = true
                }
            },
            onCommitScrub = {
                interactionCount++
                if (isControlScrubbing) {
                    onSeekTo(scrubPreviewPosition)
                    isControlScrubbing = false
                }
            }
        )

        // TV Subtitle & Audio Menu Dialog
        TvSubtitleMenu(
            isVisible = showSubtitleMenu,
            selectedSubtitle = uiState.selectedSubtitle,
            audioTracks = audioTracks,
            selectedAudioIndex = selectedAudioIndex,
            activeTab = subtitleMenuTab,
            focusedIndex = audioMenuIndex,
            subtitleGroups = subtitleGroups,
            subtitleLangIndex = subtitleLangIndex,
            subtitleTrackIndex = subtitleTrackIndex,
            subtitlePanelFocus = subtitlePanelFocus,
            onTabChanged = { subtitleMenuTab = it },
            onSelectSubtitle = { globalIdx ->
                if (globalIdx < 0) {
                    onSelectSubtitle(null)
                } else {
                    val sub = uiState.subtitles.getOrNull(globalIdx)
                    onSelectSubtitle(sub)
                }
            },
            onSelectAudio = onSelectAudioTrack,
            onClose = {
                showSubtitleMenu = false
                showControls = true
                coroutineScope.launch {
                    delay(150)
                    runCatching { subtitleButtonFocusRequester.requestFocus() }
                }
            }
        )

        // TV Subtitle Settings Dialog
        TvSubtitleSettingsPanel(
            isVisible = showSubtitleSettings,
            selectedRow = subtitleSettingsRow,
            syncOffsetMs = subtitleSyncOffsetMs,
            sizePct = subtitleSizePct,
            verticalPct = subtitleVerticalPct,
            onRowSelect = { subtitleSettingsRow = it },
            onOffsetDecrease = {
                subtitleSyncOffsetMs = (subtitleSyncOffsetMs - 100L).coerceAtLeast(-10000L)
                onUpdateSubtitleDelay(subtitleSyncOffsetMs)
            },
            onOffsetIncrease = {
                subtitleSyncOffsetMs = (subtitleSyncOffsetMs + 100L).coerceAtMost(10000L)
                onUpdateSubtitleDelay(subtitleSyncOffsetMs)
            },
            onSizeDecrease = {
                subtitleSizePct = (subtitleSizePct - 10).coerceAtLeast(50)
                onUpdateSubtitleSize(subtitleSizePct)
            },
            onSizeIncrease = {
                subtitleSizePct = (subtitleSizePct + 10).coerceAtMost(300)
                onUpdateSubtitleSize(subtitleSizePct)
            },
            onVerticalDecrease = {
                subtitleVerticalPct = (subtitleVerticalPct - 1).coerceAtLeast(0)
                onUpdateSubtitleVerticalPosition(subtitleVerticalPct)
            },
            onVerticalIncrease = {
                subtitleVerticalPct = (subtitleVerticalPct + 1).coerceAtMost(50)
                onUpdateSubtitleVerticalPosition(subtitleVerticalPct)
            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(7f)
        )

        // TV Sources Menu
        StreamSelector(
            isVisible = showSourceMenu,
            streams = uiState.streams,
            selectedStream = uiState.selectedStream,
            isLoading = uiState.isLoadingStreams,
            hasStreamingAddons = !uiState.isSetupError,
            addonOrderedIds = uiState.addonOrderedIds,
            title = uiState.title,
            subtitle = if (seasonNumber != null && episodeNumber != null) stringResource(R.string.player_season_episode_short, seasonNumber, episodeNumber) else "",
            onFocusedStream = onPrewarmStreams,
            onSelect = { stream ->
                onSelectStream(stream)
                showSourceMenu = false
                showControls = true
                coroutineScope.launch {
                    delay(150)
                    runCatching { sourceButtonFocusRequester.requestFocus() }
                }
            },
            onClose = {
                showSourceMenu = false
                showControls = true
                coroutineScope.launch {
                    delay(150)
                    runCatching { sourceButtonFocusRequester.requestFocus() }
                }
            }
        )

        // TV Next Episode Overlay
        NextEpisodeOverlay(
            isVisible = showNextEpisodePrompt,
            showTitle = uiState.title,
            episodeTitle = stringResource(R.string.episode, pendingNextEpisode),
            seasonNumber = pendingNextSeason,
            episodeNumber = pendingNextEpisode,
            episodeImage = uiState.backdropUrl,
            countdownSeconds = 10,
            focusedButtonOverride = nextEpisodePromptButton,
            onFocusedButtonChange = { nextEpisodePromptButton = it },
            onPlayNext = onPlayPendingNextEpisode,
            onCancel = onCancelNextEpisodePrompt
        )

        // TV Volume Indicator HUD
        TvVolumeIndicator(
            isVisible = showVolumeIndicator,
            currentVolume = currentVolume,
            maxVolume = maxVolume,
            isMuted = isMuted,
            playerAccent = playerAccent,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .zIndex(6f)
        )

        // TV Aspect Ratio HUD Flash
        TvAspectIndicator(
            isVisible = showAspectIndicator,
            aspectModeLabel = aspectModeLabel,
            modifier = Modifier
                .align(Alignment.Center)
                .zIndex(6f)
        )

        // TV Skip Preview Overlay
        TvSkipOverlay(
            isVisible = showSkipOverlay,
            skipAmount = skipAmount,
            currentPositionMs = currentPositionMs,
            durationMs = durationMs,
            skipPreviewPositionMs = skipPreviewPositionMs,
            formatTime = formatTime,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(6f)
        )

        // TV Playback Error Modal
        TvErrorOverlay(
            isVisible = uiState.error != null,
            errorMessage = uiState.error?.localizedText(),
            isSetupError = uiState.isSetupError,
            focusIndex = errorModalFocusIndex,
            onRetry = onRetryPlayback,
            onBack = onBack,
            modifier = Modifier.zIndex(8f)
        )
    }
}
