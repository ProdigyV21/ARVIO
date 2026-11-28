package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.util.replaceLiteral

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.ui.focus.mirrorHorizontalForRtl
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

/*
 * The Sports hub on a TV: a left rail with the filters and per-sport counts, a row of
 * "jump to" chips, a large game panel with its actions, and team-colour match cards.
 * Kept apart from SportsGuidePane, which owns the data flow and D-pad navigation.
 */

// ---------------------------------------------------------------------------------------
// Rows
// ---------------------------------------------------------------------------------------

internal const val SPORTS_FILTER_MINE = "mine"
internal const val SPORTS_ROW_LIVE = "live-now"
internal const val SPORTS_ROW_SOON = "starting-soon"
internal const val SPORTS_ROW_CHANNELS = "live-channels"

/** Games that start within this window are "starting soon" and show a countdown. */
internal const val SPORTS_SOON_WINDOW_MS = 3 * 60 * 60_000L

/**
 * Cards per row. A big playlist can have thousands of games on at once; a row on a TV
 * shows the best ones and the counts still say how many there are.
 */
internal const val SPORTS_ROW_CAP = 60

internal data class SportsHubLabels(val live: String, val soon: String, val channels: String)

/**
 * The overview: what is on now, what starts soon, then one row per sport (or, when a
 * single sport is picked, one row per competition). Each game appears once in the top
 * rows and once in its own sport or competition row, like a TV home screen.
 */
internal fun sportsHubRows(
    events: List<SportsGuideEvent>,
    now: Long,
    byCompetition: Boolean,
    labels: SportsHubLabels,
): List<SportsGuideRow> {
    val visible = events.filter { it.hasChannels(now) && !it.isFinished() &&
        (it.isOnAir(now) || it.isScheduledNow(now) || it.programme.startUtcMillis > now) }
    val onAir = visible.filter { it.isOnAir(now) }
    val live = onAir.filterNot { it.channelOnly }
        .sortedWith(compareByDescending<SportsGuideEvent> { it.prominence }.thenBy { it.programme.startUtcMillis }.thenBy { it.id }) +
        visible.filter { it.isScheduledNow(now) && !it.channelOnly }.sortedBy { it.programme.startUtcMillis }
    val channels = onAir.filter { it.channelOnly }
    val soon = visible.filter { !it.isOnAir(now) && !it.isScheduledNow(now) &&
        it.programme.startUtcMillis > now && it.programme.startUtcMillis - now <= SPORTS_SOON_WINDOW_MS }
        .sortedWith(compareBy<SportsGuideEvent> { it.programme.startUtcMillis }.thenByDescending { it.prominence }.thenBy { it.id })
    val rest = visible.filterNot { it.channelOnly }
        .sortedWith(compareByDescending<SportsGuideEvent> { it.isOnAir(now) || it.isScheduledNow(now) }
            .thenBy { it.programme.startUtcMillis }.thenByDescending { it.prominence }.thenBy { it.id })
    fun row(id: String, title: String, items: List<SportsGuideEvent>) =
        SportsGuideRow(id, title, if (items.size > SPORTS_ROW_CAP) items.take(SPORTS_ROW_CAP) else items, items.size)
    return buildList {
        if (live.isNotEmpty()) add(row(SPORTS_ROW_LIVE, labels.live, live.distinctBy { it.id }))
        if (soon.isNotEmpty()) add(row(SPORTS_ROW_SOON, labels.soon, soon))
        if (byCompetition) {
            rest.groupBy { it.competition?.takeIf { name -> name.isNotBlank() } ?: it.fixture?.league?.takeIf { name -> name.isNotBlank() } ?: it.sport.title }
                .entries.sortedWith(compareByDescending<Map.Entry<String, List<SportsGuideEvent>>> { entry -> entry.value.maxOf { it.prominence } }
                    .thenBy { entry -> entry.value.minOf { it.programme.startUtcMillis } })
                .forEach { (name, items) -> add(row("competition-${name.lowercase(Locale.ROOT)}", name, items)) }
        } else {
            GuideSport.entries.forEach { sport ->
                val items = rest.filter { it.sport == sport }
                if (items.isNotEmpty()) add(row(sport.name, sport.title, items))
            }
        }
        if (channels.isNotEmpty()) add(row(SPORTS_ROW_CHANNELS, labels.channels, channels))
    }
}

/**
 * A refresh keeps the games already on screen in the order they were shown, and adds
 * new ones after them. Re-sorting a row under the remote moved the focused card away
 * (or out of the row) every time the schedule refreshed.
 */
internal fun stabilizeSportsRows(previous: List<SportsGuideRow>, next: List<SportsGuideRow>): List<SportsGuideRow> {
    if (previous.isEmpty()) return next
    val old = previous.associateBy { it.id }
    return next.map { row ->
        val before = old[row.id] ?: return@map row
        val rank = HashMap<String, Int>(before.events.size * 2)
        before.events.forEachIndexed { index, event -> rank.putIfAbsent(event.id, index) }
        val kept = row.events.filter { it.id in rank }.sortedBy { rank.getValue(it.id) }
        if (kept.size == row.events.size && kept.map { it.id } == row.events.map { it.id }) row
        else row.copy(events = kept + row.events.filter { it.id !in rank })
    }
}

/**
 * Scores come from a separate feed that lags behind a schedule refresh. Until it catches
 * up, a game keeps the score it last had (for a few hours), so it doesn't flip to "v".
 */
internal fun carrySportsFixtures(events: List<SportsGuideEvent>, memory: MutableMap<String, com.arflix.tv.data.model.SportsFixture>,
    now: Long): List<SportsGuideEvent> {
    var changed = false
    val result = events.map { event ->
        val fixture = event.fixture
        val remembered = memory[event.id]
        if (fixture != null && fixture.homeScore == null && remembered?.homeScore != null && remembered.id == fixture.id &&
            now - remembered.observedAt < 4 * 60 * 60_000L) {
            // A refresh that knows the game but not (yet) its score keeps the last score.
            changed = true
            event.copy(fixture = fixture.copy(homeScore = remembered.homeScore, awayScore = remembered.awayScore))
        } else if (fixture != null) { memory[event.id] = fixture; event }
        else memory[event.id]?.takeIf { now - it.observedAt < 4 * 60 * 60_000L }?.let { changed = true; event.copy(fixture = it) } ?: event
    }
    if (memory.size > 4000) memory.keys.toList().take(memory.size - 4000).forEach(memory::remove)
    return if (changed) result else events
}

/** Games per sport for the rail: how many, and how many of those are on now. */
internal data class SportsRailCount(val games: Int, val live: Int)

internal fun sportsRailCounts(events: List<SportsGuideEvent>, now: Long, wholeDay: Boolean = false): Map<GuideSport?, SportsRailCount> {
    // A picked day counts every game of that day, also finished ones and fixtures without a channel.
    val visible = if (wholeDay) events.filterNot { it.channelOnly } else events.filter { it.hasChannels(now) && !it.isFinished() &&
        (it.isOnAir(now) || it.isScheduledNow(now) || it.programme.startUtcMillis > now) && !it.channelOnly }
    val result = HashMap<GuideSport?, SportsRailCount>()
    // "Live" counts the same games as the Live now row: on air, or due to be on now.
    fun live(e: SportsGuideEvent) = e.isOnAir(now) || (!wholeDay && e.isScheduledNow(now))
    result[null] = SportsRailCount(visible.size, visible.count(::live))
    visible.groupBy { it.sport }.forEach { (sport, items) ->
        result[sport] = SportsRailCount(items.size, items.count(::live))
    }
    return result
}

