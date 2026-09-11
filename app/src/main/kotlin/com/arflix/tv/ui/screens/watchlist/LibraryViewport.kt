package com.arflix.tv.ui.screens.watchlist

/** Keep visible selections still; reveal only the clipped part of a library row or card. */
internal fun libraryRevealScrollDelta(itemStart: Int, itemSize: Int, viewportStart: Int, viewportEnd: Int): Int {
    val viewportSize = viewportEnd - viewportStart
    return when {
        viewportSize <= 0 -> 0
        itemSize > viewportSize -> itemStart - viewportStart
        itemStart < viewportStart -> itemStart - viewportStart
        itemStart + itemSize > viewportEnd -> itemStart + itemSize - viewportEnd
        else -> 0
    }
}
