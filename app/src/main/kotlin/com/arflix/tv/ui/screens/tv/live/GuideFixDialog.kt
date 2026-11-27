package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.arflix.tv.R
import com.arflix.tv.data.repository.IptvGuideFixes
import com.arflix.tv.util.LocalDeviceType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/** One channel the guide can be taken from, with what it shows now as a hint. */
internal data class GuideSourceOption(
    val channelId: String,
    val name: String,
    val playlistName: String?,
    val nowTitle: String?,
)

/**
 * "Fix guide" for one channel: take the guide of another channel (or none), and move
 * the times of the playlist's guide when it runs early or late. A separate dialog
 * window, so the guide's own key handling stays out of it.
 */
@Composable
internal fun GuideFixDialog(
    channelName: String,
    currentSourceId: String?,
    currentSourceName: String?,
    offsetPlaylistName: String?,
    offsetMinutes: Int,
    candidates: List<GuideSourceOption>,
    onPickSource: (String?) -> Unit,
    onOffsetChange: (Int) -> Unit,
    onDismiss: () -> Unit,
) {
    val touchDevice = LocalDeviceType.current.isTouchDevice()
    var query by remember { mutableStateOf("") }
    // A playlist can hold 100k channels: split the names once, rank off the main thread,
    // and wait for a pause in typing before ranking again.
    val prepared by produceState<List<GuideSourceSuggestions.Prepared<GuideSourceOption>>?>(null, candidates) {
        value = withContext(Dispatchers.Default) { GuideSourceSuggestions.prepare(candidates) { it.name } }
    }
    val ranked by produceState(emptyList<GuideSourceOption>(), query, prepared, channelName) {
        val ready = prepared ?: return@produceState
        if (query.isNotBlank()) delay(150L)
        value = withContext(Dispatchers.Default) {
            GuideSourceSuggestions.rankPrepared(channelName, query, ready, hasGuide = { it.nowTitle != null })
        }
    }
    val firstFocus = remember { FocusRequester() }
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        LaunchedEffect(Unit) {
            if (!touchDevice) {
                withFrameNanos { }
                runCatching { firstFocus.requestFocus() }
            }
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .widthIn(max = 600.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(LiveColors.PanelRaised)
                .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(16.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    stringResource(R.string.live_guide_fix_title),
                    style = LiveType.ChannelName.copy(color = LiveColors.Fg, fontSize = 18.sp),
                )
                Text(
                    channelName,
                    style = LiveType.SectionTag.copy(color = LiveColors.FgDim),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            if (offsetPlaylistName != null) {
                GuideOffsetRow(
                    playlistName = offsetPlaylistName,
                    minutes = offsetMinutes,
                    onChange = onOffsetChange,
                    modifier = Modifier.focusRequester(firstFocus),
                )
            }
            Text(
                stringResource(R.string.live_guide_fix_source_heading),
                style = LiveType.SectionTag.copy(color = LiveColors.FgDim),
            )
            GuideSearchField(query = query, onQueryChange = { query = it })
            LazyColumn(
                modifier = Modifier.fillMaxWidth().heightIn(max = 340.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                item(key = "auto") {
                    GuideOptionRow(
                        title = stringResource(R.string.live_guide_fix_automatic),
                        subtitle = stringResource(R.string.live_guide_fix_automatic_desc),
                        selected = currentSourceId == null,
                        modifier = if (offsetPlaylistName == null) Modifier.focusRequester(firstFocus) else Modifier,
                        onClick = { onPickSource(null) },
                    )
                }
                item(key = "none") {
                    GuideOptionRow(
                        title = stringResource(R.string.live_guide_fix_none),
                        subtitle = stringResource(R.string.live_guide_fix_none_desc),
                        selected = currentSourceId == IptvGuideFixes.GUIDE_SOURCE_NONE,
                        onClick = { onPickSource(IptvGuideFixes.GUIDE_SOURCE_NONE) },
                    )
                }
                // The chosen source stays listed even when the search no longer finds it.
                val current = currentSourceId?.takeIf { it != IptvGuideFixes.GUIDE_SOURCE_NONE }
                val shown = if (current != null && ranked.none { it.channelId == current }) {
                    listOf(GuideSourceOption(current, currentSourceName ?: current, null, null)) + ranked
                } else ranked
                items(shown, key = { "ch:${it.channelId}" }) { option ->
                    GuideOptionRow(
                        title = option.name,
                        subtitle = listOfNotNull(
                            option.nowTitle?.let { "▶ $it" },
                            option.playlistName,
                        ).joinToString("  ·  ").ifBlank { null },
                        selected = option.channelId == currentSourceId,
                        onClick = { onPickSource(option.channelId) },
                    )
                }
                if (shown.isEmpty()) {
                    item(key = "empty") {
                        Text(
                            stringResource(if (query.isBlank()) R.string.live_guide_fix_no_suggestions else R.string.live_guide_fix_no_results),
                            style = LiveType.SectionTag.copy(color = LiveColors.FgMute),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GuideOffsetRow(
    playlistName: String,
    minutes: Int,
    onChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var focused by remember { mutableStateOf(false) }
    val step = IptvGuideFixes.OFFSET_STEP_MINUTES
    val max = IptvGuideFixes.MAX_OFFSET_MINUTES
    fun change(delta: Int) = onChange((minutes + delta).coerceIn(-max, max))
    val border by animateColorAsState(if (focused) LiveColors.FocusRing else LiveColors.Divider, tween(120), label = "offset-border")
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (focused) LiveColors.Panel else LiveColors.PanelDeep)
            .border(if (focused) 2.dp else 1.dp, border, RoundedCornerShape(12.dp))
            .onFocusChanged { focused = it.hasFocus }
            .onKeyEvent { event ->
                if (event.type != KeyEventType.KeyDown) return@onKeyEvent false
                when (event.key) {
                    Key.DirectionLeft -> { change(-step); true }
                    Key.DirectionRight -> { change(step); true }
                    else -> false
                }
            }
            .focusable()
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(Icons.Filled.Schedule, contentDescription = null, tint = liveAccent(), modifier = Modifier.size(20.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                stringResource(R.string.live_guide_fix_offset_title),
                style = LiveType.CellTitle.copy(color = LiveColors.Fg, fontSize = 14.sp),
            )
            Text(
                stringResource(R.string.live_guide_fix_offset_desc, playlistName),
                style = LiveType.SectionTag.copy(color = LiveColors.FgMute),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
        StepButton(Icons.Filled.Remove, stringResource(R.string.live_guide_fix_earlier), enabled = minutes > -max) { change(-step) }
        Text(
            formatGuideOffset(minutes),
            style = LiveType.NumberMono.copy(color = if (minutes == 0) LiveColors.FgDim else LiveColors.Fg, fontSize = 15.sp),
            modifier = Modifier.width(56.dp),
            maxLines = 1,
        )
        StepButton(Icons.Filled.Add, stringResource(R.string.live_guide_fix_later), enabled = minutes < max) { change(step) }
    }
}

@Composable
private fun StepButton(icon: ImageVector, description: String, enabled: Boolean, onClick: () -> Unit) {
    // Touch only: on a remote, Left/Right on the row do the same without a focus stop each.
    Box(
        modifier = Modifier
            .size(34.dp)
            .clip(CircleShape)
            .background(LiveColors.PanelRaised)
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, contentDescription = description, tint = if (enabled) LiveColors.Fg else LiveColors.FgMute, modifier = Modifier.size(18.dp))
    }
}

@Composable
private fun GuideSearchField(query: String, onQueryChange: (String) -> Unit) {
    var focused by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(LiveColors.PanelDeep)
            .border(if (focused) 2.dp else 1.dp, if (focused) LiveColors.FocusRing else LiveColors.Divider, RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Icon(Icons.Filled.Search, contentDescription = null, tint = LiveColors.FgDim, modifier = Modifier.size(18.dp))
        Box(modifier = Modifier.weight(1f)) {
            if (query.isEmpty()) {
                Text(
                    stringResource(R.string.live_guide_fix_search_hint),
                    style = LiveType.CellTitle.copy(color = LiveColors.FgMute, fontSize = 14.sp),
                    maxLines = 1,
                )
            }
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                singleLine = true,
                textStyle = LiveType.CellTitle.copy(color = LiveColors.Fg, fontSize = 14.sp),
                cursorBrush = SolidColor(LiveColors.FocusRing),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { }),
                modifier = Modifier.fillMaxWidth().onFocusChanged { focused = it.hasFocus },
            )
        }
    }
}

@Composable
private fun GuideOptionRow(
    title: String,
    subtitle: String?,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var focused by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (focused) LiveColors.Panel else LiveColors.PanelDeep)
            .border(
                width = if (focused) 2.dp else 1.dp,
                color = if (focused) LiveColors.FocusRing else LiveColors.Divider,
                shape = RoundedCornerShape(12.dp),
            )
            .onFocusChanged { focused = it.hasFocus }
            .focusable()
            .clickable(onClick = onClick)
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown && (event.key == Key.DirectionCenter || event.key == Key.Enter)) {
                    onClick()
                    true
                } else false
            }
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(modifier = Modifier.size(20.dp), contentAlignment = Alignment.Center) {
            if (selected) Icon(Icons.Filled.Check, contentDescription = null, tint = liveAccent(), modifier = Modifier.size(18.dp))
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                title,
                style = LiveType.CellTitle.copy(color = LiveColors.Fg, fontSize = 14.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (subtitle != null) {
                Spacer(Modifier.size(2.dp))
                Text(
                    subtitle,
                    style = LiveType.SectionTag.copy(color = LiveColors.FgMute),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
