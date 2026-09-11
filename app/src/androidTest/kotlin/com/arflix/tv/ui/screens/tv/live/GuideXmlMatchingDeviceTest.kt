package com.arflix.tv.ui.screens.tv.live

import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.di.GuideAuditEntryPoint
import dagger.hilt.android.EntryPointAccessors
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import org.junit.Assert.assertEquals
import org.junit.Test

class GuideXmlMatchingDeviceTest {
    @Test fun numericApiIdsMatchXmlNamesAcrossQualityVariantsButNotCountries() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val repository = EntryPointAccessors.fromApplication(context, GuideAuditEntryPoint::class.java).iptvRepository()
        val channels = listOf(
            channel("low", "NL| ESPN 1 LQ", "988726"),
            channel("high", "NL| ESPN 1 HD", "988727"),
            channel("full", "NL| ESPN 1 FHD", "ESPN1.nl"),
            channel("other", "US| ESPN 1 HD", "988728"),
            channel("baby", "NL| BABYTV LQ", "1067473"),
        ) + List(10_000) { channel("padding$it", "Padding $it", "dummy$it") }
        val format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss Z").withZone(ZoneOffset.UTC)
        val start = format.format(Instant.now().minusSeconds(300))
        val stop = format.format(Instant.now().plusSeconds(300))
        val xml = """<tv>
            <channel id="ESPN1.nl"><display-name>NL| ESPN 1 HD</display-name></channel>
            <channel id="babytv.nl"><display-name>NL| BABY TV</display-name></channel>
            <programme channel="ESPN1.nl" start="$start" stop="$stop"><title>Sports</title></programme>
            <programme channel="babytv.nl" start="$start" stop="$stop"><title>Kids</title></programme>
        </tv>"""
        val guide = repository.parseXmlTvNowNext(xml.byteInputStream(), channels)
        assertEquals(setOf("low", "high", "full", "baby"), guide.keys)
        assertEquals("Sports", guide.getValue("low").now?.title)
        assertEquals("Kids", guide.getValue("baby").now?.title)
    }

    private fun channel(id: String, name: String, epgId: String) = IptvChannel(
        id = id, name = name, epgId = epgId, group = "Test", streamUrl = "https://example.invalid/$id.m3u8")
}
