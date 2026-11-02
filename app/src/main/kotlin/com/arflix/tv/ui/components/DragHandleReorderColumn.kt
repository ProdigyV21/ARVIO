package com.arflix.tv.ui.components

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.arflix.tv.R
import com.arflix.tv.util.withItemMoved
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * Touch reordering for a short list that sits inside a page which already scrolls: hold the handle
 * at the end of a row and drag it. The held row floats under the finger, the others make room, and
 * the page scrolls by itself while the row is held against the top or bottom of the screen.
 *
 * The sibling of [DragReorderState], which does the same for a list that is a LazyColumn of its
 * own (the IPTV categories). The settings pages that carry playlists, portals, catalogs and stream
 * sources are plain scrolling columns full of other rows, so a LazyColumn cannot be put inside them;
 * this one works on an ordinary Column and borrows the page's scroll instead.
 *
 * Three things it does differently from the lazy one, each for a reason:
 *
 *  1. **It is the handle that is held, not the row.** These rows already use a long press for the
 *     selection mode (bulk delete) and a tap for editing; the handle is the one place on the row
 *     that has no other meaning. Like the IPTV categories it is held for a moment first: the phone
 *     vibrates and the row is framed, so the move is announced before the finger travels. A drag
 *     that started on the first movement gave no sign of its start at all.
 *  2. **The order is kept locally while the row is held and stored once, on release.** The stores
 *     behind these lists read the order, change it and write it back; several moves fired within
 *     one frame would each read the same old order and undo one another. One move per drop, of as
 *     many places as the row travelled, cannot race.
 *  3. **Slot positions are added up from the row heights, not read from the last layout.** A swap
 *     arrives in the layout a frame later; positions read from the layout in between belong to the
 *     old order, and a second swap computed from them would land on the wrong row.
 *
 * Usage:
 * ```
 * DragHandleReorderColumn(
 *     items = rows, key = { it.id }, pageScrollState = pageScroll,
 *     onDrop = { row, from, to -> viewModel.moveBy(row.id, to - from) },
 *     heldBackground = BackgroundElevated, heldAccent = accent
 * ) { row, index, previous, handle, isHeld ->
 *     Row { /* ... */ Icon(Icons.Default.DragHandle, null, Modifier.then(handle)) }
 * }
 * ```
 */
