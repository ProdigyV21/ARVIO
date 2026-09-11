@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.tv.live

import android.view.KeyEvent as AndroidKeyEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
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
import kotlinx.coroutines.delay
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Fullscreen playback HUD matching the full-width reference player layout.
 * Auto-hides 5s after the last `pokeSignal` bump; `hideSignal` dismisses it straight away (Back).
 * With `showControls` off the seek bar and the button row are not composed at all: what surfaces on
 * its own after a channel zap is then pure information with nothing focusable in it. With it on the
 * controls are drawn and focused in the same breath, so anything visible is always operable.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun FullscreenHud(
    channel: EnrichedChannel?,
    nowNext: IptvNowNext?,
    pokeSignal: Int,
    categoryName: String? = null,
    isCatchupMode: Boolean = false,
    isPlaying: Boolean = true,
    isBuffering: Boolean = false,
    playbackPositionMs: Long = 0L,
    playbackDurationMs: Long = 0L,
    onBackClick: (() -> Unit)? = null,
    onGuideClick: (() -> Unit)? = null,
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
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(true) }
    var lastPoke by remember { mutableLongStateOf(System.currentTimeMillis()) }

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

    LaunchedEffect(pokeSignal) {
        visible = true
        onVisibilityChanged?.invoke(true)
        lastPoke = System.currentTimeMillis()
        delay(5_000)
        if (System.currentTimeMillis() - lastPoke >= 5_000) {
            visible = false
            onVisibilityChanged?.invoke(false)
        }
    }

    var clockMillis by remember { mutableLongStateOf(System.currentTimeMillis()) }
    LaunchedEffect(Unit) {
        while (true) {
            clockMillis = System.currentTimeMillis()
            delay(1_000)
        }
    }

    // Instant local play/pause icon state toggle for immediate UI feedback (0ms delay)
    var localIsPlaying by remember(isPlaying) { mutableStateOf(isPlaying) }

    val playPauseFocusRequester = remember { FocusRequester() }
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
    LaunchedEffect(visible, showControls) {
        if (!visible || !showControls) {
            initialFocusApplied = false
        } else {
            var attempts = 0
            while (!initialFocusApplied && attempts < ControlFocusAttempts) {
                attempts++
                delay(ControlFocusRetryMs)
                if (runCatching { playPauseFocusRequester.requestFocus() }.isSuccess) {
                    initialFocusApplied = true
                }
            }
        }
    }

    // --- Loading Ring Overlay in Center of Screen ---
    if (isBuffering) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.65f)),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(44.dp),
                    color = LiveColors.Accent,
                    strokeWidth = 4.dp,
                )
            }
        }
    }

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
                            0.25f to Color.Transparent,
                            0.45f to Color.Transparent,
                            1f to Color.Black.copy(alpha = 0.95f),
                        )
                    ),
            )

            // --- Top Header Row (Category Name & Formatted Date/Time) ---
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp, vertical = 24.dp),
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
                    text = formatHeaderDateTime(clockMillis),
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
                    .padding(horizontal = 48.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                // --- Row 1: Channel Logo & Program Metadata ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    if (channel != null) {
                        ChannelLogo(channel = channel, size = 68.dp)
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        // Program Title
                        Text(
                            text = now?.title
                                ?: channel?.name
                                ?: stringResource(R.string.live_empty_no_programme),
                            style = LiveType.ProgramTitle.copy(
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )

                        // Time window + remaining duration + channel number & name
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            val timeWin = formatTimeWindow(now)
                            if (timeWin.isNotBlank()) {
                                Text(
                                    text = timeWin,
                                    style = LiveType.TimeMono.copy(
                                        color = Color.White.copy(alpha = 0.9f),
                                        fontSize = 14.sp,
                                    ),
                                )
                                Text(
                                    text = "—",
                                    style = LiveType.TimeMono.copy(
                                        color = Color.White.copy(alpha = 0.5f),
                                        fontSize = 14.sp,
                                    ),
                                )
                            }

                            val remaining = remainingLabel(now)
                            if (remaining.isNotBlank()) {
                                Text(
                                    text = remaining,
                                    style = LiveType.TimeMono.copy(
                                        color = Color.White.copy(alpha = 0.75f),
                                        fontSize = 14.sp,
                                    ),
                                )
                            }

                            if (channel != null) {
                                Text(
                                    text = "${channel.number}  ${channel.name}",
                                    style = LiveType.ChannelName.copy(
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }

                        // Next Program Preview
                        if (next != null) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                Text(
                                    text = "${formatClock(next.startUtcMillis)} — ${formatClock(next.endUtcMillis)}",
                                    style = LiveType.TimeMono.copy(
                                        color = Color.White.copy(alpha = 0.5f),
                                        fontSize = 13.sp,
                                    ),
                                )
                                Text(
                                    text = next.title,
                                    style = LiveType.CellTitle.copy(
                                        color = Color.White.copy(alpha = 0.6f),
                                        fontSize = 13.sp,
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    }
                }

                // Seek bar and control buttons together are "the controls".
                // On a remote they appear only once the user asks for them with
                // OK, so that what surfaces on its own carries no focusable
                // element at all and cannot look operable while it is not.
                if (showControls) {
                    // --- Row 2: Full-Width Seek Bar with Scrubber Ball ---
                    HudSeekBar(
                        progress = progress,
                        positionMs = elapsedShowMs,
                        durationMs = totalShowMs,
                        onSeekToPosition = onSeekToPosition,
                        onOpenQuickZap = onOpenQuickZap,
                    )

                    // --- Row 3: Bottom Control Bar (Exact Centering & Time on Left) ---
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                    ) {
                        // Left Side: Time Text below Seek Bar
                        Box(
                            modifier = Modifier.align(Alignment.CenterStart),
                        ) {
                            Text(
                                text = positionText,
                                style = LiveType.TimeMono.copy(
                                    color = Color.White.copy(alpha = 0.85f),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                ),
                            )
                        }

                        // EXACT CENTER: Playback Controls Bar
                        Row(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            // Previous channel (|<)
                            HudIconButton(
                                icon = Icons.Filled.SkipPrevious,
                                contentDescription = stringResource(R.string.live_cd_previous_channel),
                                onClick = { onPreviousCatchupClick?.invoke() },
                            )

                            // Rewind (<<)
                            HudIconButton(
                                icon = Icons.Filled.FastRewind,
                                contentDescription = stringResource(R.string.live_cd_rewind),
                                onClick = { onRewindClick?.invoke() },
                            )

                            // Central Play/Pause button (Instant local state toggle!)
                            HudIconButton(
                                icon = if (localIsPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                contentDescription = stringResource(if (localIsPlaying) R.string.live_cd_pause else R.string.play),
                                emphasis = true,
                                focusRequester = playPauseFocusRequester,
                                onClick = {
                                    localIsPlaying = !localIsPlaying
                                    onPlayPauseClick?.invoke()
                                },
                            )

                            // Fast Forward (>>)
                            HudIconButton(
                                icon = Icons.Filled.FastForward,
                                contentDescription = stringResource(R.string.live_cd_fast_forward),
                                onClick = { onFastForwardClick?.invoke() },
                            )

                            // Next channel (>|)
                            HudIconButton(
                                icon = Icons.Filled.SkipNext,
                                contentDescription = stringResource(R.string.live_cd_next_channel),
                                onClick = { onNextCatchupClick?.invoke() },
                            )
                        }

                        // Right Side: Replay, LIVE, GUIDE
                        Row(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            // Replay / Restart
                            HudIconButton(
                                icon = Icons.Filled.Replay,
                                contentDescription = stringResource(R.string.live_cd_replay),
                                onClick = { onReplayClick?.invoke() },
                            )

                            // LIVE button
                            if (isCatchupMode) {
                                HudActionButton(
                                    label = stringResource(R.string.live_badge_live),
                                    onClick = { onGoLiveClick?.invoke() },
                                )
                            }

                            // Guide button at far right
                            if (onGuideClick != null) {
                                HudActionButton(
                                    label = stringResource(R.string.live_btn_guide),
                                    onClick = onGuideClick,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HudSeekBar(
    progress: Float,
    positionMs: Long,
    durationMs: Long,
    onSeekToPosition: ((Long) -> Unit)?,
    onOpenQuickZap: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
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
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isFocused) 16.dp else 6.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            val trackWidth = maxWidth
            val clampedProgress = progress.coerceIn(0f, 1f)

            // Progress track background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isFocused) 6.dp else 4.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (isFocused) Color.White.copy(alpha = 0.35f) else LiveColors.Panel),
            )

            // Active progress fill
            Box(
                modifier = Modifier
                    .fillMaxWidth(clampedProgress)
                    .height(if (isFocused) 6.dp else 4.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(LiveColors.Accent),
            )

            // Circular white Scrubber Thumb Ball when focused
            if (isFocused) {
                val maxThumbStart = (trackWidth - 16.dp).coerceAtLeast(0.dp)
                val thumbOffset = (trackWidth * clampedProgress - 8.dp).coerceIn(0.dp, maxThumbStart)
                Box(
                    modifier = Modifier
                        .padding(start = thumbOffset)
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                )
            }
        }
    }
}

@Composable
private fun HudIconButton(
    icon: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    emphasis: Boolean = false,
    focusRequester: FocusRequester? = null,
    onClick: () -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    val size = if (emphasis) 54.dp else 42.dp
    val iconSize = if (emphasis) 28.dp else 22.dp

    val bgColor = when {
        isFocused -> Color.White
        emphasis -> LiveColors.Accent
        else -> Color.Black.copy(alpha = 0.55f)
    }

    val iconColor = when {
        isFocused -> Color.Black
        emphasis -> LiveColors.Bg
        else -> Color.White
    }

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .onFocusChanged { isFocused = it.isFocused }
            .then(if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier)
            .background(bgColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = iconColor,
            modifier = Modifier.size(iconSize),
        )
    }
}

private fun formatHeaderDateTime(millis: Long): String {
    return try {
        val instant = Instant.ofEpochMilli(millis)
        val zdt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("EEE, MMM d, h:mm a", Locale.US)
        zdt.format(formatter)
    } catch (_: Exception) {
        ""
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

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun HudActionButton(
    label: String,
    onClick: () -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .onFocusChanged { isFocused = it.isFocused }
            .background(if (isFocused) Color.White else LiveColors.Accent)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 7.dp),
    ) {
        Text(
            text = label,
            style = LiveType.Badge.copy(
                color = if (isFocused) Color.Black else LiveColors.Bg,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}

// The control row is asked for focus until it answers: on the frame the HUD
// fades in from nothing the row may not be attached yet. Ten tries 50 ms apart
// is half a second at the outside, and it stops at the first one that lands.
private const val ControlFocusAttempts = 10
private const val ControlFocusRetryMs = 50L
