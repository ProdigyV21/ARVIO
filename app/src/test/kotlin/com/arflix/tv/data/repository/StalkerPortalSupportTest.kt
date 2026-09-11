package com.arflix.tv.data.repository

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.Test

class StalkerPortalSupportTest {

    private val gson = Gson()

    @Test
    fun portalIdFromChannelIdExtractsPortalSegment() {
        assertThat(StalkerPortalSupport.portalIdFromChannelId("stalker:stalker1:42"))
            .isEqualTo("stalker1")
        assertThat(StalkerPortalSupport.portalIdFromChannelId("stalker:stalker2:abc"))
            .isEqualTo("stalker2")
    }

    @Test
    fun portalIdFromChannelIdReturnsNullForNonStalkerIds() {
        assertThat(StalkerPortalSupport.portalIdFromChannelId("list_1:42")).isNull()
        assertThat(StalkerPortalSupport.portalIdFromChannelId("plain")).isNull()
    }

    @Test
    fun portalIdFromChannelIdReturnsNullWhenPortalSegmentMissing() {
        // Legacy single-portal id shape "stalker:42" has no portal segment.
        assertThat(StalkerPortalSupport.portalIdFromChannelId("stalker:42")).isNull()
    }

    @Test
    fun startsWithStalkerStillMatchesNewPrefixedIds() {
        // The existing checks rely on startsWith("stalker:") and must keep matching.
        assertThat("stalker:stalker1:42".startsWith("stalker:")).isTrue()
        assertThat("stalker:stalker2:abc".startsWith("stalker:")).isTrue()
    }

    @Test
    fun migratedPortalFromLegacyProducesPortal1() {
        val portal = StalkerPortalSupport.migratedPortalFromLegacy(
            "http://portal.example/stalker_portal/",
            "00:1A:79:12:34:56"
        )
        assertThat(portal).isNotNull()
        assertThat(portal!!.id).isEqualTo("stalker1")
        assertThat(portal.name).isEqualTo("Portal 1")
        assertThat(portal.portalUrl).isEqualTo("http://portal.example/stalker_portal")
        assertThat(portal.macAddress).isEqualTo("00:1A:79:12:34:56")
    }

    @Test
    fun migratedPortalFromLegacyReturnsNullWhenFieldsBlank() {
        assertThat(StalkerPortalSupport.migratedPortalFromLegacy("", "00:1A:79:12:34:56")).isNull()
        assertThat(StalkerPortalSupport.migratedPortalFromLegacy("http://portal", "")).isNull()
    }

    @Test
    fun migrateAndDecodeRoundTripsThroughJson() {
        val migrated = StalkerPortalSupport.migratedPortalFromLegacy(
            "http://portal.example/",
            "00:1a:79:aa:bb:cc"
        )!!
        // MAC is uppercased during migration.
        assertThat(migrated.macAddress).isEqualTo("00:1A:79:AA:BB:CC")

        val json = gson.toJson(listOf(migrated))
        val decoded = StalkerPortalSupport.decodeStalkerPortals(json, maxPortals = 3)
        assertThat(decoded).hasSize(1)
        assertThat(decoded[0].id).isEqualTo("stalker1")
        assertThat(decoded[0].portalUrl).isEqualTo("http://portal.example")
        assertThat(decoded[0].macAddress).isEqualTo("00:1A:79:AA:BB:CC")
    }

    @Test
    fun decodeStalkerPortalsPreservesMultiplePortalsAndOrder() {
        val portals = listOf(
            StalkerPortalEntry("stalker1", "Portal 1", "http://a/", "00:1A:79:11:11:11"),
            StalkerPortalEntry("stalker2", "Portal 2", "http://b/", "00:1A:79:22:22:22"),
            StalkerPortalEntry("stalker3", "Portal 3", "http://c/", "00:1A:79:33:33:33")
        )
        val json = gson.toJson(portals)
        val decoded = StalkerPortalSupport.decodeStalkerPortals(json, maxPortals = 3)
        assertThat(decoded.map { it.id })
            .containsExactly("stalker1", "stalker2", "stalker3").inOrder()
        assertThat(decoded.map { it.portalUrl })
            .containsExactly("http://a", "http://b", "http://c").inOrder()
    }

