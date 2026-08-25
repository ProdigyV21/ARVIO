package androidx.compose.animation;

import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class EnterExitTransitionModifierNode$measure$2 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ l<GraphicsLayerScope, t0> $layerBlock;
    final /* synthetic */ long $offset;
    final /* synthetic */ long $offsetDelta;
    final /* synthetic */ Placeable $placeable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public EnterExitTransitionModifierNode$measure$2(Placeable placeable, long j10, long j11, l<? super GraphicsLayerScope, t0> lVar) {
        super(1);
        this.$placeable = placeable;
        this.$offset = j10;
        this.$offsetDelta = j11;
        this.$layerBlock = lVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        placementScope.placeWithLayer(this.$placeable, IntOffset.m5802getXimpl(this.$offsetDelta) + IntOffset.m5802getXimpl(this.$offset), IntOffset.m5803getYimpl(this.$offsetDelta) + IntOffset.m5803getYimpl(this.$offset), 0.0f, this.$layerBlock);
    }
}
