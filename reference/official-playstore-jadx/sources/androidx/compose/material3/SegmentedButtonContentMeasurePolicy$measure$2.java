package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SegmentedButtonContentMeasurePolicy$measure$2 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ List<Placeable> $contentPlaceables;
    final /* synthetic */ int $height;
    final /* synthetic */ List<Placeable> $iconPlaceables;
    final /* synthetic */ int $offsetX;
    final /* synthetic */ MeasureScope $this_measure;
    final /* synthetic */ SegmentedButtonContentMeasurePolicy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SegmentedButtonContentMeasurePolicy$measure$2(List<? extends Placeable> list, MeasureScope measureScope, SegmentedButtonContentMeasurePolicy segmentedButtonContentMeasurePolicy, int i10, List<? extends Placeable> list2, int i11) {
        super(1);
        this.$iconPlaceables = list;
        this.$this_measure = measureScope;
        this.this$0 = segmentedButtonContentMeasurePolicy;
        this.$offsetX = i10;
        this.$contentPlaceables = list2;
        this.$height = i11;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        List<Placeable> list = this.$iconPlaceables;
        int i10 = this.$height;
        int size = list.size();
        for (int i11 = 0; i11 < size; i11++) {
            Placeable placeable = list.get(i11);
            Placeable.PlacementScope.place$default(placementScope, placeable, 0, androidx.compose.foundation.c.f(placeable, i10, 2), 0.0f, 4, null);
        }
        int iMo279roundToPx0680j_4 = this.$this_measure.mo279roundToPx0680j_4(SegmentedButtonKt.IconSpacing) + this.$this_measure.mo279roundToPx0680j_4(SegmentedButtonDefaults.INSTANCE.m1844getIconSizeD9Ej5fM());
        Animatable<Integer, AnimationVector1D> animatable = this.this$0.getAnimatable();
        int iIntValue = iMo279roundToPx0680j_4 + (animatable != null ? animatable.getValue().intValue() : this.$offsetX);
        List<Placeable> list2 = this.$contentPlaceables;
        int i12 = this.$height;
        int size2 = list2.size();
        for (int i13 = 0; i13 < size2; i13++) {
            Placeable placeable2 = list2.get(i13);
            Placeable.PlacementScope.place$default(placementScope, placeable2, iIntValue, androidx.compose.foundation.c.f(placeable2, i12, 2), 0.0f, 4, null);
        }
    }
}
