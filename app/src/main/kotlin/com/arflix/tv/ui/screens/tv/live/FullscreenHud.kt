@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvNowNext
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.delay

/**
 * Fullscreen playback HUD. Auto-hides 5s after the last `pokeSignal`
 * bump; parent bumps the counter on any DPAD key so the HUD re-surfaces.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun FullscreenHud(
    channel: EnrichedChannel?,
    nowNext: IptvNowNext?,
    pokeSignal: Int,
    isCatchupMode: Boolean = false,
    isPlaying: Boolean = true,
    playbackPositionMs: Long = 0L,
    playbackDurationMs: Long = 0L,
    onBackClick: (() -> Unit)? = null,
    onGuideClick: (() -> Unit)? = null,
    onPlayPauseClick: (() -> Unit)? = null,
    onGoLiveClick: (() -> Unit)? = null,
    onVisibilityChanged: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(true) }
    var lastPoke by remember { mutableLongStateOf(System.currentTimeMillis()) }

    androidx.compose.runtime.DisposableEffect(onVisibilityChanged) {
        onDispose {
            onVisibilityChanged?.invoke(false)
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
            delay(30_000)
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(200)),
        exit = fadeOut(tween(200)),
        modifier = modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            0f to Color.Transparent,
                            0.55f to Color.Transparent,
                            1f to Color(0xCC000000),
                        )
                    ),
            )

            if (channel != null) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = if (onBackClick != null) 80.dp else 20.dp, top = 20.dp, end = 20.dp, bottom = 20.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0x66000000))
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    ChannelLogo(channel = channel, size = 40.dp)
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = stringResource(R.string.live_label_ch, channel.number),
                            style = LiveType.SectionTag.copy(color = LiveColors.FgMute),
                        )
                        Text(
                            text = channel.name,
                            style = LiveType.ChannelName.copy(
                                color = LiveColors.Fg,
                                fontSize = 16.sp,
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            HudBadge(channel.quality.label, LiveColors.Fg, LiveColors.Panel)
                            channel.country?.takeIf { it != channel.lang }?.let {
                                HudBadge(it.uppercase(), LiveColors.FgDim, LiveColors.Panel)
                            }
                            HudBadge(channel.lang.uppercase(), LiveColors.FgDim, LiveColors.Panel)
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0x66000000))
                    .padding(horizontal = 14.dp, vertical = 10.dp),
            ) {
                Text(
                    text = formatClock(clockMillis),
                    style = LiveType.TimeMono.copy(
                        color = LiveColors.Fg,
                        fontSize = 18.sp,
                    ),
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(0.6f)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(LiveDims.CardRadius))
                    .background(LiveColors.PanelRaised.copy(alpha = 0.85f))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            ) {
                val now = nowNext?.now
                val next = nowNext?.next
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(
                            text = if (isCatchupMode) stringResource(R.string.live_badge_catchup) else stringResource(R.string.live_badge_now),
                            style = LiveType.SectionTag.copy(color = LiveColors.Accent),
                        )
                        Text(
                            text = formatTimeWindow(now),
                            style = LiveType.TimeMono.copy(color = LiveColors.Fg),
                        )
                        Spacer(Modifier.weight(1f))
                        val positionLabel = if (isCatchupMode && playbackDurationMs > 0L) {
                            "${formatPlaybackDuration(playbackPositionMs)} / ${formatPlaybackDuration(playbackDurationMs)}"
                        } else {
                            remainingLabel(now)
                        }
                        if (positionLabel.isNotBlank()) {
                            Text(
                                text = positionLabel,
                                style = LiveType.TimeMono.copy(color = LiveColors.Accent),
                            )
                        }
                        if (onGuideClick != null) {
                            HudActionButton(stringResource(R.string.live_btn_guide), onGuideClick)
                        }
                    }
                    Text(
                        text = now?.title
                            ?: channel?.name
                            ?: stringResource(R.string.live_empty_no_programme),
                        style = LiveType.ProgramTitle.copy(
                            color = LiveColors.Fg,
                            fontSize = 18.sp,
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    if (!now?.description.isNullOrBlank()) {
                        Text(
                            text = now!!.description!!,
                            style = LiveType.BodySynopsis.copy(
                                color = LiveColors.FgDim,
                                fontSize = 12.sp,
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    val progress = if (isCatchupMode && playbackDurationMs > 0L) {
                        (playbackPositionMs.toFloat() / playbackDurationMs.toFloat()).coerceIn(0f, 1f)
                    } else {
                        progressOf(now)
                    }
                    if (progress != null) {
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(3.dp)
                                .clip(RoundedCornerShape(2.dp)),
                            color = LiveColors.Accent,
                            trackColor = LiveColors.Panel,
                        )
                    }
                    if (isCatchupMode) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            HudIconButton(
                                icon = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                contentDescription = if (isPlaying) stringResource(R.string.live_cd_pause) else stringResource(R.string.play),
                                emphasis = true,
                                onClick = { onPlayPauseClick?.invoke() },
                            )
                            Spacer(Modifier.width(14.dp))
                            HudActionButton(stringResource(R.string.live_badge_live), onClick = { onGoLiveClick?.invoke() })
                        }
                    } else if (next != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(LiveColors.Divider),
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            Text(stringResource(R.string.live_badge_next), style = LiveType.SectionTag.copy(color = LiveColors.FgMute))
                            Text(
                                text = formatClock(next.startUtcMillis),
                                style = LiveType.TimeMono.copy(color = LiveColors.FgDim),
                            )
                            Text(
                                text = next.title,
                                style = LiveType.CellTitle.copy(
                                    color = LiveColors.FgDim,
                                    fontSize = 12.sp,
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
            }
            if (onBackClick != null) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(20.dp)
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.62f))
                        .clickable { onBackClick() },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = Color.White,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun HudIconButton(
    icon: ImageVector,
    contentDescription: String,
    emphasis: Boolean = false,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(if (emphasis) 42.dp else 36.dp)
            .clip(CircleShape)
            .background(if (emphasis) LiveColors.Accent else LiveColors.Panel)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (emphasis) LiveColors.Bg else LiveColors.Fg,
            modifier = Modifier.size(if (emphasis) 24.dp else 20.dp),
        )
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
private fun HudBadge(label: String, fg: Color, bg: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(bg)
            .padding(horizontal = 6.dp, vertical = 2.dp),
    ) {
        Text(label, style = LiveType.Badge.copy(color = fg, fontSize = 10.sp))
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun HudActionButton(label: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(LiveColors.Accent)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 5.dp),
    ) {
        Text(
            text = label,
            style = LiveType.Badge.copy(color = LiveColors.Bg, fontSize = 10.sp),
        )
    }
}
