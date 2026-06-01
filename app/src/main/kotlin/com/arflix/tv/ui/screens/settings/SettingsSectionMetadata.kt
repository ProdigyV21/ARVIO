package com.arflix.tv.ui.screens.settings

internal data class SettingsSectionDefinition(
    val id: String,
    val rows: List<Int>,
)

private val tvGeneralSectionDefinitions = listOf(
    SettingsSectionDefinition(
        id = "language",
        rows = listOf(
            0,
            3,
        ),
    ),
    SettingsSectionDefinition(
        id = "subtitles",
        rows = listOf(
            1,
            2,
            4,
            5,
            6,
            7,
            8,
            9,
        ),
    ),
    SettingsSectionDefinition(
        id = "ai_subtitles",
        rows = listOf(
            28,
            29,
            30,
            31,
            32,
            33,
        ),
    ),
    SettingsSectionDefinition(
        id = "playback",
        rows = listOf(
            10,
            11,
            12,
            13,
            14,
            34,
            16,
            15,
            27,
        ),
    ),
    SettingsSectionDefinition(
        id = "appearance",
        rows = listOf(
            17,
            18,
            20,
            21,
            24,
            23,
            22,
            36,
        ),
    ),
    SettingsSectionDefinition(
        id = "profiles",
        rows = listOf(
            19,
        ),
    ),
    SettingsSectionDefinition(
        id = "network",
        rows = listOf(
            25,
            26,
            35,
        ),
    ),
)

internal fun tvGeneralRowsForSection(section: String): List<Int> {
    return tvGeneralSectionDefinitions.firstOrNull { it.id == section }?.rows.orEmpty()
}