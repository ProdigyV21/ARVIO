package com.arflix.tv.ui.screens.tv.live

import android.net.Uri
import android.text.format.Formatter
import androidx.activity.compose.BackHandler
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.arflix.tv.R
import com.arflix.tv.recording.LiveRecording
import com.arflix.tv.recording.LiveRecordingScheduler
import com.arflix.tv.recording.LiveRecordingStore
import java.io.File
import java.text.DateFormat
import java.util.Date

/** The recordings on this device: what is scheduled, running and saved, with play and delete. */
@Composable
internal fun RecordingsDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current
    val recordings by remember { LiveRecordingStore.recordings(context.applicationContext) }.collectAsState()
    var actionsFor by remember { mutableStateOf<LiveRecording?>(null) }
    var playing by remember { mutableStateOf<File?>(null) }
    val timeFormat = remember { DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT) }

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        val first = remember { FocusRequester() }
        LaunchedEffect(recordings.isEmpty()) { runCatching { first.requestFocus() } }
        Column(Modifier.widthIn(max = 640.dp).fillMaxWidth().padding(24.dp).clip(RoundedCornerShape(8.dp))
            .background(LiveColors.Panel).border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(8.dp)).padding(16.dp)) {
            Text(stringResource(R.string.recordings_title), color = LiveColors.Fg, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(stringResource(R.string.recordings_storage_hint), color = LiveColors.FgDim, fontSize = 11.sp, lineHeight = 14.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 10.dp))
            if (recordings.isEmpty()) {
                Text(stringResource(R.string.recordings_empty), color = LiveColors.FgDim, fontSize = 13.sp, modifier = Modifier.padding(vertical = 16.dp))
                RecordingActionRow(stringResource(R.string.close), Icons.Default.Close, Modifier.focusRequester(first), onDismiss)
            } else {
                LazyColumn(Modifier.heightIn(max = 460.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    itemsIndexed(recordings, key = { _, item -> item.id }) { index, recording ->
                        var focused by remember { mutableStateOf(false) }
                        Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(6.dp))
                            .then(if (index == 0) Modifier.focusRequester(first) else Modifier)
                            .background(if (focused) LiveColors.FocusBg else Color.Transparent)
                            .border(1.dp, if (focused) Color.White else Color.Transparent, RoundedCornerShape(6.dp))
                            .onFocusChanged { focused = it.isFocused }
                            .clickable { actionsFor = recording }
                            .padding(horizontal = 10.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            val (icon, tint) = when (recording.status) {
                                LiveRecording.STATUS_RECORDING -> Icons.Default.FiberManualRecord to LiveColors.LiveRed
                                LiveRecording.STATUS_WAITING, LiveRecording.STATUS_SCHEDULED -> Icons.Default.Schedule to LiveColors.FgDim
                                LiveRecording.STATUS_DONE -> Icons.Default.PlayArrow to LiveColors.Fg
                                else -> Icons.Default.Close to LiveColors.FgMute
                            }
                            Icon(icon, null, tint = tint, modifier = Modifier.size(18.dp))
                            Column(Modifier.weight(1f).padding(start = 10.dp)) {
                                Text(recording.title, color = LiveColors.Fg, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                Text(recordingStatusLine(recording, timeFormat), color = LiveColors.FgDim, fontSize = 11.sp,
                                    maxLines = 2, overflow = TextOverflow.Ellipsis)
                            }
                        }
                    }
                }
            }
        }
    }

    actionsFor?.let { selected ->
        val recording = recordings.firstOrNull { it.id == selected.id } ?: selected
        val file = recording.filePath?.let { File(it) }?.takeIf { it.exists() && it.length() > 0 }
        Dialog(onDismissRequest = { actionsFor = null }) {
            val first = remember { FocusRequester() }
            LaunchedEffect(Unit) { runCatching { first.requestFocus() } }
            Column(Modifier.widthIn(max = 360.dp).clip(RoundedCornerShape(8.dp)).background(LiveColors.Panel)
                .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(8.dp)).padding(12.dp)) {
                Text(recording.title, color = LiveColors.Fg, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, maxLines = 2,
                    overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(start = 6.dp, bottom = 8.dp))
                var firstUsed = false
                fun focusFirst(): Modifier = if (!firstUsed) { firstUsed = true; Modifier.focusRequester(first) } else Modifier
                if (file != null) RecordingActionRow(stringResource(R.string.recordings_play), Icons.Default.PlayArrow, focusFirst()) {
                    actionsFor = null
                    playing = file
                }
                if (recording.isActive) RecordingActionRow(stringResource(R.string.recording_stop), Icons.Default.Stop, focusFirst()) {
                    LiveRecordingScheduler.cancel(context, recording.id)
                    actionsFor = null
                } else if (recording.status == LiveRecording.STATUS_SCHEDULED) RecordingActionRow(
                    stringResource(R.string.recordings_cancel), Icons.Default.Close, focusFirst()) {
                    LiveRecordingScheduler.cancel(context, recording.id)
                    actionsFor = null
                }
                if (!recording.isActive) RecordingActionRow(stringResource(R.string.recordings_delete), Icons.Default.Delete, focusFirst()) {
                    LiveRecordingStore.delete(context, recording.id)
                    LiveRecordingScheduler.schedule(context)
                    actionsFor = null
                }
                RecordingActionRow(stringResource(R.string.close), Icons.Default.Close, focusFirst()) { actionsFor = null }
            }
        }
    }

    playing?.let { file -> RecordingPlayer(file) { playing = null } }
}

