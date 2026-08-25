package androidx.compose.foundation.gestures;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.unit.Velocity;
import io.ktor.http.ContentDisposition;
import ka.k0;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rJ'\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010!\u001a\u00020 8\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'RJ\u00101\u001a8\b\u0001\u0012\u0004\u0012\u00020)\u0012\u0013\u0012\u00110*¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0.\u0012\u0006\u0012\u0004\u0018\u00010/0(¢\u0006\u0002\b08\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u0017\u00104\u001a\u0002038\u0006¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107¨\u00068"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollableGesturesNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "scrollLogic", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "", "enabled", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "nestedScrollDispatcher", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "<init>", "(Landroidx/compose/foundation/gestures/ScrollingLogic;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;Landroidx/compose/foundation/interaction/MutableInteractionSource;)V", "Lx6/t0;", "update", "(Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;)V", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "getScrollLogic", "()Landroidx/compose/foundation/gestures/ScrollingLogic;", "Landroidx/compose/foundation/gestures/Orientation;", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "Z", "getEnabled", "()Z", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "getNestedScrollDispatcher", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getInteractionSource", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Landroidx/compose/foundation/gestures/ScrollDraggableState;", "draggableState", "Landroidx/compose/foundation/gestures/ScrollDraggableState;", "getDraggableState", "()Landroidx/compose/foundation/gestures/ScrollDraggableState;", "Lkotlin/Function0;", "startDragImmediately", "Lr7/a;", "Lkotlin/Function3;", "Lka/k0;", "Landroidx/compose/ui/unit/Velocity;", "Lx6/y;", ContentDisposition.Parameters.Name, "velocity", "Ld7/d;", "", "Lx6/n;", "onDragStopped", "Lr7/q;", "Landroidx/compose/foundation/gestures/DraggableNode;", "draggableGesturesNode", "Landroidx/compose/foundation/gestures/DraggableNode;", "getDraggableGesturesNode", "()Landroidx/compose/foundation/gestures/DraggableNode;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ScrollableGesturesNode extends DelegatingNode {
    private final DraggableNode draggableGesturesNode;
    private final ScrollDraggableState draggableState;
    private final boolean enabled;
    private final MutableInteractionSource interactionSource;
    private final NestedScrollDispatcher nestedScrollDispatcher;
    private final q<k0, Velocity, d7.d<? super t0>, Object> onDragStopped;
    private final Orientation orientation;
    private final ScrollingLogic scrollLogic;
    private final r7.a<Boolean> startDragImmediately;

    public ScrollableGesturesNode(ScrollingLogic scrollingLogic, Orientation orientation, boolean z, NestedScrollDispatcher nestedScrollDispatcher, MutableInteractionSource mutableInteractionSource) {
        this.scrollLogic = scrollingLogic;
        this.orientation = orientation;
        this.enabled = z;
        this.nestedScrollDispatcher = nestedScrollDispatcher;
        this.interactionSource = mutableInteractionSource;
        delegate(new MouseWheelScrollNode(scrollingLogic));
        ScrollDraggableState scrollDraggableState = new ScrollDraggableState(scrollingLogic);
        this.draggableState = scrollDraggableState;
        ScrollableGesturesNode$startDragImmediately$1 scrollableGesturesNode$startDragImmediately$1 = new ScrollableGesturesNode$startDragImmediately$1(this);
        this.startDragImmediately = scrollableGesturesNode$startDragImmediately$1;
        ScrollableGesturesNode$onDragStopped$1 scrollableGesturesNode$onDragStopped$1 = new ScrollableGesturesNode$onDragStopped$1(this, null);
        this.onDragStopped = scrollableGesturesNode$onDragStopped$1;
        this.draggableGesturesNode = (DraggableNode) delegate(new DraggableNode(scrollDraggableState, ScrollableKt.CanDragCalculation, orientation, z, mutableInteractionSource, scrollableGesturesNode$startDragImmediately$1, ScrollableKt.NoOpOnDragStarted, scrollableGesturesNode$onDragStopped$1, false));
    }

    public final DraggableNode getDraggableGesturesNode() {
        return this.draggableGesturesNode;
    }

    public final ScrollDraggableState getDraggableState() {
        return this.draggableState;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final MutableInteractionSource getInteractionSource() {
        return this.interactionSource;
    }

    public final NestedScrollDispatcher getNestedScrollDispatcher() {
        return this.nestedScrollDispatcher;
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final ScrollingLogic getScrollLogic() {
        return this.scrollLogic;
    }

    public final void update(Orientation orientation, boolean enabled, MutableInteractionSource interactionSource) {
        this.draggableGesturesNode.update(this.draggableState, ScrollableKt.CanDragCalculation, orientation, enabled, interactionSource, this.startDragImmediately, ScrollableKt.NoOpOnDragStarted, this.onDragStopped, false);
    }
}