// ---------------------------------------------------------------------------------------
// Team identity: short codes and colours
// ---------------------------------------------------------------------------------------

private val teamCodes: Map<String, String> = mapOf(
    // Football
    "manchester city" to "MCI", "man city" to "MCI", "manchester united" to "MUN", "man united" to "MUN", "man utd" to "MUN",
    "tottenham hotspur" to "TOT", "tottenham" to "TOT", "spurs" to "TOT", "west ham united" to "WHU", "west ham" to "WHU",
    "aston villa" to "AVL", "brighton and hove albion" to "BHA", "brighton & hove albion" to "BHA", "brighton" to "BHA",
    "newcastle united" to "NEW", "newcastle" to "NEW", "nottingham forest" to "NFO", "wolverhampton wanderers" to "WOL",
    "wolves" to "WOL", "crystal palace" to "CRY", "leicester city" to "LEI", "leeds united" to "LEE", "sheffield united" to "SHU",
    "afc bournemouth" to "BOU", "bournemouth" to "BOU", "ipswich town" to "IPS", "southampton" to "SOU",
    "real madrid" to "RMA", "atletico madrid" to "ATM", "atlético madrid" to "ATM", "athletic bilbao" to "ATH", "real sociedad" to "RSO",
    "real betis" to "BET", "barcelona" to "BAR", "fc barcelona" to "BAR", "bayern munich" to "FCB", "bayern münchen" to "FCB",
    "fc bayern münchen" to "FCB", "borussia dortmund" to "BVB", "bayer leverkusen" to "B04", "rb leipzig" to "RBL",
    "paris saint-germain" to "PSG", "paris saint germain" to "PSG", "paris sg" to "PSG", "olympique marseille" to "OM",
    "olympique lyonnais" to "OL", "inter milan" to "INT", "internazionale" to "INT", "inter" to "INT", "ac milan" to "MIL",
    "juventus" to "JUV", "as roma" to "ROM", "ss lazio" to "LAZ", "napoli" to "NAP", "ssc napoli" to "NAP",
    "ajax" to "AJA", "afc ajax" to "AJA", "psv" to "PSV", "psv eindhoven" to "PSV", "feyenoord" to "FEY", "az alkmaar" to "AZ",
    "fc twente" to "TWE", "fc utrecht" to "UTR", "sporting cp" to "SCP", "benfica" to "BEN", "sl benfica" to "BEN", "fc porto" to "POR",
    "celtic" to "CEL", "rangers" to "RAN", "galatasaray" to "GAL", "fenerbahce" to "FEN", "fenerbahçe" to "FEN",
    // Basketball
    "boston celtics" to "BOS", "los angeles lakers" to "LAL", "la lakers" to "LAL", "los angeles clippers" to "LAC",
    "la clippers" to "LAC", "golden state warriors" to "GSW", "denver nuggets" to "DEN", "new york knicks" to "NYK",
    "brooklyn nets" to "BKN", "miami heat" to "MIA", "milwaukee bucks" to "MIL", "philadelphia 76ers" to "PHI",
    "oklahoma city thunder" to "OKC", "san antonio spurs" to "SAS", "new orleans pelicans" to "NOP", "portland trail blazers" to "POR",
    "minnesota timberwolves" to "MIN", "phoenix suns" to "PHX", "dallas mavericks" to "DAL", "houston rockets" to "HOU",
    // American football
    "kansas city chiefs" to "KC", "buffalo bills" to "BUF", "san francisco 49ers" to "SF", "new england patriots" to "NE",
    "green bay packers" to "GB", "las vegas raiders" to "LV", "los angeles rams" to "LAR", "los angeles chargers" to "LAC",
    "new york giants" to "NYG", "new york jets" to "NYJ", "tampa bay buccaneers" to "TB", "new orleans saints" to "NO",
    "philadelphia eagles" to "PHI", "dallas cowboys" to "DAL", "baltimore ravens" to "BAL", "detroit lions" to "DET",
)

private val ignoredTeamWords = setOf("fc", "cf", "afc", "sc", "ac", "as", "sv", "ssc", "club", "de", "the", "cd", "fk", "sk", "bk", "if", "sl")
private val clubSuffixes = setOf("city", "united", "utd", "town", "county", "rovers", "athletic", "albion", "wanderers", "hotspur")

/** A short code for a team ("Liverpool" -> LIV, "Manchester City" -> MCI, "Golden State Warriors" -> GSW). */
internal fun sportsTeamCode(name: String): String {
    val plain = name.trim()
    teamCodes[plain.lowercase(Locale.ROOT)]?.let { return it }
    val words = plain.split(' ', '-', '.', '/').map { it.trim() }
        .filter { it.isNotBlank() && it.first().isLetterOrDigit() && it.lowercase(Locale.ROOT) !in ignoredTeamWords }
    val code = when {
        words.isEmpty() -> plain.filter { it.isLetterOrDigit() }.take(3)
        words.size == 1 -> words[0].take(3)
        words.size >= 3 -> words.take(3).joinToString("") { it.take(1) }
        // "Manchester City" reads as MCI; "Boston Celtics" by its city, BOS.
        words[1].lowercase(Locale.ROOT) in clubSuffixes -> words[0].take(1) + words[1].take(2)
        else -> words[0].take(3)
    }
    return code.uppercase(Locale.ROOT).ifBlank { "?" }
}

private val teamPalette = listOf(
    0xFFC8102E, 0xFF0B3D91, 0xFF00843D, 0xFF6A1B9A, 0xFFE65100, 0xFF00838F,
    0xFF1565C0, 0xFFAD1457, 0xFF2E7D32, 0xFF283593, 0xFF8E0000, 0xFF4E342E,
).map { Color(it) }

/** A steady colour per team name, used until (or instead of) the badge's own colour. */
internal fun sportsTeamFallbackColor(name: String, avoid: Color? = null): Color {
    val index = Math.floorMod(name.trim().lowercase(Locale.ROOT).hashCode(), teamPalette.size)
    val color = teamPalette[index]
    return if (color == avoid) teamPalette[(index + 5) % teamPalette.size] else color
}

/** The badge colours found so far, by badge URL. 0 means the badge had no usable colour. */
internal object SportsTeamColors {
    private val cache = java.util.concurrent.ConcurrentHashMap<String, Int>()
    // The colour a team got from its badge, so a game that arrives without badge art
    // (cached schedule, add-on only) keeps the same colours instead of flipping.
    private val byTeam = java.util.concurrent.ConcurrentHashMap<String, Int>()

    fun forTeam(name: String): Color? = byTeam[name.trim().lowercase(Locale.ROOT)]?.let { Color(it) }
    fun store(name: String, color: Color) { byTeam[name.trim().lowercase(Locale.ROOT)] = color.toArgbInt() }

