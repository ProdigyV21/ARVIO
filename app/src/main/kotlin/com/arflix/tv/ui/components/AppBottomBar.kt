package com.arflix.tv.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.selection.selectable
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.LayoutDirection
import androidx.annotation.StringRes
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    LANDSCAPE_RAIL,
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
    AppBottomBarMode.LANDSCAPE_RAIL
} else {
    AppBottomBarMode.STANDARD
}

internal fun appBottomBarSpec(mode: AppBottomBarMode): AppBottomBarSpec = when (mode) {
    AppBottomBarMode.LANDSCAPE_RAIL -> AppBottomBarSpec(
        itemHeightDp = 52,
        rowVerticalPaddingDp = 8,
        itemVerticalPaddingDp = 0,
        itemSpacingDp = 0,
        iconHorizontalPaddingDp = 0,
        iconVerticalPaddingDp = 0,
        iconSizeDp = 25,
        indicatorSizeDp = 4,
        labelFontSizeSp = 11,
    )
    AppBottomBarMode.STANDARD -> AppBottomBarSpec(
        itemHeightDp = 64,
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

internal const val LANDSCAPE_RAIL_WIDTH_DP = 64

internal data class AppBottomBarClearance(val scrollInsetDp: Float, val fullHeightDp: Float)

internal fun appBottomBarClearance(
    mode: AppBottomBarMode,
    showBottomBar: Boolean,
    screenHeightDp: Int,
    measuredHeightDp: Float,
    navigationInsetDp: Float,
): AppBottomBarClearance {
    if (!showBottomBar) return AppBottomBarClearance(0f, 0f)
    if (mode == AppBottomBarMode.LANDSCAPE_RAIL) {
        // The rail reserves horizontal space; only the system gesture area remains below content.
        return AppBottomBarClearance(navigationInsetDp, navigationInsetDp)
    }
    // Standard bar measurements already include navigationBarsPadding().
    val fullHeight = maxOf(measuredHeightDp, (appBottomBarSpec(mode).itemHeightDp ?: 52) + navigationInsetDp)
    return AppBottomBarClearance(fullHeight, fullHeight)
}

/** Only interactive navigation avoids a side cutout; full-width screens may draw beneath it. */
internal fun navigationRailCutoutInsetPx(
    systemLeftInsetPx: Int,
    cutoutLeftInsetPx: Int,
    systemRightInsetPx: Int,
    cutoutRightInsetPx: Int,
    layoutDirection: LayoutDirection,
): Int {
    val systemStart = if (layoutDirection == LayoutDirection.Ltr) systemLeftInsetPx else systemRightInsetPx
    val cutoutStart = if (layoutDirection == LayoutDirection.Ltr) cutoutLeftInsetPx else cutoutRightInsetPx
    return (cutoutStart - systemStart).coerceAtLeast(0)
}

internal fun mobileContentInsets(
    systemBars: WindowInsets,
    showBottomBar: Boolean = false,
    edgeToEdgeTop: Boolean = false,
): WindowInsets = systemBars.only(
    if (edgeToEdgeTop) WindowInsetsSides.Horizontal else WindowInsetsSides.Top + WindowInsetsSides.Horizontal
)

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
        route == Screen.News.route -> true
        route == Screen.Music.route -> true
        route == "settings" -> true
        else -> false
    }
}

data class BottomBarItem(
    @StringRes val labelRes: Int,
    val icon: ImageVector,
    val route: String,
    /** Line icon for the unselected tab; the filled [icon] marks the selected one. */
    val idleIcon: ImageVector = icon,
)

/**
 * Phone tabs. News and Settings moved behind the profile avatar ([MobileAccountButton]), so the
 * bar keeps five readable tabs, like the web app.
 */
val bottomBarItems = listOf(
    BottomBarItem(R.string.home, Icons.Default.Home, "home", Icons.Outlined.Home),
    BottomBarItem(R.string.search, Icons.Default.Search, "search", Icons.Outlined.Search),
    BottomBarItem(R.string.nav_library, Icons.Default.Bookmark, "watchlist", Icons.Outlined.BookmarkBorder),
    BottomBarItem(R.string.topbar_tv, Icons.Default.LiveTv, "tv", Icons.Outlined.LiveTv),
    BottomBarItem(R.string.nav_music, Icons.Default.LibraryMusic, "music", Icons.Outlined.LibraryMusic),
)

