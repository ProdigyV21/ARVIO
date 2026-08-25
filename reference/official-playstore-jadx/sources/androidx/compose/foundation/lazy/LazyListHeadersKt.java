package androidx.compose.foundation.lazy;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0004\u001aF\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0000¨\u0006\f"}, d2 = {"findOrComposeLazyListHeader", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "composedVisibleItems", "", "itemProvider", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "headerIndexes", "", "", "beforeContentPadding", "layoutWidth", "layoutHeight", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyListHeadersKt {
    public static final LazyListMeasuredItem findOrComposeLazyListHeader(List<LazyListMeasuredItem> list, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, List<Integer> list2, int i10, int i11, int i12) {
        int index = ((LazyListMeasuredItem) x.m0(list)).getIndex();
        int size = list2.size();
        int iIntValue = -1;
        int iIntValue2 = -1;
        int i13 = 0;
        while (i13 < size && list2.get(i13).intValue() <= index) {
            iIntValue = list2.get(i13).intValue();
            i13++;
            iIntValue2 = ((i13 < 0 || i13 > t7.a.w(list2)) ? -1 : list2.get(i13)).intValue();
        }
        int size2 = list.size();
        int offset = Integer.MIN_VALUE;
        int offset2 = Integer.MIN_VALUE;
        int i14 = -1;
        for (int i15 = 0; i15 < size2; i15++) {
            LazyListMeasuredItem lazyListMeasuredItem = list.get(i15);
            if (lazyListMeasuredItem.getIndex() == iIntValue) {
                offset = lazyListMeasuredItem.getOffset();
                i14 = i15;
            } else if (lazyListMeasuredItem.getIndex() == iIntValue2) {
                offset2 = lazyListMeasuredItem.getOffset();
            }
        }
        if (iIntValue == -1) {
            return null;
        }
        LazyListMeasuredItem andMeasure = lazyListMeasuredItemProvider.getAndMeasure(iIntValue);
        andMeasure.setNonScrollableItem(true);
        int iMax = offset != Integer.MIN_VALUE ? Math.max(-i10, offset) : -i10;
        if (offset2 != Integer.MIN_VALUE) {
            iMax = Math.min(iMax, offset2 - andMeasure.getSize());
        }
        andMeasure.position(iMax, i11, i12);
        if (i14 != -1) {
            list.set(i14, andMeasure);
            return andMeasure;
        }
        list.add(0, andMeasure);
        return andMeasure;
    }
}
