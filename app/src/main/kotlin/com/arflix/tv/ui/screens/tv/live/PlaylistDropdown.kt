package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
internal fun PlaylistDropdown(providers: List<TvProviderFilter>, selectedId: String,
    onSelect: (String) -> Unit, requester: FocusRequester, onUp: () -> Unit, onDown: () -> Unit) {
    var open by remember { mutableStateOf(false) }
    var focused by remember { mutableStateOf(false) }
    val label = providers.firstOrNull { it.id == selectedId }?.label ?: "All playlists"
    Row(Modifier.fillMaxWidth().height(34.dp).focusRequester(requester)
        .onFocusChanged { focused = it.isFocused }
        .onPreviewKeyEvent { event ->
            if (event.type != KeyEventType.KeyDown) false else when (event.key) {
                Key.DirectionUp -> { onUp(); true }
                Key.DirectionDown -> { onDown(); true }
                else -> false
            }
        }
        .border(if (focused) 2.dp else 1.dp, if (focused) LiveColors.Fg else LiveColors.Divider,
            RoundedCornerShape(5.dp))
        .background(LiveColors.Panel, RoundedCornerShape(5.dp))
        .clickable { if (providers.isNotEmpty()) open = true }.padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = LiveColors.Fg, fontSize = 12.sp, maxLines = 1,
            overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
        Icon(Icons.Outlined.ExpandMore, null, tint = LiveColors.FgDim, modifier = Modifier.size(18.dp))
    }
    if (open) Dialog(onDismissRequest = { open = false; requester.requestFocus() }) {
        val initialFocus = remember { FocusRequester() }
        LaunchedEffect(Unit) { initialFocus.requestFocus() }
        LazyColumn(Modifier.width(320.dp).heightIn(max = 380.dp).background(LiveColors.Panel)
            .border(1.dp, LiveColors.DividerStrong).padding(10.dp)) {
            items(providers, key = { it.id }) { provider ->
                var itemFocused by remember { mutableStateOf(false) }
                Text(provider.label, color = LiveColors.Fg, fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                        .then(if (provider.id == selectedId || (providers.none { it.id == selectedId } && provider == providers.first())) Modifier.focusRequester(initialFocus) else Modifier)
                        .onFocusChanged { itemFocused = it.isFocused }
                        .border(2.dp, if (itemFocused) LiveColors.Fg else androidx.compose.ui.graphics.Color.Transparent)
                        .clickable { open = false; onSelect(provider.id); requester.requestFocus() }
                        .padding(14.dp))
            }
        }
    }
}
