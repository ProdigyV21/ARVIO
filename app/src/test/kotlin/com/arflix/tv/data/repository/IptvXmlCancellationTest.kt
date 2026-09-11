package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import io.mockk.mockk
import kotlinx.coroutines.CancellationException
import okhttp3.OkHttpClient
import org.junit.Assert.*
import org.junit.Test
import java.io.InputStream
import java.lang.reflect.InvocationTargetException
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class IptvXmlCancellationTest {
    private fun repository() = IptvRepository(
        mockk<Context>(relaxed = true), mockk<OkHttpClient>(relaxed = true),
        mockk<ProfileManager>(relaxed = true), mockk<CloudSyncInvalidationBus>(relaxed = true),
    )

    private fun xml(count: Int): String {
        val now = OffsetDateTime.now(ZoneOffset.UTC)
        val format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss Z")
        val start = now.minusMinutes(5).format(format)
        val end = now.plusHours(1).format(format)
        return buildString {
            append("<tv><channel id=\"wanted\"><display-name>Wanted</display-name></channel>")
            repeat(count) {
                append("<programme channel=\"wanted\" start=\"$start\" stop=\"$end\"><title>Programme $it</title></programme>")
            }
            append("</tv>")
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun parse(xml: String, checkActive: () -> Unit): Map<String, IptvNowNext> {
        val method = IptvRepository::class.java.getDeclaredMethod(
            "parseXmlTvNowNextWithSax", InputStream::class.java, List::class.java, Function0::class.java,
        ).apply { isAccessible = true }
        val channels = listOf(IptvChannel("source:1", "Wanted", "https://example.test/live", group = "News", epgId = "wanted"))
        return method.invoke(repository(), xml.byteInputStream(), channels, checkActive) as Map<String, IptvNowNext>
    }

    @Test fun saxFallbackStillParsesMatchingProgramme() {
        val result = parse(xml(1)) {}
        assertEquals("Programme 0", result["source:1"]?.now?.title)
    }

    @Test fun saxFallbackStopsWhenViewportRequestIsCancelled() {
        var checks = 0
        try {
            parse(xml(10_000)) { if (++checks == 2) throw CancellationException("obsolete viewport") }
            fail("Cancelled XMLTV work must not run to the end")
        } catch (error: InvocationTargetException) {
            assertTrue(error.targetException is CancellationException)
            assertEquals(2, checks)
        }
    }
}
