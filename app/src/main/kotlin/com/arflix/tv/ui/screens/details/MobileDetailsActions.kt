package com.arflix.tv.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.R

@Composable
internal fun MobileDetailsActions(
    playLabel: String,
    hasTrailer: Boolean,
    isWatched: Boolean,
    isInWatchlist: Boolean,
    watchlistIcon: ImageVector,
    watchlistLabel: String,
    onPlay: () -> Unit,
    onSources: () -> Unit,
    onTrailer: () -> Unit,
    onToggleWatched: () -> Unit,
    onToggleWatchlist: () -> Unit,
    onAddToList: (() -> Unit)? = null,
) {
    val buttonHeight = maxOf(52f, 40f * LocalDensity.current.fontScale + 12f).dp
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(
            Modifier.widthIn(max = 560.dp).fillMaxWidth().testTag("mobile_details_actions"),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                PrimaryDetailsAction(
                    label = playLabel,
                    icon = Icons.Default.PlayCircleFilled,
                    primary = true,
                    onClick = onPlay,
                    modifier = Modifier.weight(1f).height(buttonHeight).testTag("details_play"),
                )
                PrimaryDetailsAction(
                    label = stringResource(R.string.sources),
                    icon = Icons.Outlined.Layers,
                    primary = false,
                    onClick = onSources,
                    modifier = Modifier.weight(1f).height(buttonHeight).testTag("details_sources"),
                )
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                SecondaryDetailsAction(
                    label = stringResource(R.string.trailer), icon = Icons.Outlined.Movie,
                    enabled = hasTrailer, onClick = onTrailer,
                    modifier = Modifier.weight(1f).testTag("details_trailer"),
                )
                SecondaryDetailsAction(
                    label = stringResource(if (isWatched) R.string.watched else R.string.details_btn_mark_watched),
                    icon = if (isWatched) Icons.Default.Check else Icons.Outlined.Visibility,
                    active = isWatched, onClick = onToggleWatched,
                    modifier = Modifier.weight(1f).testTag("details_watched"),
                )
                SecondaryDetailsAction(
                    label = watchlistLabel, icon = watchlistIcon,
                    active = isInWatchlist, onClick = onToggleWatchlist,
                    modifier = Modifier.weight(1f).testTag("details_watchlist"),
                )
                if (onAddToList != null) {
                    SecondaryDetailsAction(
                        label = stringResource(R.string.list_add_to), icon = Icons.AutoMirrored.Filled.PlaylistAdd,
                        onClick = onAddToList,
                        modifier = Modifier.weight(1f).testTag("details_add_to_list"),
                    )
                }
            }
        }
    }
}

@Composable
private fun PrimaryDetailsAction(
    label: String,
    icon: ImageVector,
    primary: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    val foreground = if (primary) Color(0xFF111214) else Color.White
    Row(
        modifier.clip(RoundedCornerShape(16.dp))
            .background(if (primary) Color(0xFFF5F5F7) else Color(0xFF1D1E21))
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, contentDescription = null, tint = foreground, modifier = Modifier.size(22.dp))
        Text(
            text = label, color = foreground, fontSize = 16.sp, lineHeight = 20.sp,
            fontWeight = if (primary) FontWeight.SemiBold else FontWeight.Medium,
            maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f, fill = false),
        )
        if (!primary) {
            Icon(Icons.Outlined.ExpandMore, contentDescription = null,
                tint = Color.White.copy(alpha = 0.65f), modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun SecondaryDetailsAction(
    label: String,
    icon: ImageVector,
    modifier: Modifier,
    active: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val foreground = Color.White.copy(alpha = if (!enabled) 0.35f else if (active) 1f else 0.75f)
    Column(
        modifier.heightIn(min = 56.dp).clip(RoundedCornerShape(8.dp))
            .semantics { selected = active }
            .clickable(enabled = enabled, role = Role.Button, onClick = onClick)
            .padding(horizontal = 4.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
    ) {
        Icon(icon, contentDescription = null, tint = foreground, modifier = Modifier.size(22.dp))
        Text(text = label, color = foreground, fontSize = 12.sp, lineHeight = 16.sp,
            minLines = 2, maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
    }
}
