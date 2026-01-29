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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
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
import com.arflix.tv.ui.skin.ArvioSkin

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun QuickActionMenu(
    isVisible: Boolean,
    isWatched: Boolean,
    canRemoveContinueWatching: Boolean,
    onMarkWatched: () -> Unit,
    onRemoveContinueWatching: () -> Unit,
    onDismiss: () -> Unit
) {
    var focusedIndex by remember { mutableIntStateOf(0) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            focusedIndex = 0
            focusRequester.requestFocus()
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + scaleIn(initialScale = 0.92f),
        exit = fadeOut() + scaleOut(targetScale = 0.92f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.65f))
                .focusRequester(focusRequester)
                .focusable()
                .onPreviewKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        when (event.key) {
                            Key.DirectionLeft -> {
                                if (focusedIndex > 0) focusedIndex--
                                true
                            }
                            Key.DirectionRight -> {
                                if (focusedIndex < 1) focusedIndex++
                                true
                            }
                            Key.DirectionUp, Key.DirectionDown -> true
                            Key.Enter, Key.DirectionCenter -> {
                                when (focusedIndex) {
                                    0 -> {
                                        onMarkWatched()
                                        onDismiss()
                                    }
                                    1 -> {
                                        if (canRemoveContinueWatching) {
                                            onRemoveContinueWatching()
                                        }
                                        onDismiss()
                                    }
                                }
                                true
                            }
                            Key.Back, Key.Escape -> {
                                onDismiss()
                                true
                            }
                            else -> false
                        }
                    } else {
                        false
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                QuickActionTile(
                    icon = if (isWatched) Icons.Default.Check else Icons.Default.Visibility,
                    label = if (isWatched) "Watched" else "Mark Watched",
                    isFocused = focusedIndex == 0,
                    isEnabled = true
                )
                QuickActionTile(
                    icon = Icons.Default.Close,
                    label = "Remove Continue Watching",
                    isFocused = focusedIndex == 1,
                    isEnabled = canRemoveContinueWatching
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun QuickActionTile(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isFocused: Boolean,
    isEnabled: Boolean
) {
    val shape = RoundedCornerShape(12.dp)
    val background = if (isFocused) {
        ArvioSkin.colors.surfaceRaised.copy(alpha = 0.9f)
    } else {
        ArvioSkin.colors.surface.copy(alpha = 0.85f)
    }
    val outline = if (isFocused) ArvioSkin.colors.focusOutline else Color.Transparent
    val contentAlpha = if (isEnabled) 1f else 0.45f

    Column(
        modifier = Modifier
            .width(220.dp)
            .height(120.dp)
            .background(background, shape)
            .border(width = 2.dp, color = outline, shape = shape)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = ArvioSkin.colors.textPrimary.copy(alpha = contentAlpha),
            modifier = Modifier.size(26.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = label,
            style = ArvioSkin.typography.caption,
            color = ArvioSkin.colors.textPrimary.copy(alpha = contentAlpha)
        )
    }
}
