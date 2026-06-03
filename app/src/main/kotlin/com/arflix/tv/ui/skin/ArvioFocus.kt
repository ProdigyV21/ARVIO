package com.arflix.tv.ui.skin

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.arvioFocusable(
    enabled: Boolean = true,
    enableSystemFocus: Boolean = true,
    useSystemFocusForVisuals: Boolean = true,
    isFocusedOverride: Boolean = false,
    shape: Shape,
    focusedScale: Float,
    pressedScale: Float,
    outlineWidth: Dp,
    glowWidth: Dp,
    glowAlpha: Float,
    outlineColor: Color,
    focusedTransformOriginX: Float = 0.5f,
    useGradientBorder: Boolean = false,  // Arctic Fuse 2: SOLID border, not gradient
    gradientStartColor: Color = Color(0xFFFF00FF),  // Magenta (unused when solid)
    gradientEndColor: Color = Color(0xFF00D4FF),    // Cyan (unused when solid)
    showRestBorder: Boolean = false,
    animateFocus: Boolean = true,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onFocusChanged: (Boolean) -> Unit = {},
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    // Allow the user's "Accent Color" setting to override the default
    val resolvedOutlineColor = LocalAccentColorOverride.current ?: outlineColor
    val isPressed by interactionSource.collectIsPressedAsState()

    var isFocused by remember { mutableStateOf(false) }
    val visualFocused = isFocusedOverride || (useSystemFocusForVisuals && isFocused)
    val targetScale = when {
        isPressed -> pressedScale
        visualFocused -> focusedScale
        else -> 1f
    }

    val tokens = ArvioSkin.focus
    val scale = if (animateFocus) {
        val animatedScale by animateFloatAsState(
            targetValue = targetScale,
            animationSpec = tween(durationMillis = 105, easing = tokens.easing),
            label = "arvio_focus_scale",
        )
        animatedScale
    } else {
        targetScale
    }

    // Focus-in must be immediately visible on TV D-pad moves; only fade out.
    val animatedHighlightAlpha = if (animateFocus) {
        val animatedAlpha by animateFloatAsState(
            targetValue = if (visualFocused) 1f else 0f,
            animationSpec = tween(durationMillis = 120, easing = tokens.easing),
            label = "arvio_focus_alpha",
        )
        animatedAlpha
    } else {
        if (visualFocused) 1f else 0f
    }
    val highlightAlpha = if (visualFocused) 1f else animatedHighlightAlpha

    // Subtle luminous edge always visible on cards that opt in (glass morphism).
    val restBorderAlpha by animateFloatAsState(
        targetValue = if (showRestBorder && !visualFocused) 0.4f else 0f,
        animationSpec = tween(durationMillis = 150, easing = tokens.easing),
        label = "arvio_rest_border",
    )

    val originX = if (visualFocused) focusedTransformOriginX.coerceIn(0f, 1f) else 0.5f
    val focusTransformOrigin = TransformOrigin(originX, 0.5f)

    val clickable = if (onClick != null && onLongClick != null) {
        Modifier.combinedClickable(
            enabled = enabled,
            role = Role.Button,
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick,
            onLongClick = onLongClick,
        )
    } else if (onClick != null) {
        Modifier.clickable(
            enabled = enabled,
            role = Role.Button,
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick,
        )
    } else {
        Modifier
    }

    val focusModifier = if (enableSystemFocus) {
        Modifier.onFocusChanged { state ->
            val focusedNow = state.isFocused
            if (focusedNow != isFocused) {
                isFocused = focusedNow
                onFocusChanged(focusedNow)
            }
        }
    } else {
        Modifier
    }

    val systemFocusable = if (enableSystemFocus) {
        Modifier.focusable(enabled = enabled)
    } else {
        Modifier
    }

    // Keep the focus drawing modifier stable so fast D-pad moves do not
    // produce a one-frame missing-focus flash.
    val layerModifier = if (visualFocused || isPressed || kotlin.math.abs(scale - 1f) > 0.001f) {
        Modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
            transformOrigin = focusTransformOrigin
        }
    } else {
        Modifier
    }

    val borderModifier = if (highlightAlpha > 0.01f || restBorderAlpha > 0.01f) {
        Modifier.drawWithCache {
            val outline = shape.createOutline(size, layoutDirection, this)
            val borderWidth = if (highlightAlpha > 0f) outlineWidth.toPx() else 0.5.dp.toPx()
            val ringAlpha = if (highlightAlpha > 0f) highlightAlpha else restBorderAlpha * 0.5f
            val ringColor = resolvedOutlineColor.copy(alpha = ringAlpha)
            val glowStrokeWidth = glowWidth.toPx()
            val drawGlow = highlightAlpha > 0.3f && glowStrokeWidth > 0.01f && glowAlpha > 0.01f
            val glowColor = resolvedOutlineColor.copy(alpha = highlightAlpha * glowAlpha)

            onDrawWithContent {
                drawContent()
                when (outline) {
                    is Outline.Rounded -> {
                        val radius = outline.roundRect.topLeftCornerRadius
                        if (drawGlow) {
                            drawRoundRect(
                                color = glowColor,
                                cornerRadius = radius,
                                style = Stroke(width = borderWidth + glowStrokeWidth)
                            )
                        }
                        drawRoundRect(
                            color = ringColor,
                            cornerRadius = radius,
                            style = Stroke(width = borderWidth)
                        )
                    }
                    is Outline.Rectangle -> {
                        if (drawGlow) {
                            drawRect(color = glowColor, style = Stroke(width = borderWidth + glowStrokeWidth))
                        }
                        drawRect(color = ringColor, style = Stroke(width = borderWidth))
                    }
                    is Outline.Generic -> {
                        if (drawGlow) {
                            val path = Path().apply { addPath(outline.path) }
                            drawPath(path = path, color = glowColor, style = Stroke(width = borderWidth + glowStrokeWidth))
                        }
                        drawPath(path = outline.path, color = ringColor, style = Stroke(width = borderWidth))
                    }
                }
            }
        }
    } else {
        Modifier
    }

    this
        .then(focusModifier)
        .then(layerModifier)
        .then(borderModifier)
        .then(clickable)
        .then(systemFocusable)
}

