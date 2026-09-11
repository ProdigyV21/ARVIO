package com.arflix.tv.ui.components

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DetailsResponsiveLayoutTest {

    @Test
    fun compactTvReservesRatingsOverviewAndActionButtons() {
        for (restingHeight in listOf(210, 226)) {
            val bounds = resolveDetailsTvHeroBounds(540, 365, restingHeight, restingHeight + 135, 0f, 12f)
            assertThat(bounds.rowsHeightPx).isEqualTo(163)
            assertThat(bounds.heroOffsetYPx).isEqualTo(0)
        }
    }

    @Test
    fun lateRatingsImmediatelyReduceAvailableRowsHeight() {
        assertThat(resolveDetailsTvHeroBounds(540, 324, 210, 345, 0f, 12f).rowsHeightPx).isEqualTo(204)
        assertThat(resolveDetailsTvHeroBounds(540, 365, 210, 345, 0f, 12f).rowsHeightPx).isEqualTo(163)
    }

    @Test
    fun heroAndRailsStaySeparatedThroughoutExpansionAndCollapse() {
        for (step in 0..100) {
            val progress = step / 100f
            for (restingHeight in listOf(210, 226)) {
                for (heroHeight in listOf(324, 365, 410)) {
                    val bounds = resolveDetailsTvHeroBounds(540, heroHeight, restingHeight, restingHeight + 135, progress, 12f)
                    val rowsTop = 540 - bounds.rowsHeightPx
                    assertThat(rowsTop).isAtLeast(heroHeight + bounds.heroOffsetYPx + 12)
                }
            }
        }
    }

    @Test
    fun largerTvKeepsPreferredRowsHeightWhenItFits() {
        assertThat(resolveDetailsTvHeroBounds(720, 390, 246, 421, 0f, 12f).rowsHeightPx).isEqualTo(246)
        val expanded = resolveDetailsTvHeroBounds(720, 390, 246, 421, 1f, 12f)
        assertThat(expanded.rowsHeightPx).isEqualTo(421)
        assertThat(expanded.heroOffsetYPx).isEqualTo(-175)
    }

    @Test
    fun roundingNeverPlacesRowsAboveTheRequiredGap() {
        for (density in listOf(1f, 1.5f, 2f, 3f)) {
            val height = (540 * density).toInt()
            val heroHeight = (365 * density).toInt()
            val gap = 12 * density
            val bounds = resolveDetailsTvHeroBounds(height, heroHeight, (226 * density).toInt(), (361 * density).toInt(), 0.37f, gap)

            assertThat((height - bounds.rowsHeightPx).toFloat()).isAtLeast(heroHeight + bounds.heroOffsetYPx + gap)
        }
    }

    @Test
    fun oversizedHeroDoesNotProduceNegativeConstraints() {
        assertThat(resolveDetailsTvHeroBounds(540, 600, 226, 361, 0f, 12f).rowsHeightPx).isEqualTo(0)
        assertThat(resolveDetailsTvHeroBounds(0, 0, 226, 361, 1f, 12f).rowsHeightPx).isEqualTo(0)
    }

    @Test
    fun compactTvStillGetsTheFullExpandedPosterViewport() {
        for (heroHeight in listOf(324, 365, 410)) {
            val bounds = resolveDetailsTvHeroBounds(540, heroHeight, 226, 361, 1f, 12f)
            assertThat(bounds.rowsHeightPx).isEqualTo(361)
            assertThat(540 - bounds.rowsHeightPx).isAtLeast(heroHeight + bounds.heroOffsetYPx + 12)
        }
    }

    @Test
    fun phoneLandscapeUsesCompactHeightThatStillFitsOverlay() {
        assertThat(
            resolveDetailsBackdropHeightDp(
                screenWidthDp = 640,
                screenHeightDp = 360,
                isPhone = true,
            )
        ).isWithin(0.01f).of(198f)
    }

    @Test
    fun phoneLandscapeHeightIsClampedToSafeRange() {
        assertThat(
            resolveDetailsBackdropHeightDp(
                screenWidthDp = 480,
                screenHeightDp = 320,
                isPhone = true,
            )
        ).isEqualTo(190f)
        assertThat(
            resolveDetailsBackdropHeightDp(
                screenWidthDp = 1280,
                screenHeightDp = 720,
                isPhone = true,
            )
        ).isEqualTo(220f)
    }

    @Test
    fun phonePortraitKeepsExistingBackdropRule() {
        assertThat(
            resolveDetailsBackdropHeightDp(
                screenWidthDp = 411,
                screenHeightDp = 891,
                isPhone = true,
            )
        ).isWithin(0.01f).of(472.23f)
    }

    @Test
    fun landscapeTabletKeepsExistingBackdropRule() {
        assertThat(
            resolveDetailsBackdropHeightDp(
                screenWidthDp = 1280,
                screenHeightDp = 800,
                isPhone = false,
            )
        ).isWithin(0.01f).of(424f)
    }
}