    fun cached(url: String?): Color? = url?.let { cache[it] }?.takeIf { it != 0 }?.let { Color(it) }
    fun known(url: String?): Boolean = url != null && cache.containsKey(url)

    suspend fun load(context: Context, url: String): Color? {
        cache[url]?.let { return if (it == 0) null else Color(it) }
        val request = ImageRequest.Builder(context).data(url).size(64, 64).allowHardware(false)
            .memoryCacheKey("sports-badge-colour|$url").build()
        val result = context.imageLoader.execute(request) as? SuccessResult ?: return null
        val bitmap = (result.drawable as? BitmapDrawable)?.bitmap ?: return null
        val argb = withContext(Dispatchers.Default) {
            val palette = androidx.palette.graphics.Palette.from(bitmap).maximumColorCount(12).generate()
            (palette.vibrantSwatch ?: palette.darkVibrantSwatch ?: palette.dominantSwatch ?: palette.mutedSwatch)?.rgb ?: 0
        }
        cache[url] = argb
        return if (argb == 0) null else Color(argb)
    }
}

/** A team's colour: its badge colour once known, a steady fallback until then. */
@Composable
internal fun rememberSportsTeamColor(name: String, badge: String?, avoid: Color? = null): State<Color> {
    val context = LocalContext.current
    val fallback = remember(name, avoid) { sportsTeamFallbackColor(name, avoid) }
    var found by remember(badge) { mutableStateOf(SportsTeamColors.cached(badge)) }
    LaunchedEffect(badge) {
        if (badge == null || SportsTeamColors.known(badge)) return@LaunchedEffect
        found = try { SportsTeamColors.load(context, badge) } catch (e: CancellationException) { throw e } catch (_: Exception) { null }
    }
    // Very dark or washed-out badge colours read as a black or grey card; keep the fallback then.
    val usable = found?.takeIf { it.luminance() in 0.025f..0.8f }
    LaunchedEffect(usable, name) { usable?.let { SportsTeamColors.store(name, it) } }
    val known = remember(name, badge) { SportsTeamColors.forTeam(name) }
    val target = usable ?: known ?: fallback
    return animateColorAsState(target, tween(320), label = "sports-team-colour")
}

private fun Color.toArgbInt(): Int = android.graphics.Color.argb((alpha * 255).toInt(), (red * 255).toInt(), (green * 255).toInt(), (blue * 255).toInt())

private fun Color.readableText(): Color = if (luminance() > 0.5f) Color(0xFF0B0B0D) else Color.White

// ---------------------------------------------------------------------------------------
// Match art: two team panels on a slanted seam
// ---------------------------------------------------------------------------------------

/** Both teams of a game, or null when the title is not a two-team matchup. */
internal fun SportsGuideEvent.matchupTeams(): Pair<String, String>? =
    teamNames().takeIf { it.size == 2 }?.let { it[0] to it[1] }

@Composable
internal fun SportsSplitArt(
    home: String,
    away: String,
    homeBadge: String?,
    awayBadge: String?,
    modifier: Modifier = Modifier,
    large: Boolean = false,
    score: SportsScoreLine? = null,
) {
    val homeColor = rememberSportsTeamColor(home, homeBadge)
    val awayFallbackAvoid = remember(home) { sportsTeamFallbackColor(home) }
    val awayColor = rememberSportsTeamColor(away, awayBadge, avoid = awayFallbackAvoid)
    Box(modifier.testTag("sports-split-art").drawWithCache {
        val w = size.width
        val h = size.height
        val slant = w * 0.07f
        val mid = w * 0.5f
        val left = Path().apply { moveTo(0f, 0f); lineTo(mid + slant, 0f); lineTo(mid - slant, h); lineTo(0f, h); close() }
        val right = Path().apply { moveTo(mid + slant, 0f); lineTo(w, 0f); lineTo(w, h); lineTo(mid - slant, h); close() }
        val shade = Brush.verticalGradient(0f to Color.White.copy(alpha = .10f), .42f to Color.Transparent, 1f to Color.Black.copy(alpha = .58f))
        val sheen = Brush.horizontalGradient(0f to Color.Black.copy(alpha = .18f), .5f to Color.Transparent, 1f to Color.Black.copy(alpha = .18f))
        val seam = 1.5.dp.toPx()
        onDrawBehind {
            drawPath(left, homeColor.value)
            drawPath(right, awayColor.value)
            drawRect(sheen)
            drawRect(shade)
            drawLine(Color.White.copy(alpha = .45f), Offset(mid + slant, 0f), Offset(mid - slant, h), strokeWidth = seam)
        }
    }) {
        val pad = if (large) 16.dp else 8.dp
        SportsTeamTag(home, homeBadge, homeColor.value.readableText(), large, start = true,
            modifier = Modifier.align(Alignment.TopStart).padding(start = pad, top = pad).fillMaxWidth(.46f))
        SportsTeamTag(away, awayBadge, awayColor.value.readableText(), large, start = false,
            modifier = Modifier.align(Alignment.TopEnd).padding(end = pad, top = pad).fillMaxWidth(.46f))
        // Centre mark: the score when there is one, otherwise a small "v".
        val mark = score?.text() ?: "v"
        Text(mark, color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, maxLines = 1,
            fontSize = if (score != null) (if (large) 22.sp else 13.sp) else (if (large) 14.sp else 10.sp),
            lineHeight = if (large) 26.sp else 14.sp,
            modifier = Modifier.align(Alignment.TopCenter).padding(top = if (large) 22.dp else 12.dp)
                .clip(RoundedCornerShape(if (large) 8.dp else 5.dp)).background(Color.Black.copy(alpha = .62f))
                .padding(horizontal = if (large) 10.dp else 6.dp, vertical = if (large) 4.dp else 2.dp)
                .testTag(if (score != null) "sports-split-score" else "sports-split-versus"))
    }
}

@Composable
private fun SportsTeamTag(name: String, badge: String?, color: Color, large: Boolean, start: Boolean, modifier: Modifier) {
    val code = remember(name) { sportsTeamCode(name) }
    // Club badge when the schedule has one; a national team gets its flag.
    val flag = remember(name, badge) { if (badge == null) sportsCountryFlag(name) else null }
    val markSize = if (large) 26.dp else 18.dp
    @Composable fun mark() {
        if (badge != null) SportsTeamBadge(name, badge, markSize, if (large) 96 else 64)
        else if (flag != null) Text(flag, fontSize = if (large) 22.sp else 15.sp, lineHeight = if (large) 26.sp else 18.sp, maxLines = 1)
    }
    val hasMark = badge != null || flag != null
    Column(modifier, horizontalAlignment = if (start) Alignment.Start else Alignment.End) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (hasMark && start) {
                mark()
                Spacer(Modifier.width(if (large) 6.dp else 4.dp))
            }
            Text(code, color = color, fontWeight = FontWeight.Bold, maxLines = 1, softWrap = false, letterSpacing = (-0.5).sp,
                fontSize = if (large) 30.sp else 21.sp, lineHeight = if (large) 34.sp else 24.sp)
            if (hasMark && !start) {
                Spacer(Modifier.width(if (large) 6.dp else 4.dp))
                mark()
            }
        }
        // A short underline in the text colour, like a kit stripe.
        Box(Modifier.padding(top = if (large) 4.dp else 2.dp).width(if (large) 26.dp else 14.dp).height(if (large) 3.dp else 2.dp)
            .background(color.copy(alpha = .85f), RoundedCornerShape(2.dp)))
        Text(name, color = color.copy(alpha = .92f), fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis,
            textAlign = if (start) TextAlign.Start else TextAlign.End,
            fontSize = if (large) 14.sp else 10.sp, lineHeight = if (large) 17.sp else 12.sp,
            modifier = Modifier.padding(top = if (large) 4.dp else 2.dp))
    }
}

