package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.SportsReminder
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextSecondary

/**
 * Reminders for ordinary guide shows ride on the same per-device reminder list
 * as Sports, so one banner (a minute before, with a countdown and Cancel)
 * serves both. The id keeps them apart from game ids.
 */
internal const val PROGRAMME_REMINDER_PREFIX = "tv:"

internal fun programmeReminderId(channelId: String, program: IptvProgram): String =
    "$PROGRAMME_REMINDER_PREFIX$channelId:${program.startUtcMillis}"

/** Channels the reminder may switch to: the channel itself, then its other copies. */
internal fun programmeReminder(channelIds: List<String>, program: IptvProgram): SportsReminder? {
    val first = channelIds.firstOrNull() ?: return null
    return SportsReminder(
        eventId = programmeReminderId(first, program),
        title = program.title.ifBlank { first },
        startsAt = program.startUtcMillis,
        channelIds = channelIds.distinct().take(8),
    )
}

internal fun List<SportsReminder>.withProgrammeReminder(reminder: SportsReminder, on: Boolean): List<SportsReminder> {
    val rest = filterNot { it.eventId == reminder.eventId }
    return if (on) rest + reminder else rest
}

/** Ids of the guide shows that have a reminder, for the bell in the grid and the dialog. */
internal fun List<SportsReminder>.remindedProgrammeIds(): Set<String> =
    asSequence().filter { !it.dismissed && it.eventId.startsWith(PROGRAMME_REMINDER_PREFIX) }.map { it.eventId }.toSet()

/** "in 25m", "in 2h 5m": how long until a show starts, or null once it has. */
@Composable
internal fun programmeStartsInLabel(program: IptvProgram, nowMillis: Long): String? {
    if (program.startUtcMillis <= nowMillis) return null
    val minutes = program.startsInMinutes(nowMillis).coerceAtLeast(1L)
    return if (minutes < 60) {
        stringResource(R.string.live_label_starts_in_min, minutes.toInt())
    } else {
        stringResource(R.string.live_label_starts_in_hm, (minutes / 60).toInt(), (minutes % 60).toInt())
    }
}

/**
 * Details of one guide show that cannot be played right now: a later show
 * (with Remind me) or one that aired on a channel without catch-up. Watch
 * channel tunes the channel live.
 */
@Composable
internal fun ProgrammeInfoDialog(
    channelName: String,
    program: IptvProgram,
    nowMillis: Long,
    timeRange: String,
    reminded: Boolean,
    canRemind: Boolean,
    onToggleReminder: () -> Unit,
    onWatchChannel: () -> Unit,
    onDismiss: () -> Unit,
) {
    val startsIn = programmeStartsInLabel(program, nowMillis)
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            androidx.tv.material3.Text(
                text = program.title,
                style = ArflixTypography.cardTitle,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                androidx.tv.material3.Text(
                    text = listOfNotNull(channelName, timeRange, startsIn).joinToString("  ·  "),
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                )
                program.category?.takeIf { it.isNotBlank() }?.let { category ->
                    androidx.tv.material3.Text(text = category, style = ArflixTypography.caption, color = TextSecondary)
                }
                program.description?.takeIf { it.isNotBlank() }?.let { description ->
                    androidx.tv.material3.Text(
                        text = description,
                        style = ArflixTypography.body,
                        color = Color.White.copy(alpha = 0.86f),
                        modifier = Modifier.heightIn(max = 220.dp).verticalScroll(rememberScrollState()),
                    )
                }
            }
        },
        confirmButton = {
            if (canRemind) {
                androidx.compose.material3.TextButton(onClick = onToggleReminder) {
                    androidx.tv.material3.Text(
                        text = stringResource(if (reminded) R.string.sports_reminder_on else R.string.sports_remind_me),
                        style = ArflixTypography.button,
                        color = Pink,
                    )
                }
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onWatchChannel) {
                androidx.tv.material3.Text(
                    text = stringResource(R.string.epg_watch_live),
                    style = ArflixTypography.button,
                    color = Color.White,
                )
            }
        },
        containerColor = BackgroundElevated,
        tonalElevation = 8.dp,
    )
}
