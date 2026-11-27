package com.arflix.tv.ui.screens.tv.live

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.withFrameNanos
import kotlinx.coroutines.flow.first
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text

/** The full-screen subtitle or audio list, in the same panel style as the source picker. */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun LiveTrackPickerOverlay(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    channelName: String,
    options: List<LiveTrackOption>,
    onDismiss: () -> Unit,
    onPick: (String) -> Unit,
) {
    if (options.isEmpty()) return
    val selectedIndex = options.indexOfFirst { it.selected }.coerceAtLeast(0)
    val firstFocus = remember { FocusRequester() }
    // A lazy list only attaches the rows it places, so the selected row is scrolled into view
    // first and focused once it has been laid out. Requesting focus on a row further down than the
    // first screen used to fail silently and left focus behind the overlay.
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = selectedIndex)
    LaunchedEffect(Unit) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.any { it.index == selectedIndex } }
            .first { it }
        withFrameNanos { }
        runCatching { firstFocus.requestFocus() }
    }
    BackHandler { onDismiss() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.72f))
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown && event.key == Key.Back) {
                    onDismiss()
                    true
                } else {
                    false
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .width(420.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(LiveColors.PanelRaised)
                .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = liveAccent(),
                    modifier = Modifier.size(22.dp),
                )
                Column {
                    Text(title, style = LiveType.ChannelName.copy(color = LiveColors.Fg, fontSize = 18.sp))
                    Text(
                        channelName,
                        style = LiveType.SectionTag.copy(color = LiveColors.FgDim),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 320.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                itemsIndexed(options, key = { _, option -> option.key }) { index, option ->
                    TrackRow(
                        option = option,
                        modifier = if (index == selectedIndex) Modifier.focusRequester(firstFocus) else Modifier,
                        onPick = {
                            onPick(option.key)
                            onDismiss()
                        },
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun TrackRow(
    option: LiveTrackOption,
    onPick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var focused by remember { mutableStateOf(false) }
    // Focus is white with dark text, whatever the accent colour; the current choice carries a tick.
    val bg by animateColorAsState(if (focused) Color.White else LiveColors.PanelDeep, tween(140), label = "track-row-bg")
    val fg = if (focused) Color(0xFF0B0B0C) else LiveColors.Fg
    val scale = playerFocusScale(focused, 1.02f)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(RoundedCornerShape(10.dp))
            .background(bg)
            .border(1.dp, if (focused) Color.Transparent else LiveColors.Divider, RoundedCornerShape(10.dp))
            .onFocusChanged { focused = it.hasFocus }
            // One focus target per row: clickable is focusable itself and answers OK/Enter.
            .clickable(onClick = onPick)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(modifier = Modifier.size(20.dp), contentAlignment = Alignment.Center) {
            if (option.selected) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = fg,
                    modifier = Modifier.size(20.dp),
                )
            }
        }
        Text(
            text = option.label,
            style = LiveType.CellTitle.copy(
                color = fg,
                fontSize = 15.sp,
                fontWeight = if (option.selected) FontWeight.SemiBold else FontWeight.Normal,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
