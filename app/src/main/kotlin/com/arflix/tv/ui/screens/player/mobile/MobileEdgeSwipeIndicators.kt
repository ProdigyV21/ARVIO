package com.arflix.tv.ui.screens.player.mobile

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

/**
 * Vertical edge HUD indicator for Brightness (Left) and Volume (Right).
 * Clean floating indicator without frosted glass containers, floating directly over video.
 */
@Composable
fun MobileEdgeIndicator(
    visible: Boolean,
    levelPct: Float, // 0.0f .. 2.0f for volume, 0.0f .. 1.0f for brightness
    isLeft: Boolean,
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int? = null,
    icon: ImageVector? = null,
    isAuto: Boolean = false,
) {
    val isBoost = levelPct > 1.005f
    val boostColor = Color(0xFFFFB300) // Amber accent for boost mode (> 100%)
    val activeColor = if (isBoost) boostColor else MobilePlayerTokens.InkPrimary

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(140)),
        exit = fadeOut(tween(200)),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .zIndex(9f)
                .padding(vertical = 12.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Icon
            Box(
                modifier = Modifier.size(22.dp),
                contentAlignment = Alignment.Center
            ) {
                if (iconRes != null) {
                    Icon(
                        painter = painterResource(iconRes),
                        contentDescription = null,
                        tint = activeColor,
                        modifier = Modifier.size(22.dp)
                    )
                } else if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = activeColor,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            // Vertical slider bar (4dp width, 120dp height)
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color.White.copy(alpha = 0.25f)),
                contentAlignment = Alignment.BottomCenter
            ) {
                if (!isAuto) {
                    val normalFraction = levelPct.coerceIn(0f, 1f)
                    // White bar for standard volume (0% .. 100%)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(normalFraction)
                            .background(Color.White, RoundedCornerShape(2.dp))
                    )

                    // Orange bar for boost mode (100% .. 200%) filling from bottom on top of white, consuming it
                    if (levelPct > 1.0f) {
                        val boostFraction = (levelPct - 1.0f).coerceIn(0f, 1f)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(boostFraction)
                                .background(boostColor, RoundedCornerShape(2.dp))
                        )
                    }
                }
            }

            // Percentage or "Auto" value label
            Text(
                text = if (isAuto) "Auto" else "${(levelPct * 100).toInt()}%",
                color = activeColor,
                fontSize = 11.sp,
                fontWeight = if (isBoost) FontWeight.Bold else FontWeight.SemiBold,
                style = androidx.compose.ui.text.TextStyle(
                    shadow = MobilePlayerTokens.TextShadow
                )
            )
        }
    }
}

/**
 * Transient center HUD indicator for Aspect Ratio cycling (Fit / Fill / Zoom).
 */
@Composable
fun MobileAspectIndicator(
    aspectText: String,
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(120)),
        exit = fadeOut(tween(180)),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .background(MobilePlayerTokens.PanelBg, RoundedCornerShape(10.dp))
                .border(1.dp, MobilePlayerTokens.PanelBorder, RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .zIndex(20f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = aspectText,
                color = MobilePlayerTokens.InkPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