@Stable
class DragHandleReorderState internal constructor(
    private val autoScrollEdgePx: Float,
    private val autoScrollSpeedPx: Float,
    private val onGrab: () -> Unit
) {
    /** The key of the row currently held, or null while nothing is held. */
    var draggedKey by mutableStateOf<Any?>(null)
        private set

    /**
     * The order shown while a row is held, and after release until the stored order comes back.
     * Null whenever the list simply shows what it is given.
     */
    internal var localOrder by mutableStateOf<List<Any>?>(null)
        private set

    /** The order the list was given when the row was picked up - see [forgetLocalOrderIfStale]. */
    private var orderAtPickUp: List<Any>? = null
    private var pickUpIndex = -1

    /** Updated on every composition with the keys as currently shown. */
    internal var shownKeys: List<Any> = emptyList()
    internal var scrollState: ScrollState? = null
    internal var onDrop: (key: Any, from: Int, to: Int) -> Unit = { _, _, _ -> }

    /** Where each row was laid out, in the column's own coordinates. Written by layout only. */
    internal val tops = HashMap<Any, Int>()
    internal val sizes = HashMap<Any, Int>()
    internal var columnCoordinates: LayoutCoordinates? = null

    /** Top edge of the held row in the column's coordinates - where the finger put it. */
    private var floatingTop by mutableFloatStateOf(0f)
    private var floatingSize = 0

    fun isDragging(key: Any): Boolean = draggedKey == key

    /**
     * How far the held row has to be shifted from where the column lays it out. Read while drawing.
     *
     * 🔴 The row's laid-out top is worked out from the shown order, not read from the last layout.
     * The drawing is redone as soon as the order changes, which is before the column has placed the
     * row in its new slot; a top read from the layout at that moment is the OLD slot's, and the row
     * would be drawn a whole row away from the finger until the finger moved again.
     */
    internal fun translationFor(key: Any): Float {
        val order = localOrder
        val laidOutTop = if (order != null && key in order) {
            order.takeWhile { it != key }.sumOf { sizes[it] ?: 0 }
        } else {
            tops[key] ?: return 0f
        }
        return floatingTop - laidOutTop
    }

    internal fun onDragStart(key: Any) {
        // A row dropped a moment ago is still waiting for the stored order. Picking up another one
        // now would count its move from an order the store does not have yet.
        if (draggedKey != null || localOrder != null) return
        val keys = shownKeys
        val index = keys.indexOf(key)
        if (index < 0) return
        floatingTop = (tops[key] ?: return).toFloat()
        floatingSize = sizes[key] ?: 0
        orderAtPickUp = keys
        pickUpIndex = index
        localOrder = keys
        draggedKey = key
        onGrab()
    }

    internal fun onDrag(deltaY: Float) {
        if (draggedKey == null) return
        floatingTop = clampToVisible(floatingTop + deltaY)
        applyMove()
    }

    internal fun onDragStop() {
        val key = draggedKey ?: return
        val order = localOrder.orEmpty()
        val to = order.indexOf(key)
        val from = pickUpIndex
        draggedKey = null
        pickUpIndex = -1
        if (to < 0 || to == from) {
            // Put back where it came from: nothing to store, and nothing to wait for.
            localOrder = null
            orderAtPickUp = null
            return
        }
        onDrop(key, from, to)
    }

    /**
     * Ends a drag whose gesture went away without finishing - the handle left the screen, the row
     * was removed, the selection mode took the handle away. Nothing is stored, the list shows what
     * it is given, and above all the edge scrolling stops: it runs for as long as a row is held.
     */
    internal fun cancelDrag(key: Any) {
        if (draggedKey != key) return
        draggedKey = null
        pickUpIndex = -1
        localOrder = null
        orderAtPickUp = null
    }

    /**
     * Source positions must stay fixed throughout a gesture. A changed order or membership
     * cancels a held row rather than applying its old movement to a different list. After a
     * drop, a changed source order acknowledges the save and releases the optimistic order.
     */
    internal fun forgetLocalOrderIfStale(keys: List<Any>) {
        if (localOrder == null || keys == orderAtPickUp) return
        val heldKey = draggedKey
        if (heldKey != null) cancelDrag(heldKey) else forgetLocalOrder()
    }

    /** Gives up on an answer that never came, so the list cannot be stuck showing its own order. */
    internal fun forgetLocalOrder() {
        if (draggedKey != null) return
        localOrder = null
        orderAtPickUp = null
    }

    /** Moves the held row to the slot its middle is over, if that is not where it already is. */
    private fun applyMove() {
        val key = draggedKey ?: return
        val order = localOrder ?: return
        // A row can change height while it is held (a catalog gains or loses its pack header as its
        // neighbour changes), so its height is read afresh rather than remembered from the pick-up.
        floatingSize = sizes[key] ?: floatingSize
        val slots = contiguousSlots(order.map { sizes[it] ?: 0 })
        val target = reorderTargetIndex(slots, floatingTop + floatingSize / 2f, order.size) ?: return
        val current = order.indexOf(key)
        if (current < 0 || target == current) return
        localOrder = order.withItemMoved(current, target)
    }

    /** One frame of page scrolling while the held row rests against the top or bottom of the screen. */
    internal suspend fun autoScrollStep() {
        if (draggedKey == null) return
        val scroll = scrollState ?: return
        val visible = visibleRange() ?: return
        val columnHeight = columnCoordinates?.size?.height ?: return
        val delta = autoScrollDelta(
            rowTop = floatingTop,
            rowSize = floatingSize,
            viewportStart = visible.first,
            viewportEnd = visible.second,
            edgeSize = autoScrollEdgePx,
            maxSpeed = autoScrollSpeedPx
        )
        // Only scroll towards a part of THIS list that is out of sight. A list that starts in the
        // middle of the screen has its top edge in view, and the page should not run away upwards
        // just because the row was dragged to the first place.
        val worthScrolling = (delta < 0f && visible.first > 0.5f) ||
            (delta > 0f && visible.second < columnHeight - 0.5f)
        if (!worthScrolling) return
        val consumed = scroll.scrollBy(delta)
        if (consumed == 0f) return
        // The finger stays where it is on the glass while the page moves under it, so in the
        // column's own coordinates the row has travelled exactly as far as the page did.
        floatingTop = clampToVisible(floatingTop + consumed)
        applyMove()
    }

    /** The part of the column that is on screen, in the column's own coordinates. */
    private fun visibleRange(): Pair<Float, Float>? {
        val coordinates = columnCoordinates?.takeIf { it.isAttached } ?: return null
        val top = coordinates.positionInWindow().y
        val bounds = coordinates.boundsInWindow()
        return (bounds.top - top) to (bounds.bottom - top)
    }

    private fun clampToVisible(desiredTop: Float): Float {
        val columnHeight = columnCoordinates?.size?.height ?: return desiredTop
        val visible = visibleRange()
        val start = maxOf(0f, visible?.first ?: 0f)
        val end = minOf(columnHeight.toFloat(), visible?.second ?: columnHeight.toFloat())
        return clampFloatingTop(desiredTop, start.roundToInt(), end.roundToInt(), floatingSize)
    }
}