@Composable
fun ArvioFocusableSurface(
    modifier: Modifier = Modifier,
    shape: Shape,
    backgroundColor: Color = ArvioSkin.colors.surface,
    focusedScale: Float = ArvioSkin.focus.scaleFocused,
    pressedScale: Float = ArvioSkin.focus.scalePressed,
    outlineWidth: Dp = ArvioSkin.focus.outlineWidth,
    glowWidth: Dp = ArvioSkin.focus.glowWidth,
    glowAlpha: Float = ArvioSkin.focus.glowAlpha,
    outlineColor: Color = ArvioSkin.colors.focusOutline,
    focusedTransformOriginX: Float = 0.5f,
    useGradientBorder: Boolean = false,  // Arctic Fuse 2: SOLID border, not gradient
    gradientStartColor: Color = ArvioSkin.colors.focusGradientStart,
    gradientEndColor: Color = ArvioSkin.colors.focusGradientEnd,
    showRestBorder: Boolean = false,
    animateFocus: Boolean = true,
    enabled: Boolean = true,
    enableSystemFocus: Boolean = true,
    useSystemFocusForVisuals: Boolean = true,
    isFocusedOverride: Boolean = false,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onFocusChanged: (Boolean) -> Unit = {},
    content: @Composable BoxScope.(isFocused: Boolean) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }
    val visualFocused = isFocusedOverride || (useSystemFocusForVisuals && isFocused)

    Box(
        modifier = modifier
            .arvioFocusable(
                enabled = enabled,
                enableSystemFocus = enableSystemFocus,
                useSystemFocusForVisuals = useSystemFocusForVisuals,
                isFocusedOverride = isFocusedOverride,
                shape = shape,
                focusedScale = focusedScale,
                pressedScale = pressedScale,
                outlineWidth = outlineWidth,
                glowWidth = glowWidth,
                glowAlpha = glowAlpha,
                outlineColor = outlineColor,
                focusedTransformOriginX = focusedTransformOriginX,
                useGradientBorder = useGradientBorder,
                gradientStartColor = gradientStartColor,
                gradientEndColor = gradientEndColor,
                showRestBorder = showRestBorder,
                animateFocus = animateFocus,
                onClick = onClick,
                onLongClick = onLongClick,
                onFocusChanged = {
                    isFocused = it
                    onFocusChanged(it)
                },
            )
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(shape)
                .background(backgroundColor)
        ) {
            content(visualFocused)
        }
    }
}

@Composable
fun rememberArvioCardShape(cornerRadius: Dp = ArvioSkin.radius.md): Shape {
    return remember(cornerRadius) {
        androidx.compose.foundation.shape.RoundedCornerShape(cornerRadius)
    }
}
