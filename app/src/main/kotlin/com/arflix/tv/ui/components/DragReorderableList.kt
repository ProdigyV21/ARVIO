package com.arflix.tv.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.isActive

/**
 * Touch reordering for a [androidx.compose.foundation.lazy.LazyColumn]: press and hold any row,
 * then drag it. The held row floats above the list while the others make room for it.
 *
 * Three properties make this different from the hand-rolled drag handles elsewhere in the app
 * (playlists, portals, catalogs), and they are the reason this lives in its own file:
 *
 *  1. **The gesture is keyed to the row's key, never to its position.** A row that moves while the
 *     finger is still down keeps the same key, so the gesture survives every step. Keying on the
 *     index cancels the gesture after the first move, which limits the user to one position per
 *     press.
 *  2. **No row height is ever assumed.** Every position is read from [LazyListState.layoutInfo],
 *     so rows of different heights, and rows that change height, stay under the finger.
 *  3. **The list scrolls itself** while a row is held against the top or bottom edge, which is what
 *     makes lists longer than one screen reorderable at all.
 *
 * The held row is drawn at the position the finger put it, expressed in the list's own viewport
 * coordinates. Its translation is recomputed from the live layout on every frame rather than
 * accumulated, so the row never jumps when the underlying order arrives a frame or two late -
 * which it does here, because the order is persisted before it comes back through the UI state.
 *
 * Usage:
 * ```
 * val listState = rememberLazyListState()
 * val reorderState = rememberDragReorderState(listState) { key, from, to -> /* move one step */ }
 * LazyColumn(state = listState, userScrollEnabled = reorderState.draggedKey == null) {
 *     items(rows, key = { it.id }) { row ->
 *         Row(modifier = dragReorderItem(reorderState, row.id)) { /* ... */ }
 *     }
 * }
 * ```
 * While a row is held the list is taken off the finger (`userScrollEnabled`): it still scrolls, but
 * only by itself, at the edges, which is the only scrolling that belongs to a move.
 *
 * ⚠️ The list must not use a top `contentPadding`: row offsets and the finger position are compared
 * in the same coordinate space, and top padding shifts the two apart.
 */
@Stable
class DragReorderState internal constructor(
    private val listState: LazyListState,
    private val autoScrollEdgePx: Float,
    private val autoScrollSpeedPx: Float,
    private val onGrab: () -> Unit,
    private val onMove: (key: Any, from: Int, to: Int) -> Unit
) {
    /** The key of the row currently held, or null while nothing is held. */
    var draggedKey by mutableStateOf<Any?>(null)
        private set

    /** Set when a row was picked up and put down again without being moved. */
    private var pickedUpWithoutMoving = false
    private var movedWhileHeld = false
    /** The row that was just put down, and where it was sent - see [settleAfterDrop]. */
    private var droppedKey: Any? = null
    private var droppedIndex = -1
    /** Distance the row has asked to climb since the last step it took - see [climbOneRow]. */
    private var climbProgress = 0f

    /** Top edge of the held row in viewport coordinates - where the finger put it. */
    private var floatingTop = 0f
    private var floatingSize = 0
    /** The position this row has been asked to end up at, including moves not yet echoed back. */
    private var requestedIndex = -1

    fun isDragging(key: Any): Boolean = draggedKey == key

    /**
     * Whether the tap that is arriving right now is only the release of a row that was picked up,
     * and should therefore do nothing. Answers once and forgets, so the next real tap counts.
     *
     * 🔴 **A row cannot simply be given a long-press handler of its own instead.** A long-press
     * handler swallows every finger movement that follows it, which is precisely the movement the
     * move needs - the row would light up and then sit still. So the pick-up is detected in one
     * place only, here, and the tap asks afterwards whether it still means anything.
     */
    fun consumeClickAfterPickUp(): Boolean {
        val suppress = pickedUpWithoutMoving
        pickedUpWithoutMoving = false
        return suppress
    }

    /**
     * How far the held row has to be shifted from where the list laid it out to sit where the
     * finger put it. Read live during drawing, so a late-arriving order correction is absorbed
     * instead of showing up as a jump.
     */
    internal fun translationFor(key: Any): Float {
        val item = listState.layoutInfo.visibleItemsInfo.firstOrNull { it.key == key } ?: return 0f
        return floatingTop - item.offset
    }

    internal fun onDragStart(key: Any) {
        val grabbed = listState.layoutInfo.visibleItemsInfo.firstOrNull { it.key == key } ?: return
        floatingTop = grabbed.offset.toFloat()
        floatingSize = grabbed.size
        requestedIndex = grabbed.index
        movedWhileHeld = false
        pickedUpWithoutMoving = false
        draggedKey = key
        onGrab()
    }

    internal fun onDrag(deltaY: Float) {
        if (draggedKey == null) return
        movedWhileHeld = true
        val layout = listState.layoutInfo
        // The finger may leave the list - above it, below it, off the screen - but the row it
        // carries may not: a list clips what hangs out of it, so a row that followed the finger out
        // would simply disappear, leaving an empty gap behind and no way to tell where it went.
        floatingTop = clampFloatingTop(
            desiredTop = floatingTop + deltaY,
            viewportStart = layout.viewportStartOffset,
            viewportEnd = layout.viewportEndOffset,
            rowSize = floatingSize
        )
        // At an edge the movement belongs to the edge handling below; correcting the position from
        // the finger as well would pull the row back down while it is trying to climb.
        if (!restingAgainstAnEdge()) applyMoves()
    }

    internal fun onDragStop() {
        // A row that was picked up and never moved is released like an ordinary tap, and the tap
        // would show or hide the group - which is not what changing one's mind should do. A row
        // that WAS moved never reaches the tap: the movement is claimed by the move itself.
        pickedUpWithoutMoving = draggedKey != null && !movedWhileHeld
        droppedKey = if (movedWhileHeld) draggedKey else null
        droppedIndex = requestedIndex
        movedWhileHeld = false
        draggedKey = null
        requestedIndex = -1
    }

    /**
     * Moves the held row one step at a time until it sits where the finger is. Steps are counted
     * against [requestedIndex] rather than against the rendered position, so a step that has been
     * asked for but has not come back through the UI state yet is never asked for twice.
     */
    private fun applyMoves() {
        val key = draggedKey ?: return
        val layout = listState.layoutInfo
        val slots = layout.visibleItemsInfo.map { ReorderSlot(it.index, it.offset, it.size) }
        val target = reorderTargetIndex(slots, floatingTop + floatingSize / 2f, layout.totalItemsCount) ?: return
        if (target == requestedIndex) return
        val step = if (target > requestedIndex) 1 else -1
        while (requestedIndex != target) {
            onMove(key, requestedIndex, requestedIndex + step)
            requestedIndex += step
        }
    }

    /**
     * After a row is put down: the last move can arrive a moment after the finger has left, and if
     * it takes the row above the top of the screen the list stays where it is and the group is gone
     * from view. Waits a few frames for the order to come back, then brings it into view.
     */
    internal suspend fun settleAfterDrop() {
        val key = droppedKey ?: return
        val index = droppedIndex
        droppedKey = null
        droppedIndex = -1
        if (index < 0) return
        repeat(SETTLE_FRAMES) {
            withFrameNanos { }
            val layout = listState.layoutInfo
            if (layout.visibleItemsInfo.any { it.key == key }) return
            val firstVisible = layout.visibleItemsInfo.firstOrNull() ?: return
            if (isAboveViewport(rowIndex = index, firstVisibleIndex = firstVisible.index)) {
                listState.animateScrollToItem(index)
                return
            }
        }
    }

    /**
     * One frame of edge scrolling, driven from the composition (see [rememberDragReorderState]).
     *
     * 🔴 **It is the HELD ROW that is measured against the edges, not the finger.** The row's
     * position is this class's own arithmetic and is known exactly; the finger arrives through a
     * chain of gesture coordinates that is easy to be subtly wrong about - and a scroll that never
     * happens is impossible to tell apart from a scroll that was never asked for.
     */
    internal suspend fun autoScrollStep() {
        val key = draggedKey ?: return
        // Being picked up is not a request to scroll: a row grabbed at the edge and held still
        // stays where it is until the finger actually moves it.
        if (!movedWhileHeld) return
        val delta = edgeSpeed()
        if (delta == 0f) {
            climbProgress = 0f
            return
        }
        if (delta > 0f) {
            // Downwards is the safe direction: a list keeps its place by its FIRST row, and a row
            // moving down never crosses that one, so nothing is displaced. Plain scrolling.
            listState.scrollBy(delta)
            applyMoves()
            return
        }
        climbOneRow(key, -delta)
    }

    /**
     * Climbing at the top edge, one row at a time.
     *
     * 🔴 **Why this is not simply scrolling in the other direction.** A list keeps its place by
     * holding on to whatever row is at the top of the screen. Move the held row ABOVE that one and
     * the list faithfully keeps the other row where it was - so the held row is put off screen, and
     * a row that is off screen no longer exists as far as the finger on it is concerned: the whole
     * move breaks off mid-way and the group is left wherever it had got to.
     *
     * ⭐ The cure is to make the held row itself the row the list holds on to. Pinned to the top,
     * it is what the list keeps in place, so every step it takes past the rows above leaves it
     * exactly where it is - and the list slides underneath it instead.
     */
    private suspend fun climbOneRow(key: Any, speed: Float) {
        if (floatingSize <= 0) return
        climbProgress += speed
        if (!hasClimbedAWholeRow(climbProgress, floatingSize)) return
        val current = listState.layoutInfo.visibleItemsInfo.firstOrNull { it.key == key } ?: return
        if (current.index <= 0) {
            // Already at the very top: nothing above it to climb past.
            climbProgress = 0f
            return
        }
        climbProgress -= floatingSize
        listState.scrollToItem(current.index)
        onMove(key, current.index, current.index - 1)
        requestedIndex = current.index - 1
    }

    /** Whether the row is pressed against one of the list's ends, and how fast it wants to go. */
    private fun edgeSpeed(): Float {
        val layout = listState.layoutInfo
        return autoScrollDelta(
            rowTop = floatingTop,
            rowSize = floatingSize,
            viewportStart = layout.viewportStartOffset.toFloat(),
            viewportEnd = layout.viewportEndOffset.toFloat(),
            edgeSize = autoScrollEdgePx,
            maxSpeed = autoScrollSpeedPx
        )
    }

    private fun restingAgainstAnEdge(): Boolean = edgeSpeed() != 0f
}

/**
 * Remembers the state that [Modifier.dragReorderable] and [dragReorderItem] share.
 *
 * [onMove] is called once per position the row passes, with the row's key and the two positions.
 * Callers that address their rows by name - as they should, see property 1 above - can use the key
 * and ignore the indices.
 */
@Composable
fun rememberDragReorderState(
    listState: LazyListState,
    onMove: (key: Any, from: Int, to: Int) -> Unit
): DragReorderState {
    val haptics: HapticFeedback = LocalHapticFeedback.current
    val currentOnMove by rememberUpdatedState(onMove)
    val density = LocalDensity.current
    val edgePx = with(density) { autoScrollEdge.toPx() }
    val speedPx = with(density) { autoScrollSpeedPerFrame.toPx() }
    val state = remember(listState, edgePx, speedPx) {
        DragReorderState(
            listState = listState,
            autoScrollEdgePx = edgePx,
            autoScrollSpeedPx = speedPx,
            onGrab = { haptics.performHapticFeedback(HapticFeedbackType.LongPress) },
            onMove = { key, from, to -> currentOnMove(key, from, to) }
        )
    }
    // The edge scrolling runs here rather than in a coroutine the state starts for itself: tied to
    // the composition it is started and stopped by the drag it belongs to, and it is beyond doubt
    // that it is clocked frame by frame.
    LaunchedEffect(state.draggedKey) {
        if (state.draggedKey == null) {
            state.settleAfterDrop()
            return@LaunchedEffect
        }
        while (isActive) {
            withFrameNanos { }
            state.autoScrollStep()
        }
    }
    return state
}

/**
 * Put on every row, and it does both halves: it takes the press and hold that picks the row up and
 * the drag that moves it, and it lifts the held row above the list while the others slide out of
 * its way. The sliding is [Modifier.animateItemPlacement], which needs nothing but stable keys.
 *
 * Called from inside the item itself - `modifier = dragReorderItem(state, key)`.
 *
 * 🔴 **The gesture belongs on the row, not on the list.** A list scrolls its own content, and it
 * sees a drag before anything wrapped around it does; a detector sitting outside the list is handed
 * a gesture the list has already taken for scrolling, so the row lights up when it is picked up and
 * then refuses to move. Inside the row the drag is claimed first, and the list never sees it.
 *
 * The gesture is kept at the FRONT of the modifier chain and keyed by the row's key, so that
 * picking a row up - which changes everything behind it in the chain - cannot restart the very
 * gesture that is running.
 */
@OptIn(ExperimentalFoundationApi::class)
fun LazyItemScope.dragReorderItem(state: DragReorderState, key: Any): Modifier = Modifier
    .pointerInput(key) {
        detectDragGesturesAfterLongPress(
            onDragStart = { state.onDragStart(key) },
            onDrag = { change, amount ->
                change.consume()
                state.onDrag(amount.y)
            },
            onDragEnd = { state.onDragStop() },
            onDragCancel = { state.onDragStop() }
        )
    }
    .then(
        if (state.isDragging(key)) {
            Modifier
                .zIndex(1f)
                .graphicsLayer {
                    translationY = state.translationFor(key)
                    // Deliberately not scaled up: a row wider than the list is cut off at both
                    // sides by the list, and a frame missing its left and right edge looks broken
                    // rather than lifted. The shadow does the lifting.
                    shadowElevation = draggedElevation.toPx()
                    shape = RoundedCornerShape(draggedCorner)
                    clip = false
                }
        } else {
            Modifier.animateItemPlacement()
        }
    )

// ---------------------------------------------------------------------------
// The arithmetic, kept free of Compose so it can be tested on its own.
// ---------------------------------------------------------------------------

/** One row as the list laid it out: where it starts and how tall it actually is. */
internal data class ReorderSlot(val index: Int, val offset: Int, val size: Int)

/**
 * The position the held row belongs at: the row whose slot its middle currently sits in.
 * Past either end of what is on screen it sticks to the outermost visible row, which is where
 * edge scrolling takes over. Returns null when there is nothing to reorder.
 */
internal fun reorderTargetIndex(slots: List<ReorderSlot>, floatingCenter: Float, itemCount: Int): Int? {
    if (slots.isEmpty() || itemCount <= 0) return null
    val hit = slots.firstOrNull { floatingCenter >= it.offset && floatingCenter < it.offset + it.size }
    val index = when {
        hit != null -> hit.index
        floatingCenter < slots.first().offset -> slots.first().index
        else -> slots.last().index
    }
    return index.coerceIn(0, itemCount - 1)
}

/**
 * Whether enough distance has been asked for to move the held row a whole row further.
 */
internal fun hasClimbedAWholeRow(progress: Float, rowSize: Int): Boolean =
    rowSize > 0 && progress >= rowSize

/**
 * Whether a row sits above everything the list is showing - the one direction in which a list can
 * put a row out of sight without moving itself.
 */
internal fun isAboveViewport(rowIndex: Int, firstVisibleIndex: Int): Boolean =
    rowIndex in 0 until firstVisibleIndex

/**
 * Keeps the held row inside the part of the list that is actually on screen. Everything is in the
 * list's own coordinates, and a viewport too short for the row pins it to the top rather than
 * returning something impossible.
 */
internal fun clampFloatingTop(desiredTop: Float, viewportStart: Int, viewportEnd: Int, rowSize: Int): Float {
    val lowest = (viewportEnd - rowSize).toFloat()
    return desiredTop.coerceIn(viewportStart.toFloat(), lowest.coerceAtLeast(viewportStart.toFloat()))
}

/**
 * How far to scroll this frame while the held row rests near an edge: negative towards the start of
 * the list, positive towards its end, zero anywhere in the middle. The speed ramps up with how
 * close the row has come, so entering the edge strip creeps and lying against the edge - which is
 * where the row waits while the finger presses past it - runs at [maxSpeed]. When the viewport is
 * too short for two strips the top one wins.
 */
internal fun autoScrollDelta(
    rowTop: Float,
    rowSize: Int,
    viewportStart: Float,
    viewportEnd: Float,
    edgeSize: Float,
    maxSpeed: Float
): Float {
    if (edgeSize <= 0f || viewportEnd <= viewportStart) return 0f
    val gapAbove = rowTop - viewportStart
    val gapBelow = viewportEnd - (rowTop + rowSize)
    return when {
        gapAbove < edgeSize -> -maxSpeed * ((edgeSize - gapAbove) / edgeSize).coerceIn(0f, 1f)
        gapBelow < edgeSize -> maxSpeed * ((edgeSize - gapBelow) / edgeSize).coerceIn(0f, 1f)
        else -> 0f
    }
}

private const val SETTLE_FRAMES = 12
private val autoScrollEdge = 72.dp
// Fast enough that a row travels a screenful in a couple of seconds, slow enough that the moves it
// triggers on the way - one per row it passes - are all safely stored before the next one comes.
private val autoScrollSpeedPerFrame = 10.dp
private val draggedElevation = 12.dp
private val draggedCorner = 12.dp
