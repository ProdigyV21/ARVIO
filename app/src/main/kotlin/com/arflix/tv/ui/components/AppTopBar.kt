package com.arflix.tv.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.Profile
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.theme.AnimationConstants
import com.arflix.tv.ui.theme.ArflixTypography
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R
import com.arflix.tv.util.settingsDataStore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

val AppTopBarHeight = 82.dp
val AppTopBarTopPadding = 0.dp
val AppTopBarContentTopInset = 98.dp
/** On mobile/tablet where the topbar is hidden, use a small status-bar-like inset instead. */
val MobileContentTopInset = 16.dp
val AppTopBarHorizontalPadding = 28.dp

// Navigation items that appear CENTERED in the top bar (Search, Home, Watchlist, TV).
// Settings is NOT in this list — it's rendered as a standalone gear icon on the right.
private val NAV_ITEMS = SidebarItem.entries.filter { it != SidebarItem.SETTINGS }

fun topBarMaxIndex(hasProfile: Boolean): Int {
    // Profile (0 if shown) + nav items + settings gear (last index)
    val navCount = NAV_ITEMS.size
    return if (hasProfile) navCount + 1 else navCount // +1 for settings gear at the end
}

fun topBarSelectedIndex(selectedItem: SidebarItem, hasProfile: Boolean): Int {
    if (selectedItem == SidebarItem.SETTINGS) {
        // Settings is the last focusable item
        return topBarMaxIndex(hasProfile)
    }
    val base = NAV_ITEMS.indexOf(selectedItem)
    if (base < 0) return -1
    return if (hasProfile) base + 1 else base
}

fun topBarFocusedItem(focusedIndex: Int, hasProfile: Boolean): SidebarItem? {
    if (hasProfile && focusedIndex == 0) return null // profile avatar focused
    val itemIndex = if (hasProfile) focusedIndex - 1 else focusedIndex
    // If it's the settings gear (last index after nav items)
    if (itemIndex == NAV_ITEMS.size) return SidebarItem.SETTINGS
    return NAV_ITEMS.getOrNull(itemIndex)
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AppTopBar(
    selectedItem: SidebarItem,
    isFocused: Boolean,
    focusedIndex: Int,
    profile: Profile? = null,
    profileCount: Int = 1,
    clockFormat: String = "24h",
    syncStatus: com.arflix.tv.data.repository.CloudSyncStatus = com.arflix.tv.data.repository.CloudSyncStatus.NOT_SIGNED_IN,
    hasUpdateBadge: Boolean = false,
    modifier: Modifier = Modifier
) {
    // Always show the profile avatar when a profile exists — it's clickable
    // and opens the profile switcher. The name text was removed per the mockup
    // (avatar-only, no label).
    val showProfile = profile != null
    val hasProfile = showProfile
    val currentTime = rememberTopBarTime(clockFormat)
    val selectedIndex = remember(selectedItem, hasProfile) { topBarSelectedIndex(selectedItem, hasProfile) }
    // Settings gear is always the last focusable index
    val settingsIndex = topBarMaxIndex(hasProfile)
    val settingsFocused = isFocused && focusedIndex == settingsIndex
    val settingsSelected = selectedItem == SidebarItem.SETTINGS

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(AppTopBarContentTopInset)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.72f),
                        Color.Black.copy(alpha = 0.36f),
                        Color.Transparent
                    )
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTopBarHeight)
                .padding(start = AppTopBarHorizontalPadding, end = AppTopBarHorizontalPadding, top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ── LEFT: Profile avatar (only if multiple profiles) ──
            if (showProfile && profile != null) {
                TopBarProfileAvatar(
                    profile = profile,
                    isFocused = isFocused && focusedIndex == 0
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            // ── CENTER: Navigation chips (Search, Home, Watchlist, TV) ──
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    NAV_ITEMS.forEachIndexed { index, item ->
                        val itemFocusIndex = if (hasProfile) index + 1 else index
                        TopBarNavChip(
                            item = item,
                            isFocused = isFocused && focusedIndex == itemFocusIndex,
                            isSelected = selectedIndex == itemFocusIndex
                        )
                    }
                }
            }

            // ── RIGHT: Settings gear + clock ──
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Settings gear icon (no text label)
                TopBarSettingsGear(
                    isFocused = settingsFocused,
                    isSelected = settingsSelected,
                    hasBadge = hasUpdateBadge
                )

                Text(
                    text = currentTime,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White.copy(alpha = 0.55f)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun TopBarNavChip(
    item: SidebarItem,
    isFocused: Boolean,
    isSelected: Boolean
) {
    val containerColor by animateColorAsState(
        targetValue = when {
            isFocused -> Color.White.copy(alpha = 0.2f)
            isSelected -> Color.White.copy(alpha = 0.1f)
            else -> Color.Transparent
        },
        animationSpec = tween(AnimationConstants.DURATION_FAST),
        label = "topbar_chip_bg"
    )
    val iconColor by animateColorAsState(
        targetValue = when {
            isFocused -> Color.White
            isSelected -> Color.White.copy(alpha = 0.92f)
            else -> Color.White.copy(alpha = 0.62f)
        },
        animationSpec = tween(AnimationConstants.DURATION_FAST),
        label = "topbar_icon_color"
    )
    val textColor by animateColorAsState(
        targetValue = when {
            isFocused -> Color.White
            isSelected -> Color.White.copy(alpha = 0.92f)
            else -> Color.White.copy(alpha = 0.68f)
        },
        animationSpec = tween(AnimationConstants.DURATION_FAST),
        label = "topbar_text_color"
    )
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.05f else 1f,
        animationSpec = spring(dampingRatio = 0.75f, stiffness = 400f),
        label = "topbar_scale"
    )
    val label = if (item == SidebarItem.TV) {
        stringResource(R.string.topbar_tv)
    } else {
        stringResource(item.labelRes)
    }

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(containerColor)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .padding(horizontal = 14.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = label,
            tint = iconColor,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = if (isFocused || isSelected) FontWeight.SemiBold else FontWeight.Medium,
            color = textColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Settings gear icon — no text label, just the icon. Placed on the far right
 * of the top bar per the mockup. Receives focus/selection state for D-pad nav.
 */
@Composable
private fun TopBarSettingsGear(
    isFocused: Boolean,
    isSelected: Boolean,
    hasBadge: Boolean = false
) {
    val iconColor by animateColorAsState(
        targetValue = when {
            isFocused -> Color.White
            isSelected -> Color.White.copy(alpha = 0.92f)
            else -> Color.White.copy(alpha = 0.5f)
        },
        animationSpec = tween(AnimationConstants.DURATION_FAST),
        label = "topbar_settings_color"
    )
    val containerColor by animateColorAsState(
        targetValue = when {
            isFocused -> Color.White.copy(alpha = 0.2f)
            isSelected -> Color.White.copy(alpha = 0.1f)
            else -> Color.Transparent
        },
        animationSpec = tween(AnimationConstants.DURATION_FAST),
        label = "topbar_settings_bg"
    )
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.08f else 1f,
        animationSpec = spring(dampingRatio = 0.75f, stiffness = 400f),
        label = "topbar_settings_scale"
    )

    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(containerColor)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Settings,
            contentDescription = stringResource(R.string.settings),
            tint = iconColor,
            modifier = Modifier.size(20.dp)
        )

        // Update Badge
        if (hasBadge) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 2.dp, y = (-2).dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(com.arflix.tv.ui.theme.AccentRed)
            )
        }
    }
}

