package com.arflix.tv.ui.components

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AppBottomBarResponsiveLayoutTest {

    @Test
    fun landscapePhoneUsesCompactBottomBar() {
        val mode = appBottomBarMode(
            isTouchDevice = true,
            smallestScreenWidthDp = 411,
            screenWidthDp = 892,
            screenHeightDp = 360,
        )

        assertThat(mode).isEqualTo(AppBottomBarMode.LANDSCAPE_COMPACT)
    }

    @Test
    fun compactBottomBarKeepsTouchTargetsButUsesLessHeight() {
        val spec = appBottomBarSpec(AppBottomBarMode.LANDSCAPE_COMPACT)

        assertThat(spec.itemHeightDp).isEqualTo(48)
        assertThat(spec.iconSizeDp).isEqualTo(20)
        assertThat(spec.labelFontSizeSp).isEqualTo(10)
    }

    @Test
    fun portraitPhoneKeepsStandardBottomBar() {
        val mode = appBottomBarMode(
            isTouchDevice = true,
            smallestScreenWidthDp = 411,
            screenWidthDp = 411,
            screenHeightDp = 892,
        )

        assertThat(mode).isEqualTo(AppBottomBarMode.STANDARD)
    }

    @Test
    fun landscapeTabletKeepsStandardBottomBar() {
        val mode = appBottomBarMode(
            isTouchDevice = true,
            smallestScreenWidthDp = 700,
            screenWidthDp = 1280,
            screenHeightDp = 800,
        )

        assertThat(mode).isEqualTo(AppBottomBarMode.STANDARD)
    }

    @Test
    fun televisionKeepsStandardBottomBar() {
        val mode = appBottomBarMode(
            isTouchDevice = false,
            smallestScreenWidthDp = 720,
            screenWidthDp = 1280,
            screenHeightDp = 720,
        )

        assertThat(mode).isEqualTo(AppBottomBarMode.STANDARD)
    }

    @Test
    fun mobileFirstLaunchCloudConnectShowsBottomBar() {
        val showBar = shouldShowBottomBar(
            isMobile = true,
            currentRoute = "settings?autoCloudAuth=true",
            isFullscreenRoute = false
        )

        assertThat(showBar).isTrue()
    }

    @Test
    fun mobileProfileSelectionHidesBottomBar() {
        val showBar = shouldShowBottomBar(
            isMobile = true,
            currentRoute = com.arflix.tv.navigation.Screen.ProfileSelection.route,
            isFullscreenRoute = false
        )

        assertThat(showBar).isFalse()
    }

    @Test
    fun mobileLoginHidesBottomBar() {
        val showBar = shouldShowBottomBar(
            isMobile = true,
            currentRoute = com.arflix.tv.navigation.Screen.Login.route,
            isFullscreenRoute = false
        )

        assertThat(showBar).isFalse()
    }

    @Test
    fun mobilePlayerFullscreenHidesBottomBar() {
        val showBar = shouldShowBottomBar(
            isMobile = true,
            currentRoute = "player/movie/123",
            isFullscreenRoute = true
        )

        assertThat(showBar).isFalse()
    }

    @Test
    fun nonMobileDeviceHidesBottomBar() {
        val showBar = shouldShowBottomBar(
            isMobile = false,
            currentRoute = "settings?autoCloudAuth=true",
            isFullscreenRoute = false
        )

        assertThat(showBar).isFalse()
    }

    @Test
    fun unresolvedDestinationHidesBottomBar() {
        assertThat(shouldShowBottomBar(true, null, false)).isFalse()
    }

    @Test
    fun settingsDestinationPatternShowsBottomBar() {
        assertThat(shouldShowBottomBar(
            true,
            "settings?autoCloudAuth={autoCloudAuth}&initialSection={initialSection}&installPackUrl={installPackUrl}",
            false
        )).isTrue()
    }

    @Test
    fun liveTvGuideShowsBarButFullscreenPlaybackHidesIt() {
        assertThat(shouldShowBottomBar(true, "tv", false)).isTrue()
        assertThat(shouldShowBottomBar(true, "tv", true)).isFalse()
    }
}
