package com.arflix.tv.data.repository

import com.arflix.tv.data.api.StremioStream
import org.junit.Assert.assertEquals
import org.junit.Test

class AddonNoticesTest {
    private val errorStream = StremioStream(
        name = "[❌] Comet",
        description = "Invalid Premiumize API key",
        externalUrl = "https://github.com/Viren070/AIOStreams"
    )

    @Test
    fun `aiostreams error streams become notices`() {
        assertEquals(
            listOf(AddonNotice("aio", "AIOStreams", "Comet", "Invalid Premiumize API key")),
            aioStreamsNotices(listOf(errorStream), "aio", "AIOStreams")
        )
    }

    @Test
    fun `statistics rows and real sources are not notices`() {
        val statistic = StremioStream(
            name = "📊 Statistics",
            description = "34 results in 2.1s",
            externalUrl = "https://github.com/Viren070/AIOStreams"
        )
        val source = errorStream.copy(url = "https://example.com/video.mkv")
        val otherLink = errorStream.copy(externalUrl = "https://github.com/someone/else")
        assertEquals(emptyList<AddonNotice>(), aioStreamsNotices(listOf(statistic, source, otherLink), "aio", "AIOStreams"))
    }

    @Test
    fun `default error title falls back to the addon name`() {
        val bare = errorStream.copy(name = "[❌]")
        assertEquals("AIOStreams", aioStreamsNotices(listOf(bare), "aio", "AIOStreams").single().title)
    }

    @Test
    fun `sink keeps each notice once`() {
        val sink = AddonNoticeSink()
        val notice = AddonNotice("aio", "AIOStreams", "Comet", "x")
        sink.add(notice)
        sink.add(notice)
        assertEquals(listOf(notice), sink.snapshot())
    }
}