    @Test
    fun decodeStalkerPortalsCapsAtMaxPortals() {
        val portals = listOf(
            StalkerPortalEntry("stalker1", "Portal 1", "http://a/", "00:1A:79:11:11:11"),
            StalkerPortalEntry("stalker2", "Portal 2", "http://b/", "00:1A:79:22:22:22"),
            StalkerPortalEntry("stalker3", "Portal 3", "http://c/", "00:1A:79:33:33:33"),
            StalkerPortalEntry("stalker4", "Portal 4", "http://d/", "00:1A:79:44:44:44")
        )
        val json = gson.toJson(portals)
        val decoded = StalkerPortalSupport.decodeStalkerPortals(json, maxPortals = 3)
        assertThat(decoded).hasSize(3)
        assertThat(decoded.map { it.id })
            .containsExactly("stalker1", "stalker2", "stalker3").inOrder()
    }

    @Test
    fun decodeStalkerPortalsDropsEntriesWithBlankFields() {
        val portals = listOf(
            StalkerPortalEntry("stalker1", "Portal 1", "http://a/", "00:1A:79:11:11:11"),
            StalkerPortalEntry("stalker2", "Portal 2", "", "00:1A:79:22:22:22"),
            StalkerPortalEntry("stalker3", "Portal 3", "http://c/", "")
        )
        val json = gson.toJson(portals)
        val decoded = StalkerPortalSupport.decodeStalkerPortals(json, maxPortals = 3)
        assertThat(decoded).hasSize(1)
        assertThat(decoded[0].id).isEqualTo("stalker1")
    }

    @Test
    fun decodeStalkerPortalsAssignsDefaultIdAndNameWhenBlank() {
        val portals = listOf(
            StalkerPortalEntry("", "", "http://a/", "00:1A:79:11:11:11")
        )
        val json = gson.toJson(portals)
        val decoded = StalkerPortalSupport.decodeStalkerPortals(json, maxPortals = 3)
        assertThat(decoded).hasSize(1)
        assertThat(decoded[0].id).isEqualTo("stalker1")
        assertThat(decoded[0].name).isEqualTo("Portal 1")
    }

    @Test
    fun decodeStalkerPortalsReturnsEmptyForBlankOrMalformedInput() {
        assertThat(StalkerPortalSupport.decodeStalkerPortals("", maxPortals = 3)).isEmpty()
        assertThat(StalkerPortalSupport.decodeStalkerPortals("not json", maxPortals = 3)).isEmpty()
    }

    @Test
    fun multiPortalChannelIdsResolveToDistinctPortals() {
        val ids = listOf("stalker:stalker1:10", "stalker:stalker2:20", "stalker:stalker3:30")
        val portalIds = ids.mapNotNull { StalkerPortalSupport.portalIdFromChannelId(it) }
        assertThat(portalIds).containsExactly("stalker1", "stalker2", "stalker3").inOrder()
    }

    @Test
    fun playlistAndCacheKeysAreScopedPerPortal() {
        val firstId = "stalker:stalker1:10"
        val secondId = "stalker:stalker2:10"

        assertThat(StalkerPortalSupport.playlistIdFromChannelId(firstId)).isEqualTo("stalker1")
        assertThat(StalkerPortalSupport.playlistIdFromChannelId(secondId)).isEqualTo("stalker2")
        assertThat(StalkerPortalSupport.streamCacheKey(firstId, "ffmpeg http://same"))
            .isNotEqualTo(StalkerPortalSupport.streamCacheKey(secondId, "ffmpeg http://same"))
    }

    @Test
    fun nextAvailablePortalIdDoesNotReuseDeletedPortalIdentity() {
        assertThat(
            StalkerPortalSupport.nextAvailablePortalId(
                listOf("stalker1", "stalker3"),
                maxPortals = 3,
            )
        ).isEqualTo("stalker4")
    }

    @Test
    fun decodeStalkerPortalsRepairsDuplicateIds() {
        val json = gson.toJson(
            listOf(
                StalkerPortalEntry("stalker1", "A", "http://a", "00:1A:79:11:11:11"),
                StalkerPortalEntry("stalker1", "B", "http://b", "00:1A:79:22:22:22"),
            )
        )

        val decoded = StalkerPortalSupport.decodeStalkerPortals(json, maxPortals = 3)

        assertThat(decoded.map { it.id }).containsExactly("stalker1", "stalker2").inOrder()
    }

