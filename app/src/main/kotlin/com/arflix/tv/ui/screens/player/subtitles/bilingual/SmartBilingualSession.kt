package com.arflix.tv.ui.screens.player.subtitles.bilingual

import com.arflix.tv.data.model.Subtitle

/** Explicit per-video intent. Live audio only suspends it; an explicit Off clears the request. */
data class SmartBilingualRequest(
    val requestedForVideo: Boolean = false,
    val suppressedForVideo: Boolean = false,
) {
    fun start() = SmartBilingualRequest(requestedForVideo = true)
    fun stop() = SmartBilingualRequest(suppressedForVideo = true)

    /**
     * [selectionPending]: a manual remote subtitle is being localized. Re-planning then would
     * cancel the viewer's pick, so the smart mode waits instead of taking the screen back.
     */
    fun shouldRun(
        globalEnabled: Boolean,
        liveAudio: Boolean,
        findingMatch: Boolean = false,
        selectionPending: Boolean = false,
    ): Boolean = (globalEnabled || requestedForVideo) && !suppressedForVideo &&
        !liveAudio && !findingMatch && !selectionPending
}

/** State owned by the regular subtitle mode before smart bilingual temporarily takes over. */
data class SubtitleModeSnapshot(
    val streamUrl: String?,
    val selectedSubtitle: Subtitle?,
    val aiSourceSubtitle: Subtitle?,
    val aiTranslating: Boolean,
    val managerEnabled: Boolean,
    val targetLanguage: String,
    val targetLanguageName: String,
    val aiAvailable: Boolean,
    val manualSelection: Boolean,
    val userPicked: Boolean,
)

/** A re-plan (AI -> merge -> primary only) must never overwrite the pre-smart snapshot. */
class SmartBilingualSession {
    private var previous: SubtitleModeSnapshot? = null
    val isActive: Boolean get() = previous != null

    fun enter(snapshot: SubtitleModeSnapshot) {
        if (previous == null) previous = snapshot
    }

    fun leave(streamUrl: String?, restorePrevious: Boolean = true): SubtitleModeSnapshot? {
        val saved = previous
        previous = null
        return saved?.takeIf { restorePrevious && it.streamUrl == streamUrl }
    }
}

/** Conservative at startup and when switching modes: Merge never authorizes a billable fallback. */
internal fun automaticCalibrationAiAllowed(
    generationMode: BilingualGenerationMode,
    persistedMode: BilingualGenerationMode,
    aiEnabled: Boolean,
    keyReady: Boolean,
    liveAudio: Boolean,
): Boolean = aiEnabled && keyReady && !liveAudio &&
    generationMode != BilingualGenerationMode.MERGE && persistedMode != BilingualGenerationMode.MERGE
