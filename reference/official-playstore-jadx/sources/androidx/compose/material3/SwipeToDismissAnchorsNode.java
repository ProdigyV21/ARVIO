package androidx.compose.material3;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ&\u0010\u0015\u001a\u00020\u0012*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014R\"\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\"\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010\u0007\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\u001fR\u0016\u0010\"\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010\u001b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"Landroidx/compose/material3/SwipeToDismissAnchorsNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/material3/SwipeToDismissBoxState;", "state", "", "enableDismissFromStartToEnd", "enableDismissFromEndToStart", "<init>", "(Landroidx/compose/material3/SwipeToDismissBoxState;ZZ)V", "Lx6/t0;", "onDetach", "()V", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "Landroidx/compose/material3/SwipeToDismissBoxState;", "getState", "()Landroidx/compose/material3/SwipeToDismissBoxState;", "setState", "(Landroidx/compose/material3/SwipeToDismissBoxState;)V", "Z", "getEnableDismissFromStartToEnd", "()Z", "setEnableDismissFromStartToEnd", "(Z)V", "getEnableDismissFromEndToStart", "setEnableDismissFromEndToStart", "didLookahead", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SwipeToDismissAnchorsNode extends Modifier.Node implements LayoutModifierNode {
    private boolean didLookahead;
    private boolean enableDismissFromEndToStart;
    private boolean enableDismissFromStartToEnd;
    private SwipeToDismissBoxState state;

    public SwipeToDismissAnchorsNode(SwipeToDismissBoxState swipeToDismissBoxState, boolean z, boolean z5) {
        this.state = swipeToDismissBoxState;
        this.enableDismissFromStartToEnd = z;
        this.enableDismissFromEndToStart = z5;
    }

    public final boolean getEnableDismissFromEndToStart() {
        return this.enableDismissFromEndToStart;
    }

    public final boolean getEnableDismissFromStartToEnd() {
        return this.enableDismissFromStartToEnd;
    }

    public final SwipeToDismissBoxState getState() {
        return this.state;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.a(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.b(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(j10);
        if (measureScope.isLookingAhead() || !this.didLookahead) {
            AnchoredDraggableState.updateAnchors$default(this.state.getAnchoredDraggableState$material3_release(), AnchoredDraggableKt.DraggableAnchors(new SwipeToDismissAnchorsNode$measure$newAnchors$1(this, placeableMo4631measureBRTryo0.getWidth())), null, 2, null);
        }
        this.didLookahead = measureScope.isLookingAhead() || this.didLookahead;
        return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new SwipeToDismissAnchorsNode$measure$1(measureScope, this, placeableMo4631measureBRTryo0), 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.c(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.d(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.didLookahead = false;
    }

    public final void setEnableDismissFromEndToStart(boolean z) {
        this.enableDismissFromEndToStart = z;
    }

    public final void setEnableDismissFromStartToEnd(boolean z) {
        this.enableDismissFromStartToEnd = z;
    }

    public final void setState(SwipeToDismissBoxState swipeToDismissBoxState) {
        this.state = swipeToDismissBoxState;
    }
}
