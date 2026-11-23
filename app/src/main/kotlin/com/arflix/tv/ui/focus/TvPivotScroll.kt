package com.arflix.tv.ui.focus

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.BringIntoViewSpec
import androidx.compose.foundation.gestures.LocalBringIntoViewSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import kotlin.math.abs

/**
 * The "pivot" scrolling the old androidx.tv TvLazyRow/TvLazyColumn did, for the
 * standard Compose lazy lists.
 *
 * tv-foundation's lazy lists were removed after 1.0.0-alpha11; the replacement is a
 * normal LazyRow/LazyColumn with a [BringIntoViewSpec]. When D-pad focus moves to an
 * item, the list scrolls so the item's leading edge sits at [parentFraction] of the
 * viewport (minus [childFraction] of the item), which is the same maths and the same
 * defaults (0.3 / 0) as TvLazyRow's PivotOffsets.
 */
@OptIn(ExperimentalFoundationApi::class)
@Immutable
internal class TvPivotBringIntoViewSpec(
    private val parentFraction: Float = 0.3f,
    private val childFraction: Float = 0f,
) : BringIntoViewSpec {
    override fun calculateScrollDistance(offset: Float, size: Float, containerSize: Float): Float =
        tvPivotScrollDistance(offset, size, containerSize, parentFraction, childFraction)

    override fun equals(other: Any?): Boolean =
        other is TvPivotBringIntoViewSpec && other.parentFraction == parentFraction && other.childFraction == childFraction

    override fun hashCode(): Int = 31 * parentFraction.hashCode() + childFraction.hashCode()
}

/** Pure pivot maths, kept separate so it can be unit tested. */
internal fun tvPivotScrollDistance(
    offset: Float,
    size: Float,
    containerSize: Float,
    parentFraction: Float = 0.3f,
    childFraction: Float = 0f,
): Float {
    val itemSize = abs(size)
    val childSmallerThanParent = itemSize <= containerSize
    val initialTarget = parentFraction * containerSize - childFraction * itemSize
    val spaceAvailable = containerSize - initialTarget
    val target = if (childSmallerThanParent && spaceAvailable < itemSize) containerSize - itemSize else initialTarget
    return offset - target
}

/**
 * Wraps a lazy list so D-pad focus scrolls it like TvLazyRow/TvLazyColumn did.
 * Only the list itself should be inside [content]: nested scrollables inherit the spec.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TvPivotScroll(
    parentFraction: Float = 0.3f,
    childFraction: Float = 0f,
    content: @Composable () -> Unit,
) {
    val spec = remember(parentFraction, childFraction) { TvPivotBringIntoViewSpec(parentFraction, childFraction) }
    CompositionLocalProvider(LocalBringIntoViewSpec provides spec, content = content)
}
