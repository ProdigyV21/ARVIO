package com.arflix.tv.ui.screens.player.subtitles.bilingual

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BilingualLayoutTest {

    private fun place(
        heights: List<Int>, gap: Int = 10, bottom: Int = 50, offsets: List<Int> = heights.map { 0 },
        h: Int = 1000, safeTop: Int = 0, safeBottom: Int = 0,
    ) = BilingualLayout.place(BilingualLayout.Input(h, heights, gap, bottom, offsets, safeTop, safeBottom))

    @Test fun `two blocks stack bottom-up with the gap`() {
        val tops = place(listOf(40, 60))
        // bottom block: 1000-50-60 = 890 ; top block: 890-10-40 = 840
        assertEquals(listOf(840, 890), tops)
    }

    @Test fun `gap change moves only the upper block`() {
        val a = place(listOf(40, 60), gap = 0)
        val b = place(listOf(40, 60), gap = 30)
        assertEquals(a[1], b[1])
        assertEquals(a[0] - 30, b[0])
    }

    @Test fun `group position moves both blocks together`() {
        val a = place(listOf(40, 60), bottom = 50)
        val b = place(listOf(40, 60), bottom = 250)
        assertEquals(listOf(a[0] - 200, a[1] - 200), b)
    }

    @Test fun `independent offsets move a single block`() {
        val base = place(listOf(40, 60))
        val up = place(listOf(40, 60), offsets = listOf(25, 0))
        assertEquals(base[0] - 25, up[0])
        assertEquals(base[1], up[1])
    }

    @Test fun `blocks never overlap even with crossing offsets`() {
        val tops = place(listOf(40, 60), gap = 0, offsets = listOf(-200, 0))
        assertTrue(tops[0] + 40 <= tops[1])
    }

    @Test fun `group is clamped inside the safe area at the bottom`() {
        val tops = place(listOf(40, 60), bottom = 0, offsets = listOf(0, -500), safeBottom = 20)
        assertTrue(tops[1] + 60 <= 1000 - 20)
        assertTrue(tops[0] + 40 <= tops[1])
    }

    @Test fun `group is clamped inside the safe area at the top`() {
        val tops = place(listOf(40, 60), bottom = 990, safeTop = 30)
        assertTrue(tops.minOrNull()!! >= 30)
    }

    @Test fun `long wrapped translation taller than the screen is top aligned`() {
        val tops = place(listOf(300, 900), h = 1000, safeTop = 10)
        assertEquals(10, tops[0])
        assertTrue(tops[0] + 300 <= tops[1])
    }

    @Test fun `single block and empty input`() {
        assertEquals(listOf(1000 - 50 - 70), place(listOf(70)))
        assertTrue(place(emptyList()).isEmpty())
    }

    @Test fun `bottom percent converts to pixels`() {
        assertEquals(60, BilingualLayout.bottomMarginPx(1000, 6))
        assertEquals(0, BilingualLayout.bottomMarginPx(1000, -5))
    }
}
