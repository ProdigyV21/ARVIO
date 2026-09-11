package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R

/** One row of the channel long-press menu. */
internal data class ChannelMenuAction(
    val labelRes: Int,
    val icon: ImageVector,
    val onClick: () -> Unit,
)

/** Which channel the menu is open for, and where the selector sits inside it. */
internal data class ChannelMenuState(
    val channelId: String,
    val channelName: String,
    val isFavorite: Boolean,
    val hasVariants: Boolean,
    val focusedIndex: Int = 0,
)

/**
 * Builds the actions for a channel long-press.
 *
 * Move up/down are offered only for favourites: the order they change is the favourites
 * list, which is what the Live TV favourites category and the home "Favorite TV" row are
 * both ordered by. Offering them on a non-favourite would silently reorder a list the
 * channel is not in.
 */
internal fun buildChannelMenuActions(
    isFavorite: Boolean,
    hasVariants: Boolean,
    onToggleFavorite: () -> Unit,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    onOpenVariants: () -> Unit,
): List<ChannelMenuAction> = buildList {
    if (isFavorite) {
        add(ChannelMenuAction(R.string.live_menu_favorite_move_up, Icons.Filled.KeyboardArrowUp, onMoveUp))
        add(ChannelMenuAction(R.string.live_menu_favorite_move_down, Icons.Filled.KeyboardArrowDown, onMoveDown))
        add(ChannelMenuAction(R.string.live_menu_favorite_remove, Icons.Filled.StarBorder, onToggleFavorite))
    } else {
        add(ChannelMenuAction(R.string.live_menu_favorite_add, Icons.Filled.Star, onToggleFavorite))
    }
    if (hasVariants) {
        add(ChannelMenuAction(R.string.live_menu_channel_variants, Icons.Filled.Tune, onOpenVariants))
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun ChannelContextMenu(
    state: ChannelMenuState,
    actions: List<ChannelMenuAction>,
    onDismiss: () -> Unit,
    onFocusedIndexChange: (Int) -> Unit,
    onAction: (Int) -> Unit,
) {
    if (actions.isEmpty()) return

    Popup(
        alignment = Alignment.Center,
        onDismissRequest = onDismiss,
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        ),
    ) {
        Column(
            modifier = Modifier
                .width(280.dp)
                .background(LiveColors.PanelRaised, RoundedCornerShape(12.dp))
                .border(1.dp, LiveColors.FocusRing.copy(alpha = 0.7f), RoundedCornerShape(12.dp))
                .padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            Text(
                text = state.channelName,
                style = LiveType.CatLabel.copy(
                    color = LiveColors.FgDim,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            )
            actions.forEachIndexed { index, action ->
                ChannelMenuItem(
                    action = action,
                    focused = index == state.focusedIndex,
                    onClick = {
                        onFocusedIndexChange(index)
                        onAction(index)
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ChannelMenuItem(
    action: ChannelMenuAction,
    focused: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (focused) LiveColors.FocusRing else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Icon(
            imageVector = action.icon,
            contentDescription = null,
            tint = if (focused) Color.Black else LiveColors.FgDim,
            modifier = Modifier.size(16.dp),
        )
        Text(
            text = stringResource(action.labelRes),
            style = LiveType.CatLabel.copy(
                color = if (focused) Color.Black else LiveColors.Fg,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