// ---------------------------------------------------------------------------------------
// Status text
// ---------------------------------------------------------------------------------------

/** "In 29 min" / "In 1 h 34" for a game that starts within the soon window, else null. */
@Composable
internal fun sportsCountdown(start: Long, now: Long): String? {
    val minutes = ((start - now + 59_999L) / 60_000L)
    if (start <= now || start - now > SPORTS_SOON_WINDOW_MS) return null
    return if (minutes < 60) stringResource(R.string.sports_in_minutes, minutes.toInt())
    else stringResource(R.string.sports_in_hours, (minutes / 60).toInt(), (minutes % 60).toInt())
}

private val channelQualityTail = Regex("(?:\\s+(?:hd|fhd|uhd|4k|sd|raw|ᴿᴬᵂ|ᴴᴰ|ᶠᴴᴰ|backup|\\+|1080p?|720p?))+\\s*$", RegexOption.IGNORE_CASE)
private val channelLabelHead = Regex("^[^:|·]{1,24}[:|·]\\s*")

/** A channel name without playlist and country labels or quality tags, for a small badge. */
internal fun sportsChannelBadge(name: String): String {
    var rest = name.trim()
    repeat(3) {
        val next = rest.replaceFirst(channelLabelHead, "")
        if (next == rest || next.isBlank()) return@repeat
        rest = next
    }
    return rest.replaceLiteral(channelQualityTail, "").trim().ifBlank { name.trim() }
}

// ---------------------------------------------------------------------------------------
// Left rail
// ---------------------------------------------------------------------------------------

internal data class SportsRailEntry(
    val key: String,
    val label: String,
    val detail: String? = null,
    val liveDetail: String? = null,
    val count: Int? = null,
    val sport: GuideSport? = null,
    val icon: ImageVector? = null,
    val liveDot: Boolean = false,
    val selected: Boolean = false,
    val chevron: Boolean = false,
    val focus: FocusRequester? = null,
)

/**
 * The page's left column: back and title, the day, then one entry per filter with how
 * many games it has and how many are live. Right moves into the page, Left leaves it.
 */
@Composable
internal fun SportsRail(
    title: String,
    filters: List<SportsRailEntry>,
    tools: List<SportsRailEntry>,
    backFocus: FocusRequester,
    onBack: (() -> Unit)?,
    onPick: (String) -> Unit,
    onRight: () -> Unit,
    onLeft: () -> Unit,
    sectionLabel: String,
    modifier: Modifier = Modifier,
    // Up from the first entry: leaves the page for the app's top bar.
    onUpFromTop: (() -> Unit)? = null,
) {
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    fun keys(top: Boolean) = Modifier.onPreviewKeyEvent { key ->
        if (key.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
        when (key.key.mirrorHorizontalForRtl(isRtl)) {
            Key.DirectionRight -> { onRight(); true }
            Key.DirectionLeft -> { onLeft(); true }
            Key.DirectionUp -> if (top && onUpFromTop != null) { onUpFromTop(); true } else false
            else -> false
        }
    }
    val topKey = if (onBack != null) "back" else (tools.firstOrNull() ?: filters.firstOrNull())?.key
    Column(modifier.width(SportsRailWidth).fillMaxHeight().verticalScroll(rememberScrollState())
        .padding(start = 14.dp, end = 8.dp, top = 12.dp, bottom = 16.dp).testTag("sports-rail")) {
        Row(Modifier.padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            if (onBack != null) {
                SportsHeaderIcon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back),
                    modifier = Modifier.focusRequester(backFocus).then(keys(top = true)), onClick = onBack)
                Spacer(Modifier.width(8.dp))
            }
            Text(title, color = LiveColors.Fg, fontSize = 22.sp, lineHeight = 26.sp, fontWeight = FontWeight.Bold, maxLines = 1)
        }
        tools.forEach { entry ->
            SportsRailItem(entry, Modifier.then(entry.focus?.let { Modifier.focusRequester(it) } ?: Modifier)
                .then(keys(top = entry.key == topKey)).testTag("sports-rail-${entry.key}")) { onPick(entry.key) }
        }
        Text(sectionLabel.uppercase(Locale.getDefault()), color = LiveColors.FgMute, fontSize = 10.sp, letterSpacing = 1.2.sp,
            fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 10.dp, top = 14.dp, bottom = 6.dp))
        filters.forEach { entry ->
            SportsRailItem(entry, Modifier.then(entry.focus?.let { Modifier.focusRequester(it) } ?: Modifier)
                .then(keys(top = entry.key == topKey)).testTag("sports-rail-${entry.key}")) { onPick(entry.key) }
        }
    }
}

internal val SportsRailWidth = 206.dp

@Composable
private fun SportsRailItem(entry: SportsRailEntry, modifier: Modifier, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val bg by animateColorAsState(when {
        focused -> Color.White
        entry.selected -> Color.White.copy(alpha = .11f)
        else -> Color.Transparent
    }, tween(140), label = "sports-rail-bg")
    val fg = if (focused) Color(0xFF0B0B0D) else LiveColors.Fg
    val dim = if (focused) Color(0xFF45454C) else LiveColors.FgDim
    val nudge by animateFloatAsState(if (focused) 1f else 0f, androidx.compose.animation.core.spring(dampingRatio = .8f, stiffness = 500f), label = "sports-rail-nudge")
    Row(modifier.graphicsLayer { translationX = nudge * 3.dp.toPx() }.padding(vertical = 2.dp).fillMaxWidth().heightIn(min = if (entry.detail != null) 48.dp else 40.dp)
        .clip(RoundedCornerShape(10.dp)).background(bg)
        .onFocusChanged { focused = it.isFocused }.clickable(onClick = onClick)
        .padding(horizontal = 10.dp, vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
        // The chosen filter keeps a short bar on its edge, also while another row has focus.
        Box(Modifier.width(3.dp).height(22.dp).clip(RoundedCornerShape(2.dp))
            .background(if (entry.selected && !focused) Color.White else Color.Transparent))
        Spacer(Modifier.width(7.dp))
        when {
            entry.sport != null -> SportsThumb(entry.sport, 24.dp)
            entry.liveDot -> Box(Modifier.size(24.dp), contentAlignment = Alignment.Center) {
                Box(Modifier.size(9.dp).background(LiveColors.LiveRed, CircleShape))
            }
            entry.icon != null -> Icon(entry.icon, null, tint = fg, modifier = Modifier.size(20.dp))
        }
        Column(Modifier.weight(1f).padding(start = 10.dp)) {
            Text(entry.label, color = fg, fontSize = 14.sp, lineHeight = 17.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                fontWeight = if (entry.selected || focused) FontWeight.SemiBold else FontWeight.Medium)
            if (entry.detail != null || entry.liveDetail != null) Row {
                entry.detail?.let { Text(it, color = dim, fontSize = 11.sp, lineHeight = 14.sp, maxLines = 1,
                    overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f, fill = false)) }
                entry.liveDetail?.let {
                    Text((if (entry.detail != null) " · " else "") + it, color = if (focused) Color(0xFFC62828) else Color(0xFFFF6B60),
                        fontSize = 11.sp, lineHeight = 14.sp, fontWeight = FontWeight.SemiBold, maxLines = 1)
                }
            }
        }
        entry.count?.let { Text(it.toString(), color = dim, fontSize = 12.sp, modifier = Modifier.padding(start = 6.dp)) }
        if (entry.chevron) Icon(Icons.Default.ExpandMore, null, tint = fg, modifier = Modifier.padding(start = 4.dp).size(18.dp))
    }
}

