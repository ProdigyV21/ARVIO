package com.arflix.tv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.ArflixTypography
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R
import com.arflix.tv.util.settingsDataStore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

val AppTopBarHeight = 68.dp
val AppTopBarTopPadding = 0.dp
val AppTopBarContentTopInset = 82.dp
/** On mobile/tablet where the topbar is hidden, use a small status-bar-like inset instead. */
val MobileContentTopInset = 16.dp
val AppTopBarHorizontalPadding = 34.dp

// Navigation items that appear centered in the top bar.
// Settings is NOT in this list — it's rendered as a standalone gear icon on the right.
private val NAV_ITEMS = listOf(
    SidebarItem.SEARCH,
    SidebarItem.HOME,
    SidebarItem.DISCOVER,
    SidebarItem.WATCHLIST
)

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
    clockFormat: String = "24h",
    hasUpdateBadge: Boolean = false,
    modifier: Modifier = Modifier
) {
    // Majo Stream is currently a single-profile app. The profile remains an
    // internal data scope for watchlist/progress, but the top bar does not
    // expose profile switching or reserve focus space for an avatar.
    val hasProfile = false
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
                        Color.Black.copy(alpha = 0.58f),
                        ArvioSkin.colors.background.copy(alpha = 0.66f),
                        ArvioSkin.colors.surface.copy(alpha = 0.22f),
                        Color.Transparent
                    )
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTopBarHeight)
                .padding(start = AppTopBarHorizontalPadding, end = AppTopBarHorizontalPadding, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Settings gear icon (no text label)
                TopBarSettingsGear(
                    isFocused = settingsFocused,
                    isSelected = settingsSelected,
                    hasBadge = hasUpdateBadge
                )

                Text(
                    text = currentTime,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White.copy(alpha = 0.48f)
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color.Transparent,
                            ArvioSkin.colors.tealAccent.copy(alpha = 0.14f),
                            ArvioSkin.colors.accent.copy(alpha = 0.10f),
                            Color.Transparent
                        )
                    )
                )
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun TopBarNavChip(
    item: SidebarItem,
    isFocused: Boolean,
    isSelected: Boolean
) {
    val accent = resolveAccentColor(fallback = Color.White)

    val containerColor = when {
            isFocused -> ArvioSkin.colors.surfaceRaised.copy(alpha = 0.90f)
            isSelected -> accent.copy(alpha = 0.14f)
            else -> Color.Transparent
        }
    val iconColor = when {
            isFocused -> Color.White  // focused icon stays white (wins over selected)
            isSelected -> accent  // selected icon gets accent
            else -> Color.White.copy(alpha = 0.62f)
        }
    val textColor = when {
            isFocused -> Color.White  // focused text stays white (wins over selected)
            isSelected -> accent  // selected text gets accent
            else -> Color.White.copy(alpha = 0.68f)
        }
    val label = stringResource(item.labelRes)
    val chipShape = RoundedCornerShape(999.dp)

    Row(
        modifier = Modifier
            .clip(chipShape)
            .background(containerColor, chipShape)
            .border(
                width = if (isFocused || isSelected) 1.dp else 0.dp,
                color = when {
                    isFocused -> ArvioSkin.colors.focusOutline.copy(alpha = 0.62f)
                    isSelected -> accent.copy(alpha = 0.36f)
                    else -> Color.Transparent
                },
                shape = chipShape
            )
            .padding(horizontal = 13.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = label,
            tint = iconColor,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = label,
            fontSize = 12.sp,
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
    val accent = resolveAccentColor(fallback = Color.White)

    val iconColor = when {
            isFocused -> Color.White  // focused stays white (wins over selected)
            isSelected -> accent  // selected settings gear gets accent
            else -> Color.White.copy(alpha = 0.5f)
        }
    val containerColor = when {
            isFocused -> ArvioSkin.colors.surfaceRaised.copy(alpha = 0.90f)
            isSelected -> accent.copy(alpha = 0.14f)
            else -> Color.Transparent
        }

    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(containerColor)
            .border(
                width = if (isFocused || isSelected) 1.dp else 0.dp,
                color = when {
                    isFocused -> ArvioSkin.colors.focusOutline.copy(alpha = 0.62f)
                    isSelected -> accent.copy(alpha = 0.36f)
                    else -> Color.Transparent
                },
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Settings,
            contentDescription = stringResource(R.string.settings),
            tint = iconColor,
            modifier = Modifier.size(18.dp)
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
