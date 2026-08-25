package androidx.compose.ui.node;

import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class LayoutNodeLayoutDelegate$MeasurePassDelegate$placeOuterCoordinatorBlock$1 extends r implements r7.a<t0> {
    final /* synthetic */ LayoutNodeLayoutDelegate this$0;
    final /* synthetic */ LayoutNodeLayoutDelegate.MeasurePassDelegate this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LayoutNodeLayoutDelegate$MeasurePassDelegate$placeOuterCoordinatorBlock$1(LayoutNodeLayoutDelegate layoutNodeLayoutDelegate, LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate) {
        super(0);
        this.this$0 = layoutNodeLayoutDelegate;
        this.this$1 = measurePassDelegate;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m4781invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m4781invoke() {
        Placeable.PlacementScope placementScope;
        NodeCoordinator wrappedBy = this.this$0.getOuterCoordinator().getWrappedBy();
        if (wrappedBy == null || (placementScope = wrappedBy.getPlacementScope()) == null) {
            placementScope = LayoutNodeKt.requireOwner(this.this$0.layoutNode).getPlacementScope();
        }
        Placeable.PlacementScope placementScope2 = placementScope;
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = this.this$1;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.this$0;
        l<? super GraphicsLayerScope, t0> lVar = measurePassDelegate.placeOuterCoordinatorLayerBlock;
        if (lVar == null) {
            placementScope2.m4673place70tqf50(layoutNodeLayoutDelegate.getOuterCoordinator(), measurePassDelegate.placeOuterCoordinatorPosition, measurePassDelegate.placeOuterCoordinatorZIndex);
        } else {
            placementScope2.m4678placeWithLayeraW9wM(layoutNodeLayoutDelegate.getOuterCoordinator(), measurePassDelegate.placeOuterCoordinatorPosition, measurePassDelegate.placeOuterCoordinatorZIndex, lVar);
        }
    }
}
