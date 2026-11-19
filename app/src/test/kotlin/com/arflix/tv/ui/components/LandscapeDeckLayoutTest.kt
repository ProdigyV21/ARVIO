package com.arflix.tv.ui.components

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LandscapeDeckLayoutTest {
    @Test fun compactPhoneKeepsOriginalAspectAndFullStack() {
        val layout = landscapeDeckLayout(700f, 300f)
        assertThat(layout.compact).isTrue()
        assertThat(layout.visibleCards).isEqualTo(5)
        assertThat(layout.aspectRatio).isWithin(0.001f).of(16f / 9f)
        assertThat(layout.cardHeightDp + 24f).isAtMost(198.01f)
    }

    @Test fun spaciousTabletShowsFullDeck() {
        val layout = landscapeDeckLayout(1100f, 760f)
        assertThat(layout.compact).isFalse()
        assertThat(layout.visibleCards).isEqualTo(5)
        assertThat(layout.cardWidthDp).isGreaterThan(600f)
        assertThat(layout.cardWidthDp).isAtMost(704f)
        assertThat(layout.cardHeightDp + 24f).isAtMost(418.01f)
    }

    @Test fun resizingToShortWindowReducesCardHeight() {
        val tall = landscapeDeckLayout(1100f, 760f)
        val short = landscapeDeckLayout(1100f, 280f)
        assertThat(short.compact).isTrue()
        assertThat(short.cardHeightDp).isLessThan(tall.cardHeightDp)
    }

    @Test fun cardsAndWingsFitNarrowAndWideWindows() {
        for (width in listOf(240f, 440f, 700f, 1100f, 1800f)) {
            for (height in listOf(180f, 280f, 400f, 760f)) {
                val layout = landscapeDeckLayout(width, height)
                assertThat(layout.cardWidthDp).isAtMost(width)
                assertThat(layout.cardHeightDp + 24f).isAtMost(height * (if (layout.compact) 0.66f else 0.55f) + 0.01f)
                assertThat(layout.cardWidthDp / layout.cardHeightDp).isWithin(0.001f).of(layout.aspectRatio)
                val wings = (layout.visibleCards - 1) / 2
                assertThat(layout.cardWidthDp + 2 * wings * layout.wingStepDp).isAtMost(width + 0.01f)
            }
        }
    }
}
