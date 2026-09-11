package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.repository.IptvConfig
import com.arflix.tv.data.repository.IptvPlaylistEntry
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Covers the guide-source rules behind a bug users hit as "no EPG at all":
 * a large plain-M3U playlist never fetched its XMLTV, because the full-guide
 * backfill was skipped for large lists and the on-demand fallback only handles
 * Xtream channels.
 */
class LiveTvGuideSourcesTest {

    private fun playlist(
        epgUrl: String = "",
        epgUrls: List<String> = emptyList(),
        enabled: Boolean = true,
    ) = IptvPlaylistEntry(
        id = "list_1",
        name = "List 1",
        m3uUrl = "https://example.test/playlist.m3u8",
        epgUrl = epgUrl,
        enabled = enabled,
        epgUrls = epgUrls,
    )

    // ── Detecting an XMLTV source ─────────────────────────────────────────

    @Test
    fun topLevelEpgUrlCountsAsXmltv() {
        val config = IptvConfig(epgUrl = "https://example.test/epg.xml.gz")

        assertThat(LiveTvGuideSources.hasXmltvSource(config)).isTrue()
    }

    @Test
    fun perPlaylistEpgUrlCountsAsXmltv() {
        val config = IptvConfig(
            playlists = listOf(playlist(epgUrl = "https://example.test/epg.xml.gz")),
        )

        assertThat(LiveTvGuideSources.hasXmltvSource(config)).isTrue()
    }

    @Test
    fun multiSourceEpgListCountsAsXmltv() {
        // The edit dialog accepts several guide URLs, one per line.
        val config = IptvConfig(
            playlists = listOf(
                playlist(
                    epgUrls = listOf(
                        "https://example.test/pltv1/epg.xml.gz",
                        "https://example.test/svt/epg.xml.gz",
                    ),
                )
            ),
        )

        assertThat(LiveTvGuideSources.hasXmltvSource(config)).isTrue()
    }

    @Test
    fun disabledPlaylistsDoNotContributeAGuide() {
        val config = IptvConfig(
            playlists = listOf(
                playlist(epgUrl = "https://example.test/epg.xml.gz", enabled = false),
            ),
        )

        assertThat(LiveTvGuideSources.hasXmltvSource(config)).isFalse()
    }

    @Test
    fun aPlaylistWithoutAGuideUrlHasNoXmltv() {
        val config = IptvConfig(playlists = listOf(playlist()))

        assertThat(LiveTvGuideSources.hasXmltvSource(config)).isFalse()
    }

    @Test
    fun blankUrlsAreIgnored() {
        val config = IptvConfig(
            epgUrl = "",
            playlists = listOf(playlist(epgUrl = "   ".trim(), epgUrls = listOf(""))),
        )

        assertThat(LiveTvGuideSources.hasXmltvSource(config)).isFalse()
    }

    // ── Whether the full-guide backfill may run ───────────────────────────

    @Test fun xtreamLoginHasAnAutomaticXmltvFeedEvenWhenShortApiIsEmpty() {
        val entry = playlist().copy(m3uUrl = "https://example.test/get.php?username=u&password=p&type=m3u_plus")
        assertThat(LiveTvGuideSources.hasXmltvSource(IptvConfig(playlists = listOf(entry)))).isTrue()
        assertThat(LiveTvGuideSources.hasXmltvSource(IptvConfig(playlists = listOf(entry.copy(enabled = false))))).isFalse()
    }

    @Test
    fun smallPlaylistsAlwaysBackfill() {
        // Below the large-list threshold the Xtream fan-out is affordable, so
        // the backfill runs whether or not an XMLTV URL is configured.
        val config = IptvConfig(m3uUrl = "https://example.test/playlist.m3u8")

        assertThat(LiveTvGuideSources.allowsFullGuideBackfill(config, channelCount = 240)).isTrue()
    }

    @Test
    fun largePlaylistWithXmltvStillBackfills() {
        // The reported bug: 84,704 channels + an XMLTV URL produced no guide,
        // because the large-list guard also blocked the bounded XMLTV path.
        val config = IptvConfig(
            playlists = listOf(playlist(epgUrl = "https://example.test/epg.xml.gz")),
        )

        assertThat(
            LiveTvGuideSources.allowsFullGuideBackfill(config, channelCount = 84_704)
        ).isTrue()
    }

    @Test
    fun largePlaylistWithoutXmltvSkipsBackfill() {
        // Without an XMLTV file the only route is Xtream's per-channel API, and
        // thousands of requests are exactly what the guard protects against.
        val config = IptvConfig(m3uUrl = "https://example.test/playlist.m3u8")

        assertThat(
            LiveTvGuideSources.allowsFullGuideBackfill(config, channelCount = 84_704)
        ).isFalse()
    }

    @Test
    fun theLargeListThresholdIsInclusiveOfSmallerLists() {
        val config = IptvConfig(m3uUrl = "https://example.test/playlist.m3u8")
        val justUnder = LiveTvGuideSources.LARGE_LIST_CHANNEL_COUNT - 1

        assertThat(LiveTvGuideSources.allowsFullGuideBackfill(config, justUnder)).isTrue()
        assertThat(
            LiveTvGuideSources.allowsFullGuideBackfill(
                config,
                LiveTvGuideSources.LARGE_LIST_CHANNEL_COUNT,
            )
        ).isFalse()
    }
}
