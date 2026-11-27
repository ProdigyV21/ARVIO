package com.arflix.tv.ui.screens.player

/**
 * The player's fine-grained subtitle size/position (percent) and the coarse Settings value
 * ("Small", "Bottom", ...) are stored side by side. Settings and cloud sync only change the
 * coarse value, so a percent saved earlier from the player must not override it: the percent
 * is used only while it still falls in the bucket the coarse value names.
 */
internal object SubtitlePlacementPrefs {
    fun sizeBucket(pct: Int): String = when {
        pct <= 80 -> "small"
        pct >= 130 -> "extra large"
        pct >= 115 -> "large"
        else -> "medium"
    }

    fun defaultSizePct(size: String): Int = when (size.lowercase()) {
        "small" -> 80
        "large" -> 120
        "extra large" -> 140
        else -> 100
    }

    fun resolveSizePct(size: String, storedPct: Int?): Int {
        val normalized = size.lowercase().takeIf { it in setOf("small", "medium", "large", "extra large") } ?: "medium"
        return storedPct?.takeIf { sizeBucket(it) == normalized } ?: defaultSizePct(normalized)
    }

    fun offsetBucket(pct: Int): String = when {
        pct >= 60 -> "top"
        pct >= 25 -> "high"
        pct >= 12 -> "medium"
        pct >= 5 -> "low"
        else -> "bottom"
    }

    fun defaultVerticalPct(offset: String): Int = when (offset.lowercase()) {
        "low" -> 8
        "medium" -> 15
        "high" -> 25
        "top" -> 60
        else -> 2
    }

    fun resolveVerticalPct(offset: String, storedPct: Int?): Int {
        val normalized = offset.lowercase().takeIf { it in setOf("bottom", "low", "medium", "high", "top") } ?: "bottom"
        return storedPct?.takeIf { offsetBucket(it) == normalized } ?: defaultVerticalPct(normalized)
    }
}
