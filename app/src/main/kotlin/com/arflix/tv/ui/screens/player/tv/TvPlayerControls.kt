package com.arflix.tv.ui.screens.player.tv

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.AspectRatio
import androidx.compose.material.icons.filled.ClosedCaption
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.arflix.tv.R
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.screens.player.PlayerUiState
import com.arflix.tv.ui.skin.LocalAccentColorOverride
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextPrimary

import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import com.arflix.tv.ui.screens.player.preview.ReadySeekPreview
import com.arflix.tv.ui.screens.player.preview.SeekPreviewFrame
import kotlin.math.roundToInt

@Composable
fun TvPlayerControls(
    isVisible: Boolean,
    uiState: PlayerUiState,
    isPlaying: Boolean,
    isBuffering: Boolean,
    currentPositionMs: Long,
    durationMs: Long,
    progress: Float,
    isScrubbing: Boolean,
    scrubPreviewPositionMs: Long,
    clockFormat: String,
    aspectModeLabel: String,
    mediaType: MediaType,
    seasonNumber: Int?,
    episodeNumber: Int?,
    playerAccent: Color,
    seekPreviewFrame: SeekPreviewFrame? = null,
    formatTime: (Long) -> String,
    formatClockTime: (Long, String) -> String,
    // Focus Requesters
    playButtonFocusRequester: FocusRequester,
    rewindButtonFocusRequester: FocusRequester,
    forwardButtonFocusRequester: FocusRequester,
    subtitleButtonFocusRequester: FocusRequester,
    subtitleSettingsBtnFocusRequester: FocusRequester,
    sourceButtonFocusRequester: FocusRequester,
    aspectButtonFocusRequester: FocusRequester,
    nextEpisodeButtonFocusRequester: FocusRequester,
    trackbarFocusRequester: FocusRequester,
    skipIntroFocusRequester: FocusRequester,
    // Callbacks
    onTogglePlayPause: () -> Unit,
    onRewind10: () -> Unit,
    onForward10: () -> Unit,
    onCycleAspectRatio: () -> Unit,
    onOpenSubtitlesMenu: () -> Unit,
    onToggleSubtitleSettings: () -> Unit,
    onOpenSourceMenu: () -> Unit,
    onPlayNextEpisode: () -> Unit,
    onScrubSeekDelta: (Long) -> Unit,
    onCommitScrub: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(androidx.compose.animation.core.tween(150)),
        exit = fadeOut(androidx.compose.animation.core.tween(200)),
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Top info & Clock
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
                    .padding(start = 28.dp, top = 30.dp, end = 48.dp)
                    .zIndex(4f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                val isPaused = !isPlaying && !isBuffering

                TvPlayerMetadataChrome(
                    uiState = uiState,
                    mediaType = mediaType,
                    seasonNumber = seasonNumber,
                    episodeNumber = episodeNumber,
                    isPaused = isPaused,
                    accentColor = playerAccent,
                    modifier = Modifier.weight(1f, fill = false)
                )

                // Right side - Clock & Ends At Time
                Column(horizontalAlignment = Alignment.End) {
                    val currentTime = remember { mutableStateOf("") }
                    val endsAtTime = remember { mutableStateOf("") }
                    LaunchedEffect(durationMs, currentPositionMs, clockFormat) {
                        while (true) {
                            val now = System.currentTimeMillis()
                            currentTime.value = formatClockTime(now, clockFormat)
                            if (durationMs > 0 && currentPositionMs >= 0) {
                                val remainingMs = (durationMs - currentPositionMs).coerceAtLeast(0L)
                                endsAtTime.value = formatClockTime(now + remainingMs, clockFormat)
                            } else {
                                endsAtTime.value = ""
                            }
                            kotlinx.coroutines.delay(1000)
                        }
                    }

                    if (endsAtTime.value.isNotBlank()) {
                        Text(
                            text = "${stringResource(R.string.ends_at)} ${endsAtTime.value}",
                            style = ArflixTypography.label.copy(fontSize = 13.sp),
                            color = Color.White.copy(alpha = 0.55f),
                            maxLines = 1,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                    }

                    Text(
                        text = currentTime.value,
                        style = ArflixTypography.label.copy(fontSize = 15.sp, fontWeight = FontWeight.Medium),
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 1
                    )
                }
            }

            // Bottom Controls Section (Icons + Trackbar)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0f to Color.Transparent,
                                0.3f to Color.Black.copy(alpha = 0.2f),
                                1.0f to Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
                    .padding(horizontal = 48.dp)
                    .padding(top = 24.dp, bottom = 24.dp)
            ) {
                // Icon buttons row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val smallBtn = 40.dp
                    val smallIcon = 20.dp
                    val midBtn = 46.dp
                    val midIcon = 24.dp
                    val bigBtn = 56.dp
                    val bigIcon = 32.dp
                    val gap = 12.dp
                    val wideGap = 20.dp

                    // Subtitles button
                    TvPlayerIconButton(
                        icon = Icons.Default.ClosedCaption,
                        contentDescription = "${stringResource(R.string.subtitles)} / ${stringResource(R.string.audio)}",
                        focusRequester = subtitleButtonFocusRequester,
                        size = smallBtn,
                        iconSize = smallIcon,
                        onClick = onOpenSubtitlesMenu,
                        onLeftKey = { if (mediaType == MediaType.TV) nextEpisodeButtonFocusRequester.requestFocus() else aspectButtonFocusRequester.requestFocus() },
                        onRightKey = { subtitleSettingsBtnFocusRequester.requestFocus() },
                        onDownKey = { trackbarFocusRequester.requestFocus() }
                    )

                    Spacer(modifier = Modifier.width(gap))

                    // Subtitle Settings button
                    TvPlayerIconButton(
                        icon = Icons.Default.Tune,
                        contentDescription = stringResource(R.string.subtitle_settings_title),
                        focusRequester = subtitleSettingsBtnFocusRequester,
                        size = smallBtn,
                        iconSize = smallIcon,
                        onClick = onToggleSubtitleSettings,
                        onLeftKey = { subtitleButtonFocusRequester.requestFocus() },
                        onRightKey = { sourceButtonFocusRequester.requestFocus() },
                        onDownKey = { trackbarFocusRequester.requestFocus() }
                    )

                    Spacer(modifier = Modifier.width(gap))

                    // Sources
                    TvPlayerIconButton(
                        icon = Icons.Default.Folder,
                        contentDescription = stringResource(R.string.sources),
                        focusRequester = sourceButtonFocusRequester,
                        size = smallBtn,
                        iconSize = smallIcon,
                        onClick = onOpenSourceMenu,
                        onLeftKey = { subtitleSettingsBtnFocusRequester.requestFocus() },
                        onRightKey = { rewindButtonFocusRequester.requestFocus() },
                        onDownKey = { trackbarFocusRequester.requestFocus() }
                    )

                    Spacer(modifier = Modifier.width(wideGap))

                    // Rewind 10s
                    TvPlayerIconButton(
                        icon = Icons.Default.Replay10,
                        contentDescription = stringResource(R.string.player_cd_rewind),
                        focusRequester = rewindButtonFocusRequester,
                        size = midBtn,
                        iconSize = midIcon,
                        onClick = onRewind10,
                        onLeftKey = { sourceButtonFocusRequester.requestFocus() },
                        onRightKey = { playButtonFocusRequester.requestFocus() },
                        onDownKey = { trackbarFocusRequester.requestFocus() }
                    )

                    Spacer(modifier = Modifier.width(gap))

                    // Play/Pause
                    TvPlayerIconButton(
                        icon = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) stringResource(R.string.player_cd_pause) else stringResource(R.string.play),
                        focusRequester = playButtonFocusRequester,
                        size = bigBtn,
                        iconSize = bigIcon,
                        onClick = onTogglePlayPause,
                        onLeftKey = { rewindButtonFocusRequester.requestFocus() },
                        onRightKey = { forwardButtonFocusRequester.requestFocus() },
                        onDownKey = { trackbarFocusRequester.requestFocus() },
                        onUpKey = {
                            val sv = uiState.activeSkipInterval != null && !uiState.skipIntervalDismissed
                            if (sv) skipIntroFocusRequester.requestFocus()
                        }
                    )

                    Spacer(modifier = Modifier.width(gap))

                    // Forward 10s
                    TvPlayerIconButton(
                        icon = Icons.Default.Forward10,
                        contentDescription = stringResource(R.string.player_cd_forward),
                        focusRequester = forwardButtonFocusRequester,
                        size = midBtn,
                        iconSize = midIcon,
                        onClick = onForward10,
                        onLeftKey = { playButtonFocusRequester.requestFocus() },
                        onRightKey = { aspectButtonFocusRequester.requestFocus() },
                        onDownKey = { trackbarFocusRequester.requestFocus() }
                    )

                    Spacer(modifier = Modifier.width(wideGap))

                    // Aspect Ratio
                    TvPlayerIconButton(
                        icon = Icons.Default.AspectRatio,
                        contentDescription = stringResource(R.string.player_cd_aspect, aspectModeLabel),
                        focusRequester = aspectButtonFocusRequester,
                        size = smallBtn,
                        iconSize = smallIcon,
                        onClick = onCycleAspectRatio,
                        onLeftKey = { forwardButtonFocusRequester.requestFocus() },
                        onRightKey = {
                            if (mediaType == MediaType.TV) nextEpisodeButtonFocusRequester.requestFocus()
                            else subtitleButtonFocusRequester.requestFocus()
                        },
                        onDownKey = { trackbarFocusRequester.requestFocus() }
                    )

                    if (mediaType == MediaType.TV) {
                        Spacer(modifier = Modifier.width(gap))
                        TvPlayerIconButton(
                            icon = Icons.Default.SkipNext,
                            contentDescription = stringResource(R.string.next_episode),
                            focusRequester = nextEpisodeButtonFocusRequester,
                            size = smallBtn,
                            iconSize = smallIcon,
                            onClick = onPlayNextEpisode,
                            onLeftKey = { aspectButtonFocusRequester.requestFocus() },
                            onRightKey = { subtitleButtonFocusRequester.requestFocus() },
                            onDownKey = { trackbarFocusRequester.requestFocus() }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Trackbar at the very bottom with time labels
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatTime(if (isScrubbing) scrubPreviewPositionMs else currentPositionMs),
                        style = ArflixTypography.label.copy(fontSize = 13.sp),
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 1,
                        modifier = Modifier.width(55.dp)
                    )

                    var trackbarFocused by remember { mutableStateOf(false) }
                    var trackbarWidthPx by remember { mutableIntStateOf(0) }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(20.dp)
                            .onSizeChanged { trackbarWidthPx = it.width }
                            .focusRequester(trackbarFocusRequester)
                            .onFocusChanged { state ->
                                trackbarFocused = state.isFocused
                                if (!state.isFocused && isScrubbing) onCommitScrub()
                            }
                            .focusable()
                            .onKeyEvent { event ->
                                if (event.type == KeyEventType.KeyDown && trackbarFocused) {
                                    when (event.key) {
                                        Key.DirectionLeft -> { onScrubSeekDelta(-10_000L); true }
                                        Key.DirectionRight -> { onScrubSeekDelta(10_000L); true }
                                        Key.Enter, Key.DirectionCenter -> { onCommitScrub(); true }
                                        Key.DirectionUp -> { playButtonFocusRequester.requestFocus(); true }
                                        Key.DirectionDown -> true
                                        else -> false
                                    }
                                } else false
                            }
                            .background(Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        val barHeight = if (trackbarFocused) 8.dp else 4.dp
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(barHeight)
                                .background(Color.White.copy(alpha = if (trackbarFocused) 0.25f else 0.15f), RoundedCornerShape(3.dp))
                        )
                        val frac = if (durationMs > 0) ((if (isScrubbing) scrubPreviewPositionMs else currentPositionMs).toFloat() / durationMs.toFloat()).coerceIn(0f, 1f) else progress
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(barHeight)
                                .align(Alignment.Center),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(frac)
                                    .fillMaxHeight()
                                    .background(
                                        if (trackbarFocused) playerAccent else playerAccent.copy(alpha = 0.8f),
                                        RoundedCornerShape(3.dp)
                                    )
                            )
                        }

                        val previewCardWidth = 200.dp
                        val previewCardHeight = previewCardWidth * 9f / 16f
                        val previewDensity = LocalDensity.current
                        val previewCardWidthPx = with(previewDensity) { previewCardWidth.toPx() }
                        val previewOffsetY = with(previewDensity) {
                            (10.dp - barHeight / 2f - 4.dp - previewCardHeight).roundToPx()
                        }
                        val previewX = if (trackbarWidthPx > 0) {
                            (frac * trackbarWidthPx - previewCardWidthPx / 2f)
                                .coerceIn(0f, (trackbarWidthPx - previewCardWidthPx).coerceAtLeast(0f))
                                .roundToInt()
                        } else 0

                        val currentPos = if (isScrubbing) scrubPreviewPositionMs else currentPositionMs
                        ReadySeekPreview(
                            frame = seekPreviewFrame,
                            positionMs = currentPos,
                            visible = (isScrubbing || trackbarFocused) && durationMs > 0L,
                            cornerRadius = 6.dp,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .offset { IntOffset(previewX, previewOffsetY) }
                                .zIndex(12f)
                                .width(previewCardWidth)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = formatTime(durationMs),
                        style = ArflixTypography.label.copy(fontSize = 13.sp),
                        color = Color.White.copy(alpha = 0.5f),
                        maxLines = 1,
                        modifier = Modifier.width(55.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TvPlayerIconButton(
    icon: ImageVector,
    contentDescription: String,
    focusRequester: FocusRequester,
    size: Dp = 32.dp,
    iconSize: Dp = 22.dp,
    onClick: () -> Unit,
    onFocusChanged: (Boolean) -> Unit = {},
    onLeftKey: () -> Unit = {},
    onRightKey: () -> Unit = {},
    onUpKey: () -> Unit = {},
    onDownKey: () -> Unit = {}
) {
    val btnAccent = LocalAccentColorOverride.current ?: Color.White
    var focused by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (focused) 1.15f else 1f, label = "iconScale")

    Box(
        modifier = Modifier
            .size(size)
            .focusRequester(focusRequester)
            .onFocusChanged { state ->
                focused = state.isFocused
                onFocusChanged(state.isFocused)
            }
            .focusable()
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.Enter, Key.DirectionCenter -> { onClick(); true }
                        Key.DirectionLeft -> { onLeftKey(); true }
                        Key.DirectionRight -> { onRightKey(); true }
                        Key.DirectionUp -> { onUpKey(); true }
                        Key.DirectionDown -> { onDownKey(); true }
                        else -> false
                    }
                } else false
            }
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) { onClick() }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(
                color = if (focused) btnAccent else Color.Transparent,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (focused) Color.Black else Color.White,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Composable
fun TvPlayerMetadataChrome(
    uiState: PlayerUiState,
    mediaType: MediaType,
    seasonNumber: Int?,
    episodeNumber: Int?,
    isPaused: Boolean,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    val displayTitle = when {
        mediaType == MediaType.TV && !uiState.episodeTitle.isNullOrBlank() -> uiState.episodeTitle
        else -> uiState.title
    }
    val metaLine = buildTvPlaybackBaseMetaLine(uiState, mediaType, seasonNumber, episodeNumber)
    val overview = uiState.overview?.trim().orEmpty()
    val logoHeight = 44.dp
    val logoWidth = 230.dp
    val chromeHeight = when {
        isPaused && overview.isNotBlank() -> 138.dp
        isPaused -> 104.dp
        else -> 86.dp
    }

    Row(
        modifier = modifier.widthIn(max = if (isPaused) 620.dp else 520.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 2.dp)
                .width(2.dp)
                .height(chromeHeight)
                .background(accentColor.copy(alpha = if (isPaused) 0.78f else 0.46f))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.widthIn(max = if (isPaused) 560.dp else 470.dp),
            verticalArrangement = Arrangement.spacedBy(if (isPaused) 5.dp else 4.dp)
        ) {
            if (!uiState.logoUrl.isNullOrBlank()) {
                AsyncImage(
                    model = uiState.logoUrl,
                    contentDescription = uiState.title,
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(logoWidth)
                        .height(logoHeight)
                )
            } else if (displayTitle.isNotBlank()) {
                Text(
                    text = displayTitle,
                    style = ArflixTypography.sectionTitle.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (!uiState.logoUrl.isNullOrBlank() && displayTitle.isNotBlank()) {
                Text(
                    text = displayTitle,
                    style = ArflixTypography.sectionTitle.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (metaLine.isNotBlank()) {
                Text(
                    text = metaLine,
                    style = ArflixTypography.caption.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = TextPrimary.copy(alpha = 0.78f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (isPaused && overview.isNotBlank()) {
                Text(
                    text = overview,
                    style = ArflixTypography.body.copy(fontSize = 13.sp),
                    color = TextPrimary.copy(alpha = 0.76f),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.widthIn(max = 540.dp)
                )
            }
        }
    }
}

@Composable
private fun buildTvPlaybackBaseMetaLine(
    uiState: PlayerUiState,
    mediaType: MediaType,
    seasonNumber: Int?,
    episodeNumber: Int?
): String {
    val parts = mutableListOf<String>()
    if (mediaType == MediaType.TV) {
        seasonNumber?.let { parts.add(stringResource(R.string.season, it)) }
        episodeNumber?.let { parts.add(stringResource(R.string.episode, it)) }
    } else {
        uiState.releaseYear?.trim()?.takeIf { it.isNotBlank() }?.let { parts.add(it) }
    }
    return parts.distinct().joinToString(" | ")
}
