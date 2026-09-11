package com.arflix.tv.ui.screens.tv.live

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import com.arflix.tv.data.model.IptvChannel
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class LiveChannelQualityTest {
    private fun channel(name: String = "USA BET HER", group: String = "USA", label: String? = null) =
        IptvChannel(id = "provider:26", name = name, group = group,
            streamUrl = "https://example.test/live", qualityLabel = label)

    @Test fun unlabelledChannelsAreUnknownInBothLoadingPaths() {
        listOf("USA Antenna Tv", "USA Aspire", "USA AXS TV", "USA Magnolia Network", "USA BET East", "USA BET HER")
            .forEach { name ->
                val source = channel(name)
                assertThat(source.enrich(1).quality).isEqualTo(Quality.UNKNOWN)
                assertThat(source.enrichForFastStartup(1).quality).isEqualTo(Quality.UNKNOWN)
            }
        assertThat(Quality.UNKNOWN.label).isEmpty()
    }

    @Test fun explicitQualityNamesStillWork() {
        mapOf("News SD" to Quality.SD, "News 480p" to Quality.SD, "News 576i" to Quality.SD,
            "News HD" to Quality.HD, "News 720p" to Quality.HD, "News FHD" to Quality.FHD,
            "News 1080i" to Quality.FHD, "News UHD" to Quality.K4, "News 2160p" to Quality.K4)
            .forEach { (name, quality) -> assertThat(qualityFromText(name)).isEqualTo(quality) }
        assertThat(qualityFromText("SDA TV")).isEqualTo(Quality.UNKNOWN)
    }

    @Test fun providerLabelTakesPriority() {
        val source = channel("News HD", "USA SD", "FHD")
        assertThat(source.enrich(1).quality).isEqualTo(Quality.FHD)
        assertThat(source.enrichForFastStartup(1).quality).isEqualTo(Quality.FHD)
    }

    @Test fun groupQualityAlsoWorksDuringFastStartup() {
        val source = channel(group = "USA FHD")
        assertThat(source.enrich(1).quality).isEqualTo(Quality.FHD)
        assertThat(source.enrichForFastStartup(1).quality).isEqualTo(Quality.FHD)
    }

    @Test fun explicitSdNameIsNotReplacedByGroupHd() {
        val source = channel("News SD", "USA HD")
        assertThat(source.enrich(1).quality).isEqualTo(Quality.SD)
        assertThat(source.enrichForFastStartup(1).quality).isEqualTo(Quality.SD)
    }

    @Test fun unknownProviderLabelFallsBackToNameNotSd() {
        assertThat(channel("News 1080p", label = "auto").enrichForFastStartup(1).quality)
            .isEqualTo(Quality.FHD)
        assertThat(channel(label = "unknown").enrichForFastStartup(1).quality)
            .isEqualTo(Quality.UNKNOWN)
    }

    @Test fun unavailableVideoDimensionsAreUnknownNotSd() {
        listOf(0 to 0, -1 to -1, 1920 to 0, 0 to 1080).forEach { (width, height) ->
            assertThat(qualityFromVideoSize(width, height)).isEqualTo(Quality.UNKNOWN)
        }
    }

    @Test fun measuredDimensionsUseVideoNotMiniPlayerSize() {
        assertThat(qualityFromVideoSize(720, 576)).isEqualTo(Quality.SD)
        assertThat(qualityFromVideoSize(720, 480)).isEqualTo(Quality.SD)
        assertThat(qualityFromVideoSize(1280, 720)).isEqualTo(Quality.HD)
        assertThat(qualityFromVideoSize(1440, 1080)).isEqualTo(Quality.FHD)
        assertThat(qualityFromVideoSize(1920, 1080)).isEqualTo(Quality.FHD)
        assertThat(qualityFromVideoSize(3840, 2160)).isEqualTo(Quality.K4)
    }

    @Test fun measuredResolutionOnlyOverridesItsOwnChannel() {
        val row = channel(label = "SD").enrich(1)
        assertThat(row.displayQuality(LivePlaybackQuality(row.id, Quality.FHD))).isEqualTo(Quality.FHD)
        assertThat(row.displayQuality(LivePlaybackQuality("other", Quality.K4))).isEqualTo(Quality.SD)
        assertThat(row.quality).isEqualTo(Quality.SD)
    }

    @Test fun missingMeasurementPreservesMetadata() {
        val row = channel(label = "FHD").enrich(1)
        assertThat(row.displayQuality(null)).isEqualTo(Quality.FHD)
        assertThat(row.displayQuality(LivePlaybackQuality(row.id, Quality.UNKNOWN))).isEqualTo(Quality.FHD)
    }

    @Test fun listenerUpdatesWhenAdaptiveResolutionChanges() {
        val player = mockk<Player>()
        every { player.currentMediaItem } returns MediaItem.Builder().setMediaId("a").build()
        var value: LivePlaybackQuality? = null
        val listener = LivePlaybackQualityListener(player) { value = it }
        listener.onVideoSizeChanged(VideoSize(1920, 1080))
        assertThat(value).isEqualTo(LivePlaybackQuality("a", Quality.FHD))
        listener.onVideoSizeChanged(VideoSize(1280, 720))
        assertThat(value).isEqualTo(LivePlaybackQuality("a", Quality.HD))
        listener.onVideoSizeChanged(VideoSize(0, 0))
        assertThat(value).isNull()
    }

    @Test fun switchingChannelClearsOldQualityEvenWhenDimensionsStayTheSame() {
        val player = mockk<Player>()
        var item = MediaItem.Builder().setMediaId("a").build()
        every { player.currentMediaItem } answers { item }
        every { player.videoSize } returns VideoSize(1920, 1080)
        var value: LivePlaybackQuality? = null
        val listener = LivePlaybackQualityListener(player) { value = it }
        listener.onRenderedFirstFrame()
        assertThat(value).isEqualTo(LivePlaybackQuality("a", Quality.FHD))
        item = MediaItem.Builder().setMediaId("b").build()
        listener.onMediaItemTransition(item, Player.MEDIA_ITEM_TRANSITION_REASON_PLAYLIST_CHANGED)
        assertThat(value).isNull()
        listener.onRenderedFirstFrame()
        assertThat(value).isEqualTo(LivePlaybackQuality("b", Quality.FHD))
        listener.onMediaItemTransition(null, Player.MEDIA_ITEM_TRANSITION_REASON_PLAYLIST_CHANGED)
        assertThat(value).isNull()
    }

    @Test fun anonymousMediaCannotLabelAnUnrelatedChannel() {
        val player = mockk<Player>()
        every { player.currentMediaItem } returns MediaItem.Builder().build()
        var value: LivePlaybackQuality? = LivePlaybackQuality("old", Quality.SD)
        val listener = LivePlaybackQualityListener(player) { value = it }
        listener.onVideoSizeChanged(VideoSize(1920, 1080))
        assertThat(value).isNull()
    }
}
