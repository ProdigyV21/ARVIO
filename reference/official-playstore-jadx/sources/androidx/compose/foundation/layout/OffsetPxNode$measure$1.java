package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class OffsetPxNode$measure$1 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ Placeable $placeable;
    final /* synthetic */ MeasureScope $this_measure;
    final /* synthetic */ OffsetPxNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OffsetPxNode$measure$1(OffsetPxNode offsetPxNode, MeasureScope measureScope, Placeable placeable) {
        super(1);
        this.this$0 = offsetPxNode;
        this.$this_measure = measureScope;
        this.$placeable = placeable;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        long packedValue = ((IntOffset) this.this$0.getOffset().invoke(this.$this_measure)).getPackedValue();
        if (this.this$0.getRtlAware()) {
            Placeable.PlacementScope.placeRelativeWithLayer$default(placementScope, this.$placeable, IntOffset.m5802getXimpl(packedValue), IntOffset.m5803getYimpl(packedValue), 0.0f, null, 12, null);
        } else {
            Placeable.PlacementScope.placeWithLayer$default(placementScope, this.$placeable, IntOffset.m5802getXimpl(packedValue), IntOffset.m5803getYimpl(packedValue), 0.0f, null, 12, null);
        }
    }
}
