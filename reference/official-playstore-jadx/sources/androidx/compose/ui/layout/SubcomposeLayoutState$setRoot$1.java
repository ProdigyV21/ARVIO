package androidx.compose.ui.layout;

import androidx.compose.ui.node.LayoutNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/ui/layout/SubcomposeLayoutState;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/layout/SubcomposeLayoutState;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SubcomposeLayoutState$setRoot$1 extends r implements p<LayoutNode, SubcomposeLayoutState, t0> {
    final /* synthetic */ SubcomposeLayoutState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubcomposeLayoutState$setRoot$1(SubcomposeLayoutState subcomposeLayoutState) {
        super(2);
        this.this$0 = subcomposeLayoutState;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((LayoutNode) obj, (SubcomposeLayoutState) obj2);
        return t0.f22605a;
    }

    public final void invoke(LayoutNode layoutNode, SubcomposeLayoutState subcomposeLayoutState) {
        SubcomposeLayoutState subcomposeLayoutState2 = this.this$0;
        LayoutNodeSubcompositionsState subcompositionsState = layoutNode.getSubcompositionsState();
        if (subcompositionsState == null) {
            subcompositionsState = new LayoutNodeSubcompositionsState(layoutNode, this.this$0.slotReusePolicy);
            layoutNode.setSubcompositionsState$ui_release(subcompositionsState);
        }
        subcomposeLayoutState2._state = subcompositionsState;
        this.this$0.getState().makeSureStateIsConsistent();
        this.this$0.getState().setSlotReusePolicy(this.this$0.slotReusePolicy);
    }
}
