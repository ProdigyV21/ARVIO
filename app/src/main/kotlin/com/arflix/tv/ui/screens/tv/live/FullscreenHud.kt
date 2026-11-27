@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.tv.live

import android.view.KeyEvent as AndroidKeyEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.AspectRatio
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.ClosedCaption
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.util.LocalDeviceType
import kotlinx.coroutines.delay

/**
 * Fullscreen playback HUD matching the full-width reference player layout.
 * Auto-hides 5s after the last `pokeSignal` bump; `hideSignal` dismisses it straight away (Back).
 * With `showControls` off the seek bar and the button row are not composed at all: what surfaces on
 * its own after a channel zap is then pure information with nothing focusable in it. With it on the
 * controls are drawn and focused in the same breath, so anything visible is always operable.
 */
@OptIn(ExperimentalTvMaterial3Api::class, androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
fun FullscreenHud(
    channel: EnrichedChannel?,
    nowNext: IptvNowNext?,
    pokeSignal: Int,
    streamResolution: String = "",
    streamFps: String = "",
    streamVideoCodec: String = "",
    streamAudioInfo: String = "",
    streamBitrate: String = "",
    categoryName: String? = null,
    isCatchupMode: Boolean = false,
    isPlaying: Boolean = true,
    isBuffering: Boolean = false,
    playbackPositionMs: Long = 0L,
    playbackDurationMs: Long = 0L,
    onBackClick: (() -> Unit)? = null,
    onGuideClick: (() -> Unit)? = null,
    /** Picture shape (Fit / Fill / Stretch): the label shows the current one, a press moves on. */
    pictureLabel: String? = null,
    onPictureClick: (() -> Unit)? = null,
    /** Sleep timer: the label shows the current step, a press moves on (Off, 30, 60, 90, 120 min). */
    sleepLabel: String? = null,
    onSleepClick: (() -> Unit)? = null,
    onOpenVariants: (() -> Unit)? = null,
    onSubtitlesClick: (() -> Unit)? = null,
    onAudioClick: (() -> Unit)? = null,
    onPlayPauseClick: (() -> Unit)? = null,
    onRewindClick: (() -> Unit)? = null,
    onFastForwardClick: (() -> Unit)? = null,
    onPreviousCatchupClick: (() -> Unit)? = null,
    onNextCatchupClick: (() -> Unit)? = null,
    onReplayClick: (() -> Unit)? = null,
    onGoLiveClick: (() -> Unit)? = null,
    onSeekToPosition: ((Long) -> Unit)? = null,
    onOpenQuickZap: (() -> Unit)? = null,
    onVisibilityChanged: ((Boolean) -> Unit)? = null,
    hideSignal: Int = 0,
    showControls: Boolean = true,
    /** False when the stream cannot be seeked (no catch-up, no live window): rewind/forward are left out. */
    seekable: Boolean = true,
    /** Forward 10 s: only in a recording, never at the live edge. */
    forwardEnabled: Boolean = seekable,
    /** The sleep timer is running: its button carries a dot. */
    sleepActive: Boolean = false,
    /** Bumped when a list opened from the controls (subtitles, audio) closes: focus returns to its button. */
    refocusSignal: Int = 0,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(true) }
    var lastPoke by remember { mutableLongStateOf(System.currentTimeMillis()) }
    val touchControls = LocalDeviceType.current.isTouchDevice()
    val context = LocalContext.current
    var scrubbing by remember { mutableStateOf(false) }

    // Keyed on Unit, never on the callback: a caller that hands in a fresh
    // lambda per recomposition would otherwise have this effect torn down and
    // rebuilt constantly, reporting "hidden" each time. This HUD redraws every
    // second for the clock, so that would fire relentlessly — and the caller
    // uses the report to drop out of control mode.
    val latestVisibilityChanged = rememberUpdatedState(onVisibilityChanged)
    DisposableEffect(Unit) {
        onDispose {
            latestVisibilityChanged.value?.invoke(false)
        }
    }

    LaunchedEffect(pokeSignal, scrubbing, showControls) {
        visible = true
        onVisibilityChanged?.invoke(true)
        lastPoke = System.currentTimeMillis()
        if (scrubbing) return@LaunchedEffect
        // The info bar alone goes after 5 s; with the controls up the remote user gets longer.
        val hideAfter = if (showControls && !touchControls) HudControlsTimeoutMs else HudInfoTimeoutMs
        delay(hideAfter)
        if (System.currentTimeMillis() - lastPoke >= hideAfter) {
            visible = false
            onVisibilityChanged?.invoke(false)
        }
    }

    var clockMillis by remember { mutableLongStateOf(System.currentTimeMillis()) }
    val appClockFormat = rememberAppClockFormat()
    LaunchedEffect(Unit) {
        while (true) {
            clockMillis = System.currentTimeMillis()
            delay(1_000)
        }
    }

    // Instant local play/pause icon state toggle for immediate UI feedback (0ms delay)
    var localIsPlaying by remember(isPlaying) { mutableStateOf(isPlaying) }

    val playPauseFocusRequester = remember { FocusRequester() }
    val subtitlesFocusRequester = remember { FocusRequester() }
    val audioFocusRequester = remember { FocusRequester() }
    // The button whose list is open, so closing the list lands back on it.
    var focusReturnTarget by remember { mutableStateOf<FocusRequester?>(null) }
    var initialFocusApplied by remember { mutableStateOf(false) }

    // Dismiss on request (Back), without leaving fullscreen.
    LaunchedEffect(hideSignal) {
        if (hideSignal > 0) {
            visible = false
            onVisibilityChanged?.invoke(false)
        }
    }

    // Whenever the controls are on screen they are focused, so "visible" and
    // "operable" can never disagree — showing buttons that ignore the remote is
    // what the first device test rightly called broken.
    //
    // The flag records that focus actually *landed*, not that it was attempted,
    // and it clears whenever the controls leave the screen. Both matter:
    //  - Opening the controls from a bare screen flips `visible` and
    //    `showControls` in the same breath, so this effect is relaunched a
    //    second time while the first run still waits for the row to attach.
    //    Marking the attempt up front made that second run believe the work was
    //    done, while the cancelled first run never got to ask — the bar appeared
    //    and ignored the remote. Opening it while the info bar was already up
    //    changes only one key, so that path worked and hid the other one.
    //  - Closing the controls with Back and opening them again leaves `visible`
    //    untouched, so a flag that only cleared on `!visible` would have stayed
    //    set and swallowed the second open the same way.
    // Retrying costs nothing once focus has landed, and covers the row simply
    // not being attached yet on the frame the HUD fades in.
    //
    // Closing a list opened from the row (subtitles, audio) bumps `refocusSignal`: focus left
    // the row for the list, so it is placed again, on the button that opened it.
    LaunchedEffect(visible, showControls, refocusSignal) {
        if (touchControls) return@LaunchedEffect
        if (!visible || !showControls) {
            initialFocusApplied = false
        } else {
            val returnTarget = focusReturnTarget
            if (returnTarget != null) initialFocusApplied = false
            var attempts = 0
            while (!initialFocusApplied && attempts < ControlFocusAttempts) {
                attempts++
                delay(ControlFocusRetryMs)
                // The last attempt falls back to play/pause, in case the button that opened the
                // list is gone (its tracks changed while the list was open).
                val target = returnTarget?.takeIf { attempts < ControlFocusAttempts } ?: playPauseFocusRequester
                if (runCatching { target.requestFocus() }.isSuccess) {
                    initialFocusApplied = true
                    focusReturnTarget = null
                }
            }
        }
    }

    // Back to live removes the button that had focus: put focus on play/pause instead of nowhere.
    LaunchedEffect(isCatchupMode) {
        if (!isCatchupMode && visible && showControls && !touchControls) {
            repeat(ControlFocusAttempts) {
                delay(ControlFocusRetryMs)
                if (runCatching { playPauseFocusRequester.requestFocus() }.isSuccess) return@LaunchedEffect
            }
        }
    }

    // Loading ring: delayed so a quick zap never flashes it, worded once the wait gets long.
    PlayerBufferingIndicator(
        buffering = isBuffering,
        loadingLabel = stringResource(R.string.live_player_loading),
        slowLabel = stringResource(R.string.live_player_slow),
        // With the controls up the label would sit on the seek bar; the ring alone says enough.
        showSlowLabel = !(visible && showControls),
    )

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(200)),
        exit = fadeOut(tween(200)),
        modifier = modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Full screen gradient overlay (dark at top and bottom)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            0f to Color.Black.copy(alpha = 0.7f),
                            0.22f to Color.Transparent,
                            0.40f to Color.Transparent,
                            0.62f to Color.Black.copy(alpha = 0.72f),
                            1f to Color.Black.copy(alpha = 0.96f),
                        )
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        visible = false
                        onVisibilityChanged?.invoke(false)
                    },
            )

            // --- Top Header Row (Category Name & Formatted Date/Time) ---
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
                    .then(
                        if (touchControls) {
                            Modifier
                                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal))
                                .padding(horizontal = 20.dp, vertical = 12.dp)
                        } else {
                            Modifier.padding(horizontal = 48.dp, vertical = 24.dp)
                        }
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    if (onBackClick != null) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Black.copy(alpha = 0.6f))
                                .clickable { onBackClick() },
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back),
                                tint = Color.White,
                                modifier = Modifier.size(22.dp),
                            )
                        }
                    }

                    if (!categoryName.isNullOrBlank()) {
                        Text(
                            text = categoryName.uppercase(),
                            style = LiveType.SectionTag.copy(
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                            ),
                        )
                    }
                }

                Text(
                    text = formatPlayerHeaderClock(context, clockMillis, appClockFormat),
                    style = LiveType.TimeMono.copy(
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                )
            }

            // --- Bottom Sheet Layout (Full-Width) ---
            val now = nowNext?.now
            val next = nowNext?.next

            // Elapsed time passed on current show
            var frozenElapsedMs by remember(channel?.id, now?.startUtcMillis) { mutableStateOf<Long?>(null) }

            val currentElapsedShowMs = if (isCatchupMode) {
                playbackPositionMs
            } else if (now != null && now.startUtcMillis > 0L) {
                (clockMillis - now.startUtcMillis).coerceAtLeast(0L)
            } else {
                playbackPositionMs
            }

            // Freeze the timer while buffering so it doesn't continue advancing
            LaunchedEffect(isBuffering) {
                if (isBuffering) {
                    if (frozenElapsedMs == null) {
                        frozenElapsedMs = currentElapsedShowMs
                    }
                } else {
                    frozenElapsedMs = null
                }
            }

            val elapsedShowMs = if (isBuffering) {
                frozenElapsedMs ?: currentElapsedShowMs
            } else {
                currentElapsedShowMs
            }

            val totalShowMs = if (isCatchupMode && playbackDurationMs > 0L) {
                playbackDurationMs
            } else if (now != null && now.endUtcMillis > now.startUtcMillis) {
                now.endUtcMillis - now.startUtcMillis
            } else {
                playbackDurationMs
            }

            val progress = if (totalShowMs > 0L) {
                (elapsedShowMs.toFloat() / totalShowMs.toFloat()).coerceIn(0f, 1f)
            } else {
                progressOf(now) ?: 0f
            }

            val positionText = formatPlaybackDuration(elapsedShowMs)

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .then(
                        if (touchControls) {
                            Modifier
                                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal))
                                .padding(horizontal = 24.dp, vertical = 12.dp)
                        } else {
                            // Room below the button row for the caption of the focused button.
                            Modifier.padding(start = 48.dp, end = 48.dp, top = 24.dp, bottom = if (showControls) 44.dp else 28.dp)
                        }
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                // --- Info: channel, programme, time, what's next ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(if (touchControls) 14.dp else 20.dp),
                ) {
                    if (channel != null) {
                        ChannelLogo(
                            channel = channel,
                            size = if (touchControls) 56.dp else 72.dp,
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(if (touchControls) 2.dp else 4.dp),
                    ) {
                        // Channel line: LIVE marker, number and name.
                        if (channel != null) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                // Paused is no longer live, so the badge goes with it.
                                if (!isCatchupMode && localIsPlaying) PlayerLiveBadge(stringResource(R.string.live_badge_live))
                                val isMobile = onBackClick != null || LocalConfiguration.current.screenWidthDp < 600
                                if (!isMobile && channel.number > 0) {
                                    Text(
                                        text = channel.number.toString(),
                                        style = LiveType.NumberMono.copy(
                                            color = Color.White,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                        ),
                                    )
                                }
                                Text(
                                    text = channel.name,
                                    style = LiveType.ChannelName.copy(
                                        color = Color.White.copy(alpha = 0.85f),
                                        fontSize = if (touchControls) 13.sp else 15.sp,
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.weight(1f, fill = false),
                                )
                            }
                        }

                        // Programme title
                        Text(
                            text = liveProgrammeTitle(now?.title, channel?.name, stringResource(R.string.live_empty_no_programme)),
                            style = LiveType.ProgramTitle.copy(
                                color = Color.White,
                                fontSize = if (touchControls) 20.sp else 28.sp,
                                lineHeight = if (touchControls) 24.sp else 34.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            maxLines = if (now?.title.isNullOrBlank()) 2 else 1,
                            overflow = TextOverflow.Ellipsis,
                        )

                        // Time window, time left and stream details.
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            if (now != null) {
                                Text(
                                    text = formatTimeWindow(now),
                                    style = LiveType.TimeMono.copy(
                                        color = Color.White.copy(alpha = 0.9f),
                                        fontSize = if (touchControls) 12.sp else 14.sp,
                                    ),
                                )
                                val minutesLeft = playerMinutesLeft(now, clockMillis)
                                if (minutesLeft != null && !isCatchupMode) {
                                    Text(
                                        text = playerTimeLeftLabel(minutesLeft),
                                        style = LiveType.TimeMono.copy(
                                            color = Color.White.copy(alpha = 0.65f),
                                            fontSize = if (touchControls) 12.sp else 14.sp,
                                        ),
                                    )
                                }
                            }
                            StreamTechBadge(
                                resolution = streamResolution,
                                fps = streamFps,
                                videoCodec = streamVideoCodec,
                                audioInfo = streamAudioInfo,
                                bitrate = streamBitrate
                            )
                        }

                        // Next programme
                        if (next != null) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Text(
                                    text = stringResource(R.string.live_player_next_at, formatClock(next.startUtcMillis)),
                                    style = LiveType.TimeMono.copy(
                                        color = Color.White.copy(alpha = 0.55f),
                                        fontSize = if (touchControls) 11.sp else 13.sp,
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                                )
                                Text(
                                    text = next.title,
                                    style = LiveType.CellTitle.copy(
                                        color = Color.White.copy(alpha = 0.7f),
                                        fontSize = if (touchControls) 11.sp else 13.sp,
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    }
                }

                // Programme progress, always visible so the info bar alone shows how far along it is.
                if (!showControls) {
                    PlayerProgressLine(progress = progress)
                }

                // Seek bar and control buttons together are "the controls".
                AnimatedVisibility(
                    visible = showControls,
                    enter = fadeIn(tween(180)) + expandVertically(tween(220), expandFrom = Alignment.Top),
                    exit = fadeOut(tween(140)) + shrinkVertically(tween(180), shrinkTowards = Alignment.Top),
                ) {
                  Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    HudSeekBar(
                        progress = progress,
                        positionMs = elapsedShowMs,
                        durationMs = totalShowMs,
                        onSeekToPosition = onSeekToPosition?.takeIf { seekable },
                        onOpenQuickZap = onOpenQuickZap,
                        onScrubbingChanged = { scrubbing = it },
                    )

                    // Times under the bar: position on the left, programme length on the right.
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        val timeStyle = LiveType.TimeMono.copy(
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = if (touchControls) 12.sp else 13.sp,
                            fontWeight = FontWeight.Medium,
                        )
                        Text(text = positionText, style = timeStyle)
                        if (totalShowMs > 0L) Text(text = formatPlaybackDuration(totalShowMs), style = timeStyle)
                    }

                    HudControlBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 56.dp),
                        start = {
                            // Restart / reload sits apart from the transport so it is never hit by accident.
                            PlayerActionButton(
                                icon = Icons.Filled.Replay,
                                label = stringResource(R.string.live_player_restart),
                                touch = touchControls,
                                onClick = { onReplayClick?.invoke() },
                            )
                        },
                        center = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(if (touchControls) 12.dp else 18.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                PlayerActionButton(
                                    icon = Icons.Filled.SkipPrevious,
                                    label = stringResource(R.string.live_cd_previous_channel),
                                    onClick = { onPreviousCatchupClick?.invoke() },
                                )
                                if (seekable) {
                                    PlayerActionButton(
                                        icon = Icons.Filled.Replay10,
                                        label = stringResource(R.string.live_player_back_10),
                                        onClick = { onRewindClick?.invoke() },
                                    )
                                }
                                PlayerActionButton(
                                    icon = if (localIsPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                    label = stringResource(if (localIsPlaying) R.string.live_cd_pause else R.string.play),
                                    emphasis = true,
                                    focusRequester = playPauseFocusRequester,
                                    onClick = {
                                        localIsPlaying = !localIsPlaying
                                        onPlayPauseClick?.invoke()
                                    },
                                )
                                if (forwardEnabled) {
                                    PlayerActionButton(
                                        icon = Icons.Filled.Forward10,
                                        label = stringResource(R.string.live_player_forward_10),
                                        onClick = { onFastForwardClick?.invoke() },
                                    )
                                }
                                PlayerActionButton(
                                    icon = Icons.Filled.SkipNext,
                                    label = stringResource(R.string.live_cd_next_channel),
                                    onClick = { onNextCatchupClick?.invoke() },
                                )
                            }
                        },
                        end = {
                            // Wraps onto further lines when even a line of its own is too narrow
                            // (phones in portrait).
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(
                                    if (touchControls) 8.dp else 14.dp,
                                    Alignment.End,
                                ),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                if (isCatchupMode) {
                                    PlayerChipButton(
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        icon = Icons.Filled.FiberManualRecord,
                                        label = stringResource(R.string.live_player_back_to_live),
                                        highlighted = true,
                                        onClick = { onGoLiveClick?.invoke() },
                                    )
                                }
                                if (onAudioClick != null) {
                                    PlayerActionButton(
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        icon = Icons.Filled.Audiotrack,
                                        label = stringResource(R.string.live_audio),
                                        touch = touchControls,
                                        focusRequester = audioFocusRequester,
                                        onClick = {
                                            focusReturnTarget = audioFocusRequester
                                            onAudioClick()
                                        },
                                    )
                                }
                                if (onSubtitlesClick != null) {
                                    PlayerActionButton(
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        icon = Icons.Filled.ClosedCaption,
                                        label = stringResource(R.string.live_subtitles),
                                        touch = touchControls,
                                        focusRequester = subtitlesFocusRequester,
                                        onClick = {
                                            focusReturnTarget = subtitlesFocusRequester
                                            onSubtitlesClick()
                                        },
                                    )
                                }
                                if (onPictureClick != null && pictureLabel != null) {
                                    PlayerActionButton(
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        icon = Icons.Filled.AspectRatio,
                                        label = pictureLabel,
                                        touch = touchControls,
                                        onClick = onPictureClick,
                                    )
                                }
                                if (onSleepClick != null && sleepLabel != null) {
                                    PlayerActionButton(
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        icon = Icons.Filled.Bedtime,
                                        label = sleepLabel,
                                        touch = touchControls,
                                        active = sleepActive,
                                        onClick = onSleepClick,
                                    )
                                }
                                if (onOpenVariants != null) {
                                    PlayerActionButton(
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        icon = Icons.Filled.SwapHoriz,
                                        label = stringResource(R.string.live_label_choose_source),
                                        touch = touchControls,
                                        onClick = onOpenVariants,
                                    )
                                }
                                if (onGuideClick != null) {
                                    PlayerActionButton(
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        icon = Icons.AutoMirrored.Filled.ViewList,
                                        label = stringResource(R.string.live_player_guide),
                                        touch = touchControls,
                                        onClick = onGuideClick,
                                    )
                                }
                            }
                        },
                    )
                  }
                }
            }
        }
    }
}

