package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvNowNext
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MobileChannelList(channels: List<EnrichedChannel>, guide: Map<String, IptvNowNext>,
    playingId: String?, favorites: Set<String>, onSelect: (EnrichedChannel) -> Unit,
    onToggleFavorite: (String) -> Unit, onLongPress: (EnrichedChannel) -> Unit,
    onVisibleRange: (Int, Int) -> Unit, modifier: Modifier = Modifier) {
    val scroll = rememberLazyListState()
    val report by rememberUpdatedState(onVisibleRange)
    LaunchedEffect(scroll) {
        snapshotFlow { scroll.layoutInfo.visibleItemsInfo.let { (it.firstOrNull()?.index ?: 0) to (it.lastOrNull()?.index ?: 0) } }
            .distinctUntilChanged().collect { (first, last) -> report(first, last) }
    }
    LazyColumn(modifier.testTag("mobile-channel-list"), state = scroll, contentPadding = PaddingValues(bottom = 24.dp)) {
        items(channels, key = { it.id }) { channel ->
            val playing = channel.id == playingId
            val foreground = if (playing) Color.Black else Color.White
            val secondary = if (playing) Color.DarkGray else Color.LightGray
            Column(Modifier.fillMaxWidth().background(if (playing) Color.White else Color.Transparent)
                .combinedClickable(onClick = { onSelect(channel) }, onLongClick = { onLongPress(channel) })
                .padding(horizontal = 16.dp, vertical = 12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ChannelLogo(channel, 38.dp, Modifier.size(42.dp))
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(channel.name, color = foreground, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                            maxLines = 2, overflow = TextOverflow.Ellipsis)
                        val slice = guide[channel.id]
                        Text("${stringResource(R.string.now)}  ${slice?.now?.title ?: stringResource(R.string.live_placeholder_no_guide_matched)}",
                            color = foreground, fontSize = 13.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        slice?.next?.title?.let { Text("${stringResource(R.string.next)}  $it", color = secondary,
                            fontSize = 12.sp, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                    }
                    IconButton(onClick = { onToggleFavorite(channel.id) }) {
                        Icon(if (channel.id in favorites) Icons.Default.Star else Icons.Default.StarBorder,
                            stringResource(R.string.live_quick_favourites), tint = foreground)
                    }
                }
            }
            HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
        }
    }
}
