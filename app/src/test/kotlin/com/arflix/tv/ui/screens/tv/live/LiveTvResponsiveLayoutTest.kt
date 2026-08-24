package com.arflix.tv.ui.screens.tv.live

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LiveTvResponsiveLayoutTest {

    @Test
    fun landscapePhoneUsesShortMiniPlayerSoGuideRemainsVisible() {
        val layout = liveTvMiniPlayerLayout(
            isTouchDevice = true,
            smallestScreenWidthDp = 411,
            screenWidthDp = 892,
            screenHeightDp = 360,
        )

        assertThat(layout).isEqualTo(LiveTvMiniPlayerLayout.LANDSCAPE_COMPACT)
    }

    @Test
    fun portraitPhoneKeepsTheExistingFullWidthStackedPlayer() {
        val layout = liveTvMiniPlayerLayout(
            isTouchDevice = true,
            smallestScreenWidthDp = 411,
            screenWidthDp = 411,
            screenHeightDp = 892,
        )

        assertThat(layout).isEqualTo(LiveTvMiniPlayerLayout.PORTRAIT_STACKED)
    }

    @Test
    fun tabletBoundaryKeepsTheExistingStandardPlayer() {
        val layout = liveTvMiniPlayerLayout(
            isTouchDevice = true,
            smallestScreenWidthDp = 600,
            screenWidthDp = 1280,
            screenHeightDp = 800,
        )

        assertThat(layout).isEqualTo(LiveTvMiniPlayerLayout.STANDARD)
    }

    @Test
    fun televisionKeepsTheExistingStandardPlayer() {
        val layout = liveTvMiniPlayerLayout(
            isTouchDevice = false,
            smallestScreenWidthDp = 720,
            screenWidthDp = 1280,
            screenHeightDp = 720,
        )

        assertThat(layout).isEqualTo(LiveTvMiniPlayerLayout.STANDARD)
    }

    @Test
    fun landscapePhoneMiniPlayerLeavesRoomForCategoryRailAndGuide() {
        val spec = landscapePhoneMiniPlayerSpec()

        assertThat(spec.videoWidthDp).isAtMost(180)
        assertThat(spec.videoHeightDp).isAtMost(102)
        assertThat(spec.totalHeightDp).isAtMost(116)
        assertThat(spec.showDescription).isFalse()
        assertThat(spec.showNextProgramme).isFalse()
    }
}
