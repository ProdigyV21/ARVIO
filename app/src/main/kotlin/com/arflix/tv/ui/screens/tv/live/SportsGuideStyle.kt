package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateColorAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Precision
import com.arflix.tv.R
import com.arflix.tv.ui.focus.mirrorHorizontalForRtl

/*
 * Visual building blocks of the Sports page: the generated match artwork used when a
 * game has no banner, live/score badges, the compact toolbar (day, sport, live) and its
 * pickers, and loading skeletons. Kept out of SportsGuidePane so that composable stays
 * small and each piece can be read on its own.
 */

/** The photo behind a game that has no banner of its own. */
internal fun GuideSport.cardDrawable(): Int = when (asset) {
    "football" -> R.drawable.sports_card_football
    "basketball" -> R.drawable.sports_card_basketball
    "motor_sports" -> R.drawable.sports_card_motor_sports
    "tennis" -> R.drawable.sports_card_tennis
    "fight" -> R.drawable.sports_card_fight
    "american_football" -> R.drawable.sports_card_american_football
    "cricket" -> R.drawable.sports_card_cricket
    "baseball" -> R.drawable.sports_card_baseball
    "hockey" -> R.drawable.sports_card_hockey
    "rugby" -> R.drawable.sports_card_rugby
    "golf" -> R.drawable.sports_card_golf
    "billiards" -> R.drawable.sports_card_billiards
    "darts" -> R.drawable.sports_card_darts
    "afl" -> R.drawable.sports_card_afl
    else -> R.drawable.sports_card_other
}

/** A score worth showing: the game is live (confirmed by the feed) or over. */
internal data class SportsScoreLine(val home: Int, val away: Int, val live: Boolean)

internal fun SportsGuideEvent.scoreLine(now: Long): SportsScoreLine? {
    val fixture = fixture ?: return null
    val home = fixture.homeScore ?: return null
    val away = fixture.awayScore ?: return null
    return when {
        isConfirmedLive(now) -> SportsScoreLine(home, away, live = true)
        isFinished() -> SportsScoreLine(home, away, live = false)
        else -> null
    }
}

internal fun SportsScoreLine.text(): String = "$home – $away"

private val leadingLabel = Regex("^([^:]{1,40}):\\s+")

/**
 * The title without a repeated label in front, such as "Football: " or the competition
 * ("Premier League: "), which the card already shows underneath. Event names such as
 * "UFC 310: ..." are kept.
 */
internal fun SportsGuideEvent.displayTitle(): String {
    val match = leadingLabel.find(title) ?: return title
    val prefix = match.groupValues[1].trim().lowercase()
    val redundant = prefix == sport.title.lowercase() || prefix == competition?.trim()?.lowercase() ||
        GuideSport.entries.any { it.title.lowercase() == prefix } || prefix in setOf("live", "soccer", "sport", "sports")
    if (!redundant) return title
    return title.substring(match.range.last + 1).trim().ifBlank { title }
}

/**
 * Match artwork made from what we know: the sport's photo, darkened, with both team
 * badges (or names) on top. Never blank, so a game without a banner still reads as a
 * proper card instead of a grey box with small text.
 */
