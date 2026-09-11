package com.arflix.tv.ui.screens.tv.live

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Restore
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import kotlinx.coroutines.delay
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

private const val EpgPastWindowMinutes = 2 * 60
// Past 48h + future 48h: the guide shows a full ±48h span so users can scroll back
// for catch-up and forward to plan. The SQLite guide index keeps a wider window
// (past 48h / future 96h) so this data is already available without a refetch.
private const val EpgFutureWindowMinutes = 10 * 60
private const val CompactEpgPastWindowMinutes = 90
private const val CompactEpgFutureWindowMinutes = 6 * 60
private const val ChannelWindowPrefetchThreshold = 10

enum class EpgGridFocusMode {
    ChannelList,
    Epg,
}

/**
 * EPG grid per spec §3.4.
 * Window: (now - 1h rounded to :30) → +9h = 10h wide.
 * Constants: 5dp/min, 150dp per 30min, rows 84dp tall.
 * Scroll sync: header ↔ body (horizontal) + channel column ↔ body (vertical).
 */
@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EpgGrid(
    channels: List<EnrichedChannel>,
    channelWindowOffset: Int = 0,
    totalChannelCount: Int = channels.size,
    clockTickMillis: Long,
    nowNext: Map<String, IptvNowNext>,
    epgLoadingChannelIds: Set<String> = emptySet(),
    epgAttemptedChannelIds: Set<String> = emptySet(),
    isGuideBackfillLoading: Boolean = false,
    hasGuideSource: Boolean = true,
    selectedChannelId: String?,
    playingChannelId: String? = null,
    focusSelectedChannelSignal: Int,
    focusEpgSignal: Int = 0,
    focusMode: EpgGridFocusMode = EpgGridFocusMode.ChannelList,
    scrollResetKey: String = "",
    onChannelSelect: (EnrichedChannel) -> Unit,
    onProgramSelect: (EnrichedChannel, IptvProgram?) -> Unit = { channel, _ -> onChannelSelect(channel) },
    onChannelFocused: (EnrichedChannel) -> Unit = {},
    onProgramFocused: (EnrichedChannel, IptvProgram) -> Unit = { _, _ -> },
    /** Long-press / MENU on a channel row — opens the channel menu. */
    onChannelLongPress: (EnrichedChannel, Boolean) -> Unit = { _, _ -> },
    favorites: Set<String>,
    variantCountFor: (EnrichedChannel) -> Int = { 1 },
    compact: Boolean = false,
    gridFocused: Boolean = false,
    backHandlingEnabled: Boolean = true,
    onMoveLeftFromChannels: () -> Unit = {},
    onEnterEpg: (EnrichedChannel) -> Unit = {},
    onExitEpg: (EnrichedChannel?) -> Unit = {},
    onRequestPreviousChannels: () -> Unit = {},
    onRequestNextChannels: () -> Unit = {},
    onVisibleChannelRange: (Int, Int) -> Unit = { _, _ -> },
    channelColumnWidthOverride: Dp? = null,
    playbackQuality: LivePlaybackQuality? = null,
    categoryTitle: String = "All channels",
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val pxPerMin = if (compact) 96f / 30f else LiveDims.EpgPxPerMinute.toFloat()
    val selectedChannelFocusRequester = remember { FocusRequester() }
    val firstChannelFocusRequester = remember { FocusRequester() }
    val headerHeight = if (compact) 32.dp else LiveDims.EpgHeaderHeight
    val channelColumnWidth = channelColumnWidthOverride
        ?: if (compact) 164.dp else LiveDims.EpgChannelColWidth
    val halfHourWidth = (pxPerMin * 30f).dp
    val rowHeight = if (compact) 52.dp else LiveDims.EpgRowHeight
    val channelFocusRequesters = remember { LinkedHashMap<String, FocusRequester>() }
    val programFocusRequesters = remember { LinkedHashMap<String, List<FocusRequester>>() }
    val programFocusTargets = remember { LinkedHashMap<String, List<ProgramFocusTarget>>() }
    val channelIndexById = remember(channels) {
        HashMap<String, Int>(channels.size).apply {
            channels.forEachIndexed { index, channel -> put(channel.id, index) }
        }
    }
    val channelWindowIdentity = remember(channels) {
        listOf(
            channels.size.toString(),
            channels.firstOrNull()?.id.orEmpty(),
            channels.lastOrNull()?.id.orEmpty(),
        ).joinToString("|")
    }
    val selectedChannel = selectedChannelId?.let { id -> channelIndexById[id]?.let { index -> channels.getOrNull(index) } }
    val safeTotalChannelCount = totalChannelCount.coerceAtLeast(channels.size)
    fun requestMoreRowsIfNeeded(rowIdx: Int) {
        if (rowIdx <= ChannelWindowPrefetchThreshold && channelWindowOffset > 0) {
            onRequestPreviousChannels()
        }
        val absoluteAfter = channelWindowOffset + rowIdx
        if (
            channels.isNotEmpty() &&
            channels.lastIndex - rowIdx <= ChannelWindowPrefetchThreshold &&
            absoluteAfter < safeTotalChannelCount - 1
        ) {
            onRequestNextChannels()
        }
    }

    val pastWindowMinutes = if (compact) CompactEpgPastWindowMinutes else EpgPastWindowMinutes
    val futureWindowMinutes = if (compact) CompactEpgFutureWindowMinutes else EpgFutureWindowMinutes
    val windowStartMillis = remember(clockTickMillis, pastWindowMinutes) {
        roundedGuideWindowStart(clockTickMillis, pastWindowMinutes)
    }
    val windowEndMillis = remember(windowStartMillis, futureWindowMinutes) {
        windowStartMillis + (pastWindowMinutes + futureWindowMinutes) * 60L * 1000L
    }
    val slotCount = remember(windowStartMillis, windowEndMillis) {
        (((windowEndMillis - windowStartMillis) / 60_000L) / 30L).toInt().coerceAtLeast(1)
    }
    val slots = remember(windowStartMillis, slotCount) { buildHalfHourSlots(windowStartMillis, slotCount) }

    // Shared horizontal scroll state between header and body rows.
    val hScroll = rememberScrollState()
    // A single LazyListState handles vertical scrolling for both channels and EPG.
    val positions = rememberSaveable(saver = GuideListStatesSaver) { LinkedHashMap<String, LazyListState>() }
    val channelListState = remember(scrollResetKey) {
        val state = positions.remove(scrollResetKey) ?: LazyListState()
        positions[scrollResetKey] = state
        while (positions.size > 16) positions.remove(positions.keys.first())
        state
    }
    // A pending category briefly has no rows. Measuring the saved state against
    // an empty list would clamp its scroll position to zero before data arrives.
    val emptyChannelListState = remember { LazyListState() }
    var didPositionInitialSelection by rememberSaveable(scrollResetKey) { mutableStateOf(false) }
    var activeChannelFocusId by rememberSaveable(scrollResetKey) { mutableStateOf(selectedChannelId) }
    var activeChannelFocusIndex by rememberSaveable(scrollResetKey) { mutableIntStateOf(0) }
    var pendingChannelFocusId by remember(scrollResetKey) { mutableStateOf<String?>(null) }
    var focusJob by remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(scrollResetKey, channelWindowIdentity) {
        if (channels.isEmpty() || didPositionInitialSelection) return@LaunchedEffect
        val resolvedIdx = selectedChannelId?.let(channelIndexById::get)
        if (resolvedIdx != null) {
            val targetScroll = (resolvedIdx - 2).coerceAtLeast(0)
            channelListState.scrollToItem(targetScroll)
            activeChannelFocusId = selectedChannelId
            activeChannelFocusIndex = resolvedIdx
            pendingChannelFocusId = null
            didPositionInitialSelection = true
        } else if (channelListState.firstVisibleItemIndex == 0 && channelListState.firstVisibleItemScrollOffset == 0) {
            activeChannelFocusId = channels.firstOrNull()?.id
            activeChannelFocusIndex = 0
            pendingChannelFocusId = null
        }
    }

    val scope = rememberCoroutineScope()
    suspend fun revealRow(rowIdx: Int) {
        val layout = channelListState.layoutInfo
        val first = layout.visibleItemsInfo.firstOrNull() ?: return
        val row = layout.visibleItemsInfo.firstOrNull { it.index == rowIdx }
        // Rows have a fixed height. Reveal only the clipped portion instead of
        // restarting a long item-to-item spring on every remote repeat.
        val top = row?.offset ?: (first.offset + (rowIdx - first.index) * first.size)
        val bottom = top + (row?.size ?: first.size)
        val delta = when {
            top < layout.viewportStartOffset -> top - layout.viewportStartOffset
            bottom > layout.viewportEndOffset -> bottom - layout.viewportEndOffset
            else -> 0
        }
        if (delta != 0) {
            channelListState.animateScrollBy(delta.toFloat(), tween(durationMillis = 100))
        }
    }
    fun nearestProgramIndex(rowIdx: Int, anchorStartMin: Int, preferLive: Boolean = false): Int? {
        val channel = channels.getOrNull(rowIdx) ?: return null
        val targets = programFocusTargets[channel.id].orEmpty()
        if (targets.isEmpty()) return null
        if (preferLive) {
            val liveIdx = targets.indexOfFirst { it.isNow }
            if (liveIdx >= 0) return liveIdx
        }
        return targets
            .withIndex()
            .minByOrNull { (_, target) -> target.distanceTo(anchorStartMin) }
            ?.index
    }

    fun requestNearestProgramFocus(rowIdx: Int, anchorStartMin: Int, preferLive: Boolean = false): Boolean {
        val channel = channels.getOrNull(rowIdx) ?: return false
        requestMoreRowsIfNeeded(rowIdx)
        focusJob?.cancel()
        val currentTargetIdx = nearestProgramIndex(rowIdx, anchorStartMin, preferLive)
        val directRequester = currentTargetIdx?.let { programFocusRequesters[channel.id]?.getOrNull(it) }
        if (directRequester != null && runCatching { directRequester.requestFocus() }.isSuccess) {
            return true
        }
        focusJob = scope.launch {
            launch { revealRow(rowIdx) }
            // Retry a few times: Compose may need a frame to mount the row and
            // its programme; falling back to spatial focus can jump to the rail.
            repeat(8) {
                val targetIdx = nearestProgramIndex(rowIdx, anchorStartMin, preferLive)
                val requester = targetIdx?.let { programFocusRequesters[channel.id]?.getOrNull(it) }
                if (requester != null && runCatching { requester.requestFocus() }.isSuccess) {
                    return@launch
                }
                androidx.compose.runtime.withFrameNanos { }
            }
        }
        return true
    }

    fun keepChannelFocus(rowIdx: Int): Boolean {
        val channel = channels.getOrNull(rowIdx) ?: return true
        requestMoreRowsIfNeeded(rowIdx)
        activeChannelFocusId = channel.id
        activeChannelFocusIndex = rowIdx
        pendingChannelFocusId = channel.id
        focusJob?.cancel()
        val directRequester = channelFocusRequesters[channel.id]
            ?: if (rowIdx == 0) firstChannelFocusRequester
            else if (channel.id == selectedChannelId) selectedChannelFocusRequester
            else null
        if (directRequester != null && runCatching { directRequester.requestFocus() }.isSuccess) {
            pendingChannelFocusId = null
            return true
        }
        focusJob = scope.launch {
            // Request focus as soon as the target mounts, not after scrolling ends.
            launch { revealRow(rowIdx) }
            androidx.compose.runtime.withFrameNanos { }
            repeat(8) { attempt ->
                val requester = channelFocusRequesters[channel.id] ?: when {
                    rowIdx == 0 -> firstChannelFocusRequester
                    channel.id == selectedChannelId -> selectedChannelFocusRequester
                    else -> null
                }
                if (requester != null && runCatching { requester.requestFocus() }.isSuccess) {
                    pendingChannelFocusId = null
                    return@launch
                }
                if (attempt < 7) androidx.compose.runtime.withFrameNanos { }
            }
            pendingChannelFocusId = null
        }
        return true
    }

    fun moveChannelFocus(delta: Int): Boolean {
        val anchorId = activeChannelFocusId ?: selectedChannelId
        val anchorIdx = anchorId?.let(channelIndexById::get)
            ?: selectedChannelId?.let(channelIndexById::get)
            ?: return false
        val targetIdx = anchorIdx + delta
        return when {
            targetIdx < 0 -> {
                if (channelWindowOffset > 0) {
                    onRequestPreviousChannels()
                }
                true
            }
            targetIdx >= channels.size -> {
                onRequestNextChannels()
                true
            }
            // The target is already known by channel index. Avoid a spatial
            // search through the programme tree, and retain pending key repeats
            // when the next row has not been composed yet.
            else -> keepChannelFocus(targetIdx)
        }
    }

    LaunchedEffect(scrollResetKey, channelIndexById, gridFocused, focusMode) {
        if (gridFocused && focusMode == EpgGridFocusMode.ChannelList && channels.isNotEmpty() &&
            activeChannelFocusId != null && activeChannelFocusId !in channelIndexById
        ) {
            keepChannelFocus(activeChannelFocusIndex.coerceAtMost(channels.lastIndex))
        }
    }

    // Scroll the grid to the active channel whenever the selection changes
    // from outside (e.g. search result picked). Uses a keyed LaunchedEffect
    // on both selection and channel list identity so a late-arriving list
    // still lands on the right row.
    LaunchedEffect(selectedChannelId, channelWindowIdentity) {
        if (didPositionInitialSelection) return@LaunchedEffect
        val id = selectedChannelId ?: return@LaunchedEffect
        val idx = channelIndexById[id] ?: return@LaunchedEffect
        val targetScroll = (idx - 2).coerceAtLeast(0)
        channelListState.scrollToItem(targetScroll)
        activeChannelFocusId = id
        activeChannelFocusIndex = idx
        pendingChannelFocusId = null
        didPositionInitialSelection = true
    }

    var handledSelectedFocusSignal by remember { mutableIntStateOf(0) }
    LaunchedEffect(focusSelectedChannelSignal, selectedChannelId, channelWindowIdentity) {
        if (focusSelectedChannelSignal == 0) return@LaunchedEffect
        if (handledSelectedFocusSignal == focusSelectedChannelSignal) return@LaunchedEffect
        val id = selectedChannelId ?: return@LaunchedEffect
        val idx = channelIndexById[id] ?: return@LaunchedEffect
        if (channelListState.layoutInfo.visibleItemsInfo.any { it.index == idx }) {
            revealRow(idx)
        } else {
            val targetScroll = (idx - 2).coerceAtLeast(0)
            channelListState.scrollToItem(targetScroll)
        }
        // The shared anchor can still belong to the previously focused row.
        // Resolve the requested ID, and wait for its lazy row to be attached.
        activeChannelFocusId = id
        activeChannelFocusIndex = idx
        repeat(8) {
            val requester = channelFocusRequesters[id]
            if (requester != null && runCatching { requester.requestFocus() }.isSuccess) {
                handledSelectedFocusSignal = focusSelectedChannelSignal
                return@LaunchedEffect
            }
            androidx.compose.runtime.withFrameNanos { }
        }
    }

    BackHandler(enabled = backHandlingEnabled && gridFocused) {
        if (focusMode == EpgGridFocusMode.Epg) {
            onExitEpg(selectedChannel)
            runCatching { selectedChannelFocusRequester.requestFocus() }
        } else {
            onMoveLeftFromChannels()
        }
    }

    var handledEpgFocusSignal by remember { mutableIntStateOf(0) }
    LaunchedEffect(focusEpgSignal, selectedChannelId, channelWindowIdentity, windowStartMillis) {
        if (focusEpgSignal == 0) return@LaunchedEffect
        if (handledEpgFocusSignal == focusEpgSignal) return@LaunchedEffect
        val id = selectedChannelId ?: return@LaunchedEffect
        val idx = channelIndexById[id] ?: return@LaunchedEffect
        val nowMin = ((clockTickMillis - windowStartMillis) / 60_000L).toInt()
        repeat(6) {
            if (requestNearestProgramFocus(idx, nowMin)) {
                handledEpgFocusSignal = focusEpgSignal
                return@LaunchedEffect
            }
            delay(50L)
        }
        keepChannelFocus(idx)
        handledEpgFocusSignal = focusEpgSignal
    }

    LaunchedEffect(windowStartMillis, compact) {
        repeat(20) { attempt ->
            with(density) {
                val nowOffsetMin = ((clockTickMillis - windowStartMillis) / 60_000L).toInt()
                val targetPx = (nowOffsetMin * pxPerMin).dp.toPx().toInt() - 30.dp.toPx().toInt()
                hScroll.scrollTo(targetPx.coerceIn(0, hScroll.maxValue.coerceAtLeast(0)))
            }
            if (hScroll.maxValue > 0) return@LaunchedEffect
            if (attempt < 19) delay(50L)
        }
    }

    val currentVisibleRange by rememberUpdatedState(onVisibleChannelRange)
    val currentNextPage by rememberUpdatedState(onRequestNextChannels)
    LaunchedEffect(channelListState, channels.size, channelWindowOffset, safeTotalChannelCount) {
        snapshotFlow {
            val visibleItems = channelListState.layoutInfo.visibleItemsInfo
            val first = visibleItems.firstOrNull()?.index ?: return@snapshotFlow null
            val last = visibleItems.last().index
            first to last
        }
            .filterNotNull()
            .distinctUntilChanged()
            .collect { (first, last) ->
                currentVisibleRange(first + channelWindowOffset, last + channelWindowOffset)
                if (first <= ChannelWindowPrefetchThreshold && channelWindowOffset > 0) {
                    onRequestPreviousChannels()
                }
                if (
                    channels.isNotEmpty() &&
                    channels.lastIndex - last <= ChannelWindowPrefetchThreshold &&
                    channelWindowOffset + last < safeTotalChannelCount - 1
                ) {
                    currentNextPage()
                }
            }
    }

    Column(
        modifier = modifier.fillMaxSize().background(LiveColors.Bg)
            .padding(horizontal = if (compact) 0.dp else 12.dp).padding(bottom = if (compact) 0.dp else 20.dp),
    ) {
        if (!compact) Row(Modifier.fillMaxWidth().height(34.dp).padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Icon(Icons.Outlined.Menu, "Categories", tint = LiveColors.Fg,
                modifier = Modifier.size(28.dp).clickable(onClick = onMoveLeftFromChannels).padding(4.dp))
            Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(categoryTitle, color = LiveColors.Fg, fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                    maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f, fill = false))
                Text("${java.text.NumberFormat.getIntegerInstance().format(safeTotalChannelCount)} channels", color = LiveColors.FgDim, fontSize = 10.sp)
            }
            Icon(Icons.Outlined.ChevronLeft, "Earlier programmes", tint = LiveColors.Fg,
                modifier = Modifier.size(28.dp).clickable { scope.launch { hScroll.animateScrollBy(-with(density) { halfHourWidth.toPx() * 2 }) } }.padding(5.dp))
            val visibleHour by remember(hScroll, windowStartMillis, density, pxPerMin) { derivedStateOf {
                val offsetMinutes = hScroll.value / with(density) { pxPerMin.dp.toPx() }
                windowStartMillis + (offsetMinutes / 60).toLong() * 3_600_000
            } }
            val visibleDate = remember(visibleHour) {
                java.text.SimpleDateFormat("EEE, d MMM", java.util.Locale.getDefault())
                    .format(java.util.Date(visibleHour))
            }
            Text(visibleDate,
                color = LiveColors.FgDim, fontSize = 11.sp)
            Icon(Icons.Outlined.ChevronRight, "Later programmes", tint = LiveColors.Fg,
                modifier = Modifier.size(28.dp).clickable { scope.launch { hScroll.animateScrollBy(with(density) { halfHourWidth.toPx() * 2 }) } }.padding(5.dp))
            Row(Modifier.clickable { scope.launch {
                hScroll.animateScrollTo(with(density) { (((clockTickMillis - windowStartMillis) / 60_000f * pxPerMin).dp.toPx() - halfHourWidth.toPx()).toInt().coerceAtLeast(0) })
            } }.padding(4.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Icon(Icons.Outlined.Restore, null, tint = LiveColors.Fg, modifier = Modifier.size(19.dp))
                Text("Now", color = LiveColors.Fg, fontSize = 11.sp)
            }
        }
        // ─── Header row ─────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight)
                .background(LiveColors.PanelDeep),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Sticky channel-column label + current CH indicator
            Row(
                modifier = Modifier
                    .width(channelColumnWidth)
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                if (compact) Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(stringResource(R.string.live_label_channels), style = LiveType.SectionTag.copy(color = LiveColors.FgMute))
                    Text(safeTotalChannelCount.toString(),
                        style = LiveType.NumberMono.copy(color = LiveColors.FgDim))
                }
                val currentPlayingOrSelectedChannel = playingChannelId?.let { id ->
                    channelIndexById[id]?.let { index -> channels.getOrNull(index) }
                } ?: selectedChannel
                if (compact) Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(stringResource(R.string.live_badge_ch), style = LiveType.SectionTag.copy(color = LiveColors.Accent))
                    Text(
                        currentPlayingOrSelectedChannel?.number?.toString() ?: "—",
                        style = LiveType.NumberMono.copy(color = LiveColors.Accent),
                    )
                }
            }
            // Divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(LiveColors.DividerStrong)
            )
            // Scrolling time ruler with NOW pill pinned to the current minute.
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .clipToBounds(),
            ) {
                val rulerWidthPx = with(density) { maxWidth.toPx() }
                val rulerWindow by remember(hScroll, density, rulerWidthPx, pxPerMin) {
                    derivedStateOf {
                        guideRenderWindow(hScroll.value, rulerWidthPx, with(density) { pxPerMin.dp.toPx() })
                    }
                }
                // Retain the full scroll extent without laying out and visiting
                // accessibility bounds for every offscreen label on each frame.
                Box(Modifier.horizontalScroll(hScroll).width(halfHourWidth * slots.size).fillMaxHeight()) {
                    slots.forEachIndexed { index, slot ->
                        if (!rulerWindow.intersects(index * 30, (index + 1) * 30)) return@forEachIndexed
                        Box(
                            modifier = Modifier
                                .offset(x = halfHourWidth * index)
                                .width(halfHourWidth)
                                .fillMaxHeight()
                                .padding(start = 12.dp)
                                .testTag("iptv-time-slot:$index"),
                            contentAlignment = if (compact) Alignment.CenterStart else Alignment.BottomStart,
                        ) {
                            Text(
                                text = slot.label,
                                style = LiveType.TimeMono.copy(color = LiveColors.FgDim, fontSize = 9.sp),
                            )
                        }
                    }
                }
                // Cyan "NOW hh:mm" pill hovering above the now-line inside the header.
                if (clockTickMillis in windowStartMillis until windowEndMillis) {
                    val nowMin = ((clockTickMillis - windowStartMillis) / 60_000L).toInt()
                    val nowOffset = (nowMin * pxPerMin).dp
                    Box(
                        modifier = Modifier
                            .layout { measurable, constraints ->
                                val label = measurable.measure(constraints.copy(minWidth = 0, minHeight = 0))
                                val nowX = nowOffset.toPx() - hScroll.value
                                layout(label.width, label.height) {
                                    if (nowX in 0f..rulerWidthPx) {
                                        label.placeRelative(
                                            (nowX - label.width / 2f).coerceIn(0f, (rulerWidthPx - label.width).coerceAtLeast(0f)).toInt(),
                                            0,
                                        )
                                    }
                                }
                            }
                            .clip(RoundedCornerShape(4.dp))
                            .background(LiveColors.Accent)
                            .padding(horizontal = 8.dp, vertical = 3.dp),
                    ) {
                        Text(
                            text = formatClock(clockTickMillis),
                            style = LiveType.Badge.copy(color = LiveColors.Bg),
                        )
                    }
                }
            }
        }

        // Thin divider under header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(LiveColors.Divider),
        )

        // ─── Body ───────────────────────────────────────────────────
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val totalWidth = halfHourWidth * slots.size
            val viewportWidth = (maxWidth - channelColumnWidth - 1.dp).coerceAtLeast(0.dp)
            val renderWindow by remember(hScroll, density, viewportWidth, pxPerMin) {
                derivedStateOf {
                    guideRenderWindow(
                        hScroll.value,
                        with(density) { viewportWidth.toPx() },
                        with(density) { pxPerMin.dp.toPx() },
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .onKeyEvent { ev ->
                        if (ev.type != KeyEventType.KeyDown || (ev.key != Key.Back && ev.key != Key.Escape)) {
                            return@onKeyEvent false
                        }
                        if (focusMode == EpgGridFocusMode.Epg) {
                            onExitEpg(selectedChannel)
                            runCatching { selectedChannelFocusRequester.requestFocus() }
                        } else {
                            onMoveLeftFromChannels()
                        }
                        true
                    }
            ) {
                LazyColumn(
                    state = if (channels.isEmpty()) emptyChannelListState else channelListState,
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("iptv-guide")
                        .arvioDpadFocusGroup(enableFocusRestorer = false)
                ) {
                    itemsIndexed(
                        channels,
                        key = { _, ch -> ch.id },
                        contentType = { _, _ -> "channelRowAndPrograms" }
                    ) { idx, ch ->
                        val channelFocusRequester = remember(ch.id) { FocusRequester() }
                        val locallyFocused by remember(ch.id, scrollResetKey, focusMode) {
                            derivedStateOf {
                                ch.id == activeChannelFocusId && focusMode == EpgGridFocusMode.ChannelList
                            }
                        }
                        val isFocusAnchor by remember(ch.id, scrollResetKey, selectedChannelId, channels.firstOrNull()?.id) {
                            derivedStateOf {
                                ch.id == (activeChannelFocusId ?: selectedChannelId ?: channels.firstOrNull()?.id)
                            }
                        }
                        DisposableEffect(ch.id, channelFocusRequester) {
                            channelFocusRequesters[ch.id] = channelFocusRequester
                            onDispose {
                                if (channelFocusRequesters[ch.id] === channelFocusRequester) {
                                    channelFocusRequesters.remove(ch.id)
                                }
                            }
                        }
                        val rowPrograms = remember(
                            ch.id,
                            nowNext[ch.id],
                            windowStartMillis,
                            windowEndMillis,
                        ) {
                            programsInWindow(nowNext[ch.id], windowStartMillis, windowEndMillis)
                        }
                        val hasFocusable = remember(ch, rowPrograms, clockTickMillis) {
                            hasFocusablePrograms(ch, rowPrograms, clockTickMillis)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(rowHeight)
                                // Keep accessibility geometry sorting local to each guide row.
                                .semantics { isTraversalGroup = true }
                        ) {
                            val isChannelActive = if (playingChannelId != null) {
                                ch.id == playingChannelId
                            } else {
                                ch.id == selectedChannelId
                            }
                            // 1. Channel item (fixed width, doesn't scroll horizontally)
                            ChannelRow(
                                channel = ch,
                                displayQuality = ch.displayQuality(playbackQuality),
                                isActive = isChannelActive,
                                clockTickMillis = clockTickMillis,
                                nowNext = nowNext[ch.id],
                                isFavorite = ch.id in favorites,
                                stripe = idx % 2 == 1,
                                onClick = { onChannelSelect(ch) },
                                onFocused = {
                                    val pendingId = pendingChannelFocusId
                                    if (pendingId != null && pendingId != ch.id) {
                                        return@ChannelRow
                                    }
                                    pendingChannelFocusId = null
                                    activeChannelFocusId = ch.id
                                    activeChannelFocusIndex = idx
                                    requestMoreRowsIfNeeded(idx)
                                    onChannelFocused(ch)
                                },
                                onMoveLeft = onMoveLeftFromChannels,
                                onMoveRight = {
                                    if (hasFocusable) {
                                        val nowMin = ((clockTickMillis - windowStartMillis) / 60_000L).toInt()
                                        onEnterEpg(ch)
                                        requestNearestProgramFocus(idx, nowMin, preferLive = true)
                                    } else {
                                        keepChannelFocus(idx)
                                    }
                                    true
                                },
                                onMoveUp = { moveChannelFocus(-1) },
                                onMoveDown = { moveChannelFocus(+1) },
                                onLongPress = { fromKeyHold -> onChannelLongPress(ch, fromKeyHold) },
                                variantCount = variantCountFor(ch),
                                rowHeight = rowHeight,
                                forceFocused = gridFocused && locallyFocused,
                                modifier = Modifier
                                    .width(channelColumnWidth)
                                    .testTag("iptv-channel:${ch.id}")
                                    .background(LiveColors.PanelDeep)
                                    .focusRequester(channelFocusRequester)
                                    .then(if (idx == 0) Modifier.focusRequester(firstChannelFocusRequester) else Modifier)
                                    .then(
                                        if (isFocusAnchor) {
                                            Modifier.focusRequester(selectedChannelFocusRequester)
                                        } else Modifier
                                    ),
                            )

                            // 2. Vertical Divider
                            Box(
                                modifier = Modifier
                                    .width(1.dp)
                                    .fillMaxHeight()
                                    .background(LiveColors.Divider)
                            )

                            // 3. EPG programs row (scrolls horizontally using the shared hScroll)
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .horizontalScroll(hScroll)
                            ) {
                                val isGuideLoading = hasGuideSource &&
                                    rowPrograms.isEmpty() &&
                                    (
                                        ch.id in epgLoadingChannelIds ||
                                            isGuideBackfillLoading
                                        )
                                val guideAttempted = ch.id in epgAttemptedChannelIds
                                val rowHasGuideIdentity = !ch.source.epgId.isNullOrBlank() ||
                                    !ch.source.tvgName.isNullOrBlank()
                                val placeholderTitle = when {
                                    isGuideLoading -> stringResource(R.string.live_placeholder_loading_guide)
                                    !rowHasGuideIdentity -> stringResource(R.string.live_empty_no_programme)
                                    hasGuideSource && guideAttempted -> stringResource(R.string.live_placeholder_no_guide_matched)
                                    hasGuideSource -> stringResource(R.string.live_placeholder_guide_pending)
                                    else -> stringResource(R.string.live_placeholder_no_guide_source)
                                }
                                ProgramsRow(
                                    channel = ch,
                                    programs = rowPrograms,
                                    placeholderTitle = placeholderTitle,
                                    noProgrammeData = stringResource(R.string.live_empty_no_programme),
                                    clockTickMillis = clockTickMillis,
                                    windowStartMillis = windowStartMillis,
                                    windowEndMillis = windowEndMillis,
                                    totalWidth = totalWidth,
                                    pxPerMin = pxPerMin,
                                    stripe = idx % 2 == 1,
                                    isActive = false,
                                    epgMode = focusMode == EpgGridFocusMode.Epg,
                                    rowHeight = rowHeight,
                                    renderWindow = renderWindow,
                                    hScrollOffsetPx = { hScroll.value },
                                    onClick = { program ->
                                        onExitEpg(ch)
                                        onProgramSelect(ch, program)
                                        keepChannelFocus(idx)
                                    },
                                    onFocused = { program ->
                                        if (focusMode == EpgGridFocusMode.Epg) {
                                            onChannelFocused(ch)
                                            onProgramFocused(ch, program)
                                        }
                                    },
                                    onMoveVertically = { targetRowIdx, anchorStartMin ->
                                        val targetChannel = channels.getOrNull(targetRowIdx)
                                        val targetPrograms = targetChannel?.let { targetCh ->
                                            programsInWindow(nowNext[targetCh.id], windowStartMillis, windowEndMillis)
                                        }.orEmpty()
                                        val targetHasFocusable = targetChannel != null &&
                                            hasFocusablePrograms(targetChannel, targetPrograms, clockTickMillis)
                                        if (targetHasFocusable) {
                                            requestNearestProgramFocus(targetRowIdx, anchorStartMin)
                                        } else if (targetChannel != null) {
                                            onExitEpg(targetChannel)
                                            keepChannelFocus(targetRowIdx)
                                        }
                                        true
                                    },
                                    onMoveLeftFromStart = {
                                        onExitEpg(ch)
                                        keepChannelFocus(idx)
                                        true
                                    },
                                    rowIdx = idx,
                                    focusRequesters = programFocusRequesters,
                                    focusTargets = programFocusTargets,
                                )
                            }
                        }
                    }
                }

                // Read scrolling in the drawing phase, not the whole guide composition.
                Canvas(Modifier.fillMaxSize()) {
                    if (clockTickMillis in windowStartMillis until windowEndMillis) {
                        val nowMin = ((clockTickMillis - windowStartMillis) / 60_000L).toInt()
                        val inside = (nowMin * pxPerMin).dp.toPx() - hScroll.value
                        val x = (channelColumnWidth + 1.dp).toPx() + inside
                        if (inside >= 0f && x < size.width) {
                            drawRect(LiveColors.Accent, Offset(x, 0f), Size(1.dp.toPx(), size.height))
                        }
                    }
                }
            }
        }
    }
}

