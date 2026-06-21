package com.arflix.tv.ui.focus

internal fun edgeScrollTargetIndex(
    focusedItemIndex: Int,
    firstVisibleItemIndex: Int,
    lastVisibleItemIndex: Int,
    totalItems: Int
): Int? {
    if (totalItems <= 0 || focusedItemIndex < 0) return null

    val listLastIndex = totalItems - 1
    val firstVisible = firstVisibleItemIndex.coerceIn(0, listLastIndex)
    val lastVisible = lastVisibleItemIndex.coerceIn(firstVisible, listLastIndex)
    val visibleCount = (lastVisible - firstVisible + 1).coerceAtLeast(1)
    val maxFirstIndex = (totalItems - visibleCount).coerceAtLeast(0)

    val target = when {
        focusedItemIndex < firstVisible -> focusedItemIndex
        focusedItemIndex > lastVisible -> focusedItemIndex - visibleCount + 1
        else -> return null
    }

    return target.coerceIn(0, maxFirstIndex)
}

internal fun edgeVisibleWindowStartIndex(
    focusedItemIndex: Int,
    currentFirstVisibleItemIndex: Int,
    visibleItemCount: Int,
    totalItems: Int
): Int {
    if (totalItems <= 0 || focusedItemIndex < 0) return 0
    val safeVisibleItemCount = visibleItemCount.coerceAtLeast(1)
    val maxFirstIndex = (totalItems - safeVisibleItemCount).coerceAtLeast(0)
    val safeFirstVisible = currentFirstVisibleItemIndex.coerceIn(0, maxFirstIndex)
    val target = edgeScrollTargetIndex(
        focusedItemIndex = focusedItemIndex,
        firstVisibleItemIndex = safeFirstVisible,
        lastVisibleItemIndex = safeFirstVisible + safeVisibleItemCount - 1,
        totalItems = totalItems
    )
    return (target ?: safeFirstVisible).coerceIn(0, maxFirstIndex)
}

internal fun centeredItemScrollOffsetPx(
    itemSizePx: Int,
    viewportSizePx: Int
): Int {
    val extraSpace = (viewportSizePx - itemSizePx).coerceAtLeast(0)
    return -(extraSpace / 2)
}
