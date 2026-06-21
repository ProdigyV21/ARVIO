package com.arflix.tv.ui.screens.discover

import com.arflix.tv.data.stream.StreamAvailabilitySummary
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DiscoverStreamSummaryLabelTest {
    @Test
    fun `summary label exposes best quality audio subtitles readiness and source count`() {
        val label = discoverStreamSummaryLabel(
            DiscoverStreamSummaryState(
                summary = StreamAvailabilitySummary(
                    bestResolution = "4K",
                    bestVisualTag = "DV",
                    bestAudioTag = "Atmos",
                    isCachedOrDebridReady = true,
                    hasSwedishSubtitles = true,
                    sourceCount = 7,
                    maxSizeLabel = "44 GB",
                    bestSourceLabel = "AIOStreams",
                    loadedAtMs = 1L
                )
            )
        )

        assertThat(label).isEqualTo("4K / DV / Atmos / SE / Cached / AIOStreams / 7 src")
    }

    @Test
    fun `summary label stays discreet while loading or unavailable`() {
        assertThat(discoverStreamSummaryLabel(DiscoverStreamSummaryState(isLoading = true)))
            .isEqualTo("Kollar källor")
        assertThat(discoverStreamSummaryLabel(DiscoverStreamSummaryState(hasChecked = true)))
            .isEqualTo("Källor -")
        assertThat(discoverStreamSummaryLabel(null)).isNull()
    }
}