@Composable
private fun recordingStatusLine(recording: LiveRecording, format: DateFormat): String {
    val context = LocalContext.current
    val size = Formatter.formatShortFileSize(context, recording.bytes)
    val start = format.format(Date(recording.programmeStart))
    return when (recording.status) {
        LiveRecording.STATUS_SCHEDULED -> stringResource(R.string.recordings_status_scheduled, start)
        LiveRecording.STATUS_WAITING -> recording.error ?: stringResource(R.string.recording_waiting_connection)
        LiveRecording.STATUS_RECORDING -> stringResource(R.string.recordings_status_recording, size, recording.recordedChannelName.orEmpty())
        LiveRecording.STATUS_DONE -> listOfNotNull(start, size, recording.recordedChannelName, recording.error).joinToString(" · ")
        LiveRecording.STATUS_CANCELLED -> stringResource(R.string.recordings_status_cancelled, start)
        else -> stringResource(R.string.recordings_status_failed, recording.error.orEmpty())
    }
}

@Composable
private fun RecordingActionRow(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    Row(modifier.fillMaxWidth().heightIn(min = 44.dp).clip(RoundedCornerShape(6.dp))
        .background(if (focused) LiveColors.FocusBg else Color.Transparent)
        .border(1.dp, if (focused) Color.White else Color.Transparent, RoundedCornerShape(6.dp))
        .onFocusChanged { focused = it.isFocused }
        .clickable(onClick = onClick).padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = LiveColors.Fg, modifier = Modifier.size(18.dp))
        Text(label, color = LiveColors.Fg, fontSize = 14.sp, modifier = Modifier.padding(start = 10.dp))
    }
}

/** Plays a saved recording with the standard controls (seek, pause), which work with a remote. */
@OptIn(UnstableApi::class)
@Composable
private fun RecordingPlayer(file: File, onClose: () -> Unit) {
    val context = LocalContext.current
    val player = remember(file) {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.fromFile(file)))
            prepare()
            playWhenReady = true
        }
    }
    DisposableEffect(player) { onDispose { player.release() } }
    Dialog(onDismissRequest = onClose, properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false)) {
        BackHandler(onBack = onClose)
        Box(Modifier.fillMaxSize().background(Color.Black)) {
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        useController = true
                        controllerAutoShow = true
                        this.player = player
                        requestFocus()
                    }
                },
                onRelease = { view -> view.player = null },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
