package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BoxMeasurePolicy$measure$2 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ int $boxHeight;
    final /* synthetic */ int $boxWidth;
    final /* synthetic */ Measurable $measurable;
    final /* synthetic */ Placeable $placeable;
    final /* synthetic */ MeasureScope $this_measure;
    final /* synthetic */ BoxMeasurePolicy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BoxMeasurePolicy$measure$2(Placeable placeable, Measurable measurable, MeasureScope measureScope, int i10, int i11, BoxMeasurePolicy boxMeasurePolicy) {
        super(1);
        this.$placeable = placeable;
        this.$measurable = measurable;
        this.$this_measure = measureScope;
        this.$boxWidth = i10;
        this.$boxHeight = i11;
        this.this$0 = boxMeasurePolicy;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        BoxKt.placeInBox(placementScope, this.$placeable, this.$measurable, this.$this_measure.getLayoutDirection(), this.$boxWidth, this.$boxHeight, this.this$0.alignment);
    }
}
