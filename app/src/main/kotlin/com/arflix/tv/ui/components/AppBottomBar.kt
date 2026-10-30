package com.arflix.tv.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.selection.selectable
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Brush
import androidx.compose.runtime.staticCompositionLocalOf
import com.arflix.tv.ui.skin.resolveAccentColor
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.annotation.StringRes
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ripple
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeChild
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.R
import com.arflix.tv.navigation.Screen
import androidx.compose.material3.Icon
import kotlin.math.roundToInt

internal enum class AppBottomBarMode {
    STANDARD,
    LANDSCAPE_FLOATING_DOCK,
}

internal data class AppBottomBarSpec(
    val itemHeightDp: Int?,
    val rowVerticalPaddingDp: Int,
    val itemVerticalPaddingDp: Int,
    val itemSpacingDp: Int,
    val iconHorizontalPaddingDp: Int,
    val iconVerticalPaddingDp: Int,
    val iconSizeDp: Int,
    val indicatorSizeDp: Int,
    val labelFontSizeSp: Int,
)

internal fun appBottomBarMode(
    isTouchDevice: Boolean,
    smallestScreenWidthDp: Int,
    screenWidthDp: Int,
    screenHeightDp: Int,
): AppBottomBarMode = if (
    isTouchDevice &&
    screenWidthDp > screenHeightDp
) {
    AppBottomBarMode.LANDSCAPE_FLOATING_DOCK
} else {
    AppBottomBarMode.STANDARD
}

internal fun appBottomBarSpec(mode: AppBottomBarMode): AppBottomBarSpec = when (mode) {
    AppBottomBarMode.LANDSCAPE_FLOATING_DOCK -> AppBottomBarSpec(
        itemHeightDp = 52,
        rowVerticalPaddingDp = 8,
        itemVerticalPaddingDp = 0,
        itemSpacingDp = 0,
        iconHorizontalPaddingDp = 0,
        iconVerticalPaddingDp = 0,
        iconSizeDp = 32,
        indicatorSizeDp = 4,
        labelFontSizeSp = 11,
    )
    AppBottomBarMode.STANDARD -> AppBottomBarSpec(
        itemHeightDp = 52,
        rowVerticalPaddingDp = 0,
        itemVerticalPaddingDp = 0,
        itemSpacingDp = 0,
        iconHorizontalPaddingDp = 0,
        iconVerticalPaddingDp = 0,
        iconSizeDp = 25,
        indicatorSizeDp = 4,
        labelFontSizeSp = 11,
    )
}

/** Match the reference's roughly one-third screen width, keeping small-screen tabs tappable. */
internal fun landscapeDockWidthDp(screenWidthDp: Int): Int {
    val availableWidthDp = (screenWidthDp - 32).coerceAtLeast(0)
    return (screenWidthDp * 0.34f).roundToInt()
        .coerceAtLeast(280)
        .coerceAtMost(availableWidthDp)
}

/** Keep the dock close to the reference's screen-height proportion while preserving tap space. */
internal fun landscapeDockHeightDp(screenHeightDp: Int): Int =
    (screenHeightDp * 0.11f).roundToInt().coerceIn(64, 112)

internal const val LANDSCAPE_DOCK_BOTTOM_GAP_DP = 16

internal data class AppBottomBarClearance(val scrollInsetDp: Float, val fullHeightDp: Float)

internal fun appBottomBarClearance(
    mode: AppBottomBarMode,
    showBottomBar: Boolean,
    screenHeightDp: Int,
    measuredHeightDp: Float,
    navigationInsetDp: Float,
): AppBottomBarClearance {
    if (!showBottomBar) return AppBottomBarClearance(0f, 0f)
    if (mode == AppBottomBarMode.LANDSCAPE_FLOATING_DOCK) {
        val dockHeight = maxOf(measuredHeightDp, landscapeDockHeightDp(screenHeightDp).toFloat())
        val occupiedHeight = dockHeight + LANDSCAPE_DOCK_BOTTOM_GAP_DP + navigationInsetDp
        // The persistent dock never scrolls away, so the last row must scroll above it.
        return AppBottomBarClearance(occupiedHeight, occupiedHeight)
    }
    // Standard bar measurements already include navigationBarsPadding().
    val fullHeight = maxOf(measuredHeightDp, (appBottomBarSpec(mode).itemHeightDp ?: 52) + navigationInsetDp)
    return AppBottomBarClearance(navigationInsetDp, fullHeight)
}

internal fun mobileContentInsets(systemBars: WindowInsets, showBottomBar: Boolean = false): WindowInsets =
    systemBars.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal)

internal fun shouldShowBottomBar(
    isMobile: Boolean,
    currentRoute: String?,
    isFullscreenRoute: Boolean,
    isSettingsSubPage: Boolean = false,
    isTvSubScreen: Boolean = false,
): Boolean {
    if (!isMobile || currentRoute == null || isFullscreenRoute) return false
    val route = currentRoute.substringBefore('?')
    if (route == "settings" && isSettingsSubPage || route == "tv" && isTvSubScreen) return false

    // Only main screens show the bottom navigation bar; subscreens (Details, Collection, CategoryViewAll, sub-settings, TV group guide, etc.) do not
    return when {
        route == Screen.Home.route -> true
        route == Screen.Search.route -> true
        route == Screen.Watchlist.route -> true
        route == "tv" -> true
        route == "settings" -> true
        else -> false
    }
}

