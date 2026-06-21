package com.arflix.tv.ui.performance

import kotlin.math.roundToInt

private const val TV_MENU_BACKDROP_MAX_WIDTH = 960
private const val TV_MENU_BACKDROP_MAX_HEIGHT = 540
private const val TV_MENU_BLURRED_BACKDROP_MAX_WIDTH = 24
private const val TV_MENU_BLURRED_BACKDROP_MAX_HEIGHT = 14
private const val TV_MENU_LOW_DETAIL_BACKDROP_MAX_WIDTH = 480
private const val TV_MENU_LOW_DETAIL_BACKDROP_MAX_HEIGHT = 270
internal const val TV_HOME_CARD_IMAGE_DECODE_SCALE = 0.75f
internal const val TV_MENU_IMAGE_MAX_PARALLEL_REQUESTS = 4
internal const val TV_MENU_IMAGE_MAX_PARALLEL_REQUESTS_PER_HOST = 3

internal enum class TvImageRole(val cacheKeySuffix: String) {
    HOME_CARD("tv_home_card"),
    HOME_LOGO("tv_home_logo"),
    MENU_BACKDROP("tv_menu_backdrop"),
    MENU_BLURRED_BACKDROP("tv_menu_blurred_backdrop"),
    MENU_LOW_DETAIL_BACKDROP("tv_menu_low_detail_backdrop")
}

internal fun tvMenuBackdropDecodeSize(
    screenWidthPx: Int,
    screenHeightPx: Int
): Pair<Int, Int> = cappedBackdropDecodeSize(
    screenWidthPx = screenWidthPx,
    screenHeightPx = screenHeightPx,
    maxWidthPx = TV_MENU_BACKDROP_MAX_WIDTH,
    maxHeightPx = TV_MENU_BACKDROP_MAX_HEIGHT
)

internal fun tvMenuLowDetailBackdropDecodeSize(
    screenWidthPx: Int,
    screenHeightPx: Int
): Pair<Int, Int> = cappedBackdropDecodeSize(
    screenWidthPx = screenWidthPx,
    screenHeightPx = screenHeightPx,
    maxWidthPx = TV_MENU_LOW_DETAIL_BACKDROP_MAX_WIDTH,
    maxHeightPx = TV_MENU_LOW_DETAIL_BACKDROP_MAX_HEIGHT
)

internal fun tvMenuBlurredBackdropDecodeSize(
    screenWidthPx: Int,
    screenHeightPx: Int
): Pair<Int, Int> = cappedBackdropDecodeSize(
    screenWidthPx = screenWidthPx,
    screenHeightPx = screenHeightPx,
    maxWidthPx = TV_MENU_BLURRED_BACKDROP_MAX_WIDTH,
    maxHeightPx = TV_MENU_BLURRED_BACKDROP_MAX_HEIGHT
)

internal fun tvMenuImageCacheKey(
    url: String,
    widthPx: Int,
    heightPx: Int,
    role: TvImageRole,
    rgb565: Boolean
): String {
    val colorMode = if (rgb565) "rgb565" else "argb"
    return "$url|${widthPx}x$heightPx|$colorMode|${role.cacheKeySuffix}"
}

private fun cappedBackdropDecodeSize(
    screenWidthPx: Int,
    screenHeightPx: Int,
    maxWidthPx: Int,
    maxHeightPx: Int
): Pair<Int, Int> {
    val safeWidth = screenWidthPx.coerceAtLeast(1)
    val safeHeight = screenHeightPx.coerceAtLeast(1)
    val scale = minOf(
        1f,
        maxWidthPx.toFloat() / safeWidth,
        maxHeightPx.toFloat() / safeHeight
    )
    return (safeWidth * scale).roundToInt().coerceAtLeast(1) to
        (safeHeight * scale).roundToInt().coerceAtLeast(1)
}