/**
 * The bottom button row: time at the start, playback controls centred, actions at the end.
 * The controls stay exactly centred while there is room and slide aside when the actions reach
 * them. When start, controls and actions cannot share one line at all, the actions take a line
 * of their own above the controls (see [hudControlBarPlacement]).
 */
@Composable
private fun HudControlBar(
    modifier: Modifier,
    start: @Composable () -> Unit,
    center: @Composable () -> Unit,
    end: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = {
            Box { start() }
            Box { center() }
            Box { end() }
        },
    ) { measurables, constraints ->
        val width = constraints.maxWidth
        val loose = constraints.copy(minWidth = 0, minHeight = 0, maxHeight = Constraints.Infinity)
        val startPlaceable = measurables[0].measure(loose)
        val centerPlaceable = measurables[1].measure(loose)
        val endPlaceable = measurables[2].measure(loose)
        val placement = hudControlBarPlacement(
            width = width,
            minHeight = constraints.minHeight,
            startWidth = startPlaceable.width, startHeight = startPlaceable.height,
            centerWidth = centerPlaceable.width, centerHeight = centerPlaceable.height,
            endWidth = endPlaceable.width, endHeight = endPlaceable.height,
            gap = 16.dp.roundToPx(),
            lineGap = 8.dp.roundToPx(),
        )
        layout(width, constraints.constrainHeight(placement.height)) {
            startPlaceable.placeRelative(0, placement.startY)
            centerPlaceable.placeRelative(placement.centerX, placement.centerY)
            endPlaceable.placeRelative(width - endPlaceable.width, placement.endY)
        }
    }
}

