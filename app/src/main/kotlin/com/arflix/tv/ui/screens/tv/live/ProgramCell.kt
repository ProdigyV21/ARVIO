package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.util.LocalDeviceType

/**
 * A single EPG program cell placed inside a row with an absolute offset.
 * Width is determined by duration × px/min (handled by caller).
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ProgramCell(
    program: IptvProgram,
    clockTickMillis: Long,
    width: androidx.compose.ui.unit.Dp,
    isNow: Boolean,
    isPast: Boolean,
    isFocusTarget: Boolean,
    focusable: Boolean = true,
    isCatchupSupported: Boolean = false,
    onClick: () -> Unit,
    onFocused: () -> Unit = {},
    onMoveLeft: () -> Boolean = { false },
    onMoveRight: () -> Boolean = { false },
    onMoveUp: () -> Boolean = { false },
    onMoveDown: () -> Boolean = { false },
    rowHeight: androidx.compose.ui.unit.Dp = LiveDims.EpgRowHeight,
    focusRequester: FocusRequester? = null,
    modifier: Modifier = Modifier,
) {
    val deviceType = LocalDeviceType.current
    val isTouchDevice = deviceType.isTouchDevice()
    var focused by remember { mutableStateOf(false) }
    val baseBg = when {
        isNow -> LiveColors.FocusBg
        else -> LiveColors.Panel
    }
    val bg = if (focused) LiveColors.PanelRaised else baseBg
    val borderColor = when {
        focused -> LiveColors.FocusRing
        isNow -> LiveColors.Accent.copy(alpha = 0.45f)
        else -> Color.Transparent
    }
    val borderWidth = if (focusable) {
        val animated by animateDpAsState(
            targetValue = if (focused) 3.dp else 1.dp,
            animationSpec = tween(durationMillis = 80),
            label = "program-cell-border",
        )
        animated
    } else {
        if (focused) 3.dp else 1.dp
    }
    val scale = if (focusable) {
        val animated by animateFloatAsState(
            targetValue = if (focused) 1.008f else 1f,
            animationSpec = tween(durationMillis = 90),
            label = "program-cell-scale",
        )
        animated
    } else {
        1f
    }
    val contentAlpha = if (focusable) {
        val animated by animateFloatAsState(
            targetValue = if (isPast && !focused && !isCatchupSupported) 0.55f else 1f,
            animationSpec = tween(durationMillis = 90),
            label = "program-cell-alpha",
        )
        animated
    } else {
        if (isPast && !isCatchupSupported) 0.55f else 1f
    }
    Box(
        modifier = modifier
            .height(rowHeight)
            .width(width)
            // Outer gutter was 3dp×2 + inner 10dp×2 = 26dp of horizontal
            // overhead. On a 60dp min-width block that left only ~34dp for
            // text + badges, which the LIVE pill alone consumed — leaving
            // blocks visually empty. Total horizontal overhead is now 8dp.
            .padding(horizontal = 1.dp, vertical = 3.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .then(
                if (focusable && focusRequester != null) {
                    Modifier.focusRequester(focusRequester)
                } else {
                    Modifier
                }
            )
            .then(
                if (focusable) {
                    Modifier.onFocusChanged {
                        focused = it.hasFocus
                        if (it.hasFocus) onFocused()
                    }
                } else {
                    Modifier
                }
            )
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(LiveDims.CellRadius),
            )
            .clip(RoundedCornerShape(LiveDims.CellRadius))
            .background(bg)
            .alpha(contentAlpha)
            .then(if (focusable) Modifier.focusable() else Modifier)
            .then(
                if (focusable) {
                    Modifier.onKeyEvent { ev ->
                        if (ev.type != KeyEventType.KeyDown) return@onKeyEvent false
                        when (ev.key) {
                            Key.DirectionLeft -> onMoveLeft()
                            Key.DirectionRight -> onMoveRight()
                            Key.DirectionUp -> onMoveUp()
                            Key.DirectionDown -> onMoveDown()
                            Key.DirectionCenter, Key.Enter -> {
                                onClick()
                                true
                            }
                            else -> false
                        }
                    }
                } else {
                    Modifier
                }
            )
            .then(
                if (focusable || isTouchDevice) {
                    Modifier.pointerInput(Unit) { detectTapGestures(onTap = { onClick() }) }
                } else {
                    Modifier
                }
            )
            .padding(horizontal = 6.dp, vertical = 4.dp),
    ) {
        if (isNow) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                LiveColors.Accent.copy(alpha = 0.22f),
                                Color.Transparent,
                            )
                        )
                    )
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val nowMs = clockTickMillis
                if (isNow) {
                    Badge(stringResource(R.string.live_badge_live), Color.White, LiveColors.LiveRed)
                    Spacer(Modifier.size(6.dp))
                } else if (isPast && isCatchupSupported) {
                    Badge(stringResource(R.string.live_badge_archive), LiveColors.Bg, LiveColors.Accent)
                    Spacer(Modifier.size(6.dp))
                } else if (!isPast) {
                    val isNewTag = (nowMs - program.startUtcMillis) in 0..24L * 60 * 60 * 1000L &&
                        !program.isLive(nowMs)
                    if (isNewTag) {
                        Badge(stringResource(R.string.live_badge_new), LiveColors.Bg, LiveColors.Accent)
                        Spacer(Modifier.size(6.dp))
                    }
                }
                Text(
                    text = program.title,
                    style = LiveType.CellTitle.copy(color = LiveColors.Fg, fontSize = 11.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
            }
            if (!program.description.isNullOrBlank()) {
                Text(
                    text = program.description!!,
                    style = LiveType.BodySynopsis.copy(color = LiveColors.FgDim, fontSize = 9.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = formatClock(program.startUtcMillis),
                    style = LiveType.TimeMono.copy(color = LiveColors.FgMute, fontSize = 9.sp),
                )
                val mins = ((program.endUtcMillis - program.startUtcMillis) / 60_000L)
                    .coerceAtLeast(0L)
                if (mins > 0) {
                    Text(
                        text = stringResource(R.string.live_label_duration_min, mins),
                        style = LiveType.TimeMono.copy(color = LiveColors.FgMute, fontSize = 9.sp),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun Badge(label: String, fg: Color, bg: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(3.dp))
            .background(bg)
            .padding(horizontal = 5.dp, vertical = 1.dp),
    ) {
        Text(label, style = LiveType.Badge.copy(color = fg, fontSize = 9.sp))
    }
}
