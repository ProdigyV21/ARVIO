package com.arflix.tv.ui.screens.player

import androidx.media3.common.text.Cue
import com.arflix.tv.ui.screens.player.engine.exoplayer.requiresVideoFrameSubtitleViewport
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FullViewportSubtitlePlayerViewTest {

    @Test
    fun plainTextCueUsesFullPlayerViewport() {
        val cue = Cue.Builder()
            .setText("Plain subtitle")
            .build()

        assertThat(
            listOf(cue).requiresVideoFrameSubtitleViewport(
                preserveAuthoredTextPositioning = true
            )
        ).isFalse()
    }

    @Test
    fun decoderDefaultWebVttCueUsesFullPlayerViewport() {
        val cue = Cue.Builder()
            .setText("WebVTT subtitle")
            .setPosition(0.5f)
            .setPositionAnchor(Cue.ANCHOR_TYPE_MIDDLE)
            .setSize(1f)
            .build()

        assertThat(
            listOf(cue).requiresVideoFrameSubtitleViewport(
                preserveAuthoredTextPositioning = true
            )
        ).isFalse()
    }

    @Test
    fun authoredWebVttPositionUsesVideoFrameWhenStylesArePreserved() {
        val cue = Cue.Builder()
            .setText("Placed WebVTT subtitle")
            .setPosition(0.2f)
            .setPositionAnchor(Cue.ANCHOR_TYPE_START)
            .build()

        assertThat(
            listOf(cue).requiresVideoFrameSubtitleViewport(
                preserveAuthoredTextPositioning = true
            )
        ).isTrue()
        assertThat(
            listOf(cue).requiresVideoFrameSubtitleViewport(
                preserveAuthoredTextPositioning = false
            )
        ).isFalse()
    }

    @Test
    fun defaultBottomCenterAssCueUsesFullPlayerViewport() {
        val cue = Cue.Builder()
            .setText("ASS subtitle")
            .setLine(0.95f, Cue.LINE_TYPE_FRACTION)
            .setLineAnchor(Cue.ANCHOR_TYPE_END)
            .setPosition(0.5f)
            .setPositionAnchor(Cue.ANCHOR_TYPE_MIDDLE)
            .build()

        assertThat(
            listOf(cue).requiresVideoFrameSubtitleViewport(
                preserveAuthoredTextPositioning = true
            )
        ).isFalse()
    }

    @Test
    fun authoredAssPositionUsesVideoFrameWhenStylesArePreserved() {
        val cue = Cue.Builder()
            .setText("Placed subtitle")
            .setLine(0.72f, Cue.LINE_TYPE_FRACTION)
            .setLineAnchor(Cue.ANCHOR_TYPE_END)
            .setPosition(0.31f)
            .setPositionAnchor(Cue.ANCHOR_TYPE_MIDDLE)
            .build()

        assertThat(
            listOf(cue).requiresVideoFrameSubtitleViewport(
                preserveAuthoredTextPositioning = true
            )
        ).isTrue()
        assertThat(
            listOf(cue).requiresVideoFrameSubtitleViewport(
                preserveAuthoredTextPositioning = false
            )
        ).isFalse()
    }

}
