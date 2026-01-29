package com.arflix.tv.ui.skin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalArvioSkinTokens = staticCompositionLocalOf { ArvioSkinTokens.defaults() }

@Composable
fun ProvideArvioSkin(
    tokens: ArvioSkinTokens = ArvioSkinTokens.defaults(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalArvioSkinTokens provides tokens,
        content = content,
    )
}

object ArvioSkin {
    val tokens: ArvioSkinTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalArvioSkinTokens.current

    val colors: ArvioColorTokens
        @Composable
        @ReadOnlyComposable
        get() = tokens.colors

    val spacing: ArvioSpacingTokens
        @Composable
        @ReadOnlyComposable
        get() = tokens.spacing

    val radius: ArvioRadiusTokens
        @Composable
        @ReadOnlyComposable
        get() = tokens.radius

    val typography: ArvioTypographyTokens
        @Composable
        @ReadOnlyComposable
        get() = tokens.typography

    val motion: ArvioMotionTokens
        @Composable
        @ReadOnlyComposable
        get() = tokens.motion

    val focus: ArvioFocusTokens
        @Composable
        @ReadOnlyComposable
        get() = tokens.focus
}

