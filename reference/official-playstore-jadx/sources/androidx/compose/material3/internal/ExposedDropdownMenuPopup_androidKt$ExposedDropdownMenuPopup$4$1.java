package androidx.compose.material3.internal;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRectKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import t7.a;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/layout/LayoutCoordinates;", "childCoordinates", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$4$1 extends r implements l<LayoutCoordinates, t0> {
    final /* synthetic */ PopupLayout $popupLayout;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$4$1(PopupLayout popupLayout) {
        super(1);
        this.$popupLayout = popupLayout;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LayoutCoordinates) obj);
        return t0.f22605a;
    }

    public final void invoke(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        long jMo4638getSizeYbymL2g = parentLayoutCoordinates.mo4638getSizeYbymL2g();
        long jPositionInWindow = LayoutCoordinatesKt.positionInWindow(parentLayoutCoordinates);
        this.$popupLayout.setParentBounds(IntRectKt.m5834IntRectVbeCjmY(IntOffsetKt.IntOffset(a.M(Offset.m3236getXimpl(jPositionInWindow)), a.M(Offset.m3237getYimpl(jPositionInWindow))), jMo4638getSizeYbymL2g));
        this.$popupLayout.updatePosition();
    }
}
