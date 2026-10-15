package com.arflix.tv.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.Profile
import com.arflix.tv.ui.skin.resolveAccentColor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

private val PillHeight = 54.dp
private val PillCorner = 27.dp
private val NAV_ITEMS = SidebarItem.entries.filter { it != SidebarItem.SETTINGS }

/**
 * Two independently sprung edges for a fluid liquid lens effect.
 */
@Stable
private class LiquidIndicator {
    val left = Animatable(0f)
    val right = Animatable(0f)
    var placed by mutableStateOf(false)
        private set
    private var restWidth = 1f

    suspend fun moveTo(targetLeft: Float, targetRight: Float) {
        restWidth = (targetRight - targetLeft).coerceAtLeast(1f)
        if (!placed) {
            left.snapTo(targetLeft)
            right.snapTo(targetRight)
            placed = true
            return
        }
        val movingRight = targetLeft > left.value
        val lead = spring<Float>(dampingRatio = 0.72f, stiffness = 520f)
        val trail = spring<Float>(dampingRatio = 0.86f, stiffness = 210f)
        coroutineScope {
            launch { left.animateTo(targetLeft, if (movingRight) trail else lead) }
            launch { right.animateTo(targetRight, if (movingRight) lead else trail) }
        }
    }

    fun stretch(): Float = ((right.value - left.value) / restWidth - 1f).coerceIn(0f, 1.5f) / 1.5f
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TopPillNavigationBar(
    selectedItem: SidebarItem,
    isFocused: Boolean,
    focusedIndex: Int,
    profile: Profile? = null,
    profileCount: Int = 1,
    clockFormat: String = "24h",
    hasUpdateBadge: Boolean = false,
    modifier: Modifier = Modifier
) {
    val showProfile = profile != null
    val hasProfile = showProfile
    val currentTime = rememberTopBarTime(clockFormat)
    val selectedIndex = remember(selectedItem, hasProfile) { topBarSelectedIndex(selectedItem, hasProfile) }
    val settingsIndex = topBarMaxIndex(hasProfile)
    val settingsFocused = isFocused && focusedIndex == settingsIndex
    val settingsSelected = selectedItem == SidebarItem.SETTINGS

    val itemBounds = remember { mutableStateMapOf<Int, Pair<Float, Float>>() }
    var rowOffset by remember { mutableStateOf(0f) }
    val indicator = remember { LiquidIndicator() }

    // Animate fluid indicator lens to the focused or selected item
    val activeIndex = if (isFocused) focusedIndex else selectedIndex
    val targetBounds = itemBounds[activeIndex]

    LaunchedEffect(targetBounds, rowOffset) {
        if (targetBounds != null) {
            val start = rowOffset + targetBounds.first
            val end = start + targetBounds.second
            indicator.moveTo(start, end)
        }
    }

    Box(
        modifier = modifier
            .testTag("app-topbar-pill")
            .fillMaxWidth()
            .height(AppTopBarContentTopInset)
            .padding(top = 16.dp, start = AppTopBarHorizontalPadding, end = AppTopBarHorizontalPadding),
        contentAlignment = Alignment.TopCenter
    ) {
        // Floating Frosted Glass Capsule
        Box(
            modifier = Modifier
                .height(PillHeight)
                .clip(RoundedCornerShape(PillCorner))
                .background(Color(0xFF141418).copy(alpha = 0.82f))
                .drawWithCache {
                    val corner = PillCorner.toPx()
                    val radius = CornerRadius(corner)
                    val frost = Brush.verticalGradient(
                        0f to Color.White.copy(alpha = 0.22f),
                        0.5f to Color.White.copy(alpha = 0.08f),
                        1f to Color.White.copy(alpha = 0.14f),
                    )
                    val rim = Brush.linearGradient(
                        0f to Color.White.copy(alpha = 0.65f),
                        0.4f to Color.White.copy(alpha = 0.15f),
                        0.8f to Color.White.copy(alpha = 0.08f),
                        1f to Color.White.copy(alpha = 0.35f),
                        start = Offset.Zero,
                        end = Offset(size.width, size.height),
                    )
                    val rimStroke = Stroke(width = 1.2.dp.toPx())
                    val shadowStep = 1.dp.toPx()
                    val shadow = Color.Black.copy(alpha = 0.25f)

                    onDrawBehind {
                        // Cast subtle drop shadow
                        for (step in 1..3) {
                            drawRoundRect(shadow, topLeft = Offset(0f, step * shadowStep), cornerRadius = radius)
                        }
                        // Liquid lens highlight
                        if (indicator.placed) {
                            val stretch = indicator.stretch()
                            val squash = 1f - 0.15f * stretch
                            val lensH = size.height * squash
                            val topY = (size.height - lensH) / 2f
                            val width = indicator.right.value - indicator.left.value
                            if (width > 0f) {
                                val lensFill = Brush.verticalGradient(
                                    0f to Color.White.copy(alpha = 0.32f),
                                    0.6f to Color.White.copy(alpha = 0.16f),
                                    1f to Color.White.copy(alpha = 0.22f),
                                )
                                val lensRim = Brush.verticalGradient(
                                    0f to Color.White.copy(alpha = 0.75f),
                                    1f to Color.White.copy(alpha = 0.20f),
                                )
                                drawRoundRect(
                                    brush = lensFill,
                                    topLeft = Offset(indicator.left.value, topY),
                                    size = Size(width, lensH),
                                    cornerRadius = CornerRadius(lensH / 2f),
                                )
                                drawRoundRect(
                                    brush = lensRim,
                                    topLeft = Offset(indicator.left.value, topY),
                                    size = Size(width, lensH),
                                    cornerRadius = CornerRadius(lensH / 2f),
                                    style = Stroke(width = 1.dp.toPx()),
                                )
                            }
                        }
                        // Frosted sheen & specular border rim
                        drawRoundRect(frost, cornerRadius = radius)
                        drawRoundRect(rim, cornerRadius = radius, style = rimStroke)
                    }
                }
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.onPlaced {
                    rowOffset = it.positionInParent().x
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Profile Avatar (if present)
                if (showProfile && profile != null) {
                    Box(
                        modifier = Modifier
                            .onPlaced { itemBounds[0] = it.positionInParent().x to it.size.width.toFloat() }
                            .padding(end = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        TopBarProfileAvatar(
                            profile = profile,
                            isFocused = isFocused && focusedIndex == 0
                        )
                    }
                }

                // Nav Items (Search, Home, Watchlist, TV)
                NAV_ITEMS.forEachIndexed { index, item ->
                    val itemFocusIndex = if (hasProfile) index + 1 else index
                    val isItemFocused = isFocused && focusedIndex == itemFocusIndex
                    val isItemSelected = selectedIndex == itemFocusIndex

                    PillNavChip(
                        item = item,
                        isFocused = isItemFocused,
                        isSelected = isItemSelected,
                        modifier = Modifier.onPlaced {
                            itemBounds[itemFocusIndex] = it.positionInParent().x to it.size.width.toFloat()
                        }
                    )
                }

                // Settings Gear
                Box(
                    modifier = Modifier.onPlaced {
                        itemBounds[settingsIndex] = it.positionInParent().x to it.size.width.toFloat()
                    },
                    contentAlignment = Alignment.Center
                ) {
                    TopBarSettingsGear(
                        isFocused = settingsFocused,
                        isSelected = settingsSelected,
                        hasBadge = hasUpdateBadge
                    )
                }

                // Clock divider & time display
                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                        .height(18.dp)
                        .background(Color.White.copy(alpha = 0.2f))
                )

                Text(
                    text = currentTime,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.75f),
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
            }
        }
    }
}

@Composable
private fun PillNavChip(
    item: SidebarItem,
    isFocused: Boolean,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val accent = resolveAccentColor(fallback = Color.White)
    val label = if (item == SidebarItem.TV) stringResource(R.string.topbar_tv) else stringResource(item.labelRes)

    val contentAlpha by animateFloatAsState(
        targetValue = if (isFocused || isSelected) 1f else 0.65f,
        animationSpec = tween(120),
        label = "pill_chip_alpha"
    )

    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.05f else 1f,
        animationSpec = spring(dampingRatio = 0.8f, stiffness = Spring.StiffnessMedium),
        label = "pill_chip_scale"
    )

    Row(
        modifier = modifier
            .testTag("topbar-pill-item-${item.name}")
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                alpha = contentAlpha
            }
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = label,
            tint = if (isSelected && !isFocused) accent else Color.White,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = if (isFocused || isSelected) FontWeight.SemiBold else FontWeight.Medium,
            color = if (isSelected && !isFocused) accent else Color.White
        )
    }
}
