package com.arflix.tv.ui.screens.player.mobile

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.database.ContentObserver
import android.media.AudioManager
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.core.content.ContextCompat
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import android.os.SystemClock
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import kotlin.math.abs
import kotlin.math.hypot
import kotlin.math.roundToInt
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.Job
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.arflix.tv.ui.screens.player.common.PlayerSystemBarsEffect
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.data.repository.SkipInterval
import com.arflix.tv.ui.screens.player.AudioTrackInfo
import com.arflix.tv.ui.screens.player.PlayerUiState
import com.arflix.tv.ui.screens.player.localizedText
import com.arflix.tv.ui.screens.player.preview.SeekPreviewFrame
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.ErrorRed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * Full touch-optimized mobile player container for ARVIO.
 */
@Composable
fun ArvioMobilePlayer(
    uiState: PlayerUiState,
    isPlaying: Boolean,
    isBuffering: Boolean,
    hasPlaybackStarted: Boolean,
    currentPositionMs: Long,
    durationMs: Long,
    bufferedPositionMs: Long,
    audioTracks: List<AudioTrackInfo>,
    selectedAudioIndex: Int,
    currentPlaybackSpeed: Float,
    aspectModeLabel: String,
    isCasting: Boolean,
    showCastButton: Boolean,
    showPipButton: Boolean,
    seekPreviewFrame: SeekPreviewFrame? = null,
    onScrubPreviewPosition: ((Long?) -> Unit)? = null,
    onTogglePlayPause: () -> Unit,
    onSeekTo: (Long) -> Unit,
    onRewind10: () -> Unit,
    onForward10: () -> Unit,
    onCycleAspectRatio: () -> Unit,
    onSelectAspectRatio: (String) -> Unit,
    onSelectEpisode: (Episode) -> Unit,
    onSelectSource: (StreamSource) -> Unit,
    onSelectAudioTrack: (AudioTrackInfo) -> Unit,
    onSelectSubtitleTrack: (Subtitle?) -> Unit,
    onSelectPlaybackSpeed: (Float) -> Unit,
    onSkipIntro: () -> Unit,
    onSkipOutro: () -> Unit,
    onPlayNextEpisode: () -> Unit,
    canPlayNextEpisode: Boolean = false,
    onDismissNextEpisode: () -> Unit = {},
    onEnterPip: () -> Unit,
    onOpenCastChooser: () -> Unit,
    onRetryPlayback: () -> Unit,
    onReloadStreams: () -> Unit,
    onOpenAddons: (() -> Unit)? = null,
    onUpdateAutoplay: (Boolean) -> Unit,
    onUpdateAutoSkipIntro: (Boolean) -> Unit,
    onUpdateAutoSkipOutro: (Boolean) -> Unit,
    onUpdateAudioDelay: (Long) -> Unit,
    onUpdateVolumeNormalization: (Boolean) -> Unit,
    onVolumeBoostChange: (Int) -> Unit = {},
    onToggleLiveAudioTranslation: () -> Unit = {},
    onFindBestMatch: () -> Unit = {},
    onSyncSubtitleToAudio: () -> Unit = {},
    onActivateAiTranslation: () -> Unit = {},
    subtitleDelayMs: Long = 0L,
    onUpdateSubtitleDelay: (Long) -> Unit,
    onUpdateSubtitleSize: (Int) -> Unit,
    onUpdateSubtitleColor: (String) -> Unit,
    onUpdateSubtitlePosition: (String) -> Unit,
    subtitleSizePct: Int = 100,
    subtitleVerticalPct: Int = 2,
    hasActiveSubtitleCues: Boolean = false,
    onUpdateSubtitleVerticalPosition: (Int) -> Unit = {},
    onUpdateSubtitlePreload: (Boolean) -> Unit = {},
    onUpdateFilterSubtitlesByLanguage: (Boolean) -> Unit = {},
    onUpdateSubtitleRemoveHearingImpaired: (Boolean) -> Unit = {},
    onUpdateSubtitleStyle: (String) -> Unit = {},
    onUpdateSubtitleFont: (String) -> Unit = {},
    onUpdateSubtitleStylized: (Boolean) -> Unit = {},
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val latestRewind10 by rememberUpdatedState(onRewind10)
    val latestForward10 by rememberUpdatedState(onForward10)
    val latestTogglePlayPause by rememberUpdatedState(onTogglePlayPause)
    val latestCycleAspectRatio by rememberUpdatedState(onCycleAspectRatio)
    val activity = remember(context) { context.findActivity() }
    val audioManager = remember(context) { context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager }

    // Reset window brightness override when leaving player
    DisposableEffect(activity) {
        onDispose {
            activity?.let { act ->
                val lp = act.window?.attributes ?: return@let
                lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE
                act.window?.attributes = lp
            }
        }
    }

    // Overlay Visibility & Auto-Hide State
    var showControls by remember { mutableStateOf(true) }
    var isLocked by remember { mutableStateOf(false) }
    var showLockAffordance by remember { mutableStateOf(false) }
    var lockAffordanceTrigger by remember { mutableIntStateOf(0) }

    // Scrubbing State
    var isScrubbing by remember { mutableStateOf(false) }
    var scrubPreviewMs by remember { mutableLongStateOf(0L) }

    // Panels State
    var showEpisodesDrawer by remember { mutableStateOf(false) }
    var showSourcesDrawer by remember { mutableStateOf(false) }
    var showAudioSheet by remember { mutableStateOf(false) }
    var audioSheetInitialTab by remember { mutableStateOf("tracks") }
    var showSubtitlesSheet by remember { mutableStateOf(false) }
    var subtitleSheetInitialView by remember { mutableStateOf("root") }
    var showSpeedSheet by remember { mutableStateOf(false) }
    // Hold-for-3x: holding the screen plays at 3x while the finger stays down; sliding up during
    // the hold locks 3x, sliding down (or tapping the pill) unlocks.
    var speedHoldActive by remember { mutableStateOf(false) }
    var speedHoldLocked by remember { mutableStateOf(false) }
    var speedBeforeHold by remember { mutableStateOf(1f) }
    val latestSpeed by rememberUpdatedState(currentPlaybackSpeed)
    val latestOnSelectSpeed by rememberUpdatedState(onSelectPlaybackSpeed)
    val startSpeedHold: () -> Unit = {
        if (!speedHoldActive && !speedHoldLocked) {
            speedBeforeHold = latestSpeed
            speedHoldActive = true
            showControls = false
            latestOnSelectSpeed(HOLD_SPEED)
        }
    }
    val lockSpeedHold: () -> Unit = {
        if (speedHoldActive) speedHoldLocked = true
    }
    val endSpeedHold: () -> Unit = {
        if (speedHoldActive && !speedHoldLocked) {
            speedHoldActive = false
            latestOnSelectSpeed(speedBeforeHold)
        }
    }
    val unlockSpeedHold: () -> Unit = {
        speedHoldActive = false
        speedHoldLocked = false
        latestOnSelectSpeed(speedBeforeHold)
    }
    // Horizontal swipe on the video seeks (preview while dragging, seek on release).
    var swipeSeekActive by remember { mutableStateOf(false) }
    var swipeSeekStartMs by remember { mutableLongStateOf(0L) }
    var swipeSeekTargetMs by remember { mutableLongStateOf(0L) }
    val latestDurationMs by rememberUpdatedState(durationMs)
    val latestPositionMs by rememberUpdatedState(currentPositionMs)
    val latestOnSeekTo by rememberUpdatedState(onSeekTo)
    val latestScrubPreview by rememberUpdatedState(onScrubPreviewPosition)
    val swipeSeekStart: () -> Unit = {
        if (latestDurationMs > 0L) {
            swipeSeekActive = true
            swipeSeekStartMs = latestPositionMs
            swipeSeekTargetMs = latestPositionMs
        }
    }
    val swipeSeekEnd: (Boolean) -> Unit = { cancelled ->
        if (swipeSeekActive) {
            swipeSeekActive = false
            latestScrubPreview?.invoke(null)
            if (!cancelled && swipeSeekTargetMs != swipeSeekStartMs) latestOnSeekTo(swipeSeekTargetMs)
        }
    }
    val swipeSeekMove: (Float, Float) -> Unit = { dxPx, playerWidthPx ->
        if (swipeSeekActive) {
            val dur = latestDurationMs
            val delta = swipeSeekDeltaMs(dxPx, playerWidthPx, dur)
            swipeSeekTargetMs = (swipeSeekStartMs + delta).coerceIn(0L, dur)
            latestScrubPreview?.invoke(swipeSeekTargetMs)
        }
    }
    var showMoreSettingsSheet by remember { mutableStateOf(false) }
    var isRepositioningSubtitles by remember { mutableStateOf(false) }

    val anyPanelOpen = showEpisodesDrawer || showSourcesDrawer || showAudioSheet ||
        showSubtitlesSheet || showSpeedSheet || showMoreSettingsSheet

    fun closeAllPanels() {
        showEpisodesDrawer = false
        showSourcesDrawer = false
        showAudioSheet = false
        showSubtitlesSheet = false
        showSpeedSheet = false
        showMoreSettingsSheet = false
        isRepositioningSubtitles = false
    }

    BackHandler(enabled = anyPanelOpen) {
        closeAllPanels()
    }

    BackHandler(enabled = isLocked) {
        showLockAffordance = true
        lockAffordanceTrigger++
    }

    // Android system bars follow the player controls visibility state:
    // Controls visible -> Show status bar and navigation bar
    // Controls hidden (or locked) -> Hide status bar and navigation bar
    val areControlsVisible = (showControls || anyPanelOpen) && !isLocked && uiState.error == null && !isRepositioningSubtitles
    PlayerSystemBarsEffect(
        activity = activity,
        showBars = areControlsVisible
    )

    val isHeartbeatLoading = uiState.isLoading || uiState.selectedStreamUrl == null || !hasPlaybackStarted

    // Auto-hide controls after 3.4 seconds of inactivity when playing and no panel is open
    LaunchedEffect(showControls, isPlaying, isScrubbing, anyPanelOpen, isLocked) {
        if (showControls && isPlaying && !isScrubbing && !anyPanelOpen && !isLocked) {
            delay(3400)
            showControls = false
        }
    }

    // Lock affordance auto-hide (2.2s)
    LaunchedEffect(showLockAffordance, lockAffordanceTrigger) {
        if (showLockAffordance) {
            delay(2200)
            showLockAffordance = false
        }
    }

    // Brightness Gesture State
    var showBrightnessIndicator by remember { mutableStateOf(false) }
    var brightnessLevel by remember { mutableFloatStateOf(0.7f) }
    LaunchedEffect(activity) {
        brightnessLevel = getInitialBrightness(activity)
    }

    // Volume Gesture State
    var showVolumeIndicator by remember { mutableStateOf(false) }
    var volumeLevel by remember { mutableFloatStateOf(0.7f) }
    val maxVolume = remember(audioManager) { audioManager?.getStreamMaxVolume(AudioManager.STREAM_MUSIC) ?: 15 }
    var lastKnownStreamVolume by remember(audioManager) {
        mutableIntStateOf(audioManager?.getStreamVolume(AudioManager.STREAM_MUSIC) ?: -1)
    }

    LaunchedEffect(audioManager, uiState.volumeBoostDb) {
        val current = audioManager?.getStreamVolume(AudioManager.STREAM_MUSIC) ?: 10
        lastKnownStreamVolume = current
        val hardwarePct = (current.toFloat() / maxVolume.toFloat()).coerceIn(0f, 1f)
        volumeLevel = if (uiState.volumeBoostDb > 0 && current >= maxVolume) {
            1.0f + (uiState.volumeBoostDb.toFloat() / 15f).coerceIn(0f, 1f)
        } else {
            hardwarePct
        }
    }

    // Trigger counters ensuring HUD indicators always restart the auto-hide timer
    var brightnessIndicatorTrigger by remember { mutableIntStateOf(0) }
    var volumeIndicatorTrigger by remember { mutableIntStateOf(0) }

    val currentVolumeBoostDb by rememberUpdatedState(uiState.volumeBoostDb)
    val currentOnVolumeBoostChange by rememberUpdatedState(onVolumeBoostChange)

    // Hardware Physical Volume Buttons Observer (only triggers on actual STREAM_MUSIC volume delta)
    DisposableEffect(context, audioManager, maxVolume) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(c: Context?, intent: Intent?) {
                if (intent?.action == "android.media.VOLUME_CHANGED_ACTION") {
                    val streamType = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1)
                    if (streamType == AudioManager.STREAM_MUSIC || streamType == -1) {
                        val current = audioManager?.getStreamVolume(AudioManager.STREAM_MUSIC) ?: return
                        if (lastKnownStreamVolume != -1 && current != lastKnownStreamVolume) {
                            lastKnownStreamVolume = current
                            val boost = currentVolumeBoostDb
                            if (current < maxVolume && boost > 0) {
                                currentOnVolumeBoostChange(0)
                            }
                            val hardwarePct = (current.toFloat() / maxVolume.toFloat()).coerceIn(0f, 1f)
                            volumeLevel = if (boost > 0 && current >= maxVolume) {
                                1.0f + (boost.toFloat() / 15f).coerceIn(0f, 1f)
                            } else {
                                hardwarePct
                            }
                            volumeIndicatorTrigger++
                        }
                    }
                }
            }
        }

        val observer = object : ContentObserver(Handler(Looper.getMainLooper())) {
            override fun onChange(selfChange: Boolean) {
                super.onChange(selfChange)
                val current = audioManager?.getStreamVolume(AudioManager.STREAM_MUSIC) ?: return
                if (lastKnownStreamVolume != -1 && current != lastKnownStreamVolume) {
                    lastKnownStreamVolume = current
                    val boost = currentVolumeBoostDb
                    if (current < maxVolume && boost > 0) {
                        currentOnVolumeBoostChange(0)
                    }
                    val hardwarePct = (current.toFloat() / maxVolume.toFloat()).coerceIn(0f, 1f)
                    volumeLevel = if (boost > 0 && current >= maxVolume) {
                        1.0f + (boost.toFloat() / 15f).coerceIn(0f, 1f)
                    } else {
                        hardwarePct
                    }
                    volumeIndicatorTrigger++
                }
            }
        }

        val filter = IntentFilter("android.media.VOLUME_CHANGED_ACTION")
        try {
            ContextCompat.registerReceiver(
                context,
                receiver,
                filter,
                ContextCompat.RECEIVER_EXPORTED
            )
        } catch (_: Exception) {
            try {
                @Suppress("DEPRECATION")
                context.registerReceiver(receiver, filter)
            } catch (_: Exception) {}
        }

        try {
            val musicVolumeUri = Settings.System.getUriFor("volume_music_speaker")
                ?: Settings.System.getUriFor("volume_music")
            if (musicVolumeUri != null) {
                context.contentResolver.registerContentObserver(
                    musicVolumeUri,
                    false,
                    observer
                )
            }
        } catch (_: Exception) {}

        onDispose {
            runCatching { context.unregisterReceiver(receiver) }
            runCatching { context.contentResolver.unregisterContentObserver(observer) }
        }
    }

    // Aspect Ratio Flash Indicator
    var showAspectFlash by remember { mutableStateOf(false) }
    var aspectFlashText by remember { mutableStateOf(aspectModeLabel) }

    // Double Tap Seek Ripple State
    var showDoubleTapSeekLeft by remember { mutableStateOf(false) }
    var showDoubleTapSeekRight by remember { mutableStateOf(false) }
    var doubleTapSeekLeftTrigger by remember { mutableIntStateOf(0) }
    var doubleTapSeekRightTrigger by remember { mutableIntStateOf(0) }

    LaunchedEffect(doubleTapSeekLeftTrigger) {
        if (doubleTapSeekLeftTrigger > 0) {
            showDoubleTapSeekLeft = true
            delay(650)
            showDoubleTapSeekLeft = false
        }
    }

    LaunchedEffect(doubleTapSeekRightTrigger) {
        if (doubleTapSeekRightTrigger > 0) {
            showDoubleTapSeekRight = true
            delay(650)
            showDoubleTapSeekRight = false
        }
    }

    // Contextual Prompt Phase State (Intro / Outro / Up Next)
    var dismissedIntro by remember(uiState.selectedStreamUrl) { mutableStateOf(false) }
    var dismissedOutro by remember(uiState.selectedStreamUrl) { mutableStateOf(false) }
    var dismissedUpNext by remember(uiState.selectedStreamUrl) { mutableStateOf(false) }

    val activeSkip = uiState.activeSkipInterval
    val isIntroInterval = activeSkip != null && !uiState.skipIntervalDismissed && activeSkip.type.lowercase() in listOf("intro", "op", "mixed-op", "recap")
    val isOutroInterval = activeSkip != null && !uiState.skipIntervalDismissed && activeSkip.type.lowercase() in listOf("outro", "ed", "mixed-ed")

    val isTv = uiState.mediaType == MediaType.TV || uiState.seasonNumber != null || !uiState.episodeTitle.isNullOrBlank()
    val nextEpisode = uiState.seasonEpisodes.takeIf { canPlayNextEpisode }?.firstOrNull { it.episodeNumber == (uiState.episodeNumber ?: 0) + 1 }

    // Reset dismiss flags when seeking backward before intervals
    LaunchedEffect(currentPositionMs, activeSkip) {
        if (activeSkip != null && currentPositionMs < activeSkip.startMs) {
            dismissedIntro = false
            dismissedOutro = false
            dismissedUpNext = false
        }
    }

    // Is Up Next currently active on the timeline?
    val isUpNextActive = if (isOutroInterval && activeSkip != null && isTv && nextEpisode != null && !dismissedUpNext) {
        val upNextEndMs = (activeSkip.startMs + 10000L).coerceAtMost(activeSkip.endMs)
        currentPositionMs in activeSkip.startMs until upNextEndMs
    } else if (!isOutroInterval && isTv && nextEpisode != null && !dismissedUpNext && durationMs > 20000L) {
        val fallbackStartMs = durationMs - 15000L
        val fallbackEndMs = durationMs - 5000L
        currentPositionMs in fallbackStartMs until fallbackEndMs
    } else {
        false
    }

    // When Up Next triggers for the first time on the outro timeline, ensure controls are shown
    LaunchedEffect(isUpNextActive) {
        if (isUpNextActive) {
            showControls = true
        }
    }

    val skipRecapLabel = stringResource(R.string.player_skip_recap)
    val skipIntroLabel = stringResource(R.string.player_skip_intro)
    val skipEndingLabel = stringResource(R.string.player_skip_ending)

    // Automatic progression belongs to the shared STATE_ENDED prompt, not timeline position.
    val promptState = remember(
        isIntroInterval,
        isOutroInterval,
        activeSkip,
        currentPositionMs,
        durationMs,
        dismissedIntro,
        dismissedOutro,
        dismissedUpNext,
        nextEpisode,
        isTv,
        uiState.autoPlayNext
    ) {
        // 1. Skip Intro / Recap
        if (isIntroInterval && !dismissedIntro && activeSkip != null && currentPositionMs in activeSkip.startMs until activeSkip.endMs) {
            val label = when (activeSkip.type.lowercase()) {
                "recap" -> skipRecapLabel
                else -> skipIntroLabel
            }
            val totalMs = (activeSkip.endMs - activeSkip.startMs).coerceAtLeast(1000L)
            val progress = ((activeSkip.endMs - currentPositionMs).toFloat() / totalMs.toFloat()).coerceIn(0f, 1f)
            return@remember MobileContextPromptState.SkipAction(
                label = label,
                isOutro = false,
                progress = progress
            )
        }

        // 2. Up Next Card (Shows during first 10s of outro, or fallback near end)
        if (isOutroInterval && activeSkip != null && isTv && nextEpisode != null && !dismissedUpNext) {
            val upNextEndMs = (activeSkip.startMs + 10000L).coerceAtMost(activeSkip.endMs)
            if (currentPositionMs in activeSkip.startMs until upNextEndMs) {
                val remainingMs = (upNextEndMs - currentPositionMs).coerceAtLeast(0L)
                val countdownSeconds = ((remainingMs + 999L) / 1000L).toInt().coerceIn(0, 10)
                val progress = (remainingMs.toFloat() / 10000f).coerceIn(0f, 1f)
                return@remember MobileContextPromptState.UpNext(
                    nextEpisodeTitle = "S${nextEpisode.seasonNumber} · E${nextEpisode.episodeNumber} ${nextEpisode.name}",
                    nextEpisodeNumber = nextEpisode.episodeNumber,
                    thumbnail = nextEpisode.stillPath,
                    countdownSeconds = countdownSeconds,
                    progress = progress,
                    isAutoplay = false
                )
            }
        } else if (!isOutroInterval && isTv && nextEpisode != null && !dismissedUpNext && durationMs > 20000L) {
            val fallbackStartMs = durationMs - 15000L
            val fallbackEndMs = durationMs - 5000L
            if (currentPositionMs in fallbackStartMs until fallbackEndMs) {
                val remainingMs = (fallbackEndMs - currentPositionMs).coerceAtLeast(0L)
                val countdownSeconds = ((remainingMs + 999L) / 1000L).toInt().coerceIn(0, 10)
                val progress = (remainingMs.toFloat() / 10000f).coerceIn(0f, 1f)
                return@remember MobileContextPromptState.UpNext(
                    nextEpisodeTitle = "S${nextEpisode.seasonNumber} · E${nextEpisode.episodeNumber} ${nextEpisode.name}",
                    nextEpisodeNumber = nextEpisode.episodeNumber,
                    thumbnail = nextEpisode.stillPath,
                    countdownSeconds = countdownSeconds,
                    progress = progress,
                    isAutoplay = false
                )
            }
        }

        // 3. Skip Outro / Ending (Appears when in outro after Up Next is cancelled or finished)
        if (isOutroInterval && activeSkip != null && !dismissedOutro) {
            val upNextEndMs = if (isTv && nextEpisode != null && !dismissedUpNext) {
                (activeSkip.startMs + 10000L).coerceAtMost(activeSkip.endMs)
            } else {
                activeSkip.startMs
            }
            if (currentPositionMs in upNextEndMs until activeSkip.endMs) {
                val label = when (activeSkip.type.lowercase()) {
                    else -> skipEndingLabel
                }
                val totalMs = (activeSkip.endMs - activeSkip.startMs).coerceAtLeast(1000L)
                val progress = ((activeSkip.endMs - currentPositionMs).toFloat() / totalMs.toFloat()).coerceIn(0f, 1f)
                return@remember MobileContextPromptState.SkipAction(
                    label = label,
                    isOutro = true,
                    progress = progress
                )
            }
        }

        MobileContextPromptState.Hidden
    }

    val isPromptShowing = promptState !is MobileContextPromptState.Hidden

    // Title Block Text
    val eyebrowText = if (uiState.episodeTitle.isNullOrBlank()) "" else uiState.title
    val mainTitleText = if (!uiState.episodeTitle.isNullOrBlank()) {
        "S${uiState.seasonNumber ?: 1} · E${uiState.episodeNumber ?: 1} — ${uiState.episodeTitle}"
    } else {
        uiState.title.ifBlank { "ARVIO Player" }
    }

    val currentAudioTrackName = audioTracks.getOrNull(selectedAudioIndex)?.let {
        it.label?.takeIf { l -> l.isNotBlank() }
            ?: it.language?.takeIf { l -> l.isNotBlank() }
            ?: stringResource(R.string.mplayer_audio_track_number, it.index + 1)
    } ?: stringResource(R.string.audio)

    // Blank means "off"; the controls show their own translated label for it.
    val currentSubtitleTrackName = uiState.selectedSubtitle?.lang?.takeIf { it.isNotBlank() }.orEmpty()

    var initialAspectSeen by remember { mutableStateOf(false) }
    // Automatically trigger HUD flash when aspect ratio changes (suppressed on initial load)
    LaunchedEffect(aspectModeLabel) {
        if (!initialAspectSeen) {
            initialAspectSeen = true
            return@LaunchedEffect
        }
        aspectFlashText = aspectModeLabel
        showAspectFlash = true
        delay(900)
        showAspectFlash = false
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            // Multi-Tap Gesture Detection (Single Tap = Controls, Double Tap = Seek -10s/+10s, Triple Tap = Aspect Ratio)
            .pointerInput(isLocked, uiState.error, anyPanelOpen, isHeartbeatLoading) {
                if (uiState.error != null || isHeartbeatLoading) return@pointerInput
                coroutineScope {
                    var tapCount = 0
                    var lastTapTime = 0L
                    var lastOffset = Offset.Zero
                    var tapTimerJob: Job? = null

                    awaitEachGesture {
                        val down = awaitFirstDown(requireUnconsumed = false)
                        val downTime = SystemClock.uptimeMillis()
                        val up = waitForUpOrCancellation()
                        if (up != null && !up.isConsumed) {
                            val upTime = SystemClock.uptimeMillis()
                            val isWithinTapSlop = (up.position - down.position).getDistance() < viewConfiguration.touchSlop
                            if (isWithinTapSlop && (upTime - downTime) < viewConfiguration.longPressTimeoutMillis) {
                                val now = SystemClock.uptimeMillis()
                                if (now - lastTapTime > 320L) {
                                    tapCount = 1
                                } else {
                                    tapCount++
                                }
                                lastTapTime = now
                                lastOffset = up.position

                                tapTimerJob?.cancel()

                                if (tapCount >= 3) {
                                    tapCount = 0
                                    if (!isLocked && uiState.error == null && !anyPanelOpen) {
                                        latestCycleAspectRatio()
                                    }
                                } else {
                                    tapTimerJob = launch {
                                        delay(300L)
                                        val count = tapCount
                                        val offset = lastOffset
                                        tapCount = 0
                                        if (count == 1) {
                                            if (isLocked) {
                                                showLockAffordance = true
                                                lockAffordanceTrigger++
                                            } else if (anyPanelOpen) {
                                                closeAllPanels()
                                            } else if (uiState.error == null) {
                                                showControls = !showControls
                                            }
                                        } else if (count == 2) {
                                            if (!isLocked && uiState.error == null && !anyPanelOpen) {
                                                val screenWidth = size.width
                                                if (offset.x < screenWidth * 0.42f) {
                                                    latestRewind10()
                                                    doubleTapSeekLeftTrigger++
                                                } else if (offset.x > screenWidth * 0.58f) {
                                                    latestForward10()
                                                    doubleTapSeekRightTrigger++
                                                } else {
                                                    latestTogglePlayPause()
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
    ) {
        // Auto-hide indicators after release (restarts on every increment of trigger)
        LaunchedEffect(brightnessIndicatorTrigger) {
            if (brightnessIndicatorTrigger > 0) {
                showBrightnessIndicator = true
                delay(1200)
                showBrightnessIndicator = false
            }
        }
        LaunchedEffect(volumeIndicatorTrigger) {
            if (volumeIndicatorTrigger > 0) {
                showVolumeIndicator = true
                delay(1200)
                showVolumeIndicator = false
            }
        }

        // ── Left Half: Brightness Swipe (displays Brightness indicator on the Right) ──
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .align(Alignment.CenterStart)
                .pointerInput(isLocked, anyPanelOpen, uiState.error, isHeartbeatLoading) {
                    if (isLocked || anyPanelOpen || uiState.error != null || isHeartbeatLoading) return@pointerInput
                    detectPlayerVerticalAdjustmentGesture(
                        thresholdPx = 28.dp.toPx(),
                        sensitivity = 0.95f,
                        getBounds = { 0.0f..1.0f },
                        getInitialValue = { brightnessLevel },
                        onHoldStart = startSpeedHold,
                        onHoldLock = lockSpeedHold,
                        onHoldEnd = endSpeedHold,
                        isHoldLocked = { speedHoldLocked },
                        onHoldUnlock = unlockSpeedHold,
                        onHoldLockCancel = { speedHoldLocked = false },
                        onHorizontalStart = swipeSeekStart,
                        // Each gesture zone covers half of the player, so the full width is twice ours.
                        onHorizontalMove = { dxPx -> swipeSeekMove(dxPx, size.width * 2f) },
                        onHorizontalEnd = swipeSeekEnd,
                        onActivate = {
                            showControls = false
                            brightnessIndicatorTrigger++
                        },
                        onValueChange = { newLevel ->
                            brightnessLevel = newLevel
                            brightnessIndicatorTrigger++
                            setWindowBrightness(activity, newLevel)
                        }
                    )
                }
        )

        // ── Right Half: Volume Swipe (displays Volume indicator on the Left) ──
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .align(Alignment.CenterEnd)
                .pointerInput(isLocked, anyPanelOpen, maxVolume, uiState.error, isHeartbeatLoading, uiState.volumeBoostDb) {
                    if (isLocked || anyPanelOpen || uiState.error != null || isHeartbeatLoading) return@pointerInput
                    detectPlayerVerticalAdjustmentGesture(
                        thresholdPx = 28.dp.toPx(),
                        sensitivity = 0.95f,
                        getBounds = { initVal ->
                            val hasActiveBoost = uiState.volumeBoostDb > 0 || initVal > 1.005f
                            val strokeMin = if (hasActiveBoost) 1.0f else 0.0f
                            val strokeMax = if (initVal < 0.999f) 1.0f else 2.0f
                            strokeMin..strokeMax
                        },
                        getInitialValue = { volumeLevel },
                        onHoldStart = startSpeedHold,
                        onHoldLock = lockSpeedHold,
                        onHoldEnd = endSpeedHold,
                        isHoldLocked = { speedHoldLocked },
                        onHoldUnlock = unlockSpeedHold,
                        onHoldLockCancel = { speedHoldLocked = false },
                        onHorizontalStart = swipeSeekStart,
                        // Each gesture zone covers half of the player, so the full width is twice ours.
                        onHorizontalMove = { dxPx -> swipeSeekMove(dxPx, size.width * 2f) },
                        onHorizontalEnd = swipeSeekEnd,
                        onActivate = {
                            showControls = false
                            volumeIndicatorTrigger++
                        },
                        onValueChange = { newLevel ->
                            volumeLevel = newLevel
                            volumeIndicatorTrigger++
                            if (newLevel <= 1.0f) {
                                val targetVol = (newLevel * maxVolume).roundToInt().coerceIn(0, maxVolume)
                                lastKnownStreamVolume = targetVol
                                audioManager?.setStreamVolume(AudioManager.STREAM_MUSIC, targetVol, 0)
                                if (uiState.volumeBoostDb > 0) {
                                    onVolumeBoostChange(0)
                                }
                            } else {
                                lastKnownStreamVolume = maxVolume
                                audioManager?.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0)
                                val targetBoostDb = ((newLevel - 1.0f) * 15f).roundToInt().coerceIn(0, 15)
                                if (targetBoostDb != uiState.volumeBoostDb) {
                                    onVolumeBoostChange(targetBoostDb)
                                }
                            }
                        }
                    )
                }
        )

        // ── Hold-for-3x pill ──
        if (speedHoldActive) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 28.dp)
                    .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(50))
                    .then(if (speedHoldLocked) Modifier.clickable { unlockSpeedHold() } else Modifier)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (speedHoldLocked) {
                        stringResource(R.string.player_hold_speed_locked)
                    } else {
                        stringResource(R.string.player_hold_speed)
                    },
                    style = ArflixTypography.caption.copy(fontWeight = FontWeight.Bold, fontSize = 13.sp),
                    color = Color.White
                )
            }
        }

        // ── Swipe-to-seek indicator ──
        if (swipeSeekActive) {
            val deltaMs = swipeSeekTargetMs - swipeSeekStartMs
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(16.dp))
                    .padding(horizontal = 22.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                seekPreviewFrame?.let { frame ->
                    androidx.compose.foundation.Image(
                        bitmap = frame.bitmap.asImageBitmap(),
                        contentDescription = null,
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        modifier = Modifier
                            .width(176.dp)
                            .height(99.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Spacer(Modifier.height(8.dp))
                }
                Text(
                    text = (if (deltaMs >= 0) "+" else "-") + formatSwipeSeekTime(abs(deltaMs)),
                    style = ArflixTypography.caption.copy(fontWeight = FontWeight.Bold, fontSize = 22.sp),
                    color = Color.White
                )
                Text(
                    text = formatSwipeSeekTime(swipeSeekTargetMs) + " / " + formatSwipeSeekTime(durationMs),
                    style = ArflixTypography.caption.copy(fontSize = 13.sp),
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        // ── Double Tap Seek Left Feedback ──
        AnimatedVisibility(
            visible = showDoubleTapSeekLeft,
            enter = fadeIn(tween(100)) + scaleIn(tween(150), initialScale = 0.8f),
            exit = fadeOut(tween(250)) + scaleOut(tween(200), targetScale = 0.9f),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 72.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.65f), CircleShape)
                    .padding(20.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_huge_replay_10),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "-10s",
                    style = ArflixTypography.caption.copy(fontWeight = FontWeight.Bold, fontSize = 13.sp),
                    color = Color.White
                )
            }
        }

        // ── Double Tap Seek Right Feedback ──
        AnimatedVisibility(
            visible = showDoubleTapSeekRight,
            enter = fadeIn(tween(100)) + scaleIn(tween(150), initialScale = 0.8f),
            exit = fadeOut(tween(250)) + scaleOut(tween(200), targetScale = 0.9f),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 72.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.65f), CircleShape)
                    .padding(20.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_huge_forward_10),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "+10s",
                    style = ArflixTypography.caption.copy(fontWeight = FontWeight.Bold, fontSize = 13.sp),
                    color = Color.White
                )
            }
        }

        // ── Main Controls Overlay (Top Bar, Center, Bottom) ──
        val layoutDirection = LocalLayoutDirection.current
        val systemBarsInsets = WindowInsets.systemBars.asPaddingValues()
        val cutoutInsets = WindowInsets.displayCutout.asPaddingValues()

        // Combine live system bars and display cutout so controls avoid camera notches,
        // status bar icons, and navigation bar buttons/pills on any edge
        val startInset = maxOf(
            systemBarsInsets.calculateStartPadding(layoutDirection),
            cutoutInsets.calculateStartPadding(layoutDirection)
        )
        val endInset = maxOf(
            systemBarsInsets.calculateEndPadding(layoutDirection),
            cutoutInsets.calculateEndPadding(layoutDirection)
        )
        val maxHorizontalPadding = maxOf(startInset, endInset, 24.dp)

        val topSafePadding = maxOf(
            systemBarsInsets.calculateTopPadding() + 8.dp,
            cutoutInsets.calculateTopPadding() + 12.dp,
            16.dp
        )
        val bottomSafePadding = maxOf(
            systemBarsInsets.calculateBottomPadding() + 8.dp,
            cutoutInsets.calculateBottomPadding() + 12.dp,
            16.dp
        )

        AnimatedVisibility(
            visible = hasPlaybackStarted && showControls && !isLocked && uiState.error == null && !isRepositioningSubtitles,
            enter = fadeIn(tween(200)),
            exit = fadeOut(tween(240)),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0f to Color.Black.copy(alpha = 0.60f),
                                0.24f to Color.Black.copy(alpha = 0.24f),
                                0.44f to Color.Transparent,
                                0.56f to Color.Transparent,
                                0.76f to Color.Black.copy(alpha = 0.38f),
                                1.0f to Color.Black.copy(alpha = 0.80f)
                            )
                        )
                    )
            ) {
                // Top Bar
                MobilePlayerTopBar(
                    onClose = onBack,
                    onLock = {
                        isLocked = true
                        showControls = false
                        closeAllPanels()
                    },
                    onPip = onEnterPip,
                    onCast = onOpenCastChooser,
                    onOpenMoreSettings = {
                        closeAllPanels()
                        showMoreSettingsSheet = true
                    },
                    isCasting = isCasting,
                    showCastButton = showCastButton,
                    showPipButton = showPipButton,
                    horizontalPadding = maxHorizontalPadding,
                    topPadding = topSafePadding,
                    modifier = Modifier.align(Alignment.TopCenter)
                )

                // Center Play/Pause & Skip Controls
                MobilePlayerCenterControls(
                    isPlaying = isPlaying,
                    isBuffering = isBuffering,
                    onTogglePlayPause = onTogglePlayPause,
                    onRewind10 = onRewind10,
                    onForward10 = onForward10,
                    modifier = Modifier.align(Alignment.Center)
                )

                // Bottom Section (Meta, Scrubber, Utility Row)
                MobilePlayerBottomSection(
                    eyebrow = eyebrowText,
                    mainTitle = mainTitleText,
                    currentPositionMs = currentPositionMs,
                    durationMs = durationMs,
                    bufferedPositionMs = bufferedPositionMs,
                    isScrubbing = isScrubbing,
                    scrubPreviewMs = scrubPreviewMs,
                    currentAudioTrack = currentAudioTrackName,
                    currentSubtitleTrack = currentSubtitleTrackName,
                    currentPlaybackSpeed = currentPlaybackSpeed,
                    isEpisodeListAvailable = uiState.seasonEpisodes.isNotEmpty(),
                    isPromptShowing = isPromptShowing,
                    seekPreviewFrame = seekPreviewFrame,
                    hasNextEpisode = isTv && nextEpisode != null,
                    onPlayNextEpisode = {
                        closeAllPanels()
                        dismissedUpNext = true
                        onPlayNextEpisode()
                    },
                    onOpenSources = {
                        closeAllPanels()
                        showSourcesDrawer = true
                    },
                    onOpenEpisodes = {
                        closeAllPanels()
                        showEpisodesDrawer = true
                    },
                    onOpenAudio = {
                        closeAllPanels()
                        audioSheetInitialTab = "tracks"
                        showAudioSheet = true
                    },
                    onOpenSubtitles = {
                        closeAllPanels()
                        subtitleSheetInitialView = "root"
                        showSubtitlesSheet = true
                    },
                    onOpenSpeed = {
                        closeAllPanels()
                        showSpeedSheet = true
                    },
                    onSeekStart = { pct ->
                        if (durationMs > 0L) {
                            val pos = (pct * durationMs).toLong()
                            scrubPreviewMs = pos
                            isScrubbing = true
                            onScrubPreviewPosition?.invoke(pos)
                        }
                    },
                    onSeekMove = { pct ->
                        if (durationMs > 0L) {
                            val pos = (pct * durationMs).toLong().coerceIn(0L, durationMs)
                            scrubPreviewMs = pos
                            isScrubbing = true
                            onScrubPreviewPosition?.invoke(pos)
                        }
                    },
                    onSeekEnd = {
                        if (isScrubbing) {
                            onSeekTo(scrubPreviewMs)
                            isScrubbing = false
                            onScrubPreviewPosition?.invoke(null)
                        }
                    },
                    onSeekCancel = {
                        isScrubbing = false
                        onScrubPreviewPosition?.invoke(null)
                    },
                    horizontalPadding = maxHorizontalPadding,
                    bottomPadding = bottomSafePadding,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }

        // ── Contextual Prompt Slot (Bottom Right: stable placement above bottom section) ──
        if (hasPlaybackStarted && !isLocked && uiState.error == null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = maxHorizontalPadding, bottom = bottomSafePadding + 76.dp)
                    .zIndex(8f)
            ) {
                MobileContextualPrompt(
                    promptState = promptState,
                    showControls = showControls,
                    onSkipIntro = {
                        dismissedIntro = true
                        onSkipIntro()
                    },
                    onSkipOutro = {
                        dismissedOutro = true
                        onSkipOutro()
                    },
                    onPlayNextEpisode = {
                        dismissedUpNext = true
                        onPlayNextEpisode()
                    },
                    onCancelPrompt = {
                        dismissedUpNext = true
                        onDismissNextEpisode()
                    }
                )
            }
        }

        // ── Edge Swipe Indicators (Volume on Left, Brightness on Right) ──
        val volumeIconRes = if (volumeLevel <= 0.01f) R.drawable.ic_huge_volume_mute else R.drawable.ic_huge_speaker
        MobileEdgeIndicator(
            visible = showVolumeIndicator,
            iconRes = volumeIconRes,
            levelPct = volumeLevel,
            isLeft = true,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = maxHorizontalPadding)
        )
        val isAutoBrightness = brightnessLevel <= 0.005f
        val brightnessIconRes = if (isAutoBrightness) R.drawable.ic_huge_sun_dim else R.drawable.ic_huge_sun
        MobileEdgeIndicator(
            visible = showBrightnessIndicator,
            iconRes = brightnessIconRes,
            levelPct = brightnessLevel,
            isLeft = false,
            isAuto = isAutoBrightness,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = maxHorizontalPadding)
        )

        // ── Center Aspect Ratio HUD Flash Indicator ──
        MobileAspectIndicator(
            aspectText = aspectModeDisplayLabel(aspectFlashText),
            visible = showAspectFlash,
            modifier = Modifier.align(Alignment.Center)
        )

        // ── Unified Independent Buffering Overlay (Persistent across showControls toggles) ──
        AnimatedVisibility(
            visible = hasPlaybackStarted && isBuffering && !isLocked && uiState.error == null,
            enter = fadeIn(tween(160)),
            exit = fadeOut(tween(200)),
            modifier = Modifier
                .align(Alignment.Center)
                .zIndex(15f)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            onTogglePlayPause()
                            showControls = true
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MobilePlayerTokens.InkPrimary,
                    strokeWidth = 3.dp,
                    modifier = Modifier.size(38.dp)
                )
            }
        }

        // ── Lock Mode "Tap to Unlock" Affordance ──
        AnimatedVisibility(
            visible = isLocked && showLockAffordance,
            enter = fadeIn(tween(160)),
            exit = fadeOut(tween(200)),
            modifier = Modifier
                .align(Alignment.Center)
                .zIndex(20f)
        ) {
            Column(
                modifier = Modifier
                    .clip(MobilePlayerTokens.ShapePill)
                    .background(Color(0xB817181C))
                    .border(1.dp, MobilePlayerTokens.PanelBorder, MobilePlayerTokens.ShapePill)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            isLocked = false
                            showLockAffordance = false
                            showControls = true
                        }
                    )
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_huge_lock_open),
                    contentDescription = stringResource(R.string.mplayer_cd_unlock),
                    tint = MobilePlayerTokens.InkPrimary,
                    modifier = Modifier.size(26.dp)
                )
                Text(
                    text = stringResource(R.string.mplayer_tap_to_unlock),
                    color = MobilePlayerTokens.InkSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // ── Error Overlay ──
        if (uiState.error != null) {
            val hasStreams = uiState.streams.isNotEmpty()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundDark)
                    .zIndex(30f)
            ) {
                // Top-left Close ('X') button so users can exit the player
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(
                            top = topSafePadding + 14.dp,
                            start = maxHorizontalPadding + 16.dp
                        )
                ) {
                    MobileIconButton(
                        icon = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close),
                        onClick = onBack,
                        size = 20.dp
                    )
                }

                // Centered Error Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ErrorOutline,
                        contentDescription = null,
                        tint = ErrorRed,
                        modifier = Modifier.size(42.dp)
                    )
                    Text(
                        text = stringResource(
                            if (!hasStreams) R.string.mplayer_error_no_streams_title else R.string.mplayer_error_playback_failed_title
                        ),
                        color = MobilePlayerTokens.InkPrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = uiState.error?.localizedText().orEmpty().ifBlank {
                            if (!hasStreams) {
                                stringResource(R.string.mplayer_error_no_streams_message)
                            } else {
                                stringResource(R.string.mplayer_error_playback_failed_message)
                            }
                        },
                        color = MobilePlayerTokens.InkTertiary,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp,
                        modifier = Modifier.fillMaxWidth(0.85f)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (hasStreams) {
                            // Retry button
                            Box(
                                modifier = Modifier
                                    .clip(MobilePlayerTokens.ShapeBtn)
                                    .background(Color.Transparent)
                                    .border(1.dp, Color(0x47FFFFFF), MobilePlayerTokens.ShapeBtn)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = onRetryPlayback
                                    )
                                    .padding(horizontal = 22.dp, vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(R.string.retry),
                                    color = MobilePlayerTokens.InkPrimary,
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        // Reload Streams button
                        Box(
                            modifier = Modifier
                                .clip(MobilePlayerTokens.ShapeBtn)
                                .background(Color.White)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = onReloadStreams
                                )
                                .padding(horizontal = 22.dp, vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.mplayer_reload_streams),
                                color = Color.Black,
                                fontSize = 13.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // No streams usually means no addon is installed: offer the way there.
                        if (!hasStreams && onOpenAddons != null) {
                            Box(
                                modifier = Modifier
                                    .clip(MobilePlayerTokens.ShapeBtn)
                                    .border(1.dp, Color(0x47FFFFFF), MobilePlayerTokens.ShapeBtn)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = onOpenAddons
                                    )
                                    .padding(horizontal = 22.dp, vertical = 10.dp)
                                    .testTag("mobile_player_open_addons"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(R.string.mplayer_open_addons),
                                    color = MobilePlayerTokens.InkPrimary,
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }

        // ── Shared Dim Scrim Backdrop ──
        SharedScrimBackdrop(
            visible = anyPanelOpen,
            onDismiss = { closeAllPanels() }
        )

        // ── Panels: Drawers & Sheets ──

        // Episodes Drawer
        MobileEpisodesDrawer(
            visible = showEpisodesDrawer,
            episodes = uiState.seasonEpisodes,
            currentEpisodeNumber = uiState.episodeNumber,
            onSelectEpisode = onSelectEpisode,
            onClose = { showEpisodesDrawer = false }
        )

        // Sources Drawer
        MobileSourcesDrawer(
            visible = showSourcesDrawer,
            streams = uiState.streams,
            selectedStream = uiState.selectedStream,
            onSelectSource = onSelectSource,
            onClose = { showSourcesDrawer = false }
        )

        // Audio Track Sheet
        MobileAudioTrackSheet(
            visible = showAudioSheet,
            audioTracks = audioTracks,
            selectedAudioIndex = selectedAudioIndex,
            volumeLevelPct = volumeLevel,
            volumeBoostDb = uiState.volumeBoostDb,
            audioDelayMs = uiState.audioDelayMs,
            volumeNormalization = uiState.audioNormalization,
            isLiveAudioTranslating = uiState.isLiveAudioTranslating,
            initialTab = audioSheetInitialTab,
            onSelectAudio = onSelectAudioTrack,
            onUpdateVolumeLevel = { newLevel: Float ->
                volumeLevel = newLevel
                volumeIndicatorTrigger++
                if (newLevel <= 1.0f) {
                    val targetVol = (newLevel * maxVolume).roundToInt().coerceIn(0, maxVolume)
                    lastKnownStreamVolume = targetVol
                    audioManager?.setStreamVolume(AudioManager.STREAM_MUSIC, targetVol, 0)
                    if (uiState.volumeBoostDb > 0) {
                        onVolumeBoostChange(0)
                    }
                } else {
                    lastKnownStreamVolume = maxVolume
                    audioManager?.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0)
                    val targetBoostDb = ((newLevel - 1.0f) * 15f).roundToInt().coerceIn(0, 15)
                    if (targetBoostDb != uiState.volumeBoostDb) {
                        onVolumeBoostChange(targetBoostDb)
                    }
                }
            },
            onVolumeBoostChange = onVolumeBoostChange,
            onUpdateAudioDelay = onUpdateAudioDelay,
            onToggleVolumeNorm = onUpdateVolumeNormalization,
            onToggleLiveAudioTranslation = onToggleLiveAudioTranslation,
            onClose = { showAudioSheet = false }
        )

        val currentSubColorHex = when (uiState.subtitleColor.lowercase()) {
            "yellow" -> "#ffe066"
            "cyan" -> "#66d9ff"
            "red" -> "#ff6666"
            else -> "#fff"
        }
        val currentSubPos = if (uiState.subtitleOffset.equals("high", ignoreCase = true) || uiState.subtitleOffset.equals("top", ignoreCase = true)) "top" else "bottom"

        // Subtitles Sheet
        MobileSubtitlesSheet(
            visible = showSubtitlesSheet,
            subtitles = uiState.subtitles,
            selectedSubtitle = uiState.selectedSubtitle,
            isFindingBestMatch = uiState.isFindingBestMatch,
            matchLanguageName = uiState.matchLanguageName,
            onFindBestMatch = onFindBestMatch,
            onSyncSubtitleToAudio = onSyncSubtitleToAudio,
            isSubtitleAudioSyncRequested = uiState.isSubtitleAudioSyncRequested,
            isAiTranslating = uiState.isAiTranslating,
            isAiAvailable = uiState.isAiAvailable,
            aiTargetLanguageName = uiState.aiTargetLanguageName,
            onActivateAiTranslation = onActivateAiTranslation,
            subtitleDelayMs = subtitleDelayMs,
            onUpdateSubtitleDelay = onUpdateSubtitleDelay,
            subtitleSizePct = subtitleSizePct,
            onUpdateSubtitleSize = onUpdateSubtitleSize,
            subtitleColorHex = currentSubColorHex,
            onUpdateSubtitleColor = onUpdateSubtitleColor,
            subtitlePosition = currentSubPos,
            onUpdateSubtitlePosition = onUpdateSubtitlePosition,
            subtitleVerticalPct = subtitleVerticalPct,
            onUpdateSubtitleVerticalPosition = onUpdateSubtitleVerticalPosition,
            onStartInteractiveRepositioning = {
                showControls = false
                showSubtitlesSheet = false
                isRepositioningSubtitles = true
            },
            subtitleStyle = uiState.subtitleStyle,
            onUpdateSubtitleStyle = onUpdateSubtitleStyle,
            subtitleFont = uiState.subtitleFont,
            onUpdateSubtitleFont = onUpdateSubtitleFont,
            subtitleStylized = uiState.subtitleStylized,
            onUpdateSubtitleStylized = onUpdateSubtitleStylized,
            subtitlePreloadEnabled = uiState.subtitlePreloadEnabled,
            onToggleSubtitlePreload = onUpdateSubtitlePreload,
            filterSubtitlesByLanguage = uiState.filterSubtitlesByLanguage,
            onToggleFilterSubtitlesByLanguage = onUpdateFilterSubtitlesByLanguage,
            subtitleRemoveHearingImpaired = uiState.subtitleRemoveHearingImpaired,
            onToggleSubtitleRemoveHearingImpaired = onUpdateSubtitleRemoveHearingImpaired,
            initialView = subtitleSheetInitialView,
            onSelectSubtitle = onSelectSubtitleTrack,
            onClose = { showSubtitlesSheet = false }
        )

        // Playback Speed Sheet
        MobilePlaybackSpeedSheet(
            visible = showSpeedSheet,
            currentSpeed = currentPlaybackSpeed,
            onSelectSpeed = onSelectPlaybackSpeed,
            onClose = { showSpeedSheet = false }
        )

        // More Settings Sheet
        MobileMoreSettingsSheet(
            visible = showMoreSettingsSheet,
            autoplayNext = uiState.autoPlayNext,
            autoSkipIntro = uiState.autoSkipIntro,
            autoSkipOutro = uiState.autoSkipOutro,
            aspectRatio = aspectModeLabel,
            onToggleAutoplay = onUpdateAutoplay,
            onToggleAutoSkipIntro = onUpdateAutoSkipIntro,
            onToggleAutoSkipOutro = onUpdateAutoSkipOutro,
            onSelectAspectRatio = onSelectAspectRatio,
            onClose = { showMoreSettingsSheet = false }
        )

        // Interactive Subtitle Repositioning Overlay
        MobileSubtitleRepositionOverlay(
            visible = isRepositioningSubtitles,
            verticalPercent = subtitleVerticalPct,
            hasActiveSubtitles = hasActiveSubtitleCues,
            subtitleColorHex = currentSubColorHex,
            subtitleStyle = uiState.subtitleStyle,
            subtitleFont = uiState.subtitleFont,
            subtitleSizePct = subtitleSizePct,
            onUpdateVerticalPercent = onUpdateSubtitleVerticalPosition,
            onDismiss = { isRepositioningSubtitles = false },
            modifier = Modifier.zIndex(45f)
        )
    }
}

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

private fun getInitialBrightness(activity: Activity?): Float {
    val cur = activity?.window?.attributes?.screenBrightness ?: -1f
    return if (cur in 0.01f..1.0f) cur else {
        0f // Default to Auto Brightness (BRIGHTNESS_OVERRIDE_NONE)
    }
}

private fun setWindowBrightness(activity: Activity?, brightness: Float) {
    activity?.let { act ->
        val window = act.window ?: return@let
        val lp = window.attributes ?: return@let
        if (brightness <= 0.005f) {
            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE
        } else {
            lp.screenBrightness = brightness.coerceIn(0.01f, 1.0f)
        }
        window.attributes = lp
    }
}

/** Playback speed used while the screen is held (and while that hold is locked). */
private const val HOLD_SPEED = 3f

private enum class PlayerSwipeGestureState {
    UNCLASSIFIED,
    VERTICAL_ACTIVE,
    HORIZONTAL_ACTIVE,
    HORIZONTAL_REJECTED
}

/**
 * Shared vertical drag adjustment gesture handler for volume & brightness controls.
 *
 * Captures an activation baseline (activationDeltaY) once vertical dominance is established
 * past [thresholdPx], then computes adjustments strictly from displacement past that baseline:
 *   effectiveDeltaY = totalDeltaY - activationDeltaY
 *   normalizedDelta = -effectiveDeltaY / playerHeight
 *   newValue = initialValue + normalizedDelta * sensitivity
 *
 * This ensures:
 * - Upward movements always strictly increase values (no sign reversal / threshold subtraction bug).
 * - Downward movements always strictly decrease values.
 * - Value is perfectly continuous at activation (zero jump).
 * - Horizontal gestures and wobbles are safely filtered without breaking taps or seek gestures.
 *
 * Optional extras (all off unless their callbacks are given):
 * - Long press ([onHoldStart]/[onHoldEnd]): holding still past the long-press timeout starts a
 *   hold; sliding up during it calls [onHoldLock], sliding back down calls [onHoldLockCancel].
 *   While [isHoldLocked] is true, a downward slide calls [onHoldUnlock] and nothing else reacts.
 * - Horizontal drag ([onHorizontalStart]/[onHorizontalMove]/[onHorizontalEnd]): a horizontal
 *   gesture is reported (delta measured from activation) instead of being rejected.
 */
private suspend fun PointerInputScope.detectPlayerVerticalAdjustmentGesture(
    thresholdPx: Float,
    sensitivity: Float = 0.95f,
    getBounds: (initialValue: Float) -> ClosedFloatingPointRange<Float> = { 0f..1f },
    getInitialValue: () -> Float,
    onHoldStart: (() -> Unit)? = null,
    onHoldLock: (() -> Unit)? = null,
    onHoldEnd: (() -> Unit)? = null,
    isHoldLocked: () -> Boolean = { false },
    onHoldUnlock: (() -> Unit)? = null,
    onHoldLockCancel: (() -> Unit)? = null,
    onActivate: () -> Unit,
    onValueChange: (Float) -> Unit,
    onHorizontalStart: (() -> Unit)? = null,
    onHorizontalMove: ((deltaXPx: Float) -> Unit)? = null,
    onHorizontalEnd: ((cancelled: Boolean) -> Unit)? = null
) {
    awaitEachGesture {
        val down = awaitFirstDown(requireUnconsumed = false)
        val initialPosition = down.position
        if (onHoldUnlock != null && isHoldLocked()) {
            // 3x is locked: a downward slide unlocks it; nothing else adjusts brightness/volume.
            while (true) {
                val ev = awaitPointerEvent()
                val c = ev.changes.firstOrNull { it.id == down.id } ?: break
                if (!c.pressed) break
                val dy = c.position.y - initialPosition.y
                val dx = c.position.x - initialPosition.x
                if (dy > thresholdPx * 2f && dy > abs(dx)) {
                    c.consume()
                    onHoldUnlock()
                    while (true) {
                        val e2 = awaitPointerEvent()
                        val c2 = e2.changes.firstOrNull { it.id == down.id } ?: break
                        c2.consume()
                        if (!c2.pressed) break
                    }
                    break
                }
            }
            return@awaitEachGesture
        }
        if (onHoldStart != null) {
            // 0 = released early, 1 = moved (a normal swipe), null = held still → long press.
            val early: Int? = withTimeoutOrNull(viewConfiguration.longPressTimeoutMillis) {
                var result = 0
                while (true) {
                    val ev = awaitPointerEvent()
                    val c = ev.changes.firstOrNull { it.id == down.id }
                    if (c == null || !c.pressed) { result = 0; break }
                    if ((c.position - initialPosition).getDistance() > viewConfiguration.touchSlop) { result = 1; break }
                }
                result
            }
            if (early == 0) return@awaitEachGesture
            if (early == null) {
                onHoldStart()
                var locked = false
                var lockY = initialPosition.y
                while (true) {
                    val ev = awaitPointerEvent()
                    val c = ev.changes.firstOrNull { it.id == down.id } ?: break
                    c.consume()
                    if (!c.pressed) break
                    if (!locked && initialPosition.y - c.position.y > thresholdPx * 2f) {
                        locked = true
                        lockY = c.position.y
                        onHoldLock?.invoke()
                    } else if (locked) {
                        lockY = minOf(lockY, c.position.y)
                        if (c.position.y - lockY > thresholdPx * 2f) {
                            // Slid back down within the same press: unlock and keep holding 3x.
                            locked = false
                            onHoldLockCancel?.invoke()
                        }
                    }
                }
                if (!locked) onHoldEnd?.invoke()
                return@awaitEachGesture
            }
        }
        val initialValue = getInitialValue()
        val bounds = getBounds(initialValue)
        val playerHeight = size.height.toFloat().coerceAtLeast(1f)

        var gestureState = PlayerSwipeGestureState.UNCLASSIFIED
        var activationDeltaY = 0f

        var horizontalActivationX = 0f
        var horizontalEnded = false
        while (true) {
            val event = awaitPointerEvent()
            val change = event.changes.firstOrNull { it.id == down.id }
            if (change == null) {
                if (gestureState == PlayerSwipeGestureState.HORIZONTAL_ACTIVE) {
                    onHorizontalEnd?.invoke(true); horizontalEnded = true
                }
                break
            }
            if (!change.pressed) {
                if (gestureState == PlayerSwipeGestureState.HORIZONTAL_ACTIVE) {
                    change.consume()
                    onHorizontalEnd?.invoke(false); horizontalEnded = true
                }
                break
            }

            val currentPosition = change.position
            val totalDeltaX = currentPosition.x - initialPosition.x
            val totalDeltaY = currentPosition.y - initialPosition.y
            val totalDistance = hypot(totalDeltaX, totalDeltaY)

            if (gestureState == PlayerSwipeGestureState.UNCLASSIFIED) {
                if (totalDistance >= thresholdPx) {
                    if (abs(totalDeltaY) > abs(totalDeltaX) * 1.2f) {
                        gestureState = PlayerSwipeGestureState.VERTICAL_ACTIVE
                        activationDeltaY = totalDeltaY
                        onActivate()
                    } else if (abs(totalDeltaX) > abs(totalDeltaY) * 1.2f) {
                        if (onHorizontalMove != null) {
                            gestureState = PlayerSwipeGestureState.HORIZONTAL_ACTIVE
                            horizontalActivationX = totalDeltaX
                            onHorizontalStart?.invoke()
                        } else {
                            gestureState = PlayerSwipeGestureState.HORIZONTAL_REJECTED
                        }
                    }
                }
            }

            if (gestureState == PlayerSwipeGestureState.VERTICAL_ACTIVE) {
                change.consume()
                val effectiveDeltaY = totalDeltaY - activationDeltaY
                val normalizedDelta = -effectiveDeltaY / playerHeight
                val newValue = (initialValue + normalizedDelta * sensitivity).coerceIn(bounds.start, bounds.endInclusive)
                onValueChange(newValue)
            } else if (gestureState == PlayerSwipeGestureState.HORIZONTAL_ACTIVE) {
                change.consume()
                onHorizontalMove?.invoke(totalDeltaX - horizontalActivationX)
            }
        }
        if (gestureState == PlayerSwipeGestureState.HORIZONTAL_ACTIVE && !horizontalEnded) {
            onHorizontalEnd?.invoke(true)
        }
    }
}


/** Full screen width ≈ 2 minutes (3 minutes for long videos); never more than the video. */
internal fun swipeSeekDeltaMs(dxPx: Float, screenWidthPx: Float, durationMs: Long): Long {
    val width = screenWidthPx.coerceAtLeast(1f)
    val span = if (durationMs >= 60L * 60_000L) 180_000f else 120_000f
    return (dxPx / width * span).toLong()
}

internal fun formatSwipeSeekTime(ms: Long): String {
    val total = (ms.coerceAtLeast(0L) / 1000L)
    val h = total / 3600; val m = (total % 3600) / 60; val sec = total % 60
    return if (h > 0) "%d:%02d:%02d".format(h, m, sec) else "%d:%02d".format(m, sec)
}