    @Test
    fun legacyChannelAndGroupKeysMigrateToPortal1() {
        assertThat(StalkerPortalSupport.migrateLegacyChannelId("stalker:42"))
            .isEqualTo("stalker:stalker1:42")
        assertThat(StalkerPortalSupport.migrateLegacyChannelId("stalker:stalker2:42"))
            .isEqualTo("stalker:stalker2:42")
        assertThat(StalkerPortalSupport.migrateLegacyPlaylistGroupKey("stalker|News"))
            .isEqualTo("stalker1|News")
    }

    @Test
    fun cloudGroupKeysKeepConfiguredStalkerPortalOrdering() {
        val normalized = StalkerPortalSupport.normalizePlaylistGroupKeys(
            listOf("stalker2|Sports", "stalker1|News", "removed|Kids"),
            validSourceIds = setOf("stalker1", "stalker2"),
        )

        assertThat(normalized).containsExactly("stalker2|Sports", "stalker1|News").inOrder()
    }

    @Test
    fun normalizeStalkerPortalEntryTrimsUrlAndUppercasesMac() {
        val portal = StalkerPortalEntry("stalker1", "Portal 1", "  http://a/  ", "  00:1a:79:aa:bb:cc  ")
        val normalized = StalkerPortalSupport.normalizeStalkerPortalEntry(portal, 0)
        assertThat(normalized).isNotNull()
        assertThat(normalized!!.portalUrl).isEqualTo("http://a")
        assertThat(normalized.macAddress).isEqualTo("00:1A:79:AA:BB:CC")
        assertThat(normalized.enabled).isTrue()
    }

    @Test
    fun normalizeStalkerPortalEntryAssignsDefaultIdAndNameByIndex() {
        val portal = StalkerPortalEntry("", "", "http://a/", "00:1A:79:AA:BB:CC")
        val normalized = StalkerPortalSupport.normalizeStalkerPortalEntry(portal, 2)
        assertThat(normalized).isNotNull()
        assertThat(normalized!!.id).isEqualTo("stalker3")
        assertThat(normalized.name).isEqualTo("Portal 3")
    }

    @Test
    fun normalizeStalkerPortalEntryReturnsNullForBlankFields() {
        assertThat(StalkerPortalSupport.normalizeStalkerPortalEntry(
            StalkerPortalEntry("stalker1", "Portal 1", "", "00:1A:79:AA:BB:CC"), 0
        )).isNull()
        assertThat(StalkerPortalSupport.normalizeStalkerPortalEntry(
            StalkerPortalEntry("stalker1", "Portal 1", "http://a/", ""), 0
        )).isNull()
    }

    @Test
    fun normalizeStalkerPortalEntryPreservesEnabledFlag() {
        val portal = StalkerPortalEntry("stalker1", "Portal 1", "http://a/", "00:1A:79:AA:BB:CC", enabled = false)
        val normalized = StalkerPortalSupport.normalizeStalkerPortalEntry(portal, 0)
        assertThat(normalized).isNotNull()
        assertThat(normalized!!.enabled).isFalse()
    }

    @Test
    fun isRoutableStreamAddressRejectsPortalPlaceholders() {
        assertThat(StalkerPortalSupport.isRoutableStreamAddress("http://localhost/ch/1234_")).isFalse()
        assertThat(StalkerPortalSupport.isRoutableStreamAddress("http://127.0.0.1:8080/ch/1")).isFalse()
        assertThat(StalkerPortalSupport.isRoutableStreamAddress("http://0.0.0.0/ch/1")).isFalse()
        assertThat(StalkerPortalSupport.isRoutableStreamAddress("")).isFalse()
        assertThat(StalkerPortalSupport.isRoutableStreamAddress("   ")).isFalse()
        assertThat(StalkerPortalSupport.isRoutableStreamAddress("not a url")).isFalse()
    }

    @Test
    fun isRoutableStreamAddressAcceptsRealHosts() {
        assertThat(StalkerPortalSupport.isRoutableStreamAddress("http://provider.test/live/1")).isTrue()
        assertThat(StalkerPortalSupport.isRoutableStreamAddress("https://provider.test:8080/token")).isTrue()
    }

