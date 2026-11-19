package com.arflix.tv.ui.screens.player.preview

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.zIndex
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Draw above the seek bar without contributing height or intercepting focus. */
internal fun Modifier.seekPreviewOverlay(): Modifier = zIndex(1f).layout { measurable, constraints ->
    val preview = measurable.measure(constraints.copy(minHeight = 0))
    layout(preview.width, 0) {
        preview.placeRelative(0, -preview.height)
    }
}

/** Non-focusable filmstrip. Missing frames stay empty; every shown image retains its own time. */
@Composable
internal fun SeekPreviewFilmstrip(
    frame: SeekPreviewFrame?,
    neighbors: List<SeekPreviewFrame>,
    positionMs: Long,
    sourceGeneration: Long,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier.fillMaxWidth().height(126.dp).testTag("seek_preview_filmstrip")) {
        val selectedWidth = 224.dp.coerceAtMost(maxWidth)
        val sideWidth = ((maxWidth - selectedWidth - 32.dp) / 4).coerceIn(0.dp, 136.dp)
        val centerX = (maxWidth - selectedWidth) / 2
        // Fixed geometry while frames arrive avoids any jump in the timeline or selected card.
        if (sideWidth >= 60.dp) {
            for (slot in listOf(-2, -1, 1, 2)) {
                val target = positionMs + slot * 10_000L
                val candidate = neighbors.firstOrNull { it.isValidFor(target, sourceGeneration) }
                val x = if (slot < 0) centerX + (sideWidth + 8.dp) * slot
                    else centerX + selectedWidth + 8.dp + (sideWidth + 8.dp) * (slot - 1)
                ReadySeekPreview(
                    frame = candidate, positionMs = target, sourceGeneration = sourceGeneration,
                    placeholder = true,
                    modifier = Modifier.align(Alignment.CenterStart).offset(x = x).width(sideWidth)
                        .graphicsLayer { alpha = 0.62f },
                )
            }
        }
        ReadySeekPreview(
            frame = frame, positionMs = positionMs, sourceGeneration = sourceGeneration,
            placeholder = true,
            modifier = Modifier.align(Alignment.CenterStart).offset(x = centerX).width(selectedWidth),
        )
    }
}

/** Callers validate source identity; this also rejects stale positions during touch recomposition. */
@Composable
fun ReadySeekPreview(
    frame: SeekPreviewFrame?,
    positionMs: Long,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    sourceGeneration: Long = frame?.sourceGeneration ?: -1L,
    cornerRadius: Dp = 5.dp,
    placeholder: Boolean = false,
) {
    if (!visible) return
    if (frame == null || frame.bitmap.isRecycled || !frame.isDisplayableFor(positionMs, sourceGeneration)) {
        if (placeholder) {
            val shape = RoundedCornerShape(cornerRadius)
            Box(modifier.aspectRatio(16f / 9f).background(Color(0xFF171717), shape)
                .border(1.dp, Color.White.copy(alpha = 0.14f), shape)
                .testTag("seek_preview_pending"))
        }
        return
    }

    // Fade in only. Retaining outgoing content or crossfading would show the previous target.
    val cached = frame.origin == SeekPreviewOrigin.MEMORY || frame.origin == SeekPreviewOrigin.DISK
    val opacity = remember { Animatable(if (cached) 1f else 0f) }
    LaunchedEffect(Unit) { opacity.animateTo(1f, tween(80)) }
    val shape = RoundedCornerShape(cornerRadius)
    Box(
        modifier = modifier
            .testTag("seek_preview_ready")
            .graphicsLayer { alpha = opacity.value }
            .shadow(14.dp, shape, clip = false)
            .aspectRatio(16f / 9f)
            .background(Color.Black, shape)
            .border(1.dp, Color.White.copy(alpha = 0.68f), shape)
            .clip(shape)
    ) {
        Image(
            bitmap = frame.bitmap.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize(),
        )
        if (frame.approximate) {
            // The seek cursor stays exact. Label the actual nearby sample explicitly.
            Text("≈ ${android.text.format.DateUtils.formatElapsedTime(frame.positionMs / 1_000)}",
                color = Color.White, fontSize = 11.sp,
                modifier = Modifier.align(Alignment.BottomEnd).background(Color.Black.copy(alpha = 0.8f))
                    .padding(horizontal = 5.dp, vertical = 2.dp).testTag("seek_preview_approximate"))
        }
    }
}
