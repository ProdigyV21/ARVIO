package androidx.compose.animation;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AnimatedContentMeasurePolicy$measure$3 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ int $maxHeight;
    final /* synthetic */ int $maxWidth;
    final /* synthetic */ Placeable[] $placeables;
    final /* synthetic */ AnimatedContentMeasurePolicy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnimatedContentMeasurePolicy$measure$3(Placeable[] placeableArr, AnimatedContentMeasurePolicy animatedContentMeasurePolicy, int i10, int i11) {
        super(1);
        this.$placeables = placeableArr;
        this.this$0 = animatedContentMeasurePolicy;
        this.$maxWidth = i10;
        this.$maxHeight = i11;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope placementScope2;
        Placeable[] placeableArr = this.$placeables;
        AnimatedContentMeasurePolicy animatedContentMeasurePolicy = this.this$0;
        int i10 = this.$maxWidth;
        int i11 = this.$maxHeight;
        int length = placeableArr.length;
        int i12 = 0;
        while (i12 < length) {
            Placeable placeable = placeableArr[i12];
            if (placeable != null) {
                long jMo3109alignKFBX0sM = animatedContentMeasurePolicy.getRootScope().getContentAlignment().mo3109alignKFBX0sM(IntSizeKt.IntSize(placeable.getWidth(), placeable.getHeight()), IntSizeKt.IntSize(i10, i11), LayoutDirection.Ltr);
                placementScope2 = placementScope;
                Placeable.PlacementScope.place$default(placementScope2, placeable, IntOffset.m5802getXimpl(jMo3109alignKFBX0sM), IntOffset.m5803getYimpl(jMo3109alignKFBX0sM), 0.0f, 4, null);
            } else {
                placementScope2 = placementScope;
            }
            i12++;
            placementScope = placementScope2;
        }
    }
}
