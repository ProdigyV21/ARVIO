package com.arflix.tv.ui.screens.player.preview

import kotlin.math.abs

internal enum class SeekSurface { Quick, Controls, Touch }
internal enum class SeekPhase { Idle, QuickSkip, Browsing, Exiting }

/** The displayed target survives both committing playback and the overlay's exit animation. */
internal data class SeekInteraction(
    val surface: SeekSurface = SeekSurface.Quick,
    val phase: SeekPhase = SeekPhase.Idle,
    val originMs: Long = 0L,
    val targetMs: Long = 0L,
    val resumeAfterBrowse: Boolean = false,
    val lastInputMs: Long = 0L,
    val previewSession: Boolean = false,
) {
    val browsing: Boolean get() = phase == SeekPhase.Browsing
    val quickVisible: Boolean
        get() = surface == SeekSurface.Quick && (phase == SeekPhase.QuickSkip || phase == SeekPhase.Browsing)

    fun autoCommitDelayMs(nowMs: Long): Long? =
        if (browsing && surface != SeekSurface.Touch) {
            (2_000L - (nowMs - lastInputMs).coerceAtLeast(0L)).coerceAtLeast(0L)
        } else null

    fun step(
        requestedSurface: SeekSurface,
        deltaMs: Long,
        playerPositionMs: Long,
        durationMs: Long,
        isPlaying: Boolean,
        nowMs: Long,
    ): SeekInteraction {
        val continuing = browsing || (surface == requestedSurface &&
            phase == SeekPhase.QuickSkip && nowMs - lastInputMs in 0L..2_200L)
        val base = if (continuing) copy(surface = requestedSurface) else SeekInteraction(
            surface = requestedSurface,
            originMs = playerPositionMs.coerceIn(0L, durationMs.coerceAtLeast(0L)),
            targetMs = playerPositionMs.coerceIn(0L, durationMs.coerceAtLeast(0L)),
            resumeAfterBrowse = isPlaying,
        )
        val target = (base.targetMs + deltaMs).coerceIn(0L, durationMs.coerceAtLeast(0L))
        val browse = base.browsing || requestedSurface != SeekSurface.Quick || abs(target - base.originMs) >= 20_000L
        return base.copy(
            targetMs = target,
            phase = if (browse) SeekPhase.Browsing else SeekPhase.QuickSkip,
            previewSession = browse,
            lastInputMs = nowMs,
        )
    }

    fun dragTo(positionMs: Long, playerPositionMs: Long, durationMs: Long, isPlaying: Boolean): SeekInteraction {
        val continuing = browsing
        return copy(
            surface = SeekSurface.Touch,
            phase = SeekPhase.Browsing,
            previewSession = true,
            originMs = if (continuing) originMs else playerPositionMs,
            targetMs = positionMs.coerceIn(0L, durationMs.coerceAtLeast(0L)),
            resumeAfterBrowse = if (continuing) resumeAfterBrowse else isPlaying,
        )
    }

    fun finish(): SeekInteraction =
        if (phase == SeekPhase.QuickSkip || browsing) copy(phase = SeekPhase.Exiting) else this
    fun afterExit(): SeekInteraction = copy(phase = SeekPhase.Idle)
}
