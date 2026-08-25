package androidx.compose.material3;

import androidx.compose.foundation.ScrollState;
import androidx.compose.ui.unit.Density;
import java.util.List;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J1\u0010\u000f\u001a\u00020\u000b*\u00020\b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J3\u0010\u0013\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\r2\u0006\u0010\u0011\u001a\u00020\u000b¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0016R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/compose/material3/ScrollableTabData;", "", "Landroidx/compose/foundation/ScrollState;", "scrollState", "Lka/k0;", "coroutineScope", "<init>", "(Landroidx/compose/foundation/ScrollState;Lka/k0;)V", "Landroidx/compose/material3/TabPosition;", "Landroidx/compose/ui/unit/Density;", "density", "", "edgeOffset", "", "tabPositions", "calculateTabOffset", "(Landroidx/compose/material3/TabPosition;Landroidx/compose/ui/unit/Density;ILjava/util/List;)I", "selectedTab", "Lx6/t0;", "onLaidOut", "(Landroidx/compose/ui/unit/Density;ILjava/util/List;I)V", "Landroidx/compose/foundation/ScrollState;", "Lka/k0;", "Ljava/lang/Integer;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ScrollableTabData {
    private final k0 coroutineScope;
    private final ScrollState scrollState;
    private Integer selectedTab;

    public ScrollableTabData(ScrollState scrollState, k0 k0Var) {
        this.scrollState = scrollState;
        this.coroutineScope = k0Var;
    }

    private final int calculateTabOffset(TabPosition tabPosition, Density density, int i10, List<TabPosition> list) {
        int iMo279roundToPx0680j_4 = density.mo279roundToPx0680j_4(((TabPosition) x.w0(list)).m2048getRightD9Ej5fM()) + i10;
        int maxValue = iMo279roundToPx0680j_4 - this.scrollState.getMaxValue();
        int iMo279roundToPx0680j_42 = density.mo279roundToPx0680j_4(tabPosition.getLeft()) - ((maxValue / 2) - (density.mo279roundToPx0680j_4(tabPosition.getWidth()) / 2));
        int i11 = iMo279roundToPx0680j_4 - maxValue;
        if (i11 < 0) {
            i11 = 0;
        }
        return qb.d.n(iMo279roundToPx0680j_42, 0, i11);
    }

    public final void onLaidOut(Density density, int edgeOffset, List<TabPosition> tabPositions, int selectedTab) {
        int iCalculateTabOffset;
        Integer num = this.selectedTab;
        if (num != null && num.intValue() == selectedTab) {
            return;
        }
        this.selectedTab = Integer.valueOf(selectedTab);
        TabPosition tabPosition = (TabPosition) x.p0(selectedTab, tabPositions);
        if (tabPosition == null || this.scrollState.getValue() == (iCalculateTabOffset = calculateTabOffset(tabPosition, density, edgeOffset, tabPositions))) {
            return;
        }
        m0.p(this.coroutineScope, null, 0, new ScrollableTabData$onLaidOut$1$1(this, iCalculateTabOffset, null), 3);
    }
}