@Composable
internal fun SportsMatchupArt(
    event: SportsGuideEvent,
    artW: Int,
    artH: Int,
    modifier: Modifier = Modifier,
    large: Boolean = false,
    score: SportsScoreLine? = null,
) {
    val context = LocalContext.current
    val photo = event.sport.cardDrawable()
    val photoRequest = remember(photo, artW, artH) {
        ImageRequest.Builder(context).data(photo).size(artW, artH).precision(Precision.INEXACT)
            .allowHardware(true).crossfade(false).memoryCacheKey("sports-photo|$photo|${artW}x$artH").build()
    }
    Box(modifier.background(LiveColors.PanelRaised)) {
        AsyncImage(photoRequest, null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
        Box(Modifier.fillMaxSize().background(
            Brush.verticalGradient(0f to Color.Black.copy(alpha = .45f), 1f to Color.Black.copy(alpha = .82f))))
        val teams = remember(event.id, event.title, event.teamArtwork) { event.teamNames() }
        val pair = event.teamArtwork
        val badgeSize = if (large) 72.dp else 0.dp
        Row(Modifier.fillMaxSize().padding(horizontal = if (large) 18.dp else 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            if (teams.size == 2) {
                SportsTeamSide(teams[0], pair?.homeBadge, artH, badgeSize, large, Modifier.weight(1f))
                Text(score?.text() ?: "VS", color = Color.White, textAlign = TextAlign.Center,
                    fontSize = if (score != null) (if (large) 28.sp else 18.sp) else (if (large) 15.sp else 11.sp),
                    fontWeight = if (score != null) FontWeight.Bold else FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 6.dp))
                SportsTeamSide(teams[1], pair?.awayBadge, artH, badgeSize, large, Modifier.weight(1f))
            } else {
                Text(event.displayTitle(), color = Color.White, textAlign = TextAlign.Center,
                    fontSize = if (large) 20.sp else 13.sp, lineHeight = if (large) 24.sp else 16.sp,
                    fontWeight = FontWeight.Bold, maxLines = 3, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Composable
private fun SportsTeamSide(name: String, badge: String?, artH: Int, largeBadge: Dp, large: Boolean, modifier: Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        val size = if (large) largeBadge else 34.dp
        SportsTeamBadge(name, badge, size, artH)
        Text(name, color = Color.White, fontSize = if (large) 14.sp else 10.sp, lineHeight = if (large) 17.sp else 12.sp,
            fontWeight = FontWeight.SemiBold, maxLines = if (large) 2 else 1, overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center, modifier = Modifier.padding(top = 4.dp))
    }
}

/** A team's badge, or its initials in a circle when there is none (or it fails to load). */
@Composable
internal fun SportsTeamBadge(name: String, url: String?, size: Dp, decodePx: Int) {
    var failed by remember(url) { mutableStateOf(url.isNullOrBlank()) }
    Box(Modifier.size(size), contentAlignment = Alignment.Center) {
        if (failed) {
            Box(Modifier.fillMaxSize().clip(CircleShape).background(Color.White.copy(alpha = .14f))
                .border(1.dp, Color.White.copy(alpha = .35f), CircleShape), contentAlignment = Alignment.Center) {
                Text(teamInitials(name), color = Color.White, fontWeight = FontWeight.Bold,
                    fontSize = (size.value * .34f).sp, maxLines = 1)
            }
        } else {
            val context = LocalContext.current
            val px = decodePx.coerceIn(48, 256)
            val request = remember(url, px) {
                ImageRequest.Builder(context).data(url).size(px, px).precision(Precision.INEXACT)
                    .allowHardware(true).crossfade(false).memoryCacheKey("sports-badge|$url|$px").build()
            }
            AsyncImage(request, name, contentScale = ContentScale.Fit, onError = { failed = true },
                modifier = Modifier.fillMaxSize())
        }
    }
}

internal fun teamInitials(name: String): String {
    val words = name.split(' ', '-', '.').filter { it.isNotBlank() && it.first().isLetterOrDigit() }
    return when {
        words.isEmpty() -> name.take(2).uppercase()
        words.size == 1 -> words[0].take(3).uppercase()
        else -> words.take(3).joinToString("") { it.first().uppercase() }
    }
}

/** Red pill with a pulsing dot, for games that are on now. */
@Composable
internal fun SportsLivePill(label: String, modifier: Modifier = Modifier, large: Boolean = false) {
    val pulse = rememberInfiniteTransition(label = "sports-live")
    val alpha by pulse.animateFloat(1f, .35f, infiniteRepeatable(tween(900, easing = LinearEasing), RepeatMode.Reverse), label = "sports-live-dot")
    Row(modifier.clip(RoundedCornerShape(6.dp)).background(LiveColors.LiveRed)
        .padding(horizontal = if (large) 8.dp else 6.dp, vertical = if (large) 3.dp else 2.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(if (large) 7.dp else 6.dp).graphicsLayer { this.alpha = alpha }.background(Color.White, CircleShape))
        Text(label, color = Color.White, fontSize = if (large) 12.sp else 10.sp, lineHeight = if (large) 14.sp else 12.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 5.dp))
    }
}

/** Dark pill used for kick-off time, "Finished" and the score on top of artwork. */
@Composable
internal fun SportsInfoPill(text: String, modifier: Modifier = Modifier, strong: Boolean = false, large: Boolean = false) {
    Text(text, color = Color.White, fontSize = if (large) 13.sp else if (strong) 12.sp else 10.sp,
        lineHeight = if (large) 15.sp else 13.sp,
        fontWeight = if (strong) FontWeight.Bold else FontWeight.Medium, maxLines = 1,
        modifier = modifier.clip(RoundedCornerShape(6.dp)).background(Color.Black.copy(alpha = .78f))
            .padding(horizontal = if (large) 8.dp else 6.dp, vertical = if (large) 3.dp else 2.dp))
}

// ---------------------------------------------------------------------------------------
// Toolbar
// ---------------------------------------------------------------------------------------

/**
 * One pill of the toolbar. On a TV the focused pill turns solid white with dark text and
 * grows a little, so it is impossible to miss; the active choice keeps a soft white fill.
 */
@Composable
internal fun SportsPill(
    label: String,
    icon: ImageVector?,
    modifier: Modifier = Modifier,
    active: Boolean = false,
    chevron: Boolean = false,
    leading: (@Composable () -> Unit)? = null,
    iconOnly: Boolean = false,
    touch: Boolean = false,
    onClick: () -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (focused && !touch) 1.06f else 1f, tween(160, easing = FastOutSlowInEasing), label = "sports-pill-scale")
    val bg by animateColorAsState(
        when {
            focused -> Color.White
            active -> Color.White.copy(alpha = .16f)
            else -> Color.White.copy(alpha = .07f)
        }, tween(140), label = "sports-pill-bg")
    val fg = if (focused) Color(0xFF0B0B0D) else LiveColors.Fg
    val height = if (touch) 40.dp else 34.dp
    Row(modifier
        .graphicsLayer { scaleX = scale; scaleY = scale }
        .height(height)
        .clip(RoundedCornerShape(50))
        .background(bg)
        .border(1.dp, if (active && !focused) Color.White.copy(alpha = .38f) else Color.Transparent, RoundedCornerShape(50))
        .onFocusChanged { focused = it.isFocused }
        .clickable(onClick = onClick)
        .padding(horizontal = if (iconOnly) 9.dp else 14.dp),
        verticalAlignment = Alignment.CenterVertically) {
        leading?.invoke()
        if (icon != null) Icon(icon, if (iconOnly) label else null, tint = fg, modifier = Modifier.size(if (touch) 18.dp else 16.dp))
        if (!iconOnly) Text(label, color = fg, fontSize = if (touch) 14.sp else 13.sp, lineHeight = 16.sp,
            fontWeight = if (active || focused) FontWeight.SemiBold else FontWeight.Medium, maxLines = 1,
            modifier = Modifier.padding(start = if (icon != null || leading != null) 7.dp else 0.dp))
        if (chevron) Icon(Icons.Default.ExpandMore, null, tint = fg, modifier = Modifier.padding(start = 4.dp).size(18.dp))
    }
}

/** Day, sport and live filters in one tidy row, with Multiview and Recordings at the end. */
@Composable
internal fun SportsToolbar(
    dayLabel: String,
    dayActive: Boolean,
    sportLabel: String,
    sport: GuideSport?,
    liveOn: Boolean,
    liveCount: Int,
    touch: Boolean,
    firstFocus: FocusRequester,
    sportFocus: FocusRequester,
    onPickDay: () -> Unit,
    onPickSport: () -> Unit,
    onToggleLive: () -> Unit,
    onUp: () -> Unit,
    onDown: () -> Unit,
    onLeaveStart: () -> Unit,
    onMultiview: (() -> Unit)?,
    onRecordings: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    fun Modifier.keys(first: Boolean) = onPreviewKeyEvent { key ->
        if (key.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
        when (key.key.mirrorHorizontalForRtl(isRtl)) {
            Key.DirectionUp -> { onUp(); true }
            Key.DirectionDown -> { onDown(); true }
            Key.DirectionLeft -> if (first) { onLeaveStart(); true } else false
            else -> false
        }
    }
    Row(modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 8.dp).testTag("sports-toolbar"),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        SportsPill(dayLabel, Icons.Default.CalendarToday, active = dayActive, chevron = true, touch = touch,
            modifier = Modifier.focusRequester(firstFocus).keys(first = true).testTag("sports-day-picker"), onClick = onPickDay)
        SportsPill(sportLabel, if (sport == null) Icons.Default.SportsSoccer else null, active = sport != null, chevron = true, touch = touch,
            leading = sport?.let { s -> { SportsThumb(s, if (touch) 22.dp else 20.dp) } },
            modifier = Modifier.focusRequester(sportFocus).keys(first = false).testTag("sports-sport-picker"), onClick = onPickSport)
        SportsPill(
            label = if (liveCount > 0) "${stringResource(R.string.sports_filter_live)} · $liveCount" else stringResource(R.string.sports_filter_live),
            icon = null, active = liveOn, touch = touch,
            leading = { Box(Modifier.size(8.dp).background(LiveColors.LiveRed, CircleShape)) },
            modifier = Modifier.keys(first = false).testTag("sports-live-toggle"), onClick = onToggleLive)
        Spacer(Modifier.weight(1f))
        if (onMultiview != null) SportsPill(stringResource(R.string.multiview_title), Icons.Default.GridView, iconOnly = touch, touch = touch,
            modifier = Modifier.keys(first = false).testTag("sports-tool-multiview"), onClick = onMultiview)
        if (onRecordings != null) SportsPill(stringResource(R.string.recordings_title), Icons.Default.VideoLibrary, iconOnly = touch, touch = touch,
            modifier = Modifier.keys(first = false).testTag("sports-tool-recordings"), onClick = onRecordings)
    }
}

/** The sport's photo as a small round thumbnail, for the sport picker. */
@Composable
internal fun SportsThumb(sport: GuideSport, size: Dp) {
    val context = LocalContext.current
    val res = sport.cardDrawable()
    val request = remember(res) {
        ImageRequest.Builder(context).data(res).size(96, 96).precision(Precision.INEXACT)
            .allowHardware(true).crossfade(false).memoryCacheKey("sports-thumb|$res").build()
    }
    AsyncImage(request, null, contentScale = ContentScale.Crop, modifier = Modifier.size(size).clip(CircleShape))
}

internal data class SportsPickerOption(
    val key: String,
    val label: String,
    val detail: String? = null,
    val sport: GuideSport? = null,
    val live: Boolean = false,
)

/**
 * A short list to choose a day or a sport from. Opens on the current choice, the focused
 * row is solid white, and Back closes it without changing anything.
 */
@Composable
internal fun SportsPickerDialog(
    title: String,
    options: List<SportsPickerOption>,
    selectedKey: String,
    onPick: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    val touch = com.arflix.tv.util.LocalDeviceType.current.isTouchDevice()
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        val window = (LocalView.current.parent as? DialogWindowProvider)?.window
        SideEffect {
            window?.addFlags(android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            window?.setDimAmount(.6f)
        }
        val selectedIndex = options.indexOfFirst { it.key == selectedKey }.coerceAtLeast(0)
        val listState = rememberLazyListState(initialFirstVisibleItemIndex = (selectedIndex - 3).coerceAtLeast(0))
        val initial = remember { FocusRequester() }
        LaunchedEffect(Unit) {
            if (touch) return@LaunchedEffect
            withFrameNanos { }
            withFrameNanos { }
            runCatching { initial.requestFocus() }
        }
        // Enter animation: a short rise and fade, drawn only (no relayout).
        val shown = remember { androidx.compose.animation.core.Animatable(0f) }
        LaunchedEffect(Unit) { shown.animateTo(1f, tween(180, easing = FastOutSlowInEasing)) }
        Column(Modifier
            .graphicsLayer { alpha = shown.value; translationY = (1f - shown.value) * 24.dp.toPx() }
            .widthIn(max = 420.dp).fillMaxWidth(.9f).heightIn(max = 520.dp)
            .clip(RoundedCornerShape(16.dp)).background(LiveColors.Panel)
            .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(16.dp))
            .padding(vertical = 14.dp)
            .testTag("sports-picker")) {
            Text(title, color = LiveColors.Fg, fontSize = 18.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp))
            LazyColumn(state = listState, contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)) {
                itemsIndexed(options, key = { _, option -> option.key }) { index, option ->
                    SportsPickerRow(option, selected = option.key == selectedKey, touch = touch,
                        modifier = if (index == selectedIndex) Modifier.focusRequester(initial) else Modifier) {
                        onPick(option.key)
                    }
                }
            }
        }
    }
}

