package com.arflix.tv.ui.screens.player.mobile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.arflix.tv.R
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.wrapContentHeight
import com.arflix.tv.ui.screens.player.preview.ReadySeekPreview
import com.arflix.tv.ui.screens.player.preview.SeekPreviewFrame
import kotlin.math.roundToInt
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

/**
 * Top bar for the ARVIO Mobile Player.
 */
@Composable
fun MobilePlayerTopBar(
    onClose: () -> Unit,
    onLock: () -> Unit,
    onPip: () -> Unit,
    onCast: () -> Unit,
    onOpenMoreSettings: () -> Unit,
    isCasting: Boolean,
    showCastButton: Boolean,
    showPipButton: Boolean,
    horizontalPadding: Dp = 24.dp,
    topPadding: Dp = 16.dp,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = horizontalPadding, end = horizontalPadding, top = topPadding)
            .zIndex(6f),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Nav Group: Close & Lock
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MobileIconButton(
                iconRes = R.drawable.ic_huge_arrow_left,
                contentDescription = "Back",
                onClick = onClose,
                size = 24.dp
            )
            MobileIconButton(
                iconRes = R.drawable.ic_huge_lock,
                contentDescription = "Lock Controls",
                onClick = onLock
            )
        }

        // Right Nav Group: PiP, Cast, More Settings
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (showPipButton) {
                MobileIconButton(
                    iconRes = R.drawable.ic_huge_pip,
                    contentDescription = "Picture in Picture",
                    onClick = onPip
                )
            }
            if (showCastButton) {
                MobileIconButton(
                    iconRes = if (isCasting) R.drawable.ic_huge_cast_connected else R.drawable.ic_huge_cast,
                    contentDescription = "Cast",
                    tint = if (isCasting) Color(0xFF66D9FF) else MobilePlayerTokens.InkPrimary,
                    onClick = onCast
                )
            }
            MobileIconButton(
                iconRes = R.drawable.ic_huge_more_horizontal,
                contentDescription = "More Settings",
                onClick = onOpenMoreSettings
            )
        }
    }
}

/**
 * Center Playback Controls: Rewind 10s, Play/Pause, Forward 10s.
 */
@Composable
fun MobilePlayerCenterControls(
    isPlaying: Boolean,
    isBuffering: Boolean,
    onTogglePlayPause: () -> Unit,
    onRewind10: () -> Unit,
    onForward10: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .zIndex(6f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Rewind 10s
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onRewind10
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_huge_replay_10),
                contentDescription = "Rewind 10 seconds",
                tint = MobilePlayerTokens.InkPrimary,
                modifier = Modifier.size(34.dp)
            )
        }

        Spacer(modifier = Modifier.width(48.dp))

        // Play/Pause button (center spinner is hoisted to persistent unified overlay)
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onTogglePlayPause
                ),
            contentAlignment = Alignment.Center
        ) {
            if (!isBuffering) {
                Icon(
                    painter = painterResource(if (isPlaying) R.drawable.ic_huge_pause else R.drawable.ic_huge_play),
                    contentDescription = if (isPlaying) "Pause" else "Play",
                    tint = MobilePlayerTokens.InkPrimary,
                    modifier = Modifier.size(if (isPlaying) 38.dp else 42.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(48.dp))

        // Forward 10s
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onForward10
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_huge_forward_10),
                contentDescription = "Forward 10 seconds",
                tint = MobilePlayerTokens.InkPrimary,
                modifier = Modifier.size(34.dp)
            )
        }
    }
}

/**
 * Contextual prompt type: Intro skip, Outro skip, or Up Next card.
 */
sealed class MobileContextPromptState {
    object Hidden : MobileContextPromptState()
    data class SkipAction(
        val label: String,
        val isOutro: Boolean = false,
        val progress: Float = 1f
    ) : MobileContextPromptState()
    data class UpNext(
        val nextEpisodeTitle: String,
        val nextEpisodeNumber: Int,
        val thumbnail: String?,
        val countdownSeconds: Int = 10,
        val progress: Float = 1f,
        val isAutoplay: Boolean = true
    ) : MobileContextPromptState()
}

/**
 * Shared contextual prompt slot at bottom-right (right: 20dp, bottom: 96dp).
 */