/** The landscape rail has the height for Settings, pinned below the tabs. */
internal val railSettingsItem = BottomBarItem(R.string.settings, Icons.Default.Settings, "settings")

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
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    onSwitchProfile: () -> Unit = {},
) {
    val mode = currentAppBottomBarMode()
    val spec = appBottomBarSpec(mode)
    val isRail = mode == AppBottomBarMode.LANDSCAPE_RAIL
    val accent = resolveAccentColor(fallback = Color.White)
    val barHeight = maxOf((spec.itemHeightDp ?: 64).toFloat(), 34f + 28f * androidx.compose.ui.platform.LocalDensity.current.fontScale).dp
    val surfaceShape = RectangleShape

    val items = bottomBarItems.filter {
        com.arflix.tv.util.isNavigationSectionVisible(it.route, NavigationVisibility.hiddenSections, KidsNavigation.hideLiveTv)
    }
    // Music keeps playing on other screens; Music itself has its own mini player.
    val song by com.arflix.tv.music.MusicNowPlaying.song.collectAsState()
    val onMusic = currentRoute?.substringBefore('?') == "music"
    val openMusicPlayer = {
        com.arflix.tv.music.MusicNowPlaying.requestOpenPlayer()
        if (!onMusic) onNavigate("music")
    }
    if (isRail) {
        // Solid black like the pages beside it, so the rail and the cutout strip read as one edge;
        // a hairline separates it from content.
        Column(
            modifier = modifier
                .background(Color.Black)
                .drawBehind {
                    val x = size.width - 1.dp.toPx()
                    drawLine(Color.White.copy(alpha = 0.07f), androidx.compose.ui.geometry.Offset(x, 0f),
                        androidx.compose.ui.geometry.Offset(x, size.height), 1.dp.toPx())
                }
                .width(LANDSCAPE_RAIL_WIDTH_DP.dp)
                .fillMaxHeight()
                .navigationBarsPadding()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Only the upper group scrolls on very short windows. Settings stays pinned below it.
            Column(
                modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (currentProfile != null) {
                    Box(
                        modifier = Modifier.size(48.dp)
                            .clickable(onClickLabel = stringResource(R.string.switch_profile), onClick = onSwitchProfile),
                        contentAlignment = Alignment.Center,
                    ) {
                        Box(Modifier.size(32.dp).clip(CircleShape)) {
                            ProfileAvatarVisual(profile = currentProfile, letterFontSize = 14.sp, iconPadding = 4.dp)
                        }
                    }
                }
                items.forEach { item ->
                    BottomBarItemButton(item, currentRoute, onNavigate, spec, accent, Modifier.height(48.dp), song = song)
                }
            }
            BottomBarItemButton(railSettingsItem, currentRoute, onNavigate, spec, accent, Modifier.height(48.dp))
        }
    } else {
        // Floating, rounded bar over a soft fade, so artwork stays visible beside it.
        Column(
            modifier = modifier.fillMaxWidth()
                .background(Brush.verticalGradient(0f to Color.Transparent, 0.45f to Color.Black.copy(alpha = 0.7f), 1f to Color.Black.copy(alpha = 0.92f)))
                .navigationBarsPadding()
                .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 8.dp)
        ) {
            song?.takeIf { !onMusic }?.let {
                MobileNowPlayingBar(it, accent, onOpen = openMusicPlayer)
                Spacer(Modifier.height(6.dp))
            }
            // Glassy dark capsule: a lit top edge, a deep soft shadow, and one indicator that
            // glides between tabs instead of each tab fading its own highlight.
            androidx.compose.foundation.layout.BoxWithConstraints(
                modifier = Modifier.align(Alignment.CenterHorizontally).widthIn(max = 480.dp).fillMaxWidth().height(barHeight)
                    .shadow(24.dp, FloatingBarShape, clip = false, ambientColor = Color.Black, spotColor = Color.Black)
                    .clip(FloatingBarShape)
                    .background(Brush.verticalGradient(listOf(Color(0xF2202024), Color(0xF5121215))))
                    .border(
                        1.dp,
                        Brush.verticalGradient(listOf(Color.White.copy(alpha = 0.16f), Color.White.copy(alpha = 0.04f))),
                        FloatingBarShape,
                    )
                    .padding(horizontal = 6.dp, vertical = 6.dp)
                    .testTag("floating_tab_bar"),
            ) {
                val slot = if (items.isEmpty()) maxWidth else maxWidth / items.size
                val selectedIndex = items.indexOfFirst { currentRoute?.substringBefore('?') == it.route }
                val indicatorX by androidx.compose.animation.core.animateDpAsState(
                    targetValue = slot * selectedIndex.coerceAtLeast(0),
                    animationSpec = androidx.compose.animation.core.spring(dampingRatio = 0.78f, stiffness = 420f),
                    label = "tab_indicator_x",
                )
                val indicatorAlpha by androidx.compose.animation.core.animateFloatAsState(
                    if (selectedIndex >= 0) 1f else 0f, tween(200), label = "tab_indicator_alpha")
                Box(
                    Modifier.offset(x = indicatorX).width(slot).fillMaxHeight()
                        .padding(horizontal = 4.dp)
                        .graphicsLayer { alpha = indicatorAlpha }
                        .clip(RoundedCornerShape(22.dp))
                        .background(Brush.verticalGradient(listOf(accent.copy(alpha = 0.18f), accent.copy(alpha = 0.10f))))
                        .border(1.dp, accent.copy(alpha = 0.10f), RoundedCornerShape(22.dp))
                )
                Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    items.forEach { item ->
                        BottomBarItemButton(item, currentRoute, onNavigate, spec, accent,
                            Modifier.weight(1f).fillMaxHeight(), ownIndicator = false, song = song)
                    }
                }
            }
        }
    }
}