/** Where [HudControlBar] puts its three groups (x for the centred group, y for each). */
internal data class HudBarPlacement(
    val oneLine: Boolean,
    val height: Int,
    val startY: Int,
    val centerX: Int,
    val centerY: Int,
    val endY: Int,
)

/**
 * One line when time, controls and actions fit side by side with [gap] between them: controls
 * centred, moved only as far as needed to clear both sides. Otherwise the actions go on a line of
 * their own at the top and time and controls share the line below, so nothing is drawn over
 * anything else.
 */
internal fun hudControlBarPlacement(
    width: Int,
    minHeight: Int,
    startWidth: Int,
    startHeight: Int,
    centerWidth: Int,
    centerHeight: Int,
    endWidth: Int,
    endHeight: Int,
    gap: Int,
    lineGap: Int,
): HudBarPlacement {
    val centered = (width - centerWidth) / 2
    if (startWidth + gap + centerWidth + gap + endWidth <= width) {
        val height = maxOf(minHeight, startHeight, centerHeight, endHeight)
        return HudBarPlacement(
            oneLine = true,
            height = height,
            startY = (height - startHeight) / 2,
            centerX = centered.coerceIn(startWidth + gap, width - endWidth - gap - centerWidth),
            centerY = (height - centerHeight) / 2,
            endY = (height - endHeight) / 2,
        )
    }
    val lowerTop = endHeight + lineGap
    val lowerHeight = maxOf(startHeight, centerHeight)
    val minX = startWidth + gap
    val maxX = (width - centerWidth).coerceAtLeast(minX)
    return HudBarPlacement(
        oneLine = false,
        height = maxOf(minHeight, lowerTop + lowerHeight),
        startY = lowerTop + (lowerHeight - startHeight) / 2,
        centerX = centered.coerceIn(minX, maxX),
        centerY = lowerTop + (lowerHeight - centerHeight) / 2,
        endY = 0,
    )
}