private val GuideListStatesSaver = listSaver<LinkedHashMap<String, LazyListState>, Any>(
    save = { states ->
        states.flatMap { (key, state) -> listOf(key, state.firstVisibleItemIndex, state.firstVisibleItemScrollOffset) }
    },
    restore = { values ->
        LinkedHashMap<String, LazyListState>().apply {
            values.chunked(3).forEach { (key, index, offset) ->
                put(key as String, LazyListState(index as Int, offset as Int))
            }
        }
    },
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ProgramsRow(
    channel: EnrichedChannel,
    programs: List<IptvProgram>,
    placeholderTitle: String,
    noProgrammeData: String,
    clockTickMillis: Long,
    windowStartMillis: Long,
    windowEndMillis: Long,
    totalWidth: Dp,
    pxPerMin: Float,
    stripe: Boolean,
    isActive: Boolean,
    epgMode: Boolean,
    rowHeight: Dp,
    renderWindow: GuideRenderWindow,
    hScrollOffsetPx: () -> Int = { 0 },
    onClick: (IptvProgram?) -> Unit,
    onFocused: (IptvProgram) -> Unit,
    onMoveVertically: (rowIdx: Int, anchorStartMin: Int) -> Boolean,
    onMoveLeftFromStart: () -> Boolean,
    rowIdx: Int,
    focusRequesters: MutableMap<String, List<FocusRequester>>,
    focusTargets: MutableMap<String, List<ProgramFocusTarget>>,
) {
    val nowMillis = clockTickMillis
    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .width(totalWidth)
            .height(rowHeight)
            .background(
                if (stripe) LiveColors.RowStripe else Color.Transparent
            ),
    ) {
        // Placement geometry (cell offsets/widths + gap placeholders) does NOT depend
        // on the clock, so it is keyed only on the programmes and window. This stops the
        // whole row's layout from being rebuilt on every 30s clock tick — the recompute
        // storm that made dpad navigation hitch. The now/past state is derived cheaply
        // per render from `nowMillis` via ProgramPlacement.isNow()/isPast(), and the
        // placeholder anchor refreshes whenever `windowStartMillis` rounds forward.
        val placements = remember(programs, placeholderTitle, noProgrammeData, windowStartMillis, windowEndMillis) {
            buildProgramPlacements(programs, windowStartMillis, windowEndMillis, nowMillis, placeholderTitle, noProgrammeData)
        }
        val focusablePlacementIndices = remember(placements, channel.catchupDays, nowMillis) {
            placements.mapIndexedNotNull { index, placement ->
                val canFocus = placement.canFocus(channel, nowMillis)
                if (canFocus) index else null
            }
        }
        val focusableIndexByPlacementIndex = remember(focusablePlacementIndices) {
            focusablePlacementIndices
                .withIndex()
                .associate { (focusIndex, placementIndex) -> placementIndex to focusIndex }
        }
        val rowFocusRequesters = remember(channel.id, focusablePlacementIndices.size) {
            List(focusablePlacementIndices.size) { FocusRequester() }
        }
        val rowFocusTargets = remember(placements, focusablePlacementIndices, nowMillis) {
            focusablePlacementIndices.mapNotNull { index ->
                placements.getOrNull(index)?.let { placement ->
                    ProgramFocusTarget(
                        startMin = placement.startMin,
                        endMin = placement.endMin,
                        isNow = placement.isNow(nowMillis),
                    )
                }
            }
        }
        DisposableEffect(channel.id, rowFocusRequesters, rowFocusTargets) {
            focusRequesters[channel.id] = rowFocusRequesters
            focusTargets[channel.id] = rowFocusTargets
            onDispose {
                if (focusRequesters[channel.id] === rowFocusRequesters) {
                    focusRequesters.remove(channel.id)
                }
                if (focusTargets[channel.id] === rowFocusTargets) {
                    focusTargets.remove(channel.id)
                }
            }
        }
        if (placements.isNotEmpty()) {
            placements.forEachIndexed { placementIndex, placement ->
                // Preserve the complete focus graph while navigating programmes. In
                // channel/touch mode only construct cells near the visible timeline.
                if (!epgMode && !renderWindow.intersects(placement.startMin, placement.endMin)) {
                    return@forEachIndexed
                }
                val offset = (placement.startMin * pxPerMin).dp
                val width = (placement.durationMin * pxPerMin).dp
                val cellOffsetPx = with(density) { offset.toPx() }
                val cellWidthPx = with(density) { width.toPx() }
                val maxShiftPx = (cellWidthPx - with(density) { 50.dp.toPx() }).coerceAtLeast(0f)
                val isCatchupSupported = placement.isCatchupSupported(channel, nowMillis)
                val focusableIndex = focusableIndexByPlacementIndex[placementIndex] ?: -1
                val isFocusable = focusableIndex >= 0
                val placementIsNow = placement.isNow(nowMillis)
                val placementIsPast = placement.isPast(nowMillis)
                key(placement.startMillis, placement.endMillis) {
                    ProgramCell(
                        program = placement.program,
                        clockTickMillis = clockTickMillis,
                        width = width,
                        isNow = placementIsNow,
                        isPast = placementIsPast,
                        isFocusTarget = placementIsNow,
                        focusable = isFocusable && epgMode,
                        renderContent = renderWindow.intersects(placement.startMin, placement.endMin),
                        isCatchupSupported = isCatchupSupported,
                        contentStartOffsetPx = {
                            (hScrollOffsetPx() - cellOffsetPx).coerceIn(0f, maxShiftPx).toInt()
                        },
                        onClick = {
                            epgProgramActionTarget(
                                program = placement.program,
                                isPast = placementIsPast,
                                isLive = placementIsNow,
                                isCatchupSupported = isCatchupSupported,
                            )?.let(onClick)
                        },
                        onFocused = { onFocused(placement.program) },
                        onMoveLeft = {
                            if (focusableIndex > 0) {
                                runCatching { rowFocusRequesters[focusableIndex - 1].requestFocus() }
                                true
                            } else {
                                onMoveLeftFromStart()
                            }
                        },
                        onMoveRight = {
                            if (focusableIndex in 0 until rowFocusRequesters.lastIndex) {
                                runCatching { rowFocusRequesters[focusableIndex + 1].requestFocus() }
                                true
                            } else {
                                true
                            }
                        },
                        onMoveUp = {
                            onMoveVertically(rowIdx - 1, placement.startMin)
                        },
                        onMoveDown = {
                            onMoveVertically(rowIdx + 1, placement.startMin)
                        },
                        rowHeight = rowHeight,
                        focusRequester = rowFocusRequesters.getOrNull(focusableIndex),
                        modifier = Modifier.offset(x = offset),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun NowLine(
    clockTickMillis: Long,
    windowStartMillis: Long,
    pxPerMin: Float,
    hScrollOffsetPx: Int,
) {
    val density = LocalDensity.current
    val nowMin = ((clockTickMillis - windowStartMillis) / 60_000L).toInt()
    val xDp = with(density) { ((nowMin * pxPerMin).dp.toPx() - hScrollOffsetPx).toDp() }
    if (xDp < 0.dp) return
    Box(
        modifier = Modifier
            .offset(x = xDp)
            .fillMaxHeight()
            .width(2.dp)
            .background(LiveColors.Accent),
    )
    // Glow behind the 2dp line
    Box(
        modifier = Modifier
            .offset(x = xDp - 3.dp)
            .fillMaxHeight()
            .width(8.dp)
            .background(LiveColors.Accent.copy(alpha = 0.22f)),
    )
}

private data class TimeSlot(val millis: Long, val label: String, val isNow: Boolean)

private fun buildHalfHourSlots(startMillis: Long, count: Int): List<TimeSlot> {
    val out = ArrayList<TimeSlot>(count)
    val now = System.currentTimeMillis()
    for (i in 0 until count) {
        val t = startMillis + i * 30L * 60_000L
        val isNow = now in t..(t + 30L * 60_000L - 1)
        out += TimeSlot(t, formatClock(t), isNow)
    }
    return out
}

private fun roundedGuideWindowStart(nowMillis: Long, pastWindowMinutes: Int): Long {
    val halfHourMs = 30L * 60_000L
    val roundedNow = nowMillis - (nowMillis % halfHourMs)
    return roundedNow - pastWindowMinutes * 60_000L
}

private fun programsInWindow(
    item: IptvNowNext?,
    start: Long,
    end: Long,
): List<IptvProgram> {
    if (item == null) return emptyList()
    val buf = ArrayList<IptvProgram>(16)
    fun add(p: IptvProgram?) {
        if (p == null) return
        if (p.endUtcMillis > start && p.startUtcMillis < end) buf.add(p)
    }
    item.recent.forEach(::add)
    add(item.now)
    add(item.next)
    add(item.later)
    item.upcoming.forEach(::add)
    return buf.distinctBy { Triple(it.startUtcMillis, it.endUtcMillis, it.title) }
        .sortedBy { it.startUtcMillis }
}

private data class ProgramPlacement(
    val program: IptvProgram,
    val startMin: Int,
    val durationMin: Int,
    val startMillis: Long,
    val endMillis: Long,
    val isPlaceholder: Boolean = false,
) {
    val endMin: Int get() = startMin + durationMin
    fun isNow(nowMs: Long): Boolean = nowMs in startMillis until endMillis
    fun isPast(nowMs: Long): Boolean = endMillis <= nowMs
}

internal data class ProgramFocusTarget(val startMin: Int, val endMin: Int, val isNow: Boolean = false) {
    fun distanceTo(anchorStartMin: Int): Int = when {
        anchorStartMin < startMin -> startMin - anchorStartMin
        // Programme intervals are half-open: at 19:30 the 19:00-19:30
        // programme must not tie with the one that actually starts at 19:30.
        anchorStartMin >= endMin -> anchorStartMin - endMin + 1
        else -> 0
    }
}

internal fun epgProgramActionTarget(
    program: IptvProgram,
    isPast: Boolean,
    isLive: Boolean,
    isCatchupSupported: Boolean,
): IptvProgram? = when {
    isPast && isCatchupSupported -> program
    isLive -> program
    else -> null
}

private fun ProgramPlacement.isCatchupSupported(channel: EnrichedChannel, nowMillis: Long): Boolean {
    return !isPlaceholder && com.arflix.tv.data.model.IptvGuideHistory.canReplay(channel.source, program, nowMillis)
}

private fun effectiveCatchupDays(channel: EnrichedChannel): Int {
    return com.arflix.tv.data.model.IptvGuideHistory.days(channel.source)
}

private fun ProgramPlacement.canFocus(channel: EnrichedChannel, nowMillis: Long): Boolean =
    !isPlaceholder && (!isPast(nowMillis) || isCatchupSupported(channel, nowMillis))

private fun hasFocusablePrograms(
    channel: EnrichedChannel,
    programs: List<IptvProgram>,
    nowMillis: Long,
): Boolean {
    if (programs.isEmpty()) return false
    return programs.any { p ->
        p.endUtcMillis > nowMillis || com.arflix.tv.data.model.IptvGuideHistory.canReplay(channel.source, p, nowMillis)
    }
}

private fun buildProgramPlacements(
    programs: List<IptvProgram>,
    windowStartMillis: Long,
    windowEndMillis: Long,
    nowMillis: Long,
    placeholderTitle: String = "",
    noProgrammeData: String = placeholderTitle,
): List<ProgramPlacement> {
    val placements = mutableListOf<ProgramPlacement>()
    var cursor = windowStartMillis
    val gapTitle = if (programs.isEmpty()) placeholderTitle else noProgrammeData

    programs.forEach { program ->
        // 1. Fill gap before this program
        if (program.startUtcMillis > cursor) {
            val gapEnd = minOf(program.startUtcMillis, windowEndMillis)
            if (gapEnd > cursor) {
                addPlaceholderPlacement(
                    placements = placements,
                    title = gapTitle,
                    gapStart = cursor,
                    gapEnd = gapEnd,
                    windowStartMillis = windowStartMillis,
                    nowMillis = nowMillis,
                )
                cursor = gapEnd
            }
        }

        if (cursor >= windowEndMillis) return@forEach

        // 2. Add the actual program
        val clampedStart = maxOf(program.startUtcMillis, windowStartMillis, cursor)
        val clampedEnd = minOf(program.endUtcMillis, windowEndMillis)
        if (clampedEnd > clampedStart) {
            placements += ProgramPlacement(
                program = program,
                startMin = ((clampedStart - windowStartMillis) / 60_000L).toInt(),
                durationMin = ((clampedEnd - clampedStart) / 60_000L).toInt().coerceAtLeast(1),
                startMillis = clampedStart,
                endMillis = clampedEnd,
            )
            cursor = clampedEnd
        }
    }

    // 3. Fill trailing gap
    if (cursor < windowEndMillis) {
        addPlaceholderPlacement(
            placements = placements,
            title = gapTitle,
            gapStart = cursor,
            gapEnd = windowEndMillis,
            windowStartMillis = windowStartMillis,
            nowMillis = nowMillis,
        )
    }

    return placements
}

private fun addPlaceholderPlacement(
    placements: MutableList<ProgramPlacement>,
    title: String,
    gapStart: Long,
    gapEnd: Long,
    windowStartMillis: Long,
    nowMillis: Long,
) {
    if (gapEnd <= gapStart) return
    val anchor = nowMillis.coerceIn(gapStart, gapEnd - 1L)
    val preferredStart = anchor - 30L * 60_000L
    val placeholderStart = preferredStart
        .coerceAtLeast(gapStart)
        .coerceAtMost((gapEnd - 1L).coerceAtLeast(gapStart))
    val placeholderEnd = minOf(
        gapEnd,
        maxOf(placeholderStart + 60L * 60_000L, anchor + 30L * 60_000L)
    )
    placements += ProgramPlacement(
        program = IptvProgram(title, startUtcMillis = placeholderStart, endUtcMillis = placeholderEnd),
        startMin = ((placeholderStart - windowStartMillis) / 60_000L).toInt(),
        durationMin = ((placeholderEnd - placeholderStart) / 60_000L).toInt().coerceAtLeast(1),
        startMillis = placeholderStart,
        endMillis = placeholderEnd,
        isPlaceholder = true,
    )
}

