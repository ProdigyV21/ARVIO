package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import r7.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000S\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002JD\u0010\r\u001a\u00020\u0003*\u00020\u00032/\u0010\f\u001a+\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\u0002\b\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ#\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0017\u001a\u00020\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016¢\u0006\u0004\b\u0017\u0010\u0018R#\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"androidx/compose/material3/TabRowKt$TabRowImpl$1$scope$1$1", "Landroidx/compose/material3/TabIndicatorScope;", "Landroidx/compose/material3/TabPositionsHolder;", "Landroidx/compose/ui/Modifier;", "Lkotlin/Function4;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/unit/Constraints;", "", "Landroidx/compose/material3/TabPosition;", "Landroidx/compose/ui/layout/MeasureResult;", "Lx6/n;", "measure", "tabIndicatorLayout", "(Landroidx/compose/ui/Modifier;Lr7/r;)Landroidx/compose/ui/Modifier;", "", "selectedTabIndex", "", "matchContentSize", "tabIndicatorOffset", "(Landroidx/compose/ui/Modifier;IZ)Landroidx/compose/ui/Modifier;", "positions", "Lx6/t0;", "setTabPositions", "(Ljava/util/List;)V", "Landroidx/compose/runtime/MutableState;", "tabPositions", "Landroidx/compose/runtime/MutableState;", "getTabPositions", "()Landroidx/compose/runtime/MutableState;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TabRowKt$TabRowImpl$1$scope$1$1 implements TabIndicatorScope, TabPositionsHolder {
    private final MutableState<List<TabPosition>> tabPositions = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(z.f19728i, null, 2, null);

    public final MutableState<List<TabPosition>> getTabPositions() {
        return this.tabPositions;
    }

    @Override // androidx.compose.material3.TabPositionsHolder
    public void setTabPositions(List<TabPosition> positions) {
        this.tabPositions.setValue(positions);
    }

    @Override // androidx.compose.material3.TabIndicatorScope
    public Modifier tabIndicatorLayout(Modifier modifier, r<? super MeasureScope, ? super Measurable, ? super Constraints, ? super List<TabPosition>, ? extends MeasureResult> rVar) {
        return LayoutModifierKt.layout(modifier, new TabRowKt$TabRowImpl$1$scope$1$1$tabIndicatorLayout$1(rVar, this));
    }

    @Override // androidx.compose.material3.TabIndicatorScope
    public Modifier tabIndicatorOffset(Modifier modifier, int i10, boolean z) {
        return modifier.then(new TabIndicatorModifier(this.tabPositions, i10, z));
    }
}
