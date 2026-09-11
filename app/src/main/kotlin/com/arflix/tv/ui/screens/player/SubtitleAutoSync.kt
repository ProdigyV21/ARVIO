package com.arflix.tv.ui.screens.player

/**
 * A constant timing correction for an auto-matched subtitle: a cue authored at media time `t` is
 * displayed at `t + offsetMs`.
 *
 * Applied live in [AiSubtitleRenderersFactory]'s offset renderer rather than baked into the served
 * file, so a correction needs no MediaItem rebuild.
 *
 * A linear *drift* term (`rate`, for a 25 fps subtitle over 23.976 fps video) used to live here
 * alongside the offset, fitted from reference samples taken at several points in the file. It was
 * removed in Sept 2026: it never once produced a usable fit in testing (it needs three collinear
 * anchors to tell drift from a re-cut, and real files gave two), and measuring it required sampling
 * far ahead of playback — which only the headless second player could do, and that player was
 * itself removed for costing ~260 MB of heap. Correcting drift now would need a trigger that costs
 * nothing when there is no drift, e.g. noticing a verified subtitle drift out later in the file.
 */
@JvmInline
value class SubtitleAutoSync(val offsetUs: Long) {

    val offsetMs: Long get() = offsetUs / 1000L

    val isIdentity: Boolean get() = offsetUs == 0L

    companion object {
        fun ofMs(offsetMs: Long) = SubtitleAutoSync(offsetMs * 1000L)
    }
}
