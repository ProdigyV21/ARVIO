package androidx.compose.ui.layout;

import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\n\u001a\u00020\u0007*\u00020\u00002\u001d\u0010\u0006\u001a\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001¢\u0006\u0002\b\u0005H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"Landroidx/compose/ui/node/LayoutNode;", "Lkotlin/Function2;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lx6/n;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/LayoutNode;Lr7/p;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SubcomposeLayoutState$setMeasurePolicy$1 extends r implements p<LayoutNode, p<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult>, t0> {
    final /* synthetic */ SubcomposeLayoutState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubcomposeLayoutState$setMeasurePolicy$1(SubcomposeLayoutState subcomposeLayoutState) {
        super(2);
        this.this$0 = subcomposeLayoutState;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((LayoutNode) obj, (p<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult>) obj2);
        return t0.f22605a;
    }

    public final void invoke(LayoutNode layoutNode, p<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> pVar) {
        layoutNode.setMeasurePolicy(this.this$0.getState().createMeasurePolicy(pVar));
    }
}
