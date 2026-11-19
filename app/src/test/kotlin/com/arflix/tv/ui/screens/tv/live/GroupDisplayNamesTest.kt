package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.repository.IptvCloudProfileState
import com.arflix.tv.data.repository.normalizeIptvGroupNames
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.Test

/** Category names set in ARVIO Web Settings (iptvByProfile.<id>.groupNames). */
class GroupDisplayNamesTest {

    private fun category(playlistId: String?, group: String?, label: String = group ?: "All Channels") =
        LiveCategory(id = "grp:$playlistId:1", label = label, count = 1, iconToken = CategoryIcon.Grid,
            playlistGroupName = group, playlistId = playlistId)

    @Test
    fun aPlaylistCategoryShowsTheNameKeyedByItsPlaylistAndOwnGroupName() {
        val names = mapOf("list_1|News" to "Headlines", "stalker2|Sport" to "Live sport")
        assertThat(customGroupName(category("list_1", "News"), names)).isEqualTo("Headlines")
        assertThat(customGroupName(category("stalker2", "Sport"), names)).isEqualTo("Live sport")
        // Another playlist with the same group keeps its own name.
        assertThat(customGroupName(category("list_2", "News"), names)).isNull()
        // Sections that carry a "source:" playlist id still match.
        assertThat(customGroupName(category("source:stalker2", "Sport"), names)).isEqualTo("Live sport")
    }

    @Test
    fun categoriesThatAreNotPlaylistGroupsNeverGetAName() {
        val names = mapOf("list_1|All Channels" to "Everything")
        assertThat(customGroupName(category(null, null), names)).isNull()
        assertThat(customGroupName(category("list_1", null, "All Channels"), names)).isNull()
        assertThat(customGroupName(category("list_1", "News"), emptyMap())).isNull()
    }

    @Test
    fun aBlankGroupUsesTheUngroupedKey() {
        assertThat(customGroupName(category("list_1", "  "), mapOf("list_1|Ungrouped" to "Misc"))).isEqualTo("Misc")
    }

    @Test
    fun cloudValuesAreCleanedBeforeStorage() {
        @Suppress("UNCHECKED_CAST")
        val raw = mapOf(
            "list_1|News" to "  Head   lines ",
            "list_1|Same" to "Same",
            "noPlaylistKey" to "x",
            "list_1|Blank" to " ",
            "list_1|Null" to null,
        ) as Map<String, String>
        assertThat(normalizeIptvGroupNames(raw)).containsExactly("list_1|News", "Head lines")
        assertThat(normalizeIptvGroupNames(mapOf("p|A" to "x".repeat(100)))["p|A"]).hasLength(60)
        assertThat(normalizeIptvGroupNames(null)).isEmpty()
    }

    @Test
    fun theCloudStateParsesGroupNamesAndOlderPayloadsWithoutThem() {
        val gson = Gson()
        val withNames = gson.fromJson("""{"groupNames":{"list_1|News":"Headlines"}}""", IptvCloudProfileState::class.java)
        assertThat(withNames.groupNames).containsExactly("list_1|News", "Headlines")
        val older = gson.fromJson("""{"playlists":[]}""", IptvCloudProfileState::class.java)
        assertThat(normalizeIptvGroupNames(runCatching { older.groupNames }.getOrNull())).isEmpty()
    }

    @Test
    fun hiddenAndOrderKeysStillUseThePlaylistName() {
        // Renaming changes only the label: the channel's key is built from its own group.
        val channel = IptvChannel(id = "list_1:a", name = "A", group = "News", streamUrl = "https://example.test/a")
            .enrichForFastStartup(1)
        assertThat(isHiddenPlaylistGroup(channel, setOf("list_1|News"))).isTrue()
        assertThat(isHiddenPlaylistGroup(channel, setOf("list_1|Headlines"))).isFalse()
    }
}
