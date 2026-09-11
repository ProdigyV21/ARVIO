package com.arflix.tv.ui.screens.player.engine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.arflix.tv.ui.screens.player.applySubtitleAppearance
import com.arflix.tv.ui.screens.player.engine.exoplayer.FullViewportSubtitlePlayerView
import com.arflix.tv.ui.screens.player.engine.exoplayer.ExoPlayerEngine

@Composable
fun PlayerEngineSurface(
    engine: PlayerEngine,
    resizeMode: Int,
    subtitleSizePref: String = "Normal",
    subtitleSizePct: Int = 100,
    subtitleColorPref: String = "White",
    subtitleStylePref: String = "Outline",
    subtitleFontPref: String = "System",
    subtitleStylizedPref: Boolean = true,
    subtitleOffsetPref: String = "Bottom",
    subtitleVerticalPct: Int = 2,
    isInPipMode: Boolean = false,
    modifier: Modifier = Modifier
) {
    when (engine) {
        is ExoPlayerEngine -> {
            AndroidView(
                modifier = modifier,
                factory = { ctx ->
                    FullViewportSubtitlePlayerView(ctx).apply {
                        keepScreenOn = true
                        player = engine.exoPlayer
                        useController = false
                        setKeepContentOnPlayerReset(true)
                        this.resizeMode = resizeMode
                        subtitleView?.applySubtitleAppearance(
                            context = ctx,
                            sizePreference = subtitleSizePref,
                            sizePercent = subtitleSizePct,
                            verticalPercent = subtitleVerticalPct,
                            colorPreference = subtitleColorPref,
                            stylePreference = subtitleStylePref,
                            fontPreference = subtitleFontPref,
                            preserveEmbeddedStyles = subtitleStylizedPref,
                            inPictureInPicture = isInPipMode,
                        )
                    }
                },
                update = { playerView ->
                    playerView.keepScreenOn = true
                    playerView.resizeMode = resizeMode
                    playerView.subtitleView?.applySubtitleAppearance(
                        context = playerView.context,
                        sizePreference = subtitleSizePref,
                        sizePercent = subtitleSizePct,
                        verticalPercent = subtitleVerticalPct,
                        colorPreference = subtitleColorPref,
                        stylePreference = subtitleStylePref,
                        fontPreference = subtitleFontPref,
                        preserveEmbeddedStyles = subtitleStylizedPref,
                        inPictureInPicture = isInPipMode,
                    )
                }
            )
        }
        else -> {
            Box(modifier = modifier.fillMaxSize().background(Color.Black))
        }
    }
}
