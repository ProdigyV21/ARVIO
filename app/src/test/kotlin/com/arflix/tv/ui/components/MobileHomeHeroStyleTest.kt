package com.arflix.tv.ui.components

import org.junit.Assert.*
import org.junit.Test

class MobileHomeHeroStyleTest {
    @Test fun unknownPreferenceUsesImmersiveWithoutChangingPosterChoice() {
        assertEquals(MobileHomeHeroStyle.IMMERSIVE, parseMobileHeroStyle(null))
        assertEquals(MobileHomeHeroStyle.IMMERSIVE, parseMobileHeroStyle("invalid"))
        assertEquals(MobileHomeHeroStyle.POSTER, parseMobileHeroStyle("POSTER"))
    }

    @Test fun preferenceIsIsolatedPerProfile() {
        assertNotEquals(mobileHeroStyleKey("one"), mobileHeroStyleKey("two"))
        assertEquals("profile_one_mobile_home_hero_style", mobileHeroStyleKey("one").name)
    }
}
