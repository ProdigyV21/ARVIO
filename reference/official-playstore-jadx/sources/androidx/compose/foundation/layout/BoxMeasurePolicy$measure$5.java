package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BoxMeasurePolicy$measure$5 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ i0 $boxHeight;
    final /* synthetic */ i0 $boxWidth;
    final /* synthetic */ List<Measurable> $measurables;
    final /* synthetic */ Placeable[] $placeables;
    final /* synthetic */ MeasureScope $this_measure;
    final /* synthetic */ BoxMeasurePolicy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BoxMeasurePolicy$measure$5(Placeable[] placeableArr, List<? extends Measurable> list, MeasureScope measureScope, i0 i0Var, i0 i0Var2, BoxMeasurePolicy boxMeasurePolicy) {
        super(1);
        this.$placeables = placeableArr;
        this.$measurables = list;
        this.$this_measure = measureScope;
        this.$boxWidth = i0Var;
        this.$boxHeight = i0Var2;
        this.this$0 = boxMeasurePolicy;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        Placeable[] placeableArr = this.$placeables;
        List<Measurable> list = this.$measurables;
        MeasureScope measureScope = this.$this_measure;
        i0 i0Var = this.$boxWidth;
        i0 i0Var2 = this.$boxHeight;
        BoxMeasurePolicy boxMeasurePolicy = this.this$0;
        int length = placeableArr.length;
        int i10 = 0;
        int i11 = 0;
        while (i10 < length) {
            BoxKt.placeInBox(placementScope, placeableArr[i10], list.get(i11), measureScope.getLayoutDirection(), i0Var.f19744i, i0Var2.f19744i, boxMeasurePolicy.alignment);
            i10++;
            i11++;
        }
    }
}
