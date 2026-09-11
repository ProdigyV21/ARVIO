package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.animateColorAsState
import androidx.compose.ui.layout.layout

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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.ui.focus.mirrorHorizontalForRtl
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
    renderContent: Boolean = true,
    isCatchupSupported: Boolean = false,
    onClick: () -> Unit,
    onFocused: () -> Unit = {},
    onMoveLeft: () -> Boolean = { false },
    onMoveRight: () -> Boolean = { false },
    onMoveUp: () -> Boolean = { false },
    onMoveDown: () -> Boolean = { false },
    rowHeight: androidx.compose.ui.unit.Dp = LiveDims.EpgRowHeight,
    contentStartOffsetPx: () -> Int = { 0 },
    focusRequester: FocusRequester? = null,
    modifier: Modifier = Modifier,
) {
    val deviceType = LocalDeviceType.current
    val isTouchDevice = deviceType.isTouchDevice()
    // Retain the standard layout for touch, expanded rows and RTL text layout.
    if (!focusable && !isTouchDevice && rowHeight < 60.dp &&
        LocalLayoutDirection.current == LayoutDirection.Ltr) {
        ChannelProgrammeCanvas(program, width, rowHeight, isNow, isPast,
            isCatchupSupported, contentStartOffsetPx, onClick, modifier)
        return
    }
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val currentOnClick by rememberUpdatedState(onClick)
    var focused by remember { mutableStateOf(false) }
    val baseBg = when {
        isNow -> LiveColors.FocusBg
        else -> LiveColors.Panel
    }
    val bg = animateColorAsState(
        if (focused) LiveColors.PanelRaised else baseBg,
        tween(120), label = "programme-surface",
    )
    val contentAlpha = animateFloatAsState(
        targetValue = if (isPast && !focused && !isCatchupSupported) 0.55f else 1f,
        animationSpec = tween(durationMillis = 90),
        label = "program-cell-alpha",
    )
    Box(
        modifier = modifier
            .height(rowHeight)
            .width(width)
            // Outer gutter was 3dp×2 + inner 10dp×2 = 26dp of horizontal
            // overhead. On a 60dp min-width block that left only ~34dp for
            // text + badges, which the LIVE pill alone consumed — leaving
            // blocks visually empty. Total horizontal overhead is now 8dp.
            .padding(horizontal = 1.dp, vertical = 1.dp)
            .then(if (isPast && !isCatchupSupported) Modifier.graphicsLayer {
                alpha = contentAlpha.value
            } else Modifier)
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
            .liveFocusOutline(focused, LiveDims.CellRadius)
            .drawBehind {
                val radius = LiveDims.CellRadius.toPx()
                drawRoundRect(bg.value, cornerRadius = CornerRadius(radius))
            }
            .then(if (focusable) Modifier.focusable() else Modifier)
            .then(
                if (focusable) {
                    Modifier.onKeyEvent { ev ->
                        if (ev.type != KeyEventType.KeyDown) return@onKeyEvent false
                        when (ev.key.mirrorHorizontalForRtl(isRtl)) {
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
                    Modifier.pointerInput(Unit) { detectTapGestures(onTap = { currentOnClick() }) }
                } else {
                    Modifier
                }
            )
            // Keep focus semantics above this node, but stop accessibility from
            // walking the decorative/text layout beneath each programme.
            .clearAndSetSemantics {
                this[SemanticsProperties.Text] = listOfNotNull(
                    AnnotatedString(program.title),
                    AnnotatedString(formatClock(program.startUtcMillis)),
                    program.description?.takeIf { it.isNotBlank() }?.let(::AnnotatedString),
                )
                if (isNow || (isPast && isCatchupSupported)) {
                    onClick {
                        currentOnClick()
                        true
                    }
                }
            }
            .padding(horizontal = 6.dp, vertical = 2.dp),
    ) {
        // Retain off-screen bounds/focus targets without laying out invisible text.
        if (renderContent) Column(
            modifier = Modifier
                .fillMaxSize()
                // Read scroll position in measurement, not row composition.
                .layout { measurable, constraints ->
                    val shift = contentStartOffsetPx().coerceIn(0, constraints.maxWidth)
                    val content = measurable.measure(constraints.copy(
                        minWidth = (constraints.minWidth - shift).coerceAtLeast(0),
                        maxWidth = (constraints.maxWidth - shift).coerceAtLeast(0),
                    ))
                    layout(content.width + shift, content.height) {
                        content.placeRelative(shift, 0)
                    }
                },
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val nowMs = clockTickMillis
                if (isPast && isCatchupSupported && width >= 150.dp) {
                    Badge(stringResource(R.string.live_badge_archive), LiveColors.FgDim, LiveColors.PanelRaised)
                    Spacer(Modifier.size(6.dp))
                } else if (!isPast) {
                    val isNewTag = (nowMs - program.startUtcMillis) in 0..24L * 60 * 60 * 1000L &&
                        !program.isLive(nowMs)
                    if (isNewTag) {
                        Badge(stringResource(R.string.live_badge_new), LiveColors.FgDim, LiveColors.PanelRaised)
                        Spacer(Modifier.size(6.dp))
                    }
                }
                Text(
                    text = program.title,
                    style = LiveType.CellTitle.copy(color = LiveColors.Fg, fontSize = 10.sp, lineHeight = 12.sp),
                    maxLines = if (width < 120.dp) 2 else 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
            }
            if (rowHeight >= 60.dp && width >= 150.dp && !program.description.isNullOrBlank()) {
                Text(
                    text = program.description!!,
                    style = LiveType.BodySynopsis.copy(color = LiveColors.FgDim, fontSize = 8.sp, lineHeight = 10.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            if (width >= 120.dp) Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = formatClock(program.startUtcMillis),
                    style = LiveType.TimeMono.copy(color = LiveColors.FgMute, fontSize = 8.sp, lineHeight = 10.sp),
                )
                val mins = ((program.endUtcMillis - program.startUtcMillis) / 60_000L)
                    .coerceAtLeast(0L)
                if (mins > 0) {
                    Text(
                        text = stringResource(R.string.live_label_duration_min, mins),
                        style = LiveType.TimeMono.copy(color = LiveColors.FgMute, fontSize = 8.sp, lineHeight = 10.sp),
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
            .padding(horizontal = 4.dp, vertical = 0.5.dp),
    ) {
        Text(label, style = LiveType.Badge.copy(color = fg, fontSize = 7.5.sp, lineHeight = 9.sp))
    }
}
