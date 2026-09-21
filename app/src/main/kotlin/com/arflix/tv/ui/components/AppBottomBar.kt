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
import androidx.compose.material.icons.filled.VideoLibrary
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.ripple.rememberRipple
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

internal enum class AppBottomBarMode {
    STANDARD,
    LANDSCAPE_COMPACT,
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
    smallestScreenWidthDp < 600 &&
    screenWidthDp > screenHeightDp
) {
    AppBottomBarMode.LANDSCAPE_COMPACT
} else {
    AppBottomBarMode.STANDARD
}

internal fun appBottomBarSpec(mode: AppBottomBarMode): AppBottomBarSpec = when (mode) {
    AppBottomBarMode.LANDSCAPE_COMPACT -> AppBottomBarSpec(
        itemHeightDp = 48,
        rowVerticalPaddingDp = 2,
        itemVerticalPaddingDp = 0,
        itemSpacingDp = 1,
        iconHorizontalPaddingDp = 10,
        iconVerticalPaddingDp = 2,
        iconSizeDp = 20,
        indicatorSizeDp = 3,
        labelFontSizeSp = 10,
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
        route == "vod" -> true
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
    BottomBarItem(R.string.nav_vod, Icons.Default.VideoLibrary, "vod"),
    BottomBarItem(R.string.settings, Icons.Default.Settings, "settings")
)

/** Bottom clearance applied inside scrolling content rather than to its viewport. */
val LocalBottomBarInset = staticCompositionLocalOf { 0.dp }

/** Full height of the bottom bar including system navigation bar insets when visible. */
val LocalBottomBarHeight = staticCompositionLocalOf { 0.dp }

@Composable
internal fun currentBottomBarSpec(): AppBottomBarSpec {
    val config = LocalConfiguration.current
    return appBottomBarSpec(appBottomBarMode(LocalDeviceType.current.isTouchDevice(),
        config.smallestScreenWidthDp, config.screenWidthDp, config.screenHeightDp))
}

@Composable
fun AppBottomBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    hazeState: HazeState? = null,
) {
    val spec = currentBottomBarSpec()
    val accent = resolveAccentColor(fallback = Color.White)
    val barHeight = (spec.itemHeightDp ?: 52).dp

    val baseModifier = if (hazeState != null) {
        modifier.hazeChild(
            state = hazeState,
            shape = RectangleShape,
            style = HazeStyle(
                tint = Color(0xCC000000), // 80% black tint
                blurRadius = 24.dp,
                noiseFactor = 0.04f
            )
        )
    } else {
        modifier.background(Color(0xCC000000))
    }

    Column(
        modifier = baseModifier
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
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            bottomBarItems.forEach { item ->
                val isSelected = currentRoute?.contains(item.route, ignoreCase = true) == true
                var isFocused by remember { mutableStateOf(false) }
                val label = stringResource(item.labelRes)
                val iconColor by animateColorAsState(
                    targetValue = if (isSelected || isFocused) accent else Color.White.copy(alpha = 0.55f),
                    animationSpec = tween(180),
                    label = "navigation_icon_color"
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(CircleShape)
                        .selectable(
                            selected = isSelected,
                            role = Role.Tab,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false, radius = 24.dp),
                            onClick = { onNavigate(item.route) }
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
        }
    }
}
