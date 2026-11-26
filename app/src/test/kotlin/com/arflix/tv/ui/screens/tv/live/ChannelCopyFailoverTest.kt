package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ChannelCopyFailoverTest {

    private fun channel(id: String) = IptvChannel(id = id, name = "NPO 1 $id", streamUrl = "https://example.invalid/$id", group = "NL")

    @Test
    fun failedChannelMovesOnToEachOtherCopyOnce() {
        val failover = channelCopyFailover(channel("hd"), listOf(channel("fhd"), channel("hd"), channel("sd")))!!
        assertThat(failover.channels.map { it.id }).containsExactly("hd", "fhd", "sd").inOrder()
        val (first, afterFirst) = failover.next("hd")!!
        assertThat(first.id).isEqualTo("fhd")
        val (second, afterSecond) = afterFirst.next("fhd")!!
        assertThat(second.id).isEqualTo("sd")
        assertThat(afterSecond.next("sd")).isNull()
    }

    @Test
    fun noOtherCopyMeansNoFailover() {
        assertThat(channelCopyFailover(channel("hd"), listOf(channel("hd")))).isNull()
        assertThat(channelCopyFailover(channel("hd"), emptyList())).isNull()
    }
}
