package com.arflix.tv.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.theme.AnimationConstants
import com.arflix.tv.ui.theme.TextSecondary

/**
 * Premium navigation sidebar with smooth animations
 * Ultra slim icon-only bar with animated focus states
 */
enum class SidebarItem(val icon: ImageVector, val label: String) {
    SEARCH(Icons.Outlined.Search, "Search"),
    HOME(Icons.Outlined.Home, "Home"),
    WATCHLIST(Icons.Outlined.Bookmark, "Watchlist"),
    SETTINGS(Icons.Outlined.Settings, "Settings")
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun Sidebar(
    selectedItem: SidebarItem = SidebarItem.HOME,
    isSidebarFocused: Boolean = false,
    focusedIndex: Int = 1,
    onItemSelected: (SidebarItem) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val items = SidebarItem.entries

    // Sidebar: subtle transparent gradient so backdrop shows through
    Box(
        modifier = modifier
            .width(56.dp)
            .fillMaxHeight()
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color.Black.copy(alpha = 0.4f),
                        Color.Black.copy(alpha = 0.2f),
                        Color.Transparent
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // All icons centered vertically
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            items.forEachIndexed { index, item ->
                SidebarIcon(
                    item = item,
                    isSelected = item == selectedItem,
                    isFocused = isSidebarFocused && index == focusedIndex,
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SidebarIcon(
    item: SidebarItem,
    isSelected: Boolean,
    isFocused: Boolean,
) {
    // Animated icon color - dark grey normally, pure white when focused
    val iconColor by animateColorAsState(
        targetValue = when {
            isFocused -> Color.White  // Pure white when focused
            isSelected -> Color(0xFF666666)  // Dark grey when selected
            else -> Color(0xFF444444)  // Darker grey when unfocused
        },
        animationSpec = tween(
            durationMillis = AnimationConstants.DURATION_FAST,
            easing = AnimationConstants.EaseOut
        ),
        label = "icon_color"
    )

    // Slight scale when focused
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.12f else 1f,
        animationSpec = tween(
            durationMillis = AnimationConstants.DURATION_FAST,
            easing = AnimationConstants.EaseOut
        ),
        label = "icon_scale"
    )

    // Icon with slight scale on focus
    Icon(
        imageVector = item.icon,
        contentDescription = item.label,
        tint = iconColor,
        modifier = Modifier
            .size(20.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
    )
}


