package com.arflix.tv.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.Profile
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.AnimationConstants
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.util.settingsDataStore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

val AppTopBarHeight = 82.dp
val AppTopBarTopPadding = 0.dp
val AppTopBarContentTopInset = 98.dp
val MobileContentTopInset = 16.dp
val AppTopBarHorizontalPadding = 28.dp

// Uniform, fixed item dimensions for zero-delay mathematical alignment
private val TvChipWidth = 106.dp
private val TvChipHeight = 36.dp
private val TvChipSpacing = 4.dp

private val ALL_NAV_ITEMS = listOf(
    SidebarItem.SEARCH,
    SidebarItem.HOME,
    SidebarItem.TV,
    SidebarItem.WATCHLIST,
    SidebarItem.SETTINGS
)

private fun sidebarItemIcon(item: SidebarItem): ImageVector = when (item) {
    SidebarItem.SEARCH -> Icons.Default.Search
    SidebarItem.HOME -> Icons.Default.Home
    SidebarItem.TV -> Icons.Default.LiveTv
    SidebarItem.WATCHLIST -> Icons.Default.Bookmark
    SidebarItem.SETTINGS -> Icons.Default.Settings
}

private fun sidebarItemLabelRes(item: SidebarItem): Int = when (item) {
    SidebarItem.SEARCH -> R.string.search
    SidebarItem.HOME -> R.string.home
    SidebarItem.TV -> R.string.topbar_tv
    SidebarItem.WATCHLIST -> R.string.nav_library
    SidebarItem.SETTINGS -> R.string.settings
}

fun topBarMaxIndex(hasProfile: Boolean): Int {
    return if (hasProfile) ALL_NAV_ITEMS.size else ALL_NAV_ITEMS.size - 1
}

fun topBarSelectedIndex(selectedItem: SidebarItem, hasProfile: Boolean): Int {
    val base = ALL_NAV_ITEMS.indexOf(selectedItem)
    if (base < 0) return -1
    return if (hasProfile) base + 1 else base
}