@Composable
fun MobileContextualPrompt(
    promptState: MobileContextPromptState,
    showControls: Boolean = true,
    onSkipIntro: () -> Unit,
    onSkipOutro: () -> Unit,
    onPlayNextEpisode: () -> Unit,
    onCancelPrompt: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isVisible = promptState !is MobileContextPromptState.Hidden

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(tween(180)) + slideInVertically(tween(180)) { it / 2 },
        exit = fadeOut(tween(180)) + slideOutVertically(tween(180)) { it / 2 },
        modifier = modifier.wrapContentSize()
    ) {
        when (promptState) {
            is MobileContextPromptState.SkipAction -> {
                SkipActionPrompt(
                    label = promptState.label,
                    progress = promptState.progress,
                    onAction = if (promptState.isOutro) onSkipOutro else onSkipIntro
                )
            }
            is MobileContextPromptState.UpNext -> {
                UpNextPromptCard(
                    nextEpisodeTitle = promptState.nextEpisodeTitle,
                    thumbnail = promptState.thumbnail,
                    countdownSeconds = promptState.countdownSeconds,
                    progress = promptState.progress,
                    isAutoplay = promptState.isAutoplay,
                    onPlayNow = onPlayNextEpisode,
                    onCancel = onCancelPrompt
                )
            }
            MobileContextPromptState.Hidden -> Unit
        }
    }
}

@Composable
private fun SkipActionPrompt(
    label: String,
    progress: Float,
    onAction: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(MobilePlayerTokens.ShapePrompt)
            .background(Color(0xD9141518))
            .border(1.dp, Color(0x38FFFFFF), MobilePlayerTokens.ShapePrompt)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onAction
            )
    ) {
        // Text without triangle icon
        Text(
            text = label,
            color = MobilePlayerTokens.InkPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 12.dp)
        )

        // Drain progress line hugging the very bottom edge of the button
        Box(
            modifier = Modifier.matchParentSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(Color(0x38FFFFFF))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress.coerceIn(0f, 1f))
                        .fillMaxHeight()
                        .background(MobilePlayerTokens.InkPrimary)
                )
            }
        }
    }
}

