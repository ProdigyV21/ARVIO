package androidx.compose.foundation.text;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TextMeasurePolicy$measure$1 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ List<x> $toPlace;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TextMeasurePolicy$measure$1(List<? extends x> list) {
        super(1);
        this.$toPlace = list;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        List<x> list = this.$toPlace;
        if (list != null) {
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                x xVar = list.get(i10);
                Placeable.PlacementScope.m4669place70tqf50$default(placementScope, (Placeable) xVar.f22608i, ((IntOffset) xVar.f22609l).getPackedValue(), 0.0f, 2, null);
            }
        }
    }
}
