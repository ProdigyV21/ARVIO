package com.arflix.tv.ui.performance

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MenuPerformancePolicyTest {
    @Test
    fun `caps 1080p tv backdrops to half resolution`() {
        assertEquals(
            960 to 540,
            tvMenuBackdropDecodeSize(screenWidthPx = 1920, screenHeightPx = 1080)
        )
    }

    @Test
    fun `keeps tiny screens at valid non-zero size`() {
        assertEquals(
            1 to 1,
            tvMenuBackdropDecodeSize(screenWidthPx = 0, screenHeightPx = 0)
        )
    }

    @Test
    fun `preserves aspect while respecting both caps`() {
        assertEquals(
            960 to 405,
            tvMenuBackdropDecodeSize(screenWidthPx = 2560, screenHeightPx = 1080)
        )
    }

    @Test
    fun `caps low detail tv backdrops to quarter 1080p`() {
        assertEquals(
            480 to 270,
            tvMenuLowDetailBackdropDecodeSize(screenWidthPx = 1920, screenHeightPx = 1080)
        )
    }

    @Test
    fun `caps blurred tv home backdrops to tiny cinematic resolution`() {
        assertEquals(
            24 to 14,
            tvMenuBlurredBackdropDecodeSize(screenWidthPx = 1920, screenHeightPx = 1080)
        )
    }

    @Test
    fun `uses reduced decode scale for tv home card images`() {
        assertEquals(0.75f, TV_HOME_CARD_IMAGE_DECODE_SCALE, 0.0f)
    }

    @Test
    fun `limits parallel tv image requests`() {
        assertEquals(4, TV_MENU_IMAGE_MAX_PARALLEL_REQUESTS)
        assertEquals(3, TV_MENU_IMAGE_MAX_PARALLEL_REQUESTS_PER_HOST)
    }

    @Test
    fun `builds stable tv image cache key by role size and color mode`() {
        assertEquals(
            "https://image.test/a.jpg|320x180|rgb565|tv_home_card",
            tvMenuImageCacheKey(
                url = "https://image.test/a.jpg",
                widthPx = 320,
                heightPx = 180,
                role = TvImageRole.HOME_CARD,
                rgb565 = true
            )
        )
    }

    @Test
    fun `scales display size for menu decode without dropping below one pixel`() {
        assertEquals(300 to 169, scaledDecodeSize(400, 225, 0.75f))
        assertEquals(1 to 1, scaledDecodeSize(0, 0, 0.75f))
    }

    @Test
    fun `keeps tv home warm rows scoped to focused row when adjacent precompose is disabled`() {
        assertEquals(4..4, tvHomeWarmRowRange(4, 12, precomposeAdjacentRows = false))
        assertEquals(0..0, tvHomeWarmRowRange(-2, 12, precomposeAdjacentRows = false))
        assertEquals(IntRange.EMPTY, tvHomeWarmRowRange(0, 0, precomposeAdjacentRows = false))
    }

    @Test
    fun `can precompose adjacent tv home rows when explicitly enabled`() {
        assertEquals(3..5, tvHomeWarmRowRange(4, 12, precomposeAdjacentRows = true))
        assertEquals(0..1, tvHomeWarmRowRange(0, 12, precomposeAdjacentRows = true))
        assertEquals(10..11, tvHomeWarmRowRange(11, 12, precomposeAdjacentRows = true))
    }

    @Test
    fun `preserves wide aspect for blurred tv home backdrops`() {
        assertEquals(
            24 to 10,
            tvMenuBlurredBackdropDecodeSize(screenWidthPx = 2560, screenHeightPx = 1080)
        )
    }

    @Test
    fun `preserves wide aspect for low detail tv backdrops`() {
        assertEquals(
            480 to 203,
            tvMenuLowDetailBackdropDecodeSize(screenWidthPx = 2560, screenHeightPx = 1080)
        )
    }

    @Test
    fun `keeps settled backdrop while tv focus differs from settled backdrop`() {
        assertFalse(
            shouldUseLowDetailTvBackdrop(
                isTvDevice = true,
                currentBackdropUrl = "next",
                settledBackdropUrl = "previous"
            )
        )
    }

    @Test
    fun `keeps full backdrop when focus and settled backdrop match`() {
        assertFalse(
            shouldUseLowDetailTvBackdrop(
                isTvDevice = true,
                currentBackdropUrl = "same",
                settledBackdropUrl = "same"
            )
        )
    }

    @Test
    fun `does not use low detail backdrop on touch devices`() {
        assertFalse(
            shouldUseLowDetailTvBackdrop(
                isTvDevice = false,
                currentBackdropUrl = "next",
                settledBackdropUrl = "previous"
            )
        )
    }

    @Test
    fun `uses direct scroll during fast tv navigation`() {
        assertFalse(
            shouldAnimateTvMenuScroll(
                smoothScrollingEnabled = true,
                isFastScrolling = true
            )
        )
    }

    @Test
    fun `uses direct scroll for tv menus even when navigation has settled`() {
        assertFalse(
            shouldAnimateTvMenuScroll(
                smoothScrollingEnabled = true,
                isFastScrolling = false
            )
        )
    }

    @Test
    fun `treats fresh dpad navigation as immediate fast scroll`() {
        assertTrue(
            isRecentTvMenuNavigation(
                nowMs = 1_200L,
                lastNavEventTimeMs = 1_000L,
                fastScrollThresholdMs = 650L
            )
        )
    }

    @Test
    fun `does not treat old or missing dpad navigation as fast scroll`() {
        assertFalse(
            isRecentTvMenuNavigation(
                nowMs = 2_000L,
                lastNavEventTimeMs = 1_000L,
                fastScrollThresholdMs = 650L
            )
        )
        assertFalse(
            isRecentTvMenuNavigation(
                nowMs = 2_000L,
                lastNavEventTimeMs = 0L,
                fastScrollThresholdMs = 650L
            )
        )
    }

    @Test
    fun `skips fullscreen alpha layer when fully opaque`() {
        assertFalse(shouldUseFullscreenAlphaLayer(alpha = 1f))
    }

    @Test
    fun `uses fullscreen alpha layer only while fading`() {
        assertTrue(shouldUseFullscreenAlphaLayer(alpha = 0.72f))
    }

    @Test
    fun `keeps stable chrome while tv navigation is active`() {
        assertFalse(
            shouldUseLeanTvNavigationChrome(
                isTvDevice = true,
                navigationSettled = false
            )
        )
    }

    @Test
    fun `keeps full chrome after tv navigation settles`() {
        assertFalse(
            shouldUseLeanTvNavigationChrome(
                isTvDevice = true,
                navigationSettled = true
            )
        )
    }

    @Test
    fun `keeps full chrome for touch devices`() {
        assertFalse(
            shouldUseLeanTvNavigationChrome(
                isTvDevice = false,
                navigationSettled = false
            )
        )
    }

    @Test
    fun `uses one rail focus overlay for normal focused tv rows`() {
        assertTrue(
            shouldUseSingleRailFocusOverlay(
                isCurrentRow = true,
                hasFeaturedCard = false,
                focusedItemIndex = 2
            )
        )
    }

    @Test
    fun `keeps per-card focus when featured card can expand`() {
        assertFalse(
            shouldUseSingleRailFocusOverlay(
                isCurrentRow = true,
                hasFeaturedCard = true,
                focusedItemIndex = 2
            )
        )
    }

    @Test
    fun `does not show rail focus overlay without valid focus`() {
        assertFalse(
            shouldUseSingleRailFocusOverlay(
                isCurrentRow = true,
                hasFeaturedCard = false,
                focusedItemIndex = -1
            )
        )
    }

    @Test
    fun `does not pin home rows to bottom when tv hero text layer is disabled`() {
        assertFalse(
            shouldPinTvHomeRowsToBottom(
                isSidebarFocused = true,
                hasTvHeroTextLayer = false
            )
        )
    }

    @Test
    fun `can pin home rows to bottom only while sidebar and hero text layer are active`() {
        assertTrue(
            shouldPinTvHomeRowsToBottom(
                isSidebarFocused = true,
                hasTvHeroTextLayer = true
            )
        )
        assertFalse(
            shouldPinTvHomeRowsToBottom(
                isSidebarFocused = false,
                hasTvHeroTextLayer = true
            )
        )
    }

    @Test
    fun `uses only focused home row height when adjacent row preview is disabled`() {
        assertEquals(
            202,
            tvHomeRowsViewportHeightDp(
                focusedRowHeightDp = 202,
                showAdjacentRowPreview = false
            )
        )
    }

    @Test
    fun `allows two complete home rows when adjacent row preview is enabled`() {
        assertEquals(
            404,
            tvHomeRowsViewportHeightDp(
                focusedRowHeightDp = 202,
                showAdjacentRowPreview = true
            )
        )
    }

    @Test
    fun `adds row start padding to rail focus overlay offset`() {
        assertEquals(
            72,
            tvRailFocusOverlayOffsetPx(
                itemOffsetPx = 0,
                contentStartPaddingPx = 72
            )
        )
    }

    @Test
    fun `preserves lazy row item offset when rail has already scrolled`() {
        assertEquals(
            132,
            tvRailFocusOverlayOffsetPx(
                itemOffsetPx = 60,
                contentStartPaddingPx = 72
            )
        )
    }

    @Test
    fun `uses passive focusable surface for manually focused unfocused cards`() {
        assertTrue(
            shouldUsePassiveFocusableSurface(
                enableSystemFocus = false,
                hasClick = false,
                hasLongClick = false,
                isFocusedOverride = false,
                showRestBorder = false,
                focusedScale = 1f
            )
        )
    }

    @Test
    fun `keeps interactive focusable surface when card owns focus visual`() {
        assertFalse(
            shouldUsePassiveFocusableSurface(
                enableSystemFocus = false,
                hasClick = false,
                hasLongClick = false,
                isFocusedOverride = true,
                showRestBorder = false,
                focusedScale = 1f
            )
        )
    }

    @Test
    fun `keeps interactive focusable surface when card handles clicks`() {
        assertFalse(
            shouldUsePassiveFocusableSurface(
                enableSystemFocus = false,
                hasClick = true,
                hasLongClick = false,
                isFocusedOverride = false,
                showRestBorder = false,
                focusedScale = 1f
            )
        )
    }

    @Test
    fun `delays tv backdrop swaps after first image is displayed`() {
        assertEquals(
            240L,
            tvMenuBackdropSwapDelayMs(
                isTvDevice = true,
                hasDisplayedBackdrop = true
            )
        )
    }

    @Test
    fun `does not delay first tv backdrop`() {
        assertEquals(
            0L,
            tvMenuBackdropSwapDelayMs(
                isTvDevice = true,
                hasDisplayedBackdrop = false
            )
        )
    }

    @Test
    fun `does not delay touch backdrop swaps`() {
        assertEquals(
            0L,
            tvMenuBackdropSwapDelayMs(
                isTvDevice = false,
                hasDisplayedBackdrop = true
            )
        )
    }
}
