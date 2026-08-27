package com.arflix.tv.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.R
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType

internal enum class AppBottomBarMode {
    STANDARD,
    LANDSCAPE_COMPACT,
}

internal data class AppBottomBarSpec(
    val rowVerticalPaddingDp: Int,
    val itemVerticalPaddingDp: Int,
    val itemSpacingDp: Int,
    val iconHorizontalPaddingDp: Int,
    val iconSizeDp: Int,
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
        rowVerticalPaddingDp = 4,
        itemVerticalPaddingDp = 4,
        itemSpacingDp = 3,
        iconHorizontalPaddingDp = 14,
        iconSizeDp = 21,
        labelFontSizeSp = 10,
    )
    AppBottomBarMode.STANDARD -> AppBottomBarSpec(
        rowVerticalPaddingDp = 5,
        itemVerticalPaddingDp = 4,
        itemSpacingDp = 3,
        iconHorizontalPaddingDp = 14,
        iconSizeDp = 22,
        labelFontSizeSp = 11,
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

@Composable
fun AppBottomBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val mode = appBottomBarMode(
        isTouchDevice = LocalDeviceType.current.isTouchDevice(),
        smallestScreenWidthDp = configuration.smallestScreenWidthDp,
        screenWidthDp = configuration.screenWidthDp,
        screenHeightDp = configuration.screenHeightDp,
    )
    val spec = appBottomBarSpec(mode)

    var activeRoute by remember(currentRoute) { mutableStateOf(currentRoute ?: "home") }

    val selectedIndex = remember(activeRoute) {
        when {
            activeRoute.startsWith("search") -> 1
            activeRoute.startsWith("watchlist") -> 2
            activeRoute.startsWith("tv") -> 3
            activeRoute.startsWith("settings") -> 4
            else -> 0
        }
    }

    val itemPositions = remember { mutableStateMapOf<Int, Float>() }
    val itemWidths = remember { mutableStateMapOf<Int, Float>() }
    val itemHeights = remember { mutableStateMapOf<Int, Float>() }
    val indicatorOffset = remember { Animatable(0f) }
    val indicatorWidth = remember { Animatable(0f) }

    val fastSpringSpec = remember {
        spring<Float>(dampingRatio = 0.82f, stiffness = 900f)
    }

    var isAnyItemPressed by remember { mutableStateOf(false) }

    val wholeBarScale by animateFloatAsState(
        targetValue = if (isAnyItemPressed) 1.05f else 1.0f,
        animationSpec = fastSpringSpec,
        label = "whole_bar_zoom"
    )

    LaunchedEffect(selectedIndex, itemPositions[selectedIndex], itemWidths[selectedIndex]) {
        val targetX = itemPositions[selectedIndex] ?: 0f
        val targetW = itemWidths[selectedIndex] ?: 0f
        if (targetW > 0f) {
            if (indicatorWidth.value == 0f) {
                indicatorOffset.snapTo(targetX)
                indicatorWidth.snapTo(targetW)
            } else {
                indicatorOffset.animateTo(targetX, fastSpringSpec)
                indicatorWidth.animateTo(targetW, fastSpringSpec)
            }
        }
    }

    Box(
        modifier = modifier
            .wrapContentSize()
            .graphicsLayer {
                scaleX = wholeBarScale
                scaleY = wholeBarScale
            }
            .shadow(
                elevation = 20.dp,
                shape = CircleShape,
                ambientColor = Color.Black.copy(alpha = 0.65f),
                spotColor = Color(0xFF00F0D0).copy(alpha = 0.25f)
            )
            .clip(CircleShape)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF252836).copy(alpha = 0.65f),
                        Color(0xFF111319).copy(alpha = 0.78f)
                    )
                )
            )
            .border(
                width = 1.2.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.35f),
                        Color.White.copy(alpha = 0.06f)
                    )
                ),
                shape = CircleShape
            )
            .padding(horizontal = 8.dp, vertical = spec.rowVerticalPaddingDp.dp)
    ) {
        // Sliding Highlight Pill positioned with coverage over keywords
        if (indicatorWidth.value > 0f) {
            val targetHeightDp = with(density) {
                ((itemHeights[selectedIndex] ?: 0f) + 4.dp.toPx()).toDp()
            }
            val targetWidthDp = with(density) {
                ((indicatorWidth.value) + 6.dp.toPx()).toDp()
            }
            val extraXOffsetPx = with(density) { (-3.dp).toPx() }
            val extraYOffsetPx = with(density) { (-1.dp).toPx() }

            if (targetHeightDp > 0.dp) {
                Box(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                x = (indicatorOffset.value + extraXOffsetPx).toInt(),
                                y = extraYOffsetPx.toInt()
                            )
                        }
                        .width(targetWidthDp)
                        .height(targetHeightDp)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.24f),
                                    Color.White.copy(alpha = 0.09f)
                                )
                            )
                        )
                        .border(
                            1.dp,
                            Brush.verticalGradient(
                                listOf(
                                    Color.White.copy(alpha = 0.45f),
                                    Color.Transparent
                                )
                            ),
                            CircleShape
                        )
                )
            }
        }

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spec.itemSpacingDp.dp)
        ) {
            bottomBarItems.forEachIndexed { index, item ->
                val isSelected = selectedIndex == index
                var isFocused by remember { mutableStateOf(false) }
                val label = stringResource(item.labelRes)
                val interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()

                LaunchedEffect(isPressed) {
                    if (isPressed) isAnyItemPressed = true
                }

                val itemZoomScale by animateFloatAsState(
                    targetValue = if (isSelected || isFocused) 1.10f else 1.0f,
                    animationSpec = fastSpringSpec,
                    label = "bottom_item_zoom"
                )

                val contentColor by animateColorAsState(
                    targetValue = when {
                        isFocused -> Color.White
                        isSelected -> Color(0xFF00F0D0)
                        else -> Color.White.copy(alpha = 0.60f)
                    },
                    animationSpec = tween(120),
                    label = "bottom_tint"
                )

                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .onGloballyPositioned { coordinates ->
                            val x = coordinates.positionInParent().x
                            val w = coordinates.size.width.toFloat()
                            val h = coordinates.size.height.toFloat()
                            itemPositions[index] = x
                            itemWidths[index] = w
                            itemHeights[index] = h
                        }
                        .clip(CircleShape)
                        .focusable()
                        .onFocusChanged { isFocused = it.isFocused }
                        .onKeyEvent { event ->
                            if (event.type == KeyEventType.KeyDown && (event.key == Key.Enter || event.key == Key.DirectionCenter)) {
                                activeRoute = item.route
                                onNavigate(item.route)
                                true
                            } else false
                        }
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            activeRoute = item.route
                            onNavigate(item.route)
                        }
                        .padding(
                            horizontal = spec.iconHorizontalPaddingDp.dp,
                            vertical = spec.itemVerticalPaddingDp.dp
                        )
                        .graphicsLayer {
                            scaleX = itemZoomScale
                            scaleY = itemZoomScale
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = spec.itemSpacingDp.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = label,
                        tint = contentColor,
                        modifier = Modifier.size(spec.iconSizeDp.dp)
                    )

                    Text(
                        text = label,
                        style = ArflixTypography.caption.copy(
                            fontSize = spec.labelFontSizeSp.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        ),
                        color = contentColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}