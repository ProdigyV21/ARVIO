package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

private const val EpgWindowMinutes = 24 * 60
private const val EpgWindowSlotCount = EpgWindowMinutes / 30

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
    onChannelSelect: (EnrichedChannel, IptvProgram?) -> Unit,
    onChannelFocused: (EnrichedChannel) -> Unit = {},
    onChannelFavoriteToggle: (String) -> Unit,
    favorites: Set<String>,
    compact: Boolean = false,
    gridFocused: Boolean = false,
    onMoveLeftFromChannels: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val pxPerMin = LiveDims.EpgPxPerMinute
    val selectedChannelFocusRequester = remember { FocusRequester() }
    val headerHeight = if (compact) 32.dp else LiveDims.EpgHeaderHeight
    val channelColumnWidth = if (compact) 164.dp else LiveDims.EpgChannelColWidth
    val halfHourWidth = if (compact) 96.dp else LiveDims.EpgHalfHourWidth
    val rowHeight = if (compact) 52.dp else LiveDims.EpgRowHeight

    // Window: now − 30 min → now + 2 h = 2.5 h total.
    // Past is limited to 30 min so most of the ruler is future programmes
    // (what you're about to watch), not what already aired.
    var windowStartMillis by remember { mutableStateOf(roundedWindowStart()) }
    val windowEndMillis = remember(windowStartMillis) {
        windowStartMillis + EpgWindowMinutes * 60L * 1000L
    }
    // 5 half-hour slots across the window.
    val slots = remember(windowStartMillis) { buildHalfHourSlots(windowStartMillis, EpgWindowSlotCount) }

    // Shared horizontal scroll state between header and body rows.
    val hScroll = rememberScrollState()
    // Two separate vertical list states: one per LazyColumn.
    // A single LazyListState cannot be shared across two LazyColumns —
    // Compose asserts exclusive ownership and crashes on recomposition
    // when it detects two attached hosts. We keep them in lock-step via
    // snapshotFlow below.
    val channelListState = rememberLazyListState()
    val programListState = rememberLazyListState()
    var didPositionInitialSelection by remember(channels) { mutableStateOf(false) }

    // Two-way scroll sync without feedback loop.
    // A single leader token flips to whichever list registered a user
    // scroll last; only the leader's snapshotFlow writes to the other.
    // This survives focus-driven bringIntoView scrolls from either side.
    var leader by remember { mutableStateOf(0) } // 0=none, 1=program, 2=channel
    LaunchedEffect(programListState) {
        snapshotFlow { programListState.isScrollInProgress }
            .distinctUntilChanged()
            .collect { if (it) leader = 1 }
    }
    LaunchedEffect(channelListState) {
        snapshotFlow { channelListState.isScrollInProgress }
            .distinctUntilChanged()
            .collect { if (it) leader = 2 }
    }
    LaunchedEffect(channelListState, programListState) {
        snapshotFlow { programListState.firstVisibleItemIndex to programListState.firstVisibleItemScrollOffset }
            .distinctUntilChanged()
            .collect { (idx, off) ->
                if (leader != 1) return@collect
                if (channelListState.firstVisibleItemIndex != idx ||
                    channelListState.firstVisibleItemScrollOffset != off
                ) channelListState.scrollToItem(idx, off)
            }
    }
    LaunchedEffect(channelListState, programListState) {
        snapshotFlow { channelListState.firstVisibleItemIndex to channelListState.firstVisibleItemScrollOffset }
            .distinctUntilChanged()
            .collect { (idx, off) ->
                if (leader != 2) return@collect
                if (programListState.firstVisibleItemIndex != idx ||
                    programListState.firstVisibleItemScrollOffset != off
                ) programListState.scrollToItem(idx, off)
            }
    }

    val scope = rememberCoroutineScope()

    // Park NOW ~30 dp from the left so only a thin slice of the past is
    // visible and the rest of the viewport holds upcoming programmes.
    LaunchedEffect(Unit) {
        with(density) {
            val nowOffsetMin = ((clockTickMillis - windowStartMillis) / 60_000L).toInt()
            val targetPx = (nowOffsetMin * pxPerMin).dp.toPx().toInt() - 30.dp.toPx().toInt()
            hScroll.scrollTo(targetPx.coerceAtLeast(0))
        }
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
        leader = 0 // avoid triggering the two-way sync during the jump
        programListState.scrollToItem(idx)
        channelListState.scrollToItem(idx)
        didPositionInitialSelection = true
    }

    LaunchedEffect(focusSelectedChannelSignal, selectedChannelId, channels) {
        if (focusSelectedChannelSignal == 0) return@LaunchedEffect
        val id = selectedChannelId ?: return@LaunchedEffect
        val idx = channels.indexOfFirst { it.id == id }
        if (idx < 0) return@LaunchedEffect
        leader = 0
        programListState.scrollToItem(idx)
        channelListState.scrollToItem(idx)
        runCatching { selectedChannelFocusRequester.requestFocus() }
    }

    val days = remember {
        val base = roundedWindowStart()
        (0..6).map { i ->
            val start = base - i * 24 * 60 * 60_000L
            val label = when (i) {
                0 -> "TODAY"
                1 -> "YESTERDAY"
                else -> {
                    val cal = java.util.Calendar.getInstance()
                    cal.timeInMillis = start
                    cal.getDisplayName(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.SHORT, java.util.Locale.US)?.uppercase() ?: "DAY"
                }
            }
            EpgDay(label, start)
        }
    }
    var selectedDayIdx by remember { mutableIntStateOf(0) }
    LaunchedEffect(selectedDayIdx) {
        windowStartMillis = days[selectedDayIdx].startMillis
    }

    Column(
        modifier = modifier.fillMaxSize().background(LiveColors.Bg),
    ) {
        // ─── Day Selector ───────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LiveColors.PanelDeep)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("ARCHIVE", style = LiveType.SectionTag.copy(color = LiveColors.FgMute))
            days.forEachIndexed { idx, day ->
                val isSelected = selectedDayIdx == idx
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (isSelected) LiveColors.Accent else LiveColors.Panel)
                        .border(1.dp, if (isSelected) LiveColors.Accent else LiveColors.Divider, RoundedCornerShape(4.dp))
                        .pointerInput(Unit) { detectTapGestures { selectedDayIdx = idx } }
                        .focusable()
                        .onKeyEvent { ev ->
                            if (ev.type == KeyEventType.KeyDown && (ev.key == Key.DirectionCenter || ev.key == Key.Enter)) {
                                selectedDayIdx = idx; true
                            } else false
                        }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = day.label,
                        style = LiveType.Badge.copy(color = if (isSelected) LiveColors.Bg else LiveColors.Fg)
                    )
                }
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

        // Thin divider under header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(LiveColors.Divider),
        )

        // ─── Body ───────────────────────────────────────────────────
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxSize()) {
                // Channel column (sticky left, vertical scroll only)
                LazyColumn(
                    state = channelListState,
                    modifier = Modifier
                        .width(channelColumnWidth)
                        .fillMaxHeight()
                        .arvioDpadFocusGroup()
                        .background(LiveColors.PanelDeep),
                ) {
                    itemsIndexed(
                        channels,
                        key = { _, ch -> ch.id },
                        contentType = { _, _ -> "channel" }
                    ) { idx, ch ->
                        ChannelRow(
                            channel = ch,
                            isActive = ch.id == selectedChannelId,
                            clockTickMillis = clockTickMillis,
                            nowNext = nowNext[ch.id],
                            isFavorite = ch.id in favorites,
                            stripe = idx % 2 == 1,
                            onClick = { onChannelSelect(ch, null) },
                            onFocused = { onChannelFocused(ch) },
                            onMoveLeft = onMoveLeftFromChannels,
                            onFavoriteToggle = { onChannelFavoriteToggle(ch.id) },
                            rowHeight = rowHeight,
                            forceFocused = gridFocused && ch.id == selectedChannelId,
                            modifier = if (ch.id == selectedChannelId) {
                                Modifier.focusRequester(selectedChannelFocusRequester)
                            } else {
                                Modifier
                            },
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(LiveColors.Divider)
                )
                // Program grid (scrolls both ways, synced with above)
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        state = programListState,
                        userScrollEnabled = false,
                        modifier = Modifier
                            .fillMaxSize()
                            .horizontalScroll(hScroll),
                    ) {
                        itemsIndexed(
                            channels,
                            key = { _, ch -> ch.id },
                            contentType = { _, _ -> "programsRow" }
                        ) { idx, ch ->
                            // Memoise the windowed program list per channel.
                            // Without this, every vertical scroll tick triggers
                            // a full recomputation across every visible row —
                            // which turns the category-expand interaction into
                            // a stutter/ANR on lower-end TV boxes.
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
                                totalWidth = halfHourWidth * slots.size,
                                pxPerMin = pxPerMin,
                                stripe = idx % 2 == 1,
                                isActive = ch.id == selectedChannelId,
                                rowHeight = rowHeight,
                                onClick = { program -> onChannelSelect(ch, program) },
                                onFocused = { onChannelFocused(ch) },
                            )
                        }
                    }
                    // NOW glow line across full body
                    NowLine(
                        clockTickMillis = clockTickMillis,
                        windowStartMillis = windowStartMillis,
                        pxPerMin = pxPerMin,
                        hScrollOffsetPx = hScroll.value,
                    )
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
    pxPerMin: Int,
    stripe: Boolean,
    isActive: Boolean,
    rowHeight: Dp,
    onClick: (IptvProgram?) -> Unit,
    onFocused: () -> Unit,
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
        if (placements.isNotEmpty()) {
            placements.forEach { placement ->
                val offset = (placement.startMin * pxPerMin).dp
                val width = (placement.durationMin * pxPerMin).dp
                val isCatchupSupported = channel.catchupDays > 0 &&
                    placement.program.startUtcMillis >= System.currentTimeMillis() - channel.catchupDays * 24 * 60 * 60_000L
                ProgramCell(
                    program = placement.program,
                    clockTickMillis = clockTickMillis,
                    width = width,
                    isNow = placement.isNow,
                    isPast = placement.isPast,
                    isFocusTarget = placement.isNow,
                    focusable = !placement.isPast || isCatchupSupported,
                    isCatchupSupported = isCatchupSupported,
                    onClick = {
                        if (placement.isPast && isCatchupSupported) {
                            onClick(placement.program)
                        } else if (!placement.isPast) {
                            onClick(null)
                        }
                    },
                    onFocused = onFocused,
                    rowHeight = rowHeight,
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
    pxPerMin: Int,
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

private data class EpgDay(val label: String, val startMillis: Long)

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

/** Round down to the nearest half-hour, shifted 30 min back so the user
 *  can still see what just aired without the past dominating the viewport. */
private fun roundedWindowStart(): Long {
    val cal = java.util.Calendar.getInstance()
    cal.timeInMillis = System.currentTimeMillis()
    cal.set(java.util.Calendar.SECOND, 0)
    cal.set(java.util.Calendar.MILLISECOND, 0)
    val min = cal.get(java.util.Calendar.MINUTE)
    cal.set(java.util.Calendar.MINUTE, if (min >= 30) 30 else 0)
    return cal.timeInMillis - 30L * 60_000L
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
    val isPast: Boolean
)

private fun buildProgramPlacements(
    programs: List<IptvProgram>,
    windowStartMillis: Long,
    windowEndMillis: Long,
    nowMillis: Long
): List<ProgramPlacement> {
    if (programs.isEmpty()) return emptyList()

    val placements = mutableListOf<ProgramPlacement>()
    var cursor = windowStartMillis
    programs.forEach { program ->
        val clampedStart = maxOf(program.startUtcMillis, windowStartMillis, cursor)
        val clampedEnd = minOf(program.endUtcMillis, windowEndMillis)
        if (clampedEnd <= clampedStart) return@forEach

        placements += ProgramPlacement(
            program = program,
            startMin = ((clampedStart - windowStartMillis) / 60_000L).toInt().coerceAtLeast(0),
            durationMin = ((clampedEnd - clampedStart) / 60_000L).toInt().coerceAtLeast(1),
            isNow = nowMillis in clampedStart until clampedEnd,
            isPast = clampedEnd <= nowMillis
        )
        cursor = clampedEnd
    }
    return placements
}