@Composable
private fun UpNextPromptCard(
    nextEpisodeTitle: String,
    thumbnail: String?,
    countdownSeconds: Int,
    progress: Float,
    isAutoplay: Boolean,
    onPlayNow: () -> Unit,
    onCancel: () -> Unit
) {

    Box(
        modifier = Modifier
            .width(180.dp)
            .clip(MobilePlayerTokens.ShapeCard)
            .background(MobilePlayerTokens.PanelBg)
            .border(1.dp, MobilePlayerTokens.PanelBorder, MobilePlayerTokens.ShapeCard)
            .padding(10.dp)
    ) {
        Column {
            // Thumbnail preview (tap to play next episode immediately)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(MobilePlayerTokens.ShapeThumb)
                    .background(MobilePlayerTokens.PanelBg2)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onPlayNow
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (!thumbnail.isNullOrBlank()) {
                    AsyncImage(
                        model = thumbnail,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.55f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_huge_play),
                        contentDescription = null,
                        tint = MobilePlayerTokens.InkPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = if (isAutoplay) "PLAYING NEXT IN ${countdownSeconds}S" else "UP NEXT",
                color = MobilePlayerTokens.InkTertiary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )

            Text(
                text = nextEpisodeTitle,
                color = MobilePlayerTokens.InkPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Cancel button (user taps image thumbnail to play next)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MobilePlayerTokens.ShapeBtn)
                    .background(Color.Transparent)
                    .border(1.dp, Color(0x40FFFFFF), MobilePlayerTokens.ShapeBtn)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onCancel
                    )
                    .padding(vertical = 7.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Cancel",
                    color = MobilePlayerTokens.InkPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

/**
 * Bottom Section containing Title Block, Secondary Controls, Progress Scrubber, and Utility Row.
 */
@Composable
fun MobilePlayerBottomSection(
    eyebrow: String,
    mainTitle: String,
    currentPositionMs: Long,
    durationMs: Long,
    bufferedPositionMs: Long,
    isScrubbing: Boolean,
    scrubPreviewMs: Long,
    currentAudioTrack: String,
    currentSubtitleTrack: String,
    currentPlaybackSpeed: Float,
    isEpisodeListAvailable: Boolean,
    isPromptShowing: Boolean,
    seekPreviewFrame: SeekPreviewFrame? = null,
    onOpenSources: () -> Unit,
    onOpenEpisodes: () -> Unit,
    onOpenAudio: () -> Unit,
    onOpenSubtitles: () -> Unit,
    onOpenSpeed: () -> Unit,
    onSeekStart: (Float) -> Unit,
    onSeekMove: (Float) -> Unit,
    onSeekEnd: () -> Unit,
    onSeekCancel: () -> Unit,
    horizontalPadding: Dp = 24.dp,
    bottomPadding: Dp = 16.dp,
    modifier: Modifier = Modifier
) {
    var showTotalTimeMode by remember { mutableStateOf(false) }
    val latestSeekStart by androidx.compose.runtime.rememberUpdatedState(onSeekStart)
    val latestSeekMove by androidx.compose.runtime.rememberUpdatedState(onSeekMove)
    val latestSeekEnd by androidx.compose.runtime.rememberUpdatedState(onSeekEnd)
    val latestSeekCancel by androidx.compose.runtime.rememberUpdatedState(onSeekCancel)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = horizontalPadding, end = horizontalPadding, bottom = bottomPadding)
            .zIndex(6f),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Meta & Secondary Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            // Title Block
            Column(modifier = Modifier.weight(1f, fill = false)) {
                if (eyebrow.isNotBlank()) {
                    Text(
                        text = eyebrow.uppercase(),
                        color = MobilePlayerTokens.InkSecondary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        style = androidx.compose.ui.text.TextStyle(
                            shadow = MobilePlayerTokens.TextShadow
                        )
                    )
                }
                Text(
                    text = mainTitle,
                    color = MobilePlayerTokens.InkPrimary,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = MobilePlayerTokens.TextShadow
                    )
                )
            }

            // Secondary Controls (Sources & Episodes) — faded out if prompt is showing
            val secondaryControlsAlpha by animateFloatAsState(
                if (isPromptShowing) 0f else 1f,
                animationSpec = tween(200),
                label = "secondaryControlsAlpha"
            )
            Row(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .offset(y = (-2).dp)
                    .graphicsLayerAlpha(secondaryControlsAlpha),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MobileIconButton(
                    iconRes = R.drawable.ic_huge_film,
                    contentDescription = "Sources",
                    size = 20.dp,
                    onClick = onOpenSources
                )
                if (isEpisodeListAvailable) {
                    MobileIconButton(
                        iconRes = R.drawable.ic_huge_list_video,
                        contentDescription = "Episodes",
                        size = 20.dp,
                        onClick = onOpenEpisodes
                    )
                }
            }
        }

        // Progress Bar Row (Elapsed + Custom Scrubber + Remaining/Total toggle)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val displayPos = if (isScrubbing) scrubPreviewMs else currentPositionMs
            val remainingMs = (durationMs - displayPos).coerceAtLeast(0L)

            // Elapsed timestamp (left) - natural single-line measurement
            Text(
                text = formatTime(displayPos),
                color = MobilePlayerTokens.InkSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                softWrap = false,
                style = androidx.compose.ui.text.TextStyle(
                    shadow = MobilePlayerTokens.TextShadow
                )
            )

            // Interactive Scrubber Bar - takes all remaining space, shrinks dynamically to fit timestamps
            var trackWidthPx by remember { mutableIntStateOf(0) }
            val scrubberHeight = 36.dp
            val progressFraction = if (durationMs > 0) (displayPos.toFloat() / durationMs.toFloat()).coerceIn(0f, 1f) else 0f
            val bufferedFraction = if (durationMs > 0) (bufferedPositionMs.toFloat() / durationMs.toFloat()).coerceIn(0f, 1f) else 0f
            val trackHeight by animateDpAsState(
                targetValue = if (isScrubbing) 5.5.dp else 3.5.dp,
                animationSpec = tween(150),
                label = "trackHeight"
            )
            val needleWidth by animateDpAsState(
                targetValue = if (isScrubbing) 4.dp else 2.5.dp,
                animationSpec = tween(150),
                label = "needleWidth"
            )
            val needleHeight by animateDpAsState(
                targetValue = if (isScrubbing) 18.dp else 12.dp,
                animationSpec = tween(150),
                label = "needleHeight"
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(scrubberHeight)
                    .testTag("mobile_player_scrubber")
                    .onSizeChanged { trackWidthPx = it.width }
                    .pointerInput(durationMs) {
                        awaitEachGesture {
                            val down = awaitFirstDown(requireUnconsumed = false)
                            var seeking = trackWidthPx > 0 && durationMs > 0L
                            try {
                                if (seeking) {
                                    val initialPct = (down.position.x / trackWidthPx).coerceIn(0f, 1f)
                                    latestSeekStart(initialPct)
                                    down.consume()
                                }

                                val pointerId = down.id
                                while (true) {
                                    val event = awaitPointerEvent()
                                    val change = event.changes.firstOrNull { it.id == pointerId } ?: break
                                    // Android cancellation arrives as a consumed synthetic up.
                                    if (change.isConsumed) break
                                    if (!change.pressed) {
                                        change.consume()
                                        if (seeking) latestSeekEnd()
                                        seeking = false
                                        break
                                    }
                                    if (seeking) {
                                        val currentPct = (change.position.x / trackWidthPx).coerceIn(0f, 1f)
                                        latestSeekMove(currentPct)
                                        change.consume()
                                    }
                                }
                            } finally {
                                if (seeking) latestSeekCancel()
                            }
                        }
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                // Background track
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(trackHeight)
                        .clip(RoundedCornerShape(3.dp))
                        .background(MobilePlayerTokens.TrackBg)
                )

                // Buffered track
                if (bufferedFraction > 0f) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(bufferedFraction)
                            .height(trackHeight)
                            .clip(RoundedCornerShape(3.dp))
                            .background(MobilePlayerTokens.TrackBuffered)
                    )
                }

                // Played Fill track
                if (progressFraction > 0f) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progressFraction)
                            .height(trackHeight)
                            .clip(RoundedCornerShape(3.dp))
                            .background(MobilePlayerTokens.TrackFill)
                    )
                }

                // Playhead Needle: Modern precision scrubber (zero circular blob)
                val density = LocalDensity.current
                val needleWidthPx = with(density) { needleWidth.toPx() }
                val needleOffsetPx = if (trackWidthPx > needleWidthPx) {
                    (progressFraction * (trackWidthPx - needleWidthPx)).coerceIn(0f, trackWidthPx - needleWidthPx)
                } else 0f

                Box(
                    modifier = Modifier
                        .offset { IntOffset(needleOffsetPx.roundToInt(), 0) }
                        .shadow(if (isScrubbing) 6.dp else 2.dp, RoundedCornerShape(2.dp))
                        .size(width = needleWidth, height = needleHeight)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color.White)
                )

                // Floating seek thumbnail preview card
                val previewCardWidth = 150.dp
                val previewCardHeight = previewCardWidth * 9f / 16f
                val previewDensity = LocalDensity.current
                val previewCardWidthPx = with(previewDensity) { previewCardWidth.toPx() }
                val previewOffsetY = with(previewDensity) {
                    (scrubberHeight / 2f - needleHeight / 2f - 4.dp - previewCardHeight).roundToPx()
                }
                val previewX = if (trackWidthPx > 0) {
                    (progressFraction * trackWidthPx - previewCardWidthPx / 2f)
                        .coerceIn(0f, (trackWidthPx - previewCardWidthPx).coerceAtLeast(0f))
                        .roundToInt()
                } else 0

                ReadySeekPreview(
                    frame = seekPreviewFrame,
                    positionMs = displayPos,
                    visible = isScrubbing && durationMs > 0L,
                    cornerRadius = 8.dp,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset { IntOffset(previewX, previewOffsetY) }
                        .zIndex(12f)
                        .width(previewCardWidth)
                        .wrapContentHeight(align = Alignment.Top, unbounded = true),
                )
            }

            // Remaining / Total toggle timestamp (right) - natural single-line measurement, never wraps
            Text(
                text = if (showTotalTimeMode) formatTime(durationMs) else "-${formatTime(remainingMs)}",
                color = MobilePlayerTokens.InkSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                softWrap = false,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { showTotalTimeMode = !showTotalTimeMode }
                    ),
                style = androidx.compose.ui.text.TextStyle(
                    shadow = MobilePlayerTokens.TextShadow
                )
            )
        }

        // Utility Row: Audio + Dot + Subtitles + Dot + Speed
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Audio Track chip
            Row(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onOpenAudio
                    )
                    .padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_huge_speaker),
                    contentDescription = null,
                    tint = MobilePlayerTokens.InkSecondary,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = currentAudioTrack.ifBlank { "Audio" },
                    color = MobilePlayerTokens.InkSecondary,
                    fontSize = 12.5.sp,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = MobilePlayerTokens.TextShadow
                    )
                )
            }

            // Dot
            Box(
                modifier = Modifier
                    .size(2.5.dp)
                    .clip(CircleShape)
                    .background(MobilePlayerTokens.InkTertiary)
            )

            // Subtitles chip
            val isSubtitleOff = currentSubtitleTrack.isBlank() || currentSubtitleTrack.equals("Off", ignoreCase = true)
            Row(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onOpenSubtitles
                    )
                    .padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    painter = painterResource(if (isSubtitleOff) R.drawable.ic_huge_captions_off else R.drawable.ic_huge_captions),
                    contentDescription = null,
                    tint = MobilePlayerTokens.InkSecondary,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = if (isSubtitleOff) "Subtitles Off" else "Subtitles: $currentSubtitleTrack",
                    color = MobilePlayerTokens.InkSecondary,
                    fontSize = 12.5.sp,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = MobilePlayerTokens.TextShadow
                    )
                )
            }

            // Dot
            Box(
                modifier = Modifier
                    .size(2.5.dp)
                    .clip(CircleShape)
                    .background(MobilePlayerTokens.InkTertiary)
            )

            // Playback Speed chip
            Row(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onOpenSpeed
                    )
                    .padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_huge_speedometer),
                    contentDescription = null,
                    tint = MobilePlayerTokens.InkSecondary,
                    modifier = Modifier.size(15.dp)
                )
                val speedLabel = if (currentPlaybackSpeed == 1f) "1x" else "${currentPlaybackSpeed}x"
                Text(
                    text = "$speedLabel Speed",
                    color = MobilePlayerTokens.InkSecondary,
                    fontSize = 12.5.sp,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = MobilePlayerTokens.TextShadow
                    )
                )
            }
        }
    }
}

