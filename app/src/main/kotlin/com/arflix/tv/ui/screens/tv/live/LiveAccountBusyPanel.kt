package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R

/** The account answered full: what stays on screen until the user retries, plays anyway, zaps or goes back. */
internal data class LiveAccountBusy(val active: Int, val max: Int, val focusedIndex: Int = 0)

internal const val LIVE_BUSY_ACTION_COUNT = 2

/** Moves by a manual index like the channel menu, because the focused row underneath keeps Up and Down for zapping. */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun LiveAccountBusyPanel(
    busy: LiveAccountBusy,
    onRetry: () -> Unit,
    onPlayAnyway: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(12.dp)
    Column(
        modifier = modifier
            .widthIn(max = 420.dp)
            .background(LiveColors.PanelRaised, shape)
            .border(1.dp, LiveColors.FocusRing.copy(alpha = 0.7f), shape)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = stringResource(R.string.iptv_account_busy_title),
            style = LiveType.CatLabel.copy(color = LiveColors.Fg, fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
        )
        Text(
            text = stringResource(R.string.iptv_account_busy_live_detail, busy.active, busy.max),
            style = LiveType.CatLabel.copy(color = LiveColors.FgDim, fontSize = 12.sp),
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            LiveBusyAction(stringResource(R.string.retry), busy.focusedIndex == 0, onRetry)
            LiveBusyAction(stringResource(R.string.iptv_account_play_anyway), busy.focusedIndex == 1, onPlayAnyway)
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun LiveBusyAction(label: String, focused: Boolean, onClick: () -> Unit) {
    Text(
        text = label,
        style = LiveType.CatLabel.copy(
            color = if (focused) Color.Black else LiveColors.Fg,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (focused) LiveColors.FocusRing else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 8.dp),
    )
}
