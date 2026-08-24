package com.arflix.tv.ui.components

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
        labelFontSizeSp = 8,
    )
    AppBottomBarMode.STANDARD -> AppBottomBarSpec(
        itemHeightDp = null,
        rowVerticalPaddingDp = 6,
        itemVerticalPaddingDp = 2,
        itemSpacingDp = 2,
        iconHorizontalPaddingDp = 14,
        iconVerticalPaddingDp = 4,
        iconSizeDp = 24,
        indicatorSizeDp = 4,
        labelFontSizeSp = 10,
    )
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

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AppBottomBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val mode = appBottomBarMode(
        isTouchDevice = LocalDeviceType.current.isTouchDevice(),
        smallestScreenWidthDp = configuration.smallestScreenWidthDp,
        screenWidthDp = configuration.screenWidthDp,
        screenHeightDp = configuration.screenHeightDp,
    )
    val spec = appBottomBarSpec(mode)

    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White.copy(alpha = 0.08f))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(appBackgroundDark().copy(alpha = 0.95f))
                .padding(horizontal = 8.dp, vertical = spec.rowVerticalPaddingDp.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            bottomBarItems.forEach { item ->
                val isSelected = currentRoute?.contains(item.route, ignoreCase = true) == true
                var isFocused by remember { mutableStateOf(false) }
                val label = stringResource(item.labelRes)

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .then(
                            spec.itemHeightDp?.let { heightDp -> Modifier.heightIn(min = heightDp.dp) }
                                ?: Modifier
                        )
                        .clip(RoundedCornerShape(8.dp))
                        .then(
                            if (isFocused) Modifier.border(2.dp, Color.White.copy(alpha = 0.7f), RoundedCornerShape(8.dp))
                            else Modifier
                        )
                        .background(if (isFocused) Color.White.copy(alpha = 0.1f) else Color.Transparent)
                        .focusable()
                        .onFocusChanged { isFocused = it.isFocused }
                        .onKeyEvent { event ->
                            if (event.type == KeyEventType.KeyDown && (event.key == Key.Enter || event.key == Key.DirectionCenter)) {
                                onNavigate(item.route)
                                true
                            } else false
                        }
                        .clickable { onNavigate(item.route) }
                        .padding(vertical = spec.itemVerticalPaddingDp.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = spec.itemSpacingDp.dp,
                        alignment = Alignment.CenterVertically,
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                when {
                                    isFocused -> Color.White.copy(alpha = 0.18f)
                                    isSelected -> Color.White.copy(alpha = 0.12f)
                                    else -> Color.Transparent
                                }
                            )
                            .padding(
                                horizontal = spec.iconHorizontalPaddingDp.dp,
                                vertical = spec.iconVerticalPaddingDp.dp,
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = label,
                            tint = when {
                                isFocused -> Color.White
                                isSelected -> TextPrimary
                                else -> TextSecondary.copy(alpha = 0.6f)
                            },
                            modifier = Modifier.size(spec.iconSizeDp.dp)
                        )
                    }
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .size(spec.indicatorSizeDp.dp)
                                .clip(CircleShape)
                                .background(if (isFocused) Color.White else TextPrimary)
                        )
                    } else {
                        Spacer(modifier = Modifier.size(spec.indicatorSizeDp.dp))
                    }
                    Text(
                        text = label,
                        style = ArflixTypography.caption.copy(fontSize = spec.labelFontSizeSp.sp),
                        color = when {
                            isFocused -> Color.White
                            isSelected -> TextPrimary
                            else -> TextSecondary.copy(alpha = 0.6f)
                        },
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