@Composable
private fun SportsPickerRow(option: SportsPickerOption, selected: Boolean, touch: Boolean, modifier: Modifier, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val bg by animateColorAsState(if (focused) Color.White else if (selected) Color.White.copy(alpha = .10f) else Color.Transparent,
        tween(120), label = "sports-picker-bg")
    val fg = if (focused) Color(0xFF0B0B0D) else LiveColors.Fg
    val dim = if (focused) Color(0xFF3A3A40) else LiveColors.FgDim
    Row(modifier.fillMaxWidth().heightIn(min = if (touch) 52.dp else 46.dp).clip(RoundedCornerShape(10.dp)).background(bg)
        .onFocusChanged { focused = it.isFocused }.clickable(onClick = onClick).padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically) {
        when {
            option.sport != null -> SportsThumb(option.sport, 28.dp)
            option.live -> Box(Modifier.size(28.dp), contentAlignment = Alignment.Center) {
                Box(Modifier.size(10.dp).background(LiveColors.LiveRed, CircleShape))
            }
            else -> Spacer(Modifier.size(0.dp))
        }
        Text(option.label, color = fg, fontSize = 15.sp, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f).padding(start = if (option.sport != null || option.live) 12.dp else 4.dp))
        option.detail?.let { Text(it, color = dim, fontSize = 13.sp, maxLines = 1, modifier = Modifier.padding(start = 8.dp)) }
        if (selected) Icon(Icons.Default.Check, null, tint = fg, modifier = Modifier.padding(start = 10.dp).size(18.dp))
    }
}