@Composable
internal fun HudSeekBar(
    progress: Float,
    positionMs: Long,
    durationMs: Long,
    onSeekToPosition: ((Long) -> Unit)?,
    onOpenQuickZap: (() -> Unit)?,
    modifier: Modifier = Modifier,
    onScrubbingChanged: (Boolean) -> Unit = {},
) {
    if (LocalDeviceType.current.isTouchDevice()) {
        var scrubProgress by remember { mutableStateOf<Float?>(null) }
        androidx.compose.material3.Slider(
            value = scrubProgress ?: progress.coerceIn(0f, 1f),
            onValueChange = {
                scrubProgress = it
                onScrubbingChanged(true)
            },
            onValueChangeFinished = {
                scrubProgress?.let { onSeekToPosition?.invoke((it * durationMs).toLong()) }
                scrubProgress = null
                onScrubbingChanged(false)
            },
            enabled = onSeekToPosition != null && durationMs > 0L,
            modifier = modifier.fillMaxWidth().height(48.dp),
            colors = androidx.compose.material3.SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = liveAccent(),
                inactiveTrackColor = LiveColors.Panel,
            ),
        )
        return
    }
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused = it.isFocused }
            // Without anything to seek the bar is information only, so focus skips it.
            .focusable(enabled = onSeekToPosition != null)
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    val stepMs = 10_000L
                    when (event.nativeKeyEvent.keyCode) {
                        AndroidKeyEvent.KEYCODE_DPAD_LEFT -> {
                            if (onSeekToPosition != null) {
                                val newPos = (positionMs - stepMs).coerceAtLeast(0L)
                                onSeekToPosition(newPos)
                                true
                            } else false
                        }
                        AndroidKeyEvent.KEYCODE_DPAD_RIGHT -> {
                            if (onSeekToPosition != null) {
                                val maxDuration = if (durationMs > 0L) durationMs else positionMs + 300_000L
                                val newPos = (positionMs + stepMs).coerceAtMost(maxDuration)
                                onSeekToPosition(newPos)
                                true
                            } else false
                        }
                        AndroidKeyEvent.KEYCODE_DPAD_UP -> {
                            if (onOpenQuickZap != null) {
                                onOpenQuickZap()
                                true
                            } else false
                        }
                        else -> false
                    }
                } else false
            }
            .padding(vertical = 4.dp),
    ) {
        val trackHeight by animateDpAsState(if (isFocused) 6.dp else 4.dp, tween(160), label = "seek-track")
        val thumbScale by animateFloatAsState(if (isFocused) 1f else 0f, tween(160), label = "seek-thumb")
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            val trackWidth = maxWidth
            val clampedProgress = progress.coerceIn(0f, 1f)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(trackHeight)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White.copy(alpha = if (isFocused) 0.35f else 0.22f)),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(clampedProgress)
                    .height(trackHeight)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isFocused) Color.White else liveAccent()),
            )
            // Scrubber thumb grows in when the bar takes focus.
            val maxThumbStart = (trackWidth - 18.dp).coerceAtLeast(0.dp)
            val thumbOffset = (trackWidth * clampedProgress - 9.dp).coerceIn(0.dp, maxThumbStart)
            Box(
                modifier = Modifier
                    .padding(start = thumbOffset)
                    .size(18.dp)
                    .graphicsLayer {
                        scaleX = thumbScale
                        scaleY = thumbScale
                    }
                    .shadow(6.dp, CircleShape)
                    .clip(CircleShape)
                    .background(Color.White),
            )
        }
    }
}

