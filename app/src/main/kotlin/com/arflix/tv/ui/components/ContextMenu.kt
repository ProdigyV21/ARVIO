package com.arflix.tv.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.focusable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

/**
 * Context menu action
 */
data class ContextAction(
    val id: String,
    val label: String,
    val icon: ImageVector,
    val color: Color = TextPrimary
)

/**
 * Predefined context actions
 */
object ContextActions {
    val play = ContextAction("play", "Play", Icons.Default.PlayArrow, Pink)
    val selectSource = ContextAction("sources", "Select Source", Icons.Default.Info, TextPrimary)
    val markWatched = ContextAction("mark_watched", "Mark as Watched", Icons.Default.Visibility, Color(0xFF22C55E))
    val markUnwatched = ContextAction("mark_unwatched", "Mark as Unwatched", Icons.Default.VisibilityOff, TextSecondary)
    val addWatchlist = ContextAction("add_watchlist", "Add to Watchlist", Icons.Default.BookmarkBorder, Pink)
    val removeWatchlist = ContextAction("remove_watchlist", "Remove from Watchlist", Icons.Default.Bookmark, TextSecondary)
    val viewDetails = ContextAction("view_details", "View Details", Icons.Default.Info, TextPrimary)
}

/**
 * Context menu popup for media items and episodes
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ContextMenu(
    isVisible: Boolean,
    title: String,
    subtitle: String? = null,
    actions: List<ContextAction>,
    onAction: (ContextAction) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    var focusedIndex by remember { mutableIntStateOf(0) }
    val focusRequester = remember { FocusRequester() }

    // Request focus when menu becomes visible
    LaunchedEffect(isVisible) {
        if (isVisible) {
            focusedIndex = 0 // Reset to first item
            focusRequester.requestFocus()
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + scaleIn(initialScale = 0.8f),
        exit = fadeOut() + scaleOut(targetScale = 0.8f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.85f))
                .focusRequester(focusRequester)
                .focusable()
                .onPreviewKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        when (event.key) {
                            Key.Back, Key.Escape -> {
                                onDismiss()
                                true
                            }
                            Key.DirectionUp -> {
                                if (focusedIndex > 0) focusedIndex--
                                true
                            }
                            Key.DirectionDown -> {
                                if (focusedIndex < actions.size - 1) focusedIndex++
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                actions.getOrNull(focusedIndex)?.let { action ->
                                    onAction(action)
                                }
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
                    .width(480.dp)
                    .background(BackgroundElevated, RoundedCornerShape(24.dp))
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = title,
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )
                
                // Subtitle
                if (subtitle != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = subtitle,
                        style = ArflixTypography.body,
                        color = TextSecondary
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Divider
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.1f))
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Actions
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    actions.forEachIndexed { index, action ->
                        ContextMenuItem(
                            action = action,
                            isFocused = index == focusedIndex
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Close hint
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Press Back to cancel",
                        style = ArflixTypography.caption,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ContextMenuItem(
    action: ContextAction,
    isFocused: Boolean
) {
    val bgColor = if (isFocused) Color.White.copy(alpha = 0.1f) else Color.Transparent
    val borderColor = if (isFocused) Pink else Color.Transparent
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor, RoundedCornerShape(12.dp))
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = action.icon,
            contentDescription = null,
            tint = if (isFocused) Pink else action.color,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(
            text = action.label,
            style = ArflixTypography.body,
            color = if (isFocused) TextPrimary else action.color
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        if (isFocused) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Pink, RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

/**
 * Episode context menu with standard actions
 */
@Composable
fun EpisodeContextMenu(
    isVisible: Boolean,
    episodeName: String,
    seasonEpisode: String,
    isWatched: Boolean,
    onPlay: () -> Unit,
    onSelectSource: () -> Unit,
    onToggleWatched: () -> Unit,
    onDismiss: () -> Unit
) {
    val actions = listOf(
        ContextActions.play,
        ContextActions.selectSource,
        if (isWatched) ContextActions.markUnwatched else ContextActions.markWatched
    )
    
    ContextMenu(
        isVisible = isVisible,
        title = episodeName,
        subtitle = seasonEpisode,
        actions = actions,
        onAction = { action ->
            when (action.id) {
                "play" -> onPlay()
                "sources" -> onSelectSource()
                "mark_watched", "mark_unwatched" -> onToggleWatched()
            }
            onDismiss()
        },
        onDismiss = onDismiss
    )
}

/**
 * Media item context menu with standard actions
 */
@Composable
fun MediaContextMenu(
    isVisible: Boolean,
    title: String,
    year: String? = null,
    isWatched: Boolean,
    isInWatchlist: Boolean,
    onPlay: () -> Unit,
    onSelectSource: () -> Unit,
    onToggleWatched: () -> Unit,
    onToggleWatchlist: () -> Unit,
    onViewDetails: () -> Unit,
    onDismiss: () -> Unit
) {
    val actions = listOf(
        ContextActions.play,
        ContextActions.selectSource,
        if (isWatched) ContextActions.markUnwatched else ContextActions.markWatched,
        if (isInWatchlist) ContextActions.removeWatchlist else ContextActions.addWatchlist,
        ContextActions.viewDetails
    )
    
    ContextMenu(
        isVisible = isVisible,
        title = title,
        subtitle = year,
        actions = actions,
        onAction = { action ->
            when (action.id) {
                "play" -> onPlay()
                "sources" -> onSelectSource()
                "mark_watched", "mark_unwatched" -> onToggleWatched()
                "add_watchlist", "remove_watchlist" -> onToggleWatchlist()
                "view_details" -> onViewDetails()
            }
            onDismiss()
        },
        onDismiss = onDismiss
    )
}
