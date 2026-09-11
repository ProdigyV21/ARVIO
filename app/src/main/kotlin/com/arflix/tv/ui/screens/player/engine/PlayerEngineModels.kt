package com.arflix.tv.ui.screens.player.engine

import androidx.compose.runtime.Immutable

enum class ResizeMode(val label: String) {
    AUTO("Auto"),
    FIT("Fit to Screen"),
    STRETCH("Stretch"),
    CROP("Crop");

    companion object {
        fun fromString(str: String?): ResizeMode {
            if (str.isNullOrBlank()) return AUTO
            return when (str.trim().lowercase()) {
                "auto" -> AUTO
                "fit", "fit to screen" -> FIT
                "fill", "stretch" -> STRETCH
                "zoom", "crop" -> CROP
                else -> entries.firstOrNull { it.name.equals(str, ignoreCase = true) || it.label.equals(str, ignoreCase = true) } ?: AUTO
            }
        }
    }
}

enum class PlayerPlaybackState {
    IDLE,
    BUFFERING,
    READY,
    ENDED
}

@Immutable
data class AudioTrackItem(
    val id: String,
    val index: Int,
    val label: String? = null,
    val language: String? = null,
    val isDefault: Boolean = false,
    val mimeType: String? = null,
    val channelCount: Int = 0,
    val sampleRate: Int = 0
) {
    fun displayLabel(): String {
        return label?.takeIf { it.isNotBlank() }
            ?: language?.takeIf { it.isNotBlank() }
            ?: "Audio ${index + 1}"
    }
}

@Immutable
data class SubtitleTrackItem(
    val id: String,
    val label: String? = null,
    val language: String? = null,
    val isDefault: Boolean = false,
    val isExternal: Boolean = false
) {
    fun displayLabel(): String {
        return label?.takeIf { it.isNotBlank() }
            ?: language?.takeIf { it.isNotBlank() }
            ?: "Track"
    }
}

@Immutable
data class PlayerEngineState(
    val currentPositionMs: Long = 0L,
    val durationMs: Long = 0L,
    val bufferedPositionMs: Long = 0L,
    val isPlaying: Boolean = false,
    val isBuffering: Boolean = false,
    val hasPlaybackStarted: Boolean = false,
    val playbackSpeed: Float = 1.0f,
    val resizeMode: ResizeMode = ResizeMode.AUTO,
    val audioTracks: List<AudioTrackItem> = emptyList(),
    val selectedAudioIndex: Int = 0,
    val subtitleTracks: List<SubtitleTrackItem> = emptyList(),
    val selectedSubtitleTrack: SubtitleTrackItem? = null,
    val volume: Float = 1.0f,
    val isMuted: Boolean = false,
    val audioDelayMs: Long = 0L,
    val errorMessage: String? = null
)
