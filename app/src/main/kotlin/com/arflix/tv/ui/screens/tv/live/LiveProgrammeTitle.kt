package com.arflix.tv.ui.screens.tv.live

/** Display-only fallback: a channel name must never become a synthetic EPG programme. */
internal fun liveProgrammeTitle(
    programmeTitle: String?,
    channelName: String?,
    noProgrammeData: String,
): String = programmeTitle?.takeIf { it.isNotBlank() }
    ?: channelName?.takeIf { it.isNotBlank() }
    ?: noProgrammeData