private fun formatPlaybackDuration(ms: Long): String {
    val totalSeconds = (ms / 1000L).coerceAtLeast(0L)
    val hours = totalSeconds / 3600L
    val minutes = (totalSeconds % 3600L) / 60L
    val seconds = totalSeconds % 60L
    return if (hours > 0L) {
        "%d:%02d:%02d".format(hours, minutes, seconds)
    } else {
        "%02d:%02d".format(minutes, seconds)
    }
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun StreamTechBadge(
    resolution: String = "",
    fps: String = "",
    videoCodec: String = "",
    audioInfo: String = "",
    bitrate: String = ""
) {
    val elements = listOf(fps, resolution, videoCodec, audioInfo, bitrate).filter { it.isNotBlank() }
    if (elements.isEmpty()) return

    androidx.compose.foundation.layout.FlowRow(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black.copy(alpha = 0.45f))
            .padding(horizontal = 6.dp, vertical = 2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        elements.forEachIndexed { index, text ->
            Text(
                text = text,
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            if (index < elements.size - 1) {
                Box(
                    modifier = Modifier
                        .size(3.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.4f))
                )
            }
        }
    }
}
// The control row is asked for focus until it answers: on the frame the HUD
// fades in from nothing the row may not be attached yet. Ten tries 50 ms apart
// is half a second at the outside, and it stops at the first one that lands.
private const val ControlFocusAttempts = 10
private const val ControlFocusRetryMs = 50L

/** Whole minutes left in a programme that is on now, or null outside it. */
internal fun playerMinutesLeft(now: com.arflix.tv.data.model.IptvProgram, clockMillis: Long): Long? {
    if (clockMillis !in now.startUtcMillis..now.endUtcMillis) return null
    return ((now.endUtcMillis - clockMillis + 59_999L) / 60_000L).coerceAtLeast(0L)
}

@Composable
private fun playerTimeLeftLabel(minutes: Long): String =
    if (minutes >= 60) stringResource(R.string.live_player_hours_left, (minutes / 60).toInt(), (minutes % 60).toInt())
    else stringResource(R.string.live_player_minutes_left, minutes.toInt())

/** Thin programme progress line for the info bar, when the seek bar is not shown. */
@Composable
private fun PlayerProgressLine(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(3.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(Color.White.copy(alpha = 0.2f)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .height(3.dp)
                .background(Color.White),
        )
    }
}

private const val HudInfoTimeoutMs = 5_000L
private const val HudControlsTimeoutMs = 8_000L
