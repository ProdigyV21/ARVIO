package com.arflix.tv.ui.screens.vod

import org.junit.Assert.assertEquals
import org.junit.Test

class CleanIptvTitleTest {

    @Test
    fun `strips provider language prefixes`() {
        assertEquals("The Matrix", cleanIptvTitle("EN - The Matrix"))
        assertEquals("Fauda", cleanIptvTitle("|HE| Fauda"))
        assertEquals("Dune", cleanIptvTitle("[EN] Dune"))
    }

    @Test
    fun `strips trailing year and quality tags`() {
        assertEquals("Inception", cleanIptvTitle("Inception (2010)"))
        assertEquals("Inception", cleanIptvTitle("Inception (2010) [4K]"))
        assertEquals("Oppenheimer", cleanIptvTitle("EN - Oppenheimer 2023 FHD"))
    }

    @Test
    fun `keeps titles that contain numbers`() {
        assertEquals("Blade Runner 2049", cleanIptvTitle("Blade Runner 2049 (2017)"))
        assertEquals("Ocean's Eleven", cleanIptvTitle("Ocean's Eleven"))
    }
}