// ---------------------------------------------------------------------------------------
// Jump to
// ---------------------------------------------------------------------------------------

internal data class SportsJumpChip(val rowId: String, val label: String, val count: Int, val live: Boolean)

/** One chip per row; selecting a chip moves focus to that row's first game. */
@Composable
internal fun SportsJumpChips(
    chips: List<SportsJumpChip>,
    activeRowId: String?,
    label: String,
    firstFocus: FocusRequester,
    onJump: (String) -> Unit,
    onUp: () -> Unit,
    onDown: () -> Unit,
    onLeftEdge: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    Row(modifier.fillMaxWidth().padding(start = 18.dp, top = 6.dp, bottom = 2.dp).testTag("sports-jump"),
        verticalAlignment = Alignment.CenterVertically) {
        Text(label.uppercase(Locale.getDefault()), color = LiveColors.FgMute, fontSize = 10.sp, letterSpacing = 1.2.sp,
            fontWeight = FontWeight.SemiBold, maxLines = 1, modifier = Modifier.padding(end = 10.dp))
        // Not lazy: there are only a few chips, and the first must always be there to take focus.
        Row(Modifier.horizontalScroll(rememberScrollState()).padding(horizontal = 6.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            chips.forEachIndexed { index, chip -> key(chip.rowId) {
                SportsJumpChipView(chip, active = chip.rowId == activeRowId,
                    modifier = Modifier.then(if (index == 0) Modifier.focusRequester(firstFocus) else Modifier)
                        .onPreviewKeyEvent { key ->
                            if (key.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                            when (key.key.mirrorHorizontalForRtl(isRtl)) {
                                Key.DirectionUp -> { onUp(); true }
                                Key.DirectionDown -> { onDown(); true }
                                Key.DirectionLeft -> if (index == 0) { onLeftEdge(); true } else false
                                Key.DirectionRight -> index == chips.lastIndex
                                else -> false
                            }
                        }.testTag("sports-jump-${chip.rowId}")) { onJump(chip.rowId) }
            } }
        }
    }
}

@Composable
private fun SportsJumpChipView(chip: SportsJumpChip, active: Boolean, modifier: Modifier, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (focused) 1.06f else 1f, tween(150, easing = FastOutSlowInEasing), label = "sports-jump-scale")
    val fg = if (focused) Color(0xFF0B0B0D) else LiveColors.Fg
    Row(modifier.graphicsLayer { scaleX = scale; scaleY = scale }.height(30.dp).clip(RoundedCornerShape(50))
        .background(if (focused) Color.White else if (active) Color.White.copy(alpha = .18f) else Color.White.copy(alpha = .07f))
        .border(1.dp, if (active && !focused) Color.White.copy(alpha = .35f) else Color.Transparent, RoundedCornerShape(50))
        .onFocusChanged { focused = it.isFocused }.clickable(onClick = onClick).padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically) {
        if (chip.live) Box(Modifier.padding(end = 6.dp).size(7.dp).background(LiveColors.LiveRed, CircleShape))
        Text(chip.label, color = fg, fontSize = 12.sp, lineHeight = 14.sp, maxLines = 1,
            fontWeight = if (focused || active) FontWeight.SemiBold else FontWeight.Medium)
        if (chip.count > 0) Text(chip.count.toString(), color = if (focused) Color(0xFF45454C) else LiveColors.FgMute,
            fontSize = 11.sp, modifier = Modifier.padding(start = 6.dp))
    }
}

// ---------------------------------------------------------------------------------------
// Game panel
// ---------------------------------------------------------------------------------------

internal data class SportsHeroAction(
    val key: String,
    val label: String,
    val icon: ImageVector,
    val primary: Boolean = false,
    val active: Boolean = false,
    val iconOnly: Boolean = false,
    val onClick: () -> Unit,
)

/**
 * The game the D-pad is on (or the top live game), large, with its actions: watch,
 * reminder, follow, multiview and all sources. Up goes to the chips, Down to the rows.
 */
@Composable
internal fun SportsHero(
    event: SportsGuideEvent,
    competition: String,
    status: String,
    statusLive: Boolean,
    timeLine: String?,
    channelNames: List<String>,
    channelsLabel: String,
    score: SportsScoreLine?,
    progress: Float,
    actions: List<SportsHeroAction>,
    firstFocus: FocusRequester,
    onUp: () -> Unit,
    onDown: () -> Unit,
    onLeftEdge: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    Row(modifier.fillMaxWidth().height(SportsHeroHeight).padding(horizontal = 18.dp, vertical = 6.dp)
        .clip(RoundedCornerShape(14.dp)).background(LiveColors.Panel).testTag("sports-focus-preview"),
        verticalAlignment = Alignment.CenterVertically) {
        val artHeight = SportsHeroHeight - 12.dp
        // The picture cross-fades to the next game as focus moves along a row.
        androidx.compose.animation.Crossfade(targetState = event, animationSpec = tween(220), label = "sports-hero-art",
            modifier = Modifier.fillMaxHeight().width(artHeight * 16f / 9f).clip(RoundedCornerShape(topStart = 14.dp, bottomStart = 14.dp))) { shown ->
            Box(Modifier.fillMaxSize()) {
                SportsEventVisual(shown, Modifier.fillMaxSize(), large = true, score = if (shown.id == event.id) score else shown.scoreLine(System.currentTimeMillis()))
                if (statusLive && shown.id == event.id) SportsLivePill(status, large = true,
                    modifier = Modifier.align(Alignment.BottomStart).padding(10.dp))
            }
        }
        Column(Modifier.weight(1f).fillMaxHeight().padding(start = 18.dp, end = 16.dp, top = 10.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            // The text eases in from a little below each time the game changes.
            val textIn = remember { androidx.compose.animation.core.Animatable(1f) }
            LaunchedEffect(event.id) {
                textIn.snapTo(0f)
                textIn.animateTo(1f, tween(240, easing = FastOutSlowInEasing))
            }
            Column(Modifier.graphicsLayer { alpha = .35f + .65f * textIn.value; translationY = (1f - textIn.value) * 6.dp.toPx() }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(competition.uppercase(Locale.getDefault()), color = LiveColors.Fg, fontSize = 10.sp, lineHeight = 12.sp,
                        letterSpacing = 1.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.widthIn(max = 260.dp).clip(RoundedCornerShape(4.dp)).background(Color.White.copy(alpha = .12f))
                            .padding(horizontal = 6.dp, vertical = 3.dp))
                    Text(status, color = if (statusLive) Color(0xFFFF6B60) else LiveColors.FgDim, fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold, maxLines = 1, modifier = Modifier.padding(start = 10.dp))
                }
                Row(Modifier.padding(top = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(event.displayTitle(), color = LiveColors.Fg, fontSize = 20.sp, lineHeight = 24.sp, fontWeight = FontWeight.SemiBold,
                        maxLines = 1, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f, fill = false).testTag("sports-focus-preview-title"))
                    if (score != null) Text(score.text(), color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 12.dp).testTag("sports-focus-preview-score"))
                }
                timeLine?.let {
                    Text(it, color = LiveColors.FgDim, fontSize = 12.sp, lineHeight = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 3.dp))
                }
                if (channelNames.isNotEmpty()) Row(Modifier.padding(top = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(channelsLabel, color = LiveColors.FgMute, fontSize = 11.sp, maxLines = 1)
                    channelNames.take(2).forEach { name ->
                        Text(name, color = LiveColors.Fg, fontSize = 11.sp, lineHeight = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = 6.dp).widthIn(max = 150.dp).clip(RoundedCornerShape(4.dp))
                                .background(Color.White.copy(alpha = .10f)).padding(horizontal = 6.dp, vertical = 2.dp))
                    }
                    if (channelNames.size > 2) Text("+${channelNames.size - 2}", color = LiveColors.FgDim, fontSize = 11.sp,
                        modifier = Modifier.padding(start = 6.dp))
                }
            }
            androidx.compose.foundation.layout.BoxWithConstraints {
                // A narrow panel (the drawer open, a small screen) keeps every button in
                // view by showing the secondary ones as icons only.
                val compact = maxWidth < (actions.size * 150).dp
                Column {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    actions.forEachIndexed { index, action ->
                        SportsHeroButton(if (compact && !action.primary) action.copy(iconOnly = true) else action,
                            Modifier.then(if (index == 0) Modifier.focusRequester(firstFocus) else Modifier)
                                .onPreviewKeyEvent { key ->
                                    if (key.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                                    when (key.key.mirrorHorizontalForRtl(isRtl)) {
                                        Key.DirectionUp -> { onUp(); true }
                                        Key.DirectionDown -> { onDown(); true }
                                        Key.DirectionLeft -> if (index == 0) { onLeftEdge(); true } else false
                                        Key.DirectionRight -> index == actions.lastIndex
                                        else -> false
                                    }
                                }.testTag("sports-hero-${action.key}"))
                    }
                }
                // How far the game is, from the guide's start and end times.
                Box(Modifier.padding(top = 8.dp).fillMaxWidth().height(3.dp).clip(RoundedCornerShape(2.dp))
                    .background(Color.White.copy(alpha = if (progress > 0f) .14f else 0f))) {
                    val shownProgress by animateFloatAsState(progress.coerceIn(0f, 1f), tween(400, easing = FastOutSlowInEasing), label = "sports-hero-progress")
                    if (progress > 0f) Box(Modifier.fillMaxHeight().fillMaxWidth(shownProgress)
                        .background(Brush.horizontalGradient(listOf(Color(0xFFFF3B30), Color(0xFFFF8A65)))))
                }
                }
            }
        }
    }
}