internal fun shouldAnimateTvMenuScroll(
    smoothScrollingEnabled: Boolean,
    isFastScrolling: Boolean
): Boolean {
    if (!smoothScrollingEnabled) return false
    if (isFastScrolling) return false
    return false
}

internal fun isRecentTvMenuNavigation(
    nowMs: Long,
    lastNavEventTimeMs: Long,
    fastScrollThresholdMs: Long
): Boolean {
    if (lastNavEventTimeMs <= 0L || fastScrollThresholdMs <= 0L) return false
    return nowMs - lastNavEventTimeMs in 0L..fastScrollThresholdMs
}

internal fun shouldUseFullscreenAlphaLayer(alpha: Float): Boolean = alpha < 0.999f

internal fun shouldUseLeanTvNavigationChrome(
    isTvDevice: Boolean,
    navigationSettled: Boolean
): Boolean {
    if (!isTvDevice) return false
    if (navigationSettled) return false
    // Keep TV Home chrome stable during D-pad sweeps. Swapping full-screen
    // scrim/content branches on every focus move costs more than keeping the
    // steady composition tree alive.
    return false
}

internal fun shouldUseLowDetailTvBackdrop(
    isTvDevice: Boolean,
    currentBackdropUrl: String?,
    settledBackdropUrl: String?
): Boolean {
    if (!isTvDevice) return false
    if (currentBackdropUrl.isNullOrBlank()) return false
    if (settledBackdropUrl.isNullOrBlank()) return false
    return false
}

internal fun shouldUseSingleRailFocusOverlay(
    isCurrentRow: Boolean,
    hasFeaturedCard: Boolean,
    focusedItemIndex: Int
): Boolean = isCurrentRow && !hasFeaturedCard && focusedItemIndex >= 0

internal fun shouldPinTvHomeRowsToBottom(
    isSidebarFocused: Boolean,
    hasTvHeroTextLayer: Boolean
): Boolean = isSidebarFocused && hasTvHeroTextLayer

internal fun tvHomeRowsViewportHeightDp(
    focusedRowHeightDp: Int,
    showAdjacentRowPreview: Boolean
): Int {
    val safeFocusedRowHeight = focusedRowHeightDp.coerceAtLeast(1)
    return if (showAdjacentRowPreview) {
        safeFocusedRowHeight * 2
    } else {
        safeFocusedRowHeight
    }
}

internal fun tvHomeWarmRowRange(
    focusedRowIndex: Int,
    categoryCount: Int,
    precomposeAdjacentRows: Boolean
): IntRange {
    if (categoryCount <= 0) return IntRange.EMPTY
    val safeFocusedRowIndex = focusedRowIndex.coerceIn(0, categoryCount - 1)
    val first = if (precomposeAdjacentRows) safeFocusedRowIndex - 1 else safeFocusedRowIndex
    val last = if (precomposeAdjacentRows) safeFocusedRowIndex + 1 else safeFocusedRowIndex
    return first.coerceIn(0, categoryCount - 1)..last.coerceIn(0, categoryCount - 1)
}

internal fun tvRailFocusOverlayOffsetPx(
    itemOffsetPx: Int,
    contentStartPaddingPx: Int
): Int = itemOffsetPx + contentStartPaddingPx

internal fun shouldUsePassiveFocusableSurface(
    enableSystemFocus: Boolean,
    hasClick: Boolean,
    hasLongClick: Boolean,
    isFocusedOverride: Boolean,
    showRestBorder: Boolean,
    focusedScale: Float
): Boolean {
    return !enableSystemFocus &&
        !hasClick &&
        !hasLongClick &&
        !isFocusedOverride &&
        !showRestBorder &&
        focusedScale == 1f
}

internal fun tvMenuBackdropSwapDelayMs(
    isTvDevice: Boolean,
    hasDisplayedBackdrop: Boolean
): Long {
    return if (isTvDevice && hasDisplayedBackdrop) 240L else 0L
}
