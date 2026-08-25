package androidx.compose.material3;

import androidx.compose.ui.layout.Placeable;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class NavigationBarKt$placeIcon$1 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ int $height;
    final /* synthetic */ Placeable $iconPlaceable;
    final /* synthetic */ int $iconX;
    final /* synthetic */ int $iconY;
    final /* synthetic */ Placeable $indicatorPlaceable;
    final /* synthetic */ Placeable $indicatorRipplePlaceable;
    final /* synthetic */ int $rippleX;
    final /* synthetic */ int $rippleY;
    final /* synthetic */ int $width;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NavigationBarKt$placeIcon$1(Placeable placeable, Placeable placeable2, int i10, int i11, Placeable placeable3, int i12, int i13, int i14, int i15) {
        super(1);
        this.$indicatorPlaceable = placeable;
        this.$iconPlaceable = placeable2;
        this.$iconX = i10;
        this.$iconY = i11;
        this.$indicatorRipplePlaceable = placeable3;
        this.$rippleX = i12;
        this.$rippleY = i13;
        this.$width = i14;
        this.$height = i15;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope placementScope2;
        Placeable placeable = this.$indicatorPlaceable;
        if (placeable != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, androidx.compose.foundation.c.B(placeable, this.$width, 2), androidx.compose.foundation.c.f(placeable, this.$height, 2), 0.0f, 4, null);
            placementScope2 = placementScope;
        } else {
            placementScope2 = placementScope;
        }
        Placeable.PlacementScope.placeRelative$default(placementScope2, this.$iconPlaceable, this.$iconX, this.$iconY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope2, this.$indicatorRipplePlaceable, this.$rippleX, this.$rippleY, 0.0f, 4, null);
    }
}
