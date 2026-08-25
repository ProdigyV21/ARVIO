package androidx.compose.ui.node;

import androidx.compose.ui.layout.Placeable;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class LayoutNodeLayoutDelegate$LookaheadPassDelegate$placeAt$2 extends r implements r7.a<t0> {
    final /* synthetic */ Owner $owner;
    final /* synthetic */ long $position;
    final /* synthetic */ LayoutNodeLayoutDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LayoutNodeLayoutDelegate$LookaheadPassDelegate$placeAt$2(LayoutNodeLayoutDelegate layoutNodeLayoutDelegate, Owner owner, long j10) {
        super(0);
        this.this$0 = layoutNodeLayoutDelegate;
        this.$owner = owner;
        this.$position = j10;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m4776invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m4776invoke() {
        LookaheadDelegate lookaheadDelegate;
        Placeable.PlacementScope placementScope = null;
        if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(this.this$0.layoutNode)) {
            NodeCoordinator wrappedBy = this.this$0.getOuterCoordinator().getWrappedBy();
            if (wrappedBy != null) {
                placementScope = wrappedBy.getPlacementScope();
            }
        } else {
            NodeCoordinator wrappedBy2 = this.this$0.getOuterCoordinator().getWrappedBy();
            if (wrappedBy2 != null && (lookaheadDelegate = wrappedBy2.getLookaheadDelegate()) != null) {
                placementScope = lookaheadDelegate.getPlacementScope();
            }
        }
        if (placementScope == null) {
            placementScope = this.$owner.getPlacementScope();
        }
        Placeable.PlacementScope.m4669place70tqf50$default(placementScope, this.this$0.getOuterCoordinator().getLookaheadDelegate(), this.$position, 0.0f, 2, null);
    }
}
