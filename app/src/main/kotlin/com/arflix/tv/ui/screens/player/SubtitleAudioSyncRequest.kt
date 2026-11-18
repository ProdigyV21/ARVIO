package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.Subtitle

/** Ownership of an explicit request, including while its subtitle is still downloading. */
internal data class SubtitleAudioSyncRequest(
    val generation: Long,
    val streamUrl: String,
    val subtitleKey: String,
) {
    fun isCurrent(
        generation: Long,
        streamUrl: String?,
        selectedSubtitle: Subtitle?,
        lockedSubtitleKey: String?,
        isAiTranslating: Boolean,
        isLiveAudioTranslating: Boolean,
    ): Boolean = this.generation == generation && this.streamUrl == streamUrl &&
        selectedSubtitle != null && !selectedSubtitle.isEmbedded && !selectedSubtitle.isBitmap &&
        SubtitleSelectionPolicy.key(selectedSubtitle) == subtitleKey && lockedSubtitleKey == subtitleKey &&
        !isAiTranslating && !isLiveAudioTranslating
}
