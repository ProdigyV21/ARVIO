package androidx.compose.foundation;

import androidx.compose.ui.layout.Placeable;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import qb.d;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ScrollingLayoutNode$measure$1 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ Placeable $placeable;
    final /* synthetic */ int $side;
    final /* synthetic */ ScrollingLayoutNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollingLayoutNode$measure$1(ScrollingLayoutNode scrollingLayoutNode, int i10, Placeable placeable) {
        super(1);
        this.this$0 = scrollingLayoutNode;
        this.$side = i10;
        this.$placeable = placeable;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        int iN = d.n(this.this$0.getScrollerState().getValue(), 0, this.$side);
        int i10 = this.this$0.getIsReversed() ? iN - this.$side : -iN;
        Placeable.PlacementScope.placeRelativeWithLayer$default(placementScope, this.$placeable, this.this$0.getIsVertical() ? 0 : i10, this.this$0.getIsVertical() ? i10 : 0, 0.0f, null, 12, null);
    }
}
