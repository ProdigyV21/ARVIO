package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arflix.tv.R
import com.arflix.tv.data.model.SportsEventArtwork
import com.arflix.tv.data.model.SportsFollow
import com.arflix.tv.data.model.sportsFollowKey
import com.arflix.tv.data.repository.SportsMetadataRepository
import com.arflix.tv.data.repository.SportsPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay

/** What was last seen of one followed game. */
internal data class SportsScoreState(val home: Int?, val away: Int?, val status: String)

internal data class SportsScoreAlert(
    val eventId: String,
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int,
    val awayScore: Int,
    val final: Boolean,
)

/** Games of followed teams in a scores slice, by fixture id. */
internal fun followedScores(items: List<SportsEventArtwork>, follows: Collection<SportsFollow>): Map<String, Pair<SportsEventArtwork, SportsScoreState>> {
    val teams = follows.filter { it.kind == SportsFollow.KIND_TEAM }.mapTo(hashSetOf()) { sportsFollowKey(it.name) }
    if (teams.isEmpty()) return emptyMap()
    return items.mapNotNull { item ->
        val fixture = item.fixture ?: return@mapNotNull null
        val home = item.homeTeam ?: return@mapNotNull null
        val away = item.awayTeam ?: return@mapNotNull null
        if (sportsFollowKey(home) !in teams && sportsFollowKey(away) !in teams) return@mapNotNull null
        fixture.id to (item to SportsScoreState(fixture.homeScore, fixture.awayScore, fixture.status))
    }.toMap()
}

/**
 * Alerts between two observations: a score went up, or a game ended. The first
 * observation only sets the baseline. Basketball scores every few seconds, so it
 * only alerts at the end. The game being watched is skipped: its stream may run
 * behind the score feed.
 */
internal fun detectScoreAlerts(
    previous: Map<String, SportsScoreState>?,
    current: Map<String, Pair<SportsEventArtwork, SportsScoreState>>,
    watchingEventId: String?,
): List<SportsScoreAlert> {
    if (previous == null) return emptyList()
    return current.mapNotNull { (id, entry) ->
        val (item, now) = entry
        val before = previous[id] ?: return@mapNotNull null
        if (id == watchingEventId) return@mapNotNull null
        val home = now.home ?: return@mapNotNull null
        val away = now.away ?: return@mapNotNull null
        val ended = now.status == "finished" && before.status != "finished"
        val basketball = item.genres.any { it.contains("basketball", ignoreCase = true) }
        val scored = before.home != null && before.away != null && home + away > before.home + before.away && now.status == "live"
        if (!ended && (!scored || basketball)) return@mapNotNull null
        SportsScoreAlert(id, item.homeTeam.orEmpty(), item.awayTeam.orEmpty(), home, away, final = ended)
    }
}

/** Scores are checked this often while a followed team plays, else this often to spot kick-off. */
internal const val SPORTS_SCORE_POLL_LIVE_MS = 60_000L
internal const val SPORTS_SCORE_POLL_IDLE_MS = 10 * 60_000L

/** A followed game that is live, about to start, or may have just ended. */
internal fun Map<String, Pair<SportsEventArtwork, SportsScoreState>>.anyActive(now: Long): Boolean = values.any { (item, state) ->
    val start = item.startsAt ?: return@any false
    state.status == "live" || (state.status != "finished" && start - now < 15 * 60_000L && now - start < 4 * 3600_000L)
}

@HiltViewModel
class SportsScoreAlertViewModel @Inject constructor(
    val preferences: SportsPreferencesRepository,
    val metadata: SportsMetadataRepository,
) : ViewModel()

/**
 * Score alerts for followed teams, over every screen: a small card in a corner
 * for a few seconds. It never takes focus, so it does not interrupt the remote.
 */
@Composable
fun SportsScoreAlertHost(enabled: Boolean) {
    val viewModel: SportsScoreAlertViewModel = hiltViewModel()
    val preferences = viewModel.preferences
    val follows by preferences.follows.collectAsStateWithLifecycle(initialValue = emptyList())
    val alertsOn by preferences.scoreAlerts.collectAsStateWithLifecycle(initialValue = true)
    var queue by remember { mutableStateOf(emptyList<SportsScoreAlert>()) }
    val followsTeams = follows.any { it.kind == SportsFollow.KIND_TEAM }
    LaunchedEffect(enabled, alertsOn, followsTeams, follows) {
        if (!enabled || !alertsOn || !followsTeams) return@LaunchedEffect
        var seen: Map<String, SportsScoreState>? = null
        while (true) {
            val items = viewModel.metadata.loadScores()
            var wait = SPORTS_SCORE_POLL_IDLE_MS
            if (items != null) {
                val current = followedScores(items, follows)
                val alerts = detectScoreAlerts(seen, current, preferences.watchingEventId.value)
                if (alerts.isNotEmpty()) queue = (queue + alerts).takeLast(4)
                seen = current.mapValues { it.value.second }
                if (current.anyActive(System.currentTimeMillis())) wait = SPORTS_SCORE_POLL_LIVE_MS
            } else wait = 2 * SPORTS_SCORE_POLL_LIVE_MS
            delay(wait)
        }
    }
    val alert = queue.firstOrNull()
    // Kept through the fade-out, so the card does not empty while it leaves.
    var lastShown by remember { mutableStateOf<SportsScoreAlert?>(null) }
    if (alert != null && alert != lastShown) lastShown = alert
    LaunchedEffect(alert) {
        if (alert == null) return@LaunchedEffect
        delay(8_000)
        queue = queue.drop(1)
    }
    Box(Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.navigationBars), contentAlignment = Alignment.TopEnd) {
        AnimatedVisibility(visible = enabled && alert != null, enter = fadeIn(), exit = fadeOut()) {
            val shown = lastShown ?: return@AnimatedVisibility
            Column(
                Modifier
                    .padding(20.dp)
                    .widthIn(max = 360.dp)
                    .testTag("sports-score-alert")
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xF2161616))
                    .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.EmojiEvents, null, tint = liveAccent(), modifier = Modifier.size(16.dp))
                    Text(stringResource(if (shown.final) R.string.sports_alert_final else R.string.sports_alert_score),
                        color = LiveColors.FgDim, fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp))
                }
                Text("${shown.homeTeam}  ${shown.homeScore} – ${shown.awayScore}  ${shown.awayTeam}", color = LiveColors.Fg,
                    fontSize = 15.sp, fontWeight = FontWeight.SemiBold, maxLines = 2, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp))
            }
        }
    }
}