// ---------------------------------------------------------------------------------------
// Loading
// ---------------------------------------------------------------------------------------

/** Placeholder rows shaped like the real ones, shown while the schedule loads. */
@Composable
internal fun SportsSkeletonRows(cardWidth: Dp, modifier: Modifier = Modifier, rows: Int = 3, cards: Int = 6) {
    val pulse = rememberInfiniteTransition(label = "sports-skeleton")
    val alpha by pulse.animateFloat(.05f, .12f, infiniteRepeatable(tween(850, easing = LinearEasing), RepeatMode.Reverse), label = "sports-skeleton-alpha")
    Column(modifier.fillMaxWidth().testTag("sports-skeleton"), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        repeat(rows) {
            Column(Modifier.padding(horizontal = 18.dp)) {
                Box(Modifier.width(140.dp).height(14.dp).clip(RoundedCornerShape(4.dp)).graphicsLayer { this.alpha = 1f }
                    .background(Color.White.copy(alpha = alpha)))
                Row(Modifier.padding(top = 10.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    repeat(cards) {
                        Column(Modifier.width(cardWidth)) {
                            Box(Modifier.fillMaxWidth().aspectRatio(16f / 9f).clip(RoundedCornerShape(10.dp))
                                .background(Color.White.copy(alpha = alpha)))
                            Box(Modifier.padding(top = 8.dp).fillMaxWidth(.8f).height(10.dp).clip(RoundedCornerShape(3.dp))
                                .background(Color.White.copy(alpha = alpha)))
                            Box(Modifier.padding(top = 6.dp).fillMaxWidth(.5f).height(8.dp).clip(RoundedCornerShape(3.dp))
                                .background(Color.White.copy(alpha = alpha * .8f)))
                        }
                    }
                }
            }
        }
    }
}

/** A round icon button for the page header with a clear focus state (solid white). */
@Composable
internal fun SportsHeaderIcon(
    icon: ImageVector,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (focused) 1.1f else 1f, tween(150), label = "sports-header-icon")
    Box(modifier.graphicsLayer { scaleX = scale; scaleY = scale }.size(40.dp).clip(CircleShape)
        .background(if (focused) Color.White else Color.Transparent)
        .onFocusChanged { focused = it.isFocused }
        .clickable(onClick = onClick), contentAlignment = Alignment.Center) {
        Icon(icon, description, tint = if (focused) Color(0xFF0B0B0D) else LiveColors.Fg, modifier = Modifier.size(24.dp))
    }
}

