package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Precision
import androidx.compose.ui.platform.LocalDensity
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.SportsFollow
import com.arflix.tv.data.model.aroundHighlighted
import com.arflix.tv.data.model.SportsReminder
import androidx.compose.runtime.saveable.rememberSaveable
import com.arflix.tv.ui.focus.mirrorHorizontalForRtl
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextPrimary
import androidx.compose.ui.unit.LayoutDirection
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.animation.core.tween
import androidx.compose.ui.zIndex

/** Shared day label formatter for sports cards — was allocated per card per compose. */
private val sportsDayFormat: java.time.format.DateTimeFormatter =
    java.time.format.DateTimeFormatter.ofPattern("EEE d MMM", java.util.Locale.getDefault())

internal const val SPORTS_GUIDE_CATEGORY = "sports-hub"

/**
 * The last Sports rows built in this process, so reopening Sports doesn't start
 * from a spinner. Reused only for the same events (matched by id and start), so a
 * different playlist or profile never shows someone else's rows.
 */
internal object SportsPresentationCache {
    @Volatile private var key: List<String>? = null
    @Volatile private var rows: List<SportsGuideRow>? = null

    internal fun keyOf(events: List<SportsGuideEvent>): List<String> =
        events.map { it.id + "@" + it.programme.startUtcMillis }

    fun rowsFor(events: List<SportsGuideEvent>): List<SportsGuideRow>? {
        if (events.isEmpty()) return null
        val cached = rows ?: return null
        return cached.takeIf { key == keyOf(events) }
    }

    fun store(events: List<SportsGuideEvent>, built: List<SportsGuideRow>) {
        if (events.isEmpty() || built.isEmpty()) return
        rows = built
        key = keyOf(events)
    }

    internal fun clear() { key = null; rows = null }
}
internal const val SPORTS_CHANNEL_CATEGORY = "g-sports"

/** How much the focused Sports card grows (spacing between cards leaves room for it). */
internal const val SPORTS_CARD_FOCUS_SCALE = 1.07f

/**
 * Gives the match guide a home, preferring the one right next to the sport it
 * belongs to.
 *
 * When the playlist has a "Sports · Global" channel list, the guide goes inside
 * "All Channels" immediately before it: one is the fixtures, the other the
 * channels, and split across two levels they read as duplicates of each other.
 * Without that list — sport from addons, or an empty playlist — there is nothing
 * to sit beside, so the guide stays a top-level row and remains reachable.
 *
 * The label stays in English so grouping and comparison keep working;
 * liveCategoryLabel() localizes it at render time like the other categories.
 *
 * Applying this twice changes nothing.
 */
internal fun LiveCategoryTree.withSportsDestination(): LiveCategoryTree {
    val guide = LiveCategory(SPORTS_GUIDE_CATEGORY, "Sports", 0, CategoryIcon.Sport)
    val withoutGuide = top.filterNot { it.id == SPORTS_GUIDE_CATEGORY }.map { category ->
        if (category.id != "all") category
        else category.copy(children = category.children.filterNot { it.id == SPORTS_GUIDE_CATEGORY })
    }
    val beside = withoutGuide.any { category ->
        category.id == "all" && category.children.any { it.id == SPORTS_CHANNEL_CATEGORY }
    }
    if (!beside) {
        // No sports channels to pair with, so keep it at the top level where the
        // sports addons can still be reached.
        val all = withoutGuide.indexOfFirst { it.id == "all" }
        return copy(
            top = if (all < 0) withoutGuide + guide
            else withoutGuide.take(all + 1) + guide + withoutGuide.drop(all + 1),
        )
    }
    return copy(
        top = withoutGuide.map { category ->
            if (category.id != "all") category
            else category.copy(
                children = category.children.flatMap { child ->
                    if (child.id != SPORTS_CHANNEL_CATEGORY) listOf(child) else listOf(guide, child)
                },
            )
        },
    )
}

