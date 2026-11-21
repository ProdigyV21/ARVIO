package com.arflix.tv.ui.screens.player.subtitles.bilingual

import com.arflix.tv.ui.screens.player.SubtitleFontOption
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

/**
 * Profile-scoped pre-bilingual preferences. Null means the preference was never saved: a new
 * profile must retain the bilingual defaults rather than inherit the old 24sp/white defaults.
 * Numeric size/position, when present, take precedence over their older named presets.
 * No Android/Compose dependencies: migration and live appearance use the same JVM-tested rules.
 */
@Serializable
data class LegacySubtitleAppearance(
    val size: String? = null,
    val sizePercent: Int? = null,
    val color: String? = null,
    val position: String? = null,
    val verticalPercent: Int? = null,
    val style: String? = null,
    val font: String? = null,
    val stylized: Boolean? = null,
) {
    val resolvedSizePercent: Int
        get() = (sizePercent ?: when (size?.lowercase()) {
            "small" -> 80
            "large" -> 120
            "extra large" -> 140
            else -> 100
        }).coerceIn(50, 250)

    val resolvedVerticalPercent: Int
        get() = (verticalPercent ?: when (position?.lowercase()) {
            "low" -> 8
            "medium" -> 15
            "high" -> 25
            "top" -> 88
            else -> 2
        }).coerceIn(1, 88)

    private val resolvedColor: String
        get() = when (color?.lowercase()) {
            "yellow" -> "#FFFF00"
            "green" -> "#00FF00"
            "cyan" -> "#00FFFF"
            "red" -> "#FF0000"
            else -> BilingualColors.normalizeHex(color) ?: "#FFFFFF"
        }

    private val resolvedFont: String
        get() = SubtitleFontOption.fromPreference(font).preferenceValue

    private val resolvedStyle: String get() = style?.lowercase() ?: "bold"

    /**
     * Establish a durable anchor once. Existing bilingual JSON is authoritative, including
     * intentional choices equal to defaults; it is never guessed from value comparisons.
     * With no bilingual JSON, import only legacy fields actually present on disk.
     */
    fun initialize(settings: BilingualSubtitleSettings, hasBilingualSettings: Boolean): BilingualSubtitleSettings {
        if (settings.legacyAppearance != null) return settings
        val migrated = if (hasBilingualSettings) settings else settings.copy(
            bottomPercent = if (position != null || verticalPercent != null) resolvedVerticalPercent else settings.bottomPercent,
            original = migrateLine(settings.original),
            translation = migrateLine(settings.translation),
        )
        return migrated.copy(legacyAppearance = this).sanitized()
    }

    private fun migrateLine(line: BilingualLineStyle): BilingualLineStyle {
        var result = line
        if (size != null || sizePercent != null) {
            result = result.copy(sizeSp = (24f * resolvedSizePercent / 100f).roundToInt())
        }
        if (color != null) result = result.copy(colorHex = resolvedColor)
        if (style != null) result = applyStyle(result)
        if (font != null) result = result.copy(font = resolvedFont)
        return result
    }

    /**
     * Resolve against the saved anchor, never against last frame's result. The old size has
     * already been migrated into sizeSp, so only the ratio to the captured legacy size applies.
     * Later global controls affect both languages; per-language differences/offsets survive.
     * At the captured baseline, independent bilingual styles win; returning global controls to
     * that baseline restores them. A bilingual edit/reset captures the currently resolved result
     * and a new anchor (see the store), without applying any legacy value twice.
     */
    fun resolve(settings: BilingualSubtitleSettings): BilingualSubtitleSettings {
        val baseline = settings.legacyAppearance ?: return settings
        val ratio = resolvedSizePercent.toFloat() / baseline.resolvedSizePercent
        val positionDelta = resolvedVerticalPercent - baseline.resolvedVerticalPercent
        fun resolveLine(line: BilingualLineStyle): BilingualLineStyle {
            var result = line.copy(sizeSp = (line.sizeSp * ratio).roundToInt())
            if (resolvedColor != baseline.resolvedColor) result = result.copy(colorHex = resolvedColor)
            if (resolvedFont != baseline.resolvedFont) result = result.copy(font = resolvedFont)
            if (resolvedStyle != baseline.resolvedStyle) result = applyStyle(result)
            return result
        }
        // subtitle_stylized controls authored Cue spans/positions in SubtitleView. The AI bus
        // contains plain strings, not Cue styling. Keep it in the snapshot, but do not let it
        // erase explicit bilingual styles or pretend that authored styling can be restored.
        return settings.copy(
            bottomPercent = settings.bottomPercent + positionDelta,
            original = resolveLine(settings.original),
            translation = resolveLine(settings.translation),
            legacyAppearance = this,
        ).sanitized()
    }

    private fun applyStyle(line: BilingualLineStyle): BilingualLineStyle = line.copy(
        bold = resolvedStyle != "normal",
        outline = if (resolvedStyle == "normal" || resolvedStyle == "background") SubtitleOutline.NONE else SubtitleOutline.THIN,
        outlineColorHex = "#000000",
        shadow = false,
        background = resolvedStyle == "background",
    )
}
