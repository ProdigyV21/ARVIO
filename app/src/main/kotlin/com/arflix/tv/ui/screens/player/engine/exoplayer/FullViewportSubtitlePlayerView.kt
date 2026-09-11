package com.arflix.tv.ui.screens.player.engine.exoplayer

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.media3.common.text.Cue
import androidx.media3.ui.PlayerView
import androidx.media3.ui.R as Media3UiR
import androidx.media3.ui.SubtitleView
import kotlin.math.abs

/**
 * Media3 places its subtitle layer inside the aspect-ratio content frame by default.
 * Moving it to PlayerView lets ordinary captions use letterbox space, while authored
 * bitmap/positioned cues can still be constrained to the measured video frame.
 */
class FullViewportSubtitlePlayerView(
    context: Context
) : PlayerView(context) {

    private val contentFrame: View? = findViewById(Media3UiR.id.exo_content_frame)
    private val subtitleLayer: SubtitleView? = subtitleView
    private var useVideoFrame = false

    init {
        subtitleLayer?.let { layer ->
            (layer.parent as? ViewGroup)?.removeView(layer)
            addView(
                layer,
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    Gravity.CENTER
                )
            )
        }
        contentFrame?.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            if (useVideoFrame) applySubtitleViewport()
        }
    }

    fun setUseVideoFrameForSubtitles(enabled: Boolean) {
        if (useVideoFrame == enabled) return
        useVideoFrame = enabled
        applySubtitleViewport()
    }

    internal fun isSubtitleLayerAttachedToFullViewport(): Boolean =
        subtitleLayer?.parent === this

    private fun applySubtitleViewport() {
        val layer = subtitleLayer ?: return
        val frame = contentFrame
        val width: Int
        val height: Int

        if (useVideoFrame) {
            val frameWidth = frame?.width ?: 0
            val frameHeight = frame?.height ?: 0
            if (frameWidth <= 0 || frameHeight <= 0) return
            width = frameWidth
            height = frameHeight
        } else {
            width = FrameLayout.LayoutParams.MATCH_PARENT
            height = FrameLayout.LayoutParams.MATCH_PARENT
        }

        val current = layer.layoutParams as? FrameLayout.LayoutParams
        if (
            current?.width == width &&
            current.height == height &&
            current.gravity == Gravity.CENTER &&
            current.leftMargin == 0 &&
            current.topMargin == 0 &&
            current.rightMargin == 0 &&
            current.bottomMargin == 0
        ) {
            return
        }

        layer.layoutParams = FrameLayout.LayoutParams(width, height, Gravity.CENTER)
    }
}

fun List<Cue>.requiresVideoFrameSubtitleViewport(
    preserveAuthoredTextPositioning: Boolean
): Boolean = any { cue ->
    cue.bitmap != null ||
        cue.verticalType != Cue.TYPE_UNSET ||
        (preserveAuthoredTextPositioning && cue.hasNonDefaultAuthoredPosition())
}

private fun Cue.hasNonDefaultAuthoredPosition(): Boolean {
    if (line == Cue.DIMEN_UNSET) {
        return position != Cue.DIMEN_UNSET &&
            (positionAnchor != Cue.ANCHOR_TYPE_MIDDLE || !position.approximatelyEquals(0.5f))
    }
    if (lineType != Cue.LINE_TYPE_FRACTION) return true

    val defaultLine = defaultFractionForAnchor(lineAnchor)
    val defaultPosition = defaultFractionForAnchor(positionAnchor)
    return defaultLine == null ||
        !line.approximatelyEquals(defaultLine) ||
        (position != Cue.DIMEN_UNSET &&
            (defaultPosition == null || !position.approximatelyEquals(defaultPosition)))
}

private fun defaultFractionForAnchor(anchor: Int): Float? = when (anchor) {
    Cue.ANCHOR_TYPE_START -> 0.05f
    Cue.ANCHOR_TYPE_MIDDLE -> 0.5f
    Cue.ANCHOR_TYPE_END -> 0.95f
    else -> null
}

private fun Float.approximatelyEquals(other: Float): Boolean =
    abs(this - other) < 0.0001f
