@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.tv.live

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import kotlinx.coroutines.delay
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private enum class GuideProgramState {
    PastPlayable,
    PastUnavailable,
    Live,
    Future,
}

private data class GuideProgramItem(
    val program: IptvProgram,
    val state: GuideProgramState,
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun FullscreenGuideOverlay(
    visible: Boolean,
    channel: EnrichedChannel?,
    guide: IptvNowNext?,
    selectedProgram: IptvProgram?,
    isTouchDevice: Boolean,
    onDismiss: () -> Unit,
    onProgramSelect: (IptvProgram?) -> Unit,
    onLeftClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    if (channel == null) return

    BackHandler(enabled = visible, onBack = onDismiss)

    var nowMillis by remember { mutableLongStateOf(System.currentTimeMillis()) }
    LaunchedEffect(Unit) {
        while (true) {
            nowMillis = System.currentTimeMillis()
            delay(30_000)
        }
    }

    val catchupSupported = remember(channel) { channel.supportsFullscreenCatchup() }
    val pastWindowStart = nowMillis - 48L * 60L * 60_000L
    val past = remember(guide, nowMillis, catchupSupported) {
        guide?.recent.orEmpty()
            .asSequence()
            .filter { it.endUtcMillis <= nowMillis && it.endUtcMillis >= pastWindowStart }
            .distinctBy { "${it.startUtcMillis}:${it.endUtcMillis}:${it.title}" }
            .sortedBy { it.startUtcMillis }
            .map {
                GuideProgramItem(
                    it,
                    if (catchupSupported) GuideProgramState.PastPlayable else GuideProgramState.PastUnavailable
                )
            }
            .toList()
    }
    val live = remember(guide, nowMillis) {
        guide?.now
            ?.takeIf { it.isLive(nowMillis) }
            ?.let { listOf(GuideProgramItem(it, GuideProgramState.Live)) }
            .orEmpty()
    }
    val future = remember(guide, nowMillis) {
        buildList {
            guide?.next?.let { add(it) }
            guide?.later?.let { add(it) }
            addAll(guide?.upcoming.orEmpty())
        }
            .asSequence()
            .filter { it.startUtcMillis > nowMillis }
            .distinctBy { "${it.startUtcMillis}:${it.endUtcMillis}:${it.title}" }
            .sortedBy { it.startUtcMillis }
            .map { GuideProgramItem(it, GuideProgramState.Future) }
            .toList()
    }
    val items = remember(past, live, future) {
        (past + live + future)
            .distinctBy { "${it.program.startUtcMillis}:${it.program.endUtcMillis}:${it.program.title}" }
            .sortedBy { it.program.startUtcMillis }
    }
    val anchorIndex = remember(items, selectedProgram, nowMillis) {
        val selectedIndex = selectedProgram?.let { selected ->
            items.indexOfFirst {
                it.program.startUtcMillis == selected.startUtcMillis &&
                    it.program.endUtcMillis == selected.endUtcMillis &&
                    it.program.title == selected.title
            }
        } ?: -1
        val liveIndex = items.indexOfFirst { it.state == GuideProgramState.Live }
        if (selectedIndex >= 0) {
            selectedIndex
        } else if (liveIndex >= 0) {
            liveIndex
        } else {
            val nextIndex = items.indexOfFirst { it.program.startUtcMillis > nowMillis }
            when {
                nextIndex >= 0 -> nextIndex
                items.isNotEmpty() -> items.lastIndex
                else -> 0
            }
        }
    }

    val enter = if (isTouchDevice) {
        slideInVertically(tween(260, easing = FastOutSlowInEasing)) { it } + fadeIn(tween(180))
    } else {
        slideInHorizontally(tween(260, easing = FastOutSlowInEasing)) { it } + fadeIn(tween(180))
    }
    val exit = if (isTouchDevice) {
        slideOutVertically(tween(220, easing = FastOutSlowInEasing)) { it } + fadeOut(tween(160))
    } else {
        slideOutHorizontally(tween(220, easing = FastOutSlowInEasing)) { it } + fadeOut(tween(160))
    }

    AnimatedVisibility(
        visible = visible,
        enter = enter,
        exit = exit,
        modifier = modifier.fillMaxSize(),
    ) {
        val panelShape = if (isTouchDevice) {
            RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp, bottomStart = 14.dp, bottomEnd = 14.dp)
        } else {
            RoundedCornerShape(18.dp)
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = if (isTouchDevice) 0.52f else 0.42f))
                    .clickable(
                        interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                        indication = null,
                        onClick = onDismiss,
                    ),
            )
            Box(
                modifier = Modifier
                    .align(if (isTouchDevice) Alignment.BottomCenter else Alignment.CenterEnd)
                    .then(
                        if (isTouchDevice) {
                            Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.82f)
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                        } else {
                            Modifier
                                .width(520.dp)
                                .fillMaxHeight()
                                .padding(top = 24.dp, end = 24.dp, bottom = 24.dp)
                        }
                    )
                    .onPreviewKeyEvent { ev ->
                        if (ev.type == KeyEventType.KeyDown && ev.key == Key.DirectionLeft) {
                            onLeftClick?.invoke()
                            true
                        } else {
                            false
                        }
                    }
                    .clip(panelShape)
                    .border(
                        BorderStroke(1.dp, Color.White.copy(alpha = if (isTouchDevice) 0.16f else 0.12f)),
                        panelShape
                    )
                    .background(
                        Brush.verticalGradient(
                            0f to if (isTouchDevice) Color(0xF520222B) else Color(0xF2191B22),
                            1f to Color(0xF0090A0E),
                        )
                    )
                    .padding(if (isTouchDevice) 14.dp else 18.dp),
            ) {
                FullscreenGuideContent(
                    channel = channel,
                    selectedProgram = selectedProgram,
                    pastCount = past.size,
                    liveCount = live.size,
                    futureCount = future.size,
                    items = items,
                    anchorIndex = anchorIndex,
                    catchupSupported = catchupSupported,
                    nowMillis = nowMillis,
                    isTouchDevice = isTouchDevice,
                    onDismiss = onDismiss,
                    onProgramSelect = onProgramSelect,
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun FullscreenGuideContent(
    channel: EnrichedChannel,
    selectedProgram: IptvProgram?,
    pastCount: Int,
    liveCount: Int,
    futureCount: Int,
    items: List<GuideProgramItem>,
    anchorIndex: Int,
    catchupSupported: Boolean,
    nowMillis: Long,
    isTouchDevice: Boolean,
    onDismiss: () -> Unit,
    onProgramSelect: (IptvProgram?) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(if (isTouchDevice) 10.dp else 14.dp),
    ) {
        if (isTouchDevice) {
            GuideSheetHandle()
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            if (isTouchDevice) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.08f))
                        .clickable(onClick = onDismiss),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.live_cd_close_guide),
                        tint = LiveColors.Fg,
                        modifier = Modifier.size(21.dp),
                    )
                }
            }
            ChannelLogo(channel = channel, size = if (isTouchDevice) 40.dp else 48.dp)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = channel.name,
                    style = LiveType.ChannelName.copy(
                        color = LiveColors.Fg,
                        fontSize = if (isTouchDevice) 17.sp else 19.sp,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(if (isTouchDevice) 5.dp else 6.dp)) {
                    GuideChip(stringResource(R.string.live_label_ch, channel.number), LiveColors.FgDim, Color.White.copy(alpha = 0.08f))
                    GuideChip(channel.quality.label, LiveColors.FgDim, Color.White.copy(alpha = 0.08f))
                    if (catchupSupported) {
                        GuideChip(stringResource(R.string.live_label_catchup), LiveColors.Bg, LiveColors.Accent)
                    }
                }
            }
        }

        GuideTimelineSummary(
            pastCount = pastCount,
            liveCount = liveCount,
            futureCount = futureCount,
            catchupSupported = catchupSupported,
            isTouchDevice = isTouchDevice,
        )

        val listState = rememberLazyListState()
        val anchorFocusRequester = remember { FocusRequester() }
        LaunchedEffect(channel.id, items.size, anchorIndex) {
            if (items.isNotEmpty()) {
                listState.scrollToItem((anchorIndex - 1).coerceAtLeast(0))
                delay(90)
                runCatching { anchorFocusRequester.requestFocus() }
            }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 8.dp),
        ) {
            if (items.isEmpty()) {
                item {
                    GuideEmptyState(
                        catchupSupported = catchupSupported,
                        isTouchDevice = isTouchDevice,
                    )
                }
            } else {
                itemsIndexed(
                    items = items,
                    key = { index, item -> "${item.state}:${item.program.startUtcMillis}:${item.program.title}:$index" },
                ) { index, item ->
                    GuideProgramRow(
                        item = item,
                        selected = when (item.state) {
                            GuideProgramState.Live -> selectedProgram == null
                            else -> item.program == selectedProgram
                        },
                        nowMillis = nowMillis,
                        focusRequester = if (index == anchorIndex) anchorFocusRequester else null,
                        isTouchDevice = isTouchDevice,
                        onClick = {
                            when (item.state) {
                                GuideProgramState.PastPlayable -> onProgramSelect(item.program)
                                GuideProgramState.PastUnavailable -> Unit
                                GuideProgramState.Live -> onProgramSelect(null)
                                GuideProgramState.Future -> Unit
                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun GuideSheetHandle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Spacer(
            modifier = Modifier
                .width(42.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(Color.White.copy(alpha = 0.24f))
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GuideTimelineSummary(
    pastCount: Int,
    liveCount: Int,
    futureCount: Int,
    catchupSupported: Boolean,
    isTouchDevice: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White.copy(alpha = if (isTouchDevice) 0.055f else 0.045f))
            .border(BorderStroke(1.dp, Color.White.copy(alpha = 0.08f)), RoundedCornerShape(14.dp))
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(if (isTouchDevice) 5.dp else 7.dp),
    ) {
        GuideTimelinePill(
            label = stringResource(R.string.live_label_aired),
            value = pastCount.toString(),
            accent = if (catchupSupported) LiveColors.Accent else LiveColors.FgMute,
            modifier = Modifier.weight(1f),
        )
        GuideTimelinePill(
            label = stringResource(R.string.live),
            value = liveCount.coerceAtMost(1).toString(),
            accent = LiveColors.LiveRed,
            modifier = Modifier.weight(1f),
        )
        GuideTimelinePill(
            label = stringResource(R.string.later),
            value = futureCount.toString(),
            accent = LiveColors.FgDim,
            modifier = Modifier.weight(1f),
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GuideTimelinePill(
    label: String,
    value: String,
    accent: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(accent.copy(alpha = 0.13f))
            .padding(horizontal = 8.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = label,
            style = LiveType.Badge.copy(color = LiveColors.Fg, fontSize = 10.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = value,
            style = LiveType.Badge.copy(color = accent, fontSize = 10.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GuideProgramRow(
    item: GuideProgramItem,
    selected: Boolean,
    nowMillis: Long,
    focusRequester: FocusRequester?,
    isTouchDevice: Boolean,
    onClick: () -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    val playable = item.state == GuideProgramState.PastPlayable || item.state == GuideProgramState.Live
    val active = focused || selected
    val bg = when {
        active -> LiveColors.PanelRaised.copy(alpha = 0.94f)
        item.state == GuideProgramState.Future -> Color.White.copy(alpha = 0.045f)
        else -> Color.White.copy(alpha = 0.07f)
    }
    val border = when {
        selected -> LiveColors.Accent
        focused -> LiveColors.Fg.copy(alpha = 0.42f)
        else -> Color.White.copy(alpha = 0.08f)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier)
            .onFocusChanged { focused = it.hasFocus }
            .focusable()
            .onKeyEvent { ev ->
                if (ev.type == KeyEventType.KeyDown && (ev.key == Key.DirectionCenter || ev.key == Key.Enter)) {
                    if (playable) onClick()
                    true
                } else {
                    false
                }
            }
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .border(BorderStroke(1.dp, border), RoundedCornerShape(12.dp))
            .clickable(enabled = playable, onClick = onClick)
            .heightIn(min = if (isTouchDevice) 70.dp else 0.dp)
            .padding(if (isTouchDevice) 10.dp else 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(if (isTouchDevice) 10.dp else 12.dp),
    ) {
        Column(
            modifier = Modifier
                .width(if (isTouchDevice) 70.dp else 84.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            Text(
                text = timelineDateLabel(item.program, nowMillis),
                style = LiveType.Badge.copy(
                    color = when (item.state) {
                        GuideProgramState.PastPlayable -> LiveColors.Accent
                        GuideProgramState.PastUnavailable -> LiveColors.FgDim
                        GuideProgramState.Live -> LiveColors.LiveRed
                        GuideProgramState.Future -> LiveColors.FgDim
                    },
                    fontSize = if (isTouchDevice) 9.sp else 10.sp,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = formatTimeWindow(item.program),
                style = LiveType.TimeMono.copy(color = LiveColors.FgDim, fontSize = if (isTouchDevice) 8.sp else 9.sp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Box(
            modifier = Modifier
                .size(if (isTouchDevice) 34.dp else 38.dp)
                .clip(CircleShape)
                .background(
                    when (item.state) {
                        GuideProgramState.PastPlayable -> LiveColors.Accent.copy(alpha = 0.18f)
                        GuideProgramState.PastUnavailable -> Color.White.copy(alpha = 0.06f)
                        GuideProgramState.Live -> LiveColors.LiveRed.copy(alpha = 0.22f)
                        GuideProgramState.Future -> Color.White.copy(alpha = 0.08f)
                    }
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = when (item.state) {
                    GuideProgramState.PastPlayable -> Icons.Default.Replay
                    GuideProgramState.PastUnavailable -> Icons.Default.Replay
                    GuideProgramState.Live -> Icons.Default.PlayArrow
                    GuideProgramState.Future -> Icons.Default.Schedule
                },
                contentDescription = null,
                tint = when (item.state) {
                    GuideProgramState.PastPlayable -> LiveColors.Accent
                    GuideProgramState.PastUnavailable -> LiveColors.FgDim
                    GuideProgramState.Live -> LiveColors.LiveRed
                    GuideProgramState.Future -> LiveColors.FgDim
                },
                modifier = Modifier.size(if (isTouchDevice) 18.dp else 20.dp),
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                GuideChip(
                    label = when (item.state) {
                        GuideProgramState.PastPlayable -> stringResource(R.string.live_badge_aired)
                        GuideProgramState.PastUnavailable -> stringResource(R.string.live_badge_aired)
                        GuideProgramState.Live -> stringResource(R.string.live_badge_live)
                        GuideProgramState.Future -> startsLabel(item.program, nowMillis)
                    },
                    fg = when (item.state) {
                        GuideProgramState.PastPlayable -> LiveColors.Bg
                        GuideProgramState.PastUnavailable -> LiveColors.FgDim
                        GuideProgramState.Live -> Color.White
                        GuideProgramState.Future -> LiveColors.FgDim
                    },
                    bg = when (item.state) {
                        GuideProgramState.PastPlayable -> LiveColors.Accent
                        GuideProgramState.PastUnavailable -> Color.White.copy(alpha = 0.08f)
                        GuideProgramState.Live -> LiveColors.LiveRed
                        GuideProgramState.Future -> Color.White.copy(alpha = 0.08f)
                    },
                )
            }
            Text(
                text = item.program.title,
                style = LiveType.ProgramTitle.copy(
                    color = if (item.state == GuideProgramState.Future || item.state == GuideProgramState.PastUnavailable) {
                        LiveColors.FgDim
                    } else {
                        LiveColors.Fg
                    },
                    fontSize = if (isTouchDevice) 14.sp else 15.sp,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (!item.program.description.isNullOrBlank()) {
                Text(
                    text = item.program.description!!,
                    style = LiveType.BodySynopsis.copy(color = LiveColors.FgMute, fontSize = if (isTouchDevice) 9.sp else 10.sp),
                    maxLines = if (isTouchDevice) 1 else 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            if (item.state == GuideProgramState.Live) {
                progressOf(item.program)?.let { progress ->
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
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GuideEmptyState(
    catchupSupported: Boolean,
    isTouchDevice: Boolean,
) {
    val text = when {
        !catchupSupported ->
            stringResource(R.string.live_empty_no_timeline_channel)
        else ->
            stringResource(R.string.live_empty_no_timeline_yet)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isTouchDevice) 118.dp else 150.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White.copy(alpha = 0.055f))
            .border(BorderStroke(1.dp, Color.White.copy(alpha = 0.08f)), RoundedCornerShape(14.dp))
            .padding(if (isTouchDevice) 14.dp else 18.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = LiveType.BodySynopsis.copy(color = LiveColors.FgDim, fontSize = if (isTouchDevice) 12.sp else 13.sp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GuideChip(label: String, fg: Color, bg: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(bg)
            .padding(horizontal = 6.dp, vertical = 2.dp),
    ) {
        Text(label, style = LiveType.Badge.copy(color = fg, fontSize = 9.sp), maxLines = 1)
    }
}

private val timelineDateFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("EEE d MMM", Locale.getDefault())

@Composable
private fun timelineDateLabel(program: IptvProgram, nowMillis: Long): String {
    val zone = ZoneId.systemDefault()
    val today = Instant.ofEpochMilli(nowMillis).atZone(zone).toLocalDate()
    val programDate = Instant.ofEpochMilli(program.startUtcMillis).atZone(zone).toLocalDate()
    return when (programDate) {
        today.minusDays(1) -> stringResource(R.string.live_label_yesterday)
        today -> stringResource(R.string.live_label_today)
        today.plusDays(1) -> stringResource(R.string.live_label_tomorrow)
        else -> timelineDateFormatter.format(programDate)
    }
}

private fun EnrichedChannel.supportsFullscreenCatchup(): Boolean {
    val channelSource = this.source
    if (channelSource.catchupDays > 0) return true
    if (!channelSource.catchupType.isNullOrBlank() || !channelSource.catchupSource.isNullOrBlank()) return true
    return channelSource.streamUrl.contains("/timeshift/", ignoreCase = true)
        || channelSource.xtreamStreamId != null
        || channelSource.streamUrl.contains("/live/", ignoreCase = true)
}

@Composable
private fun startsLabel(program: IptvProgram, nowMillis: Long): String {
    val minutes = ((program.startUtcMillis - nowMillis) / 60_000L).coerceAtLeast(0L)
    return when {
        minutes < 60 -> stringResource(R.string.live_label_starts_in_min, minutes.toInt())
        minutes < 24 * 60 -> stringResource(R.string.live_label_starts_in_hm, (minutes / 60).toInt(), (minutes % 60).toInt())
        else -> stringResource(R.string.live_label_starts_later)
    }
}
