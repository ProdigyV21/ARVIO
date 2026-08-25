package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class LazyListMeasureKt$measureLazyList$7 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ LazyListMeasuredItem $headerItem;
    final /* synthetic */ boolean $isLookingAhead;
    final /* synthetic */ MutableState<t0> $placementScopeInvalidator;
    final /* synthetic */ List<LazyListMeasuredItem> $positionedItems;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyListMeasureKt$measureLazyList$7(List<LazyListMeasuredItem> list, LazyListMeasuredItem lazyListMeasuredItem, boolean z, MutableState<t0> mutableState) {
        super(1);
        this.$positionedItems = list;
        this.$headerItem = lazyListMeasuredItem;
        this.$isLookingAhead = z;
        this.$placementScopeInvalidator = mutableState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        List<LazyListMeasuredItem> list = this.$positionedItems;
        LazyListMeasuredItem lazyListMeasuredItem = this.$headerItem;
        boolean z = this.$isLookingAhead;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            LazyListMeasuredItem lazyListMeasuredItem2 = list.get(i10);
            if (lazyListMeasuredItem2 != lazyListMeasuredItem) {
                lazyListMeasuredItem2.place(placementScope, z);
            }
        }
        LazyListMeasuredItem lazyListMeasuredItem3 = this.$headerItem;
        if (lazyListMeasuredItem3 != null) {
            lazyListMeasuredItem3.place(placementScope, this.$isLookingAhead);
        }
        ObservableScopeInvalidator.m695attachToScopeimpl(this.$placementScopeInvalidator);
    }
}
