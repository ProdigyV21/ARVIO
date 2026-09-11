package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import coil.compose.AsyncImage
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.ui.focus.mirrorHorizontalForRtl
import androidx.compose.ui.unit.LayoutDirection
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

internal const val SPORTS_GUIDE_CATEGORY = "sports-hub"

internal fun LiveCategoryTree.withSportsDestination(): LiveCategoryTree = copy(
    top = top.filterNot { it.id == SPORTS_GUIDE_CATEGORY }.flatMap { category ->
        if (category.id == "all") listOf(category, LiveCategory(SPORTS_GUIDE_CATEGORY, "Sports", 0, CategoryIcon.Sport))
        else listOf(category)
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
) {
    var focusedRow by remember { mutableStateOf<String?>(null) }
    var focusedOrder by remember { mutableStateOf(emptyList<String>()) }
    var artworkRetry by remember { mutableIntStateOf(0) }
    var failedArtwork by remember(artworkRetry) { mutableStateOf(emptySet<String>()) }
    var presentationRows by remember { mutableStateOf(emptyList<SportsGuideRow>()) }
    LaunchedEffect(events, now) {
        presentationRows = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Default) {
            sportsPresentationRows(events, now, emptySet())
        }
    }
    val rows = remember(presentationRows, focusedRow, focusedOrder) {
        presentationRows.map { row ->
            if (row.id == focusedRow) {
                val rank = focusedOrder.withIndex().associate { it.value to it.index }
                row.copy(events = row.events.sortedBy { rank[it.id] ?: Int.MAX_VALUE })
            } else row
        }
    }
    var selected by remember { mutableStateOf<SportsGuideEvent?>(null) }
    var returnFocus by remember { mutableStateOf<FocusRequester?>(null) }
    var showScore by remember { mutableStateOf(false) }
    val firstFocus = remember { FocusRequester() }
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val narrow = configuration.screenWidthDp < 600
    val timeFormat = remember(context, clockFormat) {
        if (clockFormat == null) android.text.format.DateFormat.getTimeFormat(context)
        else java.text.SimpleDateFormat(if (clockFormat == "12h") "h:mm a" else "HH:mm", java.util.Locale.getDefault())
    }
    val today = Instant.ofEpochMilli(now).atZone(ZoneId.systemDefault()).toLocalDate()
    fun eventTime(event: SportsGuideEvent): String {
        if (event.isConfirmedLive(now)) return "LIVE"
        if (event.isOnAir(now)) return "ON AIR"
        val date = Instant.ofEpochMilli(event.programme.startUtcMillis).atZone(ZoneId.systemDefault())
        val day = when (date.toLocalDate()) {
            today -> "Today"
            today.plusDays(1) -> "Tomorrow"
            else -> date.format(DateTimeFormatter.ofPattern("EEE d MMM"))
        }
        return "$day ${timeFormat.format(Date(event.programme.startUtcMillis))}"
    }
    LaunchedEffect(focusSignal, rows.isEmpty(), sidebarOpen) {
        if (focusSignal > 0 && rows.isNotEmpty() && !sidebarOpen) {
            // Lazy cards are attached after the drawer starts its layout transition.
            withFrameNanos { }
            withFrameNanos { }
            runCatching { firstFocus.requestFocus() }
        }
    }
    LaunchedEffect(selected) {
        if (selected == null) returnFocus?.let { runCatching { it.requestFocus() } }
    }
    CompositionLocalProvider(LocalTextStyle provides LocalTextStyle.current.copy(fontFamily = LiveFontFamily)) {
    BoxWithConstraints(modifier.fillMaxSize().background(LiveColors.Bg)) {
    // Use full-screen dimensions so opening the drawer never resizes loaded artwork.
    val viewport = configuration.screenWidthDp.dp
    val columns = when { viewport >= 850.dp -> 5; viewport >= 620.dp -> 3; viewport >= 420.dp -> 2; else -> 1 }
    val cardWidth = if (columns == 1) viewport - 54.dp else (viewport - 36.dp - 12.dp * (columns - 1)) / columns
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.height(32.dp).padding(horizontal = 18.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            if (!sidebarOpen) Icon(Icons.Outlined.Menu, "Categories", tint = LiveColors.Fg,
                modifier = Modifier.size(20.dp).clickable(onClick = onOpenCategories))
            Text("Sports", color = LiveColors.Fg, fontSize = 21.sp, lineHeight = 25.sp, fontWeight = FontWeight.SemiBold)
        }
        if (rows.isEmpty()) {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                if (loading) CircularProgressIndicator(color = LiveColors.Accent, modifier = Modifier.size(24.dp))
                else Icon(Icons.Default.SportsSoccer, null, tint = LiveColors.FgDim, modifier = Modifier.size(32.dp))
                Text(if (loading) "Reading sports schedule" else if (failed) "Schedule unavailable"
                    else if (events.any { it.hasChannels(now) && (it.isOnAir(now) || it.programme.startUtcMillis > now) }) "Event artwork unavailable"
                    else "No sports events matched to your channels",
                    color = LiveColors.FgDim, modifier = Modifier.padding(14.dp))
                if (!loading) Text("Retry", color = LiveColors.Fg,
                    modifier = Modifier.clickable { artworkRetry++; onRetry() }.padding(16.dp))
                Text("Categories", color = LiveColors.Fg, modifier = Modifier.focusRequester(firstFocus)
                    .clickable(onClick = onOpenCategories).padding(16.dp))
            }
        } else LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            itemsIndexed(rows, key = { _, row -> row.id }) { rowIndex, row ->
                Column {
                    Row(Modifier.fillMaxWidth().height(if (narrow) 44.dp else 20.dp).padding(horizontal = 18.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(row.title, color = LiveColors.Fg, fontWeight = FontWeight.Medium, fontSize = 15.sp, lineHeight = 18.sp,
                            modifier = Modifier.weight(1f))
                    }
                    LazyRow(Modifier.padding(horizontal = 18.dp), contentPadding = PaddingValues(vertical = 1.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        itemsIndexed(row.events, key = { _, event -> event.id }) { index, event ->
                            val scheduleOnly = row.id.endsWith("-schedule")
                            val itemRequester = remember { FocusRequester() }
                            val requester = if (index == 0 && rowIndex == 0) firstFocus else itemRequester
                            var focused by remember { mutableStateOf(false) }
                            Column(Modifier.width(cardWidth).testTag("sports-event-card")
                                .focusRequester(requester)
                                .onFocusChanged {
                                    focused = it.isFocused
                                    if (it.isFocused) {
                                        onContentFocused()
                                        if (focusedRow != row.id) { focusedRow = row.id; focusedOrder = row.events.map { it.id } }
                                    }
                                }
                                .onPreviewKeyEvent { key ->
                                    if (index == 0 && key.type == KeyEventType.KeyDown &&
                                        key.key.mirrorHorizontalForRtl(isRtl) == Key.DirectionLeft) {
                                        onOpenCategories(); true
                                    } else false
                                }
                                .clickable { returnFocus = requester; showScore = false; selected = event }
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
                                    Box(Modifier.matchParentSize().border(2.dp, if (focused) Color.White else Color.Transparent, RoundedCornerShape(4.dp)))
                                }
                                Text(event.title, color = LiveColors.Fg, fontSize = 11.sp, lineHeight = 14.sp, fontWeight = FontWeight.Medium,
                                    minLines = 2, maxLines = 2, overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(top = 4.dp))
                                Row(Modifier.fillMaxWidth().height(14.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Text(listOfNotNull(event.sport.title, event.competition).joinToString(" · "), color = LiveColors.FgDim, fontSize = 10.sp, lineHeight = 13.sp,
                                        maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                                    if (event.isOnAir(now)) {
                                        Icon(Icons.Default.Tv, null, tint = LiveColors.FgDim, modifier = Modifier.size(13.dp))
                                        Text(channelCount(event.availableChannels(now).size + event.possibleChannels.size), color = LiveColors.FgDim, fontSize = 9.sp, lineHeight = 12.sp,
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
        val confirmedChannels = if (onAir) event?.availableChannels(now).orEmpty() else event?.channels.orEmpty()
        val possibleIds = event?.possibleChannels.orEmpty().mapTo(hashSetOf()) { it.id }
        val sourceChannels = (confirmedChannels + event?.possibleChannels.orEmpty()).distinctBy { it.id }
        val initialFocus = remember(event?.id) { FocusRequester() }
        LaunchedEffect(event?.id, onAir, sourceChannels.isEmpty()) {
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
                Icon(Icons.Default.Close, "Close", tint = LiveColors.Fg,
                    modifier = Modifier.size(44.dp)
                        .then(if (!onAir || sourceChannels.isEmpty()) Modifier.focusRequester(initialFocus) else Modifier)
                        .clickable(onClick = ::dismiss).padding(10.dp))
            }
            event?.fixture?.let { fixture ->
                Text(listOfNotNull(event.competition, fixture.venue, fixture.round?.let { "Round $it" }).joinToString(" · "),
                    color = LiveColors.FgDim, fontSize = 11.sp, maxLines = 2, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 10.dp))
                if (fixture.homeScore != null && fixture.awayScore != null && now - fixture.observedAt < 300_000) {
                    Text(if (showScore) "${fixture.homeScore} : ${fixture.awayScore}" else "Show score", color = LiveColors.Fg,
                        fontSize = 12.sp, modifier = Modifier.clickable { showScore = !showScore }.padding(vertical = 10.dp))
                }
            }
            Row(Modifier.fillMaxWidth().padding(top = 9.dp, bottom = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(if (onAir) "Channels" else "Scheduled channels", fontSize = 14.sp, color = LiveColors.Fg,
                    fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
                Text(channelCount(sourceChannels.size), fontSize = 11.sp, color = LiveColors.FgDim)
            }
            LazyColumn(Modifier.heightIn(max = (if (narrow) 49.dp else 41.dp) * sourceChannels.size.coerceIn(1, 20))) {
                itemsIndexed(sourceChannels, key = { _, channel -> channel.id }) { index, channel ->
                    var focused by remember { mutableStateOf(false) }
                    Row(Modifier.fillMaxWidth().heightIn(min = if (narrow) 48.dp else 40.dp).clip(RoundedCornerShape(4.dp))
                        .then(if (index == 0 && onAir) Modifier.focusRequester(initialFocus) else Modifier)
                        .border(1.dp, if (focused) Color.White else Color.Transparent, RoundedCornerShape(4.dp))
                        .background(if (focused) LiveColors.FocusBg else Color.Transparent)
                        .onFocusChanged { focused = it.isFocused }
                        .clickable(enabled = onAir) {
                            if (event?.isOnAir(System.currentTimeMillis()) == true && (event.availableChannels(System.currentTimeMillis()).any { it.id == channel.id } || channel.id in possibleIds)) { dismiss(); onPlay(channel) }
                        }.padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        val logoChannel = remember(channel) { channel.enrichForFastStartup(0) }
                        ChannelLogo(logoChannel, 30.dp, Modifier.size(if (narrow) 40.dp else 88.dp, 30.dp))
                        Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                            Text(channel.name, color = LiveColors.Fg, fontSize = 13.sp, lineHeight = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            val provider = providerNames[channelPlaylistId(channel.id)]
                            Text("${provider ?: channel.group.orEmpty()} · ${if (channel.id in possibleIds) "Possible broadcast" else "Guide match"}", color = LiveColors.FgDim, fontSize = 10.sp, lineHeight = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
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
            if (sourceChannels.isEmpty()) Text("No matching channels in your playlists.", color = LiveColors.FgDim,
                fontSize = 12.sp, modifier = Modifier.padding(vertical = 20.dp))
            if (event == null) Text("This event is no longer in the available guide.", color = LiveColors.FgDim)
        }
    }
    }
}

private fun channelCount(count: Int) = "$count ${if (count == 1) "channel" else "channels"}"

@Composable
private fun EventArtwork(event: SportsGuideEvent, modifier: Modifier = Modifier, onUnavailable: () -> Unit = {}) {
    var loaded by remember(event.artwork) { mutableStateOf(false) }
    var bannerFailed by remember(event.artwork) { mutableStateOf(event.artwork.isNullOrBlank()) }
    val pair = event.teamArtwork
    var pairFailed by remember(pair) { mutableStateOf(pair == null) }
    var homeLoaded by remember(pair?.homeBadge) { mutableStateOf(false) }
    var awayLoaded by remember(pair?.awayBadge) { mutableStateOf(false) }
    LaunchedEffect(bannerFailed, pairFailed) { if (bannerFailed && pairFailed) onUnavailable() }
    Box(modifier.background(LiveColors.Panel).testTag(if (loaded) "sports-artwork-loaded" else "sports-artwork-pending")) {
        event.artwork?.let { url ->
            AsyncImage(url, null, contentScale = ContentScale.Fit,
                onSuccess = { loaded = true }, onError = { loaded = false; bannerFailed = true },
                modifier = Modifier.fillMaxSize())
        }
        if (!loaded && pair != null) {
            Row(Modifier.fillMaxSize().background(if (homeLoaded && awayLoaded) Color(0xFF20262C) else Color.Transparent)
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                AsyncImage(pair.homeBadge, pair.homeTeam, contentScale = ContentScale.Fit,
                    onSuccess = { homeLoaded = true }, onError = { homeLoaded = false; pairFailed = true },
                    modifier = Modifier.weight(1f).fillMaxHeight().graphicsLayer { alpha = if (homeLoaded && awayLoaded) 1f else 0f })
                Text("VS", color = if (homeLoaded && awayLoaded) LiveColors.Fg else Color.Transparent,
                    fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                AsyncImage(pair.awayBadge, pair.awayTeam, contentScale = ContentScale.Fit,
                    onSuccess = { awayLoaded = true }, onError = { awayLoaded = false; pairFailed = true },
                    modifier = Modifier.weight(1f).fillMaxHeight().graphicsLayer { alpha = if (homeLoaded && awayLoaded) 1f else 0f })
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
