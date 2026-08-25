package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AlignmentLineKt$alignmentLineOffsetMeasure$1 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ AlignmentLine $alignmentLine;
    final /* synthetic */ float $before;
    final /* synthetic */ int $height;
    final /* synthetic */ int $paddingAfter;
    final /* synthetic */ int $paddingBefore;
    final /* synthetic */ Placeable $placeable;
    final /* synthetic */ int $width;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlignmentLineKt$alignmentLineOffsetMeasure$1(AlignmentLine alignmentLine, float f10, int i10, int i11, int i12, Placeable placeable, int i13) {
        super(1);
        this.$alignmentLine = alignmentLine;
        this.$before = f10;
        this.$paddingBefore = i10;
        this.$width = i11;
        this.$paddingAfter = i12;
        this.$placeable = placeable;
        this.$height = i13;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        int width;
        if (AlignmentLineKt.getHorizontal(this.$alignmentLine)) {
            width = 0;
        } else {
            width = !Dp.m5683equalsimpl0(this.$before, Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM()) ? this.$paddingBefore : (this.$width - this.$paddingAfter) - this.$placeable.getWidth();
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, this.$placeable, width, AlignmentLineKt.getHorizontal(this.$alignmentLine) ? !Dp.m5683equalsimpl0(this.$before, Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM()) ? this.$paddingBefore : (this.$height - this.$paddingAfter) - this.$placeable.getHeight() : 0, 0.0f, 4, null);
    }
}