/**
 * A column of [items] that can be put in a new order by holding the handle each row places with the
 * Modifier it is given. [onDrop] is called once, on release, with the item, the place it was picked
 * up from and the place it was put down - nothing is reported while the finger is still moving.
 *
 * [pageScrollState] is the scroll of the page the column sits in; without it everything works except
 * scrolling at the edges. [heldBackground] has to be opaque: the held row is drawn over the others.
 */
@Composable
fun <T> DragHandleReorderColumn(
    items: List<T>,
    key: (T) -> Any,
    onDrop: (item: T, from: Int, to: Int) -> Unit,
    heldBackground: Color,
    heldAccent: Color,
    modifier: Modifier = Modifier,
    pageScrollState: ScrollState? = null,
    enabled: Boolean = true,
    row: @Composable (item: T, index: Int, previous: T?, handle: Modifier, isHeld: Boolean) -> Unit
) {
    val haptics = LocalHapticFeedback.current
    val density = LocalDensity.current
    val state = remember(density) {
        DragHandleReorderState(
            autoScrollEdgePx = with(density) { handleAutoScrollEdge.toPx() },
            autoScrollSpeedPx = with(density) { handleAutoScrollSpeedPerFrame.toPx() },
            onGrab = { haptics.performHapticFeedback(HapticFeedbackType.LongPress) }
        )
    }
    val itemsByKey = items.associateBy(key)
    val sourceKeys = items.map(key)
    val currentOnDrop by rememberUpdatedState(onDrop)
    val local = state.localOrder
    // A local order that no longer holds the same rows (one was added or removed meanwhile) is of
    // no use: show what the list was given.
    val shownKeys = if (local != null && local.size == sourceKeys.size && local.toSet() == sourceKeys.toSet()) {
        local
    } else {
        sourceKeys
    }

    SideEffect {
        // Validate before another pointer event can commit a drop against new source positions.
        state.forgetLocalOrderIfStale(sourceKeys)
        state.shownKeys = state.localOrder ?: sourceKeys
        state.scrollState = pageScrollState
        state.onDrop = { k, from, to -> itemsByKey[k]?.let { currentOnDrop(it, from, to) } }
    }
    LaunchedEffect(state.draggedKey, local) {
        if (state.draggedKey == null) {
            if (local != null) {
                delay(LOCAL_ORDER_TIMEOUT_MS)
                state.forgetLocalOrder()
            }
            return@LaunchedEffect
        }
        while (isActive) {
            withFrameNanos { }
            state.autoScrollStep()
        }
    }

    val dragLabel = stringResource(R.string.settings_cd_drag_reorder)
    val moveUpLabel = stringResource(R.string.live_menu_move_up)
    val moveDownLabel = stringResource(R.string.live_menu_move_down)

    Column(modifier = modifier.onGloballyPositioned { state.columnCoordinates = it }) {
        shownKeys.forEachIndexed { index, itemKey ->
            val item = itemsByKey[itemKey] ?: return@forEachIndexed
            val previous = shownKeys.getOrNull(index - 1)?.let { itemsByKey[it] }
            // Keyed, so that a row which moves keeps its composition - and with it the gesture
            // that is moving it. Without the key the gesture would end at the first step.
            key(itemKey) {
                val isHeld = state.isDragging(itemKey)
                val scope = rememberCoroutineScope()
                // Plain state, set during layout: the drawing of the same frame already sees it, so
                // a row making room never shows for a frame in its new place before sliding there.
                var slide by remember { mutableFloatStateOf(0f) }
                val slideJob = remember { arrayOfNulls<Job>(1) }
                val lastTop = remember { IntArray(1) { Int.MIN_VALUE } }
                DisposableEffect(itemKey) {
                    onDispose { state.cancelDrag(itemKey) }
                }
                val handle = if (enabled) {
                    Modifier
                        .pointerInput(itemKey) {
                            try {
                                detectDragGesturesAfterLongPress(
                                    onDragStart = { state.onDragStart(itemKey) },
                                    onDragEnd = { state.onDragStop() },
                                    onDragCancel = { state.cancelDrag(itemKey) },
                                    onDrag = { change, amount ->
                                        change.consume()
                                        state.onDrag(amount.y)
                                    }
                                )
                            } finally {
                                // A gesture cut off from outside reports neither end nor cancel.
                                state.cancelDrag(itemKey)
                            }
                        }
                        // A tap on the handle does nothing - and, above all, does not fall through
                        // to the row, where it would edit, toggle or start the selection mode.
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {}
                        )
                        // Dragging is no use to a screen reader: it gets "move up" and "move down".
                        .clearAndSetSemantics {
                            contentDescription = dragLabel
                            customActions = buildList {
                                if (index > 0) {
                                    add(CustomAccessibilityAction(moveUpLabel) {
                                        state.onDrop(itemKey, index, index - 1); true
                                    })
                                }
                                if (index < shownKeys.lastIndex) {
                                    add(CustomAccessibilityAction(moveDownLabel) {
                                        state.onDrop(itemKey, index, index + 1); true
                                    })
                                }
                            }
                        }
                } else {
                    Modifier
                }
                Box(
                    modifier = Modifier
                        // First in the chain, so it reports where the column put the row, before
                        // any of the shifting below is applied.
                        .onPlaced { coordinates ->
                            val top = coordinates.positionInParent().y.roundToInt()
                            state.tops[itemKey] = top
                            state.sizes[itemKey] = coordinates.size.height
                            val previousTop = lastTop[0]
                            lastTop[0] = top
                            // The rows that make room slide into their new place instead of jumping.
                            if (previousTop != Int.MIN_VALUE && previousTop != top &&
                                state.draggedKey != null && !state.isDragging(itemKey)
                            ) {
                                slide += (previousTop - top)
                                slideJob[0]?.cancel()
                                slideJob[0] = scope.launch {
                                    animate(slide, 0f, animationSpec = tween(SLIDE_MS)) { value, _ ->
                                        slide = value
                                    }
                                }
                            }
                        }
                        .then(
                            if (isHeld) {
                                Modifier
                                    .zIndex(1f)
                                    .graphicsLayer {
                                        translationY = state.translationFor(itemKey)
                                        shadowElevation = handleDraggedElevation.toPx()
                                        shape = RoundedCornerShape(handleDraggedCorner)
                                        clip = false
                                    }
                                    .background(heldBackground)
                                    .background(heldAccent.copy(alpha = 0.20f))
                                    .border(2.dp, heldAccent, RoundedCornerShape(handleDraggedCorner))
                            } else {
                                Modifier.graphicsLayer { translationY = slide }
                            }
                        )
                ) {
                    row(item, index, previous, handle, isHeld)
                }
            }
        }
    }
}

/** Slots for rows stacked without gaps, in the order given, starting at zero. */
internal fun contiguousSlots(sizes: List<Int>): List<ReorderSlot> {
    var top = 0
    return sizes.mapIndexed { index, size ->
        ReorderSlot(index, top, size).also { top += size }
    }
}

private const val LOCAL_ORDER_TIMEOUT_MS = 3_000L
private const val SLIDE_MS = 150
private val handleAutoScrollEdge = 72.dp
private val handleAutoScrollSpeedPerFrame = 10.dp
private val handleDraggedElevation = 12.dp
private val handleDraggedCorner = 12.dp
