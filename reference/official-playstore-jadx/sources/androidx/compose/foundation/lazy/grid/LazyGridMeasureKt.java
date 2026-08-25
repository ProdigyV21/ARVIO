package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.collections.z;
import qb.d;
import r7.l;
import x7.g;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0003\u001aõ\u0001\u0010-\u001a\u00020*2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00000\u001d2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020!2/\u0010)\u001a+\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0000\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0$¢\u0006\u0002\b'\u0012\u0004\u0012\u00020(0#H\u0000ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001aT\u00101\u001a\b\u0012\u0004\u0012\u0002000\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00000\u001d2\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e0$2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00100$H\u0083\b¢\u0006\u0004\b1\u00102\u001a\u0093\u0001\u0010=\u001a\b\u0012\u0004\u0012\u0002000<2\f\u00104\u001a\b\u0012\u0004\u0012\u0002030\u001d2\f\u00105\u001a\b\u0012\u0004\u0012\u0002000\u001d2\f\u00106\u001a\b\u0012\u0004\u0012\u0002000\u001d2\u0006\u00107\u001a\u00020\u00002\u0006\u00108\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020\u00002\u0006\u0010;\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b=\u0010>\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006?"}, d2 = {"", "itemsCount", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;", "measuredLineProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;", "measuredItemProvider", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenLines", "firstVisibleLineIndex", "firstVisibleLineScrollOffset", "", "scrollToBeConsumed", "Landroidx/compose/ui/unit/Constraints;", "constraints", "", "isVertical", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "horizontalArrangement", "reverseLayout", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;", "placementAnimator", "Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;", "spanLayoutProvider", "", "pinnedItems", "Lka/k0;", "coroutineScope", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "placementScopeInvalidator", "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "Lx6/n;", "Landroidx/compose/ui/layout/MeasureResult;", TtmlNode.TAG_LAYOUT, "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "measureLazyGrid-W2FL7xs", "(ILandroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;IIIIIIFJZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;Ljava/util/List;Lka/k0;Landroidx/compose/runtime/MutableState;Lr7/q;)Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "measureLazyGrid", "itemConstraints", "filter", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "calculateExtraItems", "(Ljava/util/List;Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;Lr7/l;Lr7/l;)Ljava/util/List;", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLine;", "lines", "itemsBefore", "itemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "firstLineScrollOffset", "", "calculateItemsOffsets", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;IIIIIZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;)Ljava/util/List;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyGridMeasureKt {
    private static final List<LazyGridMeasuredItem> calculateExtraItems(List<Integer> list, LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, l<? super Integer, Constraints> lVar, l<? super Integer, Boolean> lVar2) {
        LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider2;
        int size = list.size();
        ArrayList arrayList = null;
        int i10 = 0;
        while (i10 < size) {
            int iIntValue = list.get(i10).intValue();
            if (((Boolean) lVar2.invoke(Integer.valueOf(iIntValue))).booleanValue()) {
                lazyGridMeasuredItemProvider2 = lazyGridMeasuredItemProvider;
                LazyGridMeasuredItem lazyGridMeasuredItemM668getAndMeasure3p2s80s$default = LazyGridMeasuredItemProvider.m668getAndMeasure3p2s80s$default(lazyGridMeasuredItemProvider2, iIntValue, 0, ((Constraints) lVar.invoke(Integer.valueOf(iIntValue))).getValue(), 2, null);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyGridMeasuredItemM668getAndMeasure3p2s80s$default);
            } else {
                lazyGridMeasuredItemProvider2 = lazyGridMeasuredItemProvider;
            }
            i10++;
            lazyGridMeasuredItemProvider = lazyGridMeasuredItemProvider2;
        }
        return arrayList == null ? z.f19728i : arrayList;
    }

    private static final List<LazyGridMeasuredItem> calculateItemsOffsets(List<LazyGridMeasuredLine> list, List<LazyGridMeasuredItem> list2, List<LazyGridMeasuredItem> list3, int i10, int i11, int i12, int i13, int i14, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z5, Density density) {
        int i15 = i11;
        int i16 = z ? i15 : i10;
        int i17 = 0;
        boolean z10 = i12 < Math.min(i16, i13);
        if (z10 && i14 != 0) {
            throw new IllegalStateException("non-zero firstLineScrollOffset");
        }
        int size = list.size();
        int length = 0;
        for (int i18 = 0; i18 < size; i18++) {
            length += list.get(i18).getItems().length;
        }
        ArrayList arrayList = new ArrayList(length);
        if (!z10) {
            int size2 = list2.size() - 1;
            if (size2 >= 0) {
                int mainAxisSizeWithSpacings = i14;
                while (true) {
                    int i19 = size2 - 1;
                    LazyGridMeasuredItem lazyGridMeasuredItem = list2.get(size2);
                    mainAxisSizeWithSpacings -= lazyGridMeasuredItem.getMainAxisSizeWithSpacings();
                    LazyGridMeasuredItem.position$default(lazyGridMeasuredItem, mainAxisSizeWithSpacings, 0, i10, i15, 0, 0, 48, null);
                    arrayList.add(lazyGridMeasuredItem);
                    if (i19 < 0) {
                        break;
                    }
                    size2 = i19;
                }
            }
            int size3 = list.size();
            int mainAxisSizeWithSpacings2 = i14;
            for (int i20 = 0; i20 < size3; i20++) {
                LazyGridMeasuredLine lazyGridMeasuredLine = list.get(i20);
                x.c0(arrayList, lazyGridMeasuredLine.position(mainAxisSizeWithSpacings2, i10, i15));
                mainAxisSizeWithSpacings2 += lazyGridMeasuredLine.getMainAxisSizeWithSpacings();
            }
            int size4 = list3.size();
            int mainAxisSizeWithSpacings3 = mainAxisSizeWithSpacings2;
            while (i17 < size4) {
                LazyGridMeasuredItem lazyGridMeasuredItem2 = list3.get(i17);
                LazyGridMeasuredItem.position$default(lazyGridMeasuredItem2, mainAxisSizeWithSpacings3, 0, i10, i15, 0, 0, 48, null);
                arrayList.add(lazyGridMeasuredItem2);
                mainAxisSizeWithSpacings3 += lazyGridMeasuredItem2.getMainAxisSizeWithSpacings();
                i17++;
                i15 = i11;
            }
        } else {
            if (!list2.isEmpty() || !list3.isEmpty()) {
                throw new IllegalArgumentException("no items");
            }
            int size5 = list.size();
            int[] iArr = new int[size5];
            for (int i21 = 0; i21 < size5; i21++) {
                iArr[i21] = list.get(calculateItemsOffsets$reverseAware(i21, z5, size5)).getMainAxisSize();
            }
            int[] iArr2 = new int[size5];
            for (int i22 = 0; i22 < size5; i22++) {
                iArr2[i22] = 0;
            }
            if (z) {
                if (vertical == null) {
                    throw new IllegalArgumentException("null verticalArrangement");
                }
                vertical.arrange(density, i16, iArr, iArr2);
            } else {
                if (horizontal == null) {
                    throw new IllegalArgumentException("null horizontalArrangement");
                }
                horizontal.arrange(density, i16, iArr, LayoutDirection.Ltr, iArr2);
            }
            i iVarA0 = r.a0(iArr2);
            g gVarP = iVarA0;
            if (z5) {
                gVarP = d.P(iVarA0);
            }
            int i23 = gVarP.f22619i;
            int i24 = gVarP.f22620l;
            int i25 = gVarP.f22621m;
            if ((i25 > 0 && i23 <= i24) || (i25 < 0 && i24 <= i23)) {
                while (true) {
                    int mainAxisSize = iArr2[i23];
                    LazyGridMeasuredLine lazyGridMeasuredLine2 = list.get(calculateItemsOffsets$reverseAware(i23, z5, size5));
                    if (z5) {
                        mainAxisSize = (i16 - mainAxisSize) - lazyGridMeasuredLine2.getMainAxisSize();
                    }
                    x.c0(arrayList, lazyGridMeasuredLine2.position(mainAxisSize, i10, i15));
                    if (i23 == i24) {
                        break;
                    }
                    i23 += i25;
                }
            }
        }
        return arrayList;
    }

    private static final int calculateItemsOffsets$reverseAware(int i10, boolean z, int i11) {
        return !z ? i10 : (i11 - i10) - 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:88:0x019c  */
    /* JADX INFO: renamed from: measureLazyGrid-W2FL7xs, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.foundation.lazy.grid.LazyGridMeasureResult m665measureLazyGridW2FL7xs(int r37, androidx.compose.foundation.lazy.grid.LazyGridMeasuredLineProvider r38, androidx.compose.foundation.lazy.grid.LazyGridMeasuredItemProvider r39, int r40, int r41, int r42, int r43, int r44, int r45, float r46, long r47, boolean r49, androidx.compose.foundation.layout.Arrangement.Vertical r50, androidx.compose.foundation.layout.Arrangement.Horizontal r51, boolean r52, androidx.compose.ui.unit.Density r53, androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator r54, androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider r55, java.util.List<java.lang.Integer> r56, ka.k0 r57, androidx.compose.runtime.MutableState<x6.t0> r58, r7.q<? super java.lang.Integer, ? super java.lang.Integer, ? super r7.l<? super androidx.compose.ui.layout.Placeable.PlacementScope, x6.t0>, ? extends androidx.compose.ui.layout.MeasureResult> r59) {
        /*
            Method dump skipped, instruction units count: 818
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt.m665measureLazyGridW2FL7xs(int, androidx.compose.foundation.lazy.grid.LazyGridMeasuredLineProvider, androidx.compose.foundation.lazy.grid.LazyGridMeasuredItemProvider, int, int, int, int, int, int, float, long, boolean, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, boolean, androidx.compose.ui.unit.Density, androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator, androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider, java.util.List, ka.k0, androidx.compose.runtime.MutableState, r7.q):androidx.compose.foundation.lazy.grid.LazyGridMeasureResult");
    }
}
