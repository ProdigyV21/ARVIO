package com.arflix.tv.ui.screens.player.subtitles.bilingual

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * Bilingual subtitles — what the AI-translation subtitle layer shows.
 *
 * Only applies while ARVIO's AI translation is active (an embedded text track is being
 * translated by Gemini/Groq). Ordinary subtitle tracks are rendered by ARVIO's own SubtitleView
 * exactly as upstream does, whatever this mode is.
 */
@Serializable
enum class BilingualDisplayMode {
    /** Source text only (no translation requests are made). */
    @SerialName("original") ORIGINAL_ONLY,
    /** Translation only (falls back to the source text while/if translation is unavailable). */
    @SerialName("translation") TRANSLATION_ONLY,
    /** Source and translation together. Default. */
    @SerialName("bilingual") BILINGUAL,
    /** Hide AI subtitles entirely. */
    @SerialName("off") OFF;

    val needsTranslation: Boolean get() = this == TRANSLATION_ONLY || this == BILINGUAL
}

/**
 * HOW the second language is produced (independent of [BilingualDisplayMode], which decides what
 * is SHOWN). Only used while the bilingual layer is on ([BilingualSubtitleSettings.smartEnabled]).
 */
@Serializable
enum class BilingualGenerationMode {
    /** Two embedded text tracks → merge (no AI); one missing → AI-translate the primary. Default. */
    @SerialName("auto") AUTO,
    /** Only ever merge existing tracks; never call AI, even when a language is missing. */
    @SerialName("merge") MERGE,
    /** Always AI-translate the chosen primary track, even when the target language exists. */
    @SerialName("ai") AI_TRANSLATE;

    val allowsAi: Boolean get() = this != MERGE
    val allowsMerge: Boolean get() = this != AI_TRANSLATE
}

@Serializable
enum class BilingualOrder {
    @SerialName("original_first") ORIGINAL_FIRST,
    @SerialName("translation_first") TRANSLATION_FIRST,
}

@Serializable
enum class SubtitleOutline(val widthDp: Float) {
    @SerialName("none") NONE(0f),
    @SerialName("thin") THIN(1.6f),
    @SerialName("medium") MEDIUM(2.6f),
    @SerialName("thick") THICK(3.8f);
}

/** Style of one language line block. All sizes are in sp / dp so they follow screen density. */
@Serializable
data class BilingualLineStyle(
    val visible: Boolean = true,
    val colorHex: String = "#FFFFFF",
    val sizeSp: Int = 18,
    val bold: Boolean = false,
    /** A [com.arflix.tv.ui.screens.player.SubtitleFontOption] preference value. */
    val font: String = "System",
    val outline: SubtitleOutline = SubtitleOutline.THIN,
    val outlineColorHex: String = "#000000",
    val shadow: Boolean = true,
    val background: Boolean = false,
    /** Extra vertical offset of this block, dp. Positive moves the block up. */
    val offsetDp: Int = 0,
) {
    fun sanitized(): BilingualLineStyle = copy(
        colorHex = BilingualColors.normalizeHex(colorHex) ?: "#FFFFFF",
        outlineColorHex = BilingualColors.normalizeHex(outlineColorHex) ?: "#000000",
        sizeSp = sizeSp.coerceIn(MIN_SIZE_SP, MAX_SIZE_SP),
        offsetDp = offsetDp.coerceIn(-MAX_OFFSET_DP, MAX_OFFSET_DP),
    )

    companion object {
        const val MIN_SIZE_SP = 10
        const val MAX_SIZE_SP = 48
        const val MAX_OFFSET_DP = 120
    }
}

