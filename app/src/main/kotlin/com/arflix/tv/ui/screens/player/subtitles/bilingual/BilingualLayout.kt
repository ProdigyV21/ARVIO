package com.arflix.tv.ui.screens.player.subtitles.bilingual

import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Pure vertical layout of the bilingual subtitle group, in pixels.
 *
 * Blocks are given top-to-bottom (as [BilingualComposer] returns them) with their measured
 * heights. The group is anchored so that its last block's bottom edge sits [bottomMarginPx]
 * above the container bottom; blocks are stacked upwards with [gapPx] between them; each block
 * may then be nudged by its own offset (positive = up).
 *
 * Guarantees:
 *  - blocks never overlap (an offset that would cross the neighbour is limited to touching it);
 *  - the group stays within [safeTopPx, containerHeightPx - safeBottomPx] whenever it fits;
 *    if the group is taller than the safe area it is top-aligned to the safe top.
 */
object BilingualLayout {

    data class Input(
        val containerHeightPx: Int,
        val blockHeightsPx: List<Int>,
        val gapPx: Int,
        val bottomMarginPx: Int,
        val blockOffsetsPx: List<Int> = blockHeightsPx.map { 0 },
        val safeTopPx: Int = 0,
        val safeBottomPx: Int = 0,
    )

    /** Returns the top y of each block, same order as [Input.blockHeightsPx]. */
    fun place(input: Input): List<Int> {
        val heights = input.blockHeightsPx.map { max(0, it) }
        val n = heights.size
        if (n == 0) return emptyList()
        val gap = max(0, input.gapPx)
        val offsets = List(n) { input.blockOffsetsPx.getOrElse(it) { 0 } }

        // 1. Natural stack, bottom-anchored.
        val tops = IntArray(n)
        var bottom = input.containerHeightPx - max(0, input.bottomMarginPx)
        for (i in n - 1 downTo 0) {
            tops[i] = bottom - heights[i]
            bottom = tops[i] - gap
        }
        // 2. Per-block offsets (positive = up).
        for (i in 0 until n) tops[i] -= offsets[i]
        // 3. No overlap: walk bottom-up, a block may not extend below the top of the one under it.
        for (i in n - 2 downTo 0) {
            val limitTop = tops[i + 1] - heights[i]
            if (tops[i] > limitTop) tops[i] = limitTop
        }
        // 4. Keep the whole group inside the safe area.
        val minTop = max(0, input.safeTopPx)
        val maxBottom = input.containerHeightPx - max(0, input.safeBottomPx)
        val groupTop = (0 until n).minOf { tops[it] }
        val groupBottom = (0 until n).maxOf { tops[it] + heights[it] }
        var shift = 0
        if (groupBottom > maxBottom) shift = maxBottom - groupBottom
        if (groupTop + shift < minTop) shift = minTop - groupTop
        return tops.map { it + shift }
    }

    /** Bottom margin in px for a "percent of the video height" setting. */
    fun bottomMarginPx(containerHeightPx: Int, bottomPercent: Int): Int =
        (containerHeightPx * (bottomPercent.coerceIn(0, 100) / 100f)).roundToInt()
}
