package com.arflix.tv.data.model

import org.junit.Assert.*
import org.junit.Test

class ChannelLogoIndexTest {
    private fun entry(id: String, country: String, vararg names: String) =
        ChannelLogoEntry(id, country, names.toList(), listOf("https://example.invalid/$id.png"))
    private val index = ChannelLogoIndex(listOf(
        entry("ESPN.us", "US", "ESPN"), entry("ESPN.nl", "NL", "ESPN"),
        entry("BBCOne.uk", "UK", "BBC One", "BBC 1"), entry("Channel4.uk", "UK", "Channel 4"),
        entry("Channel4Plus1.uk", "UK", "Channel 4 +1"), entry("Local.us", "US", "NBC Charlotte"),
    ))

    @Test fun exactEpgIdWinsOverProviderName() {
        assertEquals(listOf("https://example.invalid/ESPN.us.png"), index.candidates(" ESPN.US ", "Sports feed"))
    }
    @Test fun providerQualityAndPackagePrefixesDoNotHideKnownLogos() {
        assertEquals(index.candidates(null, "UK | BBC One"), index.candidates(null, "4K| UK-NOWTV| BBC One FHD"))
        assertTrue(index.candidates(null, "FR-NOWTV| BBC One HD").isEmpty())
    }
    @Test fun namesNeedUniqueIdentityAndCountryCanDisambiguate() {
        assertTrue(index.candidates(null, "ESPN").isEmpty())
        assertEquals(listOf("https://example.invalid/ESPN.nl.png"), index.candidates(null, "NL | ESPN FHD"))
        assertEquals(listOf("https://example.invalid/BBCOne.uk.png"), index.candidates(null, "UK: BBC 1 HD"))
    }
    @Test fun neverDropChannelNumbersTimeShiftsOrRegions() {
        assertEquals(listOf("https://example.invalid/Channel4Plus1.uk.png"), index.candidates(null, "Channel 4 +1 HD"))
        for (name in listOf("Channel 5", "BBC One +1", "NBC", "NBC Boston", "Unknown", "FR | BBC One")) {
            assertTrue(name, index.candidates(null, name).isEmpty())
        }
    }
}
