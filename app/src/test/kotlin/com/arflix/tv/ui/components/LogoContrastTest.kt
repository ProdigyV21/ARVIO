package com.arflix.tv.ui.components

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LogoContrastTest {
    private fun argb(a: Int, r: Int, g: Int, b: Int) = (a shl 24) or (r shl 16) or (g shl 8) or b

    @Test fun blackLetteringIsDark() {
        val pixels = IntArray(200) { if (it % 2 == 0) argb(255, 12, 10, 14) else argb(0, 0, 0, 0) }
        assertThat(logoPixelsAreDark(pixels)).isTrue()
    }

    @Test fun whiteOrColouredLogosAreNotDark() {
        assertThat(logoPixelsAreDark(IntArray(100) { argb(255, 240, 240, 240) })).isFalse()
        assertThat(logoPixelsAreDark(IntArray(100) { argb(255, 220, 30, 30) })).isFalse()
    }

    @Test fun transparentOrTinyImagesAreLeftAlone() {
        assertThat(logoPixelsAreDark(IntArray(100) { 0 })).isFalse()
        assertThat(logoPixelsAreDark(IntArray(4) { argb(255, 0, 0, 0) })).isFalse()
    }
}
