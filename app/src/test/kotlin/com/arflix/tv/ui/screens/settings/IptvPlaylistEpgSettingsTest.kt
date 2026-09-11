package com.arflix.tv.ui.screens.settings

import com.arflix.tv.data.repository.IptvPlaylistEntry
import org.junit.Assert.assertEquals
import org.junit.Test

class IptvPlaylistEpgSettingsTest {
    private val playlist = IptvPlaylistEntry(
        id = "test", name = "Test", m3uUrl = "https://provider.example/get.php?username=u&password=p"
    )

    @Test
    fun preservesCustomXtreamEndpointsAndBackupOrder() {
        val sources = listOf(
            "https://guide.example/xmltv.php?username=guide&password=secret",
            "https://backup.example/get.php?type=xmltv",
            "https://third.example/guide.xml.gz",
        )
        assertEquals(sources.joinToString("\n"), playlist.copy(epgUrls = sources).settingsEpgInput())
    }

    @Test
    fun preservesLegacySingleGuideUrl() {
        val source = "https://guide.example/xmltv.php?custom=1"
        assertEquals(source, playlist.copy(epgUrl = source).settingsEpgInput())
    }

    @Test
    fun blankGuideRemainsBlankForAutomaticProviderFallback() {
        assertEquals("", playlist.settingsEpgInput())
    }
}
