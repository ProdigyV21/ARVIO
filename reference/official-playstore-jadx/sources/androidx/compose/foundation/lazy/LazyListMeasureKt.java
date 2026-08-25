package androidx.compose.foundation.lazy;

import androidx.compose.foundation.layout.Arrangement;
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
import x7.g;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u0095\u0002\u0010/\u001a\u00020,2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00000\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00002\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00000\u00102\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010\"\u001a\u00020!2\u0006\u0010$\u001a\u00020#2/\u0010+\u001a+\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0000\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(0&¢\u0006\u0002\b)\u0012\u0004\u0012\u00020*0%H\u0000ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001ac\u00105\u001a\b\u0012\u0004\u0012\u0002010\u00102\f\u00102\u001a\b\u0012\u0004\u0012\u000201002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u00002\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00000\u00102\u0006\u00103\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u000e2\b\u00104\u001a\u0004\u0018\u00010\u001fH\u0002¢\u0006\u0004\b5\u00106\u001a;\u00108\u001a\b\u0012\u0004\u0012\u0002010\u00102\u0006\u00107\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00002\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00000\u0010H\u0002¢\u0006\u0004\b8\u00109\u001a\u0093\u0001\u0010B\u001a\b\u0012\u0004\u0012\u000201002\f\u0010:\u001a\b\u0012\u0004\u0012\u0002010\u00102\f\u0010;\u001a\b\u0012\u0004\u0012\u0002010\u00102\f\u0010<\u001a\b\u0012\u0004\u0012\u0002010\u00102\u0006\u0010=\u001a\u00020\u00002\u0006\u0010>\u001a\u00020\u00002\u0006\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\bB\u0010C\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006D"}, d2 = {"", "itemsCount", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "measuredItemProvider", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenItems", "firstVisibleItemIndex", "firstVisibleItemScrollOffset", "", "scrollToBeConsumed", "Landroidx/compose/ui/unit/Constraints;", "constraints", "", "isVertical", "", "headerIndexes", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "horizontalArrangement", "reverseLayout", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/foundation/lazy/LazyListItemAnimator;", "itemAnimator", "beyondBoundsItemCount", "pinnedItems", "hasLookaheadPassOccurred", "isLookingAhead", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "postLookaheadLayoutInfo", "Lka/k0;", "coroutineScope", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "placementScopeInvalidator", "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "Lx6/n;", "Landroidx/compose/ui/layout/MeasureResult;", TtmlNode.TAG_LAYOUT, "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "measureLazyList-5IMabDg", "(ILandroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;IIIIIIFJZLjava/util/List;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/LazyListItemAnimator;ILjava/util/List;ZZLandroidx/compose/foundation/lazy/LazyListLayoutInfo;Lka/k0;Landroidx/compose/runtime/MutableState;Lr7/q;)Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "measureLazyList", "", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "visibleItems", "consumedScroll", "lastPostLookaheadLayoutInfo", "createItemsAfterList", "(Ljava/util/List;Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;IILjava/util/List;FZLandroidx/compose/foundation/lazy/LazyListLayoutInfo;)Ljava/util/List;", "currentFirstItemIndex", "createItemsBeforeList", "(ILandroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;ILjava/util/List;)Ljava/util/List;", "items", "extraItemsBefore", "extraItemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "itemsScrollOffset", "calculateItemsOffsets", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;IIIIIZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;)Ljava/util/List;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyListMeasureKt {
    private static final List<LazyListMeasuredItem> calculateItemsOffsets(List<LazyListMeasuredItem> list, List<LazyListMeasuredItem> list2, List<LazyListMeasuredItem> list3, int i10, int i11, int i12, int i13, int i14, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z5, Density density) {
        int i15 = z ? i11 : i10;
        boolean z10 = i12 < Math.min(i15, i13);
        if (z10 && i14 != 0) {
            throw new IllegalStateException("non-zero itemsScrollOffset");
        }
        ArrayList arrayList = new ArrayList(list3.size() + list2.size() + list.size());
        if (!z10) {
            int size = list2.size();
            int sizeWithSpacings = i14;
            for (int i16 = 0; i16 < size; i16++) {
                LazyListMeasuredItem lazyListMeasuredItem = list2.get(i16);
                sizeWithSpacings -= lazyListMeasuredItem.getSizeWithSpacings();
                lazyListMeasuredItem.position(sizeWithSpacings, i10, i11);
                arrayList.add(lazyListMeasuredItem);
            }
            int size2 = list.size();
            int sizeWithSpacings2 = i14;
            for (int i17 = 0; i17 < size2; i17++) {
                LazyListMeasuredItem lazyListMeasuredItem2 = list.get(i17);
                lazyListMeasuredItem2.position(sizeWithSpacings2, i10, i11);
                arrayList.add(lazyListMeasuredItem2);
                sizeWithSpacings2 += lazyListMeasuredItem2.getSizeWithSpacings();
            }
            int size3 = list3.size();
            for (int i18 = 0; i18 < size3; i18++) {
                LazyListMeasuredItem lazyListMeasuredItem3 = list3.get(i18);
                lazyListMeasuredItem3.position(sizeWithSpacings2, i10, i11);
                arrayList.add(lazyListMeasuredItem3);
                sizeWithSpacings2 += lazyListMeasuredItem3.getSizeWithSpacings();
            }
        } else {
            if (!list2.isEmpty() || !list3.isEmpty()) {
                throw new IllegalArgumentException("no extra items");
            }
            int size4 = list.size();
            int[] iArr = new int[size4];
            for (int i19 = 0; i19 < size4; i19++) {
                iArr[i19] = list.get(calculateItemsOffsets$reverseAware(i19, z5, size4)).getSize();
            }
            int[] iArr2 = new int[size4];
            for (int i20 = 0; i20 < size4; i20++) {
                iArr2[i20] = 0;
            }
            if (z) {
                if (vertical == null) {
                    throw new IllegalArgumentException("null verticalArrangement when isVertical == true");
                }
                vertical.arrange(density, i15, iArr, iArr2);
            } else {
                if (horizontal == null) {
                    throw new IllegalArgumentException("null horizontalArrangement when isVertical == false");
                }
                horizontal.arrange(density, i15, iArr, LayoutDirection.Ltr, iArr2);
            }
            i iVarA0 = r.a0(iArr2);
            g gVarP = iVarA0;
            if (z5) {
                gVarP = d.P(iVarA0);
            }
            int i21 = gVarP.f22619i;
            int i22 = gVarP.f22620l;
            int i23 = gVarP.f22621m;
            if ((i23 > 0 && i21 <= i22) || (i23 < 0 && i22 <= i21)) {
                while (true) {
                    int size5 = iArr2[i21];
                    LazyListMeasuredItem lazyListMeasuredItem4 = list.get(calculateItemsOffsets$reverseAware(i21, z5, size4));
                    if (z5) {
                        size5 = (i15 - size5) - lazyListMeasuredItem4.getSize();
                    }
                    lazyListMeasuredItem4.position(size5, i10, i11);
                    arrayList.add(lazyListMeasuredItem4);
                    if (i21 == i22) {
                        break;
                    }
                    i21 += i23;
                }
            }
        }
        return arrayList;
    }

    private static final int calculateItemsOffsets$reverseAware(int i10, boolean z, int i11) {
        return !z ? i10 : (i11 - i10) - 1;
    }

    private static final List<LazyListMeasuredItem> createItemsAfterList(List<LazyListMeasuredItem> list, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i10, int i11, List<Integer> list2, float f10, boolean z, LazyListLayoutInfo lazyListLayoutInfo) {
        LazyListItemInfo lazyListItemInfo;
        LazyListMeasuredItem lazyListMeasuredItem;
        LazyListMeasuredItem lazyListMeasuredItem2;
        int sizeWithSpacings;
        LazyListMeasuredItem lazyListMeasuredItem3;
        int index;
        int iMin;
        LazyListMeasuredItem lazyListMeasuredItem4;
        LazyListMeasuredItem lazyListMeasuredItem5;
        int i12 = i10 - 1;
        int iMin2 = Math.min(((LazyListMeasuredItem) x.w0(list)).getIndex() + i11, i12);
        int index2 = ((LazyListMeasuredItem) x.w0(list)).getIndex() + 1;
        ArrayList arrayList = null;
        if (index2 <= iMin2) {
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyListMeasuredItemProvider.getAndMeasure(index2));
                if (index2 == iMin2) {
                    break;
                }
                index2++;
            }
        }
        if (z && lazyListLayoutInfo != null && !lazyListLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            List<LazyListItemInfo> visibleItemsInfo = lazyListLayoutInfo.getVisibleItemsInfo();
            int size = visibleItemsInfo.size();
            while (true) {
                size--;
                if (-1 >= size) {
                    lazyListItemInfo = null;
                    break;
                }
                if (visibleItemsInfo.get(size).getIndex() > iMin2 && (size == 0 || visibleItemsInfo.get(size - 1).getIndex() <= iMin2)) {
                    break;
                }
            }
            lazyListItemInfo = visibleItemsInfo.get(size);
            LazyListItemInfo lazyListItemInfo2 = (LazyListItemInfo) x.w0(lazyListLayoutInfo.getVisibleItemsInfo());
            if (lazyListItemInfo != null && (index = lazyListItemInfo.getIndex()) <= (iMin = Math.min(lazyListItemInfo2.getIndex(), i12))) {
                while (true) {
                    if (arrayList != null) {
                        int size2 = arrayList.size();
                        int i13 = 0;
                        while (true) {
                            if (i13 >= size2) {
                                lazyListMeasuredItem5 = null;
                                break;
                            }
                            lazyListMeasuredItem5 = arrayList.get(i13);
                            if (lazyListMeasuredItem5.getIndex() == index) {
                                break;
                            }
                            i13++;
                        }
                        lazyListMeasuredItem4 = lazyListMeasuredItem5;
                    } else {
                        lazyListMeasuredItem4 = null;
                    }
                    if (lazyListMeasuredItem4 == null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(lazyListMeasuredItemProvider.getAndMeasure(index));
                    }
                    if (index == iMin) {
                        break;
                    }
                    index++;
                }
            }
            float viewportEndOffset = ((lazyListLayoutInfo.getViewportEndOffset() - lazyListItemInfo2.getOffset()) - lazyListItemInfo2.getSize()) - f10;
            if (viewportEndOffset > 0.0f) {
                int index3 = lazyListItemInfo2.getIndex() + 1;
                int i14 = 0;
                while (index3 < i10 && i14 < viewportEndOffset) {
                    if (index3 <= iMin2) {
                        int size3 = list.size();
                        int i15 = 0;
                        while (true) {
                            if (i15 >= size3) {
                                lazyListMeasuredItem3 = null;
                                break;
                            }
                            lazyListMeasuredItem3 = list.get(i15);
                            if (lazyListMeasuredItem3.getIndex() == index3) {
                                break;
                            }
                            i15++;
                        }
                        lazyListMeasuredItem = lazyListMeasuredItem3;
                    } else if (arrayList != null) {
                        int size4 = arrayList.size();
                        int i16 = 0;
                        while (true) {
                            if (i16 >= size4) {
                                lazyListMeasuredItem2 = null;
                                break;
                            }
                            lazyListMeasuredItem2 = arrayList.get(i16);
                            if (lazyListMeasuredItem2.getIndex() == index3) {
                                break;
                            }
                            i16++;
                        }
                        lazyListMeasuredItem = lazyListMeasuredItem2;
                    } else {
                        lazyListMeasuredItem = null;
                    }
                    if (lazyListMeasuredItem != null) {
                        index3++;
                        sizeWithSpacings = lazyListMeasuredItem.getSizeWithSpacings();
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(lazyListMeasuredItemProvider.getAndMeasure(index3));
                        index3++;
                        sizeWithSpacings = ((LazyListMeasuredItem) x.w0(arrayList)).getSizeWithSpacings();
                    }
                    i14 += sizeWithSpacings;
                }
            }
        }
        if (arrayList != null && ((LazyListMeasuredItem) x.w0(arrayList)).getIndex() > iMin2) {
            iMin2 = ((LazyListMeasuredItem) x.w0(arrayList)).getIndex();
        }
        int size5 = list2.size();
        for (int i17 = 0; i17 < size5; i17++) {
            int iIntValue = list2.get(i17).intValue();
            if (iIntValue > iMin2) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyListMeasuredItemProvider.getAndMeasure(iIntValue));
            }
        }
        return arrayList == null ? z.f19728i : arrayList;
    }

    private static final List<LazyListMeasuredItem> createItemsBeforeList(int i10, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i11, List<Integer> list) {
        int iMax = Math.max(0, i10 - i11);
        int i12 = i10 - 1;
        ArrayList arrayList = null;
        if (iMax <= i12) {
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyListMeasuredItemProvider.getAndMeasure(i12));
                if (i12 == iMax) {
                    break;
                }
                i12--;
            }
        }
        int size = list.size() - 1;
        if (size >= 0) {
            while (true) {
                int i13 = size - 1;
                int iIntValue = list.get(size).intValue();
                if (iIntValue < iMax) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(lazyListMeasuredItemProvider.getAndMeasure(iIntValue));
                }
                if (i13 < 0) {
                    break;
                }
                size = i13;
            }
        }
        return arrayList == null ? z.f19728i : arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:95:0x01f0, code lost:
    
        r21 = r2;
     */
    /* JADX INFO: renamed from: measureLazyList-5IMabDg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.foundation.lazy.LazyListMeasureResult m636measureLazyList5IMabDg(int r26, androidx.compose.foundation.lazy.LazyListMeasuredItemProvider r27, int r28, int r29, int r30, int r31, int r32, int r33, float r34, long r35, boolean r37, java.util.List<java.lang.Integer> r38, androidx.compose.foundation.layout.Arrangement.Vertical r39, androidx.compose.foundation.layout.Arrangement.Horizontal r40, boolean r41, androidx.compose.ui.unit.Density r42, androidx.compose.foundation.lazy.LazyListItemAnimator r43, int r44, java.util.List<java.lang.Integer> r45, boolean r46, boolean r47, androidx.compose.foundation.lazy.LazyListLayoutInfo r48, ka.k0 r49, androidx.compose.runtime.MutableState<x6.t0> r50, r7.q<? super java.lang.Integer, ? super java.lang.Integer, ? super r7.l<? super androidx.compose.ui.layout.Placeable.PlacementScope, x6.t0>, ? extends androidx.compose.ui.layout.MeasureResult> r51) {
        /*
            Method dump skipped, instruction units count: 864
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListMeasureKt.m636measureLazyList5IMabDg(int, androidx.compose.foundation.lazy.LazyListMeasuredItemProvider, int, int, int, int, int, int, float, long, boolean, java.util.List, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, boolean, androidx.compose.ui.unit.Density, androidx.compose.foundation.lazy.LazyListItemAnimator, int, java.util.List, boolean, boolean, androidx.compose.foundation.lazy.LazyListLayoutInfo, ka.k0, androidx.compose.runtime.MutableState, r7.q):androidx.compose.foundation.lazy.LazyListMeasureResult");
    }
}
