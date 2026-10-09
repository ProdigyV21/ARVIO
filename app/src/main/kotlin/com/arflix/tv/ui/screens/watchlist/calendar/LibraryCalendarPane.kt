package com.arflix.tv.ui.screens.watchlist.calendar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.compose.AsyncImage
import com.arflix.tv.R
import com.arflix.tv.data.model.*
import com.arflix.tv.ui.components.LocalBottomBarInset
import com.arflix.tv.ui.focus.mirrorHorizontalForRtl
import com.arflix.tv.util.LocalAppLanguage
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.appLocale
import com.arflix.tv.util.tr
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlinx.coroutines.launch

private val CalendarSecondary = Color(0xFFACC5D8)

@Composable
internal fun LibraryCalendarRoute(
    onOpenDetails: (MediaType, Int) -> Unit,
    modifier: Modifier = Modifier,
    onExitUp: () -> Unit = {},
    viewModel: ReleaseCalendarViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(viewModel) { viewModel.onVisible() }
    DisposableEffect(lifecycle, viewModel) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) viewModel.onVisible()
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }
    LibraryCalendarPane(state, viewModel::selectDate, viewModel::changeMonth,
        viewModel::selectSource, viewModel::refresh, onOpenDetails, modifier, onExitUp)
}

/** A single, borderless month surface. Data and focus can be exercised without a network account. */
@Composable
fun LibraryCalendarPane(
    state: ReleaseCalendarUiState,
    onSelectDate: (LocalDate) -> Unit,
    onChangeMonth: (Long) -> Unit,
    onSelectSource: (String) -> Unit,
    onRefresh: () -> Unit,
    onOpenDetails: (MediaType, Int) -> Unit,
    modifier: Modifier = Modifier,
    onExitUp: () -> Unit = {}
) {
    val touch = LocalDeviceType.current.isTouchDevice()
    val locale = appLocale(LocalAppLanguage.current)
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val dates = remember(state.month) { calendarMonthDays(state.month) }
    val requesters = remember(dates) { dates.associateWith { FocusRequester() } }
    val firstRelease = remember { FocusRequester() }
    val releaseRail = rememberLazyListState()
    val focusScope = rememberCoroutineScope()
    val monthButton = remember { FocusRequester() }
    val emptyAction = remember { FocusRequester() }
    val retryAction = remember { FocusRequester() }
    var showSources by remember { mutableStateOf(false) }
    val releasesByDate = remember(state.entries, state.selectedSourceId) { state.releasesByDate }
    val selectedReleases = releasesByDate[state.selectedDate].orEmpty()
    val nextReleaseDate = releasesByDate.keys.filter { it > state.selectedDate && YearMonth.from(it) == state.month }.minOrNull()
    val today = LocalDate.now(state.timezone)
    val hasProblem = state.error != null || state.warnings.isNotEmpty()

    LaunchedEffect(state.selectedDate, state.selectedSourceId) { releaseRail.scrollToItem(0) }
    fun openDay() {
        if (selectedReleases.isEmpty()) {
            if (nextReleaseDate != null) emptyAction.requestFocus()
            else if (hasProblem) retryAction.requestFocus()
            return
        }
        focusScope.launch {
            // The first card may have been recycled after scrolling to later releases.
            releaseRail.scrollToItem(0)
            withFrameNanos { }
            runCatching { firstRelease.requestFocus() }
        }
    }

    LaunchedEffect(Unit) { if (!touch) requesters[state.selectedDate]?.requestFocus() }
    fun selectAndFocus(date: LocalDate) {
        // Keep remote movement on this month's stable nodes. A spillover cell
        // must not replace the grid while a repeated key is still being handled.
        if (YearMonth.from(date) != state.month) return
        if (!touch) requesters[date]?.requestFocus()
        onSelectDate(date)
    }
    val retryModifier = Modifier.testTag("calendar-retry").focusRequester(retryAction)
        .onPreviewKeyEvent { event ->
            if (event.type == KeyEventType.KeyDown && event.key == Key.DirectionUp) {
                requesters[state.selectedDate]?.requestFocus(); true
            } else false
        }
    BoxWithConstraints(modifier.fillMaxSize().testTag("library-calendar")) {
        val compact = maxWidth < 600.dp
        val compactToolbar = maxWidth < 800.dp
        val scrollable = compact || maxHeight < 320.dp
        val scroll = rememberScrollState()
        Column(Modifier.fillMaxSize().then(if (scrollable) Modifier.verticalScroll(scroll) else Modifier)) {
            CalendarToolbar(state, locale, compactToolbar, monthButton, onChangeMonth,
                onSources = { showSources = true }, onExitUp = onExitUp,
                onEnterGrid = { requesters[state.selectedDate]?.requestFocus() })
            if (compact && hasProblem) {
                CalendarButton(tr("Some release details unavailable") + " · " + tr("Retry"),
                    retryModifier.padding(vertical = 5.dp), onClick = onRefresh)
            }
            Row(Modifier.fillMaxWidth().height(if (compact) 30.dp else 24.dp), verticalAlignment = Alignment.CenterVertically) {
                DayOfWeek.entries.forEach { day ->
                    Text(day.getDisplayName(if (compact) TextStyle.SHORT else TextStyle.FULL, locale),
                        Modifier.weight(1f), color = CalendarSecondary, fontSize = if (compact) 11.sp else 11.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center, maxLines = 1)
                }
            }
            Column(Modifier.fillMaxWidth().then(if (scrollable) Modifier.height((dates.size / 7 * if (compact) 67 else 52).dp) else Modifier.weight(1f))
                .testTag("calendar-month-grid"), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                dates.chunked(7).forEach { week ->
                    Row(Modifier.fillMaxWidth().weight(1f), horizontalArrangement = Arrangement.spacedBy(if (compact) 3.dp else 4.dp)) {
                        week.forEach { date ->
                            CalendarDay(date, state.month, releasesByDate[date].orEmpty(), date == state.selectedDate,
                                state.timezone, locale, compact, date == today,
                                modifier = Modifier.weight(1f).fillMaxHeight().focusRequester(requesters.getValue(date))
                                    .focusProperties { canFocus = YearMonth.from(date) == state.month }
                                    .onPreviewKeyEvent { event ->
                                        val key = event.key.mirrorHorizontalForRtl(isRtl)
                                        if (key == Key.Enter || key == Key.DirectionCenter || key == Key.NumPadEnter) {
                                            // Keep both halves of OK on the day. Moving focus on key-down
                                            // lets the destination's clickable consume key-up as a click.
                                            if (event.type == KeyEventType.KeyUp) openDay()
                                            true
                                        } else if (event.type != KeyEventType.KeyDown) false else when (key) {
                                            Key.DirectionLeft -> { selectAndFocus(date.minusDays(1)); true }
                                            Key.DirectionRight -> { selectAndFocus(date.plusDays(1)); true }
                                            Key.DirectionUp -> {
                                                val target = date.minusWeeks(1)
                                                if (YearMonth.from(target) != state.month) monthButton.requestFocus() else selectAndFocus(target)
                                                true
                                            }
                                            Key.DirectionDown -> {
                                                if (YearMonth.from(date.plusWeeks(1)) != state.month) {
                                                    openDay()
                                                } else selectAndFocus(date.plusWeeks(1))
                                                true
                                            }
                                            else -> false
                                        }
                                    },
                                onFocused = { if (!touch && YearMonth.from(date) == state.month && date != state.selectedDate) onSelectDate(date) },
                                onClick = { onSelectDate(date) })
                        }
                    }
                }
            }
            Row(Modifier.fillMaxWidth().height(if (compact) 46.dp else 29.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(state.selectedDate.format(DateTimeFormatter.ofPattern(if (compact) "EEE d MMMM" else "EEEE d MMMM", locale)),
                    color = Color.White, fontSize = if (compact) 17.sp else 15.sp, fontWeight = FontWeight.SemiBold,
                    maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f, fill = false).testTag("calendar-selected-date"))
                if (!state.isLoading || selectedReleases.isNotEmpty()) Text("${selectedReleases.size} ${tr(if (selectedReleases.size == 1) "release" else "releases")}", color = CalendarSecondary, fontSize = 11.sp)
                if (!compact && hasProblem) {
                    Spacer(Modifier.weight(1f))
                    CalendarButton(tr("Some release details unavailable") + " · " + tr("Retry"), retryModifier, onClick = onRefresh)
                }
            }
            if (selectedReleases.isNotEmpty()) {
                LazyRow(Modifier.fillMaxWidth().height(if (compact) 169.dp else 130.dp).testTag("calendar-release-rail"), state = releaseRail,
                    horizontalArrangement = Arrangement.spacedBy(10.dp), contentPadding = PaddingValues(top = 2.dp, bottom = 2.dp)) {
                    itemsIndexed(selectedReleases, key = { _, item -> item.id }) { index, release ->
                        CalendarReleaseCard(release, state.timezone, locale, if (compact) 280.dp else (this@BoxWithConstraints.maxWidth / 3.28f),
                            compact, Modifier.then(if (index == 0) Modifier.focusRequester(firstRelease) else Modifier)
                                .onPreviewKeyEvent { event ->
                                    if (event.type == KeyEventType.KeyDown && event.key == Key.DirectionUp) {
                                        requesters[state.selectedDate]?.requestFocus(); true
                                    } else false
                                }, onClick = { onOpenDetails(release.media.mediaType, release.media.id) })
                    }
                }
            } else {
                Column(Modifier.fillMaxWidth().height(if (compact) 140.dp else 130.dp).testTag("calendar-empty-day"),
                    verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(tr(when { state.isLoading -> "Finding your releases…"; state.error != null || (hasProblem && state.watchlistCount == 0) -> "Calendar unavailable"
                        state.watchlistCount == 0 -> "Your watchlist, on the calendar"; else -> "No releases on this day" }), color = Color.White, fontSize = 15.sp,
                        modifier = if (state.isLoading) Modifier.testTag("calendar-loading") else Modifier)
                    if (!state.isLoading && !hasProblem && nextReleaseDate == null) {
                        Spacer(Modifier.height(7.dp))
                        Text(tr(if (state.watchlistCount == 0) "Add movies and shows to your ARVIO watchlist. No tracking account needed."
                            else "Choose another day or watchlist to explore upcoming releases."), color = CalendarSecondary,
                            fontSize = 11.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                    }
                    if (nextReleaseDate != null) {
                        Spacer(Modifier.height(10.dp))
                        CalendarButton(tr("Next release") + " · " + nextReleaseDate.format(DateTimeFormatter.ofPattern("d MMM", locale)),
                            Modifier.testTag("calendar-next-release").focusRequester(emptyAction)
                                .onPreviewKeyEvent { event ->
                                    if (event.type == KeyEventType.KeyDown && event.key == Key.DirectionUp) {
                                        requesters[state.selectedDate]?.requestFocus(); true
                                    } else false
                                }, onClick = { selectAndFocus(nextReleaseDate) })
                    }
                }
            }
            Spacer(Modifier.height(if (touch) 16.dp + LocalBottomBarInset.current else 8.dp))
        }
    }
    if (showSources) Dialog(onDismissRequest = { showSources = false }) {
        val selectedSourceFocus = remember { FocusRequester() }
        LaunchedEffect(Unit) { selectedSourceFocus.requestFocus() }
        Column(Modifier.widthIn(max = 360.dp).clip(RoundedCornerShape(18.dp)).background(Color(0xFF141619))
            .padding(20.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(tr("Watchlists"), color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
            state.sources.forEach { source ->
                CalendarButton(tr(source.label), Modifier.fillMaxWidth().heightIn(min = 44.dp).testTag("calendar-source-${source.id}")
                    .then(if (source.id == state.selectedSourceId) Modifier.focusRequester(selectedSourceFocus) else Modifier)
                    .semantics { selected = source.id == state.selectedSourceId },
                    selected = source.id == state.selectedSourceId, onClick = { onSelectSource(source.id); showSources = false })
            }
            CalendarButton(tr("Close"), Modifier.fillMaxWidth(), onClick = { showSources = false })
        }
    }
}

@Composable
private fun CalendarToolbar(state: ReleaseCalendarUiState, locale: Locale, compact: Boolean, monthButton: FocusRequester,
    onMonth: (Long) -> Unit, onSources: () -> Unit, onExitUp: () -> Unit, onEnterGrid: () -> Unit) {
    val touch = LocalDeviceType.current.isTouchDevice()
    Column(Modifier.onPreviewKeyEvent {
        if (!touch && it.type == KeyEventType.KeyDown && it.key == Key.DirectionDown) { onEnterGrid(); true } else false
    }) {
        Row(Modifier.fillMaxWidth().height(if (compact || touch) 44.dp else 24.dp)
            .onPreviewKeyEvent { if (!touch && it.type == KeyEventType.KeyDown && it.key == Key.DirectionUp) { onExitUp(); true } else false }, verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(if (compact) 3.dp else 10.dp)) {
            CalendarArrow(false, Modifier.focusRequester(monthButton).testTag("calendar-previous-month")
                .onPreviewKeyEvent { if (it.type == KeyEventType.KeyDown && it.key == Key.DirectionUp) { onExitUp(); true } else false }) { onMonth(-1) }
            Text(state.month.format(DateTimeFormatter.ofPattern("MMMM yyyy", locale)), color = Color.White,
                fontSize = if (compact) 15.sp else 13.sp, maxLines = 1, fontWeight = FontWeight.Medium,
                modifier = Modifier.testTag("calendar-month-label").then(if (compact) Modifier.weight(1f) else Modifier))
            CalendarArrow(true, Modifier.testTag("calendar-next-month")) { onMonth(1) }
            if (!compact) {
                CalendarButton(tr(state.sources.firstOrNull { it.id == state.selectedSourceId }?.label ?: "All watchlists"),
                    Modifier.widthIn(min = 118.dp).testTag("calendar-source-filter"), dropdown = true, onClick = onSources)
                Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    state.sources.filter { state.selectedSourceId == ReleaseCalendarSource.ALL.id && it != ReleaseCalendarSource.ALL && it != ReleaseCalendarSource.ARVIO }.forEach { source ->
                        CalendarSourceBadge(source)
                    }
                }
            }
        }
        if (compact) Row(Modifier.fillMaxWidth().height(44.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CalendarButton(tr(state.sources.firstOrNull { it.id == state.selectedSourceId }?.label ?: "All watchlists"),
                Modifier.testTag("calendar-source-filter"), dropdown = true, onClick = onSources)
        }
    }
}

@Composable
private fun CalendarSourceBadge(source: ReleaseCalendarSource) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        val drawable = when (source) { ReleaseCalendarSource.TRAKT -> R.drawable.calendar_trakt_official
            ReleaseCalendarSource.SIMKL -> R.drawable.calendar_simkl_official
            ReleaseCalendarSource.MDBLIST -> R.drawable.calendar_mdblist_official; else -> null }
        drawable?.let { Image(painterResource(it), contentDescription = source.label,
            modifier = (if (source == ReleaseCalendarSource.SIMKL) Modifier.width(54.dp).height(14.dp) else Modifier.size(18.dp))
                .testTag("calendar-brand-${source.id}"), contentScale = ContentScale.Fit) }
        if (source != ReleaseCalendarSource.SIMKL) Text(source.label, color = CalendarSecondary, fontSize = 10.sp, maxLines = 1)
    }
}

@Composable
private fun CalendarArrow(next: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val touch = LocalDeviceType.current.isTouchDevice()
    Box(modifier.size(if (touch) 44.dp else 24.dp).onFocusChanged { focused = it.isFocused }.clip(RoundedCornerShape(24.dp))
        .background(if (focused) Color.White else Color.Transparent).clickable(onClick = onClick), contentAlignment = Alignment.Center) {
        Icon(if (next) Icons.AutoMirrored.Outlined.KeyboardArrowRight else Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
            contentDescription = tr(if (next) "Next month" else "Previous month"), tint = if (focused) Color.Black else Color.White,
            modifier = Modifier.size(22.dp))
    }
}

@Composable
private fun CalendarButton(label: String, modifier: Modifier = Modifier, selected: Boolean = false, dropdown: Boolean = false, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val touch = LocalDeviceType.current.isTouchDevice()
    val active = selected || focused
    Row(modifier.heightIn(min = if (touch) 44.dp else 0.dp).onFocusChanged { focused = it.isFocused }.clip(RoundedCornerShape(30.dp))
        .background(if (active) Color.White else Color.Transparent).border(.6.dp, if (active) Color.White else Color(0xFF53616D), RoundedCornerShape(30.dp))
        .clickable(onClick = onClick).padding(horizontal = 16.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(label, color = if (active) Color.Black else Color.White, fontSize = if (touch) 12.sp else 10.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false))
        if (dropdown) Icon(Icons.Outlined.KeyboardArrowDown, contentDescription = null, tint = if (active) Color.Black else Color.White, modifier = Modifier.size(15.dp))
    }
}

@Composable
private fun CalendarDay(date: LocalDate, month: YearMonth, releases: List<CalendarRelease>, selected: Boolean,
    timezone: ZoneId, locale: Locale, compact: Boolean, today: Boolean, modifier: Modifier, onFocused: () -> Unit, onClick: () -> Unit) {
    val titleColor = if (selected) Color.Black else Color.White
    val secondary = if (selected) Color(0xFF24313A) else CalendarSecondary
    val background by animateColorAsState(if (selected) Color.White else Color.Transparent, tween(100), label = "calendar-day")
    val first = releases.firstOrNull()
    val rtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val accessibility = date.format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", locale)) +
        (if (today) ", " + tr("Today") else "") +
        (if (releases.isEmpty()) "" else ", " + releases.joinToString { it.media.title })
    BoxWithConstraints(modifier.testTag("calendar-day-$date").semantics { contentDescription = accessibility; this.selected = selected }
        .onFocusChanged { if (it.isFocused) onFocused() }.clip(RoundedCornerShape(5.dp)).background(background).clickable(onClick = onClick)) {
        if (!selected && first != null && (compact || releases.size == 1)) {
            AsyncImage(first.artwork(), null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            Box(Modifier.fillMaxSize().background(if (compact) Brush.verticalGradient(listOf(Color.Black.copy(.72f), Color.Transparent, Color.Black.copy(.12f)))
                else Brush.horizontalGradient(listOf(Color.Black.copy(.9f), Color.Black.copy(.65f), Color.Black.copy(.05f)).let { if (rtl) it.reversed() else it })))
        }
        Column(Modifier.padding(start = if (compact) 5.dp else 7.dp, top = 3.dp)) {
            Text(date.dayOfMonth.toString(), color = if (YearMonth.from(date) == month || selected) titleColor else CalendarSecondary.copy(.62f),
                fontSize = 12.sp, fontWeight = if (selected || today) FontWeight.SemiBold else FontWeight.Normal)
            if (today) Box(Modifier.width(12.dp).height(1.5.dp).background(titleColor).testTag("calendar-today-marker-$date"))
        }
        if (first != null) {
            if (compact) {
                if (selected) AsyncImage(first.artwork(), null, Modifier.fillMaxWidth().height((maxHeight - 22.dp).coerceAtLeast(22.dp)).align(Alignment.BottomCenter)
                    .padding(3.dp).clip(RoundedCornerShape(3.dp)), contentScale = ContentScale.Crop)
                Text(releases.size.toString(), Modifier.align(Alignment.BottomEnd).padding(4.dp).clip(RoundedCornerShape(8.dp))
                    .background(if (selected) Color.White else Color.Black.copy(.7f)).padding(horizontal = 4.dp, vertical = 1.dp)
                    .testTag("calendar-day-count-$date"), color = titleColor, fontSize = 8.sp)
            } else if (releases.size > 1) {
                // Busy dates show the actual posters, whether focused or not. Reserve room
                // for the date and overflow before sizing artwork so six-week months fit.
                val shown = releases.take(3)
                val extra = releases.size - shown.size
                val availableWidth = (maxWidth - 32.dp - 3.dp * (shown.size - 1) - if (extra > 0) 20.dp else 0.dp).coerceAtLeast(1.dp)
                val posterHeight = minOf((maxHeight - 8.dp).coerceAtLeast(1.dp), availableWidth / shown.size * 1.5f)
                Row(Modifier.fillMaxSize().padding(start = 27.dp, end = 5.dp, top = 4.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(3.dp), verticalAlignment = Alignment.CenterVertically) {
                    shown.forEachIndexed { index, release ->
                        Box(Modifier.size(posterHeight / 1.5f, posterHeight).clip(RoundedCornerShape(3.dp))
                            .background(if (selected) Color(0xFFE3E6E8) else Color(0xFF171B20))
                            .testTag("calendar-poster-$date-$index"), contentAlignment = Alignment.Center) {
                            Text(release.media.title.take(1), color = secondary, fontSize = 10.sp)
                            AsyncImage(release.media.image.takeIf { it.isNotBlank() } ?: release.artwork(), null,
                                Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                        }
                    }
                    if (extra > 0) Text("+$extra", color = secondary, fontSize = 9.sp, maxLines = 1,
                        modifier = Modifier.testTag("calendar-more-$date"))
                }
            } else {
                val dense = maxHeight < 45.dp
                val dayTextStyle = LocalTextStyle.current.copy(platformStyle = PlatformTextStyle(includeFontPadding = false))
                Column(Modifier.fillMaxSize().padding(start = 27.dp, top = if (selected) 2.dp else if (dense) 6.dp else 13.dp, end = if (selected) 44.dp else 5.dp, bottom = if (selected) 0.dp else 2.dp),
                    verticalArrangement = if (selected) Arrangement.Top else Arrangement.Center) {
                    Text(first.media.title, color = titleColor, style = dayTextStyle, fontSize = 10.sp, lineHeight = 11.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(releaseSubtitle(first, timezone, locale, concise = selected && releases.size > 1), color = secondary,
                        style = dayTextStyle, fontSize = 9.sp, lineHeight = 10.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                if (selected) Column(Modifier.align(Alignment.CenterEnd).padding(end = 5.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    releases.take(1).forEach { release ->
                        AsyncImage(release.artwork(), null, Modifier.size(width = 39.dp, height = if (dense) 25.dp else 18.dp).clip(RoundedCornerShape(3.dp)), contentScale = ContentScale.Crop)
                    }
                }
            }
        }
    }
}

@Composable
private fun CalendarReleaseCard(release: CalendarRelease, timezone: ZoneId, locale: Locale, width: Dp,
    compact: Boolean, modifier: Modifier, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    Column(modifier.width(width).testTag("calendar-release-${release.id}").onFocusChanged { focused = it.isFocused }
        .clip(RoundedCornerShape(6.dp)).border(if (focused) 1.5.dp else 0.dp, if (focused) Color.White else Color.Transparent, RoundedCornerShape(6.dp))
        .clickable(onClick = onClick)) {
        Box(Modifier.fillMaxWidth().height(if (compact) 112.dp else 84.dp).clip(RoundedCornerShape(5.dp))) {
            AsyncImage(release.artwork(), release.media.title, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            if (!release.logoUrl.isNullOrBlank()) {
                Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(.45f)))))
                AsyncImage(release.logoUrl, null, Modifier.align(Alignment.BottomEnd).padding(10.dp).width(width * .52f).height(43.dp), contentScale = ContentScale.Fit)
            }
        }
        Text(release.media.title, color = Color.White, fontSize = if (compact) 14.sp else 12.sp, lineHeight = 15.sp,
            fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 3.dp))
        Text(releaseSubtitle(release, timezone, locale), color = CalendarSecondary, fontSize = if (compact) 11.sp else 10.sp,
            lineHeight = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Text(release.sourceIds.mapNotNull { id -> ReleaseCalendarSource.entries.firstOrNull { it.id == id }?.label }
            .joinToString(" + "), color = CalendarSecondary.copy(.85f), fontSize = 8.sp, lineHeight = 11.sp, maxLines = 1)
    }
}

private fun CalendarRelease.artwork(): String? = media.episodeStill?.takeIf { it.isNotBlank() }
    ?: media.backdrop?.takeIf { it.isNotBlank() } ?: media.image.takeIf { it.isNotBlank() }

@Composable
private fun releaseSubtitle(release: CalendarRelease, timezone: ZoneId, locale: Locale, concise: Boolean = false): String {
    val time = release.releaseInstant?.atZone(timezone)?.format(DateTimeFormatter.ofPattern("HH:mm", locale)) ?: tr("Time TBA")
    if (concise) return time
    val label = if (release.kind == CalendarReleaseKind.EPISODE) {
        if (release.seasonNumber != null && release.episodeNumber != null) String.format(Locale.ROOT, "S%02d E%02d", release.seasonNumber, release.episodeNumber)
        else tr("Episode")
    } else listOfNotNull(tr(release.kind.label), release.region).joinToString(" · ")
    return "$label · $time"
}

internal fun calendarMonthDays(month: YearMonth): List<LocalDate> {
    val first = month.atDay(1)
    val start = first.minusDays((first.dayOfWeek.value - 1).toLong())
    val cells = ((first.dayOfWeek.value - 1 + month.lengthOfMonth() + 6) / 7) * 7
    return List(cells) { start.plusDays(it.toLong()) }
}