@Composable
internal fun SportsGuidePane(
    events: List<SportsGuideEvent>,
    now: Long,
    loading: Boolean,
    focusSignal: Int,
    onContentFocused: () -> Unit,
    onOpenCategories: () -> Unit,
    onPlay: (IptvChannel) -> Unit,
    modifier: Modifier = Modifier,
    failed: Boolean = false,
    onRetry: () -> Unit = {},
    providerNames: Map<String, String> = emptyMap(),
    sidebarOpen: Boolean = false,
    clockFormat: String? = null,
    showHeader: Boolean = true,
    onOpenSearch: (() -> Unit)? = null,
    resolveAddon: suspend (com.arflix.tv.data.model.SportsAddonEvent) -> List<com.arflix.tv.data.repository.SportsAddonStream> = { emptyList() },
    onPlayAddon: (com.arflix.tv.data.model.SportsAddonEvent, com.arflix.tv.data.repository.SportsAddonStream) -> Unit = { _, _ -> },
    follows: List<SportsFollow> = emptyList(),
    reminders: List<SportsReminder> = emptyList(),
    // The schedule feed's fixtures; the day tabs also show games without a channel.
    fixtures: List<com.arflix.tv.data.model.SportsEventArtwork> = emptyList(),
    loadDayFixtures: suspend (String) -> List<com.arflix.tv.data.model.SportsEventArtwork> = { emptyList() },
    onToggleFollow: (SportsFollow) -> Unit = {},
    onToggleReminder: (SportsGuideEvent, Boolean) -> Unit = { _, _ -> },
    // Channels of league networks (NBA TV, Sky Sports F1, ...) in the user's playlists.
    networkEvents: List<SportsGuideEvent> = emptyList(),
    // League or network key -> the channel last chosen for it on this device.
    channelMemory: Map<String, String> = emptyMap(),
    loadGameDetails: suspend (String) -> com.arflix.tv.data.model.SportsGameDetails? = { null },
    scoreAlerts: Boolean = true,
    onToggleScoreAlerts: (Boolean) -> Unit = {},
    // A channel picked on a game's sheet, with the game's other channels (best first) for failover.
    onPlayFromEvent: ((SportsGuideEvent, IptvChannel, List<IptvChannel>) -> Unit)? = null,
    // Multiview and recording: a game's channels (best first), and shortcuts above the rows.
    onMultiview: ((SportsGuideEvent, List<IptvChannel>) -> Unit)? = null,
    onRecord: ((SportsGuideEvent, List<IptvChannel>) -> Unit)? = null,
    recordedEventIds: Set<String> = emptySet(),
    onOpenMultiview: (() -> Unit)? = null,
    onOpenRecordings: (() -> Unit)? = null,
) {
    // Only a schedule refresh consumes this order. Focus alone must not rebuild
    // every catalogue and invalidate all visible lazy rows.
    val focusedOrder = remember { arrayOfNulls<Pair<String, List<String>>>(1) }
    // Reopening Sports shows the rows from the last visit straight away (when they
    // were built from the same events) instead of a spinner, then refreshes them.
    val cachedPresentation = remember(events) { SportsPresentationCache.rowsFor(events) }
    var presentationRows by remember { mutableStateOf(cachedPresentation.orEmpty()) }
    var presentationLoading by remember { mutableStateOf(cachedPresentation == null) }
    // The events the rows were last built from (identity), to tell "not built yet" from "empty".
    var builtFor by remember { mutableStateOf<List<SportsGuideEvent>?>(null) }
    // null = the overview; otherwise days from today (-7..7).
    var dayOffset by rememberSaveable { mutableStateOf<Int?>(null) }
    var sportsFilter by rememberSaveable { mutableStateOf(SPORTS_FILTER_ALL) }
    val zone = remember { ZoneId.systemDefault() }
    val todayDate = Instant.ofEpochMilli(now).atZone(zone).toLocalDate()
    val selectedDate = dayOffset?.let { todayDate.plusDays(it.toLong()) }
    var dayFixtures by remember { mutableStateOf<Pair<java.time.LocalDate, List<com.arflix.tv.data.model.SportsEventArtwork>>?>(null) }
    var dayLoading by remember { mutableStateOf(false) }
    LaunchedEffect(selectedDate) {
        val date = selectedDate ?: return@LaunchedEffect
        if (dayFixtures?.first == date) return@LaunchedEffect
        dayLoading = true
        try {
            val loaded = sportsUtcDaysFor(date, zone).flatMap { day -> runCatching { loadDayFixtures(day) }.getOrDefault(emptyList()) }
            dayFixtures = date to loaded
        } finally { dayLoading = false }
    }
    val labelMySports = stringResource(R.string.sports_my_sports)
    val labelLiveNow = stringResource(R.string.sports_filter_live)
    val dayExtra = dayFixtures?.takeIf { it.first == selectedDate }?.second.orEmpty()
    val labelNetworks = stringResource(R.string.sports_league_networks)
    LaunchedEffect(events, now, follows, sportsFilter, selectedDate, dayExtra, fixtures, networkEvents) {
        if (presentationRows.isEmpty()) presentationLoading = true
        try {
            val built = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Default) {
                if (selectedDate == null) {
                    val filtered = if (sportsFilter == SPORTS_FILTER_ALL) events else events.filter { it.matchesSportsFilter(sportsFilter, now) }
                    val mine = mySportsEvents(filtered, follows, now)
                    // An image failure must not remove/reparent the focused lazy item.
                    val base = sportsPresentationRows(filtered, now, emptySet())
                    if (sportsFilter == SPORTS_FILTER_ALL && follows.isEmpty()) SportsPresentationCache.store(events, base)
                    val networks = networkEvents.filter {
                        sportsFilter == SPORTS_FILTER_ALL || (sportsFilter != SPORTS_FILTER_LIVE && it.matchesSportsFilter(sportsFilter, now))
                    }
                    (if (mine.isEmpty()) emptyList() else listOf(SportsGuideRow(SPORTS_ROW_MINE, labelMySports, mine))) +
                        (if (networks.isEmpty()) emptyList() else listOf(SportsGuideRow(SPORTS_ROW_NETWORKS, labelNetworks, networks))) + base
                } else {
                    sportsEventsForDay(events, fixtures + dayExtra, selectedDate, zone)
                        .filter { it.matchesSportsFilter(sportsFilter, now) }
                        .let { sportsDayRows(it, follows, now) }
                        .map { row -> when (row.id) {
                            SPORTS_ROW_MINE -> row.copy(title = labelMySports)
                            "day-live" -> row.copy(title = labelLiveNow)
                            else -> row
                        } }
                }
            }
            if (built != presentationRows) presentationRows = built
            builtFor = events
        } finally {
            presentationLoading = false
        }
    }
    // The chips list every sport of the day (or of the overview), whatever filter is on.
    val filterSports = remember(events, selectedDate, dayExtra, fixtures) {
        if (selectedDate == null) sportsFilterOptions(events.filter { it.hasChannels(now) })
        else sportsFilterOptions(sportsEventsForDay(events, fixtures + dayExtra, selectedDate, zone))
    }
    val sportCounts = remember(events, selectedDate, dayExtra, fixtures, now) {
        val source = if (selectedDate == null) events.filter { it.hasChannels(now) }
            else sportsEventsForDay(events, fixtures + dayExtra, selectedDate, zone)
        source.groupingBy { it.sport }.eachCount()
    }
    val rows = remember(presentationRows) {
        val focusSnapshot = focusedOrder[0]
        val focusOrderRanks = focusSnapshot?.second.orEmpty().withIndex().associate { it.value to it.index }
        presentationRows.map { row ->
            if (row.id == focusSnapshot?.first) {
                row.copy(events = row.events.sortedBy { focusOrderRanks[it.id] ?: Int.MAX_VALUE })
            } else row
        }
    }
    var selected by remember { mutableStateOf<SportsGuideEvent?>(null) }
    // The game the D-pad is on, shown large above the rows. Only the preview reads it.
    var previewEvent by remember { mutableStateOf<SportsGuideEvent?>(null) }
    var returnFocus by remember { mutableStateOf<FocusRequester?>(null) }
    // Scores are shown everywhere on this page; the game info follows that.
    val showScore = true
    var showDetails by remember { mutableStateOf(false) }
    val firstFocus = remember { FocusRequester() }
    val categoriesFocus = remember { FocusRequester() }
    val dayChipsFocus = remember { FocusRequester() }
    var picker by remember { mutableStateOf<SportsPicker?>(null) }
    val sportPillFocus = remember { FocusRequester() }
    val liveCount = remember(events, now) { events.count { it.isOnAir(now) && it.hasChannels(now) } }
    val listState = rememberLazyListState()
    val rowStates = remember { mutableMapOf<String, LazyListState>() }
    val cardFocus = remember { mutableMapOf<Pair<String, String>, FocusRequester>() }
    val scope = rememberCoroutineScope()
    var navigationJob by remember { mutableStateOf<Job?>(null) }
    var navigationTarget by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var handledFocusSignal by remember { mutableIntStateOf(0) }
    fun leaveCards() {
        navigationJob?.cancel()
        navigationTarget = null
        onOpenCategories()
    }
    val keysByRow = remember(rows) {
        rows.associate { it.id to disambiguatedLazyKeys(it.events) { event -> event.id } }
    }
    val touchUi = com.arflix.tv.util.LocalDeviceType.current.isTouchDevice()
    // Up/Down lands on the card that is visually above/below, even when the two rows
    // are scrolled differently.
    fun alignedColumn(fromRow: Int, fromColumn: Int, toRow: Int): Int {
        val from = rows.getOrNull(fromRow)?.id?.let { rowStates[it] }?.firstVisibleItemIndex ?: 0
        val to = rows.getOrNull(toRow)?.id?.let { rowStates[it] }?.firstVisibleItemIndex ?: 0
        return (to + (fromColumn - from)).coerceAtLeast(0)
    }
    fun moveTo(rowIndex: Int, columnIndex: Int) {
        val row = rows.getOrNull(rowIndex) ?: return
        val column = columnIndex.coerceIn(0, row.events.lastIndex)
        val eventKey = keysByRow.getValue(row.id)[column]
        navigationJob?.cancel()
        navigationTarget = rowIndex to column
        // Already attached targets react in this key event, not after a scroll animation.
        val focusedImmediately = cardFocus[row.id to eventKey]?.let { runCatching { it.requestFocus() }.isSuccess } == true
        navigationJob = scope.launch {
            // Materialize the target before requesting focus. Compose 1.6's
            // beyond-bounds focus search can inspect a recycled row's coordinates.
            val verticalLayout = listState.layoutInfo
            val visibleRow = verticalLayout.visibleItemsInfo.firstOrNull { it.index == rowIndex }
            suspend fun reveal(state: LazyListState, index: Int) {
                val layout = state.layoutInfo
                val item = layout.visibleItemsInfo.firstOrNull { it.index == index }
                if (item == null) state.scrollToItem(index)
                else {
                    val delta = when {
                        item.offset < layout.viewportStartOffset -> item.offset - layout.viewportStartOffset
                        item.offset + item.size > layout.viewportEndOffset -> item.offset + item.size - layout.viewportEndOffset
                        else -> 0
                    }
                    if (delta != 0) state.animateScrollBy(delta.toFloat(), tween(100))
                }
            }
            // Scrolling may continue after focus moves; new keys replace this job.
            // On a TV the focused row moves to the top, so no half row (captions without
            // their cards) is left showing under the preview.
            launch {
                if (touchUi) reveal(listState, rowIndex)
                else {
                    // After focus has moved: focus's own bring-into-view scroll would
                    // otherwise cancel this one and leave a half row on top.
                    withFrameNanos { }
                    withFrameNanos { }
                    listState.animateScrollToItem(rowIndex)
                }
            }
            if (visibleRow == null) withFrameNanos { }
            rowStates[row.id]?.let { horizontal ->
                launch { reveal(horizontal, column) }
            }
            if (!focusedImmediately) repeat(8) {
                withFrameNanos { }
                val requester = cardFocus[row.id to eventKey]
                if (requester != null && runCatching { requester.requestFocus() }.isSuccess) {
                    navigationTarget = null
                    return@launch
                }
            }
            navigationTarget = null
        }
    }
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val touch = com.arflix.tv.util.LocalDeviceType.current.isTouchDevice()
    val narrow = configuration.screenWidthDp < 600
    val timeFormat = remember(context, clockFormat) {
        if (clockFormat == null) android.text.format.DateFormat.getTimeFormat(context)
        else java.text.SimpleDateFormat(if (clockFormat == "12h") "h:mm a" else "HH:mm", java.util.Locale.getDefault())
    }
    val today = Instant.ofEpochMilli(now).atZone(ZoneId.systemDefault()).toLocalDate()
    // Resolved up here because eventTime() is a plain local function, not a composable.
    val labelLive = stringResource(R.string.live_badge_live)
    val labelOnAir = stringResource(R.string.live_sports_badge_on_air)
    val labelScheduledNow = stringResource(R.string.live_sports_badge_scheduled_now)
    val labelToday = stringResource(R.string.live_label_today)
    val labelTomorrow = stringResource(R.string.live_label_tomorrow)
    val labelFinished = stringResource(R.string.sports_finished)
    val remindedIds = remember(reminders) { reminders.filter { !it.dismissed }.mapTo(hashSetOf()) { it.eventId } }
    fun eventTime(event: SportsGuideEvent): String {
        if (event.isFinished()) return labelFinished
        if (event.isConfirmedLive(now) || (event.channelOnly && event.isOnAir(now))) return labelLive
        if (event.isOnAir(now)) return labelLive
        if (event.isScheduledNow(now)) return labelScheduledNow
        val date = Instant.ofEpochMilli(event.programme.startUtcMillis).atZone(ZoneId.systemDefault())
        val day = when (date.toLocalDate()) {
            today -> labelToday
            today.plusDays(1) -> labelTomorrow
            else -> date.format(sportsDayFormat)
        }
        return "$day ${timeFormat.format(Date(event.programme.startUtcMillis))}"
    }
    LaunchedEffect(focusSignal, rows.isEmpty(), sidebarOpen) {
        if (!touch && focusSignal > handledFocusSignal && !sidebarOpen) {
            // Lazy cards are attached after the drawer starts its layout transition.
            withFrameNanos { }
            withFrameNanos { }
            if (focusSignal > handledFocusSignal) runCatching { firstFocus.requestFocus() }
        }
    }
    LaunchedEffect(selected) {
        if (!touch && selected == null) returnFocus?.let { runCatching { it.requestFocus() } }
    }
    CompositionLocalProvider(LocalTextStyle provides LocalTextStyle.current.copy(fontFamily = LiveFontFamily)) {
    BoxWithConstraints(modifier.fillMaxSize().background(LiveColors.Bg)) {
    // Use full-screen dimensions so opening the drawer never resizes loaded artwork.
    val viewport = configuration.screenWidthDp.dp
    val cardWidth = if (narrow) {
        180.dp
    } else {
        val columns = when {
            viewport >= 850.dp -> 5
            viewport >= 620.dp -> 3
            else -> 2
        }
        (viewport - 36.dp - 12.dp * (columns - 1)) / columns
    }
    // Art fills the card's width inside its 2.dp padding, at 16:9.
    val cardArtSize = remember(cardWidth) {
        val width = cardWidth - 4.dp
        androidx.compose.ui.unit.DpSize(width, width * 9f / 16f)
    }
    Column(Modifier.fillMaxSize()) {
        if (showHeader) {
            if (!sidebarOpen) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SportsHeaderIcon(
                        icon = Icons.AutoMirrored.Filled.ArrowBack,
                        description = stringResource(R.string.back),
                        modifier = Modifier
                            .focusRequester(categoriesFocus)
                            .onPreviewKeyEvent { key ->
                                if (key.key == Key.DirectionDown && key.type == KeyEventType.KeyDown) {
                                    // Toolbar first; on the empty page its Retry button.
                                    if (runCatching { dayChipsFocus.requestFocus() }.isFailure &&
                                        runCatching { firstFocus.requestFocus() }.isFailure) moveTo(0, 0)
                                    true
                                } else false
                            }
                            .padding(end = 0.dp),
                        onClick = onOpenCategories,
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = stringResource(R.string.live_quick_sports),
                        style = ArflixTypography.heroTitle.copy(fontSize = 24.sp),
                        color = TextPrimary,
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    if (onOpenSearch != null) {
                        SportsHeaderIcon(Icons.Default.Search, stringResource(R.string.search), onClick = onOpenSearch)
                    }
                }
            } else {
                Row(
                    Modifier.height(38.dp).padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(stringResource(R.string.live_quick_sports), color = LiveColors.Fg, fontSize = 18.sp, lineHeight = 22.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
        // Rows are built a frame after new events arrive; never flash an empty state then.
        val isProcessing = loading || presentationLoading ||
            (rows.isEmpty() && builtFor !== events)
        val browsing = dayOffset != null || sportsFilter != SPORTS_FILTER_ALL
        if (rows.isEmpty() && !browsing && isProcessing) {
            // Shaped like the page that is coming, so it does not jump when rows arrive.
            Column(Modifier.fillMaxSize().padding(top = 8.dp)) {
                Text(stringResource(R.string.live_sports_reading_schedule), color = LiveColors.FgDim, fontSize = 13.sp,
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp))
                SportsSkeletonRows(cardWidth)
            }
        } else if (rows.isEmpty() && !browsing) {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                run {
                    Icon(Icons.Default.SportsSoccer, null, tint = LiveColors.FgDim, modifier = Modifier.size(36.dp))
                    Text(
                        text = if (failed) stringResource(R.string.live_sports_schedule_unavailable)
                        else if (events.any { it.hasChannels(now) && (it.isOnAir(now) || it.programme.startUtcMillis > now) }) stringResource(R.string.live_sports_artwork_unavailable)
                        else stringResource(R.string.live_sports_no_events_matched),
                        color = LiveColors.FgDim,
                        modifier = Modifier.padding(14.dp),
                    )
                    Row(Modifier.padding(top = 6.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        SportsPill(stringResource(R.string.retry), null, touch = touch,
                            modifier = Modifier.focusRequester(firstFocus)) { onRetry() }
                        SportsPill(stringResource(R.string.live_groups_title), null, touch = touch, onClick = onOpenCategories)
                    }
                }
            }
        } else Column(Modifier.fillMaxSize()) {
            SportsToolbar(
                dayLabel = sportsDayLabel(dayOffset, todayDate),
                dayActive = dayOffset != null,
                sportLabel = filterSports.firstOrNull { it.name == sportsFilter }?.title ?: stringResource(R.string.sports_filter_all),
                sport = filterSports.firstOrNull { it.name == sportsFilter },
                liveOn = sportsFilter == SPORTS_FILTER_LIVE,
                liveCount = liveCount,
                touch = touch,
                firstFocus = dayChipsFocus,
                sportFocus = sportPillFocus,
                onPickDay = { picker = SportsPicker.Day },
                onPickSport = { picker = SportsPicker.Sport },
                onToggleLive = {
                    sportsFilter = if (sportsFilter == SPORTS_FILTER_LIVE) SPORTS_FILTER_ALL else SPORTS_FILTER_LIVE
                    // Live means now, so it always shows the overview, whatever day was picked.
                    if (sportsFilter == SPORTS_FILTER_LIVE) dayOffset = null
                    focusedOrder[0] = null
                },
                onUp = { if (showHeader && !sidebarOpen) runCatching { categoriesFocus.requestFocus() } },
                onDown = { if (rows.isNotEmpty()) moveTo(0, 0) },
                onLeaveStart = ::leaveCards,
                onMultiview = onOpenMultiview,
                onRecordings = onOpenRecordings,
            )
            if (rows.isEmpty() && (dayLoading || isProcessing)) SportsSkeletonRows(cardWidth, Modifier.weight(1f).padding(top = 8.dp))
            else if (rows.isEmpty()) Column(Modifier.fillMaxWidth().weight(1f), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.SportsSoccer, null, tint = LiveColors.FgMute, modifier = Modifier.size(32.dp))
                Text(stringResource(R.string.sports_no_games_selection), color = LiveColors.FgDim, modifier = Modifier.padding(14.dp))
            } else {
            if (!touch && !narrow) SportsFocusPreview(
                eventProvider = {
                    val current = previewEvent
                    current?.let { focusedEvent -> rows.firstNotNullOfOrNull { row -> row.events.firstOrNull { it.id == focusedEvent.id } } }
                        ?: rows.firstOrNull()?.events?.firstOrNull()
                },
                timeLabel = ::eventTime,
                isLive = { it.isOnAir(now) && !it.isFinished() },
                scoreOf = { it.scoreLine(now) },
                liveLabel = labelLive,
                channelNames = { event ->
                    ((if (event.isOnAir(now)) event.availableChannels(now) else event.channels) + event.possibleChannels)
                        .distinctBy { it.id }.map { it.name }
                },
            )
            LazyColumn(Modifier.weight(1f).fillMaxWidth().clipToBounds().testTag("sports-guide-list"), state = listState, contentPadding = PaddingValues(bottom = 24.dp + WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()),
            verticalArrangement = Arrangement.spacedBy(2.dp)) {
            val rowKeys = disambiguatedLazyKeys(rows) { it.id }
            itemsIndexed(rows, key = { index, _ -> rowKeys[index] }, contentType = { _, _ -> "sports-row" }) { rowIndex, row ->
                val rowState = rememberLazyListState()
                DisposableEffect(row.id, rowState) {
                    rowStates[row.id] = rowState
                    onDispose { rowStates.remove(row.id) }
                }
                Column {
                    SportsRowTitle(row.title, row.events.size, touch)
                    val eventKeys = keysByRow.getValue(row.id)
                    // Inner padding leaves room for the focused card's lift without clipping;
                    // the outer padding shrinks by the same amount so cards stay aligned.
                    LazyRow(Modifier.padding(horizontal = 8.dp), state = rowState, contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        itemsIndexed(row.events, key = { index, _ -> eventKeys[index] }, contentType = { _, _ -> "sports-card" }) { index, event ->
                            val scheduleOnly = row.id.endsWith("-schedule")
                            val itemRequester = remember { FocusRequester() }
                            val requester = if (index == 0 && rowIndex == 0) firstFocus else itemRequester
                            DisposableEffect(row.id, eventKeys[index], requester) {
                                val key = row.id to eventKeys[index]
                                cardFocus[key] = requester
                                onDispose { cardFocus.remove(key) }
                            }
                            var focused by remember { mutableStateOf(false) }
                            // A small lift on the focused card. Read only inside graphicsLayer,
                            // so the animation redraws the card without recomposing or relayout.
                            val focusScale by androidx.compose.animation.core.animateFloatAsState(
                                targetValue = if (focused && !touch) SPORTS_CARD_FOCUS_SCALE else 1f,
                                animationSpec = tween(180, easing = androidx.compose.animation.core.FastOutSlowInEasing),
                                label = "sportsCardFocusScale",
                            )
                            Column(Modifier.width(cardWidth).testTag("sports-event-card")
                                .zIndex(if (focused) 1f else 0f)
                                .graphicsLayer { scaleX = focusScale; scaleY = focusScale }
                                .focusRequester(requester)
                                .onFocusChanged {
                                    focused = it.isFocused
                                    if (it.isFocused) {
                                        previewEvent = event
                                        handledFocusSignal = focusSignal
                                        onContentFocused()
                                        if (focusedOrder[0]?.first != row.id) {
                                            focusedOrder[0] = row.id to row.events.map { it.id }
                                        }
                                    }
                                }
                                .onPreviewKeyEvent { key ->
                                    val direction = key.key.mirrorHorizontalForRtl(isRtl)
                                    if (direction !in listOf(Key.DirectionUp, Key.DirectionDown, Key.DirectionLeft, Key.DirectionRight)) false
                                    else {
                                        if (key.type == KeyEventType.KeyDown) when (direction) {
                                            Key.DirectionUp -> if ((navigationTarget?.first ?: rowIndex) > 0) {
                                                    val fromRow = navigationTarget?.first ?: rowIndex
                                                    moveTo(fromRow - 1, alignedColumn(fromRow, navigationTarget?.second ?: index, fromRow - 1))
                                                }
                                                else {
                                                    navigationJob?.cancel(); navigationTarget = null
                                                    if (runCatching { dayChipsFocus.requestFocus() }.isFailure) {
                                                        if (sidebarOpen) leaveCards() else runCatching { categoriesFocus.requestFocus() }
                                                    }
                                                }
                                            Key.DirectionDown -> {
                                                val fromRow = navigationTarget?.first ?: rowIndex
                                                moveTo(fromRow + 1, alignedColumn(fromRow, navigationTarget?.second ?: index, fromRow + 1))
                                            }
                                            Key.DirectionLeft -> if ((navigationTarget?.second ?: index) == 0) leaveCards() else moveTo(navigationTarget?.first ?: rowIndex, (navigationTarget?.second ?: index) - 1)
                                            Key.DirectionRight -> moveTo(navigationTarget?.first ?: rowIndex, (navigationTarget?.second ?: index) + 1)
                                        }
                                        true
                                    }
                                }
                                .clickable {
                                    navigationJob?.cancel(); navigationTarget = null
                                    returnFocus = requester; showDetails = false; selected = event
                                }
                                .padding(2.dp)) {
                                val score = event.scoreLine(now)
                                if (scheduleOnly) Row(Modifier.fillMaxWidth().height(36.dp)
                                    .clip(SportsCardShape).background(Color.White.copy(alpha = if (focused) 1f else .07f))
                                    .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Text(eventTime(event), color = if (focused) Color(0xFF0B0B0D) else LiveColors.Fg, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                    if (event.id in remindedIds) Icon(Icons.Default.NotificationsActive, null, tint = if (focused) Color(0xFF0B0B0D) else LiveColors.Fg,
                                        modifier = Modifier.padding(start = 6.dp).size(12.dp))
                                } else Box(Modifier.fillMaxWidth().aspectRatio(16f / 9f).testTag("sports-event-art").clip(SportsCardShape)) {
                                    EventArtwork(event, Modifier.fillMaxSize(), knownSize = cardArtSize)
                                    // Status top-left: LIVE, Finished or the kick-off time.
                                    Row(Modifier.padding(7.dp), verticalAlignment = Alignment.CenterVertically) {
                                        if (event.isOnAir(now) && !event.isFinished()) SportsLivePill(labelLive)
                                        else SportsInfoPill(eventTime(event))
                                        if (event.id in remindedIds) Icon(Icons.Default.NotificationsActive, null, tint = Color.White,
                                            modifier = Modifier.padding(start = 5.dp).size(13.dp))
                                    }
                                    // Score bottom-right, always visible for live and finished games.
                                    if (score != null) SportsInfoPill(score.text(), strong = true,
                                        modifier = Modifier.align(Alignment.BottomEnd).padding(7.dp).testTag("sports-card-score"))
                                    Box(Modifier.matchParentSize().liveFocusOutline(focused, 10.dp))
                                }
                                Text(event.displayTitle(), color = LiveColors.Fg, fontSize = if (touch) 14.sp else SportsTitleSize,
                                    lineHeight = if (touch) 18.sp else 16.sp, fontWeight = FontWeight.SemiBold,
                                    maxLines = 1, overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(top = 7.dp, start = 2.dp, end = 2.dp))
                                Row(Modifier.fillMaxWidth().padding(top = 2.dp, start = 2.dp, end = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Text(listOfNotNull(event.competition ?: event.sport.title).joinToString(" · "), color = LiveColors.FgDim,
                                        fontSize = if (touch) 12.sp else 11.sp, lineHeight = 14.sp,
                                        maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                                    val count = remember(event, now) {
                                        ((if (event.isOnAir(now)) event.availableChannels(now) else event.channels) + event.possibleChannels).distinctBy { it.id }.size + event.addonSources.size
                                    }
                                    if (count > 0) {
                                        Icon(Icons.Default.Tv, null, tint = LiveColors.FgMute, modifier = Modifier.padding(start = 6.dp).size(12.dp))
                                        Text(count.toString(), color = LiveColors.FgMute, fontSize = 11.sp, lineHeight = 14.sp,
                                            modifier = Modifier.padding(start = 3.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
            }
        }
    }
    }
    when (picker) {
        SportsPicker.Day -> SportsPickerDialog(
            title = stringResource(R.string.sports_pick_day),
            options = sportsDayOptions(todayDate),
            selectedKey = dayOffset?.toString() ?: SPORTS_DAY_OVERVIEW,
            onPick = { key ->
                dayOffset = key.toIntOrNull()
                if (dayOffset != null && sportsFilter == SPORTS_FILTER_LIVE) sportsFilter = SPORTS_FILTER_ALL
                focusedOrder[0] = null
                picker = null
            },
            onDismiss = { picker = null },
        )
        SportsPicker.Sport -> SportsPickerDialog(
            title = stringResource(R.string.sports_pick_sport),
            options = listOf(SportsPickerOption(SPORTS_FILTER_ALL, stringResource(R.string.sports_filter_all))) +
                filterSports.map { SportsPickerOption(it.name, it.title, sportCounts[it]?.toString(), sport = it) },
            selectedKey = if (sportsFilter == SPORTS_FILTER_LIVE) SPORTS_FILTER_ALL else sportsFilter,
            onPick = { key ->
                sportsFilter = key
                focusedOrder[0] = null
                picker = null
            },
            onDismiss = { picker = null },
        )
        null -> Unit
    }
    // Back on the pill that opened the picker once it closes.
    var lastPicker by remember { mutableStateOf<SportsPicker?>(null) }
    LaunchedEffect(picker) {
        val closed = lastPicker
        lastPicker = picker
        if (picker == null && closed != null && !touch) {
            withFrameNanos { }
            withFrameNanos { }
            runCatching { (if (closed == SportsPicker.Sport) sportPillFocus else dayChipsFocus).requestFocus() }
        }
    }
    val selection = selected
    val event = selection?.let { snapshot -> events.firstOrNull { it.id == snapshot.id }
        // Day tabs also list games known only from the schedule feed.
        ?: rows.firstNotNullOfOrNull { row -> row.events.firstOrNull { it.id == snapshot.id } } }
    fun dismiss() { selected = null }
    if (selection != null) Dialog(onDismissRequest = ::dismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)) {
        val window = (LocalView.current.parent as? DialogWindowProvider)?.window
        SideEffect {
            window?.addFlags(android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            window?.setDimAmount(.65f)
        }
        val onAir = event?.isOnAir(now) == true
        val canOpenChannel = onAir || event?.isScheduledNow(now) == true
        val confirmedChannels = if (onAir) event?.availableChannels(now).orEmpty() else event?.channels.orEmpty()
        val possibleIds = event?.possibleChannels.orEmpty().mapTo(hashSetOf()) { it.id }
        val memoryKeys = (event ?: selection).channelMemoryKeys()
        val sourceChannels = (confirmedChannels + event?.possibleChannels.orEmpty()).distinctBy { it.id }
            .withRememberedFirst(channelMemory, memoryKeys)
        val rememberedId = memoryKeys.firstNotNullOfOrNull { channelMemory[it] }
        val initialFocus = remember(event?.id) { FocusRequester() }
        LaunchedEffect(event?.id, sourceChannels.isEmpty()) {
            if (touch) return@LaunchedEffect
            // The native dialog window must own focus before Compose assigns its row.
            withFrameNanos { }
            withFrameNanos { }
            runCatching { initialFocus.requestFocus() }
        }
        Column(Modifier.widthIn(max = 586.dp).fillMaxWidth().heightIn(max = configuration.screenHeightDp.dp * .82f)
            .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp)).background(LiveColors.Panel).padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                EventArtwork(event ?: selection, Modifier.width(if (narrow) 104.dp else 160.dp).aspectRatio(16f / 9f).clip(SportsCardShape),
                    score = (event ?: selection).scoreLine(now))
                Column(Modifier.weight(1f).padding(start = 14.dp)) {
                    Text(listOfNotNull(event?.let(::eventTime), event?.sport?.title).joinToString("  ·  "),
                        color = LiveColors.FgDim, fontSize = 11.sp)
                    Text(event?.title ?: selection.title, fontSize = 18.sp, lineHeight = 21.sp, maxLines = 2,
                        overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.SemiBold, color = LiveColors.Fg,
                        modifier = Modifier.padding(top = 7.dp))
                }
                var closeFocused by remember { mutableStateOf(false) }
                Icon(Icons.Default.Close, stringResource(R.string.close), tint = LiveColors.Fg,
                    modifier = Modifier.size(44.dp)
                        .then(if (sourceChannels.isEmpty() && event?.addonSources.orEmpty().isEmpty()) Modifier.focusRequester(initialFocus) else Modifier)
                        .onFocusChanged { closeFocused = it.isFocused }
                        .liveFocusOutline(closeFocused, 4.dp)
                        .clickable(onClick = ::dismiss).padding(10.dp))
            }
            event?.fixture?.let { fixture ->
                Text(listOfNotNull(event.competition, fixture.venue, fixture.round?.takeIf { it.isNotBlank() && it != "0" }?.let { stringResource(R.string.live_sports_round, it) }).joinToString(" · "),
                    color = LiveColors.FgDim, fontSize = 11.sp, maxLines = 2, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 10.dp))
                // Chips, not plain text: on a TV each needs a visible focus state.
                Row(Modifier.padding(top = 10.dp).horizontalScroll(androidx.compose.foundation.rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (!event.isNetwork) {
                        SportsActionChip(stringResource(R.string.sports_channels_tab), Icons.Default.Tv, active = !showDetails,
                            modifier = Modifier.testTag("sports-channels-tab")) { showDetails = false }
                        SportsActionChip(stringResource(R.string.sports_game_info), Icons.Default.Info, active = showDetails,
                            modifier = Modifier.testTag("sports-game-info")) { showDetails = true }
                    }
                }
            }
            val actionEvent = event ?: selection
            if (!actionEvent.isNetwork) SportsEventActions(
                event = actionEvent,
                now = now,
                reminded = actionEvent.id in remindedIds,
                follows = follows,
                onToggleReminder = { on -> onToggleReminder(actionEvent, on) },
                onToggleFollow = onToggleFollow,
                scoreAlerts = scoreAlerts,
                onToggleScoreAlerts = onToggleScoreAlerts,
            )
            if (!actionEvent.isNetwork && event != null && sourceChannels.isNotEmpty()) SportsWatchActions(
                canWatch = canOpenChannel,
                canRecord = !event.isFinished() && event.programme.endUtcMillis > now,
                recorded = event.id in recordedEventIds,
                onMultiview = onMultiview?.let { add -> { dismiss(); add(event, sourceChannels) } },
                onRecord = onRecord?.let { record -> { record(event, sourceChannels) } },
            )
            val detailsFixture = if (showDetails && event != null && !event.isNetwork) event.fixture else null
            if (detailsFixture != null && event != null) {
                SportsGameInfo(
                    fixture = detailsFixture,
                    teams = event.teamNames(),
                    showScore = showScore,
                    spoilerSensitive = event.isFinished() || event.isConfirmedLive(now),
                    load = loadGameDetails,
                    modifier = Modifier.weight(1f, fill = false).heightIn(max = configuration.screenHeightDp.dp * .55f),
                )
            } else {
            Row(Modifier.fillMaxWidth().padding(top = 9.dp, bottom = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(if (event?.addonSources.orEmpty().isNotEmpty()) stringResource(R.string.sources) else if (onAir) stringResource(R.string.live_sports_channels) else stringResource(R.string.live_sports_scheduled_channels), fontSize = 14.sp, color = LiveColors.Fg,
                    fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
                Text(if (event?.addonSources.orEmpty().isEmpty()) channelCount(sourceChannels.size)
                    else stringResource(R.string.live_sports_source_count, sourceChannels.size + event?.addonSources.orEmpty().size), fontSize = 11.sp, color = LiveColors.FgDim)
            }
            val channelKeys = remember(sourceChannels) { disambiguatedLazyKeys(sourceChannels) { it.id } }
            LazyColumn(Modifier.weight(1f, fill = false).heightIn(max = configuration.screenHeightDp.dp * .55f)) {
                itemsIndexed(sourceChannels, key = { index, _ -> channelKeys[index] }, contentType = { _, _ -> "sports-channel" }) { index, channel ->
                    var focused by remember { mutableStateOf(false) }
                    Row(Modifier.fillMaxWidth().heightIn(min = if (narrow) 48.dp else 40.dp).clip(RoundedCornerShape(8.dp))
                        .then(if (index == 0) Modifier.focusRequester(initialFocus) else Modifier)
                        .border(2.dp, if (focused) Color.White else Color.Transparent, RoundedCornerShape(8.dp))
                        .background(if (focused) Color.White.copy(alpha = .12f) else Color.Transparent)
                        .onFocusChanged { focused = it.isFocused }
                        // Scheduled channels are browsable with a remote, but cannot play early.
                        .focusable(enabled = !canOpenChannel)
                        .clickable(enabled = canOpenChannel) {
                            val time = System.currentTimeMillis()
                            if (event != null && (event.isOnAir(time) || event.isScheduledNow(time)) && (event.availableChannels(time).any { it.id == channel.id } || channel.id in possibleIds)) {
                                dismiss()
                                if (onPlayFromEvent != null) onPlayFromEvent(event, channel, sourceChannels) else onPlay(channel)
                            }
                        }.padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        val logoChannel = remember(channel) { channel.enrichForFastStartup(0) }
                        ChannelLogo(logoChannel, 30.dp, Modifier.size(if (narrow) 40.dp else 88.dp, 30.dp))
                        Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                            Text(channel.name, color = LiveColors.Fg, fontSize = 13.sp, lineHeight = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            val provider = providerNames[channelPlaylistId(channel.id)]
                            val kind = when {
                                channel.id == rememberedId && sourceChannels.size > 1 -> stringResource(R.string.sports_last_used_channel)
                                event?.isNetwork == true -> stringResource(R.string.sports_league_network)
                                channel.id in possibleIds -> stringResource(R.string.live_sports_possible_broadcast)
                                else -> stringResource(R.string.live_sports_guide_match)
                            }
                            Text("${provider ?: channel.group.orEmpty()} · $kind", color = LiveColors.FgDim, fontSize = 10.sp, lineHeight = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }
                        val enriched = remember(channel) { channel.enrich(0) }
                        if (enriched.quality != Quality.UNKNOWN) PickerBadge(enriched.quality.label)
                        // EN was a guessed fallback, not provider metadata.
                        channel.language?.takeIf { it.isNotBlank() }?.let { PickerBadge(it.uppercase()) }
                        Icon(if (focused) Icons.Default.PlayArrow else Icons.Outlined.ChevronRight, null,
                            tint = LiveColors.Fg, modifier = Modifier.padding(start = 12.dp).size(18.dp))
                    }
                    Box(Modifier.fillMaxWidth().height(1.dp).background(LiveColors.Divider))
                }
                items(event?.addonSources.orEmpty(), key = { "addon:${it.key}" }, contentType = { "SportsGuidePane.event" }) { source ->
                    SportsAddonSourceRow(source, (canOpenChannel || source.isLive(now)) && (source.startsAt == null || source.startsAt <= now), resolveAddon,
                        if (sourceChannels.isEmpty() && source == event?.addonSources?.firstOrNull()) initialFocus else null) { stream ->
                        onPlayAddon(source, stream)
                        dismiss()
                    }
                }
            }
            if (sourceChannels.isEmpty() && event?.addonSources.orEmpty().isEmpty()) Text(stringResource(R.string.live_sports_no_matching_channels), color = LiveColors.FgDim,
                fontSize = 12.sp, modifier = Modifier.padding(vertical = 20.dp))
            }
            if (event == null) Text(stringResource(R.string.live_sports_event_gone), color = LiveColors.FgDim)
        }
    }
    }
}

@Composable
private fun SportsAddonSourceRow(
    event: com.arflix.tv.data.model.SportsAddonEvent,
    canPlay: Boolean,
    resolve: suspend (com.arflix.tv.data.model.SportsAddonEvent) -> List<com.arflix.tv.data.repository.SportsAddonStream>,
    initialFocus: FocusRequester?,
    onPlay: (com.arflix.tv.data.repository.SportsAddonStream) -> Unit,
) {
    var sources by remember(event.key) { mutableStateOf<List<com.arflix.tv.data.repository.SportsAddonStream>?>(null) }
    var loading by remember(event.key) { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    Column {
        var focused by remember { mutableStateOf(false) }
        Row(Modifier.fillMaxWidth().heightIn(min = 44.dp).then(initialFocus?.let { Modifier.focusRequester(it) } ?: Modifier).liveFocusOutline(focused, 4.dp)
            .onFocusChanged { focused = it.isFocused }.clickable {
                if (!loading) scope.launch {
                    loading = true
                    try { sources = resolve(event) }
                    catch (_: kotlinx.coroutines.TimeoutCancellationException) { sources = emptyList() }
                    catch (e: kotlinx.coroutines.CancellationException) { throw e }
                    catch (_: Exception) { sources = emptyList() }
                    finally { loading = false }
                }
            }.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Tv, null, tint = LiveColors.Fg, modifier = Modifier.size(22.dp))
            Column(Modifier.weight(1f).padding(start = 12.dp)) {
                Text(event.addonName, color = LiveColors.Fg, fontSize = 13.sp)
                Text(stringResource(if (sources?.isEmpty() == true) R.string.live_sports_addon_retry else R.string.live_sports_addon_sources),
                    color = LiveColors.FgDim, fontSize = 10.sp)
            }
            if (loading) CircularProgressIndicator(Modifier.size(18.dp), color = LiveColors.Fg, strokeWidth = 2.dp)
            else Icon(Icons.Outlined.ChevronRight, null, tint = LiveColors.Fg, modifier = Modifier.size(20.dp))
        }
        sources.orEmpty().forEach { stream ->
            var focused by remember(stream) { mutableStateOf(false) }
            Row(Modifier.fillMaxWidth().heightIn(min = 44.dp).liveFocusOutline(focused, 4.dp)
                .onFocusChanged { focused = it.isFocused }.clickable(enabled = !loading && (canPlay || stream.external)) { onPlay(stream) }
                .focusable(enabled = !canPlay && !stream.external).padding(horizontal = 24.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(stream.name, color = LiveColors.Fg, fontSize = 12.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    stream.description?.let { Text(it, color = LiveColors.FgDim, fontSize = 10.sp, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                    if (stream.external) Text(stringResource(R.string.live_sports_external_source), color = LiveColors.FgDim, fontSize = 10.sp)
                }
                Icon(Icons.Default.PlayArrow, null, tint = LiveColors.Fg, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
private fun channelCount(count: Int) =
    if (count == 1) stringResource(R.string.live_channel_count_one, count)
    else liveChannelsLabel(count)

/**
 * A game's picture. The generated match art (sport photo + teams) is always drawn, and a
 * real banner fades in over it once it has loaded, so a card is never empty or grey.
 */
@Composable
private fun EventArtwork(
    event: SportsGuideEvent,
    modifier: Modifier = Modifier,
    // The card's art size when the caller already knows it. Skips BoxWithConstraints,
    // whose extra subcomposition per card showed up while scrolling Sports rows.
    knownSize: androidx.compose.ui.unit.DpSize? = null,
    large: Boolean = false,
    score: SportsScoreLine? = null,
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val bannerUrl = event.artwork?.takeIf { it.isNotBlank() }
    var loaded by remember(bannerUrl) { mutableStateOf(false) }
    val bannerAlpha by androidx.compose.animation.core.animateFloatAsState(if (loaded) 1f else 0f, tween(220), label = "sports-banner-fade")
    val boxModifier = modifier.background(LiveColors.Panel).testTag(if (loaded) "sports-artwork-loaded" else "sports-artwork-pending")
    val layers: @Composable (Int, Int) -> Unit = { artW, artH ->
        // Decode once per URL at card size and keep the request anchored across
        // recompositions (clock ticks, D-pad focus) via remember + key().
        val bannerRequest = remember(bannerUrl, artW, artH) {
            bannerUrl?.let { url ->
                ImageRequest.Builder(context)
                    .data(url)
                    .size(artW, artH)
                    .precision(Precision.INEXACT)
                    .allowHardware(true)
                    .crossfade(false)
                    .memoryCacheKey("sports-banner|$url|${artW}x$artH")
                    .placeholderMemoryCacheKey("sports-banner|$url|${artW}x$artH")
                    .build()
            }
        }
        if (!loaded || bannerAlpha < 1f) SportsMatchupArt(event, artW, artH, Modifier.fillMaxSize(), large = large, score = score)
        bannerRequest?.let { request ->
            key(bannerUrl) {
                AsyncImage(request, null, contentScale = ContentScale.Crop,
                    onSuccess = { loaded = true }, onError = { loaded = false },
                    modifier = Modifier.fillMaxSize().graphicsLayer { alpha = bannerAlpha })
            }
        }
    }
    if (knownSize != null) {
        val artW = with(density) { knownSize.width.roundToPx() }.coerceAtLeast(1)
        val artH = with(density) { knownSize.height.roundToPx() }.coerceAtLeast(1)
        Box(boxModifier) { layers(artW, artH) }
    } else BoxWithConstraints(boxModifier) {
        layers(with(density) { maxWidth.roundToPx() }.coerceAtLeast(1), with(density) { maxHeight.roundToPx() }.coerceAtLeast(1))
    }
}

/**
 * The focused game, large, above the Sports rows.
 * Reads the focused event through [eventProvider] so moving focus recomposes only
 * this banner, never the rows.
 */
@Composable
private fun SportsFocusPreview(
    eventProvider: () -> SportsGuideEvent?,
    timeLabel: (SportsGuideEvent) -> String,
    isLive: (SportsGuideEvent) -> Boolean,
    channelNames: (SportsGuideEvent) -> List<String>,
    modifier: Modifier = Modifier,
    scoreOf: (SportsGuideEvent) -> SportsScoreLine? = { null },
    liveLabel: String = "LIVE",
) {
    val event = eventProvider()
    Row(
        modifier.fillMaxWidth().zIndex(1f).background(LiveColors.Bg).height(SportsPreviewHeight).padding(horizontal = 18.dp, vertical = 8.dp)
            .testTag("sports-focus-preview"),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        if (event == null) return@Row
        val score = scoreOf(event)
        Box(Modifier.width(SportsPreviewArtWidth).aspectRatio(16f / 9f).clip(SportsCardShape)) {
            key(event.id) {
                EventArtwork(event, Modifier.fillMaxSize(),
                    knownSize = androidx.compose.ui.unit.DpSize(SportsPreviewArtWidth, SportsPreviewArtWidth * 9f / 16f))
            }
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(listOfNotNull(event.sport.title, event.competition).joinToString(" · "),
                color = LiveColors.FgDim, fontSize = 13.sp, lineHeight = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(event.displayTitle(), color = LiveColors.Fg, fontSize = 24.sp, lineHeight = 28.sp, fontWeight = FontWeight.SemiBold,
                    maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f, fill = false).testTag("sports-focus-preview-title"))
                if (score != null) Text(score.text(), color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp).testTag("sports-focus-preview-score"))
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                if (isLive(event)) SportsLivePill(liveLabel, large = true) else SportsInfoPill(timeLabel(event), large = true)
                val names = channelNames(event)
                if (names.isNotEmpty()) {
                    Icon(Icons.Default.Tv, null, tint = LiveColors.FgDim, modifier = Modifier.size(14.dp))
                    Text(names.take(3).joinToString(", ") + if (names.size > 3) " +${names.size - 3}" else "",
                        color = LiveColors.FgDim, fontSize = 12.sp, lineHeight = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

private val SportsPreviewHeight = 124.dp
private val SportsPreviewArtWidth = 192.dp

@Composable
private fun PickerBadge(label: String) {
    Text(label, color = LiveColors.Fg, fontSize = 10.sp, lineHeight = 12.sp, modifier = Modifier.padding(start = 8.dp)
        .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(8.dp)).background(LiveColors.Bg)
        .padding(horizontal = 7.dp, vertical = 3.dp))
}


/** Remind me, and follow either team or the competition, from a game's detail sheet. */
@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun SportsEventActions(
    event: SportsGuideEvent,
    now: Long,
    reminded: Boolean,
    follows: List<SportsFollow>,
    onToggleReminder: (Boolean) -> Unit,
    onToggleFollow: (SportsFollow) -> Unit,
    scoreAlerts: Boolean = true,
    onToggleScoreAlerts: (Boolean) -> Unit = {},
) {
    val canRemind = !event.isFinished() && !event.isOnAir(now) && event.programme.startUtcMillis > now
    val options = remember(event.id, event.title, event.competition) { event.followOptions() }
    val followed = remember(follows) { follows.mapTo(hashSetOf()) { it.key } }
    // Alerts are for followed teams, so the switch shows where one plays and the game is not over.
    val playsFollowedTeam = remember(event.id, follows) { event.followsTeam(follows) }
    val canAlert = !event.isFinished() && playsFollowedTeam
    if (!canRemind && options.isEmpty() && !canAlert) return
    // Wraps instead of scrolling sideways, so no chip is ever half outside the sheet.
    FlowRow(Modifier.fillMaxWidth().padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        if (canRemind) SportsActionChip(
            label = stringResource(if (reminded) R.string.sports_reminder_on else R.string.sports_remind_me),
            icon = if (reminded) Icons.Default.NotificationsActive else Icons.Default.NotificationsNone,
            active = reminded,
        ) { onToggleReminder(!reminded) }
        options.forEach { option ->
            val on = option.key in followed
            SportsActionChip(
                label = stringResource(if (on) R.string.sports_following else R.string.sports_follow, option.name),
                icon = if (on) Icons.Default.Star else Icons.Default.StarBorder,
                active = on,
            ) { onToggleFollow(option) }
        }
        if (canAlert) SportsActionChip(
            label = stringResource(if (scoreAlerts) R.string.sports_score_alerts_on else R.string.sports_score_alerts),
            icon = Icons.Default.EmojiEvents,
            active = scoreAlerts,
        ) { onToggleScoreAlerts(!scoreAlerts) }
    }
}

/** Watch alongside other games, or record this one from the user's own channels. */
@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun SportsWatchActions(
    canWatch: Boolean,
    canRecord: Boolean,
    recorded: Boolean,
    onMultiview: (() -> Unit)?,
    onRecord: (() -> Unit)?,
) {
    val showMultiview = canWatch && onMultiview != null
    val showRecord = (canRecord || recorded) && onRecord != null
    if (!showMultiview && !showRecord) return
    // Wraps instead of scrolling sideways, so no chip is ever half outside the sheet.
    FlowRow(Modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        if (showMultiview && onMultiview != null) SportsActionChip(
            label = stringResource(R.string.multiview_add_to),
            icon = Icons.Default.GridView,
            active = false,
            modifier = Modifier.testTag("sports-multiview"),
            onClick = onMultiview,
        )
        if (showRecord && onRecord != null) SportsActionChip(
            label = stringResource(if (recorded) R.string.recording_scheduled_chip else R.string.recording_record),
            icon = Icons.Default.FiberManualRecord,
            active = recorded,
            modifier = Modifier.testTag("sports-record"),
            onClick = onRecord,
        )
    }
}

@Composable
private fun SportsActionChip(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, active: Boolean,
    modifier: Modifier = Modifier, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val scale by androidx.compose.animation.core.animateFloatAsState(if (focused) 1.05f else 1f, tween(150), label = "sports-chip-scale")
    val fg = if (focused) Color(0xFF0B0B0D) else LiveColors.Fg
    Row(modifier.graphicsLayer { scaleX = scale; scaleY = scale }.clip(RoundedCornerShape(50))
        .background(if (focused) Color.White else if (active) Color.White.copy(alpha = 0.16f) else Color.White.copy(alpha = 0.07f))
        .border(1.dp, if (active && !focused) Color.White.copy(alpha = .38f) else Color.Transparent, RoundedCornerShape(50))
        .onFocusChanged { focused = it.isFocused }
        .clickable(onClick = onClick)
        .heightIn(min = 36.dp).padding(horizontal = 14.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = if (active && !focused) liveAccent() else fg, modifier = Modifier.size(16.dp))
        Text(label, color = fg, fontSize = 12.sp, fontWeight = if (focused || active) FontWeight.SemiBold else FontWeight.Medium,
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 6.dp).widthIn(max = 220.dp))
    }
}

/**
 * The game page beyond its channels: score per period, both teams' form, earlier
 * meetings and the table. For a live or finished game the period scores and the
 * table stay hidden until the viewer taps Show score; form and meetings never
 * include this game. Each line can take focus, so a remote can scroll the list.
 */
@Composable
private fun SportsGameInfo(
    fixture: com.arflix.tv.data.model.SportsFixture,
    teams: List<String>,
    showScore: Boolean,
    spoilerSensitive: Boolean,
    load: suspend (String) -> com.arflix.tv.data.model.SportsGameDetails?,
    modifier: Modifier = Modifier,
) {
    var details by remember(fixture.id) { mutableStateOf<com.arflix.tv.data.model.SportsGameDetails?>(null) }
    var loading by remember(fixture.id) { mutableStateOf(true) }
    LaunchedEffect(fixture.id) {
        loading = true
        details = runCatching { load(fixture.id) }.getOrNull()
        loading = false
    }
    val home = teams.getOrNull(0)
    val away = teams.getOrNull(1)
    val first = remember(fixture.id) { FocusRequester() }
    val touch = com.arflix.tv.util.LocalDeviceType.current.isTouchDevice()
    LaunchedEffect(fixture.id, loading) {
        if (!touch && !loading) { withFrameNanos { }; runCatching { first.requestFocus() } }
    }
    val info = details
    LazyColumn(modifier.fillMaxWidth().padding(top = 8.dp).testTag("sports-game-details")) {
        if (loading && info == null) {
            item(key = "loading") {
                Row(Modifier.padding(vertical = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    CircularProgressIndicator(color = liveAccent(), modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                    Text(stringResource(R.string.sports_details_loading), color = LiveColors.FgDim, fontSize = 12.sp, modifier = Modifier.padding(start = 10.dp))
                }
            }
            return@LazyColumn
        }
        if (info == null || info.isEmpty) {
            item(key = "none") {
                GameInfoLine(Modifier.focusRequester(first)) {
                    Text(stringResource(R.string.sports_details_none), color = LiveColors.FgDim, fontSize = 12.sp)
                }
            }
            return@LazyColumn
        }
        var firstUsed = false
        fun lineModifier(): Modifier = if (firstUsed) Modifier else { firstUsed = true; Modifier.focusRequester(first) }
        val hidden = spoilerSensitive && !showScore
        if (info.periods.isNotEmpty()) {
            item(key = "periods-title") { GameInfoTitle(stringResource(R.string.sports_score_per_period)) }
            if (hidden) { val m = lineModifier(); item(key = "periods-hidden") {
                GameInfoLine(m) { Text(stringResource(R.string.sports_details_hidden), color = LiveColors.FgDim, fontSize = 12.sp) }
            } } else {
                val m = lineModifier()
                item(key = "periods") {
                    GameInfoLine(m) {
                        Column(Modifier.fillMaxWidth()) {
                            PeriodRow("", info.periods.map { it.label }, bold = false)
                            PeriodRow(home.orEmpty(), info.periods.map { it.home.toString() }, bold = true)
                            PeriodRow(away.orEmpty(), info.periods.map { it.away.toString() }, bold = true)
                        }
                    }
                }
            }
        }
        if (info.homeForm.isNotEmpty() || info.awayForm.isNotEmpty()) {
            item(key = "form-title") { GameInfoTitle(stringResource(R.string.sports_form)) }
            listOf(home to info.homeForm, away to info.awayForm).forEachIndexed { index, (team, form) ->
                if (form.isEmpty() || team == null) return@forEachIndexed
                val m = lineModifier()
                item(key = "form-$index") {
                    GameInfoLine(m) {
                        Text(team, color = LiveColors.Fg, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                        form.forEach { result -> FormBadge(result) }
                    }
                }
            }
        }
        if (info.headToHead.isNotEmpty()) {
            item(key = "h2h-title") { GameInfoTitle(stringResource(R.string.sports_head_to_head)) }
            info.headToHead.forEachIndexed { index, match ->
                val m = lineModifier()
                item(key = "h2h-$index") {
                    GameInfoLine(m) {
                        Text(match.date, color = LiveColors.FgDim, fontSize = 11.sp, modifier = Modifier.width(84.dp))
                        Text("${match.homeTeam}  ${match.homeScore} – ${match.awayScore}  ${match.awayTeam}", color = LiveColors.Fg, fontSize = 12.sp,
                            maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
        }
        if (info.standings.isNotEmpty()) {
            item(key = "table-title") { GameInfoTitle(listOfNotNull(stringResource(R.string.sports_standings), info.standingsTitle).joinToString(" · ")) }
            if (hidden) { val m = lineModifier(); item(key = "table-hidden") {
                GameInfoLine(m) { Text(stringResource(R.string.sports_details_hidden), color = LiveColors.FgDim, fontSize = 12.sp) }
            } } else {
                item(key = "table-head") { StandingRow("#", "", listOf("P", "W", "D", "L", "+/-", "Pts"), bold = false, dim = true) }
                info.standings.aroundHighlighted().forEach { row ->
                    val m = lineModifier()
                    item(key = "table-${row.rank}-${row.team}") {
                        GameInfoLine(m) {
                            StandingRow(row.rank.toString(), row.team,
                                listOf(row.played, row.won, row.drawn, row.lost, row.difference, row.points).map { it?.toString() ?: "–" },
                                bold = row.highlight, dim = false)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GameInfoTitle(text: String) {
    Text(text, color = LiveColors.Fg, fontSize = 13.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp))
}

@Composable
private fun GameInfoLine(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    var focused by remember { mutableStateOf(false) }
    Row(modifier.fillMaxWidth().heightIn(min = 32.dp).clip(RoundedCornerShape(6.dp))
        .background(if (focused) Color.White.copy(alpha = .12f) else Color.Transparent)
        .border(2.dp, if (focused) Color.White else Color.Transparent, RoundedCornerShape(6.dp))
        .onFocusChanged { focused = it.isFocused }.focusable()
        .padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically, content = content)
}

@Composable
private fun PeriodRow(label: String, values: List<String>, bold: Boolean) {
    Row(Modifier.fillMaxWidth().padding(vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = LiveColors.Fg, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
        values.forEach { value ->
            Text(value, color = if (bold) LiveColors.Fg else LiveColors.FgDim, fontSize = 12.sp,
                fontWeight = if (bold) FontWeight.Medium else FontWeight.Normal, maxLines = 1,
                textAlign = androidx.compose.ui.text.style.TextAlign.End, modifier = Modifier.width(34.dp))
        }
    }
}

@Composable
private fun StandingRow(rank: String, team: String, values: List<String>, bold: Boolean, dim: Boolean) {
    val color = if (dim) LiveColors.FgDim else LiveColors.Fg
    val weight = if (bold) FontWeight.Bold else FontWeight.Normal
    Row(Modifier.fillMaxWidth().padding(horizontal = if (dim) 8.dp else 0.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(rank, color = color, fontSize = 11.sp, fontWeight = weight, modifier = Modifier.width(26.dp))
        Text(team, color = color, fontSize = 12.sp, fontWeight = weight, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
        values.forEach { value ->
            Text(value, color = color, fontSize = 11.sp, fontWeight = weight, maxLines = 1,
                textAlign = androidx.compose.ui.text.style.TextAlign.End, modifier = Modifier.width(32.dp))
        }
    }
}

@Composable
private fun FormBadge(result: String) {
    val background = when (result) {
        "W" -> Color(0xFF2E7D32)
        "L" -> Color(0xFFC62828)
        else -> Color(0xFF616161)
    }
    Box(Modifier.padding(start = 4.dp).size(20.dp).clip(RoundedCornerShape(4.dp)).background(background), contentAlignment = Alignment.Center) {
        Text(result, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}
