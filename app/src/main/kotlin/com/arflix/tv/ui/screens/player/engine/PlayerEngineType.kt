package com.arflix.tv.ui.screens.player.engine

enum class PlayerEngineType(val displayName: String) {
    EXOPLAYER("ExoPlayer"),
    MPV("MPV"),
    VLC("VLC");

    companion object {
        fun fromString(name: String?): PlayerEngineType {
            return entries.firstOrNull { it.name.equals(name, ignoreCase = true) } ?: EXOPLAYER
        }
    }
}
