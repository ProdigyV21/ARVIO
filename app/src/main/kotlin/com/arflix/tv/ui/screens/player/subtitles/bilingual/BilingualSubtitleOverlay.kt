package com.arflix.tv.ui.screens.player.subtitles.bilingual

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.ui.screens.player.resolveSubtitleTypeface
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationManager

/**
 * The bilingual AI subtitle layer: draws the current [BilingualFrame] of
 * [SubtitleTranslationManager.bilingualBus] as two independently styled text blocks.
 *
 * It is a plain Compose layer above the video (no second track, decoder or TextRenderer):
 * the text comes from the single source TextRenderer via the renderers factory, so timing is
 * the source track's own. Style/position changes recompose immediately.
 */
@Composable
fun BilingualSubtitleOverlay(
    manager: SubtitleTranslationManager,
    aiActive: Boolean,
    hidden: Boolean,
    isInPictureInPicture: Boolean,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val store = remember { BilingualSubtitleSettingsStore.from(context) }
    val settings by store.settings.collectAsState()
    val frame by manager.bilingualBus.frame.collectAsState()

    // Keep the renderer in step with the chosen mode ("Original only"/"Off" stop API calls).
    LaunchedEffect(settings.mode) { manager.displayMode = settings.mode }

    // Switching to a translating mode mid-line: fetch the current line's translation too.
    // translate() joins an in-flight request / hits the cache, so this never duplicates a call.
    val current = frame
    LaunchedEffect(current?.generation, settings.mode, aiActive) {
        if (current == null || !aiActive || !manager.isEnabled) return@LaunchedEffect
        if (current.translation != null || current.translationFailed) return@LaunchedEffect
        // Path A (second embedded track) never translates.
        if (!manager.translationNeeded) return@LaunchedEffect
        val (translated, ok) = try {
            manager.translateDetailed(current.sourceKey)
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (e: Exception) {
            current.sourceKey to false
        }
        manager.bilingualBus.deliverTranslation(current.generation, current.sourceKey, translated, !ok)
    }

    // Smart-mode status ("merging embedded tracks", "no AI key", "tracks don't line up"…):
    // shown briefly at the top so a fallback is never silent.
    val notice by manager.bilingualNotice.collectAsState()
    LaunchedEffect(notice?.id) {
        val shown = notice ?: return@LaunchedEffect
        kotlinx.coroutines.delay(if (shown.isWarning) 7_000L else 4_000L)
        if (manager.bilingualNotice.value?.id == shown.id) manager.bilingualNotice.value = null
    }
    if (!hidden && !isInPictureInPicture) {
        notice?.let { n ->
            Box(modifier = modifier.fillMaxSize().windowInsetsPadding(WindowInsets.displayCutout)) {
                Text(
                    text = n.message(),
                    color = if (n.isWarning) Color(0xFFFFD49A) else Color(0xFFEFEFEF),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 18.dp, start = 24.dp, end = 24.dp)
                        .background(Color(0xB3000000), RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }
    }

    if (!aiActive || hidden) return
    val blocks = BilingualComposer.compose(frame, settings)
    if (blocks.isEmpty()) return

    BilingualSubtitleGroup(
        blocks = blocks,
        settings = settings,
        scale = if (isInPictureInPicture) 0.45f else 1f,
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.displayCutout),
    )
}

/**
 * Lays out [blocks] with [BilingualLayout] inside the available box. Also used by the
 * settings preview so the preview is pixel-identical to playback.
 */
@Composable
fun BilingualSubtitleGroup(
    blocks: List<BilingualBlock>,
    settings: BilingualSubtitleSettings,
    modifier: Modifier = Modifier,
    scale: Float = 1f,
    safeTopDp: Int = 8,
    safeBottomDp: Int = 4,
) {
    val density = LocalDensity.current
    Layout(
        modifier = modifier,
        content = {
            blocks.forEach { block ->
                val style = if (block.role == BilingualRole.ORIGINAL) settings.original else settings.translation
                BilingualSubtitleText(text = block.text, style = style, scale = scale)
            }
        }
    ) { measurables, constraints ->
        val width = constraints.maxWidth
        val height = if (constraints.hasBoundedHeight) constraints.maxHeight else 0
        val maxTextWidth = (width * 0.92f).toInt().coerceAtLeast(0)
        val placeables = measurables.map {
            it.measure(Constraints(minWidth = 0, maxWidth = maxTextWidth, minHeight = 0))
        }
        val offsets = blocks.map { block ->
            val s = if (block.role == BilingualRole.ORIGINAL) settings.original else settings.translation
            with(density) { (s.offsetDp * scale).dp.roundToPx() }
        }
        val tops = BilingualLayout.place(
            BilingualLayout.Input(
                containerHeightPx = height,
                blockHeightsPx = placeables.map { it.height },
                gapPx = with(density) { (settings.gapDp * scale).dp.roundToPx() },
                bottomMarginPx = BilingualLayout.bottomMarginPx(height, settings.bottomPercent),
                blockOffsetsPx = offsets,
                safeTopPx = with(density) { safeTopDp.dp.roundToPx() },
                safeBottomPx = with(density) { safeBottomDp.dp.roundToPx() },
            )
        )
        layout(width, height) {
            placeables.forEachIndexed { i, p ->
                p.place(x = (width - p.width) / 2, y = tops[i])
            }
        }
    }
}

/** One language block: fill text over an optional outline (stroke) layer, optional shadow/box. */
@Composable
fun BilingualSubtitleText(
    text: String,
    style: BilingualLineStyle,
    scale: Float = 1f,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val fontFamily = remember(style.font, style.bold) {
        FontFamily(resolveSubtitleTypeface(context, style.font, style.bold))
    }
    val fill = Color(BilingualColors.parseArgb(style.colorHex))
    val outlineColor = Color(BilingualColors.parseArgb(style.outlineColorHex, 0xFF000000.toInt()))
    val fontSize = (style.sizeSp * scale).sp
    val base = TextStyle(
        fontFamily = fontFamily,
        fontWeight = if (style.bold) FontWeight.Bold else FontWeight.Normal,
        fontSize = fontSize,
        lineHeight = fontSize * 1.18f,
        textAlign = TextAlign.Center,
    )
    val boxModifier = if (style.background) {
        modifier
            .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 1.dp)
    } else modifier
    Box(modifier = boxModifier, contentAlignment = Alignment.Center) {
        if (style.outline != SubtitleOutline.NONE) {
            val strokePx = with(LocalDensity.current) { (style.outline.widthDp * scale).dp.toPx() } * 2f
            Text(
                text = text,
                style = base.copy(
                    color = outlineColor,
                    drawStyle = Stroke(width = strokePx, join = androidx.compose.ui.graphics.StrokeJoin.Round),
                ),
            )
        }
        Text(
            text = text,
            style = base.copy(
                color = fill,
                shadow = if (style.shadow) Shadow(
                    color = Color.Black.copy(alpha = 0.85f),
                    offset = Offset(2f * scale, 2f * scale),
                    blurRadius = 4f * scale,
                ) else null,
            ),
        )
    }
}