internal val SportsHeroHeight = 168.dp

@Composable
private fun SportsHeroButton(action: SportsHeroAction, modifier: Modifier) {
    var focused by remember { mutableStateOf(false) }
    val interaction = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale by animateFloatAsState(when { pressed -> .94f; focused -> 1.06f; else -> 1f },
        androidx.compose.animation.core.spring(dampingRatio = .75f, stiffness = 600f), label = "sports-hero-button")
    val bg by animateColorAsState(when {
        focused -> Color.White
        action.primary -> LiveColors.LiveRed
        action.active -> Color.White.copy(alpha = .20f)
        else -> Color.White.copy(alpha = .08f)
    }, tween(140), label = "sports-hero-button-bg")
    val fg = if (focused) Color(0xFF0B0B0D) else Color.White
    Row(modifier.graphicsLayer { scaleX = scale; scaleY = scale }.height(38.dp)
        .then(if (action.iconOnly) Modifier.widthIn(min = 38.dp) else Modifier)
        .clip(RoundedCornerShape(50)).background(bg)
        .border(1.dp, if (action.active && !focused) Color.White.copy(alpha = .4f) else Color.Transparent, RoundedCornerShape(50))
        .onFocusChanged { focused = it.isFocused }.clickable(interactionSource = interaction, indication = null, onClick = action.onClick)
        .padding(horizontal = if (action.iconOnly) 10.dp else if (action.primary) 18.dp else 14.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Icon(action.icon, if (action.iconOnly) action.label else null, tint = fg, modifier = Modifier.size(18.dp))
        if (!action.iconOnly) Text(action.label, color = fg, fontSize = 13.sp, lineHeight = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 7.dp).widthIn(max = 180.dp))
    }
}

// ---------------------------------------------------------------------------------------
// The picture of a game
// ---------------------------------------------------------------------------------------

/**
 * Two-team games get the team panels; anything else (a race, a fight night, a channel)
 * keeps its banner or the sport photo.
 */
@Composable
internal fun SportsEventVisual(
    event: SportsGuideEvent,
    modifier: Modifier = Modifier,
    knownSize: androidx.compose.ui.unit.DpSize? = null,
    large: Boolean = false,
    score: SportsScoreLine? = null,
) {
    val teams = remember(event.id, event.title, event.teamArtwork) { if (event.channelOnly || event.isNetwork) null else event.matchupTeams() }
    if (teams != null) SportsSplitArt(teams.first, teams.second, event.teamArtwork?.homeBadge, event.teamArtwork?.awayBadge,
        modifier, large = large, score = score)
    else EventArtwork(event, modifier, knownSize = knownSize, large = large, score = score)
}

/** The live game, the next soon game, or a time: what a card says about when it is on. */
internal enum class SportsCardStatus { Live, Soon, Later, Finished }

internal fun SportsGuideEvent.cardStatus(now: Long): SportsCardStatus = when {
    isFinished() -> SportsCardStatus.Finished
    isOnAir(now) || isScheduledNow(now) -> SportsCardStatus.Live
    programme.startUtcMillis > now && programme.startUtcMillis - now <= SPORTS_SOON_WINDOW_MS -> SportsCardStatus.Soon
    else -> SportsCardStatus.Later
}

/** The channel shown on a card: the first live one, else the first listed, else the add-on. */
internal fun SportsGuideEvent.cardChannel(now: Long): String? {
    val channel: IptvChannel? = (if (isOnAir(now)) availableChannels(now) else channels).firstOrNull() ?: possibleChannels.firstOrNull()
    return channel?.name?.let(::sportsChannelBadge) ?: addonSources.firstOrNull()?.addonName
}

