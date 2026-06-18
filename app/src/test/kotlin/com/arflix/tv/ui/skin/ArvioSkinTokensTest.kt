package com.arflix.tv.ui.skin

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ArvioSkinTokensTest {

    @Test
    fun `default skin uses compact modern card radius`() {
        val tokens = ArvioSkinTokens.defaults()

        assertEquals(8.dp, tokens.radius.md)
    }

    @Test
    fun `default skin keeps visible focus glow for TV navigation`() {
        val tokens = ArvioSkinTokens.defaults()

        assertTrue(tokens.focus.glowWidth > 0.dp)
        assertTrue(tokens.focus.glowAlpha >= 0.18f)
        assertTrue(tokens.focus.outlineWidth <= 2.5.dp)
    }

    @Test
    fun `default skin is not purely monochrome`() {
        val tokens = ArvioSkinTokens.defaults()

        assertTrue(colorDistance(tokens.colors.accent, tokens.colors.textPrimary) > 0.12f)
        assertTrue(colorDistance(tokens.colors.surface, tokens.colors.surfaceRaised) > 0.05f)
        assertTrue(colorDistance(tokens.colors.tealAccent, tokens.colors.accent) > 0.18f)
    }

    private fun colorDistance(first: Color, second: Color): Float {
        val red = first.red - second.red
        val green = first.green - second.green
        val blue = first.blue - second.blue
        return kotlin.math.sqrt(red * red + green * green + blue * blue)
    }
}