@Serializable
data class BilingualSubtitleSettings(
    val mode: BilingualDisplayMode = BilingualDisplayMode.BILINGUAL,
    val order: BilingualOrder = BilingualOrder.ORIGINAL_FIRST,
    /** Vertical distance between the two language blocks, dp. */
    val gapDp: Int = 4,
    /** Distance of the whole group from the bottom of the video area, percent of its height. */
    val bottomPercent: Int = 6,
    /** Style of the PRIMARY language block (kept as "original" for settings compatibility). */
    val original: BilingualLineStyle = DEFAULT_ORIGINAL,
    /** Style of the SECONDARY language block (AI translation or second embedded track). */
    val translation: BilingualLineStyle = DEFAULT_TRANSLATION,
    /**
     * Smart bilingual mode (opt-in): pick the subtitle source automatically — two embedded text
     * tracks are merged without AI (path A), otherwise the primary is translated by AI (path B).
     * Off = ARVIO's normal subtitle behaviour; the bilingual layer then only appears when the
     * user turns on AI translation.
     */
    val smartEnabled: Boolean = false,
    /** Auto / Merge / AI Translate — see [BilingualGenerationMode]. */
    val generationMode: BilingualGenerationMode = BilingualGenerationMode.AUTO,
    /** "auto" or a BCP 47 tag (e.g. "en", "ja"). */
    val primaryLanguage: String = SubtitleLanguage.AUTO,
    /** BCP 47 tag of the second language, default Simplified Chinese. */
    val secondaryLanguage: String = DEFAULT_SECONDARY_LANGUAGE,
) {
    /** Resolved secondary language (never null). */
    val secondary: SubtitleLanguage
        get() = SubtitleLanguage.parse(secondaryLanguage) ?: SubtitleLanguage.SIMPLIFIED_CHINESE

    /** Explicit primary language, or null for Auto. */
    val primaryExplicit: SubtitleLanguage? get() = SubtitleLanguage.parse(primaryLanguage)

    fun sanitized(): BilingualSubtitleSettings = copy(
        primaryLanguage = SubtitleLanguage.parse(primaryLanguage)?.tag ?: SubtitleLanguage.AUTO,
        secondaryLanguage = SubtitleLanguage.parse(secondaryLanguage)?.tag ?: DEFAULT_SECONDARY_LANGUAGE,
        gapDp = gapDp.coerceIn(0, MAX_GAP_DP),
        bottomPercent = bottomPercent.coerceIn(0, MAX_BOTTOM_PERCENT),
        original = original.sanitized(),
        translation = translation.sanitized(),
    )

    companion object {
        const val DEFAULT_SECONDARY_LANGUAGE = "zh-Hans"
        const val MAX_GAP_DP = 48
        const val MAX_BOTTOM_PERCENT = 60

        val DEFAULT_ORIGINAL = BilingualLineStyle(
            colorHex = "#FFFFFF",
            sizeSp = 17,
            bold = false,
            outline = SubtitleOutline.THIN,
        )
        val DEFAULT_TRANSLATION = BilingualLineStyle(
            colorHex = "#F4D979",
            sizeSp = 21,
            bold = true,
            outline = SubtitleOutline.THIN,
        )
        val DEFAULT = BilingualSubtitleSettings()
    }
}

/** JSON persistence of [BilingualSubtitleSettings]. Tolerant: unknown keys and bad values fall back. */
object BilingualSettingsCodec {
    /** DataStore key name (profile-scoped through ProfileManager). Not part of cloud sync. */
    const val PREF_NAME = "bilingual_subtitle_settings_v1"

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        coerceInputValues = true
        isLenient = true
    }

    fun encode(settings: BilingualSubtitleSettings): String =
        json.encodeToString(BilingualSubtitleSettings.serializer(), settings.sanitized())

    fun decode(raw: String?): BilingualSubtitleSettings {
        if (raw.isNullOrBlank()) return BilingualSubtitleSettings.DEFAULT
        return try {
            json.decodeFromString(BilingualSubtitleSettings.serializer(), raw).sanitized()
        } catch (_: Exception) {
            BilingualSubtitleSettings.DEFAULT
        }
    }
}

object BilingualColors {
    /** Preset palette shown in the settings UI. */
    val PRESETS: List<Pair<String, String>> = listOf(
        "White" to "#FFFFFF",
        "Warm Yellow" to "#F4D979",
        "Yellow" to "#FFE600",
        "Light Gray" to "#D0D0D0",
        "Cyan" to "#7FE3FF",
        "Green" to "#9CF28B",
        "Pink" to "#FFB3D1",
        "Orange" to "#FFB36B",
    )

    private val HEX = Regex("^#?([0-9a-fA-F]{3}|[0-9a-fA-F]{6}|[0-9a-fA-F]{8})$")

    /** Returns "#RRGGBB" / "#AARRGGBB" in upper case, or null when [input] is not a colour. */
    fun normalizeHex(input: String?): String? {
        val trimmed = input?.trim() ?: return null
        val m = HEX.matchEntire(trimmed) ?: return null
        var digits = m.groupValues[1].uppercase()
        if (digits.length == 3) digits = digits.map { "$it$it" }.joinToString("")
        return "#$digits"
    }

    /** ARGB int for a hex colour; [fallback] when it cannot be parsed. */
    fun parseArgb(hex: String?, fallback: Int = 0xFFFFFFFF.toInt()): Int {
        val norm = normalizeHex(hex) ?: return fallback
        val digits = norm.substring(1)
        val value = digits.toLong(16)
        return if (digits.length == 6) (0xFF000000L or value).toInt() else value.toInt()
    }
}