data class BottomBarItem(
    @StringRes val labelRes: Int,
    val icon: ImageVector,
    val route: String
)

val bottomBarItems = listOf(
    BottomBarItem(R.string.home, Icons.Default.Home, "home"),
    BottomBarItem(R.string.search, Icons.Default.Search, "search"),
    BottomBarItem(R.string.nav_library, Icons.Default.Bookmark, "watchlist"),
    BottomBarItem(R.string.topbar_tv, Icons.Default.LiveTv, "tv"),
    BottomBarItem(R.string.settings, Icons.Default.Settings, "settings")
)

/** Bottom clearance applied inside scrolling content rather than to its viewport. */
val LocalBottomBarInset = staticCompositionLocalOf { 0.dp }

/** Full height of the bottom bar including system navigation bar insets when visible. */
val LocalBottomBarHeight = staticCompositionLocalOf { 0.dp }

@Composable
internal fun currentAppBottomBarMode(): AppBottomBarMode {
    val config = LocalConfiguration.current
    return appBottomBarMode(LocalDeviceType.current.isTouchDevice(),
        config.smallestScreenWidthDp, config.screenWidthDp, config.screenHeightDp)
}

@Composable
internal fun currentBottomBarSpec(): AppBottomBarSpec =
    appBottomBarSpec(currentAppBottomBarMode())

@Composable
fun AppBottomBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    hazeState: HazeState? = null,
) {
    val mode = currentAppBottomBarMode()
    val spec = appBottomBarSpec(mode)
    val isFloatingDock = mode == AppBottomBarMode.LANDSCAPE_FLOATING_DOCK
    val accent = resolveAccentColor(fallback = Color.White)
    val barHeight = (spec.itemHeightDp ?: 52).dp
    val configuration = LocalConfiguration.current
    val dockWidth = landscapeDockWidthDp(configuration.screenWidthDp).dp
    val dockHeight = landscapeDockHeightDp(configuration.screenHeightDp).dp
    val surfaceShape = if (isFloatingDock) RoundedCornerShape(18.dp) else RectangleShape

    val baseModifier = if (hazeState != null) {
        modifier.hazeChild(
            state = hazeState,
            shape = surfaceShape,
            style = HazeStyle(
                tint = Color(0xCC000000), // 80% black tint
                blurRadius = 24.dp,
                noiseFactor = 0.04f
            )
        )
    } else {
        modifier.background(Color(0xCC000000))
    }

    val haptic = LocalHapticFeedback.current

    Column(
        modifier = baseModifier
            .then(
                if (isFloatingDock) {
                    Modifier
                        .width(dockWidth)
                        .clip(surfaceShape)
                        .border(0.5.dp, Color.White.copy(alpha = 0.12f), surfaceShape)
                } else {
                    Modifier
                        .fillMaxWidth()
                        .drawBehind {
                // X-style subtle hairline top border
                            val strokeWidth = 0.5.dp.toPx()
                            drawLine(
                                color = Color.White.copy(alpha = 0.12f),
                                start = Offset(0f, strokeWidth / 2),
                                end = Offset(size.width, strokeWidth / 2),
                                strokeWidth = strokeWidth
                            )
                        }
                        .navigationBarsPadding()
                }
            )
    ) {
        val items = bottomBarItems.filter { it.route != "tv" || !KidsNavigation.hideLiveTv }
        if (isFloatingDock) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dockHeight)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items.forEach { item ->
                    BottomBarItemButton(
                        item,
                        currentRoute,
                        onNavigate,
                        spec,
                        accent,
                        Modifier.weight(1f).fillMaxHeight()
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(barHeight),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { item ->
                    BottomBarItemButton(item, currentRoute, onNavigate, spec, accent, Modifier.weight(1f).fillMaxHeight())
                }
            }
        }
    }
}

@Composable
private fun BottomBarItemButton(
    item: BottomBarItem,
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    spec: AppBottomBarSpec,
    accent: Color,
    modifier: Modifier,
) {
    val isSelected = currentRoute?.contains(item.route, ignoreCase = true) == true
    val haptic = LocalHapticFeedback.current
    var isFocused by remember { mutableStateOf(false) }
    val label = stringResource(item.labelRes)
    val iconColor by animateColorAsState(
        targetValue = if (isSelected || isFocused) accent else Color.White.copy(alpha = 0.55f),
        animationSpec = tween(180),
        label = "navigation_icon_color"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .selectable(
                selected = isSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false, radius = 24.dp),
                onClick = {
                    if (!isSelected) {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    }
                    onNavigate(item.route)
                }
            )
            .onFocusChanged { isFocused = it.isFocused }
            .then(if (isFocused) Modifier.border(1.dp, accent, CircleShape) else Modifier),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = label,
            tint = iconColor,
            modifier = Modifier.size(spec.iconSizeDp.dp)
        )
    }
}
