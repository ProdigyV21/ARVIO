package com.arflix.tv.ui.components

import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.LiveTv
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.Profile
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
    TV(Icons.Outlined.LiveTv, "TV"),
    SETTINGS(Icons.Outlined.Settings, "Settings")
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun Sidebar(
    selectedItem: SidebarItem = SidebarItem.HOME,
    isSidebarFocused: Boolean = false,
    focusedIndex: Int = 1,
    profile: Profile? = null,
    onProfileClick: () -> Unit = {},
    onItemSelected: (SidebarItem) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val centerItems = listOf(SidebarItem.SEARCH, SidebarItem.HOME, SidebarItem.WATCHLIST, SidebarItem.TV)
    val bottomItem = SidebarItem.SETTINGS
    val hasProfile = profile != null
    // With profile: index 0 = profile, 1-4 = center items, 5 = settings. Without: 0-3 = center, 4 = settings.
    val centerFocusedIndex = if (hasProfile) focusedIndex - 1 else focusedIndex
    val settingsFocused = if (hasProfile) focusedIndex == 5 else focusedIndex == 4

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
            )
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile at top
            if (hasProfile) {
                Spacer(modifier = Modifier.height(7.dp))
                SidebarProfileAvatar(
                    profile = profile!!,
                    isFocused = isSidebarFocused && focusedIndex == 0
                )
                Spacer(modifier = Modifier.height(28.dp))
            }

            // Flexible space - pushes center group down
            Spacer(modifier = Modifier.weight(1f))

            // Center group: Search, Home, Watchlist, TV (vertically centered)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                centerItems.forEachIndexed { index, item ->
                    SidebarIcon(
                        item = item,
                        isSelected = item == selectedItem,
                        isFocused = isSidebarFocused && index == centerFocusedIndex,
                    )
                }
            }

            // Flexible space - pushes settings to bottom
            Spacer(modifier = Modifier.weight(1f))

            // Settings at bottom
            SidebarIcon(
                item = bottomItem,
                isSelected = bottomItem == selectedItem,
                isFocused = isSidebarFocused && settingsFocused,
            )
            Spacer(modifier = Modifier.height(7.dp))
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SidebarProfileAvatar(
    profile: Profile,
    isFocused: Boolean
) {
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.12f else 1f,
        animationSpec = tween(
            durationMillis = AnimationConstants.DURATION_FAST,
            easing = AnimationConstants.EaseOut
        ),
        label = "profile_scale"
    )
    val indicatorAlpha by animateFloatAsState(
        targetValue = if (isFocused) 1f else 0f,
        animationSpec = tween(
            durationMillis = AnimationConstants.DURATION_FAST,
            easing = AnimationConstants.EaseOut
        ),
        label = "profile_indicator"
    )
    Box(
        modifier = Modifier
            .width(42.dp)
            .height(34.dp),
        contentAlignment = Alignment.Center
    ) {
        if (indicatorAlpha > 0.01f) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .width(3.dp)
                    .height(22.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(ArvioSkin.colors.focusOutline.copy(alpha = indicatorAlpha))
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(30.dp)
                .clip(CircleShape)
                .background(Color(profile.avatarColor))
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = profile.name.firstOrNull()?.uppercase() ?: "?",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
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

    val chipBackground by animateColorAsState(
        targetValue = when {
            isFocused -> Color.White.copy(alpha = 0.16f)
            isSelected -> Color.White.copy(alpha = 0.06f)
            else -> Color.Transparent
        },
        animationSpec = tween(
            durationMillis = AnimationConstants.DURATION_FAST,
            easing = AnimationConstants.EaseOut
        ),
        label = "sidebar_chip_bg"
    )

    val indicatorAlpha by animateFloatAsState(
        targetValue = if (isFocused) 1f else 0f,
        animationSpec = tween(
            durationMillis = AnimationConstants.DURATION_FAST,
            easing = AnimationConstants.EaseOut
        ),
        label = "sidebar_indicator_alpha"
    )

    Box(
        modifier = Modifier
            .width(42.dp)
            .height(34.dp),
        contentAlignment = Alignment.Center
    ) {
        if (indicatorAlpha > 0.01f) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .width(3.dp)
                    .height(22.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(ArvioSkin.colors.focusOutline.copy(alpha = indicatorAlpha))
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(30.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(chipBackground),
            contentAlignment = Alignment.Center
        ) {
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
    }
}


