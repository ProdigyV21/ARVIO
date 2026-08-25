package androidx.compose.material3;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BadgeKt$BadgedBox$2$1 extends r implements l<LayoutCoordinates, t0> {
    final /* synthetic */ MutableFloatState $greatGrandParentAbsoluteRight$delegate;
    final /* synthetic */ MutableFloatState $greatGrandParentAbsoluteTop$delegate;
    final /* synthetic */ MutableFloatState $layoutAbsoluteLeft$delegate;
    final /* synthetic */ MutableFloatState $layoutAbsoluteTop$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BadgeKt$BadgedBox$2$1(MutableFloatState mutableFloatState, MutableFloatState mutableFloatState2, MutableFloatState mutableFloatState3, MutableFloatState mutableFloatState4) {
        super(1);
        this.$layoutAbsoluteLeft$delegate = mutableFloatState;
        this.$layoutAbsoluteTop$delegate = mutableFloatState2;
        this.$greatGrandParentAbsoluteRight$delegate = mutableFloatState3;
        this.$greatGrandParentAbsoluteTop$delegate = mutableFloatState4;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LayoutCoordinates) obj);
        return t0.f22605a;
    }

    public final void invoke(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates parentLayoutCoordinates;
        this.$layoutAbsoluteLeft$delegate.setFloatValue(LayoutCoordinatesKt.boundsInWindow(layoutCoordinates).getLeft());
        this.$layoutAbsoluteTop$delegate.setFloatValue(LayoutCoordinatesKt.boundsInWindow(layoutCoordinates).getTop());
        LayoutCoordinates parentLayoutCoordinates2 = layoutCoordinates.getParentLayoutCoordinates();
        LayoutCoordinates parentCoordinates = (parentLayoutCoordinates2 == null || (parentLayoutCoordinates = parentLayoutCoordinates2.getParentLayoutCoordinates()) == null) ? null : parentLayoutCoordinates.getParentCoordinates();
        if (parentCoordinates != null) {
            MutableFloatState mutableFloatState = this.$greatGrandParentAbsoluteRight$delegate;
            MutableFloatState mutableFloatState2 = this.$greatGrandParentAbsoluteTop$delegate;
            mutableFloatState.setFloatValue(LayoutCoordinatesKt.boundsInWindow(parentCoordinates).getRight());
            mutableFloatState2.setFloatValue(LayoutCoordinatesKt.boundsInWindow(parentCoordinates).getTop());
        }
    }
}
