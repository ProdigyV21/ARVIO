package androidx.compose.material3;

import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class NavigationRailKt$placeLabelAndIcon$1 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ boolean $alwaysShowLabel;
    final /* synthetic */ float $animationProgress;
    final /* synthetic */ Placeable $iconPlaceable;
    final /* synthetic */ int $iconX;
    final /* synthetic */ Placeable $indicatorPlaceable;
    final /* synthetic */ Placeable $indicatorRipplePlaceable;
    final /* synthetic */ Placeable $labelPlaceable;
    final /* synthetic */ int $labelX;
    final /* synthetic */ float $labelY;
    final /* synthetic */ float $offset;
    final /* synthetic */ int $rippleX;
    final /* synthetic */ float $rippleY;
    final /* synthetic */ float $selectedIconY;
    final /* synthetic */ MeasureScope $this_placeLabelAndIcon;
    final /* synthetic */ int $width;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NavigationRailKt$placeLabelAndIcon$1(Placeable placeable, boolean z, float f10, Placeable placeable2, int i10, float f11, float f12, Placeable placeable3, int i11, float f13, Placeable placeable4, int i12, float f14, int i13, MeasureScope measureScope) {
        super(1);
        this.$indicatorPlaceable = placeable;
        this.$alwaysShowLabel = z;
        this.$animationProgress = f10;
        this.$labelPlaceable = placeable2;
        this.$labelX = i10;
        this.$labelY = f11;
        this.$offset = f12;
        this.$iconPlaceable = placeable3;
        this.$iconX = i11;
        this.$selectedIconY = f13;
        this.$indicatorRipplePlaceable = placeable4;
        this.$rippleX = i12;
        this.$rippleY = f14;
        this.$width = i13;
        this.$this_placeLabelAndIcon = measureScope;
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
            int i10 = this.$width;
            float f10 = this.$selectedIconY;
            MeasureScope measureScope = this.$this_placeLabelAndIcon;
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, androidx.compose.foundation.c.B(placeable, i10, 2), t7.a.M((f10 - measureScope.mo285toPx0680j_4(NavigationRailKt.IndicatorVerticalPaddingWithLabel)) + this.$offset), 0.0f, 4, null);
            placementScope2 = placementScope;
        } else {
            placementScope2 = placementScope;
        }
        if (this.$alwaysShowLabel || this.$animationProgress != 0.0f) {
            Placeable.PlacementScope.placeRelative$default(placementScope2, this.$labelPlaceable, this.$labelX, t7.a.M(this.$labelY + this.$offset), 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default(placementScope2, this.$iconPlaceable, this.$iconX, t7.a.M(this.$selectedIconY + this.$offset), 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope2, this.$indicatorRipplePlaceable, this.$rippleX, t7.a.M(this.$rippleY + this.$offset), 0.0f, 4, null);
    }
}