fun topBarFocusedItem(focusedIndex: Int, hasProfile: Boolean): SidebarItem? {
    if (hasProfile && focusedIndex == 0) return null
    val itemIndex = if (hasProfile) focusedIndex - 1 else focusedIndex
    return ALL_NAV_ITEMS.getOrNull(itemIndex)
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
    val showProfile = profile != null
    val hasProfile = showProfile
    val currentTime = rememberTopBarTime(clockFormat)
    val density = LocalDensity.current

    val fastSpringFloat = remember {
        spring<Float>(dampingRatio = 0.85f, stiffness = 850f)
    }

    val barScale by animateFloatAsState(
        targetValue = if (isFocused) 1.05f else 1.0f,
        animationSpec = fastSpringFloat,
        label = "tv_topbar_zoom"
    )

    val activeNavIndex = if (hasProfile) focusedIndex - 1 else focusedIndex

    val targetHighlightIndex = if (isFocused) {
        if (hasProfile && focusedIndex == 0) -1 else activeNavIndex
    } else {
        ALL_NAV_ITEMS.indexOf(selectedItem)
    }

    val indicatorOffset = remember { Animatable(0f) }
    val indicatorAlpha = remember { Animatable(1f) }

    // Dp arithmetic fixed: multiply Dp by Int
    val targetXPx = remember(targetHighlightIndex, density) {
        if (targetHighlightIndex >= 0) {
            with(density) { ((TvChipWidth + TvChipSpacing) * targetHighlightIndex).toPx() }
        } else {
            0f
        }
    }

    LaunchedEffect(targetHighlightIndex, isFocused) {
        if (targetHighlightIndex >= 0) {
            indicatorAlpha.animateTo(1f, tween(100))
            indicatorOffset.animateTo(targetXPx, fastSpringFloat)
        } else {
            indicatorAlpha.animateTo(0f, tween(100))
        }
    }

    // Always centered container
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .graphicsLayer {
                    scaleX = barScale
                    scaleY = barScale
                }
                .shadow(
                    elevation = if (isFocused) 26.dp else 16.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.75f),
                    spotColor = Color(0xFF00F0D0).copy(alpha = if (isFocused) 0.35f else 0.12f)
                )
                .clip(CircleShape)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF222530).copy(alpha = if (isFocused) 0.65f else 0.48f),
                            Color(0xFF101217).copy(alpha = if (isFocused) 0.78f else 0.62f)
                        )
                    )
                )
                .border(
                    width = if (isFocused) 1.5.dp else 1.dp,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = if (isFocused) 0.45f else 0.22f),
                            Color.White.copy(alpha = 0.05f)
                        )
                    ),
                    shape = CircleShape
                )
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (showProfile) {
                    TopBarProfileAvatar(
                        profile = profile,
                        isFocused = isFocused && focusedIndex == 0
                    )
                }

                // Center Navigation Bar with Deterministic Sliding Capsule
                Box(
                    modifier = Modifier.wrapContentSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (targetHighlightIndex >= 0) {
                        Box(
                            modifier = Modifier
                                .offset { IntOffset(x = indicatorOffset.value.toInt(), y = 0) }
                                .width(TvChipWidth)
                                .height(TvChipHeight)
                                .graphicsLayer { alpha = indicatorAlpha.value }
                                .clip(CircleShape)
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(
                                            Color.White.copy(alpha = if (isFocused) 0.28f else 0.14f),
                                            Color.White.copy(alpha = 0.06f)
                                        )
                                    )
                                )
                                .border(
                                    1.dp,
                                    Brush.verticalGradient(
                                        listOf(
                                            Color.White.copy(alpha = 0.50f),
                                            Color.White.copy(alpha = 0.10f)
                                        )
                                    ),
                                    CircleShape
                                )
                        )
                    }

                    Row(
                        modifier = Modifier.wrapContentSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(TvChipSpacing)
                    ) {
                        ALL_NAV_ITEMS.forEachIndexed { index, item ->
                            val itemFocusIndex = if (hasProfile) index + 1 else index
                            val isItemFocused = isFocused && focusedIndex == itemFocusIndex
                            val isItemCurrentScreen = selectedItem == item
                            val showActiveState = isItemFocused || (!isFocused && isItemCurrentScreen)

                            TopBarNavChip(
                                item = item,
                                isFocused = isItemFocused,
                                isSelected = showActiveState,
                                hasBadge = item == SidebarItem.SETTINGS && hasUpdateBadge
                            )
                        }
                    }
                }

                Text(
                    text = currentTime,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.65f),
                    modifier = Modifier.padding(start = 4.dp, end = 6.dp)
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
    isSelected: Boolean,
    hasBadge: Boolean = false,
    modifier: Modifier = Modifier
) {
    val accent = resolveAccentColor(fallback = Color(0xFF00F0D0))

    val fastSpringFloat = remember {
        spring<Float>(dampingRatio = 0.85f, stiffness = 850f)
    }

    val zoomScale by animateFloatAsState(
        targetValue = when {
            isFocused -> 1.08f
            isSelected -> 1.02f
            else -> 1.0f
        },
        animationSpec = fastSpringFloat,
        label = "tv_chip_zoom"
    )

    val iconColor by animateColorAsState(
        targetValue = when {
            isFocused -> Color.White
            isSelected -> accent
            else -> Color.White.copy(alpha = 0.65f)
        },
        animationSpec = tween(120),
        label = "topbar_icon_color"
    )

    val textColor by animateColorAsState(
        targetValue = when {
            isFocused -> Color.White
            isSelected -> accent
            else -> Color.White.copy(alpha = 0.70f)
        },
        animationSpec = tween(120),
        label = "topbar_text_color"
    )

    val label = stringResource(sidebarItemLabelRes(item))
    val icon = sidebarItemIcon(item)

    Row(
        modifier = modifier
            .width(TvChipWidth)
            .height(TvChipHeight)
            .clip(CircleShape)
            .graphicsLayer {
                scaleX = zoomScale
                scaleY = zoomScale
            }
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconColor,
                modifier = Modifier.size(17.dp)
            )

            if (hasBadge) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 2.dp, y = (-2).dp)
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(com.arflix.tv.ui.theme.AccentRed)
                )
            }
        }

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = label,
            fontSize = 12.5.sp,
            fontWeight = if (isFocused) FontWeight.Bold else FontWeight.SemiBold,
            color = textColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun TopBarProfileAvatar(
    profile: Profile,
    isFocused: Boolean
) {
    val fastSpringFloat = remember {
        spring<Float>(dampingRatio = 0.85f, stiffness = 850f)
    }

    val containerColor by animateColorAsState(
        targetValue = if (isFocused) Color.White.copy(alpha = 0.28f) else Color.Transparent,
        animationSpec = tween(120),
        label = "topbar_profile_bg"
    )
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.18f else 1.0f,
        animationSpec = fastSpringFloat,
        label = "topbar_profile_scale"
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
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            ProfileAvatarVisual(
                profile = profile,
                letterFontSize = 12.sp,
                iconPadding = 3.dp
            )
        }
    }
}

@Composable
private fun rememberTopBarTime(clockFormat: String): String {
    val context = LocalContext.current
    var resolvedFormat by remember(clockFormat) { mutableStateOf(clockFormat) }
    var currentTime by remember(resolvedFormat) { mutableStateOf(topBarCurrentTime(resolvedFormat)) }

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