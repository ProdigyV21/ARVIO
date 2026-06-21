package com.arflix.tv.ui.screens.search

import com.arflix.tv.data.stream.StreamAvailabilitySummary
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SearchStreamSummaryLabelTest {

    @Test
    fun `summary label exposes best stream quality and source count`() {
        val label = searchStreamSummaryLabel(
            SearchStreamSummaryState(
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

        assertThat(label).isEqualTo("4K / DV / Atmos / SE subs / Cached / AIOStreams / 7 src")
    }

    @Test
    fun `summary label is discreet while loading or unavailable`() {
        assertThat(searchStreamSummaryLabel(SearchStreamSummaryState(isLoading = true)))
            .isEqualTo("Checking sources")
        assertThat(searchStreamSummaryLabel(SearchStreamSummaryState(hasChecked = true)))
            .isEqualTo("Sources -")
        assertThat(searchStreamSummaryLabel(null)).isNull()
    }
}