private val FloatingBarShape = RoundedCornerShape(28.dp)

@Composable
private fun BottomBarItemButton(
    item: BottomBarItem,
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    spec: AppBottomBarSpec,
    accent: Color,
    modifier: Modifier,
    ownIndicator: Boolean = true,
    song: com.arflix.tv.music.MusicNowPlaying.Song? = null,
) {
    val isSelected = currentRoute?.substringBefore('?') == item.route
    val haptic = LocalHapticFeedback.current
    var isFocused by remember { mutableStateOf(false) }
    val label = stringResource(item.labelRes)
    val iconColor by animateColorAsState(
        targetValue = if (isSelected || isFocused) accent else Color.White.copy(alpha = 0.55f),
        animationSpec = tween(180),
        label = "navigation_icon_color"
    )
    // The selected icon springs up a touch, so a tab change is felt as well as seen.
    val iconScale by androidx.compose.animation.core.animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.92f,
        animationSpec = androidx.compose.animation.core.spring(
            dampingRatio = androidx.compose.animation.core.Spring.DampingRatioMediumBouncy,
            stiffness = androidx.compose.animation.core.Spring.StiffnessMediumLow,
        ),
        label = "navigation_icon_scale"
    )
    val indicatorColor by animateColorAsState(
        targetValue = if (isSelected && ownIndicator) accent.copy(alpha = 0.16f) else Color.Transparent,
        animationSpec = tween(200),
        label = "navigation_indicator"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .semantics { contentDescription = label }
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
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(2.dp)) {
            // Selected tab: a soft pill behind the icon (Material 3 style) instead of colour alone.
            Box(
                Modifier.widthIn(max = 52.dp).fillMaxWidth().height(28.dp).clip(CircleShape)
                    .background(indicatorColor),
                contentAlignment = Alignment.Center,
            ) {
                if (item.route == "music" && song != null) {
                    // Something is playing: the tab's icon bounces with it.
                    NowPlayingBars(iconColor, song.isPlaying, Modifier.size(18.dp))
                } else Icon(
                    imageVector = if (isSelected) item.icon else item.idleIcon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp).graphicsLayer { scaleX = iconScale; scaleY = iconScale }
                )
            }
            androidx.compose.material3.Text(label, color = iconColor, fontSize = spec.labelFontSizeSp.sp,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                lineHeight = 14.sp, maxLines = 1, softWrap = false,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(horizontal = 2.dp))
        }
    }
}