    @Test
    fun sanitizePlaybackCommandStripsAnyLeadingCommandWord() {
        // Measured portals: "ffmpeg " at two of them, "auto " at a third.
        assertThat(StalkerPortalSupport.sanitizePlaybackCommand("ffmpeg http://host/live/1"))
            .isEqualTo("http://host/live/1")
        assertThat(StalkerPortalSupport.sanitizePlaybackCommand("auto http://host/live.ts?channelId=1"))
            .isEqualTo("http://host/live.ts?channelId=1")
        assertThat(StalkerPortalSupport.sanitizePlaybackCommand("  extension  http://host/live/1  "))
            .isEqualTo("http://host/live/1")
    }

    @Test
    fun sanitizePlaybackCommandLeavesBareAddressesAlone() {
        assertThat(StalkerPortalSupport.sanitizePlaybackCommand("http://host/live/1"))
            .isEqualTo("http://host/live/1")
        // A first word that carries the scheme is the address itself, not a command.
        assertThat(StalkerPortalSupport.sanitizePlaybackCommand("http://host/live/1 extra"))
            .isEqualTo("http://host/live/1 extra")
        assertThat(StalkerPortalSupport.sanitizePlaybackCommand(null)).isEqualTo("")
        assertThat(StalkerPortalSupport.sanitizePlaybackCommand("   ")).isEqualTo("")
    }

    @Test
    fun isDirectStreamAddressAcceptsOnlyReadyToPlayAddresses() {
        assertThat(StalkerPortalSupport.isDirectStreamAddress("http://host/play/live.php?stream=1")).isTrue()
        assertThat(StalkerPortalSupport.isDirectStreamAddress("https://host:8080/token")).isTrue()
        // Still carries the portal's command word.
        assertThat(StalkerPortalSupport.isDirectStreamAddress("ffmpeg http://host/live/1")).isFalse()
        // The create_link placeholder, in both of its measured shapes.
        assertThat(StalkerPortalSupport.isDirectStreamAddress("http://localhost/ch/1234_")).isFalse()
        assertThat(StalkerPortalSupport.isDirectStreamAddress("http://host/ch/1234_")).isFalse()
        // A play_token may end in an underscore; only the path decides.
        assertThat(StalkerPortalSupport.isDirectStreamAddress("http://host/play/live.php?play_token=ab_"))
            .isTrue()
        assertThat(StalkerPortalSupport.isDirectStreamAddress("rtmp://host/live/1")).isFalse()
        assertThat(StalkerPortalSupport.isDirectStreamAddress("")).isFalse()
    }

    @Test
    fun directLiveStreamUrlPublishesTheAddressWhenThePortalNeedsNoTemporaryLink() {
        val cmd = "ffmpeg http://host/play/live.php?stream=1284583&extension=ts&play_token=T"
        assertThat(
            StalkerPortalSupport.directLiveStreamUrl(cmd, "0", "0", "0")
        ).isEqualTo("http://host/play/live.php?stream=1284583&extension=ts&play_token=T")
        // The other measured shape, from a portal that prefixes with "auto".
        assertThat(
            StalkerPortalSupport.directLiveStreamUrl("auto http://host/live.ts?channelId=9", "0", null, null)
        ).isEqualTo("http://host/live.ts?channelId=9")
    }

    @Test
    fun directLiveStreamUrlKeepsTheRoundTripWheneverTheAnswerIsNotClear() {
        val cmd = "ffmpeg http://host/play/live.php?stream=1&extension=ts"
        // The portal asks for a temporary link.
        assertThat(StalkerPortalSupport.directLiveStreamUrl(cmd, "1", "0", "0")).isNull()
        assertThat(StalkerPortalSupport.directLiveStreamUrl(cmd, "0", "1", "0")).isNull()
        assertThat(StalkerPortalSupport.directLiveStreamUrl(cmd, "0", "0", "1")).isNull()
        // The portal says nothing at all.
        assertThat(StalkerPortalSupport.directLiveStreamUrl(cmd, null, null, null)).isNull()
        assertThat(StalkerPortalSupport.directLiveStreamUrl(cmd, "", null, null)).isNull()
        // It says no temporary link but publishes a placeholder anyway.
        assertThat(
            StalkerPortalSupport.directLiveStreamUrl("ffmpeg http://localhost/ch/1_", "0", "0", "0")
        ).isNull()
        assertThat(StalkerPortalSupport.directLiveStreamUrl(null, "0", "0", "0")).isNull()
    }
}
