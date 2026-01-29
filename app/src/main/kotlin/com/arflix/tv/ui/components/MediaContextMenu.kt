package com.arflix.tv.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

/**
 * Context menu for media cards on home screen
 * Triggered by long press or Menu button
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MediaContextMenu(
    isVisible: Boolean,
    title: String,
    isInWatchlist: Boolean,
    isWatched: Boolean,
    isContinueWatching: Boolean = false,
    onPlay: () -> Unit,
    onViewDetails: () -> Unit,
    onToggleWatchlist: () -> Unit,
    onToggleWatched: () -> Unit,
    onRemoveFromContinueWatching: (() -> Unit)? = null,
    onDismiss: () -> Unit
) {
    var focusedIndex by remember { mutableIntStateOf(0) }
    val focusRequester = remember { FocusRequester() }

    val menuItems = buildList {
        add(MenuItem(
            icon = Icons.Default.PlayArrow,
            label = "Play",
            action = onPlay
        ))
        add(MenuItem(
            icon = Icons.Default.Info,
            label = "View Details",
            action = onViewDetails
        ))
        add(MenuItem(
            icon = if (isInWatchlist) Icons.Default.Remove else Icons.Default.Add,
            label = if (isInWatchlist) "Remove from Watchlist" else "Add to Watchlist",
            action = onToggleWatchlist
        ))
        add(MenuItem(
            icon = if (isWatched) Icons.Default.Visibility else Icons.Default.Check,
            label = if (isWatched) "Mark as Unwatched" else "Mark as Watched",
            action = onToggleWatched
        ))
        // Add "Remove from Continue Watching" only when applicable
        if (isContinueWatching && onRemoveFromContinueWatching != null) {
            add(MenuItem(
                icon = Icons.Default.Close,
                label = "Remove from Continue Watching",
                action = onRemoveFromContinueWatching
            ))
        }
    }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            focusedIndex = 0
            focusRequester.requestFocus()
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + scaleIn(initialScale = 0.9f),
        exit = fadeOut() + scaleOut(targetScale = 0.9f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f))
                .focusRequester(focusRequester)
                .focusable()
                .onPreviewKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        when (event.key) {
                            Key.DirectionUp -> {
                                if (focusedIndex > 0) focusedIndex--
                                true
                            }
                            Key.DirectionDown -> {
                                if (focusedIndex < menuItems.size - 1) focusedIndex++
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                menuItems[focusedIndex].action()
                                onDismiss()
                                true
                            }
                            Key.Back, Key.Escape -> {
                                onDismiss()
                                true
                            }
                            else -> false
                        }
                    } else false
                },
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(350.dp)
                    .background(BackgroundCard, RoundedCornerShape(16.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = title,
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Menu items
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    menuItems.forEachIndexed { index, item ->
                        ContextMenuItem(
                            icon = item.icon,
                            label = item.label,
                            isFocused = index == focusedIndex,
                            onClick = {
                                item.action()
                                onDismiss()
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Hint
                Text(
                    text = "Press BACK to close",
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ContextMenuItem(
    icon: ImageVector,
    label: String,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isFocused) Pink else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
            .border(
                width = if (isFocused) 0.dp else 1.dp,
                color = if (isFocused) Color.Transparent else Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isFocused) Color.White else TextSecondary,
                modifier = Modifier.size(22.dp)
            )
            Text(
                text = label,
                style = ArflixTypography.body,
                color = if (isFocused) Color.White else TextPrimary
            )
        }
    }
}

private data class MenuItem(
    val icon: ImageVector,
    val label: String,
    val action: () -> Unit
)
