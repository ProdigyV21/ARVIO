package com.arflix.tv.ui.screens.player.engine

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.arflix.tv.ui.screens.player.engine.exoplayer.ExoPlayerEngine
import kotlinx.coroutines.CoroutineScope

object PlayerEngineFactory {
    fun createEngine(
        type: PlayerEngineType,
        context: Context,
        exoPlayer: ExoPlayer,
        scope: CoroutineScope
    ): PlayerEngine {
        return when (type) {
            PlayerEngineType.EXOPLAYER -> ExoPlayerEngine(exoPlayer, scope)
            PlayerEngineType.MPV -> {
                // Fallback to ExoPlayer until MPV native bindings are linked
                ExoPlayerEngine(exoPlayer, scope)
            }
            PlayerEngineType.VLC -> {
                // Fallback to ExoPlayer until VLC native bindings are linked
                ExoPlayerEngine(exoPlayer, scope)
            }
        }
    }
}
