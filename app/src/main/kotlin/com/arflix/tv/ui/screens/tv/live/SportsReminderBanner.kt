package com.arflix.tv.ui.screens.tv.live

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
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
import com.arflix.tv.data.model.SportsReminder
import com.arflix.tv.data.repository.SportsPreferencesRepository
import com.arflix.tv.data.repository.SportsSwitchRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class SportsReminderViewModel @Inject constructor(
    val preferences: SportsPreferencesRepository,
) : ViewModel()

/**
 * Shown over every screen a minute before a reminded game starts: a short
 * countdown, then Live TV switches to the game's channel. Cancel (or Back)
 * stops it. Focus starts on Cancel, so a stray OK never switches early.
 */
@Composable
fun SportsReminderHost(
    enabled: Boolean,
    isOnLiveTv: () -> Boolean,
    openLiveTv: () -> Unit,
) {
    val viewModel: SportsReminderViewModel = hiltViewModel()
    val preferences = viewModel.preferences
    val reminders by preferences.reminders.collectAsStateWithLifecycle(initialValue = emptyList())
    // Handled this session; also saved as dismissed so a restart does not repeat it.
    var handled by remember { mutableStateOf(emptySet<String>()) }
    var due by remember { mutableStateOf<SportsReminder?>(null) }
    LaunchedEffect(reminders, handled, enabled) {
        if (!enabled) { due = null; return@LaunchedEffect }
        while (true) {
            val now = System.currentTimeMillis()
            val reminder = reminders.dueReminder(now, handled)
            if (reminder != null) { due = reminder; return@LaunchedEffect }
            due = null
            val next = reminders.nextReminderAt(now, handled) ?: return@LaunchedEffect
            delay((next - now).coerceIn(500L, 30_000L))
        }
    }
    val reminder = due ?: return
    val scope = rememberCoroutineScope()
    fun finish() {
        handled = handled + reminder.eventId
        due = null
        scope.launch {
            preferences.updateReminders { list ->
                list.map { if (it.eventId == reminder.eventId) it.copy(dismissed = true) else it }
            }
        }
    }
    fun switchNow() {
        if (reminder.channelIds.isNotEmpty()) {
            preferences.requestSwitch(SportsSwitchRequest(reminder.eventId, reminder.title, reminder.channelIds))
        }
        if (!isOnLiveTv()) openLiveTv()
        finish()
    }
    val hasChannel = reminder.channelIds.isNotEmpty()
    var remaining by remember(reminder.eventId) { mutableIntStateOf((SPORTS_REMINDER_COUNTDOWN_MS / 1000).toInt()) }
    LaunchedEffect(reminder.eventId) {
        if (!hasChannel) {
            // Nothing to switch to: leave the note up for a while, then let it go.
            delay(20_000)
            finish()
            return@LaunchedEffect
        }
        while (remaining > 0) {
            delay(1_000)
            remaining--
        }
        switchNow()
    }
    BackHandler(onBack = ::finish)
    val touch = com.arflix.tv.util.LocalDeviceType.current.isTouchDevice()
    val cancelFocus = remember(reminder.eventId) { FocusRequester() }
    LaunchedEffect(reminder.eventId) {
        if (!touch) {
            delay(50)
            runCatching { cancelFocus.requestFocus() }
        }
    }
    val started = System.currentTimeMillis() >= reminder.startsAt
    Box(Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.navigationBars), contentAlignment = Alignment.BottomEnd) {
        Column(
            Modifier
                .padding(24.dp)
                .widthIn(max = 420.dp)
                .fillMaxWidth()
                .testTag("sports-reminder-banner")
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xF2161616))
                .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(12.dp))
                .padding(18.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.NotificationsActive, null, tint = liveAccent(), modifier = Modifier.size(18.dp))
                Text(
                    stringResource(if (started) R.string.sports_reminder_started else R.string.sports_reminder_kickoff),
                    color = LiveColors.FgDim, fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp),
                )
            }
            Text(reminder.title, color = LiveColors.Fg, fontSize = 17.sp, lineHeight = 21.sp, fontWeight = FontWeight.SemiBold,
                maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 6.dp))
            Text(
                if (hasChannel) stringResource(R.string.sports_reminder_switching, reminder.title, remaining)
                else stringResource(R.string.sports_reminder_no_channel),
                color = LiveColors.FgDim, fontSize = 12.sp, maxLines = 2, overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp),
            )
            Row(Modifier.fillMaxWidth().padding(top = 14.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ReminderButton(
                    label = stringResource(if (hasChannel) R.string.sports_reminder_watch_now else R.string.sports_reminder_open),
                    primary = true,
                ) { switchNow() }
                ReminderButton(stringResource(R.string.sports_reminder_cancel), primary = false,
                    modifier = Modifier.focusRequester(cancelFocus)) { finish() }
            }
        }
    }
}

@Composable
private fun ReminderButton(label: String, primary: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    Text(
        label,
        color = if (primary && !focused) Color.Black else LiveColors.Fg,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        modifier = modifier
            .onFocusChanged { focused = it.isFocused }
            .clip(RoundedCornerShape(8.dp))
            .background(if (focused) LiveColors.FocusBg else if (primary) Color.White else Color.White.copy(alpha = 0.08f))
            .border(2.dp, if (focused) Color.White else Color.Transparent, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .heightIn(min = 40.dp)
            .padding(horizontal = 16.dp, vertical = 10.dp),
    )
}