/**
 * Compact Icon button with touch ripple and subtle press transform.
 */
@Composable
fun MobileIconButton(
    @DrawableRes iconRes: Int,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: androidx.compose.ui.unit.Dp = 22.dp,
    tint: Color = MobilePlayerTokens.InkPrimary
) {
    MobileIconButton(
        painter = painterResource(iconRes),
        contentDescription = contentDescription,
        onClick = onClick,
        modifier = modifier,
        size = size,
        tint = tint
    )
}

@Composable
fun MobileIconButton(
    painter: Painter,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: androidx.compose.ui.unit.Dp = 22.dp,
    tint: Color = MobilePlayerTokens.InkPrimary
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.88f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "icon_btn_scale"
    )

    Box(
        modifier = modifier
            .size(38.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(if (isPressed) Color.White.copy(alpha = 0.16f) else Color.Transparent)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(size)
        )
    }
}

@Composable
fun MobileIconButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: androidx.compose.ui.unit.Dp = 22.dp,
    tint: Color = MobilePlayerTokens.InkPrimary
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.88f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "icon_btn_scale"
    )

    Box(
        modifier = modifier
            .size(38.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(if (isPressed) Color.White.copy(alpha = 0.16f) else Color.Transparent)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(size)
        )
    }
}

private fun Modifier.graphicsLayerAlpha(alphaValue: Float): Modifier = this.alpha(alphaValue)

private fun formatTime(ms: Long): String {
    val totalSeconds = (ms / 1000).coerceAtLeast(0)
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    val hours = minutes / 60
    return if (hours > 0) {
        String.format("%d:%02d:%02d", hours, minutes % 60, seconds)
    } else {
        String.format("%02d:%02d", minutes, seconds)
    }
}
