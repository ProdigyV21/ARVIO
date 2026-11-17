package com.arflix.tv.ui.components

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MobileHeroResponsiveLayoutTest {
    @Test fun immersiveHeroFillsTheWidthAndLeavesTheNextCatalogVisible() {
        listOf(360f to 668f, 411f to 780f, 800f to 1128f, 320f to 420f).forEach { (width, height) ->
            val layout = resolveImmersiveMobileHeroLayout(width, height)
            assertThat(layout.cardWidthDp).isEqualTo(width)
            assertThat(layout.carouselHorizontalPaddingDp).isEqualTo(0f)
            assertThat(layout.cardHeightDp).isAtMost(height * 0.88f)
            assertThat(layout.cardHeightDp).isGreaterThan(height * 0.7f)
        }
    }


    @Test
    fun pagerSlotsMatchTheCardWidthWhenHeightCapped() {
        listOf(800f to 308f, 1280f to 748f, 800f to 900f).forEach { (width, height) ->
            val layout = resolveMobileHeroLayout(width, height)
            assertThat(width - 2f * layout.carouselHorizontalPaddingDp)
                .isWithin(0.01f).of(layout.cardWidthDp)
        }
    }

    @Test
    fun portraitPhoneRetainsOriginalThreeByFourCard() {
        val layout = resolveMobileHeroLayout(360f, 800f)

        assertThat(layout.compactContent).isFalse()
        assertThat(layout.cardWidthDp).isWithin(0.01f).of(296f)
        assertThat(layout.cardHeightDp).isWithin(0.01f).of(394.67f)
    }

    @Test
    fun landscapePhoneHeightIsBoundedByUsableViewport() {
        val layout = resolveMobileHeroLayout(800f, 308f)

        assertThat(layout.compactContent).isTrue()
        assertThat(layout.cardHeightDp).isAtMost(308f * 0.56f)
        assertThat(layout.cardWidthDp / layout.cardHeightDp).isWithin(0.01f).of(16f / 9f)
    }

    @Test
    fun largeLandscapePhoneDoesNotScaleHeroFromFullScreenWidth() {
        val layout = resolveMobileHeroLayout(915f, 359f)

        assertThat(layout.cardHeightDp).isAtMost(202f)
        assertThat(layout.cardWidthDp).isLessThan(915f - 64f)
    }

    @Test
    fun unfoldedFoldableAndTabletLandscapeRemainHeightBounded() {
        val foldable = resolveMobileHeroLayout(841f, 621f)
        val tablet = resolveMobileHeroLayout(1280f, 748f)

        assertThat(foldable.cardHeightDp).isAtMost(280f)
        assertThat(tablet.cardHeightDp).isAtMost(280f)
    }

    @Test
    fun foldedFoldableUsesPortraitAndLandscapeVariants() {
        val foldedPortrait = resolveMobileHeroLayout(360f, 748f)
        val foldedLandscape = resolveMobileHeroLayout(748f, 308f)

        assertThat(foldedPortrait.compactContent).isFalse()
        assertThat(foldedLandscape.compactContent).isTrue()
        assertThat(foldedLandscape.cardHeightDp).isLessThan(foldedPortrait.cardHeightDp)
    }

    @Test
    fun portraitTabletKeepsPortraitArtworkProportions() {
        val layout = resolveMobileHeroLayout(800f, 1228f)

        assertThat(layout.compactContent).isFalse()
        assertThat(layout.cardWidthDp / layout.cardHeightDp).isWithin(0.01f).of(3f / 4f)
    }

    @Test
    fun narrowLandscapeViewportStillFitsTheHero() {
        val layout = resolveMobileHeroLayout(640f, 240f)

        assertThat(layout.cardHeightDp).isAtMost(134.4f)
    }

    @Test
    fun orientationChangeRecomputesAndRestoresPortraitGeometry() {
        val portrait = resolveMobileHeroLayout(411f, 839f)
        val landscape = resolveMobileHeroLayout(839f, 359f)
        val portraitAgain = resolveMobileHeroLayout(411f, 839f)

        assertThat(landscape.compactContent).isTrue()
        assertThat(portraitAgain).isEqualTo(portrait)
    }
}
