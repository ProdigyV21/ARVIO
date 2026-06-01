package com.arflix.tv.ui.screens.settings

/**
 * Symbolic row metadata for the TV/general settings sections.
 *
 * The settings screen uses these row keys instead of hard-coded numeric
 * positions, so reordering or inserting rows only requires updating this
 * single definition table.
 */
internal enum class TvGeneralSettingRow {
    CONTENT_LANGUAGE,
    DEFAULT_SUBTITLE,
    SECONDARY_SUBTITLE,
    AUDIO_LANGUAGE,
    SUBTITLE_SIZE,
    SUBTITLE_COLOR,
    SUBTITLE_OFFSET,
    SUBTITLE_STYLE,
    SUBTITLE_STYLIZED,
    FILTER_SUBTITLES_BY_LANGUAGE,
    AUTO_PLAY_NEXT,
    AUTO_PLAY_SINGLE_SOURCE,
    AUTO_PLAY_MIN_QUALITY,
    TRAILER_AUTO_PLAY,
    TRAILER_SOUND_ENABLED,
    FRAME_RATE_MATCHING_MODE,
    QUALITY_FILTERS,
    CARD_LAYOUT_MODE,
    UI_MODE,
    SKIP_PROFILE_SELECTION,
    OLED_BLACK_BACKGROUND,
    CLOCK_FORMAT,
    SHOW_BUDGET,
    SPOILER_BLUR,
    ACCENT_COLOR,
    DNS_PROVIDER,
    SHOW_LOADING_STATS,
    VOLUME_BOOST,
    SUBTITLE_AI_ENABLED,
    SUBTITLE_AI_MODEL,
    SUBTITLE_AI_AUTO_SELECT,
    SUBTITLE_REMOVE_HEARING_IMPAIRED,
    SUBTITLE_AI_API_KEY,
    SUBTITLE_AI_SERVER,
    TRAILER_DELAY,
    CUSTOM_USER_AGENT,
}

internal data class SettingsSectionDefinition(
    val id: String,
    val rows: List<TvGeneralSettingRow>,
)

private val tvGeneralSectionDefinitions = listOf(
    SettingsSectionDefinition(
        id = "language",
        rows = listOf(
            TvGeneralSettingRow.CONTENT_LANGUAGE,
            TvGeneralSettingRow.AUDIO_LANGUAGE,
        ),
    ),
    SettingsSectionDefinition(
        id = "subtitles",
        rows = listOf(
            TvGeneralSettingRow.DEFAULT_SUBTITLE,
            TvGeneralSettingRow.SECONDARY_SUBTITLE,
            TvGeneralSettingRow.SUBTITLE_SIZE,
            TvGeneralSettingRow.SUBTITLE_COLOR,
            TvGeneralSettingRow.SUBTITLE_OFFSET,
            TvGeneralSettingRow.SUBTITLE_STYLE,
            TvGeneralSettingRow.SUBTITLE_STYLIZED,
            TvGeneralSettingRow.FILTER_SUBTITLES_BY_LANGUAGE,
        ),
    ),
    SettingsSectionDefinition(
        id = "ai_subtitles",
        rows = listOf(
            TvGeneralSettingRow.SUBTITLE_AI_ENABLED,
            TvGeneralSettingRow.SUBTITLE_AI_MODEL,
            TvGeneralSettingRow.SUBTITLE_AI_AUTO_SELECT,
            TvGeneralSettingRow.SUBTITLE_REMOVE_HEARING_IMPAIRED,
            TvGeneralSettingRow.SUBTITLE_AI_API_KEY,
            TvGeneralSettingRow.SUBTITLE_AI_SERVER,
        ),
    ),
    SettingsSectionDefinition(
        id = "playback",
        rows = listOf(
            TvGeneralSettingRow.AUTO_PLAY_NEXT,
            TvGeneralSettingRow.AUTO_PLAY_SINGLE_SOURCE,
            TvGeneralSettingRow.AUTO_PLAY_MIN_QUALITY,
            TvGeneralSettingRow.TRAILER_AUTO_PLAY,
            TvGeneralSettingRow.TRAILER_SOUND_ENABLED,
            TvGeneralSettingRow.TRAILER_DELAY,
            TvGeneralSettingRow.QUALITY_FILTERS,
            TvGeneralSettingRow.FRAME_RATE_MATCHING_MODE,
            TvGeneralSettingRow.VOLUME_BOOST,
        ),
    ),
    SettingsSectionDefinition(
        id = "appearance",
        rows = listOf(
            TvGeneralSettingRow.CARD_LAYOUT_MODE,
            TvGeneralSettingRow.UI_MODE,
            TvGeneralSettingRow.OLED_BLACK_BACKGROUND,
            TvGeneralSettingRow.CLOCK_FORMAT,
            TvGeneralSettingRow.ACCENT_COLOR,
            TvGeneralSettingRow.SPOILER_BLUR,
            TvGeneralSettingRow.SHOW_BUDGET,
        ),
    ),
    SettingsSectionDefinition(
        id = "profiles",
        rows = listOf(
            TvGeneralSettingRow.SKIP_PROFILE_SELECTION,
        ),
    ),
    SettingsSectionDefinition(
        id = "network",
        rows = listOf(
            TvGeneralSettingRow.DNS_PROVIDER,
            TvGeneralSettingRow.SHOW_LOADING_STATS,
            TvGeneralSettingRow.CUSTOM_USER_AGENT,
        ),
    ),
)

internal fun tvGeneralRowsForSection(section: String): List<TvGeneralSettingRow> {
    return tvGeneralSectionDefinitions.firstOrNull { it.id == section }?.rows.orEmpty()
}