@Composable
internal fun SportsChannelBadge(name: String, modifier: Modifier = Modifier) {
    Text(name, color = Color.White, fontSize = 9.sp, lineHeight = 11.sp, fontWeight = FontWeight.SemiBold, maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.clip(RoundedCornerShape(5.dp)).background(Color.Black.copy(alpha = .72f))
            .border(1.dp, Color.White.copy(alpha = .14f), RoundedCornerShape(5.dp))
            .padding(horizontal = 5.dp, vertical = 2.dp))
}

@Composable
internal fun SportsCountdownPill(text: String, modifier: Modifier = Modifier) {
    Text(text.uppercase(Locale.getDefault()), color = Color.White, fontSize = 9.sp, lineHeight = 11.sp, fontWeight = FontWeight.Bold,
        letterSpacing = .6.sp, maxLines = 1,
        modifier = modifier.clip(RoundedCornerShape(5.dp)).background(Color.Black.copy(alpha = .72f))
            .padding(horizontal = 5.dp, vertical = 2.dp))
}

@Composable
internal fun SportsSportIcon(sport: GuideSport?, size: Dp) {
    if (sport != null) SportsThumb(sport, size)
    else Icon(Icons.Default.SportsSoccer, null, tint = LiveColors.Fg, modifier = Modifier.size(size))
}

internal val SportsRailDayIcon = Icons.Default.CalendarToday
internal val SportsRailTeamsIcon = Icons.Default.Star

// ---------------------------------------------------------------------------------------
// Phone
// ---------------------------------------------------------------------------------------

/** Phone filters: the day, then every filter as a pill with its count, scrolling sideways. */
@Composable
internal fun SportsPhoneFilters(
    dayLabel: String,
    dayActive: Boolean,
    filters: List<SportsRailEntry>,
    onPickDay: () -> Unit,
    onPick: (String) -> Unit,
    onMultiview: (() -> Unit)?,
    onRecordings: (() -> Unit)?,
    multiviewLabel: String,
    recordingsLabel: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(horizontal = 14.dp, vertical = 8.dp)
        .testTag("sports-toolbar"), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        SportsPill(dayLabel, Icons.Default.CalendarToday, active = dayActive, chevron = true, touch = true,
            modifier = Modifier.testTag("sports-day-picker"), onClick = onPickDay)
        filters.forEach { entry ->
            val sport = entry.sport
            val lead: (@Composable () -> Unit)? = when {
                sport != null -> { { SportsThumb(sport, 22.dp) } }
                entry.liveDot -> { { Box(Modifier.size(8.dp).background(LiveColors.LiveRed, CircleShape)) } }
                else -> null
            }
            SportsPill(
                label = entry.label + (entry.count?.takeIf { it > 0 }?.let { "  $it" } ?: ""),
                icon = if (sport == null && !entry.liveDot) entry.icon else null,
                active = entry.selected, touch = true,
                leading = lead,
                modifier = Modifier.testTag("sports-filter-${entry.key}"),
            ) { onPick(entry.key) }
        }
        if (onMultiview != null) SportsPill(multiviewLabel, Icons.Default.GridView, iconOnly = true, touch = true,
            modifier = Modifier.testTag("sports-tool-multiview"), onClick = onMultiview)
        if (onRecordings != null) SportsPill(recordingsLabel, Icons.Default.VideoLibrary, iconOnly = true, touch = true,
            modifier = Modifier.testTag("sports-tool-recordings"), onClick = onRecordings)
    }
}

/** The featured game on a phone: big team art, then what it is and its actions. */
@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
internal fun SportsHeroCompact(
    event: SportsGuideEvent,
    competition: String,
    status: String,
    statusLive: Boolean,
    timeLine: String?,
    score: SportsScoreLine?,
    progress: Float,
    actions: List<SportsHeroAction>,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 6.dp).clip(RoundedCornerShape(16.dp))
        .background(LiveColors.Panel).testTag("sports-focus-preview")) {
        Box(Modifier.fillMaxWidth().aspectRatio(16f / 8f)) {
            key(event.id) { SportsEventVisual(event, Modifier.fillMaxSize(), large = true, score = score) }
            if (statusLive) SportsLivePill(status, large = true, modifier = Modifier.align(Alignment.BottomStart).padding(10.dp))
            if (progress > 0f) Box(Modifier.align(Alignment.BottomStart).fillMaxWidth(progress.coerceIn(0f, 1f)).height(3.dp)
                .background(Brush.horizontalGradient(listOf(Color(0xFFFF3B30), Color(0xFFFF8A65)))))
        }
        Column(Modifier.padding(horizontal = 14.dp, vertical = 12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(competition.uppercase(Locale.getDefault()), color = LiveColors.Fg, fontSize = 10.sp, lineHeight = 12.sp,
                    letterSpacing = 1.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false).clip(RoundedCornerShape(4.dp)).background(Color.White.copy(alpha = .12f))
                        .padding(horizontal = 6.dp, vertical = 3.dp))
                if (!statusLive) Text(status, color = LiveColors.FgDim, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, maxLines = 1,
                    modifier = Modifier.padding(start = 10.dp))
            }
            Text(event.displayTitle(), color = LiveColors.Fg, fontSize = 18.sp, lineHeight = 22.sp, fontWeight = FontWeight.SemiBold,
                maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 8.dp).testTag("sports-focus-preview-title"))
            timeLine?.let { Text(it, color = LiveColors.FgDim, fontSize = 12.sp, lineHeight = 15.sp, maxLines = 1,
                overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 3.dp)) }
            // Wraps, so a longer label (Following ...) never pushes a button off the card.
            FlowRow(Modifier.padding(top = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {
                actions.forEach { action -> SportsHeroButton(action, Modifier.testTag("sports-hero-${action.key}")) }
            }
        }
    }
}

/** A card's picture with its status (live, countdown or time), channel and live progress. */
@Composable
internal fun SportsCardArt(
    event: SportsGuideEvent,
    now: Long,
    focused: Boolean,
    artSize: androidx.compose.ui.unit.DpSize,
    reminded: Boolean,
    timeLabel: (SportsGuideEvent) -> String,
    liveLabel: String,
    scheduledLabel: String,
    maxBadgeWidth: Dp,
) {
    val score = event.scoreLine(now)
    val status = event.cardStatus(now)
    val split = remember(event.id, event.title, event.teamArtwork) { event.matchupTeams() != null && !event.channelOnly && !event.isNetwork }
    Box(Modifier.fillMaxWidth().aspectRatio(16f / 9f).testTag("sports-event-art").clip(SportsCardShape)) {
        SportsEventVisual(event, Modifier.fillMaxSize(), knownSize = artSize, score = score)
        Row(Modifier.align(Alignment.BottomStart).fillMaxWidth().padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
            when {
                status == SportsCardStatus.Live && event.isOnAir(now) -> SportsLivePill(liveLabel)
                status == SportsCardStatus.Live -> SportsCountdownPill(scheduledLabel)
                status == SportsCardStatus.Soon -> SportsCountdownPill(sportsCountdown(event.programme.startUtcMillis, now) ?: timeLabel(event))
                else -> SportsCountdownPill(timeLabel(event))
            }
            if (reminded) Icon(androidx.compose.material.icons.Icons.Default.NotificationsActive, null, tint = Color.White,
                modifier = Modifier.padding(start = 4.dp).size(12.dp))
            Spacer(Modifier.weight(1f))
            val channel = remember(event, now) { event.cardChannel(now) }
            channel?.let { SportsChannelBadge(it, Modifier.padding(start = 6.dp).widthIn(max = maxBadgeWidth)) }
        }
        if (score != null && !split) SportsInfoPill(score.text(), strong = true,
            modifier = Modifier.align(Alignment.TopEnd).padding(7.dp).testTag("sports-card-score"))
        if (event.isOnAir(now)) {
            val progress = liveProgrammeProgress(event.programme, now)
            if (progress > 0f) Box(Modifier.align(Alignment.BottomStart).fillMaxWidth(progress).height(3.dp)
                .background(LiveColors.LiveRed))
        }
        Box(Modifier.matchParentSize().liveFocusOutline(focused, 10.dp))
    }
}