/**
 * Profile avatar only — no name text. Just the circular avatar with gradient/icon.
 * Shown only when multiple profiles exist.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun TopBarProfileAvatar(
    profile: Profile,
    isFocused: Boolean
) {
    val containerColor by animateColorAsState(
        targetValue = if (isFocused) Color.White.copy(alpha = 0.2f) else Color.Transparent,
        animationSpec = tween(AnimationConstants.DURATION_FAST),
        label = "topbar_profile_bg"
    )
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.08f else 1f,
        animationSpec = spring(dampingRatio = 0.75f, stiffness = 400f),
        label = "topbar_profile_scale"
    )

    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(containerColor)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            ProfileAvatarVisual(
                profile = profile,
                letterFontSize = 13.sp,
                iconPadding = 4.dp
            )
        }
    }
}

@Composable
private fun rememberTopBarTime(clockFormat: String): String {
    val context = LocalContext.current
    var resolvedFormat by remember(clockFormat) { mutableStateOf(clockFormat) }
    var currentTime by remember(resolvedFormat) { mutableStateOf(topBarCurrentTime(resolvedFormat)) }

    // AppTopBar is used on multiple screens that don't all have SettingsUiState.
    // Read the persisted clock format directly so the clock updates app-wide.
    LaunchedEffect(context, clockFormat) {
        runCatching {
            val prefs = context.settingsDataStore.data.first()
            val saved = prefs.asMap().entries
                .firstOrNull { (key, _) -> key.name.endsWith("_clock_format") }
                ?.value as? String
            resolvedFormat = saved ?: clockFormat
        }
    }

    LaunchedEffect(resolvedFormat) {
        while (true) {
            currentTime = topBarCurrentTime(resolvedFormat)
            val now = System.currentTimeMillis()
            val delayToNextMinute = 60_000L - (now % 60_000L)
            delay(delayToNextMinute.coerceIn(1_000L, 60_000L))
        }
    }
    return currentTime
}

private fun topBarCurrentTime(clockFormat: String): String {
    val pattern = when (clockFormat) {
        "12h" -> "h:mm a"
        else -> "HH:mm"
    }
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(Date())
}
