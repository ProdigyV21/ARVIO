package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.api.StremioMetaPreview
import com.arflix.tv.data.model.*
import org.junit.Assert.*
import org.junit.Test

class SportsArtworkTest {
    private val meta = StremioMetaPreview(id = "event:1", name = "Barcelona vs Feyenoord",
        background = "https://example.com/event.webp", poster = "https://example.com/event_UTC.webp", genres = listOf("Football"))
    private val event = SportsGuideEvent("epg:1", "LIVE: Football: Barcelona vs. Feyenoord", GuideSport.FOOTBALL,
        IptvProgram("fixture", startUtcMillis = 1, endUtcMillis = 2), emptyList())

    @Test fun backgroundWinsOverTimezoneStampedPoster() {
        assertEquals(meta.background, meta.toSportsEventArtwork()!!.background)
        assertNull(meta.copy(background = null).toSportsEventArtwork())
        assertNull(meta.copy(background = meta.poster).toSportsEventArtwork())
    }
    @Test fun channelRecordingsAndNonNetworkImagesAreRejected() {
        assertNull(meta.copy(id = "leaf:channel").toSportsEventArtwork())
        assertNull(meta.copy(background = "file:///private").toSportsEventArtwork())
        assertNull(meta.copy(background = "not a URL").toSportsEventArtwork())
    }
    @Test fun normalizedExactMatchPreservesScheduleAndChannels() {
        val actual = attachSportsArtwork(listOf(event), listOf(meta.toSportsEventArtwork()!!)).single()
        assertEquals(meta.background, actual.artwork)
        assertSame(event.programme, actual.programme)
        assertSame(event.channels, actual.channels)
        assertEquals(sportsArtworkKey("São Paulo versus Feyenoord"), sportsArtworkKey("Sao Paulo vs. Feyenoord"))
    }
    @Test fun differentOpponentOrSportMustNeverBorrowArtwork() {
        val artwork = listOf(meta.toSportsEventArtwork()!!)
        assertNull(attachSportsArtwork(listOf(event.copy(title = "Barcelona vs Madrid")), artwork).single().artwork)
        assertNull(attachSportsArtwork(listOf(event.copy(sport = GuideSport.BASKETBALL)), artwork).single().artwork)
    }
    @Test fun artworkRequiresCompatibleEventDateWhenAddonProvidesIt() {
        val dated = meta.copy(released = "2026-09-09T18:00:00Z").toSportsEventArtwork()!!
        assertNull(attachSportsArtwork(listOf(event), listOf(dated)).single().artwork)
        val sameDay = event.copy(programme = event.programme.copy(startUtcMillis = dated.startsAt!!, endUtcMillis = dated.startsAt + 60_000))
        assertEquals(meta.background, attachSportsArtwork(listOf(sameDay), listOf(dated)).single().artwork)
    }
    @Test fun removingAddonArtworkClearsOldBanner() {
        assertNull(attachSportsArtwork(listOf(event.copy(artwork = meta.background)), emptyList()).single().artwork)
    }
    @Test fun providerDashSeparatorsMatchButDifferentTeamsAndQualifiersDoNot() {
        val banner = listOf(meta.toSportsEventArtwork()!!)
        for (title in listOf("Barcelona - Feyenoord", "Football: Barcelona – Feyenoord", "Feyenoord at Barcelona")) {
            assertEquals(title, meta.background, attachSportsArtwork(listOf(event.copy(title = title)), banner).single().artwork)
        }
        for (title in listOf("Barcelona U21 - Feyenoord U21", "Barcelona Women - Feyenoord Women", "Barcelona - Madrid")) {
            assertNull(title, attachSportsArtwork(listOf(event.copy(title = title)), banner).single().artwork)
        }
    }
}
