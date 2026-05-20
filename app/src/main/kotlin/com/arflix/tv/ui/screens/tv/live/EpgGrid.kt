package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

private const val EpgWindowMinutes = 24 * 60

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
    clockTickMillis: Long,
    nowNext: Map<String, IptvNowNext>,
    selectedChannelId: String?,
    focusSelectedChannelSignal: Int,
    focusEpgSignal: Int = 0,
    focusMode: EpgGridFocusMode = EpgGridFocusMode.ChannelList,
    onChannelSelect: (EnrichedChannel, IptvProgram?) -> Unit,
    onProgramSelect: (EnrichedChannel, IptvProgram?) -> Unit = onChannelSelect,
    onChannelFocused: (EnrichedChannel) -> Unit = {},
    onChannelFavoriteToggle: (String) -> Unit,
    favorites: Set<String>,
    compact: Boolean = false,
    gridFocused: Boolean = false,
    onMoveLeftFromChannels: () -> Unit = {},
    onEnterEpg: (EnrichedChannel) -> Unit = {},
    onExitEpg: (EnrichedChannel?) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val pxPerMin = if (compact) 96f / 30f else LiveDims.EpgPxPerMinute.toFloat()
    val selectedChannelFocusRequester = remember { FocusRequester() }
    val firstChannelFocusRequester = remember { FocusRequester() }
    val headerHeight = if (compact) 32.dp else LiveDims.EpgHeaderHeight
    val channelColumnWidth = if (compact) 164.dp else LiveDims.EpgChannelColWidth
    val halfHourWidth = (pxPerMin * 30f).dp
    val rowHeight = if (compact) 52.dp else LiveDims.EpgRowHeight
    val channelFocusRequesters = remember { mutableStateMapOf<String, FocusRequester>() }
    val programFocusRequesters = remember { mutableStateMapOf<String, List<FocusRequester>>() }
    val programFocusTargets = remember { mutableStateMapOf<String, List<ProgramFocusTarget>>() }

    val maxCatchupDays = remember(channels) {
        channels.maxOfOrNull { ch -> effectiveCatchupDays(ch) } ?: 0
    }
    val todayStartMillis = remember { roundedWindowStart() }
    val windowStartMillis = remember(todayStartMillis, maxCatchupDays) {
        todayStartMillis - maxCatchupDays * 24L * 60L * 60_000L
    }
    val windowEndMillis = remember(todayStartMillis) {
        todayStartMillis + EpgWindowMinutes * 60L * 1000L
    }
    val slotCount = remember(windowStartMillis, windowEndMillis) {
        (((windowEndMillis - windowStartMillis) / 60_000L) / 30L).toInt().coerceAtLeast(1)
    }
    val slots = remember(windowStartMillis, slotCount) { buildHalfHourSlots(windowStartMillis, slotCount) }

    // Shared horizontal scroll state between header and body rows.
    val hScroll = rememberScrollState()
    // A single LazyListState handles vertical scrolling for both channels and EPG.
    val channelListState = rememberLazyListState()
    var didPositionInitialSelection by remember(channels) { mutableStateOf(false) }
    var activeChannelFocusId by remember(channels) { mutableStateOf(selectedChannelId) }
    var pendingChannelFocusId by remember(channels) { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()
    fun requestProgramFocus(rowIdx: Int, targetIdx: Int): Boolean {
        val channel = channels.getOrNull(rowIdx) ?: return false
        val requesters = programFocusRequesters[channel.id].orEmpty()
        if (requesters.isEmpty()) return false
        val safeTargetIdx = targetIdx.coerceIn(0, requesters.lastIndex)
        scope.launch {
            channelListState.scrollToItem(rowIdx)
            runCatching { requesters[safeTargetIdx].requestFocus() }
        }
        return true
    }

    fun nearestProgramIndex(rowIdx: Int, anchorStartMin: Int): Int? {
        val channel = channels.getOrNull(rowIdx) ?: return null
        val targets = programFocusTargets[channel.id].orEmpty()
        if (targets.isEmpty()) return null
        return targets
            .withIndex()
            .minByOrNull { (_, target) -> target.distanceTo(anchorStartMin) }
            ?.index
    }

    fun requestNearestProgramFocus(rowIdx: Int, anchorStartMin: Int): Boolean {
        val targetIdx = nearestProgramIndex(rowIdx, anchorStartMin) ?: return false
        return requestProgramFocus(rowIdx, targetIdx)
    }

    fun keepChannelFocus(rowIdx: Int): Boolean {
        val channel = channels.getOrNull(rowIdx) ?: return true
        activeChannelFocusId = channel.id
        pendingChannelFocusId = channel.id
        onChannelFocused(channel)
        scope.launch {
            channelListState.scrollToItem(rowIdx)
            delay(16L)
            repeat(4) { attempt ->
                val requester = channelFocusRequesters[channel.id] ?: when {
                    rowIdx == 0 -> firstChannelFocusRequester
                    channel.id == selectedChannelId -> selectedChannelFocusRequester
                    else -> null
                }
                if (requester != null && runCatching { requester.requestFocus() }.isSuccess) {
                    return@launch
                }
                if (attempt < 3) delay(16L)
            }
        }
        return true
    }

    fun moveChannelFocus(delta: Int): Boolean {
        val anchorId = activeChannelFocusId ?: selectedChannelId
        val anchorIdx = anchorId?.let { id -> channels.indexOfFirst { it.id == id } }
            ?.takeIf { it >= 0 }
            ?: channels.indexOfFirst { it.id == selectedChannelId }
        if (anchorIdx < 0) return true
        return keepChannelFocus(anchorIdx + delta)
    }

    // Scroll the grid to the active channel whenever the selection changes
    // from outside (e.g. search result picked). Uses a keyed LaunchedEffect
    // on both selection and channel list identity so a late-arriving list
    // still lands on the right row.
    LaunchedEffect(selectedChannelId, channels) {
        if (didPositionInitialSelection) return@LaunchedEffect
        val id = selectedChannelId ?: return@LaunchedEffect
        val idx = channels.indexOfFirst { it.id == id }
        if (idx < 0) return@LaunchedEffect
        channelListState.scrollToItem(idx)
        didPositionInitialSelection = true
    }

    LaunchedEffect(focusSelectedChannelSignal, selectedChannelId, channels) {
        if (focusSelectedChannelSignal == 0) return@LaunchedEffect
        val id = selectedChannelId ?: return@LaunchedEffect
        val idx = channels.indexOfFirst { it.id == id }
        if (idx < 0) return@LaunchedEffect
        channelListState.scrollToItem(idx)
        runCatching { selectedChannelFocusRequester.requestFocus() }
    }

    LaunchedEffect(focusEpgSignal, selectedChannelId, channels, windowStartMillis) {
        if (focusEpgSignal == 0) return@LaunchedEffect
        val id = selectedChannelId ?: return@LaunchedEffect
        val idx = channels.indexOfFirst { it.id == id }
        if (idx < 0) return@LaunchedEffect
        val nowMin = ((clockTickMillis - windowStartMillis) / 60_000L).toInt()
        repeat(6) {
            if (requestNearestProgramFocus(idx, nowMin)) return@LaunchedEffect
            delay(50L)
        }
        keepChannelFocus(idx)
    }

    LaunchedEffect(windowStartMillis) {
        with(density) {
            val nowOffsetMin = ((clockTickMillis - windowStartMillis) / 60_000L).toInt()
            val targetPx = (nowOffsetMin * pxPerMin).dp.toPx().toInt() - 30.dp.toPx().toInt()
            hScroll.scrollTo(targetPx.coerceAtLeast(0))
        }
    }

    Column(
        modifier = modifier.fillMaxSize().background(LiveColors.Bg),
    ) {
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
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("CHANNELS", style = LiveType.SectionTag.copy(color = LiveColors.FgMute))
                    Text(channels.size.toString(),
                        style = LiveType.NumberMono.copy(color = LiveColors.FgDim))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("CH", style = LiveType.SectionTag.copy(color = LiveColors.Accent))
                    val currentNumber = channels.firstOrNull { it.id == selectedChannelId }?.number
                    Text(
                        currentNumber?.toString() ?: "—",
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(hScroll),
            ) {
                Row {
                    slots.forEach { slot ->
                        Box(
                            modifier = Modifier
                                .width(halfHourWidth)
                                .fillMaxHeight()
                                .padding(start = 12.dp),
                            contentAlignment = Alignment.CenterStart,
                        ) {
                            Text(
                                text = slot.label,
                                style = LiveType.TimeMono.copy(color = LiveColors.FgDim),
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
                            .offset(x = nowOffset - 46.dp, y = 6.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(LiveColors.Accent)
                            .padding(horizontal = 8.dp, vertical = 3.dp),
                    ) {
                        Text(
                            text = "NOW " + formatClock(clockTickMillis),
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .onKeyEvent { ev ->
                        if (ev.type != KeyEventType.KeyDown || (ev.key != Key.Back && ev.key != Key.Escape)) {
                            return@onKeyEvent false
                        }
                        if (focusMode == EpgGridFocusMode.Epg) {
                            onExitEpg(selectedChannelId?.let { id -> channels.firstOrNull { it.id == id } })
                            selectedChannelFocusRequester.requestFocus()
                        } else {
                            onMoveLeftFromChannels()
                        }
                        true
                    }
            ) {
                LazyColumn(
                    state = channelListState,
                    modifier = Modifier
                        .fillMaxSize()
                        .arvioDpadFocusGroup()
                ) {
                    itemsIndexed(
                        channels,
                        key = { _, ch -> ch.id },
                        contentType = { _, _ -> "channelRowAndPrograms" }
                    ) { idx, ch ->
                        val channelFocusRequester = remember(ch.id) { FocusRequester() }
                        SideEffect {
                            channelFocusRequesters[ch.id] = channelFocusRequester
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(rowHeight)
                        ) {
                            // 1. Channel item (fixed width, doesn't scroll horizontally)
                            ChannelRow(
                                channel = ch,
                                isActive = ch.id == selectedChannelId,
                                clockTickMillis = clockTickMillis,
                                nowNext = nowNext[ch.id],
                                isFavorite = ch.id in favorites,
                                stripe = idx % 2 == 1,
                                onClick = { onChannelSelect(ch, null) },
                                onFocused = {
                                    val pendingId = pendingChannelFocusId
                                    if (pendingId != null && pendingId != ch.id) {
                                        return@ChannelRow
                                    }
                                    pendingChannelFocusId = null
                                    activeChannelFocusId = ch.id
                                    onChannelFocused(ch)
                                },
                                onMoveLeft = onMoveLeftFromChannels,
                                onMoveRight = {
                                    val nowMin = ((clockTickMillis - windowStartMillis) / 60_000L).toInt()
                                    onEnterEpg(ch)
                                    if (requestNearestProgramFocus(idx, nowMin)) {
                                        true
                                    } else {
                                        keepChannelFocus(idx)
                                    }
                                    true
                                },
                                onMoveUp = { moveChannelFocus(-1) },
                                onMoveDown = { moveChannelFocus(+1) },
                                onFavoriteToggle = { onChannelFavoriteToggle(ch.id) },
                                rowHeight = rowHeight,
                                forceFocused = gridFocused &&
                                    focusMode == EpgGridFocusMode.ChannelList &&
                                    ch.id == selectedChannelId,
                                modifier = Modifier
                                    .width(channelColumnWidth)
                                    .background(LiveColors.PanelDeep)
                                    .focusRequester(channelFocusRequester)
                                    .then(if (idx == 0) Modifier.focusRequester(firstChannelFocusRequester) else Modifier)
                                    .then(if (ch.id == selectedChannelId) Modifier.focusRequester(selectedChannelFocusRequester) else Modifier),
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
                                val rowPrograms = remember(
                                    ch.id,
                                    nowNext[ch.id],
                                    windowStartMillis,
                                    windowEndMillis,
                                ) {
                                    programsInWindow(nowNext[ch.id], windowStartMillis, windowEndMillis)
                                }
                                ProgramsRow(
                                    channel = ch,
                                    programs = rowPrograms,
                                    clockTickMillis = clockTickMillis,
                                    windowStartMillis = windowStartMillis,
                                    windowEndMillis = windowEndMillis,
                                    totalWidth = totalWidth,
                                    pxPerMin = pxPerMin,
                                    stripe = idx % 2 == 1,
                                    isActive = ch.id == selectedChannelId && focusMode == EpgGridFocusMode.Epg,
                                    epgMode = focusMode == EpgGridFocusMode.Epg,
                                    rowHeight = rowHeight,
                                    onClick = { program ->
                                        onExitEpg(ch)
                                        onProgramSelect(ch, program)
                                        keepChannelFocus(idx)
                                    },
                                    onFocused = {
                                        if (focusMode == EpgGridFocusMode.Epg) {
                                            onChannelFocused(ch)
                                        }
                                    },
                                    onMoveVertically = { targetRowIdx, anchorStartMin ->
                                        requestNearestProgramFocus(targetRowIdx, anchorStartMin)
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

                // NOW glow line across full body
                if (clockTickMillis in windowStartMillis until windowEndMillis) {
                    val nowMin = ((clockTickMillis - windowStartMillis) / 60_000L).toInt()
                    val xDpInside = (nowMin * pxPerMin).dp - with(density) { hScroll.value.toDp() }
                    if (xDpInside >= 0.dp) {
                        val xDp = channelColumnWidth + 1.dp + xDpInside
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
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ProgramsRow(
    channel: EnrichedChannel,
    programs: List<IptvProgram>,
    clockTickMillis: Long,
    windowStartMillis: Long,
    windowEndMillis: Long,
    totalWidth: Dp,
    pxPerMin: Float,
    stripe: Boolean,
    isActive: Boolean,
    epgMode: Boolean,
    rowHeight: Dp,
    onClick: (IptvProgram?) -> Unit,
    onFocused: () -> Unit,
    onMoveVertically: (rowIdx: Int, anchorStartMin: Int) -> Boolean,
    onMoveLeftFromStart: () -> Boolean,
    rowIdx: Int,
    focusRequesters: MutableMap<String, List<FocusRequester>>,
    focusTargets: MutableMap<String, List<ProgramFocusTarget>>,
) {
    val nowMillis = clockTickMillis
    Box(
        modifier = Modifier
            .width(totalWidth)
            .height(rowHeight)
            .clipToBounds()
            .background(
                when {
                    isActive -> LiveColors.FocusBg
                    stripe -> LiveColors.RowStripe
                    else -> Color.Transparent
                }
            ),
    ) {
        val placements = remember(programs, windowStartMillis, windowEndMillis, nowMillis) {
            buildProgramPlacements(programs, windowStartMillis, windowEndMillis, nowMillis)
        }
        val focusablePlacementIndices = remember(placements, channel.catchupDays, nowMillis, epgMode) {
            if (!epgMode) return@remember emptyList()
            placements.mapIndexedNotNull { index, placement ->
                val canFocus = placement.canFocus(channel, nowMillis)
                if (canFocus) index else null
            }
        }
        val rowFocusRequesters = remember(channel.id, focusablePlacementIndices.size) {
            List(focusablePlacementIndices.size) { FocusRequester() }
        }
        val rowFocusTargets = remember(placements, focusablePlacementIndices) {
            focusablePlacementIndices.mapNotNull { index ->
                placements.getOrNull(index)?.let { placement ->
                    ProgramFocusTarget(placement.startMin, placement.endMin)
                }
            }
        }
        SideEffect {
            focusRequesters[channel.id] = rowFocusRequesters
            focusTargets[channel.id] = rowFocusTargets
        }
        if (placements.isNotEmpty()) {
            placements.forEachIndexed { placementIndex, placement ->
                val offset = (placement.startMin * pxPerMin).dp
                val width = (placement.durationMin * pxPerMin).dp
                val isCatchupSupported = placement.isCatchupSupported(channel, nowMillis)
                val focusableIndex = focusablePlacementIndices.indexOf(placementIndex)
                val isFocusable = focusableIndex >= 0
                ProgramCell(
                    program = placement.program,
                    clockTickMillis = clockTickMillis,
                    width = width,
                    isNow = placement.isNow,
                    isPast = placement.isPast,
                    isFocusTarget = placement.isNow,
                    focusable = isFocusable,
                    isCatchupSupported = isCatchupSupported,
                    onClick = {
                        if (placement.isPast && isCatchupSupported) {
                            onClick(placement.program)
                        } else if (!placement.isPast) {
                            onClick(null)
                        }
                    },
                    onFocused = onFocused,
                    onMoveLeft = {
                        if (focusableIndex > 0) {
                            rowFocusRequesters[focusableIndex - 1].requestFocus()
                            true
                        } else {
                            onMoveLeftFromStart()
                        }
                    },
                    onMoveRight = {
                        if (focusableIndex in 0 until rowFocusRequesters.lastIndex) {
                            rowFocusRequesters[focusableIndex + 1].requestFocus()
                            true
                        } else {
                            false
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

/** Round down to the start of the current day (00:00) so the user can
 *  scroll back through the full daily timeline for catchup. */
private fun roundedWindowStart(): Long {
    val cal = java.util.Calendar.getInstance()
    cal.timeInMillis = System.currentTimeMillis()
    cal.set(java.util.Calendar.HOUR_OF_DAY, 0)
    cal.set(java.util.Calendar.MINUTE, 0)
    cal.set(java.util.Calendar.SECOND, 0)
    cal.set(java.util.Calendar.MILLISECOND, 0)
    return cal.timeInMillis
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
    val isNow: Boolean,
    val isPast: Boolean,
    val isPlaceholder: Boolean = false,
) {
    val endMin: Int get() = startMin + durationMin
}

private data class ProgramFocusTarget(val startMin: Int, val endMin: Int) {
    fun distanceTo(anchorStartMin: Int): Int = when {
        anchorStartMin < startMin -> startMin - anchorStartMin
        anchorStartMin > endMin -> anchorStartMin - endMin
        else -> 0
    }
}

private fun ProgramPlacement.isCatchupSupported(channel: EnrichedChannel, nowMillis: Long): Boolean {
    val days = effectiveCatchupDays(channel)
    return days > 0 &&
        !isPlaceholder &&
        program.startUtcMillis >= nowMillis - days * 24L * 60L * 60_000L
}

private fun effectiveCatchupDays(channel: EnrichedChannel): Int {
    val explicitDays = channel.catchupDays.coerceIn(0, 7)
    if (explicitDays > 0) return explicitDays
    val source = channel.source
    val hasCatchupMetadata = !source.catchupType.isNullOrBlank() || !source.catchupSource.isNullOrBlank()
    if (hasCatchupMetadata) return 7
    val pathId = source.streamUrl.substringBefore('?')
        .trimEnd('/')
        .substringAfterLast('/')
        .substringBefore('.')
        .toIntOrNull()
    val looksLikeXtream = source.xtreamStreamId != null ||
        (pathId != null && source.streamUrl.contains("/live/", ignoreCase = true))
    return if (looksLikeXtream) 7 else 0
}

private fun ProgramPlacement.canFocus(channel: EnrichedChannel, nowMillis: Long): Boolean =
    !isPast || isCatchupSupported(channel, nowMillis)

private fun buildProgramPlacements(
    programs: List<IptvProgram>,
    windowStartMillis: Long,
    windowEndMillis: Long,
    nowMillis: Long
): List<ProgramPlacement> {
    val placements = mutableListOf<ProgramPlacement>()
    var cursor = windowStartMillis

    programs.forEach { program ->
        // 1. Fill gap before this program
        if (program.startUtcMillis > cursor) {
            val gapEnd = minOf(program.startUtcMillis, windowEndMillis)
            if (gapEnd > cursor) {
                // Clamp placeholder start to at most 60 mins before now to avoid horizontal scroll jumps
                var placeholderStart = maxOf(cursor, nowMillis - 60 * 60_000L)
                while (placeholderStart < gapEnd) {
                    val blockEnd = minOf(placeholderStart + 60 * 60_000L, gapEnd)
                    placements += ProgramPlacement(
                        program = IptvProgram("No Information", startUtcMillis = placeholderStart, endUtcMillis = blockEnd),
                        startMin = ((placeholderStart - windowStartMillis) / 60_000L).toInt(),
                        durationMin = ((blockEnd - placeholderStart) / 60_000L).toInt().coerceAtLeast(1),
                        isNow = nowMillis in placeholderStart until blockEnd,
                        isPast = blockEnd <= nowMillis,
                        isPlaceholder = true,
                    )
                    placeholderStart = blockEnd
                }
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
                isNow = nowMillis in clampedStart until clampedEnd,
                isPast = clampedEnd <= nowMillis
            )
            cursor = clampedEnd
        }
    }

    // 3. Fill trailing gap
    if (cursor < windowEndMillis) {
        // Clamp placeholder start to at most 60 mins before now to avoid horizontal scroll jumps
        var placeholderStart = maxOf(cursor, nowMillis - 60 * 60_000L)
        while (placeholderStart < windowEndMillis) {
            val blockEnd = minOf(placeholderStart + 60 * 60_000L, windowEndMillis)
            placements += ProgramPlacement(
                program = IptvProgram("No Information", startUtcMillis = placeholderStart, endUtcMillis = blockEnd),
                startMin = ((placeholderStart - windowStartMillis) / 60_000L).toInt(),
                durationMin = ((blockEnd - placeholderStart) / 60_000L).toInt().coerceAtLeast(1),
                isNow = nowMillis in placeholderStart until blockEnd,
                isPast = blockEnd <= nowMillis,
                isPlaceholder = true,
            )
            placeholderStart = blockEnd
        }
    }

    return placements
}
