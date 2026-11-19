package com.arflix.tv.ui.components

import androidx.compose.ui.unit.IntSize
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LandscapeDeckArtworkTest {
    private val item = MediaItem(1, "Fixture", mediaType = MediaType.MOVIE, image = "poster.jpg")

    @Test fun backdropIsPreferredWithoutChangingSourceItem() {
        val source = item.copy(backdrop = "  backdrop.jpg  ")
        assertThat(landscapeDeckArtworkUrl(source)).isEqualTo("backdrop.jpg")
        assertThat(source.backdrop).isEqualTo("  backdrop.jpg  ")
    }

    @Test fun missingOrBlankBackdropFallsBackToPoster() {
        for (backdrop in listOf(null, "", " ", "\t\n")) {
            assertThat(landscapeDeckArtworkUrl(item.copy(backdrop = backdrop)))
                .isEqualTo("poster.jpg")
        }
    }

    @Test fun missingArtworkDoesNotCreateEmptyRequest() {
        assertThat(landscapeDeckArtworkUrl(item.copy(backdrop = " ", image = "\t"))).isNull()
        assertThat(landscapeDeckArtworkUrl(item.copy(image = ""))).isNull()
    }

    @Test fun compactArtworkUsesRenderedPixelBoundsAtFullDensity() {
        val layout = landscapeDeckLayout(700f, 300f)
        val sizes = landscapeDeckArtworkSizes(layout, 3f)
        assertThat(sizes.backdrop).isEqualTo(IntSize(928, 522))
        assertThat(sizes.logo).isEqualTo(IntSize(557, 157))
    }

    @Test fun tabletArtworkIsNotReducedBelowRenderedResolution() {
        val layout = landscapeDeckLayout(1100f, 760f)
        val sizes = landscapeDeckArtworkSizes(layout, 2f)
        assertThat(sizes.backdrop).isEqualTo(IntSize(1401, 788))
        assertThat(sizes.logo).isEqualTo(IntSize(880, 192))
    }

    @Test fun smallerNoncompactCardUsesSmallerLogoBox() {
        val sizes = landscapeDeckArtworkSizes(landscapeDeckLayout(850f, 500f), 2f)
        assertThat(sizes.logo).isEqualTo(IntSize(560, 128))
    }

    @Test fun resizeRecalculatesBothArtworkBounds() {
        val tall = landscapeDeckArtworkSizes(landscapeDeckLayout(1100f, 760f), 2f)
        val short = landscapeDeckArtworkSizes(landscapeDeckLayout(1100f, 280f), 2f)
        assertThat(short.backdrop.width).isLessThan(tall.backdrop.width)
        assertThat(short.logo.width).isLessThan(tall.logo.width)
        assertThat(short.logo.height).isLessThan(tall.logo.height)
    }

    @Test fun tinyWindowsStillRequestAtLeastOnePixel() {
        val sizes = landscapeDeckArtworkSizes(landscapeDeckLayout(0f, 0f), 0.1f)
        assertThat(sizes.backdrop).isEqualTo(IntSize(1, 1))
        assertThat(sizes.logo).isEqualTo(IntSize(1, 1))
    }

    @Test fun prefetchIncludesVisibleNeighborsAndOneAheadWithoutDuplicates() {
        assertThat(landscapeDeckPrefetchIndices(0, 10))
            .containsExactly(8, 9, 0, 1, 2, 3).inOrder()
        assertThat(landscapeDeckPrefetchIndices(0, 2)).containsExactly(0, 1).inOrder()
        assertThat(landscapeDeckPrefetchIndices(0, 1)).containsExactly(0)
    }

    @Test fun prefetchHandlesEmptyAndOutOfRangeIndices() {
        assertThat(landscapeDeckPrefetchIndices(0, 0)).isEmpty()
        for (index in listOf(-1, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val indices = landscapeDeckPrefetchIndices(index, 4)
            assertThat(indices).containsExactly(0, 1, 2, 3)
        }
    }
}
