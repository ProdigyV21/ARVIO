package com.arflix.tv.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.zIndex
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R

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
    val isMobile = LocalDeviceType.current.isTouchDevice()
    var focusedIndex by remember { mutableIntStateOf(0) }
    val focusRequester = remember { FocusRequester() }

    val menuItems = buildList {
        add(MenuItem(
            icon = Icons.Default.PlayArrow,
            labelRes = R.string.play,
            action = onPlay
        ))
        add(MenuItem(
            icon = Icons.Default.Info,
            labelRes = R.string.details,
            action = onViewDetails
        ))
        add(MenuItem(
            icon = if (isInWatchlist) Icons.Default.Remove else Icons.Default.Add,
            labelRes = if (isInWatchlist) R.string.remove_from_watchlist else R.string.add_to_watchlist,
            action = onToggleWatchlist
        ))
        add(MenuItem(
            icon = if (isWatched) Icons.Default.Visibility else Icons.Default.Check,
            labelRes = if (isWatched) R.string.unwatched else R.string.watched,
            action = onToggleWatched
        ))
        // Add "Remove from Continue Watching" only when applicable
        if (isContinueWatching && onRemoveFromContinueWatching != null) {
            add(MenuItem(
                icon = Icons.Default.Close,
                labelRes = R.string.delete,
                action = onRemoveFromContinueWatching
            ))
        }
    }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            focusedIndex = 0
            if (!isMobile) {
                runCatching { focusRequester.requestFocus() }
            }
        }
    }

    BackHandler(enabled = isVisible) {
        onDismiss()
    }

    if (!isMobile) {
        // --- TV layout: centered card with D-pad navigation ---
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn() + scaleIn(initialScale = 0.9f),
            exit = fadeOut() + scaleOut(targetScale = 0.9f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(50f)
                    .background(Color.Black.copy(alpha = 0.5f))
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
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 110.dp)
                        .width(320.dp)
                        .background(BackgroundCard, RoundedCornerShape(14.dp))
                        .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(14.dp))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = title,
                        style = ArflixTypography.sectionTitle,
                        color = TextPrimary,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Menu items
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        menuItems.forEachIndexed { index, item ->
                            ContextMenuItem(
                                icon = item.icon,
                                label = stringResource(item.labelRes),
                                isFocused = index == focusedIndex,
                                onClick = {
                                    item.action()
                                    onDismiss()
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Hint
                    Text(
                        text = stringResource(R.string.press_back_to_close),
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.5f)
                    )
                }
            }
        }
    } else {
        // --- Mobile layout: bottom-sheet style menu ---
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(50f)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(
                        indication = null,
                        interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                    ) { onDismiss() }
                    .onPreviewKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown && (event.key == Key.Back || event.key == Key.Escape)) {
                            onDismiss()
                            true
                        } else false
                    },
                contentAlignment = Alignment.BottomCenter
            ) {
                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                BackgroundElevated,
                                RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                            )
                            .clickable(
                                indication = null,
                                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                            ) { /* consume click so backdrop handler doesn't fire */ }
                            // The mobile bottom bar is drawn over the screen; without its height
                            // the last entries ("Watched", and "Remove" on Continue Watching) sit behind it.
                            .padding(top = 16.dp, bottom = 24.dp + LocalBottomBarHeight.current)
                    ) {
                        // Drag handle indicator
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .width(36.dp)
                                .height(4.dp)
                                .background(
                                    Color.White.copy(alpha = 0.2f),
                                    RoundedCornerShape(2.dp)
                                )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Title
                        Text(
                            text = title,
                            style = ArflixTypography.sectionTitle,
                            color = TextPrimary,
                            maxLines = 2,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Divider
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color.White.copy(alpha = 0.08f))
                        )

                        // Menu items
                        menuItems.forEachIndexed { index, item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 48.dp)
                                    .clickable {
                                        item.action()
                                        onDismiss()
                                    }
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = null,
                                    tint = TextSecondary,
                                    modifier = Modifier.size(22.dp)
                                )
                                Text(
                                    text = stringResource(item.labelRes),
                                    style = ArflixTypography.body,
                                    color = TextPrimary
                                )
                            }
                            // Subtle divider between items (not after last)
                            if (index < menuItems.size - 1) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .height(1.dp)
                                        .background(Color.White.copy(alpha = 0.05f))
                                )
                            }
                        }
                    }
                }
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
                tint = if (isFocused) Color.Black else TextSecondary,
                modifier = Modifier.size(22.dp)
            )
            Text(
                text = label,
                style = ArflixTypography.body,
                color = if (isFocused) Color.Black else TextPrimary
            )
        }
    }
}

private data class MenuItem(
    val icon: ImageVector,
    @StringRes val labelRes: Int,
    val action: () -> Unit
)
