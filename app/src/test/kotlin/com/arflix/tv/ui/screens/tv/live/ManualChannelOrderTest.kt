package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.repository.normalizeIptvChannelOrder
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/** The manual channel order set in ARVIO Web Settings (iptvByProfile.<id>.channelOrder). */
class ManualChannelOrderTest {

    private fun channel(id: String, group: String = "News", number: String? = null) =
        IptvChannel(id = id, name = id, group = group, streamUrl = "https://example.test/live", providerChannelNumber = number)
            .enrichForFastStartup(1)

    private val news = listOf(channel("list_1:a"), channel("list_1:b"), channel("list_1:c"), channel("list_1:d"))

    @Test
    fun listedChannelsComeFirstAndTheRestKeepTheirOrder() {
        val ordered = applyManualChannelOrder(news, "grp:list_1:abc", mapOf("list_1|News" to listOf("list_1:c", "list_1:missing", "list_1:a")))
        assertThat(ordered.map { it.id }).containsExactly("list_1:c", "list_1:a", "list_1:b", "list_1:d").inOrder()
    }

    @Test
    fun onlyPlaylistCategoriesAreReordered() {
        val order = mapOf("list_1|News" to listOf("list_1:d"))
        assertThat(applyManualChannelOrder(news, "all", order)).isSameInstanceAs(news)
        assertThat(applyManualChannelOrder(news, "grp:list_1:abc", emptyMap())).isSameInstanceAs(news)
        assertThat(applyManualChannelOrder(news, "grp:list_1:abc", mapOf("other|News" to listOf("list_1:d")))).isSameInstanceAs(news)
    }

    @Test
    fun manualOrderOverridesTheSortOrderAndUnlistedChannelsStaySorted() {
        val numbered = listOf(channel("list_1:x", number = "3"), channel("list_1:y", number = "1"), channel("list_1:z", number = "2"))
        val prepared = prepareGuideChannels(
            channels = numbered,
            categoryId = "grp:list_1:abc",
            sortOrder = "number",
            hiddenGroups = emptySet(),
            restrictedGroups = emptySet(),
            channelOrder = mapOf("list_1|News" to listOf("list_1:x")),
        )
        assertThat(prepared.map { it.id }).containsExactly("list_1:x", "list_1:y", "list_1:z").inOrder()
    }

    @Test
    fun favouritesAndRecentsAreNeverReordered() {
        val prepared = prepareGuideChannels(news, "fav", "provider", emptySet(), emptySet(), mapOf("list_1|News" to listOf("list_1:d")))
        assertThat(prepared.map { it.id }).containsExactly("list_1:a", "list_1:b", "list_1:c", "list_1:d").inOrder()
    }

    @Test
    fun cloudValuesAreCleanedBeforeStorage() {
        @Suppress("UNCHECKED_CAST")
        val raw = mapOf(
            "list_1|News" to listOf(" list_1:a ", "", "list_1:a", null),
            "noPlaylistKey" to listOf("x"),
            "list_1|Empty" to emptyList<String>(),
        ) as Map<String, List<String>>
        assertThat(normalizeIptvChannelOrder(raw)).containsExactly("list_1|News", listOf("list_1:a"))
        assertThat(normalizeIptvChannelOrder(null)).isEmpty()
    }
}
