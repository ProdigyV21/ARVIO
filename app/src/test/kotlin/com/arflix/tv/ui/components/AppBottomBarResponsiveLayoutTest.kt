package com.arflix.tv.ui.components

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class AppBottomBarResponsiveLayoutTest {
    @Test fun contentCanDrawUnderCutoutWhileRailKeepsIndependentClearance() {
        val insets = mobileContentInsets(
            systemBars = WindowInsets(0, 24, 0, 24),
            showBottomBar = true,
        )
        val density = Density(1f)
        assertThat(insets.getLeft(density, LayoutDirection.Ltr)).isEqualTo(0)
        assertThat(navigationRailCutoutInsetPx(0, 44, 0, 0, LayoutDirection.Ltr)).isEqualTo(44)
        assertThat(navigationRailCutoutInsetPx(24, 44, 0, 0, LayoutDirection.Ltr)).isEqualTo(20)
        assertThat(navigationRailCutoutInsetPx(44, 44, 0, 0, LayoutDirection.Ltr)).isEqualTo(0)
        assertThat(navigationRailCutoutInsetPx(44, 0, 0, 0, LayoutDirection.Ltr)).isEqualTo(0)
        assertThat(insets.getTop(density)).isEqualTo(24)
        assertThat(insets.getBottom(density)).isEqualTo(0)
    }

    @Test fun rtlRailUsesRightCutoutAndSubtractsConsumedRightSystemInset() {
        assertThat(navigationRailCutoutInsetPx(0, 0, 0, 44, LayoutDirection.Rtl)).isEqualTo(44)
        assertThat(navigationRailCutoutInsetPx(0, 0, 24, 44, LayoutDirection.Rtl)).isEqualTo(20)
        assertThat(navigationRailCutoutInsetPx(0, 0, 44, 44, LayoutDirection.Rtl)).isEqualTo(0)
        assertThat(navigationRailCutoutInsetPx(0, 0, 48, 0, LayoutDirection.Rtl)).isEqualTo(0)
    }

    @Test fun cutoutOnOppositeEdgeDoesNotMoveRail() {
        assertThat(navigationRailCutoutInsetPx(0, 0, 0, 44, LayoutDirection.Ltr)).isEqualTo(0)
        assertThat(navigationRailCutoutInsetPx(0, 44, 0, 0, LayoutDirection.Rtl)).isEqualTo(0)
    }

    @Test fun subscreenFlagsOnlyAffectTheirOwnDestination() {
        assertThat(shouldShowBottomBar(true, "home", false, true, true)).isTrue()
        assertThat(shouldShowBottomBar(true, "settings", false, true, false)).isFalse()
        assertThat(shouldShowBottomBar(true, "tv", false, false, true)).isFalse()
        assertThat(shouldShowBottomBar(true, "details/movie/1", false)).isFalse()
        assertThat(shouldShowBottomBar(true, "collection/1", false)).isFalse()
        assertThat(shouldShowBottomBar(true, "settings-other", false)).isFalse()
    }

    @Test
    fun landscapePhoneUsesRail() {
        val mode = appBottomBarMode(
            isTouchDevice = true,
            smallestScreenWidthDp = 411,
            screenWidthDp = 892,
            screenHeightDp = 360,
        )

        assertThat(mode).isEqualTo(AppBottomBarMode.LANDSCAPE_RAIL)
    }

    @Test
    fun railKeepsPortraitIconSize() {
        assertThat(appBottomBarSpec(AppBottomBarMode.LANDSCAPE_RAIL).iconSizeDp)
            .isEqualTo(appBottomBarSpec(AppBottomBarMode.STANDARD).iconSizeDp)
    }

    @Test
    fun railReservesOnlySystemBottomInsetRegardlessOfMeasuredHeight() {
        for (height in listOf(240, 360, 800)) {
            for (measured in listOf(0f, 64f, 800f)) {
                for (navigation in listOf(0f, 24f, 48f)) {
                    assertThat(appBottomBarClearance(AppBottomBarMode.LANDSCAPE_RAIL,
                        true, height, measured, navigation))
                        .isEqualTo(AppBottomBarClearance(navigation, navigation))
                }
            }
        }
    }

    @Test
    fun standardBarDoesNotDoubleCountMeasuredSystemNavigation() {
        val clearance = appBottomBarClearance(AppBottomBarMode.STANDARD, true, 892, 76f, 24f)
        assertThat(clearance).isEqualTo(AppBottomBarClearance(24f, 76f))
    }

    @Test
    fun standardBarKeepsItsExistingFallbackAndScrollInset() {
        val clearance = appBottomBarClearance(AppBottomBarMode.STANDARD, true, 892, 0f, 48f)
        assertThat(clearance).isEqualTo(AppBottomBarClearance(48f, 100f))
    }

    @Test
    fun hiddenBarsLeaveNoNavigationClearanceInEitherMode() {
        for (mode in AppBottomBarMode.values()) {
            assertThat(appBottomBarClearance(mode, false, 800, 88f, 24f))
                .isEqualTo(AppBottomBarClearance(0f, 0f))
        }
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
    fun landscapeTabletUsesRail() {
        val mode = appBottomBarMode(
            isTouchDevice = true,
            smallestScreenWidthDp = 700,
            screenWidthDp = 1280,
            screenHeightDp = 800,
        )

        assertThat(mode).isEqualTo(AppBottomBarMode.LANDSCAPE_RAIL)
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
