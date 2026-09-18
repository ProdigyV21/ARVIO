package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.PlayArrow
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

/** Shared day label formatter for sports cards — was allocated per card per compose. */
private val sportsDayFormat: java.time.format.DateTimeFormatter =
    java.time.format.DateTimeFormatter.ofPattern("EEE d MMM", java.util.Locale.getDefault())

internal const val SPORTS_GUIDE_CATEGORY = "sports-hub"
internal const val SPORTS_CHANNEL_CATEGORY = "g-sports"

/**
 * Puts the match guide inside "All Channels", right before the "Sports · Global"
 * channel list. The two belong together — one is the fixtures, the other the
 * channels — and apart they read as duplicates. With no sports channels there is
 * nothing to sit beside, so nothing is added.
 *
 * The label stays in English so grouping and comparison keep working;
 * liveCategoryLabel() localizes it at render time like the other categories.
 */
internal fun LiveCategoryTree.withSportsDestination(): LiveCategoryTree = copy(
    top = top.filterNot { it.id == SPORTS_GUIDE_CATEGORY }.map { category ->
        if (category.id != "all") category
        else category.copy(
            children = category.children.flatMap { child ->
                if (child.id != SPORTS_CHANNEL_CATEGORY) listOf(child)
                else listOf(LiveCategory(SPORTS_GUIDE_CATEGORY, "Sports", 0, CategoryIcon.Sport), child)
            },
        )
    },
)

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
) {
    // Only a schedule refresh consumes this order. Focus alone must not rebuild
    // every catalogue and invalidate all visible lazy rows.
    val focusedOrder = remember { arrayOfNulls<Pair<String, List<String>>>(1) }
    var artworkRetry by remember { mutableIntStateOf(0) }
    var failedArtwork by remember(artworkRetry) { mutableStateOf(emptySet<String>()) }
    var presentationRows by remember { mutableStateOf(emptyList<SportsGuideRow>()) }
    LaunchedEffect(events, now) {
        presentationRows = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Default) {
            // An image failure must not remove/reparent the focused lazy item.
            sportsPresentationRows(events, now, emptySet())
        }
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
    var returnFocus by remember { mutableStateOf<FocusRequester?>(null) }
    var showScore by remember { mutableStateOf(false) }
    val firstFocus = remember { FocusRequester() }
    val categoriesFocus = remember { FocusRequester() }
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
            launch { reveal(listState, rowIndex) }
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
    fun eventTime(event: SportsGuideEvent): String {
        if (event.isConfirmedLive(now) || (event.channelOnly && event.isOnAir(now))) return labelLive
        if (event.isOnAir(now)) return labelOnAir
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
        if (focusSignal > handledFocusSignal && rows.isNotEmpty() && !sidebarOpen) {
            // Lazy cards are attached after the drawer starts its layout transition.
            withFrameNanos { }
            withFrameNanos { }
            if (focusSignal > handledFocusSignal) runCatching { firstFocus.requestFocus() }
        }
    }
    LaunchedEffect(selected) {
        if (selected == null) returnFocus?.let { runCatching { it.requestFocus() } }
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
    Column(Modifier.fillMaxSize()) {
        if (showHeader) {
            if (!sidebarOpen) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = TextPrimary,
                        modifier = Modifier
                            .focusRequester(categoriesFocus)
                            .onPreviewKeyEvent { key ->
                                if (key.key == Key.DirectionDown && key.type == KeyEventType.KeyDown) {
                                    moveTo(0, 0); true
                                } else false
                            }
                            .clickable(onClick = onOpenCategories)
                            .padding(end = 16.dp)
                            .size(28.dp),
                    )
                    Text(
                        text = stringResource(R.string.live_quick_sports),
                        style = ArflixTypography.heroTitle.copy(fontSize = 24.sp),
                        color = TextPrimary,
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    if (onOpenSearch != null) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search),
                            tint = TextPrimary,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable(onClick = onOpenSearch),
                        )
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
        if (rows.isEmpty()) {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                if (loading) CircularProgressIndicator(color = liveAccent(), modifier = Modifier.size(24.dp))
                else Icon(Icons.Default.SportsSoccer, null, tint = LiveColors.FgDim, modifier = Modifier.size(32.dp))
                Text(if (loading) stringResource(R.string.live_sports_reading_schedule) else if (failed) stringResource(R.string.live_sports_schedule_unavailable)
                    else if (events.any { it.hasChannels(now) && (it.isOnAir(now) || it.programme.startUtcMillis > now) }) stringResource(R.string.live_sports_artwork_unavailable)
                    else stringResource(R.string.live_sports_no_events_matched),
                    color = LiveColors.FgDim, modifier = Modifier.padding(14.dp))
                if (!loading) Text(stringResource(R.string.retry), color = LiveColors.Fg,
                    modifier = Modifier.clickable { artworkRetry++; onRetry() }.padding(16.dp))
                Text(stringResource(R.string.live_groups_title), color = LiveColors.Fg, modifier = Modifier.focusRequester(firstFocus)
                    .clickable(onClick = onOpenCategories).padding(16.dp))
            }
        } else LazyColumn(Modifier.fillMaxSize().testTag("sports-guide-list"), state = listState, contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val rowKeys = disambiguatedLazyKeys(rows) { it.id }
            itemsIndexed(rows, key = { index, _ -> rowKeys[index] }) { rowIndex, row ->
                val rowState = rememberLazyListState()
                DisposableEffect(row.id, rowState) {
                    rowStates[row.id] = rowState
                    onDispose { rowStates.remove(row.id) }
                }
                Column {
                    Row(Modifier.fillMaxWidth().height(if (narrow) 44.dp else 20.dp).padding(horizontal = 18.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(row.title, color = LiveColors.Fg, fontWeight = FontWeight.Medium, fontSize = 15.sp, lineHeight = 18.sp,
                            modifier = Modifier.weight(1f))
                    }
                    val eventKeys = keysByRow.getValue(row.id)
                    LazyRow(Modifier.padding(horizontal = 18.dp), state = rowState, contentPadding = PaddingValues(vertical = 1.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        itemsIndexed(row.events, key = { index, _ -> eventKeys[index] }) { index, event ->
                            val scheduleOnly = row.id.endsWith("-schedule")
                            val itemRequester = remember { FocusRequester() }
                            val requester = if (index == 0 && rowIndex == 0) firstFocus else itemRequester
                            DisposableEffect(row.id, eventKeys[index], requester) {
                                val key = row.id to eventKeys[index]
                                cardFocus[key] = requester
                                onDispose { cardFocus.remove(key) }
                            }
                            var focused by remember { mutableStateOf(false) }
                            Column(Modifier.width(cardWidth).testTag("sports-event-card")
                                .focusRequester(requester)
                                .onFocusChanged {
                                    focused = it.isFocused
                                    if (it.isFocused) {
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
                                            Key.DirectionUp -> if ((navigationTarget?.first ?: rowIndex) > 0) moveTo((navigationTarget?.first ?: rowIndex) - 1, navigationTarget?.second ?: index)
                                                else if (sidebarOpen) leaveCards() else {
                                                    navigationJob?.cancel(); navigationTarget = null
                                                    runCatching { categoriesFocus.requestFocus() }.let { }
                                                }
                                            Key.DirectionDown -> moveTo((navigationTarget?.first ?: rowIndex) + 1, navigationTarget?.second ?: index)
                                            Key.DirectionLeft -> if ((navigationTarget?.second ?: index) == 0) leaveCards() else moveTo(navigationTarget?.first ?: rowIndex, (navigationTarget?.second ?: index) - 1)
                                            Key.DirectionRight -> moveTo(navigationTarget?.first ?: rowIndex, (navigationTarget?.second ?: index) + 1)
                                        }
                                        true
                                    }
                                }
                                .clickable {
                                    navigationJob?.cancel(); navigationTarget = null
                                    returnFocus = requester; showScore = false; selected = event
                                }
                                .padding(2.dp)) {
                                if (scheduleOnly) Row(Modifier.fillMaxWidth().height(36.dp)
                                    .border(2.dp, if (focused) Color.White else LiveColors.DividerStrong, RoundedCornerShape(4.dp))
                                    .padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Text(eventTime(event), color = LiveColors.Fg, fontSize = 11.sp)
                                } else Box(Modifier.fillMaxWidth().aspectRatio(16f / 9f).testTag("sports-event-art").clip(RoundedCornerShape(4.dp))) {
                                    if (event.id !in failedArtwork) {
                                        EventArtwork(event, Modifier.fillMaxSize()) { failedArtwork = failedArtwork + event.id }
                                    } else {
                                        // Keep the focused card in place when an image fails.
                                        Box(Modifier.fillMaxSize().background(LiveColors.Panel), contentAlignment = Alignment.Center) {
                                            Text(event.title, color = LiveColors.FgDim, maxLines = 3,
                                                overflow = TextOverflow.Ellipsis, fontSize = 12.sp, modifier = Modifier.padding(12.dp))
                                        }
                                    }
                                    Row(Modifier.padding(6.dp).background(Color.Black.copy(alpha = .85f), RoundedCornerShape(3.dp))
                                        .padding(horizontal = 5.dp, vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                                        if (event.isOnAir(now)) Box(Modifier.padding(end = 4.dp).size(7.dp).background(LiveColors.LiveRed, RoundedCornerShape(50)))
                                        Text(eventTime(event), color = Color.White, fontSize = 10.sp, lineHeight = 12.sp)
                                    }
                                    Box(Modifier.matchParentSize().liveFocusOutline(focused, 4.dp))
                                }
                                Text(event.title, color = LiveColors.Fg, fontSize = 11.sp, lineHeight = 14.sp, fontWeight = FontWeight.Medium,
                                    minLines = 2, maxLines = 2, overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(top = 4.dp))
                                Row(Modifier.fillMaxWidth().height(14.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Text(listOfNotNull(event.sport.title, event.competition).joinToString(" · "), color = LiveColors.FgDim, fontSize = 10.sp, lineHeight = 13.sp,
                                        maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                                    val count = remember(event, now) {
                                        ((if (event.isOnAir(now)) event.availableChannels(now) else event.channels) + event.possibleChannels).distinctBy { it.id }.size
                                    }
                                    if (count > 0) {
                                        Icon(Icons.Default.Tv, null, tint = LiveColors.FgDim, modifier = Modifier.size(13.dp))
                                        Text(channelCount(count), color = LiveColors.FgDim, fontSize = 9.sp, lineHeight = 12.sp,
                                            modifier = Modifier.padding(start = 6.dp))
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
    val selection = selected
    val event = selection?.let { snapshot -> events.firstOrNull { it.id == snapshot.id } }
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
        val sourceChannels = (confirmedChannels + event?.possibleChannels.orEmpty()).distinctBy { it.id }
        val initialFocus = remember(event?.id) { FocusRequester() }
        LaunchedEffect(event?.id, sourceChannels.isEmpty()) {
            // The native dialog window must own focus before Compose assigns its row.
            withFrameNanos { }
            withFrameNanos { }
            runCatching { initialFocus.requestFocus() }
        }
        Column(Modifier.widthIn(max = 586.dp).fillMaxWidth().heightIn(max = configuration.screenHeightDp.dp * .82f)
            .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp)).background(LiveColors.Panel).padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if ((event ?: selection).hasEventArtwork && (event ?: selection).id !in failedArtwork)
                    EventArtwork(event ?: selection, Modifier.width(if (narrow) 84.dp else 132.dp).aspectRatio(16f / 9f).clip(RoundedCornerShape(3.dp)))
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
                        .then(if (sourceChannels.isEmpty()) Modifier.focusRequester(initialFocus) else Modifier)
                        .onFocusChanged { closeFocused = it.isFocused }
                        .liveFocusOutline(closeFocused, 4.dp)
                        .clickable(onClick = ::dismiss).padding(10.dp))
            }
            event?.fixture?.let { fixture ->
                Text(listOfNotNull(event.competition, fixture.venue, fixture.round?.let { stringResource(R.string.live_sports_round, it) }).joinToString(" · "),
                    color = LiveColors.FgDim, fontSize = 11.sp, maxLines = 2, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 10.dp))
                if (fixture.homeScore != null && fixture.awayScore != null && event?.isConfirmedLive(now) == true) {
                    Text(if (showScore) "${fixture.homeScore} : ${fixture.awayScore}" else stringResource(R.string.live_sports_show_score), color = LiveColors.Fg,
                        fontSize = 12.sp, modifier = Modifier.clickable { showScore = !showScore }.padding(vertical = 10.dp))
                }
            }
            Row(Modifier.fillMaxWidth().padding(top = 9.dp, bottom = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(if (onAir) stringResource(R.string.live_sports_channels) else stringResource(R.string.live_sports_scheduled_channels), fontSize = 14.sp, color = LiveColors.Fg,
                    fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
                Text(channelCount(sourceChannels.size), fontSize = 11.sp, color = LiveColors.FgDim)
            }
            val channelKeys = remember(sourceChannels) { disambiguatedLazyKeys(sourceChannels) { it.id } }
            LazyColumn(Modifier.heightIn(max = (if (narrow) 49.dp else 41.dp) * sourceChannels.size.coerceIn(1, 20))) {
                itemsIndexed(sourceChannels, key = { index, _ -> channelKeys[index] }) { index, channel ->
                    var focused by remember { mutableStateOf(false) }
                    Row(Modifier.fillMaxWidth().heightIn(min = if (narrow) 48.dp else 40.dp).clip(RoundedCornerShape(4.dp))
                        .then(if (index == 0) Modifier.focusRequester(initialFocus) else Modifier)
                        .border(1.dp, if (focused) Color.White else Color.Transparent, RoundedCornerShape(4.dp))
                        .background(if (focused) LiveColors.FocusBg else Color.Transparent)
                        .onFocusChanged { focused = it.isFocused }
                        // Scheduled channels are browsable with a remote, but cannot play early.
                        .focusable(enabled = !canOpenChannel)
                        .clickable(enabled = canOpenChannel) {
                            val time = System.currentTimeMillis()
                            if (event != null && (event.isOnAir(time) || event.isScheduledNow(time)) && (event.availableChannels(time).any { it.id == channel.id } || channel.id in possibleIds)) { dismiss(); onPlay(channel) }
                        }.padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        val logoChannel = remember(channel) { channel.enrichForFastStartup(0) }
                        ChannelLogo(logoChannel, 30.dp, Modifier.size(if (narrow) 40.dp else 88.dp, 30.dp))
                        Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                            Text(channel.name, color = LiveColors.Fg, fontSize = 13.sp, lineHeight = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            val provider = providerNames[channelPlaylistId(channel.id)]
                            Text("${provider ?: channel.group.orEmpty()} · ${if (channel.id in possibleIds) stringResource(R.string.live_sports_possible_broadcast) else stringResource(R.string.live_sports_guide_match)}", color = LiveColors.FgDim, fontSize = 10.sp, lineHeight = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
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
            }
            if (sourceChannels.isEmpty()) Text(stringResource(R.string.live_sports_no_matching_channels), color = LiveColors.FgDim,
                fontSize = 12.sp, modifier = Modifier.padding(vertical = 20.dp))
            if (event == null) Text(stringResource(R.string.live_sports_event_gone), color = LiveColors.FgDim)
        }
    }
    }
}

@Composable
private fun channelCount(count: Int) =
    if (count == 1) stringResource(R.string.live_channel_count_one, count)
    else stringResource(R.string.live_channels_count, count)

@Composable
private fun EventArtwork(event: SportsGuideEvent, modifier: Modifier = Modifier, onUnavailable: () -> Unit = {}) {
    var loaded by remember(event.artwork) { mutableStateOf(false) }
    var bannerFailed by remember(event.artwork) { mutableStateOf(event.artwork.isNullOrBlank()) }
    val pair = event.teamArtwork
    // A null model previously errored immediately in Coil; with remembered
    // requests a missing badge URL must still count as a failed pair so the
    // onUnavailable fallback behaves exactly as before.
    var pairFailed by remember(pair) { mutableStateOf(pair == null || (pair.homeBadge == null && pair.awayBadge == null)) }
    var homeLoaded by remember(pair?.homeBadge) { mutableStateOf(false) }
    var awayLoaded by remember(pair?.awayBadge) { mutableStateOf(false) }
    LaunchedEffect(bannerFailed, pairFailed) { if (bannerFailed && pairFailed) onUnavailable() }
    val context = LocalContext.current
    val density = LocalDensity.current
    // Decode once per URL at card size and keep the request anchored across
    // recompositions (clock ticks, D-Pad focus) via remember + key(), mirroring
    // ChannelLogo. The previous raw-String models re-decoded full-res art on
    // every pass and retried failures on each recomposition.
    BoxWithConstraints(modifier.background(LiveColors.Panel).testTag(if (loaded) "sports-artwork-loaded" else "sports-artwork-pending")) {
        val artW = with(density) { maxWidth.roundToPx() }.coerceAtLeast(1)
        val artH = with(density) { maxHeight.roundToPx() }.coerceAtLeast(1)
        val bannerUrl = event.artwork
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
        val homeRequest = remember(pair?.homeBadge, artW, artH) {
            pair?.homeBadge?.let { url ->
                ImageRequest.Builder(context)
                    .data(url)
                    .size(artW, artH)
                    .precision(Precision.INEXACT)
                    .allowHardware(true)
                    .crossfade(false)
                    .memoryCacheKey("sports-badge|$url|${artW}x$artH")
                    .placeholderMemoryCacheKey("sports-badge|$url|${artW}x$artH")
                    .build()
            }
        }
        val awayRequest = remember(pair?.awayBadge, artW, artH) {
            pair?.awayBadge?.let { url ->
                ImageRequest.Builder(context)
                    .data(url)
                    .size(artW, artH)
                    .precision(Precision.INEXACT)
                    .allowHardware(true)
                    .crossfade(false)
                    .memoryCacheKey("sports-badge|$url|${artW}x$artH")
                    .placeholderMemoryCacheKey("sports-badge|$url|${artW}x$artH")
                    .build()
            }
        }
        bannerRequest?.let { request ->
            key(bannerUrl) {
                AsyncImage(request, null, contentScale = ContentScale.Fit,
                    onSuccess = { loaded = true }, onError = { loaded = false; bannerFailed = true },
                    modifier = Modifier.fillMaxSize())
            }
        }
        if (!loaded && pair != null) {
            Row(Modifier.fillMaxSize().background(if (homeLoaded && awayLoaded) Color(0xFF20262C) else Color.Transparent)
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                homeRequest?.let { request ->
                    key(pair.homeBadge) {
                        AsyncImage(request, pair.homeTeam, contentScale = ContentScale.Fit,
                            onSuccess = { homeLoaded = true }, onError = { homeLoaded = false; pairFailed = true },
                            modifier = Modifier.weight(1f).fillMaxHeight().graphicsLayer { alpha = if (homeLoaded && awayLoaded) 1f else 0f })
                    }
                }
                Text("VS", color = if (homeLoaded && awayLoaded) LiveColors.Fg else Color.Transparent,
                    fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                awayRequest?.let { request ->
                    key(pair.awayBadge) {
                        AsyncImage(request, pair.awayTeam, contentScale = ContentScale.Fit,
                            onSuccess = { awayLoaded = true }, onError = { awayLoaded = false; pairFailed = true },
                            modifier = Modifier.weight(1f).fillMaxHeight().graphicsLayer { alpha = if (homeLoaded && awayLoaded) 1f else 0f })
                    }
                }
            }
        }
    }
}

@Composable
private fun PickerBadge(label: String) {
    Text(label, color = LiveColors.Fg, fontSize = 10.sp, lineHeight = 12.sp, modifier = Modifier.padding(start = 8.dp)
        .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(3.dp)).background(LiveColors.Bg)
        .padding(horizontal = 7.dp, vertical = 3.dp))
}
