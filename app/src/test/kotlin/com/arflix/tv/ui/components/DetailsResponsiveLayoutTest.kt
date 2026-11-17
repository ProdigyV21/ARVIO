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
                availableWidthDp = 640f,
                availableHeightDp = 360f,
            )
        ).isWithin(0.01f).of(198f)
    }

    @Test
    fun landscapeHeightUsesAdaptiveFloorAndCap() {
        assertThat(
            resolveDetailsBackdropHeightDp(
                availableWidthDp = 480f,
                availableHeightDp = 320f,
            )
        ).isEqualTo(190f)
        assertThat(
            resolveDetailsBackdropHeightDp(1280f, 720f)
        ).isEqualTo(360f)
    }

    @Test
    fun phonePortraitFitsCompletePosterAspectRatio() {
        assertThat(
            resolveDetailsBackdropHeightDp(
                availableWidthDp = 411f,
                availableHeightDp = 891f,
            )
        ).isWithin(0.01f).of(616.5f)
    }

    @Test
    fun landscapeTabletIsCappedByAvailableHeight() {
        assertThat(
            resolveDetailsBackdropHeightDp(
                availableWidthDp = 1280f,
                availableHeightDp = 800f,
            )
        ).isEqualTo(360f)
    }

    @Test
    fun unfoldedFoldableUsesLandscapeRuleInsteadOfPortraitMinimum() {
        assertThat(resolveDetailsBackdropHeightDp(841f, 673f)).isEqualTo(360f)
    }

    @Test
    fun foldedFoldableUsesPortraitAndLandscapeGeometry() {
        val portrait = resolveDetailsBackdropHeightDp(360f, 748f)
        val landscape = resolveDetailsBackdropHeightDp(748f, 360f)

        assertThat(portrait).isWithin(0.01f).of(540f)
        assertThat(landscape).isWithin(0.01f).of(198f)
    }

    @Test
    fun portraitTabletKeepsPortraitPresentation() {
        assertThat(resolveDetailsBackdropHeightDp(800f, 1228f))
            .isWithin(0.01f).of(1031.52f)
    }

    @Test
    fun veryShortLandscapeViewportUsesAProportionalOverlayFloor() {
        assertThat(resolveDetailsBackdropHeightDp(640f, 240f))
            .isWithin(0.01f).of(163.2f)
    }

    @Test
    fun orientationChangeRestoresPortraitSizing() {
        val portrait = resolveDetailsBackdropHeightDp(411f, 891f)
        val landscape = resolveDetailsBackdropHeightDp(891f, 411f)
        val portraitAgain = resolveDetailsBackdropHeightDp(411f, 891f)

        assertThat(landscape).isLessThan(portrait)
        assertThat(portraitAgain).isEqualTo(portrait)
    }

    @Test fun shortPortraitLeavesRoomForActions() {
        assertThat(resolveDetailsBackdropHeightDp(360f, 500f)).isEqualTo(420f)
    }

    @Test fun portraitPrefersPosterAndLandscapePrefersBackdrop() {
        assertThat(mobileDetailsArtworkSource("poster", "backdrop", true))
            .isEqualTo(MobileDetailsArtworkSource("poster", true))
        assertThat(mobileDetailsArtworkSource("poster", "backdrop", false))
            .isEqualTo(MobileDetailsArtworkSource("backdrop", false))
    }

    @Test fun artworkFallbackHandlesMissingAndBlankUrls() {
        assertThat(mobileDetailsArtworkSource(" ", "backdrop", true))
            .isEqualTo(MobileDetailsArtworkSource("backdrop", false))
        assertThat(mobileDetailsArtworkSource("poster", null, false))
            .isEqualTo(MobileDetailsArtworkSource("poster", true))
        assertThat(mobileDetailsArtworkSource(null, " ", true)).isNull()
        assertThat(mobileDetailsArtworkSource("https://image/w500/backdrop.jpg", "https://image/original/backdrop.jpg", true))
            .isEqualTo(MobileDetailsArtworkSource("https://image/original/backdrop.jpg", false))
    }
}
