package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.arflix.tv.R
import com.arflix.tv.util.LocalDeviceType

/** A separate focus window keeps the fullscreen player's key handlers behind the selector. */
@Composable
internal fun FullscreenSourcesDialog(
    channel: EnrichedChannel,
    variants: List<EnrichedChannel>,
    loading: Boolean,
    failed: Boolean,
    onDismiss: () -> Unit,
    onPick: (EnrichedChannel) -> Unit,
) {
    val touchDevice = LocalDeviceType.current.isTouchDevice()
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        val hasAlternatives = !loading && !failed && variants.size > 1
        val targetKey = if (hasAlternatives) "source:${variants.first().id}" else "cancel"
        val firstFocus = remember(targetKey) { FocusRequester() }
        var targetPlaced by remember(targetKey) { mutableStateOf(false) }
        LaunchedEffect(firstFocus, targetPlaced, touchDevice) {
            if (!touchDevice && targetPlaced) {
                withFrameNanos { }
                // The dialog may close or replace its lazy items before this frame.
                runCatching { firstFocus.requestFocus() }
            }
        }
        val initialFocus = Modifier.focusRequester(firstFocus)
            .onGloballyPositioned { if (it.isAttached) targetPlaced = true }
        Column(
            modifier = Modifier.widthIn(max = 560.dp).fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(LiveColors.PanelRaised)
                .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(16.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Icon(Icons.Filled.SwapHoriz, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                Column {
                    Text(
                        stringResource(R.string.live_label_choose_source),
                        style = LiveType.ChannelName.copy(color = LiveColors.Fg, fontSize = 18.sp, lineHeight = 22.sp),
                    )
                    Text(channel.name, style = LiveType.SectionTag.copy(color = LiveColors.FgDim, fontSize = 12.sp, lineHeight = 15.sp))
                }
            }
            when {
                loading -> Box(Modifier.fillMaxWidth().padding(vertical = 16.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 3.dp)
                }
                failed -> Text(stringResource(R.string.live_sources_failed), color = Color.White)
                !hasAlternatives -> Text(stringResource(R.string.live_sources_empty), color = Color.White)
                else -> LazyColumn(
                    modifier = Modifier.fillMaxWidth().heightIn(max = 320.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    itemsIndexed(variants, key = { _, item -> item.id }) { index, variant ->
                        PlayerListRow(
                            label = variant.name,
                            detail = listOf(variant.quality.takeIf { it != Quality.UNKNOWN }?.label, variant.lang.takeIf { it.isNotBlank() })
                                .filterNotNull().joinToString("  ·  "),
                            selected = variant.id == channel.id,
                            onClick = { onPick(variant) },
                            modifier = if (index == 0) initialFocus else Modifier,
                        )
                    }
                }
            }
            PlayerChipButton(
                icon = Icons.Filled.Close,
                label = stringResource(android.R.string.cancel),
                onClick = onDismiss,
                modifier = if (!hasAlternatives) initialFocus else Modifier,
            )
        }
    }
}
