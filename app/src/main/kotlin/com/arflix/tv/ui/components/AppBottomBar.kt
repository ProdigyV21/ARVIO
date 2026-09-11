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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.navigation.Screen
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType

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
        itemHeightDp = 56,
        rowVerticalPaddingDp = 6,
        itemVerticalPaddingDp = 2,
        itemSpacingDp = 2,
        iconHorizontalPaddingDp = 14,
        iconVerticalPaddingDp = 4,
        iconSizeDp = 24,
        indicatorSizeDp = 4,
        labelFontSizeSp = 11,
    )
}

internal fun shouldShowBottomBar(
    isMobile: Boolean,
    currentRoute: String?,
    isFullscreenRoute: Boolean
): Boolean {
    if (!isMobile || currentRoute == null || isFullscreenRoute) return false
    val isProfileOrLogin = currentRoute == Screen.ProfileSelection.route || currentRoute == Screen.Login.route
    return !isProfileOrLogin
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

@Composable
internal fun currentBottomBarSpec(): AppBottomBarSpec {
    val config = LocalConfiguration.current
    return appBottomBarSpec(appBottomBarMode(LocalDeviceType.current.isTouchDevice(),
        config.smallestScreenWidthDp, config.screenWidthDp, config.screenHeightDp))
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AppBottomBar(currentRoute: String?, onNavigate: (String) -> Unit, modifier: Modifier = Modifier) {
    val spec = currentBottomBarSpec()
    val accent = resolveAccentColor(fallback = Color.White)
    val background = appBackgroundDark()
    Column(modifier = modifier.fillMaxWidth()
        .background(Brush.verticalGradient(
            0f to Color.Transparent, 0.30f to background.copy(alpha = 0.65f),
            0.75f to background.copy(alpha = 0.92f), 1f to background.copy(alpha = 0.98f)))
        .navigationBarsPadding().padding(top = 20.dp)) {
        // Lower the whole touch row while keeping the fade and content clearance stable.
        Row(modifier = Modifier.fillMaxWidth().offset(y = 6.dp).padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            bottomBarItems.forEach { item ->
                val isSelected = currentRoute?.contains(item.route, ignoreCase = true) == true
                var isFocused by remember { mutableStateOf(false) }
                val label = stringResource(item.labelRes)
                val iconColor by animateColorAsState(
                    if (isSelected || isFocused) accent else Color.White.copy(alpha = 0.62f),
                    animationSpec = tween(200), label = "navigation_icon")
                val labelColor by animateColorAsState(
                    if (isSelected || isFocused) Color.White else Color.White.copy(alpha = 0.62f),
                    animationSpec = tween(200), label = "navigation_label")
                Column(modifier = Modifier.weight(1f).heightIn(min = (spec.itemHeightDp ?: 56).dp)
                    .clip(RoundedCornerShape(12.dp))
                    .then(if (isFocused) Modifier.border(1.dp, accent, RoundedCornerShape(12.dp)) else Modifier)
                    .onFocusChanged { isFocused = it.isFocused }
                    .selectable(selected = isSelected, role = Role.Tab, onClick = { onNavigate(item.route) })
                    .padding(vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)) {
                    Icon(imageVector = item.icon, contentDescription = null, tint = iconColor,
                        modifier = Modifier.size(spec.iconSizeDp.dp))
                    Text(text = label, style = ArflixTypography.caption.copy(fontSize = spec.labelFontSizeSp.sp,
                        fontWeight = FontWeight.Medium, letterSpacing = 0.sp), color = labelColor,
                        maxLines = 1, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis)
                }
            }
        }
    }
}