private val sportsNotAStream = Regex(
    "🔒|\\b(premium|upgrade|unlock|locked|subscribe|sale|black friday|discount|tip|donat\\w*|support the project|configure|settings|install)\\b",
    RegexOption.IGNORE_CASE)
private val sportsNotAStreamHosts = setOf("google.com", "www.google.com", "ko-fi.com", "paypal.com", "www.paypal.com", "patreon.com", "www.patreon.com")

/**
 * The stream the Watch button plays: the first one that is a real video in this app.
 * Add-ons also list locked teasers, "use an external player" tips, sale banners and
 * configure links as streams; those are skipped so Watch never lands on a dead player.
 */
internal fun sportsWatchStream(streams: List<com.arflix.tv.data.repository.SportsAddonStream>) = streams.firstOrNull { stream ->
    if (stream.external) return@firstOrNull false
    val uri = runCatching { java.net.URI(stream.url.trim()) }.getOrNull() ?: return@firstOrNull false
    val scheme = uri.scheme?.lowercase()
    if (scheme != "http" && scheme != "https" && scheme != "rtmp" && scheme != "rtsp") return@firstOrNull false
    val host = uri.host?.lowercase().orEmpty()
    val path = uri.path?.trimEnd('/')?.lowercase().orEmpty()
    host.isNotEmpty() && host !in sportsNotAStreamHosts && !path.endsWith("/configure") && path != "/configure" &&
        !sportsNotAStream.containsMatchIn("${stream.name} ${stream.description.orEmpty()}")
}

private val countryCodes: Map<String, String> = buildMap {
    fun add(code: String, vararg names: String) = names.forEach { put(it, code) }
    add("NL", "netherlands", "nederland", "holland"); add("BE", "belgium", "belgie", "belgië", "belgia", "belgique")
    add("DE", "germany", "duitsland", "deutschland", "tyskland"); add("FR", "france", "frankrijk", "frankrike")
    add("ES", "spain", "spanje", "españa", "spania"); add("IT", "italy", "italie", "italië", "italia")
    add("PT", "portugal"); add("GB-ENG", "england", "engeland"); add("GB-SCT", "scotland", "schotland")
    add("GB-WLS", "wales"); add("GB", "great britain", "united kingdom"); add("IE", "ireland", "ierland", "republic of ireland")
    add("NO", "norway", "noorwegen", "norge"); add("SE", "sweden", "zweden", "sverige"); add("DK", "denmark", "denemarken", "danmark")
    add("FI", "finland", "suomi"); add("IS", "iceland", "ijsland"); add("PL", "poland", "polen", "polska")
    add("CZ", "czech republic", "czechia", "tsjechie", "tsjechië"); add("SK", "slovakia"); add("AT", "austria", "oostenrijk", "österreich")
    add("CH", "switzerland", "zwitserland", "schweiz"); add("HR", "croatia", "kroatie", "kroatië", "hrvatska"); add("RS", "serbia", "servie", "servië")
    add("SI", "slovenia"); add("BA", "bosnia and herzegovina", "bosnia"); add("ME", "montenegro"); add("AL", "albania", "albanie", "albanië")
    add("MK", "north macedonia"); add("GR", "greece", "griekenland"); add("TR", "turkey", "turkiye", "türkiye", "turkije")
    add("HU", "hungary", "hongarije"); add("RO", "romania", "roemenie", "roemenië"); add("BG", "bulgaria", "bulgarije")
    add("UA", "ukraine", "oekraine", "oekraïne"); add("GE", "georgia"); add("AM", "armenia"); add("AZ", "azerbaijan")
    add("IL", "israel"); add("CY", "cyprus"); add("LU", "luxembourg", "luxemburg"); add("EE", "estonia"); add("LV", "latvia"); add("LT", "lithuania")
    add("US", "usa", "united states"); add("CA", "canada"); add("MX", "mexico"); add("BR", "brazil", "brazilie", "brazilië", "brasil")
    add("AR", "argentina", "argentinie", "argentinië"); add("UY", "uruguay"); add("CO", "colombia"); add("CL", "chile")
    add("PE", "peru"); add("EC", "ecuador"); add("PY", "paraguay"); add("VE", "venezuela"); add("BO", "bolivia")
    add("JP", "japan"); add("KR", "south korea", "korea republic"); add("CN", "china"); add("AU", "australia", "australie", "australië")
    add("NZ", "new zealand", "nieuw-zeeland"); add("IN", "india"); add("PK", "pakistan"); add("BD", "bangladesh"); add("LK", "sri lanka")
    add("AF", "afghanistan"); add("ZA", "south africa", "zuid-afrika"); add("ZW", "zimbabwe"); add("SA", "saudi arabia")
    add("QA", "qatar"); add("AE", "united arab emirates", "uae"); add("IR", "iran"); add("IQ", "iraq"); add("JO", "jordan")
    add("EG", "egypt"); add("MA", "morocco", "marokko"); add("TN", "tunisia"); add("DZ", "algeria"); add("NG", "nigeria")
    add("GH", "ghana"); add("SN", "senegal"); add("CM", "cameroon", "kameroen"); add("CI", "ivory coast", "cote d'ivoire")
}

/** Squad suffixes such as " U21", " Women" or " A" that still mean the national team. */
private val sportsTeamSuffix =
    Regex("""(\s+(u\d{2}|women|(\(w\))|a|xi|w))+$""")

/**
 * A flag for a national team ("Norge", "Belgium", "England"), as an emoji, or null for
 * a club. England, Scotland and Wales use their own flags.
 */
internal fun sportsCountryFlag(name: String): String? {
    val key = name.trim().lowercase(Locale.ROOT)
        .replaceLiteral(sportsTeamSuffix, "").trim()
    val code = countryCodes[key] ?: return null
    return when (code) {
        "GB-ENG" -> "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F"
        "GB-SCT" -> "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC73\uDB40\uDC63\uDB40\uDC74\uDB40\uDC7F"
        "GB-WLS" -> "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC77\uDB40\uDC6C\uDB40\uDC73\uDB40\uDC7F"
        else -> buildString { code.forEach { appendCodePoint(0x1F1E6 + (it - 'A')) } }
    }
}
