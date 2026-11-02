package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.screens.player.subtitles.RETIMED_SUBTITLE_ID_SUFFIX

/** Pure identity/source rules shared by manual selection, track updates, and AI activation. */
internal object SubtitleSelectionPolicy {
    fun key(subtitle: Subtitle): String =
        "${subtitle.provider}|${subtitle.id.removeSuffix(RETIMED_SUBTITLE_ID_SUFFIX)}"

    fun resolveSelected(selected: Subtitle?, tracks: List<Subtitle>): Subtitle? {
        selected ?: return null
        // Never replace a decoded/retimed local copy with its remote menu entry.
        if (!selected.isEmbedded && selected.url.startsWith("file:")) return selected
        return tracks.firstOrNull { it.provider == selected.provider && it.id == selected.id }
            ?: tracks.firstOrNull {
                it.provider == selected.provider && selected.url.isNotBlank() && it.url == selected.url
            }
            ?: if (selected.isEmbedded) tracks.firstOrNull {
                it.isEmbedded && it.provider == selected.provider &&
                    it.lang == selected.lang && it.label == selected.label
            } ?: selected else selected
    }

    fun aiSource(
        subtitles: List<Subtitle>,
        targetLanguage: String,
        untranslatableKeys: Set<String>,
        normalizeLanguage: (String) -> String
    ): Subtitle? {
        // Android containers still commonly expose the legacy two-letter Hebrew tag.
        fun language(value: String) = normalizeLanguage(value).let { if (it == "iw") "he" else it }
        // PlayerScreen writes "und" for an untagged container track. It is not a language: the
        // label is the only signal there, so use it instead of treating "und" as a real tag.
        fun Subtitle.languageTag(): String {
            val tag = lang.trim()
            return if (tag.isBlank() || tag.equals("und", true) || tag.equals("unknown", true)) label else tag
        }
        val target = language(targetLanguage)
        val usable = subtitles.filter {
            it.isEmbedded && !it.isBitmap && !it.isForced &&
                !it.label.contains("forced", ignoreCase = true) && key(it) !in untranslatableKeys &&
                (target.isBlank() || language(it.languageTag()) != target)
        }
        fun List<Subtitle>.best(): Subtitle? =
            firstOrNull { !it.label.contains("SDH", ignoreCase = true) && !it.label.contains("CC", ignoreCase = true) }
                ?: firstOrNull()
        return usable.filter { language(it.languageTag()) == "en" }.best()
            ?: usable.filter { it.languageTag().isNotBlank() }.best()
            ?: usable.best()
    }
}
