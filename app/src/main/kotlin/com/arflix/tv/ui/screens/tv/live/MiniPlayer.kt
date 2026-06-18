@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.FitScreen
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.util.formatGenreName
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MiniPlayerRow(
    exoPlayer: ExoPlayer,
    channel: EnrichedChannel?,
    clockTickMillis: Long,
    nowNext: IptvNowNext?,
    favoriteSet: Set<String>,
    onFavoriteToggle: (String) -> Unit,
    onFullscreenClick: (() -> Unit)? = null,
    variantCount: Int = 1,
    onOpenVariants: (() -> Unit)? = null,
    compact: Boolean = false,
    modifier: Modifier = Modifier,
) {
    if (compact) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 6.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            VideoCard(
                exoPlayer = exoPlayer,
                channel = channel,
                compact = true,
                onFullscreenClick = onFullscreenClick,
                modifier = Modifier.fillMaxWidth(),
            )
            InfoColumn(
                channel = channel,
                clockTickMillis = clockTickMillis,
                nowNext = nowNext,
                isFavorite = channel?.id?.let { it in favoriteSet } == true,
                onFavoriteToggle = onFavoriteToggle,
                variantCount = variantCount,
                onOpenVariants = onOpenVariants,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    } else {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 14.dp, top = 6.dp, bottom = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Top,
        ) {
            VideoCard(
                exoPlayer = exoPlayer,
                channel = channel,
                onFullscreenClick = onFullscreenClick,
            )
            InfoColumn(
                channel = channel,
                clockTickMillis = clockTickMillis,
                nowNext = nowNext,
                isFavorite = channel?.id?.let { it in favoriteSet } == true,
                onFavoriteToggle = onFavoriteToggle,
                variantCount = variantCount,
                onOpenVariants = onOpenVariants,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun VideoCard(
    exoPlayer: ExoPlayer,
    channel: EnrichedChannel?,
    compact: Boolean = false,
    onFullscreenClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val deviceType = LocalDeviceType.current
    val isTouchDevice = deviceType.isTouchDevice()

    Box(
        modifier = modifier
            .then(
                if (compact) {
                    Modifier.aspectRatio(16f / 9f)
                } else {
                    Modifier.size(LiveDims.MiniPlayerWidth, LiveDims.MiniPlayerHeight)
                }
            )
            .clickable(enabled = isTouchDevice && onFullscreenClick != null) {
                onFullscreenClick?.invoke()
            }
            .clip(RoundedCornerShape(LiveDims.VideoRadius))
            .background(LiveColors.PanelDeep),
    ) {
        // Fallback brand gradient while video is loading or channel is null.
        if (channel == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(LiveColors.Panel, LiveColors.Bg),
                        )
                    ),
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(channel.brandBg, LiveColors.Bg),
                        )
                    ),
            )
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        this.player = exoPlayer
                        useController = false
                        setKeepContentOnPlayerReset(true)
                    }
                },
                update = { view ->
                    if (view.player !== exoPlayer) {
                        view.player = exoPlayer
                    }
                },
                modifier = Modifier.fillMaxSize(),
            )
            LiveBug(modifier = Modifier.align(Alignment.TopEnd).padding(10.dp))
        }

        if (isTouchDevice && onFullscreenClick != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onFullscreenClick.invoke() },
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp)
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.62f))
                    .clickable { onFullscreenClick.invoke() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Default.FitScreen,
                    contentDescription = stringResource(R.string.live_cd_fullscreen),
                    tint = Color.White,
                    modifier = Modifier.size(21.dp),
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun LiveBug(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "live-bug")
    val alpha by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0.55f,
        animationSpec = infiniteRepeatable(tween(900), RepeatMode.Reverse),
        label = "live-alpha",
    )
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xAA000000))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .alpha(alpha)
                .background(LiveColors.LiveRed, CircleShape),
        )
        Text(
            text = stringResource(R.string.live_badge_live),
            style = LiveType.Badge.copy(color = Color.White),
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun InfoColumn(
    channel: EnrichedChannel?,
    clockTickMillis: Long,
    nowNext: IptvNowNext?,
    isFavorite: Boolean,
    onFavoriteToggle: (String) -> Unit,
    variantCount: Int,
    onOpenVariants: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ChannelIdentityRow(channel = channel, variantCount = variantCount, onOpenVariants = onOpenVariants)
        NowCard(channel = channel, clockTickMillis = clockTickMillis, nowNext = nowNext)
        NextRow(nowNext = nowNext)
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ChannelIdentityRow(
    channel: EnrichedChannel?,
    variantCount: Int,
    onOpenVariants: (() -> Unit)?,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        if (channel != null) {
            ChannelLogo(channel = channel, size = 30.dp)
            Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = stringResource(R.string.live_label_ch, channel.number),
                        style = LiveType.SectionTag.copy(color = LiveColors.FgMute),
                    )
                    Text(
                        text = formatGenreName(channel.genre.name),
                        style = LiveType.SectionTag.copy(color = LiveColors.FgMute),
                    )
                }
                Text(
                    text = channel.name,
                    style = LiveType.ChannelName.copy(color = LiveColors.Fg),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    QualityBadge(channel.quality)
                    if (variantCount > 1) {
                        SourceBadge(variantCount, onOpenVariants)
                    }
                    channel.country?.takeIf { it != channel.lang }?.let { LangBadge(it) }
                    LangBadge(channel.lang)
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(LiveColors.Panel),
            )
            Text(
                text = "—",
                style = LiveType.ChannelName.copy(color = LiveColors.FgMute),
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SourceBadge(count: Int, onOpenVariants: (() -> Unit)?) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(LiveColors.Panel)
            .then(if (onOpenVariants != null) Modifier.clickable { onOpenVariants() } else Modifier)
            .padding(horizontal = 6.dp, vertical = 2.dp),
    ) {
        Text(stringResource(R.string.live_label_sources, count), style = LiveType.Badge.copy(color = LiveColors.Accent))
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun QualityBadge(q: Quality) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(LiveColors.Panel)
            .padding(horizontal = 6.dp, vertical = 2.dp),
    ) {
        Text(q.label, style = LiveType.Badge.copy(color = LiveColors.Fg))
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun LangBadge(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(LiveColors.Panel)
            .padding(horizontal = 6.dp, vertical = 2.dp),
    ) {
        Text(text.uppercase(), style = LiveType.Badge.copy(color = LiveColors.FgDim))
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun NowCard(channel: EnrichedChannel?, clockTickMillis: Long, nowNext: IptvNowNext?) {
    val now = nowNext?.now
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(LiveDims.CardRadius))
            .background(LiveColors.PanelRaised)
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(stringResource(R.string.live_badge_now), style = LiveType.SectionTag.copy(color = LiveColors.Accent))
            Text(
                text = formatTimeWindow(now),
                style = LiveType.TimeMono.copy(color = LiveColors.Fg),
            )
            Spacer(Modifier.weight(1f))
            val remaining = remainingLabel(now)
            if (remaining.isNotBlank()) {
                Text(
                    text = remaining,
                    style = LiveType.TimeMono.copy(color = LiveColors.Accent),
                )
            }
        }
        Text(
            text = now?.title ?: channel?.name ?: stringResource(R.string.live_empty_no_programme),
            style = LiveType.ProgramTitle.copy(color = LiveColors.Fg),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        if (!now?.description.isNullOrBlank()) {
            Text(
                text = now!!.description!!,
                style = LiveType.BodySynopsis.copy(color = LiveColors.FgDim),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
        val progress = progressOf(now?.takeIf { clockTickMillis >= 0L })
        if (progress != null) {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(3.dp).clip(RoundedCornerShape(2.dp)),
                color = LiveColors.Accent,
                trackColor = LiveColors.Panel,
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun NextRow(nowNext: IptvNowNext?) {
    val next = nowNext?.next ?: return
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
            style = LiveType.CellTitle.copy(color = LiveColors.FgDim),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )
    }
}

internal fun formatTimeWindow(p: IptvProgram?): String {
    if (p == null) return "—"
    return "${formatClock(p.startUtcMillis)} – ${formatClock(p.endUtcMillis)}"
}

internal fun formatClock(utcMillis: Long): String {
    val c = java.util.Calendar.getInstance()
    c.timeInMillis = utcMillis
    return "%02d:%02d".format(c.get(java.util.Calendar.HOUR_OF_DAY), c.get(java.util.Calendar.MINUTE))
}

internal fun remainingLabel(p: IptvProgram?): String {
    if (p == null) return ""
    val now = System.currentTimeMillis()
    if (now !in p.startUtcMillis..p.endUtcMillis) return ""
    val minsLeft = ((p.endUtcMillis - now) / 60_000L).coerceAtLeast(0L)
    return if (minsLeft >= 60) "${minsLeft / 60}h ${minsLeft % 60}m left" else "${minsLeft}m left"
}

internal fun progressOf(p: IptvProgram?): Float? {
    if (p == null) return null
    val span = (p.endUtcMillis - p.startUtcMillis).toFloat()
    if (span <= 0f) return null
    val done = (System.currentTimeMillis() - p.startUtcMillis).toFloat()
    return (done / span).coerceIn(0f, 1f)
}