/** Row heading: title with a quiet count. */
@Composable
internal fun SportsRowTitle(title: String, count: Int, touch: Boolean, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth().padding(start = 18.dp, end = 18.dp, top = 10.dp, bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(title, color = LiveColors.Fg, fontWeight = FontWeight.SemiBold, fontSize = if (touch) 18.sp else 16.sp,
            lineHeight = 20.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f, fill = false))
        if (count > 0) Text("$count", color = LiveColors.FgMute, fontSize = if (touch) 14.sp else 13.sp,
            modifier = Modifier.padding(start = 8.dp))
    }
}

internal val SportsCardShape = RoundedCornerShape(10.dp)
internal val SportsTitleSize: TextUnit = 13.sp

internal enum class SportsPicker { Day, Sport }

internal const val SPORTS_DAY_OVERVIEW = "overview"

/** Label of the day pill: Overview, Yesterday, Today, Tomorrow or a short date. */
@Composable
internal fun sportsDayLabel(dayOffset: Int?, today: java.time.LocalDate): String = when (dayOffset) {
    null -> stringResource(R.string.sports_overview)
    0 -> stringResource(R.string.live_label_today)
    1 -> stringResource(R.string.live_label_tomorrow)
    -1 -> stringResource(R.string.sports_yesterday)
    else -> today.plusDays(dayOffset.toLong()).format(sportsPickerDayFormat)
}

private val sportsPickerDayFormat: java.time.format.DateTimeFormatter =
    java.time.format.DateTimeFormatter.ofPattern("EEE d MMM", java.util.Locale.getDefault())

/** Overview, then a week back to a week ahead; the date is shown next to relative names. */
@Composable
internal fun sportsDayOptions(today: java.time.LocalDate): List<SportsPickerOption> {
    val overview = stringResource(R.string.sports_overview)
    val todayLabel = stringResource(R.string.live_label_today)
    val tomorrow = stringResource(R.string.live_label_tomorrow)
    val yesterday = stringResource(R.string.sports_yesterday)
    return remember(today, overview, todayLabel, tomorrow, yesterday) {
        listOf(SportsPickerOption(SPORTS_DAY_OVERVIEW, overview)) + SPORTS_DAY_OFFSETS.map { offset ->
            val date = today.plusDays(offset.toLong())
            val formatted = date.format(sportsPickerDayFormat)
            val relative = when (offset) { 0 -> todayLabel; 1 -> tomorrow; -1 -> yesterday; else -> null }
            SportsPickerOption(offset.toString(), relative ?: formatted, detail = if (relative != null) formatted else null)
        }
    }
}
