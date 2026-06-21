package com.arflix.tv.ui.screens.collections

import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.stream.StreamAvailabilitySummary
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CollectionStreamSummaryLabelTest {

    @Test
    fun `compact label exposes stream quality for collection rows`() {
        val summary = summary(
            bestResolution = "4K",
            bestVisualTag = "DV",
            bestAudioTag = "Atmos",
            hasSwedishSubtitles = true,
            sourceCount = 7
        )

        assertThat(collectionStreamSummaryCompactLabel(summary))
            .isEqualTo("4K/DV/Atmos/SE/AIOStreams/7 src")
    }

    @Test
    fun `row metadata appends compact stream summary`() {
        val item = MediaItem(
            id = 10,
            title = "Heat",
            mediaType = MediaType.MOVIE,
            year = "1995",
            duration = "2h 50m"
        )

        val meta = rowMeta(
            item = item,
            streamSummary = summary(
                bestResolution = "1080p",
                bestVisualTag = "HDR",
                bestAudioTag = "5.1",
                sourceCount = 3
            )
        )

        assertThat(meta).contains("1995")
        assertThat(meta).contains("2h50m")
        assertThat(meta).contains("1080p/HDR/5.1/AIOStreams/3 src")
    }

    private fun summary(
        bestResolution: String,
        bestVisualTag: String? = null,
        bestAudioTag: String? = null,
        hasSwedishSubtitles: Boolean = false,
        sourceCount: Int
    ): StreamAvailabilitySummary {
        return StreamAvailabilitySummary(
            bestResolution = bestResolution,
            bestVisualTag = bestVisualTag,
            bestAudioTag = bestAudioTag,
            isCachedOrDebridReady = true,
            hasSwedishSubtitles = hasSwedishSubtitles,
            sourceCount = sourceCount,
            maxSizeLabel = null,
            bestSourceLabel = "AIOStreams",
            loadedAtMs = 1L
        )
    }
}
